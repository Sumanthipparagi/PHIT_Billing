package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SaleReturnDetailsService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleReturnDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SaleReturnDetails.findAllByRefId("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    SaleReturnDetails get(String id)
    {
        return SaleReturnDetails.findById(Long.parseLong(id))
    }

    SaleReturnDetails save(JSONObject jsonObject)
    {
        SaleReturnDetails saleReturnDetails = new SaleReturnDetails()
        saleReturnDetails.finId =  Long.parseLong(jsonObject.get("finId").toString())
        saleReturnDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleReturnDetails.series = Long.parseLong(jsonObject.get("series").toString())
        saleReturnDetails.type = jsonObject.get("type").toString()
        saleReturnDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        saleReturnDetails.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        saleReturnDetails.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        saleReturnDetails.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        saleReturnDetails.refId = jsonObject.get("refId").toString()
        saleReturnDetails.maxDnAmount = Double.parseDouble(jsonObject.get("maxDnAmount").toString())
        saleReturnDetails.supplierContact = jsonObject.get("supplierContact").toString()
        saleReturnDetails.supplierEmail = jsonObject.get("supplierEmail").toString()
        saleReturnDetails.gross = Double.parseDouble(jsonObject.get("gross").toString())
        saleReturnDetails.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
        saleReturnDetails.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        saleReturnDetails.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleReturnDetails.totalSgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleReturnDetails.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        saleReturnDetails.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        saleReturnDetails.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        saleReturnDetails.items = Integer.parseInt(jsonObject.get("items").toString())
        saleReturnDetails.quantity = Integer.parseInt(jsonObject.get("quantity").toString())
        saleReturnDetails.totalAmount = Double.parseDouble(jsonObject.get("quantity").toString())
        saleReturnDetails.balance = Double.parseDouble(jsonObject.get("balance").toString())
        saleReturnDetails.dbAdjAmount = Double.parseDouble(jsonObject.get("dbAdjAmount").toString())
        saleReturnDetails.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        saleReturnDetails.debitIds = jsonObject.get("debitIds").toString()
        saleReturnDetails.adjAmount = 0
        saleReturnDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        saleReturnDetails.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        saleReturnDetails.adjustmentStatus = jsonObject.get("adjustmentStatus").toString()
        saleReturnDetails.message = jsonObject.get("message").toString()
        saleReturnDetails.ignoreSold = Integer.parseInt(jsonObject.get("ignoreSold").toString())
        saleReturnDetails.financialYear = jsonObject.get("financialYear").toString()
        saleReturnDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleReturnDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        saleReturnDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        saleReturnDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        saleReturnDetails.save(flush: true)
        if (!saleReturnDetails.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(saleReturnDetails.entryDate)
            String month = cal.get(Calendar.MONTH)
            String year = cal.get(Calendar.YEAR)
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String invoiceNumber = null;
//            String seriesCode = jsonObject.get("seriesCode")
            SaleReturnDetails saleReturnDetails1
            invoiceNumber = saleReturnDetails.entityId + "/S/" + month + year + "/" + saleReturnDetails.serBillId
            println("Invoice Number generated: " + invoiceNumber)
            if (invoiceNumber)
            {
                saleReturnDetails.invoiceNumber = invoiceNumber
                saleReturnDetails.isUpdatable = true
                saleReturnDetails.save(flush: true)
            }
            return saleReturnDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleReturnDetails.findAllByCustomerIdAndEntityIdAndFinancialYearAndAdjustmentStatus(Long.parseLong(customerId) as
                String, Long.parseLong(entityId),financialYear,"1")
    }

    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleReturnDetails.findAllByCustomerIdAndEntityIdAndFinancialYearAndAdjustmentStatus(Long.parseLong(customerId) as
                String, Long.parseLong(entityId),financialYear,"0")
    }

    def getAllByCustomerId(String customerId, String entityId, String financialYear)
    {
        return SaleReturnDetails.findAllByCustomerIdAndEntityIdAndFinancialYear(customerId,Long.parseLong(entityId),
                financialYear)
    }
}
