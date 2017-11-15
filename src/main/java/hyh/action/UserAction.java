package hyh.action;

import hyh.entity.BaseUser;
import hyh.entity.Teacher;
import hyh.entity.User;
import hyh.entity.UserInfo;
import hyh.global.Variable;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.Ip;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

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

    public static void setBaseUser(BaseUser user, int studentid, String name,
                                   String qq, String phone, String email,
                                   String ip) {
        user.setStudentid(studentid);
        user.setName(name);
        user.setQq(qq);
        user.setPhone(phone);
        user.setEmail(email);
        user.setIp(ip);
    }

    public static boolean setBaseUser(BaseUser user, int studentid, HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String qq = request.getParameter("qq");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            if (!checkNull(name, qq, email, phone)) {
                return false;
            }

            user.setStudentid(studentid);
            user.setName(name);
            user.setQq(qq);
            user.setPhone(phone);
            user.setEmail(email);
            user.setIp(Ip.getIp(request));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    synchronized public static String addUser(User user, UserInfo usefinfo, int type,
                                              UserService userservice, UserInfoService userinfoservice,
                                              org.apache.log4j.Logger log) {
        try {
            if (userservice.getCountByType(1) >= Variable.bremaxnum) {
                return "full";
            }

            if (userservice.add(user) == 1 && userinfoservice.add(usefinfo) == 1) {
                return "done";
            } else {
                userservice.deleteByStudentidAndType(user.getStudentid(), type);
                return "error";
            }
        } catch (Exception e) {
            Variable.errornum++;
            log.error(e + "\n");
            userservice.deleteByStudentidAndType(user.getStudentid(), type);
            return "error";
        }
    }

    public static List<Object> getUsers(String genre, int studentid, Logger log,
                                        UserService userservice, TeacherService teacherservice) {
        List<Object> result = new ArrayList<Object>();
        User temp1, temp2;
        Teacher teacher;

        try {
            if (genre == null || genre.equals("全部")) {
                if (studentid != 0) {
                    temp1 = userservice.getByStudentidAndType(studentid, 2);
                    temp2 = userservice.getByStudentidAndType(studentid, 1);
                    teacher = teacherservice.getByStudentid(studentid);

                    UserAction.filter(result, temp1, temp2, teacher);
                } else {
                    result.addAll(userservice.getAll());
                    result.addAll(teacherservice.getAll());
                }
            } else if (genre.equals("早餐叫醒")) {
                if (studentid != 0) {
                    UserAction.filter(result, userservice.getByStudentidAndType(studentid, 1));
                } else {
                    result.addAll(userservice.getByType(1));
                }
            } else if (genre.equals("相约自习")) {
                if (studentid != 0) {
                    UserAction.filter(result, userservice.getByStudentidAndType(studentid, 2));
                } else {
                    result.addAll(userservice.getByType(2));
                }
            } else if (genre.equals("辅学")) {
                if (studentid != 0) {
                    UserAction.filter(result, teacherservice.getByStudentid(studentid));
                } else {
                    result.addAll(teacherservice.getAll());
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            Variable.errornum++;
            log.fatal(e);
            return null;
        }

        return result.size() == 0 ? null : result;
    }

    public static void filter(List<Object> list, Object... objects) {
        for (Object obj : objects) {
            if (obj != null) {
                list.add(obj);
            }
        }
    }

    synchronized public static boolean checkBreNum(UserService userservice) {
        return userservice.getCountByType(1) >= Variable.bremaxnum;
    }
}
