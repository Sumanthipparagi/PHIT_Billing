package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.glassfish.jersey.jackson.JacksonFeature
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Transactional
class ProductService {

    def productServiceStatus()
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_SERVICE_STATUS)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :product , action :  salesServiceStatus  , Ex:' + ex)
            log.error('Service :product , action :  salesServiceStatus  , Ex:' + ex)
        }

    }
    /**
     *
     * @param jsonObject
     * @return
     */
    def getProducts() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductById(String id) {
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getEntity  , Ex:' + ex)
            log.error('Service :ProductService , action :  getEntity  , Ex:' + ex)
        }

    }

    def getProductTypes() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_TYPE_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }
    }

    def getProductTypesByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_TYPE_SHOW_BY_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }
    }

    def getProductRegister() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductGroups() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_GROUP_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductGroupById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_GROUP_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse?.status == 200){
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class));
                return jsonObject
            }
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductGroupsByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_GROUP_SHOW_BY_ENTITY+ "/"+ entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductGroupsByEntity  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductGroupsByEntity  , Ex:' + ex)
        }

    }

    def getDivisions() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().DIVISION_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getDivisionById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().DIVISION_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse?.status == 200){
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class));
                return  jsonObject
            }
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductClass() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_CLASS_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductCost() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_COST_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductCostByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_COST_SHOW_BY_ENTITY + "/"+ entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductCategories() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CATEGORY_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }
    }


    def getProductCategoryById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CATEGORY_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse?.status == 200){
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class));
                return jsonObject
            }else{
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }
    }

    def getProductCategoriesByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CATEGORY_BY_ENTITY_SHOW+"/"+ entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductCategoriesByEntity  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductCategoriesByEntity  , Ex:' + ex)
        }

    }


    def getDivisionGroups() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().DIVISION_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }
    /**
     *
     * @param jsonObject
     * @return
     */
    def getProductSchedules() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_SCHEDULE_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductSchedules  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductSchedules  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def getProductSchedulebyId(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_SCHEDULE_SHOW+"/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse?.status == 200){
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class));
                return jsonObject
            }else{
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductSchedules  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductSchedules  , Ex:' + ex)
        }

    }


    /**
     *
     * @param jsonObject
     * @return
     */
    def getProductSchedulesByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_SCHEDULE_SHOW_BY_ENTITY+ "/"+entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductSchedulesByEntity  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductSchedulesByEntity  , Ex:' + ex)
        }

    }

    def getCompositions() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().PRODUCT_COMPOSITION_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getProductCompositionByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_COMPOSITION_SHOW_BY_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getCompositions  , Ex:' + ex)
            log.error('Service :ProductService , action :  getCompositions  , Ex:' + ex)
        }

    }

    def getProductByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_BY_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductByEntity  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductByEntity  , Ex:' + ex)
        }

    }

    def getProductByName(String productName, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_SEARCH)
                    .queryParam("productName", productName)
                    .queryParam("entityId", entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductByName  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductByName  , Ex:' + ex)
        }

    }

    def getBatchRegister() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().BATCH_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchRegister  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchRegister  , Ex:' + ex)
        }

    }

    def getBatchById(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().BATCH_REGISTER_SHOW + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class));
                return jsonObject
            }
            else
            {
                return null
            }
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchRegister  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchRegister  , Ex:' + ex)
        }

    }

    def getBatchesOfProduct(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {

            Response apiResponse = target
                    .path(new Links().GET_BATCH_BY_PRODUCT + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
            log.error('Service :ProductService , action :  getBatchesOfProduct  , Ex:' + ex)
        }

    }

    def getUnitType() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().UNIT_TYPE_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def getUnitTypeByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);

        try {

            Response apiResponse = target
                    .path(new Links().UNIT_TYPE_SHOW_BY_ENTITY + "/"+ entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getUnitTypeByEntity  , Ex:' + ex)
            log.error('Service :ProductService , action :  getUnitTypeByEntity  , Ex:' + ex)
        }

    }


    def getCustomerGroup() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try {

            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_SHOW)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

    def dayEndByEntityId(String entityId){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().DAY_END_MASTER_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }
    }

    def getCustomerGroupByEntity(String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().CUSTOMER_GROUP_REGISTER_SHOW_BY_ENTITY + "/" + entityId)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()

            return apiResponse
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProducts  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProducts  , Ex:' + ex)
        }

    }

