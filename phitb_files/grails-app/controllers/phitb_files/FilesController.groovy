package phitb_files

import grails.converters.JSON
import grails.web.servlet.mvc.GrailsParameterMap
import org.apache.catalina.connector.ClientAbortException
import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.Part
import javax.ws.rs.ClientErrorException
import java.text.SimpleDateFormat

class FilesController {

    static allowedMethods = [uploadFile: ['POST']]
    def index() { }

    def uploadFile()
    {
        MultipartFile multipartFile = params.file
        long entityId = Long.parseLong(params.entityId)
        long uploadedByUserId = Long.parseLong(params.userId)
        String docNumber = params.docNumber
        String docType = params.docType
        String description = params.description
        String name = ""
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmssSSS")
        String date = sdf.format(new Date())

        String folderName = File.separator + new Date().format("YYYY") + File.separator + new Date().format("MM") + File.separator + entityId + File.separator
        String fileName = ""
        if (multipartFile != null)
        {
            def fragments = multipartFile.getOriginalFilename().split("\\.")
            if ((multipartFile.size > 1) && (fragments[fragments.length - 1].equalsIgnoreCase("jpg"))
                    || (fragments[fragments.length - 1].equalsIgnoreCase("jpeg"))
                    || (fragments[fragments.length - 1].equalsIgnoreCase("png"))
                    || (fragments[fragments.length - 1].equalsIgnoreCase("pdf"))
                    || (fragments[fragments.length - 1].equalsIgnoreCase("doc"))
                    || (fragments[fragments.length - 1].equalsIgnoreCase("docx"))
            )
            {
                if ((fileName == null) || (fileName.equalsIgnoreCase("NA") || (fileName.trim() == '')))
                {
                    name = date + "." + fragments[fragments.length - 1]
                }
                else
                {
                    fileName = fileName?.split('\\.')[0]
                    name = fileName + "." + fragments[fragments.length - 1]
                }

                String uploadedFileLocation = new FilesService().getApplicationPath() + "documents" + folderName + name
                File objFile = new File(uploadedFileLocation)
                objFile.mkdirs()
                if (objFile.exists())
                {
                    objFile.delete()
                }
                InputStream ios = multipartFile.getInputStream()
                new FileOutputStream(uploadedFileLocation).leftShift(ios).close()
                ios.close();

                UploadedFile uploadedFile = new UploadedFile()
                uploadedFile.fileName = name
                uploadedFile.folderName = folderName
                uploadedFile.entityId = entityId
                uploadedFile.uploadedByUser = uploadedByUserId
                uploadedFile.docNumber = docNumber
                uploadedFile.docType = docType
                uploadedFile.description = description
                uploadedFile.save(flush:true)

                if(!uploadedFile.hasErrors())
                {
                    respond uploadedFile, formats: ['json'], status: 200
                    return
                }
                else {
                    response.status = 400
                    return
                }
            }
            else
            {
                response.status = 400
                return
            }
        }
        else
        {
            response.status = 400
            return
        }
    }

    def downloadFile()
    {
        //Security measure as recommended by the owasp zed tool
        response.setHeader("X-Content-Type-Options", "nosniff")
        response.addHeader("X-XSS-Protection", "1; mode=block")
        response.addHeader("X-Frame-Options", "SAMEORIGIN")

        try
        {
            String fileName = params.fileName
            long entityId = Long.parseLong(params.entityId)
            UploadedFile uploadedFile = UploadedFile.findByFileNameAndEntityId(fileName, entityId)
            if(uploadedFile != null)
            {
                String fileLocation = new FilesService().getApplicationPath() + "documents" + File.separator + uploadedFile.folderName + uploadedFile.fileName
                File requestedFile = new File(fileLocation)
                println("File Controller: Complete path requested file: " + requestedFile.absolutePath)

                if (requestedFile.isDirectory())
                {
                    System.out.println("Requested for folder, instead of file")
                    render(text: "No Such File.")
                }
                else
                {
                    try
                    {
                        if (requestedFile.exists())
                        {
                            render(file: requestedFile, contentType: getServletContext().getMimeType(requestedFile.getName()), fileName: fileName)
                        }
                        else
                        {
                            response.status = 400
                        }
                    }
                    catch (ClientAbortException cl)
                    {
                        println("FileLocationController:: " + cl.message)
                        response.status = 400
                    }
                    catch (ClientErrorException ce)
                    {
                        println("FileLocationController:: " + ce.message)
                        response.status = 400
                    }
                    catch (Exception ex)
                    {
                        println("FileLocationController:: " + ex.message)
                        response.status = 400
                    }
                }
            }
            else
            {
                response.status = 400
            }

        }
        catch (Exception ex)
        {
            println("FileLocationController:: " + ex.message)
            render(text: "No Such File.")
        }
    }

    def deleteFile()
    {
        String fileName = params.fileName
        long entityId = Long.parseLong(params.entityId)
        UploadedFile uploadedFile = UploadedFile.findByFileNameAndEntityId(fileName, entityId)
        if(uploadedFile != null)
        {
            String fileLocation = new FilesService().getApplicationPath() + "documents" + File.separator + uploadedFile.folderName + uploadedFile.fileName
            File file = new File(fileLocation)
            if(file.exists())
            {
                file.delete()
            }
            uploadedFile.deletedTime = new Date()
            uploadedFile.save(flush:true)
            //remove it
            uploadedFile.delete()

            respond uploadedFile, formats: ['json'], status: 200
        }
        else
        {
            response.status = 400
        }
    }

    def saveCanvasImage() {
        try {
//            GrailsParameterMap parameterMap = getParams()
            JSONObject paramsJsonObject = new JSONObject(params)
            String type = paramsJsonObject.get("type")
            def entity = paramsJsonObject.get("entityId").toString()


            if (entity) {
                String result = ""
                boolean fileProcessed = false

                for (Part part : request.getParts()) {
                    String fileName = part.getSubmittedFileName()

                    if (fileName) {
                        long fileSize = part.getSize()
                        InputStream inputStream = part.getInputStream()
                        if (result.isEmpty()) {
                            result = FilesService.uploadFileDynamicallyToFTP(type, inputStream, fileName, entity)
                        } else {
                            result += "," + FilesService.uploadFileDynamicallyToFTP(type, inputStream, fileName, entity)
                        }
                        fileProcessed = true
                    }
                }

                if (fileProcessed) {
                    def responseData = [
                            'results': 'success',
                            'file'   : result
                    ]
                    render responseData as JSON
                } else {
                    def responseData = [
                            'results': 'error',
                            'message': 'No files processed'
                    ]
                    render responseData as JSON
                }
            } else {
                def responseData = [
                        'results': 'error',
                        'message': 'EntityId or DivisionId is missing'
                ]
                render responseData as JSON
            }
        } catch (Exception ex) {
            log.error("Error uploading file: ${ex.message}", ex)
            def responseData = [
                    'results': 'error',
                    'message': ex.message
            ]
            render responseData as JSON
        }
    }
}
