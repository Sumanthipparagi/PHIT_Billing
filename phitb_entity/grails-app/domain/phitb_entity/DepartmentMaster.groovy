package phitb_entity

import gorm.logical.delete.LogicalDelete

class DepartmentMaster implements LogicalDelete<DepartmentMaster> {

    String name
    String description
    long entityTypeId
    long entityId
    UserRegister createdUser
    UserRegister modifiedUser

    static constraints = {
        name maxSize: 500
        description maxSize: 500
    }

    Date dateCreated
    Date lastUpdated

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("DepartmentMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DepartmentMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
