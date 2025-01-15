//package com.openfx.erdiagram;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.control.TitledPane;
//import javafx.scene.control.Tooltip;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class TitledPane extends ERDiagramApplication {
//	private Lines line;	
//	public void start(Stage primaryStage) throws Exception {
//		
//		
//		
//		TitledPane tabletitledPane = new TitledPane();
//		tabletitledPane.setPrefWidth(150);
//		tabletitledPane.setText("actor");
//		tabletitledPane.alignmentProperty().setValue(Pos.CENTER);
//		tabletitledPane.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
//		VBox titledPaneVBox = new VBox();
//		titledPaneVBox.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
//		titledPaneVBox.setSpacing(3);
//		titledPaneVBox.getChildren().add(new Label("actorId"));
//		titledPaneVBox.getChildren().add(new Label("actorName"));
//		titledPaneVBox.getChildren().add(new Label("actorCity"));
//		titledPaneVBox.getChildren().add(new Label("actorId"));
//		titledPaneVBox.getChildren().add(new Label("actorName"));
//		titledPaneVBox.getChildren().add(new Label("actorCity"));
//		tabletitledPane.setContent(titledPaneVBox);
//		tabletitledPane.setCollapsible(false);
//		tabletitledPane.setLayoutX(100);   // used to place it a particular X location on screen
//		tabletitledPane.setLayoutY(100);   // used to place it a particular Y location on screen
//		enableDragAndDrop(tabletitledPane, mainPane);
//		mainPane.getChildren().add(tabletitledPane);
//
//		TitledPane tabletitled1Pane = new TitledPane();
//		tabletitled1Pane.setPrefWidth(150);
//		tabletitled1Pane.setText("address");
//		tabletitled1Pane.alignmentProperty().setValue(Pos.CENTER);
//		tabletitled1Pane.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
//		VBox titledPane1VBox = new VBox();
//		titledPane1VBox.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
//		titledPane1VBox.setSpacing(3);
//		titledPane1VBox.getChildren().add(new Label("address_id"));
//		titledPane1VBox.getChildren().add(new Label("address"));
//		titledPane1VBox.getChildren().add(new Label("address2"));
//		titledPane1VBox.getChildren().add(new Label("district"));
//		titledPane1VBox.getChildren().add(new Label("city_id"));
//		titledPane1VBox.getChildren().add(new Label("postal_code"));
//		titledPane1VBox.getChildren().add(new Label("phone"));
//		titledPane1VBox.getChildren().add(new Label("location"));
//		titledPane1VBox.getChildren().add(new Label("last_update"));
//		tabletitled1Pane.setContent(titledPane1VBox);
//		tabletitled1Pane.setCollapsible(false);
//		tabletitled1Pane.setLayoutX(300);   // used to place it a particular X location on screen
//		tabletitled1Pane.setLayoutY(300); // used to place it a particular Y location on screen
//		enableDragAndDrop(tabletitled1Pane, mainPane);
//
//		mainPane.getChildren().add(tabletitled1Pane);
//	}
//}




//package com.openfx.erdiagram;
//
//import java.awt.Dimension;
//import java.awt.Toolkit;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.sun.javafx.geom.Point2D;
//
//import javafx.application.Application;
//import javafx.beans.binding.Bindings;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TitledPane;
//import javafx.scene.control.Tooltip;
//import javafx.scene.effect.BlurType;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.input.ScrollEvent;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Polygon;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.transform.Scale;
//import javafx.stage.Stage;
//
//public class ERDiagramApplication extends Application{
//
//	public static void main(String[] args) {
//		   launch(ERDiagramApplication.class, args);
//	}
//	
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//
//		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.print(size.getHeight() + "<--->" +size.getWidth());
//		
//		Pane mainPane = new Pane();
//		mainPane.setPrefSize(size.getWidth(),size.getHeight());	
	
		// Diagram using TitledPane
