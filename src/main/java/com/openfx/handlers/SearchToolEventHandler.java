package com.openfx.handlers;


import org.openjfx.fx.Menu_Items_FX;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SearchToolEventHandler implements EventHandler<ActionEvent> {

	public Menu_Items_FX menu_Items_FX;
	public SplitPane searchTabSplitPane;
	
	public SearchToolEventHandler(Menu_Items_FX menu_Items_FX) {
		this.menu_Items_FX = menu_Items_FX;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
	
		if(menu_Items_FX.dataSearchtabPane != null) {
			SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
        	singleSelectionModel.select(menu_Items_FX.dataSearchtabPane);
			return;
		}
		
		searchTabSplitPane = new SplitPane();
		searchTabSplitPane.setOrientation(Orientation.VERTICAL);
        
		// ****************//
		VBox vBoxMain = new VBox();
		
		GridPane searchDatabasesGridPane= new GridPane();
		Label searchDatabasesLabel = new Label("Find");
		TextField searchDatabasesTextField = new TextField();
		searchDatabasesTextField.getStyleClass().add("textfield");
		Label searchDatabasesClearAllLable = new Label("Clear All");
		searchDatabasesClearAllLable.setId("clearlabel");
//		searchDatabasesClearAllLable.setFont(Font.font("System",FontWeight.NORMAL,FontPosture.ITALIC,12));
//		searchDatabasesClearAllLable.setTextFill(Color.BLUE);
		searchDatabasesGridPane.getChildren().addAll(searchDatabasesLabel,searchDatabasesTextField,searchDatabasesClearAllLable);
		GridPane.setConstraints(searchDatabasesLabel, 0, 0); // Column 0 row 0
		GridPane.setHalignment(searchDatabasesLabel,HPos.CENTER);
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(50));
		GridPane.setConstraints(searchDatabasesTextField, 1, 0); // Column 0 row 0
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(160));
		GridPane.setConstraints(searchDatabasesClearAllLable, 2, 0); // Column 0 row 0
		GridPane.setHalignment(searchDatabasesClearAllLable,HPos.RIGHT);
		GridPane.setValignment(searchDatabasesClearAllLable,VPos.BOTTOM);
		searchDatabasesGridPane.getColumnConstraints().add(new ColumnConstraints(60));
				
		GridPane gridPaneTextLabel = new GridPane();
		gridPaneTextLabel.getStyleClass().add("grid-pane-label");
	    //gridPaneTextLabel.setPadding(new Insets(5,0,0,5));
	    //gridPaneTextLabel.setPadding(Insets.EMPTY);
	    gridPaneTextLabel.setHgap(10);
		gridPaneTextLabel.getChildren().add(searchDatabasesGridPane);
		GridPane.setConstraints(searchDatabasesGridPane, 0, 0); // Column 0 row 0
		gridPaneTextLabel.getColumnConstraints().add(new ColumnConstraints(280));
		
		// *****************//
		
		GridPane searchTablesGridPane= new GridPane();
		Label searchTablesLabel = new Label("Find");
		TextField searchTablesTextField = new TextField();
		searchTablesTextField.getStyleClass().add("textfield");
		Label searchTablesClearAllLable = new Label("Clear All");
		searchTablesClearAllLable.setId("clearlabel");
		//searchTablesClearAllLable.setFont(Font.font("System",FontWeight.NORMAL,FontPosture.ITALIC,12));
		//searchTablesClearAllLable.setTextFill(Color.BLUE);
		searchTablesGridPane.getChildren().addAll(searchTablesLabel,searchTablesTextField,searchTablesClearAllLable);
		GridPane.setConstraints(searchTablesLabel, 0, 0); // Column 0 row 0
		GridPane.setHalignment(searchTablesLabel,HPos.CENTER);
		searchTablesGridPane.getColumnConstraints().add(new ColumnConstraints(50));
		GridPane.setConstraints(searchTablesTextField, 1, 0); // Column 0 row 0
		searchTablesGridPane.getColumnConstraints().add(new ColumnConstraints(190));
		GridPane.setConstraints(searchTablesClearAllLable, 2, 0); // Column 0 row 0
		GridPane.setHalignment(searchTablesClearAllLable,HPos.RIGHT);
		GridPane.setValignment(searchTablesClearAllLable,VPos.BOTTOM);
		searchTablesGridPane.getColumnConstraints().add(new ColumnConstraints(130));

		gridPaneTextLabel.getChildren().add(searchTablesGridPane);
		GridPane.setConstraints(searchTablesGridPane, 1, 0); // Column 0 row 0
		gridPaneTextLabel.getColumnConstraints().add(new ColumnConstraints(380));
		
		//*****************//
		
		GridPane toSearchTablesTextGridPane= new GridPane();
		Label toSearchTablesLabel = new Label("Find");
		TextField toSearchTablesTextField = new TextField();
		toSearchTablesTextField.getStyleClass().add("textfield");
		Label toSearchTablesClearAllLable = new Label("Clear All");
		toSearchTablesClearAllLable.setId("clearlabel");
