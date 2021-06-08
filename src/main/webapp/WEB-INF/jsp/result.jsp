<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib
        prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>考试结果</title>
    <style>
        .button {
            display: inline-block;
            background: #44ACFF;
            border: 1px solid #44ACFF;
            border-radius: .3em;
            color: white;
            width: 80px;
            height: 32px;
            font-size: 14px;
            outline: none;
        }

        .button:active {
            background-color: #E27635;
            border-color: #E27635;
        }
    </style>
    <script src="../js/jquery.min.js"></script>
    <script>
        $(function () {
            showResult();
        })

        //获取url中的参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg); //匹配目标参数
            if (r != null)
                return unescape(r[2]);
            return null; //返回参数值
        }

        function showResult() {
            var testScore = 0;
            var Username = sessionStorage.Username;
            $.ajax({
                url: '/exam/manage/GetPeriod',
                type: "POST",
                datatype: 'json',
                data: {Username: Username},
                success: function (result) {
                    console.log(result);
                    data = result;
                    if (data.length == 0) {
                        testScore = 0
                    } else {
                        testScore = data[0].testScore;
                    }
                }
            });
            var answer = JSON.parse(sessionStorage.answer);//标准答案
            /* var answer_score =
            [ 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 ];//答案的分数 */
            var user_answer = JSON.parse(sessionStorage.json);//获取用户答案
            var options_result = 0;//单选分数
            var options_right_num = 0;//单选答对个数
            var result = 0;//总分数
            var user_answer_result = new Array();//用户没到题的答题情况
            for (var i = 0; i < user_answer.length; i++) {
                if (user_answer[i] == answer[i]) {
                    options_result = options_result + 5;
                    options_right_num++;
                    user_answer_result[i] = "正确";
                } else {
                    user_answer_result[i] = "错误";
                }
            }
            result = parseInt(options_result);
            right_num = parseInt(options_right_num);
            //结果展示
            var show_result1;
            var show_result2;
            var show_result3;
            var show_result4;
            var show_result5;
            var show_result6;
            show_result1 = "你的答案结果为：";
            for (var i = 0; i < user_answer.length; i++) {
                show_result1 = show_result1 + (i + 1) + "：" + user_answer_result[i]
                    + "； ";
            }
            show_result2 = "总题目个数：" + user_answer.length;
            show_result3 = "答对题目个数：" + right_num + "； 得分：" + result;
            show_result5 = "答错题目个数：" + (user_answer.length - right_num);
            show_result6 = " 本次考试总成绩为：" + result;
            $("p#show_result1").html(show_result1);
            $("p#show_result2").html(show_result2);
            $("p#show_result3").html(show_result3);
            $("p#show_result4").html(show_result4);
            $("p#show_result5").html(show_result5);
            $("p#show_result6").html(show_result6);
            addAchievement(result);
            document.getElementById("showResult").style.display = "none";
            if (result < testScore) {
                alert("本次成绩低于上次，请继续努力！");
            } else {
                submmitScore(result);
            }
        }

        function addAchievement(Score) {
            var Username = sessionStorage.Username;
            $.ajax({
                url: '/exam/manage/addAchievement',
                type: 'POST',
                datatype: 'json',
                data:
                    {
                        Username: Username,
                        TestScore: Score
                    },
                success: function (result) {

                }

            });
        }

        function submmitScore(Score) {
            var Username = sessionStorage.Username;
            $.ajax({
                url: '/exam/manage/submitScore',
                type: "POST",
                datatype: 'json',
                data: {Username: Username, TestScore: Score},
                success: function (result) {
                    alert("成绩记录成功");
                }
            });
        }
    </script>
</head>
<body>
<h2>考试结束！</h2>
<hr/>
<input type="button" id='showResult' onclick="showResult()" value="查看结果" class="button">
<p id="show_result1">
<p>
<hr/>
<p id="show_result2"></p>
<p id="show_result3"></p>
<p id="show_result4"></p>
<p id="show_result5"></p>
<hr/>
<p id="show_result6"></p>
<input type="button" onclick="javascript:window.location.href='/exam/manage/toExamManagement'"
       class="button" value="返回列表"></input>
</body>
</html>