///	
//		  TitledPane addressPane = createTitledPane(
//			        "address",
//			        "This is a table in RDBD\nThis is tooltip for table",
//			        300, 300,
//			        mainPane,
//			        "address_id", "address", "address2", "district", "city_id", "postal_code", "phone", "location", "last_update"
//			    );
//		  
//		  TitledPane addressPane1 = createTitledPane(
//				  "address",
//				  "This is a table in RDBD\nThis is tooltip for table",
//				  700, 700,
//				  mainPane,
//				  "address_id", "address", "address2", "district", "city_id", "postal_code", "phone", "location", "last_update"
//				  );
//
//			    // Add the titled pane to the mainPane
//			    mainPane.getChildren().add(addressPane);
		
		// Diagram using TitledPane
//				TitledPane tabletitledPane = new TitledPane();
//				tabletitledPane.setPrefWidth(150);
//				tabletitledPane.setText("actor");
//				tabletitledPane.alignmentProperty().setValue(Pos.CENTER);
//				tabletitledPane.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
//				VBox titledPaneVBox = new VBox();
//				titledPaneVBox.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
//				titledPaneVBox.setSpacing(3);
//				titledPaneVBox.getChildren().add(new Label("actorId"));
//				titledPaneVBox.getChildren().add(new Label("actorName"));
//				titledPaneVBox.getChildren().add(new Label("actorCity"));
//				titledPaneVBox.getChildren().add(new Label("actorId"));
//				titledPaneVBox.getChildren().add(new Label("actorName"));
//				titledPaneVBox.getChildren().add(new Label("actorCity"));
//				tabletitledPane.setContent(titledPaneVBox);
//				tabletitledPane.setCollapsible(false);
//				tabletitledPane.setLayoutX(100);   // used to place it a particular X location on screen
//				tabletitledPane.setLayoutY(100);   // used to place it a particular Y location on screen
//			    enableDragAndDrop(tabletitledPane, mainPane);
//				mainPane.getChildren().add(tabletitledPane);
//				
//				TitledPane tabletitled1Pane = new TitledPane();
//				tabletitled1Pane.setPrefWidth(150);
//				tabletitled1Pane.setText("address");
//				tabletitled1Pane.alignmentProperty().setValue(Pos.CENTER);
//				tabletitled1Pane.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
//				VBox titledPane1VBox = new VBox();
//				titledPane1VBox.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
//				titledPane1VBox.setSpacing(3);
//				titledPane1VBox.getChildren().add(new Label("address_id"));
//				titledPane1VBox.getChildren().add(new Label("address"));
//				titledPane1VBox.getChildren().add(new Label("address2"));
//				titledPane1VBox.getChildren().add(new Label("district"));
//				titledPane1VBox.getChildren().add(new Label("city_id"));
//				titledPane1VBox.getChildren().add(new Label("postal_code"));
//				titledPane1VBox.getChildren().add(new Label("phone"));
//				titledPane1VBox.getChildren().add(new Label("location"));
//				titledPane1VBox.getChildren().add(new Label("last_update"));
//				tabletitled1Pane.setContent(titledPane1VBox);
//				tabletitled1Pane.setCollapsible(false);
//				tabletitled1Pane.setLayoutX(300);   // used to place it a particular X location on screen
//				tabletitled1Pane.setLayoutY(300); // used to place it a particular Y location on screen
//				enableDragAndDrop(tabletitled1Pane, mainPane);
//				
//				mainPane.getChildren().add(tabletitled1Pane);
//		
//		Line line = LineNode.createLine(tabletitledPane,tabletitled1Pane);
				
//		Line sampleLine = new Line();
//		sampleLine.setStartX(170);
//		sampleLine.setStartY(500);
//		sampleLine.setEndX(300);
//		sampleLine.setEndY(500);
//		
//		// Bind the line's end to the middle of the left side of the TitledPane
//     	sampleLine.endXProperty().bind(tabletitled1Pane.layoutXProperty());
//		sampleLine.endYProperty().bind(
//		    tabletitled1Pane.layoutYProperty().add(tabletitled1Pane.getBoundsInParent().getHeight() / 2.0)
//		);

		// Bind the line's end to the middle of the left side of the TitledPane
//		sampleLine.endXProperty().bind(tabletitled1Pane.layoutXProperty());
//		sampleLine.endYProperty().bind(
//		    tabletitled1Pane.layoutYProperty().add(tabletitled1Pane.prefHeightProperty().divide(2))
//		);
		
