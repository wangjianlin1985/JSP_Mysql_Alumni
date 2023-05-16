// 
// 
// 

package control;

import java.io.OutputStream;
import util.Info;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class Upload extends HttpServlet
{
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final StringBuffer sb = new StringBuffer(50);
        response.setContentType("application/x-msdownload;charset=UTF-8");
        try {
            response.setHeader("Content-Disposition", new String(sb.toString().getBytes(), "UTF-8"));
        }
        catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String filename = request.getParameter("filename");
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            try {
                filename = new String(filename.getBytes("UTF-8"), "UTF-8");
            }
            catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            try {
                filename = URLEncoder.encode(filename, "UTF-8");
            }
            catch (UnsupportedEncodingException ex) {}
        }
        response.setContentType("text/plain");
        response.setHeader("Location", filename);
        response.reset();
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        try {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            OutputStream fos = null;
            bis = new BufferedInputStream(new FileInputStream(String.valueOf(request.getRealPath("/upfile/")) + "/" + filename));
            fos = (OutputStream)response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            int bytesRead = 0;
            final byte[] buffer = new byte[5120];
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            bis.close();
            fos.close();
            new Info();
            Info.delPic(String.valueOf(request.getRealPath("/upfile/")) + "/", filename);
        }
        catch (Exception ex2) {}
    }
    
    public void init() throws ServletException {
    }
}
