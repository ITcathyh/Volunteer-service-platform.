package hyh.action;

import hyh.entity.User;
import hyh.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserAction {
    public static boolean checkNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                return false;
            }
        }

        return true;
    }

    public static UserInfo getUserInfo(HttpServletRequest request, int studentid, int type) {
        String college, time, selfcollege;
        int sex;
        UserInfo buf = new UserInfo();

        try {
            college = request.getParameter("college");
            time = request.getParameter("time");
            selfcollege = request.getParameter("selfcollege");
            String sexstr = request.getParameter("sex");
            sex = sexstr.equals("同性优先") ? 1 : sexstr.equals("异性优先") ? 0 : -1;

            if (!checkNull(college, time, selfcollege)) {
                return null;
            }

            buf.setCollege(college);
            buf.setSelfcollege(selfcollege);
            buf.setSex(sex);
            buf.setStudentid(studentid);
            buf.setTime(time);
            buf.setType(type);
        } catch (Exception e) {
            return null;
        }

        return buf;
    }

    public static void filter(List<Object> list, Object... objects) {
        for (Object obj : objects) {
            if (obj != null) {
                list.add(obj);
            }
        }
    }
}
