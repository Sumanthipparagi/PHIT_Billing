package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

@Transactional
class SaleReportsService {

    HashMap<Long, JSONArray> getTotalSalesTillDate(Date date, long entityId)
    {
        HashMap<Long, JSONArray> totalSales = new HashMap<>()
        ArrayList<SaleBillDetails> saleBillDetails = SaleBillDetails.findAllByDateCreatedLessThanEqualsAndEntityIdAndBillStatusNotEqual(date, entityId, "CANCELLED")
        if(saleBillDetails.size()>0) {
            ArrayList<SaleProductDetails> productDetails = SaleProductDetails.findAllByBillIdInList(saleBillDetails.id)
            for (SaleProductDetails productDetail : productDetails) {
                if (totalSales.containsKey(productDetail.productId)) {
                    JSONArray salesInfos = totalSales.get(productDetail.productId)
                    ArrayList<String> batchNumbers = salesInfos.batchNumber
                    if (batchNumbers.contains(productDetail.batchNumber)) {
                        for (JSONObject salesInfo : salesInfos) {
                            if (salesInfo.batchNumber.equalsIgnoreCase(productDetail.batchNumber)) {
                                salesInfo.sqty += productDetail.sqty
                                salesInfo.freeQty += productDetail.freeQty
                            }
                        }
                    } else {
                        JSONObject salesInfo = new JSONObject()
                        salesInfo.put("batchNumber", productDetail.batchNumber)
                        salesInfo.put("productId", productDetail.productId)
                        salesInfo.put("sqty", productDetail.sqty)
                        salesInfo.put("freeQty", productDetail.freeQty)
                        salesInfos.put(salesInfo)
                    }
                } else {
                    JSONObject salesInfo = new JSONObject()
                    salesInfo.put("batchNumber", productDetail.batchNumber)
                    salesInfo.put("productId", productDetail.productId)
                    salesInfo.put("sqty", productDetail.sqty)
                    salesInfo.put("freeQty", productDetail.freeQty)
                    totalSales.put(productDetail.productId, new JSONArray().put(salesInfo))
                }
            }
        }

        return totalSales
    }

    HashMap<Long, JSONArray> getTotalSaleReturnTillDate(Date date, long entityId)
    {
        HashMap<Long, JSONArray> totalSaleReturns = new HashMap<>()
        ArrayList<SaleReturn> saleReturns = SaleReturn.findAllByDateCreatedLessThanEqualsAndEntityIdAndReturnStatusNotEqual(date, entityId, "CANCELLED")
        if(saleReturns.size()>0) {
            ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllByBillIdInList(saleReturns.id)
            for (SaleReturnDetails productDetail : saleReturnDetails) {
                if (totalSaleReturns.containsKey(productDetail.productId)) {
                    JSONArray saleReturnInfos = totalSaleReturns.get(productDetail.productId)
                    ArrayList<String> batchNumbers = saleReturnInfos.batchNumber
                    if (batchNumbers.contains(productDetail.batchNumber)) {
                        for (JSONObject saleReturnInfo : saleReturnInfos) {
                            if (saleReturnInfo.batchNumber.equalsIgnoreCase(productDetail.batchNumber)) {
                                saleReturnInfo.sqty += productDetail.sqty
                                saleReturnInfo.freeQty += productDetail.freeQty
                            }
                        }
                    } else {
                        JSONObject salesReturnInfo = new JSONObject()
                        salesReturnInfo.put("batchNumber", productDetail.batchNumber)
                        salesReturnInfo.put("productId", productDetail.productId)
                        salesReturnInfo.put("sqty", productDetail.sqty)
                        salesReturnInfo.put("freeQty", productDetail.freeQty)
                        salesReturnInfo.put("returnStatus", productDetail.returnStatus)
                        saleReturnInfos.put(salesReturnInfo)
                    }
                } else {
                    JSONObject salesReturnInfo = new JSONObject()
                    salesReturnInfo.put("batchNumber", productDetail.batchNumber)
                    salesReturnInfo.put("productId", productDetail.productId)
                    salesReturnInfo.put("sqty", productDetail.sqty)
                    salesReturnInfo.put("freeQty", productDetail.freeQty)
                    salesReturnInfo.put("returnStatus", productDetail.returnStatus)
                    totalSaleReturns.put(productDetail.productId, new JSONArray().put(salesReturnInfo))
                }
            }
        }
        return totalSaleReturns
    }

