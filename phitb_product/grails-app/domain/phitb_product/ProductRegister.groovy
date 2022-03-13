package phitb_product

import gorm.logical.delete.LogicalDelete

class ProductRegister implements Serializable, LogicalDelete<ProductRegister> {
    
    String productCode
    String productName
    long manufacturerId
    long mktCompanyId
    String hsnCode
    long rackId
    Division division
    CompositionMaster composition
    ProductCostRange costRange
    ProductTypeMaster productType
    UnitTypeMaster unit
    String unitPacking
    long productMoo
    String perLotQuantity
    double purchaseRate
    double purchaseTradeDiscount
    double purchaseMarginPercent
    double saleRate
    double saleTradeDiscount
    double saleMarginPercent
    double salesmenPercent
    double vipPRate
    double vipSRate
    double mrp
    double ptr
    double restrictedRate
    double nriRate
    double salesmanCommission
    double grossProfitPercentage
    long taxId
    String thresholdLevel
    long orderQuantity
    ProductGroupMaster group
    ProductScheduleMaster schedule
    ProductCategoryMaster category
    String sendMail
    String discountAllowed
    String ccmProduct
    String narration
    String restrictedAssignment
    
    String soundexCode
    long status
    long syncStatus
    long entityTypeId
    long entityId
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    String fieldForceId

    static mapping = {
        narration sqlType: 'longText'
    }
    static constraints = {
        fieldForceId nullable: true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("ProductRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("ProductRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
