//package com.openfx.erdiagram;
//
//import com.sun.javafx.geom.Point2D;
//
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.control.TitledPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Line;
//import javafx.stage.Stage;
//
//public class Lines extends ERDiagramApplication {
//
//	private String lineContent;
//	private TitledPane titledPane;
//	ZoomableScrollPane zoomableScrollPane;
//	Pane mainPane;
//
//	public void start(Stage primaryStage) throws Exception {
//		String lineContent;
//		TitledPane titledPane; // Reference to TitledPane
//			
//		Line sampleLine = new Line();
//		sampleLine.setStartX(170);
//		sampleLine.setStartY(500);
//		sampleLine.setEndX(300);
//		sampleLine.setEndY(500);
//		
//		Node tabletitled1Pane = null;
//		// Bind the line's end to the middle of the left side of the TitledPane
//     	sampleLine.endXProperty().bind(tabletitled1Pane.layoutXProperty());
//		sampleLine.endYProperty().bind(
//		    tabletitled1Pane.layoutYProperty().add(tabletitled1Pane.getBoundsInParent().getHeight() / 2.0)
//		);
//		
//		Line sampleLine1 = new Line();
//		sampleLine1.setStartX(170);
//		sampleLine1.setStartY(262);
//		sampleLine1.setEndX(170);
//		sampleLine1.setEndY(500);
//		
//		sampleLine1.endXProperty().bind(sampleLine.startXProperty());
//		sampleLine1.endYProperty().bind(sampleLine.startYProperty());
//		
//		
//		Pane tabletitledPane = null;
//		sampleLine1.startXProperty().bind(tabletitledPane.layoutXProperty().add(tabletitledPane.getBoundsInParent().getHeight() / 2.0) );
//		sampleLine1.startYProperty().bind(
//				tabletitledPane.layoutYProperty().add(tabletitledPane.getBoundsInParent().getHeight() / 2.0)
//				);
//		
//		
//		mainPane.getChildren().addAll(sampleLine,sampleLine1);
//		double slopeOfLine = (sampleLine.getEndY() - sampleLine.getStartY())/(sampleLine.getEndX() - sampleLine.getStartX());
//		System.out.println("Slope of the line:"+ slopeOfLine);
//		
//		//perpendicular slope of the line
//		double perpSlope = -1 / slopeOfLine;
//		System.out.println(" Perpendicular slope of the line:"+ perpSlope);
//	}
//
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
//     
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
//     
//    }
//	
//	// Constructor
//	public Lines(String lineContent) {
//		this.lineContent = lineContent;
//	}
//
//	// Setter for TitledPane reference
//	public void setTitledPane(TitledPane titledPane) {
//		this.titledPane = titledPane;
//	}
//
//}
