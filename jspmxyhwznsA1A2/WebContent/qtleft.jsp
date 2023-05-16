<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>



			
<script language="javascript">
function checklog()
{
	if(document.userlog.username.value=="" || document.userlog.pwd1.value=="" || document.userlog.yzm.value=="")
	{
		alert("请输入完整");
		return false;
	}
}
</script>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width=10><IMG height=28 alt="" 
            src="qtimages/B0008.gif" width=14></TD>
          <TD 
          style="BACKGROUND-IMAGE: url(qtimages/B0010.gif); PADDING-TOP: 10px" 
          align=middle><SPAN class=font14><IMG height=11 alt="" 
            src="qtimages/B0011.gif" width=11> 
            会员登陆 <IMG height=11 alt="" 
            src="qtimages/B0011.gif" 
            width=11></SPAN></TD>
          <TD width=10><IMG height=28 alt="" 
            src="qtimages/B0009.gif" 
        width=14></TD></TR></TBODY></TABLE><TABLE cellSpacing=0 cellPadding=0 width=180 bgColor=#ffffff border=0>
  <TBODY>
  <TR>
    <TD class=L_R-BDBDBD align=middle>
	 <%
    if((String)request.getSession().getAttribute("username")==null || (String)request.getSession().getAttribute("username")==""){
     %>
	 <form action="jspmxyhwznsA1A2?ac=login&a=a" method="post" name="f11" style="display: inline">
							                           <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">

                                  <tr>
                                    <td width="9" height="28">&nbsp;</td>
                                    <td width="54" height="28">用户名:</td>
                                    <td height="28" colspan="2"><input name="username" type="text" id="username" style="width:100px; height:20px; border:solid 1px #000000; color:#666666" /></td>
                                  </tr>
                                  <tr>
                                    <td height="28">&nbsp;</td>
                                    <td height="28">密码:</td>
                                    <td height="28" colspan="2"><input name="pwd1" type="password" id="pwd1"  style="width:100px; height:20px; border:solid 1px #000000; color:#666666" /></td>
                                  </tr>
                                  <tr >
                                    <td height="28">&nbsp;</td>
                                    <td height="28">权限:</td>
                                    <td height="28" colspan="2"><select name="cx" id="cx" style="width:100px; height:20px; border:solid 1px #000000; color:#666666" >
										<option value="注册用户">注册用户</option>
                                    </select></td>
                                  </tr>
                                  <tr >
                                    <td height="28">&nbsp;</td>
                                    <td height="28">验证码</td>
                                    <td width="52" height="28"><input name="pagerandom" type="text" id="pagerandom" style=" height:20px; border:solid 1px #000000; color:#666666; width:50px" /></td>
                                    <td width="96"><img src="ma.jsp" id="yzm" onClick="ma();" style="cursor:pointer"></img></td>
                                  </tr>
								  <script type="text/javascript">
           function ma()
           {
           var a = document.getElementById("yzm");
           var myDate=new Date()
           a.src="ma.jsp?d="+myDate;
           }
           
           
           </script>     
                                  <tr>
                                    <td height="38" align="center">&nbsp;</td>
                                    <td height="38" colspan="3" align="center"><input type="submit" name="Submit" value="登陆" class="hsgbutton" onclick="return checklog();" />
                                        <input type="reset" name="Submit2" value="重置" class="hsgbutton" />
                                        <input type="button" name="Submit4" value="找回密码" class="hsgbutton" onclick="location.href='zmm.jsp';" /></td>
                                  </tr>
                              
                              </table>
							    </form>
							  <%}else{ %>
					<table width="90%" height="82%" border="0" align="center" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td width="31%" height="30">用户名</td>
                                  <td width="69%" height="30"><%=request.getSession().getAttribute("username")%> </td>
                                </tr>
                                <tr>
                                  <td height="30">权限</td>
                                  <td height="30"><%=request.getSession().getAttribute("cx")%> </td>
                                </tr>
                                <tr>
                                  <td height="30" colspan="2" align="center"><input type="button" name="Submit3" value="退出" onClick="javascript:location.href='logout.jsp';" style=" height:19px; border:solid 1px #000000; color:#666666">
                                      <input type="button" name="Submit32" value="个人后台" onClick="javascript:location.href='main.jsp';" style=" height:19px; border:solid 1px #000000; color:#666666">
                                  </td>
                                </tr>
                              </table>
							     <%} %>
		
	  </TD></TR>
  <TR>
    <TD vAlign=top><IMG height=5 src="qtimages/B0012.gif" width=230></TD></TR>
  <TR>
    <TD height=8></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width=10><IMG height=28 alt="" 
            src="qtimages/B0024.gif" width=14></TD>
          <TD 
          style="BACKGROUND-IMAGE: url(qtimages/B0026.gif); PADDING-TOP: 10px" 
          align=middle><SPAN class=font14><IMG height=11 alt="" 
            src="qtimages/B0027.gif" width=11> 
            网站公告 <IMG height=11 alt="" 
            src="qtimages/B0027.gif" 
            width=11></SPAN></TD>
          <TD width=10><IMG height=28 alt="" 
            src="qtimages/B0025.gif" 
        width=14></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD class=L_R-BDBDBD align=middle><marquee border="0" direction="up" height="130" onMouseOut="start()" onMouseOver="stop()"
                scrollamount="1" scrolldelay="50"><TABLE cellSpacing=5 
      cellPadding=0 width="92%" align=center 
            border=0><TBODY><TR><TD><P> <% HashMap m1 = new CommDAO().getmaps("leibie","系统公告","dx");  out.print(m1.get("content"));//out.print(Info.ensubStr(m1.get("content"),185)); %></P></TD></TR></TBODY></TABLE></marquee></TD>
        </TR>
        <TR>
          <TD vAlign=top><IMG height=5 alt="" 
            src="qtimages/B0012.gif" 
        width=230></TD></TR>
        <TR>
          <TD height=8></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width=10><IMG height=28 alt="" 
            src="qtimages/B0008.gif" width=14></TD>
          <TD 
          style="BACKGROUND-IMAGE: url(qtimages/B0010.gif); PADDING-TOP: 10px" 
          align=middle><SPAN class=font14><IMG height=11 alt="" 
            src="qtimages/B0011.gif" width=11> 
            友情连接 <IMG height=11 alt="" 
            src="qtimages/B0011.gif" 
            width=11></SPAN></TD>
          <TD width=10><IMG height=28 alt="" 
            src="qtimages/B0009.gif" 
        width=14></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD height="28" align=middle class=L_R-BDBDBD><table width="100%" height="7%" border="0" cellpadding="0" cellspacing="0" class="newsline">
           <%
    for(HashMap map:new CommDAO().select("select * from youqinglianjie order by id desc",1,8)){
     %>
            <tr>
              <td width="12%" height="26" align="center"><img src="qtimages/q6.gif" width="5" height="7"></td>
              <td width="88%" align="left"><a href="<%=map.get("wangzhi") %>" target="_blank" ><%=map.get("wangzhanmingcheng") %></a></td>
            </tr>
           <%
								  }
								  %>
                                 
          </table></TD>
        </TR>
        <TR>
          <TD vAlign=top><IMG height=5 alt="" 
            src="qtimages/B0012.gif" 
        width=230></TD></TR>
        <TR>
          <TD height=8></TD></TR></TBODY></TABLE>


