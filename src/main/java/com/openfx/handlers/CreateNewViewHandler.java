package com.openfx.handlers;

import com.openfx.handlers.CreateNewTableHandler.TableRow;
import com.openfx.ui.MySqlUI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateNewViewHandler implements EventHandler<ActionEvent> {

	public MySqlUI mysqlui;
	public Stage stage;
	public TextArea generatedSQLQueryTextArea;

	public CreateNewViewHandler(MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.mysqlui = mysqlui;
	}
    
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		mysqlui.createNewViewHandler = this;
		
		VBox newViewTabVBox = new VBox(); 		 
		//VBox.setVgrow(newTableTabVBox, Priority.ALWAYS);

		HBox viewPropertiesHbox = new HBox();
		viewPropertiesHbox.getStyleClass().add("viewpropertiesHbox");

		VBox leftViewPropertiesVbox = new VBox();
		leftViewPropertiesVbox.getStyleClass().add("leftpropertiesVbox");
	    
		HBox viewnameValueHbox = new HBox();
		viewnameValueHbox.getStyleClass().add("viewnameValueHbox");
		Label viewnameLabel = new Label("View Name* : ");
		viewnameLabel.setId("createViewTabLabels");
		TextField viewNameValueTextField = new TextField();
		
		viewNameValueTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
//			public void handle(KeyEvent keyEvent) {
//				System.out.println("\"CREATE VIEW $"+ viewNameValueField.getText());
//				generatedSQLQueryTextArea.setText("\"CREATE VIEW $ "+ viewNameValueField.getText());
//				
//			}
			
			public void handle(KeyEvent keyEvent) {
				String viewName = viewNameValueTextField.getText().trim();
				String viewSQL = "CREATE VIEW " + viewName ;

				if(keyEvent.getCode().toString().equals("ENTER")) {
					if (!viewName.isEmpty() && !viewName.isEmpty()) {
						viewSQL+= " AS";
					}
				}
				System.out.println(viewSQL);
				generatedSQLQueryTextArea.setText(viewSQL);
			}
		});

		viewNameValueTextField.focusedProperty().addListener((observable,oldValue,newValue)->{
			if (!newValue) { // focus lost
		        String viewName = viewNameValueTextField.getText();

		        if (viewName != null && !viewName.isEmpty()) {
		            String viewSQL = "CREATE VIEW " + viewName;
		            viewSQL +=" AS";
		            
		            generatedSQLQueryTextArea.setText(viewSQL);
		            System.out.println(viewSQL);
		        }
		    }
		});
		
		viewNameValueTextField.getStyleClass().add("Valuetextfield");
		viewnameValueHbox.getChildren().addAll(viewnameLabel,viewNameValueTextField);

		HBox algorithmHBox = new HBox();
		algorithmHBox.getStyleClass().add("algorithmHBox");
		Label algorithmLabel = new Label("Algorithm: ");
		algorithmLabel.setId("createViewTabLabels");
		TextField algorithmTextField = new TextField();
		algorithmTextField.getStyleClass().add("Valuetextfield");
		algorithmTextField.setEditable(false);
		algorithmHBox.getChildren().addAll(algorithmLabel,algorithmTextField);


//		HBox descriptionValuehbox = new HBox();
//		descriptionValuehbox.getStyleClass().add("descriptionValueHBox");
//		Label descrpitionLabel = new Label("Description : ");
//		descrpitionLabel.setId("createViewTabLabels");
//		TextArea descriptionField = new TextArea();
//		descriptionField.getStyleClass().add("descriptiontextfield");
//		descriptionValuehbox.getChildren().addAll(descrpitionLabel,descriptionField);
//		leftpropertiesVbox.getChildren().addAll(viewnameValueHbox,algorithmHBox,descriptionValuehbox);
		leftViewPropertiesVbox.getChildren().addAll(viewnameValueHbox,algorithmHBox);


		VBox rightViewPropertiesVbox = new VBox();
		rightViewPropertiesVbox.getStyleClass().add("rightpropertiesVbox");

		HBox updatablehbox = new HBox();
		updatablehbox.getStyleClass().add("updatablehbox");
		CheckBox updatableCheckbox = new CheckBox();
		//updatableCheckbox.setId("partitionedCheckbox");
		Label updatablelabel = new Label("Updatable");
		updatablelabel.setId("createViewTabLabels");
		Label checkOptionLabel = new Label("Check Option : ");
		checkOptionLabel.setId("createViewTabLabels");
		ComboBox checkOptionComboBox = new ComboBox();
		checkOptionComboBox.getStyleClass().add("combobox");
		checkOptionComboBox.getItems().addAll("NONE","LOCAL","CASCADE");
		
