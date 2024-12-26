package com.openfx.erdiagram;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.sun.javafx.geom.Point2D;

import javafx.application.Application;
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
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		
		
		// Diagram using VBox stackpane and rectangles
//		VBox rectangleVBox = new VBox();
//		rectangleVBox.setSpacing(0);
//		StackPane stackPane1 = new StackPane();
//		Rectangle tableNameRectangle = new Rectangle(150,25);
//		tableNameRectangle.setFill(Color.WHITE);
//		tableNameRectangle.setStroke(Color.BLACK);
//		tableNameRectangle.setArcHeight(8);
//		tableNameRectangle.setArcWidth(8);
//		tableNameRectangle.setStrokeWidth(1);	
//		tableNameRectangle.setFill(Color.SILVER);
//		stackPane1.getChildren().addAll(tableNameRectangle,new Label("actor"));
//		
//		StackPane stackPane2 = new StackPane();
//		Rectangle columnNamesRectangle = new Rectangle(150,80);
//		columnNamesRectangle.setFill(Color.WHITE);
//		columnNamesRectangle.setStroke(Color.BLACK);
//		columnNamesRectangle.setArcHeight(8);
//		columnNamesRectangle.setArcWidth(8);
//		columnNamesRectangle.setStrokeWidth(1);
//		columnNamesRectangle.setFill(Color.AZURE);
//		VBox vBoxColumns = new VBox();
//		vBoxColumns.setPadding(new Insets(10,0,0,10));
//		vBoxColumns.setSpacing(5);
//		vBoxColumns.getChildren().addAll(new Label(" actorId"),new Label(" actorName"),new Label(" actorCity"),new Label(" actorId"),new Label(" actorName"),new Label(" actorCity"));
//		columnNamesRectangle.setHeight(vBoxColumns.getChildren().size()*25);
//		stackPane2.getChildren().addAll(columnNamesRectangle,vBoxColumns);
//		rectangleVBox.getChildren().addAll(stackPane1,stackPane2);
//		rectangleVBox.setLayoutX(100);  // used to place it a particular X location on screen
//		rectangleVBox.setLayoutY(300);  // used to place it a particular Y location on screen
//		mainPane.getChildren().add(rectangleVBox);
		
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
		enableDragAndDrop(tabletitled1Pane, mainPane);
		
		mainPane.getChildren().add(tabletitled1Pane);
	
		Line sampleLine = new Line();
		sampleLine.setStartX(200);
		sampleLine.setStartY(200);
		sampleLine.setEndX(600);
		sampleLine.setEndY(600);
		
		mainPane.getChildren().add(sampleLine);
		enableDragAndDrop(sampleLine, mainPane);
		
		
		 Polygon triangle = new Polygon();
		 triangle.getPoints().addAll(new Double[]{
				0.0,0.0,
				20.0, 10.0,
				10.0, 20.0 });
		 	
		 mainPane.getChildren().add(triangle);
		 
		
		 // https://www.mathsisfun.com/equation_of_line.html
		 // https://www.quora.com/How-do-I-find-the-coordinates-of-point-P-which-is-perpendicular-to-line-4x-y-20
		 // use these above links to find slope and perpendiclar line and draw a small rectangle from the end point of the line these like an arrow
		 //  refer this to draw a triangle at the end of line , the end of the line will be the top vertex of the triangle
		
		 
		 
		// This will set the backgroud color the Zoom Pane
		mainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
		
		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(mainPane);
		
	    Scene scene = new Scene(zoomableScrollPane, 600, 600/* Color.rgb(35, 39, 50) */);  
	//    scene.getStylesheets().add(ERDiagramApplication.class.getResource("/testLayout.css").toExternalForm());
		primaryStage.setTitle("No DataBase Connection ");   
		primaryStage.setScene(scene);
					    
		primaryStage.show();
		
	}
	
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
