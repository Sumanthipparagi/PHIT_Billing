package phitb_accounts

import gorm.logical.delete.LogicalDelete

class PaymentCollectionLog implements LogicalDelete<PaymentCollectionLog>
{

    String collectedAmount
    String balance
    String invoiceAmount
    String documentNumber
    long  receiptId
    String instrumentId
    long userId
    long entityId
    long entityTypeId
    String status //ACTIVE,CANCELLED, APPROVED
    Date approvedDate
    String reason

    Date dateCreated
    Date lastUpdated

    long createdUser
    long modifiedUser

    static constraints = {
        approvedDate nullable: true
        reason nullable:true
    }
}
