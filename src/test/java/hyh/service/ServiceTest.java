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
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-mvc.xml", "classpath*:/spring-mybatis.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
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
    private AssistInforService assistinforservice;
    @Autowired
    private Email email;
    private Logger log = Logger.getLogger(this.getClass());

    @Test
    @Rollback(false)
    public void test(){
        try {
            testadd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    void testadd() throws Exception{
        AssistInfo a = new AssistInfo();

        a.setTime((short)1);
        a.setStustudentid(123);
        a.setTeastudentid(125);

        assistinforservice.add(a);

        String a1 = null;

        throw new RuntimeException("test");
    }

}
