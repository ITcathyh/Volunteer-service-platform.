package hyh.controller;

import hyh.action.UserAction;
import hyh.entity.Admin;
import hyh.global.Variable;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.GetRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class LoginController {
    @Autowired
    private Admin admin;

    @RequestMapping("/login")
    public String getToLogin(HttpSession session) {
        if (session.getAttribute("islogin") == null) {
            return "login";
        } else {
            return "redirect:/admin";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("islogin");

        return "redirect:/login";
    }

    @RequestMapping("checklogin")
    @ResponseBody
    public String checkLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {
        System.out.println(username + " "+ password);

        if (session.getAttribute("islogin") == null) {
            if (!username.equals(admin.getUsername()) || !password.equals(admin.getPassword())) {
                Object obj = session.getAttribute("errortime");

                if (obj == null) {
                    session.setAttribute("errortime", 1);
                } else {
                    int time = (Integer) obj;

                    if (time >= 2) {
                        session.setAttribute("loginlock", 1);
                        session.removeAttribute("errortime");
                        return "loginlock";
                    } else {
                        session.setAttribute("errortime", ++time);
                    }
                }

                return "error";
            } else {
                session.setAttribute("islogin", 1);
                return "done";
            }
        } else {
            return "error";
        }
    }

}
