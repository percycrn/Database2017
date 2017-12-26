package client.ui.signin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.ClientStart;

public class SignIn extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(root);
        ClientStart.setStage(primaryStage);
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void launch() {
        launch(new String[0]);
    }
}