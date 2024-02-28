package phitb_product

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject

@Transactional
class AlternateProductService {

    def getAllProducts()
    {
        try {
            def result =  ProductMaster.findAll()
            return result
        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }

    def getAllCompany()
    {
        try {
            def result =  CompanyMaster.findAll()
            return result
        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }

    def getAllComposition()
    {
        try {
            def result =  MasterComposition.findAll()
            return result
        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }

    JSONObject getProductByCompositionId(JSONObject paramsJsonObject, String start, String length)
    {
        try {

            String searchTerm = paramsJsonObject.get("search[value]")
            String composition = paramsJsonObject.get("compositionId")

            Integer offset = start ? Integer.parseInt(start.toString()) : 0
            Integer max = length ? Integer.parseInt(length.toString()) : 100
            def masterCompositionCriteria = ProductMaster.createCriteria()
            def compositionArrayList = masterCompositionCriteria.list(max: max, offset: offset) {
                eq('composition', composition)  // It works as findAllB
                or {
                    if (searchTerm != "") {
                        def decodedSearchTerm = URLDecoder.decode(searchTerm, "UTF-8")
                        def normalizedSearchTerm = decodedSearchTerm.trim().replaceAll("\\s+", " ")
                        ilike('productName', '%' + normalizedSearchTerm + '%')
                    }
                }
            }

            JSONObject results = new JSONObject()
            results.put("productList", compositionArrayList)


            return results

        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }

    def getProductByCompanyId(String companyId)
    {
        try {
            def result = ProductMaster.findAllByCompany(companyId)
            return result
        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }

    def getCompositionListByProductId(String productId)
    {
        try {
            def result = ProductMaster.findById(Long.parseLong(productId))
            def composition = result.composition

            def products = ProductMaster.findAllByComposition(Long.parseLong(composition))
            return products

        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }
}
