// 
// 
// 

package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrUtil
{
    private static int idSequence;
    
    static {
        StrUtil.idSequence = 10000;
    }
    
    public static String checkStr(final Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    
    public static synchronized String generalSrid() {
        final StringBuffer ret = new StringBuffer(20);
        ret.append(getFormatDate("yyyyMMddHHmmss"));
        ++StrUtil.idSequence;
        if (StrUtil.idSequence > 20000) {
            StrUtil.idSequence -= 10000;
        }
        ret.append(String.valueOf(StrUtil.idSequence).substring(1));
        return ret.toString();
    }
    
    public static String generalFileName(final String srcFileName) {
        try {
            final int index = srcFileName.lastIndexOf(".");
            return String.valueOf(generalSrid()) + srcFileName.substring(index).toLowerCase();
        }
        catch (Exception e) {
            return generalSrid();
        }
    }
    
    public static String parseOS(final String agent) {
        String system = "Other";
        if (agent.indexOf("Windows NT 5.2") != -1) {
            system = "Win2003";
        }
        else if (agent.indexOf("Windows NT 5.1") != -1) {
            system = "WinXP";
        }
        else if (agent.indexOf("Windows NT 5.0") != -1) {
            system = "Win2000";
        }
        else if (agent.indexOf("Windows NT") != -1) {
            system = "WinNT";
        }
        else if (agent.indexOf("Windows 9") != -1) {
            system = "Win9x";
        }
        else if (agent.indexOf("unix") != -1) {
            system = "unix";
        }
        else if (agent.indexOf("SunOS") != -1) {
            system = "SunOS";
        }
        else if (agent.indexOf("BSD") != -1) {
            system = "BSD";
        }
        else if (agent.indexOf("linux") != -1) {
            system = "linux";
        }
        else if (agent.indexOf("Mac") != -1) {
            system = "Mac";
        }
        else {
            system = "Other";
        }
        return system;
    }
    
    public static String getFormatDate(final String formatString) {
        final Date now = new Date(System.currentTimeMillis());
        final SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        final String ret = sdf.format(now);
        return ret;
    }
    
    public static Date getCurrentDate() {
        final Date now = new Date(System.currentTimeMillis());
        return now;
    }
    
    public static Date formatDate(final String dateString) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat();
            final Date date = sdf.parse(dateString);
            return date;
        }
        catch (ParseException e) {
            return new Date();
        }
    }
    
    public static int parseInt(final String numberStr) {
        if (numberStr == null) {
            return 0;
        }
        final Pattern pattern = Pattern.compile("^[\\-]{0,1}[0-9]+$");
        final Matcher matcher = pattern.matcher(numberStr);
        if (matcher.find()) {
            return Integer.parseInt(numberStr);
        }
        return 0;
    }
}
