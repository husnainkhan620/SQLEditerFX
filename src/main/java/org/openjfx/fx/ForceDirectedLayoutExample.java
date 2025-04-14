package org.openjfx.fx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ForceDirectedLayoutExample extends Application {

	private  int numNodes = 17;
    private static final int NODE_RADIUS = 200;  
    private static final int CANVAS_WIDTH = 4800;    // 12 * 4 
    private static final int CANVAS_HEIGHT = 4800;  // 12 * 4
    private static final double REPULSION_FORCE = 4800*4800; // Force pushing nodes apart  CANVAS_WIDTH * CANVAS_HEIGHT
    private static final double ATTRACTION_FORCE = 0.1; // Force pulling connected nodes together
    private static final double DAMPING_FACTOR = 0.6; // Damping to slow down node movement
    private static final int ITERATIONS = 100; // Number of iterations for force-directed layout

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
    //    Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    //    GraphicsContext gc = canvas.getGraphicsContext2D();

        // Define the nodes and their initial random positions
   //     int numNodes = 6;
        numNodes = 17;
        List<double[]> nodePositions = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            double x = Math.random() * CANVAS_WIDTH;
            double y = Math.random() * CANVAS_HEIGHT;
            nodePositions.add(new double[]{x, y});
        }

        // Define the edges (graph with multiple cycles)
        int[][] edges1 = {
            {0, 1},  // Edge from Node 0 to Node 1
            {1, 2},  // Edge from Node 1 to Node 2
            {2, 3},  // Edge from Node 2 to Node 3
            {3, 4},  // Edge from Node 3 to Node 4
            {4, 5},  // Edge from Node 4 to Node 5
            {5, 0},  // Edge from Node 5 to Node 0 (creates a cycle)
            {1, 4},  // Edge from Node 1 to Node 4 (creates another cycle)
            {2, 5},   // Edge from Node 2 to Node 5 (creates another cycle) 
            {2, 0},  // Edge from Node 1 to Node 4 (creates another cycle)
            {3, 1},   // Edge from Node 2 to Node 5 (creates another cycle) 
        };
        
        
        int[][] edges = {
        		{0,1},
                {1, 12},{1,7},
                {2,3},
                {3,13},
                {4,14},{4,7},
                {5,10},{5,11},{5,6},
                {6,2},{6,8},
                {7,15},
                {8,2},{8,6},
                {9,7},{9,8},
                {10,2},{10,8},
                {11,10},{11,9},{11,6},
                {16,16}
        }  ;
        
        // Apply force-directed layout to adjust node positions
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            applyForceDirectedLayout(nodePositions, edges);
        }

        // Draw the edges
        boolean isBlack = true;
        
        for (int[] edge : edges) {
        	Line line = new Line();
        	Circle circle = new Circle();
        	circle.setRadius(200);
        	line.setStrokeWidth(4);
            double startX = nodePositions.get(edge[0])[0];
            double startY = nodePositions.get(edge[0])[1];
            double endX = nodePositions.get(edge[1])[0];
            double endY = nodePositions.get(edge[1])[1];
            if(isBlack) {
            	line.setStroke(Color.BLUE);
            	isBlack=false;
            }
            else {
            	line.setStroke(Color.BLUE);
            	isBlack = true;
            }
            line.setStartX(startX);line.setStartY(startY);line.setEndX(endX);line.setEndY(endY);
            circle.setCenterX(startX);
            circle.setCenterY(startY);
            circle.setOnMouseEntered(event ->{
            	System.out.println("Enter Node is "+ edge[0]);
            });
            root.getChildren().add(circle);
            root.getChildren().add(line);
        }

    	root.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
    	root.setPrefSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    	
		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(root,false,null);
        // Draw the nodes
        Scene scene = new Scene(zoomableScrollPane, 1100, 700);
        primaryStage.setTitle("Force-Directed Layout for Planar Graph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to apply force-directed layout
    private void applyForceDirectedLayout(List<double[]> nodePositions, int[][] edges) {
        int numNodes = nodePositions.size();
        double[] forcesX = new double[numNodes];
        double[] forcesY = new double[numNodes];

        // Calculate repulsion forces (nodes push each other apart)
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                double dx = nodePositions.get(j)[0] - nodePositions.get(i)[0];
                double dy = nodePositions.get(j)[1] - nodePositions.get(i)[1];
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance > 0) {
                    double force = REPULSION_FORCE / (distance * distance);
                    forcesX[i] -= force * dx / distance;
                    forcesY[i] -= force * dy / distance;
                    forcesX[j] += force * dx / distance;
                    forcesY[j] += force * dy / distance;
                }
            }
        }

        // Calculate attraction forces (edges pull nodes together)
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            double dx = nodePositions.get(j)[0] - nodePositions.get(i)[0];
            double dy = nodePositions.get(j)[1] - nodePositions.get(i)[1];
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance > 0) {
                double force = ATTRACTION_FORCE * distance;
                forcesX[i] += force * dx / distance;
                forcesY[i] += force * dy / distance;
                forcesX[j] -= force * dx / distance;
                forcesY[j] -= force * dy / distance;
            }
        }

        // Update node positions based on forces
        for (int i = 0; i < numNodes; i++) {
            nodePositions.get(i)[0] += forcesX[i] * DAMPING_FACTOR;
            nodePositions.get(i)[1] += forcesY[i] * DAMPING_FACTOR;

            // Ensure nodes stay within the canvas bounds
            nodePositions.get(i)[0] = Math.max(NODE_RADIUS, Math.min((CANVAS_WIDTH-200) - NODE_RADIUS, nodePositions.get(i)[0]));
            nodePositions.get(i)[1] = Math.max(NODE_RADIUS, Math.min((CANVAS_HEIGHT-200) - NODE_RADIUS, nodePositions.get(i)[1]));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ZoomableScrollPane extends ScrollPane {
	Group zoomGroup;
	Scale scaleTransform;
	Node content;
	double scaleValue = 1.0;
	double delta = 0.1;
	Rectangle focusRectangle ;

	public ZoomableScrollPane(Node content,boolean isPreview,ZoomableScrollPane mainZoomableScrollPane) {
		this.content = content;
		Group contentGroup = new Group();
		zoomGroup = new Group();
		contentGroup.getChildren().add(zoomGroup);
		zoomGroup.getChildren().add(content);
		setContent(contentGroup);
		scaleTransform = new Scale(scaleValue, scaleValue, 0, 0);
		zoomGroup.getTransforms().add(scaleTransform);

		if(isPreview) {
			focusRectangle = getDraggableRectangle(mainZoomableScrollPane);
			contentGroup.getChildren().add(focusRectangle);
			zoomTo(0.1);
		}else {
			zoomGroup.setOnScroll(new ZoomHandler());
		}
	}

	public Rectangle getDraggableRectangle(ZoomableScrollPane mainZoomableScrollPane) {
		
		 Rectangle rectangle = new Rectangle(50,50);
		 //rectangle.setStroke(Color.BLACK);
		 //rectangle.setFill(Color.TRANSPARENT);
		 rectangle.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		            new Stop[]{
		            new Stop(0,Color.TRANSPARENT),
		            new Stop(0.5, Color.LIGHTBLUE),
		            new Stop(1,Color.TRANSPARENT),}));
				rectangle.setStroke(Color.BLACK);
				rectangle.setStrokeWidth(2);
				rectangle.setArcHeight(2);
				rectangle.setArcWidth(2);
		
		final double[] offset = new double[2];
		
		 // When the mouse is pressed, record the offset between the mouse position and the TitledPane position
		rectangle.setOnMousePressed(event -> {
            offset[0] = event.getSceneX() - rectangle.getLayoutX();
            offset[1] = event.getSceneY() - rectangle.getLayoutY();
        });
        
		rectangle.setOnMouseDragged(event -> { 

			double newX = event.getSceneX() - offset[0];
			double newY = event.getSceneY() - offset[1];

            // Restrict movement within the bounds of the mainPane
            if (newX >= 0 && ( newX + rectangle.getWidth()) <=  ((Pane) content).getMinWidth()/10 )  {
            	rectangle.setLayoutX(newX);
            }
            if (newY >= 0 && ( newY + rectangle.getHeight()) <= ((Pane) content).getMinHeight()/10 ) {
            	rectangle.setLayoutY(newY);
            }
            
           System.out.println("Rectangle New X "+newX);
           System.out.println("Rectangle New Y "+newY);
            
           // change the vValue hValue of the mainframe and its scrollPAne here     
            mainZoomableScrollPane.setHvalue( (newX/200) );
            mainZoomableScrollPane.setVvalue( (newY/200) );
		});		
		
		return rectangle;
	}
		
	public Rectangle getFocusRectangle() {
		return focusRectangle;
	}

	public double getScaleValue() {
		return scaleValue;
	}

	public void zoomToActual() {
		zoomTo(1.0);
	}

	public void zoomTo(double scaleValue) {

		System.out.println("Scale Value is --> "+scaleValue);
		
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


