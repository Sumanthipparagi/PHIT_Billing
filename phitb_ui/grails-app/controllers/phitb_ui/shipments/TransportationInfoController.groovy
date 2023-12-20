package phitb_ui.shipments

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.EntityService
import phitb_ui.PurchaseService
import phitb_ui.SalesService
import phitb_ui.ShipmentService
import phitb_ui.SystemService

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
}
