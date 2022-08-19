<%@ page import="phitb_ui.Constants" %>
<asset:javascript src="spring-websocket"/>
<script>
    var socket = new SockJS("${createLink(uri: '/stomp')}");
    var client = Stomp.over(socket);
    client.debug = null;

    function tempStockPool() {
        client.connect({}, function () {
            client.subscribe("/topicTempStockPool/get/${session.getAttribute("userId")}", function (message) {
                var json = JSON.parse(message.body);
                $("#tempStockSidebar").html("");

                if (json.length === 0) {
                    $("#tempStockSidebar").html("<div style=\"text-align: center\">\n" +
                        "                                <i class=\"zmdi zmdi-storage\" style=\"font-size: 48px;\"></i><br>\n" +
                        "                                <small>temporary stocks empty</small>\n" +
                        "                            </div>");
                } else {
                    var tmpStkSidebarContent = "";
                    var tableHeader = "<table class=\"table table-condensed\" style=\"width:100%\">\n" +
                        "                         <thead>\n" +
                        "                         <tr>\n" +
                        "                         <th style=\"width: 80%\"></th>\n" +
                        "                         </tr>\n" +
                        "                         </thead>\n" +
                        "                         <tbody>";

                    for (var i = 0; i < json.length; i++) {
                        tmpStkSidebarContent += "<tr>" +
                            "<td class=\"left-align\" style=\"text-align: left\">" + json[i].productName + "<br>" +
                            "Btch: " + json[i]['batchNumber'] + "<br>" +
                            "Qty: " + (json[i]['userOrderQty'] + "<br>Fr. Qty: " + json[i]['userOrderFreeQty']) +
                            "</td>" +
                            "</tr>"
                    }
                    $("#tempStockSidebar").html(tableHeader + tmpStkSidebarContent + "</tbody></table>");
                }


            })
        })
    }

    tempStockPool();

    /*======================================= Ajax Requests ========================================*/
    <g:if test="${!session.getAttribute("role").toString().equalsIgnoreCase(Constants.SUPER_USER)}">
    setTimeout(getTempStock, 1000);
    </g:if>

    function getTempStock() {
        $.ajax({
            type: "GET",
            url: "/tempstockbook/user/${session.getAttribute("userId")}",
            dataType: 'json',
            success: function (data) {

            }
        })
    }
</script>