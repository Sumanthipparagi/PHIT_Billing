package phitb_entity

class CustomerGroupRegister {

    String customerGroupName
    String shortName
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
            System.out.println("CustomerGroupRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CustomerGroupRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
