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

    def accountsServiceStatus()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().ACCOUNTS_SERVICE_STATUS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :accounts , action :  accountsServiceStatus  , Ex:' + ex)
            log.error('Service :accounts , action :  accountsServiceStatus  , Ex:' + ex)
        }

    }

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

    def getBankRegisterByEntity(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_BY_ENTITY + "/"+ id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class).toString())
                return jsonArray
            }
            else {
                return null
            }

        }
        catch (Exception ex)
        {
            System.err.println('Service :showAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :showAccountModes , action :  show  , Ex:' + ex)
        }

    }

    //Receipt Detail
    def saveReceipt(JSONObject jsonObject, String financialYear)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
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

    def approveReceipt(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().RECIPT_APPROVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
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

    def getBankById(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {

            Response apiResponse = target
                    .path(new Links().BANK_REGISTER_SHOW+"/"+id)
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

    def getAllSaleReturnByCustomer(long id, long entityId, String financialYear, String returnStatus = null)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_RETURN_CUSTOMER)
                    .queryParam("id", id)
                    .queryParam("financialYear", financialYear)
                    .queryParam("entityId", entityId)
                    .queryParam("returnStatus", returnStatus)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getAllSaleReturnByCustomer  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getAllSaleReturnByCustomer  , Ex:' + ex)
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
                    .path(new Links().SALE_BILL_BALANCE_UPDATE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow+"/status/"+jsonObject.status)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  updatePurchaseBalance  , Ex:' + ex)
            log.error('Service :AccountsService , action :  updatePurchaseBalance  , Ex:' + ex)
        }

    }

    def updateSaleBalanceAndCredit(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SALE_BILL_BALANCE_CREDITS_UPDATE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(jsonObject))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  updateSaleBalanceAndCredit  , Ex:' + ex)
            log.error('Service :AccountsService , action :  updateSaleBalanceAndCredit  , Ex:' + ex)
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
                    .path(new Links().UPDATE_SALE_RETURN_BALANCE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow+"/status/"+jsonObject.status)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  updateSaleReturnBalance  , Ex:' + ex)
            log.error('Service :AccountsService , action :  updateSaleReturnBalance  , Ex:' + ex)
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
                    .path(new Links().UPDATE_GTN_BALANCE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow+"/status/"+jsonObject.status)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  updateGTNBalance  , Ex:' + ex)
            log.error('Service :AccountsService , action :  updateGTNBalance  , Ex:' + ex)
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
                    .path(new Links().PURCHASE_BILL_BALANCE_UPDATE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow+"/status/"+jsonObject.status)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.form(form))
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  updatePurchaseBalance  , Ex:' + ex)
            log.error('Service :AccountsService , action :  updatePurchaseBalance  , Ex:' + ex)
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
                    .path(new Links().PURCHASE_RETURN_BALANCE_UPDATE+"/id/"+jsonObject.id+"/balance/"+jsonObject.paidNow+"/status/"+jsonObject.status)
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
            System.err.println('Service :AccountsService , action :  getReceiptLogInvById  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getReceiptLogInvById  , Ex:' + ex)
        }

    }


    def getReceiptLogByBillTypeAndId(String id, String billType, String dateRange = null)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
