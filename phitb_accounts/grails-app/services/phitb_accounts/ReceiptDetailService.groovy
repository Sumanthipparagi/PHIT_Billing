package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class ReceiptDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return ReceiptDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return ReceiptDetail.findAllByNarrationIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
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
                    ilike('accountModeId', '%' + searchTerm + '%')
                    ilike('paymentModeId', '%' + searchTerm + '%')
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
