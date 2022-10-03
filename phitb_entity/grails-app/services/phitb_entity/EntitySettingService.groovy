package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import java.text.SimpleDateFormat

@Transactional
class EntitySettingService
{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    def getAll(String limit, String offset, String query)
    {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
        {
            return EntitySetting.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        }
        else
        {
            return EntitySetting.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
        }
    }

    EntitySetting get(String id)
    {
        return EntitySetting.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId)
        {
            case '0':
                orderColumn = "code"
                break;
            case '1':
                orderColumn = "entityId"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def entitySettingCriteria = EntitySetting.createCriteria()
        def entitySettingArrayList = entitySettingCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "")
                {
                    ilike('code', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

        def recordsTotal = entitySettingArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", entitySettingArrayList)
        return jsonObject
    }

    def save(JSONObject jsonObject)
    {
//        EntitySetting entitySetting = new EntitySetting()
//        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
//        entitySetting.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
//        entitySetting.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id.toString()))
//        entitySetting.name = jsonObject.get("name").toString()
//        entitySetting.code = jsonObject.get("code").toString()
//        entitySetting.value = jsonObject.get("value").toString()
//        entitySetting.save(flush: true)
//        if (!entitySetting.hasErrors())
//            return entitySetting
//        else
//            throw new BadRequestException()
        try
        {
            EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
            ArrayList<EntitySetting> entitySetting = EntitySetting.findAllByEntity(entityRegister)
            if (entitySetting.size() == 0)
            {

                if (jsonObject.get("igm").toString() != null)
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "IGM";
                    entitySetting1.name = "Invoice Generation Methods";
                    entitySetting1.value = jsonObject.get("igm").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
//                if (jsonObject.get("dsi").toString())
//                {
//                    EntitySetting entitySetting1 = new EntitySetting()
//                    entitySetting1.code = "DSI";
//                    entitySetting1.name = "Default Sales Invoice Series";
//                    entitySetting1.value = jsonObject.get("dsi").toString();
//                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
//                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
//                    entitySetting1.save(flush: true)
//                }

                if (jsonObject.get("ipg").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "IPG";
                    entitySetting1.name = "Invoice Print Grouping";
                    entitySetting1.value = jsonObject.get("ipg").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ips").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "IPS";
                    entitySetting1.name = "Invoice Print Sorting";
                    entitySetting1.value = jsonObject.get("ips").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("acm").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ACM";
                    entitySetting1.name = "Advance Cheque Mandatory (Cheque is Mandatory in Invoice)";
                    entitySetting1.value = jsonObject.get("acm").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ulub").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ULUB";
                    entitySetting1.name = "Utilize Local or Universal Barcode";
                    entitySetting1.value = jsonObject.get("ulub").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("MUSC").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "MUSC";
                    entitySetting1.name = "Max Utilization of a Single Cheque (For Invoices)";
                    entitySetting1.value = jsonObject.get("MUSC").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("clm").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "CLM";
                    entitySetting1.name = "Credit Limit Management";
                    entitySetting1.value = jsonObject.get("clm").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ALLOW_SAME_BATCH").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ALLOW_SAME_BATCH";
                    entitySetting1.name = "Allow Same Batch to be repeated in a Single Sales Invoice?";
                    entitySetting1.value = jsonObject.get("ALLOW_SAME_BATCH").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
//                if (jsonObject.get("ro").toString())
//                {
//                    EntitySetting entitySetting1 = new EntitySetting()
//                    entitySetting1.code = "RO";
//                    entitySetting1.name = "Round Off the Scheme Quantity?";
//                    entitySetting1.value = jsonObject.get("ro").toString();
//                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
//                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
//                    entitySetting1.save(flush: true)
//                }
//                if (jsonObject.get("essr").toString())
//                {
//                    EntitySetting entitySetting1 = new EntitySetting()
//                    entitySetting1.code = "ESSR";
//                    entitySetting1.name = "Excess/Shortage stocks rounded off to be autoadjusted in Next Invoice?";
//                    entitySetting1.value = jsonObject.get("essr").toString();
//                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
//                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
//                    entitySetting1.save(flush: true)
//
//                }
                if (jsonObject.get("as").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "AS";
                    entitySetting1.name = "How to Apply Scheme?";
                    entitySetting1.value = jsonObject.get("as").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)

                }
                if (jsonObject.get("ron").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "RON";
                    entitySetting1.name = "Round off Total Net Value to ?";
                    entitySetting1.value = jsonObject.get("ron").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ZERO_INVOICE_VALUE").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ZERO_INVOICE_VALUE";
                    entitySetting1.name = "Can the Invoice Value be Zero?";
                    entitySetting1.value = jsonObject.get("ZERO_INVOICE_VALUE").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)

                }
                if (jsonObject.get("transportationCharges").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "transportationCharges";
                    entitySetting1.name = "Apply Transportation Chanrges to Customer? (Paid Amt.)";
                    entitySetting1.value = jsonObject.get("transportationCharges").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
            }
            else
            {
                for (EntitySetting e : entitySetting)
                {
                    e.isUpdatable = true
                    e.delete()
                }
                if (jsonObject.get("igm").toString() != null)
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "IGM";
                    entitySetting1.name = "Invoice Generation Methods";
                    entitySetting1.value = jsonObject.get("igm").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
//                if (jsonObject.get("dsi").toString())
//                {
//                    EntitySetting entitySetting1 = new EntitySetting()
//                    entitySetting1.code = "DSI";
//                    entitySetting1.name = "Default Sales Invoice Series";
//                    entitySetting1.value = jsonObject.get("dsi").toString();
//                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
//                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
//                    entitySetting1.save(flush: true)
//                }
                if (jsonObject.get("ipg").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "IPG";
                    entitySetting1.name = "Invoice Print Grouping";
                    entitySetting1.value = jsonObject.get("ipg").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ips").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "IPS";
                    entitySetting1.name = "Invoice Print Sorting";
                    entitySetting1.value = jsonObject.get("ips").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("acm").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ACM";
                    entitySetting1.name = "Advance Cheque Mandatory (Cheque is Mandatory in Invoice)";
                    entitySetting1.value = jsonObject.get("acm").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ulub").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ULUB";
                    entitySetting1.name = "Utilize Local or Universal Barcode";
                    entitySetting1.value = jsonObject.get("ulub").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("MUSC").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "MUSC";
                    entitySetting1.name = "Max Utilization of a Single Cheque (For Invoices)";
                    entitySetting1.value = jsonObject.get("MUSC").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("clm").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "CLM";
                    entitySetting1.name = "Credit Limit Management";
                    entitySetting1.value = jsonObject.get("clm").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ALLOW_SAME_BATCH").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ALLOW_SAME_BATCH";
                    entitySetting1.name = "Allow Same Batch to be repeated in a Single Sales Invoice?";
                    entitySetting1.value = jsonObject.get("ALLOW_SAME_BATCH").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
//                if (jsonObject.get("ro").toString())
//                {
//                    EntitySetting entitySetting1 = new EntitySetting()
//                    entitySetting1.code = "RO";
//                    entitySetting1.name = "Round Off the Scheme Quantity?";
//                    entitySetting1.value = jsonObject.get("ro").toString();
//                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
//                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
//                    entitySetting1.save(flush: true)
//                }
//                if (jsonObject.get("essr").toString())
//                {
//                    EntitySetting entitySetting1 = new EntitySetting()
//                    entitySetting1.code = "ESSR";
//                    entitySetting1.name = "Excess/Shortage stocks rounded off to be autoadjusted in Next Invoice?";
//                    entitySetting1.value = jsonObject.get("essr").toString();
//                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
//                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
//                    entitySetting1.save(flush: true)
//
//                }

                if (jsonObject.get("as").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "AS";
                    entitySetting1.name = "How to Apply Scheme?";
                    entitySetting1.value = jsonObject.get("as").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)

                }
                if (jsonObject.get("ron").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "RON";
                    entitySetting1.name = "Round off Total Net Value to ?";
                    entitySetting1.value = jsonObject.get("ron").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
                if (jsonObject.get("ZERO_INVOICE_VALUE").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "ZERO_INVOICE_VALUE";
                    entitySetting1.name = "Can the Invoice Value be Zero?";
                    entitySetting1.value = jsonObject.get("ZERO_INVOICE_VALUE").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)

                }
                if (jsonObject.get("transportationCharges").toString())
                {
                    EntitySetting entitySetting1 = new EntitySetting()
                    entitySetting1.code = "transportationCharges";
                    entitySetting1.name = "Apply Transportation Chanrges to Customer? (Paid Amt.)";
                    entitySetting1.value = jsonObject.get("transportationCharges").toString();
                    entitySetting1.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entityId").toString()))
                    entitySetting1.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id as String))
                    entitySetting1.save(flush: true)
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e)
        }
    }

