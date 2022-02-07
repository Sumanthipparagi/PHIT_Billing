package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class CustomerGroupRegisterService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return CustomerGroupRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return CustomerGroupRegister.findAllByCustomerGroupName("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return CustomerGroupRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return CustomerGroupRegister.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    CustomerGroupRegister get(String id)
    {
        return CustomerGroupRegister.findById(Long.parseLong(id))
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
                orderColumn = "customerGroupName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def customerGroupRegisterCriteria = CustomerGroupRegister.createCriteria()
        def customerGroupRegisterArrayList = customerGroupRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('customerGroupName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = customerGroupRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", customerGroupRegisterArrayList)
        return jsonObject
    }

    CustomerGroupRegister save(JSONObject jsonObject)
    {
        CustomerGroupRegister customerGroupRegister = new CustomerGroupRegister()
        customerGroupRegister.customerGroupName = jsonObject.get("customerGroupName").toString()
        customerGroupRegister.shortName = jsonObject.get("shortName").toString()
        customerGroupRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        customerGroupRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        customerGroupRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        customerGroupRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        customerGroupRegister.save(flush: true)
        if (!customerGroupRegister.hasErrors())
        {
            return customerGroupRegister
        }
        else
        {
            throw new BadRequestException()
        }
    }

    CustomerGroupRegister update(JSONObject jsonObject, String id)
    {
        CustomerGroupRegister customerGroupRegister = CustomerGroupRegister.findById(Long.parseLong(id))
        if (customerGroupRegister)
        {
            customerGroupRegister.isUpdatable = true
            customerGroupRegister.customerGroupName = jsonObject.get("customerGroupName").toString()
            customerGroupRegister.shortName = jsonObject.get("shortName").toString()
            customerGroupRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            customerGroupRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            customerGroupRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            customerGroupRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            customerGroupRegister.save(flush: true)
            if (!customerGroupRegister.hasErrors())
            {
                return customerGroupRegister
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
            CustomerGroupRegister customerGroupRegister = CustomerGroupRegister.findById(Long.parseLong(id))
            if (customerGroupRegister)
            {
                customerGroupRegister.isUpdatable = true
                customerGroupRegister.delete()
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
