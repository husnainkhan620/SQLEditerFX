package com.openfx.handlers;


import com.openfx.handlers.CreateNewTableHandler.TriggersTableRow;
import com.openfx.ui.MySqlUI;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateNewTriggerHandler implements EventHandler<ActionEvent> {

	public CreateNewTableHandler createNewTableHandler;
	public Stage stage;
	public MySqlUI mysqlui;
	public TextField triggerNameField;
	public TextField tablenameValueField;
	public ComboBox<String> timingTypeComboBox;
	public ComboBox<String> typeComboBox ;
	public TextArea generatedSQLQueryTextArea; 
	public TextArea triggerdescriptionTextArea ; 
	

	public CreateNewTriggerHandler(CreateNewTableHandler createNewTableHandler,MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.createNewTableHandler = createNewTableHandler;
		this.mysqlui = mysqlui;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		createNewTableHandler.createNewTriggerHandler = this;

		VBox newTriggerTabVBox = new VBox(); 	
		//newTriggerTabVBox.getStyleClass().add("newTriggerTabVBox");
		//VBox.setVgrow(newTableTabVBox, Priority.ALWAYS);

		HBox triggerPropertiesHbox = new HBox();
		triggerPropertiesHbox.getStyleClass().add("triggerPropertiesHbox");

		VBox leftTriggerPropertiesVbox = new VBox();
		leftTriggerPropertiesVbox.getStyleClass().add("leftpropertiesVbox");
		
		HBox tablenameValueHbox = new HBox();
		tablenameValueHbox.getStyleClass().add("tablenameHbox");
		Label tablenameLabel = new Label("Table Name :");
		tablenameLabel.setId("createTriggerTabLabels");
		tablenameValueField = new TextField();
		tablenameValueField.getStyleClass().add("triggerValuetextfield");
		tablenameValueField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        updateTriggerSQL();
		    }
		});

		// FOCUS LOST LISTENER for intervalValueTextField
		tablenameValueField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		    	updateTriggerSQL();
		    }
		});
		
		tablenameValueHbox.getChildren().addAll(tablenameLabel,tablenameValueField);

		HBox triggerNameHbox = new HBox();
		triggerNameHbox.getStyleClass().add("triggerNameHbox");
		Label triggerNameLabel = new Label("Trigger Name* :");
		triggerNameLabel.setId("createTriggerTabLabels");
		triggerNameField = new TextField();
		triggerNameField.getStyleClass().add("triggerValuetextfield");
		triggerNameField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        updateTriggerSQL();
		    }
		});

		// FOCUS LOST LISTENER for intervalValueTextField
		triggerNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		    	updateTriggerSQL();
		    }
		});
		triggerNameHbox.getChildren().addAll(triggerNameLabel,triggerNameField);

		HBox timingValuehbox = new HBox();
		timingValuehbox.getStyleClass().add("timingValueHBox");
		Label timingLabel = new Label("Timing :");
		timingLabel.setId("createTriggerTabLabels");
		//TextField timingTextField = new TextField();
		timingTypeComboBox = new ComboBox<>();
		timingTypeComboBox.getStyleClass().add("triggerValuetextfield");
		timingTypeComboBox.getItems().addAll("BEFORE","AFTER");	
		timingTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateTriggerSQL();
		});

		// Alternatively, using setOnAction (also works for selection change)
		timingTypeComboBox.setOnAction(selectionEvent -> {
			updateTriggerSQL();
		});

		// Existing focus lost listener (keep if needed)
		timingTypeComboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) { // focus lost
				updateTriggerSQL();
			}
		});
		timingValuehbox.getChildren().addAll(timingLabel,timingTypeComboBox);
		
		HBox typeValuehbox = new HBox();
		typeValuehbox.getStyleClass().add("typeValueHBox");
		Label typeLabel = new Label("Type :");
		typeLabel.setId("createTriggerTabLabels");
		//TextField typeTextField = new TextField();
		typeComboBox = new ComboBox<>();
		typeComboBox.getStyleClass().add("triggerValuetextfield");
		typeComboBox.getItems().addAll("DELETE","INSERT","UPDATE");
		typeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateTriggerSQL();
		});

		// Alternatively, using setOnAction (also works for selection change)
		typeComboBox.setOnAction(selectionEvent -> {
			updateTriggerSQL();
		});

		// Existing focus lost listener (keep if needed)
		typeComboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) { // focus lost
				updateTriggerSQL();
			}
		});
		typeValuehbox.getChildren().addAll(typeLabel,typeComboBox);

		leftTriggerPropertiesVbox.getChildren().addAll(tablenameValueHbox,triggerNameHbox,timingValuehbox,typeValuehbox);

		VBox rightTriggerpropertiesVbox = new VBox();
		rightTriggerpropertiesVbox.getStyleClass().add("rightpropertiesVbox");

		HBox clientCharsethbox = new HBox();
		clientCharsethbox.getStyleClass().add("clientCharsethbox");
		Label clientCharsetlabel = new Label("Client Charset :");
		clientCharsetlabel.setId("createTriggerTabRightVBoxLabels");
		TextField clientCharsetTextField = new TextField();
		clientCharsetTextField.getStyleClass().add("triggerValuetextfield");
		clientCharsetTextField.setEditable(false);
		clientCharsethbox.getChildren().addAll(clientCharsetlabel,clientCharsetTextField);
		
		
		HBox sqlModehbox = new HBox();
		sqlModehbox.getStyleClass().add("sqlModehbox");
		Label sqlModelabel = new Label("Sql Mode :");
		sqlModelabel.setId("createTriggerTabRightVBoxLabels");
		TextField sqlModeTextField = new TextField();
		sqlModeTextField.getStyleClass().add("triggerValuetextfield");
		sqlModeTextField.setEditable(false);
		sqlModehbox.getChildren().addAll(sqlModelabel,sqlModeTextField);

		HBox triggerdescriptionValuehbox = new HBox();
		triggerdescriptionValuehbox.getStyleClass().add("triggerdescriptionValuehbox");
		Label triggerdescriptionLabel = new Label("Trigger Description :");
		triggerdescriptionLabel.setId("createTriggerTabRightVBoxLabels");
		triggerdescriptionTextArea = new TextArea();
		triggerdescriptionTextArea.getStyleClass().add("triggerdescriptionTextArea");
		triggerdescriptionTextArea.setWrapText(true);
		triggerdescriptionValuehbox.getChildren().addAll(triggerdescriptionLabel,triggerdescriptionTextArea);

		
		rightTriggerpropertiesVbox.getChildren().addAll(clientCharsethbox,sqlModehbox,triggerdescriptionValuehbox);

		triggerPropertiesHbox.getChildren().addAll(leftTriggerPropertiesVbox,rightTriggerpropertiesVbox);
		
		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox generatedSQLLabelHBox = new HBox();
		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
		Label generatedSQLColumnLabel = new Label("Generated SQL");
		generatedSQLColumnLabel.setId("generatedSQLLabel");
		generatedSQLLabelHBox.getChildren().add(generatedSQLColumnLabel);

		HBox buttons1HBox = new HBox();
		buttons1HBox.getStyleClass().add("buttons--hbox");
		Button okButton = new Button("OK");
		okButton.setId("buttons");
		okButton.setDisable(true);

