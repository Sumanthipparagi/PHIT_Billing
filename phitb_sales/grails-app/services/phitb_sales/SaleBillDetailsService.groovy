package phitb_sales

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException
import org.glassfish.jersey.jackson.JacksonFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SaleBillDetailsService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String query)
    {
//        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
//        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleBillDetails.findAll()
        }
        else
        {
            return SaleBillDetails.findAllByFinancialYearIlike("%" + query + "%")
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SaleBillDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SaleBillDetails.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }


    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleBillDetails.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 0)
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {

        return SaleBillDetails.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 1)

    }

    SaleBillDetails get(String id)
    {
        return SaleBillDetails.findById(Long.parseLong(id))
    }

    SaleBillDetails getDraftBillById(String id)
    {
        return SaleBillDetails.findByBillStatusAndId('DRAFT', Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        String invoiceStatus = paramsJsonObject.get("invoiceStatus")

        long userId = 0
        if(paramsJsonObject.has("userId"))
            userId = paramsJsonObject.get("userId")

        long entityId = paramsJsonObject.get("entityId")


        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "financialYear"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def saleBillDetailsCriteria = SaleBillDetails.createCriteria()
        def saleBillDetailsArrayList = saleBillDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('financialYear', '%' + searchTerm + '%')
                    ilike('invoiceNumber', '%' + searchTerm + '%')
                }
            }
            if (!invoiceStatus.equalsIgnoreCase("ALL"))
            {
                eq('billStatus', invoiceStatus)
            }

            if(userId > 0)
                eq("userId", userId)

            eq("entityId", entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = saleBillDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", saleBillDetailsArrayList)
        return jsonObject
    }

    SaleBillDetails save(JSONObject jsonObject)
    {
        SaleBillDetails saleBillDetails = new SaleBillDetails()
        saleBillDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        saleBillDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleBillDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        saleBillDetails.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        saleBillDetails.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        saleBillDetails.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        //saleBillDetails.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        saleBillDetails.entryDate = new Date()
        saleBillDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        saleBillDetails.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        saleBillDetails.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        saleBillDetails.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        //saleBillDetails.orderDate = sdf.parse(jsonObject.get("orderDate").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            saleBillDetails.orderDate = new Date()

        saleBillDetails.refOrderId = jsonObject.get("refOrderId").toString()
        saleBillDetails.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        saleBillDetails.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        saleBillDetails.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        saleBillDetails.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        saleBillDetails.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        saleBillDetails.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        saleBillDetails.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        saleBillDetails.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        saleBillDetails.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        saleBillDetails.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        saleBillDetails.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        saleBillDetails.userId = Long.parseLong(jsonObject.get("userId").toString())
        saleBillDetails.balance = Double.parseDouble(jsonObject.get("balance").toString())
        saleBillDetails.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        saleBillDetails.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        saleBillDetails.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        saleBillDetails.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleBillDetails.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        saleBillDetails.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        saleBillDetails.rep = jsonObject.get("rep").toString()
        saleBillDetails.gstStatus = jsonObject.get("gstStatus").toString()
        saleBillDetails.billStatus = jsonObject.get("billStatus").toString()
        saleBillDetails.lockStatus = jsonObject.get("lockStatus").toString()
        saleBillDetails.syncStatus = jsonObject.get("syncStatus").toString()
        saleBillDetails.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        saleBillDetails.adjAmount = Double.parseDouble("0")
        saleBillDetails.creditIds = jsonObject.get("creditIds").toString()
        saleBillDetails.referralDoctor = jsonObject.get("referralDoctor").toString()
        saleBillDetails.message = jsonObject.get("message").toString()
        saleBillDetails.invtype = jsonObject.get("invtype").toString()
        saleBillDetails.financialYear = jsonObject.get("financialYear").toString()
        saleBillDetails.refNo = jsonObject.get("refNo").toString()
        if(jsonObject.get("refDate")!=''){
            saleBillDetails.refDate = sdf.parse(jsonObject.get("refDate").toString())
        }
       else{
            saleBillDetails.refDate = null
        }
        saleBillDetails.privateNote = jsonObject.get("privateNote").toString()
        saleBillDetails.publicNote = jsonObject.get("publicNote").toString()
        saleBillDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        saleBillDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        saleBillDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        saleBillDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleBillDetails.uuid = jsonObject.get("uuid").toString()
        saleBillDetails.save(flush: true)
        if (!saleBillDetails.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(saleBillDetails.entryDate)
            String month = cal.get(Calendar.MONTH) + 1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00")
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            SaleBillDetails saleBillDetails1
            if (saleBillDetails.billStatus == "DRAFT")
            {
                println(saleBillDetails.billStatus)
//                invoiceNumber = saleBillDetails.entityId+"/DR/S/" + month + year + "/" + seriesCode + "/__";'
                saleBillDetails.invoiceNumber = null
            }
            else
            {
                invoiceNumber = saleBillDetails.entityId + "S" + month + year + seriesCode + saleBillDetails.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                saleBillDetails.invoiceNumber = invoiceNumber
                saleBillDetails.isUpdatable = true
                saleBillDetails.save(flush: true)
            }
            return saleBillDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SaleBillDetails update(JSONObject jsonObject, String id)
    {
        SaleBillDetails saleBillDetails = SaleBillDetails.findById(Long.parseLong(id))
        saleBillDetails.isUpdatable = true
        saleBillDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        saleBillDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleBillDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        saleBillDetails.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        saleBillDetails.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        saleBillDetails.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
       // saleBillDetails.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        saleBillDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        saleBillDetails.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        saleBillDetails.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        saleBillDetails.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        //saleBillDetails.orderDate = sdf.parse(jsonObject.get("orderDate").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            saleBillDetails.orderDate = new Date()

        saleBillDetails.refOrderId = jsonObject.get("refOrderId").toString()
        saleBillDetails.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        saleBillDetails.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        saleBillDetails.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        saleBillDetails.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        saleBillDetails.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        saleBillDetails.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        saleBillDetails.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        saleBillDetails.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        saleBillDetails.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        saleBillDetails.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        saleBillDetails.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        saleBillDetails.userId = Long.parseLong(jsonObject.get("userId").toString())
        saleBillDetails.balance = Double.parseDouble(jsonObject.get("balance").toString())
        saleBillDetails.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        saleBillDetails.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        saleBillDetails.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        saleBillDetails.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleBillDetails.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        saleBillDetails.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        saleBillDetails.rep = jsonObject.get("rep").toString()
        saleBillDetails.gstStatus = jsonObject.get("gstStatus").toString()
        saleBillDetails.billStatus = jsonObject.get("billStatus").toString()
        saleBillDetails.lockStatus = jsonObject.get("lockStatus").toString()
        saleBillDetails.syncStatus = jsonObject.get("syncStatus").toString()
        saleBillDetails.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        saleBillDetails.adjAmount = Double.parseDouble("0")
        saleBillDetails.creditIds = jsonObject.get("creditIds").toString()
        saleBillDetails.referralDoctor = jsonObject.get("referralDoctor").toString()
        saleBillDetails.message = jsonObject.get("message").toString()
        saleBillDetails.invtype = jsonObject.get("invtype").toString()
        saleBillDetails.financialYear = jsonObject.get("financialYear").toString()
        saleBillDetails.refNo = jsonObject.get("refNo").toString()
        if(jsonObject.get("refDate")!=''){
            saleBillDetails.refDate = sdf.parse(jsonObject.get("refDate").toString())
        }
        else{
            saleBillDetails.refDate = null
        }
        saleBillDetails.privateNote = jsonObject.get("privateNote").toString()
        saleBillDetails.publicNote = jsonObject.get("publicNote").toString()
        saleBillDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        saleBillDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        saleBillDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        saleBillDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleBillDetails.uuid = jsonObject.get("uuid").toString()
        saleBillDetails.save(flush: true)
        if (!saleBillDetails.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(saleBillDetails.entryDate)
            String month = cal.get(Calendar.MONTH) + 1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            SaleBillDetails saleBillDetails1
            if (saleBillDetails.billStatus == "DRAFT")
            {
                println(saleBillDetails.billStatus)
                saleBillDetails.invoiceNumber = null
            }
            else
            {
                invoiceNumber = saleBillDetails.entityId + "S"+ month + year +  seriesCode + saleBillDetails.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                saleBillDetails.invoiceNumber = invoiceNumber
                saleBillDetails.isUpdatable = true
                saleBillDetails.save(flush: true)
            }
            return saleBillDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    void delete(String id)
    {
        if (id)
        {
            SaleBillDetails saleBillDetails = SaleBillDetails.findById(Long.parseLong(id))
            if (saleBillDetails)
            {
                saleBillDetails.isUpdatable = true
                saleBillDetails.delete()
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }

    JSONObject getRecentByFinancialYearAndEntity(String financialYear, String entityId, billStatus)
    {

        JSONObject jsonObject = new JSONObject()
        ArrayList<SaleBillDetails> saleBillDetails =
                SaleBillDetails.findAllByFinancialYearAndEntityIdAndBillStatusNotEqual(financialYear, Long.parseLong(entityId), 'DRAFT', [sort: 'id', order: 'desc'])
        println(saleBillDetails.serBillId)
        jsonObject.put("serBillId", saleBillDetails.serBillId.max())
        jsonObject.put("finId", saleBillDetails.finId.max())
        return jsonObject

    }

    def getEntityById(String id)
    {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Constants().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Constants().ENTITY_REGISTER_SHOW + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }
    }


    def getAllByCustomerId(String id,String financialYear,String entityId, String dateRange = null)
    {
        if (id)
        {
            if(dateRange == null)
                return SaleBillDetails.findAllByCustomerIdAndFinancialYearAndEntityId(Long.parseLong(id),financialYear,Long.parseLong(entityId))
            else
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
                Date fromDate = sdf.parse(dateRange.split("-")[0].trim())
                Date toDate = sdf.parse(dateRange.split("-")[1].trim())
                Calendar calendar = Calendar.getInstance()
                calendar.setTime(toDate)
                calendar.add(Calendar.HOUR_OF_DAY, 23)
                calendar.add(Calendar.MINUTE, 59)
                calendar.add(Calendar.SECOND, 59)
                toDate = calendar.getTime()
                return SaleBillDetails.findAllByCustomerIdAndFinancialYearAndEntityIdAndOrderDateBetween(Long.parseLong(id),financialYear,Long.parseLong(entityId), fromDate, toDate)
            }
        }
    }


    def getAllByCustomerByStartDate(String id,String financialYear,String entityId, String dateRange = null){
        if(id)
        {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(dateRange.split("-")[0].trim())
            Date toDate = sdf.parse(dateRange.split("-")[1].trim())
            Calendar calendar = Calendar.getInstance()
            calendar.setTime(toDate)
            calendar.add(Calendar.HOUR_OF_DAY, 23)
            calendar.add(Calendar.MINUTE, 59)
            calendar.add(Calendar.SECOND, 59)
            toDate = calendar.getTime()
            return SaleBillDetails.findAllByCustomerIdAndFinancialYearAndEntityIdAndOrderDateLessThan(Long.parseLong(id),
                    financialYear,Long.parseLong(entityId), fromDate)
        }

    }


    def cancelSaleBill(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        String userId = jsonObject.get("userId")
        JSONObject saleInvoice = new JSONObject()
        SaleBillDetails saleBillDetails = SaleBillDetails.findById(Long.parseLong(id))
        if (saleBillDetails)
        {
            if (saleBillDetails.financialYear.equalsIgnoreCase(financialYear) && saleBillDetails.entityId == Long.parseLong(entityId))
            {
                ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetails.id)
                for (SaleProductDetails saleProductDetail : saleProductDetails)
                {
                    saleProductDetail.status = 0
                    saleProductDetail.isUpdatable = true
                    saleProductDetail.save(flush: true)
                }
                saleBillDetails.billStatus = "CANCELLED"
                saleBillDetails.cancelledDate = new Date()
                saleBillDetails.modifiedUser = Long.parseLong(userId)
                saleBillDetails.isUpdatable = true

                // cancel credit adjustments
                ArrayList<SaleReturn> saleReturns = SaleReturn.findAllByCustomerIdAndEntityIdAndFinancialYearAndReturnStatus(saleBillDetails.customerId.toString(),Long.parseLong(entityId),financialYear,"ACTIVE")
                for(SaleReturn sr: saleReturns){
                    if(sr.balance == 0){
                        sr.isUpdatable = true
                        sr.balance = sr.totalAmount
                        sr.save(flush:true)
                        saleBillDetails.balance += sr.totalAmount
                        saleBillDetails.adjAmount = 0
                    }
                }
                saleBillDetails.save(flush: true)
                saleInvoice.put("products", saleProductDetails)
                saleInvoice.put("invoice", saleBillDetails)
                return saleInvoice
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }


    def updateIRNDetails(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        Date cancelledDate = null
        if(jsonObject.has("cancelledDate")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            cancelledDate = sdf.parse(jsonObject.get("cancelledDate").toString())
        }

        SaleBillDetails saleBillDetails = SaleBillDetails.findById(Long.parseLong(id))
        if (saleBillDetails)
        {
            saleBillDetails.isUpdatable = true
            if(jsonObject.has("irnDetails"))
                saleBillDetails.irnDetails = jsonObject.get("irnDetails").toString()
            if(cancelledDate)
                saleBillDetails.cancelledDate = cancelledDate

            saleBillDetails.save(flush:true)
            return saleBillDetails
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    def getByDateRangeAndEntity(String dateRange, String entityId)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(dateRange.split("-")[0].trim().toString())
            Date toDate = sdf.parse(dateRange.split("-")[1].trim().toString())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            long eid = Long.parseLong(entityId)
            JSONArray finalBills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByEntityIdAndOrderDateBetween(eid, fromDate, toDate)
            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                JSONObject saleBillDetail1 = new JSONObject((saleBillDetail as JSON).toString())
                def productDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    saleBillDetail1.put("products", prdt)
                }
                finalBills.add(saleBillDetail1)
            }
            return finalBills
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            throw new BadRequestException()
        }
    }

    def getByDateRangeAndCustomerId(String dateRange, String customerId)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date fromDate = sdf.parse(dateRange.split("-")[0].trim().toString())
            Date toDate = sdf.parse(dateRange.split("-")[1].trim().toString())
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate)
            cal.set(Calendar.HOUR_OF_DAY, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            toDate = cal.getTime()
            long cid = Long.parseLong(customerId)
            JSONArray finalBills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByCustomerIdAndOrderDateBetween(cid, fromDate, toDate)
            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                JSONObject saleBillDetail1 = new JSONObject((saleBillDetail as JSON).toString())
                def productDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    saleBillDetail1.put("products", prdt)
                }
                finalBills.add(saleBillDetail1)
            }
            return finalBills
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            throw new BadRequestException()
        }
    }


    def getAllDraftBillByEntityAndUser(long entityId, long userId){
        try{
            JSONArray draftBills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails
                    .findAllByBillStatusAndEntityIdAndUserIdAndBillStatusNotEqual('DRAFT', entityId,userId,"CANCELLED")
            for(SaleBillDetails saleBillDetails1: saleBillDetails){
                JSONObject jsonObject1 = new JSONObject((saleBillDetails1 as JSON).toString())
                def productDetails = SaleProductDetails.findAllByBillId(saleBillDetails1.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    jsonObject1.put("products", prdt)
                }
                draftBills.add(jsonObject1)
            }
            return draftBills
        }catch(Exception e){
           e.printStackTrace()
            throw  new BadRequestException()
        }
    }

    void deleteAllDraftsSaleBill(long entityId,long userId){
        try{
          ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByInvoiceNumberAndEntityIdAndUserId(null, entityId, userId)
            if(saleBillDetails.size()!=0){
                for(SaleBillDetails saleBillDetail:saleBillDetails){
                    ArrayList<SaleProductDetails> saleProductDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                    if(saleBillDetails){
                        for (SaleProductDetails saleProductDetail: saleProductDetails){
                            saleProductDetail.isUpdatable=true
                            saleProductDetail.delete()
                        }
                    }
                    saleBillDetail.isUpdatable=true
                    saleBillDetail.delete()
                }
            }else{
                throw new ResourceNotFoundException()
            }
        }catch(Exception ex){
            ex.printStackTrace()
            throw new BadRequestException()
        }
    }


    def getByPendingIRNAndEntity(String entityId, String financialYear)
    {
        try {
            long eid = Long.parseLong(entityId)
            JSONArray finalBills = new JSONArray()
            ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByEntityIdAndFinancialYearAndIrnDetailsIsNullAndBillStatus(eid, financialYear, "ACTIVE")
            for (SaleBillDetails saleBillDetail : saleBillDetails) {
                JSONObject saleBillDetail1 = new JSONObject((saleBillDetail as JSON).toString())
                def productDetails = SaleProductDetails.findAllByBillId(saleBillDetail.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    saleBillDetail1.put("products", prdt)
                }
                finalBills.add(saleBillDetail1)
            }
            return finalBills
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            throw new BadRequestException()
        }
    }

    def getPaymentPendingBills(JSONObject paramsJsonObject, String start, String length)
    {
        try
        {
            String financialYear = paramsJsonObject.get("financialYear")
            long entityId = Long.parseLong(paramsJsonObject.get("entityId").toString())
            String cIds = paramsJsonObject.get("customerIds")
            ArrayList<String> customerIdsStrings = cIds.split(",")
            ArrayList<Long> customerIds = new ArrayList<>()
            for (String str : customerIdsStrings) {
                customerIds.add(Long.parseLong(str))
            }

            String searchTerm = paramsJsonObject.get("search[value]")
            String orderColumnId = paramsJsonObject.get("order[0][column]")
            String orderDir = paramsJsonObject.get("order[0][dir]")
            double minBalance = 0

            String orderColumn = "id"
            switch (orderColumnId)
            {
                case '0':
                    orderColumn = "id"
                    break;
                case '1':
                    orderColumn = "financialYear"
                    break;
            }
            Integer offset = start ? Integer.parseInt(start.toString()) : 0
            Integer max = length ? Integer.parseInt(length.toString()) : 100
            def saleBillDetailsCriteria = SaleBillDetails.createCriteria()
            def saleBillDetailsArrayList = saleBillDetailsCriteria.list(max: max, offset: offset) {
                or {
                    if (searchTerm != "")
                    {
                        ilike('financialYear', '%' + searchTerm + '%')
                        ilike('invoiceNumber', '%' + searchTerm + '%')
                    }
                }
                eq('billStatus', "ACTIVE")
                gt('balance', minBalance)
                eq('financialYear', financialYear)
                'in'('customerId', customerIds)
                eq("entityId", entityId)
                eq('deleted', false)
                order(orderColumn, orderDir)
            }
            def recordsTotal = saleBillDetailsArrayList.totalCount
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("draw", paramsJsonObject.draw)
            jsonObject.put("recordsTotal", recordsTotal)
            jsonObject.put("recordsFiltered", recordsTotal)
            jsonObject.put("data", saleBillDetailsArrayList)
            return jsonObject

            //return SaleBillDetails.findAllByEntityIdAndBillStatusAndBalanceGreaterThanAndFinancialYearAndCustomerIdInList(entityId, "ACTIVE", 0, financialYear, customerId)
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
        }
    }

}