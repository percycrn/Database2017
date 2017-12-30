package client.util;

import client.data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class ConnDB {

    ConnDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travelsystem", "root", "699685");
    }

    private Statement getStatement() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travelsystem", "root", "699685").createStatement();
    }

    // to check whether the account exist in database or not
    public boolean accountExist(String telNumber) throws SQLException {
        ResultSet resultSet = getStatement().executeQuery(
                "SELECT customer_telNumber FROM CUSTOMER WHERE customer_telNumber='" + telNumber + "'");
        return resultSet.next();
    }

    // to check whether the password of the specific account is correct or not
    public boolean checkPassword(String telNumber, String password) throws SQLException {
        ResultSet resultSet = getStatement().executeQuery(
                "SELECT PASSWORD FROM CUSTOMER WHERE customer_phone='" + telNumber + "'");
        resultSet.next();
        return resultSet.getString("PASSWORD").trim().equals(password);
    }

    // to store new customer
    public void storeNewCustomer(Customer customer) throws SQLException {
        getStatement().executeUpdate("INSERT INTO CUSTOMER VALUES('" + customer.getCustomer_telNumber() + "','"
                + customer.getCustomer_name() + "','" + customer.getCustomer_idCard() + "','"
                + customer.getCustomer_password() + "','" + customer.getCustomer_occupation() + "')");
    }

    public ObservableList<String> getCustomerStr(String customer_telNumber) {
        Customer customer = getCustomer(customer_telNumber);
        ObservableList<String> customerList = FXCollections.observableArrayList();
        if (customer != null) {
            customerList.add("telephone number: " + customer.getCustomer_telNumber());
            customerList.add("name: " + customer.getCustomer_name());
            customerList.add("id card: " + customer.getCustomer_idCard());
            customerList.add("name: " + customer.getCustomer_name());
            customerList.add("occupation: " + customer.getCustomer_occupation());
            return customerList;
        } else {
            System.out.println("fail to get customer information");
            return null;
        }
    }

    public Customer getCustomer(String customer_telNumber) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from customer where customer_telNumber='" + customer_telNumber + "'");
            Customer customer = new Customer();
            resultSet.next();
            customer.setCustomer_telNumber(resultSet.getString("customer_telNumber"));
            customer.setCustomer_name(resultSet.getString("customer_name"));
            customer.setCustomer_idCard(resultSet.getString("customer_idCard"));
            customer.setCustomer_password(resultSet.getString("customer_password"));
            customer.setCustomer_occupation(resultSet.getString("customer_occupation"));
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get customer information");
            return null;
        }
    }

    public String getSubsidiaryName() {
        try {
            StringBuilder subsidiaryName = new StringBuilder();
            ResultSet resultSet = getStatement().executeQuery("select subsidiary_name from subsidiary");
            while (resultSet.next()) {
                subsidiaryName = subsidiaryName.append(resultSet.getString("subsidiary_name").trim())
                        .append(",");
            }
            return String.valueOf(subsidiaryName);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get subsidiary name");
            return null;
        }
    }

    public String getSubsidiaryNo(String subsidiary_name) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select subsidiary_no from subsidiary where subsidiary_name='" + subsidiary_name + "'");
            resultSet.next();
            return resultSet.getString("subsidiary_no");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get subsidiary_no");
            return null;
        }
    }

    public ObservableList<String> getSubsidiary(String subsidiary_no) {
        try {
            ResultSet resultSet = getStatement().executeQuery("select * from subsidiary where subsidiary_no='"
                    + subsidiary_no + "'");
            resultSet.next();
            ResultSet resultSet1 = getStatement().executeQuery(
                    "select parentCompany_name from parentCompany where parentCompany_no='"
                            + resultSet.getString("parentCompany_no") + "'");
            resultSet1.next();
            ObservableList<String> subsidiaryList = FXCollections.observableArrayList();
            subsidiaryList.add("subsidiary name: " + resultSet.getString("subsidiary_name"));
            subsidiaryList.add("subsidiary address: " + resultSet.getString("subsidiary_address"));
            subsidiaryList.add("belongs to " + resultSet1.getString("parentCompany_name"));
            subsidiaryList.add("manager name: " + resultSet.getString("manager_name"));
            subsidiaryList.add("manager take office in " + resultSet.getString("manager_time"));
            return subsidiaryList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get subsidiary information");
            return null;
        }
    }

    public ObservableList<String> getRoute(String route_no) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from route where route_no='" + route_no + "'");
            resultSet.next();
            ObservableList<String> routeList = FXCollections.observableArrayList();
            routeList.add("travel program: " + resultSet.getString("route_name"));
            routeList.add("route spot: " + getSpot(resultSet.getString("route_spot")));
            routeList.add("average price: " + resultSet.getString("averagePrice"));
            routeList.add("traffic: " + resultSet.getString("traffic"));
            return routeList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route information");
            return null;
        }
    }

    private String getSpot(String route_spot) {
        try {
            String[] spotNumArr = route_spot.split(",");
            StringBuilder spotName = new StringBuilder();
            for (String aSpotNumArr : spotNumArr) {
                ResultSet resultSet = getStatement().executeQuery(
                        "select spot_name from spot where spot_no=" + aSpotNumArr);
                resultSet.next();
                spotName = spotName.append(resultSet.getString("spot_name")).append(",");
            }
            spotName = spotName.append("...");
            return String.valueOf(spotName);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get spot name");
            return null;
        }
    }

    public String getRouteName(String subsidiary_no) {
        try {
            StringBuilder routeName = new StringBuilder();
            ResultSet resultSet = getStatement().executeQuery(
                    "select route_name from route where subsidiary_no='" + subsidiary_no + "'");
            while (resultSet.next()) {
                routeName = routeName.append(resultSet.getString("route_name").trim())
                        .append(",");
            }
            return String.valueOf(routeName);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route name");
            return null;
        }
    }

    public String getTimePriceName(String route_no) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select startTime,endTime,price from timePrice where route_no='" + route_no + "'");
            StringBuilder timePriceName = new StringBuilder();
            while (resultSet.next()) {
                timePriceName = timePriceName
                        .append(resultSet.getString("timePrice_no")).append(": ")
                        .append(resultSet.getDate("startTime")).append(" - ")
                        .append(resultSet.getDate("endTime")).append(" ¥")
                        .append(resultSet.getFloat("price")).append(",");
            }
            return String.valueOf(timePriceName);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get timePrice name");
            return null;
        }
    }

    public String getGuideNo(String timePrice_no) {
        try {
            ResultSet resultSet = getStatement().executeQuery("" +
                    "select guide_no from timePrice where timePrice_no='" + timePrice_no + "'");
            resultSet.next();
            return resultSet.getString("guide_no");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get guide no");
            return null;
        }
    }

    public ObservableList<String> getContract(Customer customer) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from contract where customer_telNumber='" + customer.getCustomer_telNumber() + "'");
            ObservableList<String> contractList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ResultSet resultSet1 = getStatement().executeQuery(
                        "select * from subsidiary where subsidiary_no='"
                                + resultSet.getString("subsidiary_no") + "'");
                ResultSet resultSet2 = getStatement().executeQuery(
                        "select * from route where route_no='"
                                + resultSet.getString("route_no") + "'");
                ResultSet resultSet3 = getStatement().executeQuery(
                        "select * from timePrice where timePrice_no='"
                                + resultSet.getString("timePrice_no") + "'");
                ResultSet resultSet4 = getStatement().executeQuery(
                        "select * from insurance where insurance_no='"
                                + resultSet.getString("insurance_no") + "'");
                resultSet1.next();
                resultSet2.next();
                resultSet3.next();
                resultSet4.next();
                contractList.add("registration site: " + resultSet1.getString("subsidiary_name") + "\n"
                        + "travel program: " + resultSet2.getString("route_name") + "\n"
                        + "time arrangement: " + resultSet3.getString("startDate")
                        + " - " + resultSet3.getString("endDate") + "\n"
                        + "price: " + resultSet3.getString("price") + "\n"
                        + "server level: " + resultSet3.getString("serverLevel") + "\n"
                        + "insurance" + resultSet4.getString("insurance_name"));
            }
            return contractList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get contract information");
            return null;
        }
    }

    public String getRouteNo(String route_name) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select route_no from route where route_name='" + route_name + "'");
            resultSet.next();
            return resultSet.getString("route_no");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route_no");
            return null;
        }
    }

    public boolean addNewContract(Contract contract) {
        try {
            ResultSet resultSet = getStatement().executeQuery("select count(*) from contract");
            resultSet.next();
            int maxNo = resultSet.getInt(1);
            contract.setContract_no("XCC" + (maxNo + 1));
            getStatement().executeUpdate("insert into contract values('"
                    + contract.getContract_no() + "','" + contract.getCustomer_telNumber() + "','"
                    + contract.getSubsidiary_no() + "','" + contract.getRoute_no() + "','"
                    + contract.getGuide_no() + "','" + contract.getTimePrice_no() + "','"
                    + contract.getInsurance_no() + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to add new contract");
            return false;
        }
    }
}