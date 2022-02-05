package phitb_sales

import gorm.logical.delete.LogicalDelete

class CreditDebitSettlement implements Serializable, LogicalDelete<CreditDebitSettlement>
{
    long finId
    String date
    long userId
    long customerId
    String status
    long syncStatus
    String remarks
    String financialYear
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser
    Date dateCreated
    Date lastUpdated
    static constraints = {
    }
    static  mapping = {
        remarks sqlType: "longText"
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("CreditDebitSettlement Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CreditDebitSettlement domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}