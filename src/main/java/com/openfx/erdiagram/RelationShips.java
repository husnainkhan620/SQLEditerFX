package com.openfx.erdiagram;

import java.util.ArrayList;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

public class RelationShips {
	
	private TitledPaneNode onFocusTitledPane;
	private TitledPaneNode relatedOtherEndTitledPane;

	private ArrayList<LineNode> relationshipLineNodes;
	private ArrayList<Line> toFromLines;
	private ArrayList<Circle> toFromCircles;
	
	
	private Circle fromRelationshipCircle,toRelationshipCircle;
	private Line fromRelationshipMain,toRelationshipMain;
	private Line fromRelationshipCrowLine1,toRelationshipCrowLine1;
	private Line fromRelationshipCrowLine2,toRelationshipCrowLine2;

	
	private Polygon polygon;
	
	public RelationShips(TitledPaneNode onFocusTitledPane,TitledPaneNode relatedOtherEndTitledPane,Pane pane) {
		
		this.onFocusTitledPane= onFocusTitledPane;
		this.relatedOtherEndTitledPane = relatedOtherEndTitledPane;
		LineNode lineNode1 = new LineNode();LineNode lineNode2 = new LineNode();LineNode lineNode3 = new LineNode();LineNode lineNode4 = new LineNode();LineNode lineNode5 = new LineNode();
		lineNode1.setStrokeWidth(2);lineNode2.setStrokeWidth(2);lineNode3.setStrokeWidth(2);lineNode4.setStrokeWidth(2);lineNode5.setStrokeWidth(2);
		lineNode1.setStrokeType(StrokeType.CENTERED);lineNode2.setStrokeType(StrokeType.CENTERED);lineNode3.setStrokeType(StrokeType.CENTERED);lineNode4.setStrokeType(StrokeType.CENTERED);
		lineNode5.setStrokeType(StrokeType.CENTERED);
		
		
		relationshipLineNodes = new ArrayList<LineNode>();
		relationshipLineNodes.add(lineNode1);relationshipLineNodes.add(lineNode2);
		relationshipLineNodes.add(lineNode3);relationshipLineNodes.add(lineNode4);
		relationshipLineNodes.add(lineNode4);relationshipLineNodes.add(lineNode5);
		
		polygon = new Polygon();
		
		fromRelationshipCircle = new Circle(0);
		fromRelationshipCircle.setStroke(Color.BLACK);
		fromRelationshipCircle.setFill(Color.WHITE);
		fromRelationshipMain = new Line();fromRelationshipMain.setStrokeWidth(2);fromRelationshipMain.setStrokeType(StrokeType.CENTERED);
		fromRelationshipCrowLine1 = new Line();fromRelationshipCrowLine1.setStrokeWidth(2);fromRelationshipCrowLine1.setStrokeType(StrokeType.CENTERED);
		fromRelationshipCrowLine2 = new Line();fromRelationshipCrowLine2.setStrokeWidth(2);fromRelationshipCrowLine2.setStrokeType(StrokeType.CENTERED);
		
		toRelationshipCircle = new Circle(0);
		toRelationshipCircle.setStroke(Color.BLACK);
		toRelationshipCircle.setFill(Color.WHITE);
		toRelationshipMain = new Line();toRelationshipMain.setStrokeWidth(2);toRelationshipMain.setStrokeType(StrokeType.CENTERED);
		toRelationshipCrowLine1 = new Line();toRelationshipCrowLine1.setStrokeWidth(2);toRelationshipCrowLine1.setStrokeType(StrokeType.CENTERED);
		toRelationshipCrowLine2 = new Line();toRelationshipCrowLine2.setStrokeWidth(2);toRelationshipCrowLine2.setStrokeType(StrokeType.CENTERED);
				
		toFromLines = new ArrayList<Line>();toFromCircles = new ArrayList<Circle>();
		toFromLines.add(fromRelationshipMain);toFromLines.add(fromRelationshipCrowLine1);toFromLines.add(fromRelationshipCrowLine2);
		toFromLines.add(toRelationshipMain);toFromLines.add(toRelationshipCrowLine1);toFromLines.add(toRelationshipCrowLine2);
		toFromCircles.add(toRelationshipCircle);toFromCircles.add(fromRelationshipCircle);
		
		
		lineNode1.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode1.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode2.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode2.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode3.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode3.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode4.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode4.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode5.setOnMouseEntered(new LineEnteredEventHandler(relationshipLineNodes,toFromLines,toFromCircles));
		lineNode5.setOnMouseExited(new LineExitedEventHandler(relationshipLineNodes,toFromLines,toFromCircles));

		
	
		pane.getChildren().add(lineNode1);pane.getChildren().add(lineNode2);pane.getChildren().add(lineNode3);pane.getChildren().add(lineNode4);pane.getChildren().add(lineNode5);
		pane.getChildren().add(polygon);
		
		pane.getChildren().add(fromRelationshipMain);pane.getChildren().add(fromRelationshipCrowLine1);pane.getChildren().add(fromRelationshipCrowLine2);
		pane.getChildren().add(toRelationshipMain);pane.getChildren().add(toRelationshipCrowLine1);pane.getChildren().add(toRelationshipCrowLine2);
		pane.getChildren().add(fromRelationshipCircle);pane.getChildren().add(toRelationshipCircle);
	}

	public void getOneToOneRelationShip(Line line1, Line line2) {
		
		
	}
	
	public void getOneToMoneyRelationShip(Line line) {
		
		
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

	public Circle getfromRelationshipCircle() {
		return fromRelationshipCircle;
	}

	public void setfromRelationshipCircle(Circle fromRelationshipCircle) {
		this.fromRelationshipCircle = fromRelationshipCircle;
	}

	public Circle gettoRelationshipCircle() {
		return toRelationshipCircle;
	}

	public void settoRelationshipCircle(Circle toRelationshipCircle) {
		this.toRelationshipCircle = toRelationshipCircle;
	}

	public Line getfromRelationshipMain() {
		return fromRelationshipMain;
	}

	public void setfromRelationshipMain(Line fromRelationshipMain) {
		this.fromRelationshipMain = fromRelationshipMain;
	}

	public Line gettoRelationshipMain() {
		return toRelationshipMain;
	}

	public void settoRelationshipMain(Line toRelationshipMain) {
		this.toRelationshipMain = toRelationshipMain;
	}

	public Line getfromRelationshipCrowLine1() {
		return fromRelationshipCrowLine1;
	}

	public void setfromRelationshipCrowLine1(Line fromRelationshipCrowLine1) {
		this.fromRelationshipCrowLine1 = fromRelationshipCrowLine1;
	}

	public Line gettoRelationshipCrowLine1() {
		return toRelationshipCrowLine1;
	}

	public void settoRelationshipCrowLine1(Line toRelationshipCrowLine1) {
		this.toRelationshipCrowLine1 = toRelationshipCrowLine1;
	}

	public Line getfromRelationshipCrowLine2() {
		return fromRelationshipCrowLine2;
	}

	public void setfromRelationshipCrowLine2(Line fromRelationshipCrowLine2) {
		this.fromRelationshipCrowLine2 = fromRelationshipCrowLine2;
	}

	public Line gettoRelationshipCrowLine2() {
		return toRelationshipCrowLine2;
	}

	public void settoRelationshipCrowLine2(Line toRelationshipCrowLine2) {
		this.toRelationshipCrowLine2 = toRelationshipCrowLine2;
	}
	
}
