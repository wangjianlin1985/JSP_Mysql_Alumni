// 
// 
// 

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import com.google.zxing.WriterException;
import java.io.OutputStream;
import util.QRCodeUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class QRCode extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        final String website = request.getParameter("website");
        try {
            QRCodeUtil.genGR(website, (OutputStream)response.getOutputStream());
        }
        catch (WriterException e) {
            e.printStackTrace();
        }
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
