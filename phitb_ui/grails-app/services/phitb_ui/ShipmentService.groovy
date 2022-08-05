package phitb_ui

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class ShipmentService {

    def shipmentServiceStatus() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().SHIPMENT_SERVICE_STATUS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ShipementService , action :  shipmentServiceStatus  , Ex:' + ex)
            log.error('Service :ShipementService , action :  shipmentServiceStatus  , Ex:' + ex)
        }

    }


//    Transport Type
    def saveTransportType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TRANSPORT_TYPE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def showTransportType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        //WebTarget target = client.target("http://localhost:8082")
        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORT_TYPE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }


    def putTransportType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORT_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTransportType(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORT_TYPE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :shipmentService , action :  delete  , Ex:' + ex)
            log.error('Service :shipmentService , action :  delete  , Ex:' + ex)
        }

    }

    def getTransportTypeList() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORT_TYPE_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :shipmentService , action :  getStateList  , Ex:' + ex)
            log.error('Service :shipmentService , action :  getStateList  , Ex:' + ex)
        }

    }

//    Vehicle Detail
    def saveVehicleDetail(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().VECHILE_DETAIL_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def showVehicleDetail(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().VECHILE_DETAIL_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }


    def putVehicleDetail(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().VECHILE_DETAIL_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteVehicleDetail(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().VECHILE_DETAIL_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :shipmentService , action :  delete  , Ex:' + ex)
            log.error('Service :shipmentService , action :  delete  , Ex:' + ex)
        }

    }

    def getVehicleDetailList() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().VECHILE_DETAIL_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :shipmentService , action :  getStateList  , Ex:' + ex)
            log.error('Service :shipmentService , action :  getStateList  , Ex:' + ex)
        }

    }

//=========================================//

    //Transporter
    def saveTransporter(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().TRANSPORTER_TYPE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  saveTransporter  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  saveTransporter  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def showTransporter(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        //WebTarget target = client.target("http://localhost:8082")
        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORTER_TYPE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :getAccountModes , action :  showTransporter  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  showTransporter  , Ex:' + ex)
        }

    }

    def getAllTransporterByEntity(String entityId) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        //WebTarget target = client.target("http://localhost:8082")
        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORTER_TYPE_SHOW_BY_ENTITY + "/"+entityId)
                    .request().get()

            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :shipmentService , action :  getAllTransporterByEntity  , Ex:' + ex)
            log.error('Service :shipmentService , action :  getAllTransporterByEntity  , Ex:' + ex)
        }

    }


    def updateTransporter(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORTER_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service : , action :  updateTransporter  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  updateTransporter  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteTransporter(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORTER_TYPE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :shipmentService , action :  deleteTransporter  , Ex:' + ex)
            log.error('Service :shipmentService , action :  deleteTransporter  , Ex:' + ex)
        }

    }

    def getTransporterList() {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)

        try {
            Response apiResponse = target
                    .path(new Links().TRANSPORTER_TYPE_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :shipmentService , action :  getTransporterList  , Ex:' + ex)
            log.error('Service :shipmentService , action :  getTransporterList  , Ex:' + ex)
        }


    }
}
