package phitb_ui

import java.text.DateFormat
import java.text.SimpleDateFormat


class SecurityInterceptor {

    SecurityInterceptor()
    {
        matchAll()
    }

    boolean before() {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Or whatever IST is supposed to be

        println("Controller: "+ controllerName + ", Action: "+actionName+" "+formatter.format(new Date()))
        boolean login = session.getAttribute('login')

        if (controllerName == null)
        {
            //web unuthenticated
            true
        }
        else if (controllerName == "auth")
        {
            true
        }
        else
        {
            if(login)
            {
                true
            }
            else
            {
                false
                redirect(uri: '/')
            }
        }
    }

    boolean after() {
        //security features
      /*  if(session.hasProperty("menuDark") && session.hasProperty("theme")) {
            boolean menuDark = session.getAttribute("menuDark")
            String theme = session.getAttribute("theme")
            String themeExpiryDate = "Sun, 31 Dec 2119 07:28:00 GMT"
            response.setHeader("SET-COOKIE", "theme="+theme+"; Expires="+themeExpiryDate+";secure;HttpOnly;SameSite=None;")
            response.setHeader("SET-COOKIE", "menu-dark="+menuDark+"; Expires="+themeExpiryDate+";secure;HttpOnly;SameSite=None;")
        }*/

        response.setHeader("X-Frame-Options", "SAMEORIGIN")
        response.setHeader("Cache-Control", "must-revalidate")
        response.setHeader("SET-COOKIE", "JSESSIONID=" + request.getSession().id + ";secure;HttpOnly;SameSite=None;")
        response.setHeader("X-Content-Type-Options", "nosniff")
        response.setHeader("X-XSS-Protection", "1; mode=block")

        true
    }

    void afterView() {
        // no-op
    }
}
