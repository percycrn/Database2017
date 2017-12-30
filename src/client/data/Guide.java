package client.data;


import java.sql.Date;

public class Guide {
    private String guide_no;
    private String guide_name;
    private String guide_telNumber;
    private String guide_level;
    private Date guide_time;
    private String subsidiary_no;

    public String getGuide_no() {
        return guide_no;
    }

    public void setGuide_no(String guide_no) {
        this.guide_no = guide_no;
    }

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public String getGuide_telNumber() {
        return guide_telNumber;
    }

    public void setGuide_telNumber(String guide_telNumber) {
        this.guide_telNumber = guide_telNumber;
    }

    public String getGuide_level() {
        return guide_level;
    }

    public void setGuide_level(String guide_level) {
        this.guide_level = guide_level;
    }

    public Date getGuide_time() {
        return guide_time;
    }

    public void setGuide_time(Date guide_time) {
        this.guide_time = guide_time;
    }

    public String getSubsidiary_no() {
        return subsidiary_no;
    }

    public void setSubsidiary_no(String subsidiary_no) {
        this.subsidiary_no = subsidiary_no;
    }
}
