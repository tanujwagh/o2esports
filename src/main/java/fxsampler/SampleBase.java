package fxsampler;


import java.util.ServiceLoader;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;



public abstract class SampleBase
extends Application
implements Sample {
    public void start(Stage primaryStage) throws Exception {
        ServiceLoader<FXSamplerConfiguration> configurationServiceLoader = ServiceLoader.load(FXSamplerConfiguration.class);
        primaryStage.setTitle(this.getSampleName());
        Scene scene = new Scene((Parent)SampleBase.buildSample(this, primaryStage), 800.0, 800.0);
        scene.getStylesheets().add(SampleBase.class.getClassLoader().getResource("css/fxsampler.css").toExternalForm());
        for (FXSamplerConfiguration fxsamplerConfiguration : configurationServiceLoader) {
            String stylesheet = fxsamplerConfiguration.getSceneStylesheet();
            if (stylesheet == null) continue;
            scene.getStylesheets().add(stylesheet);
        }
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("images/logo.png")));
        primaryStage.show();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public Node getControlPanel() {
        return null;
    }

    @Override
    public double getControlPanelDividerPosition() {
        return 0.6;
    }

    @Override
    public String getSampleDescription() {
        return "";
    }

    @Override
    public String getProjectName() {
        return "ControlsFX";
    }

    public static Node buildSample(Sample sample, Stage stage) {
        String description;
        SplitPane splitPane = new SplitPane();
        Node samplePanel = sample.getPanel(stage);
        Node controlPanel = sample.getControlPanel();
        splitPane.setDividerPosition(0, sample.getControlPanelDividerPosition());
        if (samplePanel != null) {
            splitPane.getItems().add(samplePanel);
        }
        VBox rightPanel = new VBox();
        rightPanel.getStyleClass().add("right-panel");
        rightPanel.setMaxHeight(Double.MAX_VALUE);
        boolean addRightPanel = false;
        Label sampleName = new Label(sample.getSampleName());
        sampleName.getStyleClass().add("sample-name");
        rightPanel.getChildren().add(sampleName);
        String version = sample.getProjectVersion();
        version = version == null ? "" : (version.equals("@version@") ? "" : " " + version.trim());
        String projectName = sample.getProjectName() + version;
        if (projectName != null && !projectName.isEmpty()) {
            Label projectNameTitleLabel = new Label("Project: ");
            projectNameTitleLabel.getStyleClass().add("project-name-title");
            Label projectNameLabel = new Label(projectName);
            projectNameLabel.getStyleClass().add("project-name");
            projectNameLabel.setWrapText(true);
            TextFlow textFlow = new TextFlow(new Node[]{projectNameTitleLabel, projectNameLabel});
            rightPanel.getChildren().add(textFlow);
        }
        if ((description = sample.getSampleDescription()) != null && !description.isEmpty()) {
            Label descriptionLabel = new Label(description);
            descriptionLabel.getStyleClass().add("description");
            descriptionLabel.setWrapText(true);
            rightPanel.getChildren().add(descriptionLabel);
            addRightPanel = true;
        }
        if (controlPanel != null) {
            rightPanel.getChildren().add(new Separator());
            controlPanel.getStyleClass().add("control-panel");
            rightPanel.getChildren().add(controlPanel);
            VBox.setVgrow((Node)controlPanel, (Priority)Priority.ALWAYS);
            addRightPanel = true;
        }
        if (addRightPanel) {
            ScrollPane scrollPane = new ScrollPane((Node)rightPanel);
            scrollPane.setMaxHeight(Double.MAX_VALUE);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            SplitPane.setResizableWithParent((Node)scrollPane, (Boolean)false);
            splitPane.getItems().add(scrollPane);
        }
        return splitPane;
    }
}