//		sampleLine.endXProperty().bind(
//			    tabletitled1Pane.layoutXProperty().add(tabletitled1Pane.prefWidthProperty().divide(2))
//			);
//			sampleLine.endYProperty().bind(
//			    tabletitled1Pane.layoutYProperty().add(tabletitled1Pane.prefHeightProperty().divide(2))
//			);

		
//		sampleLine.endXProperty().bind( titledPane1VBox.layoutXProperty().add( titledPane1VBox.getBoundsInParent().getWidth() / 2.0));
//		sampleLine.endYProperty().bind( titledPane1VBox.layoutYProperty().add( titledPane1VBox.getBoundsInParent().getHeight() / 2.0));
//						
//		double x3 = sampleLine.getEndX() - 40;
//		System.out.println("the value of x3 point is:"+x3);
		
//		Line sampleLine1 = new Line();
//		sampleLine1.setStartX(170);
//		sampleLine1.setStartY(262);
//		sampleLine1.setEndX(170);
//		sampleLine1.setEndY(500);
//		
//		sampleLine1.endXProperty().bind(sampleLine.startXProperty());
//		sampleLine1.endYProperty().bind(sampleLine.startYProperty());
//		
//		sampleLine1.startXProperty().bind(tabletitledPane.layoutXProperty().add(tabletitledPane.getBoundsInParent().getHeight() / 2.0) );
//		sampleLine1.startYProperty().bind(
//		    tabletitledPane.layoutYProperty().add(tabletitledPane.getBoundsInParent().getHeight() / 2.0)
//		);
		
		
		
		//slope of line
//		double slopeOfLine = (sampleLine.getEndY() - sampleLine.getStartY())/(sampleLine.getEndX() - sampleLine.getStartX());
//		System.out.println("Slope of the line:"+ slopeOfLine);
//		
//		//perpendicular slope of the line
//		double perpSlope = -1 / slopeOfLine;
//		System.out.println(" Perpendicular slope of the line:"+ perpSlope);
//		
//		mainPane.getChildren().addAll(sampleLine,sampleLine1);
//		mainPane.getChildren().addAll(line);
//		enableDragAndDrop(line, mainPane);

//		enableDragAndDrop(sampleLine1, mainPane);
		
//		 double x1 = 0.0,x2= 20.0,x3= 10.0,y1= 0.0,y2= 10.0,y3=20.0;
//     	 Polygon triangle = new Polygon();
//		 double centerXPoint =(triangle.getLayoutX()+(x2+x3)/2);
//		 double centerYPoint =(triangle.getLayoutY()+(y2+y3)/2);
//		 triangle.getPoints().addAll(new Double[]{
//				x1,y1,
//				x2, y2,
//				x3, y3 });  
		 
		 //perpendicular points of the triangle
//		 double offset = 50; // Length of triangle's base
//		 double perpX1 = sampleLine.getEndX() + offset / Math.sqrt(1 + perpSlope * perpSlope);
//		 double perpY1 = sampleLine.getEndY()  + perpSlope * (perpX1 - sampleLine.getEndX());
//
//		 double perpX2 = sampleLine.getEndX() - offset / Math.sqrt(1 + perpSlope * perpSlope);
//		 double perpY2 = sampleLine.getEndY()  + perpSlope * (perpX2 - sampleLine.getEndX());

		 // Update triangle points
//		 triangle.getPoints().setAll(
//			 sampleLine.getEndX(), sampleLine.getEndY(), // Triangle top vertex
//		     perpX1, perpY1, // First base vertex
//		     perpX2, perpY2  // Second base vertex
//		 );
		
//		 sampleLine.endXProperty().bind(
//		            Bindings.createDoubleBinding(() -> {
//		                return triangle.getLayoutX() + (x2 + x3) / 2;  // Midpoint of the base X
//		            }, triangle.layoutXProperty())
//		        );
//
//		        sampleLine.endYProperty().bind(
//		            Bindings.createDoubleBinding(() -> {
//		                return triangle.getLayoutY() + (y2 + y3) / 2;  // Midpoint of the base Y
//		            }, triangle.layoutYProperty())
//		        );
		     		 	
