package phitb_shipments


import grails.rest.*
import grails.converters.*

class StatusController {
	static responseFormats = ['json', 'xml']
    def index() {
        respond status: 200;
    }
}
