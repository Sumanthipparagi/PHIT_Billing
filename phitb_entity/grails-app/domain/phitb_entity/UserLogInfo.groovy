package phitb_entity

import gorm.logical.delete.LogicalDelete

class UserLogInfo implements LogicalDelete<UserLogInfo>
{

    long userId
    String loginId
    String ipAddress
    String formId
    Date loginTime
    Date logoutTime
    EntityTypeMaster entityType
    EntityRegister entity
    String browserInfo

    Date dateCreated
    Date lastUpdated
    static constraints = {
        logoutTime nullable: true
        formId nullable: true
        browserInfo nullable: true, maxSize: 500
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
