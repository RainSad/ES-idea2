$(function () {
    //初始化后台管理页面
    vm.returnView = 1;
    initRelativeBto();
})

//控制右侧菜单栏加载
var vm = new Vue({
    data: {
        returnView: 0
    }
})
vm.$watch("returnView", function () {
    switch (vm.returnView) {
        case 1:
            ///
            break
        case 2:
            ///
            break
        case 3:
            ///
            break
    }
});

//初始化页面显示
function initRelativeBto() {
    $(".toggle-btn").click(function () {
        $("#leftMeun").toggleClass("show");
        $("#rightContent").toggleClass("pd0px");
    });
    $(".meun-item").click(function () {
        $(".meun-item").removeClass("meun-item-active");
        $(this).addClass("meun-item-active");
        var itmeObj = $(".meun-item").find("img");
        itmeObj.each(function () {
            var items = $(this).attr("src");
            items = items.replace("_grey.png", ".png");
            items = items.replace(".png", "_grey.png")
            $(this).attr("src", items);
        });
    });

}




