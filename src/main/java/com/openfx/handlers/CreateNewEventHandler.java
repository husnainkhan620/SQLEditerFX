package com.openfx.handlers;

import com.openfx.ui.MySqlUI;

import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateNewEventHandler implements EventHandler<ActionEvent> {

	public MySqlUI mysqlui;
	public Stage stage;
	public TextArea generatedSQLQueryTextAreaEvent;
	public TextField eventNameValueTextField;
	public TextField eventTypeTextField;
	public TextField intervalValueTextField;
	public TextField intervalFieldTextField;
	public String eventType;
	public String intervalValue;
	public String intervalField;
	
	public CreateNewEventHandler(MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.mysqlui = mysqlui;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		mysqlui.createNewEventHandler = this;
		VBox newEventTabVBox = new VBox(); 		 
		//VBox.setVgrow(newTableTabVBox, Priority.ALWAYS);

		HBox propertiesHbox = new HBox();
		propertiesHbox.getStyleClass().add("propertiesHbox");

		VBox leftpropertiesEventsVbox = new VBox();
		leftpropertiesEventsVbox.getStyleClass().add("leftpropertiesVbox");
	    
		HBox eventnameValueHbox = new HBox();
		eventnameValueHbox.getStyleClass().add("eventnameValueHbox");
		Label eventnameLabel = new Label("Event Name* : ");
		eventnameLabel.setId("createEventTabLabels");
		eventNameValueTextField = new TextField();
		eventNameValueTextField.getStyleClass().add("Valuetextfield");
		eventNameValueTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        updateEventSQL();
		    }
		});

		// FOCUS LOST LISTENER for eventNameValueTextField
		eventNameValueTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		        updateEventSQL();
		    }
		});
		
//		eventNameValueTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent keyEvent) {
//				// TODO Auto-generated method stub
//				String eventName = eventNameValueTextField.getText();
//				String eventSQL = "CREATE EVENT [IF NOT EXISTS] "+ eventName;
//				 
//				if(keyEvent.getCode().toString().equals("ENTER")) {
//					if(eventName != null && !eventName.isEmpty()) {
//						eventSQL +="\nON SCHEDULE ";
////						eventSQL += eventType;
////						eventSQL += intervalValue;
////						eventSQL += intervalField;						
//						
//					}
//				}
//				generatedSQLQueryTextAreaEvent.setText(eventSQL);
//				System.out.println(eventSQL);
//			}
//			
//		});	
//		
//		eventNameValueTextField.focusedProperty().addListener((observable, oldValue , newValue) -> {
//			if (!newValue) { // focus lost
//		        String eventName = eventNameValueTextField.getText();
//
//		        if (eventName != null && !eventName.isEmpty()) {
//		            String eventSQL = "CREATE EVENT [IF NOT EXISTS] " + eventName;
//		            eventSQL +="\nON SCHEDULE ";
//		            
//		            generatedSQLQueryTextAreaEvent.setText(eventSQL);
//		            System.out.println(eventSQL);
//		        }
//		    }
//		});
		eventnameValueHbox.getChildren().addAll(eventnameLabel,eventNameValueTextField);

		HBox eventTypeHBox = new HBox();
		eventTypeHBox.getStyleClass().add("eventTypeHBox");
		Label eventTypeLabel = new Label("Type: ");
		eventTypeLabel.setId("createEventTabLabels");
		eventTypeTextField = new TextField();
		eventTypeTextField.getStyleClass().add("Valuetextfield");
		eventTypeTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        updateEventSQL();
		    }
		});

		// FOCUS LOST LISTENER for eventTypeTextField
		eventTypeTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		        updateEventSQL();
		    }
		});
		
