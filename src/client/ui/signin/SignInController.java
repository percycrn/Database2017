package client.ui.signin;

import client.ClientStart;
import client.ui.signup.Signup;
import client.ui.select.Select;
import client.util.ConnDB;
import client.util.ManageClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController extends ManageClient implements Initializable {
    @FXML
    public PasswordField password;
    @FXML
    public TextField telNumber;
    private static ConnDB connDB = new ConnDB();

    @FXML
    protected void handleSignInAction() {
        if (telNumber.getText().equals("") || password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "输入信息不能为空");
            return;
        }
        try {
            if (!connDB.accountExist(telNumber.getText())) {
                JOptionPane.showMessageDialog(null, "account doesn't exits");
            } else if (!connDB.checkPassword(telNumber.getText(), password.getText())) {
                JOptionPane.showMessageDialog(null, "password is incorrect");
            } else {
                Select select = new Select();
                select.init();
                select.start(ClientStart.getStage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleSignUpAction() {
        Signup signup = new Signup();
        try {
            signup.init();
            signup.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleExitAction() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
