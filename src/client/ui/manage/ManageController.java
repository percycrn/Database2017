package client.ui.manage;

import client.ClientStart;
import client.ui.signin.SignIn;
import client.util.ManageClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageController extends ManageClient implements Initializable {

    @FXML
    protected void addCompany(){

    }

    @FXML
    protected void addGuide(){

    }

    @FXML
    protected void addRoute(){

    }

    @FXML
    protected void back(){
        SignIn signIn = new SignIn();
        try {
            signIn.init();
            signIn.start(ClientStart.getStage());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("fail to go to signin interface");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}