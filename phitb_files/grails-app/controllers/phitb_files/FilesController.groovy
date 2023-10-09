package phitb_files

import org.apache.catalina.connector.ClientAbortException
import org.springframework.web.multipart.MultipartFile

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
                            render(file: requestedFile, contentType: getServletContext().getMimeType(requestedFile.getName()))
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
}
