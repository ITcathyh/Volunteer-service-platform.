package hyh.entity;

import java.sql.Timestamp;

public class User extends BaseUser {
    private long id;

    private int selfsex;

    private int pairtype;

    private int type;

    private int status;

    private Timestamp createtime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSelfsex() {
        return selfsex;
    }

    public void setSelfsex(int selfsex) {
        this.selfsex = selfsex;
    }

    public int getPairtype() {
        return pairtype;
    }

    public void setPairtype(int pairtype) {
        this.pairtype = pairtype;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (studentid != that.studentid) return false;
        if (pairid != that.pairid) return false;
        if (pairtype != that.pairtype) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + studentid;
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (pairid ^ (pairid >>> 32));
        result = 31 * result + pairtype;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentid=" + studentid +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", selfsex=" + selfsex +
                ", createtime=" + createtime +
                ", pairid=" + pairid +
                ", pairtype=" + pairtype +
                '}';
    }

    public String[] makeStrings() {
        return new String[]{name, Integer.toString(studentid), qq, phone, email,
                Integer.toString(pairid), (type == 1) ? "早餐叫醒" : "相约自习"};
    }
}
