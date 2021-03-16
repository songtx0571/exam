<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>杭州浩维管理平台</title>
	<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../DataTables-1.10.15/media/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="../js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../js/easyui/themes/icon.css">

    <script type="text/javascript" src="../js/layui/layui.all.js"></script>
    <script type="text/javascript" src="../js/ajaxfileupload.js"></script>
    <script src="../layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/week/exercisemanage.js"></script>
    <script type="text/javascript" src="../js/week/exammanage.js"></script>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta charset="utf-8">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../css/Inform.css">
	<link href="../css/logo.css" rel="stylesheet"/>

</head>
<style>
    #warpper {
        border: #dfdfdf 1px solid;
        padding: 10px;
        width: 1200px;
        margin: 20px auto;
        background: #fff;
        border-radius: 4px;
        height: 600px;
    }

    .title_tab {
        border-bottom: #dedede 1px solid;
        margin: 0 -10px 5px;
        height: 40px;
    }

    .title_tab ul {
        font-family: "华文黑体", "黑体", "Arial";
        color: #FFFFFF;
        float: left;
        width: 1200px;
        height: 30px;
    }

    .title_tab ul li {
        float: left;
        position: relative;
        top: 1px;
        background: #44ACFF;
        border: #dedede 1px solid;
        width: 50px;
        text-align: center;
        line-height: 28px;
        margin: 0 4px;
        cursor: pointer;
        border-radius: 4px;
    }

    .title_tab ul li.current {
        background: #E27635;
        border-bottom: #E27635 1px solid;
        border-left: #dedede 1px solid;
        border-right: #dedede 1px solid;
        border-top: #dedede 1px solid;
    }
    .tab_content {
        display: none;
        line-height: 36px;
        font-size: 16px;
    }

    .tab_content.current {
        display: block;
    }
    .white_content {
        display: none;
        position: fixed;
        top: 10%;
        left: 10%;
        width: 80%;
        height: 80%;
        padding: 20px;
        border: 10px solid #DFF3FC;
        background-color: white;
        z-index: 1001;
        overflow: auto;
    }
    .black_overlay {
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 200%;
        background-color: black;
        z-index: 1000;
        -moz-opacity: 0.8;
        opacity: .80;
        filter: alpha(opacity = 88);
    }

    .black_overlay-exam{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=88);
    }
    .white_content-exam {
        display: none;
        position: absolute;
        top: 25%;
        left: 25%;
        width: 60%;
        height: 55%;
        padding: 20px;
        border: 10px solid #0ac8fa;
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
    #bottom {
        width: 1000px;
    }

   
    th .layui-table-cell {
        text-align: center;
    }

    th .layui-table-cell {
        text-align: center;
    }

    select {
        appearance: none;
        -moz-appearance: none;
        -webkit-appearance: none;
        background: url("../img/down.png") no-repeat scroll right 5px center transparent;
        background-size: 14px 14px;
        border-radius: 4px;
        outline: none;
        border: 1px #9B9B9B solid;
        color: #9B9B9B;
        text-align-last: center;
        width: 90px;
    }

    option {
        text-align: center;
        color: black;
    }

    .itable td {
        width: 100px;
        height: 32px;
    }

    .itable input {
        width: 100px;
    }
    #banner{
		width: 100%;
    	height: 100%;
    	z-index: 1000;
    	position: fixed;
    	top: 0;
    	right: 0;
    	bottom: 0;
    	left: 0;
    	overflow: hidden;
		outline: 0;
    	-webkit-overflow-scrolling: touch;
		filter: alpha(opacity=20);  
		background-color: rgba(0,0,0,0.5);
		display: none;
	}
	#achievementRecord{
		 z-index: 1001;
		display:none;
		position: absolute;
		border-radius: 4px;
		top: 10%;
		left: 12%;
		width: 1400px;
		height: 600px;
		background: #FFFFFF;
    	box-shadow: 0 0 8px 0 rgba(74, 144, 226, 0.80);
	}
	#achievementRecord table{
		border-collapse: collapse;
		border-spacing: 0;
		width: 98%;
		margin: auto;
		margin-top: 10px;
	}
	#achievementRecord table td,#achievementRecord table th{
		border-width: 1px;
		border-style: solid;
		border-color: #e6e6e6
	}
	table.dataTable thead th, table.dataTable thead td {
    	padding: 5px 18px 5px 5px;
    }
    #achievementTimeDIV{
    	text-align: center;
    	margin-top: 10px;
    }
    #findAchievementRecord{
    	background: #44ACFF;
    	height: 30px;
    	width:60px;
    	line-height: 30px;
    	padding: 0 5px;
    	font-size: 16px;
    	border: 1px solid #44ACFF;
    	cursor: pointer;
    	color: #fff;
    	margin-left: 15px;
    	border-radius: 4px;
	}
	#achievementTime{
		height:30px;
		width: 100px;
		font-size: 16px;
		padding-left: 10px;
		background-image: url(../img/calendar.svg);
		background-size: 24px 24px;
		background-repeat: no-repeat;
		background-position: 90% 50%;
		border: 1px solid #44ACFF;
	}
	.dataTables_length select{width:50px;text-align-last: left;padding-left: 5px;}

    .cov{
        z-index:500;
        display:none;
        position:absolute;
        text-align:center;
        font-size: 16px;
        box-shadow:0px 0px 5px black;
    }
    .con{
        position: fixed;
        z-index:1100;
        width:600px;
        height:200px;
        background-color:white;
        position:fixed;
        right:33%;
        top:33%;
        border-radius: 10px;
        box-shadow: 0px 0px 5px black;
    }
    .ptitle{
        width:100%;
        height:35px;
        background-color:#3daae9;
        color:white;
        line-height:35px;
        border-radius:10px 10px 0px 0px;
    }
    .dbt{
        border-radius: 5px;
        width: 70px;
        height:30px;
        background-color:#3daae9;
        right:20px;
        bottom:20px;
        position:absolute;
        line-height:30px;
        color:white;
        display: block;
    }

