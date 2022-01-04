package phitb_system

class AccountModesMasterController {
	static responseFormats = ['json', 'xml']
	
    def index() {
        String limit = params.limit
        String offset = params.offset
        ArrayList<AccountModesMaster> accountModesMasters = AccountModesMaster.findAll([sort: 'id', max: limit, offset: offset, order: 'desc'])
        respond accountModesMasters
    }

    def show()
    {
        String id = params.id
        if(id)
        {
            AccountModesMaster accountModesMaster = AccountModesMaster.findById(Long.parseLong(id))
            if(accountModesMaster)
                respond accountModesMaster
            else
                response.status = 400
        }
        else
        {
            response.status = 400
        }
    }

    def save()
    {
        String mode = params.mode
        if(mode)
        {
            AccountModesMaster accountModesMaster = new AccountModesMaster()
            accountModesMaster.mode = mode
            accountModesMaster.save(flush:true)
            if(!accountModesMaster.hasErrors())
                respond accountModesMaster
            else
                response.status = 400
        }
        else
        {
            response.status = 400
        }
    }

    def update()
    {
        String id = params.id
        String mode = params.mode
        if(mode && id)
        {
            AccountModesMaster accountModesMaster = AccountModesMaster.findById(Long.parseLong(id))
            if(accountModesMaster) {
                accountModesMaster.mode = mode
                accountModesMaster.save(flush: true)
                if (!accountModesMaster.hasErrors())
                    respond accountModesMaster
                else
                    response.status = 400
            }
            else
                response.status = 400
        }
        else
        {
            response.status = 400
        }
    }

    def search()
    {
        String query = params.query
        String limit = params.limit
        String offset = params.offset
        ArrayList<AccountModesMaster> accountModesMasters = AccountModesMaster.findAllByModeIlike("%"+query+"%",[sort: 'id', max: limit, offset: offset, order: 'desc'])
        respond accountModesMasters
    }
}
