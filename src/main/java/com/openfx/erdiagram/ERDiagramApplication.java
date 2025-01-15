package com.openfx.erdiagram;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Point2D;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ERDiagramApplication extends Application{

	public static void main(String[] args) {
		launch(ERDiagramApplication.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.print(size.getHeight() + "<--->" +size.getWidth());

		Pane mainPane = new Pane();
		mainPane.setPrefSize(size.getWidth(),size.getHeight());	

		// Diagram using TitledPane
		TitledPane tabletitledPane = new TitledPane();
		tabletitledPane.setPrefWidth(150);
		tabletitledPane.setText("actor");
		tabletitledPane.alignmentProperty().setValue(Pos.CENTER);
		tabletitledPane.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
		VBox titledPaneVBox = new VBox();
		titledPaneVBox.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
		titledPaneVBox.setSpacing(3);
		titledPaneVBox.getChildren().add(new Label("actorId"));
		titledPaneVBox.getChildren().add(new Label("actorName"));
		titledPaneVBox.getChildren().add(new Label("actorCity"));
		titledPaneVBox.getChildren().add(new Label("actorId"));
		titledPaneVBox.getChildren().add(new Label("actorName"));
		titledPaneVBox.getChildren().add(new Label("actorCity"));
		tabletitledPane.setContent(titledPaneVBox);
		tabletitledPane.setCollapsible(false);
		tabletitledPane.setLayoutX(100);   // used to place it a particular X location on screen
		tabletitledPane.setLayoutY(100);   // used to place it a particular Y location on screen
		addSquaresToTitledPane(tabletitledPane,mainPane);
		enableDragAndDrop(tabletitledPane, mainPane);
		mainPane.getChildren().add(tabletitledPane);

		TitledPane tabletitled1Pane = new TitledPane();
		tabletitled1Pane.setPrefWidth(150);
		tabletitled1Pane.setText("address");
		tabletitled1Pane.alignmentProperty().setValue(Pos.CENTER);
		tabletitled1Pane.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
		VBox titledPane1VBox = new VBox();
		titledPane1VBox.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
		titledPane1VBox.setSpacing(3);
		titledPane1VBox.getChildren().add(new Label("address_id"));
		titledPane1VBox.getChildren().add(new Label("address"));
		titledPane1VBox.getChildren().add(new Label("address2"));
		titledPane1VBox.getChildren().add(new Label("district"));
		titledPane1VBox.getChildren().add(new Label("city_id"));
		titledPane1VBox.getChildren().add(new Label("postal_code"));
		titledPane1VBox.getChildren().add(new Label("phone"));
		titledPane1VBox.getChildren().add(new Label("location"));
		titledPane1VBox.getChildren().add(new Label("last_update"));
		tabletitled1Pane.setContent(titledPane1VBox);
		tabletitled1Pane.setCollapsible(false);
		tabletitled1Pane.setLayoutX(300);   // used to place it a particular X location on screen
		tabletitled1Pane.setLayoutY(300); // used to place it a particular Y location on screen
		addSquaresToTitledPane(tabletitled1Pane,mainPane);
		enableDragAndDrop(tabletitled1Pane, mainPane);

		mainPane.getChildren().add(tabletitled1Pane);


		TitledPane tabletitledPane3 = new TitledPane();
		tabletitledPane3.setPrefWidth(150);
		tabletitledPane3.setText("category");
		tabletitledPane3.alignmentProperty().setValue(Pos.CENTER);
		tabletitledPane3.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
		VBox titledPaneVBox3 = new VBox();
		titledPaneVBox3.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
		titledPaneVBox3.setSpacing(3);
		titledPaneVBox3.getChildren().add(new Label("categoryId"));
		titledPaneVBox3.getChildren().add(new Label("name"));
		titledPaneVBox3.getChildren().add(new Label("lastUpdate"));
		tabletitledPane3.setContent(titledPaneVBox3);
		tabletitledPane3.setCollapsible(false);
		tabletitledPane3.setLayoutX(450);   // used to place it a particular X location on screen
		tabletitledPane3.setLayoutY(30);   // used to place it a particular Y location on screen
		addSquaresToTitledPane(tabletitledPane3,mainPane);
		enableDragAndDrop(tabletitledPane3, mainPane);
		mainPane.getChildren().add(tabletitledPane3);

		TitledPane tabletitledPane4 = new TitledPane();
		tabletitledPane4.setPrefWidth(150);
		tabletitledPane4.setText("city");
		tabletitledPane4.alignmentProperty().setValue(Pos.CENTER);
		tabletitledPane4.setTooltip(new Tooltip("This is a table in RDBD \n This is tool tip for table"));
		VBox titledPaneVBox4 = new VBox();
		titledPaneVBox4.setBackground(new Background(  new BackgroundFill(javafx.scene.paint.Color.AZURE,  CornerRadii.EMPTY,Insets.EMPTY )));
		titledPaneVBox4.setSpacing(3);
		titledPaneVBox4.getChildren().add(new Label("cityId"));
		titledPaneVBox4.getChildren().add(new Label("city"));
		titledPaneVBox4.getChildren().add(new Label("countryId"));
		titledPaneVBox4.getChildren().add(new Label("lastUpdate"));
		tabletitledPane4.setContent(titledPaneVBox4);
		tabletitledPane4.setCollapsible(false);
		tabletitledPane4.setLayoutX(850);   // used to place it a particular X location on screen
		tabletitledPane4.setLayoutY(20); // used to place it a particular Y location on screen
		addSquaresToTitledPane(tabletitledPane4,mainPane);
		enableDragAndDrop(tabletitledPane4, mainPane);

		mainPane.getChildren().add(tabletitledPane4);

		Group line = LineNode.createGroupLine(tabletitledPane,tabletitled1Pane,mainPane);
		Group line1 = LineNode.createGroupLine(tabletitledPane3,tabletitledPane4,mainPane);

		//slope of line
		//		double slopeOfLine = (sampleLine.getEndY() - sampleLine.getStartY())/(sampleLine.getEndX() - sampleLine.getStartX());
		//		System.out.println("Slope of the line:"+ slopeOfLine);
		//		
		//		//perpendicular slope of the line
		//		double perpSlope = -1 / slopeOfLine;
		//		System.out.println(" Perpendicular slope of the line:"+ perpSlope);

		mainPane.getChildren().addAll(line,line1);
     	//enableDragAndDrop(line, mainPane);
//		enableDragAndDrop(line1, mainPane);

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
		mainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));

		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(mainPane);

		Scene scene = new Scene(zoomableScrollPane, 600, 600/* Color.rgb(35, 39, 50) */);  
		scene.getStylesheets().add(ERDiagramApplication.class.getResource("/testLayout.css").toExternalForm());
		primaryStage.setTitle("No DataBase Connection ");   
		primaryStage.setScene(scene);

		primaryStage.show();

	}


