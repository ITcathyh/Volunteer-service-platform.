package hyh.service;

import hyh.dao.AssistInfoDao;
import hyh.entity.AssistInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class AssistInforService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private AssistInfoDao dao;

    public int add(AssistInfo record){
        log.info("增加辅学辅学配对信息");
        return dao.add(record);
    }

    public AssistInfo get(int teastudentid, int stustudentid){
        log.info("通过辅学者查找辅学配对信息");
        return dao.get(teastudentid, stustudentid);
    }

    public int deleteAll(){
        log.info("清除辅学配对信息");
        return dao.deleteAll();
    }

    public int update(AssistInfo record){
        log.info("更新辅学配对信息");
        return dao.update(record);
    }
}
