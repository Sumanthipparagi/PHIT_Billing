package phitb_sales

import gorm.logical.delete.LogicalDelete

class SaleBillDetails implements Serializable, LogicalDelete<SaleBillDetails>
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
    String invtype
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    Double adjAmount
    String publicNote
    String privateNote
    String refNo
    Date refDate
    String rep

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
        invtype nullable: true
        totalSqty min: 0D
        totalFqty min: 0D
        totalQty min: 0D
        publicNote nullable: true
        privateNote nullable: true
        refDate nullable: true
        refNo nullable: true
        rep nullable: true
    }

    static mapping = {
        irnDetails sqlType: 'longText'
        publicNote sqlType: 'longText'
        privateNote sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SaleBillDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleBillDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
