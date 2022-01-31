package phitb_entity

class RuleMaster {

    long dlExpired
    long foodLicenseExpired
    long salesValueLimit
    long creditGraceCheck
    String checkDate
    long scheme
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
            System.out.println("RuleMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RuleMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
