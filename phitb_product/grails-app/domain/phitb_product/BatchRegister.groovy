package phitb_product

import gorm.logical.delete.LogicalDelete

class BatchRegister implements Serializable, LogicalDelete<BatchRegister> {

    ProductRegister product
    String batchNumber
    Date manfDate
    Date expiryDate
    double purchaseRate
    double saleRate
    double ptr
    double mrp
    long qty
    long box
    String caseWt
    ProductCategoryMaster productCat

    long status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        productCat nullable: true
    }

    static mapping = {
        batchNumber sqlType: 'longText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("BatchRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("BatchRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