//		 triangle.layoutXProperty().bind(sampleLine.startXProperty());
//	     triangle.layoutYProperty().bind(sampleLine.startYProperty());
	        
//		mainPane.getChildren().add(triangle);
//		enableDragAndDrop(triangle,mainPane);
		 
		 
		
		 // https://www.mathsisfun.com/equation_of_line.html
		 // https://www.quora.com/How-do-I-find-the-coordinates-of-point-P-which-is-perpendicular-to-line-4x-y-20
		 // use these above links to find slope and perpendiclar line and draw a small rectangle from the end point of the line these like an arrow
		 //  refer this to draw a triangle at the end of line , the end of the line will be the top vertex of the triangle
		
		 
		 
		// This will set the backgroud color the Zoom Pane
//		mainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
//		
//		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(mainPane);
//		
//	    Scene scene = new Scene(zoomableScrollPane, 600, 600/* Color.rgb(35, 39, 50) */);  
//	//    scene.getStylesheets().add(ERDiagramApplication.class.getResource("/testLayout.css").toExternalForm());
//		primaryStage.setTitle("No DataBase Connection ");   
//		primaryStage.setScene(scene);
//					    
//		primaryStage.show();
//		
//	}
//	
//	 	
//	private void enableDragAndDrop(TitledPane tabletitledPane, Pane mainPane) {
//        final double[] offset = new double[2];
//
//        // When the mouse is pressed, record the offset between the mouse position and the TitledPane position
//        tabletitledPane.setOnMousePressed(event -> {
//            offset[0] = event.getSceneX() - tabletitledPane.getLayoutX();
//            offset[1] = event.getSceneY() - tabletitledPane.getLayoutY();
//        });
//        
//        
//        tabletitledPane.setOnTouchPressed(null);
//        // When dragging, update the TitledPane position
//        tabletitledPane.setOnMouseDragged(event -> {
//            double newX = event.getSceneX() - offset[0];
//            double newY = event.getSceneY() - offset[1];
//
//            // Restrict movement within the bounds of the mainPane
//            if (newX >= 0 && newX + tabletitledPane.getWidth() <= mainPane.getWidth()) {
//                tabletitledPane.setLayoutX(newX);
//            }
//            if (newY >= 0 && newY + tabletitledPane.getHeight() <= mainPane.getHeight()) {
//                tabletitledPane.setLayoutY(newY);
//            }
//        });
//
//        // Optional: Provide feedback on mouse release
//        tabletitledPane.setOnMouseReleased(event -> {
//            System.out.println("TitledPane dropped at: " + tabletitledPane.getLayoutX() + ", " + tabletitledPane.getLayoutY());
//        });
//    }
//	
//	private void enableDragAndDrop(Line simpleLine, Pane mainPane) {
//        final double[] offset = new double[2];
//
//        // When the mouse is pressed, record the offset between the mouse position and the TitledPane position
//        simpleLine.setOnMousePressed(event -> {
//        	Point2D clickedpoint2D = new Point2D((float)event.getSceneX(),(float)event.getSceneY());
//        	Point2D lineStartpoint2D = new Point2D((float)simpleLine.getStartX(),(float)simpleLine.getStartY());
//        	Point2D lineEndpoint2D = new Point2D((float)simpleLine.getEndX(),(float)simpleLine.getEndY());
//        	
//        	// Distance between 2 points formula  root (x2-x1)square + (y2-y1)square
//        	double distanceBetweenClickedPointToLineStart = clickedpoint2D.distance(lineStartpoint2D);
//        	double distanceBetweenClickedPointToLineEnd = clickedpoint2D.distance(lineEndpoint2D);
//        	
//        	System.out.println("distanceBetweenClickedPointToLineStart : "+distanceBetweenClickedPointToLineStart);
//        	System.out.println("distanceBetweenClickedPointToLineEnd : "+distanceBetweenClickedPointToLineEnd);
//        	
//        	// Check if the clicked point is near to start or end of line
//        	if(distanceBetweenClickedPointToLineStart < distanceBetweenClickedPointToLineEnd) {
//        		System.out.println("Select the start point");  
//        		
//        	}
//        	if(distanceBetweenClickedPointToLineStart > distanceBetweenClickedPointToLineEnd) {
//        		System.out.println("Select the End point");  
//        	
//        	}
//        });       
//              
//        // When dragging, update the TitledPane position
//        simpleLine.setOnMouseDragged(event -> {
//        	boolean isStartLineSelected = false;
//        	boolean isEndLineSelected = false;
//        	
//        	Point2D clickedpoint2D = new Point2D((float)event.getSceneX(),(float)event.getSceneY());
//        	Point2D lineStartpoint2D = new Point2D((float)simpleLine.getStartX(),(float)simpleLine.getStartY());
//        	Point2D lineEndpoint2D = new Point2D((float)simpleLine.getEndX(),(float)simpleLine.getEndY());
//        	
//        	// Distance between 2 points formula  root (x2-x1)square + (y2-y1)square
//        	double distanceBetweenClickedPointToLineStart = clickedpoint2D.distance(lineStartpoint2D);
//        	double distanceBetweenClickedPointToLineEnd = clickedpoint2D.distance(lineEndpoint2D);
//        	
//        	System.out.println("distanceBetweenClickedPointToLineStart : "+distanceBetweenClickedPointToLineStart);
//        	System.out.println("distanceBetweenClickedPointToLineEnd : "+distanceBetweenClickedPointToLineEnd);
//        	
//        	// Check if the clicked point is near to start or end of line
//        	if(distanceBetweenClickedPointToLineStart < distanceBetweenClickedPointToLineEnd) {
//        		System.out.println("Select the start point");  
//        		isStartLineSelected=true;
//        		isEndLineSelected = false;
//        	}
//        	if(distanceBetweenClickedPointToLineStart > distanceBetweenClickedPointToLineEnd) {
//        		System.out.println("Select the End point");  
//        		isEndLineSelected = true;
//        		isStartLineSelected = false;
//        	}
//            double newX = event.getSceneX();
//            double newY = event.getSceneY();
//
//            if(isStartLineSelected) {
//	            // Restrict movement within the bounds of the mainPane
//	            if (newX >= 0 && newX  <= mainPane.getWidth()) {
//	            	simpleLine.setStartX(newX);
//	            }
//	            if (newY >= 0 && newY <= mainPane.getHeight()) {
//	            	simpleLine.setStartY(newY);
//	            }
//            }
//            else if(isEndLineSelected) {
//            	 // Restrict movement within the bounds of the mainPane
//	            if (newX >= 0 && newX  <= mainPane.getWidth()) {
//	            	simpleLine.setEndX(newX);
//	            }
//	            if (newY >= 0 && newY <= mainPane.getHeight()) {
//	            	simpleLine.setEndY(newY);
//	            	
//	            }
//            }
//        });		
//     
//    }
	
