package client;

import client.ui.signin.SignIn;
import javafx.stage.Stage;

public class ClientStart {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ClientStart.stage = stage;
    }

    public static void main(String[] args) {
        SignIn.launch();
    }
}