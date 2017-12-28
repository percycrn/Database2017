package client.data;


import java.sql.Date;

public class Guide {
    private String guide_no;
    private String name;
    private String sex;
    private Date employTime;
    private String work_level;
    private String phone;

    public String getGuide_no() {
        return guide_no;
    }

    public void setGuide_no(String guide_no) {
        this.guide_no = guide_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getEmploytime() {
        return employTime;
    }

    public void setEmploytime(Date employtime) {
        this.employTime = employtime;
    }

    public String getWork_level() {
        return work_level;
    }

    public void setWork_level(String work_level) {
        this.work_level = work_level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
