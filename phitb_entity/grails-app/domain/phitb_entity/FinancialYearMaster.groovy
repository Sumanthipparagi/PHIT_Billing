package phitb_entity

import gorm.logical.delete.LogicalDelete

class FinancialYearMaster implements LogicalDelete<FinancialYearMaster>
{

    String startDate
    String endDate
    long status
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser

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
            System.out.println("FinancialYearMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("FinancialYearMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
