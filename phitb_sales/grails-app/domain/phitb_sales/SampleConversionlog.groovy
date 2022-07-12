package phitb_sales

class SampleConversionlog {

    long saleableProductId
    String saleableBatch
    long saleableQty
    long sampleProductId
    String sampleBatch
    long sampleQty

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
            System.out.println("SampleConversionlog Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("SampleConversionlog domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
