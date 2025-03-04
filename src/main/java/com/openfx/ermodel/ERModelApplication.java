package com.openfx.ermodel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class ERModelApplication extends Application{

	public ERModel currentSelectedERModel;
	public ArrayList<ERModel> erModelList = new ArrayList<ERModel>();
	public StackPane stackPane ;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(size.getHeight() + "<--->" +size.getWidth());
		
		Pane pane = new Pane();
		pane.setId("myPane");
		
	    Scene scene = new Scene(pane, 800, 600,Color.WHITE/* Color.rgb(35, 39, 50) */);  
		primaryStage.setTitle("ER Model ");   
		primaryStage.setScene(scene);
					    
		primaryStage.show();

	//	Attribute attribute = new Attribute(100, 50, 300, 300,pane);
	//	erModelList.add(attribute);
	
	//	KeyAttribute keyAttribute = new KeyAttribute(100, 50, 300, 400,pane);
	//	erModelList.add(keyAttribute);
	

	//	MultiValuedAttribute multiValuedAttribute = new MultiValuedAttribute(100,50,350,350,pane);
	//	erModelList.add(multiValuedAttribute);
	
	//	DerivedAttribute derivedAttribute = new DerivedAttribute(100,40,400,450,pane);
	//	erModelList.add(derivedAttribute);
		
		
	//	WeakKeyAttribute weakKeyAttribute = new WeakKeyAttribute(100,40,300,400,pane);
	//	erModelList.add(weakKeyAttribute);
		
	//	Relationship relationship = new Relationship(50, 400, 100,pane);
	//	erModelList.add(relationship);

	//	WeakRelationship weakRelationship = new WeakRelationship(50, 250, 100,pane);
	//	erModelList.add(weakRelationship);
		
	//	Entity entity = new Entity(100,100,600,100,pane);
	//	erModelList.add(entity);
		
		
	//	WeakEntity weakEntity = new WeakEntity(100, 100, 100, 300,pane);
	//	erModelList.add(weakEntity);	
		
	//	AssociativeEntity associativeEntity = new AssociativeEntity(200,100,100,300,pane);
	//	erModelList.add(associativeEntity);
	
	//	PartialParticipation partialParticipation = new PartialParticipation(100, 100, 200, 100, pane);
	//	erModelList.add(partialParticipation);
		
	//	TotalParticipation totalParticipation = new TotalParticipation(100, 300, 200, 300, pane);
	//    erModelList.add(totalParticipation);		
		
	//	ThreeLineHorizontal threeLineHorizontal = new ThreeLineHorizontal(100,100,50,pane);
	//	erModelList.add(threeLineHorizontal);
		
		RelationERModel relationERModel = new RelationERModel(35,300,100,pane,erModelList);
		RelationERModel relationERModel1 = new RelationERModel(35,300,100,pane,erModelList);
		RelationERModel relationERMode2 = new RelationERModel(35,300,100,pane,erModelList);
		
		ArrayList<RelationERModel> table1Relationships = new ArrayList<RelationERModel>();
		table1Relationships.add(relationERModel);
		table1Relationships.add(relationERModel1);
		table1Relationships.add(relationERMode2);
		
		ArrayList<String> table1Attributes = new ArrayList<String>();
		table1Attributes.add("Name");
		table1Attributes.add("Age");
		table1Attributes.add("Company");
		table1Attributes.add("Dealer");
		table1Attributes.add("Address");
		table1Attributes.add("Department");
		table1Attributes.add("State");
		table1Attributes.add("Country");
		
		TableERModel tableERModel1 = new TableERModel(400,400,200,table1Attributes,table1Relationships,pane,erModelList);
				
		
		ArrayList<String> table2Relationships = new ArrayList<String>();
		table2Relationships.add("WORKS_FOR");
		table2Relationships.add("MANAGES");
		
		ArrayList<String> table2Attributes = new ArrayList<String>();
		table2Attributes.add("Name");
		table2Attributes.add("Age");
		table2Attributes.add("Company");
		table2Attributes.add("Dealer");
		table2Attributes.add("Address");
		table2Attributes.add("Department");
		
	//	TableERModel tableERModel2 = new TableERModel(erModelList,900,300,200,table2Attributes,table2Relationships,pane);
	//	erModelList.add(tableERModel2);
		
		/*
		StackPane stackPaneCircle = new StackPane();
		stackPaneCircle.setLayoutX(500);
		stackPaneCircle.setLayoutY(300);
		Circle newCircle = new Circle(50);
		newCircle.getStrokeDashArray().addAll(2d);
		newCircle.setStrokeWidth(1);
		newCircle.setStroke(Color.BLACK);
		newCircle.setFill(Color.CADETBLUE);
		TextArea textArea = new TextArea();
		textArea.setText("This is a Circle");
		textArea.setMaxSize(newCircle.getRadius() + 0.25*newCircle.getRadius(), newCircle.getRadius() + 0.25*newCircle.getRadius());
		textArea.setId("mytextarea");
		textArea.setWrapText(true);
		stackPaneCircle.getChildren().addAll(newCircle,textArea);
		enableDragAndDrop(stackPaneCircle,pane);
		pane.getChildren().add(stackPaneCircle);
		*/
		
		
		pane.setOnMouseClicked(event ->{	
			
			System.out.println("event.getSceneX() : "+event.getSceneX());
			System.out.println("event.getSceneY() : "+event.getSceneY());
		
			for(ERModel erModel : erModelList) {
			
				
				if(erModel instanceof PartialParticipation) {
					System.out.println("ITs PArtialPArticipation");
					double minX = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMinX();
					double maxX = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMaxX();
					double minY = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMinY();
					double maxY = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMaxY();
					if(! ((event.getSceneX() >= minX && event.getSceneX() <= maxX) && (event.getSceneY() >= minY && event.getSceneY() <= maxY)) ) {
						System.out.println("Clicked outside the entity");
						pane.getChildren().remove(erModel.leftAnchor);
						pane.getChildren().remove(erModel.rightAnchor);
				 
						((PartialParticipation)erModel).group.getChildren().clear();
					
						((PartialParticipation)erModel).group.getChildren().addAll(((PartialParticipation)erModel).cardinalTextLabel,((PartialParticipation)erModel).sampleLine);
					}
				
				}
				else if(erModel instanceof TotalParticipation) {
					System.out.println("ITs totalPArticipation");
				
					double minX = ((TotalParticipation)erModel).sampleLine1.getBoundsInParent().getMinX();
					double maxX = ((TotalParticipation)erModel).sampleLine1.getBoundsInParent().getMaxX();
					double minY = ((TotalParticipation)erModel).sampleLine1.getBoundsInParent().getMinY();
					double maxY = ((TotalParticipation)erModel).sampleLine1.getBoundsInParent().getMaxY();
					if(! ((event.getSceneX() >= minX && event.getSceneX() <= maxX) && (event.getSceneY() >= minY && event.getSceneY() <= maxY)) ) {
						System.out.println("Clicked outside the entity");
						pane.getChildren().remove(erModel.leftAnchor);
						pane.getChildren().remove(erModel.rightAnchor);
				 
						if(!pane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineLeft1)) {
							pane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineLeft1);
						}
						if(!pane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineLeft2)) {
							pane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineLeft2);
						}
						
						if(!pane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineRight1)) {
							pane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineRight1);
						}
						if(!pane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineRight2)) {
							pane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineRight2);
						}
						if(!pane.getChildren().contains(((TotalParticipation)erModel).sampleLine1)) {
							pane.getChildren().remove(((TotalParticipation)erModel).sampleLine1);
						}
						
						((TotalParticipation)erModel).group.getChildren().clear();

						
						((TotalParticipation)erModel).group.getChildren().addAll(((TotalParticipation)erModel).cardinalTextLabel,((TotalParticipation)erModel).sampleLine1,((TotalParticipation)erModel).parallerLine1,((TotalParticipation)erModel).parallerLine2);
					
					}
						
				}
				else if(erModel instanceof ThreeLineHorizontal) {
					
					double minX = ((ThreeLineHorizontal)erModel).group.getBoundsInParent().getMinX();
					double maxX = ((ThreeLineHorizontal)erModel).group.getBoundsInParent().getMaxX();
					double minY = ((ThreeLineHorizontal)erModel).group.getBoundsInParent().getMinY();
					double maxY = ((ThreeLineHorizontal)erModel).group.getBoundsInParent().getMaxY();
					if(! ((event.getSceneX() >= minX && event.getSceneX() <= maxX) && (event.getSceneY() >= minY && event.getSceneY() <= maxY)) ) {
						System.out.println("Clicked outside the group");
						if(pane.getChildren().contains(((ThreeLineHorizontal)erModel).linePleftCircle))
							pane.getChildren().remove(((ThreeLineHorizontal)erModel).linePleftCircle);
						if(pane.getChildren().contains(((ThreeLineHorizontal)erModel).lineQCenterCircle))
							pane.getChildren().remove(((ThreeLineHorizontal)erModel).lineQCenterCircle);
						if(pane.getChildren().contains(((ThreeLineHorizontal)erModel).lineRRightCircle))
							pane.getChildren().remove(((ThreeLineHorizontal)erModel).lineRRightCircle);
					}
				}
				
				else if(erModel.stackPaneRectangle != null) {
					System.out.println("Their is an Entity ");
					System.out.println(erModel.stackPaneRectangle.getBoundsInParent());
					double minX = erModel.stackPaneRectangle.getBoundsInParent().getMinX();
					double maxX = erModel.stackPaneRectangle.getBoundsInParent().getMaxX();
					double minY = erModel.stackPaneRectangle.getBoundsInParent().getMinY();
					double maxY = erModel.stackPaneRectangle.getBoundsInParent().getMaxY();
					
					if(! ((event.getSceneX() >= minX && event.getSceneX() <= maxX) && (event.getSceneY() >= minY && event.getSceneY() <= maxY)) ) {
						System.out.println("Clicked outside the entity");
						
						 pane.getChildren().remove(erModel.topAnchor);
						 pane.getChildren().remove(erModel.topLeftAnchor);
						 pane.getChildren().remove(erModel.topRightAnchor);
						 
						 pane.getChildren().remove(erModel.leftAnchor);
						 pane.getChildren().remove(erModel.rightAnchor);
						 
						 pane.getChildren().remove(erModel.bottomleftAnchor);
						 pane.getChildren().remove(erModel.bottomAnchor);
						 pane.getChildren().remove(erModel.bottomRightAnchor);
						 
						 ((Rectangle)  erModel.stackPaneRectangle.getChildren().get(0)).setStroke(Color.GAINSBORO);
						
					}
				}
				if(erModel instanceof TableERModel) {
					System.out.println("Their is an TableERModel ");
					System.out.println(((TableERModel)erModel).circle2.getBoundsInParent());
					double minX = ((TableERModel)erModel).circle2.getBoundsInParent().getMinX();
					double maxX = ((TableERModel)erModel).circle2.getBoundsInParent().getMaxX();
					double minY = ((TableERModel)erModel).circle2.getBoundsInParent().getMinY();
					double maxY = ((TableERModel)erModel).circle2.getBoundsInParent().getMaxY();
					
					if(! ((event.getSceneX() >= minX && event.getSceneX() <= maxX) && (event.getSceneY() >= minY && event.getSceneY() <= maxY)) ) {
						System.out.println("Clicked outside the TableERModel");
						
						 ((TableERModel)erModel).circle2.setStroke(Color.TRANSPARENT);
					}
				}
			}
		}
		);
		
		scene.getStylesheets().add(ERModelApplication.class.getResource("/testLayout.css").toExternalForm());
		
	}
	
	public static void main(String[] args) {
		   launch(ERModelApplication.class, args);
	}
	
}

