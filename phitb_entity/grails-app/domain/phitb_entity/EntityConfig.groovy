package phitb_entity

class EntityConfig {


    String config
    boolean purchaseOrder
    boolean purchaseEntry
    boolean purchaseReturn
    boolean payments
    boolean saleOrder
    boolean saleEntry
    boolean salesReturn
    boolean recipts
    boolean creditJv
    boolean debitJv
    String code
    EntityRegister entity
    EntityTypeMaster entityType

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
            System.out.println("EntityConfig Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntityConfig domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
