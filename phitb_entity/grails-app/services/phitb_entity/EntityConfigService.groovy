package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_entity.Exception.ResourceNotFoundException

@Transactional
class EntityConfigService {

    def save(JSONObject jsonObject)
    {
        try
        {
            JSONArray jsonArray = new JSONArray(jsonObject.config)
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get('entity').toString()))
            ArrayList<EntityConfig> entityConfigs = EntityConfig.findAllByEntity(entityRegister)
                if (entityConfigs.size() == 0)
                {
                    for(JSONObject config:jsonArray)
                    {
                        EntityConfig entityConfig = new EntityConfig()
                        entityConfig.purchaseOrder = Boolean.valueOf(config.get('purchaseOrder').toString());
                        entityConfig.config = config.get('name').toString()
                        entityConfig.code = config.get('code').toString()
                        entityConfig.purchaseEntry = Boolean.valueOf(config.get('purchaseEntry').toString());
                        entityConfig.purchaseReturn = Boolean.valueOf(config.get('purchaseReturn').toString());
                        entityConfig.payments = Boolean.valueOf(config.get('payments').toString());
                        entityConfig.saleOrder = Boolean.valueOf(config.get('saleOrder').toString());
                        entityConfig.saleEntry = Boolean.valueOf(config.get('saleEntry').toString());
                        entityConfig.salesReturn = Boolean.valueOf(config.get('saleReturn').toString());
                        entityConfig.recipts = Boolean.valueOf(config.get('recipts').toString());
                        entityConfig.creditJv = Boolean.valueOf(config.get('creditJV').toString());
                        entityConfig.debitJv = Boolean.valueOf(config.get('debitJV').toString());
                        entityConfig.entity = EntityRegister.findById(Long.parseLong(config.get('entityId').toString()))
                        entityConfig.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id.toString()))
                        entityConfig.save(flush: true)
                        println(entityConfig)
                    }
                    return entityConfigs
                }
                else
                {
                    for (EntityConfig e : entityConfigs)
                    {
                        e.isUpdatable = true
                        e.delete()
                    }
                    for(JSONObject config:jsonArray)
                    {
                        EntityConfig entityConfig = new EntityConfig()
                        entityConfig.purchaseOrder = Boolean.valueOf(config.get('purchaseOrder').toString());
                        entityConfig.config = config.get('name').toString()
                        entityConfig.code = config.get('code').toString()
                        entityConfig.purchaseEntry = Boolean.valueOf(config.get('purchaseEntry').toString());
                        entityConfig.purchaseReturn = Boolean.valueOf(config.get('purchaseReturn').toString());
                        entityConfig.payments = Boolean.valueOf(config.get('payments').toString());
                        entityConfig.saleOrder = Boolean.valueOf(config.get('saleOrder').toString());
                        entityConfig.saleEntry = Boolean.valueOf(config.get('saleEntry').toString());
                        entityConfig.salesReturn = Boolean.valueOf(config.get('saleReturn').toString());
                        entityConfig.recipts = Boolean.valueOf(config.get('recipts').toString());
                        entityConfig.creditJv = Boolean.valueOf(config.get('creditJV').toString());
                        entityConfig.debitJv = Boolean.valueOf(config.get('debitJV').toString());
                        entityConfig.entity = EntityRegister.findById(Long.parseLong(config.get('entityId').toString()))
                        entityConfig.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id.toString()))
                        entityConfig.save(flush: true)
                        println(entityConfig)
                    }
                    return entityConfigs
                }
            }
        catch(Exception ex)
        {
            println(ex)
        }

    }


    def getAllByEntityId(String id)
    {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(id.toString()))
        ArrayList<EntityConfig> configs = EntityConfig.findAllByEntity(entityRegister)
        JSONObject jsonObject = new JSONObject()
        for (EntityConfig config : configs) {
            jsonObject.put(config.code ,config)
        }
        return  jsonObject
    }
}
