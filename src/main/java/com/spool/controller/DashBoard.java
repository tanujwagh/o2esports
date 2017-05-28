package com.spool.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spool.types.Coins;
import com.spool.util.Common;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.samples.JFXtrasSampleBase;
import jfxtras.samples.layout.CircularPaneSample1;
import jfxtras.scene.menu.CornerMenu;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class DashBoard
        extends JFXtrasSampleBase {

    ImageView imageView = null;
    static String chartTypeflag = "";
    public static Stage OldStage;
    public static Stage parentsStage;
    public static boolean staticsFlag;
    public static boolean draftFlag;
    public static boolean liveFlag;
    public static boolean totalFlag;
    public static boolean graph2;
    public static boolean graph3;
    public static boolean graph4;
    public static boolean teamFlag;
    public static boolean imagesFlag;
    public static boolean QAFlag;

    private final SimpleBooleanProperty autoShowAndHideObjectProperty = new SimpleBooleanProperty(false);
    private static  BorderPane borderPane;
    
    private  MenuItem facebookMenuItem;
    private  MenuItem googleMenuItem;
    private  MenuItem twitter;
    private  MenuItem facebookMenuItem1;
    private  MenuItem googleMenuItem1;
    private  MenuItem twitter1;
    private  MenuItem imagesBox;
    private  MenuItem QABox;
    private  MenuItem Graph2;
    private  MenuItem Graph3;
    private  MenuItem Graph4;
    
    private  Button zero;
    private  Button one;
    private  Button two;
    private  Button three;
    private  Button four;
    private  Button five;
    private  Button six;
    private  Button seven;
    private  Button eight;
    private  Button nine;
    private  Button exit;
    

    private CornerMenu cornerMenu;
    boolean loggedin;
    public static Const const1;
    private ChoiceBox<String> animationChoiceBox;
    private GridPane grid = new GridPane();
    private boolean useCornerMenu=true;
    
    private static Image image_logo_main;
    private static Label userName_main;
    private static int fontIncrement = 3;
    public static String uid;
    public static String coins;

    static {
        const1 = new Const();
        staticsFlag = false;
        draftFlag = false;
        liveFlag = false;
        totalFlag = false;
        teamFlag = false;
        imagesFlag = false;
        QAFlag = false;
        graph2 = false;
        graph3 = false;
        graph4 = false;
    }

    public DashBoard() {
    	
     	if (useCornerMenu){
    		/*facebookMenuItem = registerAction(new MenuItem("Satistics", (Node) new ImageView(new Image(getClass().getResourceAsStream("images1.jpg")))));
            googleMenuItem = registerAction(new MenuItem("draftadvantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images2.jpg")))));
            twitter = registerAction(new MenuItem("liveadvantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images3.jpg")))));
            facebookMenuItem1 = registerAction(new MenuItem("totaladvantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images4.jpg")))));
            googleMenuItem1 = registerAction(new MenuItem("teamfight", (Node) new ImageView(new Image(getClass().getResourceAsStream("images5.jpg")))));
            imagesBox = registerAction(new MenuItem("images", (Node) new ImageView(new Image(getClass().getResourceAsStream("images6.jpg")))));
            QABox = registerAction(new MenuItem("Q&A", (Node) new ImageView(new Image(getClass().getResourceAsStream("images7.jpg")))));
            Graph2 = registerAction(new MenuItem("Win Chance", (Node) new ImageView(new Image(getClass().getResourceAsStream("images8.jpg")))));
            Graph3 = registerAction(new MenuItem("Live Advantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images8.jpg")))));
            Graph4 = registerAction(new MenuItem("Team Fight", (Node) new ImageView(new Image(getClass().getResourceAsStream("23-0-m20.png")))));
            twitter1 = registerAction(new MenuItem("Close", (Node) new ImageView(new Image(getClass().getResourceAsStream("Close-icon.png")))));*/
    		facebookMenuItem = registerAction(new MenuItem("Satistics", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-9-m20.png")))));
            Graph2 = registerAction(new MenuItem("Win Chance", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-8-m20.png")))));
            Graph3 = registerAction(new MenuItem("Live Advantage", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-7-m20.png")))));
            Graph4 = registerAction(new MenuItem("Team Fight", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-6-m20.png")))));
            googleMenuItem = registerAction(new MenuItem("draftadvantage", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-5-m20.png")))));
            twitter = registerAction(new MenuItem("liveadvantage", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-4-m20.png")))));
            facebookMenuItem1 = registerAction(new MenuItem("totaladvantage", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-3-m20.png")))));
            googleMenuItem1 = registerAction(new MenuItem("teamfight", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-2-m20.png")))));
            imagesBox = registerAction(new MenuItem("images", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-1-m20.png")))));
            QABox = registerAction(new MenuItem("Q&A", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/23-0-m20.png")))));
            twitter1 = registerAction(new MenuItem("Close", (Node) new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/Close-icon.png")))));
    		animationChoiceBox = CircularPaneSample1.animationChoiceBox();
    	} else {
    		zero = registerAction(new Button("Satistics", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-0-mini.png")))));
    		one = registerAction(new Button("Win Chance", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-1-mini.png")))));
    		two = registerAction(new Button("Live Advantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-2-mini.png")))));
    		three = registerAction(new Button("Team Fight", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-3-mini.png")))));
            four = registerAction(new Button("draftadvantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-4-mini.png")))));
            five = registerAction(new Button("liveadvantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-5-mini.png")))));
            six =registerAction( new Button("totaladvantage", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-6-mini.png")))));
            seven = registerAction(new Button("teamfight", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-7-mini.png")))));
            eight = registerAction(new Button("images", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-8-mini.png")))));
            nine = registerAction(new Button("Q&A", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/23-9-mini.png")))));
            exit = registerAction(new Button("Close", (Node) new ImageView(new Image(getClass().getResourceAsStream("images/Close-icon.png")))));
    	}
        
        borderPane = new BorderPane();
    }

    private void createQuiz(){
    	 try {

    	     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/QA.fxml"));

             Parent root = fxmlLoader.load();
             QuestionController controller = fxmlLoader.getController();
             Scene scene = new Scene(root, 800, 550);
             scene.setFill(Color.TRANSPARENT);
             Stage stage = new Stage(StageStyle.TRANSPARENT);
             stage.setAlwaysOnTop(true);
             stage.setOpacity(0.98);
             stage.setTitle("Questions");
             stage.setScene(scene);
             stage.show();
             controller.init();

             /*Stage stage = new QAForm().QAStage(uid);
             DragDelta delta = new DragDelta();
             stage.getScene().setOnMousePressed(event -> {
                         delta.setX(stage.getX() - event.getScreenX());
                         delta.setY(stage.getY() - event.getScreenY());
                     }
             );
             stage.getScene().setOnMouseDragged(event -> {
                         stage.setX(event.getScreenX() + delta.getX());
                         stage.setY(event.getScreenY() + delta.getY());
                     }
             );
             stage.setAlwaysOnTop(false);
             stage.show();
             OldStage = stage;*/
             
         } catch (Exception e) {
             e.printStackTrace();
             showException(e);
         }
    }
    
    
    public static void setUserCoinsLabel(){
        ImageView imageView = new ImageView(image_logo_main);
        userName_main = new Label(" o2coins: " + Coins.coins, imageView);
        
        GridPane grid = new GridPane();
        Font labelFont = userName_main.getFont();
        int newFontSize = (int)(labelFont.getSize() + fontIncrement);
        userName_main.setFont(new Font(labelFont.getName(), newFontSize));
        
         //Label userName = new Label("O2 Coins:" + coins);
        userName_main.setTextFill((Paint) Color.web((String) "#0076a3"));
        grid.add((Node) userName_main, 0, 1);
        borderPane.setBottom((Node) grid);
    }

    public static void updateCoins(){
        userName_main.setText(" o2coins: " + Coins.coins);
    }
    
    
    private MenuItem registerAction(MenuItem menuItem) {
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (useCornerMenu==true){
                	cornerMenu.hide();
                } else {
                	setGridMenuVisible(false);
                }
            	
            	if(imageView != null){
            		imageView.setVisible(true);
            	}

                if (menuItem.getText().equalsIgnoreCase("Satistics")) {
                    chartTypeflag = "one";
                    if (draftFlag || liveFlag || totalFlag || teamFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        draftFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (staticsFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        return;
                    }
                    staticsFlag = true;
                    loadGraphView();
                } else if (menuItem.getText().equalsIgnoreCase("draftadvantage")) {
                    chartTypeflag = "two";
                    if (staticsFlag || liveFlag || totalFlag || teamFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                         liveFlag = false;
                        totalFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (draftFlag) {
                        OldStage.close();
                        draftFlag = false;
                        return;
                    }
                    draftFlag = true;
                    loadGraphView();
                } else if (menuItem.getText().equalsIgnoreCase("liveadvantage")) {
                    chartTypeflag = "three";
                    if (staticsFlag || draftFlag || totalFlag || teamFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        draftFlag = false;
                        totalFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (liveFlag) {
                        OldStage.close();
                        liveFlag = false;
                        return;
                    }
                    liveFlag = true;
                    loadGraphView();
                } else if (menuItem.getText().equalsIgnoreCase("totaladvantage")) {
                    chartTypeflag = "four";
                    if (staticsFlag || liveFlag || draftFlag || teamFlag || imagesFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        draftFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (totalFlag) {
                        OldStage.close();
                        totalFlag = false;
                        return;
                    }
                    totalFlag = true;
                    loadGraphView();
                } else if (menuItem.getText().equalsIgnoreCase("teamfight")) {
                    chartTypeflag = "five";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (teamFlag) {
                        OldStage.close();
                        teamFlag = false;
                        return;
                    }
                    teamFlag = true;
                    loadGraphView();
                } else if (menuItem.getText().equalsIgnoreCase("Win Chance")) {
                    chartTypeflag = "eight";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (graph2) {
                        OldStage.close();
                        graph2 = false;
                        return;
                    }
                    graph2 = true;
                    loadGraphView();
                } else if (menuItem.getText().equalsIgnoreCase("Live Advantage")) {
                    chartTypeflag = "nine";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph4 = false;
                    }
                    if (graph3) {
                        OldStage.close();
                        graph3 = false;
                        return;
                    }
                    graph3 = true;
                    loadGraphView();
                } else if (menuItem.getText().equalsIgnoreCase("Team Fight")) {
                    chartTypeflag = "ten";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                    }
                    if (graph4) {
                        OldStage.close();
                        graph4 = false;
                        return;
                    }
                    graph4 = true;
                    loadGraphView();
                } else {
                    if (menuItem.getText().equalsIgnoreCase("images")) {
                        chartTypeflag = "six";
                        if (staticsFlag || liveFlag || totalFlag || draftFlag || teamFlag || QAFlag) {
                            OldStage.close();
                            staticsFlag = false;
                            liveFlag = false;
                            totalFlag = false;
                            draftFlag = false;
                            teamFlag = false;
                            QAFlag = false;
                        }
                        if (imagesFlag) {
                            OldStage.close();
                            imagesFlag = false;
                            return;
                        }
                        imagesFlag = true;
                        try {
                            Stage stage = new ImageGallery().createImageBox();
                            DragDelta delta = new DragDelta();
                            stage.getScene().setOnMousePressed(event -> {
                                delta.setX(stage.getX() - event.getScreenX());
                                delta.setY(stage.getY() - event.getScreenY());
                            }
                            );
                            stage.getScene().setOnMouseDragged(event -> {
                                stage.setX(event.getScreenX() + delta.getX());
                                stage.setY(event.getScreenY() + delta.getY());
                            }
                            );
                            stage.setAlwaysOnTop(true);
                            stage.show();
                            OldStage = stage;
                        } catch (Exception e) {
                            e.printStackTrace();
                            showException(e);
                        }
                        return;
                    }
                    if (menuItem.getText().equalsIgnoreCase("Q&A")) {
                        chartTypeflag = "seven";
                        if (staticsFlag || liveFlag || totalFlag || draftFlag || teamFlag || imagesFlag) {
                            OldStage.close();
                            staticsFlag = false;
                            liveFlag = false;
                            totalFlag = false;
                            draftFlag = false;
                            teamFlag = false;
                            imagesFlag = false;
                        }
                        if (QAFlag) {
                            if (OldStage != null) {
                                OldStage.close();
                            }
                            QAFlag = false;
                            return;
                        }
                        QAFlag = true;
                        
                        createQuiz();
                       
                        return;
                    }
                    if (menuItem.getText().equalsIgnoreCase("Close")) {
                        if (OldStage != null) {
                            OldStage.close();
                        }
                        parentsStage.close();
                        Platform.exit();
                    }
                }
            }

        });
        return menuItem;
    }

    private void loadGraphView() {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/DashBoard.fxml"));
                Parent root = fxmlLoader.load();
                DashboardController controller = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root, 800, 400);
                scene.setFill((Paint) Color.TRANSPARENT);
                scene.getStylesheets().add(getClass().getClassLoader().getResource("css/newCascadeStyleSheet.css").toExternalForm());
                stage.setScene(scene);
                DragDelta delta = new DragDelta();
                scene.setOnMousePressed(event -> {
                            delta.setX(stage.getX() - event.getScreenX());
                            delta.setY(stage.getY() - event.getScreenY());
                        }
                );
                scene.setOnMouseDragged(event -> {
                            stage.setX(event.getScreenX() + delta.getX());
                            stage.setY(event.getScreenY() + delta.getY());
                        }
                );
                OldStage = stage;
                stage.setAlwaysOnTop(true);
                stage.show();
                controller.init();
        } catch (Exception e) {
            showException(e);
        }
    }


    private Button registerAction(Button button) {
    	button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (useCornerMenu==true){
                	cornerMenu.hide();
                } else {
                	setGridMenuVisible(false);
                }
            	
            	if(imageView != null){
            		System.out.println("imageView no nulo");
            		imageView.setVisible(true);
            	}

                if (button.getText().equalsIgnoreCase("Satistics")) {
                    chartTypeflag = "one";
                    if (draftFlag || liveFlag || totalFlag || teamFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        draftFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (staticsFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        return;
                    }
                    staticsFlag = true;
                } else if (button.getText().equalsIgnoreCase("draftadvantage")) {
                    chartTypeflag = "two";
                    if (staticsFlag || liveFlag || totalFlag || teamFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                         liveFlag = false;
                        totalFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (draftFlag) {
                        OldStage.close();
                        draftFlag = false;
                        return;
                    }
                    draftFlag = true;
                } else if (button.getText().equalsIgnoreCase("liveadvantage")) {
                    chartTypeflag = "three";
                    if (staticsFlag || draftFlag || totalFlag || teamFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        draftFlag = false;
                        totalFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (liveFlag) {
                        OldStage.close();
                        liveFlag = false;
                        return;
                    }
                    liveFlag = true;
                } else if (button.getText().equalsIgnoreCase("totaladvantage")) {
                    chartTypeflag = "four";
                    if (staticsFlag || liveFlag || draftFlag || teamFlag || imagesFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        draftFlag = false;
                        teamFlag = false;
                        imagesFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (totalFlag) {
                        OldStage.close();
                        totalFlag = false;
                        return;
                    }
                    totalFlag = true;
                } else if (button.getText().equalsIgnoreCase("teamfight")) {
                    chartTypeflag = "five";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (teamFlag) {
                        OldStage.close();
                        teamFlag = false;
                        return;
                    }
                    teamFlag = true;
                } else if (button.getText().equalsIgnoreCase("Win Chance")) {
                    chartTypeflag = "eight";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph3 = false;
                        graph4 = false;
                    }
                    if (graph2) {
                        OldStage.close();
                        graph2 = false;
                        return;
                    }
                    graph2 = true;
                } else if (button.getText().equalsIgnoreCase("Live Advantage")) {
                    chartTypeflag = "nine";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph4 = false;
                    }
                    if (graph3) {
                        OldStage.close();
                        graph3 = false;
                        return;
                    }
                    graph3 = true;
                } else if (button.getText().equalsIgnoreCase("Team Fight")) {
                    chartTypeflag = "ten";
                    if (staticsFlag || liveFlag || totalFlag || draftFlag || imagesFlag || QAFlag) {
                        OldStage.close();
                        staticsFlag = false;
                        liveFlag = false;
                        totalFlag = false;
                        draftFlag = false;
                        imagesFlag = false;
                        QAFlag = false;
                        graph2 = false;
                        graph3 = false;
                    }
                    if (graph4) {
                        OldStage.close();
                        graph4 = false;
                        return;
                    }
                    graph4 = true;
                } else {
                    if (button.getText().equalsIgnoreCase("images")) {
                        chartTypeflag = "six";
                        if (staticsFlag || liveFlag || totalFlag || draftFlag || teamFlag || QAFlag) {
                            OldStage.close();
                            staticsFlag = false;
                            liveFlag = false;
                            totalFlag = false;
                            draftFlag = false;
                            teamFlag = false;
                            QAFlag = false;
                        }
                        if (imagesFlag) {
                            OldStage.close();
                            imagesFlag = false;
                            return;
                        }
                        imagesFlag = true;
                        try {
                            Stage stage = new ImageGallery().createImageBox();
                            DragDelta delta = new DragDelta();
                            stage.getScene().setOnMousePressed(event -> {
                                delta.setX(stage.getX() - event.getScreenX());
                                delta.setY(stage.getY() - event.getScreenY());
                            }
                            );
                            stage.getScene().setOnMouseDragged(event -> {
                                stage.setX(event.getScreenX() + delta.getX());
                                stage.setY(event.getScreenY() + delta.getY());
                            }
                            );
                            stage.setAlwaysOnTop(true);
                            stage.show();
                            OldStage = stage;
                        } catch (Exception e) {
                            e.printStackTrace();
                            showException(e);
                        }
                        return;
                    }
                    if (button.getText().equalsIgnoreCase("Q&A")) {
                        chartTypeflag = "seven";
                        if (staticsFlag || liveFlag || totalFlag || draftFlag || teamFlag || imagesFlag) {
                            OldStage.close();
                            staticsFlag = false;
                            liveFlag = false;
                            totalFlag = false;
                            draftFlag = false;
                            teamFlag = false;
                            imagesFlag = false;
                        }
                        if (QAFlag) {
                            if (OldStage != null) {
                                OldStage.close();
                            }
                            QAFlag = false;
                            return;
                        }
                        QAFlag = true;
                        try {
                            Stage stage = new QAForm().QAStage(uid);
                            DragDelta delta = new DragDelta();
                            stage.getScene().setOnMousePressed(event -> {
                                delta.setX(stage.getX() - event.getScreenX());
                                delta.setY(stage.getY() - event.getScreenY());
                            }
                            );
                            stage.getScene().setOnMouseDragged(event -> {
                                stage.setX(event.getScreenX() + delta.getX());
                                stage.setY(event.getScreenY() + delta.getY());
                            }
                            );
                            stage.setAlwaysOnTop(true);
                            stage.show();
                            OldStage = stage;
                            
                        } catch (Exception e) {
                        	showException(e);
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (button.getText().equalsIgnoreCase("Close")) {
                        if (OldStage != null) {
                            OldStage.close();
                        }
                        parentsStage.close();
                    }
                }
                if (OldStage != null) {
                    OldStage.close();
                }
                if (!button.getText().equalsIgnoreCase("Close")) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml"));
                        try {
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.TRANSPARENT);
                            Scene scene = new Scene(root1);
                            scene.setFill((Paint) Color.TRANSPARENT);
                            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/newCascadeStyleSheet.css").toExternalForm());
                            stage.setScene(scene);
                            DragDelta delta = new DragDelta();
                            scene.setOnMousePressed(event -> {
                                delta.setX(stage.getX() - event.getScreenX());
                                delta.setY(stage.getY() - event.getScreenY());
                            }
                            );
                            scene.setOnMouseDragged(event -> {
                                stage.setX(event.getScreenX() + delta.getX());
                                stage.setY(event.getScreenY() + delta.getY());
                            }
                            );
                            OldStage = stage;
                            stage.setAlwaysOnTop(true);
                            stage.show();
                        } catch (Exception e) {
                        	showException(e);
                           // Logger.getLogger(class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (Exception e) {
                    	showException(e);
                        //Logger.getLogger(class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        return button;
    }
    
    
    public void getCornerImage() {
        try {
            imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/main.png")));
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    event.consume();
                    if (useCornerMenu==true){
                    	cornerMenu.show();
                    } else {
                    	setGridMenuVisible(true);
                    }
                    
                    imageView.setVisible(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showException(e);
        }
    }

    public String getSampleName() {
        return getClass().getSimpleName();
    }

    public String getSampleDescription() {
        return null;
    }

    public boolean addLogin() {
        PasswordField pwBox = new PasswordField();
        //Sergio
        //pwBox.setText("Dream#597");
        try {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10.0);
            grid.setVgap(10.0);
            grid.setPadding(new Insets(25.0, 25.0, 25.0, 25.0));
            Scene scene = new Scene((Parent) grid, 300.0, 275.0);
            ImageView logo = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/logo.png")));
            logo.setPreserveRatio(true);
            logo.setFitWidth(40);
            logo.setFitHeight(40);
            grid.add(logo,0,0);
            Text scenetitle = new Text("o2esports");
            scenetitle.setFont(Font.font((String) "Tahoma", (FontWeight) FontWeight.NORMAL, (double) 20.0));
            grid.add((Node) scenetitle, 1, 0, 2, 1);
            Label userName = new Label("UserName:");
            grid.add((Node) userName, 0, 1);
            TextField userTextField = new TextField();
            //Sergio
            //userTextField.setText("admin_user");
            grid.add((Node) userTextField, 1, 1);
            Label pw = new Label("Password:");
            grid.add((Node) pw, 0, 2);
            grid.add((Node) pwBox, 1, 2);
            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10.0);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add((Node) hbBtn, 1, 4);
            Text actiontarget = new Text();
            grid.add((Node) actiontarget, 1, 6);
            //
            String url = "http://www.google.com";
            Hyperlink hl = new Hyperlink("forgot password");
            hl.setTooltip(new Tooltip("forgot password"));
            hl.setAlignment(Pos.BOTTOM_LEFT);
            grid.add((Node) hl, 1, 3);
            hl.setOnAction((ActionEvent event) -> {
                Hyperlink h = (Hyperlink) event.getTarget();
                String s = h.getTooltip().getText();
                try {
                    URI u = null;
                    u = new URI(url);
                    java.awt.Desktop.getDesktop().browse(u);
                } catch (Exception e) {
                	showException(e);
                    Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, e);
                }
                event.consume();
            });
            //
            
            borderPane.setCenter((Node) grid);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
		            grid.getChildren().clear();
		            borderPane.setBottom(null);
		           // borderPane.setBottom(imageView);
                    if (!pwBox.getText().isEmpty() && !userTextField.getText().isEmpty()) {
                    	uid=(loginUser(userTextField.getText(), pwBox.getText()));
                        coins=(getCoins(uid));
                        Common.getCoins(uid);
                        System.out.println("UserName: " + userTextField.getText() + "; Password: " + pwBox.getText());
                        
                        //Label userName = new Label("O2 Coins:" + coins);
                        image_logo_main = new Image(getClass().getClassLoader().getResourceAsStream("images/golden-logo.png"), 34, 39, true, true);
                        setUserCoinsLabel();
                        
                        
                        Font labelFont = userName.getFont();
                        int newFontSize = (int)(labelFont.getSize() + fontIncrement);
                        userName.setFont(new Font(labelFont.getName(), newFontSize));
                        userName.setTextFill((Paint) Color.web((String) "#0076a3"));
                        grid.add((Node) userName, 0, 1);
                        grid.setHgap(100.0);
                        
                        
                    } else {
                    	Alert alert = new Alert(AlertType.ERROR, "Please insert username and password", ButtonType.OK);
                    	alert.setHeaderText("Login Error");
                    	alert.showAndWait();
                    	System.exit(0);
                    }
                    
                	borderPane.setCenter(null);
                	
                	if (useCornerMenu){
                		createCornerMenu();
                        animationChoiceBox.getSelectionModel().selectedItemProperty().addListener(observable -> {
                            cornerMenu.setAnimationInterpolation(CircularPaneSample1.convertAnimationInterPolation((ChoiceBox) animationChoiceBox));
                        }
                        );
                        animationChoiceBox.getSelectionModel().select(CircularPaneSample1.Animations.FromOriginWithFadeRotate.toString());
                        cornerMenu.setAnimationInterpolation(CircularPaneSample1.convertAnimationInterPolation((ChoiceBox) animationChoiceBox));
                	} else {
                		createGridMenu();
                	}
                }
            });
        } catch (Exception grid) {
        	showException(grid);
            System.out.println("error=="+grid);
        }
        
        return loggedin;
    }

    public String getCoins(String uid) {
        String coins = null;
        String id = null;
        try {
            String url = "http://o2esports.com/user/coins/" + uid + "/";
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            HttpResponse response = client.execute((HttpUriRequest) request);
            System.out.println("\nSending 'POST' request to URL : " + url + uid + "/");
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
            JSONObject jsonObject = new JSONObject(result.toString());
            coins = jsonObject.getString("coins");
            id = jsonObject.getString("id");
        } catch (IOException e) {
            e.printStackTrace();
            showException(e);
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            showException(e);
            return "0";
        }
        const1.coins = Integer.parseInt(coins);
        const1.id = Integer.parseInt(id);
        
        image_logo_main = new Image(getClass().getClassLoader().getResourceAsStream("images/golden-logo.png"), 34, 39, true, true);
        setUserCoinsLabel();
        
        return coins;
    }

    public Node getPanel(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 1365.0);
        stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 420.0);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setOpacity(0.8);
        stage.setHeight(210.0);
        stage.setWidth(210.0);
        Scene scene = new Scene((Parent) borderPane);
        scene.setFill((Paint) Color.TRANSPARENT);
        stage.resizableProperty().set(false);
        stage.setScene(scene);
        DragDelta delta = new DragDelta();
        borderPane.setStyle("-fx-background-color:rgba(0, 0, 0.0, 0.9);");
        borderPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                delta.setX(stage.getX() - mouseEvent.getScreenX());
                delta.setY(stage.getY() - mouseEvent.getScreenY());
            }
        });

        borderPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                stage.setX(mouseEvent.getScreenX() + delta.getX());
                stage.setY(mouseEvent.getScreenY() + delta.getY());
            }

        });
        stage.setAlwaysOnTop(true);
        parentsStage = stage;
        return borderPane;
    }

    @Override
    public Node getControlPanel() {

     	//boolean is_LoggedIn = true; 
    	boolean is_LoggedIn = addLogin();
        if (is_LoggedIn) {
           
        	if (useCornerMenu){
        		createCornerMenu();
                animationChoiceBox.getSelectionModel().selectedItemProperty().addListener(observable -> {
                    cornerMenu.setAnimationInterpolation(CircularPaneSample1.convertAnimationInterPolation(animationChoiceBox));
                }
                );
                animationChoiceBox.getSelectionModel().select(CircularPaneSample1.Animations.FromOriginWithFadeRotate.toString());
                cornerMenu.setAnimationInterpolation(CircularPaneSample1.convertAnimationInterPolation(animationChoiceBox));
        	} else {
        		createGridMenu();
        	}
        	
        }
        return null;
    }

    private void createGridMenu(){
    	
    	grid.add((Node) zero, 0, 0);
    	grid.add((Node) one, 1, 0);
    	grid.add((Node) two, 2, 0);
    	grid.add((Node) three, 0, 1);
    	grid.add((Node) four, 1, 1);
    	grid.add((Node) five, 2, 1);
    	grid.add((Node) six, 0, 2);
    	grid.add((Node) seven, 1, 2);
    	grid.add((Node) eight, 2, 2);
    	grid.add((Node) nine,  0, 3);
    	grid.add((Node) exit, 1, 3);
        grid.add((Node) zero, 0, 4);
        grid.add((Node) one, 1, 4);
    	grid.setPadding(new Insets(10,10,10,10));
    	grid.setHgap(10);
    	grid.setVgap(10);
    	borderPane.setCenter((Node) grid);
    	
    	//setGridMenuVisible(false);
    }
    
    
    private void setGridMenuVisible(boolean isVisible){
    	grid.setVisible(isVisible);
    }
    
    private void createCornerMenu() {
        if (cornerMenu != null) {
            cornerMenu.autoShowAndHideProperty().unbind();
            cornerMenu.removeFromPane();
            cornerMenu = null;
        }
        getCornerImage();
        //cornerMenu = new CornerMenu(CornerMenu.Location.TOP_LEFT, (Pane) borderPane, false);
        cornerMenu = new CornerMenu(CornerMenu.Location.TOP_RIGHT, (Pane) borderPane, false);
        cornerMenu.getItems().addAll(new MenuItem[]{facebookMenuItem, Graph2, Graph3, Graph4, googleMenuItem, twitter, facebookMenuItem1, googleMenuItem1, imagesBox, QABox, twitter1});
        //cornerMenu.getItems().addAll(new MenuItem[]{twitter1, imagesBox, QABox});
        cornerMenu.setAnimationInterpolation(CircularPaneSample1.convertAnimationInterPolation(animationChoiceBox));
        cornerMenu.autoShowAndHideProperty().bind((ObservableValue) autoShowAndHideObjectProperty);
        //borderPane.setLeft((Node) imageView);
        borderPane.setCenter((Node) imageView);
        cornerMenu.hide();
    }

    public String getJavaDocURL() {
        return null;
    }

    public static void main(String[] args) {
        launch((String[]) args);
    }

    private String loginUser(String un, String pass) {
        String uid = null;
        try {
            String url = "http://o2esports.com/api/user/login";
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            StringEntity params = new StringEntity("{\"name\":\"" + un + "\",\"pass\":\"" + pass + "\"}");
            post.addHeader("content-type", "application/json");
            post.addHeader("Accept", "application/json");
            post.setEntity((HttpEntity) params);
            HttpResponse response = client.execute((HttpUriRequest) post);
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + (Object) post.getEntity());
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
            JSONObject jsonObject = new JSONObject(result.toString());
            JSONObject rs = jsonObject.getJSONObject("user");
            uid = rs.getString("uid");
        } catch (IOException e) 
        {
            e.printStackTrace();
            showException(e);
        }
        System.out.println(uid);
        const1.uid = Integer.parseInt(uid);
        return uid;
    }
    
    private void showException(Exception e){
        e.printStackTrace();
    	Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
    	alert.setHeaderText("Error ");
    	alert.showAndWait();
    	System.exit(0);
    }
}
