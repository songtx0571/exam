<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/exam/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/exam/layui/css/layui.css" media="all">
    <script src="/exam/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/exam/js/week/documentLibrary.js"></script>
    <link rel="stylesheet" href="/exam/css/documentLibrary.css" media="all">
</head>
<body>
<div class="warp">
    <div class="top">
        <input type="text" id="keywordInp">
        <button class="layui-btn" id="keywordBtn" style="border-radius: 0">查询</button>
    </div>
    <div class="content">
        <table id="demoTable" lay-filter="testTable"></table>
        <script type="text/html" id="tbBar">
            <a class="layui-btn" style="line-height: 30px;" lay-event="look">查看</a>
            <shiro:hasPermission name='退回文档库'>
                <a class="layui-btn " style="line-height: 30px;" lay-event="bank">退回</a>
            </shiro:hasPermission>
        </script>
    </div>
    <%--查看页面--%>
    <div class="develop_look">
        <table>
            <caption>查看</caption>
            <tr>
                <td colspan="2">
                    <input type="hidden" id="id" name="id" class="id">
                </td>
            </tr>
            <tr>
                <td>
                    <span>标题：</span></td>
                <td>
                    <input type="text" id="title" class="layui-input title" style="width: calc(100% - 65px);margin-left: 6px;">
                </td>
            </tr>
            <tr>
                <td>
                    <span>关键字：</span>
                </td>
                <td>
                    <input type="text" id="keyword" name="keyword" class="layui-input keyword"
                           style="width: calc(100% - 65px);margin-left: 6px;">
                </td>
            </tr>
            <tr style="line-height: 25px;">
                <td>
                    <span style="line-height: 80px;">内容：</span>
                </td>
                <td style="max-width: 480px;">
                    <div style="width: calc(100% - 65px); text-indent: 10px;border: 1px solid #e6e6e6;margin: 10px 0px 10px 5px;"
                         id="content"></div>
                </td>
            </tr>
            <shiro:hasPermission name='退回文档库'>
                <tr>
                    <td>
                        <span style="line-height: 80px;">备注：</span>
                    </td>
                    <td>
                    <textarea rows="3" class="layui-textarea remark" cols="50" id="remark" name="remark"
                              style="width: calc(100% - 65px);margin-left: 6px;"></textarea>
                    </td>
                </tr>
            </shiro:hasPermission>
            <tr>
                <td colspan="2" style="text-align: center;padding-top: 20px;box-sizing: border-box;">
                    <button type="button" class="layui-btn" onclick="cancel()">取消</button>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
