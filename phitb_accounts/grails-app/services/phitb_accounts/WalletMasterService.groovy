package phitb_accounts

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_accounts.Exception.BadRequestException
import phitb_accounts.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class WalletMasterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return WalletMaster.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return WalletMaster.findAllByWalletNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    WalletMaster get(String id) {
        return WalletMaster.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "walletName"
                break;
            case '1':
                orderColumn = "entityId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def walletMasterCriteria = WalletMaster.createCriteria()
        def walletMasterArrayList = walletMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('walletName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = walletMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", walletMasterArrayList)
        return jsonObject
    }

    WalletMaster save(JSONObject jsonObject) {
        WalletMaster walletMaster = new WalletMaster()
        walletMaster.walletName = jsonObject.get("walletName").toString()
        walletMaster.status = Long.parseLong(jsonObject.get("status").toString())
        walletMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
        walletMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        walletMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        walletMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        walletMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        walletMaster.save(flush: true)
        if (!walletMaster.hasErrors())
            return walletMaster
        else
            throw new BadRequestException()

    }

    WalletMaster update(JSONObject jsonObject, String id) {

        WalletMaster walletMaster = WalletMaster.findById(Long.parseLong(id))
        if (walletMaster) {
            walletMaster.isUpdatable = true
            walletMaster.walletName = jsonObject.get("walletName").toString()
            walletMaster.status = Long.parseLong(jsonObject.get("status").toString())
            walletMaster.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            walletMaster.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
            walletMaster.entityId = Long.parseLong(jsonObject.get("entityId").toString())
            walletMaster.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            walletMaster.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            walletMaster.save(flush: true)
            if (!walletMaster.hasErrors())
                return walletMaster
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            WalletMaster walletMaster = WalletMaster.findById(Long.parseLong(id))
            if (walletMaster) {
                walletMaster.isUpdatable = true
                walletMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}