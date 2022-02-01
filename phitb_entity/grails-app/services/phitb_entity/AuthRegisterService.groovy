package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class AuthRegisterService {

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return AuthRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return AuthRegister.findAllByUsernameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    AuthRegister get(String id) {
        return AuthRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "startDate"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def authRegisterCriteria = AuthRegister.createCriteria()
        def authRegisterCriteriaArrayList = authRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('startDate', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = authRegisterCriteriaArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", authRegisterCriteriaArrayList)
        return jsonObject
    }

    AuthRegister save(JSONObject jsonObject) {
        AuthRegister authRegister = new AuthRegister()
        authRegister.username = jsonObject.get("username").toString()
        authRegister.password = jsonObject.get("password").toString()
        authRegister.user = UserRegister.findById(Long.parseLong(jsonObject.get("user").toString()))
        authRegister.save(flush: true)
        if (!authRegister.hasErrors())
            return authRegister
        else
            throw new BadRequestException()
    }

    AuthRegister update(JSONObject jsonObject, String id) {
        AuthRegister authRegister = AuthRegister.findById(Long.parseLong(id))
        if (authRegister) {
            authRegister.isUpdatable = true
            authRegister.username = jsonObject.get("username").toString()
            authRegister.password = jsonObject.get("password").toString()
            authRegister.user = UserRegister.findById(Long.parseLong(jsonObject.get("user").toString()))
            authRegister.save(flush: true)
            if (!authRegister.hasErrors())
                return authRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            AuthRegister authRegister = AuthRegister.findById(Long.parseLong(id))
            if (authRegister) {
                authRegister.isUpdatable = true
                authRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
