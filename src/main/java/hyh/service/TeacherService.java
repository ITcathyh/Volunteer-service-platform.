package hyh.service;

import hyh.dao.TeacherDao;
import hyh.entity.Teacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private TeacherDao dao;

    public Teacher getByStudentidAndStatus(int studentid, int status) {
        log.info("通过学号" + studentid + "获取辅学者");
        return dao.getByStudentidAndStatus(studentid, status);
    }

    public Teacher getByStudentid(int studentid) {
        log.info("通过学号" + studentid + "获取辅学者");
        return dao.getByStudentid(studentid);
    }

    public List<Teacher> getLimit(int pos, int count) {
        log.info("限量获取辅学者");
        return dao.getLimit(pos, count);
    }

    public List<Teacher> getAll() {
        log.info("获取所有辅学者");
        return dao.getAll();
    }

    public List<Teacher> getByStatus(int status) {
        log.info("通过状态获取辅学者");
        return dao.getByStatus(status);
    }

    public int getCount() {
        log.info("获取辅学者数量");

        Integer result = dao.getCount();
        return result == null ? 0 : result;
    }

    public int getCountByIp(String ip) {
        log.info("通过ip" + ip + "获取辅学者数量");

        Integer result = dao.getCountByIp(ip);
        return result == null ? 0 : result;
    }

    public int deleteAll() {
        log.info("清除辅学者");
        return dao.deleteAll();
    }

    public int deleteByStudentid(int studentid) {
        log.info("清除辅学者" + studentid);
        return dao.deleteByStudentid(studentid);
    }

    public int add(Teacher record) {
        log.info("增加辅学者");
        return dao.add(record);
    }

    public int update(Teacher record) {
        log.info("更新辅学者");
        return dao.update(record);
    }
}
