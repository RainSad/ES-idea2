$(function () {
    initDeptDeptList(null);
});


//渲染左边部门树
var deptVm = new Vue({
    data: {
        deptData: [],
        deptParm: {}
    }
});

deptVm.$watch('deptData', function () {
    $('#treeDeptList').treeview({
        data: deptVm.deptData,
        //左侧部门树点击事件
        onNodeSelected: function (event, data) {
            var temp = new Array();
            temp.push(data);//创建json数组，兼容方法遍历
            //先清零,不然节点一直增加
            deptVm2.deptMap = [];
            getChlidrenData(temp)
            //$('#deptList').bootstrapTable('load', getChlidrenData(temp))
            //console.log(JSON.stringify(temp));

        }
    });
});
deptVm.$watch('deptParm', function () {

});

//右侧部门列表控制器
var deptVm2 = new Vue({
    data: {
        //存储点击坐车部门树后得到的子节点列表，用来渲染右边部门信息
        deptMap: [],
        //表示插件bootstrapTable是否被加载过，加载过则不再加载
        status: true
    }
});

deptVm2.$watch('deptMap', function () {
    if (deptVm2.status) {
        initDeptTable(deptVm2.deptMap);
        deptVm2.status = false;
    } else {
        $('#deptList').bootstrapTable('load', deptVm2.deptMap);
    }
});

/**
 * 添加部门弹出框控制
 */
var deptVm3 = new Vue({
    el: "#deptAdd",
    data: {
        parm: {
            apprName: '',
            name: '',
            deptAddress: '',
            deptPhone: '',
            parentId: '',
            seq: 1,
            remark: ''
        },
        isInitDeptList: false //是否下拉框被初始化过

    },
    methods: {
        deptAddSubmit: function () {
            // 验证输入
            var bootstrapValidator = $("#deptAdd").data("bootstrapValidator")
                .validate();
            // 得到校验结果 true or false
            var result = bootstrapValidator.isValid();
            if (result) {
                $('#delcfmModel').modal();
                $('#deptAdd').modal("hide");
            }
        },
        //显示下拉部门框
        parentDeptTreeList: function () {
            if (!this.isInitDeptList) {
                $('#treeDeptList2').treeview({
                    data: deptVm.deptData,
                    checkedIcon: "glyphicon glyphicon-check",
                    //部门树点击事件
                    onNodeSelected: function (event, data) {
                        deptVm3.parm.parentId = data.id;
                        deptVm3.parm.apprName = data.deptName;
                        $("#treeDeptList2").hide();
                    }
                });
                this.isInitDeptList = true;
            } else {
                $("#treeDeptList2").show();
            }

        }
    },
    mounted: function () {
        this.$nextTick(function () {
            // 加载表单校验
            validataForm("deptAdd");
        })
    }
});


/**
 * 弹出式确认框
 */
var deptVm4 = new Vue({
    el: "#confirmModel",
    components: {
        'confirm': confirmModel
    },
    methods: {
        submit: function () {
            AjaxSubmit("sys/dept/save", deptVm3.parm, deptVm4);
            //$('#delcfmModel').modal('hide');
        },
        cancel: function () {
            $('#delcfmModel').modal('hide');
            $('#deptAdd').modal("show");
        },
        //添加部门成功后回调方法
        success: function (data) {
            $('#delcfmModel').modal('hide');
            deptVm5.showModal({
                "header": "提示",
                "message": "添加部门成功!",
                "cancel": "取消",
                "confirm": "确定"
            });
        }
    },
    data: {
        info: {
            "header": "提示",
            "message": "确定提交吗？",
            "cancel": "取消",
            "confirm": "确定"
        }
    }
});

/**
 * 提示框
 */
var deptVm5 = new Vue({
    el: "#confirmModel2",
    components: {
        'confirm2': confirmModel2
    },
    methods: {
        submit: function () {
            $('#delcfmModel2').modal('hide')
        },
        cancel: function () {
            $('#delcfmModel2').modal('hide');
        },
        showModal: function (infoMessage) {
            deptVm5.info = infoMessage;
            $('#delcfmModel2').modal();
        }
    },
    data: {
        info: {
            "header": "提示",
            "message": "添加部门成功!",
            "cancel": "取消",
            "confirm": "确定"
        }
    }
});

/**
 * 部门修改弹出框数据显示控制器
 */
