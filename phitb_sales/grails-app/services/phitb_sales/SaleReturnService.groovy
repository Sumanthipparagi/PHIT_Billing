package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SaleReturnService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

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
        saleReturn.series = Long.parseLong(jsonObject.get("series").toString())
        saleReturn.type = jsonObject.get("type").toString()
        saleReturn.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        saleReturn.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        saleReturn.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        saleReturn.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        saleReturn.refId = jsonObject.get("refId").toString()
        saleReturn.maxDnAmount = Double.parseDouble(jsonObject.get("maxDnAmount").toString())
        saleReturn.supplierContact = jsonObject.get("supplierContact").toString()
        saleReturn.supplierEmail = jsonObject.get("supplierEmail").toString()
        saleReturn.gross = Double.parseDouble(jsonObject.get("gross").toString())
        saleReturn.taxable = Double.parseDouble(jsonObject.get("taxable").toString())
        saleReturn.totalGst = Double.parseDouble(jsonObject.get("totalGst").toString())
        saleReturn.totalCgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleReturn.totalSgst = Double.parseDouble(jsonObject.get("totalCgst").toString())
        saleReturn.totalIgst = Double.parseDouble(jsonObject.get("totalIgst").toString())
        saleReturn.exempted = Double.parseDouble(jsonObject.get("exempted").toString())
        saleReturn.cashDiscount = Double.parseDouble(jsonObject.get("cashDiscount").toString())
        saleReturn.items = Integer.parseInt(jsonObject.get("items").toString())
        saleReturn.quantity = Integer.parseInt(jsonObject.get("quantity").toString())
        saleReturn.totalAmount = Double.parseDouble(jsonObject.get("quantity").toString())
        saleReturn.balance = Double.parseDouble(jsonObject.get("balance").toString())
        saleReturn.dbAdjAmount = Double.parseDouble(jsonObject.get("dbAdjAmount").toString())
        saleReturn.totalDiscount = Double.parseDouble(jsonObject.get("totalDiscount").toString())
        saleReturn.debitIds = jsonObject.get("debitIds").toString()
        saleReturn.adjAmount = 0
        saleReturn.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        saleReturn.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
        saleReturn.adjustmentStatus = jsonObject.get("adjustmentStatus").toString()
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
//            String seriesCode = jsonObject.get("seriesCode")
            SaleReturn saleReturn1
            invoiceNumber = saleReturn.entityId + "/SR/" + month + year + "/" + saleReturn.id
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
}