//		eventTypeTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent keyEvent) {
//				// TODO Auto-generated method stub
//				String eventName = eventNameValueTextField.getText();
//				eventType = eventTypeTextField.getText();
//				String eventSQL = "CREATE EVENT [IF NOT EXISTS]"+ eventName + "\nON SCHEDULE ";	
//								
//					if(eventName != null && !eventName.isEmpty()) {
//						//eventSQL += eventName + "\nON SCHEDULE ";						
//						eventSQL += eventType;
//					}			
//				generatedSQLQueryTextAreaEvent.setText(eventSQL);
//				System.out.println(eventSQL);
//			}
//		});
//		
//		eventTypeTextField.focusedProperty().addListener((observable, oldValue , newValue) -> {
//			if (!newValue) { // focus lost
//		        String eventName = eventNameValueTextField.getText();
//
//		        if (eventName != null && !eventName.isEmpty()) {
//		        	String eventSQL = "CREATE EVENT [IF NOT EXISTS] "+ eventName + "\nON SCHEDULE ";
//					eventSQL += eventType;
//				
//		            generatedSQLQueryTextAreaEvent.setText(eventSQL);
//		            System.out.println(eventSQL);
//		        }
//		    }
//		});		
		eventTypeHBox.getChildren().addAll(eventTypeLabel,eventTypeTextField);


		HBox executeAtValuehbox = new HBox();
		executeAtValuehbox.getStyleClass().add("executeAtValuehbox");
		Label executeAtLabel = new Label("Execute At : ");
		executeAtLabel.setId("createEventTabLabels");
		TextField executeAtTextField = new TextField();
		executeAtTextField.getStyleClass().add("Valuetextfield");
		executeAtValuehbox.getChildren().addAll(executeAtLabel,executeAtTextField);

		leftpropertiesEventsVbox.getChildren().addAll(eventnameValueHbox,eventTypeHBox,executeAtValuehbox);


		VBox rightpropertiesEventsVbox = new VBox();
		rightpropertiesEventsVbox.getStyleClass().add("rightpropertiesVbox");

		HBox intervalValuehbox = new HBox();
		intervalValuehbox.getStyleClass().add("intervalValuehbox");
		Label intervalValuelabel = new Label("Interval Value: ");
		intervalValuelabel.setId("createEventTabLabels");
		intervalValueTextField = new TextField();
		intervalValueTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        updateEventSQL();
		    }
		});

		// FOCUS LOST LISTENER for intervalValueTextField
		intervalValueTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		        updateEventSQL();
		    }
		});
		
//		intervalValueTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent keyEvent) {
//				// TODO Auto-generated method stub
//				String eventName = eventNameValueTextField.getText();
//				intervalValue = intervalValueTextField.getText();
//				String eventSQL = "CREATE EVENT [IF NOT EXISTS] "+ eventName + "\nON SCHEDULE "+ eventType + " " ;	
//
//				if((eventName != null && !eventName.isEmpty()) && (eventType != null && !eventType.isEmpty())){
//					
//					
//					eventSQL += intervalValue;
//				}
//
//				generatedSQLQueryTextAreaEvent.setText(eventSQL);
//				System.out.println(eventSQL);
//			}
//		});
//
//		intervalValueTextField.focusedProperty().addListener((observable, oldValue , newValue) -> {
//			if (!newValue) { // focus lost
//		        String eventName = eventNameValueTextField.getText();
//
//		        if((eventName != null && !eventName.isEmpty()) && (eventType != null && !eventName.isEmpty())) {
//		        	String eventSQL = "CREATE EVENT [IF NOT EXISTS] "+ eventName + "\nON SCHEDULE "+ eventType+ " " ;	
//		       
//					eventSQL += intervalValue;
//		            generatedSQLQueryTextAreaEvent.setText(eventSQL);
//		            System.out.println(eventSQL);
//		        }
//		    }
//		});
		
		
		intervalValueTextField.getStyleClass().add("Valuetextfield");
		
	
	
		intervalValuehbox.getChildren().addAll(intervalValuelabel,intervalValueTextField);
		
		HBox intervalFieldHBox = new HBox();
		intervalFieldHBox.getStyleClass().add("intervalFieldHBox");
		Label intervalFieldLabel = new Label("Interval Field: ");
		intervalFieldLabel.setId("createEventTabLabels");
		intervalFieldTextField = new TextField();
		intervalFieldTextField.getStyleClass().add("Valuetextfield");
		intervalFieldTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent keyEvent) {
		        updateEventSQL();
		    }
		});

		// FOCUS LOST LISTENER for intervalFieldTextField
		intervalFieldTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) { // focus lost
		        updateEventSQL();
		    }
		});
		