</style>
	<script type="text/javascript">
		var minutes = 0;
		var seconds = 0;
		var hours = 0;
		function getjson(){
			var radio = new Array();
			var m = 0;
			var n = 0;
			var checkbox_num = parseInt(sessionStorage.checkbox_num);
			var radio_num = parseInt(sessionStorage.radio_num);
			var type = JSON.parse(sessionStorage.type);
			for (var i = 0; i < type.length; i++){//根据type类型获取答案
				if (type[i] == 2){
					m = m + 1;
					var checkbox_name = "checkbox_" + m;
					var chkb_value = [];
					$("input:checkbox[name=" + checkbox_name + "]:checked").each(function(){
						chkb_value.push($(this).val());
					});
					radio[i] = "";
					console.log(chkb_value);
					for (var t = 0; t < chkb_value.length; t++)	{
						radio[i] = radio[i] + chkb_value[t];
					}
				} else{
					n = n + 1;
					var radio_name = new String("radio_" + n);
					radio[i] = $('input:radio[name=' + radio_name + ']:checked').val();
				}
			}
			//数组转json串
			var json = JSON.stringify(radio);
			return json;
		}
		function my_confirm(){
			var json = getjson();
			var msg = "您真的答案是：" + json + ",是否确认提交";
			if (confirm(msg) == true)
			{
				addtimes();
				window.location.href = "/exam/manage/result";
				sessionStorage.json = json;
			} else
			{
				return false;
			}
		}

		function addtimes() {
			var UserName=sessionStorage.Username;
			$.ajax({
				url: "/exam/manage/addtimes",
				type: "post",
				data:{"UserName":UserName},
				datatype: "json",
				success: function () {
					console.log("次数加1");
				}
			})
		}
		
		function timeCount(){
			seconds = seconds + 1;
			if (seconds >= 60){
				seconds = 0;
				minutes = minutes + 1;
			}
			if (minutes >= 60){
				minutes = 0;
				hours = hours + 1;
			}
			if (seconds < 10){
				seconds_string = "0" + seconds.toString();
			} else{
				seconds_string = seconds.toString();
			}

			if (minutes < 10){
				minutes_string = "0" + minutes.toString();
			} else{
				minutes_string = minutes.toString();
			}

			if (hours < 10){
				hours_string = "0" + hours.toString();
			} else{
				hours_string = hours.toString();
			}
			html = hours_string + ":" + minutes_string + ":" + seconds_string;
			$('#TimeCount').html(html);
			t = setTimeout("timeCount()", 1000);
		}
		function stopCount(){
			clearTimeout(t);
			seconds = minutes = hours = 0;
		}
		function StopEt(){
			clearInterval(Et);
		}
	</script>
<script type="text/javascript">
    function Maxbig(obj)
    {
        var a = document.getElementById("exerciseExam");
        a.style.top = a.style.left = 0;
        a.style.width = document.documentElement.clientWidth - 50 + "px";
        a.style.height = document.documentElement.clientWidth - 50 + "px";
        obj.style.display = "none";
        /* b.style.display = "block"; */
    }
</script>
<script type="text/javascript">
    function answershow(id)
    {
        var a = document.getElementById("answer" + id);
        if (a.style.display == "none")
        {
            a.style.display = "block";
        } else
        {
            a.style.display = "none";
        }
    }
    /*弹窗*/
    function showCon(content){
        $(".content_msg").html(content);
        $(".cov").show();
    }
    function closeCon(){
        $(".cov").hide();
    }

