package org.openjfx.fx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BirdsEyeView extends Application {

    private static final double OVERVIEW_SCALE = 0.2; // 20% scale for the bird's eye view

    @Override
    public void start(Stage primaryStage) {
        // Main content pane (e.g., a large canvas or complex UI)
        Pane mainContent = createMainContent();

        // Wrap the main content in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(mainContent);
   //     scrollPane.setFitToWidth(true);
   //     scrollPane.setFitToHeight(true);

        // Create the bird's eye view
        StackPane overview = createBirdsEyeView(mainContent, scrollPane);

        // Root layout: Main ScrollPane + Overview in the corner
        BorderPane root = new BorderPane(scrollPane);
        root.setRight(overview);
        BorderPane.setAlignment(overview, Pos.BOTTOM_RIGHT);

        // Set up the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Bird's Eye View (Fixed)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane createMainContent() {
        Pane pane = new Pane();
        pane.setPrefSize(2000, 2000); // Large content area
        pane.setStyle("-fx-background-color: lightgray;");

        // Add sample content (e.g., rectangles)
        for (int i = 0; i < 10; i++) {
            Rectangle rect = new Rectangle(100 + i * 150, 100 + i * 150, 200, 200);
            rect.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            pane.getChildren().add(rect);
        }
        return pane;
    }

    private StackPane createBirdsEyeView(Pane mainContent, ScrollPane scrollPane) {
        // Create a scaled snapshot of the main content
        WritableImage snapshot = mainContent.snapshot(null, null);
        ImageView overviewImage = new ImageView(snapshot);
        overviewImage.setScaleX(OVERVIEW_SCALE);
        overviewImage.setScaleY(OVERVIEW_SCALE);

        // Create a viewport rectangle to show the visible area
        Rectangle viewportRect = new Rectangle();
        viewportRect.setFill(Color.TRANSPARENT);
        viewportRect.setStroke(Color.RED);
        viewportRect.setStrokeWidth(2);

        // Listener to update the viewport rectangle when scrolling occurs
        ChangeListener<Object> viewportUpdater = (obs, oldVal, newVal) -> {
            Bounds viewportBounds = scrollPane.getViewportBounds();
            Bounds contentBounds = mainContent.getBoundsInLocal();
            
            double visibleWidth = viewportBounds.getWidth();
            double visibleHeight = viewportBounds.getHeight();
            
            double hValue = scrollPane.getHvalue();
            double vValue = scrollPane.getVvalue();
            
            double contentWidth = contentBounds.getWidth();
            double contentHeight = contentBounds.getHeight();
            
            // Calculate visible area position
            double viewportX = hValue * (contentWidth - visibleWidth) * OVERVIEW_SCALE;
            double viewportY = vValue * (contentHeight - visibleHeight) * OVERVIEW_SCALE;
            
            // Update the rectangle
            viewportRect.setX(viewportX);
            viewportRect.setY(viewportY);
            viewportRect.setWidth(visibleWidth * OVERVIEW_SCALE);
            viewportRect.setHeight(visibleHeight * OVERVIEW_SCALE);
        };

        // Add listeners to all relevant properties
        scrollPane.viewportBoundsProperty().addListener(viewportUpdater);
        scrollPane.hvalueProperty().addListener(viewportUpdater);
        scrollPane.vvalueProperty().addListener(viewportUpdater);
        mainContent.boundsInLocalProperty().addListener(viewportUpdater);

        // Clicking/dragging the overview moves the main ScrollPane's viewport
        overviewImage.setOnMousePressed(e -> updateScrollPane(scrollPane, mainContent, e));
        overviewImage.setOnMouseDragged(e -> updateScrollPane(scrollPane, mainContent, e));

        // Container for the overview
        StackPane overview = new StackPane(overviewImage, viewportRect);
        overview.setStyle("-fx-background-color: white; -fx-border-color: darkgray;");
        overview.setMaxSize(400, 400); // Limit the overview size

        return overview;
    }

    private void updateScrollPane(ScrollPane scrollPane, Pane mainContent, javafx.scene.input.MouseEvent e) {
        Bounds contentBounds = mainContent.getBoundsInLocal();
        Bounds viewportBounds = scrollPane.getViewportBounds();
        
        double scaledX = e.getX() / OVERVIEW_SCALE;
        double scaledY = e.getY() / OVERVIEW_SCALE;
        
        double contentWidth = contentBounds.getWidth();
        double contentHeight = contentBounds.getHeight();
        double visibleWidth = viewportBounds.getWidth();
        double visibleHeight = viewportBounds.getHeight();
        
        double maxH = contentWidth - visibleWidth;
        double maxV = contentHeight - visibleHeight;
        
        double hvalue = scaledX / contentWidth;
        double vvalue = scaledY / contentHeight;
        
        scrollPane.setHvalue(Math.max(0, Math.min(hvalue, 1)));
        scrollPane.setVvalue(Math.max(0, Math.min(vvalue, 1)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}