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
class DeliveryChallanService {


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String query)
    {
//        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
//        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return DeliveryChallan.findAll()
        }
        else
        {
            return DeliveryChallan.findAllByFinancialYearIlike("%" + query + "%")
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return DeliveryChallan.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return DeliveryChallan.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }


    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return DeliveryChallan.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 0)
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {

        return DeliveryChallan.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 1)

    }

    DeliveryChallan get(String id)
    {
        return DeliveryChallan.findById(Long.parseLong(id))
    }

    DeliveryChallan getDraftBillById(String id)
    {
        return DeliveryChallan.findByBillStatusAndId('DRAFT', Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        String invoiceStatus = paramsJsonObject.get("invoiceStatus")
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
        def deliveryChallanCriteria = DeliveryChallan.createCriteria()
        def deliveryChallanArrayList = deliveryChallanCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('financialYear', '%' + searchTerm + '%')
                }
            }
            if (!invoiceStatus.equalsIgnoreCase("ALL"))
            {
                eq('billStatus', invoiceStatus)
            }
            eq('customerId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = deliveryChallanArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
//        jsonObject.put("entity", names)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", deliveryChallanArrayList)
        return jsonObject
    }

    DeliveryChallan save(JSONObject jsonObject)
    {
        DeliveryChallan deliveryChallan = new DeliveryChallan()
        deliveryChallan.finId = Long.parseLong(jsonObject.get("finId").toString())
        deliveryChallan.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        deliveryChallan.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        deliveryChallan.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        deliveryChallan.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        deliveryChallan.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        //deliveryChallan.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        deliveryChallan.entryDate = new Date()
        deliveryChallan.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        deliveryChallan.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        deliveryChallan.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        deliveryChallan.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        //deliveryChallan.orderDate = sdf.parse(jsonObject.get("orderDate").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            deliveryChallan.orderDate = new Date()

        deliveryChallan.refOrderId = jsonObject.get("refOrderId").toString()
        deliveryChallan.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        deliveryChallan.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        deliveryChallan.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        deliveryChallan.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        deliveryChallan.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        deliveryChallan.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        deliveryChallan.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        deliveryChallan.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        deliveryChallan.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        deliveryChallan.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        deliveryChallan.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        deliveryChallan.userId = Long.parseLong(jsonObject.get("userId").toString())
        deliveryChallan.balance = Double.parseDouble(jsonObject.get("balance").toString())
        deliveryChallan.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        deliveryChallan.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        deliveryChallan.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        deliveryChallan.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        deliveryChallan.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        deliveryChallan.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        deliveryChallan.gstStatus = jsonObject.get("gstStatus").toString()
        deliveryChallan.billStatus = jsonObject.get("billStatus").toString()
        deliveryChallan.lockStatus = jsonObject.get("lockStatus").toString()
        deliveryChallan.syncStatus = jsonObject.get("syncStatus").toString()
        deliveryChallan.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        deliveryChallan.adjAmount = Double.parseDouble("0")
        deliveryChallan.creditIds = jsonObject.get("creditIds").toString()
        deliveryChallan.referralDoctor = jsonObject.get("referralDoctor").toString()
        deliveryChallan.message = jsonObject.get("message").toString()
        deliveryChallan.financialYear = jsonObject.get("financialYear").toString()
        deliveryChallan.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        deliveryChallan.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        deliveryChallan.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        deliveryChallan.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        deliveryChallan.uuid = jsonObject.get("uuid").toString()
        deliveryChallan.save(flush: true)
        if (!deliveryChallan.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(deliveryChallan.entryDate)
            String month = cal.get(Calendar.MONTH)+1;
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            DeliveryChallan deliveryChallan1
            if (deliveryChallan.billStatus == "DRAFT")
            {
                println(deliveryChallan.billStatus)
//                invoiceNumber = deliveryChallan.entityId+"/DR/DC/" + month + year + "/" + seriesCode + "/__";'
                deliveryChallan.invoiceNumber = null
            }
            else
            {
                invoiceNumber = deliveryChallan.entityId + "DC" + month + year + seriesCode +  deliveryChallan.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                deliveryChallan.invoiceNumber = invoiceNumber
                deliveryChallan.isUpdatable = true
                deliveryChallan.save(flush: true)
            }
            return deliveryChallan
        }
        else
        {
            throw new BadRequestException()
        }
    }

    DeliveryChallan update(JSONObject jsonObject, String id)
    {
        DeliveryChallan deliveryChallan = DeliveryChallan.findById(Long.parseLong(id))
        deliveryChallan.isUpdatable = true
        deliveryChallan.finId = Long.parseLong(jsonObject.get("finId").toString())
        deliveryChallan.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        deliveryChallan.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        deliveryChallan.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        deliveryChallan.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        deliveryChallan.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        // deliveryChallan.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        deliveryChallan.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        deliveryChallan.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        deliveryChallan.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        deliveryChallan.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        //deliveryChallan.orderDate = sdf.parse(jsonObject.get("orderDate").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            deliveryChallan.orderDate = new Date()

        deliveryChallan.refOrderId = jsonObject.get("refOrderId").toString()
        deliveryChallan.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        deliveryChallan.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        deliveryChallan.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        deliveryChallan.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        deliveryChallan.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        deliveryChallan.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        deliveryChallan.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        deliveryChallan.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        deliveryChallan.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        deliveryChallan.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        deliveryChallan.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        deliveryChallan.userId = Long.parseLong(jsonObject.get("userId").toString())
        deliveryChallan.balance = Double.parseDouble(jsonObject.get("balance").toString())
        deliveryChallan.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        deliveryChallan.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        deliveryChallan.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        deliveryChallan.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        deliveryChallan.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        deliveryChallan.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        deliveryChallan.gstStatus = jsonObject.get("gstStatus").toString()
        deliveryChallan.billStatus = jsonObject.get("billStatus").toString()
        deliveryChallan.lockStatus = jsonObject.get("lockStatus").toString()
        deliveryChallan.syncStatus = jsonObject.get("syncStatus").toString()
        deliveryChallan.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        deliveryChallan.adjAmount = Double.parseDouble("0")
        deliveryChallan.creditIds = jsonObject.get("creditIds").toString()
        deliveryChallan.referralDoctor = jsonObject.get("referralDoctor").toString()
        deliveryChallan.message = jsonObject.get("message").toString()
        deliveryChallan.financialYear = jsonObject.get("financialYear").toString()
        deliveryChallan.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        deliveryChallan.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        deliveryChallan.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        deliveryChallan.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        deliveryChallan.uuid = jsonObject.get("uuid").toString()
        deliveryChallan.save(flush: true)
        if (!deliveryChallan.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(deliveryChallan.entryDate)
            String month = cal.get(Calendar.MONTH)+1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            DeliveryChallan deliveryChallan1
            if (deliveryChallan.billStatus == "DRAFT")
            {
                println(deliveryChallan.billStatus)
                deliveryChallan.invoiceNumber = null
            }
            else
            {
                invoiceNumber = deliveryChallan.entityId + "DC" + month + year + seriesCode + deliveryChallan.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                deliveryChallan.invoiceNumber = invoiceNumber
                deliveryChallan.isUpdatable = true
                deliveryChallan.save(flush: true)
            }
            return deliveryChallan
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
            DeliveryChallan deliveryChallan = DeliveryChallan.findById(Long.parseLong(id))
            if (deliveryChallan)
            {
                deliveryChallan.isUpdatable = true
                deliveryChallan.delete()
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
        ArrayList<DeliveryChallan> deliveryChallan =
                DeliveryChallan.findAllByFinancialYearAndEntityIdAndBillStatusNotEqual(financialYear, Long.parseLong(entityId), 'DRAFT', [sort: 'id', order: 'desc'])
        println(deliveryChallan.serBillId)
        jsonObject.put("serBillId", deliveryChallan.serBillId.max())
        jsonObject.put("finId", deliveryChallan.finId.max())
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
            System.err.println('Service :DeliveryChallan , action :  getEntityById  , Ex:' + ex)
            log.error('Service :DeliveryChallan , action :  getEntityById  , Ex:' + ex)
        }
    }


    def getAllByCustomerId(String id,String financialYear,String entityId)
    {
        if (id)
        {
            Object a = DeliveryChallan.findAllByCustomerIdAndFinancialYearAndEntityId(Long.parseLong(id),financialYear,Long.parseLong(entityId))
            return DeliveryChallan.findAllByCustomerIdAndFinancialYearAndEntityId(Long.parseLong(id),financialYear,Long.parseLong(entityId))
        }
    }

    def cancelDeliveryChallan(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject saleInvoice = new JSONObject()
        DeliveryChallan deliveryChallan = DeliveryChallan.findById(Long.parseLong(id))
        if (deliveryChallan)
        {
            if (deliveryChallan.financialYear.equalsIgnoreCase(financialYear) && deliveryChallan.customerId == Long.parseLong(entityId))
            {
                ArrayList<DeliveryChallanProduct> deliveryChallanProducts = DeliveryChallanProduct.findAllByBillId(deliveryChallan.id)
                for (DeliveryChallanProduct deliveryChallanProduct : deliveryChallanProducts)
                {
                    deliveryChallanProduct.status = 0
                    deliveryChallanProduct.isUpdatable = true
                    deliveryChallanProduct.save(flush: true)
                }
                deliveryChallan.billStatus = "CANCELLED"
                deliveryChallan.cancelledDate = new Date()
                deliveryChallan.isUpdatable = true
                deliveryChallan.save(flush: true)

                saleInvoice.put("products", deliveryChallanProducts)
                saleInvoice.put("deliveryChallan", deliveryChallan)
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

    def approveDeliveryChallan(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject gtn = new JSONObject()
        DeliveryChallan deliveryChallan = DeliveryChallan.findById(Long.parseLong(id))
        if (deliveryChallan)
        {
            if (deliveryChallan.financialYear.equalsIgnoreCase(financialYear) && deliveryChallan.entityId == Long.parseLong(entityId))
            {
                ArrayList<DeliveryChallanProduct> deliveryChallanProducts = DeliveryChallanProduct.findAllByBillId(deliveryChallan.id)
                for (DeliveryChallanProduct DeliveryChallanProduct : deliveryChallanProducts)
                {
                    DeliveryChallanProduct.status = 0
                    DeliveryChallanProduct.isUpdatable = true
                    DeliveryChallanProduct.save(flush: true)
                }
                deliveryChallan.billStatus = "APPROVED"
//                deliveryChallan.cancelledDate = new Date()
                deliveryChallan.isUpdatable = true
                deliveryChallan.save(flush: true)
                gtn.put("products", deliveryChallanProducts)
                gtn.put("gtn", deliveryChallan)
                return gtn
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

        DeliveryChallan deliveryChallan = DeliveryChallan.findById(Long.parseLong(id))
        if (deliveryChallan)
        {
            deliveryChallan.isUpdatable = true
            if(jsonObject.has("irnDetails"))
                deliveryChallan.irnDetails = jsonObject.get("irnDetails").toString()
            if(cancelledDate)
                deliveryChallan.cancelledDate = cancelledDate

            deliveryChallan.save(flush:true)
            return deliveryChallan
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
            ArrayList<DeliveryChallan> deliveryChallan = DeliveryChallan.findAllByEntityIdAndEntryDateBetween(eid, fromDate, toDate)
            for (DeliveryChallan dc : deliveryChallan) {
                JSONObject dc1 = new JSONObject((dc as JSON).toString())
                ArrayList<DeliveryChallanProduct> productDetails = DeliveryChallanProduct.findAllByBillId(dc.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    dc1.put("products", prdt)
                }
                finalBills.add(dc1)
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
