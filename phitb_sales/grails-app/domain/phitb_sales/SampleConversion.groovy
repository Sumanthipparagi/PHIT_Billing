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
    double adjAmount
    Date cancelledDate
    Date dateCreated
    Date lastUpdated
    String uuid

    static constraints = {
        invoiceNumber unique: true, nullable: true
        cancelledDate nullable: true
        orderDate nullable: true
        uuid unique: true
        totalSqty min: 0D, scale:2
        totalFqty min: 0D, scale:2
        totalQty min: 0D, scale:2
        publicNote nullable: true
        privateNote nullable: true
        refNo nullable:true
        refDate  nullable:true
        totalItems scale:2
        totalDiscount scale:2
        totalAmount scale:2
        invoiceTotal scale:2
        totalGst scale:2
        balance scale:2
        grossAmount scale:2
        taxable scale:2
        cashDiscount scale:2
        exempted scale:2
        totalCgst scale:2
        totalSgst scale:2
        totalIgst scale:2
        creditadjAmount scale:2
        adjAmount scale:2
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
