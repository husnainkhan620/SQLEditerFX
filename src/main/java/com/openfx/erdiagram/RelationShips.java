package com.openfx.erdiagram;

import java.util.ArrayList;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

public class RelationShips extends Line{
	
	private TitledPaneNode onFocusTitledPane;
	private TitledPaneNode relatedOtherEndTitledPane;

	private ArrayList<LineNode> relationshipLineNodes;
	
	private Polygon polygon;
	
	public RelationShips(TitledPaneNode onFocusTitledPane,TitledPaneNode relatedOtherEndTitledPane,Pane pane) {
		
		this.onFocusTitledPane= onFocusTitledPane;
		this.relatedOtherEndTitledPane = relatedOtherEndTitledPane;
		LineNode lineNode1 = new LineNode();LineNode lineNode2 = new LineNode();LineNode lineNode3 = new LineNode();LineNode lineNode4 = new LineNode();
		lineNode1.setStrokeWidth(2);lineNode2.setStrokeWidth(2);lineNode3.setStrokeWidth(2);lineNode4.setStrokeWidth(2);
		lineNode1.setStrokeType(StrokeType.CENTERED);lineNode2.setStrokeType(StrokeType.CENTERED);lineNode3.setStrokeType(StrokeType.CENTERED);lineNode4.setStrokeType(StrokeType.CENTERED);
	
		relationshipLineNodes = new ArrayList<LineNode>();
		relationshipLineNodes.add(lineNode1);relationshipLineNodes.add(lineNode2);
		relationshipLineNodes.add(lineNode3);relationshipLineNodes.add(lineNode4);
		
		polygon = new Polygon();
		
		lineNode1.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes));
		lineNode1.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes));
		lineNode2.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes));
		lineNode2.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes));
		lineNode3.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes));
		lineNode3.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes));
	
		pane.getChildren().add(lineNode1);pane.getChildren().add(lineNode2);pane.getChildren().add(lineNode3);pane.getChildren().add(lineNode4);
		pane.getChildren().add(polygon);
	}

	public TitledPaneNode getOnFocusTitledPane() {
		return onFocusTitledPane;
	}

	public void setOnFocusTitledPane(TitledPaneNode onFocusTitledPane) {
		this.onFocusTitledPane = onFocusTitledPane;
	}

	public TitledPaneNode getRelatedOtherEndTitledPane() {
		return relatedOtherEndTitledPane;
	}

	public void setRelatedOtherEndTitledPane(TitledPaneNode relatedOtherEndTitledPane) {
		this.relatedOtherEndTitledPane = relatedOtherEndTitledPane;
	}

	public ArrayList<LineNode> getRelationshipLineNodes() {
		return relationshipLineNodes;
	}

	public void setRelationshipLineNodes(ArrayList<LineNode> relationshipLineNodes) {
		this.relationshipLineNodes = relationshipLineNodes;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	
	
}
