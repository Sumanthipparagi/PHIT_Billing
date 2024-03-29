package phitb_entity

import gorm.logical.delete.LogicalDelete

class UserRegister implements LogicalDelete<UserRegister> {
    String userName
    String name
    String mobileNumber
    String contactNumber
    String aadharId
    long reportTo
    String email
    long genderId
    String photo
    String nationality
    String address
    String userStatus
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
    Role role
    EntityTypeMaster entityType
    EntityRegister entity
    long createdUser
    long modifiedUser

    static hasMany = [route: RouteRegister]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        address maxSize: 500
        pincode maxSize: 10
        photo nullable: true
        bankId nullable:true
        divisionId nullable:true
        licenceNumber nullable:true
        specialization nullable:true
        assignedHolidays nullable:true
        bankAccount nullable:true
        paymentModeId nullable:true
        lastPaidDate nullable: true
        designationSalary nullable: true
        approvedSalary nullable: true
        approvedSalary nullable: true
        department nullable: true
        pincode nullable: true
        userStatus nullable: true
        referenceRelation nullable: true
        permissions nullable:true
        account nullable:true
        anniversaryDate nullable:true
        lastLoginDate nullable:true
        joiningDate nullable:true
        dob nullable:true

    }

    static mapping = {
        photo sqlType: 'longText'
        permissions sqlType: 'longText'
        status nullable:true
        entityType nullable:true
        entity nullable:true
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
