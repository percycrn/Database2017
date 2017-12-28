package client.util;

import client.data.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageClient {
    protected static ObservableList<String> customerInfoList = FXCollections.observableArrayList();
    protected static ObservableList<String> contractInfoList = FXCollections.observableArrayList();
    protected static ObservableList<String> routeInfoList = FXCollections.observableArrayList();
    protected static ObservableList<String> timePriceInfoList = FXCollections.observableArrayList();
    protected static Customer customer;
    protected static String company_name;
    protected static int route_no;
    protected static ConnDB connDB = new ConnDB();
}