//	private void enableDragAndDrop(Polygon triangle, Pane mainPane) {
//        final double[] offset = new double[2];
//
//        // When the mouse is pressed, record the offset between the mouse position and the TitledPane position
//        triangle.setOnMousePressed(event -> {
//            offset[0] = event.getSceneX() - triangle.getLayoutX();
//            offset[1] = event.getSceneY() - triangle.getLayoutY();
//        });     
//               
//        triangle.setOnTouchPressed(null);
//        // When dragging, update the TitledPane position
//        triangle.setOnMouseDragged(event -> {
//            double newX = event.getSceneX() - offset[0];
//            double newY = event.getSceneY() - offset[1];
//
//            // Restrict movement within the bounds of the mainPane
//            if (newX >= 0 && newX + triangle.getStrokeWidth() <= mainPane.getWidth()) {
//            	triangle.setLayoutX(newX);
//            }
//            if (newY >= 0 && newY + triangle.getStrokeWidth() <= mainPane.getHeight()) {
//            	triangle.setLayoutY(newY);
//            }
//        });
//
//        // Optional: Provide feedback on mouse release
//        triangle.setOnMouseReleased(event -> {
//            System.out.println("Triangle dropped at: " + triangle.getLayoutX() + ", " + triangle.getLayoutY());
//        });
//    }
//	


