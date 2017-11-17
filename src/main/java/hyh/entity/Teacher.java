package hyh.entity;

import java.util.Comparator;

public final class Teacher extends BaseUser implements Comparable<Teacher> {
    private int id;

    private int selfsex;

    private int status;

    private String skill;

    private String college;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelfsex() {
        return selfsex;
    }

    public void setSelfsex(int selfsex) {
        this.selfsex = selfsex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        return studentid == teacher.studentid;
    }

    @Override
    public int hashCode() {
        return studentid;
    }

    public String toString() {
        return "<th>" + name + "</th>\n" +
                "<th>" + college + "</th>\n" +
                "<th>" + (selfsex == 1 ? "男" : "女") + "</th>\n" +
                "<th>" + skill + "</th>\n";
    }

    public int compareTo(Teacher o) {
        return this.college.compareTo(o.getCollege());
    }

    public String[] makeStrings() {
        return new String[]{name, Integer.toString(studentid), qq, phone, email,
                Integer.toString(pairid), "辅学者"};
    }
}