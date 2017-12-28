package client.util;

import client.data.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageClient {
    protected static ObservableList<String> customerInfoList = FXCollections.observableArrayList();
    protected static ObservableList<String> contractInfoList = FXCollections.observableArrayList();
    protected static ObservableList<String> routeInfoList = FXCollections.observableArrayList();
    protected static Customer customer;
    protected static ConnDB connDB;
}
