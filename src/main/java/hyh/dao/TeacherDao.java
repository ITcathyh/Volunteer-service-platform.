package hyh.dao;

import hyh.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherDao {
    Teacher getByStudentidAndStatus(@Param("studentid") int studentid, @Param("status") int status);

    Teacher getByStudentid(int studentid);

    List<Teacher> getLimit(@Param("pos") int pos, @Param("count") int count);

    List<Teacher> getAll();

    List<Teacher> getByStatus(int status);

    Integer getCount();

    Integer getCountByIp(String ip);

    int deleteAll();

    int deleteByStudentid(int studentid);

    int add(Teacher record);

    int update(Teacher record);
}