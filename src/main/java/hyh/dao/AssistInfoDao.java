package hyh.dao;

import hyh.entity.AssistInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistInfoDao {
    int add(AssistInfo record);

    AssistInfo get(@Param("teastudentid") int teastudentid, @Param("stustudentid") int stustudentid);

    int deleteAll();

    int update(AssistInfo record);
}