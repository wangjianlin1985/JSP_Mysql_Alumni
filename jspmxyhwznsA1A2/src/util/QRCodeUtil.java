// 
// 
// 

package util;

import java.io.IOException;
import com.google.zxing.WriterException;
import java.awt.image.BufferedImage;
import com.google.zxing.common.BitMatrix;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.EncodeHintType;
import java.util.Hashtable;
import java.io.OutputStream;

public class QRCodeUtil
{
    private static final int BLACK = -16777216;
    private static final int WHITE = -1;
    
    public static void genGR(final String website, final OutputStream output) throws WriterException, IOException {
        final int width = 300;
        final int height = 300;
        final String format = "jpg";
        final Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        final BitMatrix bm = new MultiFormatWriter().encode(website, BarcodeFormat.QR_CODE, width, height, (Hashtable)hints);
        final BufferedImage image = toImage(bm);
        ImageIO.write(image, format, output);
    }
    
    private static BufferedImage toImage(final BitMatrix bm) {
        final int width = bm.getWidth();
        final int height = bm.getHeight();
        final BufferedImage image = new BufferedImage(width, height, 1);
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                image.setRGB(x, y, bm.get(x, y) ? -16777216 : -1);
            }
        }
        return image;
    }
}
