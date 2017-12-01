package hyh.controller;

import hyh.action.Pair;
import hyh.action.UserAction;
import hyh.entity.User;
import hyh.entity.UserInfo;
import hyh.global.Variable;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.Ip;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BreakfastController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;
    @Autowired
    private UserInfoService userinfoservice;

    @RequestMapping("/breakfast")
    public String getToBreakfast(HttpServletRequest request) {
        if (request.getParameter("id").equals(Variable.brerurl)) {
            request.setAttribute("breurl", Variable.brerurl);
            return "breakfast";
        } else {
            return "error";
        }
    }

    @RequestMapping("/checkaddbreuser")
    @ResponseBody
    public String checkAddBreUser(HttpServletRequest request, HttpSession session) {
        String token = request.getHeader("requesttoken");
        Object sessiontoke = session.getAttribute("csrftoken");

        if (token == null || sessiontoke == null || !sessiontoke.toString().equals(token)) {
            return "error";
        } else if (!Ip.checkIp(userservice.getCountByIpAndType(1, Ip.getIp(request)))) {
            return "ipfull";
        }

        String name, qq, email, id, phone;
        int studentid, selfsex, type;
        User user = new User();

        try {
            studentid = Integer.valueOf(request.getParameter("studentid"));
            id = request.getParameter("id");
            selfsex = request.getParameter("selfsex").equals("男") ? 1 : 2;
            type = Integer.valueOf(request.getParameter("type"));

            if (id == null || type < 1 || type > 3) {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }

        if (userservice.isExist(studentid,1)) {
            return "exist";
        } else if (!id.equals(Variable.brerurl)) {
            return "error";
        } else if (!UserAction.setBaseUser(user, studentid, request)) {
            return "error";
        }

        user.setPairtype(type);
        user.setSelfsex(selfsex);
        user.setType(1);

        UserInfo buf = UserAction.getUserInfo(request, studentid, 1);

        if (type == 1) {
            if (buf == null) {
                return "error";
            } else if (UserAction.checkBreNum(userservice)) {
                return "full";
            }

            return UserAction.addUser(user, buf, 1, userservice, userinfoservice, log);
        } else {
            try {
                name = request.getParameter("anothername");
                qq = request.getParameter("anotherqq");
                email = request.getParameter("anotheremail");
                studentid = Integer.valueOf(request.getParameter("anotherstudentid"));
                phone = request.getParameter("anotherphone");

                if (!UserAction.checkNull(name, qq, email)) {
                    return "error";
                }
            } catch (Exception e) {
                return "error";
            }

            User anotherbuser = new User();
            User temp = userservice.getByStudentidAndType(studentid, 1);

            UserAction.setBaseUser(anotherbuser, studentid, name, qq, phone, email, Ip.getIp(request));
            anotherbuser.setPairtype(2);
            anotherbuser.setPairid(user.getStudentid());
            anotherbuser.setType(1);
            user.setPairid(studentid);

            if (temp != null && !temp.equals(anotherbuser)) {
                if (type == 2) {
                    return "notfound";
                }
            } else {
                if (buf == null) {
                    return "error";
                } else if (UserAction.checkBreNum(userservice)) {
                    return "full";
                }

                try {
                    if (userservice.add(user) == 1 && userservice.add(anotherbuser) == 1 && userinfoservice.add(buf) == 1) {
                        return "done";
                    } else {
                        userservice.deleteById(user.getId());
                        userservice.deleteById(anotherbuser.getId());
                        return "error";
                    }
                } catch (Exception e) {
                    Variable.errornum++;
                    log.error(e + "\n");
                    userservice.deleteById(user.getId());
                    userservice.deleteById(anotherbuser.getId());
                    return "error";
                }
            }
        }

        return "error";
    }
}
