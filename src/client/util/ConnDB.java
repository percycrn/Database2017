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
                "SELECT customer_phone FROM CUSTOMER WHERE customer_phone='" + telNumber + "'");
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
        getStatement().executeUpdate("INSERT INTO CUSTOMER VALUES('" + customer.getCustomers_phone() + "','" +
                customer.getName() + "','" + customer.getSex() + "','" + customer.getId_number() + "','" +
                customer.getPassword() + "','" + customer.getOccupation() + "')");
    }

    public ArrayList<Guide> guideInfo(String company_name) {
        try {
            ArrayList<Guide> guideInfoS = new ArrayList<>();
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "select * from Guide where company_name ='" + company_name + "'");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Guide guideInfo = new Guide();
                guideInfo.setGuide_no(rs.getString("guide_no").trim());
                guideInfo.setName(rs.getString("name").trim());
                guideInfo.setSex(rs.getString("sex").trim());
                guideInfo.setEmploytime(rs.getDate("employTime"));
                guideInfo.setPhone(rs.getString("phone").trim());
                guideInfo.setWork_level(rs.getString("work_level").trim());
                guideInfoS.add(guideInfo);
            }
            return guideInfoS;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get guide info");
            return null;
        }
    }

    public Customer getCustomerInfo(String customer_phone) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "select * from customer where customer_phone='" + customer_phone + "'");
            ResultSet rs = preparedStatement.executeQuery();
            Customer customer = new Customer();
            rs.next();
            customer.setCustomers_phone(rs.getString("customer_phone"));
            customer.setName(rs.getString("name").trim());
            customer.setSex(rs.getString("sex").trim());
            customer.setId_number(rs.getString("id_number").trim());
            customer.setPassword(rs.getString("password").trim());
            customer.setOccupation(rs.getString("occupation").trim());
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
            ResultSet resultSet = getStatement().executeQuery("select company_name from company");
            while (resultSet.next()) {
                companyName = companyName.append(resultSet.getString("company_name").trim()).append(",");
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
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from route where company_name='" + company_name + "'");
            ArrayList<Route> routeInfoS = new ArrayList<>();
            while (resultSet.next()) {
                Route route = new Route();
                route.setRoute_no(resultSet.getInt("route_no"));
                route.setRoute_name(resultSet.getString("route_name").trim());
                // route.setTime(resultSet.getDate(2));
                route.setServer_level(resultSet.getString("server_level").trim());
                route.setPlace(resultSet.getString("place").trim());
                route.setTraffic(resultSet.getString("traffic").trim());
                route.setCompany_no(resultSet.getString("company_no").trim());
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
            ResultSet resultSet = getStatement().executeQuery(
                    "select date,price from Time_Price where route_no=" + route_no);
            ArrayList<TimePrice> timePriceInfoS = new ArrayList<>();
            while (resultSet.next()) {
                TimePrice timePrice = new TimePrice();
                timePrice.setDate(resultSet.getDate("time"));
                timePrice.setPrice(resultSet.getInt("price"));
                timePriceInfoS.add(timePrice);
            }
            return timePriceInfoS;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route");
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
                contract.setContract_no(rs.getInt("contract_no"));
                contract.setCustomer_phone(rs.getString("customer_phone").trim());
                contract.setGuide_no(rs.getString("guide_no").trim());
                contract.setRoute_no(rs.getInt("route_no"));
                contract.setInsurance_no(rs.getInt("insurance_no"));
                contract = addContent(contract);
                contractInfoS.add(contract);
            }
            return contractInfoS;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get contract info");
            return null;
        }
    }

    private Contract addContent(Contract contract) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select * from route where route_no='" + contract.getRoute_no() + "'");
            resultSet.next();
            contract.setRoute_name(resultSet.getString("route_name"));
            contract.setTraffic(resultSet.getString("traffic"));
            contract.setRoute_place(resultSet.getString("place"));

            ResultSet resultSet1 = getStatement().executeQuery(
                    "select * from time_price where route_no='" + contract.getRoute_no() + "'");
            resultSet1.next();
            contract.setGuide_name(resultSet1.getString("guide_name"));
            contract.setPrice_travel(resultSet1.getInt("price"));
            contract.setDate(resultSet1.getDate("time"));

            ResultSet resultSet2 = getStatement().executeQuery(
                    "select insurance_money from insurance where insurance_no='" + contract.getInsurance_no() + "'");
            resultSet2.next();
            contract.setPrice_insurance(resultSet2.getInt("insurance_money"));
            return contract;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route_no");
            return null;
        }
    }

    public int getRouteNo(String route_name) {
        try {
            ResultSet resultSet = getStatement().executeQuery(
                    "select route_no from route where route_name='" + route_name + "'");
            resultSet.next();
            return resultSet.getInt("route_no");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail to get route_no");
            return -1;
        }
    }
}