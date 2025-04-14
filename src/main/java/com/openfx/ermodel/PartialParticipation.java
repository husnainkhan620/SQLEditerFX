package com.openfx.ermodel;

import java.util.ArrayList;

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
import javafx.scene.shape.Rectangle;

public class PartialParticipation extends ERModel{
	
	public Line sampleLine;
	public ChoiceBox<String> choiceBox;
	public Label cardinalTextLabel;
	Point2D startpoint2D ;
	Point2D endpoint2D;
	double distanceofLinePoints;
	public Group group;
	
	double orgSceneX, orgSceneY;//Used to help keep up with change in mouse position
	  
	public PartialParticipation(double startX,double startY,double endX,double endY,Pane mainPane,ArrayList<ERModel> erModelList,boolean isEditable) {
		
		group = new Group();
		leftAnchor = new Circle(startX, startY, 5);
		 
		rightAnchor = new Circle(endX, endY, 5);
		
		
		startpoint2D = new Point2D((float)startX,(float)startY);
		endpoint2D = new Point2D((float)endX,(float)endY);
		distanceofLinePoints = startpoint2D.distance(endpoint2D);
		 
		resizeRectangle = new Rectangle(distanceofLinePoints+4,10);
		resizeRectangle.setStrokeWidth(1);
		resizeRectangle.setLayoutX(startX-2);
		resizeRectangle.setLayoutY(startY-5);
		resizeRectangle.setStroke(Color.BLACK);
		resizeRectangle.setFill(Color.GAINSBORO);
		resizeRectangle.getStrokeDashArray().add(2d);
		
		 startpoint2D = new Point2D((float)startX,(float)startY);
		 endpoint2D = new Point2D((float)endX,(float)endY);
		 distanceofLinePoints = startpoint2D.distance(endpoint2D);
		
		sampleLine = new Line();
		sampleLine.setStartX(startX);
		sampleLine.setStartY(startY);
		sampleLine.setEndX(endX);
		sampleLine.setEndY(endY);		
		
		cardinalTextLabel = new Label("N");
		cardinalTextLabel.setLayoutX(startX+distanceofLinePoints/2);
		cardinalTextLabel.setLayoutY(startY);

		choiceBox = new ChoiceBox<String>();
		choiceBox.setLayoutX(startX+distanceofLinePoints/2-20);
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
		
		group.getChildren().addAll(sampleLine,cardinalTextLabel);
		
		mainPane.getChildren().addAll(group);
		
		enableDragAndDrop(this,mainPane);
		
		sampleLine.setOnMouseEntered(event ->{
			((Line) event.getSource()).getScene().setCursor(Cursor.MOVE);
			
		});
		
		sampleLine.setOnMouseExited(event ->{
			 ((Line) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
			
		});
		
		sampleLine.setOnMousePressed(event ->{
			System.out.println("Mouse Clicked this sampleline");
			//	stackPaneRectangle.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, null, null))); 
		//	resizeRectangle.setStroke(Color.BLACK);
	
			group.getChildren().clear();
			
			group.getChildren().addAll(sampleLine,choiceBox);
			
			if(isEditable) {
				if(!mainPane.getChildren().contains(leftAnchor)) {
					leftAnchor.setFill(Color.BLACK);
					leftAnchor.setStroke(Color.BLACK);
					mainPane.getChildren().add(leftAnchor);
				}
	
				
				if(!mainPane.getChildren().contains(rightAnchor)) {
					rightAnchor.setFill(Color.BLACK);
					rightAnchor.setStroke(Color.BLACK);
					mainPane.getChildren().add(rightAnchor);
				}
			}
			orgSceneX = event.getSceneX();//Store current mouse position
	        orgSceneY = event.getSceneY();//Store current mouse position
			
		});
		
		
		
		// When dragging, update the TitledPane position
		sampleLine.setOnMouseDragged(event -> {
			double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	        double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
		    
	        
            leftAnchor.setCenterX(leftAnchor.getCenterX() + offSetX);
            leftAnchor.setCenterY(leftAnchor.getCenterY() + offSetY);
            rightAnchor.setCenterX(rightAnchor.getCenterX() + offSetX);
            rightAnchor.setCenterY(rightAnchor.getCenterY() + offSetY);
            

            sampleLine.setStartX( sampleLine.getStartX() + offSetX);
    		sampleLine.setStartY(sampleLine.getStartY() + offSetY);
    		sampleLine.setEndX(sampleLine.getEndX() + offSetX);
    		sampleLine.setEndY(sampleLine.getEndY() + offSetY);
    		
    		orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	        orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        
		});
		
		cardinalTextLabel.setOnMouseEntered(event ->{
			((Label) event.getSource()).getScene().setCursor(Cursor.TEXT);
			
		});
		
		cardinalTextLabel.setOnMouseExited(event ->{
			 ((Label) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
			
		});
		
		cardinalTextLabel.setOnMousePressed(event ->{
		
			group.getChildren().clear();
			group.getChildren().addAll(sampleLine,choiceBox);
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
	            
	            System.out.println("leftAnchor.getCenterX()"+leftAnchor.getCenterX());
	            System.out.println("leftAnchor.getCenterY()"+leftAnchor.getCenterY());
	            System.out.println("rightAnchor.getCenterX()"+rightAnchor.getCenterX());
	            System.out.println("rightAnchor.getCenterY()"+rightAnchor.getCenterY());
	            sampleLine.setStartX(leftAnchor.getCenterX());
	    		sampleLine.setStartY(leftAnchor.getCenterY());
	    		sampleLine.setEndX(rightAnchor.getCenterX());
	    		sampleLine.setEndY(rightAnchor.getCenterY());
	            
	    		startpoint2D = new Point2D((float)sampleLine.getStartX(),(float)sampleLine.getStartY());
	    		endpoint2D = new Point2D((float)sampleLine.getEndX(),(float)sampleLine.getEndY());
	    		distanceofLinePoints = startpoint2D.distance(endpoint2D);
	    	
	    		
	    		
	    		if(sampleLine.getStartX() > sampleLine.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine.getStartX()-30);
	    			choiceBox.setLayoutX(sampleLine.getStartX()-30-30);
	    		}
	    		else if(sampleLine.getStartX() < sampleLine.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine.getStartX()+30);
	    			choiceBox.setLayoutX(sampleLine.getStartX()+30);
	    		}
	    		if(sampleLine.getStartY() > sampleLine.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine.getStartY()-30);
	    			choiceBox.setLayoutY(sampleLine.getStartY()-30-30);
	    		}
	    		else if(sampleLine.getStartY() < sampleLine.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine.getStartY()+30);
	    			choiceBox.setLayoutY(sampleLine.getStartY()+30);
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
	            sampleLine.setStartX(leftAnchor.getCenterX());
	    		sampleLine.setStartY(leftAnchor.getCenterY());
	    		sampleLine.setEndX(rightAnchor.getCenterX());
	    		sampleLine.setEndY(rightAnchor.getCenterY());
	    		
	    		startpoint2D = new Point2D((float)sampleLine.getStartX(),(float)sampleLine.getStartY());
	    		endpoint2D = new Point2D((float)sampleLine.getEndX(),(float)sampleLine.getEndY());
	    		distanceofLinePoints = startpoint2D.distance(endpoint2D);
	    		
	    		if(sampleLine.getStartX() > sampleLine.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine.getStartX()-30);
	    			choiceBox.setLayoutX(sampleLine.getStartX()-30);
	    		}
	    		else if(sampleLine.getStartX() < sampleLine.getEndX()) {
	    			cardinalTextLabel.setLayoutX(sampleLine.getStartX()+30);
	    			choiceBox.setLayoutX(sampleLine.getStartX()+30);
	    		}
	    		if(sampleLine.getStartY() > sampleLine.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine.getStartY()-30);
	    			choiceBox.setLayoutY(sampleLine.getStartY()-30);
	    		}
	    		else if(sampleLine.getStartY() < sampleLine.getEndY()) {
	    			cardinalTextLabel.setLayoutY(sampleLine.getStartY()+30);
	    			choiceBox.setLayoutY(sampleLine.getStartY()+30);
	    		}
	    		