//static class LineNode extends Line{
//	Line newLine;
//	TitledPaneNode leftTitledPane;
//	TitledPaneNode rightTitledPane;
//	
//	public LineNode() {
//		newLine= new Line();
//	}
//
//	public void  joinLeftPane(TitledPaneNode titledPane) {
//		this.leftTitledPane = titledPane;
//	}
//	
//	public void joinRightPane(TitledPaneNode titledPane) {
//		this.rightTitledPane = titledPane;
//	}
//	
//	public static Line createLine(TitledPane pane1, TitledPane pane2) {
//        Line line = new Line();
        // Bind the start of the line to the center of pane1
//        line.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty().divide(2)));
//        line.startYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty().divide(2)));
//        // Bind the end of the line to the center of pane2
//        line.endXProperty().bind(pane2.layoutXProperty().add(pane2.widthProperty().divide(2)));
//        line.endYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty().divide(2)));
//        line.startXProperty().bind(pane1.layoutXProperty().add(pane1.getBoundsInParent().getWidth() / 2.0) );
//        line.startYProperty().bind(
//		    pane1.layoutYProperty().add(pane1.getBoundsInParent().getWidth() / 2.0)
//		);
//        
//        line.endXProperty().bind(pane2.layoutXProperty().add(pane2.getBoundsInParent().getHeight() / 2.0) );
//        line.endYProperty().bind(
//        		pane2.layoutYProperty().add(pane2.getBoundsInParent().getHeight() / 2.0)
//        		);
        
      //  if (pane1.getLayoutX() + pane1.getWidth() < pane2.getLayoutX()) {
            // Pane1 is to the left of Pane2
//            line.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty()));
//            line.startYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty().divide(2)));
//            line.endXProperty().bind(pane2.layoutXProperty());
//            line.endYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty().divide(2)));
        //  } 
        //else if (pane2.getLayoutX() + pane2.getWidth() < pane1.getLayoutX()) {
//            // Pane2 is to the left of Pane1
//            line.endXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty()));
//            line.endYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty().divide(2)));
//            line.startXProperty().bind(pane2.layoutXProperty().add(pane2.widthProperty()));
//            line.startYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty().divide(2)));
//        } else if (pane1.getLayoutY() + pane1.getHeight() < pane2.getLayoutY()) {
//            // Pane1 is above Pane2
//            line.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty().divide(2)));
//            line.startYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty()));
//            line.endXProperty().bind(pane2.layoutXProperty().add(pane2.widthProperty().divide(2)));
//            line.endYProperty().bind(pane2.layoutYProperty());
//        } else {
//            // Pane2 is above Pane1
//            line.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty().divide(2)));
//            line.startYProperty().bind(pane1.layoutYProperty());
//            line.endXProperty().bind(pane2.layoutXProperty().add(pane2.widthProperty().divide(2)));
//            line.endYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty()));
//        }

