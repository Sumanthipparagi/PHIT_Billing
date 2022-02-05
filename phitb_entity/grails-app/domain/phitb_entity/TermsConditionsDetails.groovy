package phitb_entity

import gorm.logical.delete.LogicalDelete

class TermsConditionsDetails implements LogicalDelete<TermsConditionsDetails>
{

    long formId
    String termCondition
    long status
    long syncStatus
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser


    Date dateCreated
    Date lastUpdated

    static constraints = {
    }
    static mapping = {
        termCondition sqlType: "longText"
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("TermsConditionsDetails Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("TermsConditionsDetails domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
