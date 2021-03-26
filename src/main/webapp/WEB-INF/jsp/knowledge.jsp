<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>杭州浩维管理平台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script type="text/javascript" src="/exam/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/exam/layui/css/layui.css" media="all">
    <script src="/exam/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="/exam/css/knowledge.css" media="all">
    <script type="text/javascript" src="/exam/js/week/knowledge.js"></script>


    <link rel="stylesheet" href="/exam/assets/design/css/trumbowyg.css">
    <script src="/exam/assets/jquery.min.js"></script>
    <script src="/exam/assets/trumbowyg.js"></script>
    <script src="/exam/assets/plugins/base64/trumbowyg.base64.js"></script>
</head>
<body>
<div class="warp">
    <%--头部--%>
    <div class="top">
        <form class="layui-form" action="" style="float: left;margin-right: 20px;">
            <input type="hidden" id="typeHidden" value="1">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <select name="modules" lay-verify="required" lay-filter="type" lay-search="" id="knowledgeType">
                        <shiro:hasPermission name="添加编辑库">
                            <option value="0">编辑库</option>
                        </shiro:hasPermission>
                        <option value="1" selected>文档库</option>
                    </select>
                </div>
            </div>
        </form>
        <shiro:hasPermission name="添加编辑库">
            <button class="layui-btn" onclick="addPage()">添加</button>
        </shiro:hasPermission>
    </div>
    <%--内容--%>
    <div class="contentDiv">
        <table id="demoTable" lay-filter="testTable"></table>
        <script type="text/html" id="tbStatusBar">
            {{#  if(d.status== 0){ }}
            <span style="color: #0c7cd1">待审核</span>
            {{# }else  if(d.status== 1) { }}
            <span style="color: #0c7cd3">审核中</span>
            {{#  } else{  }}
            <span style="color: #0c7cd5">已审核</span>
            {{#  }   }}
        </script>
        <script type="text/html" id="tbTypeBar">
            {{#  if(d.type== "0"){ }}
            <span>编辑库</span>
            {{# }else  { }}
            <span>文档库</span>
            {{#  }   }}
        </script>
        <script type="text/html" id="tbBar">

            {{#  if(d.type== "0"){ }}
            <shiro:hasPermission name='审核编辑库'>
                <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
            </shiro:hasPermission>
            <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
            {{#  }   }}
            <a class="layui-btn layui-btn-xs" lay-event="look">查看</a>
            <shiro:hasPermission name='删除编辑库'>
                <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
            </shiro:hasPermission>
        </script>
    </div>
    <%--添加页面--%>
    <div class="develop_insert">
        <table class="develop_add">
            <caption class="tableTitle"></caption>
            <tr>
                <td colspan="2">
                    <input type="hidden" id="id" name="id" class="id">
                </td>
            </tr>
            <tr>
                <td>
                    <span>标题：</span></td>
                <td>
                    <input type="text" id="title" class="layui-input title" style="width: 480px;margin-left: 6px;">
                </td>
            </tr>
            <tr>
                <td>
                    <span>关键字：</span>
                </td>
                <td>
                    <input type="text" id="keyword" name="keyword" class="layui-input keyword"
                           style="width: 480px;margin-left: 6px;">
                </td>
            </tr>
            <tr>
                <td>
                    <span style="float: left;line-height: 80px;">内容：</span>
                </td>
                <td style="max-width: 480px;">
                    <div id="odiv" style="display:none;position:absolute;z-index:100;">
                        <img src="/exam/assets/pic/sx.png" title="缩小" border="0" alt="缩小" onclick="sub(-1);"/>
                        <img src="/exam/assets/pic/fd.png" title="放大" border="0" alt="放大" onclick="sub(1)"/>
                        <img src="/exam/assets/pic/sc.png" title="删除" border="0" alt="删除"
                             onclick="del();odiv.style.display='none';"/>
                    </div>
                    <div onmousedown="show_element(event)" style="clear:both" id="customized-buttonpane"
                         class="editor"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <span style="float: left;line-height: 80px;">备注：</span>
                </td>
                <td>
                    <textarea rows="3" class="layui-textarea remark" cols="50" id="remark" name="remark"
                              style="width: 480px;margin-left: 6px;"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <button type="button" class="layui-btn layui-btn-normal" onclick="save()" id="saveBtn">确定</button>&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;<button type="button" class="layui-btn" onclick="cancel()">取消</button>
                </td>
            </tr>
        </table>
    </div>
    <%--审核页面--%>
    <div class="develop_examine">
        <input type="hidden" class="id">
        <table class="develop_exm" cellspacing="0">
            <caption class="tableTitle"></caption>
            <tr>
                <td>审核标题：</td>
                <td><input type="text" id="checkTitle" class="layui-input title" readonly></td>
                <td>审核关键字：</td>
                <td><input type="text" id="checkKeyword" class="layui-input keyword" readonly></td>
            </tr>
            <tr>
                <td>审核内容：</td>
                <td style="padding: 8px 0px;box-sizing: border-box;max-width: 480px;" colspan="3">
                    <div id="checkContent" class="content"
                         style="padding: 0 10px;box-sizing: border-box;line-height: 30px;"></div>
                </td>
            </tr>
            <tr>
                <td>审核备注：</td>
                <td style="padding: 8px 0px;box-sizing: border-box;" colspan="3">
                    <textarea id="checkRemark" type="text" class="layui-textarea remark" readonly></textarea>
                </td>
            </tr>
            <tr>
                <td>审核人：</td>
                <td class="exmName" id="checkUserName" style="padding-left: 10px;box-sizing: border-box;"></td>
                <td>创建日期：</td>
                <td class="exmDate" id="checkCreateTime" style="padding-left: 10px;box-sizing: border-box;"></td>
            </tr>
            <tr>
                <td>审核成功人员：</td>
                <td colspan="3" class="employeeName" style="padding-left: 10px;box-sizing: border-box;">
                    <div id="checkedUser"></div>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="text-align: center;">
                    <button type="button" class="layui-btn layui-btn-normal" onclick="adopt()">确定</button>&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;<button type="button" class="layui-btn" onclick="reject()">取消</button>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
