package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class SaleReturnDetailsService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SaleProductDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SaleProductDetails.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
        }
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return SaleBillDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(days))
            Date dateCreated = cal.getTime()
            return SaleBillDetails.createCriteria().list {
                gt("dateCreated", dateCreated)
            }
        }
    }

    SaleReturnDetails get(String id)
    {
        return SaleReturnDetails.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "entityName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def salesProductDetailsCriteria = SaleReturnDetails.createCriteria()
        def salesProductDetailsArrayList = salesProductDetailsCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('batchNumber', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = salesProductDetailsArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", salesProductDetailsArrayList)
        return jsonObject
    }

    SaleReturnDetails save(JSONObject jsonObject)
    {
        SaleReturnDetails saleReturnDetails = new SaleReturnDetails()
        saleReturnDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        saleReturnDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
        saleReturnDetails.reason = jsonObject.get("reason").toString()
        saleReturnDetails.invoiceNumber = jsonObject.get("invoiceNumber").toString()
        if(jsonObject.get("saleBillId").toString()!=null && jsonObject.get("saleBillId").toString()!="")
        {
            saleReturnDetails.saleBillId = Long.parseLong(jsonObject.get("saleBillId").toString())
        }
        else
        {
            saleReturnDetails.saleBillId = null
        }
        saleReturnDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
        saleReturnDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        saleReturnDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        saleReturnDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
        saleReturnDetails.batchNumber = jsonObject.get("batchNumber").toString()
        saleReturnDetails.expiryDate = jsonObject.get("expiryDate").toString()
        saleReturnDetails.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
        saleReturnDetails.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
        saleReturnDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
        saleReturnDetails.pRate = Double.parseDouble("0")
        saleReturnDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        saleReturnDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        saleReturnDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
        saleReturnDetails.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
        saleReturnDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        saleReturnDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        saleReturnDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        saleReturnDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        saleReturnDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
        saleReturnDetails.reason = jsonObject.get("reason").toString()
        saleReturnDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        saleReturnDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        saleReturnDetails.saleFinId = jsonObject.get("saleFinId").toString()
        saleReturnDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        saleReturnDetails.status = Long.parseLong(jsonObject.get("status").toString())
        saleReturnDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        saleReturnDetails.financialYear = jsonObject.get("financialYear").toString()
        saleReturnDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        saleReturnDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        saleReturnDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        saleReturnDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        saleReturnDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        saleReturnDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        saleReturnDetails.save(flush: true)
        if (!saleReturnDetails.hasErrors())
        {
            return saleReturnDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SaleReturnDetails update(JSONObject jsonObject, String id)
    {
        SaleReturnDetails saleReturnDetails = SaleReturnDetails.findById(Long.parseLong(id))
        if (saleReturnDetails)
        {
            saleReturnDetails.isUpdatable = true
            saleReturnDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
            saleReturnDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
            saleReturnDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
            saleReturnDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            saleReturnDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            saleReturnDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
            saleReturnDetails.batchNumber = jsonObject.get("batchNumber").toString()
            saleReturnDetails.expiryDate = jsonObject.get("expiryDate").toString()
            saleReturnDetails.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
            saleReturnDetails.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
            saleReturnDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            saleReturnDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            saleReturnDetails.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            saleReturnDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            saleReturnDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            saleReturnDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
            saleReturnDetails.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
            saleReturnDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            saleReturnDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            saleReturnDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            saleReturnDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            saleReturnDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
            saleReturnDetails.reason = jsonObject.get("reason").toString()
            saleReturnDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            saleReturnDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            saleReturnDetails.saleFinId = jsonObject.get("saleFinId").toString()
            saleReturnDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            saleReturnDetails.status = Long.parseLong(jsonObject.get("status").toString())
            saleReturnDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            saleReturnDetails.financialYear = jsonObject.get("financialYear").toString()
            saleReturnDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            saleReturnDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            saleReturnDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            saleReturnDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            saleReturnDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            saleReturnDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
            saleReturnDetails.save(flush: true)
            if (!saleReturnDetails.hasErrors())
            {
                return saleReturnDetails
            }
            else
            {
                throw new BadRequestException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    void delete(String id)
    {
        if (id)
        {
            SaleReturnDetails saleReturnDetails = SaleReturnDetails.findById(Long.parseLong(id))
            if (saleReturnDetails)
            {
                saleReturnDetails.isUpdatable = true
                saleReturnDetails.delete()
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }

    def getBySaleBill(String id)
    {
        if (id)
        {
            ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllByBillId(Long.parseLong(id))
            if (saleReturnDetails)
            {
                return saleReturnDetails
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }

    def getBySaleBillByList(ArrayList<Long> arrayListIds)
    {
        ArrayList<SaleReturnDetails> saleReturnDetails = SaleReturnDetails.findAllByBillIdInList(arrayListIds)
        if (saleReturnDetails)
        {
            return saleReturnDetails
        }
        else
        {
            throw new ResourceNotFoundException()
        }

    }

    def getSaleReturnDetailsByBill(String id)
    {
       return SaleReturnDetails.findByBillId(Long.parseLong(id))
    }

    def getSaleReturnDetailsByProductBatchSaleBill(String productId, String batch, String saleBillId)
    {
        return SaleReturnDetails.findAllByProductIdAndBatchNumberAndSaleBillId(productId as long,batch, saleBillId as Long)
    }
}
