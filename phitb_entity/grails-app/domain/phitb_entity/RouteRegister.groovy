package phitb_entity

class RouteRegister {

    String routeName
    long cityId
    long stateId
    long countryId
    long areaManager
    String salesman
    long ccmEnabled
    String daysOfWeek
    String ccmId
    long apprExpense
    String status
    long syncStatus
    long entityTypeId
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
            System.out.println("SeriesMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SeriesMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
