package client.util;

import client.data.*;

import java.sql.*;
import java.util.ArrayList;

public class ConnDB {

    public ConnDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "", "");
    }

    private Statement getStatement() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "", "").createStatement();
    }

    // to check whether the account exist in database or not
    public boolean accountExist(String telNumber) throws SQLException {
        ResultSet resultSet = getStatement().executeQuery("SELECT telNumber FROM CUSTOMER WHERE telNumber='" + telNumber + "'");
        return resultSet.next();
    }

    // to check whether the password of the specific account is correct or not
    public boolean checkPassword(String telNumber, String password) throws SQLException {
        ResultSet resultSet = getStatement().executeQuery("SELECT PASSWORD FROM CUSTOMER WHERE telNumber='" + telNumber + "'");
        resultSet.next();
        return resultSet.getString("PASSWORD").trim().equals(password);
    }

    // to store new customer
    /*public void storeNewCustomer(Customer customer) throws SQLException {
        getStatement().executeUpdate("INSERT INTO CUSTOMER VALUES('" + customer.getTelNumber() + "','" +
                customer.getPassword() + "','" + customer.getIdCard() + "','" + customer.getName() + "','" +
                customer.getAddress() + "')");
    }*/
    public ArrayList<Guide> guideInfo() {
        try {
            ArrayList<Guide> guideInfoS = new ArrayList<>();
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from Guide");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Guide guideInfo = new Guide();
                guideInfo.setGuide_no(rs.getString(1));
                guideInfo.setName(rs.getString(2));
                guideInfo.setSex(rs.getString(3));
                guideInfo.setEmploytime(rs.getDate(4));
                guideInfo.setPhone(rs.getString(5));
                guideInfo.setWork_level(rs.getString(6));
                guideInfoS.add(guideInfo);
            }
            return guideInfoS;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get guide info");
            return null;
        }
    }

    public ArrayList<Contract> getContractInfo(String customer_phone) {
        try {
            ArrayList<Contract> contractInfoS = new ArrayList<>();
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "select * from contract where customer_phone='" + customer_phone + "'");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setContract_no(rs.getInt(1));
                contract.setCustomer_phone(rs.getString(2));
                contract.setGuide_no(rs.getString(3));
                contract.setRoute_no(rs.getInt(4));
                contract.setInsurance_no(rs.getInt(5));
                contractInfoS.add(contract);
            }
            return contractInfoS;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get contract info");
            return null;
        }
    }

    public Customer getCustomerInfo(String customer_phone) {
        try {
            ArrayList<Contract> contractInfoS = new ArrayList<>();
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "select * from customer where customer_phone='" + customer_phone + "'");
            ResultSet rs = preparedStatement.executeQuery();
            Customer customer = new Customer();
            rs.next();
            customer.setCustomers_phone(rs.getString(1));
            customer.setId_number(rs.getString(2));
            customer.setName(rs.getString(3));
            customer.setSex(rs.getString(4));
            customer.setPassword(rs.getString(5));
            customer.setOccupation(rs.getString(6));
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get customer info");
            return null;
        }
    }

    public String getCompany() {
        try {
            StringBuilder companyName = new StringBuilder();
            ResultSet resultSet = getStatement().executeQuery("select name from company");
            while (resultSet.next()) {
                companyName = companyName.append(resultSet.getString("name")).append(",");
            }
            return String.valueOf(companyName);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get company name");
            return null;
        }
    }

    public ArrayList<Route> getRoute(String company_name) {
        try {
            ResultSet resultSet = getStatement().executeQuery("select * from route where company_name=" + company_name);
            ArrayList<Route> routeInfoS = new ArrayList<>();
            while (resultSet.next()) {
                Route route = new Route();
                route.setRoute_no(resultSet.getInt(1));
                route.setTime(resultSet.getDate(2));
                route.setServer_level(resultSet.getString(3));
                route.setPrice(resultSet.getInt(4));
                route.setPlace(resultSet.getString(5));
                route.setTraffic(resultSet.getString(6));
                route.setCompany_no(resultSet.getInt(7));
                routeInfoS.add(route);
            }
            return routeInfoS;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route");
            return null;
        }
    }

    public ArrayList<TimePrice> getTimePrice(int route_no) {
        try {
            ResultSet resultSet = getStatement().executeQuery("select date,price from TimePrice where route_no=" + route_no);
            ArrayList<TimePrice> timePriceInfoS = new ArrayList<>();
            while (resultSet.next()) {
                TimePrice timePrice = new TimePrice();
                timePrice.setDate(resultSet.getDate(1));
                timePrice.setPrice(resultSet.getInt(2));
                timePriceInfoS.add(timePrice);
            }
            return timePriceInfoS;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route");
            return null;
        }
    }
}
