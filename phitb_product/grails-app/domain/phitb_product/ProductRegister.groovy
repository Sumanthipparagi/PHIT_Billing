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
    String saleType
    
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
    String barCode

    static mapping = {
        narration sqlType: 'longText'
    }
    static constraints = {
        fieldForceId nullable: true
        composition nullable: true
        costRange nullable: true
        productType nullable: true
        unit nullable: true
        group nullable: true
        schedule nullable: true
        category nullable: true
        division nullable: true
        saleType nullable: true
        narration nullable: true

        purchaseRate scale:2
        purchaseTradeDiscount scale:2
        purchaseMarginPercent scale:2
        saleRate scale:2
        saleTradeDiscount scale:2
        saleMarginPercent scale:2
        salesmenPercent scale:2
        vipPRate scale:2
        vipSRate scale:2
        mrp scale:2
        ptr scale:2
        restrictedRate scale:2
        nriRate scale:2
        salesmanCommission scale:2
        grossProfitPercentage scale:2
        barCode nullable: true
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
