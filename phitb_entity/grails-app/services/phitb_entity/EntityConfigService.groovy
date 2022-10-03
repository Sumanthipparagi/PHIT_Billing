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
        def de = EntityConfig.findByEntityAndCode(entityRegister,"DATE_EDITABLE")
        def mad = EntityConfig.findByEntityAndCode(entityRegister,"MODIFY_AFTER_DAYEND")
        def dad = EntityConfig.findByEntityAndCode(entityRegister,"DELETE_AFTER_DAYEND")
        def block = EntityConfig.findByEntityAndCode(entityRegister,"BLOCK_PRINTING")
        def auto = EntityConfig.findByEntityAndCode(entityRegister,"AUTO_ADJUST")
        def uploadProof = EntityConfig.findByEntityAndCode(entityRegister,"UPLOAD_PROOF")
        def blockInv = EntityConfig.findByEntityAndCode(entityRegister,"BLOCK_INV_DL_EXP")
        def sendMail = EntityConfig.findByEntityAndCode(entityRegister,"SEND_MAIL_AFTER_SAVE")
        def cde = EntityConfig.findByEntityAndCode(entityRegister,"CREDIT_DAYS_EDITABLE")
        def discountBtn = EntityConfig.findByEntityAndCode(entityRegister,"DISCOUNT_BUTTON")
        def reCal = EntityConfig.findByEntityAndCode(entityRegister,"RE_CALCULATION_BTN")
        def enableCashDisc = EntityConfig.findByEntityAndCode(entityRegister,"ENABLE_CASH_DISCOUNT")
        def withoutQr = EntityConfig.findByEntityAndCode(entityRegister,"PRINT_WITHOUT_QR")
        def regenNewDoc = EntityConfig.findByEntityAndCode(entityRegister,"REGEN_NEW_DOC")
        def newBatchSelection = EntityConfig.findByEntityAndCode(entityRegister, "NEW_BATCH_CREATION")
        def disableSchQty = EntityConfig.findByEntityAndCode(entityRegister, "DISABLE_SCHEME_QTY_CREDIT_NOTE")
        def allowManual = EntityConfig.findByEntityAndCode(entityRegister, "ALLOW_MANUAL_SELECT_TAX")
        def prescriptionUpload = EntityConfig.findByEntityAndCode(entityRegister,   "PRESCRIPTION_UPLOAD_MANDATORY")
        def disableLR = EntityConfig.findByEntityAndCode(entityRegister,   "DISABLE_LR_NO")
        JSONObject jsonObject1 = new JSONObject()
        jsonObject1.put("DATE_EDITABLE",de)
        jsonObject1.put("MODIFY_AFTER_DAYEND",mad)
        jsonObject1.put("DELETE_AFTER_DAYEND",dad)
        jsonObject1.put("BLOCK_PRINTING",block)
        jsonObject1.put("AUTO_ADJUST",auto)
        jsonObject1.put("UPLOAD_PROOF",uploadProof)
        jsonObject1.put("DISABLE_LR_NO",disableLR)
        jsonObject1.put("BLOCK_INV_DL_EXP",blockInv)
        jsonObject1.put("SEND_MAIL_AFTER_SAVE",sendMail)
        jsonObject1.put("CREDIT_DAYS_EDITABLE",cde)
        jsonObject1.put("DISCOUNT_BUTTON",discountBtn)
        jsonObject1.put("RE_CALCULATION_BTN",reCal)
        jsonObject1.put("ENABLE_CASH_DISCOUNT",enableCashDisc)
        jsonObject1.put("PRINT_WITHOUT_QR",withoutQr)
        jsonObject1.put("REGEN_NEW_DOC",regenNewDoc)
        jsonObject1.put("NEW_BATCH_CREATION",newBatchSelection)
        jsonObject1.put("DISABLE_SCHEME_QTY_CREDIT_NOTE",disableSchQty)
        jsonObject1.put("ALLOW_MANUAL_SELECT_TAX",allowManual)
        jsonObject1.put("PRESCRIPTION_UPLOAD_MANDATORY",prescriptionUpload)
        return  jsonObject1
    }
}
