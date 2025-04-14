package org.openjfx.fx;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CircularPlanarGraphDrawing extends Application {

    private static final int NODE_RADIUS = 20;
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
     //   Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
     //   GraphicsContext gc = canvas.getGraphicsContext2D();

        // Define the nodes and their positions in a circular layout
        int numNodes = 6;
        List<double[]> nodePositions = getCircularLayout(numNodes, CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, 250);

        // Define the edges (cyclic graph)
        int[][] edges1 = {
            {0, 1},  // Edge from Node 0 to Node 1
            {1, 2},  // Edge from Node 1 to Node 2
            {2, 3},  // Edge from Node 2 to Node 3
            {3, 4},  // Edge from Node 3 to Node 4
            {4, 0},  // Edge from Node 4 to Node 0 (creates a cycle)
            {1, 3}   // Edge from Node 1 to Node 3
        };

        int[][] edges = {
                {0, 1},  // Edge from Node 0 to Node 1
                {1, 2},  // Edge from Node 1 to Node 2
                {2, 3},  // Edge from Node 2 to Node 3
                {3, 4},  // Edge from Node 3 to Node 4
                {4, 5},  // Edge from Node 4 to Node 5
                {5, 0},  // Edge from Node 5 to Node 0 (creates a cycle)
                {1, 4},  // Edge from Node 1 to Node 4 (creates another cycle)
                {2, 5}   // Edge from Node 2 to Node 5 (creates another cycle) 
            };
        
        // Draw the edges
      //  gc.setStroke(Color.BLACK);
      //  gc.setLineWidth(2);
        for (int[] edge : edges) {
        	Line line = new Line();
            double startX = nodePositions.get(edge[0])[0];
            double startY = nodePositions.get(edge[0])[1];
            double endX = nodePositions.get(edge[1])[0];
            double endY = nodePositions.get(edge[1])[1];
            //gc.strokeLine(startX, startY, endX, endY);
            line.setStrokeWidth(2);
            line.setStartX(startX);line.setStartY(startY);line.setEndX(endX);line.setEndY(endY);
            root.getChildren().add(line);
        }

        // Draw the nodes
     //   gc.setFill(Color.BLUE);
        for (double[] position : nodePositions) {
            double x = position[0] ;
            double y = position[1] ;
        //    gc.fillOval(x, y, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
            Circle circle = new Circle();
            circle.setCenterX(x);circle.setCenterY(y);circle.setRadius(NODE_RADIUS);circle.setFill(Color.BLUE);
            root.getChildren().add(circle);
        }

  //      root.getChildren().add(canvas);
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        primaryStage.setTitle("Planar Graph Drawing with Cyclic Nodes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to calculate node positions in a circular layout
    private List<double[]> getCircularLayout(int numNodes, double centerX, double centerY, double radius) {
        List<double[]> positions = new ArrayList<>();
        double angleIncrement = 2 * Math.PI / numNodes;
        for (int i = 0; i < numNodes; i++) {
            double angle = i * angleIncrement;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            positions.add(new double[]{x, y});
        }
        return positions;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

