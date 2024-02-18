package phitb_ui.files

import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile
import phitb_ui.FilesService

class FilesController {

    def uploadFile() {

        MultipartFile multipartFile = params.file
        JSONObject uploadedFileDetails = new JSONObject()
        uploadedFileDetails.put("entityId", session.getAttribute("entityId").toString())
        uploadedFileDetails.put("userId", session.getAttribute("userId").toString())
        uploadedFileDetails.put("docNumber", params.docNumber)
        uploadedFileDetails.put("docType", params.docType)
        uploadedFileDetails.put("description", params.description)

        JSONObject jsonObject = new FilesService().uploadFile(multipartFile, uploadedFileDetails)
        if(jsonObject != null) {
            respond jsonObject, formats: ['json'], status: 200
        }
        else {
            response.status = 400
        }
    }

    def downloadFile()
    {
        String fileName = params.filename
        long entityId = session.getAttribute("entityId")
        File file = new FilesService().downloadFile(fileName, entityId)
        if(file != null)
        {
            render(file: file, contentType: getServletContext().getMimeType(file.getName()), fileName: fileName)
        }
        else
            response.status = 400
    }

    def deleteFile()
    {
        String fileName = params.filename
        long entityId = session.getAttribute("entityId")
        JSONObject jsonObject = new FilesService().deleteFile(fileName, entityId)
        if(jsonObject != null) {
            respond jsonObject, formats: ['json'], status: 200
        }
        else {
            response.status = 400
        }
    }

//    def savecanvasimagetoftp()
//    {
//        String entityId = session.getAttribute("entityId")?.toString()
//        JSONObject jsonObject = new JSONObject(params)
//        jsonObject.put("entityId",entityId)
//        JSONObject result = new FilesService().savecanvasimagetoftp(jsonObject)
//        if(jsonObject != null) {
//            respond jsonObject, formats: ['json'], status: 200
//        }
//        else {
//            response.status = 400
//        }
//    }

    def savecanvasimagetoftp() {

        MultipartFile multipartFile = params.file
        JSONObject uploadedFileDetails = new JSONObject()
        uploadedFileDetails.put("entityId", session.getAttribute("entityId").toString())
        uploadedFileDetails.put("type", params.type)
        uploadedFileDetails.put("file", multipartFile)

        JSONObject jsonObject = new FilesService().uploadCanvasimage(multipartFile, uploadedFileDetails)
        if(jsonObject != null) {
            respond jsonObject, formats: ['json'], status: 200
        }
        else {
            response.status = 400
        }
    }
}
