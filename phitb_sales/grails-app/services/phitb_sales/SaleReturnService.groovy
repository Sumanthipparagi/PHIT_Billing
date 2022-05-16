package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SaleReturnService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
    SimpleDateFormat sdf1 = new SimpleDateFormat("DD/MM/YYYY")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleReturn.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SaleReturn.findAllByRefId("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    SaleReturn get(String id)
    {
        return SaleReturn.findById(Long.parseLong(id))
    }

    SaleReturn save(JSONObject jsonObject)
    {

        SaleReturn saleReturn = new SaleReturn()
        saleReturn.finId =  Long.parseLong(jsonObject.get("finId").toString())
        saleReturn.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleReturn.series = Long.parseLong(jsonObject.get("seriesId").toString())
        saleReturn.type = jsonObject.get("type").toString()
        saleReturn.customerId = Long.parseLong(jsonObject.get("customer").toString())
        saleReturn.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        saleReturn.returnStatus = jsonObject.get("billStatus").toString()
        saleReturn.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        saleReturn.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        saleReturn.refId = jsonObject.get("refId").toString()
        saleReturn.maxDnAmount = Double.parseDouble(jsonObject.get("maxDnAmount").toString())
        saleReturn.supplierContact = jsonObject.get("supplierContact").toString()
        saleReturn.supplierEmail = jsonObject.get("supplierEmail").toString()
        saleReturn.gross = Double.parseDouble(jsonObject.get("gross").toString())
        saleReturn.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
        saleReturn.lrNo = jsonObject.get('lrNo').toString()
        saleReturn.lrDate = sdf.parse(jsonObject.get('lrDate').toString())
        saleReturn.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        saleReturn.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleReturn.totalSgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleReturn.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        saleReturn.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        saleReturn.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        saleReturn.items = Integer.parseInt(jsonObject.get("items").toString())
        saleReturn.quantity = Integer.parseInt(jsonObject.get("quantity").toString())
        saleReturn.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        saleReturn.balance = Double.parseDouble(jsonObject.get("balance").toString())
        saleReturn.dbAdjAmount = Double.parseDouble(jsonObject.get("dbAdjAmount").toString())
        saleReturn.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        saleReturn.debitIds = jsonObject.get("debitIds").toString()
        saleReturn.adjAmount = 0
        saleReturn.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        saleReturn.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        saleReturn.adjustmentStatus = jsonObject.get("adjustmentStatus").toString()
        saleReturn.uuid = jsonObject.get("uuid").toString()
        saleReturn.message = jsonObject.get("message").toString()
        saleReturn.ignoreSold = Integer.parseInt(jsonObject.get("ignoreSold").toString())
        saleReturn.financialYear = jsonObject.get("financialYear").toString()
        saleReturn.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleReturn.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        saleReturn.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        saleReturn.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        saleReturn.save(flush: true)
        if (!saleReturn.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(saleReturn.entryDate)
            String month = cal.get(Calendar.MONTH)
            String year = cal.get(Calendar.YEAR)
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            SaleReturn saleReturn1
            invoiceNumber = saleReturn.entityId + "/SR/" + month + year + "/" + seriesCode + "/" + saleReturn.serBillId
            println("Invoice Number generated: " + invoiceNumber)
            if (invoiceNumber)
            {
                saleReturn.invoiceNumber = invoiceNumber
                saleReturn.isUpdatable = true
                saleReturn.save(flush: true)
            }
            return saleReturn
        }
        else
        {
            throw new BadRequestException()
        }
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleReturn.findAllByCustomerIdAndEntityIdAndFinancialYearAndAdjustmentStatus(Long.parseLong(customerId) as
                String, Long.parseLong(entityId),financialYear,"1")
    }

    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleReturn.findAllByCustomerIdAndEntityIdAndFinancialYearAndAdjustmentStatus(Long.parseLong(customerId) as
                String, Long.parseLong(entityId),financialYear,"0")
    }

    def getAllByCustomerId(String customerId, String entityId, String financialYear)
    {
        return SaleReturn.findAllByCustomerIdAndEntityIdAndFinancialYear(customerId,Long.parseLong(entityId),
                financialYear)
    }

    JSONObject getRecentByFinancialYearAndEntity(String financialYear, String entityId)
    {

        JSONObject jsonObject = new JSONObject()
        ArrayList<SaleReturnDetails> saleReturnDetails =
                SaleReturnDetails.findAllByFinancialYearAndEntityId(financialYear, Long.parseLong(entityId), [sort: 'id', order:
                        'desc'])
        jsonObject.put("serBillId", saleReturnDetails.serBillId.max())
        jsonObject.put("finId", saleReturnDetails.finId.max())
        return jsonObject

    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
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
        def saleReturnCriteria = SaleReturn.createCriteria()
        def saleReturnArrayList = saleReturnCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('financialYear', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = saleReturnArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
//        jsonObject.put("entity", names)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", saleReturnArrayList)
        return jsonObject
    }


    def cancelSaleRetruns(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject saleInvoice = new JSONObject()
        SaleReturn saleReturn = SaleReturn.findById(Long.parseLong(id))
        if (saleReturn)
        {
            if (saleReturn.financialYear.equalsIgnoreCase(financialYear) && saleReturn.entityId == Long.parseLong(entityId))
            {
                ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllByBillId(saleReturn.id)
                for (SaleReturnDetails saleReturnDetail : saleReturnDetails)
                {
                    saleReturnDetail.status = 0
                    saleReturnDetail.returnStatus = "CANCELLED"
                    saleReturnDetail.isUpdatable = true
                    saleReturnDetail.save(flush: true)
                }
                saleReturn.returnStatus = "CANCELLED"
                saleReturn.cancelledDate = new Date()
                saleReturn.isUpdatable = true
                saleReturn.save(flush: true)

                saleInvoice.put("products", saleReturnDetails)
                saleInvoice.put("invoice", saleReturn)
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



   /* JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
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
        def saleReturnCriteria = SaleReturn.createCriteria()
        def saleReturnArrayList = saleReturnCriteria.list(max: max, offset: offset) {
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
        def recordsTotal = saleReturnArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
//        jsonObject.put("entity", names)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", saleReturnArrayList)
        return jsonObject
    }
*/
}
