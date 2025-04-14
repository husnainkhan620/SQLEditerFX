package org.openjfx.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class FixedSizeColumnVisualization extends Application {

    // Constants for table
    private static final int TABLE_WIDTH = 100;
    private static final int TABLE_HEIGHT = 100;
    
    // Constants for columns
    private static final int COLUMN_RADIUS_X = 40;
    private static final int COLUMN_RADIUS_Y = 20;
    private static final int COLUMNS_COUNT = 50;
    private static final int MAX_COLUMNS_PER_RING = 12;
    private static final int RING_SPACING = 100;
    
    // Main container dimensions
    private static final int CONTAINER_WIDTH = 2000;
    private static final int CONTAINER_HEIGHT = 2000;

    private double scale = 1.0;
    
    @Override
    public void start(Stage primaryStage) {
        // Create a large drawing pane
        Pane drawingPane = new Pane();
        drawingPane.setPrefSize(CONTAINER_WIDTH, CONTAINER_HEIGHT);
        
        // Center position in the large container
        double centerX = CONTAINER_WIDTH / 2;
        double centerY = CONTAINER_HEIGHT / 2;
        
        // Generate column names
        List<String> columnNames = generateColumnNames(COLUMNS_COUNT);
        
        // Create the table
        createTable(drawingPane, centerX, centerY, "BigTable");
        
        // Create columns in multiple rings
        createColumnRings(drawingPane, centerX, centerY, columnNames);
        
        // Create scroll pane and configure it
        ScrollPane scrollPane = new ScrollPane(drawingPane);
        scrollPane.setFitToWidth(false);  // Allow horizontal scrolling
        scrollPane.setFitToHeight(false);  // Allow vertical scrolling
        scrollPane.setPannable(true);      // Enable click-and-drag panning
        
        // Set initial viewport position to center
        scrollPane.setHvalue(scrollPane.getHmax() / 2);
        scrollPane.setVvalue(scrollPane.getVmax() / 2);
        
        // Set up zoom functionality
        setupZoomHandlers(drawingPane);
        
        Scene scene = new Scene(scrollPane, 1200, 900);
        primaryStage.setTitle("Database Table with 50 Fixed-Size Columns (Scrollable)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setupZoomHandlers(Pane pane) {
        pane.setOnScroll(event -> {
            double zoomFactor = 1.05;
            if (event.getDeltaY() < 0) {
                zoomFactor = 1 / zoomFactor;
            }
            
            scale *= zoomFactor;
            pane.setScaleX(scale);
            pane.setScaleY(scale);
            event.consume();
        });
    }
    private List<String> generateColumnNames(int count) {
        List<String> names = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            names.add("Col_" + i);
        }
        return names;
    }
    
    private void createTable(Pane pane, double centerX, double centerY, String tableName) {
        Rectangle table = new Rectangle(
            centerX - TABLE_WIDTH/2, 
            centerY - TABLE_HEIGHT/2, 
            TABLE_WIDTH, 
            TABLE_HEIGHT
        );
        table.setFill(Color.LIGHTBLUE);
        table.setStroke(Color.DARKBLUE);
        table.setStrokeWidth(2);
        
        Text nameText = new Text(centerX - 40, centerY, tableName);
        nameText.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        
        pane.getChildren().addAll(table, nameText);
    }
    
    private void createColumnRings(Pane pane, double centerX, double centerY, List<String> columnNames) {
        int columnsPlaced = 0;
        int ringNumber = 0;
        
        while (columnsPlaced < columnNames.size()) {
            int columnsInThisRing = Math.min(MAX_COLUMNS_PER_RING, columnNames.size() - columnsPlaced);
            
            double radius = 150 + ringNumber * RING_SPACING;
            
            for (int i = 0; i < columnsInThisRing; i++) {
                double angle = 2 * Math.PI * i / columnsInThisRing;
                createColumn(
                    pane, centerX, centerY,
                    radius, angle, 
                    columnNames.get(columnsPlaced + i)
                );
            }
            
            columnsPlaced += columnsInThisRing;
            ringNumber++;
        }
    }
    
    private void createColumn(Pane pane, double centerX, double centerY, 
                            double radius, double angle, String name) {
        // Calculate position
        double x = centerX + radius * Math.cos(angle);
        double y = centerY + radius * Math.sin(angle);
        
        // Create ellipse (fixed size)
        Ellipse column = new Ellipse(x, y, COLUMN_RADIUS_X, COLUMN_RADIUS_Y);
        column.setFill(Color.LIGHTGREEN);
        column.setStroke(Color.DARKGREEN);
        column.setStrokeWidth(1.5);
        
        // Add column name
        Text columnName = new Text(x - COLUMN_RADIUS_X, y - COLUMN_RADIUS_Y - 5, name);
        columnName.setStyle("-fx-font-size: 10;");
        
        // Only show text for first two rings to avoid clutter
        if (radius > 250) {
            columnName.setVisible(false);
            column.setOnMouseEntered(e -> columnName.setVisible(true));
            column.setOnMouseExited(e -> columnName.setVisible(false));
        }
        
        // Add connecting line
        Line connector = new Line(
            x - COLUMN_RADIUS_X * Math.cos(angle), 
            y - COLUMN_RADIUS_Y * Math.sin(angle),
            centerX + (TABLE_WIDTH/2 - 5) * Math.cos(angle), 
            centerY + (TABLE_HEIGHT/2 - 5) * Math.sin(angle)
        );
        connector.setStroke(Color.GRAY);
        connector.getStrokeDashArray().addAll(2d);
        
        pane.getChildren().addAll(column, columnName, connector);
    }

    public static void main(String[] args) {
        launch(args);
    }
}