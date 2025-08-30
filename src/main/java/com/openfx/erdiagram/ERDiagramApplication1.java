package com.openfx.erdiagram;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ERDiagramApplication1 extends Application{

	private Pane mainPane;
	private Pane minatureMainPane;
	private ArrayList<RelationShips> allRelationships = new ArrayList<RelationShips>();
	private TreeSet<TitledPaneNode> allTitledPaneNodes = new TreeSet<TitledPaneNode>(new TitlePaneComparator());
	
	public static void main(String[] args) {
		   launch(ERDiagramApplication1.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(size.getHeight() + "<--->" +size.getWidth());
		
		mainPane = new Pane();
		mainPane.setPrefSize(size.getWidth(),size.getHeight());
		minatureMainPane = new Pane();
		minatureMainPane.setPrefSize(size.getWidth(),size.getHeight());
		
		
		// Diagram using TitledPane
		String entityAttributes[] = new String[] {"passengerId","first_name","lasr_name","date_of_birth"};
		TitledPaneNode passengerSecurityTitledPane = new TitledPaneNode("passengerSecurity",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		passengerSecurityTitledPane.setPrefWidth(150);
		enableDragAndDrop(passengerSecurityTitledPane, mainPane);
		allTitledPaneNodes.add(passengerSecurityTitledPane);
				
		entityAttributes = new String[] {"id","check_result","comments","created_at","updated_at","passenger_id"};
		TitledPaneNode securityCheckTitledPane = new TitledPaneNode("securityCheck",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		securityCheckTitledPane.setPrefWidth(150);
		enableDragAndDrop(securityCheckTitledPane, mainPane);
		allTitledPaneNodes.add(securityCheckTitledPane);
		
		entityAttributes = new String[] {"id","check_result","created_at","updated_at","booking_id","passenger_id"};
		TitledPaneNode baggageCheckTitledPane = new TitledPaneNode("baggageCheck",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		baggageCheckTitledPane.setPrefWidth(150);
		enableDragAndDrop(baggageCheckTitledPane, mainPane);
		allTitledPaneNodes.add(baggageCheckTitledPane);
	
		entityAttributes = new String[] {"id","active_from","active_to","reason","created_at","updated_at","passenger_id"};
		TitledPaneNode noFlyListTitledPane = new TitledPaneNode("noFlyList",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		noFlyListTitledPane.setPrefWidth(150);
		enableDragAndDrop(noFlyListTitledPane, mainPane);
		allTitledPaneNodes.add(noFlyListTitledPane);
	
		RelationShips relationShips = new RelationShips(passengerSecurityTitledPane,securityCheckTitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(passengerSecurityTitledPane,baggageCheckTitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(passengerSecurityTitledPane,noFlyListTitledPane,mainPane);
		allRelationships.add(relationShips);
		
		mainPane.getChildren().add(passengerSecurityTitledPane); minatureMainPane.getChildren().add(passengerSecurityTitledPane);
		mainPane.getChildren().add(securityCheckTitledPane);minatureMainPane.getChildren().add(securityCheckTitledPane);
		mainPane.getChildren().add(baggageCheckTitledPane); minatureMainPane.getChildren().add(baggageCheckTitledPane);
		mainPane.getChildren().add(noFlyListTitledPane);minatureMainPane.getChildren().add(noFlyListTitledPane);
		
		
		entityAttributes = new String[] {"id","departing_gate","arriving_gate","created_at","updated_at","airline_id","departing_airport_id","arriving_airport_id"};
		TitledPaneNode flightTitledPane = new TitledPaneNode("flight",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		flightTitledPane.setPrefWidth(150);
		enableDragAndDrop(flightTitledPane, mainPane);
		allTitledPaneNodes.add(flightTitledPane);
		
		entityAttributes = new String[] {"id","airport_name","country","state","city","created_at","updated_at"};
		TitledPaneNode airportTitledPane = new TitledPaneNode("airport",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		airportTitledPane.setPrefWidth(150);
		enableDragAndDrop(airportTitledPane, mainPane);
		allTitledPaneNodes.add(airportTitledPane);
		
		entityAttributes = new String[] {"id","airline_code","airline_name","airline_country","created_at","updated_at"};
		TitledPaneNode airlineTitledPane = new TitledPaneNode("airline",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		airlineTitledPane.setPrefWidth(150);
		enableDragAndDrop(airlineTitledPane, mainPane);
		allTitledPaneNodes.add(airlineTitledPane);
		
		relationShips = new RelationShips(flightTitledPane,airportTitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(flightTitledPane,airlineTitledPane,mainPane);
		allRelationships.add(relationShips);
		
		mainPane.getChildren().add(flightTitledPane); minatureMainPane.getChildren().add(flightTitledPane);
		mainPane.getChildren().add(airportTitledPane); minatureMainPane.getChildren().add(airportTitledPane);
		mainPane.getChildren().add(airlineTitledPane); minatureMainPane.getChildren().add(airlineTitledPane);
		
		/*
		// Diagram using TitledPane
		String entityAttributes[] = new String[] {"actorId","actorName","actorCity","actorId","actorName","actorCity","actorCountry"};
		TitledPaneNode actortitledPane = new TitledPaneNode("actor",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,100,entityAttributes);
		actortitledPane.setPrefWidth(150);
	    enableDragAndDrop(actortitledPane, mainPane);
	    allTitledPaneNodes.add(actortitledPane);
		
		
	    entityAttributes = new String[] {"address_id","address","address2","district","city_id","postal_code","phone","location","last_update"};
		TitledPaneNode addresstitledPane = new TitledPaneNode("address",new Tooltip("This is a table in RDBD \n This is tool tip for table"),300,300,entityAttributes);
		addresstitledPane.setPrefWidth(150);
		enableDragAndDrop(addresstitledPane, mainPane);
		allTitledPaneNodes.add(addresstitledPane);
	
	
		entityAttributes = new String[] {"company_id","company_nam","company_loc","company_pin","city","last_updated"};
		TitledPaneNode companytitledPane = new TitledPaneNode("company",new Tooltip("This is a table in RDBD \n This is tool tip for table"),100,500,entityAttributes);
		companytitledPane.setPrefWidth(150);
		enableDragAndDrop(companytitledPane, mainPane);
		allTitledPaneNodes.add(companytitledPane);
		
		
		entityAttributes = new String[] {"employee_id","employee_nam","employee_loc","company_pin","city","last_updated"};
		TitledPaneNode employeetitledPane = new TitledPaneNode("employee",new Tooltip("This is a table in RDBD \n This is tool tip for table"),550,100,entityAttributes);		
		enableDragAndDrop(employeetitledPane, mainPane);
		allTitledPaneNodes.add(employeetitledPane);
		
		entityAttributes = new String[] {"department_id","department_nam","department_loc","department_pin","city","last_updated"};
		TitledPaneNode departmenttitledPane = new TitledPaneNode("department",new Tooltip("This is a table in RDBD \n This is tool tip for table"),550,500,entityAttributes);
		departmenttitledPane.setPrefWidth(150);
		enableDragAndDrop(departmenttitledPane, mainPane);
		allTitledPaneNodes.add(departmenttitledPane);
		
		entityAttributes = new String[] {"school_id","school_nam","school_loc","school_pin","city","last_updated"};
		TitledPaneNode schooltitledPane = new TitledPaneNode("school",new Tooltip("This is a table in RDBD \n This is tool tip for table"),750,500,entityAttributes);
		schooltitledPane.setPrefWidth(150);
		enableDragAndDrop(schooltitledPane, mainPane);
		allTitledPaneNodes.add(schooltitledPane);
		 
		// Establish the relationships between the titlesPanes/Tables
		RelationShips relationShips = new RelationShips(actortitledPane,addresstitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(companytitledPane,addresstitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(employeetitledPane,addresstitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(departmenttitledPane,addresstitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(employeetitledPane,schooltitledPane,mainPane);
		allRelationships.add(relationShips);
		relationShips = new RelationShips(companytitledPane,schooltitledPane,mainPane);
		allRelationships.add(relationShips);
		
		mainPane.getChildren().add(actortitledPane); minatureMainPane.getChildren().add(actortitledPane);
		mainPane.getChildren().add(addresstitledPane);minatureMainPane.getChildren().add(addresstitledPane);
		mainPane.getChildren().add(companytitledPane);minatureMainPane.getChildren().add(companytitledPane);
		mainPane.getChildren().add(employeetitledPane);minatureMainPane.getChildren().add(employeetitledPane);
		mainPane.getChildren().add(departmenttitledPane);minatureMainPane.getChildren().add(departmenttitledPane);
		mainPane.getChildren().add(schooltitledPane);minatureMainPane.getChildren().add(schooltitledPane);
		*/
		
		// This will set the backgroud color the Zoom Pane
		mainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
		mainPane.setMinWidth(2000);
		mainPane.setMinHeight(2000);
		minatureMainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
		minatureMainPane.setMinWidth(2000);
		minatureMainPane.setMinHeight(2000);
		
		ZoomableScrollPane zoomableScrollPane1 = new ZoomableScrollPane(mainPane,false,null);
		
		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(minatureMainPane,true,zoomableScrollPane1);
		zoomableScrollPane.vbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
		zoomableScrollPane.hbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
		
		
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(zoomableScrollPane1);
		TitledPane overViewTitledPane = new TitledPane();
		overViewTitledPane.setMaxSize(minatureMainPane.getMinWidth()/10, minatureMainPane.getMinHeight()/10);
		overViewTitledPane.setText("OVERVIEW");
		overViewTitledPane.setContent(zoomableScrollPane);
		stackPane.getChildren().add(overViewTitledPane);
		stackPane.setAlignment(overViewTitledPane, Pos.TOP_LEFT);
		
		
	    Scene scene = new Scene(stackPane, 800, 600/* Color.rgb(35, 39, 50) */);  
	    scene.getStylesheets().add(ERDiagramApplication.class.getResource("/testLayout.css").toExternalForm());
		primaryStage.setTitle("ER Diagram ");   
		primaryStage.setScene(scene);
					    
		primaryStage.show();
		
		mainPane.setOnMouseClicked(event -> {
			//	zoomableScrollPane1.setHvalue(zoomableScrollPane1.getHvalue()+0.1);
				System.out.println("Mouse Clicked on ZoomableScrollPane current HValue : "+zoomableScrollPane1.getHvalue()+" Vvalue : "+zoomableScrollPane1.getVvalue() );
				double capturedHValue = zoomableScrollPane1.getHvalue();
				double captiredVValue = zoomableScrollPane1.getVvalue();
				if(captiredVValue == 1.0 || captiredVValue > 0.95) {
					captiredVValue = 0.85;
				}
				if(capturedHValue == 1.0 || capturedHValue > 0.95) {
					capturedHValue = 0.95;
				}
				
				zoomableScrollPane.getFocusRectangle().setLayoutX(200 * capturedHValue);
				zoomableScrollPane.getFocusRectangle().setLayoutY(200 * captiredVValue);
			});
		
		for(RelationShips relationShip : allRelationships) {
			checkRelativePositionsBeforeDraw(relationShip.getOnFocusTitledPane(),relationShip.getRelatedOtherEndTitledPane(), relationShip);
		}
		
		for(TitledPaneNode tabletitledPane : allTitledPaneNodes) {
		
			System.out.println(tabletitledPane.getText() +" <---Top-Side-Start--> "+tabletitledPane.getText());
	    	for (TitledPaneNode titledPaneNode : tabletitledPane.getTopRelatedTitledPanes()) {
	    		System.out.println(titledPaneNode.getText());
	    	}
	    	System.out.println(tabletitledPane.getText() +" <---Top-Side-End--> "+tabletitledPane.getText());
	    	System.out.println(tabletitledPane.getText() +" <---Right-Side-Start--> "+tabletitledPane.getText());
	    	for (TitledPaneNode titledPaneNode : tabletitledPane.getRigtRelatedTitledPanes()) {
	    		System.out.println(titledPaneNode.getText());
	    	}
	    	System.out.println(tabletitledPane.getText() +" <---Right-Side-End--> "+tabletitledPane.getText());
	    	System.out.println(tabletitledPane.getText() +" <---Bottom-Side-Start--> "+tabletitledPane.getText());
	    	for (TitledPaneNode titledPaneNode : tabletitledPane.getBottomRelatedTitledPanes()) {
	    		System.out.println(titledPaneNode.getText());
	    	}
	    	System.out.println(tabletitledPane.getText() +" <---Bottom-Side-End--> "+tabletitledPane.getText());
	    	System.out.println(tabletitledPane.getText() +" <---Left-Side-Start--> "+tabletitledPane.getText());
	    	for (TitledPaneNode titledPaneNode : tabletitledPane.getLeftRelatedTitledPanes()) {
	    		System.out.println(titledPaneNode.getText());
	    	}
	    	System.out.println(tabletitledPane.getText() +" <---Left-Side-End--> "+tabletitledPane.getText());
    	
		}
		
	
		for(RelationShips relationShip : allRelationships) {
			checkRelativePositionsToDraw(relationShip.getOnFocusTitledPane(),relationShip.getRelatedOtherEndTitledPane(), relationShip);
		}
		
	}
	
	private void checkRelativePositionsBeforeDraw(TitledPaneNode titledPaneNodeA,TitledPaneNode titledPaneNodeB,RelationShips relationShip) {
		
			System.out.println("titledPaneNodeA "+titledPaneNodeA.getText());
			System.out.println("titledPaneNodeB "+titledPaneNodeB.getText());
			boolean isCovered = false;
			boolean isTop = false;
			boolean isBottom = false;
			boolean isLeft = false;
			boolean isRight = false;
			boolean isYClose=false;
			boolean isXClose=false;
			
			Bounds boundsA = titledPaneNodeA.getBoundsInParent();
			Bounds boundsB = titledPaneNodeB.getBoundsInParent();
			
			// check if its below
			// Ya+(ha*0.75) - Yb < 0
			if(( ( boundsA.getMinY() + (boundsA.getHeight()*0.75) ) - boundsB.getMinY()) < 0  ) {
				isTop = true;
				System.out.println("This is "+  titledPaneNodeA.getText() +" A(moving) above and "+titledPaneNodeB.getText() +"B(static) below relationship");
			}
			// check if its above
			// Ya+(Yah*0.25) - Yb+Ybh > 0
			double a = boundsA.getMinY() + (boundsA.getMinY()*0.25);
			double b = boundsB.getMinY()+boundsB.getHeight();
			double c = a-b;
			if(  c > 0 ) {
				isBottom = true;
				System.out.println("This is "+titledPaneNodeA.getText() +" A(moving) below and "+ titledPaneNodeB.getText() +" B(static) above relationship");
			}
			
			// if its left
			  //  Xa - Xb < 0 
			if( (boundsA.getMinX() - boundsB.getMinX()) < 0) {
				isLeft = true;
				System.out.println("This is "+titledPaneNodeA.getText()+" A(moving) at left and "+ titledPaneNodeB.getText()+" B(static) at right relationship");
			
			}
			
			// if its right
			  //  Xa - Xb > 0 
			if( (boundsA.getMinX() - boundsB.getMinX()) > 0) {
				isRight = true;
				System.out.println("This is "+titledPaneNodeA.getText() +" A(moving) at right and "+titledPaneNodeB.getText() +" B(static) at left relationship");
			}
		
			// A is at top, check if its y close
			if(boundsA.getMinY() - boundsB.getMinY() < 0) {
				System.out.println("A is top check if its y close");
				if( ((boundsA.getMaxY()-(boundsA.getHeight() * 0.25)) - boundsB.getMinY()) > 0) {
					System.out.println("This is y close with "+titledPaneNodeA.getText()  +"A 3/4th (moved) past  static(B) "+titledPaneNodeB.getText());
					isYClose=true;
				}
			}
			
			// A is at bottom, check if y close
			if(boundsA.getMinY()-boundsB.getMinY() > 0) {
				if( (boundsA.getMinY() - (boundsB.getMaxY() - (boundsB.getHeight()*0.25))<0) ) {
					System.out.println("This is y close with "+titledPaneNodeA.getText()  +"A (moved) past  static(B)3/4th "+titledPaneNodeB.getText());
					isYClose = true;
				}
			}
			
			//check if its X close with A moving
			if( (boundsA.getMinX() > boundsB.getMinX()) && (boundsA.getMinX() < (boundsB.getMinX()+boundsB.getWidth())) ) {
				isXClose = true;
				System.out.println("This is x close with "+titledPaneNodeA.getText() +"A(moving)");
				
			}
			
			//check if its X close with B moving
			if( (boundsB.getMinX() > boundsA.getMinX()) && (boundsB.getMinX() < (boundsA.getMinX() + boundsA.getWidth()))  ) {
				isXClose = true;
				System.out.println("This is x close with "+titledPaneNodeB.getText() +"B(moving)");
			}
			
			if(isTop && isRight && !isYClose) {
				System.out.println("Its Top right");	
				
				System.out.println("Line will go from left of "+titledPaneNodeA.getText()+ " to top of "+titledPaneNodeB.getText());
				// Add Left side 1 for TitlePaneA
				titledPaneNodeA.getLeftRelatedTitledPanes().add(titledPaneNodeB);
				// Remove from all other directions if present
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,true,true,true,false); //trbl
				// Add Bottom side 1 for TitlePaneB
				titledPaneNodeB.getTopRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,false,true,true,true); //trbl
				isCovered= true;
			
			}
			if(isTop && isLeft && !isYClose) {
				System.out.println("Its Top left");
			
				System.out.println("Line will go from right of "+titledPaneNodeA.getText()+ " to top of "+titledPaneNodeB.getText());
				// Add Right side 1 for TitlePaneA
				titledPaneNodeA.getRigtRelatedTitledPanes().add(titledPaneNodeB);
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,true,false,true,true); //trbl
				// Add Top side 1 for TitlePaneB
				titledPaneNodeB.getTopRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,false,true,true,true); //trbl
				isCovered = true;
			}
			if(isBottom && isRight && !isYClose) {
				System.out.println("Its Bottom right");
			
				System.out.println("Line will go from left of "+titledPaneNodeA.getText()+ " to bottom of "+titledPaneNodeB.getText());
				// Add Left side 1 for TitlePaneA
				titledPaneNodeA.getLeftRelatedTitledPanes().add(titledPaneNodeB);
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,true,true,true,false); //trbl
				// Add Bottom side 1 for TitlePaneB
				titledPaneNodeB.getBottomRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,true,true,false,true); //trbl
				isCovered = true;
			}
			if(isBottom && isLeft && !isYClose) {
				System.out.println("Its Bottom left");
				
				System.out.println("Line will go from right of "+titledPaneNodeA.getText()+ " to bottom of "+titledPaneNodeB.getText());
				// Add Right side 1 for TitlePaneA
				titledPaneNodeA.getRigtRelatedTitledPanes().add(titledPaneNodeB);
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,true,false,true,true); //trbl
				// Add Bottom side 1 for TitlePaneB
				titledPaneNodeB.getBottomRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,true,true,false,true); //trbl
				isCovered = true;
			}
			
			if(isLeft && isYClose) {
				
				System.out.println("Is left and y close ");
				System.out.println("Line will go from right of "+titledPaneNodeA.getText()+ " to left of "+titledPaneNodeB.getText());
				// Add Right side 1 for TitlePaneA
				titledPaneNodeA.getRigtRelatedTitledPanes().add(titledPaneNodeB);
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,true,false,true,true); //trbl
				// Add Left side 1 for TitlePaneB
				titledPaneNodeB.getLeftRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,true,true,true,false); //trbl
				isCovered = true;
				
			}
			
			if(isRight && isYClose) {
				
				System.out.println("Is right and y close");
				System.out.println("Line will go from left of "+titledPaneNodeA.getText()+ " to right of "+titledPaneNodeB.getText());
				// Add Left side 1 for TitlePaneA
				titledPaneNodeA.getLeftRelatedTitledPanes().add(titledPaneNodeB);
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,true,true,true,false); //trbl
				// Add Right side 1 for TitlePaneB
				titledPaneNodeB.getRigtRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,true,false,true,true); //trbl
				isCovered = true;	
			}
			
			if(isTop && isXClose) {
				System.out.println("Is Top and x close");
				System.out.println("Line will go from top of "+titledPaneNodeA.getText()+ " to bottom of "+titledPaneNodeB.getText());
				// Add bottom side 1 for TitlePaneA
				titledPaneNodeA.getBottomRelatedTitledPanes().add(titledPaneNodeB);
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,true,true,false,true); //trbl
				// Add Top side 1 for TitlePaneB
				titledPaneNodeB.getTopRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,false,true,true,true); //trbl
				isCovered = true;
			}
			
			if(isBottom && isXClose) {
				System.out.println("Is Bottom and x close");
				System.out.println("Line will go from bottom of "+titledPaneNodeA.getText()+ " to top of "+titledPaneNodeB.getText());
				// Add Top side 1 for TitlePaneA
				titledPaneNodeA.getTopRelatedTitledPanes().add(titledPaneNodeB);
				removeFromallOtherDirectionsifPresent(titledPaneNodeA,titledPaneNodeB,false,true,true,true); //trbl
				// Add Bottom side 1 for TitlePaneB
				titledPaneNodeB.getBottomRelatedTitledPanes().add(titledPaneNodeA);
				removeFromallOtherDirectionsifPresent(titledPaneNodeB,titledPaneNodeA,true,true,false,true); //trbl
				isCovered = true;
			}
			
			if(isRight && !isCovered) {
				System.out.println("Right and Non 3/4th ot 1/4th y close");
			}
			if(isLeft && !isCovered) {
				System.out.println("Left and Non 3/4th ot 1/4th y close");
			}
			
			
	}

	private void removeFromallOtherDirectionsifPresent(TitledPaneNode titledPaneNodeA,TitledPaneNode titledPaneNodeB, boolean isTopRemove, boolean isRightRemove, boolean isBottomRemove,
			boolean isLeftRemove) {
		
		if(isTopRemove) {
			titledPaneNodeA.getTopRelatedTitledPanes().remove(titledPaneNodeB);
		}
		if(isRightRemove) {
			titledPaneNodeA.getRigtRelatedTitledPanes().remove(titledPaneNodeB);
		}
		if(isBottomRemove) {
			titledPaneNodeA.getBottomRelatedTitledPanes().remove(titledPaneNodeB);
		}
		if(isLeftRemove) {
			titledPaneNodeA.getLeftRelatedTitledPanes().remove(titledPaneNodeB);
		}
		
	}


	private void checkRelativePositionsToDraw(TitledPaneNode titledPaneNodeA,TitledPaneNode titledPaneNodeB,RelationShips relationShip) {
		
		System.out.println("titledPaneNodeA "+titledPaneNodeA.getText());
		System.out.println("titledPaneNodeB "+titledPaneNodeB.getText());
		boolean isCovered= false;
		boolean isTop = false;
		boolean isBottom = false;
		boolean isLeft = false;
		boolean isRight = false;
		boolean isYClose=false;
		boolean isXClose=false;
		
		Bounds boundsA = titledPaneNodeA.getBoundsInParent();
		Bounds boundsB = titledPaneNodeB.getBoundsInParent();
		
		
		// check if its below
		// Ya+(ha*0.75) - Yb < 0
		if(( ( boundsA.getMinY() + (boundsA.getHeight()*0.75) ) - boundsB.getMinY()) < 0  ) {
			isTop = true;
			System.out.println("This is "+  titledPaneNodeA.getText() +" A(moving) above and "+titledPaneNodeB.getText() +"B(static) below relationship");
		}
		// check if its above
		// Ya+(Yah*0.25) - Yb+Ybh > 0
		double a = boundsA.getMinY() + (boundsA.getMinY()*0.25);
		double b = boundsB.getMinY()+boundsB.getHeight();
		double c = a-b;
		if(  c > 0 ) {
			isBottom = true;
			System.out.println("This is "+titledPaneNodeA.getText() +" A(moving) below and "+ titledPaneNodeB.getText() +" B(static) above relationship");
		}
		
		// if its left
		  //  Xa - Xb < 0 
		if( (boundsA.getMinX() - boundsB.getMinX()) < 0) {
			isLeft = true;
			System.out.println("This is "+titledPaneNodeA.getText()+" A(moving) at left and "+ titledPaneNodeB.getText()+" B(static) at right relationship");
		
		}
		
		// if its right
		  //  Xa - Xb > 0 
		if( (boundsA.getMinX() - boundsB.getMinX()) > 0) {
			isRight = true;
			System.out.println("This is "+titledPaneNodeA.getText() +" A(moving) at right and "+titledPaneNodeB.getText() +" B(static) at left relationship");
		}
	
		// A is at top, check if its y close
		if(boundsA.getMinY() - boundsB.getMinY() < 0) {
			System.out.println("A is top check if its y close");
			if( ((boundsA.getMaxY()-(boundsA.getHeight() * 0.25)) - boundsB.getMinY()) > 0) {
				System.out.println("This is y close with "+titledPaneNodeA.getText()  +"A 3/4th (moved) past  static(B) "+titledPaneNodeB.getText());
				isYClose=true;
			}
		}
		
		// A is at bottom, check if y close
		if(boundsA.getMinY()-boundsB.getMinY() > 0) {
			if( (boundsA.getMinY() - (boundsB.getMaxY() - (boundsB.getHeight()*0.25))<0) ) {
				System.out.println("This is y close with "+titledPaneNodeA.getText()  +"A (moved) past  static(B)3/4th "+titledPaneNodeB.getText());
				isYClose = true;
			}
		}
		
		//check if its X close with A moving
		if( (boundsA.getMinX() > boundsB.getMinX()) && (boundsA.getMinX() < (boundsB.getMinX()+boundsB.getWidth())) ) {
			isXClose = true;
			System.out.println("This is x close with "+titledPaneNodeA.getText() +"A(moving)");
		}
		
		//check if its X close with B moving
		if( (boundsB.getMinX() > boundsA.getMinX()) && (boundsB.getMinX() < (boundsA.getMinX() + boundsA.getWidth()))  ) {
			isXClose = true;
			System.out.println("This is x close with "+titledPaneNodeB.getText()  +" B(moving)");
		}
		
		if(isTop && isRight && !isYClose) {
			System.out.println("Its Top right");	

			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple left connections ? current size is " + titledPaneNodeA.getLeftRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple Top connections ? current size is " + titledPaneNodeB.getTopRelatedTitledPanes().size());
			boolean isLessthanScreenWidth = true;
			if( (boundsA.getMinX() - boundsB.getMaxX())  > 900) {
				isLessthanScreenWidth = false;
			}

			if(isLessthanScreenWidth) {
				double P1x = boundsA.getMinX();
				double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
				double Q1x = boundsB.getMinX()+boundsB.getWidth()/2;
				double Q1y = boundsA.getMinY()+boundsA.getHeight()/2;
				double R1x = boundsB.getMinX() + boundsB.getWidth()/2;
				double R1y = boundsB.getMinY();
				
				int position = titledPaneNodeA.getLeftRelatedTitledPanes().headSet(titledPaneNodeB).size();
				P1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
				Q1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
			
				position = titledPaneNodeB.getTopRelatedTitledPanes().headSet(titledPaneNodeA).size();
			//	System.out.println( " Position of "+titledPaneNodeA.getText()+ " "+position);
				Q1x = boundsB.getMinX()+boundsB.getWidth()/2+30*position;
				R1x = boundsB.getMinX()+boundsB.getWidth()/2+30*position;
				
				
				Line line1 = relationShip.getRelationshipLineNodes().get(0);
				Line line2 = relationShip.getRelationshipLineNodes().get(1);
				Line line3 = relationShip.getRelationshipLineNodes().get(2);
				line3.setStartX(0);line3.setStartY(0);line3.setEndX(0);line3.setEndY(0);
				
				Circle circle = relationShip.gettoRelationshipCircle();
				circle.setRadius(0);
								
				relationShip.getPolygon().getPoints().clear();
				
				line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
				line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
				
				if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

			/*		relationShip.getPolygon().getPoints().addAll(new Double[]{
							line1.getStartX(), line1.getStartY(),
							line1.getStartX()-10, line1.getStartY()-10,
							line1.getStartX()-10, line1.getStartY()+10});
				*/	
					
					// one to Many relationship
					double x1 = line1.getStartX()-20;
					double y1 = line1.getStartY();					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
					toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()-10);
					
					toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
					toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()+10);
					
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line2.getEndX()-10;
					double x2 = line2.getEndX()+10;
					y1 = line2.getEndY()-10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);
			
				}else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

			/*		relationShip.getPolygon().getPoints().addAll(new Double[]{
							line2.getEndX(), line2.getEndY(),
							line2.getEndX()-10, line2.getEndY()-10,
							line2.getEndX()+10, line2.getEndY()-10});
			*/
					// one to Many relationship
					double x1 = line2.getEndX();
					double y1 = line2.getEndY()-20;
					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(line2.getEndX());toRelationshipCrowLine1.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine1.setEndX(line2.getEndX()-10);toRelationshipCrowLine1.setEndY(line2.getEndY());
				
					toRelationshipCrowLine2.setStartX(line2.getEndX());toRelationshipCrowLine2.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine2.setEndX(line2.getEndX()+10);toRelationshipCrowLine2.setEndY(line2.getEndY());
		
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line1.getStartX()-10;
					y1 = line1.getEndY()-10;
					double y2 = line1.getEndY()+10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);
		
				}
				
			}
			else {
				double P1x = boundsA.getMinX();
				double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
				double Q1x = boundsA.getMinX() - ((boundsA.getMinX() - boundsB.getMaxX())/2);
				double Q1y = P1y;
				double R1x = Q1x;
				double R1y = boundsB.getMinY() - ((boundsB.getMinY() - P1y)/2);
				double S1x = boundsB.getMinX() + (boundsB.getWidth()/2);
				double S1y = R1y;
				double T1x = S1x;
				double T1y = boundsB.getMinY();
				
				int position = titledPaneNodeA.getLeftRelatedTitledPanes().headSet(titledPaneNodeB).size();
				P1y = P1y+30*position;
				Q1y = Q1y+30*position;
			
				position = titledPaneNodeB.getTopRelatedTitledPanes().headSet(titledPaneNodeA).size();
			//	System.out.println( " Position of "+titledPaneNodeA.getText()+ " "+position);
				S1x = S1x+30*position;
				T1x = T1x+30*position; 
				
				
				Line line1 = relationShip.getRelationshipLineNodes().get(0);
				Line line2 = relationShip.getRelationshipLineNodes().get(1);
				Line line3 = relationShip.getRelationshipLineNodes().get(2);
				Line line4 = relationShip.getRelationshipLineNodes().get(3);
				
				Circle circle = relationShip.gettoRelationshipCircle();
				circle.setRadius(0);
			
				relationShip.getPolygon().getPoints().clear();
				
				line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
				line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
				line3.setStartX(R1x);line3.setStartY(R1y);line3.setEndX(S1x);line3.setEndY(S1y);
				line4.setStartX(S1x);line4.setStartY(S1y);line4.setEndX(T1x);line4.setEndY(T1y);
				
				if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

				/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
							line1.getStartX(), line1.getStartY(),
							line1.getStartX()-10, line1.getStartY()-10,
							line1.getStartX()-10, line1.getStartY()+10});
				*/
					// one to Many relationship
					double x1 = line1.getStartX()-20;
					double y1 = line1.getStartY();					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
					toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()-10);
					
					toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
					toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()+10);
					
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line2.getEndX()-10;
					double x2 = line2.getEndX()+10;
					y1 = line2.getEndY()-10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);

				
					
				}else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

				/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
							line4.getEndX(), line4.getEndY(),
							line4.getEndX()-10, line4.getEndY()-10,
							line4.getEndX()+10, line4.getEndY()-10});
				*/
					// one to Many relationship
					double x1 = line2.getEndX();
					double y1 = line2.getEndY()-20;
					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(line2.getEndX());toRelationshipCrowLine1.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine1.setEndX(line2.getEndX()-10);toRelationshipCrowLine1.setEndY(line2.getEndY());
				
					toRelationshipCrowLine2.setStartX(line2.getEndX());toRelationshipCrowLine2.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine2.setEndX(line2.getEndX()+10);toRelationshipCrowLine2.setEndY(line2.getEndY());
		
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line1.getStartX()-10;
					y1 = line1.getEndY()-10;
					double y2 = line1.getEndY()+10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);

				}	
			}
			
			isCovered=true;
						
		}
		if(isTop && isLeft && !isYClose) {
			System.out.println("Its Top left");
		
			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple right connections ? current size is " + titledPaneNodeA.getRigtRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple Top connections ? current size is " + titledPaneNodeB.getTopRelatedTitledPanes().size());
			
			boolean isLessthanScreenWidth = true;
			if( (boundsB.getMinX() - boundsA.getMaxX())  > 900) {
				isLessthanScreenWidth = false;
			}
			if(isLessthanScreenWidth) {
				double P1x = boundsA.getMaxX();
				double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
				double Q1x = boundsB.getMinX()+boundsB.getWidth()/2;
				double Q1y = boundsA.getMinY()+boundsA.getHeight()/2;
				double R1x = boundsB.getMinX()+boundsB.getWidth()/2;
				double R1y = boundsB.getMinY();
				
				int position = titledPaneNodeA.getRigtRelatedTitledPanes().headSet(titledPaneNodeB).size();
				P1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
				Q1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
				
				position = titledPaneNodeB.getTopRelatedTitledPanes().headSet(titledPaneNodeA).size();
				System.out.println( " Position of "+titledPaneNodeA.getText()+ " "+position);
				Q1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
				R1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
				
				
				Line line1 = relationShip.getRelationshipLineNodes().get(0);
				Line line2 = relationShip.getRelationshipLineNodes().get(1);
				Line line3 = relationShip.getRelationshipLineNodes().get(2);				
				line3.setStartX(0);line3.setStartY(0);line3.setEndX(0);line3.setEndY(0);
				
				Circle circle = relationShip.gettoRelationshipCircle();
				circle.setRadius(0);
			
				relationShip.getPolygon().getPoints().clear();
				
				line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
				line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
				isCovered=true;
	
				if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");
	
				/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
							line1.getStartX(), line1.getStartY(),
							line1.getStartX()+10, line1.getStartY()-10,
							line1.getStartX()+10, line1.getStartY()+10});
			   */
					
					// one to Many relationship
					double x1 = line1.getStartX()+20;
					double y1 = line1.getStartY();					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
					toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()-10);
					
					toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
					toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()+10);
					
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line2.getEndX()-10;
					double x2 = line2.getEndX()+10;
					y1 = line2.getEndY()-10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);

				}else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");
	
				/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
							line2.getEndX(), line2.getEndY(),
							line2.getEndX()-10, line2.getEndY()-10,
							line2.getEndX()+10, line2.getEndY()-10});
				*/
					// one to Many relationship
					double x1 = line2.getEndX();
					double y1 = line2.getEndY()-20;
					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(line2.getEndX());toRelationshipCrowLine1.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine1.setEndX(line2.getEndX()-10);toRelationshipCrowLine1.setEndY(line2.getEndY());
				
					toRelationshipCrowLine2.setStartX(line2.getEndX());toRelationshipCrowLine2.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine2.setEndX(line2.getEndX()+10);toRelationshipCrowLine2.setEndY(line2.getEndY());
		
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line1.getStartX()+10;
					y1 = line1.getEndY()-10;
					double y2 = line1.getEndY()+10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);
				
				}
			}
			else {
				double P1x = boundsA.getMaxX();
				double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
				double Q1x = boundsA.getMaxX() + ((boundsB.getMinX()-boundsA.getMaxX())/2);
				double Q1y = P1y;
				double R1x = Q1x;
				double R1y = boundsB.getMinY() - ((boundsB.getMinY()- (boundsA.getMinY() + boundsA.getHeight()/2) )/2);
				double S1x = boundsB.getMinX() + boundsB.getWidth()/2;
				double S1y = R1y;
				double T1x = S1x;
				double T1y = boundsB.getMinY();
				
				int position = titledPaneNodeA.getRigtRelatedTitledPanes().headSet(titledPaneNodeB).size();
				P1y = P1y+30*position;
				Q1y = Q1y+30*position;
				
				position = titledPaneNodeB.getTopRelatedTitledPanes().headSet(titledPaneNodeA).size();
				System.out.println( " Position of "+titledPaneNodeA.getText()+ " "+position);
				S1x = S1x-30*position;
				T1x = T1x-30*position;
				
				
				Line line1 = relationShip.getRelationshipLineNodes().get(0);
				Line line2 = relationShip.getRelationshipLineNodes().get(1);
				Line line3 = relationShip.getRelationshipLineNodes().get(2);
				Line line4 = relationShip.getRelationshipLineNodes().get(3);
				
				relationShip.getPolygon().getPoints().clear();
				
				Circle circle = relationShip.gettoRelationshipCircle();
				circle.setRadius(0);
				
				line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
				line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
				line3.setStartX(R1x);line3.setStartY(R1y);line3.setEndX(S1x);line3.setEndY(S1y);
				line4.setStartX(S1x);line4.setStartY(S1y);line4.setEndX(T1x);line4.setEndY(T1y);
				isCovered=true;
	
				if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");
	
				/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
							line1.getStartX(), line1.getStartY(),
							line1.getStartX()+10, line1.getStartY()-10,
							line1.getStartX()+10, line1.getStartY()+10});
				*/	
					// one to Many relationship
					double x1 = line1.getStartX()+20;
					double y1 = line1.getStartY();					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
					toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()-10);
					
					toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
					toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()+10);
					
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line2.getEndX()-10;
					double x2 = line2.getEndX()+10;
					y1 = line2.getEndY()-10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);

			
				}else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
					System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");
	
				/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
							line4.getEndX(), line4.getEndY(),
							line4.getEndX()-10, line4.getEndY()-10,
							line4.getEndX()+10, line4.getEndY()-10});
				*/
					// one to Many relationship
					double x1 = line2.getEndX();
					double y1 = line2.getEndY()-20;
					
					circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
					Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
					Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
					
					toRelationshipCrowLine1.setStartX(line2.getEndX());toRelationshipCrowLine1.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine1.setEndX(line2.getEndX()-10);toRelationshipCrowLine1.setEndY(line2.getEndY());
				
					toRelationshipCrowLine2.setStartX(line2.getEndX());toRelationshipCrowLine2.setStartY(line2.getEndY()-10);
					toRelationshipCrowLine2.setEndX(line2.getEndX()+10);toRelationshipCrowLine2.setEndY(line2.getEndY());
		
					Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
					x1 = line1.getStartX()+10;
					y1 = line1.getEndY()-10;
					double y2 = line1.getEndY()+10;
					
					fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
					fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);
				}
			}			
		}
		if(isBottom && isRight && !isYClose) {
			System.out.println("Its Bottom right");
		
			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple left connections ? current size is " + titledPaneNodeA.getLeftRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple Bottom connections ? current size is " + titledPaneNodeB.getBottomRelatedTitledPanes().size());
			
			
			double P1x = boundsA.getMinX();
			double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double Q1x = boundsB.getMinX()+boundsB.getWidth()/2;
			double Q1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double R1x = boundsB.getMinX()+boundsB.getWidth()/2;
			double R1y = boundsB.getMaxY() ;
			
			int postion = titledPaneNodeA.getLeftRelatedTitledPanes().headSet(titledPaneNodeB).size();
			System.out.println( " Position of "+titledPaneNodeB.getText()+ " "+titledPaneNodeA.getLeftRelatedTitledPanes().headSet(titledPaneNodeB).size());
			P1x = boundsA.getMinX();
			P1y = boundsA.getMinY()+boundsA.getHeight()/2-30*postion;
			Q1y = boundsA.getMinY()+boundsA.getHeight()/2-30*postion;
			
			postion = titledPaneNodeB.getBottomRelatedTitledPanes().headSet(titledPaneNodeA).size();
			Q1x = boundsB.getMinX()+boundsB.getWidth()/2+30*postion;
			R1x = boundsB.getMinX()+boundsB.getWidth()/2+30*postion;
			
			Line line1 = relationShip.getRelationshipLineNodes().get(0);
			Line line2 = relationShip.getRelationshipLineNodes().get(1);
			Line line3 = relationShip.getRelationshipLineNodes().get(2);
			Line line4 = relationShip.getRelationshipLineNodes().get(3);
			
			line3.setStartX(0);line3.setStartY(0);line3.setEndX(0);line3.setEndY(0);
			line4.setStartX(0);line4.setStartY(0);line4.setEndX(0);line4.setEndY(0);
			
			Circle circle = relationShip.gettoRelationshipCircle();
			circle.setRadius(0);
			
			relationShip.getPolygon().getPoints().clear();
			
			line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
			line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
			isCovered=true;
		
			if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line1.getStartX(), line1.getStartY(),
						line1.getStartX()-10, line1.getStartY()-10,
						line1.getStartX()-10, line1.getStartY()+10});
			*/
			
				// one to Many relationship
				double x1 = line1.getStartX()-20;
				double y1 = line1.getStartY();					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()-10);
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()+10);
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line2.getEndX()-10;
				y1 = line2.getEndY()+10;
				double x2 = line2.getEndX()+10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);
						
			}else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line2.getEndX(), line2.getEndY(),
						line2.getEndX()-10, line2.getEndY()+10,
						line2.getEndX()+10, line2.getEndY()+10});
			*/
				
				// one to Many relationship
				double x1 = line2.getEndX();
				double y1 = line2.getEndY()+20;					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line2.getEndX()-10);toRelationshipCrowLine1.setEndY(line2.getEndY());
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line2.getEndX()+10);toRelationshipCrowLine2.setEndY(line2.getEndY());
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line1.getStartX()-10;
				y1 = line1.getStartY()+10;
				double y2 = line1.getStartY()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);
				
			}
		}
		if(isBottom && isLeft && !isYClose) {
			System.out.println("Its Bottom left");
			double Px;double Py;double Qx;double Qy;double Rx;double Ry;
			
			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple right connections ? current size is " + titledPaneNodeA.getRigtRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple Bottom connections ? current size is " + titledPaneNodeB.getBottomRelatedTitledPanes().size());
			
			
			double P1x = boundsA.getMaxX();
			double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double Q1x = boundsB.getMinX()+boundsB.getWidth()/2;
			double Q1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double R1x = boundsB.getMinX()+boundsB.getWidth()/2;
			double R1y = boundsB.getMaxY();
		
			int position = titledPaneNodeA.getRigtRelatedTitledPanes().headSet(titledPaneNodeB).size();
			System.out.println( " Position of "+titledPaneNodeB.getText()+ " "+position);
			P1x = boundsA.getMaxX();
			P1y = boundsA.getMinY()+boundsA.getHeight()/2-30*position;
			Q1y = boundsA.getMinY()+boundsA.getHeight()/2-30*position;
			
			position = titledPaneNodeB.getBottomRelatedTitledPanes().headSet(titledPaneNodeA).size();
			Q1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
			R1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
			
			Line line1 = relationShip.getRelationshipLineNodes().get(0);
			Line line2 = relationShip.getRelationshipLineNodes().get(1);
			Line line3 = relationShip.getRelationshipLineNodes().get(2);
			Line line4 = relationShip.getRelationshipLineNodes().get(3);
			
			line3.setStartX(0);line3.setStartY(0);line3.setEndX(0);line3.setEndY(0);
			line4.setStartX(0);line4.setStartY(0);line4.setEndX(0);line4.setEndY(0);
			
			relationShip.getPolygon().getPoints().clear();
			
			Circle circle = relationShip.gettoRelationshipCircle();
			circle.setRadius(0);
			
			line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
			line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
			isCovered=true;
			

			if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line1.getStartX(), line1.getStartY(),
						line1.getStartX()+10, line1.getStartY()-10,
						line1.getStartX()+10, line1.getStartY()+10});
			*/
				// one to Many relationship
				double x1 = line1.getStartX()+20;
				double y1 = line1.getStartY();					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()+10);
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()-10);
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line2.getEndX()+10;
				y1 = line2.getEndY()+10;
				double x2 = line2.getEndX()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);
				
			}else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line2.getEndX(), line2.getEndY(),
						line2.getEndX()-10, line2.getEndY()+10,
						line2.getEndX()+10, line2.getEndY()+10});
						*/

				// one to Many relationship
				double x1 = line2.getEndX();
				double y1 = line2.getEndY()+20;					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line2.getEndX()-10);toRelationshipCrowLine1.setEndY(line2.getEndY());
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line2.getEndX()+10);toRelationshipCrowLine2.setEndY(line2.getEndY());
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line1.getStartX()+10;
				y1 = line1.getStartY()+10;
				double y2 = line1.getStartY()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);

			}
			

		}
		
		if(isLeft && isYClose) {
		
			System.out.println("Is left and y close");
			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple right connections ? current size is " + titledPaneNodeA.getRigtRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple left connections ? current size is " + titledPaneNodeB.getLeftRelatedTitledPanes().size());
		
			Line line1 = relationShip.getRelationshipLineNodes().get(0);
			Line line2 = relationShip.getRelationshipLineNodes().get(1);
			Line line3 = relationShip.getRelationshipLineNodes().get(2);
			
			line1.setStartX(0);line1.setStartY(0);line1.setEndX(0);line1.setEndY(0);
			line2.setStartX(0);line2.setStartY(0);line2.setEndX(0);line2.setEndY(0);

			Circle circle = relationShip.gettoRelationshipCircle();
			circle.setRadius(0);

			relationShip.getPolygon().getPoints().clear();
			
			double P1x = boundsA.getMaxX();
			double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double Q1x =  boundsA.getMaxX() + (boundsB.getMinX() - boundsA.getMaxX())/2;
			double Q1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double R1x =  boundsA.getMaxX() + (boundsB.getMinX() - boundsA.getMaxX())/2;
			double R1y = boundsB.getMinY()+boundsB.getHeight()/2;
			double S1x = boundsB.getMinX();
			double S1y = boundsB.getMinY()+boundsB.getHeight()/2;
			
			int position = titledPaneNodeA.getRigtRelatedTitledPanes().headSet(titledPaneNodeB).size();
			System.out.println( " Position of "+titledPaneNodeB.getText()+ " "+position);
			if(boundsA.getMinY()-boundsB.getMinY()>0) { // A is below
				P1y = boundsA.getMinY()+boundsA.getHeight()/2-30*position;
				Q1y = boundsA.getMinY()+boundsA.getHeight()/2-30*position;
			}
			else {
				P1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
				Q1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
			}
			
			position = titledPaneNodeB.getLeftRelatedTitledPanes().headSet(titledPaneNodeA).size();
			if(boundsA.getMinY()-boundsB.getMinY()>0) { // A is below
				S1y = boundsB.getMinY()+boundsB.getHeight()/2-30*position;
				R1y = boundsB.getMinY()+boundsB.getHeight()/2-30*position;
			}
			else {
				S1y = boundsB.getMinY()+boundsB.getHeight()/2+30*position;
				R1y = boundsB.getMinY()+boundsB.getHeight()/2+30*position;
			}
			
			line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
			line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
			line3.setStartX(R1x);line3.setStartY(R1y);line3.setEndX(S1x);line3.setEndY(S1y);
			isCovered=true;
			
			
			if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line1.getStartX(), line1.getStartY(),
						line1.getStartX()+10, line1.getStartY()-10,
						line1.getStartX()+10, line1.getStartY()+10});
			*/
				
				// one to Many relationship
				double x1 = line1.getStartX()+20;
				double y1 = line1.getStartY();					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()+10);
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()-10);
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line3.getEndX()-10;
				y1 = line3.getEndY()+10;
				double y2 = line3.getEndY()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);

				
			} else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line3.getEndX(), line3.getEndY(),
						line3.getEndX()-10, line3.getEndY()-10,
						line3.getEndX()-10, line3.getEndY()+10});
			*/
				
				// one to Many relationship
				double x1 = line3.getEndX()-20;
				double y1 = line3.getEndY();					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line3.getEndX());toRelationshipCrowLine1.setEndY(line3.getEndY()+10);
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line3.getEndX());toRelationshipCrowLine2.setEndY(line3.getEndY()-10);
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line1.getStartX()+10;
				y1 = line1.getStartY()+10;
				double y2 = line1.getStartY()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);

			} 
			
		}
		
		if(isRight && isYClose) {
			System.out.println("Is Right and y close");
			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple left connections ? current size is " + titledPaneNodeA.getLeftRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple Right connections ? current size is " + titledPaneNodeB.getRigtRelatedTitledPanes().size());

			Line line1 = relationShip.getRelationshipLineNodes().get(0);
			Line line2 = relationShip.getRelationshipLineNodes().get(1);
			Line line3 = relationShip.getRelationshipLineNodes().get(2);
			
			line1.setStartX(0);line1.setStartY(0);line1.setEndX(0);line1.setEndY(0);
			line2.setStartX(0);line2.setStartY(0);line2.setEndX(0);line2.setEndY(0);
	
			Circle circle = relationShip.gettoRelationshipCircle();
			circle.setRadius(0);

			relationShip.getPolygon().getPoints().clear();
			
			double P1x = boundsA.getMinX();
			double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double Q1x = boundsA.getMinX() -  (boundsA.getMinX() - boundsB.getMaxX())/2;
			double Q1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double R1x = boundsA.getMinX() -  (boundsA.getMinX() - boundsB.getMaxX())/2;
			double R1y = boundsB.getMinY()+boundsB.getHeight()/2;
			double S1x = boundsB.getMaxX();
			double S1y = boundsB.getMinY()+boundsB.getHeight()/2;
			
			int position = titledPaneNodeA.getLeftRelatedTitledPanes().headSet(titledPaneNodeB).size();
			System.out.println( " Position of "+titledPaneNodeB.getText()+ " "+position);
			if(boundsA.getMinY()-boundsB.getMinY()>0) { // A is below
				P1y = boundsA.getMinY()+boundsA.getHeight()/2-30*position;
				Q1y = boundsA.getMinY()+boundsA.getHeight()/2-30*position;
			}
			else {
				P1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
				Q1y = boundsA.getMinY()+boundsA.getHeight()/2+30*position;
			}
			
			position = titledPaneNodeB.getRigtRelatedTitledPanes().headSet(titledPaneNodeA).size();
			if(boundsA.getMinY()-boundsB.getMinY()>0) { // A is below
				S1y = boundsB.getMinY()+boundsB.getHeight()/2-30*position;
				R1y = boundsB.getMinY()+boundsB.getHeight()/2-30*position;
			}
			else {
				S1y = boundsB.getMinY()+boundsB.getHeight()/2+30*position;
				R1y = boundsB.getMinY()+boundsB.getHeight()/2+30*position;
			}

			
			line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
			line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
			line3.setStartX(R1x);line3.setStartY(R1y);line3.setEndX(S1x);line3.setEndY(S1y);
			isCovered=true;
			
			
			if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line1.getStartX(), line1.getStartY(),
						line1.getStartX()-10, line1.getStartY()-10,
						line1.getStartX()-10, line1.getStartY()+10});
			*/
				
				// one to Many relationship
				double x1 = line1.getStartX()-20;
				double y1 = line1.getStartY();					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line1.getStartX());toRelationshipCrowLine1.setEndY(line1.getStartY()+10);
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line1.getStartX());toRelationshipCrowLine2.setEndY(line1.getStartY()-10);
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line3.getEndX()+10;
				y1 = line3.getEndY()+10;
				double y2 = line3.getEndY()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);
		
			}else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line3.getEndX(), line3.getEndY(),
						line3.getEndX()+10, line3.getEndY()-10,
						line3.getEndX()+10, line3.getEndY()+10});
			*/
				
				// one to Many relationship
				double x1 = line3.getEndX()+20;
				double y1 = line3.getEndY();					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line3.getEndX());toRelationshipCrowLine1.setEndY(line3.getEndY()+10);
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line3.getEndX());toRelationshipCrowLine2.setEndY(line3.getEndY()-10);
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line1.getStartX()-10;
				y1 = line1.getStartY()+10;
				double y2 = line1.getStartY()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x1);fromRelationshipMain.setEndY(y2);
				
			}
		}
		
		if(isTop && isXClose) {
			
			System.out.println("<-------Is top and is Xclose--=---->");
			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple Bottom connections ? current size is " + titledPaneNodeA.getBottomRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple Top connections ? current size is " + titledPaneNodeB.getTopRelatedTitledPanes().size());

			Line line1 = relationShip.getRelationshipLineNodes().get(0);
			Line line2 = relationShip.getRelationshipLineNodes().get(1);
			Line line3 = relationShip.getRelationshipLineNodes().get(2);
			
			line1.setStartX(0);line1.setStartY(0);line1.setEndX(0);line1.setEndY(0);
			line2.setStartX(0);line2.setStartY(0);line2.setEndX(0);line2.setEndY(0);

			Circle circle = relationShip.gettoRelationshipCircle();
			circle.setRadius(0);

			relationShip.getPolygon().getPoints().clear();
			
			double P1x = boundsA.getMinX() + boundsA.getWidth()/2;
			double P1y = boundsA.getMaxY();
			double Q1x = boundsA.getMinX() + boundsA.getWidth()/2;
			double Q1y =  boundsA.getMaxY() +  (boundsB.getMinY()-boundsA.getMaxY())/2;
			double R1x = boundsB.getMaxX()+boundsB.getWidth()/2;
			double R1y = boundsA.getMaxY() +  (boundsB.getMinY()-boundsA.getMaxY())/2;
			double S1x = boundsB.getMaxX()+boundsB.getWidth()/2;
			double S1y = boundsB.getMinY();
			
			int position = titledPaneNodeA.getBottomRelatedTitledPanes().headSet(titledPaneNodeB).size();
			System.out.println( " Position of "+titledPaneNodeB.getText()+ " "+position);
			if(( boundsA.getMaxX() - (boundsB.getMinX() + boundsB.getWidth()/2)) < 0) { // A is left
				P1x = boundsA.getMinX()+boundsA.getWidth()/2-30*position;
				Q1x = boundsA.getMinX()+boundsA.getWidth()/2-30*position;
			}
			else {
				P1x = boundsA.getMinX()+boundsA.getWidth()/2+30*position;
				Q1x = boundsA.getMinX()+boundsA.getWidth()/2+30*position;;
			}
			
			position = titledPaneNodeB.getTopRelatedTitledPanes().headSet(titledPaneNodeA).size();
			if((boundsA.getMaxX() - (boundsB.getMinX() + boundsB.getWidth()/2)) < 0) { // A is left
				S1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
				R1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
			}
			else {
				S1x = boundsB.getMinX()+boundsB.getWidth()/2+30*position;
				R1x = boundsB.getMinX()+boundsB.getWidth()/2+30*position;
			}
			
			line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
			line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
			line3.setStartX(R1x);line3.setStartY(R1y);line3.setEndX(S1x);line3.setEndY(S1y);
			isCovered=true;
			
			
			if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line1.getStartX(), line1.getStartY(),
						line1.getStartX()-10, line1.getStartY()+10,
						line1.getStartX()+10, line1.getStartY()+10});
			*/
				// one to Many relationship
				double x1 = line1.getStartX();
				double y1 = line1.getStartY()+20;					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line1.getStartX()-10);toRelationshipCrowLine1.setEndY(line1.getStartY());
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line1.getStartX()+10);toRelationshipCrowLine2.setEndY(line1.getStartY());
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line3.getEndX()+10;
				y1 = line3.getEndY()-10;
				double x2 = line3.getEndX()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);

		
			} else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line3.getEndX(), line3.getEndY(),
						line3.getEndX()-10, line3.getEndY()-10,
						line3.getEndX()+10, line3.getEndY()-10});
			*/
				
				// one to Many relationship
				double x1 = line3.getEndX();
				double y1 = line3.getEndY()-20;					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line3.getStartX()-10);toRelationshipCrowLine1.setEndY(line3.getEndY());
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line3.getStartX()+10);toRelationshipCrowLine2.setEndY(line3.getEndY());
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line1.getStartX()+10;
				y1 = line1.getStartY()+10;
				double x2 = line1.getStartX()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);
			} 		
			
			
		}
		if(isBottom && isXClose) {
			
			System.out.println("<-------Is bottom and is Xclose--=---->");
			System.out.println("Checking if "+titledPaneNodeA.getText()+" has multiple Top connections ? current size is " + titledPaneNodeA.getTopRelatedTitledPanes().size());
			System.out.println("Checking if "+titledPaneNodeB.getText()+" has multiple Bottom connections ? current size is " + titledPaneNodeB.getBottomRelatedTitledPanes().size());

			Line line1 = relationShip.getRelationshipLineNodes().get(0);
			Line line2 = relationShip.getRelationshipLineNodes().get(1);
			Line line3 = relationShip.getRelationshipLineNodes().get(2);
			
			line1.setStartX(0);line1.setStartY(0);line1.setEndX(0);line1.setEndY(0);
			line2.setStartX(0);line2.setStartY(0);line2.setEndX(0);line2.setEndY(0);
			
			Circle circle = relationShip.gettoRelationshipCircle();
			circle.setRadius(0);
			
			relationShip.getPolygon().getPoints().clear();
			
			double P1x = boundsA.getMinX() + boundsA.getWidth()/2;
			double P1y = boundsA.getMinY();
			double Q1x = boundsA.getMinX() + boundsA.getWidth()/2;
			double Q1y = boundsA.getMinY() -  (boundsA.getMinY()-boundsB.getMaxY())/2;
			double R1x = boundsB.getMaxX()+boundsB.getWidth()/2;
			double R1y = boundsA.getMinY() -  (boundsA.getMinY()-boundsB.getMaxY())/2;
			double S1x = boundsB.getMaxX()+boundsB.getWidth()/2;
			double S1y = boundsB.getMaxY();
			
			int position = titledPaneNodeA.getTopRelatedTitledPanes().headSet(titledPaneNodeB).size();
			System.out.println( " Position of "+titledPaneNodeB.getText()+ " "+position);
			if(( boundsA.getMinX() - (boundsB.getMinX() + boundsB.getWidth()/2)) > 0) { // A is right
				P1x = boundsA.getMinX()+boundsA.getWidth()/2-30*position;
				Q1x = boundsA.getMinX()+boundsA.getWidth()/2-30*position;
			}
			else {
				P1x = boundsA.getMinX()+boundsA.getWidth()/2+30*position;
				Q1x = boundsA.getMinX()+boundsA.getWidth()/2+30*position;;
			}
			
			position = titledPaneNodeB.getBottomRelatedTitledPanes().headSet(titledPaneNodeA).size();
			if((boundsA.getMaxX() - (boundsB.getMinX() + boundsB.getWidth()/2)) < 0) { // A is left
				S1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
				R1x = boundsB.getMinX()+boundsB.getWidth()/2-30*position;
			}
			else {
				S1x = boundsB.getMinX()+boundsB.getWidth()/2+30*position;
				R1x = boundsB.getMinX()+boundsB.getWidth()/2+30*position;
			}
			
			line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
			line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
			line3.setStartX(R1x);line3.setStartY(R1y);line3.setEndX(S1x);line3.setEndY(S1y);
			isCovered=true;
			
			if(titledPaneNodeA.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeA");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line1.getStartX(), line1.getStartY(),
						line1.getStartX()-10, line1.getStartY()-10,
						line1.getStartX()+10, line1.getStartY()-10});
			*/
				// one to Many relationship
				double x1 = line1.getStartX();
				double y1 = line1.getStartY()-20;					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line1.getStartX()-10);toRelationshipCrowLine1.setEndY(line1.getStartY());
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line1.getStartX()+10);toRelationshipCrowLine2.setEndY(line1.getStartY());
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line3.getEndX()+10;
				y1 = line3.getEndY()+10;
				double x2 = line3.getEndX()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);
		
			} else if(titledPaneNodeB.equals(relationShip.getRelatedOtherEndTitledPane())){
				System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText()+" titledPaneNodeB");

			/*	relationShip.getPolygon().getPoints().addAll(new Double[]{
						line3.getEndX(), line3.getEndY(),
						line3.getEndX()-10, line3.getEndY()+10,
						line3.getEndX()+10, line3.getEndY()+10});
			*/
				
				// one to Many relationship
				double x1 = line3.getEndX();
				double y1 = line3.getEndY()+20;					
				circle.setRadius(5);circle.setCenterX(x1);circle.setCenterY(y1);
				
				Line toRelationshipCrowLine1 =  relationShip.gettoRelationshipCrowLine1();
				Line toRelationshipCrowLine2 =  relationShip.gettoRelationshipCrowLine2();
				
				toRelationshipCrowLine1.setStartX(x1);toRelationshipCrowLine1.setStartY(y1);
				toRelationshipCrowLine1.setEndX(line3.getStartX()-10);toRelationshipCrowLine1.setEndY(line3.getEndY());
				
				toRelationshipCrowLine2.setStartX(x1);toRelationshipCrowLine2.setStartY(y1);
				toRelationshipCrowLine2.setEndX(line3.getStartX()+10);toRelationshipCrowLine2.setEndY(line3.getEndY());
				
				Line fromRelationshipMain =  relationShip.getfromRelationshipMain();					
				x1 = line1.getStartX()+10;
				y1 = line1.getStartY()-10;
				double x2 = line1.getStartX()-10;
				
				fromRelationshipMain.setStartX(x1);fromRelationshipMain.setStartY(y1);
				fromRelationshipMain.setEndX(x2);fromRelationshipMain.setEndY(y1);

			} 
						
		}
		
		if(isRight && !isCovered) {
			System.out.println("Non 3/4th ot 1/4th y close");
			
			Line line1 = relationShip.getRelationshipLineNodes().get(0);
			Line line2 = relationShip.getRelationshipLineNodes().get(1);
			Line line3 = relationShip.getRelationshipLineNodes().get(2);
			
			line1.setStartX(0);line1.setStartY(0);line1.setEndX(0);line1.setEndY(0);
			line2.setStartX(0);line2.setStartY(0);line2.setEndX(0);line2.setEndY(0);
			
			
			double P1x = boundsA.getMaxX();
			double P1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double Q1x =  boundsA.getMaxX() + (boundsB.getMinX() - boundsA.getMaxX())/2;
			double Q1y = boundsA.getMinY()+boundsA.getHeight()/2;
			double R1x =  boundsA.getMaxX() + (boundsB.getMinX() - boundsA.getMaxX())/2;
			double R1y = boundsB.getMinY()+boundsB.getHeight()/2;
			double S1x = boundsB.getMinX();
			double S1y = boundsB.getMinY()+boundsB.getHeight()/2;
			
			line1.setStartX(P1x);line1.setStartY(P1y);line1.setEndX(Q1x);line1.setEndY(Q1y);
			line2.setStartX(Q1x);line2.setStartY(Q1y);line2.setEndX(R1x);line2.setEndY(R1y);
			line3.setStartX(R1x);line3.setStartY(R1y);line3.setEndX(S1x);line3.setEndY(S1y);
			
			System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText());
			
			
		}
		if(isLeft && !isCovered) {
			System.out.println("Non 3/4th ot 1/4th y close");
			
			System.out.println( " Master Node is -->"+ relationShip.getRelatedOtherEndTitledPane().getText());
		}
		
		
}

	private void enableDragAndDrop(TitledPaneNode tabletitledPane, Pane mainPane) {
        final double[] offset = new double[2];

        // When the mouse is pressed, record the offset between the mouse position and the TitledPane position
        tabletitledPane.setOnMousePressed(event -> {
            offset[0] = event.getSceneX() - tabletitledPane.getLayoutX();
            offset[1] = event.getSceneY() - tabletitledPane.getLayoutY();
            System.out.println("Title Pane clicked color the relationship lines");
        });
        
        // When dragging, update the TitledPane position
        tabletitledPane.setOnMouseDragged(event -> {
            double newX = event.getSceneX() - offset[0];
            double newY = event.getSceneY() - offset[1];

            // Restrict movement within the bounds of the mainPane
            if (newX >= 0 && newX + tabletitledPane.getWidth() <= mainPane.getWidth()) {
                tabletitledPane.setLayoutX(newX);
            }
            if (newY >= 0 && newY + tabletitledPane.getHeight() <= mainPane.getHeight()) {
                tabletitledPane.setLayoutY(newY);
            }
            
            TitledPaneNode tabletitledPaneNodeA = tabletitledPane;
            
            for(RelationShips relationShip : allRelationships) {
            	//System.out.println("Relationships -->"+relationShip.getOnFocusTitledPane().getText() +" : "+ relationShip.getRelatedOtherEndTitledPane().getText());
            	if(relationShip.getOnFocusTitledPane().equals(tabletitledPaneNodeA)) {
            		checkRelativePositionsBeforeDraw(tabletitledPaneNodeA,relationShip.getRelatedOtherEndTitledPane(),relationShip);
            		//checkRelativePositionsBeforeDraw(relationShip.getRelatedOtherEndTitledPane(),tabletitledPaneNodeA,relationShip);
            	}
            	else if(relationShip.getRelatedOtherEndTitledPane().equals(tabletitledPaneNodeA)) {
            	//	System.out.println("Other end relationship");
            		checkRelativePositionsBeforeDraw(tabletitledPaneNodeA,relationShip.getOnFocusTitledPane(),relationShip);
            	}
            }
            
            for(RelationShips relationShip : allRelationships) {
            	//System.out.println("Relationships -->"+relationShip.getOnFocusTitledPane().getText() +" : "+ relationShip.getRelatedOtherEndTitledPane().getText());
            	if(relationShip.getOnFocusTitledPane().equals(tabletitledPaneNodeA)) {
            		checkRelativePositionsToDraw(tabletitledPaneNodeA,relationShip.getRelatedOtherEndTitledPane(),relationShip);
            	}
            	else if(relationShip.getRelatedOtherEndTitledPane().equals(tabletitledPaneNodeA)) {
            	//	System.out.println("Other end relationship");
            		checkRelativePositionsToDraw(tabletitledPaneNodeA,relationShip.getOnFocusTitledPane(),relationShip);
            	}
            }
            
            
           
            
        });

        // Optional: Provide feedback on mouse release
        tabletitledPane.setOnMouseReleased(event -> {
        	for(TitledPaneNode tabletitledPane1 : allTitledPaneNodes) {
        		
    			System.out.println(tabletitledPane1.getText() +" <---Top-Side-Start--> "+tabletitledPane1.getText());
    	    	for (TitledPaneNode titledPaneNode2 : tabletitledPane1.getTopRelatedTitledPanes()) {
    	    		System.out.println(titledPaneNode2.getText());
    	    	}
    	    	System.out.println(tabletitledPane1.getText() +" <---Top-Side-End--> "+tabletitledPane1.getText());
    	    	System.out.println(tabletitledPane1.getText() +" <---Right-Side-Start--> "+tabletitledPane1.getText());
    	    	for (TitledPaneNode titledPaneNode2 : tabletitledPane1.getRigtRelatedTitledPanes()) {
    	    		System.out.println(titledPaneNode2.getText());
    	    	}
    	    	System.out.println(tabletitledPane1.getText() +" <---Right-Side-End--> "+tabletitledPane1.getText());
    	    	System.out.println(tabletitledPane1.getText() +" <---Bottom-Side-Start--> "+tabletitledPane1.getText());
    	    	for (TitledPaneNode titledPaneNode2 : tabletitledPane1.getBottomRelatedTitledPanes()) {
    	    		System.out.println(titledPaneNode2.getText());
    	    	}
    	    	System.out.println(tabletitledPane1.getText() +" <---Bottom-Side-End--> "+tabletitledPane1.getText());
    	    	System.out.println(tabletitledPane1.getText() +" <---Left-Side-Start--> "+tabletitledPane1.getText());
    	    	for (TitledPaneNode titledPaneNode2 : tabletitledPane1.getLeftRelatedTitledPanes()) {
    	    		System.out.println(titledPaneNode2.getText());
    	    	}
    	    	System.out.println(tabletitledPane1.getText() +" <---Left-Side-End--> "+tabletitledPane1.getText());
        	
    		}	
            System.out.println("TitledPane dropped at: " + tabletitledPane.getLayoutX() + ", " + tabletitledPane.getLayoutY());
        });
    }
	
}

