/**
 * 提供异步数据提交
 * @param url 请求地址
 * @param param 请求参数
 * @param returnVm 回调的vue实例，必须有success方法
 * @constructor
 */
function AjaxRequest(url, param, returnVm) {
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

/**
 *
 * @param url 请求地址
 * @param param 请求参数  json格式
 * @param dataType 返回类型 可用类型为 xml html script json jsonp text
 * @param returnVm 回调的vue实例，必须有success方法
 * @constructor
 */
function AjaxRequest2(url, param, dataType, returnVm) {
    $.ajax({
        url: url,
        data: param,
        dataType: dataType,
        type: "post",
        success: function (data) {
            returnVm.success(data)
        }
    })
}

/**
 *
 * @param url 请求地址
 * @param param 请求参数  json格式
 * @param dataType 返回类型 可用类型为 xml html script json jsonp text
 * @param type 请求类型  为get post
 * @param returnVm 回调的vue实例，必须有success方法
 * @constructor
 */
function AjaxRequest3(url, param, dataType, type, returnVm) {
    $.ajax({
        url: url,
        data: param,
        dataType: dataType,
        type: type,
        success: function (data) {
            returnVm.success(data)
        }
    })
}