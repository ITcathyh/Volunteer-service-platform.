package hyh.util;

import javax.servlet.http.HttpServletRequest;

public class Ip {
    private static int maxnum = 2;

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static boolean checkIp(int count){
        return count < maxnum;
    }

    public static int getMaxnum() {
        return maxnum;
    }

    public static void setMaxnum(int maxnum) {
        Ip.maxnum = maxnum;
    }
}
