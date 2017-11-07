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
    public String checkAddBreUser(HttpServletRequest request) {
        if (userservice.getCountByType(1) >= Variable.bremaxnum) {
            return "full";
        } else if (!Ip.checkIp(userservice.getCountByIpAndType(1, Ip.getIp(request)))) {
            return "ipfull";
        }

        String name, qq, email, id, phone;
        int studentid, selfsex, type;
        User buser = new User();

        try {
            studentid = Integer.valueOf(request.getParameter("studentid"));
            id = request.getParameter("id");
            selfsex = request.getParameter("selfsex").equals("男") ? 1 : 2;
            type = Integer.valueOf(request.getParameter("type"));

            if (id == null || type < 1 || type > 3) {
                return "error";
            }
        } catch (Exception e) {
            log.error(e + "\n");
            return "error";
        }

        if (userservice.getByStudentidAndType(studentid, 1) != null) {
            return "exist";
        } else if (!id.equals(Variable.brerurl)) {
            return "error";
        } else if (!UserAction.setBaseUser(buser, studentid, request)) {
            return "error";
        }

        buser.setPairtype(type);
        buser.setSelfsex(selfsex);
        buser.setType(1);

        UserInfo buf = UserAction.getUserInfo(request, studentid, 1);

        if (type == 1) {
            if (buf == null) {
                return "error";
            }

            return UserAction.addUser(buser, buf, 1, userservice, userinfoservice, log);
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
                log.error(e + "\n");
                return "error";
            }

            User anotherbuser = new User();
            User temp = userservice.getByStudentidAndType(studentid, 1);

            UserAction.setBaseUser(anotherbuser, studentid, name, qq, phone, email, Ip.getIp(request));
            anotherbuser.setPairtype(2);
            anotherbuser.setPairid(buser.getStudentid());
            anotherbuser.setType(1);
            buser.setPairid(studentid);

            if (temp != null && !temp.equals(anotherbuser)) {
                if (type == 2) {
                    return "notfound";
                }
            } else {
                if (buf == null) {
                    return "error";
                }

                try {
                    if (userservice.add(buser) == 1 && userservice.add(anotherbuser) == 1 && userinfoservice.add(buf) == 1) {
                        return "done";
                    } else {
                        userservice.deleteById(buser.getId());
                        userservice.deleteById(anotherbuser.getId());
                        return "error";
                    }
                } catch (Exception e) {
                    log.error(e);
                    userservice.deleteById(buser.getId());
                    userservice.deleteById(anotherbuser.getId());
                    return "error";
                }
            }
        }

        return "error";
    }
}