//		intervalFieldTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent keyEvent) {
//				// TODO Auto-generated method stub
//				String eventName = eventNameValueTextField.getText();
//				intervalField = intervalFieldTextField.getText();
//				
//				String eventSQL = "CREATE EVENT [IF NOT EXISTS] "+ eventName + "\nON SCHEDULE "+ eventType + " " + intervalValue +" " ;	
//
//				if((eventName != null && !eventName.isEmpty()) && (eventType != null && !eventType.isEmpty())){
//					eventSQL += intervalField;
//					
//				}
//				generatedSQLQueryTextAreaEvent.setText(eventSQL);
//				System.out.println(eventSQL);
//			}
//			
//		});
//		
//		intervalFieldTextField.focusedProperty().addListener((observable, oldValue , newValue) -> {
//			if (!newValue) { // focus lost
//		        String eventName = eventNameValueTextField.getText();
//
//		        if((eventName != null && !eventName.isEmpty()) && (eventType != null && !eventName.isEmpty())) {
//		        	String eventSQL = "CREATE EVENT [IF NOT EXISTS] "+ eventName + "\nON SCHEDULE "+ eventType+ " "+ intervalValue +" " ;	
//		        	eventSQL += intervalField;
//					
//		            generatedSQLQueryTextAreaEvent.setText(eventSQL);
//		            System.out.println(eventSQL);
//		        }
//		    }
//		});
		intervalFieldHBox.getChildren().addAll(intervalFieldLabel,intervalFieldTextField);
		
		Region VSpacer = new Region();
		VBox.setVgrow(VSpacer, Priority.ALWAYS);

		//rightpropertiesVbox.getChildren().addAll(updatablehbox,checkOptionHbox,definerHBox);
		rightpropertiesEventsVbox.getChildren().addAll(intervalValuehbox,intervalFieldHBox,VSpacer);

		propertiesHbox.getChildren().addAll(leftpropertiesEventsVbox,rightpropertiesEventsVbox);
		//propertiesHbox.getChildren().addAll(leftpropertiesVbox);
			
		TabPane eventtabletabpane = new TabPane();
		eventtabletabpane.getStyleClass().add("eventtabletabpane");
		eventtabletabpane.setSide(Side.LEFT);
		eventtabletabpane.setRotateGraphic(true);

		Tab detailsTab = new Tab();
		detailsTab.setClosable(false);
		Label labelL = new Label("Details");
		labelL.setRotate(90);
		StackPane stp = new StackPane(new Group(labelL));
		detailsTab.setGraphic(stp);
		detailsTab = getDetailsTab(detailsTab);
		
