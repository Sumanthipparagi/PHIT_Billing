package phitb_sales

import gorm.logical.delete.LogicalDelete

class SampleConversion implements Serializable, LogicalDelete<SampleConversion>
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
    String refNo
    String publicNote
    String privateNote
    Date refDate
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

    static constraints = {
        invoiceNumber unique: true, nullable: true
        cancelledDate nullable: true
        orderDate nullable: true
        uuid unique: true
        totalSqty min: 0D
        totalFqty min: 0D
        totalQty min: 0D
        publicNote nullable: true
        privateNote nullable: true
        refNo nullable:true
        refDate  nullable:true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("SampleConversion Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SampleConversion domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
