package hyh.entity;

import java.sql.Timestamp;

public final class User extends BaseUser {
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

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
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
