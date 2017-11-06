package hyh.controller;

import hyh.action.UserAction;
import hyh.entity.Student;
import hyh.entity.Teacher;
import hyh.entity.User;
import hyh.entity.UserInfo;
import hyh.global.Variable;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QueryUserController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;
    @Autowired
    private UserInfoService userinfoservice;
    @Autowired
    private TeacherService teacherservice;
    @Autowired
    private StudentService studentservice;

    @RequestMapping("/adminqueryuser")
    public String getToQueryuser(HttpServletRequest request) {
        String genre = request.getParameter("genre");
        int studentid = 0, nowpage = 0;
        List<Object> result = new ArrayList<Object>();
        User temp1, temp2;
        Teacher teacher;

        try {
            studentid = Integer.valueOf(request.getParameter("searchtext"));
        } catch (Exception e) {
            genre = null;
        }

        try {
            System.out.println(request.getParameter("page"));
            nowpage = Integer.valueOf(request.getParameter("page"));
        } catch (Exception e) { }

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
                return "error";
            }
        } catch (Exception e) {
            Variable.errornum++;
            log.error("DateBase is wrong\n" + e + "\n");
        }

        request.setAttribute("result", result.size() == 0 ? null : result);
        request.setAttribute("nowpage",nowpage);

        return "queryuser";
    }

    @RequestMapping("/admingetuser")
    public String getToGetUser(@RequestParam("studentid") String sstudentid, @RequestParam("type") String stype,
                               HttpServletRequest request, HttpSession session) {
            int studentid, type, show = 0;

            try {
                studentid = Integer.valueOf(sstudentid);
                type = Integer.valueOf(stype);
            } catch (Exception e) {
                return "error";
            }

            User user = userservice.getByStudentidAndType(studentid, type);
            UserInfo userinfo = userinfoservice.getByStudentidAndType(studentid, type);

            if (user == null) {
                return "error";
            } else if (userinfo != null) {
                show = 1;
                request.setAttribute("userinfo", userinfo);
            }

            if (type == 1) {
                request.setAttribute("activity", "早餐叫醒活动");
            } else if (type == 2) {
                request.setAttribute("activity", "相约自习活动");
            } else {
                return "error";
            }

            request.setAttribute("user", user);
            request.setAttribute("show", show);
            request.setAttribute("type", type);

            return "getuser";
    }

    @RequestMapping("/admingetassist")
    public String getToGetAssist(@RequestParam("studentid") String sstudentid,
                                 HttpServletRequest request) {
            int studentid;

            try {
                studentid = Integer.valueOf(sstudentid);
            } catch (Exception e) {
                return "error";
            }

            Teacher teacher = teacherservice.getByStudentid(studentid);
            Student student;

            if (teacher == null) {
                return "error";
            } else {
                student = studentservice.getByStudentid(teacher.getPairid());

                if (student == null) {
                    student = new Student();

                    student.setName("");
                    student.setCollege("");
                    student.setEmail("");
                    student.setQq("");
                }
            }

            request.setAttribute("teacher", teacher);
            request.setAttribute("student", student);

            return "getassist";
    }

    @RequestMapping("/admincheckupdateassist")
    @ResponseBody
    public String checkUpdateAssist( HttpServletRequest request) {
            String teachername, teacherqq, teacheremail,
                    teacherskill, teachercollege, teacherphone;
            int teacherstudentid;

            try {
                teachername = request.getParameter("teachername");
                teacherqq = request.getParameter("teacherqq");
                teacherphone = request.getParameter("teacherphone");
                teacheremail = request.getParameter("teacheremail");
                teacherstudentid = Integer.valueOf(request.getParameter("teacherstudentid"));
                teacherskill = request.getParameter("teacherskill");
                teachercollege = request.getParameter("teachercollege");

                if (!UserAction.checkNull(teachername, teacherqq, teacheremail, teacherskill, teachercollege)) {
                    return "error";
                }
            } catch (Exception e) {
                return "error";
            }

            Teacher teacher = teacherservice.getByStudentid(teacherstudentid);

            if (teacher == null) {
                return "error";
            } else {
                teacher.setName(teachername);
                teacher.setQq(teacherqq);
                teacher.setEmail(teacheremail);
                teacher.setCollege(teachercollege);
                teacher.setSkill(teacherskill);
                teacher.setPhone(teacherphone);

                if (teacherservice.update(teacher) == 1) {
                    return "done";
                }
            }

            return "error";
    }

    @RequestMapping("/admincheckdelete")
    @ResponseBody
    public String checkDelete(@RequestParam("genre") String genre,
                              @RequestParam("studentid") String studentid) {
            int id;
            int result = 0;

            try {
                id = Integer.valueOf(studentid);
            } catch (Exception e) {
                return "error";
            }

            if (genre.equals("1")) {
                result = userservice.deleteByStudentidAndType(id, 1);
            } else if (genre.equals("2")) {
                result = userservice.deleteByStudentidAndType(id, 2);
            } else if (genre.equals("3")) {
                result = teacherservice.deleteByStudentid(id);
            } else if (genre.equals("4")) {
                int teacherid = studentservice.getPairid(id);
                result = studentservice.deleteByStudentid(id);

                if (teacherid != 0 && result == 1) {
                    Teacher teacher = teacherservice.getByStudentid(teacherid);

                    if (teacher != null) {
                        teacher.setPairid(0);
                        teacher.setStatus(0);
                    }

                    if (teacherservice.update(teacher) != 1) {
                        result = 0;
                    }
                }
            }

            if (result == 0) {
                return "error";
            } else {
                return "done";
            }
    }

    @RequestMapping("/admincheckupdateuser")
    @ResponseBody
    public String checkUpdateUser(HttpServletRequest request) {
            String name, qq, email, phone;
            int studentid, type;

            try {
                name = request.getParameter("name");
                qq = request.getParameter("qq");
                phone = request.getParameter("phone");
                email = request.getParameter("email");
                studentid = Integer.valueOf(request.getParameter("studentid"));
                type = Integer.valueOf(request.getParameter("type"));

                if (!UserAction.checkNull(name, qq, email, phone) || (type != 1 && type != 2)) {
                    return "error";
                }
            } catch (Exception e) {
                System.out.println(e);
                return "error";
            }

            User user = userservice.getByStudentidAndType(studentid, type);

            if (user == null) {
                return "error";
            } else {
                user.setPhone(phone);
                user.setEmail(email);
                user.setName(name);
                user.setQq(qq);

                UserInfo userinfo = UserAction.getUserInfo(request, studentid, 1);

                System.out.println(userinfo);

                if (userservice.update(user) == 1 && userinfoservice.update(userinfo) == 1) {
                    return "done";
                }
            }

            return "error";
    }
}
