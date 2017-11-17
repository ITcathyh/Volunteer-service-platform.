package hyh.entity;

public final class AppEmail {
    private String address;

    private String name;

    private String qq;

    private String phone;

    private int studentid;

    public AppEmail(String address, String name, String qq, String phone, int studentid) {
        this.address = address;
        this.name = name;
        this.qq = qq;
        this.phone = phone;
        this.studentid = studentid;
    }

    public int getStudentid() {
        return studentid;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getQq() {
        return qq;
    }

    public String getPhone() {
        return phone;
    }
}
