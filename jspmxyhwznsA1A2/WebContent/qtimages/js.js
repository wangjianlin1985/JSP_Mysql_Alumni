function checksearch(){
if (document.searchform.search_keyword.value=="")
{alert ("������Ҫ���������Ĺؼ���!");
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
		
		for (var i=0;i<myform.elements.musicid.length;i++)  //��������form�����пؼ�,
		{
			var e = myform.elements[i];
			if (e.Type="checkbox")					//����checkboxʱ��ִ������
			{
				if(e.checked)						//����checkbox��ѡȡʱ��ִ������
				{
					if(e.value=="ȫѡ"||e.value=="��ѡ"||e.value=="����>>"||e.value=="����"||e.value=="����ѡ�е�����")
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
			alert("��ѡ��Ҫ���ŵĸ���(�ɶ�ѡ)");
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
if (resuls[0]=="True"){alert('�������Ѿ�������Ŷ��');}
document.getElementById("hots").innerHTML='��������'+resuls[1]+'���Ƽ���';
}}
xmlhttp.send(null);
}

function gotourl(pages,totalpage,url)
{
if (pages==""){alert('��������Ҫ������ҳ����');return false;}
if (isNaN(pages)){alert("���������֣�");return false;}
if (pages.length>3){alert("�������3�����֣�");return false;}
if (pages>totalpage){alert("�Ƿ����룬���ҳ��Ϊ"+totalpage);return false;}
if (pages==1){window.location.href=url+'.html';}else{window.location.href=url+"_"+eval(pages-1)+'.html';}
}