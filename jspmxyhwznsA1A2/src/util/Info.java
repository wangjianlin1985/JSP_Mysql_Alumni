// 
// 
// 

package util;

import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import jxl.write.WritableCell;
import jxl.write.Label;
import java.io.IOException;
import jxl.Workbook;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Iterator;
import dao.CommDAO;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class Info
{
    public static String popheight;
    
    static {
        Info.popheight = "alliframe.style.height=document.body.scrollHeight+";
    }
    
    public static HashMap getUser(final HttpServletRequest request) {
        final HashMap map = (HashMap)((request.getSession().getAttribute("username") == null) ? request.getSession().getAttribute("user") : request.getSession().getAttribute("username"));
        return map;
    }
    
    public static int getBetweenDayNumber(final String dateA, final String dateB) {
        long dayNumber = 0L;
        final long mins = 60000L;
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            final Date d1 = df.parse(dateA);
            final Date d2 = df.parse(dateB);
            dayNumber = (d2.getTime() - d1.getTime()) / mins;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (int)dayNumber;
    }
    
    public static void main(final String[] g) {
        System.out.print(getBetweenDayNumber("2012-11-11 11:19", "2012-11-11 11:11"));
    }
    
    public static String getselect(final String name, final String tablename, final String zdname) {
        String select = "<select name=\"" + name + "\" id=\"" + name + "\" >";
        for (final HashMap permap : new CommDAO().select("select * from " + tablename + " order by id desc")) {
            select = String.valueOf(select) + "<option value=\"" + permap.get(zdname) + "\">" + permap.get(zdname) + "</option>";
        }
        select = String.valueOf(select) + "</select>";
        return select;
    }
    
    public static String getselectsl(final String name, final String tablename, final String zdname) {
        String select = "<select name=\"" + name + "\" id=\"" + name + "\" onchange='gow();'>";
        select = String.valueOf(select) + "<option value=''>\u8bf7\u9009\u62e9</option>";
        for (final HashMap permap : new CommDAO().select("select * from " + tablename + " order by id desc")) {
            select = String.valueOf(select) + "<option value=\"" + permap.get(zdname) + "\">" + permap.get(zdname) + "</option>";
        }
        select = String.valueOf(select) + "</select>";
        return select;
    }
    
    public static String getselect(final String name, final String tablename, final String zdname, final String where) {
        String select = "<select name=\"" + name + "\" id=\"" + name + "\" >";
        select = String.valueOf(select) + "<option value=\"\">\u4e0d\u9650</option>";
        for (final HashMap permap : new CommDAO().select("select * from " + tablename + " where " + where + " order by id desc")) {
            String optionstr = "";
            if (zdname.split(";").length == 1) {
                optionstr = permap.get(zdname.split("~")[0]).toString();
            }
            else {
                String[] split;
                for (int length = (split = zdname.split(";")).length, i = 0; i < length; ++i) {
                    final String str = split[i];
                    final String zdstr = str.split("~")[0];
                    final String zdnamestr = str.split("~")[1].equals("\u65e0\u540d") ? "" : (String.valueOf(str.split("~")[1]) + ":");
                    optionstr = String.valueOf(optionstr) + zdnamestr + permap.get(zdstr) + " - ";
                }
            }
            if (optionstr.indexOf(" - ") > -1) {
                optionstr = optionstr.substring(0, optionstr.length() - 3);
            }
            select = String.valueOf(select) + "<option value=\"" + optionstr + "\">" + optionstr + "</option>";
        }
        select = String.valueOf(select) + "</select>";
        return select;
    }
    
    public static String getradio(final String name, final String tablename, final String zdname, final String where) {
        String radio = "";
        int dxii = 0;
        for (final HashMap permap : new CommDAO().select("select * from " + tablename + " where  " + where + " order by id desc")) {
            String check = "";
            if (dxii == 0) {
                check = "checked=checked";
            }
            String optionstr = "";
            String[] split;
            for (int length = (split = zdname.split(";")).length, i = 0; i < length; ++i) {
                final String str = split[i];
                final String zdstr = str.split("~")[0];
                final String zdnamestr = str.split("~")[1].equals("\u65e0\u540d") ? "" : (String.valueOf(str.split("~")[1]) + ":");
                optionstr = String.valueOf(optionstr) + zdnamestr + permap.get(zdstr) + " - ";
            }
            if (optionstr.length() > 0) {
                optionstr = optionstr.substring(0, optionstr.length() - 3);
            }
            radio = String.valueOf(radio) + "<label><input type='radio' name='" + name + "' " + check + " value=\"" + optionstr + "\">" + optionstr + "</label>\n";
            ++dxii;
        }
        return radio;
    }
    
    public static void writeExcel(String fileName, final String prosstr, final List<List> plist, final HttpServletRequest request, final HttpServletResponse response) {
        WritableWorkbook wwb = null;
        String cols = "";
        String[] split;
        for (int length = (split = prosstr.split("@")).length, j = 0; j < length; ++j) {
            final String str = split[j];
            cols = String.valueOf(cols) + str.split("-")[0] + ",";
        }
        cols = cols.substring(0, cols.length() - 1);
        final String where = (request.getAttribute("where") == null) ? "" : request.getAttribute("where").toString();
        final List<List> mlist = new CommDAO().selectforlist("select " + cols + " from " + fileName + " " + where + "  order by id desc");
        fileName = String.valueOf(request.getRealPath("/")) + "/upfile/" + generalFileName("a.xls");
        final String[] pros = prosstr.split("@");
        try {
            wwb = Workbook.createWorkbook(new File(fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (wwb != null) {
            final WritableSheet ws = wwb.createSheet("sheet1", 0);
            ws.setColumnView(0, 20);
            ws.setColumnView(1, 20);
            ws.setColumnView(2, 20);
            ws.setColumnView(3, 20);
            ws.setColumnView(4, 20);
            ws.setColumnView(5, 20);
            try {
                for (int i = 0; i < pros.length; ++i) {
                    final Label label1 = new Label(i, 0, "");
                    label1.setString(pros[i]);
                    ws.addCell((WritableCell)label1);
                }
            }
            catch (RowsExceededException e2) {
                e2.printStackTrace();
            }
            catch (WriteException e3) {
                e3.printStackTrace();
            }
            int i = 1;
            for (final List t : mlist) {
                try {
                    final Iterator it = t.iterator();
                    int jj = 0;
                    while (it.hasNext()) {
                        final Label label2 = new Label(jj, i, "");
                        final String a = it.next().toString();
                        label2.setString(a);
                        ws.addCell((WritableCell)label2);
                        ++jj;
                    }
                    ++i;
                }
                catch (RowsExceededException e4) {
                    e4.printStackTrace();
                }
                catch (WriteException e5) {
                    e5.printStackTrace();
                }
            }
            try {
                wwb.write();
                wwb.close();
            }
            catch (IOException e6) {
                e6.printStackTrace();
            }
            catch (Exception e7) {
                e7.printStackTrace();
            }
        }
        try {
            response.sendRedirect("upload?filename=" + fileName.substring(fileName.lastIndexOf("/") + 1));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getcheckbox(final String name, final String tablename, final String zdname, final String where) {
        String checkbox = "";
        for (final HashMap permap : new CommDAO().select("select * from " + tablename + " where " + where + " order by id desc")) {
            String optionstr = "";
            String[] split;
            for (int length = (split = zdname.split(";")).length, i = 0; i < length; ++i) {
                final String str = split[i];
                final String zdstr = str.split("~")[0];
                final String zdnamestr = str.split("~")[1].equals("\u65e0\u540d") ? "" : (String.valueOf(str.split("~")[1]) + ":");
                optionstr = String.valueOf(optionstr) + zdnamestr + permap.get(zdstr) + " - ";
            }
            if (optionstr.length() > 0) {
                optionstr = optionstr.substring(0, optionstr.length() - 3);
            }
            checkbox = String.valueOf(checkbox) + "<label><input type='checkbox' name='" + name + "' value=\"" + optionstr + "\">" + optionstr + "</label>\n";
        }
        checkbox = String.valueOf(checkbox) + "<input type=hidden name='" + name + "' value='' />";
        return checkbox;
    }
    
    public static String getCombox(final String tablename, final String zdname, final String where) {
        String checkbox = "";
        for (final HashMap permap : new CommDAO().select("select * from " + tablename + " where " + where + " order by id desc")) {
            String optionstr = "'";
            String[] split;
            for (int length = (split = zdname.split(";")).length, i = 0; i < length; ++i) {
                final String str = split[i];
                if (str.indexOf("~") > -1) {
                    final String zdstr = str.split("~")[0];
                    final String zdnamestr = str.split("~")[1].equals("\u65e0\u540d") ? "" : (String.valueOf(str.split("~")[1]) + ":");
                    optionstr = String.valueOf(optionstr) + zdnamestr + permap.get(zdstr) + " - ";
                }
                else {
                    optionstr = String.valueOf(optionstr) + permap.get(str);
                }
            }
            if (optionstr.length() > 0) {
                optionstr = optionstr.substring(0, optionstr.length() - 3);
            }
            optionstr = String.valueOf(optionstr) + "',";
            checkbox = String.valueOf(checkbox) + optionstr;
        }
        if (checkbox.length() > 0) {
            checkbox = checkbox.substring(0, checkbox.length() - 1);
        }
        return checkbox;
    }
    
    public static String getcheckboxDk(final String name, final String tablename, final String zdname, final String nstr, final String where) {
        String checkbox = "";
        int i = 0;
        for (final HashMap permap : new CommDAO().select("select * from " + tablename + " where " + where + " order by id desc")) {
            String optionstr = "";
            String[] split;
            for (int length = (split = zdname.split(";")).length, j = 0; j < length; ++j) {
                final String str = split[j];
                final String zdstr = str.split("~")[0];
                final String zdnamestr = str.split("~")[1].equals("\u65e0\u540d") ? "" : (String.valueOf(str.split("~")[1]) + ":");
                optionstr = String.valueOf(optionstr) + zdnamestr + permap.get(zdstr) + " - ";
            }
            if (optionstr.length() > 0) {
                optionstr = optionstr.substring(0, optionstr.length() - 3);
            }
            String nbs = "";
            if (i > 0) {
                nbs = "&nbsp;";
            }
            checkbox = String.valueOf(checkbox) + "<label>" + nbs + "<input type='checkbox' name='" + name + "' value=\"" + optionstr + "\">" + optionstr + "</label>&nbsp;&nbsp;\n";
            checkbox = String.valueOf(checkbox) + "<label>&nbsp;- " + nstr + " &nbsp;<input type='text' size='5' name='" + optionstr + "' value=\"\"></label><br />\n";
            ++i;
        }
        checkbox = String.valueOf(checkbox) + "<input type=hidden name='" + name + "' value='' /><input type=hidden name='dk-" + name + "-value' value='" + nstr + "' />";
        return checkbox;
    }
    
    public static String getFileUpInfo() {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<script src=js/popup.js></script>";
        jscode = String.valueOf(jscode) + "<font onclick=\"uploaddoc()\" src=\"js/nopic.jpg\" style='cursor:hand' id=txt >\u70b9\u51fb\u6b64\u5904\u4e0a\u4f20</font>";
        jscode = String.valueOf(jscode) + "&nbsp;&nbsp;<input type=text readonly style='border:0px' size=30  name=\"docname\" id=\"docname\" value=\"\" />";
        return jscode;
    }
    
    public static String getFileUpInfo2() {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<script src=js/popup.js></script>";
        jscode = String.valueOf(jscode) + "<font onclick=\"uploaddoc2()\" src=\"js/nopic.jpg\" style='cursor:hand' id=filetxt2 >\u70b9\u51fb\u6b64\u5904\u4e0a\u4f20</font>";
        jscode = String.valueOf(jscode) + "&nbsp;&nbsp;<input type=text readonly style='border:0px' size=30  name=\"docname2\" id=\"docname2\" value=\"\" />";
        return jscode;
    }
    
    public static String getFileUpInfo3() {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<script src=js/popup.js></script>";
        jscode = String.valueOf(jscode) + "<font onclick=\"uploaddoc3()\" src=\"js/nopic.jpg\" style='cursor:hand' id=filetxt3 >\u70b9\u51fb\u6b64\u5904\u4e0a\u4f20</font>";
        jscode = String.valueOf(jscode) + "&nbsp;&nbsp;<input type=text readonly style='border:0px' size=30  name=\"docname3\" id=\"docname3\" value=\"\" />";
        return jscode;
    }
    
    public static String tform(final HashMap map, final String formname) {
        String jscode = "";
        try {
            jscode = String.valueOf(jscode) + "<script type=\"text/javascript\">\n";
            jscode = String.valueOf(jscode) + "function getPvalue()\n";
            jscode = String.valueOf(jscode) + "{\n";
            final Set set = map.entrySet();
            final Iterator it = set.iterator();
            while (it.hasNext()) {
                final String pm = it.next().toString();
                String str1 = "";
                String str2 = "";
                final String[] strs = pm.split("=");
                str1 = strs[0];
                if (strs.length == 1) {
                    str2 = "";
                }
                if (strs.length == 2) {
                    str2 = strs[1];
                }
                str2 = str2.replaceAll("\r\n", "---");
                if (!str1.equals("content")) {
                    jscode = String.valueOf(jscode) + " if(document.getElementsByName(\"" + str1 + "\").length>1)\n";
                    jscode = String.valueOf(jscode) + " {\n";
                    jscode = String.valueOf(jscode) + " var radios = document.getElementsByName(\"" + str1 + "\");\n";
                    jscode = String.valueOf(jscode) + " if(radios[0].type=='radio'){\n";
                    jscode = String.valueOf(jscode) + " for(var i=0;i<radios.length;i++)\n";
                    jscode = String.valueOf(jscode) + " {\n";
                    jscode = String.valueOf(jscode) + " if(radios[i].value==\"" + str2 + "\")\n";
                    jscode = String.valueOf(jscode) + " {\n";
                    jscode = String.valueOf(jscode) + " radios[i].checked=\"checked\";\n";
                    jscode = String.valueOf(jscode) + " }\n";
                    jscode = String.valueOf(jscode) + " }\n";
                    jscode = String.valueOf(jscode) + " }\n";
                    jscode = String.valueOf(jscode) + " if(radios[0].type=='checkbox'){\n";
                    jscode = String.valueOf(jscode) + " for(var i=0;i<radios.length;i++)\n";
                    jscode = String.valueOf(jscode) + " {\n";
                    jscode = String.valueOf(jscode) + " if(\"" + str2 + "\".indexOf(radios[i].value)>-1&&radios[i].id.indexOf('choosebox')==-1)\n";
                    jscode = String.valueOf(jscode) + " {\n";
                    jscode = String.valueOf(jscode) + " radios[i].checked=\"checked\";\n";
                    if (str2.indexOf(" - ") > -1) {
                        String[] split;
                        for (int length = (split = str2.split(" ~ ")).length, i = 0; i < length; ++i) {
                            final String strch = split[i];
                            final String strchname = strch.substring(0, strch.lastIndexOf(" - "));
                            jscode = String.valueOf(jscode) + " if(document.getElementsByName('" + strchname + "').length>0)\n";
                            jscode = String.valueOf(jscode) + " {\n";
                            jscode = String.valueOf(jscode) + " document.getElementsByName('" + strchname + "')[0].value='" + strch.substring(strch.lastIndexOf(":") + 1) + "';\n";
                            jscode = String.valueOf(jscode) + " }\n";
                        }
                    }
                    jscode = String.valueOf(jscode) + " }\n";
                    jscode = String.valueOf(jscode) + " }\n";
                    jscode = String.valueOf(jscode) + " }\n";
                    jscode = String.valueOf(jscode) + " if(radios.type=='select'){\n";
                    jscode = String.valueOf(jscode) + " " + formname + "." + str1 + ".value=\"" + str2 + "\";\n";
                    jscode = String.valueOf(jscode) + " }\n";
                    jscode = String.valueOf(jscode) + " }else{\n";
                    jscode = String.valueOf(jscode) + " if(" + formname + "." + str1 + ")\n";
                    jscode = String.valueOf(jscode) + "{\n";
                    jscode = String.valueOf(jscode) + formname + "." + str1 + ".value=\"" + str2 + "\";\n";
                    jscode = String.valueOf(jscode) + "}\n";
                    jscode = String.valueOf(jscode) + "}\n";
                    jscode = String.valueOf(jscode) + "if(document.getElementById(\"txt\"))\n";
                    jscode = String.valueOf(jscode) + "{\n";
                    jscode = String.valueOf(jscode) + "document.getElementById(\"txt\").src=\"upfile/" + map.get("filename") + "\";\n";
                    jscode = String.valueOf(jscode) + "}\n";
                    jscode = String.valueOf(jscode) + "if(document.getElementById(\"txt2\"))\n";
                    jscode = String.valueOf(jscode) + "{\n";
                    jscode = String.valueOf(jscode) + "document.getElementById(\"txt2\").src=\"upfile/" + map.get("filename2") + "\";\n";
                    jscode = String.valueOf(jscode) + "}\n";
                    jscode = String.valueOf(jscode) + "if(document.getElementById(\"txt3\"))\n";
                    jscode = String.valueOf(jscode) + "{\n";
                    jscode = String.valueOf(jscode) + "document.getElementById(\"txt3\").src=\"upfile/" + map.get("filename3") + "\";\n";
                    jscode = String.valueOf(jscode) + "}\n";
                    jscode = String.valueOf(jscode) + "if(document.getElementById(\"txt4\"))\n";
                    jscode = String.valueOf(jscode) + "{\n";
                    jscode = String.valueOf(jscode) + "document.getElementById(\"txt4\").src=\"upfile/" + map.get("filename4") + "\";\n";
                    jscode = String.valueOf(jscode) + "}\n";
                    jscode = String.valueOf(jscode) + "if(document.getElementById(\"txt5\"))\n";
                    jscode = String.valueOf(jscode) + "{\n";
                    jscode = String.valueOf(jscode) + "document.getElementById(\"txt5\").src=\"upfile/" + map.get("filename5") + "\";\n";
                    jscode = String.valueOf(jscode) + "}\n";
                }
            }
            jscode = String.valueOf(jscode) + "}\n";
            jscode = String.valueOf(jscode) + " getPvalue();\n";
            jscode = String.valueOf(jscode) + "</script>\n";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jscode;
    }
    
    public static String generalFileName(final String srcFileName) {
        try {
            final int index = srcFileName.lastIndexOf(".");
            return String.valueOf(StrUtil.generalSrid()) + srcFileName.substring(index).toLowerCase();
        }
        catch (Exception e) {
            return StrUtil.generalSrid();
        }
    }
    
    public static synchronized String getID() {
        final Random random = new Random();
        final StringBuffer ret = new StringBuffer(20);
        final Date date = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.CHINA);
        ret.append(format.format(date));
        final String rand = String.valueOf(Math.abs(random.nextInt()));
        ret.append(rand.substring(0, 4));
        return ret.toString();
    }
    
    public static String getImgUpInfo(final int height) {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<img style=\"cursor: pointer;margin:3px\" onclick=\"uploadimg()\" src=\"js/nopic.jpg\" id=txt height=\"" + height + "\"/>";
        jscode = String.valueOf(jscode) + "<input type=hidden name=\"filename\" id=\"filename\" value=\"\" />";
        jscode = String.valueOf(jscode) + "<script type=\"text/javascript\"  src=\"js/popups.js\"></script>";
        return jscode;
    }
    
    public static String getImgUpInfo2(final int height) {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<img style=\"cursor: hand\" onclick=\"uploadimg2()\" src=\"js/nopic.jpg\" id=txt2 height=\"" + height + "\"/>";
        jscode = String.valueOf(jscode) + "<input type=hidden name=\"filename2\" id=\"filename2\" value=\"\" />";
        jscode = String.valueOf(jscode) + "<script type=\"text/javascript\"  src=\"js/popup.js\"></script>";
        return jscode;
    }
    
    public static String getImgUpInfo3(final int height) {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<img style=\"cursor: hand\" onclick=\"uploadimg3()\" src=\"js/nopic.jpg\" id=txt3 height=\"" + height + "\"/>";
        jscode = String.valueOf(jscode) + "<input type=hidden name=\"filename3\" id=\"filename3\" value=\"\" />";
        jscode = String.valueOf(jscode) + "<script type=\"text/javascript\"  src=\"js/popup.js\"></script>";
        return jscode;
    }
    
    public static String getImgUpInfo4(final int height) {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<img style=\"cursor: hand\" onclick=\"uploadimg4()\" src=\"js/nopic.jpg\" id=txt4 height=\"" + height + "\"/>";
        jscode = String.valueOf(jscode) + "<input type=hidden name=\"filename4\" id=\"filename4\" value=\"\" />";
        jscode = String.valueOf(jscode) + "<script type=\"text/javascript\"  src=\"js/popup.js\"></script>";
        return jscode;
    }
    
    public static String getImgUpInfo5(final int height) {
        String jscode = "";
        jscode = String.valueOf(jscode) + "<img style=\"cursor: hand\" onclick=\"uploadimg5()\" src=\"js/nopic.jpg\" id=txt5 height=\"" + height + "\"/>";
        jscode = String.valueOf(jscode) + "<input type=hidden name=\"filename5\" id=\"filename5\" value=\"\" />";
        jscode = String.valueOf(jscode) + "<script type=\"text/javascript\"  src=\"js/popup.js\"></script>";
        return jscode;
    }
    
    public static String fck(final int height, final String content) {
        String jscode = "<TEXTAREA   name=\"content\" id=\"content\">" + content + "</TEXTAREA>";
        jscode = String.valueOf(jscode) + "<script type=\"text/javascript\"  src=\"fckeditor/fckeditor.js\"></script>";
        jscode = String.valueOf(jscode) + "<script language=\"javascript\">";
        jscode = String.valueOf(jscode) + "function fckinit()";
        jscode = String.valueOf(jscode) + "{";
        jscode = String.valueOf(jscode) + " var of = new FCKeditor(\"content\");";
        jscode = String.valueOf(jscode) + "of.BasePath=\"fckeditor/\";";
        jscode = String.valueOf(jscode) + "of.Height = \"" + height + "\";";
        jscode = String.valueOf(jscode) + "of.ToolbarSet=\"Default\";";
        jscode = String.valueOf(jscode) + "of.ReplaceTextarea();";
        jscode = String.valueOf(jscode) + "}";
        jscode = String.valueOf(jscode) + "fckinit();";
        jscode = String.valueOf(jscode) + "</script>";
        return jscode;
    }
    
    public static synchronized String subStr(String source, final int length) {
        if (source.length() > length) {
            source = String.valueOf(source.substring(0, length)) + "...";
        }
        return source;
    }
    
    public static synchronized String ensubStr(final Object source, final int length) {
        String msource = filterStrIgnoreCase(source.toString(), "<", ">");
        if (msource.length() > length) {
            msource = String.valueOf(msource.substring(0, length)) + "...";
        }
        return msource;
    }
    
    public static String getDateStr() {
        String dateString = "";
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date currentTime_1 = new Date();
            dateString = formatter.format(currentTime_1);
        }
        catch (Exception ex) {}
        return dateString;
    }
    
    public static String getUTFStr(String str) {
        if (str == null) {
            return "";
        }
        try {
            str = new String(str.getBytes("UTF-8"), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    public static String getGBKStr(final String str) throws UnsupportedEncodingException {
        if (str == null) {
            return "";
        }
        return new String(str.getBytes("UTF-8"), "UTF-8");
    }
    
    public static String getGB2312Str(final String str) throws UnsupportedEncodingException {
        if (str == null) {
            return "";
        }
        return new String(str.getBytes("UTF-8"), "UTF-8");
    }
    
    public static String getDay(final String date, final int day) {
        final String b = date.substring(0, 10);
        final String c = b.substring(0, 4);
        final String d = b.substring(5, 7);
        final String f = b.substring(8, 10);
        final String aa = String.valueOf(c) + "/" + d + "/" + f;
        final String a = "";
        final DateFormat dateFormat = DateFormat.getDateInstance(2);
        final GregorianCalendar grc = new GregorianCalendar();
        grc.setTime(new Date(aa));
        grc.add(5, day);
        final String resu = dateFormat.format(grc.getTime());
        final String[] t = resu.split("-");
        String sesuu = "";
        for (int i = 0; i < t.length; ++i) {
            if (t[i].length() == 1) {
                t[i] = "0" + t[i];
            }
            sesuu = String.valueOf(sesuu) + t[i] + "-";
        }
        return sesuu.substring(0, 10);
    }
    
    public static int dayToday(String DATE1, String DATE2) {
        int i = 0;
        if (DATE1.indexOf(" ") > -1) {
            DATE1 = DATE1.substring(0, DATE1.indexOf(" "));
        }
        if (DATE2.indexOf(" ") > -1) {
            DATE2 = DATE2.substring(0, DATE2.indexOf(" "));
        }
        final String[] d1 = DATE1.split("-");
        if (d1[1].length() == 1) {
            DATE1 = String.valueOf(d1[0]) + "-0" + d1[1];
        }
        else {
            DATE1 = String.valueOf(d1[0]) + "-" + d1[1];
        }
        if (d1[2].length() == 1) {
            DATE1 = String.valueOf(DATE1) + "-0" + d1[2];
        }
        else {
            DATE1 = String.valueOf(DATE1) + "-" + d1[2];
        }
        final String[] d2 = DATE2.split("-");
        if (d2[1].length() == 1) {
            DATE2 = String.valueOf(d2[0]) + "-0" + d2[1];
        }
        else {
            DATE2 = String.valueOf(d2[0]) + "-" + d2[1];
        }
        if (d2[2].length() == 1) {
            DATE2 = String.valueOf(DATE2) + "-0" + d2[2];
        }
        else {
            DATE2 = String.valueOf(DATE2) + "-" + d2[2];
        }
        for (int j = 0; j < 10000; ++j) {
            i = j;
            final String gday = getDay(DATE1, j);
            if (gday.equals(DATE2)) {
                break;
            }
        }
        return i;
    }
    
    public static String compare_datezq(final String DATE1, final String DATE2) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            final Date dt1 = df.parse(DATE1);
            final Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return "big";
            }
            if (dt1.getTime() < dt2.getTime()) {
                return "small";
            }
            return "den";
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return "den";
        }
    }
    
    public static String filterStrIgnoreCase(String source, final String from, final String to) {
        String sourceLowcase = source.toLowerCase();
        String subLowcase2;
        String subLowcase1;
        String sub2 = sub2 = (subLowcase1 = (subLowcase2 = ""));
        int start = 0;
        boolean done = true;
        if (source == null) {
            return null;
        }
        if (from == null || from.equals("") || to == null || to.equals("")) {
            return source;
        }
        while (done) {
            start = sourceLowcase.indexOf(from, start);
            if (start == -1) {
                break;
            }
            subLowcase1 = sourceLowcase.substring(0, start);
            sub2 = source.substring(0, start);
            int end = sourceLowcase.indexOf(to, start);
            if (end == -1) {
                end = sourceLowcase.indexOf("/>", start);
                if (end != -1) {
                    continue;
                }
                done = false;
            }
            else {
                end += to.length();
                subLowcase2 = sourceLowcase.substring(end, source.length());
                sub2 = source.substring(end, source.length());
                sourceLowcase = String.valueOf(subLowcase1) + subLowcase2;
                source = String.valueOf(sub2) + sub2;
            }
        }
        return source;
    }
    
    public static void delPic(final String path, final String img) {
        if (img != null && !img.equals("")) {
            final File file1 = new File(String.valueOf(path) + "/" + img);
            if (file1.exists()) {
                file1.deleteOnExit();
                file1.delete();
            }
        }
    }
}
