package phitb_sales

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_sales.Exception.BadRequestException
import phitb_sales.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class SampleConversionDetailsService {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
        {
            return SampleConversionDetails.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return SampleConversionDetails.findAllByBatchNumberIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
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

    SampleConversionDetails get(String id)
    {
        return SampleConversionDetails.findById(Long.parseLong(id))
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
        def salesProductDetailsCriteria = SampleConversionDetails.createCriteria()
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

    SampleConversionDetails save(JSONObject jsonObject)
    {
        SampleConversionDetails sampleConversionDetails = new SampleConversionDetails()
        sampleConversionDetails.finId = Long.parseLong(jsonObject.get("finId").toString())
        sampleConversionDetails.billId = Long.parseLong(jsonObject.get("billId").toString())
        sampleConversionDetails.reason = jsonObject.get("reason").toString()
        sampleConversionDetails.billType = Long.parseLong(jsonObject.get("billType").toString())
        sampleConversionDetails.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
        sampleConversionDetails.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
        sampleConversionDetails.productId = Long.parseLong(jsonObject.get("productId").toString())
        sampleConversionDetails.batchNumber = jsonObject.get("batchNumber").toString()
        sampleConversionDetails.expiryDate = jsonObject.get("expiryDate").toString()
        sampleConversionDetails.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
        sampleConversionDetails.originalSqty = Double.parseDouble(jsonObject.get("originalSqty").toString())
        sampleConversionDetails.originalFqty = Double.parseDouble(jsonObject.get("originalFqty").toString())
        sampleConversionDetails.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
        sampleConversionDetails.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
        sampleConversionDetails.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
        sampleConversionDetails.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
        sampleConversionDetails.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
        sampleConversionDetails.discount = Double.parseDouble(jsonObject.get("discount").toString())
        sampleConversionDetails.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
        sampleConversionDetails.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
        sampleConversionDetails.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
        sampleConversionDetails.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
        sampleConversionDetails.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
        sampleConversionDetails.amount = Double.parseDouble(jsonObject.get("amount").toString())
        sampleConversionDetails.reason = jsonObject.get("reason").toString()
        sampleConversionDetails.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
        sampleConversionDetails.kitName = Long.parseLong(jsonObject.get("kitName").toString())
        sampleConversionDetails.saleFinId = jsonObject.get("saleFinId").toString()
        sampleConversionDetails.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
        sampleConversionDetails.status = Long.parseLong(jsonObject.get("status").toString())
        sampleConversionDetails.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        sampleConversionDetails.financialYear = jsonObject.get("financialYear").toString()
        sampleConversionDetails.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        sampleConversionDetails.entityId = Long.parseLong(jsonObject.get("entityId").toString())

        sampleConversionDetails.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
        sampleConversionDetails.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
        sampleConversionDetails.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
        sampleConversionDetails.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
        sampleConversionDetails.uuid = jsonObject.get("uuid").toString()

        sampleConversionDetails.save(flush: true)
        if (!sampleConversionDetails.hasErrors())
        {
            return sampleConversionDetails
        }
        else
        {
            throw new BadRequestException()
        }
    }

    SampleConversionDetails update(JSONObject jsonObject, String id)
    {
        SampleConversionDetails sampleConversion = SampleConversionDetails.findById(Long.parseLong(id))
        if (SampleConversionDetails)
        {
            sampleConversion.isUpdatable = true
            sampleConversion.finId = Long.parseLong(jsonObject.get("finId").toString())
            sampleConversion.billId = Long.parseLong(jsonObject.get("billId").toString())
            sampleConversion.billType = Long.parseLong(jsonObject.get("billType").toString())
            sampleConversion.serBillId = Long.parseLong(jsonObject.get("serBillId").toString())
            sampleConversion.seriesId = Long.parseLong(jsonObject.get("seriesId").toString())
            sampleConversion.productId = Long.parseLong(jsonObject.get("productId").toString())
            sampleConversion.batchNumber = jsonObject.get("batchNumber").toString()
            sampleConversion.expiryDate = jsonObject.get("expiryDate").toString()
            sampleConversion.originalSqty = Double.parseDouble(jsonObject.get("originalSqty").toString())
            sampleConversion.originalFqty = Double.parseDouble(jsonObject.get("originalFqty").toString())
            sampleConversion.sqty = Double.parseDouble(jsonObject.get("sqty").toString())
            sampleConversion.freeQty = Double.parseDouble(jsonObject.get("freeQty").toString())
            sampleConversion.repQty = Double.parseDouble(jsonObject.get("repQty").toString())
            sampleConversion.pRate = Double.parseDouble(jsonObject.get("pRate").toString())
            sampleConversion.sRate = Double.parseDouble(jsonObject.get("sRate").toString())
            sampleConversion.mrp = Double.parseDouble(jsonObject.get("mrp").toString())
            sampleConversion.discount = Double.parseDouble(jsonObject.get("discount").toString())
            sampleConversion.gstId = Double.parseDouble(jsonObject.get("gstId").toString())
            sampleConversion.gstAmount = Double.parseDouble(jsonObject.get("gstAmount").toString())
            sampleConversion.sgstAmount = Double.parseDouble(jsonObject.get("sgstAmount").toString())
            sampleConversion.cgstAmount = Double.parseDouble(jsonObject.get("cgstAmount").toString())
            sampleConversion.igstAmount = Double.parseDouble(jsonObject.get("igstAmount").toString())
            sampleConversion.amount = Double.parseDouble(jsonObject.get("amount").toString())
            sampleConversion.reason = jsonObject.get("reason").toString()
            sampleConversion.fridgeId = Long.parseLong(jsonObject.get("fridgeId").toString())
            sampleConversion.kitName = Long.parseLong(jsonObject.get("kitName").toString())
            sampleConversion.saleFinId = jsonObject.get("saleFinId").toString()
            sampleConversion.redundantBatch = Long.parseLong(jsonObject.get("redundantBatch").toString())
            sampleConversion.status = Long.parseLong(jsonObject.get("status").toString())
            sampleConversion.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            sampleConversion.financialYear = jsonObject.get("financialYear").toString()
            sampleConversion.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            sampleConversion.entityId = Long.parseLong(jsonObject.get("entityId").toString())

            sampleConversion.gstPercentage = Double.parseDouble(jsonObject.get("gstPercentage").toString())
            sampleConversion.sgstPercentage = Double.parseDouble(jsonObject.get("sgstPercentage").toString())
            sampleConversion.cgstPercentage = Double.parseDouble(jsonObject.get("cgstPercentage").toString())
            sampleConversion.igstPercentage = Double.parseDouble(jsonObject.get("igstPercentage").toString())
            sampleConversion.uuid = jsonObject.get("uuid").toString()
            sampleConversion.save(flush: true)
            if (!sampleConversion.hasErrors())
            {
                return sampleConversion
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
            SampleConversionDetails sampleConversionDetails = SampleConversionDetails.findById(Long.parseLong(id))
            if (sampleConversionDetails)
            {
                sampleConversionDetails.isUpdatable = true
                sampleConversionDetails.delete()
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
            ArrayList<SampleConversionDetails> sampleConversion = SampleConversionDetails.findAllByBillId(Long.parseLong(id))
            if (sampleConversion)
            {
                return sampleConversion
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
        ArrayList<SampleConversionDetails> sampleConversionDetails = SampleConversionDetails.findAllByBillIdInList(arrayListIds)
        if (sampleConversionDetails)
        {
            return sampleConversionDetails
        }
        else
        {
            throw new ResourceNotFoundException()
        }

    }

    Object getSampleConversionDetailsByProductId(String productId)
    {
        try
        {
            return SampleConversionDetails.findAllByProductId(Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }


    Object getSampleConversionDetailsByBillIdAndBatch(String billId, String batchNumber,String productId)
    {
        try
        {
            return SampleConversionDetails.findByBillIdAndBatchNumberAndProductId(Long.parseLong(billId),batchNumber,Long.parseLong(productId))
        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }

}
