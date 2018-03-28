$(function () {
    initUserDeptListData();
    initUsersListData();
});

//渲染左边部门树
var UserVm = new Vue({
    data: {
        deptData: []
    }
});

UserVm.$watch('deptData', function () {
    $('#treeUserList').treeview({
        data: UserVm.deptData
    });
});

//右侧用户列表显示
var UserVm2 = new Vue({
    data: {
        deptLevel: '',
        usersData: []
    }
});
UserVm2.$watch('deptLevel', function () {
    //initUsersTable(UserVm2.deptLevel);
});

UserVm2.$watch('usersData', function () {
    initUsersTable();
});


function initUserDeptListData() {
    var url = "sys/dept/tree.json"
    $.getJSON(url, function (result) {
        UserVm.deptData = result.data;
        UserVm2.deptLevel = result.level;
    });
}

function initUsersListData() {
    var url = "sys/users/user.json"
    $.getJSON(url, function (result) {
        UserVm2.usersData = result;
    });
}


function initUsersTable() {
    $('#userList').bootstrapTable({
        url: 'sys/users/user.json',         //请求后台的URL（*）
        //data: data,
        method: 'get',                      //请求方式（*）
        toolbar: '#userToolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: {resultd: true},      //传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 5,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
                title: '登录名',
                field: 'username',
                sortable: true
            },
            {
                title: '姓名',
                field: 'deptId',
                sortable: true
            },
            {
                title: '手机号',
                field: 'phone',
            },
            {
                title: '邮箱',
                field: 'email'
            },
            {
                title: '注册日期',
                field: 'regTime',
                sortable: true
            },
            {
                title: '状态',
                field: 'status',
                align: 'center',
                //列数据格式化
                formatter: operateFormatter
            }
        ],
        locale: 'zh-CN',//中文支持,
        responseHandler: function (res) {
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        }
    });

    //三个参数，value代表该列的值
    function operateFormatter(value, row, index) {
        if (value == 2) {
            return '<i class="fa fa-lock" style="color:red"></i>'
        } else if (value == 1) {
            return '<i class="fa fa-unlock" style="color:green"></i>'
        } else {
            return '数据错误'
        }
    }

    //查询按钮事件
    $('#search_btn').click(function () {
        $('#userList').bootstrapTable('refresh', {url: 'sys/users/user.json'});
    })
}