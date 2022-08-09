package phitb_sales

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SampleConversionService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String query)
    {
//        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
//        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SampleConversion.findAll()
        }
        else
        {
            return SampleConversion.findAllByFinancialYearIlike("%" + query + "%")
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SampleConversion.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SampleConversion.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }


    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SampleConversion.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 0)
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {

        return SampleConversion.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 1)

    }

    SampleConversion get(String id)
    {
        return SampleConversion.findById(Long.parseLong(id))
    }

    SampleConversion getDraftBillById(String id)
    {
        return SampleConversion.findByBillStatusAndId('DRAFT', Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        String invoiceStatus = paramsJsonObject.get("invoiceStatus")
        String userId = paramsJsonObject.get("userId")
        long uid = 0
        if(userId)
            uid = Long.parseLong(userId)

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
        def SampleConversionCriteria = SampleConversion.createCriteria()
        def SampleConversionArrayList = SampleConversionCriteria.list(max: max, offset: offset) {
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
            if(userId)
                eq("userId", uid)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = SampleConversionArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", SampleConversionArrayList)
        return jsonObject
    }

    SampleConversion save(JSONObject jsonObject)
    {
        SampleConversion sampleConversion = new SampleConversion()
        sampleConversion.finId = Long.parseLong(jsonObject.get("finId").toString())
        sampleConversion.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        sampleConversion.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        sampleConversion.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        sampleConversion.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        sampleConversion.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        sampleConversion.entryDate = new Date()
        sampleConversion.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        sampleConversion.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        sampleConversion.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        sampleConversion.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            sampleConversion.orderDate = new Date()

        sampleConversion.refOrderId = jsonObject.get("refOrderId").toString()
        sampleConversion.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        sampleConversion.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        sampleConversion.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        sampleConversion.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        sampleConversion.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        sampleConversion.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        sampleConversion.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        sampleConversion.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        sampleConversion.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        sampleConversion.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        sampleConversion.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        sampleConversion.userId = Long.parseLong(jsonObject.get("userId").toString())
        sampleConversion.balance = Double.parseDouble(jsonObject.get("balance").toString())
        sampleConversion.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        sampleConversion.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        sampleConversion.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        sampleConversion.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        sampleConversion.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        sampleConversion.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        sampleConversion.gstStatus = jsonObject.get("gstStatus").toString()
        sampleConversion.billStatus = jsonObject.get("billStatus").toString()
        sampleConversion.lockStatus = jsonObject.get("lockStatus").toString()
        sampleConversion.syncStatus = jsonObject.get("syncStatus").toString()
        sampleConversion.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        sampleConversion.adjAmount = Double.parseDouble("0")
        sampleConversion.creditIds = jsonObject.get("creditIds").toString()
        sampleConversion.referralDoctor = jsonObject.get("referralDoctor").toString()
        sampleConversion.message = jsonObject.get("message").toString()
        sampleConversion.financialYear = jsonObject.get("financialYear").toString()
        sampleConversion.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        sampleConversion.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        sampleConversion.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        sampleConversion.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        sampleConversion.uuid = jsonObject.get("uuid").toString()
        sampleConversion.save(flush: true)
        if (!sampleConversion.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(sampleConversion.entryDate)
            String month = cal.get(Calendar.MONTH) + 1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00")
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            SampleConversion sampleConversion1
            if (sampleConversion.billStatus == "DRAFT")
            {
                println(sampleConversion.billStatus)
//                invoiceNumber = SampleConversion.entityId+"/DR/S/" + month + year + "/" + seriesCode + "/__";'
                sampleConversion.invoiceNumber = null
            }
            else
            {
                invoiceNumber = sampleConversion.entityId + "SI" + month + year + seriesCode + sampleConversion.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                sampleConversion.invoiceNumber = invoiceNumber
                sampleConversion.isUpdatable = true
                sampleConversion.save(flush: true)
            }
            return sampleConversion
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SampleConversion update(JSONObject jsonObject, String id)
    {
        SampleConversion sampleConversion = SampleConversion.findById(Long.parseLong(id))
        sampleConversion.isUpdatable = true
        sampleConversion.finId = Long.parseLong(jsonObject.get("finId").toString())
        sampleConversion.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        sampleConversion.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        sampleConversion.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        sampleConversion.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        sampleConversion.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        // sampleConversion.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        sampleConversion.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        sampleConversion.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        sampleConversion.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        sampleConversion.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        //sampleConversion.orderDate = sdf.parse(jsonObject.get("orderDate").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            sampleConversion.orderDate = new Date()

        sampleConversion.refOrderId = jsonObject.get("refOrderId").toString()
        sampleConversion.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        sampleConversion.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        sampleConversion.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        sampleConversion.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        sampleConversion.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        sampleConversion.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        sampleConversion.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        sampleConversion.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        sampleConversion.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        sampleConversion.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        sampleConversion.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        sampleConversion.userId = Long.parseLong(jsonObject.get("userId").toString())
        sampleConversion.balance = Double.parseDouble(jsonObject.get("balance").toString())
        sampleConversion.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        sampleConversion.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        sampleConversion.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        sampleConversion.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        sampleConversion.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        sampleConversion.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        sampleConversion.gstStatus = jsonObject.get("gstStatus").toString()
        sampleConversion.billStatus = jsonObject.get("billStatus").toString()
        sampleConversion.lockStatus = jsonObject.get("lockStatus").toString()
        sampleConversion.syncStatus = jsonObject.get("syncStatus").toString()
        sampleConversion.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        sampleConversion.adjAmount = Double.parseDouble("0")
        sampleConversion.creditIds = jsonObject.get("creditIds").toString()
        sampleConversion.referralDoctor = jsonObject.get("referralDoctor").toString()
        sampleConversion.message = jsonObject.get("message").toString()
        sampleConversion.financialYear = jsonObject.get("financialYear").toString()
        sampleConversion.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        sampleConversion.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        sampleConversion.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        sampleConversion.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        sampleConversion.uuid = jsonObject.get("uuid").toString()
        sampleConversion.save(flush: true)
        if (!sampleConversion.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(sampleConversion.entryDate)
            String month = cal.get(Calendar.MONTH) + 1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            SampleConversion sampleConversion1
            if (sampleConversion.billStatus == "DRAFT")
            {
                println(sampleConversion.billStatus)
                sampleConversion.invoiceNumber = null
            }
            else
            {
                invoiceNumber = sampleConversion.entityId + "S"+ month + year +  seriesCode + sampleConversion.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                sampleConversion.invoiceNumber = invoiceNumber
                sampleConversion.isUpdatable = true
                sampleConversion.save(flush: true)
            }
            return sampleConversion
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
            SampleConversion sampleConversion = SampleConversion.findById(Long.parseLong(id))
            if (sampleConversion)
            {
                sampleConversion.isUpdatable = true
                sampleConversion.delete()
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
        ArrayList<SampleConversion> sampleConversions =
                SampleConversion.findAllByFinancialYearAndEntityIdAndBillStatusNotEqual(financialYear, Long.parseLong(entityId), 'DRAFT', [sort: 'id', order: 'desc'])
        println(sampleConversions.serBillId)
        jsonObject.put("serBillId", sampleConversions.serBillId.max())
        jsonObject.put("finId", sampleConversions.finId.max())
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


    def getAllByCustomerId(String id,String financialYear,String entityId)
    {
        if (id)
        {
            return SampleConversion.findAllByCustomerIdAndFinancialYearAndEntityId(Long.parseLong(id),financialYear,Long.parseLong(entityId))
        }
    }

    def cancelSampleBill(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject saleInvoice = new JSONObject()
        SampleConversion sampleConversion = SampleConversion.findById(Long.parseLong(id))
        if (sampleConversion)
        {
            if (sampleConversion.financialYear.equalsIgnoreCase(financialYear) && sampleConversion.entityId == Long.parseLong(entityId))
            {
                ArrayList<SampleConversionDetails> sampleConversionDetails = SampleConversionDetails.findAllByBillId(sampleConversion.id)
                for (SampleConversionDetails sampleConversionDetail : sampleConversionDetails)
                {
                    sampleConversionDetail.status = 0
                    sampleConversionDetail.isUpdatable = true
                    sampleConversionDetail.save(flush: true)
                }
                sampleConversion.billStatus = "CANCELLED"
                sampleConversion.cancelledDate = new Date()
                sampleConversion.isUpdatable = true
                sampleConversion.save(flush: true)

                saleInvoice.put("products", sampleConversionDetails)
                saleInvoice.put("invoice", sampleConversion)
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
            ArrayList<SampleConversion> sampleConversions = SampleConversion.findAllByEntityIdAndOrderDateBetween(eid, fromDate, toDate)
            for (SampleConversion sampleConversion : sampleConversions) {
                JSONObject sampleConversion1 = new JSONObject((sampleConversion as JSON).toString())
                def productDetails = SampleConversionDetails.findAllByBillId(sampleConversion.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    sampleConversion1.put("products", prdt)
                }
                finalBills.add(sampleConversion1)
            }
            return finalBills
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            throw new BadRequestException()
        }
    }

}
