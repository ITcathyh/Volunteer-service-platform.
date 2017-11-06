package hyh.dao;

import hyh.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
    int deleteById(long id);

    int deleteByStudentid(int studentid);

    int deleteAll();

    int add(Student record);

    Student getById(long id);

    Student getByStudentid(int studentid);

    List<Student> getAll();

    Integer getPairid(int studentid);

    Integer getCount();

    Integer getCountByIp(String ip);

    int update(Student record);
}