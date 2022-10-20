package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException

@Transactional
class EntityDomainTypeService {

    def save(JSONObject jsonObject)
    {
        try
        {
           if(jsonObject.get('id').toString()!=''){
               EntityDomainType entityDomainType = EntityDomainType.findById(Long.parseLong(jsonObject.get('id').toString()))
               if(entityDomainType){
                   entityDomainType.isUpdatable = true
                   entityDomainType.domainType = jsonObject.get('domainType').toString()
                   entityDomainType.save(flush:true)
                   if (!entityDomainType.hasErrors())
                   {
                       return entityDomainType
                   }
               }else{
                   entityDomainType = new EntityDomainType()
                   entityDomainType.domainType = jsonObject.get('domainType').toString()
                   entityDomainType.save(flush:true)
                   if (!entityDomainType.hasErrors())
                   {
                       return entityDomainType
                   }
               }
           }
        }
        catch (Exception e)
        {
            System.out.println(e)
        }
    }

    def getAll()
    {
        ArrayList<EntityDomainType> entityDomainTypes = EntityDomainType.findAll();
        JSONArray jsonArray = new JSONArray(entityDomainTypes)
        return jsonArray
    }
}
