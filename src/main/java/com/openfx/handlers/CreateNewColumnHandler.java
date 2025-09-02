package com.openfx.handlers;

import javax.swing.text.Element;


import org.openjfx.fx.Menu_Items_FX;

import com.openfx.handlers.CreateNewTableHandler.ConstraintsTableRow;
import com.openfx.handlers.CreateNewTableHandler.TableRow;
import com.openfx.ui.MySqlUI;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateNewColumnHandler implements EventHandler<ActionEvent> {

	public String whiteThemeCss = CreateNewColumnHandler.class.getResource("/whiteTheme.css").toExternalForm();
	public String darkThemeCss = CreateNewColumnHandler.class.getResource("/darkTheme.css").toExternalForm();
	public String selectedTheme = whiteThemeCss;
	public MySqlUI mysqlui;
	public Scene scene;
	public Stage stage;
	public SplitPane splitPane;
	public Menu_Items_FX menu_Items_FX;
	public Stage primaryStage;
	public EditCommentTableHandler editCommentTableHandler;
	public CreateNewTableHandler createNewTableHandler;
	public TextField columnNameTextField;
	public int rowNumber;
	public ComboBox<String> datatypeComboBox;
	public ComboBox<String> typeComboBox;
	public CheckBox autoIncreementCheckBox ;
	public CheckBox notnullCheckBox ;
	public TextField keyvalueTextField;
	public TextField defaultvalueTextField;
	public TextField expressionvalueTextField;
	public TextField extravalueTextField;
	public TextArea commentvalueTextField;
	

	public CreateNewColumnHandler(CreateNewTableHandler createNewTableHandler,MySqlUI mysqlui) {
		
		this.createNewTableHandler = createNewTableHandler;
		this.mysqlui = mysqlui;
	// TODO Auto-generated constructor stub
}
	
	public CreateNewColumnHandler(EditCommentTableHandler editCommentTableHandler,MySqlUI mysqlui) {
		
		this.editCommentTableHandler = editCommentTableHandler;
		this.mysqlui = mysqlui;
		// TODO Auto-generated constructor stub
	}

	 
	private String columnName;
	private String dataType;
	private boolean isNotNull;
	private boolean autoIncrement;
	private String key;
	private String defaultValue;
	private String extra;
	private String expression;
	private String comment;	
	private TableRow newRow;
	
	ObservableList<String> dataTypeitems = FXCollections.observableArrayList(
		    "DOUBLE UNSIGNED", "ENUM", "FLOAT", "GEOMETRY", "GEOMETRY COLLECTION", "INT", 
		    "INT UNSIGNED", "INTEGER", "INTEGER UNSIGNED", "LINESTRING", "LONG VARBINARY", 
		    "LONG VARCHAR", "LONG BLOB", "LONGTEXT", "MEDIUMBLOB", "MEDIUMINT", 
		    "MEDIUMINT UNSIGNED", "MEDIUMTEXT", "MULTILINESTRING", "MULTIPOINT", 
		    "MULTIPOLYGON", "NUMERIC", "POINT", "POLYGON", "REAL", "SET", "SMALLINT", 
		    "SMALLINT UNSIGNED", "TEXT", "TIME", "TIMESTAMP", "TINYBLOB", "TINYINT", 
		    "TINYINT UNSIGNED", "TINYTEXT", "VARBINARY", "VARCHAR", "YEAR", "json", 
		    "varchar(100)"
		);
	public TextField tablenameValueField;

//private ObservableList<TableRow> data = FXCollections.observableArrayList();
//	 public CreateNewColumnHandler(ObservableList<TableRow> data) {
//		 this.data = data;
//	 }

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	    createNewTableHandler.createNewColumnHandler = this;
		// System.out.println("tabs opend ie creae table");
		 
//		SplitPane splitPane = new SplitPane();
//		splitPane.setId("SplitPane");
//		splitPane.setDividerPositions(0.21);
		// mysqlui.scene = new Scene(splitPane, 500, 400);
		
		 VBox newColumnTabVBox = new VBox(); 
		 
		 VBox.setVgrow(newColumnTabVBox, Priority.ALWAYS);
		 
		 VBox addColumnLabelVbox = new VBox();
		 addColumnLabelVbox.getStyleClass().add("addColumnLabelVbox");
		 Label addColumnLabel = new Label("Add Column"); 		 
		 addColumnLabelVbox.getChildren().addAll(addColumnLabel);
		 
		 VBox columnPropertiesVbox = new VBox();
		 columnPropertiesVbox.getStyleClass().add("propertiesVbox");
		 
		 Label propertiesLabel = new Label("Properties");
		 HBox nameValueHbox = new HBox();
		 nameValueHbox.getStyleClass().add("nameValueHbox");
		 Label columnNameLabel = new Label("Name* : ");
		 columnNameLabel.setId("labels");
		 columnNameTextField = new TextField();
		 columnNameTextField.getStyleClass().add("Valuetextfield");
		 nameValueHbox.getChildren().addAll(columnNameLabel,columnNameTextField);
	 
		 HBox datatypeHBox = new HBox();
		 datatypeHBox.getStyleClass().add("datatypeHBox");
		 Label dataTypeLabel = new Label("Data Type* :");
		 dataTypeLabel.setId("labels");
		 datatypeComboBox = new ComboBox<>();
		 datatypeComboBox.setId("descriptiontextfield");
		 datatypeComboBox.setEditable(true);
//		 datatypeComboBox.getItems().addAll("BIGINT","BIGINT UNSIGNED","BINARY","BIT","BLOB","BOOL","CHAR","DATE","DATETIME","DECIMAL","DOUBLE","DOUBLE PRECISION","DOUBLE PRECISION UNSIGNED",
//				 "DOUBLE UNSIGNED","ENUM","FLOAT","GEOMETRY","GEOMETRY COLLECTION","INT","INT UNSIGNED","INTEGER", "INTEGER UNSIGNED","LINESTRING","LONG VARBINARY","LONG VARCHAR","LONG BLOB",
//				 "LONGTEXT","MEDIUMBLOB","MEDIUMINT","MEDIUMINT UNSIGNED","MEDIUMTEXT","MULTILINESTRING","MULTIPOINT","MULTIPOLYGON","NUMERIC","POINT","POLYGON","REAL","SET","SMALLINT","SMALLINT UNSIGNED",
//				 "TEXT","TIME","TIMESTAMP","TINYBLOB","TINYINT","TINYINT UNSIGNED","TINYTEXT","VARBINARY","VARCHAR","YEAR","json","varchar(100)");
		 
		 FilteredList<String> filteredItems = new FilteredList<>(dataTypeitems, s -> true);
		 datatypeComboBox.setItems(filteredItems);
	        
	        // Handle key events for filtering
		 datatypeComboBox.getEditor().setOnKeyReleased(keyEvent -> {
	            String input = datatypeComboBox.getEditor().getText().toLowerCase();
	            
	            // Filter items based on input
	            filteredItems.setPredicate(item -> 
	                item != null && item.toLowerCase().contains(input)
	            );
	            
	            // Show dropdown only if there are matching items
	            if (filteredItems.isEmpty()) {
	            	datatypeComboBox.hide();
	            } else {
	            	datatypeComboBox.show();
	            }
	        });

	        // Handle selection to reset filter when an item is chosen
		 datatypeComboBox.setOnHidden(keyEvent -> {
	            String selected = datatypeComboBox.getSelectionModel().getSelectedItem();
	            if (selected != null) {
	            	datatypeComboBox.getEditor().setText(selected);
	                // Reset filter to show all items next time
	                filteredItems.setPredicate(s -> true);
	            }
	        });
		 datatypeHBox.getChildren().addAll(dataTypeLabel,datatypeComboBox);
		 
		 HBox checkboxHBox = new HBox();
		 checkboxHBox.setId("checkboxHBox");
		 notnullCheckBox = new CheckBox("Not Null");
		 //notnull.setId("notnullcheckbox");
		 autoIncreementCheckBox = new CheckBox("Auto Increment");
		 //autoIncreement.setId("autoIncreementcheckbox");
		 checkboxHBox.getChildren().addAll(notnullCheckBox,autoIncreementCheckBox);
		 
		 HBox defaultHbox = new HBox();
		 defaultHbox.getStyleClass().add("defaultHbox");
		 Label defaultLabel = new Label("Default : ");
		 defaultLabel.setId("labels");
		 defaultvalueTextField = new TextField();
		 defaultvalueTextField.getStyleClass().add("Valuetextfield");
		 defaultHbox.getChildren().addAll(defaultLabel,defaultvalueTextField);
		 
		 HBox extraHbox = new HBox();
		 extraHbox.getStyleClass().add("extraHbox");
		 Label extraLabel = new Label("Extra : ");
		 extraLabel.setId("labels");
		 extravalueTextField = new TextField();
		 extravalueTextField.getStyleClass().add("Valuetextfield");
		 extraHbox.getChildren().addAll(extraLabel,extravalueTextField);
		 
		 HBox expressionHbox = new HBox();
		 expressionHbox.getStyleClass().add("expressionHbox");
		 Label expressionLabel = new Label("Expression : ");
		 expressionLabel.setId("labels");
		 expressionvalueTextField = new TextField();
		 expressionvalueTextField.getStyleClass().add("Valuetextfield");
		 expressionHbox.getChildren().addAll(expressionLabel,expressionvalueTextField);
		 
		 HBox charsethbox = new HBox();
		 charsethbox.getStyleClass().add("expressionHbox");
		 Label  charsetlabel = new Label("Charset :");
		 charsetlabel.setId("labels");
		 ComboBox<String> charsetComboBox = new ComboBox();
		 charsetComboBox.getItems().addAll("Primary Key","Unique Key");
		 Label  collationlabel = new Label("Collation :");
		 ComboBox<String> collationComboBox = new ComboBox();
		 collationComboBox.getItems().addAll("Primary Key","Unique Key");
		 charsethbox.getChildren().addAll(charsetlabel,charsetComboBox,collationlabel,collationComboBox);
		 
		 columnPropertiesVbox.getChildren().addAll(propertiesLabel,nameValueHbox,datatypeHBox,checkboxHBox,defaultHbox,extraHbox,expressionHbox,charsethbox);
		 
		 
		 VBox keysVBox = new VBox();
		 keysVBox.setId("keysVBox");
		 
		 TabPane keyandOtherstabpane = new TabPane();		 
		 Tab keyTab = new Tab("Keys");
		 VBox keyBox = new VBox();
		 keyBox.setId("keyBox");
		 
		 HBox keyshbox = new HBox();
		 keyshbox.getStyleClass().add("expressionHbox");
		 CheckBox keycheckbox = new CheckBox("Key");
		 Label typelabel = new Label("Type :");
		 typeComboBox = new ComboBox();
		 typeComboBox.setDisable(!keycheckbox.isSelected());
		 typeComboBox.getItems().addAll("PRIMARY","UNIQUE");
		 
//		 if(!uniquecheckbox.isSelected()) {
//			 typeComboBox.setDisable(true);
//		 }else {
//			 typeComboBox.setDisable(false);
//		 }
		 
		 keycheckbox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
			    typeComboBox.setDisable(!isSelected);
			});
		 keyshbox.getChildren().addAll(keycheckbox,typelabel,typeComboBox);
		 
		 
		 HBox keynameValueHbox = new HBox();
		 keynameValueHbox.getStyleClass().add("nameValueHbox");
		 Label keynameLabel = new Label("Name : ");
		 keynameLabel.setId("labels");
		 keyvalueTextField = new TextField();
		 keyvalueTextField.getStyleClass().add("Valuetextfield");
		 keynameValueHbox.getChildren().addAll(keynameLabel,keyvalueTextField);
		 
		 typeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			 updateConstraintsKeyName();
		    });

		    // Alternatively, using setOnAction (also works for selection change)
		 typeComboBox.setOnAction(selectionEvent -> {
			 updateConstraintsKeyName();
		    });

		    // Existing focus lost listener (keep if needed)
		 typeComboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    	if (!newValue) { // focus lost
		    		updateConstraintsKeyName();
		    	}
		    });
		 
		 keyBox.getChildren().addAll(keyshbox,keynameValueHbox);
		 
		 //this.mysqlui.currentConnection.createStatement().execute(columnName)
		 
		 keyTab.setContent(keyBox);
		 keyTab.setClosable(false);
		 
		 Tab othersTab = new Tab("Comment");
		 VBox othersBox = new VBox();
		 othersBox.setId("othersBox");
		 
		 HBox commentsHbox = new HBox();
		 commentsHbox.getStyleClass().add("commentsHbox");
		 Label commentsLabel = new Label("Comment :");
		// commentsLabel.setId("labels");
		 commentvalueTextField = new TextArea();
		 commentvalueTextField.getStyleClass().add("Commenttextfield");
