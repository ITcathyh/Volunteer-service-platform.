package hyh.controller;

import hyh.action.UserAction;
import hyh.entity.*;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.Email;
import hyh.util.Ip;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class AssistController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;
    @Autowired
    private UserInfoService userinfoservice;
    @Autowired
    private TeacherService teacherservice;
    @Autowired
    private StudentService studentservice;
    @Autowired
    private Email uemail;

    @RequestMapping("/assist")
    public String getToAssist(HttpServletRequest request) {
        int nowpage;
        List<Teacher> teachers = teacherservice.getAllFree();

        try {
            nowpage = request.getParameter("page") == null ? 0 : Integer.valueOf(request.getParameter("page"));
        } catch (NumberFormatException e) {
            nowpage = 0;
        }

        Collections.sort(teachers);
        request.setAttribute("nowpage", nowpage);
        request.setAttribute("teachers", teachers.size() == 0 ? null : teachers);

        return "assist";
    }

    @RequestMapping("/applyteacher")
    public String getToTeacher() {
        return "applyteacher";
    }

    @RequestMapping("/checkaddteacher")
    @ResponseBody
    public String checkAddTeacher(HttpServletRequest request) {
        if (!Ip.checkIp(teacherservice.getCountByIp(Ip.getIp(request)))) {
            return "ipfull";
        }

        String name, qq, email, skill, college, phone;
        int studentid, selfsex;
        Teacher teacher = new Teacher();

        try {
            name = request.getParameter("name");
            qq = request.getParameter("qq");
            phone = request.getParameter("phone");
            email = request.getParameter("email");
            studentid = Integer.valueOf(request.getParameter("studentid"));
            selfsex = request.getParameter("selfsex").equals("男") ? 1 : 2;
            skill = request.getParameter("skill");
            college = request.getParameter("college");

            if (!UserAction.checkNull(name, qq, email, skill, college, phone)) {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }

        if (teacherservice.getByStudentid(studentid) != null) {
            return "exist";
        }

        teacher.setCollege(college);
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setQq(qq);
        teacher.setSkill(skill);
        teacher.setStudentid(studentid);
        teacher.setSelfsex(selfsex);
        teacher.setPhone(phone);
        teacher.setIp(Ip.getIp(request));

        try {
            if (teacherservice.add(teacher) == 1) {
                return "done";
            } else {
                teacherservice.deleteByStudentid(studentid);
            }
        } catch (Exception e) {
            log.error(e);
            teacherservice.deleteByStudentid(studentid);
            return "error";
        }

        return "error";
    }

    @RequestMapping("/self-studying")
    public String getToAddSelfstudying() {
        return "self-studying";
    }

    @RequestMapping("/checkaddstudying")
    @ResponseBody
    public String checkAddStudying(HttpServletRequest request) {
        if (!Ip.checkIp(userservice.getCountByIpAndType(2, Ip.getIp(request)))) {
            return "ipfull";
        }

        String name, qq, email, phone;
        int studentid, selfsex;
        User user = new User();

        try {
            name = request.getParameter("name");
            qq = request.getParameter("qq");
            phone = request.getParameter("phone");
            email = request.getParameter("email");
            studentid = Integer.valueOf(request.getParameter("studentid"));
            selfsex = request.getParameter("selfsex").equals("男") ? 1 : 2;

            if (!UserAction.checkNull(name, qq, email, phone)) {
                return "error";
            }
        } catch (Exception e) {
            log.error(e);
            return "error";
        }

        if (userservice.getByStudentidAndType(studentid, 2) != null) {
            return "exist";
        }

        user.setName(name);
        user.setEmail(email);
        user.setQq(qq);
        user.setSelfsex(selfsex);
        user.setStudentid(studentid);
        user.setType(2);
        user.setPhone(phone);
        user.setIp(Ip.getIp(request));

        UserInfo buf = UserAction.getUserInfo(request, studentid, 2);

        if (buf == null) {
            return "error";
        }

        try {
            if (userservice.add(user) == 1 && userinfoservice.add(buf) == 1) {
                return "done";
            } else {
                userservice.deleteByStudentidAndType(studentid, 2);
                return "error";
            }
        } catch (Exception e) {
            log.error(e);
            userservice.deleteByStudentidAndType(studentid, 2);
            return "error";
        }
    }

    @RequestMapping("/teacher")
    public String getToAddTeacher(HttpServletRequest request) {
        Teacher teacher;

        try {
            teacher = teacherservice.getByStudentidAndStatus(Integer.valueOf(request.getParameter("studentid")), 0);
        } catch (Exception e) {
            return "redirect:/assist";
        }

        if (teacher == null) {
            return "redirect:/assist";
        }

        request.setAttribute("teacher", teacher);

        return "teacher";
    }

    @RequestMapping("/checkaddstudent")
    @ResponseBody
    public String checkAddStudent(HttpServletRequest request) {
        if (!Ip.checkIp(studentservice.getCountByIp(Ip.getIp(request)))) {
            return "ipfull";
        }

        String name, qq, email, college, phone;
        int studentid, pairid;
        Student student = new Student();

        try {
            name = request.getParameter("name");
            qq = request.getParameter("qq");
            phone = request.getParameter("phone");
            email = request.getParameter("email");
            studentid = Integer.valueOf(request.getParameter("studentid"));
            pairid = Integer.valueOf(request.getParameter("pairid"));
            college = request.getParameter("college");

            if (!UserAction.checkNull(name, qq, email, college, phone)) {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }

        student.setCollege(college);
        student.setName(name);
        student.setEmail(email);
        student.setQq(qq);
        student.setStudentid(studentid);
        student.setPairid(pairid);
        student.setPhone(phone);
        student.setIp(Ip.getIp(request));

        Teacher teacher = teacherservice.getByStudentidAndStatus(pairid, 0);

        if (teacher == null) {
            return "booked";
        }

        try {
            if (studentservice.add(student) == 1) {
                teacher.setStatus(1);
                teacher.setPairid(studentid);

                if (teacherservice.update(teacher) == 1) {
                    if (uemail.sendEmailSafely(new AppEmail(student.getEmail(), teacher.getName(),
                            teacher.getQq(), teacher.getPhone(), teacher.getStudentid()), "辅学") &&
                            uemail.sendEmailSafely(new AppEmail(teacher.getEmail(), student.getName(),
                                    student.getQq(), student.getPhone(), student.getStudentid()), "辅学")) {
                        return "done";
                    } else {
                        studentservice.deleteByStudentid(studentid);
                        teacher.setPairid(0);
                        teacher.setStatus(0);
                        teacherservice.update(teacher);
                        return "error";
                    }
                }
            } else {
                studentservice.deleteByStudentid(studentid);
            }
        } catch (Exception e) {
            log.error(e);
            studentservice.deleteByStudentid(studentid);
            return "error";
        }

        return "error";
    }

    @RequestMapping("/authenticate")
    public String gteToAuthenticate() {
        return "authenticate";
    }
}
