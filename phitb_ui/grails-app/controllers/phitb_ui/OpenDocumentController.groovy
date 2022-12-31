package phitb_ui

import org.grails.web.json.JSONObject

/**
 * This controller enables the public to view any specific document with the help of unique link
 */
class OpenDocumentController {

    def getLink() {
        String entityId = session.getAttribute("entityId")
        String docCode = params.docCode
        String docNo = params.docNo
        String docId = params.docId
        String uniqueLink = new UtilsService().generateUniqueLink(entityId, docCode, docId, docNo)
        if(uniqueLink)
           render(text: uniqueLink)
        else
            response.status = 400
    }

    def decodeLink() {
        String uniqueCode = params.uniqueCode
        JSONObject documentDetails = new UtilsService().decryptUniqueLink(uniqueCode)
        if(documentDetails)
            respond documentDetails, formats:['json']
        else
            response.status = 400
    }
}
