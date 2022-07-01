package phitb_inventory


import grails.rest.*
import grails.converters.*

class StatusController {
	static responseFormats = ['json', 'xml']

    def index() {
        respond status: 200;
    }}
