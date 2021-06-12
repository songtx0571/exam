var otabLis = 0;
var temp = 0;
$(function () {
    checkLogin1();
})
//检查用户权限
function checkLogin1() {
    $.ajax({
        "type": 'post',
        "url": "/exam/manage/getPrincipal",
        "data": null,
        "success": function (data) {
            sessionStorage.Username = data;
            exam.loodtable();
        }
    });
}

var exam = (function (jQuery) {
    function insertpublishexam() {
        if (checkdate()) {
            var unitObj = document.getElementById("pPoisionA");
            var PoisionA = unitObj.value;
            var unitObj2 = document.getElementById("pPoisionB1");
            var PoisionB1 = unitObj2.value;
            var unitObj3 = document.getElementById("week");
            var week = unitObj3.value;
            var CourseID = $("#pCourseID").val();
            var ChapterID = $("#pChapterID").val();
            var startdate = $("#startdate").val();
            var enddate = $("#enddate").val();
            var examTime = $('#examTime').val();
            if (PoisionA != '' && PoisionB1 != '' && CourseID != '' && ChapterID != '' && startdate != '' && enddate != '' && week != '') {
                $.ajax({
                    url: "/exam/manage/addExampublish",
                    type: "post",
                    data: {
                        "PoisionA": PoisionA,
                        "PoisionB1": PoisionB1,
                        "CourseId": CourseID,
                        "ChapterId": ChapterID,
                        "startdate": startdate,
                        "enddate": enddate,
                        "week": week,
                        "examTime": examTime
                    },
                    async: false,//是否异步请求
                    datatype: "json",
                    success: function (data) {
                        if (data == "success") {
                            $.messager.alert("提示", "发布成功");
                            setnone('exampublish');
                            clearExampublish();
                            exam.loodtable();
                        } else if (data == "failweek") {
                            $.messager.alert("提示", "周期已存在");
                        }
                    }
                })
            } else $.messager.alert("提示", "参数不能为空");
        }
    }

    function loodtable() {
        if (sessionStorage.Username != null && sessionStorage.Username != '') {
            document.getElementById("bottom").style.width = "1482px";
            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: '#test'
                    , url: '/exam/manage/findExampublish'
                    , page: true
                    , cols: [[
                        {field: 'id', width: 70, title: '序号', sort: true}
                        , {field: 'courseId', align: 'center', width: 100, title: '课程编号', sort: true}
                        , {field: 'chapterId', align: 'center', width: 100, title: '章节编号', sort: true}
                        , {field: 'poisionA', align: 'center', width: 100, title: '分类1'}
                        , {field: 'poisionB1', align: 'center', width: 100, title: '分类2'}
                        , {
                            field: 'week', align: 'center', width: 100, title: '周', templet: function (row) {
                                return translateweek(row.week);
                            }
                        }
                        , {
                            field: 'state', align: 'center', width: 130, title: '状态', templet: function (row) {
                                if (row.state == 1) {
                                    return "进行中";
                                } else if (row.state == 0) {
                                    return "已结束";
                                } else {
                                    return "未进行";
                                }
                            }
                        }
                        , {field: 'startdate', align: 'center', width: 150, title: '开始时间'}
                        , {field: 'enddate', align: 'center', width: 150, title: '结束时间'}
                        , {
                            field: 'examTime', align: 'center', width: 150, title: '考试时长', templet: function (row) {
                                return row.examTime + "分钟";
                            }
                        }
                        , {fixed: 'right', title: '详情', toolbar: '#barDemo', width: 320}
                    ]]
                });
                table.on('tool(demo)', function (obj) {
                    var data = obj.data;
                    console.log(obj)
                    console.log(data)
                    if (obj.event === 'edit') {
                        setblock('exampublish');
                        setnone('ptitle');
                        setnone('addexambtn');
                        setblock('utitle')
                        setblock('updateexambtn');
                        excise.PoisionAshow('pPoisionA');
                        $("#pPoisionA").val(data.poisionA);
                        excise.PoisionB1show('pPoisionA', 'pPoisionB1');
                        $("#id").val(data.id);
                        $("#pCourseID").val(data.courseID);
                        $("#pChapterID").val(data.chapterID);
                        $("#pPoisionB1").val(data.poisionB1);
                        $("#startdate").val(data.startdate);
                        $("#enddate").val(data.enddate);
                        $("#week").val(data.week);
                        $("#examTime").val(data.examTime);
                        setColor('pPoisionA');
                        setColor('pPoisionB1');
                        setColor('week');
                        setColor('examTime');
                    } else if (obj.event === 'start') {
                        startexam(data.id);
                    } else if (obj.event === 'del') {
                        $.messager.confirm("确认", "确定删除吗", function (r) {
                            if (r) {
                                exam.deleteexam(data.id);
                            }
                        });
                    } else if (obj.event === 'finish') {
                        finishexam(data.id, data.week);
                    } else if (obj.event == 'startexam') {
                        Showtimes(data.id,data.week);
                    }
                });
            });
        } else {
            let a = document.getElementsByClassName("button");
            for (let i = 0; i < a.length; i++) {
                // a[i].style.display="none";
            }
            layui.use('table', function () {
                var table = layui.table;
                table.render({
                    elem: '#test'
                    , url: '/exam/question/getQuestion1'
                    , page: false
                    , cols: [[
                        {field: 'id', width: 79, title: '序号', sort: true}
                        , {field: 'courseId', align: 'center', width: 100, title: '课程编号', sort: true}
                        , {field: 'chapterId', align: 'center', width: 100, title: '章节编号', sort: true}
                        , {field: 'poisionA', align: 'center', width: 100, title: '分类1'}
                        , {field: 'poisionB1', align: 'center', width: 100, title: '分类2'}
                        , {
                            field: 'week', align: 'center', width: 100, title: '周', templet: function (row) {
                                return translateweek(row.week);
                            }
                        }
                        , {
                            field: 'state', align: 'center', width: 130, title: '状态', templet: function (row) {
                                if (row.state == 1) {
                                    return "进行中";
                                } else if (row.state == 0) {
                                    return "已结束";
                                } else {
                                    return "未进行";
                                }
                            }
                        }
                        , {field: 'startdate', align: 'center', width: 150, title: '开始时间'}
                        , {field: 'enddate', align: 'center', width: 150, title: '结束时间'}
                        , {
                            field: 'examTime', align: 'center', width: 150, title: '考试时长', templet: function (row) {
                                return row.examTime + "分钟";
                            }
                        }
                        , {fixed: 'right', title: '详情', toolbar: '#barDemo', width: 270}
                    ]]
                });
                table.on('tool(demo)', function (obj) {
                     var data = obj.data;
                    if (obj.event === 'startexam') {
                       // time();
                        Showtimes(data.id,data.week);
                    }
                });
            });
            document.getElementById("barDemo").innerHTML = "<a class='layui-btn layui-btn-xs' lay-event='startexam' style='background: #44ACFF'>考试</a>"

        }
    }

    function translateweek(x) {
        var cweeks = new Array('第一周', '第二周', '第三周', '第四周');
        return cweeks[x - 1];
    }

    function startexam(id) {
        $.ajax({
            url: "/exam/manage/startexam",
            type: "post",
            async: false,//是否异步请求
            data: {"id": id},
            datatype: "json",
            success: function (data) {
                if (data == "success") {
                    $.messager.alert("提示", "发布考试成功");
                    exam.loodtable();
                } else if (data == "fail") {
                    $.messager.alert("提示", "有考试正在进行中");
                } else if (data == "failstate") {
                    $.messager.alert("提示", "考试已结束，请勿再次发布");
                } else if (data == "failstate2") {
                    $.messager.alert("提示", "考试未开始，请先开始考试");
                }
            }
        })
    }

    function finishexam(id, week) {
        $.ajax({
            url: "/exam/manage/finishexam",
            type: "post",
            data: {
                "id": id,
                "week": week
            },
            datatype: "json",
            success: function (data) {
                if (data == "success") {
                    $.messager.alert("提示", "结束考试");
                    exam.loodtable();
                } else if (data == "failstate2") {
                    $.messager.alert("提示", "考试未开始，请先开始考试");
                } else if (data == "failstate") {
                    $.messager.alert("提示", "考试已结束");
                } else if (data == "no id") {
                    $.messager.alert("提示", "身份过期,请重新登陆");
                } else if (data == "error") {
                    $.messager.alert("提示", "程序出错,请联系技术部门!");
                }
            }
        })
    }


    function deleteexam(id) {
        $.ajax({
            url: "/exam/manage/deleteExampublish",
            type: "post",
            data: {
                "id": id
            },
            async: false,//是否异步请求
            datatype: "json",
            success: function (data) {
                if (data == "success") {
                    $.messager.alert("提示", "删除成功");
                    exam.loodtable();
                }
            }
        })
    }

    function updateexam() {
        if (checkdate()) {
            var id = $("#id").val();
            var CourseId = $("#pCourseID").val();
            var ChapterId = $("#pChapterID").val();
            var PoisionA = $("#pPoisionA").val();
            var PoisionB1 = $("#pPoisionB1").val();
            var startdate = $("#startdate").val();
            var enddate = $("#enddate").val();
            var week = $('#week').val();
            var examTime = $('#examTime').val();
            if (PoisionA != '' && PoisionB1 != '' && CourseId != '' && ChapterId != '' && startdate != '' && enddate != '' && PoisionA != '请选择' && PoisionB1 != '请选择') {
                $.ajax({
                    url: "/exam/manage/updExampublish",
                    type: "post",
                    data: {
                        "id": id,
                        "CourseId": CourseId,
                        "ChapterId": ChapterId,
                        "PoisionA": PoisionA,
                        "PoisionB1": PoisionB1,
                        "enddate": enddate,
                        "startdate": startdate,
                        "week": week,
                        "examTime": examTime
                    },
                    async: false,//是否异步请求
                    datatype: "json",
                    success: function (data) {
                        if (data == "success")
                            $.messager.alert("提示", "修改成功"),
                                setnone('exampublish'),
                                clearExampublish(),
                                exam.loodtable();
                    }
                })
            } else {
                $.messager.alert("提示", "请输入参数");
            }
        }
    }

    function time() {
        $(function () {
            $.ajax({
                url: "/exam/question/getExamTime",
                type: "post",
                datatype: "json",
                success: function (data) {
                    var m = data.examTime;
                    var s = 00;
                    Et = setInterval(function () {
                        if (m >= 0) {
                            if (s < 10) {
                                $('#time').html("剩余时间：" + m + ':0' + s);
                            } else {
                                $('#time').html("剩余时间：" + m + ':' + s);
                            }
                            s--;
                            if (s < 0) {
                                s = 59;
                                m--;
                            }
                            if (m == 0 && s < 1) {
                                window.location.href = "result";
                                sessionStorage.json = getjson();
                            }
                        }
                    }, 1000)//每1000ms循环一次（其实就是计数器的作用）
                }
            })
        })
    }

    function Showtimes(examId,week) {
        var UserName = sessionStorage.Username;
        $.ajax({
            url: '/exam/manage/selecttimes',
            datatype: 'json',
            type: 'post',
            data: {
                examId: examId,
                week:week
            },
            success: function (res) {
                if (res.code != 0) {
                    $.messager.alert("提示",res.msg);
                } else
                if (res.data<4) {
                    time();
                    document.getElementById('exam').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';
                    ShowQuestion();
                } else {
                    $.messager.alert("提示","考试次数用完");

                }
            }
        })
    }

    function ShowQuestion() {
        $.ajax({
            url: '/exam/question/getQuestion', //获取了当前试卷的题目信息
            type: "POST",
            datatype: 'json',
            success: function (result) {
                if (result == null || result == '') {
                    document.getElementById('exam').style.display = 'none';
                    document.getElementById('fade').style.display = 'none';
                    StopEt();
                } else {
                    data = result;
                    var answer = [];
                    var type = [];
                    var checkbox_num = 0;
                    var radio_num = 0;
                    var html = "<div class='title_tab' id='title_tab1'>" +
                        "<ul class='clearfix'>" +
                        "<li onclick=\"exam.tab1('candidates1',0)\" class='current'>第1题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',1)\">第2题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',2)\">第3题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',3)\">第4题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',4)\">第5题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',5)\">第6题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',6)\">第7题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',7)\">第8题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',8)\">第9题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',9)\">第10题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',10)\">第11题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',11)\">第12题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',12)\">第13题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',13)\">第14题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',14)\">第15题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',15)\">第16题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',16)\">第17题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',17)\">第18题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',18)\">第19题</li>" +
                        "<li onclick=\"exam.tab1('candidates1',19)\">第20题</li>" +
                        "</ul></div>";
                    for (var i = 0; i < data.length; i++) {
                        answer.push(data[i].answer);
                        type.push(data[i].type);
                        if (data[i].type == 1) {
                            radio_num = radio_num + 1;
                            var index = radio_num;
                            html += "<div id='candidates1_" + i + "'class='tab_content' style='height: 400px;'>";
                            html += "<div>" + (i + 1) + "." + data[i].question + "</div>";
                            html += "<div><input type='radio' name='radio_" + index + "' value='A'>A." + data[i].optionA + "</input><br>";
                            html += "<input type='radio' name='radio_" + index + "' value='B'>B." + data[i].optionB + "</input><br></div></div>";
                        } else if (data[i].type == 2) {
                            checkbox_num = checkbox_num + 1;
                            var index = checkbox_num;
                            html += "<div id='candidates1_" + i + "'class='tab_content' style='height: 400px;'>";
                            html += "<div>" + (i + 1) + "." + data[i].question + "</div>";
                            html += "<div><input type='checkbox' name='checkbox_" + index + "' value='A'>A." + data[i].optionA + "</input><br>";
                            html += "<input type='checkbox' name='checkbox_" + index + "' value='B'>B." + data[i].optionB + "</input><br>";
                            html += "<input type='checkbox' name='checkbox_" + index + "' value='C'>C." + data[i].optionC + "</input><br>";
                            html += "<input type='checkbox' name='checkbox_" + index + "' value='D'>D." + data[i].optionD + "</input><br></div></div>";
                        } else {
                            radio_num = radio_num + 1;
                            var index = radio_num;
                            html += "<div id='candidates1_" + i + "'class='tab_content' style='height: 400px;'>";
                            html += "<div>" + (i + 1) + "." + data[i].question + "</div>";
                            html += "<div><input type='radio' name='radio_" + index + "' value='A'>A." + data[i].optionA + "</input><br>";
                            html += "<input type='radio' name='radio_" + index + "' value='B'>B." + data[i].optionB + "</input><br>";
                            html += "<input type='radio' name='radio_" + index + "' value='C'>C." + data[i].optionC + "</input><br>";
                            html += "<input type='radio' name='radio_" + index + "' value='D'>D." + data[i].optionD + "</input><br></div></div>";

                        }
                    }
                    $('#tab_candidates1').html(html);
                    $('#candidates1_0').removeClass("tab_content");
                    $('#candidates1_0').addClass("tab_content current");
                    sessionStorage.answer = JSON.stringify(answer);

                    sessionStorage.type = JSON.stringify(type);
                    temp = 0;

                    //将考试记录传至后端
                    var questionId = 1;
                    var userName = sessionStorage.Username;
                    var day2 = new Date();
                    day2.setTime(day2.getTime());
                    var year = day2.getFullYear();
                    var month = (day2.getMonth() + 1);
                    var day = day2.getDate();
                    var time = year + "-" + month + "-" + day;//显示时间：年-月-日
                    var cycle = year + "-" + month;
                    var times;

                    var week;//获取当前week
                    $.ajax({
                        url: "/exam/manage/getExamWeek",//请求地址
                        datatype: "json",//数据格式
                        data: {
                            "week": week,
                        },
                        type: "post",//请求方式
                        success: function (data) {
                            week = data.week;
                        }
                    });


                    $.ajax({
                        url: "/exam/manage/getExamTimes",//请求地址
                        datatype: "json",//数据格式
                        data: {
                            "userName": userName,
                            "cycle": cycle,
                            "week": week,
                            "questionId": questionId,
                            "times": times
                        },//此处不应有questionId,但是后端sql已经把它删了，为了节约时间，这里不管他了
                        type: "post",//请求方式
                        success: function (data) {
                            times = data.times;
                            console.log(times);
                            if (times != "0" && times != "1" && times != "2" && times != "3" && times != "4") {
                                // alert("无数据");
                                //无数据，则添加
                                times = 1;
                                $.ajax({
                                    url: "/exam/manage/insertExamHis",//请求地址
                                    datatype: "json",//数据格式
                                    data: {
                                        "userName": userName,
                                        "cycle": cycle,
                                        "week": week,
                                        "questionId": questionId,
                                        "times": times,
                                    },
                                    type: "post",//请求方式
                                    success: function (data) {

                                    }
                                });
                            } else {
                                //次数未超过限制，插入新数据
                                times++;
                                $.ajax({
                                    url: "/exam/manage/insertExamHis",//请求地址
                                    datatype: "json",//数据格式
                                    data: {
                                        "userName": userName,
                                        "cycle": cycle,
                                        "week": week,
                                        "questionId": questionId,
                                        "times": times,
                                    },
                                    type: "post",//请求方式
                                    success: function (data) {

                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

    }

    function tab1(obj, id) {
        otabLis = document.getElementById("title_tab1").getElementsByTagName("li");
        var m = $("#tab_" + obj + " li");
        m.removeClass("current");
        m.eq(id).addClass("current");
        var c = $("#tab_" + obj + " .tab_content");
        c.removeClass("current");
        c.eq(id).addClass("current");
        for (var i = 0; i < otabLis.length; i++) {
            if (otabLis[i].className == "current") {
                temp = i;
            }
        }
    }

    function tabNext1() //下一题
    {
        otabLis = document.getElementById("title_tab1").getElementsByTagName("li");
        temp++;
        // alert(temp);
        // alert(otabLis.length);
        if (temp >= otabLis.length) {
            temp = 0;
        }
        for (var i = 0; i < otabLis.length; i++) {
            otabLis.item(i).className = "";
        }
        otabLis.item(temp).className = "current";
        tab1('candidates1', temp);
    }

    function tabPrev1() //上一题
    {
        otabLis = document.getElementById("title_tab1").getElementsByTagName("li");
        temp--;
        // alert(temp);
        // alert(otabLis.length);
        if (temp < 0) {
            temp = otabLis.length - 1;
        }
        for (var i = 0; i < otabLis.length; i++) {
            otabLis.item(i).className = "";
        }
        otabLis.item(temp).className = "current";
        tab1('candidates1', temp);
    }


    return {
        insertpublishexam: insertpublishexam,
        loodtable: loodtable,
        deleteexam: deleteexam,
        updateexam: updateexam,
        startexam: startexam,
        finishexam: finishexam,
        tabNext1: tabNext1,
        tabPrev1: tabPrev1,
        tab1: tab1
        // loodtableExam:loodtableExam,
    };
}(jQuery));

//显示考试内容列表
function clearExampublish() {
    $("#pChapterID").val('2');
    $("#pCourseID").val('1');
    $("#enddate").val('');
    $("#startdate").val('');
    $("#week").val('');
    $("#pPoisionA").find("option:not(:first)").remove();
    $("#pPoisionB1").find("option:not(:first)").remove();
}


function checkdate() {
    var startdate = $("#startdate").val();
    var enddate = $("#enddate").val();
    if (startdate != null && startdate != '') {
        if (startdate >= enddate) {
            $.messager.alert("提示", "结束日期需要大于开始日期");
            $("#enddate").val('');
            return false;
        } else return true;
    } else {
        alert("请设置开始日期");
        $("#enddate").val('');
        return false;
    }
}

$(function () {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#achievementTime' //指定元素
            , zIndex: 99999999
            , trigger: 'click'
            , type: 'month'
            , value: new Date()
        });
    });
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    if (month < 10) {
        month = "0" + month
    }
    var data = year + "-" + month;
    findAchievementRecord(data);
})
var achievementOTable;

function showAchievementRecord() {
    document.getElementById("achievementRecord").style.display = "block";
    document.getElementById("banner").style.display = "block";
}

function showExamHistory() {
    document.getElementById("examHistory").style.display = "block";
    document.getElementById("fadeExam").style.display = "block";
    layui.use('table', function () {
        var table = layui.table;
        var userName = sessionStorage.Username;
        table.render({
            elem: '#testExam'
            , url: '/exam/manage/findExamHis?userName=' + userName
            , page: true
            , cols: [[
                {field: 'zizengExam', align: 'center', width: 90, title: '序号', templet: '#zizengExam'}
                , {field: 'name', align: 'center', width: 200, title: '人员姓名', sort: true}
                , {field: 'cycle', align: 'center', width: 235, title: '考试年月', sort: true}
                , {field: 'week', align: 'center', width: 200, title: '考试周数', sort: true}
                , {field: 'times', align: 'center', width: 200, title: '考试次数', sort: true}
                , {fixed: 'right', align: 'center', title: '详情', toolbar: '#barDemoExam', width: 200}
            ]]
        });
        table.on('tool(demoExam)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'detail') {
                // layer.msg('查看操作');
                document.getElementById('examHistory').style.display = 'none';
                document.getElementById('fadeExam').style.display = 'none';
                document.getElementById('exerciseExam').style.display = 'block';
                document.getElementById('fade').style.display = 'block';
                var UserName = userName;
                var Cycle = data.cycle;
                var Week = data.week;
                var times = data.times;
                // showByExam();
                mt = 0;
                console.log(UserName, Cycle, Week, times);
                $.ajax({
                    url: "/exam/manage/showByExam",
                    type: "POST",
                    datatype: 'json',
                    data:
                        {
                            UserName: UserName,
                            Cycle: Cycle,
                            Week: Week,
                            times: times
                        },
                    success: function (result) {
                        data = result;
                        console.log(data);
                        var html = "<div class='title_tab' id='title_tab' style='overflow:auto'><ul class='clearfix'>";
                        for (var j = 0; j < data.length; j++) {
                            var index = j + 1;
                            html += "<li onclick=\"tab('candidates'," + j
                                + ")\" id='li" + j + "'>第" + index + "题</li>";
                        }
                        html += "</ul></div>";
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].type == 1) {
                                var index = i + 1;
                                html += "<div id='candidates_"
                                    + i
                                    + "'class='tab_content' style='height: 400px;'>";
                                html += "<div>"
                                    // + data[i].questionID + "."
                                    + data[i].question + "</div>";
                                html += "<div><input type='radio' name='radio_"
                                    + index + "' value='A'>A."
                                    + data[i].optionA + "</input><br>";
                                html += "<input type='radio' name='radio_" + index
                                    + "' value='B'>B." + data[i].optionB
                                    + "</input><br></div>";
                                html += "<div><input type='button' class='button' value='查看答案' onclick='answershow("
                                    + i
                                    + ")'/></div><div id='answer"
                                    + i
                                    + "' style='display: none;'>答案和解析：<br>"
                                    + data[i].remarks
                                    + "<br>答案:"
                                    + data[i].answer + "</div></div>";
                            } else if (data[i].type == 2) {
                                var index = i + 1;
                                html += "<div id='candidates_"
                                    + i
                                    + "'class='tab_content' style='height: 400px;'>";
                                html += "<div>"
                                    // + data[i].questionID + "."
                                    + data[i].question + "</div>";
                                html += "<div><input type='checkbox' name='radio_"
                                    + index + "' value='A'>A."
                                    + data[i].optionA + "</input><br>";
                                html += "<input type='checkbox' name='radio_"
                                    + index + "' value='B'>B."
                                    + data[i].optionB + "</input><br>";
                                html += "<input type='checkbox' name='radio_"
                                    + index + "' value='C'>C."
                                    + data[i].optionC + "</input><br>";
                                html += "<input type='checkbox' name='radio_"
                                    + index + "' value='D'>D."
                                    + data[i].optionD + "</input><br></div>";
                                html += "<div><input type='button' class='button' value='查看答案' onclick='answershow("
                                    + i
                                    + ")'/></div><div id='answer"
                                    + i
                                    + "' style='display: none;'>答案和解析：<br>"
                                    + data[i].remarks
                                    + "<br>答案:"
                                    + data[i].answer + "</div></div>";
                            } else {
                                var index = i + 1;
                                html += "<div id='candidates_"
                                    + i
                                    + "'class='tab_content' style='height: 400px;'>";
                                html += "<div>"
                                    // + data[i].questionID + "."
                                    + data[i].question + "</div>";
                                html += "<div><input type='radio' name='radio_"
                                    + index + "' value='A'>A."
                                    + data[i].optionA + "</input><br>";
                                html += "<input type='radio' name='radio_" + index
                                    + "' value='B'>B." + data[i].optionB
                                    + "</input><br>";
                                html += "<input type='radio' name='radio_" + index
                                    + "' value='C'>C." + data[i].optionC
                                    + "</input><br>";
                                html += "<input type='radio' name='radio_" + index
                                    + "' value='D'>D." + data[i].optionD
                                    + "</input><br></div>";
                                html += "<div><input type='button' class='button' value='查看答案' onclick='answershow("
                                    + i
                                    + ")'/></div><div id='answer"
                                    + i
                                    + "' style='display: none;'>答案和解析：<br>"
                                    + data[i].remarks
                                    + "<br>答案:"
                                    + data[i].answer + "</div></div>";
                            }
                        }
                        $('#tab_candidates').html(html);
                        $('#li0').addClass("current");
                        $('#candidates_0').removeClass("tab_content");
                        $('#candidates_0').addClass("tab_content current");
                        index1 = 0;
                    }
                });
            }
        });
    });
}

