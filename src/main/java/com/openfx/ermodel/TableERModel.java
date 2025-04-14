package com.openfx.ermodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TableERModel extends ERModel{

	public String tableName;
	double offsetX;   double offsetY;
	double orgSceneX,orgSceneY;
	public Circle circle1;
	public Circle circle2;
	public Entity entity;
	public ArrayList<Attribute> attributeArrayList = new ArrayList<Attribute>();
	public ArrayList<KeyAttribute> keyAttributeArrayList = new ArrayList<KeyAttribute>();
	
	public TableERModel( double circle1X,double circle1Y,double circle1Radius,Map<String,String>  tableAllattributesMap,String tableName,Pane pane,ArrayList<ERModel> erModelList,Stack<Runnable> undoStack) {
		
		this.tableName = tableName;
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
		            
		            // removing and then redrawing below
		            for(Attribute attribute : attributeArrayList) {
		            	
		            	pane.getChildren().remove(attribute.connectionLine);
		            	pane.getChildren().remove(attribute.stackPaneRectangle);
		            }
		            
		            redrawTableERModel(tableAllattributesMap,erModelList,circle2.getCenterX(),circle2.getCenterY(),circle1Radius,pane,undoStack);
		            
		            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
		            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
			});
			circle2.setOnMouseExited((event) -> {
				circle2.getScene().setCursor(null);
		    });
			
		
		entity = new Entity(tableName.length()*10,50,circle1X-tableName.length()*10/2,circle1Y - 50/2,tableName,pane);
		
		redrawTableERModel(tableAllattributesMap,erModelList,circle1X,circle1Y,circle1Radius,pane,undoStack);
		
		// This below is to bring to focus
		pane.getChildren().remove(entity.stackPaneRectangle);
		pane.getChildren().add(entity.stackPaneRectangle);
		
		 final double[] offset = new double[2];
		 
		 entity.stackPaneRectangle.setOnMousePressed(event -> {
		        	
	        	 offset[0] = event.getSceneX() - entity.stackPaneRectangle.getLayoutX();
	             offset[1] = event.getSceneY() - entity.stackPaneRectangle.getLayoutY() ;
	             
	             
	             entity.resizeRectangle.setStroke(Color.BLACK);
	 			
	 			if(!pane.getChildren().contains(entity.leftAnchor)) {
	 				entity.leftAnchor.setFill(Color.BLACK);
	 				entity.leftAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.leftAnchor);
	 			}

	 			if(!pane.getChildren().contains(entity.topLeftAnchor)) {
	 				entity.topLeftAnchor.setFill(Color.BLACK);
	 				entity.topLeftAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.topLeftAnchor);
	 			}
	 			
	 			if(!pane.getChildren().contains(entity.bottomleftAnchor)) {
	 				entity.bottomleftAnchor.setFill(Color.BLACK);
	 				entity.bottomleftAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.bottomleftAnchor);
	 			}
	 			
	 			if(!pane.getChildren().contains(entity.topAnchor)) {
	 				entity.topAnchor.setFill(Color.BLACK);
	 				entity.topAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.topAnchor);
	 			}
	 			
	 			if(!pane.getChildren().contains(entity.bottomAnchor)) {
	 				entity.bottomAnchor.setFill(Color.BLACK);
	 				entity.bottomAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.bottomAnchor);
	 			}
	 			
	 			if(!pane.getChildren().contains(entity.topRightAnchor)) {
	 				entity.topRightAnchor.setFill(Color.BLACK);
	 				entity.topRightAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.topRightAnchor);
	 			}
	 			
	 			if(!pane.getChildren().contains(entity.rightAnchor)) {
	 				entity.rightAnchor.setFill(Color.BLACK);
	 				entity.rightAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.rightAnchor);
	 			}
	 			
	 			if(!pane.getChildren().contains(entity.bottomRightAnchor)) {
	 				entity.bottomRightAnchor.setFill(Color.BLACK);
	 				entity.bottomRightAnchor.setStroke(Color.BLACK);
	 				pane.getChildren().add(entity.bottomRightAnchor);
	 			} 
	                   
	 			
	 		// Store original position for undo
            double originalX = entity.stackPaneRectangle.getLayoutX();
            double originalY = entity.stackPaneRectangle.getLayoutY();
            
            double attributeArrayListX[] = new double[attributeArrayList.size()];	
  	 		double attributeArrayListY[] = new double[attributeArrayList.size()];
  	 		  
  	 		for(int i=0;i<attributeArrayList.size();i++) {
  	 			  attributeArrayListX[i] =  attributeArrayList.get(i).stackPaneRectangle.getLayoutX();
  		          attributeArrayListY[i] = attributeArrayList.get(i).stackPaneRectangle.getLayoutY();	  
  	 		}
  	 		  
            undoStack.push(() -> {
                entity.stackPaneRectangle.setLayoutX(originalX);
                entity.stackPaneRectangle.setLayoutY(originalY);
                for(int i=0;i<attributeArrayList.size();i++) {
                	attributeArrayList.get(i).stackPaneRectangle.setLayoutX(attributeArrayListX[i]);
                    attributeArrayList.get(i).stackPaneRectangle.setLayoutY(attributeArrayListY[i]);  
    	 		}    
            });

	             orgSceneX = event.getSceneX();//Store current mouse position
		         orgSceneY = event.getSceneY();//Store current mouse position
	          
	        });
	        
	        // When dragging, update the TitledPane position
		 entity.stackPaneRectangle.setOnMouseDragged(event -> {
	        	
	            // Restrict movement within the bounds of the mainPane
	            double newX = event.getSceneX() - offset[0];
	    		double newY = event.getSceneY() - offset[1];

	    		double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
		        double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position 
		            
	    		// Restrict movement within the bounds of the mainPane
	    		if (newX >= 0 && newX + entity.stackPaneRectangle.getWidth() <= pane.getWidth()) {
	    			entity.stackPaneRectangle.setLayoutX(newX);
	    		}
	    		if (newY >= 0 && newY + entity.stackPaneRectangle.getHeight() <= pane.getHeight()) {
	    			entity.stackPaneRectangle.setLayoutY(newY);
	    		}
	    		
	    	//	System.out.println("offSetX --> "+ offSetX);
	    	//	System.out.println("offSetY --> "+ offSetY);
	    		
	    		for(Attribute attribute : attributeArrayList) {    	
		            	attribute.stackPaneRectangle.setLayoutX(attribute.stackPaneRectangle.getLayoutX()+offSetX);
		            	attribute.stackPaneRectangle.setLayoutY(attribute.stackPaneRectangle.getLayoutY() + offSetY);
		        }
	    		
				
	    		 
				if(entity.stackPaneRectangle != null) {
					entity.topAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX() + entity.resizeRectangle.getWidth()/2);
					entity.topAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY() );
		    		
					entity.topLeftAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX());
					entity.topLeftAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY());
		    		
					entity.topRightAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX() + entity.stackPaneRectangle.getWidth());
					entity.topRightAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY());
		    		
					entity.leftAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX());
					entity.leftAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY() + entity.stackPaneRectangle.getHeight()/2);
		    		
					entity.bottomleftAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX());
					entity.bottomleftAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY() + entity.stackPaneRectangle.getHeight());
		    		
					entity.bottomAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX() + entity.stackPaneRectangle.getWidth()/2);
					entity.bottomAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY() + entity.stackPaneRectangle.getHeight());
		    		
					entity.rightAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX() + entity.stackPaneRectangle.getWidth());
					entity.rightAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY() + entity.stackPaneRectangle.getHeight()/2);
		    		
					entity.bottomRightAnchor.setCenterX(entity.stackPaneRectangle.getLayoutX() + entity.stackPaneRectangle.getWidth());
					entity.bottomRightAnchor.setCenterY(entity.stackPaneRectangle.getLayoutY() + entity.stackPaneRectangle.getHeight());
				
				}	
				
				 orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
		         orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	        });  
		 
		 entity.stackPaneRectangle.setOnMouseReleased(event -> {
			pane.getChildren().remove(entity.stackPaneRectangle);
			pane.getChildren().add(entity.stackPaneRectangle);
		 });
		 
		 erModelList.add(entity);
	}
    
    int MAX_COLUMNS_PER_RING = 10;
    int RING_SPACING = 150;
	    
	public void redrawTableERModel(Map<String,String> tableAllattributesMap,ArrayList<ERModel> erModelList,double circle1X,double circle1Y, double circle1Radius,Pane pane,Stack<Runnable> undoStack) {
		
		
		 double layoutX = 100;double layoutY = 100;
		 double preferredWidth = 60;double preferredHeight = 20;
		 
		 int columnsPlaced = 0;
	     int ringNumber = 0;
	        
	     while (columnsPlaced < tableAllattributesMap.size()) {
	            int columnsInThisRing = Math.min(MAX_COLUMNS_PER_RING, tableAllattributesMap.size() - columnsPlaced);
	            
	            double radius = circle1Radius + ringNumber * RING_SPACING;
	            
	            for (int i = 0; i < columnsInThisRing; i++) { 	
	    			// Split it bSED ON # FOR pri ATTRVIBUTE 
	    			
	    			Attribute nameAttribute = new Attribute((String)tableAllattributesMap.values().toArray()[columnsPlaced],preferredWidth, preferredHeight, layoutX, layoutY,pane,undoStack);
	    			erModelList.add(nameAttribute);
	    			attributeArrayList.add(nameAttribute);
	    	
	        		
	    			Line _line =  nameAttribute.getConnectionLine();
	    	
	    			if(entity != null) {
	    				_line.setStartX(entity.stackPaneRectangle.getLayoutX() + entity.resizeRectangle.getWidth()/2);
	    				_line.setStartY(entity.stackPaneRectangle.getLayoutY() + entity.resizeRectangle.getHeight()/2);
	    				_line.startXProperty().bind(entity.stackPaneRectangle.layoutXProperty().add(entity.resizeRectangle.getWidth()/2) );
	    		    	_line.startYProperty().bind(entity.stackPaneRectangle.layoutYProperty().add(entity.resizeRectangle.getHeight()/2) );
	    			}
	    		
	    			double angle = 2 * Math.PI * i / columnsInThisRing;
	                // Calculate positio
	    	        
	    	        
	    			double _x1,_y1;
	        		
	    			_x1 =  circle1X + radius * Math.cos(angle);
	    			_y1 = circle1Y + radius * Math.sin(angle);
	    			
	        		
	        		_line.setEndX(_x1);
	        		_line.setEndY(_y1);
	        	
	        		nameAttribute.getStackPaneRectangle().setLayoutX(_line.getEndX()-preferredWidth);
	        		nameAttribute.getStackPaneRectangle().setLayoutY(_line.getEndY()-preferredHeight);
	        	
	        		
	        		_line.endXProperty().bind(nameAttribute.getStackPaneRectangle().layoutXProperty().add(preferredWidth));
	        		_line.endYProperty().bind(nameAttribute.getStackPaneRectangle().layoutYProperty().add(preferredHeight));
	        	
	        		
	    	        columnsPlaced ++;
	            }
	            
	            // alter +1 - 1 ovet iterations
	             MAX_COLUMNS_PER_RING = MAX_COLUMNS_PER_RING - 1;
	            ringNumber++;
	        }

	    /* 
		Integer numberofPartitions = tableAllattributesMap.size()-1;
		double spacingAngle = 270/numberofPartitions;  // to open the right side slit

		for(int i = 0;i<=numberofPartitions;i++) {	
			
			// Split it bSED ON # FOR pri ATTRVIBUTE 
			
			Attribute nameAttribute = new Attribute((String)tableAllattributesMap.values().toArray()[i],preferredWidth, preferredHeight, layoutX, layoutY,pane,undoStack);
			erModelList.add(nameAttribute);
			attributeArrayList.add(nameAttribute);
		//	layoutX = layoutX+ preferredWidth*2 + preferredSpacing;
    		
			Line _line =  nameAttribute.getConnectionLine();
	
			if(entity != null) {
				_line.setStartX(entity.stackPaneRectangle.getLayoutX() + entity.resizeRectangle.getWidth()/2);
				_line.setStartY(entity.stackPaneRectangle.getLayoutY() + entity.resizeRectangle.getHeight()/2);
				_line.startXProperty().bind(entity.stackPaneRectangle.layoutXProperty().add(entity.resizeRectangle.getWidth()/2) );
		    	_line.startYProperty().bind(entity.stackPaneRectangle.layoutYProperty().add(entity.resizeRectangle.getHeight()/2) );
			}
		
			
			double _x1,_y1;
    		
    		_x1 = circle1X - circle1Radius * Math.cos(Math.toRadians(i*spacingAngle-135)) ;   //  to open the right side slit
    		_y1 = circle1Y - circle1Radius * Math.sin(Math.toRadians(i*spacingAngle-135));    // to open the right side slit
    		
    		_line.setEndX(_x1);
    		_line.setEndY(_y1);
    	
    		nameAttribute.getStackPaneRectangle().setLayoutX(_line.getEndX()-preferredWidth);
    		nameAttribute.getStackPaneRectangle().setLayoutY(_line.getEndY()-preferredHeight);
    	
    		
    		_line.endXProperty().bind(nameAttribute.getStackPaneRectangle().layoutXProperty().add(preferredWidth));
    		_line.endYProperty().bind(nameAttribute.getStackPaneRectangle().layoutYProperty().add(preferredHeight));
    	
		} */
		
		// Try to kill the existing entity and create a new one with exising entities dimentions
		erModelList.add(this);
	}
	
}

