package client.data;

import java.sql.Date;

public class Route {
    private int route_no;
    private Date time;
    private String server_level;
    private float price;
    private String place;
    private String traffic;
    private int company_no;
    private String route_name;

    public int getRoute_no() {
        return route_no;
    }

    public void setRoute_no(int route_no) {
        this.route_no = route_no;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getServer_level() {
        return server_level;
    }

    public void setServer_level(String server_level) {
        this.server_level = server_level;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public int getCompany_no() {
        return company_no;
    }

    public void setCompany_no(int company_no) {
        this.company_no = company_no;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }
}
