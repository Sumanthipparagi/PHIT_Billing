package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SaleBillDetailsService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleBillDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SaleBillDetails.findAllByFinancialYearIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
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
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SaleBillDetails.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }
    SaleBillDetails get(String id)
    {
        return SaleBillDetails.findById(Long.parseLong(id))
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
        def saleBillDetailsCriteria = SaleBillDetails.createCriteria()
        def saleBillDetailsArrayList = saleBillDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('financialYear', '%' + searchTerm + '%')
                }
            }
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
        saleBillDetails.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
        saleBillDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        saleBillDetails.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
        saleBillDetails.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
        saleBillDetails.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
        saleBillDetails.orderDate = sdf.parse(jsonObject.get("orderDate").toString())
        saleBillDetails.refOrderId = jsonObject.get("refOrderId").toString()
        saleBillDetails.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
        saleBillDetails.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
        saleBillDetails.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
        saleBillDetails.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
        saleBillDetails.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
        saleBillDetails.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
        saleBillDetails.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
        saleBillDetails.totalDiscount =Double.parseDouble(jsonObject.get("totalDiscount").toString())
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
        saleBillDetails.gstStatus = jsonObject.get("gstStatus").toString()
        saleBillDetails.billStatus = jsonObject.get("billStatus").toString()
        saleBillDetails.lockStatus = jsonObject.get("lockStatus").toString()
        saleBillDetails.syncStatus = jsonObject.get("syncStatus").toString()
        saleBillDetails.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
        saleBillDetails.creditIds = jsonObject.get("creditadjAmount").toString()
        saleBillDetails.referralDoctor = jsonObject.get("referralDoctor").toString()
        saleBillDetails.message = jsonObject.get("message").toString()
        saleBillDetails.financialYear = jsonObject.get("financialYear").toString()
        saleBillDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleBillDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        saleBillDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        saleBillDetails.save(flush: true)
        if (!saleBillDetails.hasErrors())
        {
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
        if (saleBillDetails)
        {
            saleBillDetails.isUpdatable = true
            saleBillDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
            saleBillDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            saleBillDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            saleBillDetails.paymentStatus = Long.parseLong(jsonObject.get("paymentStatus").toString())
            saleBillDetails.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
            saleBillDetails.priorityId = Long.parseLong(jsonObject.get("priorityId").toString())
            saleBillDetails.entryDate = sdf.parse(jsonObject.get("entryDate").toString())
            saleBillDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
            saleBillDetails.customerNumber = Long.parseLong(jsonObject.get("customerNumber").toString())
            saleBillDetails.salesmanId = Long.parseLong(jsonObject.get("salesmanId").toString())
            saleBillDetails.salesmanComm = Long.parseLong(jsonObject.get("salesmanComm").toString())
            saleBillDetails.orderDate = sdf.parse(jsonObject.get("orderDate").toString())
            saleBillDetails.refOrderId = jsonObject.get("refOrderId").toString()
            saleBillDetails.dueDate = sdf.parse(jsonObject.get("dueDate").toString())
            saleBillDetails.dispatchDate = sdf.parse(jsonObject.get("dispatchDate").toString())
            saleBillDetails.deliveryManId = Long.parseLong(jsonObject.get("deliveryManId").toString())
            saleBillDetails.totalSqty = Double.parseDouble(jsonObject.get("totalSqty").toString())
            saleBillDetails.totalFqty = Double.parseDouble(jsonObject.get("totalFqty").toString())
            saleBillDetails.totalItems = Double.parseDouble(jsonObject.get("totalItems").toString())
            saleBillDetails.totalQty = Double.parseDouble(jsonObject.get("totalQty").toString())
            saleBillDetails.totalDiscount =Double.parseDouble(jsonObject.get("totalDiscount").toString())
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
            saleBillDetails.gstStatus = jsonObject.get("gstStatus").toString()
            saleBillDetails.billStatus = jsonObject.get("billStatus").toString()
            saleBillDetails.lockStatus = jsonObject.get("lockStatus").toString()
            saleBillDetails.syncStatus = jsonObject.get("syncStatus").toString()
            saleBillDetails.creditadjAmount = Double.parseDouble(jsonObject.get("creditadjAmount").toString())
            saleBillDetails.creditIds = jsonObject.get("creditadjAmount").toString()
            saleBillDetails.referralDoctor = jsonObject.get("referralDoctor").toString()
            saleBillDetails.message = jsonObject.get("message").toString()
            saleBillDetails.financialYear = jsonObject.get("financialYear").toString()
            saleBillDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            saleBillDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            saleBillDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            saleBillDetails.save(flush: true)
            if (!saleBillDetails.hasErrors())
            {
                return saleBillDetails
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
}
