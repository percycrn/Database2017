package client.ui.manage.spot;

import client.ui.manage.ManageManage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SpotController extends ManageManage implements Initializable {

    public TextField spot_name;

    @FXML
    protected void addSpot() {
        if (spot_name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please input correct message");
            return;
        }
        if (connDB.addSpot(spot_name.getText())) {
            JOptionPane.showMessageDialog(null, "success to add new spot");
            spot_name.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "fail to add new spot");
        }
    }


    @FXML
    protected void back() {
        backToManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}