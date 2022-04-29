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
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    Double adjAmount
    Date dateCreated
    Date lastUpdated

    String irnDetails
    static constraints = {
        invoiceNumber unique: true, nullable: true
        irnDetails nullable: true
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
            System.out.println("SaleBillDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleBillDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
