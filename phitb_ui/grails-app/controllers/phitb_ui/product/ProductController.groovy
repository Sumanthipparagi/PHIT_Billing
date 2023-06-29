package phitb_ui.product

import javafx.scene.control.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.TaxController
import phitb_ui.entity.UserRegisterController
import phitb_ui.facility.RackController
import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Links
import phitb_ui.ProductService

import java.nio.file.Files
import java.text.SimpleDateFormat

class ProductController {

    def index() {
        try {
            ArrayList<String> productTypes = new ProductTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> divisions = new DivisionController().getByEntity() as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().getByEntity() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().getByEntity() as ArrayList<String>
            ArrayList<String> racks = new RackController().getByEntity() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().getByEntity() as ArrayList<String>
            render(view: '/product/productRegister/productRegister', model: [productTypes     : productTypes,
                                                                             productGroups    : productGroups,
                                                                             productCategories: productCategories,
                                                                             productSchedules : productSchedules,
                                                                             racks            : racks,
                                                                             compositions     : compositions,
                                                                             divisions        : divisions, entity: entity])

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }


    def addProduct() {
        try {
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> tax = new EntityService().getTaxesByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> productTypes = new ProductTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> divisions = new ProductService().getDivisionsByEntityId(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().getByEntity() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().getByEntity() as ArrayList<String>
            ArrayList<String> racks = new RackController().getByEntity() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().getByEntity() as ArrayList<String>
            ArrayList<String> productcost = new ProductCostRangeController().getByEntity() as ArrayList<String>
            ArrayList<String> unittype = new UnitTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> manufacturerList = []
            ArrayList<String> companyList = []
            entity.each {
                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER)
                        || it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER_AND_MARKETING)) {
                    manufacturerList.add(it)
                }

                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER_AND_MARKETING)
                        || it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_C_F)
                        || it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER)) {
                    companyList.add(it)
                }
            }
            render(view: '/product/productRegister/add-product', model: [productTypes     : productTypes,
                                                                         productGroups    : productGroups,
                                                                         productCategories: productCategories,
                                                                         productSchedules : productSchedules,
                                                                         racks            : racks,
                                                                         compositions     : compositions,
                                                                         divisions        : divisions,
                                                                         entity           : entity,
                                                                         productcost      : productcost,
                                                                         unittype         : unittype,
                                                                         tax              : tax,
                                                                         manufacturerList : manufacturerList,
                                                                         companyList      : companyList
            ])

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }

    def updateProduct() {
        try {
            ArrayList<String> productTypes = new ProductTypeController().show() as ArrayList<String>
            ArrayList<String> entity = new EntityService().getByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> tax = new EntityService().getTaxesByEntity(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> productGroups = new ProductGroupController().getByEntity() as ArrayList<String>
            ArrayList<String> divisions = new ProductService().getDivisionsByEntityId(session.getAttribute("entityId").toString()) as ArrayList<String>
            ArrayList<String> productCategories = new ProductCategoryController().getByEntity() as ArrayList<String>
            ArrayList<String> productSchedules = new ProductScheduleController().getByEntity() as ArrayList<String>
            ArrayList<String> racks = new RackController().getByEntity() as ArrayList<String>
            ArrayList<String> compositions = new CompositionController().getByEntity() as ArrayList<String>
            ArrayList<String> producttype = new ProductTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> productcost = new ProductCostRangeController().getByEntity() as ArrayList<String>
            ArrayList<String> unittype = new UnitTypeController().getByEntity() as ArrayList<String>
            ArrayList<String> manufacturerList = []
            ArrayList<String> companyList = []
            entity.each {
                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_MANUFACTURER)) {
                    manufacturerList.add(it)
                }

                if (it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_COMPANY)
                        || it.entityType.name.toString().equalsIgnoreCase(Constants.ENTITY_C_F)) {
                    companyList.add(it)
                }
            }
            def product = new ProductService().getProductById(params.id)
            render(view: '/product/productRegister/update-product', model: [productTypes     : productTypes,
                                                                            productGroups    : productGroups,
                                                                            productCategories: productCategories,
                                                                            productSchedules : productSchedules,
                                                                            racks            : racks,
                                                                            compositions     : compositions,
                                                                            divisions        : divisions, entity: entity,
                                                                            // entitytype       : entitytype,
                                                                            producttype      : producttype,
                                                                            product          : product,
                                                                            productcost      : productcost,
                                                                            unittype         : unittype,
                                                                            tax              : tax,
                                                                            manufacturerList : manufacturerList,
                                                                            companyList      : companyList
            ])

        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }

    }


    def dataTable() {
        try {
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId"))
            def apiResponse = new ProductService().showProductRegister(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                JSONArray dataArray = responseObject.data
                JSONArray finalArray = new JSONArray()
                for (JSONObject product : dataArray) {
                    def tax = new TaxController().show(product.taxId.toString())
                    product.put("tax", tax)

                    JSONObject marketingCompany = new EntityService().getEntityById(product?.mktCompanyId?.toString())
                    if(marketingCompany) {
                        product.put("marketingCompany", marketingCompany)
                    }
                    finalArray.add(product)
                }
                responseObject.put("data", finalArray)

                respond responseObject, formats: ['json'], status: 200
            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def save() {
        try {
            boolean autoGenBatch = false
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId").toString())
            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            jsonObject.put("soundexCode", "NA")
            def apiResponse = new ProductService().saveProductRegister(jsonObject)
            if (apiResponse?.status == 200) {
                JSONObject savedProduct = new JSONObject(apiResponse.readEntity(String.class))
                if(jsonObject.has("autoBatch"))
                {
                    Date today = new Date()
                    Calendar cal = Calendar.getInstance()
                    cal.setTime(today)
                    cal.add(Calendar.YEAR, 100)
                    Date after100Years = cal.getTime()

                    JSONObject batchRegister = new JSONObject()
                    batchRegister.product = savedProduct.get("id")
                    batchRegister.manfDate = today.format("dd/MM/yyyy")
                    batchRegister.expiryDate = after100Years.format("dd/MM/yyyy")
                    batchRegister.purchaseRate = Double.parseDouble(jsonObject.get("purchaseRate").toString())
                    batchRegister.saleRate = Double.parseDouble(jsonObject.get("saleRate").toString())
                    batchRegister.ptr = Double.parseDouble(jsonObject.get("ptr").toString())
                    batchRegister.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
                    batchRegister.qty = 1
                    batchRegister.box = 1
                    batchRegister.caseWt = "1"
                    //auto batch number: Entity ID + Product ID + date in ddMMyyyyHHmmss format
                    batchRegister.batchNumber = savedProduct.get("entityId") +""+savedProduct.get("id")+today.format("ddMMyyyyHHmmss")
                    if(jsonObject.has("category") && jsonObject.get("category").toString()!=0)
                    {
                        batchRegister.productCat =  jsonObject.get("category").toString()
                    }
                    else
                    {
                        batchRegister.productCat = null
                    }
                    batchRegister.status = 1
                    batchRegister.syncStatus = 1
                    batchRegister.entityTypeId = session.getAttribute("entityTypeId")
                    batchRegister.entityId =  session.getAttribute("entityId")
                    batchRegister.createdUser = session.getAttribute("userId")
                    batchRegister.modifiedUser = session.getAttribute("userId")
                    def batchResponse = new ProductService().saveBatchRegister(batchRegister)
                    if(batchResponse.status == 200)
                    {
                        println("Batch generated")
                    }
                }
                redirect(uri: '/product')
            } else {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update() {
        try {
            println(params)
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("entityId", session.getAttribute("entityId").toString())
            jsonObject.put("entityTypeId", session.getAttribute("entityTypeId").toString())
            jsonObject.put("soundexCode", "NA")
            def apiResponse = new ProductService().putProductRegister(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                respond obj, formats: ['json'], status: 200
                redirect(uri: '/product')

            } else {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def delete() {
        try {
            JSONObject jsonObject = new JSONObject(params)

            //check if product is used in purchase
            boolean purchaseDelete = new PurchaseService().purchaseProductDeleteCheck(jsonObject.id)

            //check if product is used in sales
            boolean salesDelete = new SalesService().salesProductDeleteCheck(jsonObject.id)

            if(purchaseDelete && salesDelete) {
                def apiResponse = new ProductService().deleteProductRegister(jsonObject)
                if (apiResponse.status == 200) {
                    JSONObject data = new JSONObject()
                    data.put("success", "success")
                    respond data, formats: ['json'], status: 200
                } else {
                    response.status = 400
                }
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def show() {
        try {
            def apiResponse = new ProductService().getProducts()
            if (apiResponse?.status == 200) {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            } else {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def searchByName() {
        try {
            String entityId = session.getAttribute("entityId")
            String productName = params.search
            JSONArray jsonArray = new ProductService().getProductByName(productName, entityId)
            respond jsonArray, formats: ['json']
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def getProductByDivision() {
        try {
            JSONArray jsonArray = new ProductService().getProductsByDivision(params.id)
            if (jsonArray)
                respond jsonArray, formats: ['json']
            else
                response.status = 404
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def getProductBySeries() {
        try {
            String search = params.search
            String page = params.page
            JSONObject jsonObject = new ProductService().getProductsBySeries(params.id, session.getAttribute("entityId").toString(), page, search)
            if (jsonObject) {
                respond jsonObject, formats: ['json']
            }
            else
                response.status = 400
        }
        catch (Exception ex) {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def productByEntityId(){
        JSONArray productArray = new ProductService().getProductByEntity(session.getAttribute('entityId').toString())
        if(productArray!=null){
            respond productArray, formats: ['json'], status: 200;
        }

    }

    /* def getProductbyId()
     {
         try
         {
             def apiResponse = new ProductService().getProductById(params.id)
             if (apiResponse?.status == 200)
             {
                 JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                 ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                 return arrayList
             }
             else
             {
                 return []
             }
         }
         catch (Exception ex)
         {
             System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
             log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
             response.status = 400
         }
     }*/






    def productReportExport(){
        try{
            JSONArray productArray = new ProductService().getProductByEntity(session.getAttribute('entityId').toString())
            if(productArray.size()!=0){
                for(JSONObject product:productArray){
                    product.put("division",product?.division?.divisionName)
                    product.put("group",product?.group?.groupName)
                    product.put("unit",product?.unit?.unitName)
                    product.put("composition",product?.composition?.compositionName)
                    product.put("costRange",product?.costRange?.maximumRate)
                    product.put("productType",product?.productType?.productType)
                    product.put("schedule",product?.schedule?.scheduleCode)
                    product.put("category",product?.category?.categoryName)

                    try {
                        def tax = new TaxController().show(product.taxId.toString())
                        product.put("taxName", tax.taxName)
                        product.put("taxValue", tax.taxValue)
                    }
                    catch (Exception ex)
                    {
                        println("unable to get tax: "+ex.printStackTrace())
                    }
                }
                respond productArray, formats: ['json'], status: 200;
            }
        }catch(Exception ex){
            System.out.println(controllerName+' '+ex)
            log.error(controllerName+' '+ex)
        }
    }

    def saveBulkProducts(){
      try{
          JSONArray params = new JSONArray(params.productData)
          JSONArray productArray = new JSONArray()
          JSONArray unusedProductArray = new JSONArray()
          JSONObject responseObject = new JSONObject()
          def division = new ProductService().getDivisionsByEntityId(session.getAttribute('entityId').toString())
          if(division == null){
              return
          }
          for(JSONObject jsonObject:params){

              String taxValue = jsonObject.get('12')
              def taxId = new EntityService().getTaxRegisterByValueAndEntity(taxValue,session.getAttribute('entityId').toString())
              if(taxId!=null)
              {
                  jsonObject.put("taxId",taxId.id)
                  jsonObject.put("division",division[0].id)
                  jsonObject.put("productName", jsonObject.get('0'))
                  jsonObject.put("productCode", jsonObject.get('1'))
                  jsonObject.put("purchaseMarginPercent", jsonObject.get('2'))
                  jsonObject.put("vipSRate", jsonObject.get('3'))
                  jsonObject.put("saleRate", jsonObject.get('4'))
                  jsonObject.put("orderQuantity", jsonObject.get('5'))
                  jsonObject.put("mrp", jsonObject.get('6'))
                  jsonObject.put("purchaseTradeDiscount", jsonObject.get('7'))
                  jsonObject.put("saleTradeDiscount", jsonObject.get('8'))
                  jsonObject.put("restrictedRate", jsonObject.get('9'))
                  jsonObject.put("ccmProduct", jsonObject.get('10'))
                  jsonObject.put("grossProfitPercentage", jsonObject.get('11'))
                  jsonObject.put("ptr", jsonObject.get('13'))
                  jsonObject.put("narration", jsonObject.get('14'))
                  jsonObject.put("hsnCode", jsonObject.get('15'))
                  jsonObject.put("salesmenPercent", jsonObject.get('16'))
                  jsonObject.put("purchaseRate", jsonObject.get('17'))
                  jsonObject.put("discountAllowed", jsonObject.get('18'))
                  jsonObject.put("saleMarginPercent", jsonObject.get('19'))
                  jsonObject.put("vipPRate", jsonObject.get('20'))
                  jsonObject.put("restrictedAssignment", jsonObject.get('21'))
                  jsonObject.put("perLotQuantity", jsonObject.get('22'))
                  jsonObject.put("unitPacking", jsonObject.get('23'))
                  jsonObject.put("nriRate", jsonObject.get('24'))
                  jsonObject.put("salesmanCommission", jsonObject.get('25'))
                  jsonObject.put("saleType", jsonObject.get('26'))
                  jsonObject.put("mktCompanyId", session.getAttribute('userId').toString())
                  jsonObject.put("manufacturerId",session.getAttribute('userId').toString())
                  jsonObject.put("entityId", session.getAttribute('entityId'))
                  jsonObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                  jsonObject.put("userId", session.getAttribute('userId'))
                  jsonObject.put("createdUser", session.getAttribute('userId'))
                  jsonObject.put("modifiedUser", session.getAttribute('userId'))
                  productArray.add(jsonObject)
              }else{
                  unusedProductArray.add(jsonObject)
              }
          }
          JSONArray jsonArray = new ProductService().saveBulkProductRegister(productArray)
          if(jsonArray!=null){
              responseObject.put("createdProducts", jsonArray)
              responseObject.put("unusedProducts", unusedProductArray)
              respond responseObject, formats: ['json'], status: 200
          }else{
              response.status=400;
          }
      }catch(Exception ex){
          println(ex)
      }

    }


}
