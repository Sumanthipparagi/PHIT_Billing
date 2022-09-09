package phitb_sales

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SalesOrderEntryService
{

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
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SalesOrderEntry.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }

    SalesOrderEntry get(String id)
    {
        return SalesOrderEntry.findById(Long.parseLong(id))
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
            ArrayList<SalesOrderEntry> salesOrderEntries = SalesOrderEntry.findAllByEntityIdAndEntryDateBetween(eid, fromDate, toDate)
            for (SalesOrderEntry saleOrder : salesOrderEntries) {
                JSONObject saleOrder1 = new JSONObject((saleOrder as JSON).toString())
                ArrayList<SaleOrderProductDetails> productDetails = SaleOrderProductDetails.findAllByBillId(saleOrder.id)
                if (productDetails) {
                    JSONArray prdt =  new  JSONArray((productDetails as JSON).toString())
                    saleOrder1.put("products", prdt)
                }

                finalBills.add(saleOrder1)
            }
            return finalBills
        }
        catch (Exception ex)
        {
            ex.printStackTrace()
            throw new BadRequestException()
        }
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
        salesOrderEntry.finId = Long.parseLong(jsonObject.get("finId").toString())
        salesOrderEntry.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        salesOrderEntry.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        salesOrderEntry.refNumber = jsonObject.get("refNumber").toString()
        salesOrderEntry.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        salesOrderEntry.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        salesOrderEntry.refDate = sdf.parse(jsonObject.get("refDate").toString())
        salesOrderEntry.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
        salesOrderEntry.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        salesOrderEntry.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        salesOrderEntry.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
        salesOrderEntry.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        salesOrderEntry.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
        salesOrderEntry.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        salesOrderEntry.orderValidity = sdf.parse(jsonObject.get("orderValidity").toString())
        salesOrderEntry.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        salesOrderEntry.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
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
            String month = cal.get(Calendar.MONTH) + 1
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
                invoiceNumber = salesOrderEntry.entityId + "SO" + month + year + seriesCode + salesOrderEntry.serBillId
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
            salesOrderEntry.finId = Long.parseLong(jsonObject.get("finId").toString())
            salesOrderEntry.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            salesOrderEntry.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
            salesOrderEntry.refNumber = jsonObject.get("refNumber").toString()
            salesOrderEntry.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
            salesOrderEntry.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            salesOrderEntry.refDate = sdf.parse(jsonObject.get("refDate").toString())
            salesOrderEntry.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
            salesOrderEntry.totalSgst = Double.parseDouble(jsonObject.get("totalSgst").toString())
            salesOrderEntry.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
            salesOrderEntry.totalAmount = Double.parseDouble(jsonObject.get("totalAmount").toString())
            salesOrderEntry.customerId = Long.parseLong(jsonObject.get("customerId").toString())
            salesOrderEntry.transportTypeId = Long.parseLong(jsonObject.get("transportTypeId").toString())
            salesOrderEntry.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
            salesOrderEntry.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
            salesOrderEntry.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
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

    def convertToSaleEntry(JSONObject jsonObject)
    {
        try
        {
            SalesOrderEntry salesOrderEntry = SalesOrderEntry.findById(Long.parseLong(jsonObject.id))
            if (salesOrderEntry)
            {
                SaleBillDetails saleBillDetails = new SaleBillDetails()
                saleBillDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
                saleBillDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
                saleBillDetails.seriesId = salesOrderEntry.seriesId
                saleBillDetails.paymentStatus = Long.parseLong("0")
                saleBillDetails.accountModeId = salesOrderEntry.accountModeId
                saleBillDetails.priorityId = salesOrderEntry.priorityId
                saleBillDetails.entryDate = new Date()
                saleBillDetails.customerId = salesOrderEntry.customerId
                saleBillDetails.customerNumber = Long.parseLong("0")
                saleBillDetails.salesmanId = salesOrderEntry.salesmanId
                saleBillDetails.salesmanComm = Long.parseLong("0")
                saleBillDetails.refOrderId = Long.parseLong("0")
                saleBillDetails.dueDate = salesOrderEntry.dueDate
                saleBillDetails.dispatchDate = new Date()
                saleBillDetails.deliveryManId = Long.parseLong("0")
                saleBillDetails.totalSqty = salesOrderEntry.totalSqty
                saleBillDetails.totalFqty = salesOrderEntry.totalFqty
                saleBillDetails.totalItems = salesOrderEntry.totalSqty + salesOrderEntry.totalFqty
                saleBillDetails.totalQty = salesOrderEntry.totalSqty + salesOrderEntry.totalFqty
                saleBillDetails.totalDiscount = salesOrderEntry.totalDiscount
                saleBillDetails.totalAmount = salesOrderEntry.totalAmount
                saleBillDetails.invoiceTotal = salesOrderEntry.totalAmount
                saleBillDetails.totalGst = salesOrderEntry.totalGst
                saleBillDetails.userId = jsonObject.userId
                saleBillDetails.balance = salesOrderEntry.totalAmount
                saleBillDetails.grossAmount = salesOrderEntry.totalAmount + salesOrderEntry.totalDiscount
                saleBillDetails.cashDiscount = Double.parseDouble("0")
                saleBillDetails.exempted = Double.parseDouble("0")
                saleBillDetails.totalCgst = salesOrderEntry.totalCgst
                saleBillDetails.totalSgst = salesOrderEntry.totalSgst
                saleBillDetails.totalIgst = salesOrderEntry.totalIgst
                saleBillDetails.gstStatus = "0"
                saleBillDetails.billStatus = salesOrderEntry.billStatus
                saleBillDetails.lockStatus = salesOrderEntry.lockStatus
                saleBillDetails.syncStatus = salesOrderEntry.syncStatus
                saleBillDetails.creditadjAmount = Double.parseDouble("0")
                saleBillDetails.adjAmount = Double.parseDouble("0")
                saleBillDetails.creditIds = "0"
                saleBillDetails.referralDoctor = "0"
                saleBillDetails.message = "0"
                saleBillDetails.financialYear = jsonObject.get("financialYear").toString()
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
                        invoiceNumber = saleBillDetails.entityId + "S" + month + year + seriesCode + saleBillDetails.serBillId
                        println("Invoice Number generated: " + invoiceNumber)
                    }
                    if (invoiceNumber)
                    {
                        saleBillDetails.invoiceNumber = invoiceNumber
                        saleBillDetails.isUpdatable = true
                        saleBillDetails.save(flush: true)
                    }
                    if(saleBillDetails)
                    {
                        ArrayList<SaleOrderProductDetails> saleOrderProductDetails = SaleOrderProductDetails.findAllByBillId(Long.parseLong(salesOrderEntry.id.toString()))
                        if (saleOrderProductDetails)
                        {
                            UUID uuid
                            for (def saleOrderProductDetail : saleOrderProductDetails)
                            {
                                uuid = UUID.randomUUID()
                                SaleProductDetails saleProductDetails = new SaleProductDetails()
                                saleProductDetails.finId = saleBillDetails.finId
                                saleProductDetails.billId = saleBillDetails.id
                                saleProductDetails.reason = "0"
                                saleProductDetails.billType = Long.parseLong("0")
                                saleProductDetails.serBillId = saleBillDetails.serBillId
                                saleProductDetails.seriesId = saleBillDetails.seriesId
                                saleProductDetails.productId = saleOrderProductDetail.productId
                                saleProductDetails.batchNumber = saleOrderProductDetail.batchNumber
                                saleProductDetails.expiryDate = saleOrderProductDetail.expiryDate
                                saleProductDetails.sqty = saleOrderProductDetail.sqty
                                saleProductDetails.originalSqty = saleOrderProductDetail.originalSqty
                                saleProductDetails.originalFqty = saleOrderProductDetail.originalFqty
                                saleProductDetails.freeQty = saleOrderProductDetail.freeQty
                                saleProductDetails.repQty = saleOrderProductDetail.repQty
                                saleProductDetails.pRate = saleOrderProductDetail.pRate
                                saleProductDetails.sRate = saleOrderProductDetail.sRate
                                saleProductDetails.mrp = saleOrderProductDetail.mrp
                                saleProductDetails.discount = saleOrderProductDetail.discount
                                saleProductDetails.gstId = saleOrderProductDetail.gstId
                                saleProductDetails.gstAmount = saleOrderProductDetail.gstAmount
                                saleProductDetails.sgstAmount = saleOrderProductDetail.sgstAmount
                                saleProductDetails.cgstAmount = saleOrderProductDetail.cgstAmount
                                saleProductDetails.igstAmount = saleOrderProductDetail.igstAmount
                                saleProductDetails.amount = saleOrderProductDetail.sgstAmount
                                saleProductDetails.reason = saleOrderProductDetail.reason
                                saleProductDetails.fridgeId = saleOrderProductDetail.fridgeId
                                saleProductDetails.kitName = saleOrderProductDetail.kitName
                                saleProductDetails.saleFinId = saleOrderProductDetail.saleFinId
                                saleProductDetails.redundantBatch = saleOrderProductDetail.redundantBatch
                                saleProductDetails.status = saleOrderProductDetail.status
                                saleProductDetails.syncStatus = saleOrderProductDetail.syncStatus
                                saleProductDetails.financialYear = saleBillDetails.financialYear
                                saleProductDetails.entityTypeId = saleBillDetails.entityTypeId
                                saleProductDetails.entityId = saleBillDetails.entityId

                                saleProductDetails.gstPercentage = saleOrderProductDetail.gstPercentage
                                saleProductDetails.sgstPercentage = saleOrderProductDetail.sgstPercentage
                                saleProductDetails.cgstPercentage = saleOrderProductDetail.cgstPercentage
                                saleProductDetails.igstPercentage = saleOrderProductDetail.igstPercentage
                                saleProductDetails.uuid = uuid.toString()
                                SaleProductDetails saleProductDetails1 = saleProductDetails.save(flush: true)
                                if (saleProductDetails1)
                                {
                                    println("Product Saved!")
                                    salesOrderEntry.isUpdatable = true;
                                    salesOrderEntry.billStatus = "CONVERTED";
                                    salesOrderEntry.save(flush:true)
                                }
                            }
                        }
                        return saleBillDetails
                    }
                    else
                    {
                        throw new BadRequestException()
                    }
                }
            }
        }
        catch (Exception ex)
        {
            println("SaleOrderEntryService"+ex)
        }
    }
}
