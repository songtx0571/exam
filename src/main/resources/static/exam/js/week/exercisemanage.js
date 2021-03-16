$(function(){
    checkLogin1();
})

function checkLogin1(){
    $.ajax({
        "type" : 'post',
        "url": "/exam/question/getPrincipal",
        "data":null,
        "success":function(data){
            sessionStorage.Username=data;
        }
    });
}

function checkLogin(){
    var userName = sessionStorage.Username;
    if(userName==null){
        window.location="../";
    }
    $.ajax({
        "type" : 'post',
        "url": "/getPermissionByUserIdAndPermissionId",
        "data":{userName:userName,permissionId:1},
        "success":function(data){
            if(!data){
                layer.alert('该账号没有查看题库管理的权限，请换账号重试!', {icon : 2});
                window.location="../";
            }

        }
    });
}

var excise=(function (jQuery) {
    function PoisionAshow(x) {
        var unitObj = document.getElementById(x);
        $.ajax({
            url: "/exam/question/findPoisionA",
            type: "post",
            async: false,//是否异步请求
            datatype: "json",
            success: function (data) {
                $("#"+x).find("option:not(:first)").remove();
                for (var i = 0; i < data.length; i++) {
                    unitObj.options.add(new Option(data[i],data[i]));
                }
                PoisionB1show('PoisionA','PoisionB1')
            }
        })
    }

    function PoisionB1show(x,y) {
        var option1;
        var unitObj = document.getElementById(x);
        if(unitObj){
            option1=unitObj.value;
        }
        var unitObj2 = document.getElementById(y);
        $.ajax({
            url: "/exam/question/findPoisionB1",
            type: "post",
            data: {"option1": option1},
            async: false,//是否异步请求
            datatype: "json",
            success: function (data) {
                $("#"+y).find("option:not(:first)").remove();
                for (var i = 0; i < data.length; i++) {
                    unitObj2.options.add(new Option(data[i],data[i]));
                }
            }
        })
    }



    function impExcel() {
        var fileInput = $('#ufiles').get(0).files[0];
        if(fileInput){
            setblock('loadimg');
            $.ajaxFileUpload({
                url: "/impExcel",//请求地址
                datatype: "json",//数据格式
                fileElementId: "ufiles",
                type: "post",//请求方式
                async: false,//是否异步请求
                success: function (data, status) {
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != '') {
                            alert(data.error);
                            setnone('loadimg');
                        } else {
                            setnone('loadimg');
                            alert("导入成功");

                        }
                    }else {
                        setnone('loadimg');
                        alert("导入成功");
                        setnone('impExcel');
                        $("#ufiles").val('');
                        loodtable();
                    }
                },
                error: function (data, status, e) {
                    alert(e);
                }
            })
        }else{
            alert("请选择上传文件！");
        }

    }
    function clearInput() {
        $("#QuestionID").val('');
        $("#CourseID").val('');
        $("#ChapterID").val('');
        $("#PoisionA").val('');
        $("#PoisionB1").val('');
        $("#PoisionB3").val('');
        $("#Question").val('');
        $("#Answer").val('');
        $("#OptionA").val('');
        $("#OptionB").val('');
        $("#OptionC").val('');
        $("#OptionD").val('');
        $("#Remarks").val('');
        $("#QuestionTime").val('');
        $("#Type").val('');
    }
    function insertexam() {
        var CourseID=$("#CourseID").val();
        var ChapterID=$("#ChapterID").val();
        var PoisionA=$("#PoisionA").val();
        var PoisionB1=$("#PoisionB1").val();
        var PoisionB3=$("#PoisionB3").val();
        var Question=$("#Question").val();
        var Answer=$("#Answer").val();
        var OptionA=$("#OptionA").val();
        var OptionB=$("#OptionB").val();
        var OptionC=$("#OptionC").val();
        var OptionD=$("#OptionD").val();
        var Remarks=$("#Remarks").val();
        var QuestionTime=$("#QuestionTime").val();
        var Type=$("#Type").val();
        if(CourseID!=null && CourseID!=''&&ChapterID!=null && ChapterID!=''){
            $.ajax({
                url: "/exam/question/insert",
                type: "post",
                data: {
                    "dourseId":CourseID ,
                    "dhapterId":ChapterID ,
                    "PoisionA":PoisionA ,
                    "PoisionB1":PoisionB1 ,
                    "PoisionB3":PoisionB3 ,
                    "Question":Question ,
                    "Answer":Answer ,
                    "OptionA":OptionA ,
                    "OptionB":OptionB ,
                    "OptionC":OptionC ,
                    "OptionD":OptionD ,
                    "Remarks":Remarks ,
                    "QuestionTime":QuestionTime ,
                    "Type":Type ,
                },
                async: false,//是否异步请求
                datatype: "json",
                success: function (data) {
                    if(data=="success")alert("添加成功"),clearInput();

                }
            })

        }else alert("请输入内容");
    }
    function deleteexercise(x) {
        $.ajax({
            url: "/exam/question/delete",
            type: "post",
            data: {"QuestionID":x },
            async: false,//是否异步请求
            datatype: "json",
            success: function (data) {
                if(data=="success")alert("删除成功");//loodtable() ;
            }
        })
    }
    function updateexcise() {
        var QuestionID=$("#QuestionID").val();
        var CourseID=$("#CourseID").val();
        var ChapterID=$("#ChapterID").val();
        var PoisionA=$("#PoisionA").val();
        var PoisionB1=$("#PoisionB1").val();
        var PoisionB3=$("#PoisionB3").val();
        var Question=$("#Question").val();
        var Answer=$("#Answer").val();
        var OptionA=$("#OptionA").val();
        var OptionB=$("#OptionB").val();
        var OptionC=$("#OptionC").val();
        var OptionD=$("#OptionD").val();
        var Remarks=$("#Remarks").val();
        var QuestionTime=$("#QuestionTime").val();
        var Type=$("#Type").val();
        $.ajax({
            url: "/exam/question/update",
            type: "post",
            data: {
                "questionId":QuestionID ,
                "courseId":CourseID ,
                "chapterId":ChapterID ,
                "PoisionA":PoisionA ,
                "PoisionB1":PoisionB1 ,
                "PoisionB3":PoisionB3 ,
                "Question":Question ,
                "Answer":Answer ,
                "OptionA":OptionA ,
                "OptionB":OptionB ,
                "OptionC":OptionC ,
                "OptionD":OptionD ,
                "Remarks":Remarks ,
                "QuestionTime":QuestionTime ,
                "Type":Type ,
            },
            async: false,//是否异步请求
            datatype: "json",
            success: function (data) {
                if(data=="success"){
                    alert("修改成功");
                    setnone('exam');setnone('updatetitle');setnone('addtitle');setnone('addexambtn');setnone('updateexambtn');
                    clearInput();
                    //loodtable()
                }

            }
        })
    }

    return {
        impExcel:impExcel,
        PoisionAshow:PoisionAshow,
        PoisionB1show:PoisionB1show,
        deleteexercise:deleteexercise,
        insertexam:insertexam,
        clearInput:clearInput,
        updateexcise:updateexcise,
    };
}(jQuery));

