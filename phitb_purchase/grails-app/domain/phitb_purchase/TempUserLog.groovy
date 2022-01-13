package phitb_purchase

import gorm.logical.delete.LogicalDelete

class TempUserLog implements LogicalDelete<TempUserLog> {

    long userId
    String billId
    String billType
    String serBillId
    String series
    long entityTypeId
    long entityId

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("TempUserLog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("TempUserLog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
