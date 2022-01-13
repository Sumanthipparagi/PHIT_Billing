package phitb_accounts

import gorm.logical.delete.LogicalDelete

class ChequeReturns implements LogicalDelete<ChequeReturns> {

    long finId
    String chequeNumber
    String receiptNumber
    double bankPenaltyCharges
    double officePenaltyCharges
    Date returnDate
    String financialYear
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("ChequeReturns Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ChequeReturns domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
