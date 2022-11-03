package phitb_system

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import phbit_system.Exception.BadRequestException
import phbit_system.Exception.ResourceNotFoundException

@Transactional
class CityMasterService {

    /**
     * Gets all city Master
     * @param query
     * @param offset
     * @param limit
     * @return list of account types
     */
    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return CityMaster.findAll([sort: 'areaName', max: l, offset: o, order: 'desc'])
        else
            return CityMaster.findAllByAreaNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }



    /**
     * Gets city master by id
     * @param id
     * @return city master by id
     */
    CityMaster get(String id) {
        return CityMaster.findById(Long.parseLong(id))
    }

    /**
     * Gets city master by entityId
     * @param limit
     * @param offset
     * @param id
     * @return list of city master by entityId
     */
    def getAllByEntityId(String limit, String offset, long id) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!id)
            return CityMaster.findAll([sort: 'id', max: l, offset: o, order: 'asc'])
        else
            return CityMaster.findAllById(id,[sort: 'id', max: l, offset: o, order: 'desc'])
    }

    /**
     * Gets city master in datatables format
     * @param jsonObject
     * @param start
     * @param length
     * @return jsonObject of city master
     */
    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "name"
                break;
        }

        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100

        def cityMasterCriteria = CityMaster.createCriteria()
        def cityMasterArrayList = cityMasterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('name', '%' + searchTerm + '%')
                    stateMaster{
                        ilike('name', '%' + searchTerm + '%')
                    }
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }

//        def names = []
//        cityMasterArrayList.each {
//            println(it.entityId)
//            def apires = showCityByEntityId(it.entityId.toString())
//            names.push(apires)
//        }

        def recordsTotal = cityMasterArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", cityMasterArrayList)
        return jsonObject
    }

    /**
     * save city master
     * @param jsonObject
     * @return saved city master
     */
    CityMaster save(JSONObject jsonObject) {
        CityMaster cityMaster = new CityMaster()
        cityMaster.circleName = jsonObject.get("circleName").toString()
        RegionMaster regionMaster = RegionMaster.findById(Long.parseLong(jsonObject.get("region").toString()))
        if(regionMaster)
        {
            cityMaster.regionName = regionMaster.regionName
            cityMaster.regionCode = regionMaster.regionCode
            cityMaster.region = regionMaster
        }
        DivisionMaster divisionMaster = DivisionMaster.findById(Long.parseLong(jsonObject.get("division").toString()))
        if(divisionMaster)
        {
            cityMaster.divisionName = divisionMaster.divisionName
            cityMaster.divisionCode = divisionMaster.divisionCode
            cityMaster.division = divisionMaster
        }
        DistrictMaster districtMaster = DistrictMaster.findById(Long.parseLong(jsonObject.get("district").toString()))
        if(districtMaster)
        {
            cityMaster.districtName = districtMaster.district
            cityMaster.districtCode = districtMaster.districtCode
            cityMaster.district = districtMaster

        }
        StateMaster stateMaster = StateMaster.findById(Long.parseLong(jsonObject.get("state").toString()))
        if(stateMaster)
        {
            cityMaster.state = stateMaster
            cityMaster.stateName = stateMaster.name
        }
        cityMaster.areaName = jsonObject.get("areaName").toString()
        cityMaster.areaCode = jsonObject.get("areaCode").toString()
        cityMaster.pincode = jsonObject.get("pincode").toString()
        cityMaster.latitude = jsonObject.get("latitude").toString()
        cityMaster.logitude = jsonObject.get("logitude").toString()
        cityMaster.save(flush: true)
        if (!cityMaster.hasErrors())
            return cityMaster
        else
            throw new BadRequestException()
    }

    /**
     * update city master
     * @param jsonObject
     * @param id
     * @return updated city master
     */
    CityMaster update(JSONObject jsonObject, String id) {
        if (id) {
            CityMaster cityMaster = CityMaster.findById(Long.parseLong(id))
            if (cityMaster) {
                cityMaster.isUpdatable = true
                cityMaster.circleName = jsonObject.get("circleName").toString()
                RegionMaster regionMaster = RegionMaster.findById(Long.parseLong(jsonObject.get("region").toString()))
                if(regionMaster)
                {
                    cityMaster.regionName = regionMaster.regionName
                    cityMaster.regionCode = regionMaster.regionCode
                    cityMaster.region = regionMaster
                }
                DivisionMaster divisionMaster = DivisionMaster.findById(Long.parseLong(jsonObject.get("division").toString()))
                if(divisionMaster)
                {
                    cityMaster.divisionName = divisionMaster.divisionName
                    cityMaster.divisionCode = divisionMaster.divisionCode
                    cityMaster.division = divisionMaster
                }
                DistrictMaster districtMaster = DistrictMaster.findById(Long.parseLong(jsonObject.get("district").toString()))
                if(districtMaster)
                {
                    cityMaster.districtName = districtMaster.district
                    cityMaster.districtCode = districtMaster.districtCode
                    cityMaster.district = districtMaster

                }
                StateMaster stateMaster = StateMaster.findById(Long.parseLong(jsonObject.get("state").toString()))
                if(stateMaster)
                {
                    cityMaster.state = stateMaster
                    cityMaster.stateName = stateMaster.name
                }
                cityMaster.areaName = jsonObject.get("areaName").toString()
                cityMaster.areaCode = jsonObject.get("areaCode").toString()
                cityMaster.pincode = jsonObject.get("pincode").toString()
                cityMaster.latitude = jsonObject.get("latitude").toString()
                cityMaster.logitude = jsonObject.get("logitude").toString()
                cityMaster.save(flush: true)
                if (!cityMaster.hasErrors())
                    return cityMaster
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }


    /**
     * delete city master
     * @param id
     * @return deleted city master
     */
    void delete(String id) {
        if (id) {
            CityMaster cityMaster = CityMaster.findById(Long.parseLong(id))
            if (cityMaster) {
                cityMaster.isUpdatable = true
                cityMaster.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }

    /**
     * get city details by pincode
     * @param id
     * @return cities based on pincodes
     */
    def getCityDetailsByPinCode(String pincode)
    {
        try {
            if(pincode)
            {
                ArrayList<CityMaster> cityMasters =  CityMaster.findAllByPincodeIlike(pincode) as ArrayList
                if (cityMasters) {
//                    JSONArray cityDetails = new JSONArray((cityMasters as JSON).toString())
//                    println(cityDetails)
                    return cityMasters
                }
            }
            else
            {
                return null
            }
        }
        catch(Exception ex)
        {
            println(ex)
            throw new BadRequestException()
        }
    }

}