//	private void enableDragAndDrop(Group line, Pane mainPane) {
//		// TODO Auto-generated method stub
//		
//	}

	private void enableDragAndDrop(TitledPane tabletitledPane, Pane mainPane) {
		final double[] offset = new double[2];

		// When the mouse is pressed, record the offset between the mouse position and the TitledPane position
		tabletitledPane.setOnMousePressed(event -> {
			offset[0] = event.getSceneX() - tabletitledPane.getLayoutX();
			offset[1] = event.getSceneY() - tabletitledPane.getLayoutY();
		});


		tabletitledPane.setOnTouchPressed(null);
		// When dragging, update the TitledPane position
		tabletitledPane.setOnMouseDragged(event -> {
			double newX = event.getSceneX() - offset[0];
			double newY = event.getSceneY() - offset[1];

			// Restrict movement within the bounds of the mainPane
			if (newX >= 0 && newX + tabletitledPane.getWidth() <= mainPane.getWidth()) {
				tabletitledPane.setLayoutX(newX);
			}
			if (newY >= 0 && newY + tabletitledPane.getHeight() <= mainPane.getHeight()) {
				tabletitledPane.setLayoutY(newY);
			}
		});

		// Optional: Provide feedback on mouse release
		tabletitledPane.setOnMouseReleased(event -> {
			System.out.println("TitledPane dropped at: " + tabletitledPane.getLayoutX() + ", " + tabletitledPane.getLayoutY());
		});
	}

	private void enableDragAndDrop(Line simpleLine, Pane mainPane) {
		final double[] offset = new double[2];

		// When the mouse is pressed, record the offset between the mouse position and the TitledPane position
		simpleLine.setOnMousePressed(event -> {
			Point2D clickedpoint2D = new Point2D((float)event.getSceneX(),(float)event.getSceneY());
			Point2D lineStartpoint2D = new Point2D((float)simpleLine.getStartX(),(float)simpleLine.getStartY());
			Point2D lineEndpoint2D = new Point2D((float)simpleLine.getEndX(),(float)simpleLine.getEndY());

			// Distance between 2 points formula  root (x2-x1)square + (y2-y1)square
			double distanceBetweenClickedPointToLineStart = clickedpoint2D.distance(lineStartpoint2D);
			double distanceBetweenClickedPointToLineEnd = clickedpoint2D.distance(lineEndpoint2D);

			System.out.println("distanceBetweenClickedPointToLineStart : "+distanceBetweenClickedPointToLineStart);
			System.out.println("distanceBetweenClickedPointToLineEnd : "+distanceBetweenClickedPointToLineEnd);

			// Check if the clicked point is near to start or end of line
			if(distanceBetweenClickedPointToLineStart < distanceBetweenClickedPointToLineEnd) {
				System.out.println("Select the start point");  

			}
			if(distanceBetweenClickedPointToLineStart > distanceBetweenClickedPointToLineEnd) {
				System.out.println("Select the End point");  

			}
		});       

		// When dragging, update the TitledPane position
		simpleLine.setOnMouseDragged(event -> {
			boolean isStartLineSelected = false;
			boolean isEndLineSelected = false;

			Point2D clickedpoint2D = new Point2D((float)event.getSceneX(),(float)event.getSceneY());
			Point2D lineStartpoint2D = new Point2D((float)simpleLine.getStartX(),(float)simpleLine.getStartY());
			Point2D lineEndpoint2D = new Point2D((float)simpleLine.getEndX(),(float)simpleLine.getEndY());

			// Distance between 2 points formula  root (x2-x1)square + (y2-y1)square
			double distanceBetweenClickedPointToLineStart = clickedpoint2D.distance(lineStartpoint2D);
			double distanceBetweenClickedPointToLineEnd = clickedpoint2D.distance(lineEndpoint2D);

			System.out.println("distanceBetweenClickedPointToLineStart : "+distanceBetweenClickedPointToLineStart);
			System.out.println("distanceBetweenClickedPointToLineEnd : "+distanceBetweenClickedPointToLineEnd);

			// Check if the clicked point is near to start or end of line
			if(distanceBetweenClickedPointToLineStart < distanceBetweenClickedPointToLineEnd) {
				System.out.println("Select the start point");  
				isStartLineSelected=true;
				isEndLineSelected = false;
			}
			if(distanceBetweenClickedPointToLineStart > distanceBetweenClickedPointToLineEnd) {
				System.out.println("Select the End point");  
				isEndLineSelected = true;
				isStartLineSelected = false;
			}
			double newX = event.getSceneX();
			double newY = event.getSceneY();

			if(isStartLineSelected) {
				// Restrict movement within the bounds of the mainPane
				if (newX >= 0 && newX  <= mainPane.getWidth()) {
					simpleLine.setStartX(newX);
				}
				if (newY >= 0 && newY <= mainPane.getHeight()) {
					simpleLine.setStartY(newY);
				}
			}
			else if(isEndLineSelected) {
				// Restrict movement within the bounds of the mainPane
				if (newX >= 0 && newX  <= mainPane.getWidth()) {
					simpleLine.setEndX(newX);
				}
				if (newY >= 0 && newY <= mainPane.getHeight()) {
					simpleLine.setEndY(newY);

				}
			}
		});		

	}
	
	private void addSquaresToTitledPane(TitledPane titledPane, Pane mainPane) {
	    // Create squares for the four sides
	    Rectangle topSquare = createSquare();
	    Rectangle rightSquare = createSquare();
	    Rectangle bottomSquare = createSquare();
	    Rectangle leftSquare = createSquare();

	    // Create squares for the four corners
	    Rectangle topLeftSquare = createSquare();
	    Rectangle topRightSquare = createSquare();
	    Rectangle bottomLeftSquare = createSquare();
	    Rectangle bottomRightSquare = createSquare();

	    // Bind the positions of the squares
	    // Side squares
	    topSquare.xProperty().bind(titledPane.layoutXProperty().add(titledPane.widthProperty().divide(2)).subtract(5));
	    topSquare.yProperty().bind(titledPane.layoutYProperty().subtract(5));

	    rightSquare.xProperty().bind(titledPane.layoutXProperty().add(titledPane.widthProperty()).subtract(5));
	    rightSquare.yProperty().bind(titledPane.layoutYProperty().add(titledPane.heightProperty().divide(2)).subtract(5));

	    bottomSquare.xProperty().bind(titledPane.layoutXProperty().add(titledPane.widthProperty().divide(2)).subtract(5));
	    bottomSquare.yProperty().bind(titledPane.layoutYProperty().add(titledPane.heightProperty()).subtract(5));

	    leftSquare.xProperty().bind(titledPane.layoutXProperty().subtract(5));
	    leftSquare.yProperty().bind(titledPane.layoutYProperty().add(titledPane.heightProperty().divide(2)).subtract(5));

	    // Corner squares
	    topLeftSquare.xProperty().bind(titledPane.layoutXProperty().subtract(5));
	    topLeftSquare.yProperty().bind(titledPane.layoutYProperty().subtract(5));

	    topRightSquare.xProperty().bind(titledPane.layoutXProperty().add(titledPane.widthProperty()).subtract(5));
	    topRightSquare.yProperty().bind(titledPane.layoutYProperty().subtract(5));

	    bottomLeftSquare.xProperty().bind(titledPane.layoutXProperty().subtract(5));
	    bottomLeftSquare.yProperty().bind(titledPane.layoutYProperty().add(titledPane.heightProperty()).subtract(5));

	    bottomRightSquare.xProperty().bind(titledPane.layoutXProperty().add(titledPane.widthProperty()).subtract(5));
	    bottomRightSquare.yProperty().bind(titledPane.layoutYProperty().add(titledPane.heightProperty()).subtract(5));

	    // Add squares to the main pane
	    mainPane.getChildren().addAll(
	        topSquare, rightSquare, bottomSquare, leftSquare,
	        topLeftSquare, topRightSquare, bottomLeftSquare, bottomRightSquare
	    );
	}

	// Helper method to create a square with black border and white fill
	private Rectangle createSquare() {
	    Rectangle square = new Rectangle(10, 10); // Square size 10x10
	    square.setFill(Color.WHITE);
	    square.setStroke(Color.BLACK);
	    return square;
	}

	
	static class LineNode extends Line{
		Line newLine;
		TitledPaneNode leftTitledPane;
		TitledPaneNode rightTitledPane;


		public LineNode() {
			newLine= new Line();
		}

		public void  joinLeftPane(TitledPaneNode titledPane) {
			this.leftTitledPane = titledPane;
		}

		public void joinRightPane(TitledPaneNode titledPane) {
			this.rightTitledPane = titledPane;
		}

		public static Line createLine(TitledPane pane1, TitledPane pane2) {
			Line line = new Line();

			if (pane1.getLayoutX() + pane1.getWidth() < pane2.getLayoutX()) {
				//Pane1 is to the left of Pane2
				line.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty()));
				line.startYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty().divide(2)));
				line.endXProperty().bind(pane2.layoutXProperty());
				line.endYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty().divide(2)));
			}else if (pane2.getLayoutX() + pane2.getWidth() < pane1.getLayoutX()) {
				// Pane2 is to the left of Pane1
				line.startXProperty().bind(pane2.layoutXProperty());
				line.startYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty().divide(2)));
				line.endXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty()));
				line.endYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty().divide(2)));
			} else if (pane1.getLayoutY() + pane1.getHeight() < pane2.getLayoutY()) {
				// Pane1 is above Pane2
				line.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty().divide(2)));
				line.startYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty()));
				line.endXProperty().bind(pane2.layoutXProperty().add(pane2.widthProperty().divide(2)));
				line.endYProperty().bind(pane2.layoutYProperty());
			} else {
				// Pane2 is above Pane1
				line.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty().divide(2)));
				line.startYProperty().bind(pane1.layoutYProperty());
				line.endXProperty().bind(pane2.layoutXProperty().add(pane2.widthProperty().divide(2)));
				line.endYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty()));
			}
			
		