</script>
<body leftmargin=0 topmargin=0 width=100% height=100%>

<div id="centre"></div>
<div id="bottom" style="width: 1441px">
    <shiro:hasPermission name='考试管理员'>
        <div style="float: left;">
            <button class="layui-btn layui-btn-normal button" style="margin-left:1000px;width: 100px"
                    onclick="excise.PoisionAshow('pPoisionA');setnone('updateexambtn');setnone('utitle');
             setblock('ptitle');setblock('addexambtn');setblock('exampublish');setColor('pPoisionA');setColor('pPoisionB1')">
                发布考试
            </button>
            <button class="layui-btn layui-btn-normal button" style="width: 100px" onclick='showExamHistory()'>
                历史记录
            </button>
        </div>
    </shiro:hasPermission>
    <div style="width: 100px;float: left;">
        <button class="layui-btn layui-btn-normal button" style="width: 100px;" onclick='showAchievementRecord()'>
            成绩查询
        </button>
    </div>
    <div style="clear: both;"></div>
    <div style="margin: auto">
        <table class="layui-hide" id="test" lay-filter="demo"></table>
    </div>
    <script type="text/html" id="zizeng">
        {{d.LAY_TABLE_INDEX+1}}
    </script>
    <script type="text/html" id="barDemo">
        <shiro:hasPermission name='考试管理员'>
            <a class="layui-btn layui-btn-xs" lay-event="edit" style="background: #44ACFF">修改</a>
            <a class="layui-btn layui-btn-xs" lay-event="del" style="background: #44ACFF">删除</a>
            <a class="layui-btn layui-btn-xs" lay-event="start" style="background: #44ACFF">开始考试</a>
            <a class="layui-btn layui-btn-xs" lay-event="finish" style="background: #44ACFF">结束考试</a>
        </shiro:hasPermission>
        <a class="layui-btn layui-btn-xs" lay-event="startexam" style="background: #44ACFF">考试</a>
    </script>

    <div id="exampublish" class="white_content1" style="width:448px;height: 300px;">
        <div style="font-size: 18px; line-height: 32px;" id="ptitle">
            <a style="margin: 20px auto;padding-left: 20px">发布考试</a>
        </div>
        <div style="font-size: 18px; line-height: 32px" id="utitle">
            <a style="margin: 20px auto;padding-left: 20px">修改信息</a>
        </div>
        <img height="16px" style="left: 426px;top:10px;position: absolute" src="../img/close.png"
             onclick="window.document.getElementById('exampublish').style.display='none';clearExampublish()">
        <div class="tab-content tab-content-show">
            <form name="changePI" style="margin-left: 20px;">
                <table class="itable" cellspacing="0" cellpadding="0">
                    <tr>
                        <td colspan="2">序号</td>
                        <td colspan="2"><input type="text" id="id1"  hidden>
                            <input type="text" id="id"  >
                        </td>
                    </tr>
                    <tr>
                        <td>课程编号</td>
                        <td><input type="text" id="pCourseID" oninput="value=value.replace(/[^\d]/g,'')" value="1"></td>
                        <td>章节</td>
                        <td><input type="text" id="pChapterID" oninput="value=value.replace(/[^\d]/g,'')" value="2"></td>
                    </tr>
                    <tr>
                        <td>分类1</td>
                        <td>
                            <select id="pPoisionA" style="border: 0px;text-align-last: left;padding-left: 10px"
                                    onchange="excise.PoisionB1show('pPoisionA','pPoisionB1');setColor('pPoisionA');setColor('pPoisionB1');">
                                <option>请选择</option>
                            </select>
                        </td>
                        <td>分类2</td>
                        <td>
                            <select id="pPoisionB1" onchange="setColor('pPoisionB1')"
                                    style="border: 0px;text-align-last: left;padding-left: 10px">
                                <option>请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>开始时间</td>
                        <td><input type="text" id="startdate"></td>
                        <td>结束时间</td>
                        <td><input type="text" id="enddate" ></td>
                    </tr>
                    <tr>
                        <td >周期</td>
                        <td>
                            <select id="week" style="border: 0px;text-align-last: left;padding-left: 10px" onchange="setColor('week')">
                                <option value="1">第一周</option>
                                <option value="2">第二周</option>
                                <option value="3">第三周</option>
                                <option value="4">第四周</option>
                            </select>
                        </td>
                        <td >考试时长</td>
                        <td >
                            <select id="examTime" style="border: 0px;text-align-last: left;padding-left: 10px;color:black;">
                                <option value="10">10分钟</option>
                                <option value="15">15分钟</option>
                                <option value="20">20分钟</option>
                                <option value="25">25分钟</option>
                                <option value="30">30分钟</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
            <script>
                layui.use('laydate', function () {
                    var laydate = layui.laydate;
                    //执行一个laydate实例
                    laydate.render({
                        elem: '#startdate' //指定元素
                        , zIndex: 99999999
                        , trigger: 'click'
                    });
                    laydate.render({
                        elem: '#enddate' //指定元素
                        , zIndex: 99999999
                        , trigger: 'click'
                    });
                });
            </script>
            <input id="addexambtn" value="确认" type="button" class="button" style="margin: 20px auto;"
                   onclick="exam.insertpublishexam()">
            <input id="updateexambtn" value="确认" type="button" class="button" style="margin: 20px auto;"
                   onclick="exam.updateexam()">
        </div>
    </div>
