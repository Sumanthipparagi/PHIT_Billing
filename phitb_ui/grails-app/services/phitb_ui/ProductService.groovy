package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.glassfish.jersey.jackson.JacksonFeature
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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

    def getProductTypes() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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

    def getProductGroups() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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

    def getDivisions() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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

    def getProductClass() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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

    def getProductCategories() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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


    def getDivisionGroups() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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

    def getCompositions() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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


    def getCustomerGroup() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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

//    Division
    def saveDivision(JSONObject jsonObject)
    {
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        Client client = ClientBuilder.newClient().register(JacksonFeature.class)
        WebTarget target = client.target(new Links().API_GATEWAY);
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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
        GrailsHttpSession session = WebUtils.retrieveGrailsWebRequest().session
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




}