//			DropShadow highlightEffect = new DropShadow();
//			highlightEffect.setColor(Color.CORNFLOWERBLUE);
//			highlightEffect.setRadius(10);
//			highlightEffect.setSpread(0.7); 
//
//			// Add a click event handler to toggle the highlight effect
//			line.setOnMouseClicked(event -> {
//				if (line.getEffect() == null) {
//					line.setEffect(highlightEffect); // Apply highlight
//				} else {
//					line.setEffect(null);           // Remove highlight
//				}
//			});

			return line;
		}	
		
		public static Group createGroupLine(TitledPane pane1, TitledPane pane2, Pane mainPane) {

			Line horizontalLine1 = new Line();
		    horizontalLine1.startXProperty().bind(pane1.layoutXProperty().add(pane1.widthProperty()));
		    horizontalLine1.startYProperty().bind(pane1.layoutYProperty().add(pane1.heightProperty().divide(2)));
		    horizontalLine1.endXProperty().bind(horizontalLine1.startXProperty().add(50)); // Fixed gap for vertical line
		    horizontalLine1.endYProperty().bind(horizontalLine1.startYProperty());

		    // Vertical line: Connects the end of the first horizontal line to the second horizontal line
		    Line verticalLine = new Line();
		    verticalLine.startXProperty().bind(horizontalLine1.endXProperty());
		    verticalLine.startYProperty().bind(horizontalLine1.endYProperty());
		    verticalLine.endXProperty().bind(verticalLine.startXProperty());
		    verticalLine.endYProperty().bind(pane2.layoutYProperty().add(pane2.heightProperty().divide(2)));
			
		    Line horizontalLine2 = new Line();
		    horizontalLine2.startXProperty().bind(verticalLine.endXProperty());
		    horizontalLine2.startYProperty().bind(verticalLine.endYProperty());
		    horizontalLine2.endXProperty().bind(pane2.layoutXProperty());
		    horizontalLine2.endYProperty().bind(verticalLine.endYProperty());
	    

		    DropShadow highlightEffectOnClicked = new DropShadow();
		    highlightEffectOnClicked.setColor(Color.CORNFLOWERBLUE);
		    highlightEffectOnClicked.setRadius(10);
		    highlightEffectOnClicked.setSpread(0.7);
		    
		    DropShadow highlightEffectOnHover= new DropShadow();
		    highlightEffectOnHover.setColor(Color.DARKORANGE);
		    highlightEffectOnHover.setRadius(10);
		    highlightEffectOnHover.setSpread(0.7);
		    
//		    DropShadow highlightEffectOnHoverExit= new DropShadow();
//		    highlightEffectOnHoverExit.setColor(Color.DARKORANGE);
//		    highlightEffectOnHoverExit.setRadius(10);
//		    highlightEffectOnHoverExit.setSpread(0.7);
//			
			

			// Add a click event handler to toggle the highlight effect
			
//		    horizontalLine1.setOnMouseMoved( event -> {
//		    	if (horizontalLine1.getEffect() == null) {
//		    		horizontalLine1.setEffect(highlightEffectOnHover); // Apply highlight
//		    	} else {
//		    		horizontalLine1.setEffect(null);           // Remove highlight
//		    	}
//		    });
//		    
//			horizontalLine1.setOnMouseClicked(event -> {
//				if (horizontalLine1.getEffect() == null) {
//					horizontalLine1.setEffect(highlightEffectOnClicked); // Apply highlight
//				} else {
//					horizontalLine1.setEffect(null);           // Remove highlight
//				}
//			});
//			

		    EventHandler<MouseEvent> highlightHandler = event -> {
			    Line clickedLine = (Line) event.getSource(); // The line that was clicked
			    boolean applyEffect = clickedLine.getEffect() == null; // Determine whether to apply or remove effect

			    // Apply or remove the highlight effect for all lines
			    if (applyEffect) {
			        horizontalLine1.setEffect(highlightEffectOnClicked);
			        verticalLine.setEffect(highlightEffectOnClicked);
			        horizontalLine2.setEffect(highlightEffectOnClicked);
			         } else {
			        horizontalLine1.setEffect(null);
			        verticalLine.setEffect(null);
			        horizontalLine2.setEffect(null);
			        
			    }
			};

			EventHandler<MouseEvent> mouseEnteredHandler = event -> {
				Line clickedLine = (Line) event.getSource();
				 boolean applyEffect = clickedLine.getEffect() == null;
				 if (applyEffect) {
			    horizontalLine1.setEffect(highlightEffectOnHover);
			    verticalLine.setEffect(highlightEffectOnHover);
			    horizontalLine2.setEffect(highlightEffectOnHover);
				 } else {
					 horizontalLine1.setEffect(null);
				        verticalLine.setEffect(null);
				        horizontalLine2.setEffect(null);
				 }
			};

//			EventHandler<MouseEvent> mouseExitedHandler = event -> {
//			    horizontalLine1.setEffect(null); // Reset to default color (black)
//			    verticalLine.setEffect(null);
//			    horizontalLine2.setEffect(null);
//			};
			
			// Attach the shared handler to all lines
			horizontalLine1.setOnMouseClicked(highlightHandler);
			verticalLine.setOnMouseClicked(highlightHandler);
			horizontalLine2.setOnMouseClicked(highlightHandler);
			
			horizontalLine1.setOnMouseEntered(mouseEnteredHandler);
			verticalLine.setOnMouseEntered(mouseEnteredHandler);
			horizontalLine2.setOnMouseEntered(mouseEnteredHandler);
			
//			horizontalLine1.setOnMouseExited(mouseExitedHandler);
//			verticalLine.setOnMouseExited(mouseExitedHandler);
//			horizontalLine2.setOnMouseExited(mouseExitedHandler);
			
			 mainPane.getChildren().addAll(horizontalLine1, verticalLine,horizontalLine2);
             return new Group(horizontalLine1,verticalLine,horizontalLine2);
		}

	}

	static class TitledPaneNode extends TitledPane{
		TitledPane newTitledPane;
		List<LineNode> attachedLineNodes = new ArrayList<LineNode>();


		public TitledPaneNode() {
			newTitledPane = new TitledPane();
		}
	}
}

