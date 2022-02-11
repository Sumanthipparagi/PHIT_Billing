package phitb_entity

import gorm.logical.delete.LogicalDelete

class TaxRegister implements LogicalDelete<TaxRegister>
{
    String taxName
    Double taxValue
    String salesTaxType
    Double salesSgst
    Double salesCgst
    String purchaseTaxType
    Double purchaseSgst
    Double purchaseCgst
    Double salesIgst
    Double purchaseIgst
    String gstOnMrpSales
    String gstOnSchemeValueSales
    String gstOnMrpPur
    String gstOnSchemeValuePur
    String gstDiscountSales
    String gstDiscountPur
    String saleStatus
    String purStatus
    String taxDescription
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser


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
            System.out.println("TaxRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("TaxRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
