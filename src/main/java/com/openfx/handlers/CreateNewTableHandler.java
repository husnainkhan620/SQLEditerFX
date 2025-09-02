package com.openfx.handlers;

import org.openjfx.fx.Menu_Items_FX;

//import com.openfx.handlers.CreateNewConstraintHandler.ConstraintsTableRow;
import com.openfx.handlers.CreateNewFunctionHandler.FunctionTableRow;
import com.openfx.handlers.CreateNewIndexHandler.CreateNewIndexTableRow;
import com.openfx.ui.MySqlUI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
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
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.CheckBoxTableCell;

public class CreateNewTableHandler implements EventHandler<ActionEvent> {

	public String whiteThemeCss = Menu_Items_FX.class.getResource("/whiteTheme.css").toExternalForm();
	public String darkThemeCss = Menu_Items_FX.class.getResource("/darkTheme.css").toExternalForm();
	public String selectedTheme = whiteThemeCss;
	public MySqlUI mysqlui;
	public Scene scene;
	public Stage stage;
	public SplitPane splitPane;
	public Menu_Items_FX menu_Items_FX;
	public Stage primaryStage;
	public EditCommentTableHandler editCommentTableHandler;
	public CreateNewColumnHandler createNewColumnHandler;
	public GeneratedSQLQueryHandler generatedSQLQueryHandler;
	public CreateNewConstraintHandler createNewConstraintHandler;
	public Scene createNewConstraintScene;
	public CreateNewForeignKeyHandler createNewForeignKeyHandler;
	public Scene createNewForeignKeyScene;
	public Scene createNewTriggerScene;
	public CreateNewTriggerHandler createNewTriggerHandler;
	public CreateNewIndexHandler createNewIndexHandler;
	public Scene createNewIndexScene;
	public TextArea generatedSQLQueryTextAreaGeneral ;
	public TextField tablenameValueField;
	public TextField autoincrementField ;
	public ComboBox<String> engineFieldComboBox ;
	public ComboBox<String> charsetdropdown ;
	public ComboBox<String> collationComboBox ;
	public  CheckBox partitionedCheckbox ;

	public CreateNewTableHandler(MySqlUI mysqlui) {

		this.mysqlui = mysqlui;
		// TODO Auto-generated constructor stub
	}

	public CreateNewTableHandler(Menu_Items_FX menu_Items_FX) {

		this.menu_Items_FX = menu_Items_FX;
		// TODO Auto-generated constructor stub
	}

	public CreateNewTableHandler(CreateNewColumnHandler createNewColumnHandler,MySqlUI mysqlui) {

		this.createNewColumnHandler = createNewColumnHandler;
		this.mysqlui = mysqlui;
		// TODO Auto-generated constructor stub
	}
	
	public CreateNewTableHandler(GeneratedSQLQueryHandler generatedSQLQueryHandler) {
		
		this.generatedSQLQueryHandler = generatedSQLQueryHandler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(ActionEvent event) {


		mysqlui.createNewTableHandler = this;
		VBox newTableTabVBox = new VBox(); 		 
		//VBox.setVgrow(newTableTabVBox, Priority.ALWAYS);

		HBox createTablePropertiesHbox = new HBox();
		createTablePropertiesHbox.getStyleClass().add("propertiesHbox");

		VBox leftcreateTablepropertiesVbox = new VBox();
		leftcreateTablepropertiesVbox.getStyleClass().add("leftpropertiesVbox");
		HBox tablenameValueHbox = new HBox();
		tablenameValueHbox.getStyleClass().add("tablenameValueHbox");
		Label tablenameLabel = new Label("Table Name* : ");
		tablenameLabel.setId("createTableTabLabels");
		tablenameValueField = new TextField();
		
		tablenameValueField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		    	updateTableSQLQuery();
		    }
		});

		// FOCUS LOST LISTENER for eventNameValueTextField
		tablenameValueField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		    	updateTableSQLQuery();
		    }
		});	
		
//		tablenameValueField.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent keyEvent) {
//				System.out.println("CREATE TABLE [IF NOT EXISTS] "+ tablenameValueField.getText()+ "( " + " ) ");
//				generatedSQLQueryTextAreaGeneral.setText("CREATE TABLE [IF NOT EXISTS] "+ tablenameValueField.getText()+ "( "+ " ) ");
//				
//			}
//		});
		tablenameValueField.getStyleClass().add("Valuetextfield");
		tablenameValueHbox.getChildren().addAll(tablenameLabel,tablenameValueField);


		HBox autoincrementHbox = new HBox();
		autoincrementHbox.getStyleClass().add("autoincrementHbox");
		Label autoincrementLabel = new Label("Auto Increment : ");
		autoincrementLabel.setId("createTableTabLabels");
		autoincrementField = new TextField();
		autoincrementField.getStyleClass().add("Valuetextfield");
		autoincrementField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		    	updateTableSQLQuery();
		    }
		});

		// FOCUS LOST LISTENER for eventNameValueTextField
		autoincrementField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		    	updateTableSQLQuery();
		    }
		});	
		
		
		autoincrementHbox.getChildren().addAll(autoincrementLabel,autoincrementField);


		HBox descriptionValuehbox = new HBox();
		descriptionValuehbox.getStyleClass().add("descriptionValueHBox");
		Label descrpitionLabel = new Label("Description : ");
		descrpitionLabel.setId("createTableTabLabels");
		TextArea descriptionTextArea = new TextArea();
		descriptionTextArea.getStyleClass().add("descriptiontextfield");
//		descriptionField.setPrefHeight(175);
//		descriptionField.setMinHeight(175);
//		descriptionField.setMaxHeight(175);
		//descriptionField.setPrefRowCount(4);
		descriptionTextArea.setWrapText(true);
		descriptionValuehbox.getChildren().addAll(descrpitionLabel,descriptionTextArea);

		leftcreateTablepropertiesVbox.getChildren().addAll(tablenameValueHbox,autoincrementHbox,descriptionValuehbox);

		VBox rightCreateTablepropertiesVbox = new VBox();
		rightCreateTablepropertiesVbox.getStyleClass().add("rightpropertiesVbox");

		HBox enginehbox = new HBox();
		enginehbox.getStyleClass().add("enginehbox");
	    partitionedCheckbox = new CheckBox();
		partitionedCheckbox.setId("partitionedCheckbox");
		partitionedCheckbox.setOnAction(e ->{
			updateTableSQLQuery();
		});
		
		Label partitionedlabel = new Label("Partitioned");
		partitionedlabel.setId("createTableTabLabels");
		Label engineLabel = new Label("Engine : ");
		//engineLabel.setId("createTableTabLabels");
		engineFieldComboBox = new ComboBox<>();
		engineFieldComboBox.getStyleClass().add("combobox");		
	    engineFieldComboBox.getItems().addAll("InnoDB","MyISAM","MEMORY","CSV","ARCHIVE","EXAMPLE","FEDERATED","HEAP","MERGE","NDB");
 

	    // Listener for selection change
	    engineFieldComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
	    	updateTableSQLQuery();
	    });

	    // Alternatively, using setOnAction (also works for selection change)
	    engineFieldComboBox.setOnAction(selectionEvent -> {
	    	updateTableSQLQuery();
	    });

	    // Existing focus lost listener (keep if needed)
	    engineFieldComboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
	    	if (!newValue) { // focus lost
	    		updateTableSQLQuery();
	    	}
	    });
	    
//	    engineFieldComboBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
//		    @Override
//		    public void handle(KeyEvent keyEvent) {
//		    	updateTableSQLQuery();
//		    }
//		});
//
//		// FOCUS LOST LISTENER for eventNameValueTextField
//		engineFieldComboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
//		    if (!newValue) { // focus lost
//		    	updateTableSQLQuery();
//		    }
//		});	
	    
//		engineFieldComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//				System.out.println("Old Engine --> " + oldValue);
//				System.out.println("New Engine --> " + newValue);
//
//				if (newValue != null && !newValue.isEmpty()) {
//					String existingQuery = generatedSQLQueryTextAreaGeneral.getText();
//
//					// Remove old ENGINE=oldValue if exists
//					if (oldValue != null && !oldValue.isEmpty()) {
//						existingQuery = existingQuery.replaceAll("ENGINE=" + oldValue, "").trim();
//					}
//
//					// Append ENGINE=newValue
//					String updatedQuery = existingQuery + " ENGINE=" + newValue;
//
//					generatedSQLQueryTextAreaGeneral.setText(updatedQuery.trim());
//				}
//			}
//		});
		enginehbox.getChildren().addAll(partitionedCheckbox,partitionedlabel,engineLabel,engineFieldComboBox);

		HBox charsetHBox = new HBox();
		charsetHBox.getStyleClass().add("charsetHBox");
		Label charsetLabel = new Label("Charset: ");
		charsetLabel.setId("charsetLabel");
		charsetdropdown = new ComboBox<>();
		charsetdropdown.getStyleClass().add("combobox");
		charsetdropdown.getItems().addAll("BIGINT","YEAR","json","varchar(100)");
		
		charsetdropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
	    	updateTableSQLQuery();
	    });

	    // Alternatively, using setOnAction (also works for selection change)
		charsetdropdown.setOnAction(selectionEvent -> {
	    	updateTableSQLQuery();
	    });

	    // Existing focus lost listener (keep if needed)
		charsetdropdown.focusedProperty().addListener((observable, oldValue, newValue) -> {
	    	if (!newValue) { // focus lost
	    		updateTableSQLQuery();
	    	}
	    });
		charsetHBox.getChildren().addAll(charsetLabel,charsetdropdown);

		HBox collationHBox = new HBox();
		collationHBox.getStyleClass().add("collationHBox");
		Label collationLabel = new Label("Collation: ");
		collationLabel.setId("charsetLabel");
		collationComboBox = new ComboBox<>();
		collationComboBox.getStyleClass().add("combobox");
		
		collationComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
	    	updateTableSQLQuery();
	    });

	    // Alternatively, using setOnAction (also works for selection change)
		collationComboBox.setOnAction(selectionEvent -> {
	    	updateTableSQLQuery();
	    });

	    // Existing focus lost listener (keep if needed)
		collationComboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
	    	if (!newValue) { // focus lost
	    		updateTableSQLQuery();
	    	}
	    });
		collationHBox.getChildren().addAll(collationLabel,collationComboBox);

		rightCreateTablepropertiesVbox.getChildren().addAll(enginehbox,charsetHBox,collationHBox);

		createTablePropertiesHbox.getChildren().addAll(leftcreateTablepropertiesVbox,rightCreateTablepropertiesVbox);
		// propertiesVbox.getChildren().addAll(tablenameValueHbox,enginehbox,autoincrementHbox,charsetHBox,collationHBox,descriptionValuehbox);

		
		VBox tablestabpaneVBox = new VBox();		
		tablestabpaneVBox.setId("tablestabpaneVBox");
		TabPane tablestabpane = new TabPane();
		tablestabpane.setPrefHeight(260); // sets preferred height to 300
		tablestabpane.setMinHeight(260);  // prevents it from shrinking below 300
		tablestabpane.setMaxHeight(260);
		tablestabpane.getStyleClass().add("tablestabpane");
		tablestabpane.setSide(Side.LEFT);
		tablestabpane.setRotateGraphic(true);
		//tablestabpane.prefHeightProperty().bind(tablestabpaneVBox.heightProperty().multiply(0.5));
		
		Tab columnsTab = new Tab();
		columnsTab.setClosable(false);
		Label labelL = new Label("Columns");
		labelL.setRotate(90);
		StackPane stp = new StackPane(new Group(labelL));
		columnsTab.setGraphic(stp);
		columnsTab = getColumnsTab(columnsTab);

		Tab constraintsTab = new Tab();
		constraintsTab.setClosable(false);
		labelL = new Label("Constraints");
		labelL.setRotate(90);
		stp = new StackPane(new Group(labelL));
		constraintsTab.setGraphic(stp);
		constraintsTab = getConstraintsTab(constraintsTab);


		Tab foreignKeysTab = new Tab();
		foreignKeysTab.setClosable(false);
		labelL = new Label("Foreign Keys");
		labelL.setRotate(90);
		stp = new StackPane(new Group(labelL));
		foreignKeysTab.setGraphic(stp);
		foreignKeysTab = getForeignKeysTab(foreignKeysTab);


