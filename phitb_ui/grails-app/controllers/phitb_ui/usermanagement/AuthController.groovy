package phitb_ui.usermanagement

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AccountsService
import phitb_ui.AuthService
import phitb_ui.EInvoiceService
import phitb_ui.EntityService
import phitb_ui.ProductService
import phitb_ui.SystemService
import phitb_ui.UtilsService
import phitb_ui.accounts.BankRegisterController
import phitb_ui.entity.AccountRegisterController
import phitb_ui.entity.EntityRegisterController
import phitb_ui.entity.RoleController
import phitb_ui.entity.UserRegisterController
import phitb_ui.product.DivisionController
import phitb_ui.system.CityController
import phitb_ui.system.CountryController
import phitb_ui.system.StateController
import phitb_ui.system.ZoneController

import java.text.SimpleDateFormat

class AuthController {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

    def index() {
        if(session.getAttribute("login"))
        {
            redirect(uri: "/dashboard")
        }
        else
            render(view: '/usermanagement/auth/index')
    }

    def login() {
        String username = params.username
        String password = params.password
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        if (username != null && password != null) {

            //TODO: Login Log
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            String browserInfo = request.getHeader("User-Agent")

            JSONObject auth = new EntityService().getAuth(username)

            if (auth) {
                byte[] salt = new AuthService().toByteArray(auth.get("password").toString().split("_")[1])
                //hash the password sent from client
                String password1 = new AuthService().toHexString(new AuthService().hash(password.toCharArray(), salt))
                //replace original password with hashed password in client request
                password = password1 + "_" + auth.get("password").toString().split("_")[1]
                if (auth.get("username").toString().equals(username) && auth.get("password").toString().equals(password)) {
                    JSONObject entity = auth.get("user").entity
                    session.setMaxInactiveInterval(3600000)

                    try {
                        JSONObject loginInfo = new JSONObject()
                        loginInfo.put("userId", auth.get("user")?.id)
                        loginInfo.put("ipAddress", ip)
                        loginInfo.put("formId", "")
                        loginInfo.put("browserInfo", browserInfo)
                        loginInfo.put("loginId", username)
                        loginInfo.put("entityType", entity?.get("entityType")?.id)
                        loginInfo.put("entity", entity?.get("id"))

                        loginInfo.put("loginTime", sdf1.format(new Date()))
                        loginInfo = new EntityService().saveUserLoginInfo(loginInfo)
                        session.setAttribute("loginInfoId", loginInfo.get("id"))
                    }
                    catch (Exception ex) {
                        ex.printStackTrace()
                    }

                    session.setAttribute("login", true)
                    session.setAttribute("userId", auth.get("user")?.id)
                    session.setAttribute("entityId", entity?.get("id"))
                    session.setAttribute("entityName", entity?.get("entityName"))
                    session.setAttribute("userName", username)
                    session.setAttribute("stateId", entity?.get("stateId"))
                    session.setAttribute("entityAddress1", entity?.get("addressLine1"))
                    session.setAttribute("entityAddress2", entity?.get("addressLine2"))
                    session.setAttribute("entityPinCode", entity?.get("pinCode"))
                    session.setAttribute("entityMobileNumber", entity?.get("mobileNumber"))
                    session.setAttribute("entityTypeId", entity?.get("entityType")?.id)
                    session.setAttribute("entityTypeName", entity?.get("entityType")?.name)
                    session.setAttribute("role", auth?.get("user")?.role?.name?.toString())
                    String permittedFeatures = auth?.get("user")?.role?.permittedFeatures
                    session.setAttribute("permittedFeatures", permittedFeatures)
                    session.setAttribute("features", new EntityService().getFeatureList(permittedFeatures))
                    JSONArray jsonArray = new EntityService().getFinancialYearByEntity(entity?.id?.toString())
                    if (jsonArray == null || jsonArray.size() < 1) {
                        try {
                            String loginInfoId = session.getAttribute("loginInfoId")
                            JSONObject loginInfo = new EntityService().getUserLoginInfo(loginInfoId)
                            loginInfo.put("logoutTime", sdf1.format(new Date()))
                            new EntityService().updateUserLoginInfo(loginInfo)
                        }
                        catch (Exception ex) {
                            ex.printStackTrace()
                        }
                        println("Financial year not available")
                        session.invalidate()
                        redirect(uri: "/")
                    } else {
                        Date currentDate = new Date()
                        JSONObject jsonObject = jsonArray.last() as JSONObject
                        Date startDate = sdf.parse(jsonObject.get("startDate").toString())
                        Date endDate = sdf.parse(jsonObject.get("endDate").toString())
                        session.setAttribute("financialYear", startDate.toCalendar().get(Calendar.YEAR) + "-" + endDate.toCalendar().get(Calendar.YEAR))
                        session.setAttribute("startDate", startDate.format("dd/MM/yyyy"))
                        session.setAttribute("endDate", endDate.format("dd/MM/yyyy"))
                        boolean financialYearValid = new UtilsService().isDateWithinRange(currentDate, startDate, endDate)
                        session.setAttribute("financialYearValid", financialYearValid)

                        session.setAttribute("menuDark", false)
                        session.setAttribute("theme", "theme-dark")

                        redirect(uri: "/dashboard")
                    }
                } else {
                    session.setAttribute("loginErrorMessage", "login failed! please check username or password")
                    redirect(uri: "/")
                }
            } else {
                session.setAttribute("loginErrorMessage", "login failed! please check username or password")
                redirect(uri: "/")
            }
        } else {
            session.setAttribute("loginErrorMessage", "login failed! please check username or password")
            redirect(uri: "/")
        }
    }

