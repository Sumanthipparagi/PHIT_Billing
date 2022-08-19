package phitb_ui.sales

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.InventoryService
import phitb_ui.ProductService
import phitb_ui.SalesService
import phitb_ui.SystemService
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.SeriesController
import phitb_ui.entity.TaxController
import javax.ws.rs.core.Response
import java.text.DateFormat
import java.text.SimpleDateFormat

class StockAdjustmentDetailsController {

    def stockAdjustment() {
        String entityId = session.getAttribute("entityId")?.toString()
        String userId = session.getAttribute("userId")?.toString()
        JSONArray divisions = new ProductService().getDivisionsByEntityId(entityId)
        ArrayList<String> customers = new EntityRegisterController().getByAffiliateById(entityId) as ArrayList<String>
        def priorityList = new SystemService().getPriorityByEntity(entityId)
        def series = new SeriesController().getByEntity(entityId)
        def taxRegister = new TaxController().show() as ArrayList<String>
        ArrayList<String> salesmanList = []
        /*users.each {
            if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN)) {
                salesmanList.add(it)
            }
        }*/
        render(view: '/sales/stockAdjustment/stockAdjustment', model: [customers   : customers, divisions: divisions, series: series,
                                                                       salesmanList: salesmanList, priorityList: priorityList,
                                                                       taxRegister:taxRegister])
    }

    def saveStockAdjustmentDetails(){
        try
        {
            JSONArray stockData = new JSONArray(params.stockData)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            JSONArray stockArray = new JSONArray();
            String series = params.series
            String priority = params.priority
            UUID uuid
            for(JSONObject stock: stockData )
            {
                uuid = UUID.randomUUID()
                String productId = stock.get("1")
                String batchNumber = stock.get("2")
                String expDate = stock.get("3")
                long saleQty = stock.get("4")
                long freeQty = stock.get("5")
                String saleRate = stock.get("6")
                String mrp = stock.get("7")
                String purRate = stock.get("8")
                String gst = stock.get("9")
                String pack = stock.get("10")
                String manfDate = stock.get("11")
                String taxId = stock.get("12")
                long originalSQty = 0
                long originalFQty = 0
                if(stock.has('13')){
                    originalSQty = Long.parseLong(stock.get('13').toString())
                }
                if(stock.has('13')){
                    originalFQty = Long.parseLong(stock.get('14').toString())
                }
                JSONObject jsonObject = new JSONObject()
                jsonObject.put("productId", productId)
                jsonObject.put("batchNumber", batchNumber)
                jsonObject.put("expDate",  expDate)
                jsonObject.put("saleQty", saleQty)
                jsonObject.put("freeQty", freeQty)
                jsonObject.put("manfDate", manfDate)
                jsonObject.put("saleRate", saleRate)
                jsonObject.put("mrp",mrp)
                jsonObject.put("purRate",purRate)
                jsonObject.put("gst",gst)
                jsonObject.put("pack",pack)
                jsonObject.put("taxId",taxId)
                jsonObject.put("series",series)
                jsonObject.put("priority",priority)
                jsonObject.put("originalSqty", originalSQty)
                jsonObject.put("originalFQty", originalFQty)
                jsonObject.put("entityId", session.getAttribute('entityId'))
                jsonObject.put("entityTypeId", session.getAttribute('entityTypeId'))
                jsonObject.put("createdUser", session.getAttribute("userId"))
                jsonObject.put("modifiedUser", session.getAttribute("userId"))
                jsonObject.put("uuid",uuid)
                stockArray.add(jsonObject)
                Response stockAdjResponse = new SalesService().saveStockAdjustmentDetails(jsonObject)
                if(stockAdjResponse?.status == 200){
                    println("Stock Adjustment Success!!")
                }
                def stockBook = new InventoryService().getStocksOfProductAndBatch(productId, batchNumber.trim(), session
                        .getAttribute('entityId').toString())
                if(stockBook!=null){
                    double remainingQty
                    double remainingFreeQty
                    if(Math.signum(Double.parseDouble(stockBook.get("remainingQty").toString())+saleQty.toDouble()) == 1){
                        remainingQty = stockBook.get("remainingQty") + saleQty
                    }else {
                        remainingQty = 0;
                    }
                    if(Math.signum(Double.parseDouble(stockBook.get("remainingFreeQty").toString())+freeQty.toDouble()) == 1){
                        remainingFreeQty = stockBook.get("remainingFreeQty") + freeQty
                    }else {
                        remainingFreeQty = 0;
                    }
                    stockBook.put("productId", productId)
                    stockBook.put("batchNumber", batchNumber)
                    String expStr = expDate
                    Date date = sdf.parse(expStr);
                    sdf = new SimpleDateFormat("dd-MM-yyyy");
                    expStr = sdf.format(date);
                    stockBook.put("expDate", expStr)
                    stockBook.put("purchaseRate", purRate)
                    stockBook.put("saleRate", saleRate)
                    stockBook.put("mrp", mrp)
                    stockBook.put("purcTradeDiscount", "0")
                    stockBook.put("supplierId", 0)
                    stockBook.put("packingDesc", pack)
                    stockBook.put("remainingQty", remainingQty.toLong())
                    stockBook.put("remainingFreeQty", remainingFreeQty.toLong())
                    stockBook.put("remainingReplQty", 0)
                    stockBook.put("entityTypeId", session.getAttribute("entityTypeId"))
                    stockBook.put("entityId", session.getAttribute("entityId"))
                    stockBook.put("createdUser", session.getAttribute("userId"))
                    stockBook.put("modifiedUser", session.getAttribute("userId"))
                    stockBook.put("openingStockQty", remainingQty.toLong()+remainingFreeQty.toLong())
                    stockBook.put("taxId", taxId)
                    Response stockResponse = new InventoryService().updateStockBook(stockBook)
                    if(stockResponse?.status == 200)
                    {
                        println(stockResponse)
                        println("Stock Update Success!!")
                    }else{
                        response.status =400
                    }
                }
                else {
                    JSONObject stockObject = new JSONObject();
                    stockObject.put("productId", productId)
                    stockObject.put("batchNumber", batchNumber)
                    String expStr = expDate
                    Date date = sdf.parse(expStr);
                    sdf = new SimpleDateFormat("dd-MM-yyyy");
                    expStr = sdf.format(date);
                    stockObject.put("expDate", expStr)
                    stockObject.put("purchaseRate", purRate)
                    stockObject.put("saleRate", saleRate)
                    stockObject.put("mrp", mrp)
                    stockObject.put("purcTradeDiscount", "0")
                    stockObject.put("purcSeriesId", 0)
                    stockObject.put("purcDate", sdf.format(new Date()))
                    stockObject.put("supplierId", 0)
                    stockObject.put("manufacturingDate", manfDate)
                    stockObject.put("series", series)
                    stockObject.put("packingDesc", pack)
                    stockObject.put("purcProductValue", 0)
                    stockObject.put("remainingQty", saleQty)
                    stockObject.put("remainingFreeQty", freeQty)
                    stockObject.put("remainingReplQty", 0)
                    stockObject.put("status", "1")
                    stockObject.put("syncStatus", "1")
                    stockObject.put("mergedWith", "0")
                    stockObject.put("entityTypeId", session.getAttribute("entityTypeId"))
                    stockObject.put("entityId", session.getAttribute("entityId"))
                    stockObject.put("createdUser", session.getAttribute("userId"))
                    stockObject.put("modifiedUser", session.getAttribute("userId"))
                    stockObject.put("openingStockQty", 0)
                    stockObject.put("taxId",taxId)
                    stockObject.put("uuid", uuid)
                    Response saveStockResponse = new InventoryService().stockBookSave(stockObject)
                    if(saveStockResponse?.status == 200){
                        println(saveStockResponse)
                        println("New Stock has been Created!!")
                    }else {
                        response.status=400
                    }
                }
            }
            if(stockArray.size()!=0){
               respond stockArray,formats: ['json'], status: 200;
            }
            else {
                response.status = 400
            }

        }catch(Exception e){
            println(controllerName+""+e)
            log.info(controllerName+""+e)
        }
    }

