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

    ArrayList<UserRegister> getByEntity(String entityId) {
        if(entityId) {
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(entityId))
            if (entityRegister)
                return UserRegister.findAllByEntity(entityRegister)
            else
                throw new ResourceNotFoundException()
        }
        else
            throw new BadRequestException()
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")
        long entityId = paramsJsonObject.get("entityId")

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
            if(entityId != 0) {
                entity {
                    eq('id', entityId)
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
            userRegister.name = jsonObject.get("name").toString()
            userRegister.mobileNumber = jsonObject.get("mobileNumber").toString()
            userRegister.contactNumber = jsonObject.get("contactNumber").toString()
            userRegister.aadharId = jsonObject.get("aadharId").toString()
            userRegister.email = jsonObject.get("email").toString()
            userRegister.photo = "0"
            userRegister.nationality = jsonObject.get("nationality").toString()
            userRegister.address = jsonObject.get("address").toString()
            userRegister.referenceRelation = jsonObject.get("referenceRelation").toString()
            userRegister.pincode = jsonObject.get("pinCode").toString()
            userRegister.userStatus = jsonObject.get("userStatus").toString()
            userRegister.bankAccount = jsonObject.get("bankAccount").toString()
//            userRegister.permissions = jsonObject.get("permissions").toString()
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
//            userRegister.paymentModeId = Long.parseLong(jsonObject.get("paymentModeId").toString())
            userRegister.approvedSalary = Double.parseDouble(jsonObject.get("approvedSalary").toString())
            userRegister.designationSalary = Double.parseDouble(jsonObject.get("designationSalary").toString())

            if(jsonObject.get("joiningDate").toString()!="")
            {
                userRegister.joiningDate = sdf.parse(jsonObject.get("joiningDate").toString())
            }else {
                userRegister.joiningDate = null
            }
            if(jsonObject.get("dob").toString()!="")
            {
                userRegister.dob = sdf.parse(jsonObject.get("dob").toString())
            }else {
                userRegister.dob = null
            }

            if(jsonObject.get("anniversaryDate").toString()!="")
            {
                userRegister.anniversaryDate = sdf.parse(jsonObject.get("anniversaryDate").toString())
            }else {
                userRegister.anniversaryDate = null
            }

            if(jsonObject.get("lastLoginDate").toString()!="")
            {
                userRegister.lastLoginDate = sdf.parse(jsonObject.get("lastLoginDate").toString())
            }else {
                userRegister.lastLoginDate = null
            }

            if(jsonObject.get("lastPaidDate").toString()!="")
            {
                userRegister.lastPaidDate = sdf.parse(jsonObject.get("lastPaidDate").toString())
            }else {
                userRegister.lastPaidDate = null
            }

            userRegister.department = DepartmentMaster.findById(Long.parseLong(jsonObject.get("department").toString()))
//            userRegister.account = AccountRegister.findById(Long.parseLong(jsonObject.get("account").toString()))
            userRegister.role = Role.findById(Long.parseLong(jsonObject.get("role").toString()))
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            userRegister.entityType = entityRegister.entityType
            userRegister.entity = entityRegister
            userRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            userRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())

            if(jsonObject.has("entityRoute"))
            {
                String[] routeIdArr = jsonObject.get("entityRoute")
                for (String routeId : routeIdArr) {
                    EntityRouteRegister entityRouteRegister = EntityRouteRegister.findById(Long.parseLong(routeId))
                    if(entityRouteRegister)
                    {
                        userRegister.addToEntityRoute(entityRouteRegister)
                    }
                }
            }

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
        EntityRegister entityRegister =EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        if (userRegister) {
            userRegister.isUpdatable = true
            //userRegister.userName = jsonObject.get("userName").toString()
            userRegister.mobileNumber = jsonObject.get("mobileNumber").toString()
            userRegister.name = jsonObject.get("name").toString()
            userRegister.contactNumber = jsonObject.get("contactNumber").toString()
            userRegister.aadharId = jsonObject.get("aadharId").toString()
            userRegister.email = jsonObject.get("email").toString()
            if(userRegister.getPhoto()!='')
            {
                userRegister.photo = "0"
            }
            else
            {
                userRegister.photo = "0"
            }
            userRegister.nationality = jsonObject.get("nationality").toString()
            userRegister.address = jsonObject.get("address").toString()
            userRegister.referenceRelation = jsonObject.get("referenceRelation").toString()
            userRegister.pincode = jsonObject.get("pinCode").toString()
            userRegister.bankAccount = jsonObject.get("bankAccount").toString()
//            userRegister.permissions = jsonObject.get("permissions").toString()
            userRegister.assignedHolidays = jsonObject.get("assignedHolidays").toString()
            userRegister.specialization = jsonObject.get("specialization").toString()
            userRegister.licenceNumber = jsonObject.get("licenceNumber").toString()
            userRegister.userStatus = jsonObject.get("userStatus").toString()
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
            userRegister.approvedSalary = Double.parseDouble(jsonObject.get("approvedSalary").toString())
            userRegister.designationSalary = Double.parseDouble(jsonObject.get("designationSalary").toString())
//            userRegister.joiningDate = sdf.parse(jsonObject.get("joiningDate").toString())
//            userRegister.dob = sdf.parse(jsonObject.get("dob").toString())
//            userRegister.anniversaryDate = sdf.parse(jsonObject.get("anniversaryDate").toString())
//            userRegister.lastLoginDate = sdf.parse(jsonObject.get("lastLoginDate").toString())
//            userRegister.lastPaidDate = sdf.parse(jsonObject.get("lastPaidDate").toString())

            if(jsonObject.get("joiningDate").toString()!="")
            {
                userRegister.joiningDate = sdf.parse(jsonObject.get("joiningDate").toString())
            }else {
                userRegister.joiningDate = null
            }
            if(jsonObject.get("dob").toString()!="")
            {
                userRegister.dob = sdf.parse(jsonObject.get("dob").toString())
            }else {
                userRegister.dob = null
            }

            if(jsonObject.get("anniversaryDate").toString()!="")
            {
                userRegister.anniversaryDate = sdf.parse(jsonObject.get("anniversaryDate").toString())
            }else {
                userRegister.anniversaryDate = null
            }

            if(jsonObject.get("lastLoginDate").toString()!="")
            {
                userRegister.lastLoginDate = sdf.parse(jsonObject.get("lastLoginDate").toString())
            }else {
                userRegister.lastLoginDate = null
            }

            if(jsonObject.get("lastPaidDate").toString()!="")
            {
                userRegister.lastPaidDate = sdf.parse(jsonObject.get("lastPaidDate").toString())
            }else {
                userRegister.lastPaidDate = null
            }
            userRegister.department = DepartmentMaster.findById(Long.parseLong(jsonObject.get("department").toString()))
            userRegister.role = Role.findById(Long.parseLong(jsonObject.get("role").toString()))
            userRegister.entity = entityRegister
            userRegister.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id.toString()))
            userRegister.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
            userRegister.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())

            if(jsonObject.has("entityRoute"))
            {
                String[] routeIdArr = jsonObject.get("entityRoute")
                for (String routeId : routeIdArr) {
                    EntityRouteRegister entityRouteRegister = EntityRouteRegister.findById(Long.parseLong(routeId))
                    if(entityRouteRegister)
                    {
                        userRegister.addToEntityRoute(entityRouteRegister)
                    }
                }
            }

            userRegister.save(flush: true)
            if (!userRegister.hasErrors()) {
                AuthRegister authRegister = AuthRegister.findByUser(userRegister)
                authRegister.user = userRegister
                authRegister.username = userRegister.userName
//                authRegister.password = new AuthRegisterService().hashPassword(jsonObject.get("password").toString())
                authRegister.save(flush: true)

                return userRegister
            } else {
                throw new BadRequestException()
            }
        } else {
            throw new ResourceNotFoundException()
        }
    }

     def updatePassword(String id, String password) {
         try
         {
             UserRegister userRegister = UserRegister.findById(Long.parseLong(id))
             if (userRegister)
             {
                 AuthRegister authRegister = AuthRegister.findByUser(userRegister)
                 authRegister.isUpdatable = true
                 authRegister.user = userRegister
                 authRegister.username = userRegister.userName
                 authRegister.password = new AuthRegisterService().hashPassword(password)
                 authRegister.save(flush:true)
             }
             else
             {
                 throw new BadRequestException()
             }
         }
         catch (Exception ex)
         {
            println("Service:UserRegisterService, Password Update Failed"+ex)
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

