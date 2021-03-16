<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@taglib
prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>

<head>
    <title>杭州浩维管理平台</title>
    <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css" media="all" />
    <script type="text/javascript" src="../layui/layui.all.js"></script>
    <script type="text/javascript" src="../js/week/ajaxfileupload.js"></script>
    <script src="../layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/week/exercisemanage.js"></script>
    <link rel="stylesheet" type="text/css" href="../js/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="../js/easyui/themes/icon.css" />
    <script type="text/javascript" src="../js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta charset="utf-8" />
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    <script>
        window.onload = function () {
            excise.PoisionAshow("PoisionAselect");
            excise.PoisionAshow("PoisionA");
            loodtable();
        };
    </script>
    <link rel="stylesheet" href="../css/Inform.css" />

    <link href="../css/logo.css" rel="stylesheet" />
</head>
<style>
    #bottom {
        width: 1800px;
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
        border: 1px #9b9b9b solid;
        color: black;
        text-align-last: center;
        width: 100px;
        height: 32px;
    }

    option {
        text-align: center;
        color: black;
    }
</style>

<body leftmargin="0" topmargin="0" width="100%" height="100%">
<div id="centre"></div>
<div id="bottom">
    <img src="../img/load.gif" style="
          display: none;
          left: 45%;
          top: 35%;
          position: absolute;
          z-index: 2000;
        " id="loadimg" />
    <div class="site-demo-button" id="layerDemo" style="
          margin-bottom: 0;
          text-align: left;
          padding-left: 20px;
          font-size: 16px;
        ">
        搜索题型：<select id="PoisionAselect"
                     onchange="excise.PoisionB1show('PoisionAselect','PoisionB1select');setColor('PoisionAselect');setColor('PoisionB1select');">
        <option>请选择</option>
    </select>
        <select id="PoisionB1select" onchange="setColor('PoisionB1select');loodtable()">
            <option>请选择</option>
        </select>
        <button class="layui-btn layui-btn-normal button" style="margin-left: 1280px"
                onclick="setblock('exam');setblock('addtitle');setblock('addexambtn')">
            添加
        </button>
        <button class="button" style="margin-left: 20px; width: 100px; vertical-align: middle"
                onclick="window.document.getElementById('impExcel').style.display='block'">
            Excel导入
        </button>
    </div>
    <div style="margin: auto">
        <table class="layui-hide" id="test" lay-filter="demo"></table>
    </div>
    <script type="text/html" id="zizeng">
        {{d.LAY_TABLE_INDEX+1}}
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" data-type="auto" lay-event="edit" style="background: #44ACFF" id="tttt">修改</a
        >
        <a class="layui-btn layui-btn-xs" lay-event="del" style="background: #44ACFF">删除</a>
    </script>

    <div id="impExcel" class="white_content1" style="width: 320px; height: 170px;position: fixed;">
        <img height="16px" style="left: 290px; top: 10px; position: absolute" src="../img/close.png"
             onclick="window.document.getElementById('impExcel').style.display='none'" />
        <div style="font-size: 18px; line-height: 32px">
            <a style="margin: 20px auto; padding-left: 20px">Excel导入题库</a>
        </div>
        <div class="tab-content tab-content-show">
            <input type="file" id="ufiles" name="ufiles" style="margin-left: 80px" /><br />
            <input type="button" value="确认" class="button" style="margin: 20px auto" onclick="excise.impExcel()" />
        </div>
    </div>

    <div id="exam" class="white_content1" style="width: 100%; height: 100%; left: 0px; top: 0px;position: fixed;">
        <img height="24px" style="right: 20px; top: 10px; position: absolute" src="../img/close.png"
             onclick="setnone('exam');setnone('updatetitle');setnone('addtitle');setnone('addexambtn');setnone('updateexambtn');excise.clearInput()" />
        <div style="font-size: 18px; line-height: 32px; display: none" id="addtitle">
            <a style="margin: 20px auto; padding-left: 20px">添加题目</a>
        </div>
        <div style="font-size: 18px; line-height: 32px; display: none" id="updatetitle">
            <a style="margin: 20px auto; padding-left: 20px">修改题目</a>
        </div>
        <div class="tab-content tab-content-show">
            <form name="changePI" style="margin-left: 20px">
                <table class="itable" cellspacing="0" cellpadding="0" style="width: 743px;margin: 0 auto;">
                    <tr>
                        <input type="text" id="QuestionID" hidden />
                        <td>课程编号</td>
                        <td>
                            <input type="text" id="CourseID" oninput="value=value.replace(/[^\d]/g,'')" />
                        </td>
                        <td>章节</td>
                        <td>
                            <input type="text" id="ChapterID" oninput="value=value.replace(/[^\d]/g,'')" />
                        </td>
                    </tr>
                    <tr>
                        <td>分类1</td>
                        <td style="height: 32px">
                            <select id="PoisionA" style="
                      border: 0px;
                      text-align-last: left;
                      float: left;
                      padding-left: 20px;
                    " onchange="excise.PoisionB1show('PoisionA','PoisionB1');setColor('pPoisionA');setColor('pPoisionB1');"></select>
                        </td>
                        <td>分类2</td>
                        <td>
                            <select id="PoisionB1" onchange="setColor('PoisionB1')" style="
                      border: 0px;
                      text-align-last: left;
                      padding-left: 20px;
                      float: left;
                    "></select>
                        </td>
                    </tr>
                    <tr>
                        <td>答案类型</td>
                        <td>
                            <select id="Type" style="
                      float: left;
                      border: 0px;
                      padding-left: 20px;
                      text-align-last: left;
                      color: black;
                    ">
                                <option value="0" selected>单选</option>
                                <option value="1">判断</option>
                                <option value="2">多选</option>
                            </select>
                        </td>
                        <td>出题人</td>
                        <td><input type="text" id="PoisionB3" /></td>
                    </tr>
                    <tr>
                        <td>题目</td>
                        <td colspan="3">
                            <textarea name="" id="Question" cols="70" rows="10" style="width: 618px;border: none;"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>答题时间</td>
                        <td><input type="text" id="QuestionTime" /></td>
                        <td>答案</td>
                        <td><input type="text" id="Answer" /></td>
                    </tr>

                    <tr>
                        <td>选项A</td>
                        <td><input type="text" id="OptionA" /></td>
                        <td>选项B</td>
                        <td><input type="text" id="OptionB" /></td>
                    </tr>
                    <tr>
                        <td>选项C</td>
                        <td><input type="text" id="OptionC" /></td>
                        <td>选项D</td>
                        <td><input type="text" id="OptionD" /></td>
                    </tr>
                    <tr>
                        <td>备注</td>
                        <td colspan="3">
                            <textarea name="" id="Remarks" cols="70" rows="10" style="width: 618px;border: none;"></textarea>
                        </td>
                    </tr>
                </table>
            </form>
            <input id="addexambtn" value="确认" type="button" class="button" style="margin: 20px auto; display: none"
                   onclick="excise.insertexam()" />

            <input id="updateexambtn" value="确认" type="button" class="button"
                   style="margin: 20px auto; display: none" onclick="excise.updateexcise()" />
        </div>
    </div>
</div>
</body>

</html>