</div>
</div>
<div id='banner' onclick='closeAchievementRecord()'></div>
<div id='achievementRecord'>
    <div id='achievementTimeDIV'>
        <input id='achievementTime' />
        <button id='findAchievementRecord' onclick='changeAchievementRecord()'>查询</button>
        <button id='findAchievementRecord' onclick='downAchievementRecord()'>导出</button>
    </div>
    <table class="display" id="DataTable" style="text-align: center;">
        <thead>
        <tr>
            <th rowspan="2">员工姓名</th>
            <th rowspan="2">员工编号</th>
            <th colspan="4">第一周考试成绩</th>
            <th colspan="4">第二周考试成绩</th>
            <th colspan="4">第三周考试成绩</th>
            <th colspan="4">第四周考试成绩</th>
        </tr>
        <tr>
            <th>第一次</th>
            <th>第二次</th>
            <th>第三次</th>
            <th>第四次</th>
            <th>第一次</th>
            <th>第二次</th>
            <th>第三次</th>
            <th>第四次</th>
            <th>第一次</th>
            <th>第二次</th>
            <th>第三次</th>
            <th>第四次</th>
            <th>第一次</th>
            <th>第二次</th>
            <th>第三次</th>
            <th>第四次</th>
        </tr>
        </thead>
    </table>
</div>
<div id="examHistory" class="white_content-exam">考试历史记录查询
    <table class="layui-table" id="testExam" lay-filter="demoExam"></table>
    <script type="text/html" id="zizengExam">
        {{d.LAY_TABLE_INDEX+1}}
    </script>
    <script type="text/html" id="barDemoExam">
        <a class="layui-btn layui-btn-xs" lay-event="detail" style="background: #44ACFF">查看</a>
    </script>

    <button class="layui-btn layui-btn-normal button" style="margin-top: 15%;" href = "javascript:void(0)" onclick = "document.getElementById('examHistory').style.display='none';document.getElementById('fadeExam').style.display='none'">关闭</button></div>
<div id="fadeExam" class="black_overlay-exam"></div>
<div id="exerciseExam" class="white_content">
    <div id="controlbtn" style="float: right;">
        <input type="image" src="../img/最大化.png" width="24" height="24"
               border="0" title="最大化" onclick="Maxbig(this)" style="outline: none;"
               id="maxbig"></input> <input type="image" style="outline: none;"
                                           src="../img/关闭.png" width="24" height="24" border="0" title="关闭" a
                                           href="javascript:void(0)"
                                           onclick="document.getElementById('exerciseExam').style.display='none';document.getElementById('fade').style.display='none';"></input>
        <!--        删除了回调函数:stopCount();-->
    </div>
    <div id="warpper">
        <div id="tab_candidates"></div>
    </div>
    <div style="width: 100%; text-align: center;">
        <input type="button" value="上一题" class="button" onclick="tabPrev()"></input>
        <input type="button" value="下一题" class="button" onclick="tabNext()"></input>
    </div>
</div>
<div id="fade" class="black_overlay"></div>
<div id="exam" class="white_content">
    <div id="time"
         style="color: red; float: left; margin: 0 0 0 0; padding: 0 0 0 0; font-size: 18px;"></div>
    <div id="controlbtn" style="float: right;">
        </input> <input type="image" style="outline: none;"
                        src="../img/关闭.png" width="24" height="24" border="0" title="关闭" a
                        href="javascript:void(0)"
                        onclick="document.getElementById('exam').style.display='none';document.getElementById('fade').style.display='none';StopEt();"></input>
    </div>
    <div id="warpper">
        <div id="tab_candidates1"></div>
    </div>
    <div style="width: 100%; text-align: center;">
        <input type="button" value="上一题" class="button1" onclick="exam.tabPrev1()"></input>
        <input type="button" onclick="my_confirm()" class="button1"
               value="考试完成" /> <input type="button" value="下一题" class="button1"
                                      onclick="exam.tabNext1()"></input>
    </div>
</div>
</body>

</html>


