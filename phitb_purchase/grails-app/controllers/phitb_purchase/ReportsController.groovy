package phitb_purchase


import grails.rest.*
import grails.converters.*
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

import java.text.SimpleDateFormat

class ReportsController {
	static responseFormats = ['json', 'xml']

    def getPurchaseInfoTillDate()
    {
        try {
            JSONObject jsonObject = new JSONObject(request.reader.text)
            println(jsonObject.toString())
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date date = sdf.parse(jsonObject.get("date").toString())
            long entityId = jsonObject.get("entityId")
            HashMap<Long, JSONArray> totalSales = new PurchaseReportsService().getTotalPurchasesTillDate(date, entityId)
            HashMap<Long, JSONArray> totalSaleReturns = new PurchaseReportsService().getTotalPurchaseReturnTillDate(date, entityId)
            JSONObject responseJson = new JSONObject()
            responseJson.put("totalPurchases", totalSales)
            responseJson.put("totalPurchaseReturns", totalSaleReturns)
            respond responseJson
        }
        catch (Exception e)
        {
            e.printStackTrace()
            response.status = 400
        }
    }

    def productDeleteClearance() {
        try {
            String id = params.id
            JSONObject jsonObject = new JSONObject()
            if (id) {
                long purchaseCount = PurchaseProductDetail.countByProductId(Long.parseLong(id))
                long purchaseOrderCount = PurchaseOrderProductDetail.countByProductId(Long.parseLong(id))
                long purchaseReturnCount = PurchaseReturnDetail.countByProductId(Long.parseLong(id))

                long total = purchaseCount + purchaseOrderCount + purchaseReturnCount
                if (total == 0) {
                    jsonObject.put("delete", true)
                } else {
                    jsonObject.put("delete", false)
                }
            } else {
                jsonObject.put("delete", false)
            }
            respond jsonObject
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }

    def batchDeleteClearance() {
        try {
            String id = params.id
            String batchNumber = params.batchNumber
            JSONObject jsonObject = new JSONObject()
            if (id) {

                long purchaseCount = PurchaseProductDetail.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long purchaseOrderCount = PurchaseOrderProductDetail.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)
                long purchaseReturnCount = PurchaseReturnDetail.countByProductIdAndBatchNumber(Long.parseLong(id),batchNumber)

                long total = purchaseCount + purchaseOrderCount + purchaseReturnCount
                if (total == 0) {
                    jsonObject.put("delete", true)
                } else {
                    jsonObject.put("delete", false)
                }
            } else {
                jsonObject.put("delete", false)
            }
            respond jsonObject
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
    }
}
