package phitb_entity

class RoleFormMapping {
    long roleId
    long formIds
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    static mapping = {
        formIds sqlType: "longText"
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("RoleFormMapping Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RoleFormMapping domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
