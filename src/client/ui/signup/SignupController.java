package client.ui.signup;

import client.ClientStart;
import client.data.Customer;
import client.ui.signin.SignIn;
import client.util.ConnDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    public TextField telNumber;
    public PasswordField password;
    public TextField idCard;
    public TextField name;
    public TextField address;
    private static ConnDB connDB = new ConnDB();

    @FXML
    protected void handleFallbackAction() {
        SignIn signIn = new SignIn();
        try {
            signIn.init();
            signIn.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "fail to turn to SignIn interface");
        }
    }

    @FXML
    protected void handleSignupAction() {
        if (telNumber.getText().equals("") || password.getText().equals("") || idCard.getText().equals("") ||
                name.getText().equals("") || address.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "输入信息不能为空");
            return;
        }

        Customer customer = new Customer(telNumber.getText(), password.getText(),
                idCard.getText(), name.getText(), address.getText());
        try {
            connDB.storeNewCustomer(customer);
            JOptionPane.showMessageDialog(null,"success to sign up");
            SignIn signIn = new SignIn();
            signIn.init();
            signIn.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}