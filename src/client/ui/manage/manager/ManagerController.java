package client.ui.manage.manager;

import client.ClientStart;
import client.ui.manage.guide.Guide;
import client.ui.manage.route.Route;
import client.ui.manage.spot.Spot;
import client.ui.manage.subsidiary.Subsidiary;
import client.ui.manage.time_price.TimePrice;
import client.ui.signin.SignIn;
import client.util.ManageClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController extends ManageClient implements Initializable {

    @FXML
    protected void addSubsidiary() {
        Subsidiary subsidiary = new Subsidiary();
        try {
            subsidiary.init();
            subsidiary.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addGuide() {
        Guide guide = new Guide();
        try {
            guide.init();
            guide.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addRoute() {
        Route route = new Route();
        try {
            route.init();
            route.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addSpot() {
        Spot spot = new Spot();
        try {
            spot.init();
            spot.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addTimePrice() {
        TimePrice timePrice = new TimePrice();
        try {
            timePrice.init();
            timePrice.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void back() {
        SignIn signIn = new SignIn();
        try {
            signIn.init();
            signIn.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to go to signin interface");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}