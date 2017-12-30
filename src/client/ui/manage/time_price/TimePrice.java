package client.ui.manage.time_price;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TimePrice extends Application {

    public static void launch() {
        launch(new String[0]);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TimePrice.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("time-price");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
