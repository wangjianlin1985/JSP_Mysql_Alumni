// 
// 
// 

package dao;

import java.sql.DriverManager;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import util.Info;
import java.util.HashMap;
import java.sql.Connection;

public class CommDAO
{
    public static String dbname;
    public static String dbtype;
    public static Connection conn;
    
    static {
        CommDAO.dbname = "";
        CommDAO.dbtype = "";
        CommDAO.conn = null;
    }
    
    public CommDAO() {
        CommDAO.conn = this.getConn();
    }
    
    public void jiajian(final String tablename, final String colname, final String id, final String num) {
        final HashMap map = this.getmap(id, tablename);
        String value = map.get(colname).toString();
        if (value.equals("")) {
            value = "0";
        }
        final int i = Integer.parseInt(value);
        final int j = Integer.parseInt(num);
        this.commOper("update " + tablename + " set " + colname + " = " + (i + j) + " where id=" + id);
    }
    
    public String DynamicImage(final String categoryid, final int cut, final int width, final int height) {
        final StringBuffer imgStr = new StringBuffer();
        final StringBuffer thePics1 = new StringBuffer();
        final StringBuffer theLinks1 = new StringBuffer();
        final StringBuffer theTexts1 = new StringBuffer();
        imgStr.append("<div id=picViwer1  style='background-color: #ffffff' align=center></div><SCRIPT src='js/dynamicImage.js' type=text/javascript></SCRIPT>\n<script language=JavaScript>\n");
        thePics1.append("var thePics1=\n'");
        theLinks1.append("var theLinks1='");
        theTexts1.append("var theTexts1='");
        final List<HashMap> co = this.select("select * from xinwentongzhi where shouyetupian<>'' and shouyetupian<>'null'  and shouyetupian  like '%.jpg' order by id desc", 1, 6);
        final int i = co.size();
        int j = 0;
        for (final HashMap b : co) {
            ++j;
            final int id = Integer.parseInt(b.get("id").toString());
            final String title = Info.subStr(b.get("biaoti").toString(), 21);
            final String url = new StringBuilder().append(b.get("shouyetupian")).toString();
            final String purl = "gg_detail.jsp?id=" + b.get("id");
            if (j != i) {
                thePics1.append(String.valueOf(url.replaceAll("\n", "")) + "|");
                theLinks1.append(String.valueOf(purl) + "|");
                theTexts1.append(String.valueOf(title) + "|");
            }
            if (j == i) {
                thePics1.append(url.replaceAll("\n", ""));
                theLinks1.append("gg_detail.jsp?id=" + b.get("id"));
                theTexts1.append(title);
            }
        }
        thePics1.append("';");
        theLinks1.append("';");
        theTexts1.append("';");
        imgStr.append((Object)thePics1 + "\n");
        imgStr.append((Object)theLinks1 + "\n");
        imgStr.append((Object)theTexts1 + "\n");
        imgStr.append("\n setPic(thePics1,theLinks1,theTexts1," + width + "," + height + ",'picViwer1');</script>");
        return imgStr.toString();
    }
    
