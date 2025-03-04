package org.openjfx.fx;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author blj0011
 */
public class JavaFXApplication122 extends Application
{

    double orgSceneX, orgSceneY;//Used to help keep up with change in mouse position

    @Override
    public void start(Stage primaryStage)
    {
    	double width = 100;
    	double height = 50;
        double RATIO = height/width;  //The ration of height to width is 1/2

        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setX(400 - 50);
        rectangle.setY(250 - 25);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);

        //Circles will be used to do the event handling/movements
        Circle leftAnchor = new Circle(400 - 50, 250, 5);
        Circle topLeftAnchor = new Circle(400-50,250-25,5);
        Circle bottomleftAnchor = new Circle(400 - 50, 250+25, 5);
        
        
        Circle topAnchor = new Circle(400, 250 - 25, 5);
        Circle bottomAnchor = new Circle(400, 250 + 25, 5);
        
        Circle topRightAnchor = new Circle(400+50, 250-25, 5);
        Circle rightAnchor = new Circle(400+50, 250, 5);
        Circle bottomRightAnchor = new Circle(400+50,250+25,5);
        
        
        
        leftAnchor.setOnMouseDragEntered((event) -> {
            ((Circle) event.getSource()).getScene().setCursor(Cursor.MOVE);
        });
        leftAnchor.setOnMousePressed((event) -> {
            orgSceneX = event.getSceneX();//Store current mouse position
        });
        leftAnchor.setOnMouseDragged((event) -> {
            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position

        
            rectangle.setX(event.getSceneX());//move rectangle left side with mouse
            rectangle.setWidth(rectangle.getWidth() - offSetX);//Change rectangle's width with movement of mouse
            leftAnchor.setCenterX(leftAnchor.getCenterX() + offSetX);
            
            // re- align the anchor circles
            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);
            bottomleftAnchor.setCenterX(event.getSceneX());
            topLeftAnchor.setCenterX(event.getSceneX());
            
            topRightAnchor.setCenterX(rectangle.getX() + rectangle.getWidth());
            rightAnchor.setCenterX(rectangle.getX() + rectangle.getWidth());
            bottomRightAnchor.setCenterX(rectangle.getX() + rectangle.getWidth());
            
            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        leftAnchor.setOnMouseExited((event) -> {
            leftAnchor.getScene().setCursor(null);
        });
        
        
        rightAnchor.setOnMouseDragEntered((event) -> {
            ((Circle) event.getSource()).getScene().setCursor(Cursor.MOVE);
        });
        rightAnchor.setOnMousePressed((event) -> {
            orgSceneX = event.getSceneX();//Store current mouse position
        });
        rightAnchor.setOnMouseDragged((event) -> {
            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position

            rectangle.setWidth(rectangle.getWidth() + offSetX);//Change rectangle's width with movement of mouse
          
            rightAnchor.setCenterX(rightAnchor.getCenterX() + offSetX);
            // re align the circle anchors
            topRightAnchor.setCenterX(topRightAnchor.getCenterX() + offSetX );
            bottomRightAnchor.setCenterX(bottomRightAnchor.getCenterX() + offSetX );
            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
          
            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        rightAnchor.setOnMouseExited((event) -> {
        	rightAnchor.getScene().setCursor(null);
        });
        
        bottomleftAnchor.setOnMouseDragEntered((event) -> {
            ((Circle) event.getSource()).getScene().setCursor(Cursor.MOVE);
        });
        bottomleftAnchor.setOnMousePressed((event) -> {
            orgSceneX = event.getSceneX();//Store current mouse position
        });
        bottomleftAnchor.setOnMouseDragged((event) -> {
            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
              
            bottomleftAnchor.setCenterX(event.getSceneX());
            bottomleftAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) );//Adjust the left circle with the growth of the rectangle
            
            rectangle.setX(event.getSceneX());//move rectangle left side with mouse
            rectangle.setWidth(rectangle.getWidth() - offSetX);//Change rectangle's width with movement of mouse
            rectangle.setHeight(rectangle.getWidth() * RATIO);//Change the height so that it meets the ratio requirements
            
            leftAnchor.setCenterX(event.getSceneX());
            leftAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));//Adjust the left circle with the growth of the rectangle

            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
            
            topLeftAnchor.setCenterX(event.getSceneX());
            topLeftAnchor.setCenterY((rectangle.getY() ) );//Adjust the left circle with the growth of the rectangle
            
            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
            bottomAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            topRightAnchor.setCenterX( rectangle.getX() + rectangle.getWidth());
            
            rightAnchor.setCenterX( rectangle.getX() + rectangle.getWidth());
            rightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));//Adjust the left circle with the growth of the rectangle
            
            bottomRightAnchor.setCenterX( rectangle.getX() + rectangle.getWidth());
            bottomRightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        bottomleftAnchor.setOnMouseExited((event) -> {
        	bottomleftAnchor.getScene().setCursor(null);
        });

        
        topLeftAnchor.setOnMouseDragEntered((event) -> {
            ((Circle) event.getSource()).getScene().setCursor(Cursor.MOVE);
        });
        topLeftAnchor.setOnMousePressed((event) -> {
            orgSceneX = event.getSceneX();//Store current mouse position
            orgSceneY = event.getSceneY();//Store current mouse position
        });
        topLeftAnchor.setOnMouseDragged((event) -> {
            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse Y position
            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse Y position
            System.out.println("offSetX :"+offSetX);
            System.out.println("offSetY :"+offSetY);
            
            topLeftAnchor.setCenterY(event.getSceneY());
            topLeftAnchor.setCenterX(event.getSceneX());
            
            rectangle.setY(event.getSceneY());//move rectangle top side with mouse
            rectangle.setX(event.getSceneX());//move rectangle top side with mouse
            rectangle.setHeight(rectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse  
            rectangle.setWidth(rectangle.getWidth() - offSetX );//Change the width so that it meets the ratio requirements
            
            // re-align the circles
            leftAnchor.setCenterX(rectangle.getX());
            leftAnchor.setCenterY( (rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            bottomleftAnchor.setCenterX(rectangle.getX());
            bottomleftAnchor.setCenterY(rectangle.getY() + rectangle.getHeight());
            
            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
            topAnchor.setCenterY(rectangle.getY());//Adjust top circle as rectangle's size change
        
            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);
            bottomAnchor.setCenterY(rectangle.getY() + rectangle.getHeight());
            
            topRightAnchor.setCenterX(rectangle.getX() + rectangle.getWidth()  + offSetX / 2);
            topRightAnchor.setCenterY(event.getSceneY());
        
            rightAnchor.setCenterX(rectangle.getX() + + rectangle.getWidth() + offSetX / 2);
            rightAnchor.setCenterY( (rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            bottomRightAnchor.setCenterX(rectangle.getX() + + rectangle.getWidth() + offSetX / 2);
            bottomRightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        topLeftAnchor.setOnMouseExited((event) -> {
        	topLeftAnchor.getScene().setCursor(null);
        });
        
        
        topAnchor.setOnMouseDragEntered((event) -> {
            topAnchor.getScene().setCursor(Cursor.MOVE);
        });
        topAnchor.setOnMousePressed((event) -> {
            orgSceneY = event.getSceneY();//store current mouse position
        });
        topAnchor.setOnMouseDragged((event) -> {
            double offSetY = event.getSceneY() - orgSceneY;

            topAnchor.setCenterY(event.getSceneY());
            rectangle.setY(event.getSceneY());//move rectangle top side with mouse
            rectangle.setHeight(rectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse
            
            // re-aalign the centers
            topRightAnchor.setCenterY(event.getSceneY());
            topRightAnchor.setCenterX((rectangle.getX() + rectangle.getWidth()));//Adjust the top circle with the growth of the rectangle

            topLeftAnchor.setCenterY(event.getSceneY());
            topLeftAnchor.setCenterX(rectangle.getX() );//Adjust the top circle with the growth of the rectangle
            
            leftAnchor.setCenterX(rectangle.getX());
            leftAnchor.setCenterY( (rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            rightAnchor.setCenterX(rectangle.getX() +  rectangle.getWidth() );
            rightAnchor.setCenterY( (rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            bottomleftAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            bottomAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            bottomRightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        topAnchor.setOnMouseExited((event) -> {
            topAnchor.getScene().setCursor(null);
        });

        
        topRightAnchor.setOnMouseDragEntered((event) -> {
        	topRightAnchor.getScene().setCursor(Cursor.MOVE);
        });
        topRightAnchor.setOnMousePressed((event) -> {
            orgSceneY = event.getSceneY();//store current mouse position
            orgSceneX = event.getSceneX();//store current mouse position
        });
        topRightAnchor.setOnMouseDragged((event) -> {
            double offSetY = event.getSceneY() - orgSceneY;
            double offSetX = event.getSceneX() - orgSceneX;

          
            rectangle.setY(event.getSceneY());//move rectangle top side with mouse
            rectangle.setHeight(rectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse           
            rectangle.setWidth(rectangle.getHeight() * (1 / RATIO));//Change the width so that it meets the ratio requirements
            
            topRightAnchor.setCenterY(event.getSceneY());
            topRightAnchor.setCenterX((rectangle.getX() + rectangle.getWidth()) );//Adjust the top circle with the growth of the rectangle
            
        
            // re align the center anchors
            topAnchor.setCenterX(rectangle.getX() + rectangle.getWidth()/2 );//Adjust top circle as rectangle's size change
            topAnchor.setCenterY(rectangle.getY());//Adjust top circle as rectangle's size change
        
            bottomAnchor.setCenterX(rectangle.getX() + rectangle.getWidth()/2);
            bottomAnchor.setCenterY(rectangle.getY() + rectangle.getHeight());
            
            rightAnchor.setCenterX((rectangle.getX() + rectangle.getWidth()) );
            rightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            bottomRightAnchor.setCenterX(rectangle.getX() + rectangle.getWidth());
            bottomRightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            leftAnchor.setCenterX(leftAnchor.getCenterX());
            leftAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            topLeftAnchor.setCenterX(topLeftAnchor.getCenterX());
            topLeftAnchor.setCenterY(rectangle.getY());
            
            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        topRightAnchor.setOnMouseExited((event) -> {
        	topRightAnchor.getScene().setCursor(null);
        });

        
        bottomAnchor.setOnMouseDragEntered((event) -> {
        	bottomAnchor.getScene().setCursor(Cursor.MOVE);
        });
        bottomAnchor.setOnMousePressed((event) -> {
            orgSceneY = event.getSceneY();//store current mouse position
        });
        bottomAnchor.setOnMouseDragged((event) -> {
            double offSetY = event.getSceneY() - orgSceneY;
            
            rectangle.setHeight(rectangle.getHeight() + offSetY);//Change rectangle's height with movement of mouse   
            bottomAnchor.setCenterY(bottomAnchor.getCenterY() + offSetY);
            
            // re-align the circle anchors
            bottomleftAnchor.setCenterY(bottomleftAnchor.getCenterY() + offSetY);
            bottomRightAnchor.setCenterY(bottomRightAnchor.getCenterY() + offSetY);
            leftAnchor.setCenterY(leftAnchor.getCenterY() + offSetY / 2);
            rightAnchor.setCenterY(rightAnchor.getCenterY() + offSetY / 2);
            
            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        bottomAnchor.setOnMouseExited((event) -> {
        	bottomAnchor.getScene().setCursor(null);
        });
        
        
        bottomRightAnchor.setOnMouseDragEntered((event) -> {
        	bottomRightAnchor.getScene().setCursor(Cursor.MOVE);
        });
        bottomRightAnchor.setOnMousePressed((event) -> {
        	orgSceneX = event.getSceneX();//store current mouse position
        });
        bottomRightAnchor.setOnMouseDragged((event) -> {
        	double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
            
            rectangle.setWidth(rectangle.getWidth() + offSetX);//Change rectangle's width with movement of mouse
            rectangle.setHeight(rectangle.getWidth() * RATIO);//Change the height so that it meets the ratio requirements
            
            bottomRightAnchor.setCenterX(event.getSceneX());
            bottomRightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) );//Adjust the left circle with the growth of the rectangle
            
            // re-align the circle anchors
            topRightAnchor.setCenterY(topRightAnchor.getCenterY());
            topRightAnchor.setCenterX(event.getSceneX());
            
            bottomleftAnchor.setCenterX(bottomleftAnchor.getCenterX());
            bottomleftAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()));
            
            topAnchor.setCenterX(rectangle.getX() + rectangle.getWidth()/2 );//Adjust top circle as rectangle's size change
            topAnchor.setCenterY(rectangle.getY());//Adjust top circle as rectangle's size change
        
            bottomAnchor.setCenterX(rectangle.getX() + rectangle.getWidth()/2);
            bottomAnchor.setCenterY(rectangle.getY() + rectangle.getHeight());
            
            rightAnchor.setCenterX((rectangle.getX() + rectangle.getWidth()) );
            rightAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            leftAnchor.setCenterX(leftAnchor.getCenterX());
            leftAnchor.setCenterY((rectangle.getY() + rectangle.getHeight()) - (rectangle.getHeight() / 2));
            
            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
        });
        bottomRightAnchor.setOnMouseExited((event) -> {
        	bottomRightAnchor.getScene().setCursor(null);
        });

        
        Pane root = new Pane();
        root.getChildren().addAll(rectangle, leftAnchor,bottomleftAnchor,topLeftAnchor,topAnchor,topRightAnchor,bottomRightAnchor,bottomAnchor,rightAnchor);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
