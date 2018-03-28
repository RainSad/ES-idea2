$(function () {
    getData();
});

var vm1 = new Vue({
    el: "#messageDetail",
    data: {
        info: {
            message: ""
        }
    },
    computed: {
        setHTML: function () {
            $("#messageDetail").append(this.info.message);
        }
    }

});

function getData() {
    $.ajax({
        url: "index/viewShare/detail/351219fe8de64s42882d251c0a4f5081",
        dataType: "json",
        type: "post",
        success: function (data) {
            vm1.info.message = data.data.message;
        }
    })
}
