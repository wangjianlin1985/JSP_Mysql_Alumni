function checksearch(){
if (document.searchform.search_keyword.value=="")
{alert ("请输入要搜索舞曲的关键宇!");
document.searchform.search_keyword.focus();
return false;}			
}

function openwin(url,windowname,size) {window.open(url,windowname,size)}

function Buy(id){openwin("/music/pay.asp?id="+id+"&type=music","shopcar","menubar=no,toolbar=no,location=no,directories=no,status=no,width=670,height=482,left=0,top=0,scrollbars=yes");}
function Musictoshopcar(id){openwin("/music/pay.asp?id="+id+"&type=musictoshopcar","shopcar","menubar=no,toolbar=no,location=no,directories=no,status=no,width=670,height=482,left=0,top=0,scrollbars=yes");}
function BuyCollection(id){openwin("/music/pay.asp?id="+id+"&type=collectiontoshopcar","shopcar","menubar=no,toolbar=no,location=no,directories=no,status=no,width=670,height=482,left=0,top=0,scrollbars=yes");}
function BuyBigclassmusic(id){openwin("/music/pay.asp?id="+id+"&type=Bigclassmusic","shopcar","menubar=no,toolbar=no,location=no,directories=no,status=no,width=670,height=482,left=0,top=0,scrollbars=yes");}
function CheckOthers(form)
{
	for (var i=0;i<form.elements.length;i++)
	{
		var e = form.elements[i];
//		if (e.name != 'chkall')
			if (e.checked==false)
			{
				e.checked = true;// form.chkall.checked;
			}
			else
			{
				e.checked = false;
			}
	}
}

function CheckAll(form)
{
	for (var i=0;i<form.elements.length;i++)
	{
		var e = form.elements[i];
//		if (e.name != 'chkall')
			e.checked = true// form.chkall.checked;
	}
}

function myplay(myform,patch)
	{
		
		var strCBValue = "";
		
		for (var i=0;i<myform.elements.musicid.length;i++)  //用来历遍form中所有控件,
		{
			var e = myform.elements[i];
			if (e.Type="checkbox")					//当是checkbox时才执行下面
			{
				if(e.checked)						//当是checkbox被选取时才执行下面
				{
					if(e.value=="全选"||e.value=="反选"||e.value=="更多>>"||e.value=="连播"||e.value=="播放选中的舞曲")
					{
						strCBValue = strCBValue;
					}
					else
					{
						strCBValue = strCBValue + e.value+",";
					}
//                    strCBValue = strCBValue + e.value+"_";
				 }
			  }
		}
		
		if(strCBValue=="")
		{
			alert("请选择要播放的歌曲(可多选)");
			return;
		}
		else
		{	
			if(patch==1)
			{
			//window.open("/music/player.asp?id="+strCBValue,"cfxy","");
			strCBValue = strCBValue.substring(0,strCBValue.length-1);
			document.player.action="/music/player.asp?id="+strCBValue; 
			document.player.submit(); 
			}
		}
	}
 var xmlhttp;
function createXMLHttpRequest()
{if(window.ActiveXObject)
{
xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
else if(window.XMLHttpRequest)
{
xmlhttp = new XMLHttpRequest();
}}

function plays(filetype,filename)
{createXMLHttpRequest();
xmlhttp.open("GET","/ajax.asp?action="+filetype+"&file="+filename,true);
xmlhttp.onreadystatechange = function()
{document.getElementById("jiao15player").innerHTML="<img border=0 src='/images/loading.gif'>";
	if (xmlhttp.readyState == 4&&xmlhttp.status == 200)
{	document.getElementById("jiao15player").innerHTML=xmlhttp.responseText;}}
xmlhttp.send(null);
}

function hot(musicid)
{createXMLHttpRequest();
xmlhttp.open("GET","/view.asp?action=click&id="+musicid,true);
xmlhttp.onreadystatechange = function()
{if (xmlhttp.readyState == 4&&xmlhttp.status == 200)
{
var resuls=new Array(); 
resuls = xmlhttp.responseText.split('||');
if (resuls[0]=="True"){alert('您今天已经顶过了哦！');}
document.getElementById("hots").innerHTML='本曲共有'+resuls[1]+'人推荐！';
}}
xmlhttp.send(null);
}

function gotourl(pages,totalpage,url)
{
if (pages==""){alert('请输入您要跳至的页数！');return false;}
if (isNaN(pages)){alert("请输入数字！");return false;}
if (pages.length>3){alert("最多输入3个数字！");return false;}
if (pages>totalpage){alert("非法输入，最大页数为"+totalpage);return false;}
if (pages==1){window.location.href=url+'.html';}else{window.location.href=url+"_"+eval(pages-1)+'.html';}
}