package com.openfx.ermodel;

import com.sun.javafx.geom.Point2D;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TotalParticipation extends ERModel{
	
	public Line sampleLine1;
	public Line parpendicularLineLeft1;
	public Line parpendicularLineLeft2;
	public Line parpendicularLineRight1;
	public Line parpendicularLineRight2;
	public Line parallerLine1;
	public Line parallerLine2;
	public ChoiceBox<String> choiceBox;
	public Label cardinalTextLabel;
	Point2D startpoint2D ;
	Point2D endpoint2D;
	double distanceofLinePoints;
	public Group group;
	
	double orgSceneX, orgSceneY;//Used to help keep up with change in mouse position
	double diameter = 5;
	double deltaY ;
	double deltaX;
	double result; 

	double x1,y1,x2,y2 ;
	double xO1,yO1,xO2,yO2 ;
	
	public TotalParticipation(double startX,double startY,double endX,double endY,Pane mainPane) {
		
		group = new Group();
		leftAnchor = new Circle(startX, startY, diameter);
		 
		rightAnchor = new Circle(endX, endY, diameter);
		
		startpoint2D = new Point2D((float)startX,(float)startY);
		endpoint2D = new Point2D((float)endX,(float)endY);
		distanceofLinePoints = startpoint2D.distance(endpoint2D);
		 
		startpoint2D = new Point2D((float)startX,(float)startY);
		endpoint2D = new Point2D((float)endX,(float)endY);
		distanceofLinePoints = startpoint2D.distance(endpoint2D);

		sampleLine1 = new Line();
		sampleLine1.setStroke(Color.GAINSBORO);  // disable this to see the lines
		sampleLine1.setStartX(startX);
		sampleLine1.setStartY(startY);
		sampleLine1.setEndX(endX);
		sampleLine1.setEndY(endY);
	
		parpendicularLineLeft1 = new Line();
		parpendicularLineLeft1.setStroke(Color.GAINSBORO);  // disable this to see the lines
		parpendicularLineLeft2 = new Line();
		parpendicularLineLeft2.setStroke(Color.GAINSBORO);  // disable this to see the lines
		
		parpendicularLineRight1 = new Line();
		parpendicularLineRight1.setStroke(Color.GAINSBORO);  // disable this to see the lines
		parpendicularLineRight2 = new Line();
		parpendicularLineRight2.setStroke(Color.GAINSBORO);  // disable this to see the lines
		
		parallerLine1 = new Line();
		parallerLine2 = new Line();
		
		deltaY = (sampleLine1.getEndY() - sampleLine1.getStartY());
		deltaX = (sampleLine1.getEndX() - sampleLine1.getStartX());
		result = Math.toDegrees(Math.atan2(deltaY, deltaX)); 
		System.out.println("Angle (x) of the line :"+ result);
	
		
		x1 = leftAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
		y1 = leftAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result+90));    // a - rsin0
		
		x2 = leftAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
		y2 = leftAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result-90));    // a - rsin0
		 
		System.out.println("x1 -->"+x1);
		System.out.println("y1--->"+y1);
		System.out.println("x2 -->"+x2);
		System.out.println("y2--->"+y2);
		  
		parpendicularLineLeft1.setStartX(leftAnchor.getCenterX());
		parpendicularLineLeft1.setStartY(leftAnchor.getCenterY());
		parpendicularLineLeft1.setEndX(x1);
		parpendicularLineLeft1.setEndY(y1);
		
		parpendicularLineLeft2.setStartX(leftAnchor.getCenterX());
		parpendicularLineLeft2.setStartY(leftAnchor.getCenterY());
		parpendicularLineLeft2.setEndX(x2);
		parpendicularLineLeft2.setEndY(y2);
		
		xO1 = rightAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
		yO1 = rightAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result+90));    // a - rsin0
		
		xO2 = rightAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
		yO2 = rightAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result-90));    // a - rsin0
		 
		System.out.println("x1 -->"+xO1);
		System.out.println("y1--->"+yO1);
		System.out.println("x2 -->"+xO2);
		System.out.println("y2--->"+yO2);
		  
		parpendicularLineRight1.setStartX(rightAnchor.getCenterX());
		parpendicularLineRight1.setStartY(rightAnchor.getCenterY());
		parpendicularLineRight1.setEndX(xO1);
		parpendicularLineRight1.setEndY(yO1);
		 
		parpendicularLineRight2.setStartX(rightAnchor.getCenterX());
		parpendicularLineRight2.setStartY(rightAnchor.getCenterY());
		parpendicularLineRight2.setEndX(xO2);
		parpendicularLineRight2.setEndY(yO2);
	
		parallerLine1.setStartX(xO1);
		parallerLine1.setStartY(yO1);
		parallerLine1.setEndX(x1);
		parallerLine1.setEndY(y1);

		parallerLine2.setStartX(xO2);
		parallerLine2.setStartY(yO2);
		parallerLine2.setEndX(x2);
		parallerLine2.setEndY(y2);
		
		cardinalTextLabel = new Label("N");
		cardinalTextLabel.setLayoutX(startX+distanceofLinePoints/2);
		cardinalTextLabel.setLayoutY(startY);

		
		
		choiceBox = new ChoiceBox<String>();
		choiceBox.setLayoutX(startX+distanceofLinePoints/2-30);
		choiceBox.setLayoutY(startY);
		choiceBox.setMaxSize(20, 15);
		choiceBox.getItems().addAll("N","M","1");
		choiceBox.getSelectionModel().select(cardinalTextLabel.getText());
		choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("Selected Choice is "+newValue);
				cardinalTextLabel.setText(newValue);
			}
		}
		);
		
		group.getChildren().addAll(sampleLine1,parallerLine1,parallerLine2,cardinalTextLabel);
		
		mainPane.getChildren().addAll(group);
		
	//	enableDragAndDrop(this,mainPane);
		
		sampleLine1.setOnMouseEntered(event ->{
			((Line) event.getSource()).getScene().setCursor(Cursor.MOVE);
			
		});
		
		sampleLine1.setOnMouseExited(event ->{
			 ((Line) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
			
		});
		
		sampleLine1.setOnMousePressed(event ->{
			System.out.println("Mouse Clicked this sampleLine1");
			//	stackPaneRectangle.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, null, null))); 
		//	resizeRectangle.setStroke(Color.BLACK);
	
			group.getChildren().clear();
			
			group.getChildren().addAll(sampleLine1,parallerLine1,parallerLine2,choiceBox);
			
			if(!mainPane.getChildren().contains(leftAnchor)) {
				leftAnchor.setFill(Color.GAINSBORO);
				leftAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(leftAnchor);

			}
			
			if(!mainPane.getChildren().contains(rightAnchor)) {
				rightAnchor.setFill(Color.GAINSBORO);
				rightAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(rightAnchor);
			}
			
			
			
			if(!mainPane.getChildren().contains(parpendicularLineLeft1)) {
				mainPane.getChildren().add(parpendicularLineLeft1);
			}
			if(!mainPane.getChildren().contains(parpendicularLineLeft2)) {
				mainPane.getChildren().add(parpendicularLineLeft2);
			}
			
			if(!mainPane.getChildren().contains(parpendicularLineRight1)) {
				mainPane.getChildren().add(parpendicularLineRight1);
			}
			if(!mainPane.getChildren().contains(parpendicularLineRight2)) {
				mainPane.getChildren().add(parpendicularLineRight2);
			}
			
			orgSceneX = event.getSceneX();//Store current mouse position
	        orgSceneY = event.getSceneY();//Store current mouse position
		});
	
	
		  // When dragging, update the TitledPane position
		sampleLine1.setOnMouseDragged(event -> {
			double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	        double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
		    
	        
            leftAnchor.setCenterX(leftAnchor.getCenterX() + offSetX);
            leftAnchor.setCenterY(leftAnchor.getCenterY() + offSetY);
            rightAnchor.setCenterX(rightAnchor.getCenterX() + offSetX);
            rightAnchor.setCenterY(rightAnchor.getCenterY() + offSetY);
            
            sampleLine1.setStartX( sampleLine1.getStartX() + offSetX);
            sampleLine1.setStartY(sampleLine1.getStartY() + offSetY);
            sampleLine1.setEndX(sampleLine1.getEndX() + offSetX);
            sampleLine1.setEndY(sampleLine1.getEndY() + offSetY);
    		
        	parallerLine1.setStartX(parallerLine1.getStartX() + offSetX);
    		parallerLine1.setStartY(parallerLine1.getStartY() + offSetY);
    		parallerLine1.setEndX(parallerLine1.getEndX() + offSetX);
    		parallerLine1.setEndY(parallerLine1.getEndY() + offSetY);

    		parallerLine2.setStartX(parallerLine2.getStartX() + offSetX);
    		parallerLine2.setStartY(parallerLine2.getStartY() + offSetY);
    		parallerLine2.setEndX(parallerLine2.getEndX() + offSetX);
    		parallerLine2.setEndY(parallerLine2.getEndY() + offSetY);
    		
    		
    		orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	        orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
        	
        });
		
		
		   leftAnchor.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.E_RESIZE);
	        });
	        leftAnchor.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	            orgSceneY = event.getSceneY();//Store current mouse position
	        });
	        leftAnchor.setOnMouseDragged((event) -> {
	            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
	            
	            leftAnchor.setCenterX(leftAnchor.getCenterX() + offSetX);
	            leftAnchor.setCenterY(leftAnchor.getCenterY() + offSetY);
	            
	         
	            sampleLine1.setStartX(leftAnchor.getCenterX());
	    		sampleLine1.setStartY(leftAnchor.getCenterY());
	    		sampleLine1.setEndX(rightAnchor.getCenterX());
	    		sampleLine1.setEndY(rightAnchor.getCenterY());
	    		
	    	   
	    		startpoint2D = new Point2D((float)sampleLine1.getStartX(),(float)sampleLine1.getStartY());
	    		endpoint2D = new Point2D((float)sampleLine1.getEndX(),(float)sampleLine1.getEndY());
	    		distanceofLinePoints = startpoint2D.distance(endpoint2D);
	    		
	    		double slopeOfLine = (sampleLine1.getEndY() - sampleLine1.getStartY())/(sampleLine1.getEndX() - sampleLine1.getStartX());
	    		System.out.println("Slope of the line:"+ slopeOfLine);
	    		
	    		deltaY = (sampleLine1.getEndY() - sampleLine1.getStartY());
	    	    deltaX = (sampleLine1.getEndX() - sampleLine1.getStartX());
	    		result = Math.toDegrees(Math.atan2(deltaY, deltaX)); 
	    		System.out.println("Angle (x) of the line :"+ result);
	    	
	    		
	    		x1 = leftAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
	    		y1 = leftAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result+90));    // a - rsin0
	    		
	    		x2 = leftAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
	    		y2 = leftAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result-90));    // a - rsin0
	    		 
	    		System.out.println("x1 -->"+x1);
	    		System.out.println("y1--->"+y1);
	    		System.out.println("x2 -->"+x2);
	    		System.out.println("y2--->"+y2);
	    		  
	    		parpendicularLineLeft1.setStartX(leftAnchor.getCenterX());
	    		parpendicularLineLeft1.setStartY(leftAnchor.getCenterY());
	    		parpendicularLineLeft1.setEndX(x1);
	    		parpendicularLineLeft1.setEndY(y1);
	    		
	    		parpendicularLineLeft2.setStartX(leftAnchor.getCenterX());
	    		parpendicularLineLeft2.setStartY(leftAnchor.getCenterY());
	    		parpendicularLineLeft2.setEndX(x2);
	    		parpendicularLineLeft2.setEndY(y2);
	    		
	    		xO1 = rightAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
	    		yO1 = rightAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result+90));    // a - rsin0
	    		
	    		xO2 = rightAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
	    		yO2 = rightAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result-90));    // a - rsin0
	    		 
	    		System.out.println("x1 -->"+xO1);
	    		System.out.println("y1--->"+yO1);
	    		System.out.println("x2 -->"+xO2);
	    		System.out.println("y2--->"+yO2);
	    		  
	    		parpendicularLineRight1.setStartX(rightAnchor.getCenterX());
	    		parpendicularLineRight1.setStartY(rightAnchor.getCenterY());
	    		parpendicularLineRight1.setEndX(xO1);
	    		parpendicularLineRight1.setEndY(yO1);
	    		 
	    		parpendicularLineRight2.setStartX(rightAnchor.getCenterX());
	    		parpendicularLineRight2.setStartY(rightAnchor.getCenterY());
	    		parpendicularLineRight2.setEndX(xO2);
	    		parpendicularLineRight2.setEndY(yO2);
    		
	    		parallerLine1.setStartX(xO1);
	    		parallerLine1.setStartY(yO1);
	    		parallerLine1.setEndX(x1);
	    		parallerLine1.setEndY(y1);
	
	    		parallerLine2.setStartX(xO2);
	    		parallerLine2.setStartY(yO2);
	    		parallerLine2.setEndX(x2);
	    		parallerLine2.setEndY(y2);
	    		
	    		
	    		if(sampleLine1.getStartX() > sampleLine1.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine1.getStartX()-30);
	    			choiceBox.setLayoutX(sampleLine1.getStartX()-30-30);
	    		}
	    		else if(sampleLine1.getStartX() < sampleLine1.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine1.getStartX()+30);
	    			choiceBox.setLayoutX(sampleLine1.getStartX()+30);
	    		}
	    		if(sampleLine1.getStartY() > sampleLine1.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine1.getStartY()-30);
	    			choiceBox.setLayoutY(sampleLine1.getStartY()-30-30);
	    		}
	    		else if(sampleLine1.getStartY() < sampleLine1.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine1.getStartY()+30);
	    			choiceBox.setLayoutY(sampleLine1.getStartY()+30);
	    		}
	    		
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	        });
	        leftAnchor.setOnMouseExited((event) -> {
	            leftAnchor.getScene().setCursor(null);
	        });
	        
	        
	        rightAnchor.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.W_RESIZE);
	        });
	        rightAnchor.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	            orgSceneY = event.getSceneY();//Store current mouse position
	        });
	        rightAnchor.setOnMouseDragged((event) -> {
	            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
	         
	            rightAnchor.setCenterX(rightAnchor.getCenterX() + offSetX);
	            rightAnchor.setCenterY(rightAnchor.getCenterY() + offSetY);
	            
	            // re align the circle anchors
	            sampleLine1.setStartX(leftAnchor.getCenterX());
	    		sampleLine1.setStartY(leftAnchor.getCenterY());
	    		sampleLine1.setEndX(rightAnchor.getCenterX());
	    		sampleLine1.setEndY(rightAnchor.getCenterY());
	    		
	    		
	    		
	    		startpoint2D = new Point2D((float)sampleLine1.getStartX(),(float)sampleLine1.getStartY());
	    		endpoint2D = new Point2D((float)sampleLine1.getEndX(),(float)sampleLine1.getEndY());
	    		distanceofLinePoints = startpoint2D.distance(endpoint2D);
	    		
	    		double slopeOfLine = (sampleLine1.getEndY() - sampleLine1.getStartY())/(sampleLine1.getEndX() - sampleLine1.getStartX());
	    		System.out.println("Slope of the line:"+ slopeOfLine);
	    		
	    		deltaY = (sampleLine1.getEndY() - sampleLine1.getStartY());
	    		deltaX = (sampleLine1.getEndX() - sampleLine1.getStartX());
	    		result = Math.toDegrees(Math.atan2(deltaY, deltaX)); 
	    		System.out.println("Angle (x) of the line :"+ result);
	    		
	    		x1 = leftAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
	    		y1 = leftAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result+90));    // a - rsin0
	    		
	    		x2 = leftAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
	    		y2 = leftAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result-90));    // a - rsin0
	    		 
	    		System.out.println("x1 -->"+x1);
	    		System.out.println("y1--->"+y1);
	    		System.out.println("x2 -->"+x2);
	    		System.out.println("y2--->"+y2);
	    		  
	    		parpendicularLineLeft1.setStartX(leftAnchor.getCenterX());
	    		parpendicularLineLeft1.setStartY(leftAnchor.getCenterY());
	    		parpendicularLineLeft1.setEndX(x1);
	    		parpendicularLineLeft1.setEndY(y1);
	    		
	    		parpendicularLineLeft2.setStartX(leftAnchor.getCenterX());
	    		parpendicularLineLeft2.setStartY(leftAnchor.getCenterY());
	    		parpendicularLineLeft2.setEndX(x2);
	    		parpendicularLineLeft2.setEndY(y2);
	    		
	    		xO1 = rightAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result+90)) ;   // a - rcos0
	    		yO1 = rightAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result+90));    // a - rsin0
	    		
	    		xO2 = rightAnchor.getCenterX() - diameter * Math.cos(Math.toRadians(result-90)) ;   // a - rcos0
	    		yO2 = rightAnchor.getCenterY() - diameter * Math.sin(Math.toRadians(result-90));    // a - rsin0
	    		 
	    		System.out.println("x1 -->"+xO1);
	    		System.out.println("y1--->"+yO1);
	    		System.out.println("x2 -->"+xO2);
	    		System.out.println("y2--->"+yO2);
	    		  
	    		parpendicularLineRight1.setStartX(rightAnchor.getCenterX());
	    		parpendicularLineRight1.setStartY(rightAnchor.getCenterY());
	    		parpendicularLineRight1.setEndX(xO1);
	    		parpendicularLineRight1.setEndY(yO1);
	    		 
	    		parpendicularLineRight2.setStartX(rightAnchor.getCenterX());
	    		parpendicularLineRight2.setStartY(rightAnchor.getCenterY());
	    		parpendicularLineRight2.setEndX(xO2);
	    		parpendicularLineRight2.setEndY(yO2);
    		
	    		parallerLine1.setStartX(xO1);
	    		parallerLine1.setStartY(yO1);
	    		parallerLine1.setEndX(x1);
	    		parallerLine1.setEndY(y1);
	
	    		parallerLine2.setStartX(xO2);
	    		parallerLine2.setStartY(yO2);
	    		parallerLine2.setEndX(x2);
	    		parallerLine2.setEndY(y2);
	    		
	    		
	    		
	    		if(sampleLine1.getStartX() > sampleLine1.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine1.getStartX()-30);
	    			choiceBox.setLayoutX(sampleLine1.getStartX()-30);
	    		}
	    		else if(sampleLine1.getStartX() < sampleLine1.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine1.getStartX()+30);
	    			choiceBox.setLayoutX(sampleLine1.getStartX()+30);
	    		}
	    		if(sampleLine1.getStartY() > sampleLine1.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine1.getStartY()-30);
	    			choiceBox.setLayoutY(sampleLine1.getStartY()-30);
	    		}
	    		else if(sampleLine1.getStartY() < sampleLine1.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine1.getStartY()+30);
	    			choiceBox.setLayoutY(sampleLine1.getStartY()+30);
	    		}
	    		
	    		orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        rightAnchor.setOnMouseExited((event) -> {
	        	rightAnchor.getScene().setCursor(null);
	        });
	        
	        	        
	}
	
	
	public StackPane getStackPaneRectangle() {
		return stackPaneRectangle;
	}

}
