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

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