//		checkOptionComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//				System.out.println("Old Check Option --> " + oldValue);
//				System.out.println("New Check Option --> " + newValue);
//
//				if (newValue != null && !newValue.isEmpty()) {
//					String existingQuery = generatedSQLQueryTextArea.getText();
//
//					// Remove old ENGINE=oldValue if exists
//					if (oldValue != null && !oldValue.isEmpty()) {
//						existingQuery = existingQuery.replaceAll("WITH " + oldValue+ "CHECK OPTION", "").trim();
//					}
//
//					// Append ENGINE=newValue
//					String updatedQuery = existingQuery + "\n" + "WITH" + newValue+" CHECK OPTION";
//
//					generatedSQLQueryTextArea.setText(updatedQuery.trim());
//				}
//			}
//		});
//		
		checkOptionComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

		        System.out.println("Old Check Option --> " + oldValue);
		        System.out.println("New Check Option --> " + newValue);

		        if (newValue != null && !newValue.isEmpty()) {
		            String existingQuery = generatedSQLQueryTextArea.getText();

		            // Remove any existing CHECK OPTION using loop
		            String[] checkOptions = {"LOCAL", "CASCADE"};
		            for (String option : checkOptions) {
		                existingQuery = existingQuery.replace("WITH " + option + " CHECK OPTION", "").trim();
		            }

		            // If selected value is not NONE, append the new CHECK OPTION
		            if (!newValue.equals("NONE")) {
		                existingQuery += "\nWITH " + newValue + " CHECK OPTION";
		            }

		            generatedSQLQueryTextArea.setText(existingQuery.trim());
		        }
		    }
		});
		
		updatablehbox.getChildren().addAll(updatableCheckbox,updatablelabel,checkOptionLabel,checkOptionComboBox);
		
		HBox definerHBox = new HBox();
		definerHBox.getStyleClass().add("definerHBox");
		Label definerLabel = new Label("Definer: ");
		definerLabel.setId("createViewTabLabels");
		TextField definerTextField = new TextField();
		definerTextField.getStyleClass().add("Valuetextfield");
		definerTextField.setEditable(false);
		definerHBox.getChildren().addAll(definerLabel,definerTextField);

		//rightpropertiesVbox.getChildren().addAll(updatablehbox,checkOptionHbox,definerHBox);
		rightViewPropertiesVbox.getChildren().addAll(updatablehbox,definerHBox);

		viewPropertiesHbox.getChildren().addAll(leftViewPropertiesVbox,rightViewPropertiesVbox);
		//propertiesHbox.getChildren().addAll(leftpropertiesVbox);
			