    def dataTable() {
        try {
            String userId = session.getAttribute("userId")
            String fromDate, toDate
            JSONObject jsonObject = new JSONObject(params)
            jsonObject.put("userId", userId)
            jsonObject.put("entityId",session.getAttribute('entityId'))
            jsonObject.put("entityTypeId",session.getAttribute('entityTypeId'))
            if ((params.daterange != null) && (params.daterange != ""))
            {
                System.out.println("date=" + params.daterange.toString())
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
                fromDate = params.daterange.split("-")[0]
                System.out.println("fromdate=" + fromDate.trim())
                jsonObject.put("fromDate",fromDate.trim())
                toDate = params.daterange.split("-")[1]
                jsonObject.put("toDate",toDate.trim())
                System.out.println("toDate=" + toDate.trim())
            }
            else {
                jsonObject.put("fromDate","")
                jsonObject.put("toDate","")
            }
            def apiResponse = new SalesService().showStockAdjustment(jsonObject)
            if (apiResponse.status == 200) {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                if(responseObject)
                {
                    JSONArray jsonArray = responseObject.data
                    for (JSONObject json : jsonArray) {
                        JSONObject products = new ProductService().getProductById(json.get("productId").toString())
                        if(products!=null)
                        {
                            json.put("product", products)
                        }
                        else {
                            products?.put("product", "")
                        }
                    }
                    responseObject.put("data", jsonArray)
                }
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

    def stockAdjustmentList(){
        render(view: '/sales/stockAdjustment/stockAdjustmentList')
    }
}
