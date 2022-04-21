package phitb_ui.usermanagement

import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_ui.AuthService
import phitb_ui.EInvoiceService
import phitb_ui.EntityService

import java.text.SimpleDateFormat

class AuthController {

    def index()
    {
        new EInvoiceService().generateSignature()
        render(view: '/usermanagement/auth/index')
    }

    def login()
    {
        String username = params.username
        String password = params.password
        if(username != null && password != null)
        {

            //TODO: Login Log
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getRemoteAddr();
            }

            JSONObject auth = new EntityService().getAuth(username)

            if(auth) {
                byte[] salt = new AuthService().toByteArray(auth.get("password").toString().split("_")[1])
                //hash the password sent from client
                String password1 = new AuthService().toHexString(new AuthService().hash(password.toCharArray(), salt))
                //replace original password with hashed password in client request
                password = password1 + "_" + auth.get("password").toString().split("_")[1]
                if (auth.get("username").toString().equals(username) && auth.get("password").toString().equals(password)) {
                    JSONObject entity = auth.get("user").entity
                    session.setMaxInactiveInterval(3600000)
                    session.setAttribute("login", true)
                    session.setAttribute("userId", auth.get("user")?.id)
                    session.setAttribute("entityId", entity?.get("id"))
                    session.setAttribute("entityName", entity?.get("entityName"))
                    session.setAttribute("userName", username)
                    session.setAttribute("entityAddress1", entity?.get("addressLine1"))
                    session.setAttribute("entityAddress2", entity?.get("addressLine2"))
                    session.setAttribute("entityPinCode", entity?.get("pinCode"))
                    session.setAttribute("entityMobileNumber", entity?.get("mobileNumber"))
                    session.setAttribute("entityTypeId", entity?.get("entityType")?.id)
                    session.setAttribute("entityTypeName", entity?.get("entityType")?.name)
                    session.setAttribute("permittedFeatures", auth?.get("user").role?.permittedFeatures)
                    JSONArray jsonArray = new EntityService().getFinancialYearByEntity(entity?.get("entityType")?.id?.toString())
                    if(jsonArray == null || jsonArray.size()<1)
                    {
                        session.invalidate()
                        redirect(uri: "/")
                    }
                    else
                    {
                        JSONObject jsonObject = jsonArray.last() //TODO: this should be obtained from settings
                        String startYear = jsonObject.get("startDate").toString().split("/")[2]
                        String endYear = jsonObject.get("endDate").toString().split("/")[2]
                        session.setAttribute("financialYear", startYear+"-"+endYear)
                    }

                    redirect(uri: "/dashboard")
                } else {
                    session.setAttribute("loginErrorMessage", "login failed! please check username or password")
                    redirect(uri: "/")
                }
            }
            else
            {
                session.setAttribute("loginErrorMessage", "login failed! please check username or password")
                redirect(uri: "/")
            }
        }
        else
        {
            session.setAttribute("loginErrorMessage", "login failed! please check username or password")
            redirect(uri: "/")
        }
    }

    def logout()
    {
        session.invalidate()
        redirect(uri: "/")
    }
}
