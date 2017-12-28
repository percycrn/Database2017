package client.ui.select;

import client.ClientStart;
import client.data.Contract;
import client.data.Route;
import client.data.TimePrice;
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
    public ComboBox<String> companyList;
    public ComboBox<String> routeList;
    public ListView<String> messageListView;

    @FXML
    protected void customerInformation() {
        messageListView.getItems().remove(0, messageListView.getItems().size());
        messageListView.setItems(customerInfoList);
    }

    @FXML
    protected void showTimeAndPrice() {
        messageListView.getItems().remove(0, messageListView.getItems().size());
        ArrayList<TimePrice> timePrices = connDB.getTimePrice(route_no);
        for (TimePrice aTimePrice : timePrices) {
            timePriceInfoList.add("date: " + aTimePrice.getDate() + "  " + "price: " + aTimePrice.getPrice());
        }
        messageListView.setItems(timePriceInfoList);
    }

    @FXML
    protected void contractInformation() {
        messageListView.getItems().remove(0, messageListView.getItems().size());
        ArrayList<Contract> contractInfoS = connDB.getContractInfo(customer.getCustomers_phone());
        for (Contract aContractInfoS : contractInfoS) {
            contractInfoList.add("order form No." + aContractInfoS.getContract_no() + "\n"
                    + "program: " + aContractInfoS.getRoute_name() + "\n"
                    + "route: " + aContractInfoS.getRoute_place() + "\n"
                    + "traffic" + aContractInfoS.getTraffic() + "\n"
                    + "guide" + aContractInfoS.getGuide_name() + "\n"
                    + "price" + aContractInfoS.getPrice_travel() + "\n"
                    + "insurance" + aContractInfoS.getPrice_insurance());
        }
        messageListView.setItems(contractInfoList);
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
        messageListView.getItems().remove(0, messageListView.getItems().size());
        customerName.setText(customer.getName());
        companyList.getItems().remove(0, companyList.getItems().size());
        companyList.getItems().addAll(connDB.getCompany().split(","));
        companyList.valueProperty().addListener((ov1, str11, str12) -> {
            company_name = companyList.getSelectionModel().getSelectedItem();
            ArrayList<Route> routeInfoS = connDB.getRoute(company_name);
            routeInfoList.remove(0, routeInfoList.size());
            for (Route aRoute : routeInfoS) {
                routeInfoList.add(aRoute.getRoute_name());
            }
            routeList.getItems().remove(0, routeList.getItems().size());
            routeList.getItems().addAll(routeInfoList);
            routeList.valueProperty().addListener((ov2, str21, str22) -> {
                route_no = connDB.getRouteNo(routeList.getSelectionModel().getSelectedItem());
            });
        });
    }
}