    EntitySetting update(JSONObject jsonObject, String id)
    {
        EntitySetting entitySetting = EntitySetting.findById(Long.parseLong(id))
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
        if (entitySetting)
        {
            entitySetting.isUpdatable = true
            entitySetting.entity = EntityRegister.findById(Long.parseLong(jsonObject.get("entity").toString()))
            entitySetting.entityType = EntityTypeMaster.findById(Long.parseLong(entityRegister.entityType.id.toString()))
            entitySetting.name = jsonObject.get("name").toString()
            entitySetting.code = jsonObject.get("code").toString()
            entitySetting.value = jsonObject.get("value").toString()
            entitySetting.save(flush: true)
            if (!entitySetting.hasErrors())
            {
                return entitySetting
            }
            else
            {
                throw new BadRequestException()
            }
        }
        else
        {
            throw new ResourceNotFoundException()
        }
    }

    void delete(String id)
    {
        if (id)
        {
            EntitySetting entitySetting = EntitySetting.findById(Long.parseLong(id))
            if (entitySetting)
            {
                entitySetting.isUpdatable = true
                entitySetting.delete()
            }
            else
            {
                throw new ResourceNotFoundException()
            }
        }
        else
        {
            throw new BadRequestException()
        }
    }


    def getAllByEntityId(String id)
    {
        EntityRegister entityRegister = EntityRegister.findById(Long.parseLong(id.toString()))
        ArrayList<EntitySetting> entitySetting = EntitySetting.findAllByEntity(entityRegister) as ArrayList<JSONArray>
        JSONObject jsonObject1 = new JSONObject()
        for(EntitySetting entitySetting1: entitySetting)
        {
            jsonObject1.put(entitySetting1.code, entitySetting1.value.toString())
        }
       return  jsonObject1
    }

}
