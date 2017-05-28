package com.spool.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Tanuj on 5/28/2017.
 */
public class HomeController implements Initializable{

    /** UI Controls **/
    public Button btnQA;
    public Button btnExit;


    public Label lblCoin;
    public AnchorPane containerPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init(){
        setEventHandlers();
    }

    private void setEventHandlers() {
        btnQA.setOnAction(event -> showQAForm());
        btnExit.setOnAction(event -> Platform.exit());
    }

    private void showQAForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/QA.fxml"));
            AnchorPane root = fxmlLoader.load();
            QuestionController controller = fxmlLoader.getController();
            containerPane.getChildren().clear();
            containerPane.getChildren().add(root);
            controller.init();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
