package org.openjfx.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectToDatabase extends Application{

	public static void main(String[] args) {
		   launch(ConnectToDatabase.class, args);

	}
	
	  @Override
	   public void start(Stage stage) {
		  
		Stage connectionStage = new Stage();
		BorderPane mainPopUpborderPane = new BorderPane();
		 
		HBox topHbox = addTopHBox();
		
		//GridPane centerGridPane = addCenterGridPane();
		
		SplitPane centerSplitPane = addCenterSplitPane();
		
		HBox bottomHbox = addBottomHBox();
		
		
		mainPopUpborderPane.setTop(topHbox);
		
		mainPopUpborderPane.setCenter(centerSplitPane);;
		mainPopUpborderPane.setBottom(bottomHbox);
		
		Scene scene = new Scene(mainPopUpborderPane,500,500);  
		scene.getStylesheets().add(getClass().getResource("/layoutstyles.css").toExternalForm());  
		
		connectionStage.initModality(Modality.APPLICATION_MODAL);
		connectionStage.setTitle("No DataBase Connection ");   
		connectionStage.setScene(scene);
		 // Commenting the below for now
		 //  connectionStage.initStyle(StageStyle.TRANSPARENT);  // remove the top head of the scene
		    
		connectionStage.show();

	}

	 private HBox addTopHBox() {

		 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #336699;");
	// Use style class to set properties previously set above (with some changes)      
	        hbox.getStyleClass().add("hbox");
	        
	        Text connectToDatabseText = new Text("Connect to a Database");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	    }
	 
	 
	 private SplitPane addCenterSplitPane() {
		 
		 SplitPane splitPaneCenter = new SplitPane();
		
		 VBox topControlVBox = new VBox();
		 
		 HBox topControlHBox = new HBox();
		 topControlHBox.setPadding(new Insets(10,0,10,0));   // top  right bottom left
	     Text connectToDatabseText = new Text("Existing Connections");
	     connectToDatabseText.setFont(Font.font("Verdana",20));
	     connectToDatabseText.setFill(Color.BLACK);
	     connectToDatabseText.setTextAlignment(TextAlignment.CENTER);
	     topControlHBox.getChildren().addAll(connectToDatabseText);
	     
	     ScrollPane scrollPaneTop = new ScrollPane();
	     scrollPaneTop.setFitToWidth(true);
	     scrollPaneTop.setFitToHeight(true);
	     
	     FlowPane openConnectionsFlowPane = new FlowPane();
	     openConnectionsFlowPane.setPadding(new Insets(10, 0, 10, 10));
	     openConnectionsFlowPane.setVgap(10);
	     openConnectionsFlowPane.setHgap(20);
	     
	     ImageView pages[] = new ImageView[8];
	     for (int i=0; i<8; i++) {
	         pages[i] = new ImageView(
	         new Image(getClass().getResourceAsStream(
	                    "/graphics/chart_"+(i+1)+".png")));
	         openConnectionsFlowPane.getChildren().add(pages[i]);
	     }
	      //pages[] = new ImageView[8];
	     
	     for (int i=0; i<8; i++) {
	         pages[i] = new ImageView(
	         new Image(getClass().getResourceAsStream(
	                    "/graphics/chart_"+(i+1)+".png")));
	         openConnectionsFlowPane.getChildren().add(pages[i]);
	     }
			
	     BackgroundFill background_fill = new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ); 
	     // create Background 
	     Background background = new Background(background_fill); 
	     openConnectionsFlowPane.setBackground(background);
	  
	     scrollPaneTop.setContent(openConnectionsFlowPane);
	     
	     topControlVBox.getChildren().add(topControlHBox);
	     topControlVBox.getChildren().add(scrollPaneTop);
	     
	     HBox bottomControl = new HBox();
	     bottomControl.setPadding(new Insets(10,0,0,0));   // top  right bottom left
	     Text newConnection = new Text("New Database");
	     newConnection.setFont(Font.font("Verdana",20));
	     newConnection.setFill(Color.BLACK);
	     bottomControl.getChildren().addAll(newConnection);

	        
	     splitPaneCenter.setDividerPosition(0, 0.7);
	     
	     splitPaneCenter.setOrientation(Orientation.VERTICAL);
	     
	     splitPaneCenter.getItems().addAll(topControlVBox,bottomControl);

	     
		 return splitPaneCenter;
	 }
	 
	 private GridPane addCenterGridPane() {
		
		 
		 GridPane gridPane = new GridPane();
		 
		 //Setting the padding  
	     gridPane.setPadding(new Insets(50, 0, 0, 100));  // top  right bottom left
	      
	     //Setting the vertical and horizontal gaps between the columns 
	     
	     gridPane.setVgap(50); 
	     gridPane.setHgap(0);
	      
	     HBox hboxFirst = new HBox();
	     hboxFirst.getStyleClass().add("hboxFirst");
	     Text connectToDatabseText = new Text("Connect to existing Databases");
	     connectToDatabseText.setFont(Font.font("Verdana",20));
	     connectToDatabseText.setFill(Color.WHITE);
	     hboxFirst.getChildren().addAll(connectToDatabseText);
	     
	     HBox hboxSecond = new HBox();
	     hboxSecond.getStyleClass().add("hboxFirst");
	     Text newConnection = new Text("Connect to a new Database");
	     newConnection.setFont(Font.font("Verdana",20));
	     newConnection.setFill(Color.WHITE);
	     hboxSecond.getChildren().addAll(newConnection);
	        
	     
	     gridPane.add(hboxFirst,0,0);   // row 0 column 0
	     gridPane.add(hboxSecond,0,1);
	     
		 return gridPane;
		 
	 }
	 
	 private HBox addBottomHBox() {

		 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #334000;");
	// Use style class to set properties previously set above (with some changes)      
	        //hbox.getStyleClass().add("hbox");
	        
	        hbox.getStyleClass().add("hbox");

	        
	        return hbox;
	    }
	 
	 
}
