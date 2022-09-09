package phitb_accounts

import gorm.logical.delete.LogicalDelete


class PaymentDetail implements LogicalDelete<PaymentDetail> {

    long finId
    String paymentId
    Date date
    long accountModeId
    long paymentModeId
    String transferFrom
    String paymentTo
    Double amountPaid
    String narration
    String cardNumber
    String paymentDate
    String transId
    String employeeName
    double commission
    double cardAmount
    long totalNotes
    String chequeNumber
    BankRegister bank
    WalletMaster wallet
    String financialYear
    Long approvedBy
    Date approvedDate
    String approvedStatus
    Date cancelledDate
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        cardNumber nullable: true
        wallet nullable: true
        bank nullable: true
        approvedBy nullable: true
        approvedStatus nullable: true
        cancelledDate nullable: true
        approvedDate nullable: true
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
            System.out.println("PaymentDetail Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PaymentDetail domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
