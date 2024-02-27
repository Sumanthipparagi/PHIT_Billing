package phitb_product

import gorm.logical.delete.LogicalDelete

class ProductMaster implements Serializable, LogicalDelete<ProductMaster> {

    String productName
    String composition
    String company
    String mrp
    String packing
    String rxStatus
    String imageLink
    String urlOfProduct
    String chemicalClass
    String habitForming
    String therapeuticClass
    String actionClass
    String storage
    String countryOfOrigin


    static constraints = {
        chemicalClass nullable: true
        habitForming nullable: true
        therapeuticClass nullable: true
        actionClass nullable: true
        storage nullable: true
         composition nullable: true
         company nullable: true
         mrp nullable: true
         packing nullable: true
         rxStatus nullable: true
         imageLink nullable: true
         urlOfProduct nullable: true
        countryOfOrigin nullable: true
    }
}