var deptVm6 = new Vue({
    el: "#deptEdit",
    data: {
        parm: {
            id: "",
            apprName: '',
            name: '',
            deptAddress: '',
            deptPhone: '',
            parentId: '',
            seq: 1,
            remark: ''
        },
        isInitDeptList: false //是否下拉框被初始化过
    },
    methods: {
        deptEditSubmit: function () {
            // 验证输入
            var bootstrapValidator = $("#deptEdit").data("bootstrapValidator")
                .validate();
            // 得到校验结果 true or false
            var result = bootstrapValidator.isValid();
            if (result) {
                $('#delcfmModel3').modal();
                $('#deptEdit').modal("hide");
            }
        },
        //显示下拉部门框
        parentEditTreeList: function () {
            if (!this.isInitDeptList) {
                $('#treeDeptList2Edit').treeview({
                    data: deptVm.deptData,
                    checkedIcon: "glyphicon glyphicon-check",
                    //部门树点击事件
                    onNodeSelected: function (event, data) {
                        deptVm6.parm.parentId = data.id;
                        deptVm6.parm.apprName = data.deptName;
                        $("#treeDeptList2Edit").hide();
                    }
                });
                this.isInitDeptList = true;
            } else {
                $("#treeDeptList2Edit").show();
            }

        },
        showEditModal: function (param) {
            //只能这样一个一个添加，不能直接相等。不然值虽然正确，但是页面上的值不能响应式的更改
            deptVm6.parm.apprName = param.apprName;
            deptVm6.parm.name = param.name;
            deptVm6.parm.deptAddress = param.deptAddress;
            deptVm6.parm.deptPhone = param.deptPhone;
            deptVm6.parm.parentId = param.parentId;
            deptVm6.parm.seq = param.seq;
            deptVm6.parm.remark = param.remark;
            deptVm6.parm.id = param.id;
            $("#deptEdit").modal();
        }
    },
    mounted: function () {
        this.$nextTick(function () {
            // 加载表单校验
            validataForm("deptEdit");
        })
    }
});

/**
 * 修改，删除弹出框的数据渲染
 */
var deptVm7 = new Vue({
    el: "#deptToolbar",
    data: {
        param: {},
        parentNode: {} // 获取到的父节点
    },
    methods: {
        editModalShow: function () {
            var selectData = $('#deptList').bootstrapTable("getSelections");
            if (selectData.length == 0) {
                deptVm5.showModal({
                    "header": "提示",
                    "message": "请勾选需要修改的部门!",
                    "cancel": "取消",
                    "confirm": "确定"
                });
            } else if (selectData.length > 1) {
                deptVm5.showModal({
                    "header": "提示",
                    "message": "只能勾选一个部门!",
                    "cancel": "取消",
                    "confirm": "确定"
                });
            } else if (selectData.length == 1) {
                deptVm7.param.parentId = getParentNodeId(deptVm.deptData, selectData[0].id);
                deptVm7.param.apprName = $.isEmptyObject($("#treeDeptList").treeview('getParent', selectData[0].nodeId)) ? "" : $("#treeDeptList").treeview('getParent', selectData[0].nodeId).deptName;
                deptVm7.param.name = selectData[0].deptName;
                deptVm7.param.id = selectData[0].id;
                deptVm7.param.seq = selectData[0].seq;
                deptVm7.param.deptAddress = selectData[0].deptAddress;
                deptVm7.param.deptPhone = selectData[0].deptPhone;
                deptVm7.param.remark = selectData[0].remark;
                //弹出修改框
                deptVm6.showEditModal(deptVm7.param);
            }
        },
        delModalShow: function () {
            var selectData = $('#deptList').bootstrapTable("getSelections");
            if (selectData.length == 0) {
                deptVm5.showModal({
                    "header": "提示",
                    "message": "请勾选需要删除的部门!",
                    "cancel": "取消",
                    "confirm": "确定"
                });
            } else if (selectData.length > 0) {
                //先清零
                deptVm9.param.ids = "";
                $(selectData).each(function (i, data) {
                    //再赋值
                    deptVm9.param.ids += "." + data.id;
                });
                //调用删除控制器
                $("#deptDel").modal();
            }
        }

    }
});

/**
 * 修改弹出提示框
 */
var deptVm8 = new Vue({
    el: "#confirmModel3",
    components: {
        'confirm3': confirmModel3
    },
    methods: {
        submit: function () {
            AjaxSubmit("sys/dept/update", deptVm6.parm, deptVm8);
            //$('#delcfmModel3').modal('hide');
        },
        cancel: function () {
            $('#delcfmModel3').modal('hide');
            $('#deptEdit').modal("show");
        },
        //修改部门成功后回调方法
        success: function (data) {
            $('#delcfmModel3').modal('hide');
            deptVm5.showModal({
                "header": "提示",
                "message": "修改部门成功!",
                "cancel": "取消",
                "confirm": "确定"
            });
        }
    },
    data: {
        info: {
            "header": "提示",
            "message": "确定修改吗？",
            "cancel": "取消",
            "confirm": "确定"
        }
    }
});

