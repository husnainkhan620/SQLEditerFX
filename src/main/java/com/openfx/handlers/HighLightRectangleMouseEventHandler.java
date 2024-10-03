package com.openfx.handlers;

import java.util.ArrayList;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.ai.ConnectionsConstants;
import com.openfx.placeholders.ImageItemsHolder;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class HighLightRectangleMouseEventHandler implements EventHandler<MouseEvent>{

	private Rectangle rectangle;
	private Menu_Items_FX menu_Items_FX;
	private ArrayList<Rectangle> availableHighRectangleConnections;
	private NewMenuItemEventHandler newMenuItemEventHandler;
	private StackPane stackPane ;

	
	public HighLightRectangleMouseEventHandler(Rectangle rectangle,ArrayList<Rectangle> availableHighRectangleConnections,Menu_Items_FX menu_Items_FX,NewMenuItemEventHandler newMenuItemEventHandler,StackPane stackPane){
		this.rectangle = rectangle;
		this.menu_Items_FX = menu_Items_FX;
		this.availableHighRectangleConnections = availableHighRectangleConnections;
		this.newMenuItemEventHandler = newMenuItemEventHandler;
		this.stackPane = stackPane;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			rectangle.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.web("#4977A3")),
	            new Stop(0.5, Color.web("#B0C6DA")),
	            new Stop(1,Color.web("#9CB6CF")),}));
			rectangle.setStroke(Color.web("#D0E6FA"));
			rectangle.setArcHeight(3.5);
			rectangle.setArcWidth(3.5);
		}
		else if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			for(Rectangle availableHighRectangleConnection  : availableHighRectangleConnections) {
				
			availableHighRectangleConnection.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
			            new Stop[]{
			            new Stop(0,Color.WHITE),
			            new Stop(0.5, Color.WHITE),
			            new Stop(1,Color.WHITE),}));
			availableHighRectangleConnection.setStroke(Color.WHITE);
			availableHighRectangleConnection.setArcHeight(3.5);
			availableHighRectangleConnection.setArcWidth(3.5);
			}	
			rectangle.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
			            new Stop[]{
			            new Stop(0,Color.web("#4977A3")),
			            new Stop(0.5, Color.web("#B0C6DA")),
			            new Stop(1,Color.web("#9CB6CF")),}));
			rectangle.setStroke(Color.web("#D0E6FA"));
			rectangle.setArcHeight(3.5);
			rectangle.setArcWidth(3.5);
			
			// For DB connection Initial Pop up
			if(newMenuItemEventHandler != null) {
				newMenuItemEventHandler.buttonNext.setDisable(false);
				menu_Items_FX.currentConnectionSelected = rectangle.getClass().getSimpleName().replace("Rectangle", "");
			}
			else {
				// This is scenario from exising connections pop up
				System.out.println("Exiting pop up stackpane "+this.stackPane);													// the below should give the connection name from open exisintg connecction selected
				menu_Items_FX.currentConnectionSelected = rectangle.getClass().getSimpleName().replace("Rectangle", "")+"##"+ ((Label)this.stackPane.getChildren().get(1)).getText();
				menu_Items_FX.sqlQueryRunButtonSubmit.connectExistingConnection.setDisable(false);
			}
				
			System.out.println("currentConnectionSelected : "+menu_Items_FX.currentConnectionSelected);
			
			// If the connection is not yet established , it will get blocked during query run check their
			if(menu_Items_FX.currentConnectionSelected.contains("##")) {
				System.out.println("Existing connection hence ## avaialbe");
				// the below needs to be moved to appropriate place likely on Connect Button click
				
				
			}
			else
				System.out.println("New DB conection hence no connection name yet");
				
		}
		else if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
			
			Boolean conditionValue = this.stackPane != null ? !menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(rectangle.getClass().getSimpleName().replace("Rectangle", "")+"##"+this.stackPane.getChildren().get(1)) : !menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(rectangle.getClass().getSimpleName().replace("Rectangle", ""));
			
			if(this.stackPane == null) {
				conditionValue =  !menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(rectangle.getClass().getSimpleName().replace("Rectangle", ""));
			}
			else {
				conditionValue = !menu_Items_FX.currentConnectionSelected.equalsIgnoreCase(rectangle.getClass().getSimpleName().replace("Rectangle", "")+"##"+((Label)this.stackPane.getChildren().get(1)).getText());;
			}
			if(conditionValue)
			{
				rectangle.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
				            new Stop[]{
				            new Stop(0,Color.WHITE),
				            new Stop(0.5, Color.WHITE),
				            new Stop(1,Color.WHITE),}));
				rectangle.setStroke(Color.WHITE);
				rectangle.setArcHeight(3.5);
				rectangle.setArcWidth(3.5);
		   }
		}
	}

	
	
	
	

}
