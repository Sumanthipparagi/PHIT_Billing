package phitb_ui.shipments

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
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
                    for (JSONObject sale : sales) {
                        long customerId = sale.get("customerId")
                        JSONObject customer = new EntityService().getEntityById(customerId.toString())
                        sale.put("customer", customer)
                        JSONObject city = new SystemService().getCityById(customer.get("cityId").toString())
                        sale.put("city", city)

                        JSONObject transportationDetails = new SalesService().getSaleTransportationByBill(sale.get("id").toString(),"SALE_INVOICE")
                        sale.put("transportationDetails", transportationDetails)
                    }
                    documents.put(docType, sales)
                    break;
                case "SALE_RETURN":
                    //get sales return
                    JSONArray saleReturn = new SalesService().getSaleReturnByDateRange(dateRange, entityId)
                    documents.put(docType, saleReturn)
                    break;
                case "PURCHASE":
                    //get purchase
                    JSONArray purchase = new PurchaseService().getPurchaseBillByDateRange(dateRange, entityId)
                    documents.put(docType, purchase)
                    break;
                case "PURCHASE_RETURN":
                    //get purchase return
                    JSONArray purchaseReturn = new PurchaseService().getPurchaseRetrunByDateRange(dateRange, entityId)
                    documents.put(docType, purchaseReturn)
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
            transportObject.put("financialYear", 0)
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

    }
}
