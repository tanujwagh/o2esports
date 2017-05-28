package com.spool.controller;

import com.spool.types.*;
import com.spool.util.Common;
import com.spool.util.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Korrin on 4/5/2017.
 */
public class QuestionController implements Initializable{

    public Label lblQuestionFor;
    public Label lblDuration;

    public Label lblQuestion1;
    public Label lblQuestion2;
    public Label lblQuestion3;
    public Label lblQuestion4;

    public Label lblCoins;

    public Button btnSave;
    public Button btnRefresh;
    
    public RadioButton rdbAnswer1_1;
    public RadioButton rdbAnswer1_2;

    public RadioButton rdbAnswer2_1;
    public RadioButton rdbAnswer2_2;

    public RadioButton rdbAnswer3_1;
    public RadioButton rdbAnswer3_2;
    public RadioButton rdbAnswer3_3;
    public RadioButton rdbAnswer3_4;
    public RadioButton rdbAnswer3_5;
    public RadioButton rdbAnswer3_6;
    public RadioButton rdbAnswer3_7;
    public RadioButton rdbAnswer3_8;
    public RadioButton rdbAnswer3_9;
    public RadioButton rdbAnswer3_10;


    public RadioButton rdbAnswer4_1;
    public RadioButton rdbAnswer4_2;
    public RadioButton rdbAnswer4_3;
    public RadioButton rdbAnswer4_4;
    public RadioButton rdbAnswer4_5;
    public RadioButton rdbAnswer4_6;
    public RadioButton rdbAnswer4_7;
    public RadioButton rdbAnswer4_8;
    public RadioButton rdbAnswer4_9;
    public RadioButton rdbAnswer4_10;

    public TextField txtBid1;
    public TextField txtBid2;
    public TextField txtBid3;
    public TextField txtBid4;

    public AnchorPane informationPane;
    public Label lblInformation;
    public Button btnMessageOK;

    public Label lblMessage;

    public AnchorPane loadingPane;

    private SimpleIntegerProperty countProperty;
    private Timeline timeline;
    
    

    private ToggleGroup question1AnswersToggleGroup;
    private ToggleGroup question2AnswersToggleGroup;
    private ToggleGroup question3AnswersToggleGroup;
    private ToggleGroup question4AnswersToggleGroup;

    private RadioButton[] question3Options;
    private RadioButton[] question4Options;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question1AnswersToggleGroup = new ToggleGroup();
        question2AnswersToggleGroup = new ToggleGroup();
        question3AnswersToggleGroup = new ToggleGroup();
        question4AnswersToggleGroup = new ToggleGroup();

        countProperty = new SimpleIntegerProperty(15);
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event ->  {
                    countProperty.set(countProperty.get()-1);
                    lblMessage.setText(String.format("Save button will be disabled in %d Seconds", countProperty.get()));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(15);
        timeline.setOnFinished(event -> {
            btnSave.setDisable(true);
            lblMessage.setVisible(false);
        });
    }

    public void init(){
        initUI();
    }

    private void initUI() {
        rdbAnswer1_1.setToggleGroup(question1AnswersToggleGroup);
        rdbAnswer1_2.setToggleGroup(question1AnswersToggleGroup);

        rdbAnswer2_1.setToggleGroup(question2AnswersToggleGroup);
        rdbAnswer2_2.setToggleGroup(question2AnswersToggleGroup);

        rdbAnswer3_1.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_2.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_3.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_4.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_5.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_6.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_7.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_8.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_9.setToggleGroup(question3AnswersToggleGroup);
        rdbAnswer3_10.setToggleGroup(question3AnswersToggleGroup);
        
        rdbAnswer4_1.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_2.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_3.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_4.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_5.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_6.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_7.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_8.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_9.setToggleGroup(question4AnswersToggleGroup);
        rdbAnswer4_10.setToggleGroup(question4AnswersToggleGroup);

        question3Options = new RadioButton[]{   rdbAnswer3_1, rdbAnswer3_2, rdbAnswer3_3, rdbAnswer3_4, rdbAnswer3_5,
                                                rdbAnswer3_6, rdbAnswer3_7, rdbAnswer3_8, rdbAnswer3_9, rdbAnswer3_10
                                            };

        question4Options = new RadioButton[]{   rdbAnswer4_1, rdbAnswer4_2, rdbAnswer4_3, rdbAnswer4_4, rdbAnswer4_5,
                                                rdbAnswer4_6, rdbAnswer4_7, rdbAnswer4_8, rdbAnswer4_9, rdbAnswer4_10
                                            };

        btnMessageOK.setOnAction(event -> informationPane.setVisible(false));

        btnSave.setOnAction(event -> saveAnswers());

        btnRefresh.setOnAction(event -> getQAData());

        getQAData();
    }

