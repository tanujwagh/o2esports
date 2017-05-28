package com.spool.controller;

import com.spool.model.ImageBoxModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ImageGallery
{
  public ImageGallery() {}
  
  public Stage createImageBox()
  {
    ImageBoxModel imageBoxModel = new com.spool.service.Utility().getImageBoxData();
    Pane pane = new Pane();
    pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 15;");
    
    Scene scene = new Scene(pane, 800.0D, 460.0D);
    scene.setFill(Color.TRANSPARENT);
    Font titlefont = new Font("Cambria", 18.0D);
    Font.font("Cambria", javafx.scene.text.FontWeight.BOLD, 20.0D);
    Font font = new Font("Cambria", 18.0D);
    Font.font("Cambria", javafx.scene.text.FontWeight.BOLD, 18.0D);
    Label label = new Label();
    label.setFont(titlefont);
    label.setTranslateY(10.0D);
    label.setTranslateX(300.0D);
    label.setTextFill(Color.WHITE);
    label.setText("Counter Pick Suggestion");
    
    pane.getChildren().addAll(new Node[] { label });
    label = new Label();
    label.setFont(font);
    label.setTranslateY(50.0D);
    label.setTranslateX(30.0D);
    label.setTextFill(Color.WHITE);
    label.setText("Top 5 heroes that " + imageBoxModel.getTeamName1() + " should pick:");
    pane.getChildren().addAll(new Node[] { label });
    
    ImageView imageView = createImageView(imageBoxModel.getImage1(), 90.0D, 50.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage2(), 90.0D, 150.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage3(), 90.0D, 250.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage4(), 90.0D, 350.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage5(), 90.0D, 450.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    
    label = new Label();
    label.setFont(font);
    label.setTranslateY(170.0D);
    label.setTranslateX(30.0D);
    label.setTextFill(Color.WHITE);
    label.setText("Top 5 heroes that " + imageBoxModel.getTeamName2() + " should pick:");
    pane.getChildren().addAll(new Node[] { label });
    imageView = createImageView(imageBoxModel.getImage6(), 210.0D, 50.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage7(), 210.0D, 150.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage8(), 210.0D, 250.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage9(), 210.0D, 350.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    imageView = createImageView(imageBoxModel.getImage10(), 210.0D, 450.0D);
    if (imageView != null) {
      pane.getChildren().addAll(new Node[] { imageView });
    }
    
    Stage stage = new Stage();
    stage.initStyle(javafx.stage.StageStyle.TRANSPARENT);
    stage.setAlwaysOnTop(true);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setOpacity(0.8D);
    stage.setTitle("Counter Pick Suggestion");
    return stage;
  }
  
  private ImageView createImageView(String imageName, double Y, double X) {
    if ((imageName == null) || (imageName.trim().equals(""))) {
      return null;
    }
    



    ImageView imageView = null;
    Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/" + imageName + ".jpg"));
    imageView = new ImageView(image);
    imageView.setX(X);
    imageView.setY(Y);
    
    return imageView;
  }
}