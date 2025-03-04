package org.openjfx.fx;

import java.util.ArrayList;

import com.openfx.ermodel.ERModelApplication;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class JoiningLinesProperties extends Application{

	public Pane mainPane;
	public Line lineP;
	public Line lineQ;
	public Line lineR;
	public Group group;
	public Circle linePleftCircle;
	public Circle lineQCenterCircle;
	public Circle lineRRightCircle;
	double orgSceneX, orgSceneY;//Used to help keep up with change in mouse position
	double lineLenght = 50;
	double layoutX = 100;
	double layoutY = 100;
	double Px1,Py1,Px2,Py2;
	double Qx1,Qy1,Qx2,Qy2;
	double Rx1,Ry1,Rx2,Ry2;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
		mainPane = new Pane();
		group = new Group();
	    Scene scene = new Scene(mainPane, 600, 600/* Color.rgb(35, 39, 50) */);  
	    scene.getStylesheets().add(JoiningLinesProperties.class.getResource("/testLayout.css").toExternalForm());
		primaryStage.setTitle("No DataBase Connection ");   
		primaryStage.setScene(scene);
					    
		primaryStage.show();
		
		Px1 = layoutX;Py1 = layoutY;Px2 = Px1+lineLenght;Py2 = Py1;
		linePleftCircle = new Circle(Px1,Py1,5);
		linePleftCircle.setStroke(Color.GAINSBORO);
		linePleftCircle.setFill(Color.GAINSBORO);
		
		Qx1 = Px2;Qy1=Py2;Qx2=Px2;Qy2=Py2+lineLenght;
		lineQCenterCircle = new Circle(Qx1,Qy1 +  (Qy2-Qy1)/2,5);
		lineQCenterCircle.setStroke(Color.GAINSBORO);
		lineQCenterCircle.setFill(Color.GAINSBORO);
				
		Rx1 = Qx2;Ry1=Qy2;;Rx2=Qx2+lineLenght;Ry2=Qy2;
		lineRRightCircle = new Circle(Rx2,Ry2,5);
		lineRRightCircle.setStroke(Color.GAINSBORO);
		lineRRightCircle.setFill(Color.GAINSBORO);
				
		lineP = new Line();   //  P(x1,y1) - P(x2,y2)
		lineP.setStartX(Px1);
		lineP.setStartY(Py1);
		lineP.setEndX(Px2);
		lineP.setEndY(Py2);
		
	
		
		lineQ = new Line();   // Q(x1,y1) -  Q(x2,y2)
		lineQ.setStartX(Qx1);
		lineQ.setStartY(Qy1);
	//	lineQ.startXProperty().bind(  lineP.endXProperty());
	//	lineQ.startYProperty().bind(lineP.endYProperty());
		lineQ.setEndX(Qx2);
		lineQ.setEndY(Qy2);
		
		 lineR = new Line();  // R(x1,y1)  - R(x2,y2)
		lineR.setStartX(Rx1);
		lineR.setStartY(Ry1);
		lineR.setEndX(Rx2);
		lineR.setEndY(Ry2);
		
		
		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(lineP);lines.add(lineQ);lines.add(lineR);
		
		group.getChildren().addAll(lineP,lineQ,lineR);
		
		mainPane.getChildren().addAll(group,linePleftCircle,lineQCenterCircle,lineRRightCircle);
		
		for(Line line : lines) 
		{
					
				line.setOnMouseEntered(event ->{
					((Line) event.getSource()).getScene().setCursor(Cursor.MOVE);
					
				});
				
				line.setOnMouseExited(event ->{
					 ((Line) event.getSource()).getScene().setCursor(Cursor.DEFAULT);
					
				});
				
				 final double[] offset = new double[2];
				 line.setOnMousePressed(event ->{
					 
					if(!mainPane.getChildren().contains(linePleftCircle))
						mainPane.getChildren().add(linePleftCircle);
					if(!mainPane.getChildren().contains(lineQCenterCircle))
						mainPane.getChildren().add(lineQCenterCircle);
					if(!mainPane.getChildren().contains(lineRRightCircle))
						mainPane.getChildren().add(lineRRightCircle);
					
					linePleftCircle.setStroke(Color.BLACK);
					linePleftCircle.setFill(Color.BLACK);
					lineQCenterCircle.setStroke(Color.BLACK);
					lineQCenterCircle.setFill(Color.BLACK);
					lineRRightCircle.setStroke(Color.BLACK);
					lineRRightCircle.setFill(Color.BLACK);
				
					 offset[0] = event.getSceneX() - group.getLayoutX();
		             offset[1] = event.getSceneY() - group.getLayoutY() ;
		             
		             orgSceneX = event.getSceneX();//Store current mouse position
			         orgSceneY = event.getSceneY();//Store current mouse position
		             
				});
				
				 line.setOnMouseDragged(event ->{
					
					double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
				    double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
		
		    		
		    		linePleftCircle.setCenterX(linePleftCircle.getCenterX() + offSetX);
		    		linePleftCircle.setCenterY(linePleftCircle.getCenterY() + offSetY);
		    		lineQCenterCircle.setCenterX(lineQCenterCircle.getCenterX() + offSetX);
		    		lineQCenterCircle.setCenterY(lineQCenterCircle.getCenterY() + offSetY);
		    		lineRRightCircle.setCenterX(lineRRightCircle.getCenterX() + offSetX);
		    		lineRRightCircle.setCenterY(lineRRightCircle.getCenterY() + offSetY);
		    		
		    		lineP.setStartX( lineP.getStartX() + offSetX);
		    		lineP.setStartY(lineP.getStartY() + offSetY);
		    		lineP.setEndX(lineP.getEndX() + offSetX);
		    		lineP.setEndY(lineP.getEndY() + offSetY);    		
		    		
		    		lineQ.setStartX( lineQ.getStartX() + offSetX);
		    		lineQ.setStartY(lineQ.getStartY() + offSetY);
		    		lineQ.setEndX(lineQ.getEndX() + offSetX);
		    		lineQ.setEndY(lineQ.getEndY() + offSetY);
		    		
		    		lineR.setStartX( lineR.getStartX() + offSetX);
		    		lineR.setStartY(lineR.getStartY() + offSetY);
		    		lineR.setEndX(lineR.getEndX() + offSetX);
		    		lineR.setEndY(lineR.getEndY() + offSetY);
		    		    		
			        orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
		            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves	
				
				});
		}		 
		
		linePleftCircle.setOnMouseEntered((event) -> {
	            ((Circle) event.getSource()).getScene().setCursor(Cursor.E_RESIZE);
	        });
		linePleftCircle.setOnMousePressed((event) -> {
	            orgSceneX = event.getSceneX();//Store current mouse position
	            orgSceneY = event.getSceneY();//Store current mouse position
	        });
		linePleftCircle.setOnMouseDragged((event) -> {
	            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
	            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
	            
	            linePleftCircle.setCenterX(linePleftCircle.getCenterX() + offSetX);
	            linePleftCircle.setCenterY(linePleftCircle.getCenterY() + offSetY);
	            Px1 = linePleftCircle.getCenterX();
	            Py1 = linePleftCircle.getCenterY();
	        	lineP.setStartX(Px1);
	        	lineP.setStartY(Py1);
	    		
	        	lineP.setEndY(Py1);
	        	
	        	lineQ.setStartX(lineP.getEndX());
	        	lineQ.setStartY(lineP.getEndY());
	        	
	        	lineQCenterCircle.setCenterX(lineP.getEndX());
	        	lineQCenterCircle.setCenterY(lineQ.getStartY() +  (lineQ.getEndY()-lineQ.getStartY())/2);
	           	
	            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
	            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
	            
	        });
		linePleftCircle.setOnMouseExited((event) -> {
			linePleftCircle.getScene().setCursor(null);
	        });
		
		lineRRightCircle.setOnMouseEntered((event) -> {
            ((Circle) event.getSource()).getScene().setCursor(Cursor.E_RESIZE);
        });
		lineRRightCircle.setOnMousePressed((event) -> {
            orgSceneX = event.getSceneX();//Store current mouse position
            orgSceneY = event.getSceneY();//Store current mouse position
        });
		lineRRightCircle.setOnMouseDragged((event) -> {
            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
            
            lineRRightCircle.setCenterX(lineRRightCircle.getCenterX() + offSetX);
            lineRRightCircle.setCenterY(lineRRightCircle.getCenterY() + offSetY);
            
            Rx2 = lineRRightCircle.getCenterX();
            Ry2 = lineRRightCircle.getCenterY();
        	lineR.setEndX(Rx2);
        	lineR.setEndY(Ry2);
    		
        	lineR.setStartY(Ry2);
        	lineQ.setEndY(Ry2);
        	
        	lineQCenterCircle.setCenterX(lineR.getStartX());
        	lineQCenterCircle.setCenterY(lineR.getStartY() +  (lineQ.getStartY()-lineR.getStartY())/2);
        	
        	
            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
            
        });
		lineRRightCircle.setOnMouseExited((event) -> {
			lineRRightCircle.getScene().setCursor(null);
        });
	        
		lineQCenterCircle .setOnMouseEntered((event) -> {
            ((Circle) event.getSource()).getScene().setCursor(Cursor.E_RESIZE);
        });
		lineQCenterCircle.setOnMousePressed((event) -> {
            orgSceneX = event.getSceneX();//Store current mouse position
            orgSceneY = event.getSceneY();//Store current mouse position
        });
		lineQCenterCircle.setOnMouseDragged((event) -> {
            double offSetX = event.getSceneX() - orgSceneX;//Find change in mouse X position
            double offSetY = event.getSceneY() - orgSceneY;//Find change in mouse X position
            
            lineQCenterCircle.setCenterX(lineQCenterCircle.getCenterX() + offSetX);
            
            lineP.setEndX(lineP.getEndX() + offSetX);
            lineR.setStartX(lineR.getStartX() + offSetX);
            
            lineQ.setStartX(lineQ.getStartX() + offSetX);
            lineQ.setEndX(lineQ.getEndX() + offSetX);

            orgSceneX = event.getSceneX();//save last mouse position to recalculate change in mouse postion as the circle moves
            orgSceneY = event.getSceneY();//save last mouse position to recalculate change in mouse postion as the circle moves
            
        });
		lineQCenterCircle.setOnMouseExited((event) -> {
			lineQCenterCircle.getScene().setCursor(null);
        });
		
		
		mainPane.setOnMouseClicked(event ->{	
		
			double minX = group.getBoundsInParent().getMinX();
			double maxX = group.getBoundsInParent().getMaxX();
			double minY = group.getBoundsInParent().getMinY();
			double maxY = group.getBoundsInParent().getMaxY();
			if(! ((event.getSceneX() >= minX && event.getSceneX() <= maxX) && (event.getSceneY() >= minY && event.getSceneY() <= maxY)) ) {
				System.out.println("Clicked outside the group");
				if(mainPane.getChildren().contains(linePleftCircle))
					mainPane.getChildren().remove(linePleftCircle);
				if(mainPane.getChildren().contains(lineQCenterCircle))
					mainPane.getChildren().remove(lineQCenterCircle);
				if(mainPane.getChildren().contains(lineRRightCircle))
					mainPane.getChildren().remove(lineRRightCircle);
			}
			
		});
		
		scene.getStylesheets().add(ERModelApplication.class.getResource("/testLayout.css").toExternalForm());
	}
	
	
	public static void main(String[] args) {
		launch(JoiningLinesProperties.class, args);
	}
	
}
