package phitb_shipments


class SecurityInterceptor {

    SecurityInterceptor()
    {
        matchAll()
    }

    boolean before() {
        println("Controller: "+ controllerName + ", Action: "+actionName+" "+new Date())
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
