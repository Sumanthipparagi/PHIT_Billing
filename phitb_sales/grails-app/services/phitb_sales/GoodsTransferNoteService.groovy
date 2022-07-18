package phitb_sales

import grails.gorm.transactions.Transactional
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
class GoodsTransferNoteService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String query)
    {
//        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
//        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return GoodsTransferNote.findAll()
        }
        else
        {
            return GoodsTransferNote.findAllByFinancialYearIlike("%" + query + "%")
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return GoodsTransferNote.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return GoodsTransferNote.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }


    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return GoodsTransferNote.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 0)
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {

        return GoodsTransferNote.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId), Long.parseLong(entityId), financialYear, 1)

    }

    GoodsTransferNote get(String id)
    {
        return GoodsTransferNote.findById(Long.parseLong(id))
    }

    GoodsTransferNote getDraftBillById(String id)
    {
        return GoodsTransferNote.findByBillStatusAndId('DRAFT', Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        String invoiceStatus = paramsJsonObject.get("invoiceStatus")

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
        def goodsTransferNoteCriteria = GoodsTransferNote.createCriteria()
        def goodsTransferNoteArrayList = goodsTransferNoteCriteria.list(max: max, offset: offset) {
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
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = goodsTransferNoteArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
//        jsonObject.put("entity", names)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", goodsTransferNoteArrayList)
        return jsonObject
    }

    GoodsTransferNote save(JSONObject jsonObject)
    {
        GoodsTransferNote goodsTransferNote = new GoodsTransferNote()
        goodsTransferNote.finId = Long.parseLong(jsonObject.get("finId").toString())
        goodsTransferNote.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        goodsTransferNote.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        goodsTransferNote.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        goodsTransferNote.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        goodsTransferNote.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        //goodsTransferNote.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        goodsTransferNote.entryDate = new Date()
        goodsTransferNote.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        goodsTransferNote.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        goodsTransferNote.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        goodsTransferNote.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        //goodsTransferNote.orderDate = sdf.parse(jsonObject.get("orderDate").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            goodsTransferNote.orderDate = new Date()

        goodsTransferNote.refOrderId = jsonObject.get("refOrderId").toString()
        goodsTransferNote.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        goodsTransferNote.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        goodsTransferNote.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        goodsTransferNote.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        goodsTransferNote.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        goodsTransferNote.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        goodsTransferNote.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        goodsTransferNote.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        goodsTransferNote.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        goodsTransferNote.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        goodsTransferNote.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        goodsTransferNote.userId = Long.parseLong(jsonObject.get("userId").toString())
        goodsTransferNote.balance = Double.parseDouble(jsonObject.get("balance").toString())
        goodsTransferNote.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        goodsTransferNote.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        goodsTransferNote.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        goodsTransferNote.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        goodsTransferNote.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        goodsTransferNote.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        goodsTransferNote.gstStatus = jsonObject.get("gstStatus").toString()
        goodsTransferNote.billStatus = jsonObject.get("billStatus").toString()
        goodsTransferNote.lockStatus = jsonObject.get("lockStatus").toString()
        goodsTransferNote.syncStatus = jsonObject.get("syncStatus").toString()
        goodsTransferNote.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        goodsTransferNote.adjAmount = Double.parseDouble("0")
        goodsTransferNote.creditIds = jsonObject.get("creditIds").toString()
        goodsTransferNote.referralDoctor = jsonObject.get("referralDoctor").toString()
        goodsTransferNote.message = jsonObject.get("message").toString()
        goodsTransferNote.financialYear = jsonObject.get("financialYear").toString()
        goodsTransferNote.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        goodsTransferNote.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        goodsTransferNote.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        goodsTransferNote.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        goodsTransferNote.uuid = jsonObject.get("uuid").toString()
        goodsTransferNote.save(flush: true)
        if (!goodsTransferNote.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(goodsTransferNote.entryDate)
            String month = cal.get(Calendar.MONTH)+1;
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            GoodsTransferNote goodsTransferNote1
            if (goodsTransferNote.billStatus == "DRAFT")
            {
                println(goodsTransferNote.billStatus)
//                invoiceNumber = goodsTransferNote.entityId+"/DR/GTN/" + month + year + "/" + seriesCode + "/__";'
                goodsTransferNote.invoiceNumber = null
            }
            else
            {
                invoiceNumber = goodsTransferNote.entityId + "GTN" + month + year + seriesCode +  goodsTransferNote.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                goodsTransferNote.invoiceNumber = invoiceNumber
                goodsTransferNote.isUpdatable = true
                goodsTransferNote.save(flush: true)
            }
            return goodsTransferNote
        }
        else
        {
            throw new BadRequestException()
        }
    }

    GoodsTransferNote update(JSONObject jsonObject, String id)
    {
        GoodsTransferNote goodsTransferNote = GoodsTransferNote.findById(Long.parseLong(id))
        goodsTransferNote.isUpdatable = true
        goodsTransferNote.finId = Long.parseLong(jsonObject.get("finId").toString())
        goodsTransferNote.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        goodsTransferNote.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        goodsTransferNote.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
        goodsTransferNote.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        goodsTransferNote.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
        // goodsTransferNote.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        goodsTransferNote.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        goodsTransferNote.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        goodsTransferNote.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        goodsTransferNote.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        //goodsTransferNote.orderDate = sdf.parse(jsonObject.get("orderDate").toString())

        if(jsonObject.get("billStatus").toString().equalsIgnoreCase("ACTIVE"))
            goodsTransferNote.orderDate = new Date()

        goodsTransferNote.refOrderId = jsonObject.get("refOrderId").toString()
        goodsTransferNote.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        goodsTransferNote.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        goodsTransferNote.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        goodsTransferNote.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        goodsTransferNote.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        goodsTransferNote.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        goodsTransferNote.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        goodsTransferNote.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        goodsTransferNote.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        goodsTransferNote.invoiceTotal = Double.parseDouble(jsonObject.get("invoiceTotal").toString())
        goodsTransferNote.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        goodsTransferNote.userId = Long.parseLong(jsonObject.get("userId").toString())
        goodsTransferNote.balance = Double.parseDouble(jsonObject.get("balance").toString())
        goodsTransferNote.grossAmount = Double.parseDouble(jsonObject.get("grossAmount").toString())
        goodsTransferNote.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        goodsTransferNote.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        goodsTransferNote.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        goodsTransferNote.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        goodsTransferNote.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        goodsTransferNote.gstStatus = jsonObject.get("gstStatus").toString()
        goodsTransferNote.billStatus = jsonObject.get("billStatus").toString()
        goodsTransferNote.lockStatus = jsonObject.get("lockStatus").toString()
        goodsTransferNote.syncStatus = jsonObject.get("syncStatus").toString()
        goodsTransferNote.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        goodsTransferNote.adjAmount = Double.parseDouble("0")
        goodsTransferNote.creditIds = jsonObject.get("creditIds").toString()
        goodsTransferNote.referralDoctor = jsonObject.get("referralDoctor").toString()
        goodsTransferNote.message = jsonObject.get("message").toString()
        goodsTransferNote.financialYear = jsonObject.get("financialYear").toString()
        goodsTransferNote.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        goodsTransferNote.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        goodsTransferNote.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        goodsTransferNote.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        goodsTransferNote.uuid = jsonObject.get("uuid").toString()
        goodsTransferNote.save(flush: true)
        if (!goodsTransferNote.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(goodsTransferNote.entryDate)
            String month = cal.get(Calendar.MONTH)+1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            GoodsTransferNote goodsTransferNote1
            if (goodsTransferNote.billStatus == "DRAFT")
            {
                println(goodsTransferNote.billStatus)
                goodsTransferNote.invoiceNumber = null
            }
            else
            {
                invoiceNumber = goodsTransferNote.entityId + "GTN" + month + year + seriesCode + goodsTransferNote.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                goodsTransferNote.invoiceNumber = invoiceNumber
                goodsTransferNote.isUpdatable = true
                goodsTransferNote.save(flush: true)
            }
            return goodsTransferNote
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
            GoodsTransferNote goodsTransferNote = GoodsTransferNote.findById(Long.parseLong(id))
            if (goodsTransferNote)
            {
                goodsTransferNote.isUpdatable = true
                goodsTransferNote.delete()
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
        ArrayList<GoodsTransferNote> goodsTransferNote =
                GoodsTransferNote.findAllByFinancialYearAndEntityIdAndBillStatusNotEqual(financialYear, Long.parseLong(entityId), 'DRAFT', [sort: 'id', order: 'desc'])
        println(goodsTransferNote.serBillId)
        jsonObject.put("serBillId", goodsTransferNote.serBillId.max())
        jsonObject.put("finId", goodsTransferNote.finId.max())
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
            System.err.println('Service :GoodsTransferNote , action :  getEntityById  , Ex:' + ex)
            log.error('Service :GoodsTransferNote , action :  getEntityById  , Ex:' + ex)
        }
    }


    def getAllByCustomerId(String id,String financialYear,String entityId)
    {
        if (id)
        {
            Object a = GoodsTransferNote.findAllByCustomerIdAndFinancialYearAndEntityId(Long.parseLong(id),financialYear,Long.parseLong(entityId))
            println(a)
            return GoodsTransferNote.findAllByCustomerIdAndFinancialYearAndEntityId(Long.parseLong(id),financialYear,Long.parseLong(entityId))
        }
    }

    def cancelGTN(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject saleInvoice = new JSONObject()
        GoodsTransferNote goodsTransferNote = GoodsTransferNote.findById(Long.parseLong(id))
        if (goodsTransferNote)
        {
            if (goodsTransferNote.financialYear.equalsIgnoreCase(financialYear) && goodsTransferNote.customerId == Long.parseLong(entityId))
            {
                ArrayList<GoodsTransferNoteProduct> goodsTransferNoteProducts = GoodsTransferNoteProduct.findAllByBillId(goodsTransferNote.id)
                for (GoodsTransferNoteProduct goodsTransferNoteProduct : goodsTransferNoteProducts)
                {
                    goodsTransferNoteProduct.status = 0
                    goodsTransferNoteProduct.isUpdatable = true
                    goodsTransferNoteProduct.save(flush: true)
                }
                goodsTransferNote.billStatus = "CANCELLED"
                goodsTransferNote.cancelledDate = new Date()
                goodsTransferNote.isUpdatable = true
                goodsTransferNote.save(flush: true)

                saleInvoice.put("products", goodsTransferNoteProducts)
                saleInvoice.put("gtn", goodsTransferNote)
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

    def approveGTN(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject gtn = new JSONObject()
        GoodsTransferNote goodsTransferNote = GoodsTransferNote.findById(Long.parseLong(id))
        if (goodsTransferNote)
        {
            if (goodsTransferNote.financialYear.equalsIgnoreCase(financialYear) && goodsTransferNote.entityId == Long.parseLong(entityId))
            {
                ArrayList<GoodsTransferNoteProduct> goodsTransferNoteProducts = GoodsTransferNoteProduct.findAllByBillId(goodsTransferNote.id)
                for (GoodsTransferNoteProduct GoodsTransferNoteProduct : goodsTransferNoteProducts)
                {
                    GoodsTransferNoteProduct.status = 0
                    GoodsTransferNoteProduct.isUpdatable = true
                    GoodsTransferNoteProduct.save(flush: true)
                }
                goodsTransferNote.billStatus = "APPROVED"
//                goodsTransferNote.cancelledDate = new Date()
                goodsTransferNote.isUpdatable = true
                goodsTransferNote.save(flush: true)
                gtn.put("products", goodsTransferNoteProducts)
                gtn.put("gtn", goodsTransferNote)
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

        GoodsTransferNote goodsTransferNote = GoodsTransferNote.findById(Long.parseLong(id))
        if (goodsTransferNote)
        {
            goodsTransferNote.isUpdatable = true
            if(jsonObject.has("irnDetails"))
                goodsTransferNote.irnDetails = jsonObject.get("irnDetails").toString()
            if(cancelledDate)
                goodsTransferNote.cancelledDate = cancelledDate

            goodsTransferNote.save(flush:true)
            return goodsTransferNote
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }
}