    private void saveAnswers() {
        QAAnswers qaAnswers = new QAAnswers();

        QAAnswer qaAnswer1 =  question1AnswersToggleGroup.getSelectedToggle() != null ? (QAAnswer) question1AnswersToggleGroup.getSelectedToggle().getUserData() : null;
        if(qaAnswer1 != null){
            qaAnswer1.setBid(Integer.parseInt(txtBid1.getText()));
        }else {
            qaAnswer1 = QAAnswer.getDefault();
        }

        QAAnswer qaAnswer2 = question2AnswersToggleGroup.getSelectedToggle() != null ? (QAAnswer) question2AnswersToggleGroup.getSelectedToggle().getUserData() : null;
        if(qaAnswer2 != null){
            qaAnswer2.setBid(Integer.parseInt(txtBid2.getText()));
        }else {
            qaAnswer2 = QAAnswer.getDefault();
        }


        QAAnswer qaAnswer3 = question3AnswersToggleGroup.getSelectedToggle() != null ? (QAAnswer) question3AnswersToggleGroup.getSelectedToggle().getUserData() : null;
        if(qaAnswer3 != null){
            qaAnswer3.setBid(Integer.parseInt(txtBid3.getText()));
        }else {
            qaAnswer3 = QAAnswer.getDefault();
        }


        QAAnswer qaAnswer4 = question4AnswersToggleGroup.getSelectedToggle() != null ? (QAAnswer) question4AnswersToggleGroup.getSelectedToggle().getUserData() : null;
        if(qaAnswer4 != null){
            qaAnswer4.setBid(Integer.parseInt(txtBid4.getText()));
        }else {
            qaAnswer4 = QAAnswer.getDefault();
        }

        qaAnswers.setAnswer1(qaAnswer1);
        qaAnswers.setAnswer2(qaAnswer2);
        qaAnswers.setAnswer3(qaAnswer3);
        qaAnswers.setAnswer4(qaAnswer4);

        if(Common.saveAnswers(qaAnswers, BasicInfo.matchId, QAType.Oddtime)){
            Coins.coins = Coins.coins - (qaAnswer1.getBid() + qaAnswer2.getBid() + qaAnswer3.getBid() + qaAnswer4.getBid());
            Common.updateCoins(DashBoard.uid, Coins.coins + "", Coins.id + "");
            updateCoinsOnUI();
            Platform.runLater(() -> {
                lblInformation.setText("Answers Saved Successfully!");
                informationPane.setVisible(true);
            });
        }
    }

    private void updateCoinsOnUI() {
        Platform.runLater(() -> {
            lblCoins.setText(Coins.coins + "");
            DashBoard.updateCoins();
        });
    }