//		 commentvalueField.setScrollLeft(Double.MIN_VALUE);
//		 commentvalueField.setScrollTop(Double.MIN_VALUE);
		 
//		 Button editButton = new Button("Edit");
//		 editButton.setId("buttons");
//		 editButton.setOnAction(new EditCommentTableHandler(this, mysqlui));
		 commentsHbox.getChildren().addAll(commentsLabel,commentvalueTextField);
		 
		 othersBox.getChildren().addAll(commentsHbox);
		 othersTab.setContent(othersBox);
		 othersTab.setClosable(false);
		 
		 keyandOtherstabpane.getTabs().addAll(keyTab,othersTab);
		 keysVBox.getChildren().addAll(keyandOtherstabpane);
		 
		 HBox buttonsHBox = new HBox();
		 buttonsHBox.getStyleClass().add("buttons--hbox");
		 Button okButton = new Button("OK");
		 okButton.setId("buttons");
		 okButton.setDisable(true);
		 
		 //OK button will be disabled until name and datatype values are not entered.
		 ChangeListener<Object> formValidator = (obs, oldVal, newVal) -> {
			 boolean isNameFilled = !columnNameTextField.getText().trim().isEmpty();
			 boolean isDataTypeSelected = datatypeComboBox.getValue() != null && !datatypeComboBox.getValue().trim().isEmpty();
			 okButton.setDisable(!(isNameFilled && isDataTypeSelected));
		 };

		 // Attach listener to text field and combo box
		 columnNameTextField.textProperty().addListener(formValidator);
		 datatypeComboBox.valueProperty().addListener(formValidator);
		 	 
		 
		 int rowNumber =  createNewTableHandler.getData().size() + 1;
		 okButton.setOnAction(e -> {

			    TableRow newRow = new TableRow(
			    	columnNameTextField.getText(),
			        rowNumber,
			        datatypeComboBox.getValue(),
			        notnullCheckBox.isSelected(),
			        autoIncreementCheckBox.isSelected(),
			        typeComboBox.getValue(),
			        defaultvalueTextField.getText(),
			        extravalueTextField.getText(),
			        expressionvalueTextField.getText(),
			        commentvalueTextField.getText()
			    );

			    ConstraintsTableRow newR = new ConstraintsTableRow(
			    		keyvalueTextField.getText(),
			    		columnNameTextField.getText(),
			    		"nmnm",
			    		typeComboBox.getValue(),
			    		expressionvalueTextField.getText()
			    		);
			    
			    createNewTableHandler.getData().add(newRow);
			    createNewTableHandler.getconstraintsData().add(newR);
			    stage.close();
			});
		 
		 
