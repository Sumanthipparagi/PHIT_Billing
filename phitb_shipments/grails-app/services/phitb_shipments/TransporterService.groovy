package phitb_shipments

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_shipments.Exception.BadRequestException
import phitb_shipments.Exception.ResourceNotFoundException

@Transactional
class TransporterService {

    def getAll(String limit, String offset, String query) {

        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100

        if (!query)
            return Transporter.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return Transporter.findAllByNameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order: 'desc'])
    }

    Transporter get(String id) {
        return Transporter.findById(Long.parseLong(id))
    }

    ArrayList<Transporter> getByEntityId(String id) {
        return Transporter.findAllByEntityId(Long.parseLong(id))
    }

    ArrayList<Transporter> getByTransportType(String id) {
        TransportType transportType = TransportType.findById(Long.parseLong(id))
        if(transportType)
            return Transporter.findAllById(Long.parseLong(id))
        else {
            throw new ResourceNotFoundException()
        }
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length)
    {
        try
        {

            String searchTerm = paramsJsonObject.get("search[value]")
            String orderColumnId = paramsJsonObject.get("order[0][column]")
            String orderDir = paramsJsonObject.get("order[0][dir]")

            String orderColumn = "id"
            switch (orderColumnId)
            {
                case '0':
                    orderColumn = "id"
                    break;
                case '1':
                    orderColumn = "name"
                    break;
            }

            Integer offset = start ? Integer.parseInt(start.toString()) : 0
            Integer max = length ? Integer.parseInt(length.toString()) : 100

            def transporterCriteria = Transporter.createCriteria()
            def transporterArrayList = transporterCriteria.list(max: max, offset: offset) {
                or {
                    if (searchTerm != "")
                    {
                        ilike('name', '%' + searchTerm + '%')
                    }
                }
                eq('deleted', false)
                order(orderColumn, orderDir)
            }

            def recordsTotal = transporterArrayList.totalCount
            JSONObject jsonObject = new JSONObject()
            jsonObject.put("draw", paramsJsonObject.draw)
            jsonObject.put("recordsTotal", recordsTotal)
            jsonObject.put("recordsFiltered", recordsTotal)
            jsonObject.put("data", transporterArrayList)
            return jsonObject
        }
        catch(Exception ex)
        {
            System.out.println(ex)
            return null
        }
    }

    Transporter save(JSONObject jsonObject) {
        Transporter Transporter = new Transporter()
        Transporter.name = jsonObject.get("name")
        Transporter.address = jsonObject.get("address")
        Transporter.phone = jsonObject.get("phone")
        Transporter.gstNo = jsonObject.get("gstNo")
        Transporter.transportType = TransportType.findById(jsonObject.get("transportType"))
        Transporter.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
        Transporter.entityId = Long.parseLong(jsonObject.get("entityId").toString())
        Transporter.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
        Transporter.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
        Transporter.save(flush: true)
        if (!Transporter.hasErrors())
            return Transporter
        else
            throw new BadRequestException()
    }

    Transporter update(JSONObject jsonObject, String id) {

        if (id) {
            Transporter Transporter = Transporter.findById(Long.parseLong(id))
            if (Transporter) {
                Transporter.isUpdatable = true
                Transporter.name = jsonObject.get("name")
                Transporter.address = jsonObject.get("address")
                Transporter.phone = jsonObject.get("phone")
                Transporter.gstNo = jsonObject.get("gstNo")
                Transporter.transportType = TransportType.findById(jsonObject.get("transportType"))
                Transporter.entityTypeId = Long.parseLong(jsonObject.get("entityTypeId").toString())
                Transporter.entityId = Long.parseLong(jsonObject.get("entityId").toString())
                Transporter.createdUser = Long.parseLong(jsonObject.get("createdUser").toString())
                Transporter.modifiedUser = Long.parseLong(jsonObject.get("modifiedUser").toString())
                Transporter.save(flush: true)
                if (!Transporter.hasErrors())
                    return Transporter
                else
                    throw new BadRequestException()
            } else
                throw new ResourceNotFoundException()
        } else {
            throw new BadRequestException()
        }
    }

    void delete(String id) {
        if (id) {
            Transporter Transporter = Transporter.findById(Long.parseLong(id))
            if (Transporter) {
                Transporter.isUpdatable = true
                Transporter.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }
}
