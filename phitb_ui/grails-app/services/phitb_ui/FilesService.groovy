package phitb_ui

import grails.gorm.transactions.Transactional
import org.glassfish.jersey.media.multipart.FormDataBodyPart
import org.glassfish.jersey.media.multipart.FormDataMultiPart
import org.glassfish.jersey.media.multipart.MultiPart
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart
import org.grails.web.json.JSONObject
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

        String name = uploadFileDetails.get("entityId").toString() + "-"+new Date().format("ddMMyyyyHHmmss") +"-" + multipartFile.getOriginalFilename()
        File file = new File(name); // create a new file
        multipartFile.transferTo(file);


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
        multiPart.bodyPart(new FormDataBodyPart("entityId",uploadFileDetails.get("entityId").toString()))
        multiPart.bodyPart(new FormDataBodyPart("userId",uploadFileDetails.get("userId").toString()))
        multiPart.bodyPart(new FormDataBodyPart("docNumber",docNumber))
        multiPart.bodyPart(new FormDataBodyPart("docType",docType))
        multiPart.bodyPart(new FormDataBodyPart("description",description))
        multiPart.bodyPart(new FileDataBodyPart ("file", file, MediaType.APPLICATION_OCTET_STREAM_TYPE))

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        //WebTarget target = client.target("http://localhost:8080/");
        try {
            Response apiResponse = target
                    .path(new Links().FILE_UPLOAD)
                    .request()
                    .post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA_TYPE))
            if(apiResponse.status == 200)
            {
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
            file.delete()
        }
    }
}
