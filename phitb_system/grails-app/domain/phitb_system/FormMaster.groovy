package phitb_system

import gorm.logical.delete.LogicalDelete

class FormMaster implements LogicalDelete<FormMaster> {

    String formName
    String formButtonName
    String configAllowed
    long entityType
    long entityId
    long entityTypeId
    Date dateCreated
    Date lastUpdated
    long createdUser
    long modifiedUser

    static constraints = {
        formName maxSize: 100
        formButtonName maxSize: 100
        configAllowed maxSize: 50
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("FormMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("FormMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
