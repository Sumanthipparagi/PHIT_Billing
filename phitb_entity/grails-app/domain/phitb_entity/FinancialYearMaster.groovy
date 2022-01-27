package phitb_entity

class FinancialYearMaster {

    String startDate
    String endDate
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

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
