<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>
<html>
  <head>
    
    <title>邮件</title>
	<!--bixanjxiqxi-->
	<LINK href="css.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/popup.js"></script>
	   
  </head>
<%
//xuxyaxodenxglxu
  String id="";
 //islbd1q id=request.getParameter("id");
 //islbd1q HashMap mlbdq = new CommDAO().getmap(id,"melieibaoduqubiaoiguo");
 //islbd2q HashMap mlbdq = new CommDAO().getmaps("hsgzhujian",(String)request.getSession().getAttribute("username"),"melieibaoduqubiaoiguo");
 //islbdq gogogogogo
 //islbdq lelelelelele

   %>
<script language="javascript">

function gow()
{
	document.location.href="youjian_add.jsp?id=<%=id%>";
}
</script>
<!--hxsglxiangdxongjxs-->
 <% 
HashMap ext = new HashMap(); 
if(request.getParameter("f")!=null){
//wxfladd
//qiuji
//youshenhe
//youzhifu
//jitihuan

}
new CommDAO().insert(request,response,"youjian",ext,true,false,""); 
%>

  <body >
 <form  action="youjian_add.jsp?f=f&id=<%=id%>"  method="post" name="form1"  onsubmit="return checkform();">
  添加邮件:
  <br><br>
   <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#00FFFF" style="border-collapse:collapse">
		<tr><td  width="200">接收者：</td><td><input name='jieshouzhe' type='text' id='jieshouzhe' value='' onblur='checkform()' style='border:solid 1px #000000; color:#666666' />&nbsp;*<label id='clabeljieshouzhe' /></td></tr>
		<tr><td  width="200">消息内容：</td><td><textarea name='xiaoxineirong' cols='50' rows='5' id='xiaoxineirong' onblur='checkform()' style='border:solid 1px #000000; color:#666666' ></textarea>&nbsp;*<label id='clabelxiaoxineirong' /></td></tr>
		<tr style="display:none">
          <td>权限：</td>
		  <td><input name='cx' type='text' id='cx' value='<%=request.getSession().getAttribute("cx")%>' /></td>
     </tr>
		<tr><td  width="200">发送者：</td><td><input name='fasongzhe' type='text' id='fasongzhe' onblur='checkform()' style='border:solid 1px #000000; color:#666666' value='<%=request.getSession().getAttribute("username")%>' readonly="readonly" />&nbsp;*<label id='clabelfasongzhe' /></td></tr>
		
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="Submit" value="提交"  style='border:solid 1px #000000; color:#666666' />
      <input type="reset" name="Submit2" value="重置" style='border:solid 1px #000000; color:#666666' /></td>
    </tr>
  </table>
</form>

  </body>
</html>

<!--suxilxatxihuxan-->


<script language=javascript src='js/My97DatePicker/WdatePicker.js'></script>
<script language=javascript src='js/ajax.js'></script>

<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.HashMap"%> 

<script language=javascript >  
 
 function checkform(){  
 
	var jieshouzheobj = document.getElementById("jieshouzhe"); if(jieshouzheobj.value==""){document.getElementById("clabeljieshouzhe").innerHTML="&nbsp;&nbsp;<font color=red>请输入接收者</font>";return false;}else{document.getElementById("clabeljieshouzhe").innerHTML="  "; } 
	var xiaoxineirongobj = document.getElementById("xiaoxineirong"); if(xiaoxineirongobj.value==""){document.getElementById("clabelxiaoxineirong").innerHTML="&nbsp;&nbsp;<font color=red>请输入消息内容</font>";return false;}else{document.getElementById("clabelxiaoxineirong").innerHTML="  "; } 
	var fasongzheobj = document.getElementById("fasongzhe"); if(fasongzheobj.value==""){document.getElementById("clabelfasongzhe").innerHTML="&nbsp;&nbsp;<font color=red>请输入发送者</font>";return false;}else{document.getElementById("clabelfasongzhe").innerHTML="  "; } 
	


return true;   
}   
popheight=450;
</script>  


