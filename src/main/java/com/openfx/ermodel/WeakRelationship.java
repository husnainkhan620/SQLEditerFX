package com.openfx.ermodel;

import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class WeakRelationship extends ERModel  {

	private Polygon outerRhombus;
	private Polygon innerRhombus;
	private TextArea textAreaRhombus;
	
	double orgSceneX, orgSceneY;//Used to help keep up with change in mouse position
	Double outerRhombusSizevalues[] ;
	Double innerRhombusSizevalues[] ;
	
	public WeakRelationship(double rhombusSize,double layoutX,double layoutY,Pane mainPane) {
		
		outerRhombusSizevalues = new Double[]{
				100d, 100d,
				(double)(100- (rhombusSize*2) - 0*(rhombusSize*2)), (double)(100+rhombusSize),
				(double)(100), (double)(100+rhombusSize*2),
				(double)(100+ (rhombusSize*2) + 0*(rhombusSize*2)), (double)(100+rhombusSize),
		};
		
		double innerRhoumbusSize = rhombusSize * 0.85;
		innerRhombusSizevalues = new Double[]{
				100d, 100d,
				(double)(100- (innerRhoumbusSize*2) - 0*(innerRhoumbusSize*2)), (double)(100+innerRhoumbusSize),
				(double)(100), (double)(100+innerRhoumbusSize*2),
				(double)(100+ (innerRhoumbusSize*2) + 0*(innerRhoumbusSize*2)), (double)(100+innerRhoumbusSize),
		};
		
		leftAnchor = new Circle(layoutX, layoutY+rhombusSize, 5);
		 
		topLeftAnchor = new Circle(layoutX,layoutY,5);
		bottomleftAnchor = new Circle(layoutX ,layoutY+rhombusSize*2, 5);
		 
		topAnchor = new Circle(layoutX+rhombusSize*2,layoutY, 5);
		bottomAnchor = new Circle(layoutX+rhombusSize*2, layoutY + rhombusSize*2, 5);
		    
	    topRightAnchor = new Circle(layoutX+rhombusSize*4, layoutY, 5);
		rightAnchor = new Circle(layoutX+rhombusSize*4, layoutY+rhombusSize, 5);
	    bottomRightAnchor = new Circle(layoutX+rhombusSize*4,layoutY+rhombusSize*2,5);
	    
		stackPaneRectangle = new StackPane();
		stackPaneRectangle.setLayoutX(layoutX);
		stackPaneRectangle.setLayoutY(layoutY);
	//	stackPaneRectangle.setBorder(new Border(new BorderStroke(Color.GAINSBORO, BorderStrokeStyle.DASHED, null, null))); 
	
		resizeRectangle = new Rectangle(rhombusSize*4,rhombusSize*2);
		resizeRectangle.setStrokeWidth(1);
		resizeRectangle.setLayoutX(layoutX);
		resizeRectangle.setLayoutY(layoutY);
		resizeRectangle.setStroke(Color.GAINSBORO);
		resizeRectangle.setFill(Color.GAINSBORO);
		resizeRectangle.getStrokeDashArray().add(2d);
		
		outerRhombus = new Polygon();
		outerRhombus.setStrokeWidth(2);
		outerRhombus.setStroke(Color.BLACK);
		outerRhombus.setFill(Color.GAINSBORO);
		outerRhombus.getPoints().addAll(outerRhombusSizevalues);
		
	
		innerRhombus = new Polygon();
		innerRhombus.setStrokeWidth(2);
		innerRhombus.setStroke(Color.BLACK);
		innerRhombus.setFill(Color.GAINSBORO);
		innerRhombus.getPoints().addAll(innerRhombusSizevalues);
		
		textAreaRhombus = new TextArea();
		textAreaRhombus.setMaxSize(innerRhoumbusSize*2 + 0.50*innerRhoumbusSize, innerRhoumbusSize - 0.50*innerRhoumbusSize);
		textAreaRhombus.setId("mytextarea");
		textAreaRhombus.setText("WEAK RELAT");
		textAreaRhombus.setWrapText(true);
		textAreaRhombus.setFont(Font.font("System Regular",FontPosture.REGULAR, 16));
		stackPaneRectangle.getChildren().addAll(resizeRectangle,outerRhombus,innerRhombus,textAreaRhombus);

		mainPane.getChildren().add(stackPaneRectangle);
		
		enableDragAndDrop(this,mainPane);
		
		stackPaneRectangle.setOnMouseClicked(event ->{
			System.out.println("Mouse Clicked this StackPane");
			//	stackPaneRectangle.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, null, null))); 
			resizeRectangle.setStroke(Color.BLACK);
			
			if(!mainPane.getChildren().contains(leftAnchor)) {
				leftAnchor.setFill(Color.BLACK);
				leftAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(leftAnchor);
			}

			if(!mainPane.getChildren().contains(topLeftAnchor)) {
				topLeftAnchor.setFill(Color.BLACK);
				topLeftAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(topLeftAnchor);
			}
			
			if(!mainPane.getChildren().contains(bottomleftAnchor)) {
				bottomleftAnchor.setFill(Color.BLACK);
				bottomleftAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(bottomleftAnchor);
			}
			
			if(!mainPane.getChildren().contains(topAnchor)) {
				topAnchor.setFill(Color.BLACK);
				topAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(topAnchor);
			}
			
			if(!mainPane.getChildren().contains(bottomAnchor)) {
				bottomAnchor.setFill(Color.BLACK);
				bottomAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(bottomAnchor);
			}
			
			if(!mainPane.getChildren().contains(topRightAnchor)) {
				topRightAnchor.setFill(Color.BLACK);
				topRightAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(topRightAnchor);
			}
			
			if(!mainPane.getChildren().contains(rightAnchor)) {
				rightAnchor.setFill(Color.BLACK);
				rightAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(rightAnchor);
			}
			
			if(!mainPane.getChildren().contains(bottomRightAnchor)) {
				bottomRightAnchor.setFill(Color.BLACK);
				bottomRightAnchor.setStroke(Color.BLACK);
				mainPane.getChildren().add(bottomRightAnchor);
			}
		});
		
		stackPaneRectangle.setOnMouseEntered(event ->{
			 ((StackPane) event.getSource()).getScene().setCursor(Cursor.MOVE);
			
		});
		stackPaneRectangle.setOnMouseExited(event ->{
			 ((StackPane) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
			
		});
		
		 leftAnchor.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.E_RESIZE);
	        });
	        leftAnchor.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	        });
	        leftAnchor.setOnMouseDragged((event) -> {
	        	outerRhombus.getPoints().clear();
	        	innerRhombus.getPoints().clear();
	        	double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position

	            stackPaneRectangle.setLayoutX(event.getSceneX());
	            resizeRectangle.setX(event.getSceneX());//move rectangle left side with mouse
	            resizeRectangle.setWidth(resizeRectangle.getWidth() - offSetX);//Change rectangle's width with movement of mouse
	            
	            
	            leftAnchor.setCenterX(leftAnchor.getCenterX() + offSetX);
	            
	            // re- align the anchor circles
	            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);
	            bottomleftAnchor.setCenterX(event.getSceneX());
	            topLeftAnchor.setCenterX(event.getSceneX());
	            
	            topRightAnchor.setCenterX(resizeRectangle.getX() + resizeRectangle.getWidth());
	            rightAnchor.setCenterX(resizeRectangle.getX() + resizeRectangle.getWidth());
	            bottomRightAnchor.setCenterX(resizeRectangle.getX() + resizeRectangle.getWidth());
	            
	            // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	        	textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	        });
	        leftAnchor.setOnMouseExited((event) -> {
	            leftAnchor.getScene().setCursor(null);
	        });
	        
	        
	        rightAnchor.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.W_RESIZE);
	        });
	        rightAnchor.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	        });
	        rightAnchor.setOnMouseDragged((event) -> {
	            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	            outerRhombus.getPoints().clear();
	            innerRhombus.getPoints().clear();
	            resizeRectangle.setWidth(resizeRectangle.getWidth() + offSetX);//Change rectangle's width with movement of mouse
	            // re- align the innerRectanle and text area
	        //    newEllipse.setRadiusX(resizeRectangle.getWidth()/2);
	        //    textAreaEllipse.setMaxSize(newEllipse.getRadiusX() + 0.45*newEllipse.getRadiusX(), newEllipse.getRadiusY() + 0.40*newEllipse.getRadiusY());
	            
	            rightAnchor.setCenterX(rightAnchor.getCenterX() + offSetX);
	            // re align the circle anchors
	            topRightAnchor.setCenterX(topRightAnchor.getCenterX() + offSetX );
	            bottomRightAnchor.setCenterX(bottomRightAnchor.getCenterX() + offSetX );
	            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	          
	            // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        rightAnchor.setOnMouseExited((event) -> {
	        	rightAnchor.getScene().setCursor(null);
	        });
	        
	        bottomleftAnchor.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.SW_RESIZE);
	        });
	        bottomleftAnchor.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	            orgSceneY = event.getSceneY();//Store current mouse position
	        });
	        bottomleftAnchor.setOnMouseDragged((event) -> {
	            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position 
	            outerRhombus.getPoints().clear();
	            innerRhombus.getPoints().clear();
	            
	            stackPaneRectangle.setLayoutX(event.getSceneX());
	          
	            resizeRectangle.setX(event.getSceneX());//move rectangle left side with mouse
	            resizeRectangle.setWidth(resizeRectangle.getWidth() - offSetX);//Change rectangle's width with movement of mouse
	            resizeRectangle.setHeight(resizeRectangle.getHeight() + offSetY);//Change the height so that it meets the ratio requirements
	            
	            // re- align the innerRectanle and text area
	          //  newEllipse.setRadiusY(resizeRectangle.getHeight()/2);
	         //   newEllipse.setRadiusX(resizeRectangle.getWidth()/2);
	        //    textAreaEllipse.setMaxSize(newEllipse.getRadiusX() + 0.45*newEllipse.getRadiusX(), newEllipse.getRadiusY() + 0.40*newEllipse.getRadiusY());
	            
	            bottomleftAnchor.setCenterX(event.getSceneX());
	            bottomleftAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) );//Adjust the left circle with the growth of the rectangle
	            
	            leftAnchor.setCenterX(event.getSceneX());
	            leftAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));//Adjust the left circle with the growth of the rectangle

	            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	            
	            topLeftAnchor.setCenterX(event.getSceneX());
	            topLeftAnchor.setCenterY((stackPaneRectangle.getLayoutY() ) );//Adjust the left circle with the growth of the rectangle
	            
	            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	            bottomAnchor.setCenterY((stackPaneRectangle.getLayoutY()+ resizeRectangle.getHeight()));
	            
	            topRightAnchor.setCenterX( resizeRectangle.getX() + resizeRectangle.getWidth());
	            
	            rightAnchor.setCenterX( resizeRectangle.getX() + resizeRectangle.getWidth());
	            rightAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));//Adjust the left circle with the growth of the rectangle
	            
	            bottomRightAnchor.setCenterX( resizeRectangle.getX() + resizeRectangle.getWidth());
	            bottomRightAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()));
	            
	            // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        bottomleftAnchor.setOnMouseExited((event) -> {
	        	bottomleftAnchor.getScene().setCursor(null);
	        });

	        
	        topLeftAnchor.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.NW_RESIZE);
	        });
	        topLeftAnchor.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	            orgSceneY = event.getSceneY();//Store current mouse position
	        });
	        topLeftAnchor.setOnMouseDragged((event) -> {
	            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse Y position
	            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse Y position

	            outerRhombus.getPoints().clear();
	            innerRhombus.getPoints().clear();
	            
	            stackPaneRectangle.setLayoutX(event.getSceneX());
	            stackPaneRectangle.setLayoutY(event.getSceneY());
	            
	            resizeRectangle.setHeight(resizeRectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse  
	            resizeRectangle.setWidth(resizeRectangle.getWidth() - offSetX );//Change the width so that it meets the ratio requirements
	            
	            // re- align the innerRectanle and text area
	        //    newEllipse.setRadiusY(resizeRectangle.getHeight()/2);
	       //     newEllipse.setRadiusX(resizeRectangle.getWidth()/2);
	        //    textAreaEllipse.setMaxSize(newEllipse.getRadiusX() + 0.45*newEllipse.getRadiusX(), newEllipse.getRadiusY() + 0.40*newEllipse.getRadiusY());
	            
	            topLeftAnchor.setCenterX(event.getSceneX());
	            topLeftAnchor.setCenterY(event.getSceneY());
	            
	            // re-align the circles
	            leftAnchor.setCenterX(stackPaneRectangle.getLayoutX());
	            leftAnchor.setCenterY( (stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));
	            
	            bottomleftAnchor.setCenterX(stackPaneRectangle.getLayoutX());
	            bottomleftAnchor.setCenterY(stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight());
	            
	            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	            topAnchor.setCenterY(stackPaneRectangle.getLayoutY());//Adjust top circle as rectangle's size change
	        
	            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);
	            bottomAnchor.setCenterY(stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight());
	            
	            topRightAnchor.setCenterX(stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()  + offSetX / 2);
	            topRightAnchor.setCenterY(event.getSceneY());
	        
	            rightAnchor.setCenterX(stackPaneRectangle.getLayoutX() + + resizeRectangle.getWidth() + offSetX / 2);
	            rightAnchor.setCenterY( (stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));
	            
	            bottomRightAnchor.setCenterX(stackPaneRectangle.getLayoutX() + + resizeRectangle.getWidth() + offSetX / 2);
	            bottomRightAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()));
	            
	            // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        topLeftAnchor.setOnMouseExited((event) -> {
	        	topLeftAnchor.getScene().setCursor(null);
	        });
	        
	        
	        topAnchor.setOnMouseEntered((event) -> {
	            topAnchor.getScene().setCursor(Cursor.N_RESIZE);
	        });
	        topAnchor.setOnMousePressed((event) -> {
	            orgSceneY = event.getSceneY();//store current mouse position
	        });
	        topAnchor.setOnMouseDragged((event) -> {
	            double offSetY = event.getSceneY() - orgSceneY;
	            outerRhombus.getPoints().clear();
	            innerRhombus.getPoints().clear();
	            
	            stackPaneRectangle.setLayoutY(event.getSceneY());
	            resizeRectangle.setY( resizeRectangle.getY() - offSetY);
	            resizeRectangle.setHeight(resizeRectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse
	            topAnchor.setCenterY(topAnchor.getCenterY() + offSetY);
	            	           
	            // re- align the innerRectanle and text area
	        //    newEllipse.setRadiusY(resizeRectangle.getHeight()/2);
	        //    textAreaEllipse.setMaxSize(newEllipse.getRadiusX() + 0.45*newEllipse.getRadiusX(), newEllipse.getRadiusY() + 0.40*newEllipse.getRadiusY());
	           
	            // re-align the circle anchors
	            topLeftAnchor.setCenterY(topLeftAnchor.getCenterY() + offSetY);
	            topRightAnchor.setCenterY(topRightAnchor.getCenterY() + offSetY);
	            leftAnchor.setCenterY(leftAnchor.getCenterY() + offSetY / 2);
	            rightAnchor.setCenterY(rightAnchor.getCenterY() + offSetY / 2);
	              
	            // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        topAnchor.setOnMouseExited((event) -> {
	            topAnchor.getScene().setCursor(null);
	        });

	        
	        topRightAnchor.setOnMouseEntered((event) -> {
	        	topRightAnchor.getScene().setCursor(Cursor.NE_RESIZE);
	        });
	        topRightAnchor.setOnMousePressed((event) -> {
	            orgSceneY = event.getSceneY();//store current mouse position
	            orgSceneX = event.getSceneX();//store current mouse position
	        });
	        topRightAnchor.setOnMouseDragged((event) -> {
	            double offSetY = event.getSceneY() - orgSceneY;
	            double offSetX = event.getSceneX() - orgSceneX;
	            outerRhombus.getPoints().clear();
	            innerRhombus.getPoints().clear();
	            
	            stackPaneRectangle.setLayoutY(event.getSceneY());
	            
	            resizeRectangle.setY(event.getSceneY());//move rectangle top side with mouse
	            resizeRectangle.setHeight(resizeRectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse           
	            resizeRectangle.setWidth(resizeRectangle.getWidth() + offSetX);//Change the width so that it meets the ratio requirements
	            
	            // re- align the innerRectanle and text area
	       //     newEllipse.setRadiusY(resizeRectangle.getHeight()/2);
	      //      newEllipse.setRadiusX(resizeRectangle.getWidth()/2);
	       //     textAreaEllipse.setMaxSize(newEllipse.getRadiusX() + 0.45*newEllipse.getRadiusX(), newEllipse.getRadiusY() + 0.40*newEllipse.getRadiusY());
	            
	            topRightAnchor.setCenterY(event.getSceneY());
	            topRightAnchor.setCenterX((stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()) );//Adjust the top circle with the growth of the rectangle
	            
	            // re align the center anchors
	            topAnchor.setCenterX(stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()/2 );//Adjust top circle as rectangle's size change
	            topAnchor.setCenterY( stackPaneRectangle.getLayoutY());//Adjust top circle as rectangle's size change
	        
	            bottomAnchor.setCenterX(stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()/2);
	            bottomAnchor.setCenterY(stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight());
	            
	            rightAnchor.setCenterX((stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()) );
	            rightAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));
	            
	            bottomRightAnchor.setCenterX(stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth());
	            bottomRightAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()));
	            
	            leftAnchor.setCenterX(leftAnchor.getCenterX());
	            leftAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));
	            
	            topLeftAnchor.setCenterX(topLeftAnchor.getCenterX());
	            topLeftAnchor.setCenterY(stackPaneRectangle.getLayoutY());
	            
	         // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        topRightAnchor.setOnMouseExited((event) -> {
	        	topRightAnchor.getScene().setCursor(null);
	        });

	        
	        bottomAnchor.setOnMouseEntered((event) -> {
	        	bottomAnchor.getScene().setCursor(Cursor.S_RESIZE);
	        });
	        bottomAnchor.setOnMousePressed((event) -> {
	            orgSceneY = event.getSceneY();//store current mouse position
	        });
	        bottomAnchor.setOnMouseDragged((event) -> {
	            double offSetY = event.getSceneY() - orgSceneY;
	            outerRhombus.getPoints().clear();
	            innerRhombus.getPoints().clear();
	            
	            resizeRectangle.setHeight(resizeRectangle.getHeight() + offSetY);//Change rectangle's height with movement of mouse   
	            bottomAnchor.setCenterY(bottomAnchor.getCenterY() + offSetY);
	            
	            // re- align the innerRectanle and text area
	       //     newEllipse.setRadiusY(resizeRectangle.getHeight()/2);
	        //    textAreaEllipse.setMaxSize(newEllipse.getRadiusX() + 0.45*newEllipse.getRadiusX(), newEllipse.getRadiusY() + 0.40*newEllipse.getRadiusY());
	            
	            // re-align the circle anchors
	            bottomleftAnchor.setCenterY(bottomleftAnchor.getCenterY() + offSetY);
	            bottomRightAnchor.setCenterY(bottomRightAnchor.getCenterY() + offSetY);
	            leftAnchor.setCenterY(leftAnchor.getCenterY() + offSetY / 2);
	            rightAnchor.setCenterY(rightAnchor.getCenterY() + offSetY / 2);
	            
	            // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        bottomAnchor.setOnMouseExited((event) -> {
	        	bottomAnchor.getScene().setCursor(null);
	        });
	        
	        
	        bottomRightAnchor.setOnMouseEntered((event) -> {
	        	bottomRightAnchor.getScene().setCursor(Cursor.SE_RESIZE);
	        });
	        bottomRightAnchor.setOnMousePressed((event) -> {
	        	orgSceneX = event.getSceneX();//store current mouse position
	        	orgSceneY = event.getSceneY();//store current mouse position
	        });
	        bottomRightAnchor.setOnMouseDragged((event) -> {
	        	double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	        	double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
	        	outerRhombus.getPoints().clear();
	        	innerRhombus.getPoints().clear();
	        	
	        	resizeRectangle.setWidth(resizeRectangle.getWidth() + offSetX);//Change rectangle's width with movement of mouse
	        	resizeRectangle.setHeight(resizeRectangle.getHeight() + offSetY);//Change the height so that it meets the ratio requirements
	            
	        	// re- align the innerRectanle and text area
	       // 	newEllipse.setRadiusY(resizeRectangle.getHeight()/2);
		  //      newEllipse.setRadiusX(resizeRectangle.getWidth()/2);
		  //      textAreaEllipse.setMaxSize(newEllipse.getRadiusX() + 0.45*newEllipse.getRadiusX(), newEllipse.getRadiusY() + 0.40*newEllipse.getRadiusY());	            
	            
		        bottomRightAnchor.setCenterX(event.getSceneX());
	            bottomRightAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) );//Adjust the left circle with the growth of the rectangle
	            
	            // re-align the circle anchors
	            topRightAnchor.setCenterY(topRightAnchor.getCenterY());
	            topRightAnchor.setCenterX(event.getSceneX());
	            
	            bottomleftAnchor.setCenterX(bottomleftAnchor.getCenterX());
	            bottomleftAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()));
	            
	            topAnchor.setCenterX(stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()/2 );//Adjust top circle as rectangle's size change
	            topAnchor.setCenterY(stackPaneRectangle.getLayoutY());//Adjust top circle as rectangle's size change
	        
	            bottomAnchor.setCenterX(stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()/2);
	            bottomAnchor.setCenterY(stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight());
	            
	            rightAnchor.setCenterX((stackPaneRectangle.getLayoutX() + resizeRectangle.getWidth()) );
	            rightAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));
	            
	            leftAnchor.setCenterX(leftAnchor.getCenterX());
	            leftAnchor.setCenterY((stackPaneRectangle.getLayoutY() + resizeRectangle.getHeight()) - (resizeRectangle.getHeight() / 2));
	            
	         // realign the inner rectangle and text area
	            outerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            innerRhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY()+8,
	    				leftAnchor.getCenterX()+20, leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY()-8,
	    				rightAnchor.getCenterX()-20,rightAnchor.getCenterY(),
	    		};
	            
	            outerRhombus.getPoints().addAll(outerRhombusSizevalues);
	            innerRhombus.getPoints().addAll(innerRhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2.5,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2.5 );
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });
	        bottomRightAnchor.setOnMouseExited((event) -> {
	        	bottomRightAnchor.getScene().setCursor(null);
	        });
	}

	private void enableDragAndDrop(ERModel erModel,Pane mainPane) {

		 final double[] offset = new double[2];
		 stackPaneRectangle.setOnMousePressed(event -> {
		        	
	        	 offset[0] = event.getSceneX() - stackPaneRectangle.getLayoutX();
	             offset[1] = event.getSceneY() - stackPaneRectangle.getLayoutY() ;
	          
	        });
	        
	        // When dragging, update the TitledPane position
		 stackPaneRectangle.setOnMouseDragged(event -> {
	        	
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
					System.out.println("Setting the values "+erModel.resizeRectangle.getWidth());
					erModel.topAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX() + erModel.resizeRectangle.getWidth()/2);
					erModel.topAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() );
		    		
					erModel.topLeftAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX());
					erModel.topLeftAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY());
		    		
					erModel.topRightAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX() + erModel.stackPaneRectangle.getWidth());
					erModel.topRightAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY());
		    		
					erModel.leftAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX());
					erModel.leftAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() + erModel.stackPaneRectangle.getHeight()/2);
		    		
					erModel.bottomleftAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX());
					erModel.bottomleftAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() + erModel.stackPaneRectangle.getHeight());
		    		
					erModel.bottomAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX() + erModel.stackPaneRectangle.getWidth()/2);
					erModel.bottomAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() + erModel.stackPaneRectangle.getHeight());
		    		
					erModel.rightAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX() + erModel.stackPaneRectangle.getWidth());
					erModel.rightAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() + erModel.stackPaneRectangle.getHeight()/2);
		    		
					erModel.bottomRightAnchor.setCenterX(erModel.stackPaneRectangle.getLayoutX() + erModel.stackPaneRectangle.getWidth());
					erModel.bottomRightAnchor.setCenterY(erModel.stackPaneRectangle.getLayoutY() + erModel.stackPaneRectangle.getHeight());
				
				}
			
	    		
	        });
	}
	public StackPane getStackPaneRectangle() {
		return stackPaneRectangle;
	}


}
