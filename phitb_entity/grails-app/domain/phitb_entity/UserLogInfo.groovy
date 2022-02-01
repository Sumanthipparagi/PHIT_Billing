package phitb_entity

class UserLogInfo {

    long userId
    String loginId
    String ipAddress
    String formId
    Date loginTime
    Date logoutTime
    EntityTypeMaster entityType
    EntityRegister entity

    Date dateCreated
    Date lastUpdated
    static constraints = {

    }

    static mapping = {
        formId sqlType: "longText"
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("UserLogInfo Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("UserLogInfo domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
