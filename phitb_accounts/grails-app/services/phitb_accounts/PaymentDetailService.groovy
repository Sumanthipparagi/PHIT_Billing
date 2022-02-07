package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class PaymentDetailService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return PaymentDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PaymentDetail.findAllByNarrationIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByEntity(String limit, String offset, long entityId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!entityId)
            return PaymentDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return PaymentDetail.findAllByEntityId(entityId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    def getAllByNoOfDays(String limit, String offset, String days)
    {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!days)
        {
            return PaymentDetail.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            Date today = new Date()
            Calendar cal = new GregorianCalendar()
            cal.setTime(today)
            cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(days))
            Date date = cal.getTime()
            return PaymentDetail.createCriteria().list {
                gt("date",date)
            }
        }
    }


    PaymentDetail get(String id) {
        return PaymentDetail.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "finId"
                break;
            case '1':
                orderColumn = "accountModeId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def paymentDetailCriteria = PaymentDetail.createCriteria()
        def paymentDetailArrayList = paymentDetailCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('accountModeId', '%' + searchTerm + '%')
                    ilike('paymentModeId', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = paymentDetailArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", paymentDetailArrayList)
        return jsonObject
    }

    PaymentDetail save(JSONObject jsonObject) {
        PaymentDetail paymentDetail = new PaymentDetail()
        paymentDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
        paymentDetail.date = sdf.parse(jsonObject.get("date").toString())
        paymentDetail.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
        paymentDetail.paymentModeId = Long.parseLong(jsonObject.get("paymentModeId").toString())
        paymentDetail.transferFrom = jsonObject.get("transferFrom").toString()
        paymentDetail.paymentTo = jsonObject.get("paymentTo").toString()
        paymentDetail.amountPaid = Long.parseLong(jsonObject.get("amountPaid").toString())
        paymentDetail.narration = jsonObject.get("narration").toString()
        paymentDetail.cardNumber = jsonObject.get("cardNumber").toString()
        paymentDetail.paymentDate = jsonObject.get("paymentDate").toString()
        paymentDetail.transId = jsonObject.get("transId").toString()
        paymentDetail.employeeName = jsonObject.get("employeeName").toString()
        paymentDetail.commission = Double.parseDouble(jsonObject.get("commission").toString())
        paymentDetail.cardAmount = Double.parseDouble(jsonObject.get("cardAmount").toString())
        paymentDetail.totalNotes = Long.parseLong(jsonObject.get("totalNotes").toString())
        paymentDetail.chequeNumber = jsonObject.get("chequeNumber").toString()
        paymentDetail.bank = BankRegister.findById(Long.parseLong(jsonObject.get("bank").toString()))
        paymentDetail.wallet = WalletMaster.findById(Long.parseLong(jsonObject.get("wallet").toString()))
        paymentDetail.financialYear = jsonObject.get("financialYear").toString()
        paymentDetail.status = Long.parseLong(jsonObject.get("status").toString())
        paymentDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        paymentDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        paymentDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        paymentDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        paymentDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())

        paymentDetail.save(flush: true)
        if (!paymentDetail.hasErrors())
            return paymentDetail
        else
            throw new BadRequestException()

    }

    PaymentDetail update(JSONObject jsonObject, String id) {

        PaymentDetail paymentDetail = PaymentDetail.findById(Long.parseLong(id))
        if (paymentDetail) {
            paymentDetail.isUpdatable = true

            paymentDetail.finId = Long.parseLong(jsonObject.get("finId").toString())
            paymentDetail.date = sdf.parse(jsonObject.get("date").toString())
            paymentDetail.accountModeId = Long.parseLong(jsonObject.get("accountModeId").toString())
            paymentDetail.paymentModeId = Long.parseLong(jsonObject.get("paymentModeId").toString())
            paymentDetail.transferFrom = jsonObject.get("transferFrom").toString()
            paymentDetail.paymentTo = jsonObject.get("paymentTo").toString()
            paymentDetail.amountPaid = Long.parseLong(jsonObject.get("amountPaid").toString())
            paymentDetail.narration = jsonObject.get("narration").toString()
            paymentDetail.cardNumber = jsonObject.get("cardNumber").toString()
            paymentDetail.paymentDate = jsonObject.get("paymentDate").toString()
            paymentDetail.transId = jsonObject.get("transId").toString()
            paymentDetail.employeeName = jsonObject.get("employeeName").toString()
            paymentDetail.commission = Double.parseDouble(jsonObject.get("commission").toString())
            paymentDetail.cardAmount = Double.parseDouble(jsonObject.get("cardAmount").toString())
            paymentDetail.totalNotes = Long.parseLong(jsonObject.get("totalNotes").toString())
            paymentDetail.chequeNumber = jsonObject.get("chequeNumber").toString()
            paymentDetail.bank = BankRegister.findById(Long.parseLong(jsonObject.get("bank").toString()))
            paymentDetail.wallet = WalletMaster.findById(Long.parseLong(jsonObject.get("wallet").toString()))
            paymentDetail.financialYear = jsonObject.get("financialYear").toString()
            paymentDetail.status = Long.parseLong(jsonObject.get("status").toString())
            paymentDetail.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            paymentDetail.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            paymentDetail.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            paymentDetail.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            paymentDetail.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())

            paymentDetail.save(flush: true)
            if (!paymentDetail.hasErrors())
                return paymentDetail
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            PaymentDetail paymentDetail = PaymentDetail.findById(Long.parseLong(id))
            if (paymentDetail) {
                paymentDetail.isUpdatable = true
                paymentDetail.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