var deptVm9 = new Vue({
    el: "#deptDel",
    data: {
        param: {
            ids: "---"
        }
    },
    methods: {
        deptDelsubmit: function () {
            //弹出删除框
            console.log(deptVm9.param.ids);
            AjaxSubmit("sys/dept/del", {"ids": deptVm9.param.ids}, deptVm9);
        },
        success: function (result) {
            $('#deptDel').modal('hide');
            deptVm5.showModal({
                "header": "提示",
                "message": "删除部门成功!",
                "cancel": "取消",
                "confirm": "确定"
            });
        }
    }

});

/**
 * 渲染右侧部门列表
 * @param data
 */
function initDeptTable(data) {
    $('#deptList').bootstrapTable({
        data: data,
        toolbar: '#deptToolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 5,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [
            {
                title: '全选',
                field: 'select',
                //复选框
                checkbox: true,
                width: 25,
                align: 'center',
                valign: 'middle'
            },
            {
                title: 'ID',
                field: 'id',
                visible: false
            },
            {
                title: '部门名称',
                field: 'deptName',
                sortable: true
            },
            {
                title: '部门地址',
                field: 'deptAddress',
                sortable: true
            },
            {
                title: '部门号码',
                field: 'deptPhone',
            }
        ],
        locale: 'zh-CN'//中文支持,
    });
}

/**
 * 得到左侧部门树数据
 * @param parm
 */
function initDeptDeptList(parm) {
    var url = "sys/dept/tree.json";
    $.getJSON(url, parm, function (result) {
        if (result.ret) {
            deptVm.deptData = result.data;
        } else {
            deptVm.deptData = [];
            //TODO
            // 这里做没有数据的处理
        }

    });

}

/**
 * 遍历本节点和子节点
 * @param deptList
 */
function getChlidrenData(deptList) {
    if (deptList && deptList.length > 0) {
        $(deptList).each(function (i, dept) {
            var temp = deepCopy(dept);
            temp.nodes = [];
            //点击后更新的数据写到vue data中
            deptVm2.deptMap.push(temp);
            if (dept.nodes.length > 0) {
                getChlidrenData(dept.nodes);
            }
        })
    }
}

/**
 * 根据原始部门树数据获取父id。直接从渲染后的dom树的插件方法获取，parentid被修改过
 * @param nodeId
 */
function getParentNodeId(deptList, nodeId) {
    var parentId = "";
    if (deptList && deptList.length > 0) {
        $(deptList).each(function (i, dept) {
            if (dept.id == nodeId) {
                parentId = dept.parentId;
            }
            if (dept.nodes.length > 0) {
                getChlidrenData(dept.nodes);
            }
        })
    }
    return parentId;
}

/**
 * 深度copy数据
 * @param obj
 * @returns {*}
 */
function deepCopy(obj) {
    if (typeof obj != 'object') {
        return obj;
    }
    var temp = {};
    for (var attr in obj) {
        temp[attr] = deepCopy(obj[attr]);
    }
    return temp;
}

function AjaxSubmit(url, info, returnVm) {
    $.ajax({
        url: url,
        data: info,
        dataType: "json",
        type: "post",
        success: function (data) {
            returnVm.success(data)
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
            'deptName': {
                validators: {
                    notEmpty: {
                        message: '部门名称不能为空...'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: '字数不能超过15个字'
                    }
                }

            },
            'deptSeq': {
                validators: {
                    notEmpty: {
                        message: '显示顺序不能为空...'
                    }
                }

            },
            'deptAddr': {
                validators: {
                    notEmpty: {
                        message: '部门地址不能为空...'
                    },
                    stringLength: {
                        min: 2,
                        max: 60,
                        message: '字数不能超过30个字'
                    }
                }

            },
            'remark': {
                validators: {
                    notEmpty: {
                        message: '部门描述不能为空...'
                    },
                    stringLength: {
                        min: 2,
                        max: 300,
                        message: '字数不能超过150个字'
                    }
                }

            },
            'deptPhone': {
                validators: {
                    notEmpty: {
                        message: '部门电话不能为空...'
                    },
                    stringLength: {
                        min: 2,
                        max: 60,
                        message: '字数不能超过30个字'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
                        message: '电话号码不正确'
                    }
                }

            },
            'deptParent': {
                validators: {
                    notEmpty: {
                        message: '所属部门不能为空...'
                    },
                    stringLength: {
                        min: 2,
                        max: 60,
                        message: '字数不能超过30个字'
                    }
                }

            }

        }
    });
}

