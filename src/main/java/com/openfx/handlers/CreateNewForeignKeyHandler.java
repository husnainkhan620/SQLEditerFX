package com.openfx.handlers;

import java.util.ArrayList;
import java.util.List;

import com.openfx.handlers.CreateNewConstraintHandler.CreateNewConstraintsTableRow;
import com.openfx.handlers.CreateNewIndexHandler.CreateNewIndexTableRow;
import com.openfx.handlers.CreateNewTableHandler.ConstraintsTableRow;
import com.openfx.handlers.CreateNewTableHandler.ForeignKeyRow;
import com.openfx.handlers.CreateNewTableHandler.TableRow;
import com.openfx.ui.MySqlUI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateNewForeignKeyHandler implements EventHandler<ActionEvent> {

	public CreateNewTableHandler createNewTableHandler;
	public Stage stage;
	public NewColumnOptionsHandler newColumnOptionsHandler;
	public Scene newColumnOptionsScene;
	public MySqlUI mysqlui;
	public TextField foreignKeyvalueTextField;
	public TextField databaseValueTextField ;
	public ComboBox<String> uniqueKeyComboBox;
	public ComboBox<String> onDeleteComboBox ;
	public ComboBox<String> onUpdateComboBox ;
	
	public String whiteThemeCss = CreateNewForeignKeyHandler.class.getResource("/whiteTheme.css").toExternalForm();
	public String darkThemeCss = CreateNewForeignKeyHandler.class.getResource("/darkTheme.css").toExternalForm();
	public String selectedTheme= whiteThemeCss;

	public CreateNewForeignKeyHandler(CreateNewTableHandler createNewTableHandler,MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.createNewTableHandler = createNewTableHandler;
		this.mysqlui = mysqlui;
	}

	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		createNewTableHandler.createNewForeignKeyHandler = this;
		
		 VBox newForeignKeyTabVBox = new VBox();
		 
		 VBox foreignKeyLabelVbox = new VBox();
		 foreignKeyLabelVbox.getStyleClass().add("foreignKeyLabelVbox");
		 Label foreignKeyLabel = new Label("Create foreign key for table \"Table Name\""); 		 
		 foreignKeyLabelVbox.getChildren().addAll(foreignKeyLabel);
		 
		 VBox foreignKeyPropertiesVbox = new VBox();
		 foreignKeyPropertiesVbox.getStyleClass().add("foreignKeyPropertiesVbox");
		 
		 HBox foreignKeyNameValueHbox = new HBox();
		 foreignKeyNameValueHbox.getStyleClass().add("tableNameValueHbox");
		 Label foreignKeyNameLabel = new Label("Table : ");
		 foreignKeyNameLabel.setId("labels");
		 foreignKeyvalueTextField = new TextField();
		 foreignKeyvalueTextField.getStyleClass().add("Valuetextfield");
		 foreignKeyNameValueHbox.getChildren().addAll(foreignKeyNameLabel,foreignKeyvalueTextField);
		 
		 HBox databaseNameValueHbox = new HBox();
		 databaseNameValueHbox.getStyleClass().add("constraintNameValueHbox");
		 Label databaseNameLabel = new Label("Database : ");
		 databaseNameLabel.setId("labels");
		 databaseValueTextField = new TextField();
		 databaseValueTextField.getStyleClass().add("Valuetextfield");
		 databaseNameValueHbox.getChildren().addAll(databaseNameLabel,databaseValueTextField);
		 
		 Label referenceTabelLabel = new Label("Reference Table : ");
		 referenceTabelLabel.setId("createForeignKeyLabels");
//		 
//		 TableView referenceTableView = new TableView();
//		 referenceTableView.getStyleClass().add("referenceTableView");
		 
		 ObservableList<String> data = FXCollections.observableArrayList(
				 "default names", "city", "address", "film", "category", "pincode", "age"
				 );
		 
		 // Filtered list wrapper
		 FilteredList<String> filteredData = new FilteredList<>(data, s -> true);

		 VBox referenceTableVbox = new VBox();
		 // TextField for live filtering
		 TextField filterField = new TextField();
		 filterField.setPromptText("Type to filter...");
		 filterField.textProperty().addListener((obs, oldVal, newVal) -> {
			 String filter = newVal.toLowerCase();
			 filteredData.setPredicate(item -> item.toLowerCase().contains(filter));
		 });

		 // TableView setup (single column auto-generated)
		 TableView<String> referenceTableView = new TableView<>();
		 referenceTableView.getStyleClass().add("referenceTableView");
		 referenceTableView.setItems(filteredData);
		 referenceTableView.setEditable(false);

		 // Only one auto-generated column showing the string
		 TableColumn<String, String> valueColumn = new TableColumn<>();
		 valueColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));
		 valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		 valueColumn.setPrefWidth(200);
		 referenceTableView.getColumns().add(valueColumn);
		 referenceTableVbox.getChildren().addAll(filterField,referenceTableView);
		 
