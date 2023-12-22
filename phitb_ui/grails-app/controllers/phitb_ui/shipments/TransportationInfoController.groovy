package phitb_ui.shipments

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.ShipmentService
import phitb_ui.SystemService

import javax.ws.rs.core.Response

class TransportationInfoController {

    def index() {
        JSONArray transporters = new ShipmentService().getAllTransporterByEntity(session.getAttribute("entityId").toString())
        render(view: "/shipments/transportation-info", model: [transporters:transporters])
    }

    def getDocuments()
    {
        String entityId = session.getAttribute("entityId").toString()
        String dateRange = params.dateRange
        String docType = params.docType
        JSONObject documents = new JSONObject()
        if(docType != null && dateRange != null)
        {
            switch (docType) {
                case "SALES":
                    //get sales
                    JSONArray sales = new SalesService().getSaleBillByDateRange(dateRange, entityId)
                    JSONArray salesFiltered = new JSONArray()
                    for (JSONObject sale : sales) {
                        if(sale.get("billStatus") != "CANCELLED") {
                            long customerId = sale.get("customerId")
                            JSONObject customer = new EntityService().getEntityById(customerId.toString())
                            sale.put("customer", customer)
                            JSONObject city = new SystemService().getCityById(customer.get("cityId").toString())
                            sale.put("city", city)

                            JSONObject transportationDetails = new SalesService().getSaleTransportationByBill(sale.get("id").toString(), "SALE_INVOICE")
                            sale.put("transportationDetails", transportationDetails)
                            salesFiltered.add(sale)
                        }
                    }
                    documents.put(docType, salesFiltered)
                    break;
                case "SALE_RETURN":
                    //get sales return
                    JSONArray saleReturns = new SalesService().getSaleReturnByDateRange(dateRange, entityId)
                    JSONArray salesReturnFiltered = new JSONArray()
                    for (JSONObject saleReturn : saleReturns) {
                        if(saleReturn.get("billStatus") != "CANCELLED") {
                            long customerId = Long.parseLong(saleReturn.get("customerId").toString())
                            JSONObject customer = new EntityService().getEntityById(customerId.toString())
                            saleReturn.put("customer", customer)
                            JSONObject city = new SystemService().getCityById(customer.get("cityId").toString())
                            saleReturn.put("city", city)

                            JSONObject transportationDetails = new SalesService().getSaleTransportationByBill(saleReturn.get("id").toString(), "SALE_RETURN")
                            saleReturn.put("transportationDetails", transportationDetails)
                            salesReturnFiltered.add(saleReturn)
                        }
                    }
                    documents.put(docType, saleReturns)
                    break;
                case "PURCHASE":
                    //get purchase
                    JSONArray purchases = new PurchaseService().getPurchaseBillByDateRange(dateRange, entityId)
                    JSONArray purchaseFiltered = new JSONArray()
                    for (JSONObject purchase : purchases) {
                        if(purchase.get("billStatus") != "CANCELLED") {
                            long supplierId = Long.parseLong(purchase.get("supplierId").toString())
                            JSONObject supplier = new EntityService().getEntityById(supplierId.toString())
                            purchase.put("customer", supplier)
                            JSONObject city = new SystemService().getCityById(supplier.get("cityId").toString())
                            purchase.put("city", city)

                            JSONObject transportationDetails = new PurchaseService().getPurchaseTransportationByBill(purchase.get("id").toString(), "PURCHASE_INVOICE")
                            purchase.put("transportationDetails", transportationDetails)
                            purchaseFiltered.add(purchase)
                        }
                    }
                    documents.put(docType, purchaseFiltered)
                    break;
                case "PURCHASE_RETURN":
                    //get purchase return
                    JSONArray purchaseReturns = new PurchaseService().getPurchaseRetrunByDateRange(dateRange, entityId)
                    JSONArray purchaseReturnFiltered = new JSONArray()
                    for (JSONObject purchaseReturn : purchaseReturns) {
                        if(purchaseReturn.get("billStatus") != "CANCELLED") {
                            long supplierId = Long.parseLong(purchaseReturn.get("supplierId").toString())
                            JSONObject supplier = new EntityService().getEntityById(supplierId.toString())
                            purchaseReturn.put("customer", supplier)
                            JSONObject city = new SystemService().getCityById(supplier.get("cityId").toString())
                            purchaseReturn.put("city", city)

                            JSONObject transportationDetails = new PurchaseService().getPurchaseTransportationByBill(purchaseReturn.get("id").toString(), "PURCHASE_RETURN")
                            purchaseReturn.put("transportationDetails", transportationDetails)
                            purchaseReturnFiltered.add(purchaseReturn)
                        }
                    }
                    documents.put(docType, purchaseReturnFiltered)
                    break;
            }
        }

        respond documents, formats: ['json']
    }