//		 final TableRow[] result = new TableRow[1];
//
//	        okButton.setOnAction(e -> {
//	            result[0] = new TableRow(
//	                rowNumber,
//	                columnNameTextField.getText(),
//	                datatypeComboBox.getValue(),
//	                notnullCheckBox.isSelected(),
//	                autoIncreementCheckBox.isSelected(),
//	                keyvalueTextField.getText(),
//	                defaultvalueTextField.getText(),
//	                extravalueTextField.getText(),
//	                expressionvalueTextField.getText(),
//	                commentvalueTextField.getText()
//	              
////	                int newRowNumber = data.size() + 1;
////	                TableRow newRow = new TableRow(
////	                    newRowNumber, columnName, dataType, isNotNull, autoIncrement,
////	                    key, defaultValue, extra, expression, comment
////	            );
//	            data.add(newRow);
//	            //return result[0];
//	            stage.close();
//	            
//	        });

	       
		 
//		 okButton.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				stage.close();
//			}
//		});
		 
		 Button cancelButton = new Button("Cancel");
		 cancelButton.setId("buttons");		
		 
		 cancelButton.setOnAction(e -> {
	            
	            stage.close();
	        });
		 
		 
//		 
//		 cancelButton.setOnAction(new EventHandler<ActionEvent>() {
//				
//				@Override
//				public void handle(ActionEvent event) {
//					// TODO Auto-generated method stub
//					stage.close();
//				}
//			});
//			 
		 buttonsHBox.getChildren().addAll(cancelButton,okButton);
		 
		
		 newColumnTabVBox.getChildren().addAll(addColumnLabelVbox,columnPropertiesVbox,keysVBox,buttonsHBox);
		 
		 createNewTableHandler.scene = new Scene(newColumnTabVBox, 500, 550);
		 createNewTableHandler.scene.getStylesheets().add(createNewTableHandler.selectedTheme);
		 stage = new Stage();
		 stage.initModality(Modality.APPLICATION_MODAL);
		// stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		 stage.setTitle("Create New Column");
		 stage.setWidth(525);
		 stage.setHeight(530);
		 stage.setScene( createNewTableHandler.scene);
		      
		
		 stage.show();
	}
	
	public void updateConstraintsKeyName() {
	   // String tableName = tablenameValueField.getText(); // <-- passed from CreateNewTableHandler
	    String type = typeComboBox.getValue();

	 //   if (tableName != null && !tableName.isBlank() && type != null) {
	        if ("PRIMARY".equals(type)) {
	            keyvalueTextField.setText( "_pk");
	        } else if ("UNIQUE".equals(type)) {
	            keyvalueTextField.setText("_uni");
	        }
	    }
	}
//}
