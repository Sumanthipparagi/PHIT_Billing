package phitb_entity

import gorm.logical.delete.LogicalDelete

class Role implements LogicalDelete<Role>, Serializable{

    String name
    String permittedFeatures

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    static mapping = {
        permittedFeatures sqlType: 'mediumText'
    }

    boolean isUpdatable
    static transients = ['isUpdatable']

    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("Role Domain update Prevented " + new Date().toString() + " ,RoleId: " + this.id)
            return false
        }
        else
        {
            System.out.println("Role domain Updated " + new Date().toString() + " ,RoleId: " + this.id)
        }
    }

    @Override
    public String toString()
    {
        super.toString()
        return "Role{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", permittedFeatures='" + permittedFeatures + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", isUpdatable=" + isUpdatable +
                '}';
    }
}
