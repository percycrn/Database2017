package client.ui.manage.subsidiary;

import client.data.Subsidiary;
import client.ui.manage.ManageManage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SubsidiaryController extends ManageManage implements Initializable {

    public TextField subsidiary_name;
    public TextField subsidiary_address;
    public TextField manager_name;
    public TextField manager_time;

    @FXML
    protected void addSubsidiary() {
        if (subsidiary_name.getText().equals("") || subsidiary_address.getText().equals("")
                || manager_name.getText().equals("") || manager_time.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please input correct message");
            return;
        }
        Subsidiary subsidiary = new Subsidiary();
        subsidiary.setSubsidiary_name(subsidiary_name.getText());
        subsidiary.setSubsidiary_address(subsidiary_address.getText());
        subsidiary.setManager_name(manager_name.getText());
        subsidiary.setManager_time(manager_time.getText());
        if (connDB.addSubsidiary(subsidiary)) {
            JOptionPane.showMessageDialog(null, "success to add new subsidiary");
            subsidiary_name.setText("");
            subsidiary_address.setText("");
            manager_name.setText("");
            manager_time.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "fail to add new subsidiary");
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