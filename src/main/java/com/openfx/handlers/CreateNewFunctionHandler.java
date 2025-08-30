package com.openfx.handlers;

import com.openfx.handlers.CreateNewProcedureHandler.ProcedureTableRow;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

public class CreateNewFunctionHandler implements EventHandler<ActionEvent> {
    
	public CreateNewFunctionHandler createNewFunctionHandler;
	public MySqlUI mysqlui;
	public Stage stage;
	public CheckBox deterministicCheckbox;
	public TextArea generatedSQLQueryTextAreaForFunction;
	
	public CreateNewFunctionHandler(MySqlUI mysqlui) {
		this.mysqlui = mysqlui;
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		mysqlui.createNewFunctionHandler = this;
		VBox newFunctionsPropertiesTabVBox = new VBox(); 		 
		//VBox.setVgrow(newTableTabVBox, Priority.ALWAYS);

		HBox functionPropertiesHbox = new HBox();
		functionPropertiesHbox.getStyleClass().add("propertiesHbox");

		VBox leftFunctionPropertiesVbox = new VBox();
		leftFunctionPropertiesVbox.getStyleClass().add("leftpropertiesVbox");
	    
		HBox functionnameValueHbox = new HBox();
		functionnameValueHbox.getStyleClass().add("functionnameValueHbox");
		Label functionnameLabel = new Label("Function Name* : ");
		functionnameLabel.setId("createFunctionTabLabels");
		TextField functionNameTextField = new TextField();
		functionNameTextField.getStyleClass().add("Valuetextfield");
		
		functionNameTextField.setOnKeyReleased(new EventHandler<KeyEvent>()  {

			@Override
			public void handle(KeyEvent keyEvent) {
				// TODO Auto-generated method stub
				String functionName = functionNameTextField.getText();
				String functionSQL = "CREATE FUNCTION [IF NOT EXISTS] " + functionName;
				
				if(keyEvent.getCode().toString().equals("ENTER")) {
					if(functionName != null && !functionName.isEmpty()) {
						functionSQL += " ( )\n";
						functionSQL += deterministicCheckbox.isSelected() ? "DETERMINISTIC" : "NOT DETERMINISTIC";
			            functionSQL += "\nRETURN"+ "\nBEGIN\n"+"END";
					}
				}
				generatedSQLQueryTextAreaForFunction.setText(functionSQL);
				System.out.println(functionSQL);
				
			}
			
		});
		
		functionNameTextField.focusedProperty().addListener((observable, oldValue , newValue) -> {
			if (!newValue) { // focus lost
		        String functionName = functionNameTextField.getText();

		        if (functionName != null && !functionName.isEmpty()) {
		            String functionSQL = "CREATE FUNCTION [IF NOT EXISTS] " + functionName + "()\n";
		            functionSQL += deterministicCheckbox.isSelected() ? "DETERMINISTIC" : "NOT DETERMINISTIC";
		            functionSQL +="\nRETURN"+ "\nBEGIN\n"+"END";
		            

		            generatedSQLQueryTextAreaForFunction.setText(functionSQL);
		            System.out.println(functionSQL);
		        }
		    }
		});
		
		functionnameValueHbox.getChildren().addAll(functionnameLabel,functionNameTextField);

		HBox functionTypeHBox = new HBox();
		functionTypeHBox.getStyleClass().add("functionTypeHBox");
		Label functionTypeLabel = new Label("Function Type: ");
		functionTypeLabel.setId("createFunctionTabLabels");
		TextField functionTypeTextField = new TextField();
		functionTypeTextField.getStyleClass().add("Valuetextfield");
		functionTypeTextField.setText("FUNCTION");
		functionTypeTextField.setEditable(false);
		functionTypeHBox.getChildren().addAll(functionTypeLabel,functionTypeTextField);


		HBox descriptionValuehbox = new HBox();
		descriptionValuehbox.getStyleClass().add("descriptionValueHBox");
		Label descrpitionLabel = new Label("Description : ");
		descrpitionLabel.setId("createProcedureTabLabels");
		TextArea descriptionTextArea = new TextArea();
		descriptionTextArea.getStyleClass().add("functiondescriptiontextfield");
		descriptionTextArea.setWrapText(true);
		descriptionValuehbox.getChildren().addAll(descrpitionLabel,descriptionTextArea);

		leftFunctionPropertiesVbox.getChildren().addAll(functionnameValueHbox,functionTypeHBox,descriptionValuehbox);

		VBox rightpropertiesVbox = new VBox();
		rightpropertiesVbox.getStyleClass().add("rightpropertiesVbox");

		HBox deterministichbox = new HBox();
		deterministichbox.getStyleClass().add("deterministichbox");
		deterministicCheckbox = new CheckBox();
		deterministicCheckbox.setOnAction(e -> {
			    String functionName = functionNameTextField.getText();

			    if (functionName != null && !functionName.isEmpty()) {
			        String functionSQL = "CREATE FUNCTION [IF NOT EXISTS] " + functionName + "()\n";
			        functionSQL += deterministicCheckbox.isSelected() ? "DETERMINISTIC" : "NOT DETERMINISTIC";
			        functionSQL += "\nRETURN"+ "\nBEGIN\n"+"END";
			        generatedSQLQueryTextAreaForFunction.setText(functionSQL);
			    }
			});
		//updatableCheckbox.setId("partitionedCheckbox");
		Label deterministiclabel = new Label("Deterministic");
		deterministiclabel.setId("createProcedureTabLabels");
		deterministichbox.getChildren().addAll(deterministicCheckbox,deterministiclabel);
		
		
		HBox resultTypeHBox = new HBox();
		resultTypeHBox.getStyleClass().add("resultTypeHBox");
		Label resultTypeLabel = new Label("Result Type: ");
		resultTypeLabel.setId("createProcedureTabLabels");
		TextField resultTypeField = new TextField();
		resultTypeField.getStyleClass().add("Valuetextfield");
		resultTypeHBox.getChildren().addAll(resultTypeLabel,resultTypeField);
		
		
		HBox bodyTypeHBox = new HBox();
		bodyTypeHBox.getStyleClass().add("bodyTypeHBox");
		Label bodyTypeLabel = new Label("Body Type: ");
		bodyTypeLabel.setId("createProcedureTabLabels");
		TextField bodyTypeField = new TextField();
		bodyTypeField.getStyleClass().add("Valuetextfield");
		bodyTypeField.setText("SQL");
		bodyTypeField.setEditable(false);
		bodyTypeHBox.getChildren().addAll(bodyTypeLabel,bodyTypeField);

		rightpropertiesVbox.getChildren().addAll(deterministichbox,resultTypeHBox,bodyTypeHBox);

		functionPropertiesHbox.getChildren().addAll(leftFunctionPropertiesVbox,rightpropertiesVbox);
		
			
		TabPane functionstabletabpane = new TabPane();
		functionstabletabpane.getStyleClass().add("functionstabletabpane");
		functionstabletabpane.setSide(Side.LEFT);
		functionstabletabpane.setRotateGraphic(true);

		Tab functionsTab = new Tab();
		functionsTab.setClosable(false);
		Label labelL = new Label("Function Parameters");
		labelL.setRotate(90);
		StackPane stp = new StackPane(new Group(labelL));
		functionsTab.setGraphic(stp);
		functionsTab = getFunctionParameterTab(functionsTab);
		
		Tab sourceFunctionsTab = new Tab();
		sourceFunctionsTab.setClosable(false);
		labelL = new Label("Source");
		labelL.setRotate(90);
		stp = new StackPane(new Group(labelL));
		sourceFunctionsTab.setGraphic(stp);
		sourceFunctionsTab = getFunctionParameterTab(sourceFunctionsTab);

		HBox generatedSQLLabelHBox = new HBox();
		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
		Label generatedSQLProcedureLabel = new Label("Generated SQL");
		generatedSQLProcedureLabel.setId("generatedSQLLabel");
		generatedSQLLabelHBox.getChildren().add(generatedSQLProcedureLabel);
		
		HBox sqlFunctionHBox = new HBox();	
		sqlFunctionHBox.getStyleClass().add("sqlHBox");
		generatedSQLQueryTextAreaForFunction = new TextArea("");		
		generatedSQLQueryTextAreaForFunction.getStyleClass().add("generatedSQLQueryTextArea");	
		
		Button sqlQueryButton = new Button("Complete SQL");
		sqlQueryButton.setId("buttons");
		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(generatedSQLQueryTextAreaForFunction));
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//			));
	//	sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
		
