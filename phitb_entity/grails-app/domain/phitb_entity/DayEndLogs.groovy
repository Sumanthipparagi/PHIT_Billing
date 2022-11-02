package phitb_entity

class DayEndLogs {

    Date endDate
    long entityId
    long entityTypeId
    long userId

    Date dateCreated
    Date lastUpdated

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("DayEndLogs Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("DayEndLogs Domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
