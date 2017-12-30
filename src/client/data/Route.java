package client.data;

public class Route {
    private String route_no;
    private String route_name;
    private String route_spot;
    private float averagePrice;
    private String traffic;
    private String subsidiary_no;

    public String getRoute_no() {
        return route_no;
    }

    public void setRoute_no(String route_no) {
        this.route_no = route_no;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getRoute_spot() {
        return route_spot;
    }

    public void setRoute_spot(String route_spot) {
        this.route_spot = route_spot;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getSubsidiary_no() {
        return subsidiary_no;
    }

    public void setSubsidiary_no(String subsidiary_no) {
        this.subsidiary_no = subsidiary_no;
    }
}
