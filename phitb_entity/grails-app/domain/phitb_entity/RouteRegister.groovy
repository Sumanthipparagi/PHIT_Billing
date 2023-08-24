package phitb_entity

import gorm.logical.delete.LogicalDelete

class RouteRegister implements LogicalDelete<RouteRegister>
{

    String routeName
    String routeCode
    String zoneIds
    //long areaManagerId
    long ccmEnabled
    double apprExpense
    String status
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    long createdUserId
    long modifiedUserId


    Date dateCreated
    Date lastUpdated
    static constraints = {
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
