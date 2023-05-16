<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
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
 </script>

<nav class="navbar navbar-inverse ">
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<div class="dadadad"
			style="height: 50px; width: 1111px; margin: 0px auto;">

			<ul class="nav navbar-nav" style="float: left;">
				<li><a href="index.jsp"><font class="STYLE1">首页</font></a></li>
				<li><a href="news.jsp?lb=校园动态"><font class="STYLE1">校园动态</font></a>
				</li>
				<li><a href="dx_detail.jsp?lb=学院简介"><font class="STYLE1">学院简介</font></a>
				</li>
				<li><a href="news.jsp?lb=校园风景"><font class="STYLE1">校园风景</font></a>
				</li>
				<li><a href="news.jsp?lb=信息平台"><font class="STYLE1">信息平台</font></a>
				</li>
				<li><a href="yonghuzhucelist.jsp"><font class="STYLE1">校友风采</font></a>
				</li>
				<li><a href="bbs.jsp"><font class="STYLE1">校友论坛</font></a></li>
				<li><a href="lyb.jsp"><font class="STYLE1">在线留言</font></a></li>
				<li><a href="yonghuzhuceadd.jsp"><font class="STYLE1">校友注册</font></a>
				</li>
				<li><a href="login.jsp"><font class="STYLE1">管理员登录</font></a>
				</li>
			</ul>

			<ul class="nav navbar-nav" style="float: right; font-size: 10px;">
				<li><A class=white style="BEHAVIOR: url(#default#homepage)"
					onclick="javascript:this.style.behavior='url(#default#homepage)';
						this.setHomePage('javascript:window.external.AddFavorite
						('http://cny8.taobao.com',%20'校友会网站')');return(false);"
					href="javascript:window.external.AddFavorite
						('http://cny8.taobao.com',%20'校友会网站')">设为首页
				</A></li>
				<li><A class=white
					href="javascript:window.external.AddFavorite
						('http://cny8.taobao.com',%20'校友会网站')"
					target=_self>加入收藏 </A></li>
			</ul>
		</div>
	</div>
	<!-- /.container -->
</nav>


<!--轮播开始-->
<div class="hehe" style="width: 1111px; height: 300px; margin: 0 auto;">

	<div id="carousel-example-generic" class="carousel slide"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			<li data-target="#carousel-example-generic" data-slide-to="3"></li>
			<li data-target="#carousel-example-generic" data-slide-to="4"></li>

		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img style="width: 1111px; height: 300px;" src="images/BinHai.png"
					alt="...">
				<div class="carousel-caption"></div>
			</div>
			<div class="item">
				<img style="width: 1111px; height: 300px;" src="images/555.png"
					alt="...">
				<div class="carousel-caption"></div>
			</div>
			<div class="item">
				<img style="width: 1111px; height: 300px;" src="images/baigong.png"
					alt="...">
				<div class="carousel-caption"></div>
			</div>
			<div class="item">
				<img style="width: 1111px; height: 300px;" src="images/555.png"
					alt="...">
				<div class="carousel-caption"></div>
			</div>
			<div class="item">
				<img style="width: 1111px; height: 300px;" src="images/baigong.png"
					alt="...">
				<div class="carousel-caption"></div>
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</div>
<!--轮播结束-->





















































<TD height=5></TD>
</TR>
</TBODY>
</TABLE>
<!-- <TABLE cellSpacing=0 cellPadding=0 width=1120 align=center
	bgColor=#ffffff border=0>
	<TBODY>
		<TR>
			<TD width=5>&nbsp;</TD>
			<TD width=5><IMG height=52 alt="" src="qtimages/B0082.gif"
				width=11></TD>
			<TD style="BACKGROUND-IMAGE: url(qtimages/B0084.gif)">
				<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center
					border=0>
					<FORM id=searchform name=searchform onsubmit="" action=news.jsp
						method=post>
						<TBODY>
							<TR>
								<TD width=71 height="40" align=middle>站内搜索</TD>
								<TD width=87><select name="lb"
									style="width: 80px; height: 20px; border: solid 1px #000000; color: #666666">
										<%
						    for(HashMap m:new CommDAO().select("select distinct(leibie) as ss from xinwentongzhi")){
							%>
										<option value="<%=m.get("ss") %>"><%=m.get("ss") %></option>
										<%
							}
						   %>
								</select></TD>
								<TD width=241>关键字： <input name=keyword
									class=fourline-BDBDBD id="keyword" size=25 maxlength=30 /></TD>
								<TD width=149><INPUT
									style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
									type=image src="qtimages/B0042.gif" name=imageField>
									&nbsp;</TD>
								<TD width=400>&nbsp;&nbsp;&nbsp;</TD>
							</TR>
					</FORM>
				</TABLE> -->
			</TD>
			<TD align=right width=8><IMG height=52 alt=""
				src="qtimages/B0083.gif" width=11></TD>
			<TD width=5>&nbsp;</TD>
		</TR>
	</TBODY>
</TABLE>

<script src="lib/jquery/jquery-1.12.4.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>

