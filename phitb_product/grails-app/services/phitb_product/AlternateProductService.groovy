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
            def recordsTotal = compositionArrayList.totalCount
            results.put("draw", paramsJsonObject.draw)
            results.put("recordsTotal", recordsTotal)
            results.put("recordsFiltered", recordsTotal)
            results.put("productList", compositionArrayList)


            return results

        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }

    JSONObject getProductByCompanyId(JSONObject paramsJsonObject, String start, String length)
    {
        try {

            String searchTerm = paramsJsonObject.get("search[value]")
            String company = paramsJsonObject.get("companyId")

            Integer offset = start ? Integer.parseInt(start.toString()) : 0
            Integer max = length ? Integer.parseInt(length.toString()) : 100
            def companyCriteria = ProductMaster.createCriteria()
            def companyArrayList = companyCriteria.list(max: max, offset: offset) {
                eq('company', company)  // It works as findAllBy
                or {
                    if (searchTerm != "") {
                        def decodedSearchTerm = URLDecoder.decode(searchTerm, "UTF-8")
                        def normalizedSearchTerm = decodedSearchTerm.trim().replaceAll("\\s+", " ")
                        ilike('productName', '%' + normalizedSearchTerm + '%')
                    }
                }
            }

            JSONObject results = new JSONObject()
            def recordsTotal = companyArrayList.totalCount
            results.put("draw", paramsJsonObject.draw)
            results.put("recordsTotal", recordsTotal)
            results.put("recordsFiltered", recordsTotal)
            results.put("productList", companyArrayList)

            return results

        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }

    JSONObject getCompositionListByProductId(JSONObject paramsJsonObject, String start, String length)
    {
        try {
            JSONObject jsonObject =  new JSONObject()
            String product = paramsJsonObject.get("productId")

            def result = ProductMaster.findById(Long.parseLong(product))
            String compositionId = result.composition

            jsonObject.put("productList", result)

            String searchTerm = paramsJsonObject.get("search[value]")

            Integer offset = start ? Integer.parseInt(start.toString()) : 0
            Integer max = length ? Integer.parseInt(length.toString()) : 100
            def masterCompositionCriteria = ProductMaster.createCriteria()
            def compositionArrayList = masterCompositionCriteria.list(max: max, offset: offset) {
                eq('composition', compositionId)  // It works as findAllB
                or {
                    if (searchTerm != "") {
                        def decodedSearchTerm = URLDecoder.decode(searchTerm, "UTF-8")
                        def normalizedSearchTerm = decodedSearchTerm.trim().replaceAll("\\s+", " ")
                        ilike('productName', '%' + normalizedSearchTerm + '%')
                    }
                }
            }

            JSONObject results = new JSONObject()
            def recordsTotal = compositionArrayList.totalCount
            results.put("draw", paramsJsonObject.draw)
            results.put("recordsTotal", recordsTotal)
            results.put("recordsFiltered", recordsTotal)
            results.put("productList", compositionArrayList)

            return results

        }
        catch (Exception ex)
        {
            log.error("AlternateProductService" + ex)
            println("AlternateProductService" + ex)
        }
    }
}
