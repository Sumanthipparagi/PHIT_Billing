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
import java.text.SimpleDateFormat

@Transactional
class SchemeConfigurationService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SchemeConfiguration.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SchemeConfiguration.findAllByZoneIdsIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SchemeConfiguration.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SchemeConfiguration.createCriteria().list {
                gt("dateCreated",dateCreated)
            }
        }
    }
    SchemeConfiguration get(String id)
    {
        return SchemeConfiguration.findById(Long.parseLong(id))
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
                orderColumn = "zoneIds"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def schemeConfigurationCriteria = SchemeConfiguration.createCriteria()
        def schemeConfigurationArrayList = schemeConfigurationCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('zoneIds', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def products = []
        schemeConfigurationArrayList.each {
            def apires = getProductById(it.productId.toString())
            products.push(apires)
        }
        def recordsTotal = schemeConfigurationArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("products", products)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", schemeConfigurationArrayList)
        return jsonObject
    }

    SchemeConfiguration save(JSONObject jsonObject)
    {
        SchemeConfiguration schemeConfiguration = new SchemeConfiguration()
        schemeConfiguration.zoneIds = jsonObject.get("zoneIds").toString()
        schemeConfiguration.stateIds = jsonObject.get("stateIds").toString()
        schemeConfiguration.cityIds = jsonObject.get("cityIds").toString()
        schemeConfiguration.customerIds = jsonObject.get("customerIds").toString()
        schemeConfiguration.distributorId = jsonObject.get("distributorId").toString()
        schemeConfiguration.productId = Long.parseLong(jsonObject.get("productId").toString())
        schemeConfiguration.batch = jsonObject.get("batch").toString()
        schemeConfiguration.slab1MinQty = Long.parseLong(jsonObject.get("slab1MinQty").toString())
        schemeConfiguration.slab1SchemeQty = Long.parseLong(jsonObject.get("slab1SchemeQty").toString())
        schemeConfiguration.slab1BulkStatus = Long.parseLong(jsonObject.get("slab1BulkStatus").toString())
        schemeConfiguration.slab1Status = Long.parseLong(jsonObject.get("slab1Status").toString())
        schemeConfiguration.slab2MinQty = Long.parseLong(jsonObject.get("slab2MinQty").toString())
        schemeConfiguration.slab2SchemeQty = Long.parseLong(jsonObject.get("slab2SchemeQty").toString())
        schemeConfiguration.slab2BulkStatus = Long.parseLong(jsonObject.get("slab2BulkStatus").toString())
        schemeConfiguration.slab2Status = Long.parseLong(jsonObject.get("slab2Status").toString())
        schemeConfiguration.slab3MinQty = Long.parseLong(jsonObject.get("slab3MinQty").toString())
        schemeConfiguration.slab3SchemeQty = Long.parseLong(jsonObject.get("slab3SchemeQty").toString())
        schemeConfiguration.slab3BulkStatus = Long.parseLong(jsonObject.get("slab3BulkStatus").toString())
        schemeConfiguration.slab3Status = Long.parseLong(jsonObject.get("slab3Status").toString())
        schemeConfiguration.slabValidityFrom = sdf.parse(jsonObject.get("slabValidityFrom").toString())
        schemeConfiguration.slabValidityTo = sdf.parse(jsonObject.get("slabValidityTo").toString())
        schemeConfiguration.specialDiscount = Double.parseDouble(jsonObject.get("specialDiscount").toString())
        schemeConfiguration.specialDiscountValidTo =  sdf.parse(jsonObject.get("specialDiscountValidTo").toString())
        schemeConfiguration.specialDiscountValidFrom =  sdf.parse(jsonObject.get("specialDiscountValidFrom").toString())
        schemeConfiguration.specialRate = Long.parseLong(jsonObject.get("specialRate").toString())
        schemeConfiguration.specialRateValidFrom = sdf.parse(jsonObject.get("specialRateValidFrom").toString())
        schemeConfiguration.specialRateValidTo = sdf.parse(jsonObject.get("specialRateValidTo").toString())
        schemeConfiguration.schemeStatus = jsonObject.get("schemeStatus").toString()
        schemeConfiguration.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        schemeConfiguration.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        schemeConfiguration.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        schemeConfiguration.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        schemeConfiguration.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        schemeConfiguration.save(flush: true)
        if (!schemeConfiguration.hasErrors())
        {
            return schemeConfiguration
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SchemeConfiguration update(JSONObject jsonObject, String id)
    {
        SchemeConfiguration schemeConfiguration = SchemeConfiguration.findById(Long.parseLong(id))
        if (schemeConfiguration)
        {
            schemeConfiguration.isUpdatable = true
            schemeConfiguration.zoneIds = jsonObject.get("zoneIds").toString()
            schemeConfiguration.stateIds = jsonObject.get("stateIds").toString()
            schemeConfiguration.cityIds = jsonObject.get("cityIds").toString()
            schemeConfiguration.customerIds = jsonObject.get("customerIds").toString()
            schemeConfiguration.distributorId = jsonObject.get("distributorId").toString()
            schemeConfiguration.productId = Long.parseLong(jsonObject.get("productId").toString())
            schemeConfiguration.batch = jsonObject.get("batch").toString()
            schemeConfiguration.slab1MinQty = Long.parseLong(jsonObject.get("slab1MinQty").toString())
            schemeConfiguration.slab1SchemeQty = Long.parseLong(jsonObject.get("slab1SchemeQty").toString())
            schemeConfiguration.slab1BulkStatus = Long.parseLong(jsonObject.get("slab1BulkStatus").toString())
            schemeConfiguration.slab1Status = Long.parseLong(jsonObject.get("slab1Status").toString())
            schemeConfiguration.slab2MinQty = Long.parseLong(jsonObject.get("slab2MinQty").toString())
            schemeConfiguration.slab2SchemeQty = Long.parseLong(jsonObject.get("slab2SchemeQty").toString())
            schemeConfiguration.slab2BulkStatus = Long.parseLong(jsonObject.get("slab2BulkStatus").toString())
            schemeConfiguration.slab2Status = Long.parseLong(jsonObject.get("slab2Status").toString())
            schemeConfiguration.slab3MinQty = Long.parseLong(jsonObject.get("slab3MinQty").toString())
            schemeConfiguration.slab3SchemeQty = Long.parseLong(jsonObject.get("slab3SchemeQty").toString())
            schemeConfiguration.slab3BulkStatus = Long.parseLong(jsonObject.get("slab3BulkStatus").toString())
            schemeConfiguration.slab3Status = Long.parseLong(jsonObject.get("slab3Status").toString())
            schemeConfiguration.slabValidityFrom = sdf.parse(jsonObject.get("slabValidityFrom").toString())
            schemeConfiguration.slabValidityTo = sdf.parse(jsonObject.get("slabValidityTo").toString())
            schemeConfiguration.specialDiscount = Double.parseDouble(jsonObject.get("specialDiscount").toString())
            schemeConfiguration.specialDiscountValidTo =  sdf.parse(jsonObject.get("specialDiscountValidTo").toString())
            schemeConfiguration.specialDiscountValidFrom =  sdf.parse(jsonObject.get("specialDiscountValidFrom").toString())
            schemeConfiguration.specialRate = Double.parseDouble(jsonObject.get("specialRate").toString())
            schemeConfiguration.specialRateValidFrom = sdf.parse(jsonObject.get("specialRateValidFrom").toString())
            schemeConfiguration.specialRateValidTo = sdf.parse(jsonObject.get("specialRateValidTo").toString())
            schemeConfiguration.schemeStatus = jsonObject.get("schemeStatus").toString()
            schemeConfiguration.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            schemeConfiguration.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            schemeConfiguration.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            schemeConfiguration.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            schemeConfiguration.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            schemeConfiguration.save(flush: true)
            if (!schemeConfiguration.hasErrors())
            {
                return schemeConfiguration
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
            SchemeConfiguration schemeConfiguration = SchemeConfiguration.findById(Long.parseLong(id))
            if (schemeConfiguration)
            {
                schemeConfiguration.isUpdatable = true
                schemeConfiguration.delete()
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

    def getByProductBatchNumber(String productId, String batchNumber)
    {
       return SchemeConfiguration.findByProductIdAndBatch(Long.parseLong(productId), batchNumber)
    }


    def getProductById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Constants().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Constants().PRODUCT_REGISTER_SHOW + "/"+id)
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
            System.err.println('Service :SchemeConfiguration , action :  getEntity  , Ex:' + ex)
            log.error('Service :SchemeConfiguration , action :  getEntity  , Ex:' + ex)
        }
    }

}
