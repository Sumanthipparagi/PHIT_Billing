package phitb_sales

import gorm.logical.delete.LogicalDelete

class CreditDebitDetails implements Serializable, LogicalDelete<CreditDebitDetails>
{

    long cId
    long debitId
    long debitSeries
    String debitFinancialYear
    long crdbAdjusted
    long creditId
    long creditSeries
    String creditFinancialYear
    String financialYear
    long status
    long syncStatus
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
            System.out.println("CreditDebitDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CreditDebitDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