    public HashMap getmap(final String id, final String table) {
        final List<HashMap> list = new ArrayList<HashMap>();
        try {
            final Statement st = CommDAO.conn.createStatement();
            final ResultSet rs = st.executeQuery("select * from " + table + " where id=" + id);
            final ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                final HashMap map = new HashMap();
                for (int i = rsmd.getColumnCount(), j = 1; j <= i; ++j) {
                    if (!rsmd.getColumnName(j).equals("ID")) {
                        String str = (rs.getString(j) == null) ? "" : rs.getString(j);
                        if (str.equals("null")) {
                            str = "";
                        }
                        map.put(rsmd.getColumnName(j), str);
                    }
                    else {
                        map.put("id", rs.getString(j));
                    }
                }
                list.add(map);
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }
    
    public HashMap getmaps(final String nzd, final String zdz, final String table) {
        final List<HashMap> list = new ArrayList<HashMap>();
        try {
            final Statement st = CommDAO.conn.createStatement();
            final ResultSet rs = st.executeQuery("select * from " + table + " where " + nzd + "='" + zdz + "'");
            final ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                final HashMap map = new HashMap();
                for (int i = rsmd.getColumnCount(), j = 1; j <= i; ++j) {
                    if (!rsmd.getColumnName(j).equals("ID")) {
                        String str = (rs.getString(j) == null) ? "" : rs.getString(j);
                        if (str.equals("null")) {
                            str = "";
                        }
                        map.put(rsmd.getColumnName(j), str);
                    }
                    else {
                        map.put("id", rs.getString(j));
                    }
                }
                list.add(map);
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }
    
    public String insert(final HttpServletRequest request, final HttpServletResponse response, final String tablename, final HashMap extmap, final boolean alert, final boolean reflush, final String tzurl) {
        extmap.put("addtime", Info.getDateStr());
        if (request.getParameter("f") != null) {
            final HashMap typemap = new HashMap();
            final ArrayList<String> collist = new ArrayList<String>();
            String sql = "insert into " + tablename + "(";
            final Connection conn = this.getConn();
            try {
                final Statement st = conn.createStatement();
                final ResultSet rs = st.executeQuery("select * from " + tablename);
                final ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = rsmd.getColumnCount(), j = 1; j <= i; ++j) {
                    if (!rsmd.getColumnName(j).equals("id")) {
                        if (!rsmd.getColumnName(j).equals("ID")) {
                            if (!rsmd.getColumnName(j).equals("iD")) {
                                if (!rsmd.getColumnName(j).equals("Id")) {
                                    typemap.put(String.valueOf(rsmd.getColumnName(j)) + "---", rsmd.getColumnTypeName(j));
                                    collist.add(rsmd.getColumnName(j));
                                    sql = String.valueOf(sql) + rsmd.getColumnName(j) + ",";
                                }
                            }
                        }
                    }
                }
                sql = sql.substring(0, sql.length() - 1);
                sql = String.valueOf(sql) + ") values(";
                rs.close();
                st.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            final Enumeration enumeration = request.getParameterNames();
            String names = ",";
            while (enumeration.hasMoreElements()) {
                names = String.valueOf(names) + enumeration.nextElement().toString() + ",";
            }
            try {
                final Statement st2 = conn.createStatement();
                for (final String str : collist) {
                    if (names.indexOf("," + str + ",") > -1) {
                        final String[] values = request.getParameterValues(str);
                        String value = "";
                        String[] array;
                        for (int length = (array = values).length, k = 0; k < length; ++k) {
                            String vstr = array[k];
                            if (vstr == null) {
                                vstr = "";
                            }
                            if (vstr.equals("null")) {
                                vstr = "";
                            }
                            if (!vstr.trim().equals("")) {
                                if (request.getParameter(vstr) != null && !"".equals(request.getParameter(vstr)) && request.getParameter("dk-" + str + "-value") != null) {
                                    final String dkv = request.getParameter(vstr);
                                    final String dknamevalue = request.getParameter("dk-" + str + "-value");
                                    vstr = String.valueOf(vstr) + " - " + dknamevalue + ":" + dkv;
                                }
                                value = String.valueOf(value) + vstr + " ~ ";
                            }
                        }
                        if (value == null) {
                            value = "";
                        }
                        if (value.equals("null")) {
                            value = "";
                        }
                        if (value.length() > 0) {
                            value = value.substring(0, value.length() - 3);
                        }
                        if (typemap.get(String.valueOf(str) + "---").equals("int")) {
                            sql = String.valueOf(sql) + (value.equals("") ? -10 : value) + ",";
                        }
                        else {
                            sql = String.valueOf(sql) + "'" + (value.equals("null") ? "" : value) + "',";
                        }
                    }
                    else if (typemap.get(String.valueOf(str) + "---").equals("int")) {
                        sql = String.valueOf(sql) + (Object)((extmap.get(str) == null) ? "" : extmap.get(str)) + ",";
                    }
                    else {
                        sql = String.valueOf(sql) + "'" + (Object)((extmap.get(str) == null) ? "" : extmap.get(str)) + "',";
                    }
                }
                sql = String.valueOf(sql.substring(0, sql.length() - 1)) + ")";
                System.out.println(sql);
                this.commOper(sql);
                st2.close();
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
            String str2 = "";
            if (!reflush) {
                str2 = String.valueOf(str2) + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> \n";
            }
            str2 = String.valueOf(str2) + "<script language=javascript>\n";
            if (alert) {
                str2 = String.valueOf(str2) + "alert('\u64cd\u4f5c\u6210\u529f');\n";
            }
            if (tzurl.equals("")) {
                if (reflush) {
                    str2 = String.valueOf(str2) + "parent.location=parent.location;\n";
                }
                else {
                    str2 = String.valueOf(str2) + "window.location=String(window.location).replace(new RegExp('f=f', 'g'), '');";
                }
            }
            else {
                str2 = String.valueOf(str2) + "location.href='" + tzurl + "';\n";
            }
            str2 = String.valueOf(str2) + "</script>";
            PrintWriter wrt = null;
            try {
                wrt = response.getWriter();
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
            wrt.write(str2);
        }
        return "";
    }
    
    public void delete(final HttpServletRequest request, final String tablename) {
        final int i = 0;
        try {
            String did = request.getParameter("did");
            if (did == null) {
                did = request.getParameter("scid");
            }
            if (did != null && did.length() > 0) {
                final Statement st = CommDAO.conn.createStatement();
                System.out.println("delete from " + tablename + " where id=" + did);
                st.execute("delete from " + tablename + " where id=" + did);
                st.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getCols(final String table) {
        String str = "";
        final Connection conn = this.getConn();
        try {
            final Statement st = conn.createStatement();
            final ResultSet rs = st.executeQuery("select * from " + table);
            final ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = rsmd.getColumnCount(), j = 2; j <= i; ++j) {
                str = String.valueOf(str) + rsmd.getColumnName(j) + ",";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }
    
    public String update(final HttpServletRequest request, final HttpServletResponse response, final String tablename, final HashMap extmap, final boolean alert, final boolean reflush, final String tzurl) {
        if (request.getParameter("f") != null) {
            final Enumeration enumeration = request.getParameterNames();
            String names = ",";
            while (enumeration.hasMoreElements()) {
                names = String.valueOf(names) + enumeration.nextElement().toString() + ",";
            }
            final HashMap typemap = new HashMap();
            final ArrayList<String> collist = new ArrayList<String>();
            String sql = "update " + tablename + " set ";
            final Connection conn = this.getConn();
            try {
                final Statement st = conn.createStatement();
                final ResultSet rs = st.executeQuery("select * from " + tablename);
                final ResultSetMetaData rsmd = rs.getMetaData();
                final int i = rsmd.getColumnCount();
                System.out.println(i);
                for (int j = 1; j <= i; ++j) {
                    if (!rsmd.getColumnName(j).equals("id")) {
                        if (!rsmd.getColumnName(j).equals("ID")) {
                            if (!rsmd.getColumnName(j).equals("Id")) {
                                if (!rsmd.getColumnName(j).equals("iD")) {
                                    typemap.put(String.valueOf(rsmd.getColumnName(j)) + "---", rsmd.getColumnTypeName(j));
                                    collist.add(rsmd.getColumnName(j));
                                    if (names.indexOf("," + rsmd.getColumnName(j) + ",") > -1) {
                                        final String[] values = request.getParameterValues(rsmd.getColumnName(j));
                                        String value = "";
                                        String[] array;
                                        for (int length = (array = values).length, k = 0; k < length; ++k) {
                                            String vstr = array[k];
                                            if (vstr == null) {
                                                vstr = "";
                                            }
                                            if (vstr.equals("null")) {
                                                vstr = "";
                                            }
                                            if (!vstr.trim().equals("")) {
                                                if (request.getParameter(vstr) != null && !"".equals(request.getParameter(vstr)) && request.getParameter("dk-" + rsmd.getColumnName(j) + "-value") != null) {
                                                    final String dkv = request.getParameter(vstr);
                                                    final String dknamevalue = request.getParameter("dk-" + rsmd.getColumnName(j) + "-value");
                                                    vstr = String.valueOf(vstr) + " - " + dknamevalue + ":" + dkv;
                                                    System.out.println(vstr);
                                                }
                                                value = String.valueOf(value) + vstr + " ~ ";
                                            }
                                        }
                                        if (value == null) {
                                            value = "";
                                        }
                                        if (value.equals("null")) {
                                            value = "";
                                        }
                                        if (value.length() > 0) {
                                            value = value.substring(0, value.length() - 3);
                                        }
                                        if (rsmd.getColumnTypeName(j).equals("int")) {
                                            sql = String.valueOf(sql) + rsmd.getColumnName(j) + "=" + value + ",";
                                        }
                                        else {
                                            sql = String.valueOf(sql) + rsmd.getColumnName(j) + "='" + value + "',";
                                        }
                                    }
                                    else if (extmap.get(rsmd.getColumnName(j)) != null) {
                                        if (rsmd.getColumnTypeName(j).equals("int")) {
                                            sql = String.valueOf(sql) + rsmd.getColumnName(j) + "=" + extmap.get(rsmd.getColumnName(j)) + ",";
                                        }
                                        else {
                                            sql = String.valueOf(sql) + rsmd.getColumnName(j) + "='" + extmap.get(rsmd.getColumnName(j)) + "',";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                sql = sql.substring(0, sql.length() - 1);
                sql = String.valueOf(sql) + " where id=" + request.getParameter("id");
                System.out.println(sql);
                final Statement st2 = conn.createStatement();
                st2.execute(sql);
                st2.close();
                rs.close();
                st.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            String str = "";
            if (!reflush) {
                str = String.valueOf(str) + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
            }
            str = String.valueOf(str) + "<script language=javascript>\n";
            if (alert) {
                str = String.valueOf(str) + "alert('\u64cd\u4f5c\u6210\u529f');\n";
            }
            if (tzurl.equals("")) {
                if (reflush) {
                    str = String.valueOf(str) + "parent.location=parent.location;\n";
                }
                else {
                    str = String.valueOf(str) + "window.location=String(window.location).replace(new RegExp('f=f', 'g'), '');";
                }
            }
            else {
                str = String.valueOf(str) + "location.href='" + tzurl + "';\n";
            }
            str = String.valueOf(str) + "</script>\n";
            PrintWriter wrt = null;
            try {
                wrt = response.getWriter();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            wrt.write(str);
        }
        return "";
    }
    
    public Connection getConn() {
        try {
            if (CommDAO.conn == null || CommDAO.conn.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                CommDAO.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jsp_xiaoyouwang", "root", "123456");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return CommDAO.conn;
    }
    
    public int getInt(final String sql) {
        int i = 0;
        try {
            final Statement st = CommDAO.conn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                i = rs.getInt(1);
            }
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public double getDouble(final String sql) {
        double i = 0.0;
        try {
            final Statement st = CommDAO.conn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                i = rs.getDouble(1);
            }
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public void commOper(final String sql) {
        System.out.println(sql);
        try {
            final Statement st = CommDAO.conn.createStatement();
            st.execute(sql);
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void commOperSqls(final ArrayList<String> sql) {
        try {
            CommDAO.conn.setAutoCommit(false);
            for (int i = 0; i < sql.size(); ++i) {
                final Statement st = CommDAO.conn.createStatement();
                System.out.println(sql.get(i));
                st.execute(sql.get(i));
                st.close();
            }
            CommDAO.conn.commit();
        }
        catch (SQLException e2) {
            try {
                CommDAO.conn.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e2.printStackTrace();
            try {
                CommDAO.conn.setAutoCommit(true);
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
            return;
        }
        finally {
            try {
                CommDAO.conn.setAutoCommit(true);
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
        try {
            CommDAO.conn.setAutoCommit(true);
        }
        catch (SQLException e3) {
            e3.printStackTrace();
        }
    }
    
    public List<HashMap> select(final String sql) {
        System.out.println(sql);
        List<HashMap> list = new ArrayList<HashMap>();
        try {
            final Statement st = CommDAO.conn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            final ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                final HashMap map = new HashMap();
                for (int i = rsmd.getColumnCount(), j = 1; j <= i; ++j) {
                    if (!rsmd.getColumnName(j).equals("ID")) {
                        String str = (rs.getString(j) == null) ? "" : rs.getString(j);
                        if (str.equals("null")) {
                            str = "";
                        }
                        map.put(rsmd.getColumnName(j), str);
                    }
                    else {
                        map.put("id", rs.getString(j));
                    }
                }
                list.add(map);
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            if (sql.equals("show tables")) {
                list = this.select("select table_name from   INFORMATION_SCHEMA.tables");
            }
            else {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public List<List> selectforlist(final String sql) {
        final List<List> list = new ArrayList<List>();
        try {
            final Statement st = CommDAO.conn.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            final ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                final List<String> list2 = new ArrayList<String>();
                for (int i = rsmd.getColumnCount(), j = 1; j <= i; ++j) {
                    if (!rsmd.getColumnName(j).equals("ID")) {
                        String str = (rs.getString(j) == null) ? "" : rs.getString(j);
                        if (str.equals("null")) {
                            str = "";
                        }
                        list2.add(str);
                    }
                    else {
                        list2.add(rs.getString(j));
                    }
                }
                list.add(list2);
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void close() {
    }
    
    public List<HashMap> select(final String sql, final int pageno, final int rowsize) {
        List<HashMap> list = new ArrayList<HashMap>();
        final List<HashMap> mlist = new ArrayList<HashMap>();
        try {
            list = this.select(sql);
            final int min = (pageno - 1) * rowsize;
            final int max = pageno * rowsize;
            for (int i = 0; i < list.size(); ++i) {
                if (i >= min && i <= max - 1) {
                    mlist.add(list.get(i));
                }
            }
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
        return mlist;
    }
    
    public static void main(final String[] args) {
    }
    
    public int getwzs(final String nbk) {
        int i = 0;
        final Connection conn = this.getConn();
        try {
            final Statement st = conn.createStatement();
            final ResultSet rs = st.executeQuery("select * from tiezi where bankuai='" + nbk + "' and fid=0");
            while (rs.next()) {
                ++i;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public int gettzs(final String tzid) {
        int i = 0;
        final Connection conn = this.getConn();
        try {
            final Statement st = conn.createStatement();
            final ResultSet rs = st.executeQuery("select * from tiezi where fid=" + tzid);
            final ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                ++i;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