class ZoomableScrollPane extends ScrollPane {
	Group zoomGroup;
	Scale scaleTransform;
	Node content;
	double scaleValue = 1.0;
	double delta = 0.1;

	public ZoomableScrollPane(Node content) {
		this.content = content;
		Group contentGroup = new Group();
		zoomGroup = new Group();
		contentGroup.getChildren().add(zoomGroup);
		zoomGroup.getChildren().add(content);
		setContent(contentGroup);
		scaleTransform = new Scale(scaleValue, scaleValue, 0, 0);
		zoomGroup.getTransforms().add(scaleTransform);

		zoomGroup.setOnScroll(new ZoomHandler());
	}

	public double getScaleValue() {
		return scaleValue;
	}

	public void zoomToActual() {
		zoomTo(1.0);
	}

	public void zoomTo(double scaleValue) {

		this.scaleValue = scaleValue;

		scaleTransform.setX(scaleValue);
		scaleTransform.setY(scaleValue);

	}

	public void zoomActual() {

		scaleValue = 1;
		zoomTo(scaleValue);

	}

	public void zoomOut() {
		scaleValue -= delta;

		if (Double.compare(scaleValue, 0.1) < 0) {
			scaleValue = 0.1;
		}

		zoomTo(scaleValue);
	}

	public void zoomIn() {

		scaleValue += delta;

		if (Double.compare(scaleValue, 10) > 0) {
			scaleValue = 10;
		}

		zoomTo(scaleValue);

	}

