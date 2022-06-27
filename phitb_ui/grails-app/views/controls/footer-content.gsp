<script>
    function selectSideMenu(menu)
    {
        $(".sidemenuitem").removeClass("active");
        $(".sidemenuitem").removeClass("open");

        $("#"+menu).addClass("active");
        $("#"+menu).addClass("open");

        /******main menu hiding *****/

        if($('#purchase-menu').find('.ml-menu').children().length === 0)
            $('#purchase-menu').addClass("hidden");
        else
            $('#purchase-menu').removeClass("hidden");

        if($('#sales-menu').find('.ml-menu').children().length === 0)
            $('#sales-menu').addClass("hidden");
        else
            $('#sales-menu').removeClass("hidden");

        if($('#accounts-menu').find('.ml-menu').children().length === 0)
            $('#accounts-menu').addClass("hidden");
        else
            $('#accounts-menu').removeClass("hidden");

        if($('#entity-menu').find('.ml-menu').children().length === 0)
            $('#entity-menu').addClass("hidden");
        else
            $('#entity-menu').removeClass("hidden");

        if($('#facility-menu').find('.ml-menu').children().length === 0)
            $('#facility-menu').addClass("hidden");
        else
            $('#facility-menu').removeClass("hidden");

        if($('#system-menu').find('.ml-menu').children().length === 0)
            $('#system-menu').addClass("hidden");
        else
            $('#system-menu').removeClass("hidden");

        if($('#approvals-menu').find('.ml-menu').children().length === 0)
            $('#approvals-menu').addClass("hidden");
        else
            $('#approvals-menu').removeClass("hidden");

        if($('#product-menu').find('.ml-menu').children().length === 0)
            $('#product-menu').addClass("hidden");
        else
            $('#product-menu').removeClass("hidden");

        if($('#inventory-menu').find('.ml-menu').children().length === 0)
            $('#inventory-menu').addClass("hidden");
        else
            $('#inventory-menu').removeClass("hidden");

        if($('#settings-menu').find('.ml-menu').children().length === 0)
            $('#settings-menu').addClass("hidden");
        else
            $('#settings-menu').removeClass("hidden");

        if($('#shipments-menu').find('.ml-menu').children().length === 0)
            $('#shipments-menu').addClass("hidden");
        else
            $('#shipments-menu').removeClass("hidden");

    }
</script>

<g:include view="controls/socket.gsp"/>