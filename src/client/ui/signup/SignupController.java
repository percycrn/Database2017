package client.ui.signup;

import client.ClientStart;
import client.data.Customer;
import client.ui.signin.SignIn;
import client.util.ConnDB;
import client.util.ManageClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController extends ManageClient implements Initializable {

    public TextField telNumber;
    public PasswordField password;
    public TextField idCard;
    public TextField name;
    public TextField occupation;

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
                name.getText().equals("") || occupation.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "please input effective messages");
            return;
        }
        try {
            if (connDB.accountExist(telNumber.getText())) {
                JOptionPane.showMessageDialog(null, "account already sign up");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer();
        customer.setCustomer_telNumber(telNumber.getText());
        customer.setCustomer_name(name.getText());
        customer.setCustomer_idCard(idCard.getText());
        customer.setCustomer_password(password.getText());
        customer.setCustomer_occupation(occupation.getText());
        try {
            connDB.storeNewCustomer(customer);
            JOptionPane.showMessageDialog(null, "success to sign up");
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