package phitb_entity

import gorm.logical.delete.LogicalDelete

class EntityIRN implements LogicalDelete<EntityIRN>, Serializable {

    EntityRegister entity
    EntityTypeMaster entityType
    String irnUsername
    String irnPassword
    String irnGSTIN
    boolean forceRefreshAccessToken
    String authToken
    String tokenExpiry
    String sek
    String sessionId
    String appKey
    String aspSecretKey

    boolean active
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        authToken nullable: true
        tokenExpiry nullable: true
        sek nullable: true
        entity unique: true
        irnUsername unique: true
        irnPassword unique: true
        irnGSTIN unique: true
        sessionId nullable: true
        appKey nullable: true
        aspSecretKey nullable: true
    }

    static mapping = {
        authToken sqlType: 'longText'
        aspSecretKey sqlType: 'longText'
        sessionId sqlType: 'longText'
        appKey sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("EntityIRN Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntityIRN domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
