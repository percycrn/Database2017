package client.ui.company;

import client.ClientStart;
import client.ui.signin.SignIn;
import client.util.ManageClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CompanyController extends ManageClient implements Initializable {

    @FXML
    private TextField clientName;
    @FXML
    private PasswordField password;

    @FXML
    protected void handleFallbackAction() {
        SignIn signIn = new SignIn();
        try {
            signIn.init();
            signIn.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "fail to fallback to SignIn interface");
        }
    }

    @FXML
    protected void handleSpotAction() {
        if (clientName.getText().equals("") || password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "输入信息不能为空");
            return;
        }
    }

    @FXML
    protected void handleHistoryAction() {
        if (clientName.getText().equals("") || password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "输入信息不能为空");
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}