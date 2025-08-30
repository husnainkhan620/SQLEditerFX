package com.openfx.handlers;

import java.util.ArrayList;
import java.util.List;

import com.openfx.handlers.CreateNewFunctionHandler.FunctionTableRow;
import com.openfx.handlers.CreateNewIndexHandler.CreateNewIndexTableRow;
import com.openfx.handlers.CreateNewTableHandler.ConstraintsTableRow;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateNewConstraintHandler implements EventHandler<ActionEvent> {

	public CreateNewTableHandler createNewTableHandler;
	public Stage stage;
	public MySqlUI mysqlui;
	private TableView newConstraintsColumnsTableView;

	public CreateNewConstraintHandler(CreateNewTableHandler createNewTableHandler,MySqlUI mysqlui) {
		this.createNewTableHandler = createNewTableHandler;
		this.mysqlui = mysqlui;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		 createNewTableHandler.createNewConstraintHandler = this;
		
		 VBox newConstriantTabVBox = new VBox();
		 
		 VBox constriantLabelVbox = new VBox();
		 constriantLabelVbox.getStyleClass().add("constriantLabelVbox");
		 Label constriantLabel = new Label("Create constraint for table \"Table Name\""); 		 
		 constriantLabelVbox.getChildren().addAll(constriantLabel);
		 
		 VBox constriantPropertiesVbox = new VBox();
		 constriantPropertiesVbox.getStyleClass().add("constriantPropertiesVbox");
		 
		 HBox tableNameValueHbox = new HBox();
		 tableNameValueHbox.getStyleClass().add("tableNameValueHbox");
		 Label tableNameLabel = new Label("Table : ");
		 tableNameLabel.setId("labels");
		 TextField tableNamevalueTextField = new TextField("databaseName.tableName");
		 tableNamevalueTextField.getStyleClass().add("Valuetextfield");
		 tableNameValueHbox.getChildren().addAll(tableNameLabel,tableNamevalueTextField);
		 
		 HBox constraintNameValueHbox = new HBox();
		 constraintNameValueHbox.getStyleClass().add("constraintNameValueHbox");
		 Label constraintNameLabel = new Label("Name* : ");
		 constraintNameLabel.setId("labels");
		 TextField constraintValueTextField = new TextField();
		 constraintValueTextField.getStyleClass().add("Valuetextfield");
		 constraintNameValueHbox.getChildren().addAll(constraintNameLabel,constraintValueTextField);
	 
		 HBox constraintTypeHBox = new HBox();
		 constraintTypeHBox.getStyleClass().add("constraintTypeHBox");
		 Label constraintTypeLabel = new Label("Type* : ");
		 constraintTypeLabel.setId("labels");
		 ComboBox<String> constraintTypeComboBox = new ComboBox<>();
		 constraintTypeComboBox.getStyleClass().add("Valuetextfield");
		 constraintTypeComboBox.getItems().addAll("PRIMARY KEY","UNIQUE KEY","CHECK");
		 constraintTypeHBox.getChildren().addAll(constraintTypeLabel,constraintTypeComboBox);
		 
		
		 Label columnLabel = new Label("Column* : ");
		 columnLabel.setId("labels");
		 
//		 TableView newConstraintsTableView = new TableView();
//		 newConstraintsTableView.getStyleClass().add("ConstraintsTableView");
		 
//		 TableColumn<CreateNewConstraintsTableRow, String> columnNameCol = new TableColumn<>("Column");
//		 columnNameCol.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
//		 columnNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		 columnNameCol.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		 
		 TableColumn<CreateNewConstraintsTableRow, Boolean> columnNameCol = new TableColumn<>("Column");
		 columnNameCol.setCellValueFactory(cell -> cell.getValue().selectedProperty());
		 columnNameCol.setPrefWidth(150);
		 columnNameCol.setCellFactory(col -> new TableCell<>() {
		     private final CheckBox checkBox = new CheckBox();

		     {
		         checkBox.setOnAction(e -> {
		             CreateNewConstraintsTableRow row = getTableView().getItems().get(getIndex());
		             row.setSelected(checkBox.isSelected());
		             updateRowNumbers(); // refresh row numbers
		         });
		     }

		     @Override
		     protected void updateItem(Boolean item, boolean empty) {
		         super.updateItem(item, empty);

		         if (empty || getIndex() < 0 || getIndex() >= getTableView().getItems().size()) {
		             setGraphic(null);
		         } else {
		             CreateNewConstraintsTableRow row = getTableView().getItems().get(getIndex());
		             checkBox.setSelected(row.isSelected());
		             checkBox.setText(row.getColumnName() != null ? row.getColumnName() : "");
		             setGraphic(checkBox);
		         }
		     }
		 });
		 
		 TableColumn<CreateNewConstraintsTableRow, Number> rowNumCol = new TableColumn<>("#");
		 rowNumCol.setCellValueFactory(cell -> cell.getValue().rowNumberProperty());
		 rowNumCol.setSortable(false);
		 rowNumCol.setPrefWidth(50);
//		 TableColumn<CreateNewConstraintsTableRow, Number> rowNumCol = new TableColumn<>("#");
//		 rowNumCol.setCellValueFactory(cell -> {
//		     CreateNewConstraintsTableRow row = cell.getValue();
//		     int index = getCheckedRows().indexOf(row);
//		     return new SimpleIntegerProperty(index >= 0 ? index + 1 : null);
//		 });
//		 rowNumCol.setSortable(false);

		 TableColumn<CreateNewConstraintsTableRow, String> colType = new TableColumn<>("Type");
		 colType.setCellValueFactory(cell -> cell.getValue().typeProperty());
		 colType.setCellFactory(TextFieldTableCell.forTableColumn());
		 colType.setOnEditCommit(e -> e.getRowValue().typeProperty().set(e.getNewValue()));	
		 rowNumCol.setPrefWidth(150);
		 
		 newConstraintsColumnsTableView = new TableView<>();
		 newConstraintsColumnsTableView.getStyleClass().add("TableView");
		 newConstraintsColumnsTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		 newConstraintsColumnsTableView.setEditable(true);
		 newConstraintsColumnsTableView.getColumns().addAll(columnNameCol,rowNumCol,colType);
		 newConstraintsColumnsTableView.setItems(CreateNewConstraintsData);
		 newConstraintsColumnsTableView.getItems().clear();
		 
		 
		 Label checkExpressionLabel = new Label("Expression:");
		 columnLabel.setId("labels");
		 checkExpressionLabel.setVisible(false); // initially hidden
		 checkExpressionLabel.setManaged(false);
		 
		 HBox checkExpressionHBox = new HBox();
		 TextField checkExpressionField = new TextField();
		 checkExpressionField.setId("checkExpressionField");
		 checkExpressionHBox.getChildren().addAll(checkExpressionField);
		 checkExpressionHBox.setVisible(false); // initially hidden
		 checkExpressionHBox.setManaged(false);
		 	 
		 constraintTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
			    if ("CHECK".equals(newVal)) {
			        // Show expression input, hide table
			        newConstraintsColumnsTableView.setVisible(false);
			        newConstraintsColumnsTableView.setManaged(false);
			        columnLabel.setVisible(false);
			        columnLabel.setManaged(false);
			        checkExpressionHBox.setVisible(true);
			        checkExpressionHBox.setManaged(true);
			        checkExpressionLabel.setVisible(true);
			        checkExpressionLabel.setManaged(true);
			    } else {
			        // Show table, hide expression input
			        newConstraintsColumnsTableView.setVisible(true);
			        newConstraintsColumnsTableView.setManaged(true);
			        columnLabel.setVisible(true);
			        columnLabel.setManaged(true);
			        checkExpressionHBox.setVisible(false);
			        checkExpressionHBox.setManaged(false);
			        checkExpressionLabel.setVisible(false);
			        checkExpressionLabel.setManaged(false);
			    }
			});
		 
		 
		 Button selectAllButton = new Button("Select All");
		 selectAllButton.setId("buttons");
		 selectAllButton.setOnAction(e -> {
			 boolean anySelected = CreateNewConstraintsData.stream().anyMatch(CreateNewConstraintsTableRow::isSelected);
		

		    if (anySelected) {
			    for (CreateNewConstraintsTableRow row : CreateNewConstraintsData) {
			        row.setSelected(false);
			    } 
			    selectAllButton.setText("Select All");
		    } else {
		        // Select all rows
		        for (CreateNewConstraintsTableRow row : CreateNewConstraintsData) {
		            row.setSelected(true);
		        }
		        selectAllButton.setText("Clear");
		    }
			    newConstraintsColumnsTableView.refresh(); // update the checkboxes visually
			});
		  
				 
		 HBox buttonsHBox = new HBox();
		 buttonsHBox.getStyleClass().add("buttons--hbox");
		 Button okButton = new Button("OK");
		 okButton.setId("buttons");
		 okButton.setDisable(true);
		 
		 //int constraintRowNumber =  createNewTableHandler.getData().size() + 1;