//		TabPane viewtabletabpane = new TabPane();
//		viewtabletabpane.getStyleClass().add("viewtabletabpane");
//		viewtabletabpane.setSide(Side.LEFT);
//		viewtabletabpane.setRotateGraphic(true);
//
//		Tab columnsTab = new Tab();
//		columnsTab.setClosable(false);
//		Label labelL = new Label("Columns");
//		labelL.setRotate(90);
//		StackPane stp = new StackPane(new Group(labelL));
//		columnsTab.setGraphic(stp);
//		columnsTab = getColumnsTab(columnsTab);
//		
//		Tab descriptionTab = new Tab();
//		descriptionTab.setClosable(false);
//		labelL = new Label("Description");
//		labelL.setRotate(90);
//		stp = new StackPane(new Group(labelL));
//		descriptionTab.setGraphic(stp);
//		descriptionTab = getDescriptionTab(descriptionTab);

		HBox viewQueryHBox = new HBox();
		viewQueryHBox.getStyleClass().add("viewpropertiesHbox");
		Label viewQueryLabel = new Label("View Query : ");
		viewQueryLabel.setId("viewQueryLabel");
		TextArea viewQueryTextArea = new TextArea();
		viewQueryTextArea.setId("viewQueryTextArea");
		viewQueryHBox.getChildren().addAll(viewQueryLabel,viewQueryTextArea);
		
		HBox sqlLabelHBox = new HBox();
		sqlLabelHBox.getStyleClass().add("viewpropertiesHbox");
		Label generatedSQLLabel = new Label("Generated SQL");
		generatedSQLLabel.setId("createViewTabLabels");
		sqlLabelHBox.getChildren().addAll(generatedSQLLabel);
		
		HBox sqlColumnHBox = new HBox();	
		sqlColumnHBox.getStyleClass().add("sqlHBox");
		generatedSQLQueryTextArea = new TextArea();		
		generatedSQLQueryTextArea.getStyleClass().add("generatedSQLQueryTextArea");	
		
		Button sqlQueryButton = new Button("Complete SQL");
		sqlQueryButton.setId("buttons");
		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(generatedSQLQueryTextArea));
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//			));
		//sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
		
		Button executeSqlButton = new Button("Execute SQL");
		executeSqlButton.setId("sqlbutton");
		executeSqlButton.setDisable(true);
				
		Button okButton = new Button("OK");
		okButton.setId("sqlbutton");
		okButton.setDisable(true);
		okButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage.close();
			}		 
		});
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setId("sqlbutton");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage.close();
			}		 
		});
		
		ChangeListener<Object> formValidator = (obs, oldVal, newVal) -> {
			boolean isviewNameValueTextFieldFilled= !viewNameValueTextField.getText().trim().isEmpty();
			boolean isgeneratedSQLQueryTextAreaFilled= !generatedSQLQueryTextArea.getText().trim().isEmpty();
			executeSqlButton.setDisable((!isviewNameValueTextFieldFilled && !isgeneratedSQLQueryTextAreaFilled));
			okButton.setDisable(!isviewNameValueTextFieldFilled);
		};

		// Attach listener to text field and combo box
		generatedSQLQueryTextArea.textProperty().addListener(formValidator);
		viewNameValueTextField.textProperty().addListener(formValidator);		 
		 
		VBox sqlbuttonWrapper = new VBox(sqlQueryButton,executeSqlButton);
		sqlbuttonWrapper.setAlignment(Pos.CENTER);
		sqlbuttonWrapper.setId("buttonWrapper");
		
		VBox buttonWrapper = new VBox(okButton,cancelButton);
		buttonWrapper.setAlignment(Pos.CENTER);
		buttonWrapper.setId("buttonWrapper");
		sqlColumnHBox.getChildren().addAll(generatedSQLQueryTextArea,sqlbuttonWrapper,buttonWrapper);
		
		//viewtabletabpane.getTabs().addAll(columnsTab,descriptionTab);
		newViewTabVBox.getChildren().addAll(viewPropertiesHbox,viewQueryHBox,sqlLabelHBox,sqlColumnHBox);
				
		mysqlui.createNewViewScene = new Scene(newViewTabVBox, 650, 370);
		mysqlui.createNewViewScene.getStylesheets().add(mysqlui.selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("Create New View");
//		stage.setWidth(850);
//		stage.setHeight(630);
		stage.setWidth(800);
		stage.setHeight(400);
		stage.setScene( mysqlui.createNewViewScene);

		stage.show();
	}

