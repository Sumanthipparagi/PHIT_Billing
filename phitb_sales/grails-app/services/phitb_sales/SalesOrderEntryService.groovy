package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SalesOrderEntryService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SalesOrderEntry.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SalesOrderEntry.findAllByRefNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SalesOrderEntry.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SalesOrderEntry.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }
    SalesOrderEntry get(String id)
    {
        return SalesOrderEntry.findById(Long.parseLong(id))
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
                orderColumn = "refNumber"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def salesOrderEntryCriteria = SalesOrderEntry.createCriteria()
        def salesOrderEntryArrayList = salesOrderEntryCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('refNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = salesOrderEntryArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", salesOrderEntryArrayList)
        return jsonObject
    }

    SalesOrderEntry save(JSONObject jsonObject)
    {
        SalesOrderEntry salesOrderEntry = new SalesOrderEntry()
        salesOrderEntry.finId =  Long.parseLong(jsonObject.get("finId").toString())
        salesOrderEntry.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        salesOrderEntry.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        salesOrderEntry.refNumber = jsonObject.get("refNumber").toString()
        salesOrderEntry.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        salesOrderEntry.refDate =sdf.parse(jsonObject.get("refDate").toString())
        salesOrderEntry.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        salesOrderEntry.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        salesOrderEntry.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
        salesOrderEntry.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        salesOrderEntry.orderValidity = sdf.parse(jsonObject.get("orderValidity").toString())
        salesOrderEntry.orderId = jsonObject.get("orderId").toString()
        salesOrderEntry.totalEstimate = Long.parseLong(jsonObject.get("totalEstimate").toString())
        salesOrderEntry.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        salesOrderEntry.billStatus = jsonObject.get("billStatus").toString()
        salesOrderEntry.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        salesOrderEntry.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        salesOrderEntry.orderMechanism = jsonObject.get("orderMechanism").toString()
        salesOrderEntry.purchaseQuotationId = jsonObject.get("purchaseQuotationId").toString()
        salesOrderEntry.confirmationStatus = jsonObject.get("gstId").toString()
        salesOrderEntry.financialYear = jsonObject.get("financialYear").toString()
        salesOrderEntry.uuid = jsonObject.get('uuid').toString()
        salesOrderEntry.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        salesOrderEntry.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        salesOrderEntry.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        salesOrderEntry.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        salesOrderEntry.save(flush: true)
        if (!salesOrderEntry.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(salesOrderEntry.entryDate)
            String month = cal.get(Calendar.MONTH)+1
            String year = cal.get(Calendar.YEAR)
            year = year.substring(Math.max(year.length() - 2, 0)) //reduce to 2 digit year
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
            String seriesCode = jsonObject.get("seriesCode")
            SaleBillDetails saleBillDetails1
            if (salesOrderEntry.billStatus == "DRAFT")
            {
                println(salesOrderEntry.billStatus)
                salesOrderEntry.invoiceNumber = null
            }
            else
            {
                invoiceNumber = salesOrderEntry.entityId + "SO" + month + year  + seriesCode  + salesOrderEntry.serBillId
                println("Invoice Number generated: " + invoiceNumber)
            }
            if (invoiceNumber)
            {
                salesOrderEntry.invoiceNumber = invoiceNumber
                salesOrderEntry.isUpdatable = true
                salesOrderEntry.save(flush: true)
            }
            return salesOrderEntry
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SalesOrderEntry update(JSONObject jsonObject, String id)
    {
        SalesOrderEntry salesOrderEntry = SalesOrderEntry.findById(Long.parseLong(id))
        if (salesOrderEntry)
        {
            salesOrderEntry.isUpdatable = true
            salesOrderEntry.finId =  Long.parseLong(jsonObject.get("finId").toString())
            salesOrderEntry.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            salesOrderEntry.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
            salesOrderEntry.refNumber = jsonObject.get("refNumber").toString()
            salesOrderEntry.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            salesOrderEntry.refDate =sdf.parse(jsonObject.get("refDate").toString())
            salesOrderEntry.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
            salesOrderEntry.customerId = Long.parseLong(jsonObject.get("customerId").toString())
            salesOrderEntry.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
            salesOrderEntry.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
            salesOrderEntry.orderValidity = sdf.parse(jsonObject.get("orderValidity").toString())
            salesOrderEntry.orderId = jsonObject.get("orderId").toString()
            salesOrderEntry.totalEstimate = Long.parseLong(jsonObject.get("totalEstimate").toString())
            salesOrderEntry.totalGst = Long.parseLong(jsonObject.get("totalGst").toString())
            salesOrderEntry.billStatus = jsonObject.get("billStatus").toString()
            salesOrderEntry.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
            salesOrderEntry.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            salesOrderEntry.orderMechanism = jsonObject.get("orderMechanism").toString()
            salesOrderEntry.purchaseQuotationId = jsonObject.get("purchaseQuotationId").toString()
            salesOrderEntry.confirmationStatus = jsonObject.get("gstId").toString()
            salesOrderEntry.financialYear = jsonObject.get("financialYear").toString()
            salesOrderEntry.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            salesOrderEntry.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            salesOrderEntry.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            salesOrderEntry.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            salesOrderEntry.save(flush: true)
            if (!salesOrderEntry.hasErrors())
            {
                return salesOrderEntry
            }
            else
            {
                throw new BadRequestException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    void delete(String id)
    {
        if (id)
        {
            SalesOrderEntry salesOrderEntry = SalesOrderEntry.findById(Long.parseLong(id))
            if (salesOrderEntry)
            {
                salesOrderEntry.isUpdatable = true
                salesOrderEntry.delete()
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
        ArrayList<SalesOrderEntry> salesOrderEntry =
                SalesOrderEntry.findAllByFinancialYearAndEntityIdAndBillStatusNotEqual(financialYear, Long.parseLong(entityId), 'DRAFT', [sort: 'id', order: 'desc'])
        println(salesOrderEntry.serBillId)
        jsonObject.put("serBillId", salesOrderEntry.serBillId.max())
        jsonObject.put("finId", salesOrderEntry.finId.max())
        return jsonObject

    }

    def cancelSaleOrder(JSONObject jsonObject)
    {
        String id = jsonObject.get("id")
        String entityId = jsonObject.get("entityId")
        String financialYear = jsonObject.get("financialYear")
        JSONObject saleInvoice = new JSONObject()
        SalesOrderEntry saleOrderentry = SalesOrderEntry.findById(Long.parseLong(id))
        if (saleOrderentry)
        {
            if (saleOrderentry.financialYear.equalsIgnoreCase(financialYear) && saleOrderentry.entityId == Long.parseLong(entityId))
            {
                ArrayList<SaleOrderProductDetails> saleOrderProductDetails = SaleOrderProductDetails.findAllByBillId(saleOrderentry.id)
                for (SaleOrderProductDetails saleOrderProductDetail : saleOrderProductDetails)
                {
                    saleOrderProductDetail.status = 0
                    saleOrderProductDetail.isUpdatable = true
                    saleOrderProductDetail.save(flush: true)
                }
                saleOrderentry.billStatus = "CANCELLED"
                saleOrderentry.cancelledDate = new Date()
                saleOrderentry.isUpdatable = true
                saleOrderentry.save(flush: true)

                saleInvoice.put("products", saleOrderProductDetails)
                saleInvoice.put("invoice", saleOrderentry)
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
}
