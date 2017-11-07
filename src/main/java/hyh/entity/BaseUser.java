package hyh.entity;

abstract public class BaseUser {
    protected int studentid;

    protected String name;

    protected String qq;

    protected String phone;

    protected String email;

    protected String ip;

    protected int pairid;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPairid() {
        return pairid;
    }

    public void setPairid(int pairid) {
        this.pairid = pairid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String toString() {
        return super.toString() +
                "---" + name +
                "---" + studentid +
                "---" + qq +
                "---" + phone +
                "---" + email +
                "---" + pairid;
    }

    abstract public String[] makeStrings();
}