//	 public static class ViewTableRow {
//	    	
//	    	private final SimpleIntegerProperty rowNumber;
//	        private final StringProperty columnName = new SimpleStringProperty("");
//	        private final ObjectProperty<String> dataType = new SimpleObjectProperty<>("");
//	        private final BooleanProperty notNull = new SimpleBooleanProperty(false);
//	        private final BooleanProperty autoIncrement = new SimpleBooleanProperty(false);
//	        private final ObjectProperty<String> key = new SimpleObjectProperty<>("");
//	        private final StringProperty defaultValue = new SimpleStringProperty("");
//	        private final StringProperty extra = new SimpleStringProperty("");
//	        private final StringProperty expression = new SimpleStringProperty("");
//	        private final StringProperty comment = new SimpleStringProperty("");
//
//	        public ViewTableRow( String columnNameText,int rowNum,  String dataTypeComboBox, boolean notNullCheckBox,  boolean autoIncrementCheckBox,String keyComboBox,String defaultValueText,
//	        		String extraText, String expressionText,String commentText) {
//	            this.rowNumber = new SimpleIntegerProperty(rowNum);
//	            this.columnName.set(columnNameText);
//	            this.dataType.set(dataTypeComboBox);
//	            this.notNull.set(notNullCheckBox);
//	            this.autoIncrement.set(autoIncrementCheckBox);
//	            this.key.set(keyComboBox);
//	            this.defaultValue.set(defaultValueText);
//	            this.extra.set(extraText);
//	            this.expression.set(expressionText);
//	            this.comment.set(commentText);
//	        }
//	        
//	        public ViewTableRow(int rowNum) {
//	            this.rowNumber = new SimpleIntegerProperty(rowNum);
//	        }
//
//	        public IntegerProperty rowNumberProperty() { return rowNumber; }
//
//	        public StringProperty columnNameProperty() { return columnName; }
//	        public ObjectProperty<String> dataTypeProperty() { return dataType; }
//	        public BooleanProperty notNullProperty() { return notNull; }
//	        public BooleanProperty autoIncrementProperty() { return autoIncrement; }
//	        public ObjectProperty<String> keyProperty() { return key; }
//	        public StringProperty defaultValueProperty() { return defaultValue; }
//	        public StringProperty extraProperty() { return extra; }
//	        public StringProperty expressionProperty() { return expression; }
//	        public StringProperty commentProperty() { return comment; }
//	    }
//	    	
//
//	    private final ObservableList<ViewTableRow> data = FXCollections.observableArrayList();
//	    private final ObservableList<String> dataTypeOptions = FXCollections.observableArrayList("BIGINT","BIGINT UNSIGNED","BINARY","BIT","BLOB","BOOL","CHAR","DATE","DATETIME","DECIMAL","DOUBLE","DOUBLE PRECISION","DOUBLE PRECISION UNSIGNED",
//				 "DOUBLE UNSIGNED","ENUM","FLOAT","GEOMETRY","GEOMETRY COLLECTION","INT","INT UNSIGNED","INTEGER", "INTEGER UNSIGNED","LINESTRING","LONG VARBINARY","LONG VARCHAR","LONG BLOB",
//				 "LONGTEXT","MEDIUMBLOB","MEDIUMINT","MEDIUMINT UNSIGNED","MEDIUMTEXT","MULTILINESTRING","MULTIPOINT","MULTIPOLYGON","NUMERIC","POINT","POLYGON","REAL","SET","SMALLINT","SMALLINT UNSIGNED",
//				 "TEXT","TIME","TIMESTAMP","TINYBLOB","TINYINT","TINYINT UNSIGNED","TINYTEXT","VARBINARY","VARCHAR","YEAR","json","varchar(100)");
//	    private final ObservableList<String> keyOptions = FXCollections.observableArrayList("PRIMARY", "UNIQUE", "FOREIGN", "NONE");
//	    
//	    public ObservableList<ViewTableRow> getData() {
//	        return data;
//	    }
	    
	    
	
