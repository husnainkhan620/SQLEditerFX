package com.openfx.handlers;

import com.openfx.handlers.CreateNewForeignKeyHandler.CreateNewForeignKeyTableRow;
import com.openfx.ui.MySqlUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewColumnOptionsHandler implements EventHandler<ActionEvent> {

	public CreateNewForeignKeyHandler createNewForeignKeyHandler;
	public Stage stage;
	public MySqlUI mysqlui;

	public NewColumnOptionsHandler(CreateNewForeignKeyHandler createNewForeignKeyHandler, MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.createNewForeignKeyHandler = createNewForeignKeyHandler;
		this.mysqlui =  mysqlui;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		createNewForeignKeyHandler.newColumnOptionsHandler = this;
		
		VBox newColumnOptionsVBox = new VBox();
		newColumnOptionsVBox.setId("newColumnOptionsVBox");
		
		HBox columnNameValueHbox = new HBox();
		columnNameValueHbox.getStyleClass().add("columnNameValueHbox");
		Label columnNameLabel = new Label("Column Name : ");
		columnNameLabel.setId("createForeignKeyLabels");
		TextField columnNameValueTextField = new TextField();
		//columnNameValueField.getStyleClass().add("Valuetextfield");
		columnNameValueHbox.getChildren().addAll(columnNameLabel,columnNameValueTextField);
		
		HBox notNullCheckBoxHbox = new HBox();
		notNullCheckBoxHbox.getStyleClass().add("columnNameValueHbox");
		CheckBox notNullCheckBox = new CheckBox();
		Label notNullabel = new Label("Not Null");
		notNullabel.setId("createForeignKeyLabels");
		notNullCheckBoxHbox.getChildren().addAll(notNullCheckBox,notNullabel);
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.getStyleClass().add("buttons--hbox");
		Button okButton = new Button("OK");
		okButton.setId("buttons");

		okButton.setOnAction(e ->  {

			String columnNameText = columnNameValueTextField.getText();
			CreateNewForeignKeyTableRow newColumnName = new CreateNewForeignKeyTableRow(columnNameText);
			
			createNewForeignKeyHandler.getCreateNewForeignKeyData().add(newColumnName);
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
		newColumnOptionsVBox.getChildren().addAll(columnNameValueHbox,notNullCheckBoxHbox,buttonsHBox);
		createNewForeignKeyHandler.newColumnOptionsScene = new Scene(newColumnOptionsVBox, 100, 100);
		createNewForeignKeyHandler.newColumnOptionsScene.getStylesheets().add(createNewForeignKeyHandler.selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(createNewForeignKeyHandler.stage.getScene().getWindow());
		stage.setTitle("New column options");
		stage.setWidth(350);
		stage.setHeight(170);
		stage.setScene( createNewForeignKeyHandler.newColumnOptionsScene);		      
		stage.show();
	}

}
