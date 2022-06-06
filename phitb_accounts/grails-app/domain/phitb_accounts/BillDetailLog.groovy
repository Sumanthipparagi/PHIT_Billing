package phitb_accounts

import gorm.logical.delete.LogicalDelete

class BillDetailLog implements LogicalDelete<BillDetailLog>
{
    long billId
    String billType
    String transId
    double amountPaid
    String paymentRecord
    long approvedBy
    String currentFinancialYear
    String receiptStatus
    String financialYear
    String receiptId
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long modifiedUser
    long createdUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        receiptStatus nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("ReciptDetailLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ReciptDetailLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
