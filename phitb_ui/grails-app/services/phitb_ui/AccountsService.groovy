package phitb_ui

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Form
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class AccountsService {

    //Bank Register
    def saveBankRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  saveBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  saveBankRegister  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showBankRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  showBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  showBankRegister  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putBankRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service: AccountsService , action :  putBankRegister  , Ex:' + ex)
            log.error('Service: AccountsService , action :  putBankRegister  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteBankRegister(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  deleteBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  deleteBankRegister  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Recipt Detail
    def saveRecipt(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Recipt Detail
    def savePaymentDetail(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
//        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PAYMENT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def putRecipt(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_UPDATE)
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
        finally{
            client.close()
        }
    }

    def showRecipt(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_DATATABLE)
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

    def showPayments(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getBanks() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getWallet() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().WALLET_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    def getEntityById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

//   get Invoice to Unsettled
    def getUnSaleBillCustomerId(String id, String entityId, String financialYear) {
        Client client = ClientBuilder.newClient()
         WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_UNSETTLED + "/" + id)
                    .queryParam("entityId", entityId)
                    .queryParam("financialYear", financialYear)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    //    get Credit Note to settled
    def getCNUnsettledCustomerId(String entityId, String financialYear) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().CREDIT_UNSETTLED + "/" + entityId) //TODO: To be changed
                    .queryParam("entityId", entityId)
                    .queryParam("financialYear", financialYear)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  getCNUnsettledCustomerId  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getCNUnsettledCustomerId  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    //    get invoice to settled
    def getSaleBillSettledCustomerId(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SETTLED + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    //    get Credit Note to settled
    def getCNsettledCustomerId(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().CREDIT_SETTLED + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

//   move invoice to settled vocher
    def updateSettledVocher(JSONObject jsonObject) {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SET_PAYMENT_BILL)
                    .resolveTemplate("id", jsonObject.id)
                    .resolveTemplate("type", "settled")
                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

//   move invoice to Unsettled vocher
    def updateunSettledVocher(JSONObject jsonObject) {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SET_PAYMENT_BILL)
                    .resolveTemplate("id", jsonObject.id)
                    .resolveTemplate("type", "unsettled")
                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    //   move Credit Note to settled vocher
    def updateCNSettledVocher(JSONObject jsonObject) {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SET_CREDIT_STATUS)
                    .resolveTemplate("id", jsonObject.id)
                    .resolveTemplate("type", "settled")
                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

//   move Credit Note to Unsettled vocher
    def updateCNunSettledVocher(JSONObject jsonObject) {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().SET_CREDIT_STATUS)
                    .resolveTemplate("id", jsonObject.id)
                    .resolveTemplate("type", "unsettled")
                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    def getReciptById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def getPaymentById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {

            Response apiResponse = target
                    .path(new Links().PAYMENT_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    //Credit JV
    def saveCreditJV(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().CREDIT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
            log.error('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def saveDebitJV(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DEBIT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
            log.error('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def creditJVDatatables(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().CREDIT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
            log.error('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def creditJvApprove(String status, long entityId, long approverId, String creditJvId, String debitBalance, String toBalance) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("status", status)
            jsonObject.put("entityId", entityId.toString())
            jsonObject.put("approverId", approverId.toString())
            jsonObject.put("id", creditJvId)
            jsonObject.put("debitAcCurrentBalance", debitBalance)
            jsonObject.put("toAcCurrentBalance", toBalance)
            Response apiResponse = target
                    .path(new Links().CREDIT_APPROVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
            log.error('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }


    def debitJVDatatables(JSONObject jsonObject) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().DEBIT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  debitJVDatatables  , Ex:' + ex)
            log.error('Service :AccountsService , action :  debitJVDatatables  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }

    def debitJvApprove(String status, long entityId, long approverId, String debitJvId, String creditBalance, String fromBalance) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("status", status)
            jsonObject.put("entityId", entityId.toString())
            jsonObject.put("approverId", approverId.toString())
            jsonObject.put("id", debitJvId)
            jsonObject.put("creditAcCurrentBalance", creditBalance)
            jsonObject.put("fromAcCurrentBalance", fromBalance)
            Response apiResponse = target
                    .path(new Links().CREDIT_APPROVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  debitJvApprove  , Ex:' + ex)
            log.error('Service :AccountsService , action :  debitJvApprove  , Ex:' + ex)
        }
        finally{
            client.close()
        }
    }
}
