package com.openfx.ermodel;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ERModel extends Line{
	
	public StackPane stackPaneRectangle;
	public Rectangle resizeRectangle;
	  
	//Circles will be used to do the event handling/movements
    public Circle leftAnchor ;
    public Circle topLeftAnchor;
    public Circle bottomleftAnchor;
    
    
    public Circle topAnchor ;
    public Circle bottomAnchor ;
    
    public Circle topRightAnchor ;
    public Circle rightAnchor ;
    public  Circle bottomRightAnchor;
}
