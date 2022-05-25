package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Transactional
class ReceiptDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ReceiptDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ReceiptDetail.findAllByNarrationIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return ReceiptDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ReceiptDetail.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }
    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return ReceiptDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date date = cal.getTime()
            return ReceiptDetail.createCriteria().list {
                gt("date",date)
            }
        }
    }

    ReceiptDetail get(String id) {
        return ReceiptDetail.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "receiptId"
                break;
            case '1':
                orderColumn = "accountModeId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def receiptDetailCriteria = ReceiptDetail.createCriteria()
        def receiptDetailArrayList = receiptDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('receiptId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = receiptDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", receiptDetailArrayList)
        return jsonObject
    }

    ReceiptDetail save(JSONObject jsonObject) {
        ReceiptDetail receiptDetail = new ReceiptDetail()
        receiptDetail.receiptId = ""
        receiptDetail.date = sdf.parse(jsonObject.get("date").toString())
        receiptDetail.depositTo = jsonObject.get("depositTo").toString()
        receiptDetail.paymentModeId = Long.parseLong(jsonObject.get("paymentMode").toString())
        receiptDetail.receivedFrom = jsonObject.get("receivedFrom").toString()
        receiptDetail.amountPaid = Double.parseDouble(jsonObject.get("amountPaid").toString())
        receiptDetail.narration = jsonObject.get("narration").toString()
        if(!jsonObject.isNull("cardNumber"))
        {
            receiptDetail.cardNumber = jsonObject.get("cardNumber").toString()
        }
        else
        {
            receiptDetail.cardNumber = null
        }
        receiptDetail.paymentDate = sdf.parse(jsonObject.get("paymentDate").toString())
        receiptDetail.transId = "1"
        receiptDetail.employeeReceived = Long.parseLong("1")
        receiptDetail.commission = Double.parseDouble("1")
        receiptDetail.totalNotes = Long.parseLong("1")
        receiptDetail.chequeNumber = jsonObject.get("chequeNumber").toString()
        if(!jsonObject.isNull("bank"))
        {
            receiptDetail.bank = BankRegister.findById(Long.parseLong(jsonObject.get("bank").toString()))
        }
        else {
            receiptDetail.bank = null
        }

        if(!jsonObject.isNull("accountModeId") && jsonObject.get("accountModeId").toString()!="")
        {
            receiptDetail.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        }
        else {
            receiptDetail.accountModeId = 0
        }
        receiptDetail.wallet = WalletMaster.findById(Long.parseLong(jsonObject.get("wallet").toString()))
        receiptDetail.lockStatus = Long.parseLong("1")
        receiptDetail.approvedBy = Long.parseLong("1")
        receiptDetail.approvedDate = sdf.parse(jsonObject.get("date").toString())
        receiptDetail.financialYear = jsonObject.get("financialYear").toString()
        receiptDetail.status = Long.parseLong("1")
        receiptDetail.syncStatus = Long.parseLong("1")
        receiptDetail.entityTypeId = Long.parseLong("1")
        receiptDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        receiptDetail.modifiedUser = Long.parseLong("1")
        receiptDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        receiptDetail.save(flush: true)
        if (!receiptDetail.hasErrors())
        {
            Calendar cal = new GregorianCalendar()
            cal.setTime(receiptDetail.paymentDate)
            String month = cal.get(Calendar.MONTH)
            String year = cal.get(Calendar.YEAR)
            DecimalFormat mFormat = new DecimalFormat("00");
            month = mFormat.format(Double.valueOf(month));
            String reciptId = null;
            ReceiptDetail receiptDetail1
            reciptId = receiptDetail.entityId + "/R/" + month + year + "/" + receiptDetail.id
            println("Invoice Number generated: " + reciptId)
            if (reciptId)
            {
                receiptDetail.receiptId = reciptId
                receiptDetail.isUpdatable = true
                receiptDetail.save(flush: true)
            }
            return receiptDetail
        }
        else
        {
            throw new BadRequestException()
        }

    }

    ReceiptDetail update(JSONObject jsonObject, String id) {

        ReceiptDetail receiptDetail = ReceiptDetail.findById(Long.parseLong(id))
        if (receiptDetail) {
            receiptDetail.isUpdatable = true
            receiptDetail.receiptId = jsonObject.get("receiptId").toString()
            receiptDetail.date = sdf.parse(jsonObject.get("date").toString())
            receiptDetail.paymentModeId = Long.parseLong(jsonObject.get("paymentModeId").toString())
            receiptDetail.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
            receiptDetail.receivedFrom = jsonObject.get("receivedFrom").toString()
            receiptDetail.depositTo = jsonObject.get("depositTo").toString()
            receiptDetail.amountPaid = Double.parseDouble(jsonObject.get("amountPaid").toString())
            receiptDetail.narration = jsonObject.get("narration").toString()
            receiptDetail.cardNumber = Long.parseLong(jsonObject.get("cardNumber").toString())
            receiptDetail.paymentDate = sdf.parse(jsonObject.get("paymentDate").toString())
            receiptDetail.transId = jsonObject.get("transId").toString()
            receiptDetail.employeeReceived = Long.parseLong(jsonObject.get("employeeReceived").toString())
            receiptDetail.commission = Double.parseDouble(jsonObject.get("commission").toString())
            receiptDetail.totalNotes = Long.parseLong(jsonObject.get("totalNotes").toString())
            receiptDetail.chequeNumber = jsonObject.get("chequeNumber").toString()
            receiptDetail.bank = BankRegister.findById(Long.parseLong(jsonObject.get("bank").toString()))
            receiptDetail.wallet = WalletMaster.findById(Long.parseLong(jsonObject.get("wallet").toString()))
            receiptDetail.lockStatus = Long.parseLong(jsonObject.get("lockStatus").toString())
            receiptDetail.approvedBy = Long.parseLong(jsonObject.get("approvedBy").toString())
            receiptDetail.approvedDate = sdf.parse(jsonObject.get("approvedDate").toString())
            receiptDetail.financialYear = jsonObject.get("financialYear").toString()
            receiptDetail.status = Long.parseLong(jsonObject.get("status").toString())
            receiptDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            receiptDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            receiptDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            receiptDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            receiptDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            receiptDetail.save(flush: true)
            if (!receiptDetail.hasErrors())
                return receiptDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            ReceiptDetail receiptDetail = ReceiptDetail.findById(Long.parseLong(id))
            if (receiptDetail) {
                receiptDetail.isUpdatable = true
                receiptDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