//		 HBox uniqueKeyHBox = new HBox();
//		 uniqueKeyHBox.getStyleClass().add("uniqueKeyHBox");
//		 Label UniqueKeyLabel = new Label("Unique Key : ");
//		 uniqueKeyComboBox = new ComboBox<>();
//		 uniqueKeyComboBox.getStyleClass().add("Valuetextfield");
//		 uniqueKeyComboBox.getItems().addAll("PRIMARY KEY");
//		 uniqueKeyHBox.getChildren().addAll(UniqueKeyLabel,uniqueKeyComboBox);
		 
		 
		 Label columnLabel = new Label("Column : ");
		 columnLabel.setId("labels");
		 
		 TableView columnsTableView = new TableView();		 
		 columnsTableView.getStyleClass().add("columnsTableView");
		 
		 TableColumn<CreateNewForeignKeyTableRow, String> columnNameCol = new TableColumn<>("Column");
		 columnNameCol.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
//		 columnNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		 columnNameCol.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		 columnNameCol.setPrefWidth(150);
		 columnNameCol.setCellFactory(col -> new TableCell<>() {
	            
	            private final ComboBox<String> comboBox;
	            private final FilteredList<String> filteredItems;

	            {
	                // Wrap original options with a FilteredList
	                filteredItems = new FilteredList<>(columnsNames, s -> true);

	                comboBox = new ComboBox<>(filteredItems);
	                comboBox.setEditable(true);

	                // Handle filtering when user types
	                comboBox.getEditor().setOnKeyReleased(event -> {
	                    String input = comboBox.getEditor().getText().toLowerCase();

	                    filteredItems.setPredicate(item ->
	                        item != null && item.toLowerCase().contains(input)
	                    );

	                    if (filteredItems.isEmpty()) {
	                        comboBox.hide();
	                    } else {
	                        comboBox.show();
	                    }
	                });

	                // Reset filter when dropdown closes
	                comboBox.setOnHidden(event -> {
	                    String selected = comboBox.getSelectionModel().getSelectedItem();
	                    if (selected != null) {
	                        comboBox.getEditor().setText(selected);
	                        filteredItems.setPredicate(s -> true);
	                    }
	                });

	                // Update model on selection
	                comboBox.setOnAction(e -> {
	                    if (getIndex() >= 0 && getIndex() < getTableView().getItems().size()) {
	                    	CreateNewForeignKeyTableRow row = getTableView().getItems().get(getIndex());
	                        row.columnNameProperty().set(comboBox.getValue());
	                    }
	                });
	            }

	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty) {
	                    setGraphic(null);
	                } else {
	                    comboBox.setValue(item);
	                    setGraphic(comboBox);
	                }
	            }
	            
	        });
		 
		 TableColumn<CreateNewForeignKeyTableRow, String> colType = new TableColumn<>("Column Type");
		 colType.setCellValueFactory(cell -> cell.getValue().columntypeProperty());
		 colType.setCellFactory(TextFieldTableCell.forTableColumn());
		 colType.setOnEditCommit(e -> e.getRowValue().columntypeProperty().set(e.getNewValue()));
		 colType.setPrefWidth(150);
		 
		 TableColumn<CreateNewForeignKeyTableRow, String> refCol = new TableColumn<>("Reference Column");
		 refCol.setCellValueFactory(cell -> cell.getValue().referenceColumnNameProperty());
		 refCol.setCellFactory(TextFieldTableCell.forTableColumn());
		 refCol.setOnEditCommit(e -> e.getRowValue().referenceColumnNameProperty().set(e.getNewValue()));
		 refCol.setPrefWidth(150);
		 
		 TableColumn<CreateNewForeignKeyTableRow, String> refColtype = new TableColumn<>("Reference Column Type");
		 refColtype.setCellValueFactory(cell -> cell.getValue().referenceColumnTypeProperty());
		 refColtype.setCellFactory(TextFieldTableCell.forTableColumn());
		 refColtype.setOnEditCommit(e -> e.getRowValue().referenceColumnTypeProperty().set(e.getNewValue()));
		 refColtype.setPrefWidth(150);
		 
		 TableColumn<CreateNewForeignKeyTableRow, String> onUpdateCol = new TableColumn<>("onUpdate");
		 onUpdateCol.setCellValueFactory(cell -> cell.getValue().onUpdateProperty());
		 onUpdateCol.setPrefWidth(100);
		 onUpdateCol.setCellFactory(col -> {
	            TableCell<CreateNewForeignKeyTableRow, String> cell = new TableCell<>() {
	                private final ComboBox<String> comboBox = new ComboBox<>(onUpdateValues);
	                {
	                    	comboBox.setOnAction(e -> {
	                    		CreateNewForeignKeyTableRow row = getTableView().getItems().get(getIndex());
	                            row.onUpdateProperty().set(comboBox.getValue());
	                    });
	                }
	                @Override
//	                protected void updateItem(String item, boolean empty) {
//	                    super.updateItem(item, empty);
//	                    if (empty) {
//	                        setGraphic(null);
//	                    } else {
//	                        // If nothing selected, show "NONE"
//	                        if (item == null || item.isEmpty()) {
//	                            comboBox.setValue("N0 ");
//	                        } else {
//	                            comboBox.setValue(item);
//	                        }
//	                        setGraphic(comboBox);
//	                    }
//	                }
	                protected void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (empty) {
	                    	setGraphic(null);
	                    } else {
	                        comboBox.setValue(item);
	                        setGraphic(comboBox);
	                    }
	                }
	            };
	            return cell;
	        });
		 
		 
		 TableColumn<CreateNewForeignKeyTableRow, String> onDeleteCol = new TableColumn<>("onDelete");
		 onDeleteCol.setCellValueFactory(cell -> cell.getValue().onDeleteProperty());
		 onDeleteCol.setPrefWidth(100);
		 onDeleteCol.setCellFactory(col -> {
	            TableCell<CreateNewForeignKeyTableRow, String> cell = new TableCell<>() {
	                private final ComboBox<String> comboBox = new ComboBox<>(onDeleteValues);
	                {
	                    	    comboBox.setOnAction(e -> {
	                    		CreateNewForeignKeyTableRow row = getTableView().getItems().get(getIndex());
	                            row.onDeleteProperty().set(comboBox.getValue());
	                    });
	                }
	                @Override
//	                protected void updateItem(String item, boolean empty) {
//	                    super.updateItem(item, empty);
//	                    if (empty) {
//	                        setGraphic(null);
//	                    } else {
//	                        // If nothing selected, show "NONE"
//	                        if (item == null || item.isEmpty()) {
//	                            comboBox.setValue("NONE");
//	                        } else {
//	                            comboBox.setValue(item);
//	                        }
//	                        setGraphic(comboBox);
//	                    }
//	                }
	                protected void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (empty) {
	                    	 comboBox.setValue("No Action");
	                    	setGraphic(null);
	                    } else {
	                        comboBox.setValue(item);
	                        setGraphic(comboBox);
	                    }
	                }
	            };
	            return cell;
	        });
		 
		 columnsTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		 columnsTableView.setEditable(true);
		 columnsTableView.getColumns().addAll(columnNameCol,colType,refCol,refColtype,onDeleteCol,onUpdateCol);
		 columnsTableView.setItems(CreateNewForeignKeyData);
		 columnsTableView.getItems().clear();
		 
		 if (CreateNewForeignKeyData.isEmpty()) {
			 CreateNewForeignKeyData.add(new CreateNewForeignKeyTableRow("column_name_1", "int", " address_id","VARCHAR(100)","No Action","No Action"));
			 CreateNewForeignKeyData.add(new CreateNewForeignKeyTableRow("column_name_2", "int", " address_id", "BIGINT","No Action","No Action"));
			 CreateNewForeignKeyData.add(new CreateNewForeignKeyTableRow("column_name_3", "int", " address_id", "CHAR","No Action","No Action"));			  
		 }
		 
		 
		 Region spacer1 = new Region();
		 HBox.setHgrow(spacer1, Priority.ALWAYS);
		 
