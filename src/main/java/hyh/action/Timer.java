package hyh.action;

import hyh.entity.BaseUser;
import hyh.global.Variable;
import hyh.service.*;
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
    @Autowired
    private UserService userservice;
    @Autowired
    private UserInfoService userinfoservice;
    @Autowired
    private TeacherService teacherservice;
    @Autowired
    private StudentService studentservice;
    @Autowired
    private AssistInforService assistinforservice;
    @Autowired
    private Email email;

    @Scheduled(cron = "0 55 23 * * ?")
    public void clear() {
        Variable.brerurl = GetRandomString.getRandomString(10);
        String path = FileAction.getExcelPath() + TimeUtil.getDeaLTime() + "/";

        Excel.write(userservice, teacherservice, studentservice, path);
        studentservice.deleteAll();
        teacherservice.deleteAll();
        userinfoservice.deleteAll();
        userservice.deleteAll();
    }

    @Scheduled(cron = "0 0 7 * * ?")
    public void clearAssist() {
        studentservice.deleteAll();
        teacherservice.deleteAll();
    }

    @Scheduled(cron = "0 0 8,13,17,20 * * ?")
    public void pairstu() {
        Pair.pair(userservice, userinfoservice, 2);
        Pair.sendPairEmail(email, userservice, 2);
    }

    @Scheduled(cron = "0 0 0 1 2,7 ?")
    public void clearAssistInfo() {
        assistinforservice.deleteAll();
    }
}
