package phitb_accounts

import gorm.logical.delete.LogicalDelete

class ReceiptDetail implements LogicalDelete<ReceiptDetail> {
    
    String receiptId
    Date date
    long paymentModeId
    long accountModeId
    String receivedFrom
    String depositTo
    double amountPaid
    String narration
    String cardNumber
    Date paymentDate
    String transId
    long employeeReceived
    double commission
    long totalNotes
    String chequeNumber
    BankRegister bank
    WalletMaster wallet
    long lockStatus
    long approvedBy
    Date approvedDate
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
        depositTo nullable: true
        bank nullable: true
        cardNumber nullable: true
    }

    static mapping = {
        narration sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("ReceiptDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ReceiptDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
