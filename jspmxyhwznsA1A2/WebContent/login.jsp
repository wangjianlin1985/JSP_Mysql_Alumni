<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>
<html>
<head>
<title>校友同学网站后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-repeat: repeat-x;
	background: url("images/login.jpg");
}
.STYLE6 {color: #FFFFFF}
.STYLE5 {color: #CCFFCC;
	font-size: 26pt;
}
.STYLE9 {color: #ffffff; font-size:12px}
-->
</style>
</head>
<script type="text/javascript">
 <%
String error = (String)request.getAttribute("error"); 
if(error!=null)
{
 %>
 alert("用户名或密码错误");
 <%}%>
 
  <%
String random = (String)request.getAttribute("random"); 
if(random!=null)
{
 %>
 alert("验证码错误");
 <%}%>
 
 popheight = 39;

function check()
{
	if(document.form1.username.value=="" || document.form1.pwd.value=="" || document.form1.pagerandom.value=="")
	{
		alert('请输入完整');
		return false;
	}
}

           function loadimage(){ 
document.getElementById("randImage").src = "image.jsp?"+Math.random(); 
} 

           
           </script>     
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="header" style="
    top: 0;
    height: 60px;
    background: #426374 url(images/loginrig.png) no-repeat 0 center;
	position: absolute;
    left: 0;
    right: 0;
    width: 100%;
    z-index: 99;"></div>
<table width="1004" height="750" border="0" align="center" cellpadding="0" cellspacing="0" id="__01">
  <tr>
    <td height="293" colspan="3"><p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <table width="84%" height="56" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="56"><div style="font-family:宋体; color:#FFFFFF;WIDTH: 100%; FONT-WEIGHT: bold; FONT-SIZE:30px ; margin-top:5pt">
            <div align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校友同学网站后台管理系统</div>
        </div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td width="434" rowspan="2">&nbsp;</td>
    <td width="289" height="152"><form action="jspmxyhwznsA1A2?ac=adminlogin&a=a" method="post" name="form1" style="display: inline"><table width="205" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="44" height="30"><span class="STYLE9">用户:</span></td>
        <td height="30" colspan="2"><input name="username" type="text" id="username" style="width:100px; height:16px; border:solid 1px #000000; color:#666666"></td>
      </tr>
      <tr>
        <td height="30"><span class="STYLE9">密码:</span></td>
        <td height="30" colspan="2"><input name="pwd" type="password" id="pwd" style="width:100px; height:16px; border:solid 1px #000000; color:#666666"></td>
      </tr>
      <tr>
        <td height="30"><span class="STYLE9">权限:</span></td>
        <td height="30" colspan="2"><select name="cx" id="cx">
            <option value="管理员">管理员</option>
          </select>
        </td>
      </tr>
      <tr>
        <td height="30"><span class="STYLE9">验证码:</span></td>
        <td width="59" height="30"><input name="pagerandom" type="text" id="pagerandom" style=" height:20px; border:solid 1px #000000; color:#666666; width:50px" />
        <td width="102"><a href="javascript:loadimage();"><img alt="看不清请点我！" name="randImage" id="randImage" src="image.jsp" width="60" height="20" border="1" align="absmiddle"> </a></td>
      </tr>
      <tr>
        <td height="30" colspan="3"><input class="btn btn-success" type="submit" name="Submit" value="登&nbsp;&nbsp;&nbsp;陆" onClick="return check();" style="color:#000000;width:80px;height: 35px; border:0px;line-height:24px;margin-right: 5px; cursor:pointer">
            <input class="btn btn-success" type="reset" name="Submit2" value="重&nbsp;&nbsp;&nbsp;置" style="color:#000000;width:80px;height: 35px; border:0px;line-height:24px; margin-right: 5px; cursor:pointer" ></td>
      </tr>
    </table>
	</form></td>
    <td width="281" rowspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<div class="footer" style="
	height: 46px;
    line-height: 46px;
    bottom: 0;
    text-align: center;
    color: #fff;
    font-size: 12px;
    background-color: #426374;">信息管理系统</div>
</body>
</html>


