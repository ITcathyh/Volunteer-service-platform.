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

        String skill, college;
        int studentid, selfsex;
        Teacher teacher = new Teacher();

        try {
            studentid = Integer.valueOf(request.getParameter("studentid"));
            selfsex = request.getParameter("selfsex").equals("男") ? 1 : 2;
            skill = request.getParameter("skill");
            college = request.getParameter("college");

            if (college == null || skill == null) {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }

        if (teacherservice.getByStudentid(studentid) != null) {
            return "exist";
        } else if (!UserAction.setBaseUser(teacher, studentid, request)) {
            return "error";
        }

        teacher.setCollege(college);
        teacher.setSkill(skill);
        teacher.setSelfsex(selfsex);

        try {
            if (teacherservice.add(teacher) == 1) {
                return "done";
            } else {
                teacherservice.deleteByStudentid(studentid);
            }
        } catch (Exception e) {
            log.error(e + "\n");
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

        int studentid, selfsex;
        User user = new User();

        try {
            studentid = Integer.valueOf(request.getParameter("studentid"));
            selfsex = request.getParameter("selfsex").equals("男") ? 1 : 2;
        } catch (Exception e) {
            return "error";
        }

        if (userservice.getByStudentidAndType(studentid, 2) != null) {
            return "exist";
        } else if (!UserAction.setBaseUser(user, studentid, request)) {
            return "error";
        }

        user.setSelfsex(selfsex);
        user.setType(2);

        UserInfo buf = UserAction.getUserInfo(request, studentid, 2);

        if (buf == null) {
            return "error";
        }

        return UserAction.addUser(user, buf, 2, userservice, userinfoservice, log);
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

        String college;
        int studentid, pairid;
        Student student = new Student();

        try {
            studentid = Integer.valueOf(request.getParameter("studentid"));
            pairid = Integer.valueOf(request.getParameter("pairid"));
            college = request.getParameter("college");

            if (college == null) {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }

        if (!UserAction.setBaseUser(student, studentid, request)) {
            return "error";
        }

        student.setCollege(college);
        student.setPairid(pairid);

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
            log.error(e + "\n");
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
