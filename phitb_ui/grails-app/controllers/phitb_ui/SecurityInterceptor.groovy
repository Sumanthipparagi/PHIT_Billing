package phitb_ui


class SecurityInterceptor {

    SecurityInterceptor()
    {
        matchAll()
    }

    boolean before() {
        println("Controller: "+ controllerName + ", Action: "+actionName+" "+new Date())
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
