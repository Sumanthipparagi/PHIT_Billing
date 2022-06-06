package phitb_entity

import gorm.logical.delete.LogicalDelete

class EntityRegister implements LogicalDelete<EntityRegister>
{
    String entityName
    EntityTypeMaster entityType
    long affiliateId
    String addressLine1
    String addressLine2
    long countryId
    long stateId
    long cityId
    String pinCode
    String phoneNumber
    String mobileNumber
    String email
    String contactName
    long priorityId
    String pan
    String gstn
    String usdNumber
    String corpId
    String drugLicence1
    String drugLicence2
    String drugLicenceValidity
    String foodLicenceValidity
    String foodLicence1
    double salesBalanceLimit
    long noOfCrDays
    long noOfGraceDays
    long calculateOn
    long bankId
    String accountNo
    String upiId
    double openingBalance
    double currentBalance
    double discount
    double bankCommision
    long transportTypeId
    double defaultCharge
    long careTaker
    String contact
    String terms
    long salesman
    long manager
    double salesmanCommission
    long status
    long syncStatus
    long routeId
    String accountId
    Long hqAreaId
    String aadharId
    String companyCode
    String faxNumber
    String repName
    String repPhoneNumber
    String password
    long zoneId
    String contactDob
    long fieldForceId
    long parentEntity
    long parentEntityType
    long createdUser
    long modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        fieldForceId nullable: true
        accountId nullable: true
        aadharId nullable: true
        companyCode nullable: true
        faxNumber nullable: true
        repName nullable: true
        repPhoneNumber nullable: true
        password nullable: true
        contact nullable: true
        foodLicence1 nullable: true
        manager nullable:true
        salesman nullable:true
        hqAreaId nullable:true
    }

    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("EntityRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EntityRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
