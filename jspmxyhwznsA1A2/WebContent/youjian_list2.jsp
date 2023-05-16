<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>
<html>
  <head>
    <title>邮件</title>
	<LINK href="css.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js" charset="UTF-8"></script>
  </head>

<!--hxsglxiangdxongjxs-->
  <body >
  <p>已有邮件列表：</p>
  <form name="form1" id="form1" method="post" action="">
   搜索:  接收者：<input name="jieshouzhe" type="text" id="jieshouzhe" style='border:solid 1px #000000; color:#666666' size="12" />  发送者：<input name="fasongzhe" type="text" id="fasongzhe" style='border:solid 1px #000000; color:#666666' size="12" />
     <input type="submit" name="Submit" value="查找" style='border:solid 1px #000000; color:#666666' />
</form>

<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="00FFFF" style="border-collapse:collapse">  
  <tr>
    <td width="30" align="center" bgcolor="CCFFFF">序号</td>
    <td bgcolor='#CCFFFF'>接收者</td>    <td bgcolor='#CCFFFF'>消息内容</td>    <td bgcolor='#CCFFFF'>发送者</td>    
    <td width="138" align="center" bgcolor="CCFFFF">添加时间</td>
    <td width="60" align="center" bgcolor="CCFFFF">操作</td>
  </tr>
 <% 
  	//difengysfiqfgieuheze
//youzuiping1
//txixixngdy
  	 new CommDAO().delete(request,"youjian"); 
    String url = "youjian_list2.jsp?1=1"; 
    String sql =  "select * from youjian where fasongzhe='"+request.getSession().getAttribute("username")+"' ";
	if(request.getParameter("jieshouzhe")=="" ||request.getParameter("jieshouzhe")==null ){}else{sql=sql+" and jieshouzhe like '%"+request.getParameter("jieshouzhe")+"%'";}if(request.getParameter("fasongzhe")=="" ||request.getParameter("fasongzhe")==null ){}else{sql=sql+" and fasongzhe like '%"+request.getParameter("fasongzhe")+"%'";}
    sql+=" order by id desc";
	ArrayList<HashMap> list = PageManager.getPages(url,15,sql, request); 
	int i=0;
	for(HashMap map:list){ 
	i++;
	//wxflzhistri
	//zoxngxetxoxngjxvi
//txixgihxngjs
//youzuiping2
     %>
  <tr>
    <td width="30" align="center"><%=i %></td>
    <td><%=map.get("jieshouzhe") %></td> <td><%=map.get("xiaoxineirong") %></td> <td><%=map.get("fasongzhe") %></td> 
    <td width="138" align="center"><%=map.get("addtime") %></td>
    <td width="60" align="center"><a href="youjian_updt.jsp?id=<%=map.get("id")%>">修改</a>  <a href="youjian_list2.jsp?scid=<%=map.get("id") %>" onclick="return confirm('真的要删除？')">删除</a> <!--qiatnalijne--> </td>
  </tr>
  	<%
  }
   %>
</table><br>
<!--yoxugonxgzitoxnxgjxi--> <!--youzuiping3--> 
${page.info }

  <%
//yoxutixinxg if(kucddduntx>0)
//yoxutixinxg{
//yoxutixinxg tsgehxdhdm
//yoxutixinxg}
%>
  </body>
</html>

