package phitb_ui


class SecurityInterceptor {

    boolean before() {
        println("Controller: "+ controllerName + ", Action: "+actionName)
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