//		Tab descriptionTab = new Tab();
//		descriptionTab.setClosable(false);
//		labelL = new Label("Description");
//		labelL.setRotate(90);
//		stp = new StackPane(new Group(labelL));
//		descriptionTab.setGraphic(stp);
//		descriptionTab = getDescriptionTab(descriptionTab);

		HBox generatedSQLLabelHBox = new HBox();
		generatedSQLLabelHBox.getStyleClass().add("generatedSQLLabelHBox");
		Label generatedSQLEventLabel = new Label("Generated SQL");
		generatedSQLEventLabel.setId("generatedSQLLabel");
		generatedSQLLabelHBox.getChildren().add(generatedSQLEventLabel);
		
		HBox sqlEventsHBox = new HBox();	
		sqlEventsHBox.getStyleClass().add("sqlHBox");
		generatedSQLQueryTextAreaEvent = new TextArea();		
		generatedSQLQueryTextAreaEvent.getStyleClass().add("generatedSQLQueryTextArea");	
		
		Button sqlQueryButton = new Button("Complete SQL");
		sqlQueryButton.setId("buttons");
		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(generatedSQLQueryTextAreaEvent));
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//			));
		
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
			boolean iseventNameValueTextFieldFilled = !eventNameValueTextField.getText().trim().isEmpty();
			boolean isgeneratedSQLQueryTextAreaEventFilled= !generatedSQLQueryTextAreaEvent.getText().trim().isEmpty();
			executeSqlButton.setDisable((!isgeneratedSQLQueryTextAreaEventFilled && !iseventNameValueTextFieldFilled));
			okButton.setDisable(!iseventNameValueTextFieldFilled);
		};

		// Attach listener to text field and combo box
		eventNameValueTextField.textProperty().addListener(formValidator);
		generatedSQLQueryTextAreaEvent.textProperty().addListener(formValidator);
		
		VBox buttonWrapper = new VBox(sqlQueryButton,executeSqlButton);
		buttonWrapper.setAlignment(Pos.CENTER);
		buttonWrapper.setId("buttonWrapper");
		
		VBox buttonWrapper1 = new VBox(cancelButton,okButton);
		buttonWrapper1.setAlignment(Pos.CENTER);
		buttonWrapper1.setId("buttonWrapper");
		
	//	sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
		sqlEventsHBox.getChildren().addAll(generatedSQLQueryTextAreaEvent,buttonWrapper,buttonWrapper1);
		
		newEventTabVBox.getChildren().addAll(propertiesHbox,eventtabletabpane,generatedSQLLabelHBox,sqlEventsHBox);
		eventtabletabpane.getTabs().addAll(detailsTab);
				
		mysqlui.createNewEventScene = new Scene(newEventTabVBox, 650, 370);
		mysqlui.createNewEventScene.getStylesheets().add(mysqlui.selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("Create New Event");
		stage.setWidth(850);
		stage.setHeight(630);
		stage.setScene( mysqlui.createNewEventScene);

		stage.show();
	}
	
	private Tab getDetailsTab(Tab detailsTab) {

		VBox detailsTableVBox = new VBox();
		//ColumnsTableVBox.setId("ColumnsTableVBox");
		TableView detailsTableView = new TableView();	
		detailsTableView.getStyleClass().add("TableView");

		HBox bottomHBox = new HBox();
		bottomHBox.setId("bottomHBox");
					
			
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox buttons1HBox = new HBox();
		buttons1HBox.getStyleClass().add("buttons--hbox");
		buttons1HBox.setAlignment(Pos.BOTTOM_RIGHT);				
		
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
		buttons1HBox.getChildren().addAll(cancelButton,okButton);	
		
		
//		HBox sqlEventsHBox = new HBox();	
//		sqlEventsHBox.getStyleClass().add("sqlHBox");
//		TextArea generatedSQLQueryTextArea = new TextArea();		
//		generatedSQLQueryTextArea.getStyleClass().add("generatedSQLQueryTextArea");	
//		
//		Button sqlQueryButton = new Button("SQL");
//		sqlQueryButton.setId("buttons");
//		sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(
//			    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));"
//			));
//		VBox buttonWrapper = new VBox(sqlQueryButton);
//		buttonWrapper.setAlignment(Pos.CENTER);
//	//	sqlQueryButton.setOnAction(new GeneratedSQLQueryHandler(this));
//		sqlEventsHBox.getChildren().addAll(generatedSQLQueryTextArea,buttonWrapper);
		
		bottomHBox.getChildren().addAll(spacer,buttons1HBox);
		//sqlHBox.getChildren().addAll(generatedSQLQueryTextArea,buttons1HBox,buttons2VBox);
		detailsTableVBox.getChildren().addAll(detailsTableView,bottomHBox);
		detailsTab.setContent(detailsTableVBox);
		return detailsTab;
	}
	
	
	private void updateEventSQL() {
	    String eventName = eventNameValueTextField.getText();
	    String eventType = eventTypeTextField.getText();
	    String intervalValue = intervalValueTextField.getText();
	    String intervalField = intervalFieldTextField.getText();

	    String eventSQL = "CREATE EVENT [IF NOT EXISTS] ";

	    if (eventName != null && !eventName.isEmpty()) {
	        eventSQL += eventName;
	        eventSQL += "\nON SCHEDULE ";
	    }
        
	    if (eventName != null && !eventName.isEmpty()) {
	    	if (eventType != null && !eventType.isEmpty()) {
	    		eventSQL += eventType + " ";
	    	}

	    	if (intervalValue != null && !intervalValue.isEmpty()) {
	    		eventSQL += intervalValue + " ";
	    	}

	    	if (intervalField != null && !intervalField.isEmpty()) {
	    		eventSQL += intervalField;
	    	}

	    	generatedSQLQueryTextAreaEvent.setText(eventSQL);
	    	System.out.println(eventSQL);
	    }
	}	
}
