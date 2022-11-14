package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_product.Exception.BadRequestException
import phitb_product.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class BatchRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return BatchRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return BatchRegister.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!entityId)
        {
            return BatchRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return BatchRegister.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByProduct(long productId) {
        Date currentDate = new Date()
        if (!productId)
            return BatchRegister.findAll()
        else
            return BatchRegister.createCriteria().list(){
                product{
                    eq('id',productId)
                }
                //ge("expiryDate", currentDate)
            }
    }

    BatchRegister get(String id) {
        return BatchRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        long entityId = paramsJsonObject.get("entityId")
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

        def batchRegisterCriteria = BatchRegister.createCriteria()
        def batchRegisterArrayList = batchRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('batchNumber', '%' + searchTerm + '%')
                    ilike('caseWt', '%' + searchTerm + '%')
                }
            }
            eq('entityId', entityId)
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = batchRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", batchRegisterArrayList)
        return jsonObject
    }

    BatchRegister save(JSONObject jsonObject) {
        ProductRegister product = ProductRegister.findById(Long.parseLong(jsonObject.get("product").toString()))
        BatchRegister batchRegister = BatchRegister.findByBatchNumberAndProduct(jsonObject.get("batchNumber").toString(), product)
        if(batchRegister == null) {
            batchRegister = new BatchRegister()

            batchRegister.product = product
            batchRegister.manfDate = sdf.parse(jsonObject.get("manfDate").toString())
            batchRegister.expiryDate = sdf.parse(jsonObject.get("expiryDate").toString())
            batchRegister.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
            batchRegister.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
            batchRegister.ptr = Double.parseDouble(jsonObject.get("ptr").toString())
            batchRegister.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            batchRegister.qty = Long.parseLong(jsonObject.get("qty").toString())
            batchRegister.box = Long.parseLong(jsonObject.get("box").toString())
            batchRegister.caseWt = jsonObject.get("caseWt").toString()
            batchRegister.batchNumber = jsonObject.get("batchNumber").toString()
            if(jsonObject.has("productCat") && jsonObject.get("productCat").toString()!=0)
            {
                batchRegister.productCat =  ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("productCat").toString()))
            }
            else
            {
                batchRegister.productCat = null
            }
//            batchRegister.productCat = ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("productCat").toString()))
            batchRegister.status = Long.parseLong(jsonObject.get("status").toString())
            batchRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            batchRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            batchRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            batchRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            batchRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            batchRegister.save(flush: true)
            if (!batchRegister.hasErrors())
                return batchRegister
            else
                throw new BadRequestException()
        }
        else
            return null
    }

    BatchRegister update(JSONObject jsonObject, String id) {
        BatchRegister batchRegister = BatchRegister.findById(Long.parseLong(id))
        if (batchRegister) {
            batchRegister.isUpdatable = true
            batchRegister.product = ProductRegister.findById(Long.parseLong(jsonObject.get("product").toString()))
            batchRegister.manfDate = sdf.parse(jsonObject.get("manfDate").toString())
            batchRegister.expiryDate = sdf.parse(jsonObject.get("expiryDate").toString())
            batchRegister.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
            batchRegister.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
            batchRegister.ptr = Double.parseDouble(jsonObject.get("ptr").toString())
            batchRegister.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            batchRegister.qty = Long.parseLong(jsonObject.get("qty").toString())
            batchRegister.box = Long.parseLong(jsonObject.get("box").toString())
            batchRegister.caseWt = jsonObject.get("caseWt").toString()
            batchRegister.batchNumber = jsonObject.get("batchNumber").toString()
            if(jsonObject.has("productCat") && jsonObject.get("productCat").toString()!=0)
            {
                batchRegister.productCat =  ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("productCat").toString()))
            }
            else
            {
                batchRegister.productCat = null
            }
//            batchRegister.productCat = ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("productCat").toString()))
            batchRegister.status =  Long.parseLong(jsonObject.get("status").toString())
            batchRegister.syncStatus =  Long.parseLong(jsonObject.get("syncStatus").toString())
            batchRegister.entityTypeId =  Long.parseLong(jsonObject.get("entityTypeId").toString())
            batchRegister.entityId =  Long.parseLong(jsonObject.get("entityId").toString())
            batchRegister.createdUser =  Long.parseLong(jsonObject.get("createdUser").toString())
            batchRegister.modifiedUser =  Long.parseLong(jsonObject.get("modifiedUser").toString())
            batchRegister.save(flush: true)
            if (!batchRegister.hasErrors())
                return batchRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            BatchRegister batchRegister = BatchRegister.findById(Long.parseLong(id))
            if (batchRegister) {
                batchRegister.isUpdatable = true
                batchRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }


    def getByBatchAndProduct(String batch, String productId)
    {
        ProductRegister product = ProductRegister.findById(Long.parseLong(productId))
        if(product)
        {
            def result = BatchRegister.findByProductAndBatchNumber(product,batch)
            return result
        }
    }


    def saveBulkBatchRegister(JSONArray jsonArray){
        JSONArray batchArray = new JSONArray()
        JSONArray sameBatches = new JSONArray()
        for(JSONObject jsonObject: jsonArray){
            ProductRegister product = ProductRegister.findById(Long.parseLong(jsonObject.get("product").toString()))
            BatchRegister batchRegister = BatchRegister.findByBatchNumberAndProduct(jsonObject.get("batchNumber").toString(), product)
            if(batchRegister == null) {
                batchRegister = new BatchRegister()
                batchRegister.product = product
                batchRegister.manfDate = sdf.parse(jsonObject.get("manfDate").toString())
                batchRegister.expiryDate = sdf.parse(jsonObject.get("expiryDate").toString())
                batchRegister.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
                batchRegister.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
                batchRegister.ptr = Double.parseDouble(jsonObject.get("ptr").toString())
                batchRegister.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
                batchRegister.qty = Long.parseLong(jsonObject.get("qty").toString())
                batchRegister.box = Long.parseLong(jsonObject.get("box").toString())
                batchRegister.caseWt = jsonObject.get("caseWt").toString()
                batchRegister.batchNumber = jsonObject.get("batchNumber").toString()
                if(jsonObject.has("productCat") && jsonObject.get("productCat").toString()!=0)
                {
                    batchRegister.productCat =  ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("productCat").toString()))
                }
                else
                {
                    batchRegister.productCat = null
                }
//            batchRegister.productCat = ProductCategoryMaster.findById(Long.parseLong(jsonObject.get("productCat").toString()))
                batchRegister.status = Long.parseLong(jsonObject.get("status").toString())
                batchRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
                batchRegister.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                batchRegister.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                batchRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                batchRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                batchRegister.save(flush: true)
                batchArray.add(jsonObject)

            }else{
                sameBatches.add(jsonObject)
            }
        }
        JSONObject responseObject = new JSONObject()
        responseObject.put("sameBatches",sameBatches)
        responseObject.put("batches",batchArray)
        return  responseObject
    }
}
