package hyh.entity;

public final class UserInfo {
    private int studentid;

    private int sex;

    private int type;

    private String selfcollege;

    private String college;

    private String time;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getSelfcollege() {
        return selfcollege;
    }

    public void setSelfcollege(String selfcollege) {
        this.selfcollege = selfcollege;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return studentid +
                "---" + sex +
                "---" + type +
                "---" + selfcollege +
                "---" + college +
                "---" + time;
    }

    public String[] makeStrings() {
        return new String[]{(sex == 1) ? "同性优先" : (sex == 0) ? "同性优先" : "随意",
                selfcollege, college, time};
    }
}
