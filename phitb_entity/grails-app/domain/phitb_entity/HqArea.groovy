package phitb_entity

class HqArea {
    String hqName
    String cityId
    static constraints = {
        cityId nullable: true
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {
        if (!this.isUpdatable)
        {
            System.out.println("HqArea Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("HqArea domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }

}
