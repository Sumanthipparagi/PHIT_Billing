package phitb_sales

import gorm.logical.delete.LogicalDelete

class SalesOrderEntry implements Serializable, LogicalDelete<SalesOrderEntry>
{
    String orderNumber
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
    Date dueDate
    long totalEstimate
    double totalSqty
    double totalFqty
    double totalGst
    double totalCgst
    double totalSgst
    double totalIgst
    double totalAmount
    double totalDiscount
    Date cancelledDate
    String billStatus
    long lockStatus
    long syncStatus
    String orderMechanism
    String purchaseQuotationId
    String confirmationStatus
    String financialYear
    String invoiceNumber

    String uuid

    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    static constraints = {
        orderNumber nullable: true
        invoiceNumber nullable: true, unique: true
        cancelledDate nullable:true
        uuid unique: true
        dueDate nullable: true
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