//        DropShadow highlightEffect = new DropShadow();
//        highlightEffect.setColor(Color.CORNFLOWERBLUE);
//        highlightEffect.setRadius(10);
//        highlightEffect.setSpread(0.7);
//        
//    //    highlightEffect.setBlurType(BlurType._PASS_BOX);
//
//        // Add a click event handler to toggle the highlight effect
//        line.setOnMouseClicked(event -> {
//            if (line.getEffect() == null) {
//            	line.setEffect(highlightEffect); // Apply highlight
//            } else {
//            	line.setEffect(null);           // Remove highlight
//            }
//        });
//
//        return line;
//}	
//}
//
//static class TitledPaneNode extends TitledPane{
//	 TitledPane newTitledPane;
//	 List<LineNode> attachedLineNodes = new ArrayList<LineNode>();
//	 
//	 public TitledPaneNode() {
//		 newTitledPane = new TitledPane();
//	 }
//	 public static TitledPane createTitledPane(String title, String tooltipText, double layoutX, double layoutY, Pane mainPane, String... labels) {
//		    // Create TitledPane
//		    TitledPane titledPane = new TitledPane();
//		    titledPane.setPrefWidth(150);
//		    titledPane.setText(title);
//		    titledPane.alignmentProperty().setValue(Pos.CENTER);
//		    titledPane.setTooltip(new Tooltip(tooltipText));
//
//		    // Create VBox to hold labels
//		    VBox titledPaneVBox = new VBox();
//		    titledPaneVBox.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
//		    titledPaneVBox.setSpacing(3);
//
//		    // Add labels to VBox
//		    for (String label : labels) {
//		        titledPaneVBox.getChildren().add(new Label(label));
//		    }
//
//		    // Set content and position
////		    titledPane.setContent(titledPaneVBox);
////		    titledPane.setCollapsible(false);
////		    titledPane.setLayoutX(layoutX);
////		    titledPane.setLayoutY(layoutY);
//
////		    // Enable drag-and-drop functionality (if applicable)
////		    enableDragAndDrop(titledPane, mainPane);
//
//		    return titledPane;
//		}
//	 
//}
//}
//
//class ZoomableScrollPane extends ScrollPane {
//    Group zoomGroup;
//    Scale scaleTransform;
//    Node content;
//    double scaleValue = 1.0;
//    double delta = 0.1;
//
//    public ZoomableScrollPane(Node content) {
//        this.content = content;
//        Group contentGroup = new Group();
//        zoomGroup = new Group();
//        contentGroup.getChildren().add(zoomGroup);
//        zoomGroup.getChildren().add(content);
//        setContent(contentGroup);
//        scaleTransform = new Scale(scaleValue, scaleValue, 0, 0);
//        zoomGroup.getTransforms().add(scaleTransform);
//
//        zoomGroup.setOnScroll(new ZoomHandler());
//    }
//
//    public double getScaleValue() {
//        return scaleValue;
//    }
//
//    public void zoomToActual() {
//        zoomTo(1.0);
//    }
//
//    public void zoomTo(double scaleValue) {
//
//        this.scaleValue = scaleValue;
//
//        scaleTransform.setX(scaleValue);
//        scaleTransform.setY(scaleValue);
//
//    }
//
//    public void zoomActual() {
//
//        scaleValue = 1;
//        zoomTo(scaleValue);
//
//    }
//
//    public void zoomOut() {
//        scaleValue -= delta;
//
//        if (Double.compare(scaleValue, 0.1) < 0) {
//            scaleValue = 0.1;
//        }
//
//        zoomTo(scaleValue);
//    }
//
//    public void zoomIn() {
//
//        scaleValue += delta;
//
//        if (Double.compare(scaleValue, 10) > 0) {
//            scaleValue = 10;
//        }
//
//        zoomTo(scaleValue);
//
//    }
//
//    /**
//     * 
//     * @param minimizeOnly
//     *            If the content fits already into the viewport, then we don't
//     *            zoom if this parameter is true.
//     */
//    public void zoomToFit(boolean minimizeOnly) {
//
//        double scaleX = getViewportBounds().getWidth() / getContent().getBoundsInLocal().getWidth();
//        double scaleY = getViewportBounds().getHeight() / getContent().getBoundsInLocal().getHeight();
//
//        // consider current scale (in content calculation)
//        scaleX *= scaleValue;
//        scaleY *= scaleValue;
//
//        // distorted zoom: we don't want it => we search the minimum scale
//        // factor and apply it
//        double scale = Math.min(scaleX, scaleY);
//
//        // check precondition
//        if (minimizeOnly) {
//
//            // check if zoom factor would be an enlargement and if so, just set
//            // it to 1
//            if (Double.compare(scale, 1) > 0) {
//                scale = 1;
//            }
//        }
//
//        // apply zoom
//        zoomTo(scale);
//
//    }
//
//class ZoomHandler implements EventHandler<ScrollEvent> {
//
//        @Override
//        public void handle(ScrollEvent scrollEvent) {
//            // if (scrollEvent.isControlDown())
//            {
//
//                if (scrollEvent.getDeltaY() < 0) {
//                    scaleValue -= delta;
//                } else {
//                    scaleValue += delta;
//                }
//
//                zoomTo(scaleValue);
//
//                scrollEvent.consume();
//            }
//        }
//    }
//}


