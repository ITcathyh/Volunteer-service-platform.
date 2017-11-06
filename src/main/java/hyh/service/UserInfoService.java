package hyh.service;

import hyh.dao.UserInfoDao;
import hyh.entity.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserInfoDao dao;

    public int add(UserInfo record) {
        log.info("增加用户信息");
        return dao.add(record);
    }

    public UserInfo getByStudentidAndType(int studentid, int type) {
        log.info("查询用户" + studentid + "的信息");
        return dao.getByStudentidAndType(studentid, type);
    }

    public int deleteAll(){
        log.info("清除用户信息");
        return dao.deleteAll();
    }

    public int update(UserInfo record) {
        log.info("更新用户信息");
        return dao.update(record);
    }
}
