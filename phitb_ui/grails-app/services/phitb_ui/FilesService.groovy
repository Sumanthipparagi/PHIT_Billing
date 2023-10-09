package phitb_ui

import grails.gorm.transactions.Transactional
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.media.multipart.FormDataBodyPart
import org.glassfish.jersey.media.multipart.FormDataMultiPart
import org.glassfish.jersey.media.multipart.MultiPart
import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart
import org.grails.web.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.multipart.MultipartFile

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class FilesService {


    JSONObject uploadFile(MultipartFile multipartFile, JSONObject uploadFileDetails) {

        String extension = ".tmp"
        String originalFilename = multipartFile.originalFilename
        if (originalFilename) {
            int lastDotIndex = originalFilename.lastIndexOf('.')
            if (lastDotIndex >= 0 && lastDotIndex < originalFilename.length() - 1) {
                extension = "."+originalFilename.substring(lastDotIndex + 1).toLowerCase()
            }
        }

        // Create a temporary File from the MultipartFile
        File tempFile = File.createTempFile("temp", extension)
        multipartFile.transferTo(tempFile)


        String docNumber = "NA"
        if(uploadFileDetails.has("docNumber"))
            docNumber = uploadFileDetails.get("docNumber").toString()

        String docType = "NA"
        if(uploadFileDetails.has("docType"))
            docType = uploadFileDetails.get("docType").toString()

        String description = "NA"
        if(uploadFileDetails.has("description"))
            description = uploadFileDetails.get("description").toString()

        MultiPart multiPart = new MultiPart()
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE)
        multiPart.bodyPart(new FormDataBodyPart("entityId",uploadFileDetails.get("entityId").toString()))
        multiPart.bodyPart(new FormDataBodyPart("userId",uploadFileDetails.get("userId").toString()))
        multiPart.bodyPart(new FormDataBodyPart("docNumber",docNumber))
        multiPart.bodyPart(new FormDataBodyPart("docType",docType))
        multiPart.bodyPart(new FormDataBodyPart("description",description))
        FormDataBodyPart filePart = new FileDataBodyPart("file", tempFile, MediaType.APPLICATION_OCTET_STREAM_TYPE)
        multiPart.bodyPart(filePart)

        //WebTarget target = client.target(new Links().API_GATEWAY);
        ClientConfig config = new ClientConfig()
        // Configure Jersey Client to handle multipart requests
        config.register(MultiPartFeature.class)

        Client client = ClientBuilder.newClient(config)

        WebTarget target = client.target("http://localhost:8080/");
        try {
            Response apiResponse = target
                    .path(new Links().FILE_UPLOAD)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(multiPart, multiPart.getMediaType()))
            if(apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
            {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :FilesService , action :  uploadFile  , Ex:' + ex)
            log.error('Service :FilesService , action :  uploadFile  , Ex:' + ex)
            return null
        }
        finally {
            tempFile.delete()
        }
    }

    def downloadFile(String fileName, long entityId)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target("http://localhost:8080/");
        try {
            Response apiResponse = target
                    .path(new Links().FILE_DOWNLOAD)
                    .queryParam("fileName",fileName)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
            {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :FilesService , action :  uploadFile  , Ex:' + ex)
            log.error('Service :FilesService , action :  uploadFile  , Ex:' + ex)
            return null
        }
    }

    def deleteFile(String fileName, long entityId)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target("http://localhost:8080/");
        try {
            Response apiResponse = target
                    .path(new Links().FILE_DELETE)
                    .queryParam("fileName",fileName)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
            {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :FilesService , action :  uploadFile  , Ex:' + ex)
            log.error('Service :FilesService , action :  uploadFile  , Ex:' + ex)
            return null
        }
    }
}
