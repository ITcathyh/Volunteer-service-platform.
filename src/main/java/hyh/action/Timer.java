package hyh.action;

import hyh.entity.BaseUser;
import hyh.global.Variable;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.Email;
import hyh.util.Excel;
import hyh.util.GetRandomString;
import hyh.util.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Timer {
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

    @Scheduled(cron = "0 55 23 * * ?")
    public void clear() {
        Variable.brerurl = GetRandomString.getRandomString(10);
        ArrayList<BaseUser> list = new ArrayList<BaseUser>();
        String path = FileAction.getExcelPath() + TimeUtil.getDeaLTime() + "/";

        list.addAll(userservice.getByType(1));
        Excel.write(path, "早餐叫醒", list);
        list.clear();
        list.addAll(userservice.getByType(2));
        list.addAll(teacherservice.getAll());
        list.addAll(studentservice.getAll());
        Excel.write(path, "相约自习", list);

        studentservice.deleteAll();
        teacherservice.deleteAll();
        userinfoservice.deleteAll();
        userservice.deleteAll();
    }

    @Scheduled(cron = "0 0 8,13,17,20 * * ?")
    public void pairstu() {
        Pair.pair(userservice, userinfoservice, 2);
        Pair.sendPairEmail(email, userservice, 2);
    }
}