    public void getQAData(){
        Task<Void> getDataTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateCoinsOnUI();
                Platform.runLater(() -> loadingPane.setVisible(true));
                Common.getCoins(DashBoard.uid);
                Common.getLatestData();
                return null;
            }
        };
        getDataTask.setOnSucceeded(event -> {
            Platform.runLater(() -> loadingPane.setVisible(false));
            resetAnswers();
            setQAData();
            setQuestionAndAnswerData();
            startDisableTimer();
        });

        new Thread(getDataTask).start();
    }

    private void startDisableTimer() {
        countProperty.set(15);
        timeline.playFromStart();
    }

    private void resetAnswers() {
        question1AnswersToggleGroup.selectToggle(null);
        question2AnswersToggleGroup.selectToggle(null);
        question3AnswersToggleGroup.selectToggle(null);
        question4AnswersToggleGroup.selectToggle(null);
    }

    private void setQAData() {
        Platform.runLater(() -> {
            informationPane.setVisible(false);
            lblMessage.setVisible(true);
            lblDuration.setText(BasicInfo.duration + "m");
            lblQuestionFor.setText(QAType.Oddtime + "th");
            updateCoinsOnUI();
            txtBid1.setText(0 + "");
            txtBid2.setText(0 + "");
            txtBid3.setText(0 + "");
            txtBid4.setText(0 + "");
            btnSave.setDisable(false);
        });
    }

    private void setQuestionAndAnswerData() {
        Platform.runLater(() -> {
            lblQuestion1.setText(String.format(Constants.QUESTION_1_TEMPLATE, QAType.Oddtime));
            lblQuestion2.setText(String.format(Constants.QUESTION_2_TEMPLATE, QAType.Oddtime));
            lblQuestion3.setText(String.format(Constants.QUESTION_3_TEMPLATE, QAType.Oddtime));
            lblQuestion4.setText(String.format(Constants.QUESTION_4_TEMPLATE, QAType.Oddtime));

            QAAnswer qaAnswer1_1 = new QAAnswer();
            qaAnswer1_1.setQuestionFor(QAType.Oddtime);
            qaAnswer1_1.setAnswer(BasicInfo.RADIANT_NAME);
            qaAnswer1_1.setMultiplicationFactor(QAType.Rnetworthodds);
            rdbAnswer1_1.setText(BasicInfo.RADIANT_NAME + " - " + QAType.Rnetworthodds);
            rdbAnswer1_1.setUserData(qaAnswer1_1);

            QAAnswer qaAnswer1_2 = new QAAnswer();
            qaAnswer1_2.setQuestionFor(QAType.Oddtime);
            qaAnswer1_2.setAnswer(BasicInfo.DIRE_NAME);
            qaAnswer1_2.setMultiplicationFactor(QAType.Dnetworthodds);
            rdbAnswer1_2.setText(BasicInfo.DIRE_NAME + " - " + QAType.Dnetworthodds);
            rdbAnswer1_2.setUserData(qaAnswer1_2);

            QAAnswer qaAnswer2_1 = new QAAnswer();
            qaAnswer2_1.setQuestionFor(QAType.Oddtime);
            qaAnswer2_1.setAnswer(BasicInfo.RADIANT_NAME);
            qaAnswer2_1.setMultiplicationFactor(QAType.Rkodds);
            rdbAnswer2_1.setText(BasicInfo.RADIANT_NAME + " - " + QAType.Rkodds);
            rdbAnswer2_1.setUserData(qaAnswer2_1);

            QAAnswer qaAnswer2_2 = new QAAnswer();
            qaAnswer2_2.setQuestionFor(QAType.Oddtime);
            qaAnswer2_2.setAnswer(BasicInfo.DIRE_NAME);
            qaAnswer2_2.setMultiplicationFactor(QAType.Dkodds);
            rdbAnswer2_2.setText(BasicInfo.DIRE_NAME + " - " + QAType.Dkodds);
            rdbAnswer2_2.setUserData(BasicInfo.DIRE_NAME );

            populateAnswers(question3Options, QAType.heroNetOdds);
            populateAnswers(question4Options, QAType.heroKillOdds);

        });
    }

    private void populateAnswers(RadioButton[] options, Map<Integer, Double> odds) {
        int index = 1;
        for(RadioButton radioButton : options){
            QAAnswer qaAnswer = new QAAnswer();
            qaAnswer.setQuestionFor(QAType.Oddtime);
            qaAnswer.setAnswer("" + HeroNameData.getIdByHeroName("Hero" + index));
            qaAnswer.setMultiplicationFactor(odds.get(HeroNameData.getIdByHeroName("hero" + (index))));
            radioButton.setUserData(qaAnswer);
            radioButton.setText("" + odds.get(HeroNameData.getIdByHeroName("hero" + (index))));
            radioButton.setGraphic(new ImageView(new Image("images/" + HeroNameData.getIdByHeroName("Hero" + index) + ".jpg")));
            index++;
        }
    }
}
