<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>找回密码</TITLE>
<!--bixanjxiqxi-->
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="qtimages/style.css" type=text/css rel=stylesheet>


<META content="MSHTML 6.00.2900.6058" name=GENERATOR>
</HEAD>
<%
CommDAO dao = new CommDAO();
String yonghuming=request.getParameter("yonghuming");
String youxiang=request.getParameter("youxiang");
//HashMap ext = new HashMap(); 
if(request.getParameter("f")!=null)
		{
		String sql="select * from yonghuzhuce where yonghuming='"+yonghuming+"' and youxiang='"+youxiang+"' ";
			List<HashMap> userlist1 = dao.select(sql);
//		out.print(sql);
			if(userlist1.size()==1)
			{
			out.print("<script>javascript:alert('您的密码是：【"+userlist1.get(0).get("mima")+"】请妥善保管');location.href='zmm.jsp'</script>");
			return;
			}else{
			out.print("<script>javascript:alert('您的输入有误，无法找回');location.href='zmm.jsp';</script>");
			return;
			}
		}
	   %>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/popup.js"></script>
	<script language=javascript src='js/ajax.js'></script>
	<script language="javascript">

</script>
<!--hxsglxiangdxongjxs-->

<BODY>


<%@ include file="qttop.jsp"%>

<TABLE cellSpacing=0 cellPadding=0 width=1120 align=center bgColor=#ffffff 
border=0>
  <TBODY>
  <TR>
    <TD width=5>&nbsp;</TD>
    <TD vAlign=top width=230>
     <%@ include file="qtleft.jsp"%>
      </TD>
    <TD vAlign=top width=10>&nbsp;</TD>
    <TD vAlign=top>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD vAlign=bottom height=40>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width=50 height=40><IMG height=35 
                  src="qtimages/B0039.gif" 
                  width=30></TD>
                <TD width=817><span class="red"><strong>找回密码</strong></span></TD>
                </TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD bgColor=#dadada height=1></TD></TR>
        <TR>
          <TD bgColor=#f6f6f6 height=4></TD></TR>
        <TR>
          <TD bgColor=#ffffff height=6>
		  
		 

  <form name="form1" method="post" action="zmm.jsp?f=f">
    <table width="41%" height="126" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#CCCCCC" class="newsline" style="border-collapse:collapse">
      <tr>
        <td colspan="2"><div align="center">找回密码</div></td>
      </tr>
      <tr>
        <td align="right">您的用户名：</td>
        <td><input name="yonghuming" type="text" id="yonghuming" /></td>
      </tr>
      <tr>
        <td align="right">您的邮箱：</td>
        <td><input name="youxiang" type="text" id="youxiang" /></td>
      </tr>
      <tr>
        <td align="right">&nbsp;</td>
        <td><input type="reset" name="Submit22" value="重置" />
          <input type="submit" name="Submit4" value="确定" onClick="return check()" /></td>
      </tr>
    </table>
  </form></TD>
        </TR></TBODY></TABLE>
      </TD>
    <TD width=5>&nbsp;</TD></TR></TBODY></TABLE>
<%@ include file="qtdown.jsp"%>
<!-- dfexnxxiaxng -->
</BODY></HTML>

<!--suxilxatxihuxan-->
