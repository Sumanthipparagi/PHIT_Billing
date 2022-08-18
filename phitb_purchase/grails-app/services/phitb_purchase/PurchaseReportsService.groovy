package phitb_purchase

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

@Transactional
class PurchaseReportsService {

    HashMap<Long, JSONArray> getTotalPurchasesTillDate(Date date, long entityId)
    {
        HashMap<Long, JSONArray> totalPurchases = new HashMap<>()
        ArrayList<PurchaseBillDetail> purchaseBillDetail = PurchaseBillDetail.findAllByDateCreatedLessThanEqualsAndEntityIdAndBillStatusNotEqual(date, entityId, "CANCELLED")
        if(purchaseBillDetail.size()>0) {
            ArrayList<PurchaseProductDetail> productDetails = PurchaseProductDetail.findAllByBillIdInList(purchaseBillDetail.id)
            for (PurchaseProductDetail productDetail : productDetails) {
                if (totalPurchases.containsKey(productDetail.productId)) {
                    JSONArray purchaseInfos = totalPurchases.get(productDetail.productId)
                    ArrayList<String> batchNumbers = purchaseInfos.batchNumber
                    if (batchNumbers.contains(productDetail.batchNumber)) {
                        for (JSONObject purchaseInfo : purchaseInfos) {
                            if (purchaseInfo.batchNumber.equalsIgnoreCase(productDetail.batchNumber)) {
                                purchaseInfo.sqty += productDetail.sqty
                                purchaseInfo.freeQty += productDetail.freeQty
                            }
                        }
                    } else {
                        JSONObject purchaseInfo = new JSONObject()
                        purchaseInfo.put("batchNumber", productDetail.batchNumber)
                        purchaseInfo.put("productId", productDetail.productId)
                        purchaseInfo.put("sqty", productDetail.sqty)
                        purchaseInfo.put("freeQty", productDetail.freeQty)
                        purchaseInfos.put(purchaseInfo)
                    }
                } else {
                    JSONObject purchaseInfo = new JSONObject()
                    purchaseInfo.put("batchNumber", productDetail.batchNumber)
                    purchaseInfo.put("productId", productDetail.productId)
                    purchaseInfo.put("sqty", productDetail.sqty)
                    purchaseInfo.put("freeQty", productDetail.freeQty)
                    totalPurchases.put(productDetail.productId, new JSONArray().put(purchaseInfo))
                }
            }
        }

        return totalPurchases
    }

    HashMap<Long, JSONArray> getTotalPurchaseReturnTillDate(Date date, long entityId)
    {
        HashMap<Long, JSONArray> totalPurchaseReturns = new HashMap<>()
        ArrayList<PurchaseReturn> purchaseReturns = PurchaseReturn.findAllByDateCreatedLessThanEqualsAndEntityIdAndBillStatusNotEqual(date, entityId, "CANCELLED")
        if(purchaseReturns.size()>0) {
            ArrayList<PurchaseReturnDetail> purchaseReturnDetails = PurchaseReturnDetail.findAllByBillIdInList(purchaseReturns.id)
            for (PurchaseReturnDetail productDetail : purchaseReturnDetails) {
                if (totalPurchaseReturns.containsKey(productDetail.productId)) {
                    JSONArray purchaseReturnInfos = totalPurchaseReturns.get(productDetail.productId)
                    ArrayList<String> batchNumbers = purchaseReturnInfos.batchNumber
                    if (batchNumbers.contains(productDetail.batchNumber)) {
                        for (JSONObject purchaseReturnInfo : purchaseReturnInfos) {
                            if (purchaseReturnInfo.batchNumber.equalsIgnoreCase(productDetail.batchNumber)) {
                                purchaseReturnInfo.sqty += productDetail.sqty
                                purchaseReturnInfo.freeQty += productDetail.freeQty
                            }
                        }
                    } else {
                        JSONObject salesReturnInfo = new JSONObject()
                        salesReturnInfo.put("batchNumber", productDetail.batchNumber)
                        salesReturnInfo.put("productId", productDetail.productId)
                        salesReturnInfo.put("sqty", productDetail.sqty)
                        salesReturnInfo.put("freeQty", productDetail.freeQty)
                        salesReturnInfo.put("billStatus", productDetail.billStatus)
                        purchaseReturnInfos.put(salesReturnInfo)
                    }
                } else {
                    JSONObject salesReturnInfo = new JSONObject()
                    salesReturnInfo.put("batchNumber", productDetail.batchNumber)
                    salesReturnInfo.put("productId", productDetail.productId)
                    salesReturnInfo.put("sqty", productDetail.sqty)
                    salesReturnInfo.put("freeQty", productDetail.freeQty)
                    salesReturnInfo.put("billStatus", productDetail.billStatus)
                    totalPurchaseReturns.put(productDetail.productId, new JSONArray().put(salesReturnInfo))
                }
            }
        }
        return totalPurchaseReturns
    }
}
