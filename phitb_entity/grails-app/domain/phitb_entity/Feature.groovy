package phitb_entity

import gorm.logical.delete.LogicalDelete

class Feature implements LogicalDelete<Feature>,Serializable{

    String name
    String description
    boolean active

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
            System.out.println("Feature Domain update Prevented " + new Date().toString() + " ,FeatureId: " + this.id)
            return false
        }
        else
        {
            System.out.println("Feature domain Updated " + new Date().toString() + " ,FeatureId: " + this.id)
        }
    }

    @Override
    public String toString()
    {
        super.toString()

        return "Feature{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", isUpdatable=" + isUpdatable +
                '}';
    }
}
