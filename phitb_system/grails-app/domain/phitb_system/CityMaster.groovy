package phitb_system

import gorm.logical.delete.LogicalDelete

class CityMaster implements LogicalDelete<CityMaster> {
    String circleName
    String regionName
    String regionCode
    RegionMaster region
    String divisionName
    String divisionCode
    DivisionMaster division
    String areaName
    String areaCode
    String pincode
    String districtName
    String districtCode
    DistrictMaster district
    String stateName
    StateMaster state
    String latitude
    String logitude

    Date dateCreated
    Date lastUpdated

    static constraints = {
        circleName nullable: true
        regionName nullable: true
        regionCode nullable: true
        region nullable: true
        division nullable: true
        divisionName nullable: true
        divisionCode nullable: true
        areaCode nullable: true
        areaName nullable: true
        pincode nullable: true
        districtName nullable: true
        districtCode nullable: true
        district nullable: true
        stateName nullable: true
        latitude nullable: true
        logitude nullable: true
    }


    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("CityMaster Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("CityMaster domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
