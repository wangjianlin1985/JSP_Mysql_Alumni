<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>校友会网站</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="qtimages/style.css" type=text/css rel=stylesheet>


<META content="MSHTML 6.00.2900.6058" name=GENERATOR>
</HEAD>
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
                <TD width=817><a href="news.jsp?lb=校园动态"><span class="red"><strong>校园动态</strong></span></a></TD>
                </TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD bgColor=#dadada height=1></TD></TR>
        <TR>
          <TD bgColor=#f6f6f6 height=4></TD></TR>
        <TR>
          <TD bgColor=#ffffff height=6></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width=243> <%=new CommDAO().DynamicImage("",6,243,220) %></TD>
          <TD width=624><table width="98%" height="20" border="0" align="right" cellpadding="0" cellspacing="0" class="newsline">
            <%
										
    for(HashMap map:new CommDAO().select("select * from xinwentongzhi where leibie='校园动态' order by id desc ",1,9)){
	
     %>
            <tr>
              <td width="2%" align="right">・</td>
              <td width="84%"> <a href="gg_detail.jsp?id=<%=map.get("id") %>"target="_self" title="<%=map.get("biaoti") %>"><%=Info.ensubStr(map.get("biaoti"),26)%></a></td>
              <td width="14%"><%=map.get("addtime").toString().substring(0,10) %></td>
            </tr>
           <%
		} %>
          </table></TD>
        </TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD vAlign=bottom height=40>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width=44 height=40><IMG height=35 alt="" 
                  src="qtimages/B0015.gif" 
                  width=32></TD>
                <TD width="487" class="red"><strong>校友风彩</strong></TD>
                <TD width="336" align=right><A 
                  href="yonghuzhucelist.jsp"><IMG 
                  height=16 alt="" 
                  src="qtimages/B0017.gif" 
                  width=35 border=0></A></TD>
              </TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD bgColor=#dadada height=1></TD></TR>
        <TR>
          <TD bgColor=#f6f6f6 height=2></TD></TR>
        <TR>
          <TD bgColor=#ffffff height=3></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD class=int20>
		  
		  
		   <div id="demo"  style="float:left; overflow:hidden">
                    <div id="indemo">
                      <div id="demo1">
                        <div id="dbgdtp">
				<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                      <%
        for(HashMap m:new CommDAO().select("select * from yonghuzhuce where touxiang<>'' order by id desc",1,8)){
         %>
	    <td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" id="product">
	        <tr align="center">
	          <td>
              <div  style=" margin-left:3px; width:150px; height:120px; border:1px #CCC solid; padding:2px; background:#FFF">
              <a href="yonghuzhucedetail.jsp?id=<%=m.get("id") %>" target=""><img src="<%=m.get("touxiang") %>" border="0" title="<%=m.get("xingming") %>"  height="120" width="150"/></a></div></td>
	        </tr>
	        <tr align="center">
	          <td ><a href="" 
	          			target="">
	          		<%=m.get("xingming") %>
	          	</a></td>
	        </tr>
	       
       
	      </table></td>
          <%} %>
                  </tr>
                </table>
				
				 </div>
                      </div>
                      <div id="demo2"></div>
                    </div>
                  </div>
                  
                  
                  
                  
	       <script language="javascript"> 
			var speed=20;
			var tab=document.getElementById("demo"); 
			var tab1=document.getElementById("demo1"); 
			var tab2=document.getElementById("demo2"); 
			tab2.innerHTML=tab1.innerHTML; 
			function Marquee(){ 
			if(tab2.offsetWidth-tab.scrollLeft<=0) 
			tab.scrollLeft-=tab1.offsetWidth 
			else{ 
			tab.scrollLeft++; 
			} 
			} 
			var MyMar=setInterval(Marquee,speed); 
			tab.onmouseover=function() {clearInterval(MyMar)}; 
			tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)}; 
			</script>
       
                  <style type="text/css">
<!--
#dbgdtp {float:left;  margin:0px; }
#demo {overflow:hidden; width: 850px; }
#demo1 {float: left; }
#demo2 {float: left; }
#indemo {float: left; width: 1500%; }
-->
</style>

</TD>
        </TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD vAlign=bottom height=40>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width=44 height=40><IMG height=35 alt="" 
                  src="qtimages/B0041.gif" 
                  width=44></TD>
                <TD width="390" ><strong><a href="news.jsp?lb=校园风景"><font class="red">校园风景</font></a></strong></TD>
                <TD width="43" align=right><IMG height=35 alt="" 
                  src="qtimages/B0041.gif" 
                  width=44></TD>
                <TD width="390" ><strong><a href="news.jsp?lb=信息平台"><font class="red">信息平台</font></a></strong></TD>
              </TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD bgColor=#dadada height=1></TD></TR>
        <TR>
          <TD bgColor=#f6f6f6 height=2></TD></TR>
        <TR>
          <TD bgColor=#ffffff height=3></TD></TR></TBODY></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD width="50%" height="99" valign="top" class=int20><table width="98%" height="20" border="0" align="right" cellpadding="0" cellspacing="0" class="newsline">
             <%
										
    for(HashMap map:new CommDAO().select("select * from xinwentongzhi where leibie='校园风景' order by id desc ",1,9)){
	
     %>
            <tr>
              <td width="3%" align="right">・</td>
              <td width="79%"><a href="gg_detail.jsp?id=<%=map.get("id") %>"target="_self" title="<%=map.get("biaoti") %>"><%=Info.ensubStr(map.get("biaoti"),26)%></a></td>
              <td width="18%"> <%=map.get("addtime").toString().substring(0,10) %></td>
            </tr>
           <%
		} %>
          </table>            <p>&nbsp;</p></TD>
          <TD width="50%" valign="top" class=int20><table width="98%" height="20" border="0" align="right" cellpadding="0" cellspacing="0" class="newsline">
              <%
										
    for(HashMap map:new CommDAO().select("select * from xinwentongzhi where leibie='信息平台' order by id desc ",1,9)){
	
     %>
            <tr>
              <td width="3%" align="right">・</td>
              <td width="79%"><a href="gg_detail.jsp?id=<%=map.get("id") %>"target="_self" title="<%=map.get("biaoti") %>"><%=Info.ensubStr(map.get("biaoti"),26)%></a></td>
              <td width="18%"><%=map.get("addtime").toString().substring(0,10) %></td>
            </tr>
              <%
		} %>
          </table></TD>
        </TR></TBODY></TABLE>
     
    
      </TD>
    <TD width=5>&nbsp;</TD></TR></TBODY></TABLE>
<%@ include file="qtdown.jsp"%></BODY></HTML>
