// 
// 
// 

package control;

import jxl.Cell;
import jxl.Sheet;
import org.apache.commons.fileupload.RequestContext;
import java.util.List;
import java.io.PrintWriter;
import jxl.Workbook;
import org.apache.commons.fileupload.FileItem;
import java.util.ArrayList;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.File;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import java.util.HashMap;
import util.Info;
import dao.CommDAO;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class MainCtrl extends HttpServlet
{
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void go(final String url, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            request.getRequestDispatcher(url).forward((ServletRequest)request, (ServletResponse)response);
        }
        catch (ServletException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public void gor(final String url, final HttpServletRequest request, final HttpServletResponse response) {
        try {
            response.sendRedirect(url);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        String ac = request.getParameter("ac");
        if (ac == null) {
            ac = "";
        }
        final CommDAO dao = new CommDAO();
        final String date = Info.getDateStr();
        final String today = date.substring(0, 10);
        final String tomonth = date.substring(0, 7);
        if (ac.equals("login")) {
            final String username = request.getParameter("username");
            final String password = request.getParameter("pwd1");
            final String utype = request.getParameter("cx");
            final String pagerandom = (request.getParameter("pagerandom") == null) ? "" : request.getParameter("pagerandom");
            final String random = (String)request.getSession().getAttribute("random");
            if (!pagerandom.equals(random) && request.getParameter("a") != null) {
                request.setAttribute("random", (Object)"");
                this.go("/index.jsp", request, response);
            }
            else {
                String sql1 = "";
                if (utype.equals("\u6ce8\u518c\u7528\u6237")) {
                    sql1 = "select * from yonghuzhuce where yonghuming='" + username + "' and mima='" + password + "' ";
                }
                else {
                    sql1 = "select * from yuangongxinxi where gonghao='" + username + "' and mima='" + password + "'";
                }
                final List<HashMap> userlist1 = dao.select(sql1);
                if (userlist1.size() == 1) {
                    if (utype.equals("\u6ce8\u518c\u7528\u6237")) {
                        request.getSession().setAttribute("username", userlist1.get(0).get("yonghuming"));
                    }
                    else {
                        request.getSession().setAttribute("username", userlist1.get(0).get("gonghao"));
                    }
                    request.getSession().setAttribute("cx", (Object)utype);
                    this.gor("index.jsp", request, response);
                }
                else {
                    request.setAttribute("error", (Object)"");
                    this.go("/index.jsp", request, response);
                }
            }
        }
        if (ac.equals("adminlogin")) {
            final String username = request.getParameter("username");
            final String password = request.getParameter("pwd");
            final String utype = request.getParameter("cx");
            final String pagerandom = (request.getParameter("pagerandom") == null) ? "" : request.getParameter("pagerandom");
            final String random = (String)request.getSession().getAttribute("random");
            if (!pagerandom.equals(random) && request.getParameter("a") != null) {
                request.setAttribute("random", (Object)"");
                this.go("/login.jsp", request, response);
            }
            else {
                final String sql1 = "select * from allusers where username='" + username + "' and pwd='" + password + "'  ";
                final List<HashMap> userlist1 = dao.select(sql1);
                if (userlist1.size() == 1) {
                    request.getSession().setAttribute("username", userlist1.get(0).get("username"));
                    request.getSession().setAttribute("cx", userlist1.get(0).get("cx"));
                    this.gor("main.jsp", request, response);
                }
                else {
                    request.setAttribute("error", (Object)"");
                    this.go("/login.jsp", request, response);
                }
            }
        }
        if (ac.equals("uppass")) {
            final String olduserpass = request.getParameter("ymm");
            final String userpass = request.getParameter("xmm1");
            final String copyuserpass = request.getParameter("xmm2");
            final HashMap m = dao.getmaps("yonghuming", (String)request.getSession().getAttribute("username"), "yonghuzhuce");
            if (!m.get("mima").equals(olduserpass)) {
                request.setAttribute("error", (Object)"");
                this.go("mod2.jsp", request, response);
            }
            else {
                final String sql2 = "update yonghuzhuce set mima='" + userpass + "' where yonghuming='" + (String)request.getSession().getAttribute("username") + "'";
                dao.commOper(sql2);
                request.setAttribute("suc", (Object)"");
                this.go("mod2.jsp", request, response);
            }
        }
        if (ac.equals("adminuppass")) {
            final String olduserpass = request.getParameter("ymm");
            final String userpass = request.getParameter("xmm1");
            final String copyuserpass = request.getParameter("xmm2");
            final HashMap m = dao.getmaps("username", (String)request.getSession().getAttribute("username"), "allusers");
            if (!m.get("pwd").equals(olduserpass)) {
                request.setAttribute("error", (Object)"");
                this.go("mod.jsp", request, response);
            }
            else {
                final String sql2 = "update allusers set pwd='" + userpass + "' where username='" + (String)request.getSession().getAttribute("username") + "'";
                dao.commOper(sql2);
                request.setAttribute("suc", (Object)"");
                this.go("mod.jsp", request, response);
            }
        }
        if (ac.equals("uploaddoc")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploaddoc.jsp?docname=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ac.equals("uploaddoc2")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploaddoc2.jsp?docname=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ac.equals("uploaddoc3")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploaddoc3.jsp?docname=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ac.equals("importexcel")) {
            final String page = request.getParameter("page");
            final String whzdstr = request.getParameter("whzdstr");
            final String tablename = request.getParameter("tablename");
            try {
                String filename2 = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext2 = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext2)) {
                    final DiskFileItemFactory factory2 = new DiskFileItemFactory();
                    factory2.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload2 = new ServletFileUpload((FileItemFactory)factory2);
                    upload2.setSizeMax(104857600L);
                    List items2 = new ArrayList();
                    items2 = upload2.parseRequest(request);
                    final FileItem fileItem2 = (FileItem)items2.get(0);
                    if (fileItem2.getName() != null && fileItem2.getSize() != 0L && fileItem2.getName() != null && fileItem2.getSize() != 0L) {
                        final File fullFile2 = new File(fileItem2.getName());
                        filename2 = Info.generalFileName(fullFile2.getName());
                        final File newFile2 = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename2);
                        try {
                            fileItem2.write(newFile2);
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (filename2.indexOf(".xls") > -1) {
                        try {
                            final Workbook workbook = Workbook.getWorkbook(new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename2));
                            final Sheet sheet = workbook.getSheet(0);
                            for (int i = 1; i < 1000; ++i) {
                                Cell cell = null;
                                try {
                                    String isql = "insert into " + tablename + "(";
                                    String[] split;
                                    for (int length = (split = whzdstr.split("-")).length, k = 0; k < length; ++k) {
                                        final String str = split[k];
                                        isql = String.valueOf(isql) + str + ",";
                                    }
                                    isql = isql.substring(0, isql.length() - 1);
                                    isql = String.valueOf(isql) + ")values(";
                                    int j = 0;
                                    int empty = 1;
                                    String[] split2;
                                    for (int length2 = (split2 = whzdstr.split("-")).length, l = 0; l < length2; ++l) {
                                        final String str2 = split2[l];
                                        cell = sheet.getCell(j, i);
                                        isql = String.valueOf(isql) + "'" + cell.getContents() + "',";
                                        final String content = (cell.getContents() == null) ? "" : cell.getContents();
                                        if (!"".equals(content.trim())) {
                                            empty = 0;
                                        }
                                        ++j;
                                    }
                                    if (empty != 1) {
                                        isql = isql.substring(0, isql.length() - 1);
                                        isql = String.valueOf(isql) + ")";
                                        dao.commOper(isql);
                                    }
                                }
                                catch (Exception ex) {}
                            }
                            workbook.close();
                        }
                        catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                }
                this.go("/admin/" + page + "?docname=" + filename2, request, response);
            }
            catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        if (ac.equals("uploadimg")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploadimg.jsp?filename=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ac.equals("uploadimg2")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploadimg2.jsp?filename=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ac.equals("uploadimg3")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploadimg3.jsp?filename=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ac.equals("uploadimg4")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploadimg4.jsp?filename=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ac.equals("uploadimg5")) {
            try {
                String filename = "";
                request.setCharacterEncoding("UTF-8");
                final RequestContext requestContext = (RequestContext)new ServletRequestContext(request);
                if (FileUpload.isMultipartContent(requestContext)) {
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setRepository(new File(String.valueOf(request.getRealPath("/upfile/")) + "/"));
                    final ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
                    upload.setSizeMax(104857600L);
                    List items = new ArrayList();
                    items = upload.parseRequest(request);
                    final FileItem fileItem = (FileItem)items.get(0);
                    if (fileItem.getName() != null && fileItem.getSize() != 0L && fileItem.getName() != null && fileItem.getSize() != 0L) {
                        final File fullFile = new File(fileItem.getName());
                        filename = Info.generalFileName(fullFile.getName());
                        final File newFile = new File(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename);
                        try {
                            fileItem.write(newFile);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.go("/js/uploadimg5.jsp?filename=" + filename, request, response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        dao.close();
        out.flush();
        out.close();
    }
    
    public void init() throws ServletException {
    }
}
