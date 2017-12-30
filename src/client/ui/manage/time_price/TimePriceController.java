package client.ui.manage.time_price;

import client.data.Guide;
import client.data.TimePrice;
import client.ui.manage.ManageManage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class TimePriceController extends ManageManage implements Initializable {

    public TextField startTime;
    public TextField endTime;
    public TextField price;
    public TextField serverLevel;
    public TextField guide_name;
    public ComboBox<String> routeListBox;
    public ComboBox<String> subsidiaryListBox;
    private String route_no;

    @FXML
    protected void addTimePrice() {
        if (startTime.getText().equals("") || endTime.getText().equals("") || price.getText().equals("")
                || serverLevel.getText().equals("") || guide_name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please input correct message");
            return;
        }
        TimePrice timePrice = new TimePrice();
        timePrice.setRoute_no(route_no);
        timePrice.setStartTime(Date.valueOf(startTime.getText()));
        timePrice.setEndTime(Date.valueOf(endTime.getText()));
        timePrice.setPrice(Float.valueOf(price.getText()));
        timePrice.setServerLevel(serverLevel.getText());
        timePrice.setGuide_no(connDB.getGuideNoFromName(guide_name.getText()));
        if (connDB.addTimePrice(timePrice)) {
            JOptionPane.showMessageDialog(null, "success to add new time arrangement");
            startTime.setText("");
            endTime.setText("");
            price.setText("");
            serverLevel.setText("");
            guide_name.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "fail to add new time arrangement");
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
        subsidiaryListBox.valueProperty().addListener((ov1, str11, str12) -> {
            String subsidiary_no = connDB.getSubsidiaryNo(subsidiaryListBox.getSelectionModel().getSelectedItem());
            // refresh route comboBox
            if (!routeListBox.getItems().isEmpty()) {
                routeListBox.getItems().remove(0, routeListBox.getItems().size());
            }
            routeListBox.getItems().addAll(connDB.getRouteName(subsidiary_no).split(","));
            routeListBox.valueProperty().addListener((ov2, str21, str22) -> {
                route_no = connDB.getRouteNo(routeListBox.getSelectionModel().getSelectedItem());
            });
        });
    }
}