package com.openfx.erdiagram;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.openfx.ermodel.Attribute;
import com.openfx.ermodel.ERModel;
import com.openfx.ermodel.Entity;
import com.openfx.ermodel.PartialParticipation;
import com.openfx.ermodel.RelationERModel;
import com.openfx.ermodel.TableERModel;
import com.openfx.ermodel.ThreeLineHorizontal;
import com.openfx.ermodel.TotalParticipation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ERModelApplication extends Application{

	private  int numNodes = 0;
	private  int NODE_RADIUS = 200;  
	public  int CANVAS_WIDTH = 4800;    // numNodes * 4 
	public  int CANVAS_HEIGHT = 4800;  // numNodes * 4
	private  double REPULSION_FORCE = 4800*4800; // CANVAS_WIDTH * CANVAS_HEIGHT  Force pushing nodes apart  CANVAS_WIDTH * CANVAS_HEIGHT
	private static final double ATTRACTION_FORCE = 0.1; // Force pulling connected nodes together
	private static final double DAMPING_FACTOR = 0.6; // Damping to slow down node movement
	private static final int ITERATIONS = 100; // Number of iterations for force-directed layout
	  
	public ERModel currentSelectedERModel;
	public ArrayList<ERModel> erModelList = new ArrayList<ERModel>();
	public HashMap<Integer,TableERModel>   tableERModelMap = new HashMap<Integer,TableERModel>();
	public StackPane stackPane ;
	public Pane mainPane;
	private Pane minatureMainPane;
	
	public ScrollPane zoomableScrollPane;
	private double scale = 1.0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(size.getHeight() + "<--->" +size.getWidth());
		
		stackPane = extracted(800,800);
		
		Scene scene = new Scene(stackPane,size.getWidth(),size.getHeight()/* Color.rgb(35, 39, 50) */);  
		primaryStage.setTitle("ER Model ");   
		primaryStage.setScene(scene);	
		primaryStage.show();

	
		
		scene.getStylesheets().add(ERModelApplication.class.getResource("/testLayout.css").toExternalForm());
		
	}




	public StackPane extracted(double width,double height) {
		
	    StackPane stackPane = new StackPane();
	    
		mainPane = new Pane();
		mainPane.setPrefSize(width,height);
		mainPane.setId("myPane");
		
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
		
		ArrayList<String> table1Attributes = new ArrayList<String>();
		table1Attributes.add("Name");
		table1Attributes.add("Age");
		table1Attributes.add("Company");
		table1Attributes.add("Dealer");
		table1Attributes.add("Address");
		table1Attributes.add("Department");
		table1Attributes.add("State");
		table1Attributes.add("Country");
		
		TableERModel tableERModel1 = new TableERModel(400,400,200,null,"Sample1",mainPane,erModelList,null);
	
		ArrayList<String> table2Attributes = new ArrayList<String>();
		table2Attributes.add("Name");
		table2Attributes.add("Age");
		table2Attributes.add("Company");
		table2Attributes.add("Dealer");
		table2Attributes.add("Address");
		table2Attributes.add("Department");
		
		TableERModel tableERModel2 = new TableERModel(900,300,200,null,"Sample1",mainPane,erModelList,null);
		
		ArrayList<String> table3Attributes = new ArrayList<String>();
		table3Attributes.add("Name");
		table3Attributes.add("Age");
		table3Attributes.add("Company");
		table3Attributes.add("Dealer");
		table3Attributes.add("Address");
		table3Attributes.add("Department");
		
		TableERModel tableERModel3 = new TableERModel(400,800,200,null,"Sample1",mainPane,erModelList,null);
		
		TableERModel tableERModel4 = new TableERModel(900,800,200,null,"Sample1",mainPane,erModelList,null);
		
		
		RelationERModel relationERModel = new RelationERModel( tableERModel1,tableERModel2,mainPane,erModelList,null);
		RelationERModel relationERModel1 = new RelationERModel( tableERModel1,tableERModel2,mainPane,erModelList,null);
		RelationERModel relationERModel2 = new RelationERModel( tableERModel2,tableERModel3,mainPane,erModelList,null);
		
	//	mainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
		mainPane.setMinWidth(4000);
		mainPane.setMinHeight(2000);
	//	minatureMainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
		minatureMainPane.setMinWidth(4000);
		minatureMainPane.setMinHeight(2000);
		
		ZoomableScrollPane zoomableScrollPane1 = new ZoomableScrollPane(mainPane,false,null);
	
		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(minatureMainPane,true,zoomableScrollPane1);
		zoomableScrollPane.vbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
		zoomableScrollPane.hbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
		
		stackPane.getChildren().add(zoomableScrollPane1);
//		TitledPane overViewTitledPane = new TitledPane();
//		overViewTitledPane.setMaxSize(minatureMainPane.getMinWidth()/10, minatureMainPane.getMinHeight()/10);
//		overViewTitledPane.setText("OVERVIEW");
//		overViewTitledPane.setContent(zoomableScrollPane);
//		stackPane.getChildren().add(overViewTitledPane);
//		stackPane.setAlignment(overViewTitledPane, Pos.TOP_LEFT);
		
		
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
			
		mainPane.setOnMouseClicked(event ->{	
			
			System.out.println("event.getSceneX() : "+event.getX());
			System.out.println("event.getSceneY() : "+event.getY());
		
			for(ERModel erModel : erModelList) {
				
				if(erModel instanceof PartialParticipation) {
					System.out.println("ITs PArtialPArticipation");
					double minX = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMinX();
					double maxX = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMaxX();
					double minY = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMinY();
					double maxY = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMaxY();
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the entity");
						mainPane.getChildren().remove(erModel.leftAnchor);
						mainPane.getChildren().remove(erModel.rightAnchor);
				 
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
						mainPane.getChildren().remove(erModel.leftAnchor);
						mainPane.getChildren().remove(erModel.rightAnchor);
				 
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineLeft1)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineLeft1);
						}
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineLeft2)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineLeft2);
						}
						
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineRight1)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineRight1);
						}
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineRight2)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineRight2);
						}
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).sampleLine1)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).sampleLine1);
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
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the group");
						if(mainPane.getChildren().contains(((ThreeLineHorizontal)erModel).linePleftCircle))
							mainPane.getChildren().remove(((ThreeLineHorizontal)erModel).linePleftCircle);
						if(mainPane.getChildren().contains(((ThreeLineHorizontal)erModel).lineQCenterCircle))
							mainPane.getChildren().remove(((ThreeLineHorizontal)erModel).lineQCenterCircle);
						if(mainPane.getChildren().contains(((ThreeLineHorizontal)erModel).lineRRightCircle))
							mainPane.getChildren().remove(((ThreeLineHorizontal)erModel).lineRRightCircle);
					}
				}
				
				else if(erModel.stackPaneRectangle != null) {
					System.out.println("Their is an Entity ");
					System.out.println(erModel.stackPaneRectangle.getBoundsInParent());
					double minX = erModel.stackPaneRectangle.getBoundsInParent().getMinX();
					double maxX = erModel.stackPaneRectangle.getBoundsInParent().getMaxX();
					double minY = erModel.stackPaneRectangle.getBoundsInParent().getMinY();
					double maxY = erModel.stackPaneRectangle.getBoundsInParent().getMaxY();
					
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the entity");
						
						mainPane.getChildren().remove(erModel.topAnchor);
						mainPane.getChildren().remove(erModel.topLeftAnchor);
						mainPane.getChildren().remove(erModel.topRightAnchor);
						 
						mainPane.getChildren().remove(erModel.leftAnchor);
						mainPane.getChildren().remove(erModel.rightAnchor);
						 
						mainPane.getChildren().remove(erModel.bottomleftAnchor);
						mainPane.getChildren().remove(erModel.bottomAnchor);
						mainPane.getChildren().remove(erModel.bottomRightAnchor);
						 
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
					
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the TableERModel");
						
						 ((TableERModel)erModel).circle2.setStroke(Color.TRANSPARENT);
					}
				}
			}
		}
		);
		
		return stackPane;
	}
	
	
	
	public StackPane drawERModel(TreeItem<String> loadedDatabaseName,Connection currentConnection,Stack<Runnable> undoStack ) {

		StackPane stackPane = new StackPane();
	    
		mainPane = new Pane();
		//mainPane.setPrefSize(width,height);
		mainPane.setId("myPane");
		minatureMainPane = new Pane();
	//	minatureMainPane.setPrefSize(width,height);
		
		ArrayList<String> tableList = new ArrayList<String>();
		try( ResultSet rs = currentConnection.createStatement().executeQuery("select table_name from information_schema.tables where table_comment != 'view' and table_schema='"+loadedDatabaseName.getValue()+"'"))
		{ 			 
			  System.out.println("Tables");
			  while(rs.next()) {
				  System.out.println(rs.getString(1));
				  tableList.add(rs.getString(1));
			  }
		}catch(Exception e) {
			  e.printStackTrace() ;
		}
		
		
	// Define the nodes and their initial random positions
	//	numNodes = tableList.size()+1;
	//	CANVAS_WIDTH = numNodes * 4;
	//	CANVAS_HEIGHT = numNodes * 4;
	//	REPULSION_FORCE = CANVAS_WIDTH * CANVAS_HEIGHT;
			
		
		  // lign up the tables to establish proper rlationships
		  ArrayList<Integer[]> relationEdges = new ArrayList<Integer[]>(); 
		  HashMap<String, Set<String>> tableRelationshipsHashMap = new HashMap<String,Set<String>>();
		  try( ResultSet rs = currentConnection.createStatement().executeQuery("select CONSTRAINT_NAME,TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,REFERENCED_TABLE_SCHEMA,REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME"
		  		+ " from information_schema.key_column_usage where table_schema = '"+ loadedDatabaseName.getValue() +"' and referenced_column_name is not null "))
		  { 			 
			  System.out.println("<--Scehma Constainsts-->");
			  while(rs.next()) {
			//	  System.out.println("CONSTRAINT_NAME " +rs.getString(1));
			//	  System.out.println("TABLE_SCHEMA " +rs.getString(2));
				  System.out.print(" " +rs.getString(3));  // Table Name
				  System.out.print("--> " +rs.getString(4));
			//	  System.out.println("REFERENCED_TABLE_SCHEMA " +rs.getString(5));
				  System.out.print("  " +rs.getString(6));  // Referenced Table Name
				  System.out.println("---->  " +rs.getString(7));
				  
				  relationEdges.add( new Integer[] {tableList.indexOf(rs.getString(3)), tableList.indexOf(rs.getString(6))});
				  
				  if(!tableRelationshipsHashMap.containsKey(rs.getString(3))) {
					  tableRelationshipsHashMap.put(rs.getString(3), new TreeSet<String>());  
					  tableRelationshipsHashMap.get(rs.getString(3)).add(rs.getString(6));
				  }
				  else {
					  tableRelationshipsHashMap.get(rs.getString(3)).add(rs.getString(6));
				  }
			  }
		  }catch(Exception e) {
			  e.printStackTrace() ;
		  }		  
		
		  // Capture non related as {same,same} edge
		  
		  ArrayList<Integer> nodesNotFoundList = new ArrayList<Integer>();
		  for(int i=0;i<tableList.size();i++) {
			  boolean found = false;
			  for(Integer[] relationEdge : relationEdges) {
				  
				  if(relationEdge[0] == i) {
					  found = true;
					  break;
				  }
			  }
			  if(!found) {
				  System.out.println("Not found is " + i);
				  nodesNotFoundList.add(i);
			  }
		  }
		  
		  for(Integer nodeNotFound : nodesNotFoundList) {
			  relationEdges.add(new Integer[] { nodeNotFound, nodeNotFound}) ;
		  }
		 
		  numNodes = relationEdges.size();
		  CANVAS_WIDTH = numNodes * 200;
		  CANVAS_HEIGHT = numNodes * 200;
		  REPULSION_FORCE = CANVAS_WIDTH * CANVAS_HEIGHT;
		  
		  System.out.println("Number of nodes" + numNodes);
			List<double[]> nodePositions = new ArrayList<>();
			for (int i = 0; i < numNodes; i++) {
			     double x = Math.random() * CANVAS_WIDTH;
			     double y = Math.random() * CANVAS_HEIGHT;
			     nodePositions.add(new double[]{x, y});
			}
		
			
		  System.out.println("Edges---->Start");
		  for(Integer[] relationEdge : relationEdges) {
			  
			  System.out.println("{"+relationEdge[0]+","+relationEdge[1]+"}");
		  }
		  System.out.println("Edges---->End");
		  
		   
		  int[][] edges = new int[relationEdges.size()][2];
		  
		  for(int i=0;i<relationEdges.size();i++) {
			for(int j=0;j<2;j++) {
				edges[i][j] = relationEdges.get(i)[j];
			}
		  }
	
		  
		  // Apply force-directed layout to adjust node positions
	      for (int iteration = 0; iteration < ITERATIONS; iteration++) {
	            applyForceDirectedLayout(nodePositions, edges);
	      }

	      ArrayList<Integer> addedNodestoPaneList = new ArrayList<Integer>();
	      for (int[] edge : edges) {
	    	  
	            double startX = nodePositions.get(edge[0])[0];
	            double startY = nodePositions.get(edge[0])[1];
	            double endX = nodePositions.get(edge[1])[0];
	            double endY = nodePositions.get(edge[1])[1];
	            
	            System.out.print("startX : "+startX);
	            System.out.print("startY : "+startY);
	            System.out.print("endX : "+endX);
	            System.out.print("endY : "+endY);
	            
	            System.out.print("edge[0] : "+edge[0]);
	            System.out.println(" edge[1] : "+edge[1]);
	            
	            if(addedNodestoPaneList.contains(edge[0])) {
	            	continue;
	            }
	            addedNodestoPaneList.add( edge[0]);
	            try( ResultSet rs = currentConnection.createStatement().executeQuery("select column_name,COLUMN_KEY from information_schema.columns where table_Schema ='"+ loadedDatabaseName.getValue()  +"' and table_name = '"+tableList.get( edge[0])+"'"))
				  {
					  Map<String,String>  tableAllattributesMap = new HashMap<String,String>();
					  while(rs.next()) {
						tableAllattributesMap.put( rs.getString(1)+"#"+rs.getString(2), rs.getString(1));
					  }
					  TableERModel tableERModel = new TableERModel(startX,startY,NODE_RADIUS,tableAllattributesMap,loadedDatabaseName.getValue()+"."+tableList.get( edge[0]),mainPane,erModelList,undoStack);
					  tableERModelMap.put(edge[0], tableERModel);
				  }catch(Exception e) {
					  e.printStackTrace() ;
				  }
	      }
	      
	   // Add the relationships
		 for (int[] edge : edges) {
			   
			 	  if(edge[0] == edge[1])
			 		  continue;
				  RelationERModel relationERModel = new RelationERModel( tableERModelMap.get(edge[0]), tableERModelMap.get(edge[1]),mainPane,erModelList,undoStack);  
			  
		  }
	    // re planting on the main so that relationship lines don't overlapp the entityies 
		 	for(ERModel erModel : erModelList) {
		 		
		 		if(erModel instanceof Entity) {
		 			if(erModel.stackPaneRectangle != null) {
			 			mainPane.getChildren().remove(erModel.stackPaneRectangle);
			 			mainPane.getChildren().add(erModel.stackPaneRectangle);
			 		}
		 		}
		 		
		 		
		 	}
		  
		  /*
	      
		  // Write an algorithm to cover all relationships and the table size and their alignmnets
		  Set<String> tableSet = new LinkedHashSet<String>();
		  for(Map.Entry<String, Set<String>> entry : tableRelationshipsHashMap.entrySet()) {
			  System.out.print("key : " + entry.getKey());
			  System.out.println(" Value : "+ entry.getValue());
			  
			  tableSet.add(entry.getKey());
			  Iterator<String> iterator =  entry.getValue().iterator();
			  if(iterator.hasNext() ) {
				  tableSet.add(iterator.next());
			  }
		  }
		  
		  System.out.println("New Table Set === > "+tableSet.toString());
		  List<String> relationshippedTables = new ArrayList<String>();
		  relationshippedTables.addAll(tableSet);
		  
		  System.out.println("Table List Size -->"+relationshippedTables.size());
		  int xIndex = 0;
		  int yIndex = 1;
		  // pull the table and its attributes // calculate its space // calculate its relationships 
		  for(int i =0;i<relationshippedTables.size();i++) {
			  System.out.println("Table -->"+relationshippedTables.get(i));
			  xIndex++;
			  try( ResultSet rs = currentConnection.createStatement().executeQuery("select column_name from information_schema.columns where table_Schema ='"+ loadedDatabaseName.getValue()  +"' and table_name = '"+relationshippedTables.get(i)+"'"))
			  {
				  ArrayList<String> table1Attributes = new ArrayList<String>();
				  while(rs.next()) {
					  System.out.println(rs.getString(1));
					  table1Attributes.add(rs.getString(1));	
				  }
				  
				  TableERModel tableERModel = new TableERModel((xIndex)*900,(yIndex)*800,200,table1Attributes,loadedDatabaseName.getValue()+"."+relationshippedTables.get(i),mainPane,erModelList);
				  tableERModelMap.put(relationshippedTables.get(i), tableERModel);
				  
				  if(xIndex == 3) {
					  xIndex = 0;
					  yIndex++;
				  }  
			  }catch(Exception e) {
				  e.printStackTrace() ;
			  }
		  }		
		
		//address the missing tables
		
		// Add the relationships
		  for(Map.Entry<String, Set<String>> entry : tableRelationshipsHashMap.entrySet()) {
			  System.out.print("key : " + entry.getKey());
			  System.out.println(" Value : "+ entry.getValue());
			  
			  for(String realtionTable : entry.getValue()) {
				  RelationERModel relationERModel = new RelationERModel( tableERModelMap.get(entry.getKey()), tableERModelMap.get(realtionTable),mainPane,erModelList);  
			  }
		  }
	*/
	      
	//	RelationERModel relationERModel = new RelationERModel( tableERModelList.get(0),tableERModelList.get(1),mainPane,erModelList);
	//	RelationERModel relationERModel1 = new RelationERModel( tableERModelList.get(3),tableERModelList.get(5),mainPane,erModelList);
	//	RelationERModel relationERModel2 = new RelationERModel( tableERModel2,tableERModel3,mainPane,erModelList);
		
	//	mainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
		mainPane.setMinWidth(CANVAS_WIDTH);
		mainPane.setMinHeight(CANVAS_HEIGHT);
	//	minatureMainPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.WHITE,  CornerRadii.EMPTY,Insets.EMPTY ) ));
		minatureMainPane.setMinWidth(4000);
		minatureMainPane.setMinHeight(2000);
		
		
		 // Create scroll pane and configure it
        zoomableScrollPane = new ScrollPane(mainPane);
        zoomableScrollPane.setFitToWidth(false);  // Allow horizontal scrolling
        zoomableScrollPane.setFitToHeight(false);  // Allow vertical scrolling
    //    scrollPane.setPannable(true);      // Enable click-and-drag panning
     
        
        // Set initial viewport position to center
        //zoomableScrollPane.setHvalue(zoomableScrollPane.getHmax() / 2);
        //zoomableScrollPane.setVvalue(zoomableScrollPane.getVmax() / 2);
        
        // Set up zoom functionality
        setupZoomHandlers(mainPane);
		
	/*	ZoomableScrollPane zoomableScrollPane1 = new ZoomableScrollPane(mainPane,false,null);
	
		ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(minatureMainPane,true,zoomableScrollPane1);
		zoomableScrollPane.vbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);
		zoomableScrollPane.hbarPolicyProperty().setValue(ScrollBarPolicy.NEVER);  */
		
        System.out.println(" mainPane.getScaleX() --> "+ mainPane.getScaleX());
        System.out.println(" mainPane.getScaleY() --> "+ mainPane.getScaleY());
        
		stackPane.getChildren().add(zoomableScrollPane);

			
		mainPane.setOnMouseClicked(event ->{	
			
			System.out.println("event.getSceneX() : "+event.getX());
			System.out.println("event.getSceneY() : "+event.getY());
		
			for(ERModel erModel : erModelList) {
			
				
				if(erModel instanceof PartialParticipation) {
					System.out.println("ITs PArtialPArticipation");
					double minX = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMinX();
					double maxX = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMaxX();
					double minY = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMinY();
					double maxY = ((PartialParticipation)erModel).sampleLine.getBoundsInParent().getMaxY();
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the entity");
						mainPane.getChildren().remove(erModel.leftAnchor);
						mainPane.getChildren().remove(erModel.rightAnchor);
				 
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
						mainPane.getChildren().remove(erModel.leftAnchor);
						mainPane.getChildren().remove(erModel.rightAnchor);
				 
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineLeft1)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineLeft1);
						}
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineLeft2)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineLeft2);
						}
						
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineRight1)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineRight1);
						}
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).parpendicularLineRight2)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).parpendicularLineRight2);
						}
						if(!mainPane.getChildren().contains(((TotalParticipation)erModel).sampleLine1)) {
							mainPane.getChildren().remove(((TotalParticipation)erModel).sampleLine1);
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
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the group");
						if(mainPane.getChildren().contains(((ThreeLineHorizontal)erModel).linePleftCircle))
							mainPane.getChildren().remove(((ThreeLineHorizontal)erModel).linePleftCircle);
						if(mainPane.getChildren().contains(((ThreeLineHorizontal)erModel).lineQCenterCircle))
							mainPane.getChildren().remove(((ThreeLineHorizontal)erModel).lineQCenterCircle);
						if(mainPane.getChildren().contains(((ThreeLineHorizontal)erModel).lineRRightCircle))
							mainPane.getChildren().remove(((ThreeLineHorizontal)erModel).lineRRightCircle);
					}
				}
				
				else if(erModel.stackPaneRectangle != null) {
					System.out.println("Their is an Entity ");
					System.out.println(erModel.stackPaneRectangle.getBoundsInParent());
					double minX = erModel.stackPaneRectangle.getBoundsInParent().getMinX();
					double maxX = erModel.stackPaneRectangle.getBoundsInParent().getMaxX();
					double minY = erModel.stackPaneRectangle.getBoundsInParent().getMinY();
					double maxY = erModel.stackPaneRectangle.getBoundsInParent().getMaxY();
					
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the entity");
						
						mainPane.getChildren().remove(erModel.topAnchor);
						mainPane.getChildren().remove(erModel.topLeftAnchor);
						mainPane.getChildren().remove(erModel.topRightAnchor);
						 
						mainPane.getChildren().remove(erModel.leftAnchor);
						mainPane.getChildren().remove(erModel.rightAnchor);
						 
						mainPane.getChildren().remove(erModel.bottomleftAnchor);
						mainPane.getChildren().remove(erModel.bottomAnchor);
						mainPane.getChildren().remove(erModel.bottomRightAnchor);
						
						
						
						((Rectangle)  erModel.stackPaneRectangle.getChildren().get(0)).setStroke(Color.TRANSPARENT);   
						
					}
				}
				if(erModel instanceof TableERModel) {
					System.out.println("Their is an TableERModel ");
					System.out.println(((TableERModel)erModel).circle2.getBoundsInParent());
					double minX = ((TableERModel)erModel).circle2.getBoundsInParent().getMinX();
					double maxX = ((TableERModel)erModel).circle2.getBoundsInParent().getMaxX();
					double minY = ((TableERModel)erModel).circle2.getBoundsInParent().getMinY();
					double maxY = ((TableERModel)erModel).circle2.getBoundsInParent().getMaxY();
					
					if(! ((event.getX() >= minX && event.getX() <= maxX) && (event.getY() >= minY && event.getY() <= maxY)) ) {
						System.out.println("Clicked outside the TableERModel");
						
						 ((TableERModel)erModel).circle2.setStroke(Color.TRANSPARENT);
					}
				}
		
				
			}
		}
		);
		
		return stackPane;
	} 
	
    private void setupZoomHandlers(Pane pane) {
        pane.setOnScroll(event -> {
            double zoomFactor = 1.05;
            if (event.getDeltaY() < 0) {
                zoomFactor = 1 / zoomFactor;
            }
            
            scale *= zoomFactor;
            pane.setScaleX(scale);
            pane.setScaleY(scale);
            event.consume();
        });
    }
    
	  // Helper method to apply force-directed layout
    private void applyForceDirectedLayout(List<double[]> nodePositions, int[][] edges) {
        int numNodes = nodePositions.size();
        double[] forcesX = new double[numNodes];
        double[] forcesY = new double[numNodes];

        // Calculate repulsion forces (nodes push each other apart)
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                double dx = nodePositions.get(j)[0] - nodePositions.get(i)[0];
                double dy = nodePositions.get(j)[1] - nodePositions.get(i)[1];
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance > 0) {
                    double force = REPULSION_FORCE / (distance * distance);
                    forcesX[i] -= force * dx / distance;
                    forcesY[i] -= force * dy / distance;
                    forcesX[j] += force * dx / distance;
                    forcesY[j] += force * dy / distance;
                }
            }
        }

        // Calculate attraction forces (edges pull nodes together)
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            double dx = nodePositions.get(j)[0] - nodePositions.get(i)[0];
            double dy = nodePositions.get(j)[1] - nodePositions.get(i)[1];
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance > 0) {
                double force = ATTRACTION_FORCE * distance;
                forcesX[i] += force * dx / distance;
                forcesY[i] += force * dy / distance;
                forcesX[j] -= force * dx / distance;
                forcesY[j] -= force * dy / distance;
            }
        }

        // Update node positions based on forces
        for (int i = 0; i < numNodes; i++) {
            nodePositions.get(i)[0] += forcesX[i] * DAMPING_FACTOR;
            nodePositions.get(i)[1] += forcesY[i] * DAMPING_FACTOR;

            // Ensure nodes stay within the canvas bounds
            nodePositions.get(i)[0] = Math.max(NODE_RADIUS, Math.min((CANVAS_WIDTH-200) - NODE_RADIUS, nodePositions.get(i)[0]));
            nodePositions.get(i)[1] = Math.max(NODE_RADIUS, Math.min((CANVAS_HEIGHT-200) - NODE_RADIUS, nodePositions.get(i)[1]));
        }
    }
	
	public static void main(String[] args) {
		   launch(ERModelApplication.class, args);
	}
	
}


