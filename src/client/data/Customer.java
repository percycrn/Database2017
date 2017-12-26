package client.data;

public class Customer {
    private String telNumber;
    private String password;
    private String idCard;
    private String name;
    private String address;

    public Customer(String telNumber, String password, String idCard, String name, String address) {
        this.telNumber = telNumber;
        this.password = password;
        this.idCard = idCard;
        this.name = name;
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
