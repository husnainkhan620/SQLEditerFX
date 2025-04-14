package org.openjfx.fx;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.openfx.erdiagram.ERModelApplication;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class CirclePolarCoardinates extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(size.getHeight() + "<--->" +size.getWidth());
		
		Pane pane = new Pane();
		pane.setId("myPane");
		
	    Scene scene = new Scene(pane, 800, 600/* Color.rgb(35, 39, 50) */);  
		primaryStage.setTitle("ER Model ");   
		primaryStage.setScene(scene);
					    
		primaryStage.show();
				
		double circle1X = 500,circle1Y=200,circle1Radius = 50;
		Circle circle1 = new Circle(circle1X,circle1Y,circle1Radius);
		circle1.setStroke(Color.BLACK);
		circle1.setFill(Color.GAINSBORO);
				
		Line simpleLine = new Line();
		simpleLine.setStartX(circle1X-200);
		simpleLine.setStartY(circle1Y);
		simpleLine.setEndX(circle1X);
		simpleLine.setEndY(circle1Y);
		

		double circle2X = circle1X-200,circle2Y=circle1Y,circle2Radius = 50;
		Circle circle2 = new Circle(circle2X,circle2Y,circle2Radius);
		circle2.setStroke(Color.BLACK);
		circle2.setFill(Color.GAINSBORO);
		
		Line parpendicularLine1 = new Line();
		Line parpendicularLine2 = new Line();
		
		Line parpendicularLineO1 = new Line();
		Line parpendicularLineO2 = new Line();
		
		Line parallerLine1 = new Line();
		Line parallerLine2 = new Line();
		
		simpleLine.setOnMouseEntered(event ->{
			((Line) event.getSource()).getScene().setCursor(Cursor.MOVE);
			
		});
		
		simpleLine.setOnMouseExited(event ->{
			 ((Line) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
			
		});
		
		simpleLine.setOnMouseDragged(event ->{
			    double newX = event.getSceneX();
	            double newY = event.getSceneY();

	        	simpleLine.setStartX(newX);
	        	simpleLine.setStartY(newY);
	        	
	        	circle2.setCenterX(newX);
	        	circle2.setCenterY(newY);
	        	
	        	
	        	double slopeOfLine = (simpleLine.getEndY() - simpleLine.getStartY())/(simpleLine.getEndX() - simpleLine.getStartX());
	    		System.out.println("Slope of the line:"+ slopeOfLine);
	    		
	    		final double deltaY = (simpleLine.getEndY() - simpleLine.getStartY());
	    		final double deltaX = (simpleLine.getEndX() - simpleLine.getStartX());
	    		final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));   // angle between line and x - axis in co-ordinates
	    		System.out.println("Angle (x) of the line :"+ result);
	    		
	    		final double deltaYO = (simpleLine.getStartY() - simpleLine.getEndY());
	    		final double deltaXO = (simpleLine.getStartX() - simpleLine.getEndX());
	    		final double resultO = Math.toDegrees(Math.atan2(deltaYO, deltaXO));   // angle between line and x - axis in co-ordinates
	    		System.out.println("Angle (x) of the line :"+ resultO);
	    		
	    		double x1,y1,x2,y2 ;
	    		double xO1,yO1,xO2,yO2 ;
	    		
	    		x1 = circle1X - 50 * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
	    		y1 = circle1Y - 50 * Math.sin(Math.toRadians(result+90));    // a - rsin0
	    		
	    		x2 = circle1X - 50 * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
	    		y2 = circle1Y - 50 * Math.sin(Math.toRadians(result-90));    // a - rsin0
	    		 
	    		System.out.println("x1 -->"+x1);
	    		System.out.println("y1--->"+y1);
	    		System.out.println("x2 -->"+x2);
	    		System.out.println("y2--->"+y2);
	    		  
	    		parpendicularLine1.setStartX(circle1X);
	    		parpendicularLine1.setStartY(circle1Y);
	    		parpendicularLine1.setEndX(x1);
	    		parpendicularLine1.setEndY(y1);
	    		 
	    		parpendicularLine2.setStartX(circle1X);
	    		parpendicularLine2.setStartY(circle1Y);
	    		parpendicularLine2.setEndX(x2);
	    		parpendicularLine2.setEndY(y2);
	    		
	    		
	    		xO1 = circle2.getCenterX() - 50 * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
	    		yO1 = circle2.getCenterY() - 50 * Math.sin(Math.toRadians(result+90));    // a - rsin0
	    		
	    		xO2 = circle2.getCenterX() - 50 * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
	    		yO2 = circle2.getCenterY() - 50 * Math.sin(Math.toRadians(result-90));    // a - rsin0
	    		 
	    		System.out.println("x1 -->"+xO1);
	    		System.out.println("y1--->"+yO1);
	    		System.out.println("x2 -->"+xO2);
	    		System.out.println("y2--->"+yO2);
	    		  
	    		parpendicularLineO1.setStartX(circle2.getCenterX());
	    		parpendicularLineO1.setStartY(circle2.getCenterY());
	    		parpendicularLineO1.setEndX(xO1);
	    		parpendicularLineO1.setEndY(yO1);
	    		 
	    		parpendicularLineO2.setStartX(circle2.getCenterX());
	    		parpendicularLineO2.setStartY(circle2.getCenterY());
	    		parpendicularLineO2.setEndX(xO2);
	    		parpendicularLineO2.setEndY(yO2);
	    		
	    		parallerLine1.setStartX(xO1);
	    		parallerLine1.setStartY(yO1);
	    		parallerLine1.setEndX(x1);
	    		parallerLine1.setEndY(y1);
	
	    		parallerLine2.setStartX(xO2);
	    		parallerLine2.setStartY(yO2);
	    		parallerLine2.setEndX(x2);
	    		parallerLine2.setEndY(y2);
	
	    		
		});
		
		pane.getChildren().addAll(/*circle1,circle2,*/simpleLine,/* parpendicularLine1,parpendicularLine2,parpendicularLineO1,parpendicularLineO2,*/parallerLine1,parallerLine2);
		
		scene.getStylesheets().add(ERModelApplication.class.getResource("/testLayout.css").toExternalForm());
				
	}
	
	public static void main(String[] args) {
		   launch(CirclePolarCoardinates.class, args);
	}

}
