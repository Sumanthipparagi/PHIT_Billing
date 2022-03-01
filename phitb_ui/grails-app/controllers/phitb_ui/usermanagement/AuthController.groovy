package phitb_ui.usermanagement

import org.grails.web.json.JSONObject
import phitb_ui.AuthService
import phitb_ui.EntityService

class AuthController {

    def index()
    {
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
                    session.setMaxInactiveInterval(3600)
                    session.setAttribute("login", true)
                    session.setAttribute("entityId", entity?.get("id"))
                    session.setAttribute("entityName", entity?.get("entityName"))
                    session.setAttribute("userName", username)
                    session.setAttribute("entityAddress1", entity?.get("addressLine1"))
                    session.setAttribute("entityAddress2", entity?.get("addressLine2"))
                    session.setAttribute("entityPinCode", entity?.get("pinCode"))
                    session.setAttribute("entityMobileNumber", entity?.get("mobileNumber"))

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
