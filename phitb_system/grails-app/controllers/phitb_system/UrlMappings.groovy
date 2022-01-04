package phitb_system

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index", version: "1.0")
        get "/$controller/search(.$format)?"(action:"search")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save", version: "1.0", method:"POST")
        put "/$controller/$id(.$format)?"(action:"update")
        //patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