//	private Tab getColumnsTab(Tab columnsTab) {
//
//		VBox ColumnsTableVBox = new VBox();
//		//ColumnsTableVBox.setId("ColumnsTableVBox");
//		
//		 TableColumn<ViewTableRow, String> colName = new TableColumn<>("Column Name");
//	        colName.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
//	        colName.setCellFactory(TextFieldTableCell.forTableColumn());
//	        colName.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
//	        colName.setEditable(true);
//
//	        // 2. Row Number #
//	        TableColumn<ViewTableRow, Number> rowNumCol = new TableColumn<>("#");
//	        rowNumCol.setCellValueFactory(cell -> cell.getValue().rowNumberProperty());
//	        rowNumCol.setEditable(false);
//	        rowNumCol.setSortable(false);
//	        
//	        TableColumn<ViewTableRow, String> dataTypeCol = new TableColumn<>("DataType");
//	        dataTypeCol.setCellValueFactory(cell -> cell.getValue().dataTypeProperty());
//	        dataTypeCol.setEditable(false);
////            dataTypeCol.setCellFactory(col -> new TableCell<>() {
////	            
////	            private final ComboBox<String> comboBox;
////	            private final FilteredList<String> filteredItems;
////
////	            {
////	                // Wrap original options with a FilteredList
////	                filteredItems = new FilteredList<>(dataTypeOptions, s -> true);
////
////	                comboBox = new ComboBox<>(filteredItems);
////	                comboBox.setEditable(true);
////
////	                // Handle filtering when user types
////	                comboBox.getEditor().setOnKeyReleased(event -> {
////	                    String input = comboBox.getEditor().getText().toLowerCase();
////
////	                    filteredItems.setPredicate(item ->
////	                        item != null && item.toLowerCase().contains(input)
////	                    );
////
////	                    if (filteredItems.isEmpty()) {
////	                        comboBox.hide();
////	                    } else {
////	                        comboBox.show();
////	                    }
////	                });
////
////	                // Reset filter when dropdown closes
////	                comboBox.setOnHidden(event -> {
////	                    String selected = comboBox.getSelectionModel().getSelectedItem();
////	                    if (selected != null) {
////	                        comboBox.getEditor().setText(selected);
////	                        filteredItems.setPredicate(s -> true);
////	                    }
////	                });
////
////	                // Update model on selection
////	                comboBox.setOnAction(e -> {
////	                    if (getIndex() >= 0 && getIndex() < getTableView().getItems().size()) {
////	                    	ViewTableRow row = getTableView().getItems().get(getIndex());
////	                        row.dataTypeProperty().set(comboBox.getValue());
////	                    }
////	                });
////	            }
////
////	            @Override
////	            protected void updateItem(String item, boolean empty) {
////	                super.updateItem(item, empty);
////	                if (empty) {
////	                    setGraphic(null);
////	                } else {
////	                    comboBox.setValue(item);
////	                    setGraphic(comboBox);
////	                }
////	            }
////	            
////	        });
//	        
//	        // 4. Not Null
//	        TableColumn<ViewTableRow, Boolean> notNullCol = new TableColumn<>("Not Null");
//	        notNullCol.setCellValueFactory(cell -> cell.getValue().notNullProperty());
//	        notNullCol.setCellFactory(CheckBoxTableCell.forTableColumn(notNullCol));
//	        notNullCol.setEditable(false);
//
//	        // 5. Auto Increment
//	        TableColumn<ViewTableRow, Boolean> autoIncCol = new TableColumn<>("Auto Increment");
//	        autoIncCol.setCellValueFactory(cell -> cell.getValue().autoIncrementProperty());
//	        autoIncCol.setCellFactory(CheckBoxTableCell.forTableColumn(autoIncCol));
//	        autoIncCol.setEditable(false);
//
//	        // 6. Key
//	        TableColumn<ViewTableRow, String> keyCol = new TableColumn<>("Key");
//	        keyCol.setCellValueFactory(cell -> cell.getValue().keyProperty());
//	        keyCol.setEditable(false);
////	        keyCol.setCellFactory(col -> {
////	            TableCell<ViewTableRow, String> cell = new TableCell<>() {
////	                private final ComboBox<String> comboBox = new ComboBox<>(keyOptions);
////	                {
////	                    comboBox.setOnAction(e -> {
////	                    	ViewTableRow row = getTableView().getItems().get(getIndex());
////	                        row.keyProperty().set(comboBox.getValue());
////	                    });
////	                }
////	                @Override
////	                protected void updateItem(String item, boolean empty) {
////	                    super.updateItem(item, empty);
////	                    if (empty) {
////	                        setGraphic(null);
////	                    } else {
////	                        comboBox.setValue(item);
////	                        setGraphic(comboBox);
////	                    }
////	                }
////	            };
////	            return cell;
////	        });
//
//	        // 7. Default
//	        TableColumn<ViewTableRow, String> defaultCol = new TableColumn<>("Default");
//	        defaultCol.setCellValueFactory(cell -> cell.getValue().defaultValueProperty());
//	        defaultCol.setCellFactory(TextFieldTableCell.forTableColumn());
//	        defaultCol.setOnEditCommit(e -> e.getRowValue().defaultValueProperty().set(e.getNewValue()));
//	        defaultCol.setEditable(false);
//
//	        // 8. ExtraExpression
//	        TableColumn<ViewTableRow, String> extraCol = new TableColumn<>("Extra");
//	        extraCol.setCellValueFactory(cell -> cell.getValue().extraProperty());
//	        extraCol.setCellFactory(TextFieldTableCell.forTableColumn());
//	        extraCol.setOnEditCommit(e -> e.getRowValue().extraProperty().set(e.getNewValue()));
//	        extraCol.setEditable(false);
//	        
//	        TableColumn<ViewTableRow, String> expCol = new TableColumn<>("Expression");
//	        expCol.setCellValueFactory(cell -> cell.getValue().expressionProperty());
//	        expCol.setCellFactory(TextFieldTableCell.forTableColumn());
//	        expCol.setOnEditCommit(e -> e.getRowValue().expressionProperty().set(e.getNewValue()));
//	        expCol.setEditable(false);
//
//	        // 9. Comment
//	        TableColumn<ViewTableRow, String> commentCol = new TableColumn<>("Comment");
//	        commentCol.setCellValueFactory(cell -> cell.getValue().commentProperty());
//	        commentCol.setCellFactory(TextFieldTableCell.forTableColumn());
//	        commentCol.setOnEditCommit(e -> e.getRowValue().commentProperty().set(e.getNewValue()));
//	        commentCol.setEditable(false);
//	        
//	        TableView<ViewTableRow> Viewtable = new TableView<>();
//	        Viewtable.getStyleClass().add("TableView");
//	        Viewtable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
//	        Viewtable.setEditable(true);
//	        Viewtable.getColumns().addAll(rowNumCol,colName, dataTypeCol,notNullCol, autoIncCol,keyCol,defaultCol,extraCol,expCol,commentCol);
//	        Viewtable.setItems(data);
//		
////		TableView ColumnsTableView = new TableView();	
////		ColumnsTableView.getStyleClass().add("TableView");
//
//		
//		HBox bottomHBox = new HBox();
//		bottomHBox.setId("bottomHBox");
//						
//		HBox generatedSQLLabelHBox = new HBox();
//		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
//		Label generatedSQLColumnLabel = new Label("Generated SQL");
//		generatedSQLColumnLabel.setId("generatedSQLLabel");
//		generatedSQLLabelHBox.getChildren().add(generatedSQLColumnLabel);
//		
//		Region spacer = new Region();
//		HBox.setHgrow(spacer, Priority.ALWAYS);
//		
//		HBox buttons1HBox = new HBox();
//		buttons1HBox.getStyleClass().add("buttons--hbox");
//		buttons1HBox.setAlignment(Pos.BOTTOM_RIGHT);		
//		
//		Button addButton = new Button("Add Row");
//		Button removeButton = new Button("Remove Selected");
//
//		if (data.isEmpty()) {
//            data.add(new ViewTableRow("actor_name", data.size() + 1, "VARCHAR(100)", true, false, "PRIMARY", "N/A", "NONE", "", "Stores actor name"));
//        }
//        
//       // data.add(new TableRow("actor_name", data.size() + 1, "VARCHAR(100)", true, false, "PRIMARY", "N/A", "NONE", "", "Stores actor name"));
////        addButton.setOnAction(e -> {
////        	int newRowNumber = data.size() + 1;
////        	//data.add(new TableRow("col_name",newRowNumber,"Char",true,false,"pri","kl","hj","kl","kl"));
////        	ViewTableRow newRow = new ViewTableRow(newRowNumber);
////        	data.add(newRow);
////        });
//           
//
//        removeButton.setOnAction(e -> {
//        	ViewTableRow selected = Viewtable.getSelectionModel().getSelectedItem();
//            if (selected != null) {
//                data.remove(selected);
//                // Re-number remaining rows
//                for (int i = 0; i < data.size(); i++) {
//                    data.get(i).rowNumberProperty().set(i + 1);
//                }
//            }
//        });
//		
//		Button cancelButton = new Button("Cancel");
//		cancelButton.setId("buttons");
//		Button okButton = new Button("OK");
//		okButton.setId("buttons");
//
//		okButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				stage.close();
//			}		 
//		});
//
//		
//		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				stage.close();
//			}		 
//		});
//		buttons1HBox.getChildren().addAll(removeButton,cancelButton,okButton);	
//		
//		
////		HBox sqlColumnHBox = new HBox();	
////		sqlColumnHBox.getStyleClass().add("sqlHBox");
////		TextArea generatedSQLQueryTextArea = new TextArea("CREATE VIEW ${nameWithSchemaName} AS ...");		
////		generatedSQLQueryTextArea.getStyleClass().add("generatedSQLQueryTextArea");	
////		
////		Button sqlQueryButton = new Button("SQL");
////		sqlQueryButton.setId("buttons");
////		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
////			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
////			));
////		VBox buttonWrapper = new VBox(sqlQueryButton);
////		buttonWrapper.setAlignment(Pos.CENTER);
////		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
////		sqlColumnHBox.getChildren().addAll(generatedSQLQueryTextArea,buttonWrapper);
//		
//		bottomHBox.getChildren().addAll(generatedSQLLabelHBox,spacer,buttons1HBox);
//		//sqlHBox.getChildren().addAll(generatedSQLQueryTextArea,buttons1HBox,buttons2VBox);
//		ColumnsTableVBox.getChildren().addAll(Viewtable,bottomHBox);
//		columnsTab.setContent(ColumnsTableVBox);
//		return columnsTab;
//	}
	