//		 Region spacer2 = new Region();
//		 HBox.setHgrow(spacer2, Priority.ALWAYS);
		 
		 HBox optionsHBox = new HBox();
		 optionsHBox.getStyleClass().add("optionsHBox");
//		 Label onDeleteLabel = new Label("On Delete : ");
//		 onDeleteLabel.setId("labels");
//		 onDeleteComboBox = new ComboBox<>();
//		 //onDeletedropdown.getStyleClass().add("Valuetextfield");
//		 onDeleteComboBox.setPromptText("No Action");
//		 onDeleteComboBox.getItems().addAll("No Action","Cascade","Restrict","Set NULL","Set Default");
//		 
//		 Label onUpdateLabel = new Label("On Update : ");
//		 onUpdateLabel.setId("labels");
//		 onUpdateComboBox = new ComboBox<>();
//		 //onUpdatedropdown.getStyleClass().add("Valuetextfield");
//		 onUpdateComboBox.setPromptText("No Action");
//		 onUpdateComboBox.getItems().addAll("No Action","Cascade","Restrict","Set NULL","Set Default");
		 
		
//		 Button columnOptionsButton = new Button("Column Options");
//		 columnOptionsButton.setId("buttons");
//		 columnOptionsButton.setOnAction(new NewColumnOptionsHandler(this, mysqlui));
		// optionsHBox.getChildren().addAll(onDeleteLabel,onDeleteComboBox,spacer1,onUpdateLabel,onUpdateComboBox,spacer2);		 
		
		 HBox buttonsHBox = new HBox();
		 buttonsHBox.getStyleClass().add("buttons--hbox");
		 Button okButton = new Button("OK");
		 okButton.setId("buttons");

		 
		 okButton.setOnAction(e -> {
			 String tableName =  foreignKeyvalueTextField.getText();
			 String databaseName = databaseValueTextField.getText();
			 String selectedValue = referenceTableView.getSelectionModel().getSelectedItem();
			 String owner = foreignKeyvalueTextField.getText();
			 String type ="FK";
			 String refTableName = referenceTableView.getSelectionModel().getSelectedItem();
			 String fkName = "fk_" + refTableName;
			 String columnsName = null;
			 String refColName = null;
			   

			    // Get selected column name from columnsTableView
			    CreateNewForeignKeyTableRow selectedRow = 
			        (CreateNewForeignKeyTableRow) columnsTableView.getSelectionModel().getSelectedItem();
			    
			    String onDelete = selectedRow.getonDelete();   // ✅ get directly from row
			     String onUpdate = selectedRow.getonUpdate(); 
			    
			    if (refTableName != null && selectedRow != null && selectedRow.getColumnName() != null) {
			        // Build foreign key name
			        fkName += "_" + selectedRow.getColumnName();

			        System.out.println("Generated Foreign Key Name: " + fkName);

			       
			    } else {
			        System.out.println("Please select both a reference table and a column.");
			    }
			
			   
			   CreateNewForeignKeyTableRow selectedRow1 = 
				        (CreateNewForeignKeyTableRow) columnsTableView.getSelectionModel().getSelectedItem();

				    if (selectedRow1 != null) {
				        // Get the values from the selected row
				        columnsName = (String) selectedRow1.getColumnName();
				        String columnType = selectedRow1.getcolumntype();
				        refColName = selectedRow1.getreferenceColumnName();
				        String refColType = selectedRow1.getreferenceColumnType();
				        

				        // Add to another table (replace targetTableView with your actual table)
//				        targetTableView.getItems().add(
//				            new AnotherTableRow(colName, colType, refColName, refColType)
//				        );

				      //  System.out.println("Added row to target table: " + colName);
				    } else {
				        System.out.println("No row selected in columnsTableView.");
				    }
			   
			    ForeignKeyRow newRow = new ForeignKeyRow(
			        
			    		fkName, 
			    		columnsName,         
			    		owner,
			    		selectedValue,             
			    		type,
			    		refColName,
			    		onDelete,
			    		onUpdate
			    		
			    );
			   
			   		    
			    createNewTableHandler.getForeignKeysData().add(newRow);
			    stage.close();
			});
		 
		
		 
		 Button cancelButton = new Button("Cancel");
		 cancelButton.setId("buttons");		 
		 cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					stage.close();
				}
			});
			 
		 buttonsHBox.getChildren().addAll(okButton,cancelButton);
		 
				
		 
		 foreignKeyPropertiesVbox.getChildren().addAll(foreignKeyNameValueHbox,databaseNameValueHbox,referenceTabelLabel,referenceTableVbox,columnLabel,columnsTableView,optionsHBox,buttonsHBox);
		 
		 newForeignKeyTabVBox.getChildren().addAll(foreignKeyLabelVbox,foreignKeyPropertiesVbox);
		
		createNewTableHandler.createNewForeignKeyScene = new Scene(newForeignKeyTabVBox, 500, 550);
		 createNewTableHandler.createNewForeignKeyScene.getStylesheets().add(createNewTableHandler.selectedTheme);
		 stage = new Stage();
		 stage.initModality(Modality.APPLICATION_MODAL);
		// stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		 stage.setTitle("Create New Foreign Key");
		 stage.setWidth(600);
		 stage.setHeight(650);
		 stage.setScene( createNewTableHandler.createNewForeignKeyScene);
		      
		 stage.show();
	}

	public static class CreateNewForeignKeyTableRow {

		private final ObjectProperty<String> columnName = new SimpleObjectProperty("");
		private final StringProperty columntype = new SimpleStringProperty("");
		private final StringProperty referenceColumnName = new SimpleStringProperty("");
		private final StringProperty referenceColumnType = new SimpleStringProperty("");
		private final ObjectProperty<String> onUpdate = new SimpleObjectProperty<>("");
		private final ObjectProperty<String> onDelete = new SimpleObjectProperty<>("");
		

		public CreateNewForeignKeyTableRow( String columnNameText,String  columntypeText, String referenceColumnNameText, String referenceColumnTypeText, String onUpdateComboBox, String onDeleteComboBox ) {
			this.columnName.set(columnNameText);
			this.columntype.set(columntypeText);
			this.referenceColumnName.set(referenceColumnNameText);
			this.referenceColumnType.set(referenceColumnTypeText);		
			this.onUpdate.set(onUpdateComboBox);
			this.onDelete.set(onDeleteComboBox);
		}      
		
				
		public CreateNewForeignKeyTableRow(String columnNameText) {
			// TODO Auto-generated constructor stub
			this.columnName.set(columnNameText);
		}


		public ObjectProperty<String> columnNameProperty() { return columnName; }
		public Object getColumnName() { return columnName.get(); }
		public void setColumnName(String value) { columnName.set(value); }
		
		public StringProperty columntypeProperty() { return columntype; }
		public String getcolumntype() { return columntype.get(); }
		public void setcolumntype(String value) { columntype.set(value); }
		
		public StringProperty referenceColumnNameProperty() { return referenceColumnName; }
		public String getreferenceColumnName() { return referenceColumnName.get(); }
		public void setreferenceColumnName(String value) { referenceColumnName.set(value); }
		
		public StringProperty referenceColumnTypeProperty() { return referenceColumnType; }
		public String getreferenceColumnType() { return referenceColumnType.get(); }
		public void setreferenceColumnType(String value) { referenceColumnType.set(value); }
		
		public ObjectProperty<String> onUpdateProperty() { return onUpdate; }
		public String getonUpdate() { return onUpdate.get(); }
		public void setonUpdate(String value) { onUpdate.set(value); }
		
		public ObjectProperty<String> onDeleteProperty() { return onDelete; }
		public String getonDelete() { return onDelete.get(); }
		public void setonDelete(String value) { onDelete.set(value); }
		
	
	}

	private final ObservableList<CreateNewForeignKeyTableRow> CreateNewForeignKeyData = FXCollections.observableArrayList();
	
	private final ObservableList<String> columnsNames = FXCollections.observableArrayList("PRIMARY", "UNIQUE", "FOREIGN", "NONE");
	private final ObservableList<String> onUpdateValues = FXCollections.observableArrayList("No Action", "Cascade", "Restrict", "Set Null","Set Default");
	private final ObservableList<String> onDeleteValues = FXCollections.observableArrayList("No Action", "Cascade", "Restrict", "Set Null","Set Default");
	
	public ObservableList<CreateNewForeignKeyTableRow> getCreateNewForeignKeyData() {
		return CreateNewForeignKeyData;
	}
	
}
