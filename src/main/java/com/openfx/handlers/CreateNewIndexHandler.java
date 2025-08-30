package com.openfx.handlers;

import java.util.ArrayList;
import java.util.List;

import com.openfx.handlers.CreateNewConstraintHandler.CreateNewConstraintsTableRow;
import com.openfx.handlers.CreateNewTableHandler.IndexTableRow;
import com.openfx.handlers.CreateNewTableHandler.TableRow;
import com.openfx.handlers.CreateNewTableHandler.TriggersTableRow;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateNewIndexHandler implements EventHandler<ActionEvent> {

	public CreateNewTableHandler createNewTableHandler;
	public Stage stage;
	public MySqlUI mysqlui;
    public TextField tableNamevalueField;
    private TableView newIndexTableView;
    public ComboBox<String> indexTypedropdown ;
    public CheckBox uniqueCheckBox;
    public String tableName ;
    public boolean hasAscending;
    public String indexName;
    public String indexType;
    public boolean unique;
    public TextArea generatedSQLQueryTextArea;
    public String columnsForDisplay;
    public Button executeSqlQueryButton;
    
	public CreateNewIndexHandler(CreateNewTableHandler createNewTableHandler,MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.createNewTableHandler = createNewTableHandler;
		this.mysqlui = mysqlui;
	}
//
//	public CreateNewIndexHandler(MySqlUI mySqlUI) {
//		// TODO Auto-generated constructor stub
//		this.mySqlUI = mySqlUI;
//	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		createNewTableHandler.createNewIndexHandler = this;
		VBox newIndexTabVBox = new VBox();
		
		VBox indexLabelVbox = new VBox();
		indexLabelVbox.getStyleClass().add("indexLabelVbox");
		Label indexLabel = new Label("Create constraint for table \"Table Name\""); 		 
		indexLabelVbox.getChildren().addAll(indexLabel);

		VBox indexPropertiesVbox = new VBox();
		indexPropertiesVbox.getStyleClass().add("indexPropertiesVbox");

		HBox tableNameValueHbox = new HBox();
		tableNameValueHbox.getStyleClass().add("tableNameValueHbox");
		Label tableNameLabel = new Label("Table : ");
		tableNameLabel.setId("labels");
		tableNamevalueField = new TextField();
		tableNamevalueField.getStyleClass().add("Valuetextfield");
		tableNamevalueField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		    	updateIndexSQL();
		    }
		});

		// FOCUS LOST LISTENER for eventNameValueTextField
		tableNamevalueField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		    	updateIndexSQL();
		    }
		});	
		tableNameValueHbox.getChildren().addAll(tableNameLabel,tableNamevalueField);


		HBox indexTypeHBox = new HBox();
		indexTypeHBox.getStyleClass().add("indexTypeHBox");
		Label indexTypeLabel = new Label("Type : ");
		indexTypeLabel.setId("labels");
		indexTypedropdown = new ComboBox<>();
		indexTypedropdown.getStyleClass().add("Valuetextfield");
		indexTypedropdown.getItems().addAll("BTree","Full Text","Hash","RTree");
		indexTypedropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateIndexSQL();
	    });

	    // Alternatively, using setOnAction (also works for selection change)
		indexTypedropdown.setOnAction(selectionEvent -> {
			updateIndexSQL();
	    });

	    // Existing focus lost listener (keep if needed)
		indexTypedropdown.focusedProperty().addListener((observable, oldValue, newValue) -> {
	    	if (!newValue) { // focus lost
	    		updateIndexSQL();
	    	}
	    });
		indexTypeHBox.getChildren().addAll(indexTypeLabel,indexTypedropdown);

		HBox uniqueNameValueHbox = new HBox();
		uniqueNameValueHbox.getStyleClass().add("UniqueNameValueHbox");
		Label UniqueNameLabel = new Label("Unique : ");
		UniqueNameLabel.setId("labels");
		uniqueCheckBox= new CheckBox();
		uniqueCheckBox.getStyleClass().add("Valuetextfield");
		uniqueCheckBox.setOnAction(e ->{
			updateIndexSQL();
		});
		uniqueNameValueHbox.getChildren().addAll(UniqueNameLabel,uniqueCheckBox);


		Label columnLabel = new Label("Column* : ");
		columnLabel.setId("labels");

		newIndexTableView = new TableView();
		newIndexTableView.getStyleClass().add("IndexTableView");

		TableColumn<CreateNewIndexTableRow, Boolean> columnName = new TableColumn<>("Column");