//		 okButton.setOnAction(e -> {
//
//			 ConstraintsTableRow newRow = new ConstraintsTableRow(
//			    	constraintValueTextField.getText(),
//			    	tableNamevalueTextField.getText(),
//			    	tableNamevalueTextField.getText(),
//			      	constraintTypeComboBox.getValue(),
//			    	constraintTypeComboBox.getValue()
//			    );
//
//			    createNewTableHandler.getconstraintsData().add(newRow);
//			    stage.close();
//			});
		 
		 if (CreateNewConstraintsData.isEmpty()) {
			    CreateNewConstraintsData.add(new CreateNewConstraintsTableRow("column_name_1", 1, "VARCHAR(100)"));
			    CreateNewConstraintsData.add(new CreateNewConstraintsTableRow("column_name_2",2, "BIGINT"));
			    CreateNewConstraintsData.add(new CreateNewConstraintsTableRow("column_name_3", 3, "CHAR"));			  
		 }
		 
		 okButton.setOnAction(e -> {
			    String constraintName = constraintValueTextField.getText();
			    String columnName= "";
			    String OwnerName = tableNamevalueTextField.getText();
			    String constraintType = constraintTypeComboBox.getValue();
			    
			    String type = constraintTypeComboBox.getValue();
			    String checkExpression = "";

			    if ("CHECK".equals(constraintType)) {
			        checkExpression = checkExpressionField.getText(); // from expression TextField
			    } else {
			         type = constraintType; // "PRIMARY KEY" or "UNIQUE KEY"
			    	
			    	 List<String> selectedColumns = new ArrayList<>();
			         for (CreateNewConstraintsTableRow row : CreateNewConstraintsData) {
			             if (row.isSelected()) {
			                 selectedColumns.add(row.getColumnName());
			             }
			         }

			         // 🔗 Join names with commas
			         columnName = String.join("\n", selectedColumns);
			    }

			    ConstraintsTableRow newRow = new ConstraintsTableRow(
			        
			    	constraintName,  // column name
			    	columnName,         // data type (or table name, based on your model)
			        OwnerName,
			        type,              // type column (only if not CHECK)
			        checkExpression    // check expression column (if CHECK)
			    );
			   
			   		    
			    createNewTableHandler.getconstraintsData().add(newRow);
			    stage.close();
			});
		 
		 ChangeListener<Object> constraintFormValidator = (obs, oldVal, newVal) -> {
			    boolean isConstraintSelected = constraintTypeComboBox.getValue() != null 
			                                   && !constraintTypeComboBox.getValue().trim().isEmpty();
			    boolean isValid = true;

			    if ("CHECK".equals(constraintTypeComboBox.getValue())) {
			        // Validate CHECK → must have expression filled
			        boolean isExpressionFilled = !checkExpressionField.getText().trim().isEmpty(); 
			        isValid = isConstraintSelected && isExpressionFilled;
			        
			    } else {
			        // For other constraints → must have at least one column selected
			        boolean anyColumnSelected = CreateNewConstraintsData.stream()
			                                        .anyMatch(CreateNewConstraintsTableRow::isSelected);
			        isValid = isConstraintSelected && anyColumnSelected;
			       
			    }

			    okButton.setDisable(!isValid);
			};

			// Attach listeners			
			//constraintTypeComboBox.valueProperty().addListener(constraintFormValidator);
			CreateNewConstraintsData.forEach(row -> 
			    row.selectedProperty().addListener(constraintFormValidator) // Assuming row has BooleanProperty
			);
		 
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
		 
				
		 
		 constriantPropertiesVbox.getChildren().addAll(tableNameValueHbox,constraintNameValueHbox,constraintTypeHBox,columnLabel,newConstraintsColumnsTableView,checkExpressionLabel,checkExpressionHBox,selectAllButton,buttonsHBox);
		 
		 newConstriantTabVBox.getChildren().addAll(constriantLabelVbox,constriantPropertiesVbox);
		 createNewTableHandler.createNewConstraintScene = new Scene(newConstriantTabVBox, 500, 550);
		 createNewTableHandler.createNewConstraintScene.getStylesheets().add(createNewTableHandler.selectedTheme);
		 stage = new Stage();
		 stage.initModality(Modality.APPLICATION_MODAL);
		// stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		 stage.setTitle("Create New Constraint");
		 stage.setWidth(525);
		 stage.setHeight(530);
		 stage.setScene( createNewTableHandler.createNewConstraintScene);
		      
		 stage.show();
	}
     
	private ObservableList<CreateNewConstraintsTableRow> getCheckedRows() {
	    return CreateNewConstraintsData.filtered(CreateNewConstraintsTableRow::isSelected);
	}

	private void updateRowNumbers() {
	    newConstraintsColumnsTableView.refresh();
	}
	
	public static class CreateNewConstraintsTableRow {

		private final StringProperty columnName = new SimpleStringProperty("");
		private final StringProperty type = new SimpleStringProperty("");
		private final SimpleIntegerProperty rowNumber;	
	    private final BooleanProperty selected = new SimpleBooleanProperty(false);


		public CreateNewConstraintsTableRow( String columnNameText,int rowNum,  String type) {
			this.columnName.set(columnNameText);
			this.rowNumber = new SimpleIntegerProperty();
			this.type.set(type);
		}      


		public CreateNewConstraintsTableRow(int rowNum) {
			//this.rowNumber = new SimpleIntegerProperty(rowNum);
			this.columnName.set(""); // default
			this.type.set(""); // default
			this.rowNumber = new SimpleIntegerProperty(rowNum);
		}

				
		public StringProperty columnNameProperty() { return columnName; }
		public String getColumnName() { return columnName.get(); }
		public void setColumnName(String value) { columnName.set(value); }
		    
		public IntegerProperty rowNumberProperty() { return rowNumber; }
		public StringProperty typeProperty() { return type; }
		
		public BooleanProperty selectedProperty() { return selected; }
		public boolean isSelected() { return selected.get(); }
		public void setSelected(boolean value) { selected.set(value); }
		
	}

	private final ObservableList<CreateNewConstraintsTableRow> CreateNewConstraintsData = FXCollections.observableArrayList();

	public ObservableList<CreateNewConstraintsTableRow> getCreateNewConstraintsData() {
		return CreateNewConstraintsData;
	}

}
