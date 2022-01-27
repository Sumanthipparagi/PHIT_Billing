package phitb_entity

class SeriesMaster {

    String seriesCode
    String seriesName
    long mode
    long saleId
    long returnId
    long orderId
    long status
    int syncStatus
    long entityTypeId
    long entityId
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
