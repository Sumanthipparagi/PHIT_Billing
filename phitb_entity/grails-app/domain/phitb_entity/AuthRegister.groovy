package phitb_entity

import gorm.logical.delete.LogicalDelete

class AuthRegister implements LogicalDelete<AuthRegister> {

    UserRegister user
    String password
    String username

    static constraints = {
        password maxSize: 1000
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("AuthRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("AuthRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
