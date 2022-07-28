package phitb_sales

class SaleReturnAdjustment {

    SaleReturn saleReturn
    double totalAmount
    double adjAmount
    double balanceBefore
    double currentBalance
    long docId
    String docType //INVS

    long entityId
    long entityTypeId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("SaleReturnAdjustment Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SaleReturnAdjustment domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
