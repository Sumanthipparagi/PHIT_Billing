package phitb_shipments


import org.springframework.http.HttpStatus

class ErrorController {
	static responseFormats = ['json', 'xml']

    def error404() {
        response.status = HttpStatus.NOT_FOUND.value()
        render status: 404, view: "/notFound"
    }

    def error400() {
        response.status = HttpStatus.BAD_REQUEST.value()
        render status: 400, view: "/clientError"
    }
}
