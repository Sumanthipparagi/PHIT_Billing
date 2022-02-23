package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ProductRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return ProductRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductRegister.findAllByProductCode("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return ProductRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductRegister.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])

    }

    def getAllByDivision(long limit, long offset, long divisionId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!divisionId)
            return ProductRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ProductRegister.createCriteria().list(max: l,offset:o){
                division{
                    eq('id',divisionId)
                }
            }
    }

    ProductRegister get(String id) {
        return ProductRegister.findById(Long.parseLong(id))
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
                orderColumn = "batchNumber"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def productRegisterCriteria = ProductRegister.createCriteria()
        def productRegisterArrayList = productRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('productName', '%' + searchTerm + '%')
                    ilike('productCode', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = productRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", productRegisterArrayList)
        return jsonObject
    }

    ProductRegister save(JSONObject jsonObject) {
        ProductRegister productRegister = new ProductRegister()
        productRegister.productCode = jsonObject.get("productCode").toString()
        productRegister.productName = jsonObject.get("productName").toString()
        productRegister.manufacturerId = Long.parseLong(jsonObject.get("manufacturerId").toString())
        productRegister.mktCompanyId = Long.parseLong(jsonObject.get("mktCompanyId").toString())
        productRegister.hsnCode = jsonObject.get("hsnCode").toString()
        productRegister.rackId = Long.parseLong(jsonObject.get("rackId").toString())
        productRegister.division = Division.findById(Long.parseLong(jsonObject.get("division").toString()))
        productRegister.composition = CompositionMaster.findById(Long.parseLong(jsonObject.get("composition").toString()))
        productRegister.costRange = ProductCostRange.findById(Long.parseLong(jsonObject.get("costRange").toString()))
        productRegister.productType = ProductTypeMaster.findById(Long.parseLong(jsonObject.get("productType").toString()))
        productRegister.unit = UnitTypeMaster.findById(Long.parseLong(jsonObject.get("unit").toString()))
        productRegister.unitPacking = jsonObject.get("unitPacking").toString()
        productRegister.productMoo = Long.parseLong(jsonObject.get("productMoo").toString())
        productRegister.perLotQuantity = jsonObject.get("perLotQuantity").toString()
        productRegister.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
        productRegister.purchaseTradeDiscount = Double.parseDouble(jsonObject.get("purchaseTradeDiscount").toString())
        productRegister.purchaseMarginPercent = Double.parseDouble(jsonObject.get("purchaseMarginPercent").toString())
        productRegister.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
        productRegister.saleTradeDiscount = Double.parseDouble(jsonObject.get("saleTradeDiscount").toString())
        productRegister.saleMarginPercent = Double.parseDouble(jsonObject.get("saleMarginPercent").toString())
        productRegister.salesmenPercent = Double.parseDouble(jsonObject.get("salesmenPercent").toString())
        productRegister.vipPRate = Double.parseDouble(jsonObject.get("vipPRate").toString())
        productRegister.vipSRate = Double.parseDouble(jsonObject.get("vipSRate").toString())
        productRegister.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        productRegister.ptr = Double.parseDouble(jsonObject.get("ptr").toString())
        productRegister.restrictedRate = Double.parseDouble(jsonObject.get("restrictedRate").toString())
        productRegister.nriRate = Double.parseDouble(jsonObject.get("nriRate").toString())
        productRegister.salesmanCommission = Double.parseDouble(jsonObject.get("salesmanCommission").toString())
        productRegister.grossProfitPercentage = Double.parseDouble(jsonObject.get("grossProfitPercentage").toString())
        productRegister.taxId = Long.parseLong(jsonObject.get("taxId").toString())
        productRegister.thresholdLevel = jsonObject.get("thresholdLevel").toString()
        productRegister.orderQuantity = Long.parseLong(jsonObject.get("orderQuantity").toString())
        productRegister.group =  ProductGroupMaster.findById(Long.parseLong(jsonObject.get("group").toString()))
        productRegister.schedule =  ProductScheduleMaster.findById(Long.parseLong(jsonObject.get("schedule").toString()))
        productRegister.category =  ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("category").toString()))
        productRegister.sendMail = jsonObject.get("sendMail").toString()
        productRegister.discountAllowed = jsonObject.get("discountAllowed").toString()
        productRegister.ccmProduct = jsonObject.get("ccmProduct").toString()
        productRegister.narration = jsonObject.get("narration").toString()
        productRegister.restrictedAssignment = jsonObject.get("restrictedAssignment").toString()
        productRegister.soundexCode = jsonObject.get("soundexCode").toString()
        productRegister.status =  Long.parseLong(jsonObject.get("status").toString())
        productRegister.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
        productRegister.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
        productRegister.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
        productRegister.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
        productRegister.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
        productRegister.save(flush: true)
        if (!productRegister.hasErrors())
            return productRegister
        else
            throw new BadRequestException()
    }

    ProductRegister update(JSONObject jsonObject, String id) {
        ProductRegister productRegister = ProductRegister.findById(Long.parseLong(id))
        if (productRegister) {
            productRegister.isUpdatable = true
            productRegister.productCode = jsonObject.get("productCode").toString()
            productRegister.productName = jsonObject.get("productName").toString()
            productRegister.manufacturerId = Long.parseLong(jsonObject.get("productName").toString())
            productRegister.mktCompanyId = Long.parseLong(jsonObject.get("mktCompanyId").toString())
            productRegister.hsnCode = jsonObject.get("hsnCode").toString()
            productRegister.rackId = Long.parseLong(jsonObject.get("rackId").toString())
            productRegister.division = Division.findById(Long.parseLong(jsonObject.get("division").toString()))
            productRegister.composition = CompositionMaster.findById(Long.parseLong(jsonObject.get("composition").toString()))
            productRegister.costRange = ProductCostRange.findById(Long.parseLong(jsonObject.get("costRange").toString()))
            productRegister.productType = ProductTypeMaster.findById(Long.parseLong(jsonObject.get("productType").toString()))
            productRegister.unit = UnitTypeMaster.findById(Long.parseLong(jsonObject.get("unit").toString()))
            productRegister.unitPacking = jsonObject.get("unitPacking").toString()
            productRegister.productMoo = Long.parseLong(jsonObject.get("productMoo").toString())
            productRegister.perLotQuantity = jsonObject.get("perLotQuantity").toString()
            productRegister.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
            productRegister.purchaseTradeDiscount = Double.parseDouble(jsonObject.get("purchaseTradeDiscount").toString())
            productRegister.purchaseMarginPercent = Double.parseDouble(jsonObject.get("purchaseMarginPercent").toString())
            productRegister.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
            productRegister.saleTradeDiscount = Double.parseDouble(jsonObject.get("saleTradeDiscount").toString())
            productRegister.saleMarginPercent = Double.parseDouble(jsonObject.get("saleMarginPercent").toString())
            productRegister.salesmenPercent = Double.parseDouble(jsonObject.get("salesmenPercent").toString())
            productRegister.vipPRate = Double.parseDouble(jsonObject.get("vipPRate").toString())
            productRegister.vipSRate = Double.parseDouble(jsonObject.get("vipSRate").toString())
            productRegister.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            productRegister.ptr = Double.parseDouble(jsonObject.get("ptr").toString())
            productRegister.restrictedRate = Double.parseDouble(jsonObject.get("restrictedRate").toString())
            productRegister.nriRate = Double.parseDouble(jsonObject.get("nriRate").toString())
            productRegister.salesmanCommission = Double.parseDouble(jsonObject.get("salesmanCommission").toString())
            productRegister.grossProfitPercentage = Double.parseDouble(jsonObject.get("grossProfitPercentage").toString())
            productRegister.taxId = Long.parseLong(jsonObject.get("taxId").toString())
            productRegister.thresholdLevel = jsonObject.get("thresholdLevel").toString()
            productRegister.orderQuantity = Long.parseLong(jsonObject.get("orderQuantity").toString())
            productRegister.group =  ProductGroupMaster.findById(Long.parseLong(jsonObject.get("group").toString()))
            productRegister.schedule =  ProductScheduleMaster.findById(Long.parseLong(jsonObject.get("schedule").toString()))
            productRegister.category =  ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("category").toString()))
            productRegister.sendMail = jsonObject.get("sendMail").toString()
            productRegister.discountAllowed = jsonObject.get("discountAllowed").toString()
            productRegister.ccmProduct = jsonObject.get("ccmProduct").toString()
            productRegister.narration = jsonObject.get("narration").toString()
            productRegister.restrictedAssignment = jsonObject.get("restrictedAssignment").toString()
            productRegister.soundexCode = jsonObject.get("soundexCode").toString()
            productRegister.status =  Long.parseLong(jsonObject.get("status").toString())
            productRegister.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            productRegister.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            productRegister.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            productRegister.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            productRegister.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            productRegister.save(flush: true)
            if (!productRegister.hasErrors())
                return productRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ProductRegister productRegister = ProductRegister.findById(Long.parseLong(id))
            if (productRegister) {
                productRegister.isUpdatable = true
                productRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
