var path = "";
$(function () {
    showTable('0');
});
//打开添加页面
function addPage() {
    $(".tableTitle").html("添加");
    $("#saveBtn").css("display","revert");
    $("#id").val("");
    $("#title").val("");
    $("#keyword").val("");
    $("#content").val("");
    $("#remark").val("");
    $("#customized-buttonpane").html("");
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , id: 'develop_insert' //防止重复弹出
            , content: $(".develop_insert")
            , btnAlign: 'c' //按钮居中
            , shade: 0.4 //不显示遮罩
            , area: ['100%', '100%']
            , yes: function () {
            }
        });
    });
}
//保存
function save() {
    let knowledge = {};
    knowledge.id = $("#id").val();
    knowledge.title = $("#title").val();
    knowledge.keyword = $("#keyword").val();
    knowledge.content = $("#customized-buttonpane").html();
    knowledge.remark = $("#remark").val();
    $.ajax({
        "type" : 'post',
        "url": path + "/exam/knowledge/save",
        data: JSON.stringify(knowledge),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        "success":function(data){
            cancel();
        }
    });
    $.ajax({
        url:"/exam/operation/send",
        dataType: "json",//数据格式
        type: "post",//请求方式
        data: {employeeId:"",verb:"创建",content:"编辑库,标题为 "+$("#title").val()+" ",type:"exam",remark:"",createTime:""},
        success:function(data){}
    });
}
//显示查询到的内容
function showTable(type) {
    var win = $(window).height();
    var height = win - 100;
    layui.use(['table', 'layedit'], function () {
        var table = layui.table;
        table.render({
            elem: '#demoTable'
            , height: height
            , url: path + '/exam/knowledge/all?type=0'
            , page: true //开启分页
            , limit: 50
            , limits: [50, 100, 150]
            , id: 'demoInfo'
            , cols: [[ //表头
                {field: 'id', title: 'id', align: 'center', sort: true, hide: true}
                , {field: 'title', title: '标题'}
                , {field: 'employeeName', title: '创建人'}
                , {field: 'keyword', title: '关键字',width: 300}
                , {field: 'remark', title: '备注',width: 300}
                , {field: 'id', title: '审核人', toolbar: '#tbStatusBar'}//0不通过,1通过
                , {field: 'createTime', title: '创建时间', sort: true}
                , {fixed: '', title: '操作', toolbar: '#tbBar', align: 'center', width: 210}
            ]]
            , parseData: function (res) {}
            , done: function (res, curr, count) {}
        });
        table.on('tool(testTable)', function (obj) {
            var data = obj.data;
            $("#saveBtn").css("display","revert");
            $(".id").val(data.id);
            $(".title").val(data.title);
            $(".keyword").val(data.keyword);
            $(".remark").val(data.remark);
            $("#customized-buttonpane").html(data.content);
            $(".content").html(data.content);
            $(".exmName").text(data.employeeName);
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var time = year+"-"+month+"-"+day;
            $(".exmDate").text(time);
            if (obj.event == "check") {
                $.ajax({
                    url:path+"/exam/knowledge/get",
                    data:{
                        id:data.id
                    },
                    success: function (data){
                        let checkedEmployee= data.data.checkedEmployee;
                        let html="";
                        if(checkedEmployee.length>0){
                            for (let i=0;i<checkedEmployee.length;i++){
                                html+="<span style='margin-right: 20px'>"+checkedEmployee[i].checkEmployeeName+"</span>"
                            }
                        }else{
                            html="无"
                        }
                        $("#checkedUser").html(html);
                    }
                });
                $(".tableTitle").html("审核内容  ");
                layui.use('layer', function () { //独立版的layer无需执行这一句
                    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
                    layer.open({
                        type: 1
                        , id: 'develop_examine' //防止重复弹出
                        , content: $(".develop_examine")
                        , btnAlign: 'c' //按钮居中
                        , shade: 0.4 //不显示遮罩
                        , area: ['100%', '100%']
                        , yes: function () {
                        }
                    });
                });
            } else if (obj.event == "edit") {
                $(".tableTitle").html("修改");
                layui.use('layer', function () { //独立版的layer无需执行这一句
                    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
                    layer.open({
                        type: 1
                        , id: 'develop_upd' //防止重复弹出
                        , content: $(".develop_insert")
                        , btnAlign: 'c' //按钮居中
                        , shade: 0.4 //不显示遮罩
                        , area: ['100%', '100%']
                        , yes: function () {
                        }
                    });
                });
            } else if (obj.event == "look") {
                $(".tableTitle").html("查看");
                console.log("lookData",data);
                console.log("lookData",data);
                let knowledge={};
                knowledge.id=data.id;
                knowledge.heat=data.heat+1;
                $.ajax({
                    "type" : 'post',
                    "url": path + "/exam/knowledge/save",
                    data: JSON.stringify(knowledge),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    "success":function(res){
                        console.log(res);
                    }
                });
                $("#saveBtn").css("display","none");
                layui.use('layer', function () { //独立版的layer无需执行这一句
                    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
                    layer.open({
                        type: 1
                        , id: 'develop_look' //防止重复弹出
                        , content: $(".develop_insert")
                        , btnAlign: 'c' //按钮居中
                        , shade: 0.4 //不显示遮罩
                        , area: ['100%', '100%']
                        , yes: function () {
                        }
                    });
                });
            } else if (obj.event == "del") {
                $.ajax({
                    url: path + "/exam/knowledge/delete",
                    method: "get",
                    data: {id: data.id},
                    success: function (data) {
                        layer.alert("删除成功");
                        showTable('0');
                    }
                });
            }
        });
    });
}
//审核通过
function adopt() {
    $.ajax({
        url: path + "/exam/knowledge/check",
        method: "get",
        data: {id: $(".id").val()},
        dataType: 'json',
        success: function (data) {
            layer.closeAll();
            layer.alert(data.msg);
        }
    });
    $.ajax({
        url:"/exam/operation/send",
        type: "post",//请求方式
        data: {employeeId:"",verb:"审核",content:"编辑库,标题为 "+$("#title").val()+"",type:"exam",remark:"",createTime:""},
        success:function(data){
            showTable('0');
        }
    });
}
//审核驳回
function reject() {
    layer.closeAll();
    showTable('0');
}
//取消
function cancel() {
    $("#id").val("");
    $("#title").val("");
    $("#keyword").val("");
    $("#content").val("");
    $(".remark").val("");
    layer.closeAll();
    showTable('0');
}