//        WebTarget target = client.target("http://localhost:8089");
        try
        {
            Response apiResponse = target
                    .path(new Links().RECEIPT_DETAIL_LOG + "/" + billType + "/" + id)
                    .queryParam("dateRange", dateRange)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getReceiptLogByBillTypeAndId  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getReceiptLogByBillTypeAndId  , Ex:' + ex)
        }

    }


    def getReceiptLogByBillTypeAndIdStartDate(String id, String billType, String dateRange = null)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().RECEIPT_DETAIL_LOG_START_DATE + "/" + billType + "/" + id)
                    .queryParam("dateRange", dateRange)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                return apiResponse
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  getReceiptLogByBillTypeAndId  , Ex:' + ex)
            log.error('Service :AccountsService , action :  getReceiptLogByBillTypeAndId  , Ex:' + ex)
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

    def cancelReceipt(String id, String entityId, String financialYear)
    {
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("entityId", entityId)
        jsonObject.put("financialYear", financialYear)
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().RECIPT_CANCEL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(jsonObject))
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  cancelInvoice  , Ex:' + ex)
            log.error('Service :accountsService , action :  cancelInvoice  , Ex:' + ex)
        }
    }

    def getReceiptDetailsByDateRange(String dateRange, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().RECIPT_DETAIL_BY_DATERANGE)
                    .queryParam("dateRange", dateRange)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            println(apiResponse)
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
            log.error('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
        }

    }

    def getReceiptDetailsByDateRangeCustomer(String dateRange, String customerId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().RECEIPT_BY_DATERANGE_CUSTOMER)
                    .queryParam("dateRange", dateRange)
                    .queryParam("customerId", customerId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            println(apiResponse)
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
            log.error('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
        }

    }


    def getPaymentDetailsByDateRange(String dateRange, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DATERANGE)
                    .queryParam("dateRange", dateRange)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            println(apiResponse)
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
            log.error('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
        }

    }

    def getPaymentDetailsByDateRangeCustomer(String dateRange, String customerId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_DATERANGE_CUSTOMER)
                    .queryParam("dateRange", dateRange)
                    .queryParam("customerId", customerId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            println(apiResponse)
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
            log.error('Service :accountsService , action :  getReceiptDetailsByDateRange  , Ex:' + ex)
        }

    }




    def getCreditJvByDateRange(String dateRange, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().CREDIT_DATERANGE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex) {
            System.err.println('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
            log.error('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
        }

    }


    def getDebitJvByDateRange(String dateRange, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("dateRange", dateRange)
            jsonObject.put("entityId", entityId)
            Response apiResponse = target
                    .path(new Links().DEBIT_DATERANGE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            if (apiResponse.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
        }
        catch (Exception ex) {
            System.err.println('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
            log.error('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
        }
    }

    def savePaymentCollectionLog(JSONObject jsonObject){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().SAVE_PAYMENT_COLLECTION)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
        }catch(Exception ex){
            System.err.println('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
            log.error('Service :salesService , action :  getSaleBillByDateRange  , Ex:' + ex)
        }
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showPaymentCollection(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PAYMENT_COLLECTION_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                return responseObject
            }
            else
                return null
        }
        catch (Exception ex)
        {
            System.err.println('Service :AccountsService , action :  showPaymentCollection  , Ex:' + ex)
            log.error('Service :AccountsService , action :  showPaymentCollection  , Ex:' + ex)
        }

    }

    def changeStatusPaymentCollection(String id, String status) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_COLLECTION_CHANGE_STATUS)
                    .queryParam("id", id)
                    .queryParam("status", status)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200) {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  changeStatusPaymentCollection  , Ex:' + ex)
            log.error('Service :accountsService , action :  changeStatusPaymentCollection  , Ex:' + ex)
        }

    }


    def approveAllPaymentCollection(JSONArray jsonArray){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_COLLECTION_APPROVE_ALL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonArray.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONArray jsonArray1 = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray1
            }
        }
        catch (Exception ex) {
            System.err.println('Service :accountsService , action :  changeStatusPaymentCollection  , Ex:' + ex)
            log.error('Service :accountsService , action :  changeStatusPaymentCollection  , Ex:' + ex)
        }
    }


    def cancelReceiptPayments(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_COLLECTION_CANCEL_RECEIPT)
                    .queryParam("id", id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject1
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  cancelReceipt  , Ex:' + ex)
            log.error('Service :AccountsService , action :  cancelReceipt  , Ex:' + ex)
        }
    }

    def updatePaymentColletionBulkUpdate(JSONObject jsonObject){
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_COLLECTION_BULK_UPDATE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse.status == 200) {
                JSONArray jsonArray1 = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray1
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  cancelReceipt  , Ex:' + ex)
            log.error('Service :AccountsService , action :  cancelReceipt  , Ex:' + ex)
        }
    }

    def getPaymentCollectionLogs(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PAYMENT_COLLECTION_RECEIPTID+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray1 = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray1
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :AccountsService , action :  cancelReceipt  , Ex:' + ex)
            log.error('Service :AccountsService , action :  cancelReceipt  , Ex:' + ex)
        }
    }
}
