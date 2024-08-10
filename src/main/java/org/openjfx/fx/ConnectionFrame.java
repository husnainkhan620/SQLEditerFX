package org.openjfx.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConnectionFrame extends Application{

	public static void main(String[] args) {
		   launch(ConnectionFrame.class, args);

	}

	  @Override
	    public void start(Stage stage) {
		  
		  BorderPane border = new BorderPane();
		  
		  HBox hbox = addHBox();
		  border.setTop(hbox);
		  border.setLeft(addVBox());   
		  
		// Add a stack to the HBox in the top region
	        addStackPane(hbox);  
	        
	        border.setCenter(addFlowPane());
	      
	      HBox hboxBottom = addHBox();
	      border.setBottom(hboxBottom);
	       
	      Button maximizeButton = new Button();

	      
	      ImageView  maximizeImage = new ImageView(
                  new Image(getClass().getResourceAsStream("/images/maximize-button.png")));
	      maximizeButton.setGraphic(maximizeButton);
	      
		  Scene scene = new Scene(border,600,600);
		  // Add a style sheet to the scene        
		          scene.getStylesheets().add(getClass().getResource("/layoutstyles.css").toExternalForm());
		          stage.setScene(scene);
		          stage.setTitle("Layout Sample");
		          stage.show();
	  }
	  
	  private HBox addHBox() {

	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #336699;");
	// Use style class to set properties previously set above (with some changes)      
	        hbox.getStyleClass().add("hbox");

	        Button buttonCurrent = new Button("Current");
	        buttonCurrent.setPrefSize(100, 20);

	        Button buttonProjected = new Button("Projected");
	        buttonProjected.setPrefSize(100, 20);
	        
	        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
	        
	        return hbox;
	    }
	  
	  private VBox addVBox() {
	        
	        VBox vbox = new VBox();
//	        vbox.setPadding(new Insets(10)); // Set all sides to 10
//	        vbox.setSpacing(8);              // Gap between nodes
	// Use style classes to set properties previously set above        
	        vbox.getStyleClass().addAll("pane", "vbox");

	        Text title = new Text("Data");
	        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	        vbox.getChildren().add(title);
	        
	        Hyperlink options[] = new Hyperlink[] {
	            new Hyperlink("Sales"),
	            new Hyperlink("Marketing"),
	            new Hyperlink("Distribution"),
	            new Hyperlink("Costs")};

	        for (int i=0; i<4; i++) {
	            // Add offset to left side to indent from title
	            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
	            vbox.getChildren().add(options[i]);
	        }
	        
	        return vbox;
	    }
	  
	  private void addStackPane(HBox hb) {

	        StackPane stack = new StackPane();
	        Rectangle helpIcon = new Rectangle(30.0, 25.0);
	        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.web("#4977A3")),
	            new Stop(0.5, Color.web("#B0C6DA")),
	            new Stop(1,Color.web("#9CB6CF")),}));
	        helpIcon.setStroke(Color.web("#D0E6FA"));
	        helpIcon.setArcHeight(3.5);
	        helpIcon.setArcWidth(3.5);
	        
	        Text helpText = new Text("?");
	        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	        helpText.setFill(Color.WHITE);
	        helpText.setStroke(Color.web("#7080A0")); 

	        stack.getChildren().addAll(helpIcon, helpText);
	        stack.setAlignment(Pos.CENTER_RIGHT);
	        // Add offset to right for question mark to compensate for RIGHT 
	        // alignment of all nodes
	        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));
	        
	        hb.getChildren().add(stack);
	        HBox.setHgrow(stack, Priority.ALWAYS);
	                
	    }

	  private FlowPane addFlowPane() {

	        FlowPane flow = new FlowPane();
	        flow.setPadding(new Insets(5, 0, 5, 0));
	        flow.setVgap(10);
	        flow.setHgap(10);
//	        flow.setStyle("-fx-background-color: DAE6F3;");
	// Use style classes to set properties previously set above        
	    //    flow.getStyleClass().addAll("pane", "flow-tile");
	        flow.setPrefWrapLength(170); // preferred width allows for two columns

	        ImageView pages[] = new ImageView[8];
	        for (int i=0; i<8; i++) {
	            pages[i] = new ImageView(
	                    new Image(getClass().getResourceAsStream(
	                    "/graphics/chart_"+(i+1)+".png")));
	            flow.getChildren().add(pages[i]);
	        }

	        BackgroundFill background_fill = new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ); 
	        // create Background 
	        Background background = new Background(background_fill); 
	        flow.setBackground(background);
	        return flow;
	    }
}
