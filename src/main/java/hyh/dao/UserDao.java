package hyh.dao;

import hyh.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int deleteById(long id);

    int deleteByStudentidAndType(@Param("studentid") int studentid, @Param("type") int type);

    int deleteAll();

    int add(User record);

    User getById(long id);

    User getByStudentidAndType(@Param("studentid") int studentid, @Param("type") int type);

    List<User> getByPairtype(int type);

    List<User> getByType(int type);

    List<User> getByTypeAndPairid(@Param("type") int type, @Param("pairid") int pairid);

    List<User> getPairedByType(int type);

    List<User> getAll();

    Integer getCountByType(int type);

    Integer getFreeCountByType(int type);

    Integer getCountByStatusAndType(@Param("type")int type, @Param("status") int status);

    Integer getCountByIpAndType( @Param("type") int type, @Param("ip")String ip);

    int update(User record);

    int updatePairid(@Param("pairid") int pairid, @Param("id") long id);

    int updateStatus(@Param("status") int status, @Param("studentid") int studentid);
}
