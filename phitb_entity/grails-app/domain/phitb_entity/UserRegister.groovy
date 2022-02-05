package phitb_entity

import gorm.logical.delete.LogicalDelete

class UserRegister implements LogicalDelete<UserRegister> {
    String userName
    String mobileNumber
    String contactNumber
    String aadharId
    long reportTo
    String email
    long genderId
    String photo
    String nationality
    String address
    long countryId
    long stateId
    long cityId
    long referredBy
    String referenceRelation
    String pincode
    Date joiningDate
    //loginId
    DepartmentMaster department
    String permissions
    Date dob
    long status
    long syncStatus
    Date anniversaryDate
    Date lastLoginDate
    Double approvedSalary
    Double designationSalary
    Date lastPaidDate
    long paymentModeId
    String bankAccount
    long bankId
    String assignedHolidays
    String specialization
    String licenceNumber
    AccountRegister account
    long zoneId
    long divisionId
    RoleMaster role
    EntityTypeMaster entityType
    EntityRegister entity
    UserRegister createdUser
    UserRegister modifiedUser

    Date dateCreated
    Date lastUpdated

    static constraints = {
        address maxSize: 500
        pincode maxSize: 10
        photo nullable: true
    }

    static mapping = {
        photo sqlType: 'longText'
        permissions sqlType: 'longText'
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("UserRegister Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("UserRegister domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
