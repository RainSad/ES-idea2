/*
1.新添加一个会话 cookie： 
1
$.cookie('the_cookie', 'the_value');
注：当没有指明 cookie有效时间时，所创建的cookie有效期默认到用户关闭浏览器为止，所以被称为 
“会话cookie（session cookie）”。 
2.创建一个cookie并设置有效时间为 7天:
    1
$.cookie('the_cookie', 'the_value', { expires: 7 });
注：当指明了cookie有效时间时，所创建的cookie被称为“持久 cookie （persistent cookie）”。 
3.创建一个cookie并设置 cookie的有效路径： 
1
$.cookie('the_cookie', 'the_value', { expires: 7, path: '/' });
注：在默认情况下，只有设置 cookie的网页才能读取该 cookie。如果想让一个页面读取另一个页面设
置的cookie，必须设置cookie的路径。cookie的路径用于设置能够读取 cookie的顶级目录。将这
个路径设置为网站的根目录，可以让所有网页都能互相读取 cookie （一般不要这样设置，防止出现冲突） 。 
4.读取cookie： 
1
2
$.cookie('the_cookie'); // cookie存在 => 'the_value' 
$.cookie('not_existing'); // cookie不存在 => null
5.删除cookie，通过传递null作为cookie的值即可： 
1
$.cookie('the_cookie', null);
----------相关参数的解释---------------
    1).expires: 365
定义cookie的有效时间，值可以是一个数字（从创建cookie时算起，以天为单位）或一个Date 对
象。如果省略，那么创建的cookie是会话cookie，将在用户退出浏览器时被删除。 
2).path: '/'
默认情况：只有设置cookie的网页才能读取该cookie。 
定义cookie的有效路径。默认情况下， 该参数的值为创建 cookie 的网页所在路径（标准浏览器的行为） 。 
如果你想在整个网站中访问这个cookie需要这样设置有效路径：path: '/'。如果你想删除一个定义
了有效路径的 cookie，你需要在调用函数时包含这个路径:$.cookie('the_cookie', null,
    { path: '/' });。 domain: 'example.com'
默认值：创建 cookie的网页所拥有的域名。 
3).secure: true
默认值：false。如果为true，cookie的传输需要使用安全协议（HTTPS）。 
4).raw: true
默认值：false。 
默认情况下，读取和写入 cookie 的时候自动进行编码和解码（使用encodeURIComponent 编码， 
decodeURIComponent 解码）。要关闭这个功能设置 raw: true 即可。*/

jQuery(function () {
    initSummernote();
    initFileInput();
    checkUser();
});

/**
 * 得到用户登录地址等信息
 */
function initLoginInfo() {
    var url = 'http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_='
        + Math.random();
    $.getJSON(url, function (data) {
        saveLoginInfo(data);
    });

}

/**
 * 初始化summernote
 */