//		toSearchTablesClearAllLable.setFont(Font.font("System",FontWeight.NORMAL,FontPosture.ITALIC,12));
//		toSearchTablesClearAllLable.setTextFill(Color.BLUE);
		toSearchTablesTextGridPane.getChildren().addAll(toSearchTablesLabel,toSearchTablesTextField,toSearchTablesClearAllLable);
		GridPane.setConstraints(toSearchTablesLabel, 0, 0); // Column 0 row 0
		GridPane.setHalignment(toSearchTablesLabel,HPos.CENTER);
		toSearchTablesTextGridPane.getColumnConstraints().add(new ColumnConstraints(50));
		GridPane.setConstraints(toSearchTablesTextField, 1, 0); // Column 0 row 0
		toSearchTablesTextGridPane.getColumnConstraints().add(new ColumnConstraints(190));
		GridPane.setConstraints(toSearchTablesClearAllLable, 2, 0); // Column 0 row 0
		GridPane.setHalignment(toSearchTablesClearAllLable,HPos.RIGHT);
		GridPane.setValignment(toSearchTablesClearAllLable,VPos.BOTTOM);
		toSearchTablesTextGridPane.getColumnConstraints().add(new ColumnConstraints(130));
				
		gridPaneTextLabel.getChildren().add(toSearchTablesTextGridPane);
		GridPane.setConstraints(toSearchTablesTextGridPane, 2, 0); // Column 0 row 0
		gridPaneTextLabel.getColumnConstraints().add(new ColumnConstraints(380));
		
		vBoxMain.getChildren().add(gridPaneTextLabel);
		
		//****************//
		//   2nd levels
		
		GridPane gridPaneDatabasesLists = new GridPane();
		gridPaneTextLabel.getStyleClass().add("grid-pane-label");
	//	gridPaneDatabasesLists.getStyleClass().add("grid-pane");
		gridPaneDatabasesLists.setHgap(10);
		gridPaneTextLabel.setPadding(Insets.EMPTY);
		gridPaneDatabasesLists.setPadding(new Insets(5,0,0,5));
		//ListView<String> listView = new ListView<String>();
	
		gridPaneDatabasesLists.getChildren().add(menu_Items_FX.connectedDatabasesNames);
		GridPane.setConstraints(menu_Items_FX.connectedDatabasesNames, 0,0);
		gridPaneDatabasesLists.getColumnConstraints().add(new ColumnConstraints(280));
		
		//listView = new ListView<String>();
		//listView.setMaxHeight(350);
		
		gridPaneDatabasesLists.getChildren().add(menu_Items_FX.loadedTablesNames);
		GridPane.setConstraints(menu_Items_FX.loadedTablesNames, 1,0);
		
		gridPaneDatabasesLists.getColumnConstraints().add(new ColumnConstraints(380));
		
		//listView = new ListView<String>();
		//listView.setMaxHeight(350);
		
		gridPaneDatabasesLists.getChildren().add(menu_Items_FX.selectedTablesNames);
		GridPane.setConstraints(menu_Items_FX.selectedTablesNames, 2,0);
		gridPaneDatabasesLists.getColumnConstraints().add(new ColumnConstraints(380));
		
		vBoxMain.getChildren().add(gridPaneDatabasesLists);
		
		//*******************//
		
		GridPane gridPaneValueToSearch = new GridPane();
		gridPaneValueToSearch.setId("gridPaneValueToSearch");
		//gridPaneValueToSearch.setPadding(new Insets(10, 0, 10, 20));
		
		Button searchHelpButton = new Button("Help");
		searchHelpButton.setId("buttons");
		gridPaneValueToSearch.getChildren().add(searchHelpButton);
		GridPane.setConstraints(searchHelpButton, 0,0);
		gridPaneValueToSearch.getColumnConstraints().add(new ColumnConstraints(100));
		
		// Empty place holders
		GridPane.setConstraints(new Label(), 1,0);
		gridPaneValueToSearch.getColumnConstraints().add(new ColumnConstraints(300));
		
		Label dataValueToSearchLabel = new Label("Enter the data value to search : ");
		dataValueToSearchLabel.setId("dataValueToSearchLabel");
		GridPane.setHalignment(dataValueToSearchLabel,HPos.RIGHT);
		gridPaneValueToSearch.getChildren().add(dataValueToSearchLabel);
		GridPane.setConstraints(dataValueToSearchLabel, 2,0);
		gridPaneValueToSearch.getColumnConstraints().add(new ColumnConstraints(300));
		
		TextField dataValueToSearchTextField = new TextField();
		dataValueToSearchTextField.getStyleClass().add("textfield");
		gridPaneValueToSearch.getChildren().add(dataValueToSearchTextField);
		GridPane.setConstraints(dataValueToSearchTextField, 3,0);
		gridPaneValueToSearch.getColumnConstraints().add(new ColumnConstraints(200));
		
		// Empty place holders
		GridPane.setConstraints(new Label(), 4,0);
		gridPaneValueToSearch.getColumnConstraints().add(new ColumnConstraints(10));
		
		Button searchDataButton = new Button("Search");
		searchDataButton.setId("buttons");
		//searchDataButton.setMaxWidth(100);
		gridPaneValueToSearch.getChildren().add(searchDataButton);
		GridPane.setConstraints(searchDataButton, 5,0);
		gridPaneValueToSearch.getColumnConstraints().add(new ColumnConstraints(150));
		
		vBoxMain.getChildren().add(gridPaneValueToSearch);
		
		searchTabSplitPane.setDividerPositions(0.73);  // split pane divider moving a bit lower
		searchTabSplitPane.setId("SplitPane");
		searchTabSplitPane.getItems().addAll(vBoxMain); // Top half of 
		searchTabSplitPane.getItems().add(new HBox()); // bottom half of query editer
		
		menu_Items_FX.dataSearchtabPane = new Tab("Search Data");
		menu_Items_FX.dataSearchtabPane.setId("dataSearchtabPane");
		menu_Items_FX.dataSearchtabPane.setContent(searchTabSplitPane);
		
		menu_Items_FX.dataSearchtabPane.setOnClosed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				System.out.println("On Tab close request ");
				menu_Items_FX.dataSearchtabPane = null;
				menu_Items_FX.alltabbedEditors.getTabs().remove(menu_Items_FX.dataSearchtabPane);
			}
		});
		
		menu_Items_FX.alltabbedEditors.getTabs().add(menu_Items_FX.dataSearchtabPane);
		
        SingleSelectionModel<Tab> singleSelectionModel =  menu_Items_FX.alltabbedEditors.getSelectionModel();
        singleSelectionModel.select(menu_Items_FX.dataSearchtabPane);
		
	}

	
}
