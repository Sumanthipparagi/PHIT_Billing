package phitb_ui

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Form
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class AccountsService
{

    //Bank Register
    def saveBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  saveBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  saveBankRegister  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  showBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  showBankRegister  , Ex:' + ex)
        }

    }

    def putBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service: AccountsService , action :  putBankRegister  , Ex:' + ex)
            log.error('Service: AccountsService , action :  putBankRegister  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteBankRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  deleteBankRegister  , Ex:' + ex)
            log.error('Service :AccountsService , action :  deleteBankRegister  , Ex:' + ex)
        }

    }


    //Recipt Detail
    def saveRecipt(JSONObject jsonObject, String financialYear)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            jsonObject.put("financialYear", financialYear)
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }


    //Payment Detail
    def savePaymentDetail(JSONObject jsonObject, String financialYear)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
//        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
        try
        {
            jsonObject.put("financialYear", financialYear)
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PAYMENT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    def putRecipt(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }

    }

    def showRecipt(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }
    }

    def showPayments(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def getBanks()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {

            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getWallet()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {

            Response apiResponse = target
                    .path(new Links().WALLET_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }


    def getEntityById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().ENTITY_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }

    }

//   get Invoice to Unsettled
    def getUnSaleBillCustomerId(String id, String entityId, String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_UNSETTLED + "/" + id)
                    .queryParam("entityId", entityId)
                    .queryParam("financialYear", financialYear)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    //    get Credit Note to settled
    def getCNUnsettledCustomerId(String id,String entityId, String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_UNSETTLED +"/"+id) //TODO: To be changed
                    .queryParam("entityId", entityId)
                    .queryParam("financialYear", financialYear)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getCNUnsettledCustomerId  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getCNUnsettledCustomerId  , Ex:' + ex)
        }

    }

    //    get invoice to settled
    def getSaleBillSettledCustomerId(String id, String entityId, String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_SETTLED + "/" + id)
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }

    }

    //    get Credit Note to settled
    def getCNsettledCustomerId(String id, String entityId, String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_SETTLED + "/" + id)
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }

    }


// Sales
    def getAllSaleBillById(String id,String entityId,String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_CUSTOMER)
                    .resolveTemplate("custid",id)
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getAllSaleReturnById(String id,String entityId,String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_CUSTOMER)
                    .queryParam("id", URLEncoder.encode(id, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getAllGTNById(String id,String entityId,String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_CUSTOMER)
                    .queryParam("id", URLEncoder.encode(id, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }


//    purchase

    def getAllPurchaseBillById(String id,String entityId,String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_SUPPLIER)
                    .queryParam("id", URLEncoder.encode(id, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getAllPurchaseReturnById(String id,String entityId,String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_CUSTOMER)
                    .queryParam("id", URLEncoder.encode(id, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getAllGRNById(String id,String entityId,String financialYear)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().GTN_CUSTOMER)
                    .queryParam("id", URLEncoder.encode(id, "UTF-8"))
                    .queryParam("financialYear", URLEncoder.encode(financialYear, "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId, "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }




    //   update Sale invoice balance
    def updateSaleBalance(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_BALANCE_UPDATE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    //   update Sale return balance
    def updateSaleReturnBalance(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().UPDATE_SALE_RETURN_BALANCE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }


    //   update GTN balance
    def updateGTNBalance(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().UPDATE_GTN_BALANCE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }



    //   update Sale invoice balance
    def updatePurchaseBalance(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_BILL_BALANCE_UPDATE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    //   update Sale return balance
    def updatePurchaseReturnBalance(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PURCHASE_RETURN_BALANCE_UPDATE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    //   update Receipt Detail Log
    def updateReceiptDetailLog(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_LOG)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    def updatePaymentDetailLog(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DETAIL_LOG)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    def getReceiptLogInvById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_LOG_INVS_ID + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                 return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getReceiptLogcrntById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_LOG_CRNT_ID + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getReceiptLoggtnById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_LOG_GTN_ID + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }




//   move invoice to settled vocher
//    def updateSettledVocher(JSONObject jsonObject)
//    {
//        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target(new Links().API_GATEWAY);
//
//        try
//        {
//            Response apiResponse = target
//                    .path(new Links().SET_PAYMENT_BILL)
//                    .resolveTemplate("id", jsonObject.id)
//                    .resolveTemplate("type", "settled")
//                    .request(MediaType.APPLICATION_JSON_TYPE)
////                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
//                    .post(Entity.form(form))
//            println(apiResponse)
//            return apiResponse
//        }
//        catch (Exception ex)
//        {
//            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
//            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
//        }
//
//    }


    def updateSettledVocher(String id, String paidAmt)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SET_PAYMENT_BILL)
                    .resolveTemplate("id", id)
                    .resolveTemplate("paid", paidAmt)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

//   move invoice to Unsettled vocher
    def updateunSettledVocher(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
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
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

    //   move Credit Note to settled vocher
    def updateCNSettledVocher(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SET_SALE_RETURN_STATUS)
                    .resolveTemplate("id", jsonObject.id)
                    .resolveTemplate("type", "settled")
                    .resolveTemplate("adj", jsonObject.adj.toString())
                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }

//   move Credit Note to Unsettled vocher
    def updateCNunSettledVocher(JSONObject jsonObject)
    {
        Form form = UtilsService.jsonToFormDataConverter(jsonObject)
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().SET_SALE_RETURN_STATUS)
                    .resolveTemplate("id", jsonObject.id)
                    .resolveTemplate("type", "unsettled")
                    .resolveTemplate("adj", jsonObject.adj.toString())
                    .request(MediaType.APPLICATION_JSON_TYPE)
//                    .post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON_TYPE))
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :saveStateMaster , action :  save  , Ex:' + ex)
            log.error('Service :saveStateMaster , action :  save  , Ex:' + ex)
        }

    }


    def getReciptById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {

            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getPaymentById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {

            Response apiResponse = target
                    .path(new Links().PAYMENT_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getProducts  , Ex:' + ex)
            log.error('Service :EntityService , action :  getProducts  , Ex:' + ex)
        }

    }


    //Credit JV
    def saveCreditJV(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().CREDIT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
            log.error('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
        }

    }

    def saveDebitJV(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DEBIT_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
            log.error('Service :accountsService , action :  saveCreditJV  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def creditJVDatatables(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().CREDIT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
            log.error('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
        }

    }

    def creditJvApprove(String status, long entityId, long approverId, String creditJvId, String debitBalance, String toBalance)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
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
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
            log.error('Service :AccountsService , action :  creditJVDatatables  , Ex:' + ex)
        }

    }


    def debitJVDatatables(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().DEBIT_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  debitJVDatatables  , Ex:' + ex)
            log.error('Service :AccountsService , action :  debitJVDatatables  , Ex:' + ex)
        }

    }

    def debitJvApprove(String status, long entityId, long approverId, String debitJvId, String creditBalance, String fromBalance)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
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
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  debitJvApprove  , Ex:' + ex)
            log.error('Service :AccountsService , action :  debitJvApprove  , Ex:' + ex)
        }

    }



    def getPaymentLogInvById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DETAIL_LOG_INVS_ID + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getPaymentLogcrntById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DETAIL_LOG_CRNT_ID + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getPaymentLogGRNById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DETAIL_LOG_GRN_ID + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getProducts  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getProducts  , Ex:' + ex)
        }

    }
}
