package hyh.service;

/**
 * Created by 黄宇航 on 2017/9/20.
 */

import hyh.action.Pair;
import hyh.action.UserAction;
import hyh.entity.*;
import hyh.util.Email;
import hyh.util.Excel;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-mvc.xml", "classpath*:/spring-mybatis.xml"})
public class ServiceTest extends AbstractTransactionalTestNGSpringContextTests {
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
    private Logger log = Logger.getLogger(this.getClass());


    @Test
    public void teseTeacherService() {
        /*Teacher teacher = teacherservice.getByStudentid(20114411);

        teacher.setPhone("18640508658");
        teacher.setSkill("000");
        teacher.setCollege("软件学院");
        teacher.setQq("123456");
        teacher.setName("测速是");

        assertTrue(teacherservice.update(teacher) == 1);
        assertNotNull(teacherservice.getByStudentid(20114411));*/

    }

    @Test
    public void teseUserService() {


        try {
            User user = userservice.getByStudentidAndType(20111111, 1);

            user.setQq("13123");

            assertTrue(userservice.update(user) == 1);
            assertTrue(userservice.getByStudentidAndType(20111111, 1).getQq().equals("13123"));
        }catch (Exception e){
            log.error(e);
        }

    }

    @Test
    public void testPair() {
        User user = userservice.getById(13);
        UserInfo userInfo = userinfoservice.getByStudentidAndType(user.getStudentid(), 1);

        for (int i = 0; i < 100; i++) {
            user.setStudentid(20180000 + (i << 1));
            userInfo.setStudentid(20180000 + (i << 1));

            userservice.add(user);
            userinfoservice.add(userInfo);
            user.setPairid(0);
        }

        long startMili = System.currentTimeMillis();

        System.out.println(Pair.pair(userservice, userinfoservice, 1));

        long endMili = System.currentTimeMillis();
        System.out.println("总耗时为：" + (endMili - startMili) + "毫秒");
    }

}
