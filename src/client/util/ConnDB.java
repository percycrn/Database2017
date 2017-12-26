package client.util;

import client.data.Customer;

import java.sql.*;

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
        return getConnection().createStatement();
    }

    public ResultSet getCustomerInf() throws SQLException {
        return getStatement().executeQuery("SELECT * FROM CUSTOMER");
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
    public void storeNewCustomer(Customer customer) throws SQLException {
        getStatement().executeUpdate("INSERT INTO CUSTOMER VALUES('" + customer.getTelNumber() + "','" +
                customer.getPassword() + "','" + customer.getIdCard() + "','" + customer.getName() + "','" +
                customer.getAddress() + "')");
    }
}