    HashMap<Long, JSONArray> getTotalSampleSalesTillDate(Date date, long entityId)
    {
        HashMap<Long, JSONArray> totalSampleSales = new HashMap<>()
        ArrayList<SampleConversion> sampleConversions = SampleConversion.findAllByDateCreatedLessThanEqualsAndEntityIdAndBillStatusNotEqual(date, entityId, "CANCELLED")
        if(sampleConversions.size()>0) {
            ArrayList<SampleConversionDetails> productDetails = SampleConversionDetails.findAllByBillIdInList(sampleConversions.id)
            for (SampleConversionDetails productDetail : productDetails) {
                if (totalSampleSales.containsKey(productDetail.productId)) {
                    JSONArray salesInfos = totalSampleSales.get(productDetail.productId)
                    ArrayList<String> batchNumbers = salesInfos.batchNumber
                    if (batchNumbers.contains(productDetail.batchNumber)) {
                        for (JSONObject salesInfo : salesInfos) {
                            if (salesInfo.batchNumber.equalsIgnoreCase(productDetail.batchNumber)) {
                                salesInfo.sqty += productDetail.sqty
                                salesInfo.freeQty += productDetail.freeQty
                            }
                        }
                    } else {
                        JSONObject salesInfo = new JSONObject()
                        salesInfo.put("batchNumber", productDetail.batchNumber)
                        salesInfo.put("productId", productDetail.productId)
                        salesInfo.put("sqty", productDetail.sqty)
                        salesInfo.put("freeQty", productDetail.freeQty)
                        salesInfos.put(salesInfo)
                    }
                } else {
                    JSONObject salesInfo = new JSONObject()
                    salesInfo.put("batchNumber", productDetail.batchNumber)
                    salesInfo.put("productId", productDetail.productId)
                    salesInfo.put("sqty", productDetail.sqty)
                    salesInfo.put("freeQty", productDetail.freeQty)
                    totalSampleSales.put(productDetail.productId, new JSONArray().put(salesInfo))
                }
            }
        }

        return totalSampleSales
    }

    HashMap<Long, JSONArray> getGTNTillDate(Date date, long entityId)
    {
        HashMap<Long, JSONArray> totalGtn = new HashMap<>()
        ArrayList<GoodsTransferNote> goodsTransferNotes = GoodsTransferNote.findAllByDateCreatedLessThanEqualsAndEntityIdAndBillStatusNotEqual(date, entityId, "CANCELLED")
        if(goodsTransferNotes.size()>0) {
            ArrayList<GoodsTransferNoteProduct> productDetails = GoodsTransferNoteProduct.findAllByBillIdInList(goodsTransferNotes.id)
            for (GoodsTransferNoteProduct productDetail : productDetails) {
                if (totalGtn.containsKey(productDetail.productId)) {
                    JSONArray salesInfos = totalGtn.get(productDetail.productId)
                    ArrayList<String> batchNumbers = salesInfos.batchNumber
                    if (batchNumbers.contains(productDetail.batchNumber)) {
                        for (JSONObject salesInfo : salesInfos) {
                            if (salesInfo.batchNumber.equalsIgnoreCase(productDetail.batchNumber)) {
                                salesInfo.sqty += productDetail.sqty
                                salesInfo.freeQty += productDetail.freeQty
                            }
                        }
                    } else {
                        JSONObject salesInfo = new JSONObject()
                        salesInfo.put("batchNumber", productDetail.batchNumber)
                        salesInfo.put("productId", productDetail.productId)
                        salesInfo.put("sqty", productDetail.sqty)
                        salesInfo.put("freeQty", productDetail.freeQty)
                        salesInfos.put(salesInfo)
                    }
                } else {
                    JSONObject salesInfo = new JSONObject()
                    salesInfo.put("batchNumber", productDetail.batchNumber)
                    salesInfo.put("productId", productDetail.productId)
                    salesInfo.put("sqty", productDetail.sqty)
                    salesInfo.put("freeQty", productDetail.freeQty)
                    totalGtn.put(productDetail.productId, new JSONArray().put(salesInfo))
                }
            }
        }

        return totalGtn
    }

   /* HashMap<Long, JSONArray> getStockAdjustmentDetails(Date date, long entityId)
    {
        HashMap<Long, JSONArray> totalAdjStk = new HashMap<>()
        ArrayList<StockAdjustmentDetails> stockAdjustmentDetails = StockAdjustmentDetails.findAllByDateCreatedLessThanEqualsAndEntityId(date, entityId)
        if(stockAdjustmentDetails.size()>0) {
            for (StockAdjustmentDetails stockAdjustmentDetail : stockAdjustmentDetails) {
                if (totalAdjStk.containsKey(stockAdjustmentDetail.productId)) {
                    JSONArray salesInfos = totalAdjStk.get(productDetail.productId)
                    ArrayList<String> batchNumbers = salesInfos.batchNumber
                    if (batchNumbers.contains(productDetail.batchNumber)) {
                        for (JSONObject salesInfo : salesInfos) {
                            if (salesInfo.batchNumber.equalsIgnoreCase(productDetail.batchNumber)) {
                                salesInfo.sqty += productDetail.sqty
                                salesInfo.freeQty += productDetail.freeQty
                            }
                        }
                    } else {
                        JSONObject salesInfo = new JSONObject()
                        salesInfo.put("batchNumber", productDetail.batchNumber)
                        salesInfo.put("productId", productDetail.productId)
                        salesInfo.put("sqty", productDetail.sqty)
                        salesInfo.put("freeQty", productDetail.freeQty)
                        salesInfos.put(salesInfo)
                    }
                } else {
                    JSONObject salesInfo = new JSONObject()
                    salesInfo.put("batchNumber", productDetail.batchNumber)
                    salesInfo.put("productId", productDetail.productId)
                    salesInfo.put("sqty", productDetail.sqty)
                    salesInfo.put("freeQty", productDetail.freeQty)
                    totalAdjStk.put(productDetail.productId, new JSONArray().put(salesInfo))
                }
            }
        }

        return totalAdjStk
    }*/
}