//		Tab referencesTab = new Tab();
//		referencesTab.setClosable(false);
//		labelL = new Label("References");
//		labelL.setRotate(90);
//		stp = new StackPane(new Group(labelL));
//		referencesTab.setGraphic(stp);
//		referencesTab = getReferencesTab(referencesTab);

		Tab triggersTab = new Tab();
		triggersTab.setClosable(false);
		labelL = new Label("Triggers");
		labelL.setRotate(90);
		stp = new StackPane(new Group(labelL));
		triggersTab.setGraphic(stp);
		triggersTab = getTriggersTab(triggersTab);


		Tab indexesTab = new Tab();
		indexesTab.setClosable(false);
		labelL = new Label("Indexes");
		labelL.setRotate(90);
		stp = new StackPane(new Group(labelL));
		indexesTab.setGraphic(stp);
		indexesTab = getIndexesTab(indexesTab);


		Tab partitionsTab = new Tab();
		partitionsTab.setClosable(false);
		labelL = new Label("Partitions");
		labelL.setRotate(90);
		stp = new StackPane(new Group(labelL));
		partitionsTab.setGraphic(stp);
		partitionsTab = getPartitionsTab(partitionsTab);


		tablestabpane.getTabs().addAll(columnsTab,constraintsTab,foreignKeysTab,triggersTab,indexesTab,partitionsTab);
		tablestabpaneVBox.getChildren().add(tablestabpane);
		
		HBox generatedSQLLabelHBox = new HBox();
		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
		Label generatedSQLColumnLabel = new Label("Generated SQL");
		generatedSQLColumnLabel.setId("generatedSQLLabel");
		generatedSQLLabelHBox.getChildren().add(generatedSQLColumnLabel);
		
		
		HBox sqlQueryHBox = new HBox();	
		sqlQueryHBox.getStyleClass().add("sqlHBox");			
		generatedSQLQueryTextAreaGeneral= new TextArea();		
		generatedSQLQueryTextAreaGeneral.getStyleClass().add("generatedSQLQueryTextArea");	
		//HBox.setHgrow(generatedSQLQueryTextArea, Priority.ALWAYS);
		
			
		Button sqlQueryButton = new Button("Complete SQL");
		sqlQueryButton.setId("sqlbutton");
		
		Button executeSqlButton = new Button("Execute SQL");
		executeSqlButton.setId("sqlbutton");
		executeSqlButton.setDisable(true);
		
		ChangeListener<Object> formValidator = (obs, oldVal, newVal) -> {
			 boolean isgeneratedSQLQueryTextAreaGeneralFilled = !generatedSQLQueryTextAreaGeneral.getText().trim().isEmpty();
			 boolean isTableNameFilled= !tablenameValueField.getText().trim().isEmpty();
			 executeSqlButton.setDisable((!isgeneratedSQLQueryTextAreaGeneralFilled && !isTableNameFilled));
		 };

		 // Attach listener to text field and combo box
		 generatedSQLQueryTextAreaGeneral.textProperty().addListener(formValidator);
		 tablenameValueField.textProperty().addListener(formValidator);
		 
		Button cancelButton = new Button("Cancel");
		cancelButton.setId("buttons");
		Button okButton = new Button("OK");
		okButton.setId("buttons");

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
		
		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(generatedSQLQueryTextAreaGeneral));
		//sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//		));
//		
		VBox sqlbuttonWrapper = new VBox(sqlQueryButton, executeSqlButton);
		sqlbuttonWrapper.setAlignment(Pos.CENTER);
		sqlbuttonWrapper.setId("buttonWrapper");
		
		VBox buttonWrapper1 = new VBox(cancelButton,okButton);
		buttonWrapper1.setAlignment(Pos.CENTER);
		buttonWrapper1.setId("buttonWrapper");
		
		sqlQueryHBox.getChildren().addAll(generatedSQLQueryTextAreaGeneral,sqlbuttonWrapper,buttonWrapper1);
		
		
		newTableTabVBox.getChildren().addAll(createTablePropertiesHbox,tablestabpaneVBox,generatedSQLLabelHBox,sqlQueryHBox);

		mysqlui.scene = new Scene(newTableTabVBox, 650, 370);
		mysqlui.scene.getStylesheets().add(mysqlui.selectedTheme);
		
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("Create New Table");
		stage.setWidth(850);
		stage.setHeight(700);
		stage.setScene( mysqlui.scene);

		stage.show();
	}

		

    public static class TableRow {
    	
    	private final SimpleIntegerProperty rowNumber;
        private final StringProperty columnName = new SimpleStringProperty("");
        private final ObjectProperty<String> dataType = new SimpleObjectProperty<>("");
        private final BooleanProperty notNull = new SimpleBooleanProperty(false);
        private final BooleanProperty autoIncrement = new SimpleBooleanProperty(false);
        private final ObjectProperty<String> key = new SimpleObjectProperty<>("");
        private final StringProperty defaultValue = new SimpleStringProperty("");
        private final StringProperty extra = new SimpleStringProperty("");
        private final StringProperty expression = new SimpleStringProperty("");
        private final StringProperty comment = new SimpleStringProperty("");
		private final  StringProperty constraintName = new SimpleStringProperty("");
		private final  StringProperty owner = new SimpleStringProperty("");
		private final  StringProperty type = new SimpleStringProperty("");
		private final  StringProperty checkExpression = new SimpleStringProperty("");
		

        public TableRow( String columnNameText,int rowNum,  String dataTypeComboBox, boolean notNullCheckBox,  boolean autoIncrementCheckBox,String keyComboBox,String defaultValueText,
        		String extraText, String expressionText,String commentText) {
            this.rowNumber = new SimpleIntegerProperty(rowNum);
            this.columnName.set(columnNameText);
            this.dataType.set(dataTypeComboBox);
            this.notNull.set(notNullCheckBox);
            this.autoIncrement.set(autoIncrementCheckBox);
            this.key.set(keyComboBox);
            this.defaultValue.set(defaultValueText);
            this.extra.set(extraText);
            this.expression.set(expressionText);
            this.comment.set(commentText);
        }
        
        public TableRow(int rowNum) {
            this.rowNumber = new SimpleIntegerProperty(rowNum);
        }

        public TableRow( String constraintNameText,String columnNameText,String ownerName,  String type, String checkExpression) {
            this.rowNumber = new SimpleIntegerProperty();
			this.constraintName.set(constraintNameText);
            this.columnName.set(columnNameText);
            this.owner.set(ownerName);
            this.type.set(type);
            this.checkExpression.set(checkExpression);
           } 

		public IntegerProperty rowNumberProperty() { return rowNumber; }
        public StringProperty columnNameProperty() { return columnName; }
        public ObjectProperty<String> dataTypeProperty() { return dataType; }
        public BooleanProperty notNullProperty() { return notNull; }
        public BooleanProperty autoIncrementProperty() { return autoIncrement; }
        public ObjectProperty<String> keyProperty() { return key; }
        public StringProperty defaultValueProperty() { return defaultValue; }
        public StringProperty extraProperty() { return extra; }
        public StringProperty expressionProperty() { return expression; }
        public StringProperty commentProperty() { return comment; }
        
        public StringProperty constraintNameProperty() { return constraintName; }
        public StringProperty ownerNameProperty() { return owner; }	       
        public StringProperty typeProperty() { return type; }
        public StringProperty checkExpressionProperty() { return checkExpression; }
    }
    	

    private final ObservableList<TableRow> data = FXCollections.observableArrayList();
    private final ObservableList<String> dataTypeOptions = FXCollections.observableArrayList("BIGINT","BIGINT UNSIGNED","BINARY","BIT","BLOB","BOOL","CHAR","DATE","DATETIME","DECIMAL","DOUBLE","DOUBLE PRECISION","DOUBLE PRECISION UNSIGNED",
			 "DOUBLE UNSIGNED","ENUM","FLOAT","GEOMETRY","GEOMETRY COLLECTION","INT","INT UNSIGNED","INTEGER", "INTEGER UNSIGNED","LINESTRING","LONG VARBINARY","LONG VARCHAR","LONG BLOB",
			 "LONGTEXT","MEDIUMBLOB","MEDIUMINT","MEDIUMINT UNSIGNED","MEDIUMTEXT","MULTILINESTRING","MULTIPOINT","MULTIPOLYGON","NUMERIC","POINT","POLYGON","REAL","SET","SMALLINT","SMALLINT UNSIGNED",
			 "TEXT","TIME","TIMESTAMP","TINYBLOB","TINYINT","TINYINT UNSIGNED","TINYTEXT","VARBINARY","VARCHAR","YEAR","json","varchar(100)");
    private final ObservableList<String> keyOptions = FXCollections.observableArrayList("PRIMARY", "UNIQUE", "FOREIGN");
    
    public ObservableList<TableRow> getData() {
        return data;
    }
    
