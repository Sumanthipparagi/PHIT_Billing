<script>
    function selectSideMenu(menu)
    {
        $(".sidemenuitem").removeClass("active");
        $(".sidemenuitem").removeClass("open");

        $("#"+menu).addClass("active");
        $("#"+menu).addClass("open");
    }
</script>