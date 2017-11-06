package hyh.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    private static Logger log = Logger.getLogger("hyh.tuil.TimeUtil");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("kk");

    public static String getDeaLTime() {
        return sdf.format(new Date());
    }

    public static String getRoundDeaLTime(int day) {
        String today = sdf1.format(new Date());
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf1.parse(today));
        } catch (ParseException e) {
            log.error(e + "\n");
            e.printStackTrace();
            return "";
        }

        calendar.add(Calendar.DAY_OF_MONTH, day);

        return sdf.format(calendar.getTime());
    }

    public static String getActivityTime() {
        String today = sdf1.format(new Date());
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf1.parse(today));
        } catch (ParseException e) {
            log.error(e + "\n");
            e.printStackTrace();
            return "";
        }

        calendar.add(Calendar.DAY_OF_MONTH, 1);

        return sdf2.format(calendar.getTime());
    }

    public static String getNowHours() {
        return sdf3.format(new Date());
    }
}
