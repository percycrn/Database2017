package client.data;

import java.util.Date;

public class TimePrice {
    private String timePrice_no;
    private String route_no;
    private Date startTime;
    private Date endTime;
    private float price;
    private String serverLevel;
    private String guide_no;

    public String getTimePrice_no() {
        return timePrice_no;
    }

    public void setTimePrice_no(String timePrice_no) {
        this.timePrice_no = timePrice_no;
    }

    public String getRoute_no() {
        return route_no;
    }

    public void setRoute_no(String route_no) {
        this.route_no = route_no;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getServerLevel() {
        return serverLevel;
    }

    public void setServerLevel(String serverLevel) {
        this.serverLevel = serverLevel;
    }

    public String getGuide_no() {
        return guide_no;
    }

    public void setGuide_no(String guide_no) {
        this.guide_no = guide_no;
    }
}
