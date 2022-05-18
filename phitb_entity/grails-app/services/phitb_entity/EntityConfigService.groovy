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
        def de = EntityConfig.findByEntityAndCode(entityRegister,"DE")
        def mad = EntityConfig.findByEntityAndCode(entityRegister,"MAD")
        def dad = EntityConfig.findByEntityAndCode(entityRegister,"DAD")
        def block = EntityConfig.findByEntityAndCode(entityRegister,"BLOCK")
        def auto = EntityConfig.findByEntityAndCode(entityRegister,"AUTO")
        def uploadProof = EntityConfig.findByEntityAndCode(entityRegister,"UPLOAD_PROOF")
        def blockInv = EntityConfig.findByEntityAndCode(entityRegister,"BLOCK_INV")
        def sendMail = EntityConfig.findByEntityAndCode(entityRegister,"SEND_MAIL")
        def cde = EntityConfig.findByEntityAndCode(entityRegister,"CDE")
        def discountBtn = EntityConfig.findByEntityAndCode(entityRegister,"DISCOUNT_BUTTON")
        def reCal = EntityConfig.findByEntityAndCode(entityRegister,"RE_CAL")
        def enableCashDisc = EntityConfig.findByEntityAndCode(entityRegister,"ENABLE_CASH_DISC")
        def withoutQr = EntityConfig.findByEntityAndCode(entityRegister,"WITHOUT_QR")
        def regenNewDoc = EntityConfig.findByEntityAndCode(entityRegister,"REGEN_NEW_DOC")
        def newBatchSelection = EntityConfig.findByEntityAndCode(entityRegister, "NEW_BATCH_CREATION")
        def disableSchQty = EntityConfig.findByEntityAndCode(entityRegister, "DISABLE_SCHEME_QTY")
        def allowManual = EntityConfig.findByEntityAndCode(entityRegister, "ALLOW_MANUAL")
        def prescriptionUpload = EntityConfig.findByEntityAndCode(entityRegister,   "PRESCRIPTION_UPLOAD")
        def disableLR = EntityConfig.findByEntityAndCode(entityRegister,   "DISABLE_LR")
        JSONObject jsonObject1 = new JSONObject()
        jsonObject1.put("DE",de)
        jsonObject1.put("MAD",mad)
        jsonObject1.put("DAD",dad)
        jsonObject1.put("BLOCK",block)
        jsonObject1.put("AUTO",auto)
        jsonObject1.put("UPLOAD_PROOF",uploadProof)
        jsonObject1.put("BLOCK_INV",blockInv)
        jsonObject1.put("SEND_MAIL",sendMail)
        jsonObject1.put("CDE",cde)
        jsonObject1.put("DISCOUNT_BUTTON",discountBtn)
        jsonObject1.put("RE_CAL",reCal)
        jsonObject1.put("ENABLE_CASH_DISC",enableCashDisc)
        jsonObject1.put("WITHOUT_QR",withoutQr)
        jsonObject1.put("REGEN_NEW_DOC",regenNewDoc)
        jsonObject1.put("NEW_BATCH_SELECTION",newBatchSelection)
        jsonObject1.put("DISABLE_SCHEME_QTY",disableSchQty)
        jsonObject1.put("ALLOW_MANUAL",allowManual)
        jsonObject1.put("PRESCRIPTION_UPLOAD",prescriptionUpload)
        return  jsonObject1
    }
}