    def logout() {

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            String loginInfoId = session.getAttribute("loginInfoId")
            JSONObject loginInfo = new EntityService().getUserLoginInfo(loginInfoId)
            loginInfo.put("logoutTime", sdf1.format(new Date()))
            new EntityService().updateUserLoginInfo(loginInfo)
        }
        catch (Exception ex) {
            ex.printStackTrace()
        }
        session.invalidate()
        redirect(uri: "/")
    }

    def updateUser() {
        try {
            if (params.id != "") {
                def user = new EntityService().getUser(params.id)
                ArrayList<String> statelist = new StateController().show() as ArrayList<String>
                ArrayList<String> countrylist = new CountryController().show() as ArrayList<String>
                ArrayList<String> citylist = new CityController().show() as ArrayList<String>
                ArrayList<String> userList = new UserRegisterController().show() as ArrayList<String>
                ArrayList<String> genderList = new SystemService().getAllGender()
                def city = new SystemService().getCityById(user.cityId.toString())
                ArrayList<String> bank = new AccountsService().getBankRegisterByEntity(session.getAttribute('entityId').toString()) as ArrayList<String>
                ArrayList<String> roles = new RoleController().show() as ArrayList<String>
                //ArrayList<String> userregister = new UserRegisterController().show() as ArrayList<String>
                ArrayList<String> division = new ProductService().getDivisionsByEntityId(session.getAttribute('entityId').toString()) as ArrayList<String>
                ArrayList<String> account = new AccountRegisterController().getAllAccounts() as ArrayList<String>
                def department = new EntityService().getDeparmentByEntityId(session.getAttribute('entityId').toString())
                Object entity = new EntityRegisterController().show() as ArrayList<String>
                JSONArray routes = new EntityService().getRouteByEntity(session.getAttribute("entityId").toString())
                render(view: '/usermanagement/auth/updateUser', model: [user       : user, statelist: statelist,
                                                                        routes     : routes,
                                                                        countrylist: countrylist, citylist: citylist,
                                                                        userList   : userList,
                                                                        genderList : genderList, department: department,
                                                                        bank       : bank, roles: roles, account: account,
                                                                        //userregister: userregister,
                                                                        division   : division, entity: entity, city: city])
            } else {
                redirect(uri: '/')
            }
        }
        catch (Exception ex) {
            log.error(controllerName + ":" + ex)
            println(controllerName + ":" + ex)
        }
    }
}