	    		orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        rightAnchor.setOnMouseExited((event) -> {
	        	rightAnchor.getScene().setCursor(null);
	        });
	        
	        erModelList.add(this);
	        	        
	}
	
	private void enableDragAndDrop(ERModel erModel,Pane mainPane) {
		

		 final double[] offset = new double[2];
		 sampleLine.setOnMousePressed(event -> {
		        	
	        	 offset[0] = event.getSceneX() - sampleLine.getLayoutX();
	             offset[1] = event.getSceneY() - sampleLine.getLayoutY() ;
	          
	        });
	        
	        // When dragging, update the TitledPane position
		 sampleLine.setOnMouseDragged(event -> {
	        	
	            // Restrict movement within the bounds of the mainPane
	            double newX = event.getSceneX() - offset[0];
	    		double newY = event.getSceneY() - offset[1];

	    		// Restrict movement within the bounds of the mainPane
	    		if (newX >= 0 && newX + stackPaneRectangle.getWidth() <= mainPane.getWidth()) {
	    			stackPaneRectangle.setLayoutX(newX);
	    		}
	    		if (newY >= 0 && newY + stackPaneRectangle.getHeight() <= mainPane.getHeight()) {
	    			stackPaneRectangle.setLayoutY(newY);
	    		}
	    		
	    		// re align all the circles here			
				
				if(erModel.stackPaneRectangle != null) {
					
					erModel.leftAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX());
					erModel.leftAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() + 15+11);
		    		
					erModel.rightAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX() + erModel.stackPaneRectangle.getWidth());
					erModel.rightAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() + + 15+11);
		    	
				}
	    		
	        });
	}
	
	public StackPane getStackPaneRectangle() {
		return stackPaneRectangle;
	}

}
