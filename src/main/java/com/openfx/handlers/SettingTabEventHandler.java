package com.openfx.handlers;


import org.openjfx.fx.Menu_Items_FX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingTabEventHandler implements  EventHandler<ActionEvent> {

public String whiteThemeCss = SettingTabEventHandler.class.getResource("/whiteTheme.css").toExternalForm();
public String darkThemeCss = SettingTabEventHandler.class.getResource("/darkTheme.css").toExternalForm();
public String selectedTheme = whiteThemeCss;

public Scene sceneDataBaseConnection;
public Scene scene;
public Stage settingsStage;
public Menu_Items_FX menu_Items_FX;
public NewMenuItemEventHandler newMenuItemEventHandler;



	public SettingTabEventHandler(Menu_Items_FX menu_Items_FX) {
		
		this.menu_Items_FX = menu_Items_FX;
	// TODO Auto-generated constructor stub
}
	
	public SettingTabEventHandler(NewMenuItemEventHandler newMenuItemEventHandler) {
		
		this.newMenuItemEventHandler = newMenuItemEventHandler;
		// TODO Auto-generated constructor stub
	}



	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

		menu_Items_FX.settingTabEventHandler = this; 
				
		// SplitPane setup
		SplitPane splitPane = new SplitPane();
		splitPane.setId("SplitPane");
		splitPane.setDividerPositions(0.21);
		
//		 
//		ScrollPane settingscrollPane = new ScrollPane();    
//		settingscrollPane.setId("ScrollPane");
		
		// Left control with menu items
		VBox leftControl = new VBox();
		leftControl.setId("leftControl");
		TreeItem rootItem = new TreeItem("Settings");
		
		TreeItem webItem = new TreeItem("Appearance");
		webItem.getChildren().add(new TreeItem("Theme"));
		webItem.getChildren().add(new TreeItem("Layout"));
		rootItem.getChildren().add(webItem);

		//        TreeItem javaItem = new TreeItem("Java Tutorials");
		//        javaItem.getChildren().add(new TreeItem("Java Language"));
		//        javaItem.getChildren().add(new TreeItem("Java Collections"));
		//        javaItem.getChildren().add(new TreeItem("Java Concurrency"));
		//        rootItem.getChildren().add(javaItem);

		TreeView treeView = new TreeView();
		treeView.setRoot(rootItem);
	   	treeView.setShowRoot(false);
	   	treeView.setId("treeView");
		leftControl.getChildren().addAll(treeView
				//            new Label("Menu Item 1"),
				//            new Label("Menu Item 2"),
				//            new Label("Menu Item 3")
				);
    	//leftControl.setStyle("-fx-padding: 10; ");
    	//leftControl.setId("leftControl");
		// Right control with data display
		VBox rightControl = new VBox();
		rightControl.setId("rightControl");
		HBox hbox = new HBox();
		Label themeLabel = new Label("Theme :  ");
		themeLabel.setId("themeLabel");
		
		// ComboBox (Dropdown)
		ComboBox<String> themeDropdown = new ComboBox<>();
		themeDropdown.setId("themeDropdown");
		themeDropdown.getItems().addAll("Light", "Dark");
		themeDropdown.setPromptText("Select Theme");
		hbox.getChildren().addAll(themeLabel, themeDropdown);
		
		HBox bottomhbox = new HBox();
	    bottomhbox.setId("bottomhboxSettings");
		Button okButton = new Button("OK");
		okButton.setId("buttons");
		Button cancelButton = new Button("Cancel");
		cancelButton.setId("buttons");
		Button applyButton = new Button("Apply");
		applyButton.setId("buttons");
		
	
//		applyButton.setOnAction(new EventHandler<ActionEvent>() {
//		    @Override
//		    public void handle(ActionEvent event) {

		    	//menu_Items_FX.sceneForSettings.getStylesheets().clear();
//		    	menu_Items_FX.selectedTheme = menu_Items_FX.darkThemeCss;
//		    	menu_Items_FX.sceneForSettings.getStylesheets().add(darkThemeCss);
//		    	menu_Items_FX.scene.getStylesheets().add(darkThemeCss);
//		    	menu_Items_FX.sceneDataBaseConnection.getStylesheets().add(darkThemeCss);
//		    	scene.getStylesheets().add(menu_Items_FX.selectedTheme);
                
//		    	String selectedValue = themeDropdown.getValue();
//		    	 if (selectedValue != null) {
//		    	        // Select theme file
//		    	        selectedTheme = selectedValue.equals("Light") ? whiteThemeCss : darkThemeCss;
//		    	        menu_Items_FX.selectedTheme = selectedTheme;
//
//		    	        // Clear and apply theme to all relevant scenes
//		    	        menu_Items_FX.scene.getStylesheets().clear();
//		    	        menu_Items_FX.scene.getStylesheets().add(selectedTheme);
//
//		    	        menu_Items_FX.sceneDataBaseConnection.getStylesheets().clear();
//		    	        menu_Items_FX.sceneDataBaseConnection.getStylesheets().add(selectedTheme);
//
//		    	        menu_Items_FX.sceneForSettings.getStylesheets().clear();
//		    	        menu_Items_FX.sceneForSettings.getStylesheets().add(selectedTheme);
//
//		    	        settingsStage.getScene().getStylesheets().clear();
//		    	        settingsStage.getScene().getStylesheets().add(selectedTheme);
//		    	        
//		    		// Close the settings stage
//		    		settingsStage.close();
//		    	}
//		    } 
//		});
		    	
		applyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				String selectedValue = themeDropdown.getValue();

				if (selectedValue != null) {
					// Select theme file
					selectedTheme = selectedValue.equals("Light") ? whiteThemeCss : darkThemeCss;
					menu_Items_FX.selectedTheme = selectedTheme;

					// Apply theme to all open scenes dynamically
					applyTheme(menu_Items_FX.scene, selectedTheme);
					applyTheme(menu_Items_FX.sceneDataBaseConnection, selectedTheme);
					applyTheme(menu_Items_FX.sceneForSettings, selectedTheme);

					// Ensure the settings window itself is updated
					applyTheme(settingsStage.getScene(), selectedTheme);
                    settingsStage.close();
				}
			}  
			private void applyTheme(Scene scene, String theme) {
				if (scene != null) {
					scene.getStylesheets().clear();
					scene.getStylesheets().add(theme);
				}
			}	
		});

		// Function to update stylesheets dynamically
		
		
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        // Close the settings window
		    	settingsStage.close();
		    }
		});
		    

	
		okButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        // Close the settings window
		    	settingsStage.close();
		    }
		});
	

		bottomhbox.getChildren().addAll(okButton, cancelButton,applyButton);
		bottomhbox.setAlignment(Pos.BASELINE_RIGHT);
		//bottomhbox.setSpacing(15);
		
		rightControl.getChildren().addAll(hbox,bottomhbox);
		//rightControl.setStyle("-fx-padding: 10;");

		// Add components to SplitPane
		splitPane.getItems().addAll(leftControl, rightControl);
		splitPane.setDividerPositions(0.1); // Set initial divider position

		// Scene setup with SplitPane as the root node
		
		
		menu_Items_FX.sceneForSettings = new Scene(splitPane, 500, 400);
//		menu_Items_FX.sceneForSettings.getStylesheets().clear();
		  menu_Items_FX.sceneForSettings.getStylesheets().add(menu_Items_FX.selectedTheme);
//		 settingTab.start(settingsStage);
//		settingscrollPane.setContent(treeView);
//		settingscrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
//		settingscrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
//		settingscrollPane.fitToWidthProperty().set(true);
		 settingsStage = new Stage();
		 settingsStage.initModality(Modality.APPLICATION_MODAL);
		 settingsStage.initOwner(menu_Items_FX.primaryStage.getScene().getWindow());
		settingsStage.setTitle("Settings");
		settingsStage.setWidth(750);
		settingsStage.setHeight(550);
		settingsStage.setScene(menu_Items_FX.sceneForSettings);
//		settingsStage.getScene().getStylesheets().clear();
//        settingsStage.getScene().getStylesheets().add(selectedTheme);       
		settingsStage.show();
		

	}

}
