// 
// 
// 

package util;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import dao.CommDAO;
import java.util.Collection;

public class PageManager
{
    public static final int DEFAULTPAGESIZE = 20;
    public static final int segment = 10;
    protected int currentPage;
    protected int pageSize;
    protected long pageNumber;
    protected long count;
    protected Collection collection;
    protected CommDAO dao;
    protected String info;
    protected String path;
    protected HttpServletRequest request;
    protected String parameter;
    
    private PageManager() {
        this.dao = new CommDAO();
        this.parameter = "";
    }
    
    protected PageManager(final String path, final int pageSize, final HttpServletRequest request) {
        this.dao = new CommDAO();
        this.parameter = "";
        this.currentPage = 1;
        this.pageNumber = 1L;
        this.count = 0L;
        this.pageSize = ((pageSize <= 0) ? 20 : pageSize);
        this.request = request;
        this.path = path;
        request.setAttribute("page", (Object)this);
        try {
            this.currentPage = ((Integer.parseInt(request.getParameter("currentPage")) <= 0) ? 1 : Integer.parseInt(request.getParameter("currentPage")));
        }
        catch (Exception e) {
            try {
                this.currentPage = Integer.parseInt((String)request.getSession().getAttribute("currentPage"));
            }
            catch (Exception e2) {
                this.currentPage = 1;
            }
        }
    }
    
    public static PageManager getPage(final String path, final int pageSize, final HttpServletRequest request) {
        return new PageManager(path, pageSize, request);
    }
    
    public void doList(final String hql) {
        this.count = this.dao.select(hql).size();
        if (this.count != 0L) {
            this.pageNumber = ((this.count % this.pageSize == 0L) ? (this.count / this.pageSize) : (this.count / this.pageSize + 1L));
            if (this.currentPage > this.pageNumber) {
                this.currentPage = (int)this.pageNumber;
            }
        }
        this.request.getSession().setAttribute("currentPage", (Object)String.valueOf(this.currentPage));
        this.collection = this.dao.select(hql, this.currentPage, this.pageSize);
        this.refreshUrl();
    }
    
    public void addParameter(final List parameter) {
        final StringBuffer para = new StringBuffer("");
        if (parameter != null && parameter.size() != 0) {
            final Iterator iterator = parameter.iterator();
            while (iterator.hasNext()) {
                para.append("&").append(iterator.next().toString());
            }
        }
        this.parameter = para.toString();
    }
    
    protected void refreshUrl() {
        final StringBuffer buf = new StringBuffer();
        buf.append("<font color='#1157B7'>\u5171").append(this.count);
        buf.append("\u6761");
        buf.append("&nbsp;&nbsp;");
        buf.append("\u7b2c").append(this.currentPage).append("/").append(this.pageNumber).append("\u9875");
        buf.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        if (this.currentPage == 1) {
            buf.append("\u9996\u9875");
        }
        else {
            buf.append("<a href='").append(this.path).append("&currentPage=1").append(this.parameter).append("' class='ls'>").append("\u9996\u9875").append("</a>");
        }
        buf.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        if (this.currentPage > 1) {
            buf.append("<a href='").append(this.path).append("&currentPage=").append(this.currentPage - 1).append(this.parameter).append("' class='ls'>").append("\u4e0a\u9875").append("</a>");
        }
        else {
            buf.append("\u4e0a\u9875");
        }
        buf.append("&nbsp;&nbsp;");
        final int currentSegment = (this.currentPage % 10 == 0) ? (this.currentPage / 10) : (this.currentPage / 10 + 1);
        buf.append("&nbsp;&nbsp;");
        if (this.currentPage < this.pageNumber) {
            buf.append("<a href='").append(this.path).append("&currentPage=").append(this.currentPage + 1).append(this.parameter).append("' class='ls'>").append("\u4e0b\u9875").append("</a>");
        }
        else {
            buf.append("\u4e0b\u9875");
        }
        buf.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        if (this.currentPage == this.pageNumber) {
            buf.append("\u672b\u9875&nbsp;&nbsp;");
        }
        else {
            buf.append("<a href='").append(this.path).append("&currentPage=").append(this.pageNumber).append(this.parameter).append("' class='ls'>").append("\u672b\u9875").append("</a></font>&nbsp;&nbsp;");
        }
        buf.append("<select onchange=\"javascript:window.location='").append(this.path).append("&currentPage='+").append("this.options[this.selectedIndex].value").append(this.parameter).append("\">");
        for (int i = 0; i < this.pageNumber; ++i) {
            if (this.currentPage == i + 1) {
                buf.append("<option value=" + (i + 1) + " selected=\"selected\">" + (i + 1) + "</option>");
            }
            else {
                buf.append("<option value=" + (i + 1) + ">" + (i + 1) + "</option>");
            }
        }
        buf.append("</select>");
        this.info = buf.toString();
    }
    
    public Collection getCollection() {
        return this.collection;
    }
    
    public long getCount() {
        return this.count;
    }
    
    public int getCurrentPage() {
        return this.currentPage;
    }
    
    public long getPageNumber() {
        return this.pageNumber;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public static ArrayList<HashMap> getPages(final String url, final int pagesize, final String sql, final HttpServletRequest request) {
        final PageManager pageManager = getPage(url, pagesize, request);
        pageManager.doList(sql);
        final PageManager bean = (PageManager)request.getAttribute("page");
        final ArrayList<HashMap> nlist = (ArrayList<HashMap>)bean.getCollection();
        return nlist;
    }
}