//    Division
    def saveDivision(JSONObject jsonObject)
    {
        jsonObject = Tools.setCreatedUser(jsonObject)
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DIVISION_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showDivisoion(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().DIVISION_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putDivision(JSONObject jsonObject)
    {
        jsonObject = Tools.setModifiedUser(jsonObject)
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().DIVISION_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : Godown , action :  put  , Ex:' + ex)
            log.error('Service :putAccountMode , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteDivision(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().DIVISION_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    //    Product Category
    def saveProductCategory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_CATEGORY_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductCategory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CATEGORY_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductCategory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CATEGORY_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductCategory(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CATEGORY_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    //    Product Category
    def saveProductSchedule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_SCHEDULE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductSchedule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_SCHEDULE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductSchedule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_SCHEDULE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductSchedule(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_SCHEDULE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    //    Product Category
    def saveProductComposition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_COMPOSITION_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductComposition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_COMPOSITION_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductComposition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_COMPOSITION_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductComposition(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_COMPOSITION_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    //    Product Type
    def saveProductType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_TYPE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_TYPE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }


    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_TYPE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }

    //    Product Group
    def saveProductGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_GROUP_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_GROUP_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_GROUP_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_GROUP_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }

    //    Unit Type
    def saveUnitType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().UNIT_TYPE_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showUnitType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().UNIT_TYPE_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putUnitType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().UNIT_TYPE_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteUnitType(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().UNIT_TYPE_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    //    Division group
    def saveDivisionGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().DIVISION_GROUP_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showDivisionGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().DIVISION_GROUP_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putDivisionGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().DIVISION_GROUP_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteDivisionGroup(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().DIVISION_GROUP_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }

    //    Product Class
    def saveProductClass(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_CLASS_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductClass(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CLASS_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductClass(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CLASS_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductClass(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_CLASS_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    //    Product Cost Range
    def saveProductCost(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_COST_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductCost(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_COST_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductCost(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_COST_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductCost(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_COST_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    //   Batch Register

    def saveBatchRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
   /*     WebTarget target = client.target("http://localhost:8085/");*/
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().BATCH_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showBatchRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().BATCH_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putBatchRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().BATCH_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteBatchRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().BATCH_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :productService , action :  delete  , Ex:' + ex)
            log.error('Service :productService , action :  delete  , Ex:' + ex)
        }

    }


    //   Product Register
    def getProductsByEntityId(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_BY_ENTITY + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                def txt = apiResponse.readEntity(String.class)
                JSONArray obj = new JSONArray(txt)
                return obj
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProductByEntityId  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductByEntityId  , Ex:' + ex)
        }

    }

    def getProductsByDivision(String id)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY)
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_BY_DIVISION + "/"+id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                def txt = apiResponse.readEntity(String.class)
                JSONArray obj = new JSONArray(txt)
                return obj
            }
            else
            {
                return null
            }
        }
        catch (Exception ex)
        {
            System.err.println('Service :ProductService , action :  getProductsByDivision  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductsByDivision  , Ex:' + ex)
        }

    }

    def saveProductRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            println(jsonObject)
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_SAVE)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(apiResponse)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :Product , action :  save  , Ex:' + ex)
            log.error('Service :Product , action :  save  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def showProductRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_DATATABLE)
                    .queryParam("params", URLEncoder.encode(jsonObject.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :getAccountModes , action :  show  , Ex:' + ex)
            log.error('Service :getAccountModes , action :  show  , Ex:' + ex)
        }

    }

    def putProductRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);

        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_UPDATE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(jsonObject,MediaType.APPLICATION_JSON_TYPE))
            println(jsonObject)
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service : putProductCategory , action :  put  , Ex:' + ex)
            log.error('Service :putProductCategory , action :  put  , Ex:' + ex)
        }

    }

    /**
     *
     * @param jsonObject
     * @return
     */
    def deleteProductRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        
        try
        {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_DELETE)
                    .resolveTemplate("id", jsonObject.id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .delete()
            return apiResponse
        }
        catch (Exception ex)
        {
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }

    }


    def getDivisionsByEntityId(String id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {

            Response apiResponse = target
                    .path(new Links().DIVISION_BY_ENTITY + "/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if(apiResponse.status == 200)
            {
                JSONArray jsonArray = new JSONArray(apiResponse.readEntity(String.class))
                return jsonArray
            }
            else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getDivisionsByEntityId  , Ex:' + ex)
            log.error('Service :ProductService , action :  getDivisionsByEntityId  , Ex:' + ex)
        }

    }

    def getProductsBySeries(String seriesId, String entityId)
    {
        try
        {
        JSONArray divisions = getDivisionsByEntityId(entityId)
        JSONArray products = new JSONArray()
        for (JSONObject jsonObject : divisions) {

            if(jsonObject.get("seriesId") == Long.parseLong(seriesId))
            {
                String divisionId = jsonObject.get("id")
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target(new Links().API_GATEWAY)

                    Response apiResponse = target
                            .path(new Links().PRODUCT_REGISTER_BY_DIVISION + "/"+divisionId)
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .get()
                    if (apiResponse?.status == 200)
                    {
                        JSONArray obj = new JSONArray(apiResponse.readEntity(String.class))
                        products.addAll(obj)
                    }
                client.close()
            }
        }
        return products
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getProductsBySeries  , Ex:' + ex)
            log.error('Service :ProductService , action :  getProductsBySeries  , Ex:' + ex)
        }
    }


    def getByBatchAndProductId(String batch, String productId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().GET_BY_BATCH_AND_PRODUCT)
                    .queryParam("batch", URLEncoder.encode(batch.toString(), "UTF-8"))
                    .queryParam("productId", URLEncoder.encode(productId.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
            {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getByBatchAndProductId  , Ex:' + ex)
            log.error('Service :ProductService , action :  getByBatchAndProductId  , Ex:' + ex)
        }

    }

    def getProductByIdAndHSN(String productId, String hsnCode, String entityId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        try {
            Response apiResponse = target
                    .path(new Links().PRODUCT_REGISTER_BY_ID_HSN)
                    .queryParam("productId", URLEncoder.encode(productId.toString(), "UTF-8"))
                    .queryParam("hsnCode", URLEncoder.encode(hsnCode.toString(), "UTF-8"))
                    .queryParam("entityId", URLEncoder.encode(entityId.toString(), "UTF-8"))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get()
            if (apiResponse?.status == 200)
            {
                JSONObject jsonObject = new JSONObject(apiResponse.readEntity(String.class))
                return jsonObject
            }
            else
            {
                return []
            }
        }
        catch (Exception ex) {
            System.err.println('Service :ProductService , action :  getByProductIdAndHSN  , Ex:' + ex)
            log.error('Service :ProductService , action :  getByProductIdAndHSN  , Ex:' + ex)
        }
    }


    def saveBulkProductRegister(JSONArray jsonArray){
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().BULK_SAVE_PRODUCT_DETAIL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonArray.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse?.status == 200) {
              JSONArray jsonArray1 = new JSONArray(apiResponse.readEntity(String.class))
                return  jsonArray1
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveBulkProductRegister  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveBulkProductRegister  , Ex:' + ex)
        }

    }


    def saveBulkBatchRegister(JSONArray jsonArray){
        Client client = ClientBuilder.newClient()
        WebTarget target = client.target(new Links().API_GATEWAY)
        try {
            Response apiResponse = target
                    .path(new Links().BULK_SAVE_BATCH_DETAIL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(jsonArray.toString(), MediaType.APPLICATION_JSON_TYPE))
            if (apiResponse?.status == 200) {
                JSONObject jsonObject1 = new JSONObject(apiResponse.readEntity(String.class))
                return  jsonObject1
            } else
                return null
        }
        catch (Exception ex) {
            System.err.println('Service :EntityService , action :  saveBulkProductRegister  , Ex:' + ex)
            log.error('Service :EntityService , action :  saveBulkProductRegister  , Ex:' + ex)
        }

    }

}