package org.openjfx.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import java.util.Stack;

public class ShapeDragDropUndo extends Application {

    private Pane mainPane = new Pane();
    private Stack<Runnable> undoStack = new Stack<>();
    private Shape draggedShape = null;
    private Shape ghostShape = null;
    private double dragOffsetX, dragOffsetY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        VBox sidePanel = createSidePanel();
        Button undoButton = new Button("Undo");
        undoButton.setOnAction(e -> undoLastAction());
        
        root.setLeft(sidePanel);
        root.setCenter(mainPane);
        root.setBottom(undoButton);
        
        setupMainPane();
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Shape Drag & Drop with Ghost Effect");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidePanel() {
        VBox sidePanel = new VBox(10);
        sidePanel.setStyle("-fx-background-color: lightgray; -fx-padding: 10;");
        sidePanel.setPrefWidth(150);
        
        Rectangle rectangle = new Rectangle(50, 50, Color.BLUE);
        rectangle.setUserData("Rectangle");
        makeDraggableFromPanel(rectangle);
        
        Circle circle = new Circle(25, Color.RED);
        circle.setUserData("Circle");
        makeDraggableFromPanel(circle);
        
        sidePanel.getChildren().addAll(rectangle, circle);
        return sidePanel;
    }

    private void makeDraggableFromPanel(Shape shape) {
        shape.setOnMousePressed(e -> {
            // Create a ghost shape (translucent version)
            ghostShape = createShapeCopy(shape);
            ghostShape.setOpacity(0.5);
            ghostShape.setLayoutY(e.getSceneY());
            ghostShape.setLayoutX(e.getSceneX());
            mainPane.getChildren().add(ghostShape);
            
            // Calculate offset from mouse position to shape position
            dragOffsetX = e.getX();
            dragOffsetY = e.getY();
            
            e.consume();
        });
        
        shape.setOnMouseDragged(e -> {
            if (ghostShape != null) {
                ghostShape.setLayoutX(e.getSceneX() );
                ghostShape.setLayoutY(e.getSceneY() );
                e.consume();
            }
        });
        
        shape.setOnMouseReleased(e -> {
            if (ghostShape != null) {
                // Create the actual shape at the drop location
                Shape newShape = createShapeCopy(shape);
                newShape.setLayoutX(ghostShape.getLayoutX());
                newShape.setLayoutY(ghostShape.getLayoutY());
                mainPane.getChildren().add(newShape);
                makeDraggableInMainPane(newShape);
                
                // Add to undo stack
                Shape finalShape = newShape;
                undoStack.push(() -> mainPane.getChildren().remove(finalShape));
                
                // Remove the ghost shape
                mainPane.getChildren().remove(ghostShape);
                ghostShape = null;
                
                e.consume();
            }
        });
    }

    private void setupMainPane() {
        mainPane.setStyle("-fx-background-color: white;");
        mainPane.setOnDragOver(e -> e.acceptTransferModes(javafx.scene.input.TransferMode.ANY));
    }

    private void makeDraggableInMainPane(Shape shape) {
        shape.setOnMousePressed(e -> {
            if (e.isSecondaryButtonDown()) {
                removeShape(shape);
                e.consume();
            } else {
                draggedShape = shape;
                dragOffsetX = e.getX();
                dragOffsetY = e.getY();
                
                double originalX = shape.getLayoutX();
                double originalY = shape.getLayoutY();
                
                undoStack.push(() -> {
                    shape.setLayoutX(originalX);
                    shape.setLayoutY(originalY);
                });
                
                e.consume();
            }
        });
        
        shape.setOnMouseDragged(e -> {
            if (draggedShape != null) {
                draggedShape.setLayoutX(e.getSceneX() - dragOffsetX);
                draggedShape.setLayoutY(e.getSceneY() - dragOffsetY);
                e.consume();
            }
        });
        
        shape.setOnMouseReleased(e -> {
            draggedShape = null;
            e.consume();
        });
    }

    private Shape createShapeCopy(Shape original) {
        if (original instanceof Rectangle) {
            Rectangle rect = (Rectangle) original;
            Rectangle copy = new Rectangle(rect.getWidth(), rect.getHeight(), rect.getFill());
            copy.setStroke(rect.getStroke());
            return copy;
        } else if (original instanceof Circle) {
            Circle circle = (Circle) original;
            Circle copy = new Circle(circle.getRadius(), circle.getFill());
            copy.setStroke(circle.getStroke());
            return copy;
        }
        return null;
    }

    private void removeShape(Shape shape) {
        undoStack.push(() -> mainPane.getChildren().add(shape));
        mainPane.getChildren().remove(shape);
    }

    private void undoLastAction() {
        if (!undoStack.isEmpty()) {
            undoStack.pop().run();
        }
    }
}
