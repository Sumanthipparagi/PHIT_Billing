package phitb_sales

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

import java.text.SimpleDateFormat

@Transactional
class SaleBillDetailsService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

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
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SaleBillDetails.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }


    def getAllUnsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleBillDetails.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId),Long.parseLong(entityId),financialYear,0)
    }

    def getAllsettledByCustId(String customerId, String entityId, String financialYear)
    {
        return SaleBillDetails.findAllByCustomerIdAndEntityIdAndFinancialYearAndPaymentStatus(Long.parseLong(customerId),Long.parseLong(entityId),financialYear,1)

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
//        def names = []
//        saleBillDetailsArrayList.each {
//            println
//            def apires = getEntityById(it.entityId.toString())
//            names.push(apires)
//        }
        def recordsTotal = saleBillDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
//        jsonObject.put("entity", names)
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
        saleBillDetails.creditIds = jsonObject.get("creditIds").toString()
        saleBillDetails.referralDoctor = jsonObject.get("referralDoctor").toString()
        saleBillDetails.message = jsonObject.get("message").toString()
        saleBillDetails.financialYear = jsonObject.get("financialYear").toString()
        saleBillDetails.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        saleBillDetails.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        saleBillDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        saleBillDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
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
        println(jsonObject.has(null))
        /*if (saleBillDetails)
        {
            saleBillDetails.isUpdatable = true
            saleBillDetails.finId = Long.parseLong(jsonObject.get("json[fin_id]").toString())
            saleBillDetails.serBillId = Long.parseLong(jsonObject.get("json[SeriesBillId]").toString())
            saleBillDetails.seriesId = Long.parseLong(jsonObject.get("json[Series]").toString())
            saleBillDetails.paymentStatus = Long.parseLong(jsonObject.get("json[PaymentStatus]").toString())
            saleBillDetails.accountModeId = Long.parseLong(jsonObject.get("json[AccountMode]").toString())
            saleBillDetails.priorityId = Long.parseLong(jsonObject.get("json[Priority]").toString())
            saleBillDetails.entryDate = sdf.parse(jsonObject.get("json[EntryDate]").toString())
            saleBillDetails.customerId = Long.parseLong(jsonObject.get("json[Customer]").toString())
            saleBillDetails.customerNumber = Long.parseLong(jsonObject.get("json[CustomerNumber]").toString())
            saleBillDetails.salesmanId = Long.parseLong(jsonObject.get("json[Salesman]").toString())
            saleBillDetails.salesmanComm = Long.parseLong(jsonObject.get("json[SalesmanCommision]").toString())
            saleBillDetails.orderDate = sdf.parse(jsonObject.get("json[OrderDate]").toString())
            saleBillDetails.refOrderId = Long.parseLong(jsonObject.get("json[Ref_order]").toString())
            saleBillDetails.dueDate = sdf.parse(jsonObject.get("json[Duedate]").toString())
            saleBillDetails.dispatchDate = sdf.parse(jsonObject.get("json[Dispatchdate]").toString())
            saleBillDetails.deliveryManId = Long.parseLong(jsonObject.get("json[Deliveryman]").toString())
            saleBillDetails.totalSqty = Double.parseDouble(jsonObject.get("json[Totalsqty]").toString())
            saleBillDetails.totalFqty = Double.parseDouble(jsonObject.get("json[Totalfqty]").toString())
            saleBillDetails.totalItems = Double.parseDouble(jsonObject.get("json[Total_items]").toString())
            saleBillDetails.totalQty = Double.parseDouble(jsonObject.get("json[Totalqty]").toString())
            saleBillDetails.totalDiscount = Double.parseDouble(jsonObject.get("json[TotalDiscount]").toString())
            saleBillDetails.totalAmount = Double.parseDouble(jsonObject.get("json[TotalAmount]").toString())
            saleBillDetails.invoiceTotal = Double.parseDouble(jsonObject.get("json[InvoiceTotal]").toString())
            saleBillDetails.totalGst = Double.parseDouble(jsonObject.get("json[TotalGST]").toString())
            saleBillDetails.userId = Long.parseLong(jsonObject.get("json[User]").toString())
            saleBillDetails.balance = Double.parseDouble(jsonObject.get("json[Balance]").toString())
            saleBillDetails.grossAmount = Double.parseDouble(jsonObject.get("json[GrossAmount]").toString())
            saleBillDetails.cashDiscount = Double.parseDouble(jsonObject.get("json[CashDiscount]").toString())
            saleBillDetails.exempted = Double.parseDouble(jsonObject.get("json[Exempted]").toString())
            saleBillDetails.totalCgst = Double.parseDouble(jsonObject.get("json[Totalcgst]").toString())
            saleBillDetails.totalSgst = Double.parseDouble(jsonObject.get("json[Totalsgst]").toString())
            saleBillDetails.totalIgst = Double.parseDouble(jsonObject.get("json[Totaligst]").toString())
            saleBillDetails.gstStatus = jsonObject.get("json[GSTstatus]").toString()
            saleBillDetails.billStatus = Double.parseDouble(jsonObject.get("json[Billstatus]").toString())
            saleBillDetails.lockStatus = jsonObject.get("json[Lockstatus]").toString()
            saleBillDetails.syncStatus = "1"
            saleBillDetails.creditadjAmount = Double.parseDouble(jsonObject.get("json[CreditAdjamount]").toString())
            saleBillDetails.creditIds = Double.parseDouble(jsonObject.get("json[Creditids]").toString())
            saleBillDetails.referralDoctor = Double.parseDouble(jsonObject.get("json[Referraldoctor]").toString())
            saleBillDetails.message = jsonObject.get("json[Message]").toString()
            saleBillDetails.financialYear = jsonObject.get("json[FinancialYear]").toString()
            saleBillDetails.createdUser = Long.parseLong("1")
            saleBillDetails.modifiedUser = Long.parseLong("1")
            saleBillDetails.entityTypeId = Long.parseLong("1")
            saleBillDetails.entityId = Long.parseLong("1")
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
        }*/
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

    SaleBillDetails getRecentByFinancialYearAndEntity(String financialYear, String entityId, billStatus)
    {
        return SaleBillDetails.findByFinancialYearAndEntityIdAndBillStatus(financialYear, Long.parseLong(entityId), billStatus, [sort: 'id', order: 'desc'])
    }

    def getEntityById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Constants().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Constants().ENTITY_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  getEntity  , Ex:' + ex)
            log.error('Service :EntityService , action :  getEntity  , Ex:' + ex)
        }
    }


    def getAllByCustomerId(String id)
    {
        if(id)
        {
            return SaleBillDetails.findAllByCustomerId(Long.parseLong(id))
        }
    }

}
