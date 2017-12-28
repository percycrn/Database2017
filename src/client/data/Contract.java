package client.data;

public class Contract {
    private int contract_no;
    private String customer_phone;
    private String guide_no;
    private int route_no;
    private int insurance_no;

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