//		columnName.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
//		columnName.setCellFactory(TextFieldTableCell.forTableColumn());
//		columnName.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		columnName.setPrefWidth(150);
		
		columnName.setCellValueFactory(cell -> cell.getValue().selectedProperty());

		columnName.setCellFactory(col -> new TableCell<>() {
		     private final CheckBox checkBox = new CheckBox();

		     {
		         checkBox.setOnAction(e -> {
		        	 CreateNewIndexTableRow row = getTableView().getItems().get(getIndex());
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
		        	 CreateNewIndexTableRow row = getTableView().getItems().get(getIndex());
		             checkBox.setSelected(row.isSelected());
		             checkBox.setText(row.getColumnName() != null ? row.getColumnName() : "");
		             setGraphic(checkBox);
		         }
		     }
		 });
		
		
		TableColumn<CreateNewIndexTableRow, Number> rowNumCol = new TableColumn<>("#");
		rowNumCol.setCellValueFactory(cell -> cell.getValue().rowNumberProperty());
		rowNumCol.setSortable(false);
		rowNumCol.setPrefWidth(50);
	
		TableColumn<CreateNewIndexTableRow, String> columnType = new TableColumn<>("Type");
		columnType.setCellValueFactory(cell -> cell.getValue().typeProperty());
		columnType.setCellFactory(TextFieldTableCell.forTableColumn());
		columnType.setOnEditCommit(e -> e.getRowValue().typeProperty().set(e.getNewValue()));
		columnType.setPrefWidth(150);
		
		TableColumn<CreateNewIndexTableRow, String> orderCol = new TableColumn<>("Order");
		orderCol.setCellValueFactory(cell -> cell.getValue().orderProperty());
		orderCol.setPrefWidth(100);
		orderCol.setCellFactory(col -> {
            TableCell<CreateNewIndexTableRow, String> cell = new TableCell<>() {
                private final ComboBox<String> comboBox = new ComboBox<>(orderData);
                {
//                    comboBox.setOnAction(e -> {
//                    	CreateNewIndexTableRow row = getTableView().getItems().get(getIndex());
//                        row.orderProperty().set(comboBox.getValue());
//                    });
                    
                    comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            CreateNewIndexTableRow row = getTableView().getItems().get(getIndex());
                            row.orderProperty().set(newVal);
                            updateIndexSQL(); // Refresh SQL instantly
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
            };
            return cell;
        });
		
//		TableColumn<CreateNewIndexTableRow, String> lengthCol = new TableColumn<>("Length");
//		lengthCol.setCellValueFactory(cell -> cell.getValue().lengthProperty());
//		lengthCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		lengthCol.setOnEditCommit(e -> e.getRowValue().lengthProperty().set(e.getNewValue()));
//		lengthCol.setPrefWidth(150);
		
		newIndexTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		newIndexTableView.setEditable(true);
		newIndexTableView.getColumns().addAll(columnName,rowNumCol,columnType,orderCol);
		newIndexTableView.setItems(createNewIndexData);
		newIndexTableView.getItems().clear();
		
		Button selectAllButton = new Button("Select All");
		selectAllButton.setId("buttons");
		selectAllButton.setOnAction(e -> {
		    boolean anySelected = createNewIndexData.stream().anyMatch(CreateNewIndexTableRow::isSelected);

		    if (anySelected) {
		        // Clear all selections
		        for (CreateNewIndexTableRow row : createNewIndexData) {
		            row.setSelected(false);
		        }
		        selectAllButton.setText("Select All");
		    } else {
		        // Select all rows
		        for (CreateNewIndexTableRow row : createNewIndexData) {
		            row.setSelected(true);
		        }
		        selectAllButton.setText("Clear");
		    }

		    newIndexTableView.refresh(); // Visually update checkboxes
		});

		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");

		HBox generatedSQLLabelHBox = new HBox();
		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
		Label generatedSQLIndexesLabel = new Label("Generated SQL");
		generatedSQLIndexesLabel.setId("generatedSQLLabel");
		generatedSQLLabelHBox.getChildren().add(generatedSQLIndexesLabel);

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox buttonsHBox = new HBox();
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.getStyleClass().add("buttons--hbox");
		Button okButton = new Button("OK");
		okButton.setId("buttons");
		okButton.setDisable(true);

		 
		 if (createNewIndexData.isEmpty()) {
			 createNewIndexData.add(new CreateNewIndexTableRow("column_name_1",1, "VARCHAR(100)","ASC","  "));
			 createNewIndexData.add(new CreateNewIndexTableRow("column_name_2",2, "BIGINT","ASC","  "));
			 createNewIndexData.add(new CreateNewIndexTableRow("column_name_3", 3, "CHAR","ASC","  "));			  
		 }
		
		 
		 ChangeListener<Object> indexFormValidator = (obs, oldVal, newVal) -> {

			 boolean isValid = true;
			 boolean anyColumnSelected = createNewIndexData.stream()
					 .anyMatch(CreateNewIndexTableRow::isSelected);
			 isValid =  anyColumnSelected;

			 okButton.setDisable(!isValid);
		 };
		 createNewIndexData.forEach(row -> 
		 row.selectedProperty().addListener(indexFormValidator) // Assuming row has BooleanProperty
				 );

		 okButton.setOnAction(e -> {
			 
			 IndexTableRow newRow = new IndexTableRow(
				        indexName,
				        columnsForDisplay,
				        tableName,
				        indexType,
				        hasAscending,  // ascending checkbox
				        false,         // nullable
				        unique,        // unique checkbox
				        " ", " ", " "     // extra, cardinality, comment
				    );
				    createNewTableHandler.getindexData().add(newRow);
			 
			    // 1️⃣ Get table name
//			   tableName = tableNamevalueField.getText().trim();
//
//			    // 2️⃣ Get selected columns and their order
//			    List<String> selectedColumns = new ArrayList<>();
//			   hasAscending = false; // for Ascending checkbox
//
//			    for (CreateNewIndexTableRow row : createNewIndexData) {
//			        if (row.isSelected()) {
//			            selectedColumns.add(row.getColumnName());
//
//			            // If any column order is ASC, mark ascending checkbox
//			            if ("ASC".equalsIgnoreCase((String) row.getOrder())) {
//			                hasAscending = true;
//			            }
//			        }
//			    }
//
//			    // 3️⃣ Create index name: idx_table_col1_col2
//			     indexName = "idx_" + tableName + "_" + String.join("_", selectedColumns);
//
//			    // 4️⃣ Get index type and unique
//			    indexType = indexTypedropdown.getValue();
//			    unique = uniqueCheckBox.isSelected();
//
//			    // 5️⃣ Columns for display in table
//			    String columnsForDisplay = String.join(", ", selectedColumns);
//
//			    // 6️⃣ Create new IndexTableRow and add to your table's data
//			    IndexTableRow newRow = new IndexTableRow(
//			        indexName,
//			        columnsForDisplay,
//			        tableName,
//			        indexType,
//			        hasAscending,  // ascending checkbox
//			        false,         // nullable
//			        unique,        // unique checkbox
//			        " ", " ", " "     // extra, cardinality, comment
//			    );
//			    createNewTableHandler.getindexData().add(newRow); // Replace with your ObservableList reference

//			    // 7️⃣ Generate SQL and update textarea
//			    StringBuilder sql = new StringBuilder("CREATE ");
//			    if (unique) sql.append("UNIQUE ");
//			    if (indexType != null && !indexType.isEmpty()) sql.append(indexType.toUpperCase()).append(" ");
//			    sql.append("INDEX ").append(indexName)
//			       .append(" ON ").append(tableName)
//			       .append(" (")
//			       .append(String.join(", ", selectedColumns))
//			       .append(");");
//
//			    sqlTextArea.setText(sql.toString()); // Replace with your SQL textarea reference

			    // 8️⃣ Close the dialog
			    stage.close();
			});
		 
//		okButton.setOnAction(e -> {
//            
//			String tableName = tableNamevalueField.getText();
//		    String column= "";
//		    //String order ="";
//		    String indexType = indexTypedropdown.getValue();
//		    Boolean unique = uniqueCheckBox.isSelected();
//		    String indexName = tableName +"_"+column;
//		    
////		    String type = constraintTypeComboBox.getValue();
////		    String checkExpression = "";
//		    
//			List<String> selectedColumns = new ArrayList<>();
//	         for (CreateNewIndexTableRow row : createNewIndexData) {
//	             if (row.isSelected()) {
//	                 selectedColumns.add(row.getColumnName());
//	             }
//	         }
//
//	         // 🔗 Join names with commas
//	         column = String.join("\n", selectedColumns);
//	    
//			IndexTableRow newRow = new IndexTableRow(
//					indexName,
//					column,
//					tableName,
//					indexType,
//					false,false,unique,"nn", "nn","nn"
//		    );
//
//		 //  createNewTableHandler.getcreateNewIndexData().add(newRow);
//		    stage.close();
//		});
		
//		okButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				stage.close();
//			}		 
//		});

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
		bottomHBox.getChildren().addAll(generatedSQLLabelHBox,spacer,buttonsHBox);
		
		
		HBox sqlIndexesHBox = new HBox();	
		sqlIndexesHBox.getStyleClass().add("sqlHBox");
		generatedSQLQueryTextArea = new TextArea();		
		generatedSQLQueryTextArea.getStyleClass().add("generatedSQLQueryTextAreaIndexTab");	

		Button completeSqlQueryButton = new Button("Complete SQL");
		completeSqlQueryButton.setId("buttons");
		completeSqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(generatedSQLQueryTextArea));
		
		executeSqlQueryButton = new Button("Execute SQL");
		executeSqlQueryButton.setId("buttons");
		executeSqlQueryButton.setDisable(true);
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//			));
		//sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
		
		ChangeListener<Object> formValidator = (obs, oldVal, newVal) -> {
			 boolean isgeneratedSQLQueryTextAreaFilled = !generatedSQLQueryTextArea.getText().trim().isEmpty();
			 executeSqlQueryButton.setDisable(!isgeneratedSQLQueryTextAreaFilled);
		 };

		 // Attach listener to text field and combo box
		 generatedSQLQueryTextArea.textProperty().addListener(formValidator);

		
		
		VBox buttonWrapper = new VBox(completeSqlQueryButton,executeSqlQueryButton);
		buttonWrapper.setAlignment(Pos.CENTER);
		buttonWrapper.setId("buttonWrapper");
		sqlIndexesHBox.getChildren().addAll(generatedSQLQueryTextArea,buttonWrapper);		

		
		indexPropertiesVbox.getChildren().addAll(tableNameValueHbox,indexTypeHBox,uniqueNameValueHbox,columnLabel,newIndexTableView,selectAllButton,bottomHBox,sqlIndexesHBox);
		newIndexTabVBox.getChildren().addAll(indexPropertiesVbox);
		createNewTableHandler.createNewIndexScene = new Scene(newIndexTabVBox, 650, 370);
		createNewTableHandler.createNewIndexScene.getStylesheets().add(createNewTableHandler.selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("Create New Index");
		stage.setWidth(550);
		stage.setHeight(550);
		stage.setScene( createNewTableHandler.createNewIndexScene);

		stage.show();
	}

	private ObservableList<CreateNewIndexTableRow> getCheckedRows() {
	    return createNewIndexData.filtered(CreateNewIndexTableRow::isSelected);
	}

	private void updateRowNumbers() {
		newIndexTableView.refresh();
	}
	
	public static class CreateNewIndexTableRow {

		private final StringProperty columnName = new SimpleStringProperty("");
		private final SimpleIntegerProperty rowNumber;	
		private final StringProperty type = new SimpleStringProperty("");
	    private final BooleanProperty selected = new SimpleBooleanProperty(false);
	    private final StringProperty order = new SimpleStringProperty("");
	    private final StringProperty length = new SimpleStringProperty("");


		public CreateNewIndexTableRow( String columnNameText,int rowNum,  String type, String order, String length) {
			this.columnName.set(columnNameText);
			this.rowNumber = new SimpleIntegerProperty();
			this.type.set(type);
			this.order.set(order);
			this.length.set(length);
		}      


		public CreateNewIndexTableRow(int rowNum) {
			//this.rowNumber = new SimpleIntegerProperty(rowNum);
			this.columnName.set(""); // default
			this.type.set(""); // default
			this.rowNumber = new SimpleIntegerProperty(rowNum);
			this.order.set("");
			this.length.set("");
		}

				
		public StringProperty columnNameProperty() { return columnName; }
		public String getColumnName() { return columnName.get(); }
		public void setColumnName(String value) { columnName.set(value); }
		    
		public IntegerProperty rowNumberProperty() { return rowNumber; }
		public StringProperty typeProperty() { return type; }
		
		public BooleanProperty selectedProperty() { return selected; }
		public boolean isSelected() { return selected.get(); }
		public void setSelected(boolean value) { selected.set(value); }
		
		public StringProperty orderProperty() { return order; }
		public String getOrder() {return order.get();}
		public void setOrder(String order) {this.order.set(order);}
		    
		public StringProperty lengthProperty() { return length; }
	
		// private final StringProperty order;

//		    public CreateNewIndexTableRow(String order) {
//		        this.order = new SimpleStringProperty(order);
//		    }

		  //  public StringProperty orderTableProperty() { return order; }

		   
	
	}
	

	private final ObservableList<CreateNewIndexTableRow> createNewIndexData = FXCollections.observableArrayList();
	private final ObservableList<String> orderData = FXCollections.observableArrayList("ASC","DESC");


	public ObservableList<CreateNewIndexTableRow> getcreateNewIndexData() {
		return createNewIndexData;
	}

//	private void updateIndexSQL() {
//	    tableName = tableNamevalueField.getText().trim();
//
//	    // 1️⃣ Collect selected columns
//	    List<String> selectedColumns = new ArrayList<>();
//	    List<String> selectedColumnsWithOrder = new ArrayList<>();
//	    hasAscending = false;
//
//	    for (CreateNewIndexTableRow row : createNewIndexData) {
//	        if (row.isSelected()) {
//	            selectedColumns.add(row.getColumnName());
//	            selectedColumnsWithOrder.add(row.getColumnName() + " " + row.getOrder());
//
//	            if ("ASC".equalsIgnoreCase(row.getOrder())) {
//	                hasAscending = true;
//	            }
//	        }
//	    }
//
//	    // 2️⃣ Only build indexName if valid
//	    if (tableName != null && !tableName.isEmpty() && !selectedColumns.isEmpty()) {
//	        indexName = "idx_" + tableName + "_" + String.join("_", selectedColumns);
//	    } else {
//	        indexName = null; // avoid idx__
//	    }
//
//	    // 3️⃣ Index type & unique
//	    indexType = indexTypedropdown.getValue();
//	    unique = uniqueCheckBox.isSelected();
//
//	    // 4️⃣ Columns for display
//	    columnsForDisplay = String.join(", ", selectedColumns);
//
//	    // 5️⃣ Build SQL only if all required parts are present
//	    if (indexName != null && !indexName.isEmpty() &&
//	        tableName != null && !tableName.isEmpty() &&
//	        !selectedColumnsWithOrder.isEmpty()) {
//
//	        StringBuilder indexSQL = new StringBuilder("CREATE ");
//
//	        if (unique) {
//	            indexSQL.append("UNIQUE ");
//	        }
//
//	        indexSQL.append("INDEX ").append(indexName).append("\n");
//
//	        if (indexType != null && !indexType.isEmpty()) {
//	            indexSQL.append(indexType).append(" ON ");
//	        } else {
//	            indexSQL.append("ON ");
//	        }
//
//	        indexSQL.append(tableName).append(" (\n    ")
//	                .append(String.join(",\n    ", selectedColumnsWithOrder))
//	                .append("\n)");
//
//	        generatedSQLQueryTextArea.setText(indexSQL.toString());
//	        System.out.println(indexSQL);
//	    } else {
//	        // Nothing selected yet → clear text area
//	        generatedSQLQueryTextArea.clear();
//	    }
//	}
	
	
	private void updateIndexSQL() {
	    
		tableName = tableNamevalueField.getText().trim();

	    // 2️⃣ Get selected columns and their order
	    List<String> selectedColumns = new ArrayList<>();
	    List<String> selectedColumnsWithOrder = new ArrayList<>();
	    hasAscending = false; // for Ascending checkbox

	    for (CreateNewIndexTableRow row : createNewIndexData) {
	        if (row.isSelected()) {
	            selectedColumns.add(row.getColumnName());
	            selectedColumnsWithOrder.add(row.getColumnName() + " " + row.getOrder());

	            // If any column order is ASC, mark ascending checkbox
	            if ("ASC".equalsIgnoreCase((String) row.getOrder())) {
	                hasAscending = true;
	            }
	        }
	    }

	    if (tableName != null && !tableName.isEmpty()) {
	        indexName = "idx_" + tableName + "_" + String.join("_", selectedColumns);
	    } else {
	        indexName = null; // avoid idx__
	    }
	    
	    // 3️⃣ Create index name: idx_table_col1_col2
	     //indexName = "idx_" + tableName + "_" + String.join("_", selectedColumns);

	    // 4️⃣ Get index type and unique
	    indexType = indexTypedropdown.getValue();
	    unique = uniqueCheckBox.isSelected();

	    // 5️⃣ Columns for display in table
	      columnsForDisplay = String.join(", ", selectedColumns);

	    // 6️⃣ Create new IndexTableRow and add to your table's data
	   
		
				
		String indexSQL = "CREATE ";
		if (indexName != null && !indexName.isEmpty()) {
			if ( unique) {
				indexSQL += "UNIQUE ";  	
			}
			indexSQL += "INDEX " + indexName + "\n";  	
		}
		

		if (indexName != null && !indexName.isEmpty()) {
			if(indexType != null && !indexType.isEmpty()){
				indexSQL += indexType + " ON ";
			}
			
			if(tableName != null && !tableName.isEmpty()){
				//indexSQL += tableName + " (\n"+ selectedColumnsWithOrder+ " )\n";
				 indexSQL += tableName + " (\n    "
			              + String.join(",\n    ", selectedColumnsWithOrder)
			              + "\n)";
			}
			
	
			generatedSQLQueryTextArea.setText(indexSQL);
	    	System.out.println(indexSQL);
		}
	}
}	
	
	
//private void updateIndexSQL() {
//    // Get values from UI directly
//    String tableNameInput = tableNamevalueField.getText().trim();
//    String indexTypeInput = indexTypedropdown.getValue();
//    boolean isUnique = uniqueCheckBox.isSelected();
//
//    // Collect selected columns
//    List<String> selectedColumns = new ArrayList<>();
//    for (CreateNewIndexTableRow row : createNewIndexData) {
//        if (row.isSelected()) {
//            selectedColumns.add(row.getColumnName() + 
//                (row.getOrder() != null && !row.getOrder().toString().isEmpty() 
//                    ? " " + row.getOrder() 
//                    : ""));
//        }
//    }
//
//    // Don't proceed if table name or columns are missing
//    if (tableNameInput.isEmpty() || selectedColumns.isEmpty()) {
//        generatedSQLQueryTextArea.setText("");
//        return;
//    }
//
//    // Generate index name automatically
//    String generatedIndexName = "idx_" + tableNameInput + "_" + String.join("_", selectedColumns)
//            .replaceAll("\\s+", "_");
//
//    // Build SQL
//    StringBuilder sql = new StringBuilder("CREATE ");
//    if (isUnique) {
//        sql.append("UNIQUE ");
//    }
//    if (indexTypeInput != null && !indexTypeInput.isEmpty()) {
//        sql.append(indexTypeInput.toUpperCase()).append(" ");
//    }
//    sql.append("INDEX ").append(generatedIndexName)
//       .append(" ON ").append(tableNameInput)
//       .append(" (").append(String.join(", ", selectedColumns)).append(");");
//
//    // Show in textarea
//    generatedSQLQueryTextArea.setText(sql.toString());
//}
//}