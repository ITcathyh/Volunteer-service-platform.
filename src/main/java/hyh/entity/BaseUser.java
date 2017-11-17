package hyh.entity;

abstract public class BaseUser {
    protected int studentid;

    protected String name;

    protected String qq;

    protected String phone;

    protected String email;

    protected String ip;

    protected int pairid;

    public final int getStudentid() {
        return studentid;
    }

    public final void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getQq() {
        return qq;
    }

    public final void setQq(String qq) {
        this.qq = qq;
    }

    public final String getPhone() {
        return phone;
    }

    public final void setPhone(String phone) {
        this.phone = phone;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    public final int getPairid() {
        return pairid;
    }

    public final void setPairid(int pairid) {
        this.pairid = pairid;
    }

    public final String getIp() {
        return ip;
    }

    public final void setIp(String ip) {
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
