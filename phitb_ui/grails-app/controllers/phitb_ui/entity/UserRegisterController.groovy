package phitb_ui.entity

import groovy.json.JsonSlurper
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.springframework.web.multipart.MultipartFile
import phitb_ui.Constants
import phitb_ui.EntityService
import phitb_ui.Links
import phitb_ui.SystemService
import phitb_ui.accounts.BankRegisterController
import phitb_ui.facility.CcmController
import phitb_ui.product.DivisionController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

class UserRegisterController
{

    def index()
    {
        try
        {
            ArrayList<String> ccm = new CcmController().show() as ArrayList<String>
            ArrayList<String> entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase('MANAGER'))
                {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase('SALESMAN'))
                {
                    salesmanList.add(it)
                }
            }
            render(view: '/entity/userRegister/userRegister', model: [entity     : entity,
                                                                      statelist  : statelist, countrylist: countrylist,
                                                                      citylist   : citylist, salesmanList: salesmanList,
                                                                      managerList: managerList, zoneList: zoneList])
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def addUser()
    {
        try
        {
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> routeRegister = new RouteController().show() as ArrayList<String>
            ArrayList<String> userList = new UserRegisterController().show() as ArrayList<String>
            ArrayList <String> genderList = new SystemService().getAllGender()
            ArrayList <String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList <String> roles = new RoleController().show() as ArrayList<String>
            ArrayList <String> division  = new DivisionController().show() as ArrayList<String>
            ArrayList <String> account = new AccountRegisterController().getAllAccounts() as ArrayList<String>
            def  department = new EntityService().getAllDepartment() as ArrayList<String>
            Object entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> ccm = new CcmController().show() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER))
                {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
                {
                    salesmanList.add(it)
                }
            }
            render(view: '/entity/userRegister/add-user-register', model: [entity       : entity,
                                                                           statelist    : statelist, countrylist: countrylist,
                                                                           citylist     : citylist, salesmanList: salesmanList,
                                                                           managerList  : managerList,
                                                                           zoneList     : zoneList,
                                                                           routeregister: routeRegister,
                                                                           userregister : userregister,
                                                                           department: department,role:roles,
                                                                           bank:bank,account:account,
                                                                           division:division,gender:genderList])
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def updateUser()
    {
        try
        {
            ArrayList<String> statelist = new StateController().show() as ArrayList<String>
            Object user = new EntityService().getUser(params.id)
            ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
            ArrayList<String> citylist = new CityController().show() as ArrayList<String>
            ArrayList<String> zoneList = new ZoneController().show() as ArrayList<String>
            ArrayList<String> routeRegister = new RouteController().show() as ArrayList<String>
            ArrayList<String> userList = new UserRegisterController().show() as ArrayList<String>
            ArrayList <String> genderList = new SystemService().getAllGender()
            ArrayList <String> bank = new BankRegisterController().show() as ArrayList<String>
            ArrayList <String> roles = new RoleController().show() as ArrayList<String>
            ArrayList <String> division  = new DivisionController().show() as ArrayList<String>
            ArrayList <String> account = new AccountRegisterController().getAllAccounts() as ArrayList<String>
            def  department = new EntityService().getAllDepartment() as ArrayList<String>
            Object entity = new EntityRegisterController().show() as ArrayList<String>
            ArrayList<String> ccm = new CcmController().show() as ArrayList<String>
            ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
            ArrayList<String> managerList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_MANAGER))
                {
                    managerList.add(it)
                }
            }
            ArrayList<String> salesmanList = []
            userregister.each {
                if (it.role.name.toString().equalsIgnoreCase(Constants.ROLE_SALESMAN))
                {
                    salesmanList.add(it)
                }
            }
            render(view: '/entity/userRegister/update-user-register', model: [entity       : entity,
                                                                           statelist    : statelist, countrylist: countrylist,
                                                                           citylist     : citylist, salesmanList: salesmanList,
                                                                           managerList  : managerList,
                                                                           zoneList     : zoneList,
                                                                           routeregister: routeRegister,
                                                                           userregister : userregister,
                                                                           department: department,role:roles,
                                                                           bank:bank,account:account,
                                                                           division:division,gender:genderList,
                                                                           userregisterbyId:user])
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }



    def dataTable()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().showUser(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject responseObject = new JSONObject(apiResponse.readEntity(String.class))
                respond responseObject, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def save()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().saveUser(jsonObject)
            if (apiResponse?.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                redirect(uri: '/user-register')
//                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = apiResponse?.status ?: 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def update()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().putUser(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
//                redirect(uri: "/user-register")
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def delete()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().deleteUser(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject data = new JSONObject()
                data.put("success", "success")
                respond data, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


    def show()
    {
        try
        {
            def apiResponse = new EntityService().getUserRegister()
            if (apiResponse?.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class));
                ArrayList<String> arrayList = new ArrayList<>(jsonArray)
                return arrayList
            }
            else
            {
                return []
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }

    def updatePassword()
    {
        try
        {
            JSONObject jsonObject = new JSONObject(params)
            def apiResponse = new EntityService().updatePassword(jsonObject)
            if (apiResponse.status == 200)
            {
                JSONObject obj = new JSONObject(apiResponse.readEntity(String.class))
                respond obj, formats: ['json'], status: 200
            }
            else
            {
                response.status = 400
            }
        }
        catch (Exception ex)
        {
            System.err.println('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            log.error('Controller :' + controllerName + ', action :' + actionName + ', Ex:' + ex)
            response.status = 400
        }
    }


}
