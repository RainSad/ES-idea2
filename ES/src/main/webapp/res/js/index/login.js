/**
 * 登录页，主要控制器
 */
var vm = new Vue({
    el: "#formDiv",
    data: {
        error: "",
        username: "",
        ret: "",
        param: {
            username: "",
            password: ""
        }
    },
    methods: {

        //ajax成功回调方法
        success: function (data) {
            this.error = data.error;
            this.username = data.username;
            this.ret = data.ret;
        }

    }
});

/**
 * 提供异步数据提交
 * @param url
 * @param param
 * @param returnVm
 * @constructor
 */
function AjaxSubmit(url, param, returnVm) {
    $.ajax({
        url: url,
        data: param,
        dataType: "json",
        type: "post",
        success: function (data) {
            returnVm.success(data)
        }
    })
}
