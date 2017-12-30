package client.ui.signin;

import client.ClientStart;
import client.ui.manage.manager.Manager;
import client.ui.signup.Signup;
import client.ui.select.Select;
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

    @FXML
    protected void handleSignInAction() {
        if (telNumber.getText().equals("") || password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please input correct message");
            return;
        } else if (telNumber.getText().length() != 11 && !telNumber.getText().equals("admin")) {
            JOptionPane.showMessageDialog(null, "incorrect telNumber format");
            return;
        }
        try {
            if (telNumber.getText().equals("admin") && password.getText().equals("admin")) {
                Manager manage = new Manager();
                try {
                    manage.init();
                    manage.start(ClientStart.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("fail to go to ManageManage interface");
                }
            } else if (!connDB.accountExist(telNumber.getText())) {
                JOptionPane.showMessageDialog(null, "account doesn't exits");
            } else if (!connDB.checkPassword(telNumber.getText(), password.getText())) {
                JOptionPane.showMessageDialog(null, "password is incorrect");
            } else {
                customer = connDB.getCustomer(telNumber.getText());
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
