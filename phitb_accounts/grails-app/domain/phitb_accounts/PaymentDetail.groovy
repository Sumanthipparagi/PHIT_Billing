package phitb_accounts

import gorm.logical.delete.LogicalDelete


class PaymentDetail implements LogicalDelete<PaymentDetail> {

    long finId
    Date date
    long accountModeId
    long paymentModeId
    String transfer_from
    String payment_to
    long amount_paid
    String narration
    String cardNumber
    String payment_date
    String transId
    long employeeName
    double commission
    double cardAmount
    long totalNotes
    String chequeNumber
    BankRegister bank
    WalletMaster wallet
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