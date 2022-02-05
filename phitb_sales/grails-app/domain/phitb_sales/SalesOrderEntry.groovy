package phitb_sales

import gorm.logical.delete.LogicalDelete

class SalesOrderEntry implements Serializable, LogicalDelete<SalesOrderEntry>
{
    long finId
    long serBillId
    Date entryDate
    long priorityId
    long accountModeId
    String refNumber
    long seriesId
    Date refDate
    long customerId
    long transportTypeId
    long salesmanId
    Date orderValidity
    String orderId
    long totalEstimate
    double totalGst
    String billStatus
    long lockStatus
    long syncStatus
    String orderMechanism
    String purchaseQuotationId
    String confirmationStatus
    String financialYear
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    static constraints = {
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("SalesOrderEntry Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SalesOrderEntry domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}