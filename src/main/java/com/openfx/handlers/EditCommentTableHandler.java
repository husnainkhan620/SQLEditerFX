package com.openfx.handlers;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.ui.MySqlUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditCommentTableHandler implements EventHandler<ActionEvent> {

	public CreateNewColumnHandler createNewColumnHandler;
	public Scene scene;
	public Stage stage;
	public MySqlUI mysqlui;
	public String whiteThemeCss = Menu_Items_FX.class.getResource("/whiteTheme.css").toExternalForm();
	public String darkThemeCss = Menu_Items_FX.class.getResource("/darkTheme.css").toExternalForm();
	public String selectedTheme = whiteThemeCss;
	public CreateNewTableHandler createNewTableHandler;

	public EditCommentTableHandler(CreateNewColumnHandler createNewColumnHandler, MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.createNewColumnHandler = createNewColumnHandler;
		this.mysqlui = mysqlui;
	}
	public EditCommentTableHandler(CreateNewTableHandler createNewTableHandler,MySqlUI mysqlui) {
		// TODO Auto-generated constructor stub
		this.createNewTableHandler = createNewTableHandler;
		this.mysqlui = mysqlui;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		createNewColumnHandler.editCommentTableHandler = this;
		
		VBox editCommentVBox = new VBox();
		editCommentVBox.setId("editCommentVBox");
		HBox  editCommentHbox = new HBox();
		editCommentHbox.setId("textHbox");
		TextArea commentTextArea = new TextArea();
		commentTextArea.getStyleClass().add("EditCommenttextArea");
		//VBox.setVgrow(commentArea, Priority.ALWAYS);
//		commentArea.setPrefColumnCount(10);
//		commentArea.setPrefRowCount(3);
		editCommentHbox.getChildren().addAll(commentTextArea);
		Button editokbutton = new Button("OK");
		editokbutton.setId("buttons");
		
		editokbutton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage.close();
			}
		});
		
		Button editCancelbutton = new Button("Cancel");
		editCancelbutton.setId("buttons");
		
		editCancelbutton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage.close();
			}
		});
		
		editCommentVBox.getChildren().addAll(commentTextArea,editokbutton,editCancelbutton);
		
		createNewColumnHandler.scene = new Scene(editCommentVBox, 100, 100);
		createNewColumnHandler.scene.getStylesheets().add(createNewColumnHandler.selectedTheme);
		 stage = new Stage();
		 stage.initModality(Modality.APPLICATION_MODAL);
		 stage.initOwner(createNewColumnHandler.stage.getScene().getWindow());
		 stage.setTitle("Edit Comment");
		 stage.setWidth(320);
		 stage.setHeight(220);
		 stage.setScene( createNewColumnHandler.scene);		      
		 stage.show();
	}

}
