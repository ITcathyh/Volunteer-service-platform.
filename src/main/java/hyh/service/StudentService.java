package hyh.service;

import hyh.dao.StudentDao;
import hyh.entity.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private StudentDao dao;

    public Student getById(long id){
        log.info("通过id查找学生");
        return dao.getById(id);
    }

    public Student getByStudentid(int studentid){
        log.info("通过学号查找学生");
        return dao.getByStudentid(studentid);
    }

    public List<Student> getAll(){
        log.info("获取所有学生");
        return dao.getAll();
    }

    public int getCount(){
        log.info("获取学生数量");

        Integer result = dao.getCount();
        return result == null ? 0 : result;
    }

    public int getCountByIp(String ip) {
        log.info("通过ip" + ip + "获取学生数量");

        Integer result = dao.getCountByIp(ip);
        return result == null ? 0 : result;
    }

    public int getPairid(int studentid){
        log.info("获取匹配的辅学者学号");

        Integer temp = dao.getPairid(studentid);
        return temp == null ? 0 : temp;
    }

    public int deleteById(long id){
        log.info("删除学生");
        return dao.deleteById(id);
    }

    public int deleteByStudentid(int studentid){
        log.info("删除学生");
        return dao.deleteByStudentid(studentid);
    }

    public int deleteAll(){
        log.info("删除学生");
        return dao.deleteAll();
    }

    public int add(Student record){
        log.info("增加学生");
        return dao.add(record);
    }

    public int update(Student record){
        log.info("更新学生");
        return dao.update(record);
    }
}
