package hyh.entity;

public final class Student extends BaseUser {
    private long id;

    private String college;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String[] makeStrings() {
        return new String[]{name, Integer.toString(studentid), qq, phone, email,
                Integer.toString(pairid), "被辅学者"};
    }
}