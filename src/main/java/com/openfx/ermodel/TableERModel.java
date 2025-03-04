package com.openfx.ermodel;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TableERModel extends ERModel{

	public ERModel currentSelectedERModel;
	double orgSceneX,orgSceneY;
	public Circle circle1;
	public Circle circle2;

	
	public TableERModel( double circle1X,double circle1Y,double circle1Radius,ArrayList<String> tableAttributes,ArrayList<RelationERModel> tableRelationships,Pane pane,ArrayList<ERModel> erModelList) {
		

	//	double circle1X = 400,circle1Y=400,circle1Radius = 200;
	    circle1 = new Circle(circle1X,circle1Y,circle1Radius);
	//	circle1.setStroke(Color.BLACK);
	//	circle1.setFill(Color.TRANSPARENT);   Else this will cover the area in pane and block others
	//	circle1.getStrokeDashArray().add(2d);
	//	pane.getChildren().add(circle1);

		circle2 = new Circle(circle1X,circle1Y,5);
	//	circle2.setStroke(Color.BLACK);
		circle2.setFill(Color.TRANSPARENT);
		circle2.getStrokeDashArray().add(2d);
		pane.getChildren().add(circle2);
		
		circle2.setOnMouseClicked(event -> {
			circle2.setStroke(Color.BLACK);
		});

		circle2.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.MOVE);
	        });
		circle2.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	            orgSceneY = event.getSceneY();//Store current mouse position
	        });
		circle2.setOnMouseDragged((event) -> {
	            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position 
		
	            circle2.setCenterX(circle2.getCenterX() + offSetX);
	            circle2.setCenterY(circle2.getCenterY() + offSetY);
	            
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
		});
		circle2.setOnMouseExited((event) -> {
			circle2.getScene().setCursor(null);
	        });
		
	
		
		
		Integer numberofPartitions = tableAttributes.size()-1;
		double spacingAngle = 270/numberofPartitions;  // to open the right side slit
		
		double preferredWidth = 60;double preferredHeight = 20;
		double preferredSpacing=10;
		double layoutX = 100;double layoutY = 100;

		for(int i = 0;i<=numberofPartitions;i++) {		
			Attribute nameAttribute = new Attribute(tableAttributes.get(i),preferredWidth, preferredHeight, layoutX, layoutY,pane);
			erModelList.add(nameAttribute);
		//	layoutX = layoutX+ preferredWidth*2 + preferredSpacing;
    		
			Line _line =  nameAttribute.getConnectionLine();
			_line.setStartX(circle1.getCenterX());
			_line.setStartY(circle1.getCenterY());
			_line.startXProperty().bind(circle2.centerXProperty());
    		_line.startYProperty().bind(circle2.centerYProperty());
			
			double _x1,_y1;
    		
    		_x1 = circle1X - circle1Radius * Math.cos(Math.toRadians(i*spacingAngle-135)) ;   //  to open the right side slit
    		_y1 = circle1Y - circle1Radius * Math.sin(Math.toRadians(i*spacingAngle-135));    // to open the right side slit
    		
    		_line.setEndX(_x1);
    		_line.setEndY(_y1);
    	
    		nameAttribute.getStackPaneRectangle().setLayoutX(_line.getEndX()-preferredWidth);
    		nameAttribute.getStackPaneRectangle().setLayoutY(_line.getEndY()-preferredHeight);
    	
    		
    		_line.endXProperty().bind(nameAttribute.getStackPaneRectangle().layoutXProperty().add(preferredWidth));
    		_line.endYProperty().bind(nameAttribute.getStackPaneRectangle().layoutYProperty().add(preferredHeight));
		}
		
		numberofPartitions++;
		// just keep this 
		
		for(int i=0;i<tableRelationships.size();i++) {
			
			tableRelationships.get(i).partialParticipation1.sampleLine.setStartX(circle1.getCenterX());
			tableRelationships.get(i).partialParticipation1.sampleLine.setStartY(circle1.getCenterY());
			tableRelationships.get(i).partialParticipation1.sampleLine.startXProperty().bind(circle2.centerXProperty());
			tableRelationships.get(i).partialParticipation1.sampleLine.startYProperty().bind(circle2.centerYProperty());
			
			double _x1,_y1;
    		
    		_x1 = circle1X - circle1Radius * Math.cos(Math.toRadians((i + numberofPartitions)*spacingAngle-135)) ;   //  to open the right side slit
    		_y1 = circle1Y - circle1Radius * Math.sin(Math.toRadians((i + numberofPartitions)*spacingAngle-135));    // to open the right side slit
    		
    		tableRelationships.get(i).partialParticipation1.sampleLine.setEndX(_x1);
    		tableRelationships.get(i).partialParticipation1.sampleLine.setEndY(_y1);
    	
    		tableRelationships.get(i).getStackPaneRectangle().setLayoutX(tableRelationships.get(i).partialParticipation1.sampleLine.getEndX()-70);
    		tableRelationships.get(i).getStackPaneRectangle().setLayoutY(tableRelationships.get(i).partialParticipation1.sampleLine.getEndY()-35);
    		
    		tableRelationships.get(i).partialParticipation1.sampleLine.endXProperty().bind(tableRelationships.get(i).getStackPaneRectangle().layoutXProperty().add(70));
    		tableRelationships.get(i).partialParticipation1.sampleLine.endYProperty().bind(tableRelationships.get(i).getStackPaneRectangle().layoutYProperty().add(35));
			
    		//for other end 
    	//	tableRelationships.get(i).partialParticipation2.sampleLine.setStartX(circle1.getCenterX());
		//	tableRelationships.get(i).partialParticipation2.sampleLine.setStartY(circle1.getCenterY());
		//	tableRelationships.get(i).partialParticipation2.sampleLine.startXProperty().bind(circle2.centerXProperty());
		//	tableRelationships.get(i).partialParticipation2.sampleLine.startYProperty().bind(circle2.centerYProperty());
		}
		
		for(int i=0;i<tableRelationships.size();i++) {
			
			/*
			RelationERModel relationERModel = new RelationERModel(35, layoutX, layoutY,pane,erModelList);
			
			Line _line =  relationERModel.getConnectionLine();
			_line.setStartX(circle1.getCenterX());
			_line.setStartY(circle1.getCenterY());
			_line.startXProperty().bind(circle2.centerXProperty());
    		_line.startYProperty().bind(circle2.centerYProperty());
			
			double _x1,_y1;
    		
    		_x1 = circle1X - circle1Radius * Math.cos(Math.toRadians((i + numberofPartitions)*spacingAngle-135)) ;   //  to open the right side slit
    		_y1 = circle1Y - circle1Radius * Math.sin(Math.toRadians((i + numberofPartitions)*spacingAngle-135));    // to open the right side slit
    		
    		_line.setEndX(_x1);
    		_line.setEndY(_y1);
    	
    		relationERModel.getStackPaneRectangle().setLayoutX(_line.getEndX()-70);
    		relationERModel.getStackPaneRectangle().setLayoutY(_line.getEndY()-35);
    		
    		_line.endXProperty().bind(relationERModel.getStackPaneRectangle().layoutXProperty().add(70));
    		_line.endYProperty().bind(relationERModel.getStackPaneRectangle().layoutYProperty().add(35));
    		*/
		}
		
		erModelList.add(this);
	}
	

	
}

