package phitb_ui.accounts

import phitb_ui.EntityService

class CreditJvController {

    def index() {
        String entityId = session.getAttribute("entityId").toString()
        def accounts = new EntityService().getAllAccountByEntity(entityId)
        render(view: "/accounts/creditJV/index", model: [accounts:accounts])
    }
}
