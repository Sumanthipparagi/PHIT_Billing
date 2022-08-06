package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SaleTransportationDetailsService
{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleTransportationDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SaleTransportationDetails.findAllByBillType("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SaleTransportationDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SaleTransportationDetails.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }
    SaleTransportationDetails get(String id)
    {
        return SaleTransportationDetails.findById(Long.parseLong(id))
    }


    SaleTransportationDetails getbyBillId(String billId)
    {
        return SaleTransportationDetails.findByBillId(Long.parseLong(billId))
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
                orderColumn = "lrNumber"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def salesTransportationDetailsCriteria = SaleProductDetails.createCriteria()
        def salesTransportationDetailsArrayList = salesTransportationDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('lrNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = salesTransportationDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", salesTransportationDetailsArrayList)
        return jsonObject
    }

    SaleTransportationDetails save(JSONObject jsonObject)
    {
        SaleTransportationDetails saleTransportationDetails = new SaleTransportationDetails()
        saleTransportationDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        saleTransportationDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
        saleTransportationDetails.billType = jsonObject.get("billType").toString()
        saleTransportationDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleTransportationDetails.series = Long.parseLong(jsonObject.get("series").toString())
        saleTransportationDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
        saleTransportationDetails.transporterId = Long.parseLong(jsonObject.get("transporterId").toString())
        saleTransportationDetails.lrDate = sdf.parse(jsonObject.get("lrDate").toString())
        saleTransportationDetails.lrNumber = jsonObject.get("lrNumber").toString()
        saleTransportationDetails.cartonsCount = jsonObject.get("cartonsCount").toString()
        saleTransportationDetails.paid = jsonObject.get("paid").toString()
        saleTransportationDetails.toPay = jsonObject.get("toPay").toString()
        saleTransportationDetails.generalInfo = jsonObject.get("genralInfo").toString()
        saleTransportationDetails.selfNo = jsonObject.get("selfNo").toString()
        saleTransportationDetails.ccm = jsonObject.get("ccm").toString()
        saleTransportationDetails.receivedTemperature = jsonObject.get("recievedTemprature").toString()
        saleTransportationDetails.freightCharge = jsonObject.get("freightCharge").toString()
        saleTransportationDetails.vehicleId = Long.parseLong(jsonObject.get("vechileId").toString())
        saleTransportationDetails.weight = jsonObject.get("weight").toString()
        saleTransportationDetails.deliveryStatus = jsonObject.get("deliveryStatus").toString()
        saleTransportationDetails.dispatchDateTime = jsonObject.get("dispatchDateTime").toString()
        saleTransportationDetails.deliveryDateTime = jsonObject.get("deliveryDateTime").toString()
        saleTransportationDetails.trackingDetails = jsonObject.get("trackingDetails").toString()
        saleTransportationDetails.ewaybillId = jsonObject.get("ewaybillId").toString()
        saleTransportationDetails.ewaysupplytype = jsonObject.get("ewaysupplytype").toString()
        saleTransportationDetails.ewaysupplysubtype = jsonObject.get("ewaysupplysubtype").toString()
        saleTransportationDetails.ewaydoctype = jsonObject.get("ewaydoctype").toString()
        saleTransportationDetails.consignmentNo = jsonObject.get("consignmentNo").toString()
        saleTransportationDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        saleTransportationDetails.financialYear = jsonObject.get("financialYear").toString()
        saleTransportationDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleTransportationDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        saleTransportationDetails.save(flush: true)
        if (!saleTransportationDetails.hasErrors())
        {
            return saleTransportationDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SaleTransportationDetails update(JSONObject jsonObject, String id)
    {
        SaleTransportationDetails saleTransportationDetails = SaleTransportationDetails.findById(Long.parseLong(id))
        if (SaleProductDetails)
        {
            saleTransportationDetails.isUpdatable = true
            saleTransportationDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
            saleTransportationDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
            saleTransportationDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
            saleTransportationDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            saleTransportationDetails.series = Long.parseLong(jsonObject.get("series").toString())
            saleTransportationDetails.customerId = Long.parseLong(jsonObject.get("customerId").toString())
            saleTransportationDetails.transporterId = Long.parseLong(jsonObject.get("transporterId").toString())
            saleTransportationDetails.lrDate = sdf.parse(jsonObject.get("lrDate").toString())
            saleTransportationDetails.lrNumber = jsonObject.get("sqty").toString()
            saleTransportationDetails.cartonsCount = jsonObject.get("cartonsCount").toString()
            saleTransportationDetails.paid = jsonObject.get("paid").toString()
            saleTransportationDetails.toPay = jsonObject.get("toPay").toString()
            saleTransportationDetails.generalInfo = jsonObject.get("genralInfo").toString()
            saleTransportationDetails.selfNo = jsonObject.get("selfNo").toString()
            saleTransportationDetails.ccm = jsonObject.get("ccm").toString()
            saleTransportationDetails.receivedTemperature = jsonObject.get("recievedTemprature").toString()
            saleTransportationDetails.freightCharge = jsonObject.get("freightCharge").toString()
            saleTransportationDetails.vehicleId = Long.parseLong(jsonObject.get("vechileId").toString())
            saleTransportationDetails.weight = jsonObject.get("weight").toString()
            saleTransportationDetails.deliveryStatus = jsonObject.get("deliveryStatus").toString()
            saleTransportationDetails.dispatchDateTime = jsonObject.get("dispatchDateTime").toString()
            saleTransportationDetails.deliveryDateTime = jsonObject.get("deliveryDateTime").toString()
            saleTransportationDetails.trackingDetails = jsonObject.get("trackingDetails").toString()
            saleTransportationDetails.ewaybillId = jsonObject.get("ewaybillId").toString()
            saleTransportationDetails.ewaysupplytype = jsonObject.get("ewaysupplytype").toString()
            saleTransportationDetails.ewaysupplysubtype = jsonObject.get("ewaysupplysubtype").toString()
            saleTransportationDetails.ewaydoctype = jsonObject.get("ewaydoctype").toString()
            saleTransportationDetails.consignmentNo = jsonObject.get("consignmentNo").toString()
            saleTransportationDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            saleTransportationDetails.financialYear = jsonObject.get("financialYear").toString()
            saleTransportationDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            saleTransportationDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            saleTransportationDetails.save(flush: true)
            if (!saleTransportationDetails.hasErrors())
            {
                return saleTransportationDetails
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
            SaleTransportationDetails saleTransportationDetails = SaleTransportationDetails.findById(Long.parseLong(id))
            if (saleTransportationDetails)
            {
                saleTransportationDetails.isUpdatable = true
                saleTransportationDetails.delete()
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
