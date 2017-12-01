package hyh.service;

import hyh.dao.UserDao;
import hyh.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public final class UserService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserDao dao;

    public int deleteById(long id) {
        log.info("删除用户");
        return dao.deleteById(id);
    }

    public int deleteByStudentidAndType(int studentid, int type) {
        log.info("删除用户" + studentid);
        return dao.deleteByStudentidAndType(studentid, type);
    }

    public int deleteAll() {
        log.info("清除用户");
        return dao.deleteAll();
    }

    public int add(User record) {
        log.info("增加用户");
        return dao.add(record);
    }

    public User getById(long id) {
        log.info("通过id" + id + "获取用户");
        return dao.getById(id);
    }

    public User getByStudentidAndType(int studentid, int type) {
        log.info("通过学号" + studentid + "获取用户");
        return dao.getByStudentidAndType(studentid, type);
    }

    public List<User> getByPairtype(int pairtype) {
        log.info("通过类型获取一个用户");
        return dao.getByPairtype(pairtype);
    }

    public List<User> getByType(int type) {
        log.info("通过类型获取用户");
        return dao.getByType(type);
    }

    public List<User> getByTypeAndPairid(int type, int pairid) {
        log.info("通过类型获取用户");
        return dao.getByTypeAndPairid(type, pairid);
    }

    public List<User> getPairedByType(int type) {
        log.info("通过类型获取已匹配用户");
        return dao.getPairedByType(type);
    }

    public List<User> getAll() {
        log.info("获取所有用户");
        return dao.getAll();
    }

    public int getCountByType(int type) {
        log.info("获取" + type + "类型的用户数量");

        Integer result = dao.getCountByType(type);
        return result == null ? 0 : result;
    }

    public int getFreeCountByType(int type) {
        log.info("获取" + type + "类型的未匹配用户数量");

        Integer result = dao.getFreeCountByType(type);
        return result == null ? 0 : result;
    }

    public int getCountByStatusAndType(int type, int status) {
        log.info("获取" + type + "类型的未匹配用户数量");

        Integer result = dao.getCountByStatusAndType(type, status);
        return result == null ? 0 : result;
    }

    public int getCountByIpAndType(int type, String ip) {
        log.info("获取" + type + "类型的IP为" + ip + "的用户数量");

        Integer result = dao.getCountByIpAndType(type, ip);
        return result == null ? 0 : result;
    }

    public boolean isExist(int studentid, int type) {
        log.info("判断用户是否存在");
        return dao.isExist(studentid, type) >= 1;
    }

    public int update(User record) {
        log.info("更新用户");
        return dao.update(record);
    }

    public int updatePairid(int pairid, long id) {
        log.info("更新用户配对id");
        return dao.updatePairid(pairid, id);
    }

    public int updateStatus(int status, int studentid) {
        log.info("更新用户" + studentid + "的状态");
        return dao.updateStatus(status, studentid);
    }
}
