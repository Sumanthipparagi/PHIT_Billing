package phitb_entity


import grails.rest.*
import grails.converters.*

class StatusController {
	static responseFormats = ['json', 'xml']

    def index() {
        respond status: 200;
    }
}
