package phitb_system


class SecurityInterceptor {

    SecurityInterceptor()
    {
        matchAll()
    }

    boolean before() {
        println("Controller: "+ controllerName + ", Action: "+actionName)
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
