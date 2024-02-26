package phitb_product

import grails.gorm.transactions.Transactional

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
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
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
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
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
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }

    def getProductByCompositionId(String compositionId)
    {
        try {
            def result = ProductMaster.findAllByComposition(Long.parseLong(compositionId))
            return result

        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }

    def getProductByCompanyId(String companyId)
    {
        try {
            def result = ProductMaster.findAllByCompany(Long.parseLong(companyId))
            return result

        }
        catch (Exception ex)
        {
            log.error("SaleProductDeatilsService" + ex)
            println("SaleProductDeatilsService" + ex)
        }
    }
}
