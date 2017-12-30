package client.ui.manage.route;

import client.data.Route;
import client.ui.manage.ManageManage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RouteController extends ManageManage implements Initializable {

    public ComboBox<String> subsidiaryListBox;
    public TextField route_name;
    public TextField route_spot;
    public TextField averagePrice;
    public TextField traffic;
    private String subsidiary_no;

    @FXML
    protected void addRoute() {
        if (route_name.getText().equals("") || route_spot.getText().equals("")
                || averagePrice.getText().equals("") || traffic.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please input correct message");
            return;
        }
        Route route = new Route();
        route.setRoute_name(route_name.getText());
        route.setRoute_spot(route_spot.getText());
        route.setAveragePrice(Float.valueOf(averagePrice.getText()));
        route.setTraffic(traffic.getText());
        route.setSubsidiary_no(subsidiary_no);
        if (connDB.addRoute(route)) {
            JOptionPane.showMessageDialog(null, "success to add new route");
            route_name.setText("");
            route_spot.setText("");
            averagePrice.setText("");
            traffic.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "fail to add new route");
        }
    }

    @FXML
    protected void back() {
        backToManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!subsidiaryListBox.getItems().isEmpty()) {
            subsidiaryListBox.getItems().remove(0, subsidiaryListBox.getItems().size());
        }
        subsidiaryListBox.getItems().addAll(connDB.getSubsidiaryName().split(","));
        subsidiaryListBox.valueProperty().addListener((ov1, str11, str12) ->
                subsidiary_no = connDB.getSubsidiaryNo(subsidiaryListBox.getSelectionModel().getSelectedItem()));
    }
}