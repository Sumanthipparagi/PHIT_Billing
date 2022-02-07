package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class TaxRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return TaxRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return TaxRegister.findAllByTaxName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
        {
            return TaxRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return TaxRegister.createCriteria().list(max: l,offset:o){
                entity{
                    eq('id',entityId)
                }
            }
        }
    }


    TaxRegister get(String id) {
        return TaxRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "taxName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def taxRegsiterCriteria = TaxRegister.createCriteria()
        def taxRegsiterArrayList = taxRegsiterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('taxName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = taxRegsiterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", taxRegsiterArrayList)
        return jsonObject
    }

    TaxRegister save(JSONObject jsonObject) {
        TaxRegister taxRegister = new TaxRegister()
        taxRegister.taxName = jsonObject.get("taxName").toString()
        taxRegister.taxValue = Double.parseDouble(jsonObject.get("taxValue").toString())
        taxRegister.salesTaxType = jsonObject.get("salesTaxType").toString()
        taxRegister.salesSgst = Double.parseDouble(jsonObject.get("salesSgst").toString())
        taxRegister.salesCgst = Double.parseDouble(jsonObject.get("salesCgst").toString())
        taxRegister.purchaseTaxType = jsonObject.get("purchaseTaxType").toString()
        taxRegister.purchaseSgst = Double.parseDouble(jsonObject.get("purchaseSgst").toString())
        taxRegister.purchaseCgst = Double.parseDouble(jsonObject.get("purchaseCgst").toString())
        taxRegister.salesIgst = Double.parseDouble(jsonObject.get("salesIgst").toString())
        taxRegister.purchaseIgst = Double.parseDouble(jsonObject.get("purchaseIgst").toString())
        taxRegister.gstOnMrpSales = jsonObject.get("gstOnMrpSales").toString()
        taxRegister.gstOnSchemeValueSales = jsonObject.get("gstOnSchemeValueSales").toString()
        taxRegister.gstOnMrpPur = jsonObject.get("gstOnMrpPur").toString()
        taxRegister.gstOnSchemeValuePur = jsonObject.get("gstOnSchemeValuePur").toString()
        taxRegister.gstDiscountSales = jsonObject.get("gstDiscountSales").toString()
        taxRegister.gstDiscountPur = jsonObject.get("gstDiscountPur").toString()
        taxRegister.saleStatus = jsonObject.get("saleStatus").toString()
        taxRegister.purStatus = jsonObject.get("purStatus").toString()
        taxRegister.taxDescription = jsonObject.get("taxDescription").toString()
        taxRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        taxRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
        taxRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        taxRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
        taxRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
        taxRegister.save(flush: true)
        if (!taxRegister.hasErrors())
            return taxRegister
        else
            throw new BadRequestException()
    }

    TaxRegister update(JSONObject jsonObject, String id) {
        TaxRegister taxRegister = TaxRegister.findById(Long.parseLong(id))
        if (taxRegister) {
            taxRegister.isUpdatable = true
            taxRegister.taxName = jsonObject.get("taxName").toString()
            taxRegister.taxValue = Double.parseDouble(jsonObject.get("taxValue").toString())
            taxRegister.salesTaxType = jsonObject.get("salesTaxType").toString()
            taxRegister.salesSgst = Double.parseDouble(jsonObject.get("salesSgst").toString())
            taxRegister.salesCgst = Double.parseDouble(jsonObject.get("salesCgst").toString())
            taxRegister.purchaseTaxType = jsonObject.get("purchaseTaxType").toString()
            taxRegister.purchaseSgst = Double.parseDouble(jsonObject.get("purchaseSgst").toString())
            taxRegister.purchaseCgst = Double.parseDouble(jsonObject.get("purchaseCgst").toString())
            taxRegister.salesIgst = Double.parseDouble(jsonObject.get("salesIgst").toString())
            taxRegister.purchaseIgst = Double.parseDouble(jsonObject.get("purchaseIgst").toString())
            taxRegister.gstOnMrpSales = jsonObject.get("gstOnMrpSales").toString()
            taxRegister.gstOnSchemeValueSales = jsonObject.get("gstOnSchemeValueSales").toString()
            taxRegister.gstOnMrpPur = jsonObject.get("gstOnMrpPur").toString()
            taxRegister.gstOnSchemeValuePur = jsonObject.get("gstOnSchemeValuePur").toString()
            taxRegister.gstDiscountSales = jsonObject.get("gstDiscountSales").toString()
            taxRegister.gstDiscountPur = jsonObject.get("gstDiscountPur").toString()
            taxRegister.saleStatus = jsonObject.get("saleStatus").toString()
            taxRegister.purStatus = jsonObject.get("purStatus").toString()
            taxRegister.taxDescription = jsonObject.get("taxDescription").toString()
            taxRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            taxRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            taxRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            taxRegister.createdUser = UserRegister.findById(Long.parseLong(jsonObject.get("createdUser").toString()))
            taxRegister.modifiedUser = UserRegister.findById(Long.parseLong(jsonObject.get("modifiedUser").toString()))
            taxRegister.save(flush: true)
            if (!taxRegister.hasErrors())
                return taxRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            TaxRegister taxRegister = TaxRegister.findById(Long.parseLong(id))
            if (taxRegister) {
                taxRegister.isUpdatable = true
                taxRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
