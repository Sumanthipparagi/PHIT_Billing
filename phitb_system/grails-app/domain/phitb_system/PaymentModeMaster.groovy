package phitb_system

import gorm.logical.delete.LogicalDelete

class PaymentModeMaster implements LogicalDelete<PaymentModeMaster> {

    String name
    AccountModeMaster accountMode
    long entityId
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name maxSize: 50
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("PaymentModeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("PaymentModeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
