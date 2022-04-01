package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class UserRegisterService {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query) {
            return UserRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        } else {
            return UserRegister.findAllByUserName("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    def getAllByDivision(String limit, String offset, long divisionId) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!divisionId)
            return UserRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return UserRegister.findAllByDivisionId(divisionId, [sort: 'id', max: l, offset: o, order: 'desc'])
    }


    UserRegister get(String id) {
        return UserRegister.findById(Long.parseLong(id))
    }

    UserRegister getByUsername(String username) {
        return UserRegister.findByUserName(username)
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
                orderColumn = "userName"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def userRegisterCriteria = UserRegister.createCriteria()
        def userRegisterArrayList = userRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('userName', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = userRegisterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", userRegisterArrayList)
        return jsonObject
    }

    UserRegister save(JSONObject jsonObject) {
        String username = jsonObject.get("userName").toString()
        if(!UserRegister.findByUserName(username)) {
            UserRegister userRegister = new UserRegister()
            userRegister.userName = username
            userRegister.mobileNumber = jsonObject.get("mobileNumber").toString()
            userRegister.contactNumber = jsonObject.get("contactNumber").toString()
            userRegister.aadharId = jsonObject.get("aadharId").toString()
            userRegister.email = jsonObject.get("email").toString()
            userRegister.photo = jsonObject.get("photo").toString()
            userRegister.photo = userRegister.getPhoto()
            userRegister.nationality = jsonObject.get("nationality").toString()
            userRegister.address = jsonObject.get("address").toString()
            userRegister.referenceRelation = jsonObject.get("referenceRelation").toString()
            userRegister.pincode = jsonObject.get("pincode").toString()
            userRegister.bankAccount = jsonObject.get("bankAccount").toString()
            userRegister.permissions = jsonObject.get("permissions").toString()
            userRegister.assignedHolidays = jsonObject.get("assignedHolidays").toString()
            userRegister.specialization = jsonObject.get("specialization").toString()
            userRegister.licenceNumber = jsonObject.get("licenceNumber").toString()
            userRegister.reportTo = Long.parseLong(jsonObject.get("reportTo").toString())
            userRegister.genderId = Long.parseLong(jsonObject.get("genderId").toString())
            userRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
            userRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
            userRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
            userRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
            userRegister.zoneId = Long.parseLong(jsonObject.get("zoneId").toString())
            userRegister.divisionId = Long.parseLong(jsonObject.get("divisionId").toString())
            userRegister.referredBy = Long.parseLong(jsonObject.get("referredBy").toString())
            userRegister.status = Long.parseLong(jsonObject.get("stateId").toString())
            userRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            userRegister.paymentModeId = Long.parseLong(jsonObject.get("paymentModeId").toString())
            userRegister.approvedSalary = Double.parseDouble(jsonObject.get("approvedSalary").toString())
            userRegister.designationSalary = Double.parseDouble(jsonObject.get("designationSalary").toString())
            userRegister.joiningDate = sdf.parse(jsonObject.get("joiningDate").toString())
            userRegister.dob = sdf.parse(jsonObject.get("dob").toString())
            userRegister.anniversaryDate = sdf.parse(jsonObject.get("anniversaryDate").toString())
            userRegister.lastLoginDate = sdf.parse(jsonObject.get("lastLoginDate").toString())
            userRegister.lastPaidDate = sdf.parse(jsonObject.get("lastPaidDate").toString())
            userRegister.department = DepartmentMaster.findById(Long.parseLong(jsonObject.get("department").toString()))
            userRegister.account = AccountRegister.findById(Long.parseLong(jsonObject.get("account").toString()))
            userRegister.role = Role.findById(Long.parseLong(jsonObject.get("role").toString()))
            userRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            userRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            userRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            userRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            userRegister.save(flush: true)
            if (!userRegister.hasErrors()) {
                AuthRegister authRegister = new AuthRegister()
                authRegister.user = userRegister
                authRegister.username = userRegister.userName
                authRegister.password = new AuthRegisterService().hashPassword(jsonObject.get("password").toString())
                authRegister.save(flush: true)
                return userRegister
            } else {
                throw new BadRequestException()
            }
        }
        else
        {
            println("User already exists")
            throw new BadRequestException()
        }
    }

    UserRegister update(JSONObject jsonObject, String id) {
        UserRegister userRegister = UserRegister.findById(Long.parseLong(id))
        if (userRegister) {
            userRegister.isUpdatable = true
            //userRegister.userName = jsonObject.get("userName").toString()
            userRegister.mobileNumber = jsonObject.get("mobileNumber").toString()
            userRegister.contactNumber = jsonObject.get("contactNumber").toString()
            userRegister.aadharId = jsonObject.get("aadharId").toString()
            userRegister.email = jsonObject.get("email").toString()
            if(userRegister.getPhoto()!='')
            {
                userRegister.photo = jsonObject.get("photo").toString()
            }
            else
            {
                userRegister.photo = userRegister.getPhoto()
            }
            userRegister.nationality = jsonObject.get("nationality").toString()
            userRegister.address = jsonObject.get("address").toString()
            userRegister.referenceRelation = jsonObject.get("referenceRelation").toString()
            userRegister.pincode = jsonObject.get("pincode").toString()
            userRegister.bankAccount = jsonObject.get("bankAccount").toString()
            userRegister.permissions = jsonObject.get("permissions").toString()
            userRegister.assignedHolidays = jsonObject.get("assignedHolidays").toString()
            userRegister.specialization = jsonObject.get("specialization").toString()
            userRegister.licenceNumber = jsonObject.get("licenceNumber").toString()
            userRegister.reportTo = Long.parseLong(jsonObject.get("reportTo").toString())
            userRegister.genderId = Long.parseLong(jsonObject.get("genderId").toString())
            userRegister.countryId = Long.parseLong(jsonObject.get("countryId").toString())
            userRegister.stateId = Long.parseLong(jsonObject.get("stateId").toString())
            userRegister.cityId = Long.parseLong(jsonObject.get("cityId").toString())
            userRegister.bankId = Long.parseLong(jsonObject.get("bankId").toString())
            userRegister.zoneId = Long.parseLong(jsonObject.get("zoneId").toString())
            userRegister.divisionId = Long.parseLong(jsonObject.get("divisionId").toString())
            userRegister.referredBy = Long.parseLong(jsonObject.get("referredBy").toString())
            userRegister.status = Long.parseLong(jsonObject.get("stateId").toString())
            userRegister.syncStatus = Long.parseLong(jsonObject.get("syncStatus").toString())
            userRegister.paymentModeId = Long.parseLong(jsonObject.get("paymentModeId").toString())
            userRegister.approvedSalary = Double.parseDouble(jsonObject.get("approvedSalary").toString())
            userRegister.designationSalary = Double.parseDouble(jsonObject.get("designationSalary").toString())
            userRegister.joiningDate = sdf.parse(jsonObject.get("joiningDate").toString())
            userRegister.dob = sdf.parse(jsonObject.get("dob").toString())
            userRegister.anniversaryDate = sdf.parse(jsonObject.get("anniversaryDate").toString())
            userRegister.lastLoginDate = sdf.parse(jsonObject.get("lastLoginDate").toString())
            userRegister.lastPaidDate = sdf.parse(jsonObject.get("lastPaidDate").toString())
            userRegister.department = DepartmentMaster.findById(Long.parseLong(jsonObject.get("department").toString()))
            userRegister.account = AccountRegister.findById(Long.parseLong(jsonObject.get("account").toString()))
            userRegister.role = Role.findById(Long.parseLong(jsonObject.get("role").toString()))
            userRegister.entityType = EntityTypeMaster.findById(Long.parseLong(jsonObject.get("entityType").toString()))
            userRegister.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            userRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            userRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
            userRegister.save(flush: true)
            if (!userRegister.hasErrors()) {
                AuthRegister authRegister = new AuthRegister()
                authRegister.user = userRegister
                authRegister.username = userRegister.userName
                authRegister.password = new AuthRegisterService().hashPassword(jsonObject.get("password").toString())
                authRegister.save(flush: true)

                return userRegister
            } else {
                throw new BadRequestException()
            }
        } else {
            throw new ResourceNotFoundException()
        }
    }

    void delete(String id) {
        if (id) {
            UserRegister userRegister = UserRegister.findById(Long.parseLong(id))
            if (userRegister) {
                userRegister.isUpdatable = true
                userRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}

