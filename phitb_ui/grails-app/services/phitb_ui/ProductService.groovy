package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
        }
    }


    def getCustomerGroup() {
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
        }
    }


    //   Batch Register

    def saveBatchRegister(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
            System.err.println('Service :systemService , action :  delete  , Ex:' + ex)
            log.error('Service :systemService , action :  delete  , Ex:' + ex)
        }
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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
        finally{
            client.close()
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

}