class LineEnteredEventHandler implements EventHandler<MouseEvent>{

	private ArrayList<LineNode> getRelationshipLineNodes;
	private ArrayList<Line> toFromLines;
	private ArrayList<Circle> toFromCircles;
	
	public LineEnteredEventHandler( ArrayList<LineNode> lineNodes,ArrayList<Line> toFromLines,ArrayList<Circle> toFromCircles) {
		this.getRelationshipLineNodes = lineNodes;
		this.toFromLines = toFromLines;
		this.toFromCircles = toFromCircles;
	}
	
	@Override
	public void handle(MouseEvent event) {
		
		System.out.println("Mouse Entered!");
		for(LineNode lineNode : getRelationshipLineNodes) {
			lineNode.setStrokeWidth(2);
		//	lineNode.setEffect(this.highlightEffectOnHover);
			lineNode.setStroke(Color.DARKORANGE);
		}	
	
		for(Line line : toFromLines) {
			line.setStrokeWidth(2);
		//	lineNode.setEffect(this.highlightEffectOnHover);
			line.setStroke(Color.DARKORANGE);
		}
		for(Circle circle : toFromCircles) {
			circle.setStrokeWidth(2);
		//	lineNode.setEffect(this.highlightEffectOnHover);
			circle.setStroke(Color.DARKORANGE);
		}
	}	
}


