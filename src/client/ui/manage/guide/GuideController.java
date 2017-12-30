package client.ui.manage.guide;

import client.data.Guide;
import client.ui.manage.ManageManage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class GuideController extends ManageManage implements Initializable {

    public TextField guide_name;
    public TextField guide_telNumber;
    public TextField guide_level;
    public TextField guide_start_time;
    public ComboBox<String> subsidiaryListBox;
    private String subsidiary_no;

    @FXML
    protected void addGuide() {
        if (guide_name.getText().equals("") || guide_telNumber.getText().length() != 11
                || guide_level.getText().equals("") || guide_start_time.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please input correct message");
            return;
        }
        Guide guide = new Guide();
        guide.setGuide_name(guide_name.getText());
        guide.setGuide_telNumber(guide_telNumber.getText());
        guide.setGuide_level(guide_level.getText());
        guide.setGuide_time(Date.valueOf(guide_start_time.getText()));
        guide.setSubsidiary_no(subsidiary_no);
        if (connDB.addGuide(guide)) {
            JOptionPane.showMessageDialog(null, "success to add new guide");
            guide_name.setText("");
            guide_telNumber.setText("");
            guide_level.setText("");
            guide_start_time.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "fail to add new guide");
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