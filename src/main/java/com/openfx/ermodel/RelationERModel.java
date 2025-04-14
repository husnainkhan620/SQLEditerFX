package com.openfx.ermodel;

import java.util.ArrayList;
import java.util.Stack;

import com.sun.javafx.geom.Point2D;

import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class RelationERModel  extends ERModel{

	public PartialParticipation partialParticipation1;
	private Polygon rhombus;
	private TextArea textAreaRhombus;
	public PartialParticipation partialParticipation2;
	public TableERModel tableERModel1;
	public TableERModel tableERModel2;
	public Line line1,line2 ;
	
	double orgSceneX, orgSceneY;//Used to help keep up with change in mouse position
	Double rhombusSizevalues[] ;
	public RelationERModel(TableERModel tableERModel1,TableERModel tableERModel2,Pane mainPane,ArrayList<ERModel> erModelList,Stack<Runnable> undoStack) {
		
		line1 = new Line();
		line2 = new Line();
	//	mainPane.getChildren().add(line);
		
		this.tableERModel1 = tableERModel1;
		this.tableERModel2 = tableERModel2;
		
		double rhombusSize=35 ;
		double layoutX = tableERModel2.circle1.getCenterX() - tableERModel1.circle1.getCenterX();
		double layoutY= tableERModel2.circle1.getCenterY() -  tableERModel1.circle1.getCenterY();
		
		System.out.println(tableERModel2.tableName + " tableERModel2.circle1.getCenterX() " + tableERModel2.circle1.getCenterX());
		System.out.println(tableERModel1.tableName +" tableERModel1.circle1.getCenterX()" + tableERModel1.circle1.getCenterX());
		System.out.println(tableERModel2.tableName + " tableERModel2.circle1.getCenterY() " + tableERModel2.circle1.getCenterY());
		System.out.println(tableERModel1.tableName +" tableERModel1.circle1.getCenterY()" + tableERModel1.circle1.getCenterY());
		
		if(layoutX < 0) {
			layoutX = layoutX * (-1);
		}
		if(layoutY < 0) {
			layoutY = layoutY * (-1);
		}
		
		if(tableERModel2.circle1.getCenterX() < tableERModel1.circle1.getCenterX()) {
			layoutX = tableERModel2.circle1.getCenterX() + (layoutX/2);
		}else {
			layoutX = tableERModel2.circle1.getCenterX() - (layoutX/2);
		}
		if(tableERModel2.circle1.getCenterY() < tableERModel1.circle1.getCenterY()) {
			layoutY = tableERModel2.circle1.getCenterY() + (layoutY/2);
		}else {
			layoutY = tableERModel2.circle1.getCenterY() - (layoutY/2);
		}
		
		rhombusSizevalues = new Double[]{
				layoutX, layoutX,
				(double)(layoutX- (rhombusSize*2) - 0*(rhombusSize*2)), (double)(layoutX+rhombusSize)+rhombusSize*0.0,
				(double)(layoutX), (double)(layoutX+rhombusSize*2),
				(double)(layoutX+ (rhombusSize*2) + 0*(rhombusSize*2)), (double)(layoutX+rhombusSize)+rhombusSize*0.0,
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
		resizeRectangle.setStroke(Color.TRANSPARENT);
		resizeRectangle.setFill(Color.TRANSPARENT);
		resizeRectangle.getStrokeDashArray().add(2d);
		
		rhombus = new Polygon();
		rhombus.setStrokeWidth(2);
		rhombus.setStroke(Color.BLACK);
		rhombus.setFill(Color.BLUEVIOLET);   // change this  and relationshipmytextarea id themcss
		rhombus.getPoints().addAll(rhombusSizevalues);
		
		textAreaRhombus = new TextArea();
		textAreaRhombus.setMaxSize(rhombusSize*2 + 0.2*rhombusSize, rhombusSize - 0.7*rhombusSize);
		textAreaRhombus.setId("relationshipmytextarea");
		textAreaRhombus.setText("IS A");
		textAreaRhombus.setFont(Font.font("System Regular",FontPosture.REGULAR, 16));
		textAreaRhombus.setWrapText(true);
		
		stackPaneRectangle.getChildren().addAll(resizeRectangle,rhombus,textAreaRhombus);
		
		partialParticipation1 = new PartialParticipation(layoutX-100, layoutY+rhombusSize, layoutX, layoutY+rhombusSize, mainPane,erModelList,false);
		partialParticipation1.sampleLine.setStartX(this.tableERModel1.circle2.getCenterX());
		partialParticipation1.sampleLine.setStartY(this.tableERModel1.circle2.getCenterY());		
		
		partialParticipation2 = new PartialParticipation(layoutX+ rhombusSize*4, layoutY+rhombusSize, layoutX+rhombusSize*4+100, layoutY+rhombusSize, mainPane,erModelList,false);
		partialParticipation2.sampleLine.setEndX(this.tableERModel2.circle2.getCenterX());
		partialParticipation2.sampleLine.setEndY(this.tableERModel2.circle2.getCenterY());
		
		mainPane.getChildren().add(stackPaneRectangle);
		mainPane.getChildren().addAll(leftAnchor,rightAnchor,topAnchor,bottomAnchor);
		
		enableDragAndDrop(this,mainPane,undoStack);
		
		stackPaneRectangle.setOnMouseClicked(event ->{
			System.out.println("Mouse Clicked this StackPane");
			//	stackPaneRectangle.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, null, null))); 
			resizeRectangle.setStroke(Color.BLACK);
			
			textAreaRhombus.requestFocus();
			
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
	        	rhombus.getPoints().clear();
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
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	        	textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            realign();
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
	            rhombus.getPoints().clear();
	            
	            resizeRectangle.setWidth(resizeRectangle.getWidth() + offSetX);//Change rectangle's width with movement of mouse
	        
	            rightAnchor.setCenterX(rightAnchor.getCenterX() + offSetX);
	            // re align the circle anchors
	            topRightAnchor.setCenterX(topRightAnchor.getCenterX() + offSetX );
	            bottomRightAnchor.setCenterX(bottomRightAnchor.getCenterX() + offSetX );
	            topAnchor.setCenterX(topAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	            bottomAnchor.setCenterX(bottomAnchor.getCenterX() + offSetX / 2);//Adjust top circle as rectangle's size change
	          
	            // realign the inner rectangle and text area
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        
	            realign();
				
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
	            rhombus.getPoints().clear();
	            
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
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            realign();
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

	            rhombus.getPoints().clear();
	            stackPaneRectangle.setLayoutX(event.getSceneX());
	            stackPaneRectangle.setLayoutY(event.getSceneY());
	            
	            resizeRectangle.setHeight(resizeRectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse  
	            resizeRectangle.setWidth(resizeRectangle.getWidth() - offSetX );//Change the width so that it meets the ratio requirements
	            
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
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	            realign();
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
	            rhombus.getPoints().clear();
	            stackPaneRectangle.setLayoutY(event.getSceneY());
	            resizeRectangle.setY( resizeRectangle.getY() - offSetY);
	            resizeRectangle.setHeight(resizeRectangle.getHeight() - offSetY);//Change rectangle's height with movement of mouse
	            topAnchor.setCenterY(topAnchor.getCenterY() + offSetY);
	            	           
	            // re-align the circle anchors
	            topLeftAnchor.setCenterY(topLeftAnchor.getCenterY() + offSetY);
	            topRightAnchor.setCenterY(topRightAnchor.getCenterY() + offSetY);
	            leftAnchor.setCenterY(leftAnchor.getCenterY() + offSetY / 2);
	            rightAnchor.setCenterY(rightAnchor.getCenterY() + offSetY / 2);
	              
	            // realign the inner rectangle and text area
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	            realign();
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
	            rhombus.getPoints().clear();
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
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	            realign();
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
	            rhombus.getPoints().clear();
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
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	            realign();
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
	        	rhombus.getPoints().clear();
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
	            rhombusSizevalues = new Double[]{
	            		topAnchor.getCenterX() , topAnchor.getCenterY(),
	    				leftAnchor.getCenterX(), leftAnchor.getCenterY(),
	    				bottomAnchor.getCenterX(),bottomAnchor.getCenterY(),
	    				rightAnchor.getCenterX(),rightAnchor.getCenterY(),
	    				
	    		};
	            
	            rhombus.getPoints().addAll(rhombusSizevalues);
	            textAreaRhombus.setMaxSize( (rightAnchor.getCenterX() - leftAnchor.getCenterX())/2,(bottomAnchor.getCenterY() - topAnchor.getCenterY())/2 );
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	            realign();
	        });
	        bottomRightAnchor.setOnMouseExited((event) -> {
	        	bottomRightAnchor.getScene().setCursor(null);
	        });
			
	        erModelList.add(this);
	        realign();
	}

	private void enableDragAndDrop(ERModel erModel,Pane mainPane,Stack<Runnable> undoStack) {

	
		 final double[] offset = new double[2];
		 stackPaneRectangle.setOnMousePressed(event -> {
		        	
	        	 offset[0] = event.getSceneX() - stackPaneRectangle.getLayoutX();
	             offset[1] = event.getSceneY() - stackPaneRectangle.getLayoutY() ;
	          
	         	// Store original position for undo
	             double originalX = stackPaneRectangle.getLayoutX();
	             double originalY = stackPaneRectangle.getLayoutY();
	             
	             undoStack.push(() -> {
	                 stackPaneRectangle.setLayoutX(originalX);
	                 stackPaneRectangle.setLayoutY(originalY);
	               
	             });
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
				
				realign();
	        });
	}
	
	private void realign() {
		
		partialParticipation1.sampleLine.endXProperty().bind(this.getStackPaneRectangle().layoutXProperty().add(this.resizeRectangle.getBoundsInLocal().getWidth()/2));
		partialParticipation1.sampleLine.endYProperty().bind(this.getStackPaneRectangle().layoutYProperty().add(this.resizeRectangle.getBoundsInLocal().getHeight()/2));	
		partialParticipation1.sampleLine.startXProperty().bind(this.tableERModel1.circle2.centerXProperty());
		partialParticipation1.sampleLine.startYProperty().bind(this.tableERModel1.circle2.centerYProperty());
		this.tableERModel1.circle2.centerXProperty().bind(this.tableERModel1.entity.stackPaneRectangle.layoutXProperty().add(this.tableERModel1.entity.resizeRectangle.getWidth()/2) );
		this.tableERModel1.circle2.centerYProperty().bind(this.tableERModel1.entity.stackPaneRectangle.layoutYProperty().add(this.tableERModel1.entity.resizeRectangle.getHeight()/2) );
		
		partialParticipation2.sampleLine.startXProperty().bind(this.getStackPaneRectangle().layoutXProperty().add(this.resizeRectangle.getBoundsInLocal().getWidth()/2));
		partialParticipation2.sampleLine.startYProperty().bind(this.getStackPaneRectangle().layoutYProperty().add(this.resizeRectangle.getBoundsInLocal().getHeight()/2));
		partialParticipation2.sampleLine.endXProperty().bind(this.tableERModel2.circle2.centerXProperty());
		partialParticipation2.sampleLine.endYProperty().bind(this.tableERModel2.circle2.centerYProperty());
		this.tableERModel2.circle2.centerXProperty().bind(this.tableERModel2.entity.stackPaneRectangle.layoutXProperty().add(this.tableERModel2.entity.resizeRectangle.getWidth()/2) );
		this.tableERModel2.circle2.centerYProperty().bind(this.tableERModel2.entity.stackPaneRectangle.layoutYProperty().add(this.tableERModel2.entity.resizeRectangle.getHeight()/2) );
		
		Point2D startpoint2D = new Point2D((float)partialParticipation1.sampleLine.getStartX(),(float)partialParticipation1.sampleLine.getStartY());
		Point2D endpoint2D = new Point2D((float)partialParticipation1.sampleLine.getEndX(),(float)partialParticipation1.sampleLine.getEndY());
		double  distanceofLinePoints = startpoint2D.distance(endpoint2D);
		
		double deltaYO = (partialParticipation1.sampleLine.getStartY() - partialParticipation1.sampleLine.getEndY());
		double deltaXO = (partialParticipation1.sampleLine.getStartX() - partialParticipation1.sampleLine.getEndX());
		double resultO = Math.toDegrees(Math.atan2(deltaYO, deltaXO));   // angle between line and x - axis in co-ordinates
		System.out.println("Angle (x) of the line :"+ resultO);
		double x1,y1,spaceAngle1 = 2,spaceAngle2 = 2;
		
		if(resultO < 60 && resultO > -45)
			spaceAngle1 = 5;
		x1 = this.tableERModel1.circle2.getCenterX() - (distanceofLinePoints/2) * Math.cos(Math.toRadians(resultO-spaceAngle1)) ;   // a - rcos0
		y1 = this.tableERModel1.circle2.getCenterY() - (distanceofLinePoints/2) * Math.sin(Math.toRadians(resultO-spaceAngle1));    // a - rsin0
		
		line1.setStartX(this.tableERModel1.circle2.getCenterX());
		line1.setStartY(this.tableERModel1.circle2.getCenterY());
		line1.setEndX(x1);
		line1.setEndY(y1);
		
		partialParticipation1.cardinalTextLabel.layoutXProperty().bind(line1.endXProperty());
		partialParticipation1.cardinalTextLabel.layoutYProperty().bind(line1.endYProperty());	
		partialParticipation1.choiceBox.layoutXProperty().bind(line1.endXProperty());
		partialParticipation1.choiceBox.layoutYProperty().bind(line1.endYProperty());
	
	
		startpoint2D = new Point2D((float)partialParticipation2.sampleLine.getStartX(),(float)partialParticipation2.sampleLine.getStartY());
		endpoint2D = new Point2D((float)partialParticipation2.sampleLine.getEndX(),(float)partialParticipation2.sampleLine.getEndY());
		distanceofLinePoints = startpoint2D.distance(endpoint2D);
		
		deltaYO = (partialParticipation2.sampleLine.getEndY() - partialParticipation2.sampleLine.getStartY());
		deltaXO = (partialParticipation2.sampleLine.getEndX() - partialParticipation2.sampleLine.getStartX());
		resultO = Math.toDegrees(Math.atan2(deltaYO, deltaXO));   // angle between line and x - axis in co-ordinates
		System.out.println("Angle (x) of the line :"+ resultO);
		
		spaceAngle2 = 2;
		if(resultO < 60 && resultO > -45)
			spaceAngle2 = 5;
		x1 = this.tableERModel2.circle2.getCenterX() - (distanceofLinePoints/2) * Math.cos(Math.toRadians(resultO+spaceAngle2)) ;   // a - rcos0
		y1 = this.tableERModel2.circle2.getCenterY() - (distanceofLinePoints/2) * Math.sin(Math.toRadians(resultO+spaceAngle2));    // a - rsin0
		
		line2.setStartX(this.tableERModel2.circle2.getCenterX());
		line2.setStartY(this.tableERModel2.circle2.getCenterY());
		line2.setEndX(x1);
		line2.setEndY(y1);
		
		partialParticipation2.cardinalTextLabel.layoutXProperty().bind(line2.endXProperty());
		partialParticipation2.cardinalTextLabel.layoutYProperty().bind(line2.endYProperty());	
		partialParticipation2.choiceBox.layoutXProperty().bind(line2.endXProperty());
		partialParticipation2.choiceBox.layoutYProperty().bind(line2.endYProperty());
	
	}
	public StackPane getStackPaneRectangle() {
		return stackPaneRectangle;
	}

	public Polygon getRhombus() {
		return rhombus;
	}

	public TextArea getTextAreaRhombus() {
		return textAreaRhombus;
	}

	public void setTextAreaRhombus(String textAreaRhombus) {
		this.textAreaRhombus.setText(textAreaRhombus);
	}

}
