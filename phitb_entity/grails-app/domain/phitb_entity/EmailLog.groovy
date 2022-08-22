package phitb_entity

import gorm.logical.delete.LogicalDelete

class EmailLog implements LogicalDelete<EmailLog>, Serializable {

    EntityRegister entity
    String sentTo
    String emailContent
    boolean hasAttachments
    String emailService
    String deliveryStatus
    String docNo
    String docType //SALE ENTRY, PURCHASE ENTRY etc.,

    Date dateCreated
    Date lastUpdated

    static mapping = {
        emailContent sqlType: 'longText'
        deliveryStatus sqlType: 'longText'
    }
    static constraints = {
        docNo nullable: true
        deliveryStatus nullable: true
        docType nullable: true
        emailService inList: ["DEFAULT", "CUSTOM"]
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("EmailLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EmailLog Domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