//		okButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				stage.close();
//			}
//		});

				
		okButton.setOnAction(e -> {
			TriggersTableRow newTriggerRow = new TriggersTableRow(
					triggerNameField.getText(),
					timingTypeComboBox.getValue(),
					typeComboBox.getValue(),
					tablenameValueField.getText(),
					triggerdescriptionTextArea.getText()
					);
			createNewTableHandler.gettriggersData().add(newTriggerRow);
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

		buttons1HBox.getChildren().addAll(okButton,cancelButton);
		
		HBox sqlColumnHBox = new HBox();	
		sqlColumnHBox.getStyleClass().add("sqlHBox");
		generatedSQLQueryTextArea = new TextArea();		
		generatedSQLQueryTextArea.getStyleClass().add("generatedSQLQueryTextArea");	
		//HBox.setHgrow(generatedSQLQueryTextArea, Priority.ALWAYS);
		Button sqlQueryButton = new Button("Complete SQL");
		sqlQueryButton.setId("sqlbutton");
		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(generatedSQLQueryTextArea));
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//			));
		//sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));	
		
		Button executeSqlButton = new Button("Execute SQL");
		executeSqlButton.setId("sqlbutton");
		executeSqlButton.setDisable(true);
		
		ChangeListener<Object> formValidator = (obs, oldVal, newVal) -> {
			 boolean isNameFilled = !triggerNameField.getText().trim().isEmpty();
			 boolean isgeneratedSQLQueryTextAreaFilled = !generatedSQLQueryTextArea.getText().trim().isEmpty();
			 okButton.setDisable(!isNameFilled);
			 executeSqlButton.setDisable((!isNameFilled && !isgeneratedSQLQueryTextAreaFilled));
		 };

		 // Attach listener to text field and combo box
		 triggerNameField.textProperty().addListener(formValidator);
		 generatedSQLQueryTextArea.textProperty().addListener(formValidator);
		
		VBox buttonWrapper = new VBox(sqlQueryButton,executeSqlButton);
		buttonWrapper.setAlignment(Pos.CENTER);		
		buttonWrapper.setId("buttonWrapper");
		sqlColumnHBox.getChildren().addAll(generatedSQLQueryTextArea,buttonWrapper);
		
		bottomHBox.getChildren().addAll(generatedSQLLabelHBox,spacer,buttons1HBox);

    	newTriggerTabVBox.getChildren().addAll(triggerPropertiesHbox,bottomHBox,sqlColumnHBox);
		createNewTableHandler.createNewTriggerScene = new Scene(newTriggerTabVBox, 500, 550);
		createNewTableHandler.createNewTriggerScene.getStylesheets().add(createNewTableHandler.selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		// stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("Create New Trigger");
		stage.setWidth(770);
		stage.setHeight(440);
		stage.setScene( createNewTableHandler.createNewTriggerScene);

		stage.show();
	}
    
	private void updateTriggerSQL() {
		String tablename = tablenameValueField.getText();
	    String triggerName = triggerNameField.getText();
	    String triggerTiming = timingTypeComboBox.getValue();
	    String triggerType = typeComboBox.getValue();

	    String triggerSQL = "CREATE TRIGGER [IF NOT EXISTS] ";

	    if (triggerName != null && !triggerName.isEmpty()) {
	    	triggerSQL += triggerName+"\n";
	    }

	    if (triggerName != null && !triggerName.isEmpty()) {
	    	if (triggerTiming != null && !triggerTiming.isEmpty()) {
	    		triggerSQL += triggerTiming + " ";
	    	}

	    	if (triggerType != null && !triggerType.isEmpty()) {
	    		triggerSQL += triggerType + "\n" ;
	    		triggerSQL += "ON ";
	    	}

	    	if (tablename != null && !tablename.isEmpty()) {
	    		triggerSQL += tablename +"\n"; 
	    		triggerSQL +="FOR EACH ROW \nBEGIN \n \nEND;";
	    	}

	    	generatedSQLQueryTextArea.setText(triggerSQL);
	    	System.out.println(triggerSQL);
	    }
	}
}
