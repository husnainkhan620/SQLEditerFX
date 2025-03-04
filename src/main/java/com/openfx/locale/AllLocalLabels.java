package com.openfx.locale;

import java.sql.Connection;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.placeholders.ConnectionPlaceHolder;
import com.openfx.ui.MySqlUI;

public class AllLocalLabels {

	public  void setLocaleLables(Menu_Items_FX menu_Items_FX,String selectedLocale) {
		// list down all the Lables and re-assign locale to it
		menu_Items_FX.locale = new Locale.Builder().setLanguage(selectedLocale).build();
		menu_Items_FX.resourceBundle = ResourceBundle.getBundle("languages.LabelResource", menu_Items_FX.locale);
		menu_Items_FX.primaryStage.setTitle(menu_Items_FX.resourceBundle.getString("applicationName"));
		menu_Items_FX.fileMenu.setText(menu_Items_FX.resourceBundle.getString("File"));
		menu_Items_FX.editMenu.setText(menu_Items_FX.resourceBundle.getString("Edit"));
		menu_Items_FX.databaseMenu.setText(menu_Items_FX.resourceBundle.getString("Database"));
		menu_Items_FX.toolsMenu.setText(menu_Items_FX.resourceBundle.getString("Tools"));
		menu_Items_FX.viewMenu.setText(menu_Items_FX.resourceBundle.getString("View"));
		menu_Items_FX.helpMenu.setText(menu_Items_FX.resourceBundle.getString("Help"));
		menu_Items_FX.windowMenu.setText(menu_Items_FX.resourceBundle.getString("Window"));
		menu_Items_FX.toolBarSearch.setText(menu_Items_FX.resourceBundle.getString("Search"));
		menu_Items_FX.toolBarRunButton.setText(menu_Items_FX.resourceBundle.getString("Run"));
		menu_Items_FX.connectionExplorerTab.setText(menu_Items_FX.resourceBundle.getString("Connection_Explorer"));
		
		// Cross Class connection
		menu_Items_FX.newMenuItemEventHandler.connectToDatabseText.setText((menu_Items_FX.resourceBundle.getString("ConnectToDatabseText")));
		
		//MySQL  work on changing for all connections..n
		for (MySqlUI mySqlUI : menu_Items_FX.mySqlUIList) {
			mySqlUI.mySqlTreeItemDatabases.setValue((menu_Items_FX.resourceBundle.getString("Databases")));
			mySqlUI.mySqlTreeItemAdminister.setValue((menu_Items_FX.resourceBundle.getString("Administer")));
			mySqlUI.mySqlTreeItemSystemInfo.setValue((menu_Items_FX.resourceBundle.getString("SystemInfo")));
			mySqlUI.mySqlTreeItemSystemInfo.setValue((menu_Items_FX.resourceBundle.getString("Administration")));
		}
		
		
	}
}
