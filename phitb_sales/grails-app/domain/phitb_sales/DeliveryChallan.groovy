package phitb_sales

import gorm.logical.delete.LogicalDelete

class DeliveryChallan implements Serializable, LogicalDelete<DeliveryChallan>
{

    String invoiceNumber
    long finId
    long serBillId
    long seriesId
    long paymentStatus
    long accountModeId
    long priorityId
    Date entryDate
    long customerId
    long customerNumber
    long salesmanId
    long salesmanComm
    Date orderDate
    String refOrderId
    Date dueDate
    Date dispatchDate
    long deliveryManId
    double totalSqty
    double totalFqty
    double totalItems
    double totalQty
    double totalDiscount
    double totalAmount
    double invoiceTotal
    double totalGst
    long userId
    double balance
    double grossAmount
    double taxable
    double cashDiscount
    double exempted
    double totalCgst
    double totalSgst
    double totalIgst
    String gstStatus
    String billStatus
    String lockStatus
    String syncStatus
    double creditadjAmount
    String creditIds
    String referralDoctor
    String message
    String financialYear
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    Double adjAmount

    Date cancelledDate

    Date dateCreated
    Date lastUpdated

    String uuid

    String irnDetails


    static constraints = {
        invoiceNumber unique: true, nullable: true
        irnDetails nullable: true
        cancelledDate nullable: true
        orderDate nullable: true
        uuid unique: true
        totalSqty min: 0D, scale: 2
        totalFqty min: 0D, scale: 2
        totalQty min: 0D, scale: 2
        totalItems scale: 2
        totalDiscount scale: 2
        totalAmount scale: 2
        invoiceTotal scale: 2
        totalGst scale: 2
        creditadjAmount scale: 2
    }

    static mapping = {
        irnDetails sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("DeliveryChallan Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DeliveryChallan domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