    def SaveSaleTransportation()
    {
        String saleId = params.id
        String doctype = params.docType
        String transporter = params.transporter
        String transportDate = params.transportDate
        String lrNumber = params.lrNumber
        JSONObject transportObject = new SalesService().getSaleTransportationByBill(saleId, doctype)

        if(transportObject != null)
        {
            transportObject.put("transporterId",Long.parseLong(transporter))
            transportObject.put("lrDate", transportDate)
            transportObject.put("lrNumber", lrNumber)
            Response transportation = new SalesService().updateSaleTransportation(transportObject)
            if (transportation?.status == 200) {
                println("Transportation details added")
                respond transportObject, formats: ['json'], status: 200
            } else {
                println("something went wrong!!")
                response.status = 400
            }
        }
        else {
            JSONObject saleBillDetail = new SalesService().getSaleBillDetailsById(saleId)
            transportObject = new JSONObject()
            transportObject.put("finId", 0)
            transportObject.put("billId", saleId)
            transportObject.put("billType", doctype)
            transportObject.put("serBillId", saleBillDetail.serBillId)
            transportObject.put("series", saleBillDetail.seriesId)
            transportObject.put("customerId", saleBillDetail.customerId)
            transportObject.put("transporterId", Long.parseLong(transporter))
            transportObject.put("lrDate", transportDate)
            transportObject.put("lrNumber", lrNumber)
            transportObject.put("cartonsCount", "")
            transportObject.put("paid", 0)
            transportObject.put("toPay", 0)
            transportObject.put("generalInfo", 0)
            transportObject.put("selfNo", 0)
            transportObject.put("ccm", 0)
            transportObject.put("receivedTemperature", 0)
            transportObject.put("freightCharge", 0)
            transportObject.put("vehicleId", 0)
            transportObject.put("deliveryStatus", 0)
            transportObject.put("dispatchDateTime", 0)
            transportObject.put("deliveryDateTime", 0)
            transportObject.put("trackingDetails", 0)
            transportObject.put("ewaybillId", 0)
            transportObject.put("genralInfo", 0)
            transportObject.put("weight", 0)
            transportObject.put("ewaysupplytype", 0)
            transportObject.put("ewaysupplysubtype", 0)
            transportObject.put("ewaydoctype", 0)
            transportObject.put("consignmentNo", 0)
            transportObject.put("syncStatus", 0)
            transportObject.put("financialYear", session.getAttribute('financialYear'))
            transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
            transportObject.put("entityId", session.getAttribute('entityId'))
            Response transportation = new SalesService().saveSaleTransportation(transportObject)
            if (transportation?.status == 200) {
                println("Transportation details added")
                respond transportObject, formats: ['json'], status: 200
            } else {
                println("something went wrong!!")
                response.status = 400
            }
        }
    }

    def SavePurchaseTransportation()
    {
        String purchaseId = params.id
        String doctype = params.docType
        String transporter = params.transporter
        String transportDate = params.transportDate
        String lrNumber = params.lrNumber
        JSONObject transportObject = new PurchaseService().getPurchaseTransportationByBill(purchaseId, doctype)

        if(transportObject != null)
        {
            transportObject.put("transporterId",Long.parseLong(transporter))
            transportObject.put("lrDate", transportDate)
            transportObject.put("lrNumber", lrNumber)
            Response transportation = new PurchaseService().updatePurchaseTransportation(transportObject)
            if (transportation?.status == 200) {
                println("Transportation details added")
                respond transportObject, formats: ['json'], status: 200
            } else {
                println("something went wrong!!")
                response.status = 400
            }
        }
        else {
            JSONObject purchaseBillDetail = new PurchaseService().getPurchaseBillDetailsById(purchaseId)
            transportObject = new JSONObject();
            transportObject.put("finId", 0)
            transportObject.put("billId", purchaseBillDetail.id)
            transportObject.put("billType", doctype)
            transportObject.put("serBillId", purchaseBillDetail.serBillId)
            transportObject.put("series", purchaseBillDetail.seriesId)
            transportObject.put("supplierId", purchaseBillDetail.supplierId)
            transportObject.put("transporterId", transporter)
            transportObject.put("lrDate", transportDate)
            transportObject.put("lrNumber", lrNumber)
            transportObject.put("cartonsCount", "")
            transportObject.put("paid", 0)
            transportObject.put("toPay", 0)
            transportObject.put("generalInfo", 0)
            transportObject.put("selfNo", 0)
            transportObject.put("ccm", 0)
            transportObject.put("receivedTemperature", 0)
            transportObject.put("freightCharge", 0)
            transportObject.put("vehicleId", 0)
            transportObject.put("deliveryStatus", 0)
            transportObject.put("dispatchDateTime", 0)
            transportObject.put("deliveryDateTime", 0)
            transportObject.put("trackingDetails", 0)
            transportObject.put("ewaybillId", 0)
            transportObject.put("genralInfo", 0)
            transportObject.put("weight", 0)
            transportObject.put("ewaysupplytype", 0)
            transportObject.put("ewaysupplysubtype", 0)
            transportObject.put("ewaydoctype", 0)
            transportObject.put("consignmentNo", 0)
            transportObject.put("syncStatus", 0)
            transportObject.put("financialYear", session.getAttribute('financialYear'))
            transportObject.put("entityTypeId", session.getAttribute('entityTypeId'))
            transportObject.put("entityId", session.getAttribute('entityId'))
            transportObject.put("createdUser", session.getAttribute('userId'))
            transportObject.put("modifiedUser", session.getAttribute('userId'))
            Response transportation = new PurchaseService().savePurchaseTransportation(transportObject)
            if (transportation?.status == 200) {
                println("Transportation details added")
                respond transportObject, formats: ['json'], status: 200
            } else {
                println("Failed to add transportation details")
                response.status = 400
            }
        }
    }
}
