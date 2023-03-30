package phitb_entity

import gorm.logical.delete.LogicalDelete

class RouteRegister implements LogicalDelete<RouteRegister>
{

    String routeName
    long cityId
    long stateId
    long countryId
    UserRegister areaManager
    UserRegister salesman
    long ccmEnabled
    String daysOfWeek
    String ccmId
    double apprExpense
    String status
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser


    Date dateCreated
    Date lastUpdated
    static constraints = {
        salesman nullable: true
        areaManager nullable: true
        apprExpense scale:2
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("RouteRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("RouteRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
