package hyh.dao;

import hyh.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {
    int add(UserInfo record);

    UserInfo getByStudentidAndType(@Param("studentid") int studentid, @Param("type") int type);;

    int deleteAll();

    int update(UserInfo record);
}