package phitb_ui.sales

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.EntityService
import phitb_ui.SalesService
import phitb_ui.entity.EntityRegisterController

class CreditDebitSettlementController {

    def index() {

        String entityId = session.getAttribute('entityId').toString()
        ArrayList<String> entity = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        render(view:'/sales/creditDebitSettlement/credit-debit-settlement',model: [entity:entity])
    }


    def saveCrDbSettlement(){
        try{
            JSONArray jsonArray = new JSONArray(params.data)
            JSONObject crdbSettlement = new JSONObject()
            JSONArray crdbDetails = new JSONArray()
            crdbSettlement.put('customerId', params.customer)
            crdbSettlement.put('financialYear', session.getAttribute('financialYear'))
            crdbSettlement.put('userId', session.getAttribute('userId'))
            crdbSettlement.put('entityId', session.getAttribute('entityId'))
            crdbSettlement.put('entityTypeId', session.getAttribute('entityTypeId'))
            crdbSettlement.put('remarks', '')
            crdbSettlement.put('finId', 0)
            for(JSONObject jsonObject:jsonArray){
                JSONObject crdb = new JSONObject()
                if(jsonObject.type == 'INV'){
                    jsonObject.put('id',jsonObject.billid)
                    jsonObject.put('paidNow',jsonObject.pendamt)
                    jsonObject.put('status','NA')
                    crdb.put('debitAmt',jsonObject.pendamt)
                    crdb.put('debitSeries',jsonObject.docid)
                    crdb.put('debitId',jsonObject.billid)
                    crdb.put('debitFinancialYear',jsonObject.finyear)
                    crdb.put('transType',"DEBIT")
                    def updateInvBalance = new AccountsService().updateSaleBalance(jsonObject)
                    println(updateInvBalance)
                }
                if(jsonObject.type == 'CRNT'){
                    jsonObject.put('id',jsonObject.billid)
                    jsonObject.put('paidNow',jsonObject.pendamt)
                    jsonObject.put('status','NA')
                    crdb.put('creditAmt',jsonObject.pendamt)
                    crdb.put('creditSeries',jsonObject.docid)
                    crdb.put('creditId',jsonObject.billid)
                    crdb.put('creditFinancialYear',jsonObject.finyear)
                    crdb.put('transType',"CREDIT")
                    def updateCrntBalance = new AccountsService().updateSaleReturnBalance(jsonObject)
                    println(updateCrntBalance)
                }
                crdb.put('financialYear', session.getAttribute('financialYear'))
                crdb.put('entityId',session.getAttribute('entityId'))
                crdb.put('entityTypeId',session.getAttribute('entityTypeId'))
                crdbDetails.add(crdb)
            }
            JSONObject jsonObject = new JSONObject()
            jsonObject.put('crdbSettlement',crdbSettlement)
            jsonObject.put('crdbDetails',crdbDetails)
            def saveCrDb = new SalesService().saveCrDbSettlement(jsonObject)
            if(saveCrDb?.status == 200){
                JSONObject jsonObject1 = new JSONObject(saveCrDb.readEntity(String.class))
                respond jsonObject1, formats: ['json'],status:200;
            }else{
                response.status = 400;
            }
        }catch(Exception ex){
            println(ex)
        }
    }

    def printCrDbDetails(){
        try{
            String id = params.id
            String entityId = session.getAttribute('entityId').toString()
            def crdb = new SalesService().getCrDbDetailsById(id,entityId)
            if(crdb){
                JSONObject crdbobj = new JSONObject(crdb.get('crdbSettlement'))
                def entity = new EntityService().getEntityById(crdbobj.entityId.toString())
                def customer = new EntityService().getEntityById(crdbobj.customerId.toString())
                crdbobj.put('entityId',entity)
                crdbobj.put('customerId',customer)
                JSONArray crdbLogs = new JSONArray(crdb.get('crdbDetails').toString())
                render(view:'/sales/creditDebitSettlement/crdb-print',model: [crdbLogs:crdbLogs,crdbobj:crdbobj])
            }else{
                response.status = 400
            }
        }
        catch (Exception ex){
            println(ex)
        }
    }

}