//	private Tab getDescriptionTab(Tab descriptionTab) {
//		
//		VBox descriptionTableVBox = new VBox();
//		//ColumnsTableVBox.setId("ColumnsTableVBox");
//		TableView descriptionTableView = new TableView();	
//		descriptionTableView.getStyleClass().add("TableView");
//		
//		
//		HBox bottomHBox = new HBox();
//		bottomHBox.setId("bottomHBox");
//		
//		HBox generatedSQLLabelHBox = new HBox();
//		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
//		Label generatedSQLDescriptionLabel = new Label("Generated SQL");
//		generatedSQLDescriptionLabel.setId("generatedSQLLabel");
//		generatedSQLLabelHBox.getChildren().add(generatedSQLDescriptionLabel);
//		
//		Region spacer = new Region();
//		HBox.setHgrow(spacer, Priority.ALWAYS);
//		
//		HBox buttons1HBox = new HBox();
//		buttons1HBox.getStyleClass().add("buttons--hbox");
//		buttons1HBox.setAlignment(Pos.BOTTOM_RIGHT);				
//		
//		Button cancelButton = new Button("Cancel");
//		cancelButton.setId("buttons");
//		Button okButton = new Button("OK");
//		okButton.setId("buttons");
//		
//		okButton.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				stage.close();
//			}		 
//		});
//		
//		
//		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				stage.close();
//			}		 
//		});
//		buttons1HBox.getChildren().addAll(cancelButton,okButton);	
//		
//		
////		HBox sqlDescriptionHBox = new HBox();	
////		sqlDescriptionHBox.getStyleClass().add("sqlHBox");
////		TextArea generatedSQLQueryTextArea = new TextArea("Description..");	
////		generatedSQLQueryTextArea.setEditable(false);
////		generatedSQLQueryTextArea.getStyleClass().add("generatedSQLQueryTextArea");	
////		
////		Button sqlQueryButton = new Button("SQL");
////		sqlQueryButton.setId("buttons");
////		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
////			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
////			));
////		VBox buttonWrapper = new VBox(sqlQueryButton);
////		buttonWrapper.setAlignment(Pos.CENTER);
////		//	sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
////		sqlDescriptionHBox.getChildren().addAll(generatedSQLQueryTextArea,buttonWrapper);
//		
//		bottomHBox.getChildren().addAll(generatedSQLLabelHBox,spacer,buttons1HBox);
//		//sqlHBox.getChildren().addAll(generatedSQLQueryTextArea,buttons1HBox,buttons2VBox);
//		descriptionTableVBox.getChildren().addAll(descriptionTableView,bottomHBox);
//		descriptionTab.setContent(descriptionTableVBox);
//		return descriptionTab;
//	}



}


