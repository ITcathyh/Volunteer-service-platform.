package hyh.controller;

import com.sun.org.apache.xpath.internal.operations.VariableSafeAbsRef;
import hyh.action.Pair;
import hyh.global.Variable;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.Email;
import hyh.util.GetRandomString;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
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
    private Email email;
    private String assiturl = null;
    private String breurl = null;

    @RequestMapping("/admin")
    public String getToAdmin(HttpServletRequest request) {
        if (assiturl == null) {
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
            assiturl = tempContextUrl + "assist";
            breurl = tempContextUrl + "breakfast?id=";
        }

        request.setAttribute("brepairnum", userservice.getFreeCountByType(1));
        request.setAttribute("breekfastnum", userservice.getCountByType(1));
        request.setAttribute("stupairnum", userservice.getFreeCountByType(2));
        request.setAttribute("studyingnum", userservice.getCountByType(2));
        request.setAttribute("teachernum", teacherservice.getCount());
        request.setAttribute("studentnum", studentservice.getCount());
        request.setAttribute("assiturl", assiturl);
        request.setAttribute("breurl", breurl  + Variable.brerurl);
        request.setAttribute("bremaxnum", Variable.bremaxnum);
        request.setAttribute("errornum", Variable.errornum);
        request.setAttribute("title", Variable.title);
        request.setAttribute("logo", Variable.logo);

        return "admin";
    }

    @RequestMapping("/admineditor")
    public String getToEditor(HttpServletRequest request) {
        request.setAttribute("add1", email.getMailsender().getUsername());
        request.setAttribute("add2", email.getMailsender1().getUsername());
        request.setAttribute("add3", email.getMailsender2().getUsername());
        request.setAttribute("pwd1", email.getMailsender().getPassword());
        request.setAttribute("pwd2", email.getMailsender1().getPassword());
        request.setAttribute("pwd3", email.getMailsender2().getPassword());
        request.setAttribute("host1", email.getMailsender().getHost());
        request.setAttribute("host2", email.getMailsender1().getHost());
        request.setAttribute("host3", email.getMailsender2().getHost());

        return "editor";
    }

    @RequestMapping("/admincheckupdateemail")
    @ResponseBody
    public String checkUpdateEmail(@RequestParam("add1") String add1,
                                   @RequestParam("add2") String add2,
                                   @RequestParam("add3") String add3,
                                   @RequestParam("pwd1") String pwd1,
                                   @RequestParam("pwd2") String pwd2,
                                   @RequestParam("pwd3") String pwd3,
                                   @RequestParam("host1") String host1,
                                   @RequestParam("host2") String host2,
                                   @RequestParam("host3") String host3) {
        try {
            JavaMailSenderImpl sender = email.getMailsender();

            sender.setUsername(add1);
            sender.setPassword(pwd1);
            sender.setHost(host1);

            sender = email.getMailsender1();

            sender.setUsername(add2);
            sender.setPassword(pwd2);
            sender.setHost(host2);

            sender = email.getMailsender2();

            sender.setUsername(add3);
            sender.setPassword(pwd3);
            sender.setHost(host3);
        } catch (Exception e) {
            Variable.errornum++;
            log.error("Update email error\n");
            return "error";
        }

        return "done";
    }

    @RequestMapping("/adminchangeurl")
    @ResponseBody
    public String changeUrl() {
        Variable.brerurl = GetRandomString.getRandomString(10);
        return breurl + Variable.brerurl;
    }

    @RequestMapping("/admincheckpairbre")
    @ResponseBody
    public int checkPairBre() {
        return Pair.pair(userservice, userinfoservice, 1);
    }

    @RequestMapping("/adminsendbrepairinfo")
    @ResponseBody
    public String sendBrePairInfo() {
        if (Pair.sendPairEmail(email, userservice, 1) == -1) {
            return "error";
        }

        int sended = userservice.getCountByStatusAndType(1, 1);

        return Integer.toString(sended * 100 /
                (userservice.getCountByStatusAndType(1, 0)) + sended);
    }

    @RequestMapping("/adminalterinfo")
    @ResponseBody
    public String changeBrenum(@RequestParam("bremaxnum") int bremaxnum,
                               @RequestParam("title") String title,
                               @RequestParam("logo") String logo) {
        try {
            Variable.bremaxnum = bremaxnum;
            Variable.title = title;
            Variable.logo = logo;
        } catch (Exception e) {
            return "error";
        }

        return "done";
    }

    @RequestMapping("/admingetpaircot")
    @ResponseBody
    public int getPairCot() {
        try {
            return userservice.getFreeCountByType(1);
        } catch (Exception e) {
            return -1;
        }
    }
}
