package fxsampler;

import javafx.scene.Node;
import javafx.stage.Stage;

public interface Sample {
    public String getSampleName();

    public String getSampleDescription();

    public String getProjectName();

    public String getProjectVersion();

    public Node getPanel(Stage var1);

    public Node getControlPanel();

    public double getControlPanelDividerPosition();

    public String getJavaDocURL();

    public String getControlStylesheetURL();

    public String getSampleSourceURL();

    public boolean isVisible();
}