package org.openjfx.fx;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VariablesFrame extends Application{

	public static void main(String[] args) {
		   launch(VariablesFrame.class, args);

	}

	  @Override
	    public void start(Stage stage) {
		  
		    Stage connectionStage = new Stage();
			BorderPane mainPopUpborderPane = new BorderPane();
			 
			HBox topHbox = addTopHBox();
			
			TabPane centerTabPane = addCenterTabbedPane();
			
			HBox bottomHbox = addBottomHBox();
			
			mainPopUpborderPane.setTop(topHbox);
			
			mainPopUpborderPane.setCenter(centerTabPane);;
			mainPopUpborderPane.setBottom(bottomHbox);
			
			Scene scene = new Scene(mainPopUpborderPane,550,500);  
			scene.getStylesheets().add(getClass().getResource("/layoutstyles.css").toExternalForm());  
			
			connectionStage.initModality(Modality.APPLICATION_MODAL);
			connectionStage.setTitle("No DataBase Connection ");   
			connectionStage.setScene(scene);
			    
			connectionStage.show();
	  }
	 
	  private HBox addBottomHBox() {

			 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #334000;");
	// Use style class to set properties previously set above (with some changes)      
	        //hbox.getStyleClass().add("hbox");
	        
	        hbox.getStyleClass().add("hbox");

	        
	        return hbox;
	    }
	  
	  private TabPane addCenterTabbedPane() {
			 
		  TabPane statusSystemVariablesTabpane = new TabPane();
		 // connectionDetailsTabs.getStyleClass().addAll("databasesflowPane");  
		
		  statusSystemVariablesTabpane.setTabMinWidth(250);
		  statusSystemVariablesTabpane.setTabMinHeight(40);
		
		  Tab statusVariablesTab = new Tab("Status Variables");
		  statusVariablesTab.setClosable(false);
		  statusVariablesTab.setContent(getStatusVariables());
	  
		 
		  Tab systemVariablesTab = new Tab("System Variables");
		  systemVariablesTab.setClosable(false);		  
		  systemVariablesTab.setContent(getSystemVariables()); 
		  
		  statusSystemVariablesTabpane.getTabs().addAll(statusVariablesTab,systemVariablesTab);
			 
		  return statusSystemVariablesTabpane;
			
	  }
	  
	  private HBox addTopHBox() {

			 
	        HBox hbox = new HBox();
//	        hbox.setPadding(new Insets(15, 12, 15, 12));
//	        hbox.setSpacing(10);   // Gap between nodes
//	        hbox.setStyle("-fx-background-color: #336699;");
	// Use style class to set properties previously set above (with some changes)      
	        hbox.getStyleClass().add("hbox");
	        
	        Text connectToDatabseText = new Text("Status and System Variables");
	        connectToDatabseText.setFont(Font.font("Verdana",20));
	        connectToDatabseText.setFill(Color.WHITE);
	        hbox.getChildren().addAll(connectToDatabseText);
	        
	        return hbox;
	    }
	  
	  private VBox getStatusVariables() {
		  
		  VBox connectionDetailsVbox = new VBox();
		  
		  TabPane statusVariablesTabPane = new TabPane();
		  statusVariablesTabPane.setTabMinWidth(100);
		  statusVariablesTabPane.setTabMinHeight(30);
		  
		  Tab globalStatusVariablesTab = new Tab("Global Status Variables");
		  globalStatusVariablesTab.setClosable(false);
		  globalStatusVariablesTab.setContent(getVariablesData("GlobalStatus"));
		  // *****************//
			

		  Tab sessionStatusVariablesTab = new Tab("Session Status Variables");
		  sessionStatusVariablesTab.setClosable(false);
		  sessionStatusVariablesTab.setContent(getVariablesData("SessionStatus"));
		  
		  statusVariablesTabPane.getTabs().addAll(globalStatusVariablesTab,sessionStatusVariablesTab);
		  
		  connectionDetailsVbox.getChildren().add(statusVariablesTabPane);
		    
		  return connectionDetailsVbox;
	  }
	  
	  private VBox getSystemVariables() {
		  
		  VBox connectionDetailsVbox = new VBox();
		  
		  TabPane systemVariablesTabPane = new TabPane();
		  systemVariablesTabPane.setTabMinWidth(100);
		  systemVariablesTabPane.setTabMinHeight(30);
		  
		  Tab globalSystemVariablesTab = new Tab("Global System Variables");
		  globalSystemVariablesTab.setClosable(false);
		  globalSystemVariablesTab.setContent(getVariablesData("GlobalSystem"));

		  Tab sessionSystemVariablesTab = new Tab("Session System Variables");
		  sessionSystemVariablesTab.setClosable(false);
		  sessionSystemVariablesTab.setContent(getVariablesData("SessionSystem"));
		    
		  systemVariablesTabPane.getTabs().addAll(globalSystemVariablesTab,sessionSystemVariablesTab);
		  
		  connectionDetailsVbox.getChildren().add(systemVariablesTabPane);
		  
		  return connectionDetailsVbox;
		  
  
	  }	

	private VBox getVariablesData(String variableType) {
		
	    VBox vBoxMain = new VBox();
		
		GridPane searchDatabasesGridPane= new GridPane();
		searchDatabasesGridPane.setPadding(new Insets(5,0,0,10));
		Label searchDatabasesLabel = new Label("Find");
		TextField searchDatabasesTextField = new TextField();
		searchDatabasesGridPane.getChildren().addAll(searchDatabasesLabel,searchDatabasesTextField);
		GridPane.setConstraints(searchDatabasesLabel, 0, 0); // Column 0 row 0
		GridPane.setHalignment(searchDatabasesLabel,HPos.CENTER);
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(50));
		GridPane.setConstraints(searchDatabasesTextField, 1, 0); // Column 0 row 0
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(260));
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(60));
		
		GridPane gridPaneTextLabel = new GridPane();
		gridPaneTextLabel.setPadding(new Insets(5,0,0,10));
		gridPaneTextLabel.setHgap(10);
		gridPaneTextLabel.getChildren().add(searchDatabasesGridPane);
		GridPane.setConstraints(searchDatabasesGridPane, 0, 0); // Column 0 row 0
		gridPaneTextLabel.getColumnConstraints().add(new ColumnConstraints(380));
		
		vBoxMain.getChildren().add(gridPaneTextLabel);
		
		// *****************//
		
		//****************//
		//   2nd level
		
		GridPane gridPaneDatabasesLists = new GridPane();
		gridPaneDatabasesLists.setHgap(10);
		gridPaneDatabasesLists.setPadding(new Insets(5,0,0,15));
		

		TableView tableView = new TableView();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Map, String> tableColumnName;
    	Map<String, Object> tableRowValue  = new HashMap<>();;
    	ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();
    	
    	String[] columnNames = {"Name","Value","Description"};
		
    	 for (int i = 0; i < columnNames.length; i++) {
	        	tableColumnName = new TableColumn<>(columnNames[i]);
	        	tableColumnName.setCellValueFactory(new MapValueFactory<>(columnNames[i]));
	 	        
	 	        tableView.getColumns().add(tableColumnName);
	     }
    	 for (int i = 0; i < columnNames.length; i++) {
     		// columnValues[i] =  rs.getString(i+1); 
     		
         	tableRowValue.put(columnNames[i], variableType);
 	        
     	}	
     	items.add(tableRowValue);
     	tableView.getItems().addAll(items);
		
		gridPaneDatabasesLists.getChildren().add(tableView);
		GridPane.setConstraints(tableView, 0,0);
		gridPaneDatabasesLists.getColumnConstraints().add(new ColumnConstraints(800));
				
		vBoxMain.getChildren().add(gridPaneDatabasesLists);
		
		//*******************//
		
		GridPane gridPaneRefresh = new GridPane();
		gridPaneRefresh.setPadding(new Insets(10, 0, 10, 20));
		
		Button refreshButton = new Button("Refresh");
		refreshButton.setMaxWidth(100);
		gridPaneRefresh.getChildren().add(refreshButton);
		GridPane.setConstraints(refreshButton, 5,0);
		gridPaneRefresh.getColumnConstraints().add(new ColumnConstraints(700));
		
		vBoxMain.getChildren().add(gridPaneRefresh);
		
		return vBoxMain;
	}		
	  
}