//        // Text field property
//        public StringProperty textProperty() { return textValue; }
//        public String getText() { return textValue.get(); }
//        public void setText(String value) { textValue.set(value); }
//
//        // Combo box property
//        public ObjectProperty<String> comboProperty() { return comboValue; }
//        public String getCombo() { return comboValue.get(); }
//        public void setCombo(String value) { comboValue.set(value); }
//
//        // Check box property
//        public BooleanProperty checkProperty() { return checkValue; }
//        public boolean isCheck() { return checkValue.get(); }
//        public void setCheck(boolean value) { checkValue.set(value); }
//    }



//    private final ObservableList<String> comboOptions = FXCollections.observableArrayList(
//        "Option 1", "Option 2", "Option 3", "Other"
//    );
//
//    private Callback<TableColumn<TableRow, String>, TableCell<TableRow, String>> createComboBoxCellFactory() {
//        return column -> new TableCell<TableRow, String>() {
//            private final ComboBox<String> comboBox = new ComboBox<>(comboOptions);
//
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    comboBox.setValue(item);
//                    setGraphic(comboBox);
//                    
//                    // Update data model when selection changes
//                    comboBox.setOnAction(event -> {
//                        TableRow row = getTableView().getItems().get(getIndex());
//                        row.setCombo(comboBox.getValue());
//                    });
//                }
//            }
//        };
//    }
//
//    private Callback<TableColumn<TableRow, Boolean>, TableCell<TableRow, Boolean>> createCheckBoxCellFactory() {
//        return column -> new TableCell<TableRow, Boolean>() {
//            private final CheckBox checkBox = new CheckBox();
//
//            @Override
//            protected void updateItem(Boolean item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    checkBox.setSelected(item);
//                    setGraphic(checkBox);
//                    setAlignment(javafx.geometry.Pos.CENTER);
//                    
//                    // Update data model when checkbox changes
//                    checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
//                        TableRow row = getTableView().getItems().get(getIndex());
//                        row.setCheck(newVal);
//                    });
//                }
//            }
//        };
//    }
    
	private Tab getColumnsTab(Tab columnsTab) {

		VBox ColumnsTableVBox = new VBox();
		//ColumnsTableVBox.setId("ColumnsTableVBox");

//        data.add(new TableRow("Sample Text", "Option 1", true));
//        data.add(new TableRow("Another Item 1", "Option 2", false));
//        data.add(new TableRow("Another Item 2", "Option 3", true));

        // Create table columns
//        TableColumn<TableRow, String> nametextCol = new TableColumn<>("Column Name");
//        TableColumn<TableRow, String> dataTypeCol = new TableColumn<>("Data Type");
//        TableColumn<TableRow, String> notNullCol = new TableColumn<>("Not Null");
//        TableColumn<TableRow, String> autoIncrementCol = new TableColumn<>("Auto Increment");
//        TableColumn<TableRow, String> KeyCol = new TableColumn<>("Key");
//        TableColumn<TableRow, String> defaultCol = new TableColumn<>("Default");
//        TableColumn<TableRow, String> extraCol = new TableColumn<>("Extra");
//        TableColumn<TableRow, String> expressionCol = new TableColumn<>("Expression");
//        TableColumn<TableRow, String> commentCol = new TableColumn<>("Comment");
//
//        // Set column widths
//        nametextCol.setPrefWidth(200);
//        KeyCol.setPrefWidth(150);
//        //foreignKeyCol.setPrefWidth(100);
//
//        // Disable sorting for all columns
//        nametextCol.setSortable(false);
//        KeyCol.setSortable(false);
//        //foreignKeyCol.setSortable(false);
//
//        // Text field column
//        nametextCol.setCellValueFactory(cellData -> cellData.getValue().textProperty());
//        nametextCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        nametextCol.setOnEditCommit(event -> {
//            TableRow row = event.getRowValue();
//            row.setText(event.getNewValue());
//        });
//        dataTypeCol.setCellValueFactory(cellData -> cellData.getValue().textProperty());
//        dataTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        dataTypeCol.setOnEditCommit(event -> {
//        	TableRow row = event.getRowValue();
//        	row.setText(event.getNewValue());
//        });
//        KeyCol.setCellValueFactory(cellData -> cellData.getValue().textProperty());
//        KeyCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        KeyCol.setOnEditCommit(event -> {
//        	TableRow row = event.getRowValue();
//        	row.setText(event.getNewValue());
//        });
         
		TableColumn<TableRow, String> colName = new TableColumn<>("Column Name");
		colName.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		colName.setPrefWidth(150);

		// 2. Row Number #
		TableColumn<TableRow, Number> rowNumCol = new TableColumn<>("#");
		rowNumCol.setCellValueFactory(cell -> cell.getValue().rowNumberProperty());
		rowNumCol.setSortable(false);
		rowNumCol.setPrefWidth(100);

		// 3. DataType
		TableColumn<TableRow, String> dataTypeCol = new TableColumn<>("DataType");
		dataTypeCol.setCellValueFactory(cell -> cell.getValue().dataTypeProperty());
		dataTypeCol.setPrefWidth(150);
		//	        dataTypeCol.setCellFactory(col -> {
//	            TableCell<TableRow, String> cell = new TableCell<>() {
//	                private final ComboBox<String> comboBox = new ComboBox<>(dataTypeOptions);
//	                {
//	                    comboBox.setOnAction(e -> {
//	                    	TableRow row = getTableView().getItems().get(getIndex());
//	                        row.dataTypeProperty().set(comboBox.getValue());
//	                    });
//	                }
//	                @Override
//	                protected void updateItem(String item, boolean empty) {
//	                    super.updateItem(item, empty);
//	                    if (empty) {
//	                        setGraphic(null);
//	                    } else {
//	                        comboBox.setValue(item);
//	                        setGraphic(comboBox);
//	                    }
//	                }
//	            };
//	            return cell;
//	        });

	        dataTypeCol.setCellFactory(col -> new TableCell<>() {
	            
	            private final ComboBox<String> comboBox;
	            private final FilteredList<String> filteredItems;

	            {
	                // Wrap original options with a FilteredList
	                filteredItems = new FilteredList<>(dataTypeOptions, s -> true);

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
	                        TableRow row = getTableView().getItems().get(getIndex());
	                        row.dataTypeProperty().set(comboBox.getValue());
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
	        
	        // 4. Not Null
	        TableColumn<TableRow, Boolean> notNullCol = new TableColumn<>("Not Null");
	        notNullCol.setCellValueFactory(cell -> cell.getValue().notNullProperty());
	        notNullCol.setCellFactory(CheckBoxTableCell.forTableColumn(notNullCol));
	        notNullCol.setPrefWidth(75);

	        // 5. Auto Increment
	        TableColumn<TableRow, Boolean> autoIncCol = new TableColumn<>("Auto Increment");
	        autoIncCol.setCellValueFactory(cell -> cell.getValue().autoIncrementProperty());
	        autoIncCol.setCellFactory(CheckBoxTableCell.forTableColumn(autoIncCol));
	        autoIncCol.setPrefWidth(75);

	        // 6. Key
	        TableColumn<TableRow, String> keyCol = new TableColumn<>("Key");
	        keyCol.setCellValueFactory(cell -> cell.getValue().keyProperty());
	        keyCol.setPrefWidth(100);
	        keyCol.setCellFactory(col -> {
	            TableCell<TableRow, String> cell = new TableCell<>() {
	                private final ComboBox<String> comboBox = new ComboBox<>(keyOptions);
	                {
	                    	comboBox.setOnAction(e -> {
	                    	TableRow row = getTableView().getItems().get(getIndex());
	                        row.keyProperty().set(comboBox.getValue());
	                    });
	                }
	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (empty) {
	                        setGraphic(null);
	                    } else {
	                        // If nothing selected, show "NONE"
	                        if (item == null || item.isEmpty()) {
	                            comboBox.setValue("NONE");
	                        } else {
	                            comboBox.setValue(item);
	                        }
	                        setGraphic(comboBox);
	                    }
	                }
//	                protected void updateItem(String item, boolean empty) {
//	                    super.updateItem(item, empty);
//	                    if (empty) {
//	                    	setGraphic(null);
//	                    } else {
//	                        comboBox.setValue(item);
//	                        setGraphic(comboBox);
//	                    }
//	                }
	            };
	            return cell;
	        });

	        // 7. Default
	        TableColumn<TableRow, String> defaultCol = new TableColumn<>("Default");
	        defaultCol.setCellValueFactory(cell -> cell.getValue().defaultValueProperty());
	        defaultCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        defaultCol.setOnEditCommit(e -> e.getRowValue().defaultValueProperty().set(e.getNewValue()));
	        defaultCol.setPrefWidth(150);

	        // 8. ExtraExpression
	        TableColumn<TableRow, String> extraCol = new TableColumn<>("Extra");
	        extraCol.setCellValueFactory(cell -> cell.getValue().extraProperty());
	        extraCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        extraCol.setOnEditCommit(e -> e.getRowValue().extraProperty().set(e.getNewValue()));
	        extraCol.setPrefWidth(150);
	        
	        TableColumn<TableRow, String> expCol = new TableColumn<>("Expression");
	        expCol.setCellValueFactory(cell -> cell.getValue().expressionProperty());
	        expCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        expCol.setOnEditCommit(e -> e.getRowValue().expressionProperty().set(e.getNewValue()));
	        expCol.setPrefWidth(150);

	        // 9. Comment
	        TableColumn<TableRow, String> commentCol = new TableColumn<>("Comment");
	        commentCol.setCellValueFactory(cell -> cell.getValue().commentProperty());
	        commentCol.setCellFactory(TextFieldTableCell.forTableColumn());
	        commentCol.setOnEditCommit(e -> e.getRowValue().commentProperty().set(e.getNewValue()));
	        commentCol.setPrefWidth(150);
		

        // Create table
        TableView<TableRow> table = new TableView<>();
        table.getStyleClass().add("TableView");
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.setEditable(true);
        table.getColumns().addAll(rowNumCol,colName, dataTypeCol,notNullCol, autoIncCol,keyCol,defaultCol,extraCol,expCol,commentCol);
        table.setItems(data);
        table.getItems().clear();
	    
		//Label  neew = new Label("Columns");
//		TableView ColumnsTableView = new TableView();
//		ColumnsTableView.getStyleClass().add("TableView");
		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
//		HBox generatedSQLLabelHBox = new HBox();
//		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
//		Label generatedSQLColumnLabel = new Label("Generated SQL");
//		generatedSQLColumnLabel.setId("generatedSQLLabel");
//		generatedSQLLabelHBox.getChildren().add(generatedSQLColumnLabel);
//		
		HBox buttons1HBox = new HBox();
		buttons1HBox.getStyleClass().add("buttons--hbox");
		//buttons1HBox.setAlignment(Pos.BOTTOM_RIGHT);
		
	    Button addButton = new Button("Add Column");
        Button removeButton = new Button("Remove Selected");

        // Button actions
//        addButton.setOnAction(e -> data.add(new TableRow(0, "New Item", "Option 1", false, false, darkThemeCss, darkThemeCss, darkThemeCss, darkThemeCss)));
//        removeButton.setOnAction(e -> {
//            TableRow selected = table.getSelectionModel().getSelectedItem();
//            if (selected != null) {
//                data.remove(selected);
//            }
//        });
        
        if (data.isEmpty()) {
            data.add(new TableRow("actor_name", data.size() + 1, "VARCHAR(100)", true, false, "PRIMARY", "N/A", "NONE", "", "Stores actor name"));
        }
        
       // data.add(new TableRow("actor_name", data.size() + 1, "VARCHAR(100)", true, false, "PRIMARY", "N/A", "NONE", "", "Stores actor name"));
        addButton.setOnAction(e -> {
        	int newRowNumber = data.size() + 1;
        	//data.add(new TableRow("col_name",newRowNumber,"Char",true,false,"pri","kl","hj","kl","kl"));
        	TableRow newRow = new TableRow(newRowNumber);
        	data.add(newRow);
        });
           

        removeButton.setOnAction(e -> {
            TableRow selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                data.remove(selected);
                // Re-number remaining rows
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).rowNumberProperty().set(i + 1);
                }
            }
        });
        
		Button addColumnButton = new Button ("Add Column");
		addColumnButton.setId("buttons");
		addColumnButton.setOnAction(new CreateNewColumnHandler(this,mysqlui));
	

		buttons1HBox.getChildren().addAll(addButton,removeButton,addColumnButton);
		bottomHBox.getChildren().addAll(spacer,buttons1HBox);
		//bottomHBox.getChildren().addAll(spacer,buttons1HBox);
		//sqlHBox.getChildren().addAll(generatedSQLQueryTextArea,buttons1HBox,buttons2VBox);
		//ColumnsTableVBox.getChildren().addAll(ColumnsTableView,bottomHBox,sqlColumnHBox);
		ColumnsTableVBox.getChildren().addAll(table,bottomHBox);
		columnsTab.setContent(ColumnsTableVBox);
		return columnsTab;
	}

	
	
	 public static class ConstraintsTableRow {
	    	
	    	private final StringProperty constraintName = new SimpleStringProperty("");
	    	private final StringProperty columnName = new SimpleStringProperty("");
	    	private final StringProperty owner = new SimpleStringProperty("");
	    	private final StringProperty type = new SimpleStringProperty("");
	    	private final StringProperty checkExpression = new SimpleStringProperty("");
	    	
	        
	        public ConstraintsTableRow( String constraintNameText,String columnNameText,String ownerName,  String type, String checkExpression) {
	            this.constraintName.set(constraintNameText);
	            this.columnName.set(columnNameText);
	            this.owner.set(ownerName);
	            this.type.set(type);
	            this.checkExpression.set(checkExpression);
	           }      

	        public StringProperty constraintNameProperty() { return constraintName; }
	        public StringProperty columnNameProperty() { return columnName; }
	        public StringProperty ownerNameProperty() { return owner; }	       
	        public StringProperty typeProperty() { return type; }
	        public StringProperty checkExpressionProperty() { return checkExpression; }
	           
	    }
	    	
	    private final ObservableList<ConstraintsTableRow> constraintsData = FXCollections.observableArrayList();
	    private final ObservableList<String> typeOptions = FXCollections.observableArrayList("NONE","PRIMARY KEY","UNIQUE KEY","CHECK");
	   
	    public ObservableList<ConstraintsTableRow> getconstraintsData() {
	        return constraintsData;
	    }
	
	
	private Tab getConstraintsTab(Tab constraintsTab) {

		VBox ConstrainstTableVBox = new VBox();
		//ConstrainstTableVBox.setId("keyBox");
		//Label  neew = new Label("Constraints");
//		TableView ConstraintsTableView = new TableView();
//		ConstraintsTableView.getStyleClass().add("TableView");
		
		TableColumn<ConstraintsTableRow, String> constraintNameColumn = new TableColumn<>("Name");
		constraintNameColumn.setCellValueFactory(cell -> cell.getValue().constraintNameProperty());
		constraintNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		constraintNameColumn.setOnEditCommit(e -> e.getRowValue().constraintNameProperty().set(e.getNewValue()));
		constraintNameColumn.setPrefWidth(150);
		
		TableColumn<ConstraintsTableRow, String> columnNameCol = new TableColumn<>("Column");
		columnNameCol.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
		columnNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		columnNameCol.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		columnNameCol.setPrefWidth(150);
		
		TableColumn<ConstraintsTableRow, String> ownerCol = new TableColumn<>("Owner");
		ownerCol.setCellValueFactory(cell -> cell.getValue().ownerNameProperty());
		ownerCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ownerCol.setOnEditCommit(e -> e.getRowValue().ownerNameProperty().set(e.getNewValue()));
		ownerCol.setPrefWidth(150);
		
		TableColumn<ConstraintsTableRow, String> colType = new TableColumn<>("Type");
		colType.setCellValueFactory(cell -> cell.getValue().typeProperty());
		colType.setCellFactory(TextFieldTableCell.forTableColumn());
		colType.setOnEditCommit(e -> e.getRowValue().typeProperty().set(e.getNewValue()));
		colType.setPrefWidth(100);
		colType.setCellFactory(col -> new TableCell<>() {
            
            private final ComboBox<String> comboBox;
            private final FilteredList<String> filteredItems;

            {
                // Wrap original options with a FilteredList
                filteredItems = new FilteredList<>(typeOptions, s -> true);

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
                    	ConstraintsTableRow row = getTableView().getItems().get(getIndex());
                        row.typeProperty().set(comboBox.getValue());
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
		
		TableColumn<ConstraintsTableRow, String> checkExpCol = new TableColumn<>("Check Expression");
		checkExpCol.setCellValueFactory(cell -> cell.getValue().checkExpressionProperty());
		checkExpCol.setCellFactory(TextFieldTableCell.forTableColumn());
		checkExpCol.setOnEditCommit(e -> e.getRowValue().checkExpressionProperty().set(e.getNewValue()));
		checkExpCol.setPrefWidth(150);
		
		TableView<ConstraintsTableRow> constraintTableView = new TableView<>();
		constraintTableView.getStyleClass().add("TableView");
		constraintTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		constraintTableView.setEditable(true);
		constraintTableView.getColumns().addAll(constraintNameColumn,columnNameCol,ownerCol, colType,checkExpCol);
		constraintTableView.setItems(constraintsData);
		constraintTableView.getItems().clear();

		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
				
		HBox buttonsHBox = new HBox();
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.getStyleClass().add("buttons--hbox");
		Button addConstraintButton = new Button ("Add Constraint");
		addConstraintButton.setId("buttons");
		addConstraintButton.setOnAction(new CreateNewConstraintHandler(this,mysqlui));
		
        
		Button addButton = new Button("Add Row");
		Button removeButton = new Button("Remove Selected");
		
		if (constraintsData.isEmpty()) {
			constraintsData.add(new ConstraintsTableRow("actor_name" ,"VARCHAR(100)", "PRIMARY", "N/A", "NONE"));
        }
		
		addButton.setOnAction(e -> {
        	//int newRowNumber = constraintsData.size() + 1;
        	//data.add(new TableRow("col_name",newRowNumber,"Char",true,false,"pri","kl","hj","kl","kl"));
        	ConstraintsTableRow newRow = new ConstraintsTableRow("enter name","","","","");
        	constraintsData.add(newRow);
        });
           

        removeButton.setOnAction(e -> {
        	ConstraintsTableRow selected = constraintTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
            	constraintsData.remove(selected);
                   }
        });
		
		buttonsHBox.getChildren().addAll(addButton,removeButton,addConstraintButton);
	
		
		bottomHBox.getChildren().addAll(spacer,buttonsHBox);
		ConstrainstTableVBox.getChildren().addAll(constraintTableView,bottomHBox);
		constraintsTab.setContent(ConstrainstTableVBox);
		return constraintsTab;
	}

	
	public static class ForeignKeyRow{
		
		private final StringProperty fkname = new SimpleStringProperty("");
    	private final StringProperty columnsName = new SimpleStringProperty("");
    	private final StringProperty owner = new SimpleStringProperty("");
    	private final StringProperty refTables = new SimpleStringProperty("");
    	private final StringProperty type = new SimpleStringProperty("");
    	private final StringProperty refObject = new SimpleStringProperty("");
    	private final ObjectProperty onDelete = new SimpleObjectProperty<>("");
    	private final ObjectProperty onUpdate = new SimpleObjectProperty<>("");
    	
        
        public ForeignKeyRow( String foreignkeyName,String columnsNameText,String ownerName, String refTables, String type,String refObject, String onDelete, String onUpdate) {
            this.fkname.set(foreignkeyName);
            this.columnsName.set(columnsNameText);
            this.owner.set(ownerName);
            this.refTables.set(refTables);
            this.type.set(type);
            this.refObject.set(refObject);
            this.onDelete.set(onDelete);
            this.onUpdate.set(onUpdate);
           }      

        public StringProperty foreignKeyNameProperty() { return fkname; }
        public StringProperty columnNameProperty() { return columnsName; }
        public StringProperty ownerProperty() { return owner; }	       
        public StringProperty refTablesProperty() { return refTables; }	       
        public StringProperty typeProperty() { return type; }
        public StringProperty refObjectProperty() { return refObject; }
        public ObjectProperty<String> onDeleteProperty() { return onDelete; }
        public ObjectProperty<String> onUpdateProperty() { return onUpdate; }
           
    }
    	
    private final ObservableList<ForeignKeyRow> foreignKeysData = FXCollections.observableArrayList();
    private final ObservableList<String> onDeleteOptions = FXCollections.observableArrayList("No Action","Cascade","Restrict","Set NULL","Set Default");
    private final ObservableList<String> onUpdateOptions = FXCollections.observableArrayList("No Action","Cascade","Restrict","Set NULL","Set Default");
    
   
    public ObservableList<ForeignKeyRow> getForeignKeysData() {
        return foreignKeysData;
    }


	private Tab getForeignKeysTab(Tab foreignKeysTab) {

		VBox foreignKeysTableVBox = new VBox();
		//ConstrainstTableVBox.setId("keyBox");
		//Label  neew = new Label("FK");
//		TableView foreignKeysTableView = new TableView();
//		foreignKeysTableView.getStyleClass().add("TableView");
		
		TableColumn<ForeignKeyRow, String> foreignKeyNameColumn = new TableColumn<>("Name");
		foreignKeyNameColumn.setCellValueFactory(cell -> cell.getValue().foreignKeyNameProperty());
		foreignKeyNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		foreignKeyNameColumn.setOnEditCommit(e -> e.getRowValue().foreignKeyNameProperty().set(e.getNewValue()));
		foreignKeyNameColumn.setPrefWidth(150);
		
		TableColumn<ForeignKeyRow, String> columnNameCol = new TableColumn<>("Column");
		columnNameCol.setCellValueFactory(cell -> cell.getValue().columnNameProperty());
		columnNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		columnNameCol.setOnEditCommit(e -> e.getRowValue().columnNameProperty().set(e.getNewValue()));
		columnNameCol.setPrefWidth(150);
		
		TableColumn<ForeignKeyRow, String> ownerCol = new TableColumn<>("Owner");
		ownerCol.setCellValueFactory(cell -> cell.getValue().ownerProperty());
		ownerCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ownerCol.setOnEditCommit(e -> e.getRowValue().ownerProperty().set(e.getNewValue()));
		ownerCol.setPrefWidth(150);
		
		TableColumn<ForeignKeyRow, String> refTableCol = new TableColumn<>("Ref Table");
		refTableCol.setCellValueFactory(cell -> cell.getValue().refTablesProperty());
		refTableCol.setCellFactory(TextFieldTableCell.forTableColumn());
		refTableCol.setOnEditCommit(e -> e.getRowValue().refTablesProperty().set(e.getNewValue()));
		refTableCol.setPrefWidth(150);
		
		TableColumn<ForeignKeyRow, String> colType = new TableColumn<>("Type");
		colType.setCellValueFactory(cell -> cell.getValue().typeProperty());
		colType.setCellFactory(TextFieldTableCell.forTableColumn());
		colType.setOnEditCommit(e -> e.getRowValue().typeProperty().set(e.getNewValue()));
		colType.setPrefWidth(100);
		
		TableColumn<ForeignKeyRow, String> refObjectCol = new TableColumn<>("Ref Object");
		refObjectCol.setCellValueFactory(cell -> cell.getValue().refObjectProperty());
		refObjectCol.setCellFactory(TextFieldTableCell.forTableColumn());
		refObjectCol.setOnEditCommit(e -> e.getRowValue().refObjectProperty().set(e.getNewValue()));
		refObjectCol.setPrefWidth(150);
		
		TableColumn<ForeignKeyRow, String> onDeleteCol = new TableColumn<>("onDelete");
		onDeleteCol.setCellValueFactory(cell -> cell.getValue().onDeleteProperty());
		onDeleteCol.setPrefWidth(100);
		onDeleteCol.setCellFactory(col -> {
	            TableCell<ForeignKeyRow, String> cell = new TableCell<>() {
	                private final ComboBox<String> comboBox = new ComboBox<>(onDeleteOptions);
	                {
	                    comboBox.setOnAction(e -> {
	                    	ForeignKeyRow row = getTableView().getItems().get(getIndex());
	                        row.onDeleteProperty().set(comboBox.getValue());
	                    });
	                }
	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
//	                    if (empty) {
//	                        setGraphic(null);
//	                    } else {
//	                        comboBox.setValue(item);
//	                        setGraphic(comboBox);
//	                    }
	                    
	                    if (empty) {
	                        setGraphic(null);
	                    } else {
	                        // If nothing selected, show "NONE"
	                        if (item == null || item.isEmpty()) {
	                            comboBox.setValue("No Action");
	                        } else {
	                            comboBox.setValue(item);
	                        }
	                        setGraphic(comboBox);
	                    }
	                }
	            };
	            return cell;
	        });
		
		TableColumn<ForeignKeyRow, String> onUpdateCol = new TableColumn<>("onUpdate");
		onUpdateCol.setCellValueFactory(cell -> cell.getValue().onUpdateProperty());
		onUpdateCol.setPrefWidth(100);
		onUpdateCol.setCellFactory(col -> {
			TableCell<ForeignKeyRow, String> cell = new TableCell<>() {
				private final ComboBox<String> comboBox = new ComboBox<>(onUpdateOptions);
				{
					comboBox.setOnAction(e -> {
						ForeignKeyRow row = getTableView().getItems().get(getIndex());
						row.onUpdateProperty().set(comboBox.getValue());
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
					
//					 if (empty) {
//	                        setGraphic(null);
//	                    } else {
//	                        // If nothing selected, show "NONE"
//	                        if (item == null || item.isEmpty()) {
//	                            comboBox.setValue("No Action");
//	                        } else {
//	                            comboBox.setValue(item);
//	                        }
//	                        setGraphic(comboBox);
//	                    }
				}
			};
			return cell;
		});
		
		TableView<ForeignKeyRow> fkTableView = new TableView<>();
		fkTableView.getStyleClass().add("TableView");
		fkTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		fkTableView.setEditable(true);
		fkTableView.getColumns().addAll(foreignKeyNameColumn,columnNameCol,ownerCol,refTableCol, colType,refObjectCol,onDeleteCol,onUpdateCol);
		fkTableView.setItems(foreignKeysData);
		fkTableView.getItems().clear();
		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
	
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.getStyleClass().add("buttons--hbox");
		Button addforeignKeyButton = new Button ("Add Foreign Key");
		addforeignKeyButton.setOnAction(new CreateNewForeignKeyHandler(this,mysqlui));
		
		Button addButton = new Button("Add Row");
		addButton.setId("buttons");
		addButton.setOnAction(e -> {
        	//int newRowNumber = constraintsData.size() + 1;
        	//data.add(new TableRow("col_name",newRowNumber,"Char",true,false,"pri","kl","hj","kl","kl"));
			ForeignKeyRow newRow = new ForeignKeyRow("default" ," ", " "," "," ", " ", " " ," ");
			foreignKeysData.add(newRow);
        });

		
		Button removeButton = new Button("Remove Selected");
		removeButton.setId("buttons");
        removeButton.setOnAction(e -> {
        	ForeignKeyRow selected = fkTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
            	foreignKeysData.remove(selected);
                   }
        });
        
        
		if (foreignKeysData.isEmpty()) {
			foreignKeysData.add(new ForeignKeyRow("actor_fk" ,"film_id", "actor","film","type", "N/A", "No Action","No Action"));
        }
		
		
		buttonsHBox.getChildren().addAll(addButton,removeButton,addforeignKeyButton);	
		
		bottomHBox.getChildren().addAll(spacer,buttonsHBox);
		foreignKeysTableVBox.getChildren().addAll(fkTableView,bottomHBox);
		foreignKeysTab.setContent(foreignKeysTableVBox);
		return foreignKeysTab;
	}

public static class ReferenceKeyRow{
		
		private final StringProperty referenceName = new SimpleStringProperty("");
    	private final StringProperty owner = new SimpleStringProperty("");
    	private final StringProperty refTables = new SimpleStringProperty("");
    	private final StringProperty type = new SimpleStringProperty("");
    	private final StringProperty refObject = new SimpleStringProperty("");
    	private final ObjectProperty onDeleteReferences = new SimpleObjectProperty<>("");
    	private final ObjectProperty onCascadeReferences = new SimpleObjectProperty<>("");
    	
        
        public ReferenceKeyRow( String referenceName, String ownerName, String refTables, String type,String refObject, String onDeleteReferences, String onCascadeReferences) {
            this.referenceName.set(referenceName);
            this.owner.set(ownerName);
            this.refTables.set(refTables);
            this.type.set(type);
            this.refObject.set(refObject);
            this.onDeleteReferences.set(onDeleteReferences);
            this.onCascadeReferences.set(onCascadeReferences);
           }      

        public StringProperty referenceNameProperty() { return referenceName; }
        public StringProperty ownerProperty() { return owner; }	       
        public StringProperty refTablesProperty() { return refTables; }	       
        public StringProperty typeProperty() { return type; }
        public StringProperty refObjectProperty() { return refObject; }
        public ObjectProperty<String> onDeleteReferencesProperty() { return onDeleteReferences; }
        public ObjectProperty<String> onCascadeReferencesProperty() { return onCascadeReferences; }
           
    }
    	
    private final ObservableList<ReferenceKeyRow> referencesData = FXCollections.observableArrayList();
    private final ObservableList<String> onDeleteReferencesOptions = FXCollections.observableArrayList("No Action","Cascade","Restrict","Set NULL","Set Default");
    private final ObservableList<String> onCascadeReferencesOptions = FXCollections.observableArrayList("No Action","Cascade","Restrict","Set NULL","Set Default");
    
   
    public ObservableList<ReferenceKeyRow> getReferencesData() {
        return referencesData;
    }
	
	
	private Tab getReferencesTab(Tab referencesTab) {

		VBox referencesTableVBox = new VBox();
		//ConstrainstTableVBox.setId("keyBox");
		//Label  neew = new Label("References");
//		TableView referencesTableView = new TableView();
//		referencesTableView.getStyleClass().add("TableView");
		
		TableColumn<ReferenceKeyRow, String> referenceNameColumn = new TableColumn<>("Name");
		referenceNameColumn.setCellValueFactory(cell -> cell.getValue().referenceNameProperty());
		referenceNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		referenceNameColumn.setOnEditCommit(e -> e.getRowValue().referenceNameProperty().set(e.getNewValue()));
		referenceNameColumn.setPrefWidth(150);
				
		TableColumn<ReferenceKeyRow, String> ownerCol = new TableColumn<>("Owner");
		ownerCol.setCellValueFactory(cell -> cell.getValue().ownerProperty());
		ownerCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ownerCol.setOnEditCommit(e -> e.getRowValue().ownerProperty().set(e.getNewValue()));
		ownerCol.setPrefWidth(150);
		
		TableColumn<ReferenceKeyRow, String> refTableCol = new TableColumn<>("Ref Table");
		refTableCol.setCellValueFactory(cell -> cell.getValue().refTablesProperty());
		refTableCol.setCellFactory(TextFieldTableCell.forTableColumn());
		refTableCol.setOnEditCommit(e -> e.getRowValue().refTablesProperty().set(e.getNewValue()));
		refTableCol.setPrefWidth(150);
		
		TableColumn<ReferenceKeyRow, String> colType = new TableColumn<>("Type");
		colType.setCellValueFactory(cell -> cell.getValue().typeProperty());
		colType.setCellFactory(TextFieldTableCell.forTableColumn());
		colType.setOnEditCommit(e -> e.getRowValue().typeProperty().set(e.getNewValue()));
		colType.setPrefWidth(100);
		
		TableColumn<ReferenceKeyRow, String> refObjectCol = new TableColumn<>("Ref Object");
		refObjectCol.setCellValueFactory(cell -> cell.getValue().refObjectProperty());
		refObjectCol.setCellFactory(TextFieldTableCell.forTableColumn());
		refObjectCol.setOnEditCommit(e -> e.getRowValue().refObjectProperty().set(e.getNewValue()));
		refObjectCol.setPrefWidth(150);
		
		TableColumn<ReferenceKeyRow, String> onDeleteCol = new TableColumn<>("onDelete");
		onDeleteCol.setCellValueFactory(cell -> cell.getValue().onDeleteReferencesProperty());
		onDeleteCol.setPrefWidth(100);
		onDeleteCol.setCellFactory(col -> {
	            TableCell<ReferenceKeyRow, String> cell = new TableCell<>() {
	                private final ComboBox<String> comboBox = new ComboBox<>(onDeleteReferencesOptions);
	                {
	                    comboBox.setOnAction(e -> {
	                    	ReferenceKeyRow row = getTableView().getItems().get(getIndex());
	                        row.onDeleteReferencesProperty().set(comboBox.getValue());
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
		
		TableColumn<ReferenceKeyRow, String> onCascadeCol = new TableColumn<>("onCascade");
		onCascadeCol.setCellValueFactory(cell -> cell.getValue().onCascadeReferencesProperty());
		onCascadeCol.setPrefWidth(100);
		onCascadeCol.setCellFactory(col -> {
			TableCell<ReferenceKeyRow, String> cell = new TableCell<>() {
				private final ComboBox<String> comboBox = new ComboBox<>(onCascadeReferencesOptions);
				{
					comboBox.setOnAction(e -> {
						ReferenceKeyRow row = getTableView().getItems().get(getIndex());
						row.onCascadeReferencesProperty().set(comboBox.getValue());
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
		
		TableView<ReferenceKeyRow> referencesTableView = new TableView<>();
		referencesTableView.getStyleClass().add("TableView");
		referencesTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		referencesTableView.setEditable(true);
		referencesTableView.getColumns().addAll(referenceNameColumn,ownerCol,refTableCol, colType,refObjectCol,onDeleteCol,onCascadeCol);
		referencesTableView.setItems(referencesData);
		referencesTableView.getItems().clear();
		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.getStyleClass().add("buttons--hbox");
//		Button addReferencesButton = new Button ("Add Reference");
		//addReferencesButton.setOnAction(new CreateNewColumnHandler(this));
		
		Button removeButton = new Button("Remove Selected");
		removeButton.setId("buttons");
        removeButton.setOnAction(e -> {
        	ReferenceKeyRow selected = referencesTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
            	referencesData.remove(selected);
                   }
        });
        
		buttonsHBox.getChildren().addAll(removeButton);
		
		bottomHBox.getChildren().addAll(spacer,buttonsHBox);
		referencesTableVBox.getChildren().addAll(referencesTableView,bottomHBox);
		referencesTab.setContent(referencesTableVBox);
		return referencesTab;
	}

	
	 public static class TriggersTableRow {
	    	
	    	private final StringProperty triggersName = new SimpleStringProperty("");
	    	private final ObjectProperty<String> timing = new SimpleObjectProperty("");
	    	private final ObjectProperty<String> type = new SimpleObjectProperty("");
	    	private final StringProperty tableName = new SimpleStringProperty("");
	    	private final StringProperty triggerDescription = new SimpleStringProperty("");
	    	
	        
	        public TriggersTableRow( String triggersNameText,String timing,String type,  String tableName, String triggerDescription) {
	            this.triggersName.set(triggersNameText);
	            this.timing.set(timing);
	            this.type.set(type);
	            this.tableName.set(tableName);
	            this.triggerDescription.set(triggerDescription);
	           }      

	        public StringProperty triggersNameProperty() { return triggersName; }
	        public ObjectProperty timingProperty() { return timing; }
	        public ObjectProperty typeProperty() { return type; }
	        public StringProperty tableNameProperty() { return tableName; }	       
	        public StringProperty triggerDescriptionProperty() { return triggerDescription; }
	           
	    }
	    	
	    private final ObservableList<TriggersTableRow> triggersData = FXCollections.observableArrayList();
	    private final ObservableList<String> triggersTiming = FXCollections.observableArrayList("BEFORE","AFTER");
	    private final ObservableList<String> triggersType = FXCollections.observableArrayList("DELETE","INSERT","UPDATE");
	   
	    public ObservableList<TriggersTableRow> gettriggersData() {
	        return triggersData;
	    }
	
	
	private Tab getTriggersTab(Tab triggersTab) {

		VBox triggersTableVBox = new VBox();
		//ConstrainstTableVBox.setId("keyBox");
		//Label  neew = new Label("Triggers");
//		TableView triggersTableView = new TableView();
//		triggersTableView.getStyleClass().add("TableView");
		
		TableColumn<TriggersTableRow, String> triggerNameColumn = new TableColumn<>(" Trigger Name");
		triggerNameColumn.setCellValueFactory(cell -> cell.getValue().triggersNameProperty());
		triggerNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		triggerNameColumn.setOnEditCommit(e -> e.getRowValue().triggersNameProperty().set(e.getNewValue()));
		triggerNameColumn.setPrefWidth(150);
		
		TableColumn<TriggersTableRow, String> timingCol = new TableColumn<>("Timing");
		timingCol.setCellValueFactory(cell -> cell.getValue().timingProperty());
//		timingCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		timingCol.setOnEditCommit(e -> e.getRowValue().timingProperty().set(e.getNewValue()));
		timingCol.setCellFactory(col -> {
            TableCell<TriggersTableRow, String> cell = new TableCell<>() {
                private final ComboBox<String> comboBox = new ComboBox<>(triggersTiming);
                {
                    comboBox.setOnAction(e -> {
                    	TriggersTableRow row = getTableView().getItems().get(getIndex());
                        row.timingProperty().set(comboBox.getValue());
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
		
		timingCol.setPrefWidth(100);
		
		TableColumn<TriggersTableRow, String> typeCol = new TableColumn<>("Type");
		typeCol.setCellValueFactory(cell -> cell.getValue().typeProperty());
//		typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		typeCol.setOnEditCommit(e -> e.getRowValue().typeProperty().set(e.getNewValue()));
		typeCol.setCellFactory(col -> {
			TableCell<TriggersTableRow, String> cell = new TableCell<>() {
				private final ComboBox<String> comboBox = new ComboBox<>(triggersType);
				{
					comboBox.setOnAction(e -> {
						TriggersTableRow row = getTableView().getItems().get(getIndex());
						row.timingProperty().set(comboBox.getValue());
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

		typeCol.setPrefWidth(100);
		
		TableColumn<TriggersTableRow, String> tableNameCol = new TableColumn<>("Table Name");
		tableNameCol.setCellValueFactory(cell -> cell.getValue().tableNameProperty());
		tableNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tableNameCol.setOnEditCommit(e -> e.getRowValue().tableNameProperty().set(e.getNewValue()));
		tableNameCol.setPrefWidth(150);
		
		TableColumn<TriggersTableRow, String> triggerDescriptionCol = new TableColumn<>("Trigger Description");
		triggerDescriptionCol.setCellValueFactory(cell -> cell.getValue().triggerDescriptionProperty());
		triggerDescriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		triggerDescriptionCol.setOnEditCommit(e -> e.getRowValue().triggerDescriptionProperty().set(e.getNewValue()));
		triggerDescriptionCol.setPrefWidth(300);
		
		TableView<TriggersTableRow> triggerTableView = new TableView<>();
		triggerTableView.getStyleClass().add("TableView");
		triggerTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		triggerTableView.setEditable(true);
		triggerTableView.getColumns().addAll(triggerNameColumn,timingCol,typeCol, tableNameCol,triggerDescriptionCol);
		triggerTableView.setItems(triggersData);
		triggerTableView.getItems().clear();
		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
				
		HBox buttonsHBox = new HBox();
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.getStyleClass().add("buttons--hbox");
		Button addTriggersButton = new Button ("Add Trigger");
		addTriggersButton.setOnAction(new CreateNewTriggerHandler(this,mysqlui));
		
		Button addButton = new Button("Add Row");
		addButton.setId("buttons");
		addButton.setOnAction(e -> {
        	//int newRowNumber = constraintsData.size() + 1;
        	//data.add(new TableRow("col_name",newRowNumber,"Char",true,false,"pri","kl","hj","kl","kl"));
			TriggersTableRow newTriggerRow = new TriggersTableRow("default" ," ", " "," "," ");
			triggersData.add(newTriggerRow);
        });

		
		Button removeButton = new Button("Remove Selected");
		addButton.setId("buttons");
        removeButton.setOnAction(e -> {
        	TriggersTableRow selected = triggerTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
            	triggersData.remove(selected);
                   }
        });
        
        
		if (triggersData.isEmpty()) {
			triggersData.add(new TriggersTableRow("default" ,"none", "none","default","null"));
        }
		
		
		buttonsHBox.getChildren().addAll(addButton,removeButton,addTriggersButton);
		
		bottomHBox.getChildren().addAll(spacer,buttonsHBox);
		triggersTableVBox.getChildren().addAll(triggerTableView,bottomHBox);
		triggersTab.setContent(triggersTableVBox);
		return triggersTab;
	}

	 public static class IndexTableRow {
	    	
	    	private final StringProperty indexName = new SimpleStringProperty("");
	    	private final StringProperty columnsName = new SimpleStringProperty("");
	    	private final StringProperty tableName = new SimpleStringProperty("");
	    	private final StringProperty indexType = new SimpleStringProperty("");
	    	private final BooleanProperty ascending = new SimpleBooleanProperty(false);
	    	private final BooleanProperty nullable = new SimpleBooleanProperty(false);
	    	private final BooleanProperty unique = new SimpleBooleanProperty(false);
	    	private final StringProperty extra = new SimpleStringProperty("");
	    	private final StringProperty cardinality = new SimpleStringProperty("");
	    	private final StringProperty comment = new SimpleStringProperty("");
	    	
	        
	        public IndexTableRow( String indexName,String columnsName,String tableName,  String indexType, Boolean ascending, Boolean nullable, Boolean unique, String extra, String cardinality, String comment) {
	            this.indexName.set(indexName);
	            this.columnsName.set(columnsName);
	            this.tableName.set(tableName);
	            this.indexType.set(indexType);
	            this.ascending.set(ascending);
	            this.nullable.set(nullable);
	            this.unique.set(unique);
	            this.extra.set(extra);
	            this.cardinality.set(cardinality);
	            this.comment.set(comment);
	           }      

	        public StringProperty indexNameProperty() { return indexName; }
	        public StringProperty columnsNameProperty() { return columnsName; }
	        public StringProperty tableNameProperty() { return tableName; }
	        public StringProperty indexTypeProperty() { return indexType; }
	        public BooleanProperty ascendingProperty() { return ascending; }
	        public BooleanProperty nullableProperty() { return nullable; }
	        public BooleanProperty uniqueProperty() { return unique; }	       
	        public StringProperty extraProperty() { return extra; }
	        public StringProperty cardinalityProperty() { return cardinality; }
	        public StringProperty commentProperty() { return comment; }
	           
	    }
	    	
	    private final ObservableList<IndexTableRow> indexData = FXCollections.observableArrayList();
		private final ObservableList<String> typeData = FXCollections.observableArrayList("BTREE","Full Text","Hash","RTree");
//	    private final ObservableList<String> triggersTiming = FXCollections.observableArrayList("BEFORE","AFTER");
//	    private final ObservableList<String> triggersType = FXCollections.observableArrayList("DELETE","INSERT","UPDATE");
	   
	    public ObservableList<IndexTableRow> getindexData() {
	        return indexData;
	    }
	
	private Tab getIndexesTab(Tab indexesTab) {

		VBox indexesTableVBox = new VBox();
		//ConstrainstTableVBox.setId("keyBox");
		//Label  neew = new Label("Indexes");
//		TableView indexesTableView = new TableView();
//		indexesTableView.getStyleClass().add("TableView");
		
		TableColumn<IndexTableRow, String> indexNameColumn = new TableColumn<>("Index Name");
		indexNameColumn.setCellValueFactory(cell -> cell.getValue().indexNameProperty());
		indexNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		indexNameColumn.setOnEditCommit(e -> e.getRowValue().indexNameProperty().set(e.getNewValue()));
		indexNameColumn.setPrefWidth(150);
		
		TableColumn<IndexTableRow, String> columnName = new TableColumn<>("Column");
		columnName.setCellValueFactory(cell -> cell.getValue().columnsNameProperty());
		columnName.setCellFactory(TextFieldTableCell.forTableColumn());
		columnName.setOnEditCommit(e -> e.getRowValue().columnsNameProperty().set(e.getNewValue()));
		columnName.setPrefWidth(150);
		
		TableColumn<IndexTableRow, String> tableNameColumn = new TableColumn<>("Table");
		tableNameColumn.setCellValueFactory(cell -> cell.getValue().tableNameProperty());
		tableNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		tableNameColumn.setOnEditCommit(e -> e.getRowValue().tableNameProperty().set(e.getNewValue()));
		tableNameColumn.setPrefWidth(150);
		
		TableColumn<IndexTableRow, String> indexTypeColumn = new TableColumn<>("Index Type");
		indexTypeColumn.setCellValueFactory(cell -> cell.getValue().indexTypeProperty());
//		indexNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		indexNameColumn.setOnEditCommit(e -> e.getRowValue().indexNameProperty().set(e.getNewValue()));
		indexTypeColumn.setCellFactory(col -> {
            TableCell<IndexTableRow, String> cell = new TableCell<>() {
                private final ComboBox<String> comboBox = new ComboBox<>(typeData);
                {
                    comboBox.setOnAction(e -> {
                    	IndexTableRow row = getTableView().getItems().get(getIndex());
                        row.indexTypeProperty().set(comboBox.getValue());
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
		indexTypeColumn.setPrefWidth(150);
		
		TableColumn<IndexTableRow, Boolean> ascendingCol = new TableColumn<>("Ascending");
		ascendingCol.setCellValueFactory(cell -> cell.getValue().ascendingProperty());
		ascendingCol.setCellFactory(CheckBoxTableCell.forTableColumn(ascendingCol));
		ascendingCol.setPrefWidth(100);
		
		TableColumn<IndexTableRow, Boolean> nullableCol = new TableColumn<>("Nullable");
		nullableCol.setCellValueFactory(cell -> cell.getValue().nullableProperty());
		nullableCol.setCellFactory(CheckBoxTableCell.forTableColumn(nullableCol));
		nullableCol.setPrefWidth(100);
		
		TableColumn<IndexTableRow, Boolean> uniqueCol = new TableColumn<>("Unique");
		uniqueCol.setCellValueFactory(cell -> cell.getValue().uniqueProperty());
		uniqueCol.setCellFactory(CheckBoxTableCell.forTableColumn(uniqueCol));
		uniqueCol.setPrefWidth(100);
		
		TableColumn<IndexTableRow, String> extraColumn = new TableColumn<>("Extra");
		extraColumn.setCellValueFactory(cell -> cell.getValue().extraProperty());
		extraColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		extraColumn.setOnEditCommit(e -> e.getRowValue().extraProperty().set(e.getNewValue()));
		extraColumn.setPrefWidth(150);
		
		TableColumn<IndexTableRow, String> cardinalityColumn = new TableColumn<>("Cardinality");
		cardinalityColumn.setCellValueFactory(cell -> cell.getValue().cardinalityProperty());
		cardinalityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		cardinalityColumn.setOnEditCommit(e -> e.getRowValue().cardinalityProperty().set(e.getNewValue()));
		cardinalityColumn.setPrefWidth(150);
		
		TableColumn<IndexTableRow, String> commentColumn = new TableColumn<>("Comment");
		commentColumn.setCellValueFactory(cell -> cell.getValue().commentProperty());
		commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		commentColumn.setOnEditCommit(e -> e.getRowValue().commentProperty().set(e.getNewValue()));
		commentColumn.setPrefWidth(150);
		
		TableView<IndexTableRow> indexTableView = new TableView<>();
		indexTableView.getStyleClass().add("TableView");
		indexTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		indexTableView.setEditable(true);
		indexTableView.getColumns().addAll(indexNameColumn,columnName,tableNameColumn, indexTypeColumn,ascendingCol,nullableCol,uniqueCol,extraColumn,cardinalityColumn,commentColumn);
		indexTableView.setItems(indexData);
		indexTableView.getItems().clear();

		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");		
				
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.getStyleClass().add("buttons--hbox");
		Button addIndexesButton = new Button ("Add Index");
		addIndexesButton.setOnAction(new CreateNewIndexHandler(this, mysqlui));
		
		Button addButton = new Button("Add Row");
		addButton.setId("buttons");
		addButton.setOnAction(e -> {
        	//int newRowNumber = constraintsData.size() + 1;
        	//data.add(new TableRow("col_name",newRowNumber,"Char",true,false,"pri","kl","hj","kl","kl"));
			IndexTableRow newIndexRow = new IndexTableRow("default","default ", "default "," ",true,false,true," "," "," ");
			indexData.add(newIndexRow);
        });

		
		Button removeButton = new Button("Remove Selected");
		addButton.setId("buttons");
        removeButton.setOnAction(e -> {
        	IndexTableRow selected = indexTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
            	indexData.remove(selected);
                   }
        });
        
        
		if (indexData.isEmpty()) {
			indexData.add(new IndexTableRow("default" ,"none", "none","default",true,true,true,"-","-","-"));
        }
		buttonsHBox.getChildren().addAll(addButton,removeButton,addIndexesButton);
		
		bottomHBox.getChildren().addAll(spacer,buttonsHBox);
		indexesTableVBox.getChildren().addAll(indexTableView,bottomHBox);
		indexesTab.setContent(indexesTableVBox);
		return indexesTab;
	}

	
	public static class PartitionTableRow {

	    private final StringProperty partitionName = new SimpleStringProperty("");
	    private final IntegerProperty position = new SimpleIntegerProperty(0);
	    private final StringProperty method = new SimpleStringProperty("");
	    private final StringProperty engine = new SimpleStringProperty("");
	    private final StringProperty expression = new SimpleStringProperty("");
	    private final IntegerProperty autoIncrement = new SimpleIntegerProperty(0);
	    private final StringProperty description = new SimpleStringProperty("");
	    private final LongProperty tableRows = new SimpleLongProperty(0);
	    private final LongProperty avgRowLength = new SimpleLongProperty(0);
	    private final LongProperty maxDataLength = new SimpleLongProperty(0);
	    private final LongProperty indexLength = new SimpleLongProperty(0);
	    private final LongProperty dataFree = new SimpleLongProperty(0);
	    private final LongProperty checksum = new SimpleLongProperty(0);
	    private final StringProperty comment = new SimpleStringProperty("");
	    private final StringProperty node = new SimpleStringProperty("");
	    private final StringProperty group = new SimpleStringProperty("");
	    private final BooleanProperty partitioned = new SimpleBooleanProperty(false);

	    public PartitionTableRow(String partitionName,int position,String method, String engine,String expression, int autoIncrement, String description,
	                             long tableRows,long avgRowLength,long maxDataLength,long indexLength,long dataFree,long checksum,String comment,
	                             String node,String group, boolean partitioned) {
	        this.partitionName.set(partitionName);
	        this.position.set(position);
	        this.method.set(method);
	        this.engine.set(engine);
	        this.expression.set(expression);
	        this.autoIncrement.set(autoIncrement);
	        this.description.set(description);
	        this.tableRows.set(tableRows);
	        this.avgRowLength.set(avgRowLength);
	        this.maxDataLength.set(maxDataLength);
	        this.indexLength.set(indexLength);
	        this.dataFree.set(dataFree);
	        this.checksum.set(checksum);
	        this.comment.set(comment);
	        this.node.set(node);
	        this.group.set(group);
	        this.partitioned.set(partitioned);
	    }

	    // Getters for Property access in TableView bindings
	    public StringProperty partitionNameProperty() { return partitionName; }
	    public IntegerProperty positionProperty() { return position; }
	    public StringProperty methodProperty() { return method; }
	    public StringProperty engineProperty() { return engine; }
	    public StringProperty expressionProperty() { return expression; }
	    public IntegerProperty autoIncrementProperty() { return autoIncrement; }
	    public StringProperty descriptionProperty() { return description; }
	    public LongProperty tableRowsProperty() { return tableRows; }
	    public LongProperty avgRowLengthProperty() { return avgRowLength; }
	    public LongProperty maxDataLengthProperty() { return maxDataLength; }
	    public LongProperty indexLengthProperty() { return indexLength; }
	    public LongProperty dataFreeProperty() { return dataFree; }
	    public LongProperty checksumProperty() { return checksum; }
	    public StringProperty commentProperty() { return comment; }
	    public StringProperty nodeProperty() { return node; }
	    public StringProperty groupProperty() { return group; }
	    public BooleanProperty partitionedProperty() { return partitioned; }
	    }
	
	private final ObservableList<PartitionTableRow> partitionData = FXCollections.observableArrayList();
	public ObservableList<PartitionTableRow> getpartitionData() {
		return partitionData;
	}
	
	private Tab getPartitionsTab(Tab partitionsTab) {

		VBox partitionsTableVBox = new VBox();
		//ConstrainstTableVBox.setId("keyBox");
		//Label  neew = new Label("Partitions");
		
		TableColumn<PartitionTableRow, String> partitionNameCol = new TableColumn<>("Partition Name");
		partitionNameCol.setCellValueFactory(cell -> cell.getValue().partitionNameProperty());
		partitionNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		partitionNameCol.setOnEditCommit(e -> e.getRowValue().partitionNameProperty().set(e.getNewValue()));

		TableColumn<PartitionTableRow, Number> positionCol = new TableColumn<>("Position");
		positionCol.setCellValueFactory(cell -> cell.getValue().positionProperty());
		//positionCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		//positionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		positionCol.setOnEditCommit(e -> e.getRowValue().positionProperty().set(e.getNewValue().intValue()));

		TableColumn<PartitionTableRow, String> methodCol = new TableColumn<>("Method");
		methodCol.setCellValueFactory(cell -> cell.getValue().methodProperty());
		methodCol.setCellFactory(TextFieldTableCell.forTableColumn());
		methodCol.setOnEditCommit(e -> e.getRowValue().methodProperty().set(e.getNewValue()));

		TableColumn<PartitionTableRow, String> engineCol = new TableColumn<>("Engine");
		engineCol.setCellValueFactory(cell -> cell.getValue().engineProperty());
		engineCol.setCellFactory(TextFieldTableCell.forTableColumn());
		engineCol.setOnEditCommit(e -> e.getRowValue().engineProperty().set(e.getNewValue()));

		TableColumn<PartitionTableRow, String> expressionCol = new TableColumn<>("Expression");
		expressionCol.setCellValueFactory(cell -> cell.getValue().expressionProperty());
		expressionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		expressionCol.setOnEditCommit(e -> e.getRowValue().expressionProperty().set(e.getNewValue()));

		// Auto Increment (Long)
		TableColumn<PartitionTableRow, Number> autoIncrementCol = new TableColumn<>("Auto Increment");
		autoIncrementCol.setCellValueFactory(cell -> cell.getValue().autoIncrementProperty());
//		autoIncrementCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
//		autoIncrementCol.setOnEditCommit(e -> e.getRowValue().autoIncrementProperty().set(e.getNewValue().longValue()));

		TableColumn<PartitionTableRow, String> descriptionCol = new TableColumn<>("Description");
		descriptionCol.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
		descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		descriptionCol.setOnEditCommit(e -> e.getRowValue().descriptionProperty().set(e.getNewValue()));

		// Long fields
		TableColumn<PartitionTableRow, Number> tableRowsCol = new TableColumn<>("Table Rows");
		tableRowsCol.setCellValueFactory(cell -> cell.getValue().tableRowsProperty());
//		tableRowsCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		tableRowsCol.setOnEditCommit(e -> e.getRowValue().tableRowsProperty().set(e.getNewValue().longValue()));

		TableColumn<PartitionTableRow, Number> avgRowLengthCol = new TableColumn<>("Avg Row Length");
		avgRowLengthCol.setCellValueFactory(cell -> cell.getValue().avgRowLengthProperty());
//		avgRowLengthCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		avgRowLengthCol.setOnEditCommit(e -> e.getRowValue().avgRowLengthProperty().set(e.getNewValue().longValue()));

		TableColumn<PartitionTableRow, Number> maxDataLengthCol = new TableColumn<>("Max Data Length");
		maxDataLengthCol.setCellValueFactory(cell -> cell.getValue().maxDataLengthProperty());
		//maxDataLengthCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		maxDataLengthCol.setOnEditCommit(e -> e.getRowValue().maxDataLengthProperty().set(e.getNewValue().longValue()));

		TableColumn<PartitionTableRow, Number> indexLengthCol = new TableColumn<>("Index Length");
		indexLengthCol.setCellValueFactory(cell -> cell.getValue().indexLengthProperty());
		//indexLengthCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		indexLengthCol.setOnEditCommit(e -> e.getRowValue().indexLengthProperty().set(e.getNewValue().longValue()));

		TableColumn<PartitionTableRow, Number> dataFreeCol = new TableColumn<>("Data Free");
		dataFreeCol.setCellValueFactory(cell -> cell.getValue().dataFreeProperty());
		//dataFreeCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		dataFreeCol.setOnEditCommit(e -> e.getRowValue().dataFreeProperty().set(e.getNewValue().longValue()));

		TableColumn<PartitionTableRow, Number> checksumCol = new TableColumn<>("Checksum");
		checksumCol.setCellValueFactory(cell -> cell.getValue().checksumProperty());
		//checksumCol.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		checksumCol.setOnEditCommit(e -> e.getRowValue().checksumProperty().set(e.getNewValue().longValue()));

		TableColumn<PartitionTableRow, String> commentCol = new TableColumn<>("Comment");
		commentCol.setCellValueFactory(cell -> cell.getValue().commentProperty());
		commentCol.setCellFactory(TextFieldTableCell.forTableColumn());
		commentCol.setOnEditCommit(e -> e.getRowValue().commentProperty().set(e.getNewValue()));

		TableColumn<PartitionTableRow, String> nodeCol = new TableColumn<>("Node");
		nodeCol.setCellValueFactory(cell -> cell.getValue().nodeProperty());
		nodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nodeCol.setOnEditCommit(e -> e.getRowValue().nodeProperty().set(e.getNewValue()));

		TableColumn<PartitionTableRow, String> groupCol = new TableColumn<>("Group");
		groupCol.setCellValueFactory(cell -> cell.getValue().groupProperty());
		groupCol.setCellFactory(TextFieldTableCell.forTableColumn());
		groupCol.setOnEditCommit(e -> e.getRowValue().groupProperty().set(e.getNewValue()));

		// Boolean field with CheckBox
		TableColumn<PartitionTableRow, Boolean> partitionedCol = new TableColumn<>("Partitioned");
		partitionedCol.setCellValueFactory(cell -> cell.getValue().partitionedProperty());
		partitionedCol.setCellFactory(CheckBoxTableCell.forTableColumn(partitionedCol));
		partitionedCol.setEditable(true);
		
		TableView partitionsTableView = new TableView();
		partitionsTableView.getStyleClass().add("TableView");
		partitionsTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		partitionsTableView.setEditable(true);
		partitionsTableView.getColumns().addAll(partitionNameCol,positionCol,methodCol, engineCol,expressionCol,autoIncrementCol,descriptionCol,tableRowsCol,avgRowLengthCol,maxDataLengthCol,
				indexLengthCol,dataFreeCol,checksumCol,commentCol,nodeCol,groupCol,partitionedCol);
		partitionsTableView.setItems(partitionData);
		partitionsTableView.getItems().clear();
		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonsHBox.getStyleClass().add("buttons--hbox");
		//Button addPartionButton = new Button ("Add Partition");
		//addPartionButton.setOnAction(new CreateNewColumnHandler(this));
		
		Button removeButton = new Button("Remove Selected");
		removeButton.setId("buttons");
//        removeButton.setOnAction(e -> {
//        	PartitionTableRow selected = (PartitionTableRow) partitionsTableView.getSelectionModel().getSelectedItem();
//            if (selected != null) {
//            	partitionData.remove(selected);
//                   }
//        });
        
        
		buttonsHBox.getChildren().addAll(removeButton);			
		bottomHBox.getChildren().addAll(spacer,buttonsHBox);
		partitionsTableVBox.getChildren().addAll(partitionsTableView,bottomHBox);
		partitionsTab.setContent(partitionsTableVBox);
		return partitionsTab;
	}
   
	
	private void updateTableSQLQuery() {
		
		String tableName = tablenameValueField.getText();
		String engineName =engineFieldComboBox.getValue() ;
		String autoIncrement = autoincrementField.getText();
		String charset = charsetdropdown.getValue();
		String collation = collationComboBox.getValue();
	    String partition =partitionedCheckbox.isSelected() ? "PARTITIONED" : " ";
	    
	    String tableSQL = "CREATE TABLE [IF NOT EXISTS] ";
	    
	    if(tableName != null && !tableName.isEmpty()) {
	    	tableSQL+= tableName;
	    	tableSQL+= " (\n" + "\n" + ")\n";
	    }
	    
	    if(tableName != null && !tableName.isEmpty()) {
	    	if (engineName != null && !engineName.isEmpty()) {
	    		tableSQL += "ENGINE NAME = "+ engineName +"\n";
	    	}

	    	if (autoIncrement != null && !autoIncrement.isEmpty()) {
	    		tableSQL += "AUTO INCREMENT = "+  autoIncrement +"\n";
	    	}
	    	
	    	if (charset != null && !charset.isEmpty()) {
	    		tableSQL += "DEFAULT CHARSET = "+ charset +"\n";
	    	}
	    	
	    	if (collation != null && !collation.isEmpty()) {
	    		tableSQL += "COLLATE = "+ collation +"\n";
	    	}
	    	
	    	if (partition != null && !partition.isEmpty()) {
	    		tableSQL += partition;
	    	}
	    	
	    	generatedSQLQueryTextAreaGeneral.setText(tableSQL);
	    	System.out.println(tableSQL);
	    }

	}
	


	    
	}


