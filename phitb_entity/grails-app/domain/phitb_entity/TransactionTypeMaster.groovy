package phitb_entity

class TransactionTypeMaster {

    String transactionName

    static constraints = {
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("TransactionTypeMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("TransactionTypeMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
