package phitb_sales

import gorm.logical.delete.LogicalDelete

class TempUserLog implements Serializable, LogicalDelete<TempUserLog>
{

    long userId
    String billId
    String billType
    String serBillId
    long entityTypeId
    long entityId

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
