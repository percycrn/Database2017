package client.data;

public class Subsidiary {
    private String subsidiary_no;
    private String subsidiary_name;
    private String subsidiary_address;
    private String manager_name;
    private String manager_time;
    private String parentCompany_no;

    public String getSubsidiary_no() {
        return subsidiary_no;
    }

    public void setSubsidiary_no(String subsidiary_no) {
        this.subsidiary_no = subsidiary_no;
    }

    public String getSubsidiary_name() {
        return subsidiary_name;
    }

    public void setSubsidiary_name(String subsidiary_name) {
        this.subsidiary_name = subsidiary_name;
    }

    public String getSubsidiary_address() {
        return subsidiary_address;
    }

    public void setSubsidiary_address(String subsidiary_address) {
        this.subsidiary_address = subsidiary_address;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getManager_time() {
        return manager_time;
    }

    public void setManager_time(String manager_time) {
        this.manager_time = manager_time;
    }

    public String getParentCompany_no() {
        return parentCompany_no;
    }

    public void setParentCompany_no(String parentCompany_no) {
        this.parentCompany_no = parentCompany_no;
    }
}
