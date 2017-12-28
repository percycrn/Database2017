package client.data;

import javax.xml.crypto.Data;

public class Management {
    private String no;
    private String name;
    private String sex;
    private Data employTime;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public Data getEmploytime() {
        return employTime;
    }

    public void setEmploytime(Data employtime) {
        this.employTime = employtime;
    }
}