		Button executeSqlButton = new Button("Execute SQL");
		executeSqlButton.setId("sqlbutton");
		executeSqlButton.setDisable(true);
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setId("buttons");
		Button okButton = new Button("OK");
		okButton.setId("buttons");
		okButton.setDisable(true);

		okButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage.close();
			}		 
		});

		
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage.close();
			}		 
		});
				
		ChangeListener<Object> formValidator = (obs, oldVal, newVal) -> {
			boolean isfunctionNameTextFieldFilled = !functionNameTextField.getText().trim().isEmpty();
			boolean isgeneratedSQLQueryTextAreaForFunctionFilled= !generatedSQLQueryTextAreaForFunction.getText().trim().isEmpty();
			executeSqlButton.setDisable((!isgeneratedSQLQueryTextAreaForFunctionFilled && !isfunctionNameTextFieldFilled));
			okButton.setDisable(!isfunctionNameTextFieldFilled);
		};

		// Attach listener to text field and combo box
		functionNameTextField.textProperty().addListener(formValidator);
		generatedSQLQueryTextAreaForFunction.textProperty().addListener(formValidator);
		
		VBox buttonWrapper = new VBox(sqlQueryButton,executeSqlButton);
		buttonWrapper.setAlignment(Pos.CENTER);
		buttonWrapper.setId("buttonWrapper");
		
		VBox buttonWrapper1 = new VBox(cancelButton,okButton);
		buttonWrapper1.setAlignment(Pos.CENTER);
		buttonWrapper1.setId("buttonWrapper");
		
		sqlFunctionHBox.getChildren().addAll(generatedSQLQueryTextAreaForFunction,buttonWrapper,buttonWrapper1);
		
		
		newFunctionsPropertiesTabVBox.getChildren().addAll(functionPropertiesHbox,functionstabletabpane,generatedSQLLabelHBox,sqlFunctionHBox);
		functionstabletabpane.getTabs().addAll(functionsTab,sourceFunctionsTab);
				
		mysqlui.createNewFunctionScene = new Scene(newFunctionsPropertiesTabVBox, 650, 370);
		mysqlui.createNewFunctionScene.getStylesheets().add(mysqlui.selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("Create New Function");
		stage.setWidth(900);
		stage.setHeight(630);
		stage.setScene( mysqlui.createNewFunctionScene);

		stage.show();
//		mySqlUI.scene = new Scene(newProcedureTabVbox, 650, 370);
//		mySqlUI.scene.getStylesheets().add(mySqlUI.selectedTheme);
//		
	}

	 public static class FunctionTableRow {
	    	
	    	private final StringProperty columnType = new SimpleStringProperty("");
	    	private final StringProperty columnName = new SimpleStringProperty("");
	    	private final SimpleIntegerProperty rowNumber;
	        private final ObjectProperty<String> dataType = new SimpleObjectProperty<>("");
	        private final StringProperty length = new SimpleStringProperty("");
	        private final BooleanProperty notNull = new SimpleBooleanProperty(false);
	        private final BooleanProperty autoGenerated = new SimpleBooleanProperty(false);
	       
	        
	        public FunctionTableRow( String columnTypeText,String columnNameText,int rowNum,  String dataTypeComboBox, String lengthText, boolean notNullCheckBox,  boolean autoGeneratedCheckBox) {
	            this.rowNumber = new SimpleIntegerProperty(rowNum);
	            this.columnType.set(columnTypeText);
	            this.columnName.set(columnNameText);
	            this.dataType.set(dataTypeComboBox);
	            this.length.set(lengthText);
	            this.notNull.set(notNullCheckBox);
	            this.autoGenerated.set(autoGeneratedCheckBox);
	        }
	        
	        public FunctionTableRow(int rowNum) {
	            this.rowNumber = new SimpleIntegerProperty(rowNum);
	        }

	        public IntegerProperty rowNumberProperty() { return rowNumber; }
	        public StringProperty columnTypeProperty() { return columnType; }
	        public StringProperty columnNameProperty() { return columnName; }
	        public ObjectProperty<String> dataTypeProperty() { return dataType; }
	        public StringProperty lengthProperty() { return length; }
	        public BooleanProperty notNullProperty() { return notNull; }
	        public BooleanProperty autoGeneratedProperty() { return autoGenerated; }	    
	    }
	    	
	    private final ObservableList<FunctionTableRow> data = FXCollections.observableArrayList();
	    private final ObservableList<String> dataTypeOptions = FXCollections.observableArrayList("BIGINT","BIGINT UNSIGNED","BINARY","BIT","BLOB","BOOL","CHAR","DATE","DATETIME","DECIMAL","DOUBLE","DOUBLE PRECISION","DOUBLE PRECISION UNSIGNED",
				 "DOUBLE UNSIGNED","ENUM","FLOAT","GEOMETRY","GEOMETRY COLLECTION","INT","INT UNSIGNED","INTEGER", "INTEGER UNSIGNED","LINESTRING","LONG VARBINARY","LONG VARCHAR","LONG BLOB",
				 "LONGTEXT","MEDIUMBLOB","MEDIUMINT","MEDIUMINT UNSIGNED","MEDIUMTEXT","MULTILINESTRING","MULTIPOINT","MULTIPOLYGON","NUMERIC","POINT","POLYGON","REAL","SET","SMALLINT","SMALLINT UNSIGNED",
				 "TEXT","TIME","TIMESTAMP","TINYBLOB","TINYINT","TINYINT UNSIGNED","TINYTEXT","VARBINARY","VARCHAR","YEAR","json","varchar(100)");
	    	    
	    public ObservableList<FunctionTableRow> getData() {
	        return data;
	    }
	
	private Tab getFunctionParameterTab(Tab functionsTab) {

		VBox functionsTableVBox = new VBox();
		//ColumnsTableVBox.setId("ColumnsTableVBox");
//		TableView functionsTableView = new TableView();	
//		functionsTableView.getStyleClass().add("TableView");
		
		TableColumn<FunctionTableRow, String> colName = new TableColumn<>("Column Name");
		colName.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		colName.setPrefWidth(150);
		
		TableColumn<FunctionTableRow, String> colType = new TableColumn<>("Column Type");
		colType.setCellValueFactory(cell -> cell.getValue().columnTypeProperty());
		colType.setCellFactory(TextFieldTableCell.forTableColumn());
		colType.setOnEditCommit(e -> e.getRowValue().columnTypeProperty().set(e.getNewValue()));
		colType.setPrefWidth(100);

		// 2. Row Number #
		TableColumn<FunctionTableRow, Number> rowNumCol = new TableColumn<>("#");
		rowNumCol.setCellValueFactory(cell -> cell.getValue().rowNumberProperty());
		rowNumCol.setSortable(false);
		rowNumCol.setPrefWidth(60);

		// 3. DataType
		TableColumn<FunctionTableRow, String> dataTypeCol = new TableColumn<>("DataType");
		dataTypeCol.setCellValueFactory(cell -> cell.getValue().dataTypeProperty());
		dataTypeCol.setPrefWidth(60);
		
		TableColumn<FunctionTableRow, String> lengthCol = new TableColumn<>("Length");
		lengthCol.setCellValueFactory(cell -> cell.getValue().lengthProperty());
		lengthCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lengthCol.setOnEditCommit(e -> e.getRowValue().lengthProperty().set(e.getNewValue()));
		lengthCol.setPrefWidth(100);

        // 5. Auto Increment
        TableColumn<FunctionTableRow, Boolean> autoIncrementCol = new TableColumn<>("Auto Increment");
        autoIncrementCol.setCellValueFactory(cell -> cell.getValue().autoGeneratedProperty());
        autoIncrementCol.setCellFactory(CheckBoxTableCell.forTableColumn(autoIncrementCol));
        autoIncrementCol.setPrefWidth(100);
        
        TableColumn<FunctionTableRow, Boolean> notNullCol = new TableColumn<>("Not Null");
        notNullCol.setCellValueFactory(cell -> cell.getValue().notNullProperty());
        notNullCol.setCellFactory(CheckBoxTableCell.forTableColumn(notNullCol));
        notNullCol.setPrefWidth(100);
        
        TableView<FunctionTableRow> functionTableView = new TableView<>();
        functionTableView.getStyleClass().add("TableView");
        functionTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        functionTableView.setEditable(false);
        functionTableView.getColumns().addAll(rowNumCol,colName,colType, dataTypeCol,lengthCol,autoIncrementCol,notNullCol);
        functionTableView.setItems(data);
			
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
				
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox buttons1HBox = new HBox();
		buttons1HBox.getStyleClass().add("buttons--hbox");
		buttons1HBox.setAlignment(Pos.BOTTOM_RIGHT);				
		
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
	//	buttons1HBox.getChildren().addAll(cancelButton,okButton);
		
		
//		HBox sqlFunctionHBox = new HBox();	
//		sqlFunctionHBox.getStyleClass().add("sqlHBox");
//		TextArea generatedSQLQueryTextArea = new TextArea("");		
//		generatedSQLQueryTextArea.getStyleClass().add("generatedSQLQueryTextArea");	
//		
//		Button sqlQueryButton = new Button("SQL");
//		sqlQueryButton.setId("buttons");
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//			));
//	//	sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
//		VBox buttonWrapper = new VBox(sqlQueryButton);
//		buttonWrapper.setAlignment(Pos.CENTER);
//		sqlFunctionHBox.getChildren().addAll(generatedSQLQueryTextArea,buttonWrapper);
		
		
		bottomHBox.getChildren().addAll(spacer,buttons1HBox);
		//sqlHBox.getChildren().addAll(generatedSQLQueryTextArea,buttons1HBox,buttons2VBox);
		functionsTableVBox.getChildren().addAll(functionTableView,bottomHBox);
		functionsTab.setContent(functionsTableVBox);
		return functionsTab;
	}

}
