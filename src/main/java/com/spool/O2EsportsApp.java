package com.spool;

import com.spool.controller.HomeController;
import com.spool.controller.QuestionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Tanuj on 5/28/2017.
 */
public class O2EsportsApp extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Home.fxml"));
        Parent root = fxmlLoader.load();
        HomeController controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 1000, 560);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setOpacity(0.98);
        primaryStage.setTitle("Questions");
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.init();
    }
}