class LineExitedEventHandler implements EventHandler<MouseEvent>{

	private ArrayList<LineNode> getRelationshipLineNodes;
	private ArrayList<Line> toFromLines;
	private ArrayList<Circle> toFromCircles;
	
	
	public LineExitedEventHandler( ArrayList<LineNode> lineNodes,ArrayList<Line> toFromLines,ArrayList<Circle> toFromCircles) {
		this.getRelationshipLineNodes = lineNodes;
		this.toFromLines = toFromLines;
		this.toFromCircles = toFromCircles;
	}
	
	@Override
	public void handle(MouseEvent event) {
		
		System.out.println("Mouse Exited!");
		for(LineNode lineNode : getRelationshipLineNodes) {
			lineNode.setStrokeWidth(2);
		//	lineNode.setEffect(null);
			lineNode.setStroke(Color.BLACK);
		}
		
		for(Line line : toFromLines) {
			line.setStrokeWidth(2);
		//	lineNode.setEffect(this.highlightEffectOnHover);
			line.setStroke(Color.BLACK);
		}
		for(Circle circle : toFromCircles) {
			circle.setStrokeWidth(2);
		//	lineNode.setEffect(this.highlightEffectOnHover);
			circle.setStroke(Color.BLACK);
		}
		
	}
	
}