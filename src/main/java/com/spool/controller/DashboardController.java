package com.spool.controller;


import com.spool.model.A;
import com.spool.model.D;
import com.spool.model.Denies;
import com.spool.model.DraftAdvantage;
import com.spool.model.Gpm;
import com.spool.model.Hero;
import com.spool.model.IH;
import com.spool.model.K;
import com.spool.model.Level;
import com.spool.model.LiveAdvantage;
import com.spool.model.Networth;
import com.spool.model.TeamFight;
import com.spool.model.TotalAdvantage;
import com.spool.model.Xpm;
import com.spool.service.Advantage;
import com.spool.service.Utility;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class DashboardController
implements Initializable {

    public AnchorPane parentPane;
    public AnchorPane dataArea;

    private final Utility utility = new Utility();
    private TableView table = new TableView();
    Label caption = new Label("");

    public void initialize(URL url, ResourceBundle rb) {

    }

    public void init(){
        XYChart.Series series3;
        NumberAxis xAxis;
        LineChart lineChart;
        XYChart.Series series;
        NumberAxis yAxis2;
        TotalAdvantage totalAdvantage;
        XYChart.Series series2;
        StackedBarChart stackedBarChart;
        XYChart.Series series1;
        XYChart.Series series7;
        ObservableList list;
        XYChart.Series series6;
        XYChart.Series series5;
        XYChart.Series series4;
        CategoryAxis yAxis;
        ImageGallery gallery = new ImageGallery();
        parentPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 15;");
        dataArea.setStyle("-fx-background-color: rgba(0, 0, 0, 0.0)");
        dataArea.setOpacity(0.8);
        if (DashBoard.chartTypeflag.equalsIgnoreCase("one")) {
            table.getItems().clear();
            table.getColumns().clear();
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            table.setPrefWidth(760.0);
            table.setPrefHeight(800.0);
            table.setOpacity(0.8);
            Hero hero = utility.getHeroData();
            Networth networth = utility.getNetworthData();
            K k = utility.getKData();
            D d = utility.getDData();
            A a = utility.getAData();
            IH ih = utility.getIHData();
            Denies denies = utility.getDeniesData();
            Level level = utility.getLevelData();
            Gpm gpm = utility.getGpmData();
            Xpm xpm = utility.getXpmData();
            TableColumn heroCol = new TableColumn("Hero");
            heroCol.setPrefWidth(80.0);
            heroCol.setCellValueFactory((Callback)new PropertyValueFactory("hero"));
            TableColumn networthCol = new TableColumn("Networth");
            networthCol.setCellValueFactory((Callback)new PropertyValueFactory("networth"));
            TableColumn kCol = new TableColumn("K");
            kCol.setCellValueFactory((Callback)new PropertyValueFactory("k"));
            TableColumn dCol = new TableColumn("D");
            dCol.setCellValueFactory((Callback)new PropertyValueFactory("d"));
            TableColumn aCol = new TableColumn("A");
            aCol.setCellValueFactory((Callback)new PropertyValueFactory("a"));
            TableColumn ihCol = new TableColumn("LH");
            ihCol.setCellValueFactory((Callback)new PropertyValueFactory("ih"));
            TableColumn deniesCol = new TableColumn("Denies");
            deniesCol.setCellValueFactory((Callback)new PropertyValueFactory("denies"));
            TableColumn levelCol = new TableColumn("Level");
            levelCol.setCellValueFactory((Callback)new PropertyValueFactory("level"));
            TableColumn gpmCol = new TableColumn("GPM");
            gpmCol.setCellValueFactory((Callback)new PropertyValueFactory("gpm"));
            TableColumn xpmCol = new TableColumn("XPM");
            xpmCol.setCellValueFactory((Callback)new PropertyValueFactory("xpm"));
            table.getColumns().addAll(new Object[]{heroCol, networthCol, kCol, dCol, aCol, ihCol, deniesCol, levelCol, gpmCol, xpmCol});
            ObservableList data = FXCollections.observableArrayList((Object[])new Pair[]{new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero1())) + ".jpg"))), networth.getnetworth1(), k.getk1(), d.getd1(), a.getA1(), ih.getih1(), denies.getdenies1(), level.getlevel1(), gpm.getgpm1(), xpm.getxpm1()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero2())) + ".jpg"))), networth.getnetworth2(), k.getk2(), d.getd2(), a.getA2(), ih.getih2(), denies.getdenies2(), level.getlevel2(), gpm.getgpm2(), xpm.getxpm2()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero3())) + ".jpg"))), networth.getnetworth3(), k.getk3(), d.getd3(), a.getA3(), ih.getih3(), denies.getdenies3(), level.getlevel3(), gpm.getgpm3(), xpm.getxpm3()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero4())) + ".jpg"))), networth.getnetworth4(), k.getk4(), d.getd4(), a.getA4(), ih.getih4(), denies.getdenies4(), level.getlevel4(), gpm.getgpm4(), xpm.getxpm4()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero5())) + ".jpg"))), networth.getnetworth5(), k.getk5(), d.getd5(), a.getA5(), ih.getih5(), denies.getdenies5(), level.getlevel5(), gpm.getgpm5(), xpm.getxpm5()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero6())) + ".jpg"))), networth.getnetworth6(), k.getk6(), d.getd6(), a.getA6(), ih.getih6(), denies.getdenies6(), level.getlevel6(), gpm.getgpm6(), xpm.getxpm6()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero7())) + ".jpg"))), networth.getnetworth7(), k.getk7(), d.getd7(), a.getA7(), ih.getih7(), denies.getdenies7(), level.getlevel7(), gpm.getgpm7(), xpm.getxpm7()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero8())) + ".jpg"))), networth.getnetworth8(), k.getk8(), d.getd8(), a.getA8(), ih.getih8(), denies.getdenies8(), level.getlevel8(), gpm.getgpm8(), xpm.getxpm8()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero9())) + ".jpg"))), networth.getnetworth9(), k.getk9(), d.getd9(), a.getA9(), ih.getih9(), denies.getdenies9(), level.getlevel9(), gpm.getgpm9(), xpm.getxpm9()), new Pair(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + String.valueOf(String.valueOf(hero.getHero10())) + ".jpg"))), networth.getnetworth10(), k.getk10(), d.getd10(), a.getA10(), ih.getih10(), denies.getdenies10(), level.getlevel10(), gpm.getgpm10(), xpm.getxpm10())});
            table.setItems(data);
            VBox vbox = new VBox();
            vbox.setSpacing(5.0);
            vbox.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
            vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-background-radius: 10;");
            vbox.getChildren().addAll(new Node[]{table});
            dataArea.getChildren().addAll(new Node[]{vbox});
        }
        if (DashBoard.chartTypeflag.equalsIgnoreCase("two")) {
            DraftAdvantage draftAdvantage = utility.getDraftAdvantageData();
            xAxis = new NumberAxis(0.0, 100.0, 50.0);
            yAxis = new CategoryAxis();
            stackedBarChart = new StackedBarChart((Axis)xAxis, (Axis)yAxis);
            xAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            xAxis.setTickLabelFont(new Font("Arial", 17.0));
            xAxis.setStyle("-fx-font-weight: bold;");
            yAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            yAxis.setTickLabelFont(new Font("Arial", 15.0));
            yAxis.setStyle("-fx-font-weight: bold;");
            stackedBarChart.setTitle("DRAFT ADVANTAGE");
            stackedBarChart.setAnimated(true);
            stackedBarChart.setHorizontalGridLinesVisible(false);
            stackedBarChart.setVerticalGridLinesVisible(false);
            stackedBarChart.setLayoutX(30.0);
            stackedBarChart.setPrefWidth(740.0);
            stackedBarChart.setOpacity(0.8);
            series1 = new XYChart.Series();
            series1.setName(draftAdvantage.getTeamName1());
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getDisables1(), (Object)"Disables"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getNukes1(), (Object)"Nukes"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getPushing1(), (Object)"Pushing"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getDefending1(), (Object)"Defending"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getSplitPushing1(), (Object)"Split Pushing"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getrDmg1(), (Object)"R Dmg"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getDurability1(), (Object)"Durability"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getMobility1(), (Object)"Mobility"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getHealing1(), (Object)"Healing"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getInitiation1(), (Object)"Initiation"));
            series1.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getCounters1(), (Object)"Counters"));
            series2 = new XYChart.Series();
            series2.setName(draftAdvantage.getTeamName2());
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getDisables2(), (Object)"Disables"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getNukes2(), (Object)"Nukes"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getPushing2(), (Object)"Pushing"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getDefending2(), (Object)"Defending"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getSplitPushing2(), (Object)"Split Pushing"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getrDmg2(), (Object)"R Dmg"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getDurability2(), (Object)"Durability"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getMobility2(), (Object)"Mobility"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getHealing2(), (Object)"Healing"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getInitiation2(), (Object)"Initiation"));
            series2.getData().add((Object)new XYChart.Data((Object)draftAdvantage.getCounters2(), (Object)"Counters"));
            stackedBarChart.getData().addAll((Object[])new XYChart.Series[]{series1, series2});
            int i = 0;
            while (i < stackedBarChart.getData().size()) {
                series = (XYChart.Series)stackedBarChart.getData().get(i);
                list = series.getData();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    Data data = (Data) it.next();
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY() - 10.0);
                            caption.setText(String.valueOf(String.valueOf(String.valueOf(data.getXValue()))) + "%");
                            caption.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffebcd;");
                        }
                    });
                }
                ++i;
            }
            dataArea.getChildren().addAll(new Node[]{stackedBarChart, caption});
        }
        if (DashBoard.chartTypeflag.equalsIgnoreCase("three")) {
            LiveAdvantage liveAdvantage = utility.getLiveAdvantageData();
            xAxis = new NumberAxis(0.0, 100.0, 50.0);
            yAxis = new CategoryAxis();
            stackedBarChart = new StackedBarChart((Axis)xAxis, (Axis)yAxis);
            xAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            xAxis.setTickLabelFont(new Font("Arial", 17.0));
            xAxis.setStyle("-fx-font-weight: bold;");
            yAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            yAxis.setTickLabelFont(new Font("Arial", 15.0));
            yAxis.setStyle("-fx-font-weight: bold;");
            stackedBarChart.setTitle("LIVE ADVANTAGE");
            stackedBarChart.setAnimated(true);
            stackedBarChart.setHorizontalGridLinesVisible(false);
            stackedBarChart.setVerticalGridLinesVisible(false);
            stackedBarChart.setLayoutX(30.0);
            stackedBarChart.setPrefWidth(740.0);
            stackedBarChart.setOpacity(0.8);
            series1 = new XYChart.Series();
            series1.setName(liveAdvantage.getTeamName1());
            series1.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getTeamFight1(), (Object)"Team Fight"));
            series1.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getPushing1(), (Object)"Pushing"));
            series1.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getDefending1(), (Object)"Defending"));
            series1.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getSplitPushing1(), (Object)"Split Pushing"));
            series2 = new XYChart.Series();
            series2.setName(liveAdvantage.getTeamName2());
            series2.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getTeamFight2(), (Object)"Team Fight"));
            series2.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getPushing2(), (Object)"Pushing"));
            series2.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getDefending2(), (Object)"Defending"));
            series2.getData().add((Object)new XYChart.Data((Object)liveAdvantage.getSplitPushing2(), (Object)"Split Pushing"));
            stackedBarChart.getData().addAll((Object[])new XYChart.Series[]{series1, series2});
            int i = 0;
            while (i < stackedBarChart.getData().size()) {
                series = (XYChart.Series)stackedBarChart.getData().get(i);
                list = series.getData();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    XYChart.Data data = (XYChart.Data) it.next();
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY() - 10.0);
                            caption.setText(String.valueOf(String.valueOf(String.valueOf(data.getXValue()))) + "%");
                            caption.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffebcd;");
                        }

                    });
                }
                ++i;
            }
            dataArea.getChildren().addAll(new Node[]{stackedBarChart, caption});
        }
        if (DashBoard.chartTypeflag.equalsIgnoreCase("four")) {
            totalAdvantage = utility.getTotalAdvantageData();
            xAxis = new NumberAxis(0.0, 100.0, 50.0);
            yAxis = new CategoryAxis();
            stackedBarChart = new StackedBarChart((Axis)xAxis, (Axis)yAxis);
            xAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            xAxis.setTickLabelFont(new Font("Arial", 17.0));
            xAxis.setStyle("-fx-font-weight: bold;");
            yAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            yAxis.setTickLabelFont(new Font("Arial", 15.0));
            yAxis.setStyle("-fx-font-weight: bold;");
            stackedBarChart.setTitle("TOTAL ADVANTAGE");
            stackedBarChart.setAnimated(true);
            stackedBarChart.setHorizontalGridLinesVisible(false);
            stackedBarChart.setVerticalGridLinesVisible(false);
            stackedBarChart.setLayoutX(30.0);
            stackedBarChart.setPrefWidth(740.0);
            stackedBarChart.setOpacity(0.8);
            series1 = new XYChart.Series();
            series1.setName(totalAdvantage.getTeamName1());
            series1.getData().add((Object)new XYChart.Data((Object)totalAdvantage.getDraftAdvantage1(), (Object)"Draft Advantage"));
            series1.getData().add((Object)new XYChart.Data((Object)totalAdvantage.getLiveAdvantage1(), (Object)"Live Advantage"));
            series2 = new XYChart.Series();
            series2.setName(totalAdvantage.getTeamName2());
            series2.getData().add((Object)new XYChart.Data((Object)totalAdvantage.getDraftAdvantage2(), (Object)"Draft Advantage"));
            series2.getData().add((Object)new XYChart.Data((Object)totalAdvantage.getLiveAdvantage2(), (Object)"Live Advantage"));
            stackedBarChart.getData().addAll((Object[])new XYChart.Series[]{series1, series2});
            int i = 0;
            while (i < stackedBarChart.getData().size()) {
                series = (XYChart.Series)stackedBarChart.getData().get(i);
                list = series.getData();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    XYChart.Data data = (XYChart.Data) it.next();
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY() - 10.0);
                                caption.setText(String.valueOf(String.valueOf(String.valueOf(data.getXValue()))) + "%");
                                caption.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffebcd;");
                            }
                    );
                }
                ++i;
            }
            dataArea.getChildren().addAll(new Node[]{stackedBarChart, caption});
        }
        if (DashBoard.chartTypeflag.equalsIgnoreCase("five")) {
            TeamFight teamFight = utility.getTeamFightData();
            xAxis = new NumberAxis(0.0, 100.0, 50.0);
            yAxis = new CategoryAxis();
            stackedBarChart = new StackedBarChart((Axis)xAxis, (Axis)yAxis);
            xAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            xAxis.setTickLabelFont(new Font("Arial", 17.0));
            xAxis.setStyle("-fx-font-weight: bold;");
            yAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            yAxis.setTickLabelFont(new Font("Arial", 15.0));
            yAxis.setStyle("-fx-font-weight: bold;");
            stackedBarChart.setTitle("TEAM FIGHT");
            stackedBarChart.setAnimated(true);
            stackedBarChart.setHorizontalGridLinesVisible(false);
            stackedBarChart.setVerticalGridLinesVisible(false);
            stackedBarChart.setLayoutX(30.0);
            stackedBarChart.setPrefWidth(740.0);
            stackedBarChart.setOpacity(0.8);
            series1 = new XYChart.Series();
            series1.setName(teamFight.getTeamName1());
            series1.getData().add((Object)new XYChart.Data((Object)teamFight.getDisables1(), (Object)"Disables"));
            series1.getData().add((Object)new XYChart.Data((Object)teamFight.getNukeDmg1(), (Object)"Nuke Dmg"));
            series1.getData().add((Object)new XYChart.Data((Object)teamFight.getrDmg1(), (Object)"R Dmg"));
            series1.getData().add((Object)new XYChart.Data((Object)teamFight.getDurability1(), (Object)"Durability"));
            series1.getData().add((Object)new XYChart.Data((Object)teamFight.getInitiation1(), (Object)"Initiation"));
            series1.getData().add((Object)new XYChart.Data((Object)teamFight.getHealing1(), (Object)"Healing"));
            series1.getData().add((Object)new XYChart.Data((Object)teamFight.getAura1(), (Object)"Aura"));
            series2 = new XYChart.Series();
            series2.setName(teamFight.getTeamName2());
            series2.getData().add((Object)new XYChart.Data((Object)teamFight.getDisables2(), (Object)"Disables"));
            series2.getData().add((Object)new XYChart.Data((Object)teamFight.getNukeDmg2(), (Object)"Nuke Dmg"));
            series2.getData().add((Object)new XYChart.Data((Object)teamFight.getrDmg2(), (Object)"R Dmg"));
            series2.getData().add((Object)new XYChart.Data((Object)teamFight.getDurability2(), (Object)"Durability"));
            series2.getData().add((Object)new XYChart.Data((Object)teamFight.getInitiation2(), (Object)"Initiation"));
            series2.getData().add((Object)new XYChart.Data((Object)teamFight.getHealing2(), (Object)"Healing"));
            series2.getData().add((Object)new XYChart.Data((Object)teamFight.getAura2(), (Object)"Aura"));
            stackedBarChart.getData().addAll((Object[])new XYChart.Series[]{series1, series2});
            int i = 0;
            while (i < stackedBarChart.getData().size()) {
                series = (XYChart.Series)stackedBarChart.getData().get(i);
                list = series.getData();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    XYChart.Data data = (XYChart.Data) it.next();
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY() - 10.0);
                                caption.setText(String.valueOf(String.valueOf(String.valueOf(data.getXValue()))) + "%");
                                caption.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffebcd;");
                            }
                    );
                }
                ++i;
            }
            dataArea.getChildren().addAll(new Node[]{stackedBarChart, caption});
        }
        if (DashBoard.chartTypeflag.equalsIgnoreCase("eight")) {
            Advantage advantage;
            advantage = utility.getAdvantageData();
            xAxis = new NumberAxis(0.0, 100.0, 10.0);
            yAxis2 = new NumberAxis(-100.0, 100.0, 100.0);
            lineChart = new LineChart((Axis)xAxis, (Axis)yAxis2);
            xAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            xAxis.setTickLabelFont(new Font("Arial", 17.0));
            xAxis.setStyle("-fx-font-weight: bold;");
            yAxis2.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            yAxis2.setTickLabelFont(new Font("Arial", 15.0));
            yAxis2.setStyle("-fx-font-weight: bold;");
            lineChart.setTitle("WIN CHANCE");
            lineChart.setAnimated(true);
            lineChart.setHorizontalGridLinesVisible(false);
            lineChart.setVerticalGridLinesVisible(false);
            lineChart.setLayoutX(30.0);
            lineChart.setPrefWidth(740.0);
            lineChart.setOpacity(0.8);
            series1 = new XYChart.Series();
            lineChart.setStyle("-fx-padding: 0.0px;");
            lineChart.setStyle("-fx-background-radius: 0.0px;");
            series1.setName(advantage.getTeamName1());
            int i = 0;
            while (i < advantage.getHp().length) {
                series1.getData().add((Object)new XYChart.Data((Object)advantage.getHo()[i], (Object)advantage.getHp()[i]));
                ++i;
            }
            lineChart.getData().addAll((Object[])new XYChart.Series[]{series1});
            i = 0;
            while (i < lineChart.getData().size()) {
                series = (XYChart.Series)lineChart.getData().get(i);
                list = series.getData();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    XYChart.Data data = (XYChart.Data) it.next();
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY() - 10.0);
                                caption.setText(String.valueOf(String.valueOf(String.valueOf(data.getXValue()))) + "%");
                                caption.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffebcd;");
                            }
                    );
                }
                ++i;
            }
            dataArea.getChildren().addAll(new Node[]{lineChart, caption});
        }
        if (DashBoard.chartTypeflag.equalsIgnoreCase("nine")) {
            Advantage totaladvan = utility.getAdvantageData2();
            xAxis = new NumberAxis(0.0, 100.0, 10.0);
            yAxis2 = new NumberAxis(-100.0, 100.0, 100.0);
            lineChart = new LineChart((Axis)xAxis, (Axis)yAxis2);
            xAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            xAxis.setTickLabelFont(new Font("Arial", 17.0));
            xAxis.setStyle("-fx-font-weight: bold;");
            yAxis2.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            yAxis2.setTickLabelFont(new Font("Arial", 15.0));
            yAxis2.setStyle("-fx-font-weight: bold;");
            lineChart.setTitle("LIVE ADVANTAGE");
            lineChart.setAnimated(true);
            lineChart.setHorizontalGridLinesVisible(false);
            lineChart.setVerticalGridLinesVisible(false);
            lineChart.setLayoutX(30.0);
            lineChart.setPrefWidth(740.0);
            lineChart.setOpacity(0.8);
            series3 = new XYChart.Series();
            series3.setName(totaladvan.getTeamName3());
            int i = 0;
            while (i < totaladvan.getHq().length) {
                series3.getData().add((Object)new XYChart.Data((Object)totaladvan.getHo()[i], (Object)totaladvan.getHq()[i]));
                ++i;
            }
            series4 = new XYChart.Series();
            series4.setName(totaladvan.getTeamName4());
            i = 0;
            while (i < totaladvan.getHr().length) {
                series4.getData().add((Object)new XYChart.Data((Object)totaladvan.getHo()[i], (Object)totaladvan.getHr()[i]));
                ++i;
            }
            series5 = new XYChart.Series();
            series5.setName(totaladvan.getTeamName5());
            i = 0;
            while (i < totaladvan.getHs().length) {
                series5.getData().add((Object)new XYChart.Data((Object)totaladvan.getHo()[i], (Object)totaladvan.getHs()[i]));
                ++i;
            }
            series6 = new XYChart.Series();
            series6.setName(totaladvan.getTeamName6());
            i = 0;
            while (i < totaladvan.getHt().length) {
                series6.getData().add((Object)new XYChart.Data((Object)totaladvan.getHo()[i], (Object)totaladvan.getHt()[i]));
                ++i;
            }
            series7 = new XYChart.Series();
            series7.setName(totaladvan.getTeamName7());
            i = 0;
            while (i < totaladvan.getHu().length) {
                series7.getData().add((Object)new XYChart.Data((Object)totaladvan.getHo()[i], (Object)totaladvan.getHu()[i]));
                ++i;
            }
            lineChart.getData().addAll((Object[])new XYChart.Series[]{series3, series4, series5, series6, series7});
            i = 0;
            while (i < lineChart.getData().size()) {
                series = (XYChart.Series)lineChart.getData().get(i);
                list = series.getData();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    XYChart.Data data = (XYChart.Data) it.next();
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY() - 10.0);
                                caption.setText(String.valueOf(String.valueOf(String.valueOf(data.getXValue()))) + "%");
                                caption.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffebcd;");
                            }
                    );
                }
                ++i;
            }
            dataArea.getChildren().addAll(new Node[]{lineChart, caption});
        }
        if (DashBoard.chartTypeflag.equalsIgnoreCase("ten")) {
            Advantage advantage3 = utility.getAdvantageData3();
            xAxis = new NumberAxis(0.0, 100.0, 10.0);
            yAxis2 = new NumberAxis(-100.0, 100.0, 100.0);
            lineChart = new LineChart((Axis)xAxis, (Axis)yAxis2);
            xAxis.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            xAxis.setTickLabelFont(new Font("Arial", 17.0));
            xAxis.setStyle("-fx-font-weight: bold;");
            yAxis2.setTickLabelFill((Paint)Color.BLANCHEDALMOND);
            yAxis2.setTickLabelFont(new Font("Arial", 15.0));
            yAxis2.setStyle("-fx-font-weight: bold;");
            lineChart.setTitle("TEAM FIGHT");
            lineChart.setAnimated(true);
            lineChart.setHorizontalGridLinesVisible(false);
            lineChart.setVerticalGridLinesVisible(false);
            lineChart.setLayoutX(30.0);
            lineChart.setPrefWidth(740.0);
            lineChart.setOpacity(0.8);
            series1 = new XYChart.Series();
            series1.setName(advantage3.getTeamName1());
            int i = 0;
            while (i < advantage3.getHv().length) {
                series1.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getHv()[i]));
                ++i;
            }
            series2 = new XYChart.Series();
            series2.setName(advantage3.getTeamName2());
            i = 0;
            while (i < advantage3.getHw().length) {
                series2.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getHw()[i]));
                ++i;
            }
            series3 = new XYChart.Series();
            series3.setName(advantage3.getTeamName3());
            i = 0;
            while (i < advantage3.getHx().length) {
                series3.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getHx()[i]));
                ++i;
            }
            series4 = new XYChart.Series();
            series4.setName(advantage3.getTeamName4());
            i = 0;
            while (i < advantage3.getHy().length) {
                series4.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getHy()[i]));
                ++i;
            }
            series5 = new XYChart.Series();
            series5.setName(advantage3.getTeamName5());
            i = 0;
            while (i < advantage3.getHz().length) {
                series5.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getHz()[i]));
                ++i;
            }
            series6 = new XYChart.Series();
            series6.setName(advantage3.getTeamName6());
            i = 0;
            while (i < advantage3.getIa().length) {
                series6.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getIa()[i]));
                ++i;
            }
            series7 = new XYChart.Series();
            series7.setName(advantage3.getTeamName7());
            i = 0;
            while (i < advantage3.getIb().length) {
                series7.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getIb()[i]));
                ++i;
            }
            XYChart.Series series8 = new XYChart.Series();
            series8.setName(advantage3.getTeamName8());
            i = 0;
            while (i < advantage3.getIc().length) {
                series8.getData().add((Object)new XYChart.Data((Object)advantage3.getHo()[i], (Object)advantage3.getIc()[i]));
                ++i;
            }
            lineChart.getData().addAll((Object[])new XYChart.Series[]{series1, series2, series3, series4, series5, series6, series7, series8});
            i = 0;
            while (i < lineChart.getData().size()) {
                series = (XYChart.Series)lineChart.getData().get(i);
                list = series.getData();
                for (Iterator it = list.iterator(); it.hasNext();) {
                    XYChart.Data data = (XYChart.Data) it.next();
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                                caption.setTranslateX(e.getSceneX());
                                caption.setTranslateY(e.getSceneY() - 10.0);
                                caption.setText(String.valueOf(String.valueOf(String.valueOf(data.getXValue()))) + "%");
                                caption.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffebcd;");
                            }
                    );
                }
                ++i;
            }
            dataArea.getChildren().addAll(new Node[]{lineChart, caption});
        }
    }

    public static class Pair {
        private ImageView hero;
        private int networth;
        private int k;
        private int d;
        private int a;
        private int ih;
        private int denies;
        private int level;
        private int gpm;
        private int xpm;

        public Pair(ImageView hero, int networth, int k, int d, int a, int ih, int denies, int level, int gpm, int xpm) {
            hero = hero;
            networth = networth;
            k = k;
            d = d;
            a = a;
            ih = ih;
            denies = denies;
            level = level;
            gpm = gpm;
            xpm = xpm;
        }

        public ImageView getHero() {
            return hero;
        }

        public void setHero(ImageView hero) {
            hero = hero;
        }

        public int getNetworth() {
            return networth;
        }

        public void setNetworth(int networth) {
            networth = networth;
        }

        public int getK() {
            return k;
        }

        public void setK(int k) {
            k = k;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            d = d;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            a = a;
        }

        public int getIh() {
            return ih;
        }

        public void setIh(int ih) {
            ih = ih;
        }

        public int getDenies() {
            return denies;
        }

        public void setDenies(int denies) {
            denies = denies;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            level = level;
        }

        public int getGpm() {
            return gpm;
        }

        public void setGpm(int gpm) {
            gpm = gpm;
        }

        public int getXpm() {
            return xpm;
        }

        public void setXpm(int xpm) {
            xpm = xpm;
        }
    }

}