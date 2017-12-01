package hyh.entity;

public class AssistInfo {
    private int teastudentid;

    private int stustudentid;

    private short time;

    public Integer getTeastudentid() {
        return teastudentid;
    }

    public void setTeastudentid(Integer teastudentid) {
        this.teastudentid = teastudentid;
    }

    public Integer getStustudentid() {
        return stustudentid;
    }

    public void setStustudentid(Integer stustudentid) {
        this.stustudentid = stustudentid;
    }

    public short getTime() {
        return time;
    }

    public void setTime(short time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AssistInfo{" +
                "teastudentid=" + teastudentid +
                ", stustudentid=" + stustudentid +
                ", time=" + time +
                '}';
    }
}