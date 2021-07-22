var path = "";
$(function () {
    showTable("");

    $("#keywordBtn").click(function () {
        var keyword = $("#keywordInp").val();
        showTable (keyword);
    });
    $('#keywordInp').bind('keypress', function(event) {
        if (event.keyCode == "13") {
            $("#keywordBtn").click();
        }
    })

});
//显示表格
function showTable (keyword) {
    var win = $(window).height();
    var height = win - 100;
    layui.use(['table', 'layedit'], function () {
        var table = layui.table;
            table.render({
                elem: '#demoTable'
                , height: height
                , url: path + '/exam/knowledge/all?type=1&searchWord='+keyword
                , toolbar: true
                , page: true //开启分页
                , limit: 50
                , limits: [50, 100, 150]
                , id: 'demoInfo'
                , cols: [[ //表头
                    {field: 'id', title: 'id', align: 'center', sort: true, hide: true}
                    , {field: 'title', title: '标题'}
                    , {field: 'keyword', title: '关键字'}
                    , {field: 'employeeName', title: '创建人', hide: true}
                    , {field: 'checkedEmployee', title: '审核人', hide: true,
                        templet: function (a) {
                            var nameArr = a.checkedEmployee
                            var name = "";
                            for (var item in nameArr) {
                                name += nameArr[item].checkEmployeeName+" "
                            }
                            return name.trim();
                        }}
                    , {field: 'passTime', title: '审核通过时间', sort: true, hide: true}
                    , {field: 'heat', title: '热度', sort: true, width: 100}
                    , {fixed: '', title: '操作', toolbar: '#tbBar', align: 'center', width: 175}
                ]]
                , parseData: function (res) {}
                , done: function (res, curr, count) {}
            });
        table.on('tool(testTable)', function (obj) {
            var data = obj.data;

            if (obj.event == "look") {

                $.ajax({
                    url:path+"/exam/knowledge/get",
                    data:{id:data.id},
                    dataType: "json",
                    success: function (data){
                        showTable("");
                    }
                });
                $("#id").val(data.id);
                $("#title").val(data.title);
                $("#keyword").val(data.keyword);
                $("#remark").val(data.remark);
                $("#content").html(data.content);
                layui.use('layer', function () { //独立版的layer无需执行这一句
                    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
                    layer.open({
                        type: 1
                        , id: 'develop_look' //防止重复弹出
                        , content: $(".develop_look")
                        , btnAlign: 'c' //按钮居中
                        , shade: 0.4 //不显示遮罩
                        , area: ['100%', '100%']
                        , yes: function () {
                        }
                    });
                });
            } else if (obj.event == "bank") {
                $.ajax({
                    url:path+"/exam/knowledge/back",
                    data:{id:data.id},
                    dataType: "json",
                    success: function (data){
                        showTable("");
                    }
                });
            }
        });

    });
}
//取消
function cancel () {
    layer.closeAll();
}