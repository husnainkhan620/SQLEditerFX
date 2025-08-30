package com.openfx.handlers;

import com.openfx.handlers.CreateNewTableHandler.TableRow;
//import com.openfx.handlers.CreateNewViewHandler.ViewTableRow;
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
import javafx.scene.control.ComboBox;
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

public class CreateNewProcedureHandler implements EventHandler<ActionEvent>  {

	public MySqlUI mysqlui;
	public Stage stage;
	public Scene createNewProcedureScene;
	public TextArea generatedSQLQueryTextAreaForProcedure;
	public CheckBox deterministicCheckbox;
	public TextField procedureNameValueTextField ;

	public String whiteThemeCss = CreateNewProcedureHandler.class.getResource("/whiteTheme.css").toExternalForm();
	public String darkThemeCss = CreateNewProcedureHandler.class.getResource("/darkTheme.css").toExternalForm();
	public String selectedTheme = whiteThemeCss; 
	
	public CreateNewProcedureHandler(MySqlUI mysqlui) {
		this.mysqlui = mysqlui;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		mysqlui.createNewProcedureHandler = this;
		VBox newProceduresPropertiesTabVBox = new VBox(); 		 
		//VBox.setVgrow(newTableTabVBox, Priority.ALWAYS);

		HBox procedurePropertiesHbox = new HBox();
		procedurePropertiesHbox.getStyleClass().add("propertiesHbox");

		VBox leftProcedurePropertiesVbox = new VBox();
		leftProcedurePropertiesVbox.getStyleClass().add("leftpropertiesVbox");
	    
		HBox procedurenameValueHbox = new HBox();
		procedurenameValueHbox.getStyleClass().add("procedurenameValueHbox");
		Label procedurenameLabel = new Label("Procedure Name* : ");
		procedurenameLabel.setId("createProcedureTabLabels");
		procedureNameValueTextField = new TextField();
		procedureNameValueTextField.getStyleClass().add("Valuetextfield");
//		procedureNameValueTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
//			
//			@Override
//			public void handle(KeyEvent keyEvent) {
//				// TODO Auto-generated method stub
//				System.out.println("CREATE PROCEDURE [IF NOT EXISTS] "+ procedureNameValueTextField.getText());
//				generatedSQLQueryTextAreaForProcedure.setText("CREATE PROCEDURE [IF NOT EXISTS] "+ procedureNameValueTextField.getText());
//			}
//		});	
		
		procedureNameValueTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

		    @Override
		    public void handle(KeyEvent keyEvent) {
		    	String procedureName = procedureNameValueTextField.getText();

		        // Build base SQL
		        String sql = "CREATE PROCEDURE [IF NOT EXISTS] " + procedureName;

		        // If ENTER is pressed, append brackets and deterministic status
		        if (keyEvent.getCode().toString().equals("ENTER")) {
		            if (procedureName != null && !procedureName.isEmpty()) {
		                sql += "()\n";
		                sql += deterministicCheckbox.isSelected() ? "DETERMINISTIC" : "NOT DETERMINISTIC";
		                sql += "\nBEGIN\n"+ "END";
		            }
		        }

		        generatedSQLQueryTextAreaForProcedure.setText(sql);
		        
		    }
		});

		// Add focus listener to append () when user moves out
		procedureNameValueTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		        String procedureName = procedureNameValueTextField.getText();

		        if (procedureName != null && !procedureName.isEmpty()) {
		            String sql = "CREATE PROCEDURE [IF NOT EXISTS] " + procedureName + "()\n";
		            sql += deterministicCheckbox.isSelected() ? "DETERMINISTIC" : "NOT DETERMINISTIC";
		            sql +=  "\nBEGIN\n"+ "END";
		            generatedSQLQueryTextAreaForProcedure.setText(sql);
		        }
		    }
		});
		
		procedurenameValueHbox.getChildren().addAll(procedurenameLabel,procedureNameValueTextField);

		HBox procedureTypeHBox = new HBox();
		procedureTypeHBox.getStyleClass().add("procedureTypeHBox");
		Label procedureTypeLabel = new Label("Procedure Type: ");
		procedureTypeLabel.setId("createProcedureTabLabels");
		TextField procedureTypeTextField = new TextField();
		procedureTypeTextField.getStyleClass().add("Valuetextfield");
		procedureTypeTextField.setText("PROCEDURE");
		procedureTypeTextField.setEditable(false);
		procedureTypeHBox.getChildren().addAll(procedureTypeLabel,procedureTypeTextField);


		HBox descriptionValuehbox = new HBox();
		descriptionValuehbox.getStyleClass().add("descriptionValueHBox");
		Label descrpitionLabel = new Label("Description : ");
		descrpitionLabel.setId("createProcedureTabLabels");
		TextArea descriptionTextField = new TextArea();
		descriptionTextField.getStyleClass().add("proceduredescriptiontextfield");
		descriptionTextField.setWrapText(true);
		descriptionValuehbox.getChildren().addAll(descrpitionLabel,descriptionTextField);

		leftProcedurePropertiesVbox.getChildren().addAll(procedurenameValueHbox,procedureTypeHBox,descriptionValuehbox);

		VBox rightProcedurepropertiesVbox = new VBox();
		rightProcedurepropertiesVbox.getStyleClass().add("rightpropertiesVbox");

		HBox deterministichbox = new HBox();
		deterministichbox.getStyleClass().add("deterministichbox");
		 deterministicCheckbox = new CheckBox();
		 deterministicCheckbox.setOnAction(e -> {
			    String procedureName = procedureNameValueTextField.getText();

			    if (procedureName != null && !procedureName.isEmpty()) {
			        String sql = "CREATE PROCEDURE [IF NOT EXISTS] " + procedureName + "()\n";
			        sql += deterministicCheckbox.isSelected() ? "DETERMINISTIC" : "NOT DETERMINISTIC";
			        sql +=  "\nBEGIN \n"+ "END";
			        generatedSQLQueryTextAreaForProcedure.setText(sql);
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
		TextField resultTypeTextField = new TextField();
		resultTypeTextField.getStyleClass().add("Valuetextfield");
		resultTypeTextField.setEditable(false);
		resultTypeHBox.getChildren().addAll(resultTypeLabel,resultTypeTextField);
		
		
		HBox bodyTypeHBox = new HBox();
		bodyTypeHBox.getStyleClass().add("bodyTypeHBox");
		Label bodyTypeLabel = new Label("Body Type: ");
		bodyTypeLabel.setId("createProcedureTabLabels");
		TextField bodyTypeTextField = new TextField();
		bodyTypeTextField.getStyleClass().add("Valuetextfield");
		bodyTypeTextField.setText("SQL");
		bodyTypeTextField.setEditable(false);
		bodyTypeHBox.getChildren().addAll(bodyTypeLabel,bodyTypeTextField);

		rightProcedurepropertiesVbox.getChildren().addAll(deterministichbox,resultTypeHBox,bodyTypeHBox);

		procedurePropertiesHbox.getChildren().addAll(leftProcedurePropertiesVbox,rightProcedurepropertiesVbox);
		
			
		TabPane procedurestabletabpane = new TabPane();
		procedurestabletabpane.getStyleClass().add("procedurestabletabpane");
		procedurestabletabpane.setSide(Side.LEFT);
		procedurestabletabpane.setRotateGraphic(true);

		Tab proceduresTab = new Tab();
		proceduresTab.setClosable(false);
		Label labelL = new Label("Procedure Parameters");
		labelL.setRotate(90);
		StackPane stp = new StackPane(new Group(labelL));
		proceduresTab.setGraphic(stp);
		proceduresTab = getProcedureParameterTab(proceduresTab);
		
		Tab sourceProceduresTab = new Tab();
		sourceProceduresTab.setClosable(false);
		labelL = new Label("Source");
		labelL.setRotate(90);
		stp = new StackPane(new Group(labelL));
		sourceProceduresTab.setGraphic(stp);
		sourceProceduresTab = getProcedureParameterTab(sourceProceduresTab);
		
		HBox generatedSQLLabelHBox = new HBox();
		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
		Label generatedSQLProcedureLabel = new Label("Generated SQL");
		generatedSQLProcedureLabel.setId("generatedSQLLabel");
		generatedSQLLabelHBox.getChildren().add(generatedSQLProcedureLabel);
		
		
		HBox sqlProcedureHBox = new HBox();	
		sqlProcedureHBox.getStyleClass().add("sqlHBox");
		generatedSQLQueryTextAreaForProcedure = new TextArea("");		
		generatedSQLQueryTextAreaForProcedure.getStyleClass().add("generatedSQLQueryTextArea");	
		
		Button sqlQueryButton = new Button("Complete SQL");
		sqlQueryButton.setId("buttons");
		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(generatedSQLQueryTextAreaForProcedure));
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
			boolean isgeneratedSQLQueryTextAreaForProcedureFilled = !generatedSQLQueryTextAreaForProcedure.getText().trim().isEmpty();
			boolean isprocedureNameValueTextFieldFilled= !procedureNameValueTextField.getText().trim().isEmpty();
			executeSqlButton.setDisable((!isgeneratedSQLQueryTextAreaForProcedureFilled && !isprocedureNameValueTextFieldFilled));
			okButton.setDisable(!isprocedureNameValueTextFieldFilled);
		};

		// Attach listener to text field and combo box
		generatedSQLQueryTextAreaForProcedure.textProperty().addListener(formValidator);
		procedureNameValueTextField.textProperty().addListener(formValidator);

		 
		VBox buttonWrapper = new VBox(sqlQueryButton,executeSqlButton);
		buttonWrapper.setAlignment(Pos.CENTER);
		buttonWrapper.setId("buttonWrapper");
		
		VBox buttonWrapper1 = new VBox(cancelButton,okButton);
		buttonWrapper1.setAlignment(Pos.CENTER);
		buttonWrapper1.setId("buttonWrapper");
		
		sqlProcedureHBox.getChildren().addAll(generatedSQLQueryTextAreaForProcedure,buttonWrapper,buttonWrapper1);
		
		procedurestabletabpane.getTabs().addAll(proceduresTab,sourceProceduresTab);
		newProceduresPropertiesTabVBox.getChildren().addAll(procedurePropertiesHbox,procedurestabletabpane,generatedSQLLabelHBox,sqlProcedureHBox);
				
		mysqlui.createNewProcedureScene = new Scene(newProceduresPropertiesTabVBox, 650, 370);
		mysqlui.createNewProcedureScene.getStylesheets().add(mysqlui.selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("Create New Procedure");
		stage.setWidth(900);
		stage.setHeight(630);
		stage.setScene( mysqlui.createNewProcedureScene);

		stage.show();
//		mySqlUI.scene = new Scene(newProcedureTabVbox, 650, 370);
//		mySqlUI.scene.getStylesheets().add(mySqlUI.selectedTheme);
//		
	}

	 public static class ProcedureTableRow {
	    	
	    	private final StringProperty columnType = new SimpleStringProperty("");
	    	private final StringProperty columnName = new SimpleStringProperty("");
	    	private final SimpleIntegerProperty rowNumber;
	        private final ObjectProperty<String> dataType = new SimpleObjectProperty<>("");
	        private final StringProperty length = new SimpleStringProperty("");
	        private final BooleanProperty notNull = new SimpleBooleanProperty(false);
	        private final BooleanProperty autoIncrement = new SimpleBooleanProperty(false);
	       
	        
	        public ProcedureTableRow( String columnTypeText,String columnNameText,int rowNum,  String dataTypeComboBox, String lengthText, boolean notNullCheckBox,  boolean autoIncrementCheckBox) {
	            this.rowNumber = new SimpleIntegerProperty(rowNum);
	            this.columnType.set(columnTypeText);
	            this.columnName.set(columnNameText);
	            this.dataType.set(dataTypeComboBox);
	            this.length.set(lengthText);
	            this.notNull.set(notNullCheckBox);
	            this.autoIncrement.set(autoIncrementCheckBox);
	        }
	        
	        public ProcedureTableRow(int rowNum) {
	            this.rowNumber = new SimpleIntegerProperty(rowNum);
	        }

	        public IntegerProperty rowNumberProperty() { return rowNumber; }
	        public StringProperty columnTypeProperty() { return columnType; }
	        public StringProperty columnNameProperty() { return columnName; }
	        public ObjectProperty<String> dataTypeProperty() { return dataType; }
	        public StringProperty lengthProperty() { return length; }
	        public BooleanProperty notNullProperty() { return notNull; }
	        public BooleanProperty autoIncrementProperty() { return autoIncrement; }	    
	    }
	    	
	    private final ObservableList<ProcedureTableRow> data = FXCollections.observableArrayList();
	    private final ObservableList<String> dataTypeOptions = FXCollections.observableArrayList("BIGINT","BIGINT UNSIGNED","BINARY","BIT","BLOB","BOOL","CHAR","DATE","DATETIME","DECIMAL","DOUBLE","DOUBLE PRECISION","DOUBLE PRECISION UNSIGNED",
				 "DOUBLE UNSIGNED","ENUM","FLOAT","GEOMETRY","GEOMETRY COLLECTION","INT","INT UNSIGNED","INTEGER", "INTEGER UNSIGNED","LINESTRING","LONG VARBINARY","LONG VARCHAR","LONG BLOB",
				 "LONGTEXT","MEDIUMBLOB","MEDIUMINT","MEDIUMINT UNSIGNED","MEDIUMTEXT","MULTILINESTRING","MULTIPOINT","MULTIPOLYGON","NUMERIC","POINT","POLYGON","REAL","SET","SMALLINT","SMALLINT UNSIGNED",
				 "TEXT","TIME","TIMESTAMP","TINYBLOB","TINYINT","TINYINT UNSIGNED","TINYTEXT","VARBINARY","VARCHAR","YEAR","json","varchar(100)");
	    	    
	    public ObservableList<ProcedureTableRow> getData() {
	        return data;
	    }
	    
	private Tab getProcedureParameterTab(Tab proceduresTab) {

		VBox proceduresTableVBox = new VBox();
		//ColumnsTableVBox.setId("ColumnsTableVBox");
//		TableView proceduresTableView = new TableView();	
//		proceduresTableView.getStyleClass().add("TableView");
			
		TableColumn<ProcedureTableRow, String> colName = new TableColumn<>("Column Name");
		colName.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		colName.setPrefWidth(100);
		
		TableColumn<ProcedureTableRow, String> colType = new TableColumn<>("Column Type");
		colType.setCellValueFactory(cell -> cell.getValue().columnTypeProperty());
		colType.setCellFactory(TextFieldTableCell.forTableColumn());
		colType.setOnEditCommit(e -> e.getRowValue().columnTypeProperty().set(e.getNewValue()));
		colType.setPrefWidth(100);

		// 2. Row Number #
		TableColumn<ProcedureTableRow, Number> rowNumCol = new TableColumn<>("#");
		rowNumCol.setCellValueFactory(cell -> cell.getValue().rowNumberProperty());
		rowNumCol.setSortable(false);
		rowNumCol.setPrefWidth(60);

		// 3. DataType
		TableColumn<ProcedureTableRow, String> dataTypeCol = new TableColumn<>("DataType");
		dataTypeCol.setCellValueFactory(cell -> cell.getValue().dataTypeProperty());
		dataTypeCol.setPrefWidth(100);
		
		TableColumn<ProcedureTableRow, String> lengthCol = new TableColumn<>("Length");
		lengthCol.setCellValueFactory(cell -> cell.getValue().lengthProperty());
		lengthCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lengthCol.setOnEditCommit(e -> e.getRowValue().lengthProperty().set(e.getNewValue()));
		lengthCol.setPrefWidth(100);

        // 5. Auto Increment
        TableColumn<ProcedureTableRow, Boolean> autoIncCol = new TableColumn<>("Auto Increment");
        autoIncCol.setCellValueFactory(cell -> cell.getValue().autoIncrementProperty());
        autoIncCol.setCellFactory(CheckBoxTableCell.forTableColumn(autoIncCol));
        lengthCol.setPrefWidth(100);
        
        TableColumn<ProcedureTableRow, Boolean> notNullCol = new TableColumn<>("Not Null");
        notNullCol.setCellValueFactory(cell -> cell.getValue().notNullProperty());
        notNullCol.setCellFactory(CheckBoxTableCell.forTableColumn(notNullCol));
        notNullCol.setPrefWidth(100);
        
        TableView<ProcedureTableRow> procedureTableView = new TableView<>();
        procedureTableView.getStyleClass().add("TableView");
        procedureTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        procedureTableView.setEditable(false);
        procedureTableView.getColumns().addAll(rowNumCol,colName,colType, dataTypeCol,lengthCol,notNullCol, autoIncCol);
        procedureTableView.setItems(data);
		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
							
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
     	HBox buttons1HBox = new HBox();
//		buttons1HBox.getStyleClass().add("buttons--hbox");
//		buttons1HBox.setAlignment(Pos.BOTTOM_RIGHT);				
//		
//		Button cancelButton = new Button("Cancel");
//		cancelButton.setId("buttons");
//		Button okButton = new Button("OK");
//		okButton.setId("buttons");

//		ChangeListener<Object> formValidator = (obs, oldVal, newVal) -> {
//			 boolean isNameFilled = !procedureTypeTextField.getText().trim().isEmpty();
//			 okButton.setDisable(!isNameFilled);
//		 };
//
//		 // Attach listener to text field and combo box
//		 procedureTypeTextField.textProperty().addListener(formValidator);
		
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
		//buttons1HBox.getChildren().addAll(cancelButton,okButton);
		
		
//		HBox sqlProcedureHBox = new HBox();	
//		sqlProcedureHBox.getStyleClass().add("sqlHBox");
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
//		sqlProcedureHBox.getChildren().addAll(generatedSQLQueryTextArea,buttonWrapper);
		
		
		bottomHBox.getChildren().addAll(spacer,buttons1HBox);
		//sqlHBox.getChildren().addAll(generatedSQLQueryTextArea,buttons1HBox,buttons2VBox);
		proceduresTableVBox.getChildren().addAll(procedureTableView,bottomHBox);
		proceduresTab.setContent(proceduresTableVBox);
		return proceduresTab;
	}
}



		
				
		

		