	/**
	 * 
	 * @param minimizeOnly
	 *            If the content fits already into the viewport, then we don't
	 *            zoom if this parameter is true.
	 */
	public void zoomToFit(boolean minimizeOnly) {

		double scaleX = getViewportBounds().getWidth() / getContent().getBoundsInLocal().getWidth();
		double scaleY = getViewportBounds().getHeight() / getContent().getBoundsInLocal().getHeight();

		// consider current scale (in content calculation)
		scaleX *= scaleValue;
		scaleY *= scaleValue;

		// distorted zoom: we don't want it => we search the minimum scale
		// factor and apply it
		double scale = Math.min(scaleX, scaleY);

		// check precondition
		if (minimizeOnly) {

			// check if zoom factor would be an enlargement and if so, just set
			// it to 1
			if (Double.compare(scale, 1) > 0) {
				scale = 1;
			}
		}

		// apply zoom
		zoomTo(scale);

	}

	class ZoomHandler implements EventHandler<ScrollEvent> {

		@Override
		public void handle(ScrollEvent scrollEvent) {
			// if (scrollEvent.isControlDown())
			{

				if (scrollEvent.getDeltaY() < 0) {
					scaleValue -= delta;
				} else {
					scaleValue += delta;
				}

				zoomTo(scaleValue);

				scrollEvent.consume();
			}
		}
	}
}