function initSummernote() {
    $('#editor')
        .summernote(
            {
                lang: 'zh-CN',
                minHeight: 240,
                disableLinkTarget: true,
                dialogsFade: true,
                placeholder: '请输入描述文字，嗯，没错，就是你想的那样.....',
                toolbar: [
                    // [groupName, [list of button]]
                    ['style', ['bold', 'italic', 'underline', 'clear']],
                    ['font', ['strikethrough', 'superscript', 'subscript']],
                    ['fontsize', ['fontsize']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['height', ['height']],
                ],
            });
}

/**
 * 初始化fileinput
 */
function initFileInput() {
    $("#file-1").fileinput({
        // 异步上传
        language: 'zh', // 设置语言
        uploadUrl: 'index/upload/img', // 上传地址
        showUpload: false, // 是否显示上传按钮
        showRemove: true,
        dropZoneEnabled: false,
        showCaption: true,// 是否显示标题
        enctype: 'multipart/form-data',
        allowedPreviewTypes: ['image'],
        allowedFileTypes: ['image'],
        allowedFileExtensions: ['jpg', 'png'],
        maxFileCount: 8,
        maxImageWidth: "300px",
        uploadExtraData: function () {
            return {
                "id": vm2.info.id,
                "imgUrlId": vm2.info.imgUrlId ? vm2.info.imgUrlId : "",
                "commentId": vm2.info.commentId ? vm2.info.commentId : ""
            }
        }
    });
    // 异步上传返回结果处理
    $('#file-1').on('fileerror', function (event, data, msg) {
        console.log("fileerror");
        console.log(data);
    });
    // 异步上传返回结果处理
    $("#file-1").on("fileuploaded", function (event, data, previewId, index) {
        console.log(data.response.url);
    });
}

// 查找登陆用户
var vm = new Vue({
    el: '#login',
    components: {},
    method: {},
    data: {
        seen: true,
        user: false,
        result: {
            nickName: "孙文祥"
        }
    }
});

/**
 * 弹出式分享内容框即页面加号按钮
 */
var vm2 = new Vue({
    el: '#myModal',
    components: {},
    methods: {
        viewShareSubmit: function () {
            // 验证输入
            var bootstrapValidator = $("#myModal").data("bootstrapValidator")
                .validate();
            // 得到校验结果 true or false
            var result = bootstrapValidator.isValid();
            // 得到输入值
            var id = $("#id").val();
            var titleInput = $("#title").val();
            var messageDetail = $('#editor').summernote('code');
            if (result) {
                // 拼接到传送json字符串中
                this.info.titleInput = titleInput;
                this.info.messageDetail = messageDetail;

                $('#delcfmModel').modal();
                $('#myModal').modal("hide");
            }
        },
        resetData: function () {
            // 从后台重新加载id
            getIndexUploadId();
            // 重置标题
            $("#title").val("");
            // 重置验证 先销毁
            $("#myModal").data('bootstrapValidator').destroy();
            // 加载表单校验
            validataForm("myModal");
            // 清除图片输入
            $("#file-1").fileinput("clear");
            // 重置富文本输入
            $('#editor').summernote('reset')

        }

    },
    data: {
        info: {
            id: ""
        }
    },
    mounted: function () {
        this.$nextTick(function () {
            // 加载表单校验
            validataForm("myModal");
        })
    }
});

/**
 * 弹出式确认框
 */
var vm3 = new Vue({
    el: "#confirmModel",
    components: {
        'confirm': confirmModel,
    },
    methods: {
        submit: function () {
            AjaxSubmit("index/upload/message", vm2.info);
            $('#delcfmModel').modal('hide')
        },
        cancel: function () {
            $('#delcfmModel').modal('hide');
            $('#myModal').modal("show");
        }
    },
    data: {
        info: {
            "header": "提示",
            "message": "确定不再写点了吗？",
            "cancel": "再写点",
            "confirm": "确定"
        }
    }
});

/**
 * 分享模态框弹出控制
 */
var vm4 = new Vue({
    el: "#addRes",
    methods: {
        // 上传模态框弹出控制
        modelViewShare: function () {
            // 从后台加载id
            if(vm2.info.id === ""){
                getIndexUploadId();
                console.log(vm2.info.id);
            }
            $('#myModal').modal("show");
        }
    },
    data: {}
});

/**
 * 验证用户是否登陆或是否登陆过期
 */
function checkUser() {
    var userInfo = $.parseJSON(getCookie("yourView_userInfo"));
    if (userInfo != null && userInfo != undefined) {
        vm.result = userInfo.data[0];
        vm.user = true;
        vm.seen = false
    } else {
        vm.seen = true;
        vm.user = false;
    }
}

/**
 * 得到用户存储的cookie信息
 *
 * @returns {string}
 */
function getCookie(yourView_userInfo) {

    // 测试，手动写入cookies；
    // 创建一个cookie并设置有效时间为 7天
    // 先删除
    $.cookie('yourView_userInfo', null);
    // $.cookie('yourView_userInfo',
    // '{"data":[{"content":"很不错嘛","id":1,"nickname":"纳尼"},{"content":"哟西哟西","id":2,"nickname":"小强"}]}',
    // {expires: 7});

    var userInfo = $.cookie(yourView_userInfo); // cookie存在 => 'the_value'
    if (userInfo != null && userInfo.length > 0) {
        return userInfo;
    } else {
        return "error";
    }
}

/**
 * 上传分享信息
 *
 * @param url
 * @param info
 * @returns
 */
function AjaxSubmit(url, info) {
    $.ajax({
        url: url,
        data: info,
        dataType: "json",
        type: "post",
        success: function (data) {
            if (data.Result == "success") {

            }
        }
    })
}

/**
 * 从后台加载id 赋值给vm2的info.id
 *
 * @returns
 */
function getIndexUploadId() {
	//加载id
	var url = "index/upload/getId";
	//重置id，重置时需要传入id，删除数据库存储的数据
	var urlReset = "index/upload/getId/" + vm2.info.id;
	
	var temp = url;
	if(vm2.info.id){
		temp = urlReset;
	}
    $.getJSON(temp, function (data) {
        if (data.status == 1) {
            vm2.info.id = data.data.id;
            vm2.info.imgUrlId = data.data.imgUrlId;
            vm2.info.commentId = data.data.commentId;
        }

    });
}

/**
 * 保存用户登陆地址等log信息
 *
 * @param info
 */
function saveLoginInfo(info) {

    $.ajax({
        url: "log/saveLoginInfo",
        data: info,
        dataType: "json",
        type: "post",
        success: function (data) {
            if (data.Result == "success") {
                alert("保存成功")
            }
        }
    })
}

/**
 * 表单校验
 *
 * @returns
 */
function validataForm(formId) {
    $('#' + formId).bootstrapValidator({
        message: '这个还是需要填写的...',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'title': {
                validators: {
                    notEmpty: {
                        message: '这个还是需要填写的...'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '多几个字吧，不要大于30个就行'
                    }
                }

            }
        }
    });
}

function toDetail(id){
	
}