//显示考试内容列表
function additable() {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        //触发事件
        var active = {
            offset: function (othis) {
                var type = othis.data('type')
                    , text = othis.text();
                layer.open({
                    type: 2
                    , title: '添加考试内容'
                    , offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    , id: 'layerDemo' + type //防止重复弹出
                    , area: ['430px', '230px']
                    , content:''
                });
            }
        };
        $('#layerDemo .layui-btn').on('click', function () {
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

    });
}
function loodtable() {
    if(sessionStorage.Username!=null&&sessionStorage.Username!=''){
        layui.use('table', function () {
            var table = layui.table;
            var unitObj = document.getElementById("PoisionAselect");
            var PoisionA=unitObj.value;
            var unitObj2 = document.getElementById("PoisionB1select");
            var PoisionB1=unitObj2.value;
            if(PoisionA=='请选择'){
                PoisionA='';
            }
            if(PoisionB1=='请选择'){
                PoisionB1='';
            }
            table.render({
                elem: '#test'
                , url: '/exam/question/findExercise?PoisionA=' + PoisionA+'&PoisionB1='+ PoisionB1
                , page:true
                , cols: [[
                    {field: 'questionId', width: 85, title: '序号',sort:true}
                    ,{field:'courseId',align:'center', width:100, title: '课程编号',sort:true}
                    ,{field:'chapterId',align:'center', width:100, title: '章节编号',sort:true}
                    ,{field:'poisionA',align:'center', width:90, title: '分类1'}
                    ,{field:'poisionB1',align:'center', width:90, title: '分类2'}
                    ,{field:'poisionB3',align:'center', width:100, title: '出题人',sort:true}
                    ,{field:'question',align:'center', width:500, title: '题目'}
                    ,{field:'answer',align:'center', width:100, title: '答案'}
                    ,{field:'optionA',align:'center', width:100, title: '选项A'}
                    ,{field:'optionB',align:'center', width:100, title: '选项B'}
                    ,{field:'optionC',align:'center', width:100, title: '选项C'}
                    ,{field:'optionD',align:'center', width:100, title: '选项D'}
                    ,{field:'remarks',align:'center', width:100, title: '备注'}
                    ,{fixed: 'right', title: '详情', toolbar: '#barDemo', width: 120}
                ]]

            });
            table.on('tool(demo)', function (obj) {
                var data = obj.data;
                if (obj.event === 'edit') {
                    $("#QuestionID").val(data.questionId);
                    $("#CourseID").val(data.courseId);
                    $("#ChapterID").val(data.chapterId);
                    $("#PoisionA").val(data.poisionA);
                    excise.PoisionB1show('PoisionA','PoisionB1');
                    $("#PoisionB1").val(data.poisionB1);
                    $("#PoisionB3").val(data.poisionB3);
                    $("#Question").val(data.question);
                    $("#Answer").val(data.answer);
                    $("#OptionA").val(data.optionA);
                    $("#OptionB").val(data.optionB);
                    $("#OptionC").val(data.optionC);
                    $("#OptionD").val(data.optionD);
                    $("#Remarks").val(data.remarks);
                    $("#QuestionTime").val(data.questionTime);
                    $("#Type").val(data.type);
                    setColor('PoisionA');
                    setColor('PoisionB1');
                    setblock('exam');
                    setblock('updateexambtn');
                    setblock('updatetitle');
                } else if (obj.event === 'del') {
                    let r = confirm("确定要删除吗");
                    if(r==true) excise.deleteexercise(data.questionId);
                }
            });
        });
    }else{
        let a = document.getElementsByClassName("button");
        for(let i =0 ;i<a.length;i++){
            a[i].style.display="none";
        }
        layui.use('table', function () {
            var table = layui.table;
            var unitObj = document.getElementById("PoisionAselect");
            var PoisionA=unitObj.value;
            var unitObj2 = document.getElementById("PoisionB1select");
            var PoisionB1=unitObj2.value;
            if(PoisionA=='请选择'){
                PoisionA='';
            }
            if(PoisionB1=='请选择'){
                PoisionB1='';
            }
            table.render({
                elem: '#test'
                , url: '/exam/question/findExercise?PoisionA=' + PoisionA+'&PoisionB1='+ PoisionB1
                , page:true
                , cols: [[
                    {field: 'questionId', width: 85, title: '序号',sort:true}
                    ,{field:'courseId',align:'center', width:100, title: '课程编号',sort:true}
                    ,{field:'chapterId',align:'center', width:100, title: '章节编号',sort:true}
                    ,{field:'poisionA',align:'center', width:90, title: '分类1'}
                    ,{field:'poisionB1',align:'center', width:90, title: '分类2'}
                    ,{field:'poisionB3',align:'center', width:100, title: '出题人',sort:true}
                    ,{field:'question',align:'center', width:500, title: '题目'}
                    ,{field:'answer',align:'center', width:100, title: '答案'}
                    ,{field:'optionA',align:'center', width:100, title: '选项A'}
                    ,{field:'optionB',align:'center', width:100, title: '选项B'}
                    ,{field:'optionC',align:'center', width:100, title: '选项C'}
                    ,{field:'optionD',align:'center', width:100, title: '选项D'}
                    ,{field:'remarks',align:'center', width:220, title: '备注'}
                ]]
            });
        });
    }
}
function setColor(selectId) {
    var unitObj = document.getElementById(selectId);
    if (unitObj != null&&selectId!=null) {
        var manager = unitObj.value;
        if(manager!='请选择'){
            $("#"+selectId).css("color","black");
        }else {
            $("#"+selectId).css("color","#9B9B9B");
        }
    }
}

function setblock(x) {
    window.document.getElementById(x).style.display='block'
}
function setnone(x) {
    window.document.getElementById(x).style.display='none'
}

function clearExampublish() {
    $("#pChapterID").val('');
    $("#pCourseID").val('');
    $("#pPoisionA").find("option:not(:first)").remove();
    $("#pPoisionB1").find("option:not(:first)").remove();
    $("#startdate").val('');
    $("#enddate").val('');
}