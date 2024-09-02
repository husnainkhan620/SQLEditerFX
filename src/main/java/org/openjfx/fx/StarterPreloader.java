package org.openjfx.fx;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StarterPreloader extends Preloader{

	public Stage primaryStage;
	
	public Scene scene;

	public VBox rootPane;
	private Stage preloaderStage;
	
	 @Override
	   public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
		 
		  // System.out.println("State change "+stateChangeNotification.getType());
	      if (stateChangeNotification.getType() == Type.BEFORE_START) {
	         preloaderStage.hide();
	      }
	   }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.preloaderStage = primaryStage;
		  
		
		Image imageSQLite = new Image(getClass().getResourceAsStream("/graphics/duckDBLogo.png"));
		ImageView imageSQLitenode = new ImageView(imageSQLite);
		imageSQLitenode.setFitHeight(100);
		imageSQLitenode.setFitWidth(100);
		imageSQLitenode.setPreserveRatio(true);
		
		/*
		rootPane = new VBox();
		
		rootPane.getChildren().add(imageSQLitenode);
		scene = new Scene(rootPane,300,300);

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.show();
		*/
		
		VBox loading = new VBox(30);
	    loading.setMaxWidth(Region.USE_PREF_SIZE);
	    loading.setMaxHeight(Region.USE_PREF_SIZE);
	    loading.getChildren().add(imageSQLitenode);
	    loading.getChildren().add(new ProgressBar());
	    loading.getChildren().add(new Label("Please wait..."));
	 
	    BorderPane root = new BorderPane(loading);
	    Scene scene = new Scene(root);
	 
	    primaryStage.setWidth(300);
	    primaryStage.setHeight(300);
	    primaryStage.initStyle(StageStyle.UNDECORATED);
	    primaryStage.setScene(scene);
	    primaryStage.show();
		
	}

}
