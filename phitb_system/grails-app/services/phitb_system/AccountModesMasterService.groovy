package phitb_system

import grails.gorm.transactions.Transactional

@Transactional
class AccountModesMasterService {
    
    def add(String mode) {

        if(mode)
        {
            AccountModesMaster accountModesMaster = new AccountModesMaster()
            accountModesMaster.mode = mode
            accountModesMaster.save(flush:true)
            if(!accountModesMaster.hasErrors())
                return accountModesMaster
        }
        return null
    }
}
