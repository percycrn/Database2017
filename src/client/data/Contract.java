package client.data;

import java.util.Date;

public class Contract {
    private int contract_no;
    private String customer_phone;
    private String guide_no;
    private int route_no;
    private int insurance_no;
    private String route_name;
    private String route_place;
    private String traffic;
    private String guide_name;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int price_travel;
    private int price_insurance;

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getRoute_place() {
        return route_place;
    }

    public void setRoute_place(String route_place) {
        this.route_place = route_place;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public int getPrice_travel() {
        return price_travel;
    }

    public void setPrice_travel(int price_travel) {
        this.price_travel = price_travel;
    }

    public int getPrice_insurance() {
        return price_insurance;
    }

    public void setPrice_insurance(int price_insurance) {
        this.price_insurance = price_insurance;
    }

    public int getContract_no() {
        return contract_no;
    }

    public void setContract_no(int contract_no) {
        this.contract_no = contract_no;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getGuide_no() {
        return guide_no;
    }

    public void setGuide_no(String guide_no) {
        this.guide_no = guide_no;
    }

    public int getRoute_no() {
        return route_no;
    }

    public void setRoute_no(int route_no) {
        this.route_no = route_no;
    }

    public int getInsurance_no() {
        return insurance_no;
    }

    public void setInsurance_no(int insurance_no) {
        this.insurance_no = insurance_no;
    }
}
