package phitb_accounts

import gorm.logical.delete.LogicalDelete

class BillPaymentLog implements LogicalDelete<BillPaymentLog> {

    long billId
    String billType
    double amountPaid
    String paymentRecord
    long paymentId
    String transId
    long approvedBy
    String currentFinancialYear
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
        paymentId nullable:true
        transId nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("BillPaymentLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("BillPaymentLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
