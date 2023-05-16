<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Info"%>
<%@page import="dao.CommDAO"%>
<%@page import="util.PageManager"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <style media="all" type="text/css">
        html {scrollbar-3d-light-color:#C3DAF9; 
        scrollbar-face-color: #C3DAF9; 
        scrollbar-highlight-color: #C3DAF9; 
        scrollbar-shadow-color: #C3DAF9; 
        scrollbar-arrow-color: #fff; 
        scrollbar-base-color: #C3DAF9; 
        scrollbar-dark-shadow-color: #C3DAF9; 
        scrollbar-darkshadow-Color:#C3DAF9; }

        body,img,p,h1,h2,h3,h4,ul,dl,dt,dd {margin:0px;border:0px;padding:0px;}

body, h1,button,input,select,textarea,  fieldset,td{ font:12px/1.5 microsoft yahei, sans-serif,\5b8b\4f53; }
        body {color:#000;margin:0px;padding-left:3px; background-color:#c9defa; }
        a:link,a:visited {color:#333;text-decoration:none;}
        a:hover,a:active {color:#f60;text-decoration:underline;}
        li {list-style:none;}
        h2,h3,h4,input {font-size:12px;}
        .clear {clear:both;height:0;line-height:0;font-size:0}



        .main h3,.main li {background:url(images/index_bg.png) no-repeat;}

        .main .menutitle {background-position:0 -27px;}
        .main h3 {width:160px;height:21px;background-position: 0 0;padding-top:6px;cursor:pointer}
        .main h3 div {background:url(images/iconall.png) no-repeat;padding-left:30px;height:16px;line-height:16px}
        .main h3 .bt1 {background-position: 6px 0;}
        .main h3 .bt2 {background-position: 6px -21px;}
        .main h3 .bt3 {background-position: 6px -44px;}
        .main h3 .bt4 {background-position: 6px -64px;}
        .main h3 .bt5 {background-position: 6px -84px;}
        .main h3 .bt6 {background-position: 6px -102px;}
        .main h3 .bt7 {background-position: 6px -123px;}
        .main h3 .bt8 {background-position: 6px -144px;}
        .main h3 .bt9 {background-position: 6px -166px;}
        .main h3 .bt10 {background-position: 6px -188px;}
        .main h3 .bt11 {background-position: 6px -236px;}
        .main h3 .bt12 {background-position: 6px -213px;}
        .main ul{background:#ecf4ff;width:160px;padding:3px 0;}
        .main li {background-position: 6px -62px;padding-left:18px;height:25px;line-height:25px;border-bottom:1px solid #fff;}

        .main li .menustyle02,.main li .menustyle02:visited {color:Red;}
    </style>
    
</head>
<body style="margin: 0; border: 0;">



        <div class="main" id="menua">
		
			<h3 menuid="1"  id="menu1" onclick="showsubmenu(1)" toggleGroup="on" class="menutitle"><div class="bt1"><span>系统用户管理</span></div></h3>
				<ul id="submenu1">
					<li><a href="yhzhgl.jsp" target="frmright">系统用户管理</a></li>
					<li><a href="yonghuzhuce_list.jsp" target="frmright">注册用户管理</a></li>
					<li><a href="mod.jsp" target="frmright">修改密码</a></li>
				</ul>

            <h3 menuid="2"  id="menu2" onclick="showsubmenu(2)" toggleGroup="on" class="menutitle"><div class="bt2"><span>新闻资讯管理</span></div></h3>
				<ul id="submenu2">
					<li><a href="xinwentongzhi_add.jsp?lb=新闻资讯" target="frmright">新闻资讯添加</a></li>
					<li><a href="xinwentongzhi_list.jsp?lb=新闻资讯" target="frmright">新闻资讯查询</a></li>
				</ul>
			
<!--			<h3 menuid="3"  id="menu3" onclick="showsubmenu(3)" toggleGroup="on" class="menutitle"><div class="bt3"><span>校友会管理</span></div></h3>
				<ul id="submenu3">
					<li><a href="xiaoyouhui_add.jsp" target="frmright">校友会添加</a></li>
					<li><a href="xiaoyouhui_list.jsp" target="frmright">校友会查询</a></li>
				</ul>
			
			<h3 menuid="4"  id="menu4" onclick="showsubmenu(4)" toggleGroup="on" class="menutitle"><div class="bt4"><span>入会任职管理</span></div></h3>
				<ul id="submenu4">
					<li><a href="ruhuijilu_list.jsp" target="frmright">入会任职管理</a></li>
				</ul>
				
			<h3 menuid="5"  id="menu5" onclick="showsubmenu(5)" togglegroup="on" class="menutitle"><div class="bt5"><span>班级信息审核</span></div></h3>
				<ul id="submenu5">
					<li><a href="banjixinxi_list.jsp" target="frmright">班级信息审核</a></li>
				</ul>
-->				
			<h3 menuid="6"  id="menu6" onclick="showsubmenu(6)" toggleGroup="on" class="menutitle"><div class="bt6"><span>站内信管理</span></div></h3>
				<ul id="submenu6">
					<li><a href="youjian_add.jsp" target="frmright">发信</a></li>
					<li><a href="youjian_list2.jsp" target="frmright">发件箱</a></li>
					<li><a href="youjian_list3.jsp" target="frmright">收件箱</a></li>
				</ul>
				
	<!--		<h3 menuid="7"  id="menu7" onclick="showsubmenu(7)" togglegroup="on" class="menutitle"><div class="bt7"><span>校园动态管理</span></div></h3>
				<ul id="submenu7">
					<li><a href="xinwentongzhi_add.jsp?lb=校园动态" target="frmright">校园动态添加</a></li>
					<li><a href="xinwentongzhi_list.jsp?lb=校园动态" target="frmright">校园动态查询</a></li>
				</ul>
-->			<h3 menuid="8"  id="menu8" onclick="showsubmenu(8)" togglegroup="on" class="menutitle"><div class="bt7"><span>校友论坛管理</span></div></h3>
				<ul id="submenu8">
					<li><a href="bankuai_add.jsp" target="frmright">版块添加</a></li>
					<li><a href="bankuai_list.jsp" target="frmright">版块管理</a></li>
					<li><a href="tiezi_list.jsp" target="frmright">帖子管理</a></li>
				</ul>
			
			<h3 menuid="18"  id="menu18" onclick="showsubmenu(18)" toggleGroup="on" class="menutitle"><div class="bt8"><span>系统管理</span></div></h3>
				<ul id="submenu18">
				<!-- 	<li><a href="databack.jsp" target="frmright">数据备份</a></li> -->
					<li><a href="liuyanban_list.jsp" target="frmright">留言管理</a></li>
					<li><a href="youqinglianjie_add.jsp" target="frmright">友情连接添加</a></li>
					<li><a href="youqinglianjie_list.jsp" target="frmright">友情连接查询</a></li>
					<li><a href="dx.jsp?lb=学院简介" target="frmright">学院简介设置</a></li>
					<li><a href="dx.jsp?lb=系统公告" target="frmright">系统公告设置</a></li>
				</ul>
	   
	    </div>
        <script type="text/javascript">
             function initmenu()
             {
                var menu = document.getElementById("menua").getElementsByTagName("h3"); 
                var submenu = document.getElementById("menua").getElementsByTagName("ul"); 
                for(var i = 0; i < menu.length; i++)
                {
                    menu[i].className = "menutitle2";
                }
                for(var i = 0; i < submenu.length; i++)
                {
                    submenu[i].style.display = "none";
                }
             }
             function showsubmenu(index)
             {
                var menu = document.getElementById("menu" + index);
                var submenu = document.getElementById("submenu" + index);
                if (submenu.style.display == "")
                {
                    menu.className = "menutitle2";
                    submenu.style.display = "none";
                }
                else
                {
                    menu.className = "menutitle";
                    submenu.style.display = "";
                }
             }
             initmenu();
        </script>

    </form>
</body>
</html>
