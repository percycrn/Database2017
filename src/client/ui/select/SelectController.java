package client.ui.select;

import client.ClientStart;
import client.data.Contract;
import client.data.Route;
import client.ui.signin.SignIn;
import client.util.ManageClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectController extends ManageClient implements Initializable {

    public Text customerName;
    public ListView<String> contractListView;
    public ListView<String> customerInfoView;
    public ListView<String> timePriceView;
    public ComboBox<String> companyList;
    public ComboBox<String> routeList;

    @FXML
    protected void customerInformation() {
        contractListView.setVisible(false);
        timePriceView.setVisible(false);
        customerInfoView.setItems(customerInfoList);
        customerInfoView.setVisible(true);
    }

    @FXML
    protected void companyInformation() {

    }

    @FXML
    protected void contractInformation() {
        ArrayList<Contract> contractInfoS = connDB.getContractInfo(customer.getCustomers_phone());
        timePriceView.setVisible(false);
        customerInfoView.setVisible(false);
        contractListView.setVisible(true);
    }

    @FXML
    protected void back() {
        SignIn signIn = new SignIn();
        try {
            signIn.init();
            signIn.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "fail to fallback to SignIn interface");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contractListView.setVisible(false);
        timePriceView.setVisible(false);
        customerInfoView.setVisible(false);
        companyList.getItems().addAll(connDB.getCompany().split(","));
        companyList.valueProperty().addListener((ov, str1, str2) -> {
            System.out.println(companyList.getSelectionModel().getSelectedItem());
            ArrayList<Route> routeInfoS = connDB.getRoute(companyList.getSelectionModel().getSelectedItem());
            routeInfoList.removeAll();
            for (Route aRoute : routeInfoS) {
                routeInfoList.add(aRoute.getRoute_name() + "\n" + "route: " + aRoute.getPlace() + "\n" +
                        "server level: " + aRoute.getServer_level() + "\n" + "traffic: " +
                        aRoute.getTraffic() + "\n" + "-------------");
            }
        });
        routeList.getItems().addAll(routeInfoList);
        routeList.valueProperty().addListener((ov, str1, str2) -> {

        });
    }
}