package com.openfx.handlers;

import org.openjfx.fx.Menu_Items_FX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GeneratedSQLQueryHandler implements EventHandler<ActionEvent> {

	//public CreateNewTableHandler createNewTableHandler;
	public Stage stage;
	public Scene scene;
	public GeneratedSQLQueryHandler generatedSQLQueryHandler;
	public String whiteThemeCss = GeneratedSQLQueryHandler.class.getResource("/whiteTheme.css").toExternalForm();
	public String darkThemeCss = GeneratedSQLQueryHandler.class.getResource("/darkTheme.css").toExternalForm();
	public String selectedTheme = whiteThemeCss;
	
//
//	public GeneratedSQLQueryHandler(CreateNewTableHandler createNewTableHandler) {
//		// TODO Auto-generated constructor stub
//		this.createNewTableHandler = createNewTableHandler;
//	}
//	
//	public GeneratedSQLQueryHandler(GeneratedSQLQueryHandler generatedSQLQueryHandler) {
//		// TODO Auto-generated constructor stub
//		this.generatedSQLQueryHandler = generatedSQLQueryHandler;
//	}
//   
	private final TextArea sqlTextArea;

    public GeneratedSQLQueryHandler(TextArea sqlTextArea) {
        this.sqlTextArea = sqlTextArea;
    }
	
//	private final String sqlQuery;
//
//    public GeneratedSQLQueryHandler(String sqlQuery) {
//        this.sqlQuery = sqlQuery;
//    }
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
//		createNewTableHandler.generatedSQLQueryHandler = this;
	    String sqlQuery = sqlTextArea.getText();
		
		VBox sqlTabVBox = new VBox();
		HBox sqlGeneratedLabelHBox = new HBox();
		sqlGeneratedLabelHBox.getStyleClass().add("sqlGeneratedLabelHBox");
		Label sqlGeneratedLabel = new Label("SQL Generated Statement"); 		 
		sqlGeneratedLabelHBox.getChildren().addAll(sqlGeneratedLabel);
		
		
		TextArea sqlqueryArea = new TextArea();
		sqlqueryArea.setId("sqlqueryArea");
		sqlqueryArea.setEditable(false);
		sqlqueryArea.setText(sqlQuery);
		VBox.setVgrow(sqlqueryArea, Priority.ALWAYS); // Make it grow in VBox
		//sqlqueryArea.setPrefHeight(0);
		
		HBox buttonsHBox = new HBox();
		buttonsHBox.getStyleClass().add("SQLButtonshbox");
		Button cancelButton = new Button("Cancel");
		cancelButton.setId("buttons");
		Button okButton = new Button("OK");
		okButton.setId("buttons");
		buttonsHBox.getChildren().addAll(cancelButton,okButton);
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
		
		sqlTabVBox.getChildren().addAll(sqlGeneratedLabelHBox,sqlqueryArea,buttonsHBox);
//		createNewTableHandler.scene = new Scene(sqlTabVBox, 650, 370);
//		createNewTableHandler.scene.getStylesheets().add(createNewTableHandler.selectedTheme);
		scene = new Scene(sqlTabVBox, 650, 370);
		scene.getStylesheets().add(selectedTheme);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		//stage.initOwner(mysqlui.primaryStage.getScene().getWindow());
		stage.setTitle("SQL Generated Query");
		stage.setWidth(750);
		stage.setHeight(730);
//		stage.setScene( createNewTableHandler.scene);
		stage.setScene( scene);

		stage.show();
	}
     
}
