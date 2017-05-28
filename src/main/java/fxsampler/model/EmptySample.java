package fxsampler.model;

import fxsampler.Sample;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EmptySample
  implements Sample
{
  private final String name;
  
  public EmptySample(String name)
  {
    this.name = name;
  }
  
  public String getSampleName()
  {
    return this.name;
  }
  
  public String getSampleDescription()
  {
    return null;
  }
  
  public String getProjectName()
  {
    return null;
  }
  
  public String getProjectVersion()
  {
    return null;
  }
  
  public Node getPanel(Stage stage)
  {
    return null;
  }
  
  public String getJavaDocURL()
  {
    return null;
  }
  
  public String getSampleSourceURL()
  {
    return null;
  }
  
  public boolean isVisible()
  {
    return true;
  }
  
  public Node getControlPanel()
  {
    return null;
  }
  
  public double getControlPanelDividerPosition()
  {
    return 0.6D;
  }
  
  public String getControlStylesheetURL()
  {
    return null;
  }
}