function tab(obj, id) {
    // clearTimeout(p);
    // clearTimeout(x);
    mt = 0;
    // ExCount();
    otabLis = document.getElementById("title_tab").getElementsByTagName("li");
    var m = $("#tab_" + obj + " li");
    m.removeClass("current");
    m.eq(id).addClass("current");
    var c = $("#tab_" + obj + " .tab_content");
    c.removeClass("current");
    c.eq(id).addClass("current");
    for (var i = 0; i < otabLis.length; i++) {
        if (otabLis[i].className == "current") {
            index1 = i;
        }
    }
}

function closeAchievementRecord() {
    document.getElementById("achievementRecord").style.display = "none";
    document.getElementById("banner").style.display = "none";
}

function changeAchievementRecord() {
    if (achievementOTable) {
        achievementOTable.fnClearTable();
        achievementOTable.fnDestroy();
    }
    var cycle = $('#achievementTime').val();
    findAchievementRecord(cycle);
}

function findAchievementRecord(cycle) {
    achievementOTable = $('#DataTable').dataTable({
        "oLanguage": {
            "sProcessing": "正在抓取数据，请稍后...",
            "sLengthMenu": "显示_MENU_条 ",
            "sZeroRecords": "没有您要搜索的内容",
            "sInfo": "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(全部记录数 \_MAX\_ 条)",
            "sInfoPostFix": "",
            "sSearch": " ",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": " 上一页",
                "sNext": " 下一页",
                "sLast": " 最后一页 "
            }
        },
        "fnPreDrawCallback": function (oSettings) {
            $('.dataTables_filter input').attr({'name': 'search', 'placeholder': '模糊搜索'});//提示
        },
        "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'B><'col-xs-6'f>r>" +
            "t" +
            "<'row'<'col-my-1'i><'col-my-1'p>>",
        "autoWidth": false,
        "paging": true,
        "lengthMenu": [10],
        "sAjaxSource": "/exam/manage/findAchievementRecords",//通过ajax实现分页的url路径。
        "fnServerData": function (sSource, aoData, fnCallback) {
            $.ajax({
                'type': 'post',
                "url": sSource,
                "data": {"cycle": cycle},
                "success": function (Json) {
                    fnCallback(Json);
                }
            });
        },
        "columns": [
            {data: "userName", "width": "10%"},
            {data: "name", "width": "10%"},
            {data: "week1Result1", "width": "5%"},
            {data: "week1Result2", "width": "5%"},
            {data: "week1Result3", "width": "5%"},
            {data: "week1Result4", "width": "5%"},
            {data: "week2Result1", "width": "5%"},
            {data: "week2Result2", "width": "5%"},
            {data: "week2Result3", "width": "5%"},
            {data: "week2Result4", "width": "5%"},
            {data: "week3Result1", "width": "5%"},
            {data: "week3Result2", "width": "5%"},
            {data: "week3Result3", "width": "5%"},
            {data: "week3Result4", "width": "5%"},
            {data: "week4Result1", "width": "5%"},
            {data: "week4Result2", "width": "5%"},
            {data: "week4Result3", "width": "5%"},
            {data: "week4Result4", "width": "5%"}
        ]
    });
}

function downAchievementRecord() {
    var cycle = $('#achievementTime').val();
    window.open("/exam/manage/findAchievementRecords?cycle=" + cycle)
}