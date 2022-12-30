package phitb_entity

import gorm.logical.delete.LogicalDelete

class SMSLog implements LogicalDelete<SMSLog> {

    EntityRegister entity
    UserRegister sentByUser
    String mobileNumber
    String messageId
    String smsContent
    String deliveryStatus //PENDING, DELIVERED etc
    String docType //SALE_ENTRY, SALE_ORDER, PURCHASE_ENTRY, PURCHASE_ORDER, RECEIPT, PAYMENT, GRN, GTN
    String docId
    String docNo

    Date dateCreated
    Date lastUpdated

    static constraints = {
        deliveryStatus nullable: true
        docType nullable: true
        docId nullable: true
        docNo nullable: true
        messageId nullable: true
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SMSLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SMSLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
