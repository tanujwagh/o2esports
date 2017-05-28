package com.spool.controller;

import com.spool.model.QAModel;
import com.spool.service.Utility;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.spool.types.QAAnswer;
import com.spool.types.QAAnswers;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class QAForm {
    String[] question;
    String[] answer1;
    String[] answer2;
    String[] answer3;
    String[] answer4;
    Stage stage;
    Label label;
    RadioButton choice1;
    ToggleGroup answers1;
    ToggleGroup answers2;
    ToggleGroup answers3;
    ToggleGroup answers4;
    RadioButton userAnswered1;
    RadioButton userAnswered2;
    RadioButton userAnswered3;
    RadioButton userAnswered4;
    private Utility utility;
    TextField bid03text;
    TextField bid02text;
    TextField bid01text;
    TextField bid04text;
    QAModel qaModel;
    Button answer;
    Button refresh;
    int maxTextSize = 80;
    String uid;

    public Stage QAStage(String uid) throws Exception {
    	this.uid=uid;
    	createGUI();
    	return this.stage;
    }

    public void createGUI() throws Exception{
    	try {
		    question = new String[4];
		    answer1 = new String[10];
		    answer2 = new String[2];
		    answer3 = new String[10];
		    answer4 = new String[2];
		    stage = null;
		    label = null;
		    choice1 = new RadioButton();
		    answers1 = new ToggleGroup();
		    answers2 = new ToggleGroup();
		    answers3 = new ToggleGroup();
		    answers4 = new ToggleGroup();
		    userAnswered1 = new RadioButton();
		    userAnswered2 = new RadioButton();
		    userAnswered3 = new RadioButton();
		    userAnswered4 = new RadioButton();
		    utility = new Utility();
		    bid03text = new TextField();
		    bid02text = new TextField();
		    bid01text = new TextField();
		    bid04text = new TextField();
		    qaModel = null;
		    answer = new Button("Save");
		    refresh = new Button("Refresh");
    		    
            this.qaModel = this.utility.getQAFormData();
            this.question[0] = "4. Which Hero will have the highest kills at " + this.qaModel.getQuestionFor() + "th Min?";
            this.question[1] = "2. Which Team will have the highest kills at " + this.qaModel.getQuestionFor() + "th Min?";
            this.question[2] = "3. Which Hero will have the Highest Networth " + this.qaModel.getQuestionFor() + "th Min?";
            this.question[3] = "1. Which Team will have the Highest Networth " + this.qaModel.getQuestionFor() + "th Min?";
            this.answer1[0] = this.qaModel.getAnswer1_1();
            this.answer1[1] = this.qaModel.getAnswer1_2();
            this.answer1[2] = this.qaModel.getAnswer1_3();
            this.answer1[3] = this.qaModel.getAnswer1_4();
            this.answer1[4] = this.qaModel.getAnswer1_5();
            this.answer1[5] = this.qaModel.getAnswer1_6();
            this.answer1[6] = this.qaModel.getAnswer1_7();
            this.answer1[7] = this.qaModel.getAnswer1_8();
            this.answer1[8] = this.qaModel.getAnswer1_9();
            this.answer1[9] = this.qaModel.getAnswer1_10();
            this.answer2[0] = String.valueOf(this.qaModel.getTeamName1()) + " -" + this.qaModel.getAnswer2_1();
            this.answer2[1] = String.valueOf(this.qaModel.getTeamName2()) + " -" + this.qaModel.getAnswer2_2();
            this.answer3[0] = this.qaModel.getAnswer3_1();
            this.answer3[1] = this.qaModel.getAnswer3_2();
            this.answer3[2] = this.qaModel.getAnswer3_3();
            this.answer3[3] = this.qaModel.getAnswer3_4();
            this.answer3[4] = this.qaModel.getAnswer3_5();
            this.answer3[5] = this.qaModel.getAnswer3_6();
            this.answer3[6] = this.qaModel.getAnswer3_7();
            this.answer3[7] = this.qaModel.getAnswer3_8();
            this.answer3[8] = this.qaModel.getAnswer3_9();
            this.answer3[9] = this.qaModel.getAnswer3_10();
            this.answer4[0] = String.valueOf(this.qaModel.getTeamName1()) + " -" + this.qaModel.getAnswer4_1();
            this.answer4[1] = String.valueOf(this.qaModel.getTeamName2()) + " -" + this.qaModel.getAnswer4_2();
            
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 15;");
            pane.getChildren().clear();
            Scene scene = new Scene((Parent)pane, 800.0, 500.0);

            scene.setFill((Paint)Color.TRANSPARENT);
            Font font = new Font("Cambria", 16.0);
            Font.font((String)"Cambria", (FontWeight)FontWeight.BOLD, (double)16.0);
            
            this.label = new Label();
            this.label.setFont(font);
            this.label.setTranslateY(10.0);
            this.label.setTranslateX(350.0);
            this.label.setTextFill((Paint)Color.WHITE);
            this.label.setText("Questions");
            pane.getChildren().addAll(new Node[]{this.label});
            this.label = new Label();
            this.label.setFont(font);
            this.label.setTranslateY(40.0);
            this.label.setTranslateX(150.0);
            this.label.setTextFill((Paint)Color.WHITE);
            this.label.setText("Game Time: " + (this.qaModel.getGameTime().split("\\.").length < 2 ? this.qaModel.getGameTime() : new StringBuilder(String.valueOf(this.qaModel.getGameTime().split("\\.")[0])).append(".").append(this.qaModel.getGameTime().split("\\.")[1].length() <= 2 ? this.qaModel.getGameTime().split("\\.")[1] : this.qaModel.getGameTime().split("\\.")[1].substring(0, 2)).toString()) + "m");
            pane.getChildren().addAll(new Node[]{this.label});
            this.label = new Label();
            this.label.setFont(font);
            this.label.setTranslateY(40.0);
            this.label.setTranslateX(500.0);
            this.label.setTextFill((Paint)Color.WHITE);
            this.label.setText("Question for: " + this.qaModel.getQuestionFor() + "th Min");
            pane.getChildren().addAll(new Node[]{this.label});
            this.label = new Label();
            this.label.setFont(font);
            this.label.setTranslateY(70.0);
            this.label.setTranslateX(20.0);
            this.label.setTextFill((Paint)Color.WHITE);
            this.label.setText(this.question[3]);
            Label bid04 = new Label("Bid:");
            bid04.setFont(font);
            bid04.setTranslateY(70.0);
            bid04.setTranslateX(625.0);
            bid04.setTextFill((Paint)Color.WHITE);
            bid04.setText("Bid:");
            this.bid04text.setTranslateY(70.0);
            this.bid04text.setTranslateX(675.0);
            this.bid04text.setMaxWidth(maxTextSize);
            HBox answerArea = new HBox();
            answerArea.setTranslateY(95.0);
            answerArea.setTranslateX(50.0);
            answerArea.setSpacing(5.0);
            this.choicesQuesNo4(answerArea, font);
            pane.getChildren().addAll(new Node[]{this.label, this.bid04text, bid04, answerArea});
            this.label = new Label();
            this.label.setFont(font);
            this.label.setTranslateY(125.0);
            this.label.setTranslateX(20.0);
            this.label.setTextFill((Paint)Color.WHITE);
            this.label.setText(this.question[1]);
            Label bid3 = new Label("Bid:");
            bid3.setFont(font);
            bid3.setTranslateY(125.0);
            bid3.setTranslateX(625.0);
            bid3.setTextFill((Paint)Color.WHITE);
            bid3.setText("Bid:");
            this.bid02text.setTranslateY(125.0);
            this.bid02text.setTranslateX(675.0);
            this.bid02text.setMaxWidth(maxTextSize);
            answerArea = new HBox();
            answerArea.setTranslateY(150.0);
            answerArea.setTranslateX(50.0);
            answerArea.setSpacing(5.0);
            this.choicesQuesNo2(answerArea, font);
            pane.getChildren().addAll(new Node[]{this.label, this.bid02text, bid3, answerArea});
            this.label = new Label();
            this.label.setFont(font);
            this.label.setTranslateY(180.0);
            this.label.setTranslateX(20.0);
            this.label.setTextFill((Paint)Color.WHITE);
            this.label.setText(this.question[2]);
            Label bid03 = new Label("Bid:");
            bid03.setFont(font);
            bid03.setTranslateY(180.0);
            bid03.setTranslateX(625.0);
            bid03.setTextFill((Paint)Color.WHITE);
            bid03.setText("Bid:");
            this.bid03text.setTranslateY(180.0);
            this.bid03text.setTranslateX(675.0);
            this.bid03text.setMaxWidth(maxTextSize);
            answerArea = new HBox();
            answerArea.setTranslateY(205.0);
            answerArea.setTranslateX(50.0);
            answerArea.setSpacing(5.0);
            HBox answerArea2 = new HBox();
            answerArea2.setTranslateY(250.0);
            answerArea2.setTranslateX(50.0);
            answerArea2.setSpacing(5.0);
            this.choicesQuesNo3(answerArea, answerArea2, this.qaModel, font);
            pane.getChildren().addAll(new Node[]{this.label, bid03, this.bid03text, answerArea, answerArea2});
            this.label = new Label();
            this.label.setFont(font);
            this.label.setTranslateY(300.0);
            this.label.setTranslateX(20.0);
            this.label.setTextFill((Paint)Color.WHITE);
            this.label.setText(this.question[0]);
            Label bid01 = new Label("Bid:");
            bid01.setFont(font);
            bid01.setTranslateY(300.0);
            bid01.setTranslateX(625.0);
            bid01.setTextFill((Paint)Color.WHITE);
            bid01.setText("Bid:");
            this.bid01text.setTranslateY(300.0);
            this.bid01text.setTranslateX(675.0);
            this.bid01text.setMaxWidth(maxTextSize);
 
            answerArea = new HBox();
            answerArea.setTranslateY(325.0);
            answerArea.setTranslateX(50.0);
            answerArea.setSpacing(5.0);
            answerArea2 = new HBox();
            answerArea2.setTranslateY(370.0);
            answerArea2.setTranslateX(50.0);
            answerArea2.setSpacing(5.0);
            this.choicesQuesNo1(answerArea, answerArea2, this.qaModel, font);
            pane.getChildren().addAll(new Node[]{this.label, bid01, this.bid01text, answerArea, answerArea2});
            this.answer.setMaxWidth(Double.MAX_VALUE);
            
            //Reset values
            if (this.answers1.getSelectedToggle() != null){
            	this.answers1.getSelectedToggle().setSelected(false);
            }
            if (this.answers2.getSelectedToggle() != null){
            	this.answers2.getSelectedToggle().setSelected(false);
            }
            if (this.answers3.getSelectedToggle() != null){
            	this.answers3.getSelectedToggle().setSelected(false);
            }
            if (this.answers4.getSelectedToggle() != null){
            	this.answers4.getSelectedToggle().setSelected(false);
            }
            
            this.answers1.selectToggle(null);
            this.answers2.selectToggle(null);
            this.answers3.selectToggle(null);
            this.answers4.selectToggle(null);
            
            System.out.println(this.bid04text.getText());
            System.out.println(this.bid02text.getText());
            System.out.println("3 " + this.bid03text.getText());
            System.out.println("1 " + this.bid01text.getText());
            this.bid04text.setText("0");
            this.bid02text.setText("0");
            this.bid03text.setText("0");
            this.bid01text.setText("0");
            System.out.println(this.bid04text.getText());
            System.out.println(this.bid02text.getText());
            System.out.println(this.bid03text.getText());
            System.out.println(this.bid01text.getText());
            pane.requestLayout();
            
            refresh.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					System.out.println("Refresh button");
					try {
					    stage.close();
						createGUI();
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
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
            	
            });
            answer.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
             	
                	//questionAnswered();

                	submitAnswers();
                }
            });
            this.answers1.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                     QAForm.this.userAnswered1 = (RadioButton)newValue.getToggleGroup().getSelectedToggle();
                   }
            
            });
           this.answers2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                      QAForm.this.userAnswered2 = (RadioButton)newValue.getToggleGroup().getSelectedToggle();
                }
           
           
           });
           this.answers3.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                    QAForm.this.userAnswered3 = (RadioButton)newValue.getToggleGroup().getSelectedToggle();
                }
           
           
           });
           this.answers4.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                    QAForm.this.userAnswered4 = (RadioButton)newValue.getToggleGroup().getSelectedToggle();
                }
           
           
           });

            PauseTransition delay = new PauseTransition(Duration.seconds((double)30.0));
            delay.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    answer.setDisable(true);
                }
            });
            delay.play();
            this.answer.setDisable(false);
            this.answer.setTranslateY(450.0);
            this.answer.setTranslateX(325.0);
            pane.getChildren().addAll(new Node[]{this.answer});
            
            this.refresh.setTranslateY(450.0);
            this.refresh.setTranslateX(375.0);
            pane.getChildren().addAll(new Node[]{this.refresh});
            
            Label label1 = new Label();
            label1.setFont(font);
            label1.setTranslateY(420.0);
            label1.setTranslateX(250.0);
            label1.setTextFill((Paint)Color.WHITE);
            label1.setText("Save Button Will be disabled in 15 seconds");
            pane.getChildren().addAll(new Node[]{label1});
            
            ImageView logo = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/logo.png")));
            logo.setPreserveRatio(true);
            logo.setFitWidth(40);
            logo.setFitHeight(40);
            logo.setTranslateX(760);
            logo.setTranslateY(460);
            pane.getChildren().add(logo);


            this.stage = new Stage();
            this.stage.initStyle(StageStyle.TRANSPARENT);
            this.stage.setAlwaysOnTop(true);
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.setOpacity(0.95);//0.8);
        }
        catch (Exception e) {
    	    e.printStackTrace();
            FileOutputStream out = new FileOutputStream("erroro21.log");
            PrintStream ps2 = new PrintStream(out);
            e.printStackTrace(ps2);
            String userHome = System.getProperty("user.home");
            String outputFolder = String.valueOf(userHome) + "\\Downloads";
            try {
                Throwable answerArea = null;
                Object bid3 = null;
                try {
                    FileWriter w = new FileWriter(String.valueOf(outputFolder) + "\\erroro2.log");
                    try {
                        e.printStackTrace(new PrintWriter(new BufferedWriter(w)));
                    }
                    finally {
                        if (w != null) {
                            w.close();
                        }
                    }
                }
                catch (Throwable bid31) {
                    if (answerArea == null) {
                        answerArea = bid31;
                    } else if (answerArea != bid31) {
                        answerArea.addSuppressed(bid31);
                    }
                    try {
                        throw answerArea;
                    } catch (Throwable ex) {
                        Logger.getLogger(QAForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            catch (Exception answerArea) {
                // empty catch block
            }
        }
    }

    private void submitAnswers() {

        QAAnswer answer4 = new QAAnswer();
        answer4.setAnswer((String)this.userAnswered4.getUserData());
        answer4.setBid(Integer.parseInt(this.bid04text.getText()));
        answer4.setMultiplicationFactor(formatMultiplicationFactor(this.userAnswered4.getText()));
        answer4.setQuestionFor(Integer.parseInt(this.qaModel.getQuestionFor()));

        QAAnswer answer3 = new QAAnswer();
        answer3.setAnswer((String)this.userAnswered3.getUserData());
        answer3.setBid(Integer.parseInt(this.bid03text.getText()));
        answer3.setMultiplicationFactor(formatMultiplicationFactor(this.userAnswered3.getText()));
        answer3.setQuestionFor(Integer.parseInt(this.qaModel.getQuestionFor()));

        QAAnswer answer2 = new QAAnswer();
        answer2.setAnswer(formatAnswer(this.userAnswered2.getText()));
        answer2.setBid(Integer.parseInt(this.bid02text.getText()));
        answer2.setMultiplicationFactor(formatMultiplicationFactor(this.userAnswered2.getText()));
        answer2.setQuestionFor(Integer.parseInt(this.qaModel.getQuestionFor()));

        QAAnswer answer1 = new QAAnswer();
        answer1.setAnswer(formatAnswer(this.userAnswered1.getText()));
        answer1.setBid(Integer.parseInt(this.bid01text.getText()));
        answer1.setMultiplicationFactor(formatMultiplicationFactor(this.userAnswered1.getText()));
        answer1.setQuestionFor(Integer.parseInt(this.qaModel.getQuestionFor()));

        QAAnswers qaAnswers = new QAAnswers();
        qaAnswers.setAnswer1(answer1);
        qaAnswers.setAnswer2(answer2);
        qaAnswers.setAnswer3(answer3);
        qaAnswers.setAnswer4(answer4);

        if(this.utility.saveAnswers(qaAnswers)) {
            // this.utility.saveQAData(ans4, ans3, ans2, ans1, this.qaModel.getQuestionFor());

            //Update coins
            DashBoard.coins = "" + (Double.parseDouble(DashBoard.coins) - (answer1.getBid() + answer2.getBid() + answer3.getBid() + answer4.getBid()));
            DashBoard.updateCoins();
            DashBoard.const1.coins = (int)(Double.parseDouble(DashBoard.coins) - (answer1.getBid() + answer2.getBid() + answer3.getBid() + answer4.getBid()));

            this.utility.updateCoins("" + DashBoard.const1.uid, "" + (int)DashBoard.const1.coins, ""+ DashBoard.const1.id);

            Alert alert = new Alert(AlertType.INFORMATION, "Answers Saved Successfully!", ButtonType.OK);
            alert.setHeaderText("Data Saved!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.showAndWait();

            answer.setDisable(true);
        }else{
            Alert alert = new Alert(AlertType.ERROR, "Answer Save Failed!", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.showAndWait();
        }
    }

    private double formatMultiplicationFactor(String answer) {
        System.out.println(answer);
        return Double.parseDouble(answer.substring(answer.indexOf("-") + 1).trim());
    }

    private String formatAnswer(String answer) {
        System.out.println(answer);
        return answer.contains("-") ? answer.substring(0, answer.indexOf("-")).trim() : answer.trim();
    }


    public void questionAnswered() {
        if (this.bid04text.getText() == null) {
            this.bid04text.setText("0");
        }
        if (this.bid03text.getText() == null) {
            this.bid03text.setText("0");
        }
        if (this.bid02text.getText() == null) {
            this.bid02text.setText("0");
        }
        if (this.bid04text.getText() == null) {
            this.bid04text.setText("0");
        }
        String ans4 = String.valueOf(this.qaModel.getQuestionFor()) + "~" + "1" + "~" + Double.parseDouble(this.bid04text.getText()) * Double.parseDouble(this.userAnswered4.getText().split("-")[1]) + "~" + this.userAnswered4.getText().split("-")[0];
        String ans3 = String.valueOf(this.qaModel.getQuestionFor()) + "~" + "2" + "~" + Double.parseDouble(this.bid03text.getText()) * Double.parseDouble(this.userAnswered3.getText().split("-")[0]) + "~" + this.userAnswered3.getText().split("-")[0];
        String ans2 = String.valueOf(this.qaModel.getQuestionFor()) + "~" + "3" + "~" + Double.parseDouble(this.bid02text.getText()) * Double.parseDouble(this.userAnswered2.getText().split("-")[1]) + "~" + this.userAnswered2.getText().split("-")[0];
        String ans1 = String.valueOf(this.qaModel.getQuestionFor()) + "~" + "4" + "~" + Double.parseDouble(this.bid01text.getText()) * Double.parseDouble(this.userAnswered1.getText().split("-")[0]) + "~" + this.userAnswered1.getText().split("-")[0];
        System.out.println(String.valueOf(ans4) + "ans4");
        this.utility.saveQAData(ans4, ans3, ans2, ans1, this.qaModel.getQuestionFor());
        
        //Update coins
        DashBoard.coins = "1000";
        DashBoard.setUserCoinsLabel();
        
        
        //Show message
        String message = "Your selection of:\n\n " + ans4 + " at " + this.bid04text.getText() + " value\n " 
        		 								  + ans3 + " at " + this.bid03text.getText() + " value\n " 
        		 								  + ans2 + " at " + this.bid02text.getText() + " value\n " 
        		 								  +	ans1 + " at " + this.bid01text.getText() + " value has been saved";
        Alert alert = new Alert(AlertType.INFORMATION, message, ButtonType.OK);
    	alert.setHeaderText("Data Saved!");
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(stage);
    	alert.showAndWait();
    	
        if (this.stage != null) {
            this.stage.close();
        }
        DashBoard.QAFlag = false;
    }

    private void choicesQuesNo1(HBox answerArea, HBox answerArea2, QAModel qaModel, Font font) {
        this.choice1 = new RadioButton();
        this.choice1.setText(this.answer1[0]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setFont(font);
        this.choice1.setAccessibleHelp("1");
        this.choice1.setUserData(qaModel.getHero1().trim());
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero1()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setPrefWidth(130.0);
        this.choice1.setToggleGroup(this.answers1);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setText(this.answer1[1]);
        this.choice1.setAccessibleHelp("2");
        this.choice1.setUserData(qaModel.getHero2().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero2()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("3");
        this.choice1.setUserData(qaModel.getHero3().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero3()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[2]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("4");
        this.choice1.setUserData(qaModel.getHero4().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero4()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[3]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("5");
        this.choice1.setUserData(qaModel.getHero5().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero5()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[4]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("6");
        this.choice1.setUserData(qaModel.getHero6().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero6()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[5]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("7");
        this.choice1.setUserData(qaModel.getHero7().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero7()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[6]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("8");
        this.choice1.setUserData(qaModel.getHero8().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero8()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[7]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("9");
        this.choice1.setUserData(qaModel.getHero9().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero9()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[8]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("10");
        this.choice1.setUserData(qaModel.getHero10().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero10()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer1[9]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers1);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
    }

    private void choicesQuesNo2(HBox answerArea, Font font) {
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("1");
        this.choice1.setText(this.answer2[0]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(210.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers2);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("2");
        this.choice1.setText(this.answer2[1]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(180.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers2);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
    }

    private void choicesQuesNo3(HBox answerArea, HBox answerArea2, QAModel qaModel, Font font) {
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("1");
        this.choice1.setUserData(qaModel.getHero1().trim());
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero1()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[0]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setToggleGroup(this.answers3);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("2");
        this.choice1.setUserData(qaModel.getHero2().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero2()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[1]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setToggleGroup(this.answers3);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("3");
        this.choice1.setUserData(qaModel.getHero3().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero3()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[2]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setToggleGroup(this.answers3);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("4");
        this.choice1.setUserData(qaModel.getHero4().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero4()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[3]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers3);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("5");
        this.choice1.setUserData(qaModel.getHero5().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero5()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[4]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers3);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("6");
        this.choice1.setUserData(qaModel.getHero6().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero6()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[5]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers3);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("7");
        this.choice1.setUserData(qaModel.getHero7().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero7()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[6]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers3);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("8");
        this.choice1.setUserData(qaModel.getHero8().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero8()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[7]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers3);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("9");
        this.choice1.setUserData(qaModel.getHero9().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero9()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[8]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers3);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("10");
        this.choice1.setUserData(qaModel.getHero10().trim());
        image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(qaModel.getHero10()) + ".jpg"));
        this.choice1.setGraphic((Node)new ImageView(image));
        this.choice1.setText(this.answer3[9]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(130.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers3);
        this.choice1.setTextFill((Paint)Color.WHITE);
        answerArea2.getChildren().addAll(new Node[]{this.choice1});
    }

    private void choicesQuesNo4(HBox answerArea, Font font) {
        this.choice1 = new RadioButton();
        this.choice1.setText(this.answer4[0]);
        this.choice1.setAccessibleHelp("1");
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(210.0); //130
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers4);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
        this.choice1 = new RadioButton();
        this.choice1.setAccessibleHelp("2");
        this.choice1.setText(this.answer4[1]);
        this.choice1.setTextFill((Paint)Color.WHITE);
        this.choice1.setPrefWidth(180.0);
        this.choice1.setFont(font);
        this.choice1.setToggleGroup(this.answers4);
        answerArea.getChildren().addAll(new Node[]{this.choice1});
    }

}