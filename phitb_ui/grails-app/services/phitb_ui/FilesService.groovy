package phitb_ui

import grails.gorm.transactions.Transactional
import org.apache.commons.io.IOUtils
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

import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;

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

        WebTarget target = client.target(new Links().API_GATEWAY);
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
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().FILE_DOWNLOAD)
                    .queryParam("fileName",fileName)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                    .get()
            if(apiResponse.status == 200) {
                InputStream input = apiResponse.readEntity(InputStream.class);
                byte[] fileBytes = IOUtils.toByteArray(input); // IOUtils is from Apache Commons
                File file = File.createTempFile("tmp-",fileName)
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileBytes);
                fos.flush();
                fos.close()
                return file
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
        WebTarget target = client.target(new Links().API_GATEWAY);
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

    JSONObject uploadCanvasimage(MultipartFile multipartFile, JSONObject uploadFileDetails) {

        String extension = ".jpg"
        String originalFilename = multipartFile.originalFilename
        if (originalFilename) {
            int lastDotIndex = originalFilename.lastIndexOf('.')
            if (lastDotIndex >= 0 && lastDotIndex < originalFilename.length() - 1) {
                extension = "."+originalFilename.substring(lastDotIndex + 1).toLowerCase()
            }
        }
        // Assuming multipartFile is the actual file to be uploaded.
        String type = uploadFileDetails.getString("type");
        String entityId = uploadFileDetails.getString("entityId");
//        String filename = uploadFileDetails.get("file");

//        // Assuming you need to pass the file content as 'file' form-data part
//        FormDataMultiPart multiPart = new FormDataMultiPart()
//                .field("entityId", entityId)
//                .field("type", type)
//                .field("filename", filename)
//                .bodyPart(new StreamDataBodyPart("file", multipartFile.getInputStream(), filename));

        MultiPart multiPart = new MultiPart()
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE)
        multiPart.bodyPart(new FormDataBodyPart("entityId",uploadFileDetails.get("entityId").toString()))
        multiPart.bodyPart(new FormDataBodyPart("type",type))
        FormDataContentDisposition contentDisposition = FormDataContentDisposition
                .name("file")
                .fileName(multipartFile.getOriginalFilename()) // Ensure the filename is set here
                .build();

        FormDataBodyPart filePart = new FormDataBodyPart("file", multipartFile.getInputStream(),
                MediaType.APPLICATION_OCTET_STREAM_TYPE).contentDisposition(contentDisposition);
        multiPart.bodyPart(filePart);

        ClientConfig config = new ClientConfig();
        config.register(MultiPartFeature.class);
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target("http://localhost:8085");
        try {
            Response apiResponse = target
                    .path(new Links().FILE_UPLOAD_TO_FTP)
                    .request()
                    .post(Entity.entity(multiPart, multiPart.getMediaType()));

            if (apiResponse.getStatus() == 200) {
                String responseString = apiResponse.readEntity(String.class);
                JSONObject jsonObject = new JSONObject(responseString);
                return jsonObject;
            } else {
                // Handle the error response
                String errorResponse = apiResponse.readEntity(String.class);
                log.error("Error response from server: " + errorResponse);
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Service :FilesService , action : uploadFile , Ex:" + ex);
            log.error("Service :FilesService , action : uploadFile , Ex:" + ex);
            return null;
        } finally {
            // Cleanup resources
            multiPart.close();
        }
    }
}


