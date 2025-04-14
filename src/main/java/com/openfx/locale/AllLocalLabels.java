package com.openfx.locale;

import java.sql.Connection;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.openjfx.fx.Menu_Items_FX;

import com.openfx.handlers.SettingTabEventHandler;
import com.openfx.placeholders.ConnectionPlaceHolder;
import com.openfx.ui.MySqlUI;

import javafx.scene.control.Button;

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
		menu_Items_FX.newMenuItem.setText(menu_Items_FX.resourceBundle.getString("New"));
		menu_Items_FX.openMenuItem.setText(menu_Items_FX.resourceBundle.getString("Open"));
		menu_Items_FX.saveFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("Save"));
		menu_Items_FX.saveAsFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("SaveAs"));
		menu_Items_FX.renameFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("Rename"));
		menu_Items_FX.settingFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("Settings"));
		menu_Items_FX.closeFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("Close"));
		menu_Items_FX.closeAllFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("CloseAll"));
		menu_Items_FX.printFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("Print"));
		menu_Items_FX.recentFilesFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("Recent"));
		menu_Items_FX.exitAppFileMenuItem.setText(menu_Items_FX.resourceBundle.getString("Exit"));
		menu_Items_FX.newDatabseConnectionItem.setText(menu_Items_FX.resourceBundle.getString("NewDatabaseConnection"));
		menu_Items_FX.connectToDatabaseConnectionItem.setText(menu_Items_FX.resourceBundle.getString("ConnectToADatabase"));
		menu_Items_FX.disconnectFromDatabaseConnectionItem.setText(menu_Items_FX.resourceBundle.getString("DisconnectFromADatabase"));
		menu_Items_FX.exitDatabaseConnectionItem.setText(menu_Items_FX.resourceBundle.getString("ExitDatabase"));
		menu_Items_FX.sqlEditerResultTab.setText(menu_Items_FX.resourceBundle.getString("Result"));
		menu_Items_FX.sqlEditerResultTab.setText(menu_Items_FX.resourceBundle.getString("Console"));
		
		
		
		//SearchToolEventHandler
		menu_Items_FX.searchToolEventHandler.searchDatabasesLabel.setText(menu_Items_FX.resourceBundle.getString("Find"));
		menu_Items_FX.searchToolEventHandler.searchTablesLabel.setText(menu_Items_FX.resourceBundle.getString("Find"));
		menu_Items_FX.searchToolEventHandler.toSearchTablesLabel.setText(menu_Items_FX.resourceBundle.getString("Find"));
		menu_Items_FX.searchToolEventHandler.searchDatabasesClearAllLable.setText(menu_Items_FX.resourceBundle.getString("ClearAll"));
		menu_Items_FX.searchToolEventHandler.searchHelpButton.setText(menu_Items_FX.resourceBundle.getString("Help"));
		menu_Items_FX.searchToolEventHandler.searchDataButton.setText(menu_Items_FX.resourceBundle.getString("search"));
		menu_Items_FX.searchToolEventHandler.dataValueToSearchLabel.setText(menu_Items_FX.resourceBundle.getString("Enterthedatavaluetosearch"));
		//menu_Items_FX.searchToolEventHandler.menu_Items_FX.dataSearchtabPane.setText(menu_Items_FX.resourceBundle.getString("SearchData"));

		
		//SqlQueryRunButton
	    menu_Items_FX.sqlQueryRunButtonSubmit.connectToDatabseText.setText(menu_Items_FX.resourceBundle.getString("ConnectToADatabase"));
	    menu_Items_FX.sqlQueryRunButtonSubmit.connectToDatabseText.setText(menu_Items_FX.resourceBundle.getString("ExistingConnections"));
	    menu_Items_FX.sqlQueryRunButtonSubmit.connectExistingConnection.setText(menu_Items_FX.resourceBundle.getString("Connect"));
	    menu_Items_FX.sqlQueryRunButtonSubmit.newConnection.setText(menu_Items_FX.resourceBundle.getString("NewDatabase"));
	    menu_Items_FX.sqlQueryRunButtonSubmit.connectionStage.setTitle(menu_Items_FX.resourceBundle.getString("NoDataBaseConnection"));
		
	    
	    //SettingsTab
	    for(SettingTabEventHandler settingTabEventHandler: menu_Items_FX.settingTabEventHandlerList) {
	    	 menu_Items_FX.settingTabEventHandler.appreanceItem.setValue(menu_Items_FX.resourceBundle.getString("Appearance"));
	    	 menu_Items_FX.settingTabEventHandler.themeItem.setValue(menu_Items_FX.resourceBundle.getString("Theme"));
	    	 menu_Items_FX.settingTabEventHandler.LayoutItem.setValue(menu_Items_FX.resourceBundle.getString("Layout"));
	    	 menu_Items_FX.settingTabEventHandler.languageItem.setValue(menu_Items_FX.resourceBundle.getString("Languages"));
	    	 menu_Items_FX.settingTabEventHandler.EnglishLanguage.setValue(menu_Items_FX.resourceBundle.getString("English"));
	    	 menu_Items_FX.settingTabEventHandler.EnglishLanguage.setValue(menu_Items_FX.resourceBundle.getString("Languages"));
	    }
	    
	    menu_Items_FX.settingTabEventHandler.themeLabel.setText(menu_Items_FX.resourceBundle.getString("ThemeLabel"));
	    menu_Items_FX.settingTabEventHandler.themeDropdown.setValue(menu_Items_FX.resourceBundle.getString("Light"));
	    menu_Items_FX.settingTabEventHandler.themeDropdown.setValue(menu_Items_FX.resourceBundle.getString("Dark"));
	    menu_Items_FX.settingTabEventHandler.themeDropdown.setValue(menu_Items_FX.resourceBundle.getString("SelectTheme"));
	    menu_Items_FX.settingTabEventHandler.languageDropdown.setValue(menu_Items_FX.resourceBundle.getString("SelectLanguage"));
	    menu_Items_FX.settingTabEventHandler.okButton.setText(menu_Items_FX.resourceBundle.getString("OK"));
	    menu_Items_FX.settingTabEventHandler.cancelButton.setText(menu_Items_FX.resourceBundle.getString("Cancel"));
	    menu_Items_FX.settingTabEventHandler.applyButton.setText(menu_Items_FX.resourceBundle.getString("Apply"));
	    menu_Items_FX.settingTabEventHandler.LanguageLabel.setText(menu_Items_FX.resourceBundle.getString("Languagelabel"));
//	    menu_Items_FX.settingTabEventHandler.languageDropdown.setValue(menu_Items_FX.resourceBundle.getString("French"));
	    menu_Items_FX.settingTabEventHandler.settingsStage.setTitle(menu_Items_FX.resourceBundle.getString("SettingsTab"));

	    
	    
		// Cross Class connection
		menu_Items_FX.newMenuItemEventHandler.connectToDatabseText.setText((menu_Items_FX.resourceBundle.getString("ConnectToDatabseText")));
		menu_Items_FX.newMenuItemEventHandler.buttonTestConnection.setText((menu_Items_FX.resourceBundle.getString("TestConnection")));
		menu_Items_FX.newMenuItemEventHandler.buttonBack.setText((menu_Items_FX.resourceBundle.getString("<Back")));
		menu_Items_FX.newMenuItemEventHandler.buttonNext.setText((menu_Items_FX.resourceBundle.getString("Next>")));
		menu_Items_FX.newMenuItemEventHandler.buttonFinish.setText((menu_Items_FX.resourceBundle.getString("Finish")));
		menu_Items_FX.newMenuItemEventHandler.buttonCancel.setText((menu_Items_FX.resourceBundle.getString("Cancel")));
		menu_Items_FX.newMenuItemEventHandler.connectToDatabseText.setText((menu_Items_FX.resourceBundle.getString("connectionsettings")));
		menu_Items_FX.newMenuItemEventHandler.connectToLabel.setText((menu_Items_FX.resourceBundle.getString("ConnecttoDatabase")));
		menu_Items_FX.newMenuItemEventHandler.connectionDetailsTab.setText((menu_Items_FX.resourceBundle.getString("Connection")));
		menu_Items_FX.newMenuItemEventHandler.driverPropertiesTab.setText((menu_Items_FX.resourceBundle.getString("DriverProperties")));
		menu_Items_FX.newMenuItemEventHandler.sshDetailsTab.setText((menu_Items_FX.resourceBundle.getString("SSH")));
		menu_Items_FX.newMenuItemEventHandler.proxyDetailsTab.setText((menu_Items_FX.resourceBundle.getString("Proxy")));
		menu_Items_FX.newMenuItemEventHandler.sslDetailsTab.setText((menu_Items_FX.resourceBundle.getString("SSL")));

		
		
		//MySQL  work on changing for all connections..n
		for (MySqlUI mySqlUI : menu_Items_FX.mySqlUIList) {
			mySqlUI.mySqlTreeItemDatabases.setValue(menu_Items_FX.resourceBundle.getString("Databases"));
			mySqlUI.mySqlTreeItemAdminister.setValue(menu_Items_FX.resourceBundle.getString("Administer"));
			mySqlUI.mySqlTreeItemSystemInfo.setValue(menu_Items_FX.resourceBundle.getString("SystemInfo"));
			mySqlUI.mySqlTreeItemAdministration.setValue(menu_Items_FX.resourceBundle.getString("Administration"));
			mySqlUI.mySqlTreeItemAdministerServerStatus.setValue(menu_Items_FX.resourceBundle.getString("ServerStatus"));
			mySqlUI.mySqlTreeItemAdministerClientConnectionss.setValue(menu_Items_FX.resourceBundle.getString("ClientConnections"));
			mySqlUI.mySqlTreeItemAdministerUserandPrivileges.setValue(menu_Items_FX.resourceBundle.getString("UsersAndPrivileges"));
			mySqlUI.mySqlTreeItemAdministerStatusandSystemVariables.setValue(menu_Items_FX.resourceBundle.getString("StatusAndSystemVariables"));
     		mySqlUI.mySqlTreeItemPerformance.setValue(menu_Items_FX.resourceBundle.getString("Performance"));
     		mySqlUI.mySqlTreeItemAdministerDashboard.setValue(menu_Items_FX.resourceBundle.getString("Dashboard"));
			mySqlUI.mySqlTreeItemAdministerPerformanceReports.setValue(menu_Items_FX.resourceBundle.getString("PerformanceReports"));
			mySqlUI.mySqlTreeItemServer.setValue(menu_Items_FX.resourceBundle.getString("Server"));
			mySqlUI.mySqlTreeItemAdministerServerLogs.setValue(menu_Items_FX.resourceBundle.getString("ServerLogs"));
			mySqlUI.mySqlTreeItemSystemInfoBinaryLogs.setValue(menu_Items_FX.resourceBundle.getString("BinaryLogs"));
			mySqlUI.mySqlTreeItemSystemInfoCharacterSet.setValue(menu_Items_FX.resourceBundle.getString("CharacterSet"));
			mySqlUI.mySqlTreeItemSystemInfoCollation.setValue(menu_Items_FX.resourceBundle.getString("Collation"));
			mySqlUI.mySqlTreeItemSystemInfoEngines.setValue(menu_Items_FX.resourceBundle.getString("Engines"));
			mySqlUI.mySqlTreeItemSystemInfoErrors.setValue(menu_Items_FX.resourceBundle.getString("Errors"));
			mySqlUI.mySqlTreeItemSystemInfoEvents.setValue(menu_Items_FX.resourceBundle.getString("Events"));
			mySqlUI.mySqlTreeItemSystemInfoOpenTables.setValue(menu_Items_FX.resourceBundle.getString("OpenTables"));
			mySqlUI.mySqlTreeItemSystemInfoPlugins.setValue(menu_Items_FX.resourceBundle.getString("Plugins"));
			mySqlUI.mySqlTreeItemSystemInfoPreviliges.setValue(menu_Items_FX.resourceBundle.getString("Previliges"));
			mySqlUI.mySqlTreeItemSystemInfoProcessList.setValue(menu_Items_FX.resourceBundle.getString("ProcessList"));
			mySqlUI.mySqlTreeItemSystemInfoProfiles.setValue(menu_Items_FX.resourceBundle.getString("Profiles"));
			mySqlUI.mySqlTreeItemSystemInfoReplicas.setValue(menu_Items_FX.resourceBundle.getString("Replicas"));
			mySqlUI.mySqlTreeItemSystemInfoSessionStatus.setValue(menu_Items_FX.resourceBundle.getString("SessionStatus"));
			mySqlUI.mySqlTreeItemSystemInfoGlobalStatus.setValue(menu_Items_FX.resourceBundle.getString("GlobalStatus"));
			mySqlUI.mySqlTreeItemSystemInfoSessionVariables.setValue(menu_Items_FX.resourceBundle.getString("SessionVariables"));
			mySqlUI.mySqlTreeItemSystemInfoGlobalVariables.setValue(menu_Items_FX.resourceBundle.getString("GlobalVariables"));
			mySqlUI.mySqlTreeItemSystemInfoWarnings.setValue(menu_Items_FX.resourceBundle.getString("Warnings"));
		}
		 
		
		menu_Items_FX.mysqlui.particularTablePropertiesTab.setText(menu_Items_FX.resourceBundle.getString("Properties"));
		menu_Items_FX.mysqlui.particularTableDataTab.setText(menu_Items_FX.resourceBundle.getString("Data"));
		menu_Items_FX.mysqlui.particularTableERDiagramTab.setText(menu_Items_FX.resourceBundle.getString("ERDiagram"));
		menu_Items_FX.mysqlui.particularTableGraphVisualsTab.setText(menu_Items_FX.resourceBundle.getString("GraphVisuals"));
		menu_Items_FX.mysqlui.particularTableAiPromptTab.setText(menu_Items_FX.resourceBundle.getString("AIPrompt"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("Details"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("Columns"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("Constraints"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("ForeignKeys"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("References"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("Triggers"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("Indexes"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("Partitions"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("Source/DDL"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("IndexColumns"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("ProcedureParameters"));
		menu_Items_FX.mysqlui.l.setText(menu_Items_FX.resourceBundle.getString("FunctionParameters"));
		menu_Items_FX.mysqlui.ViewColumnsCreateButton.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.viewDataTabCreateButton.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.particularIndexesColumnsButton.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.particularTableColumnsButtons.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.particularTableConstraintsButtons.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.particularTableforeignKeysButtons.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.particularTableReferencesButtons.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.particularTableIndexesButtons.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.particularTablepartitionsButtons.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.ViewColumnsDeleteButton.setText(menu_Items_FX.resourceBundle.getString("Delete"));
		menu_Items_FX.mysqlui.createTableButton.setText(menu_Items_FX.resourceBundle.getString("Create"));
		menu_Items_FX.mysqlui.viewTableButton.setText(menu_Items_FX.resourceBundle.getString("ViewTable"));
		menu_Items_FX.mysqlui.createTableButtons.setText(menu_Items_FX.resourceBundle.getString("CreateTable"));
		menu_Items_FX.mysqlui.deleteTableButton.setText(menu_Items_FX.resourceBundle.getString("DeleteTable"));
		menu_Items_FX.mysqlui.editTableButton.setText(menu_Items_FX.resourceBundle.getString("EditTable"));
		menu_Items_FX.mysqlui.refreshTableButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.viewViewButton.setText(menu_Items_FX.resourceBundle.getString("ViewView"));
		menu_Items_FX.mysqlui.createViewButton.setText(menu_Items_FX.resourceBundle.getString("CreateView"));
		menu_Items_FX.mysqlui.editViewButton.setText(menu_Items_FX.resourceBundle.getString("EditView"));
		menu_Items_FX.mysqlui.deleteViewButton.setText(menu_Items_FX.resourceBundle.getString("DeleteView"));
		menu_Items_FX.mysqlui.refreshViewButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.viewIndexButton.setText(menu_Items_FX.resourceBundle.getString("ViewIndex"));
		menu_Items_FX.mysqlui.createIndexButton.setText(menu_Items_FX.resourceBundle.getString("CreateIndex"));
		menu_Items_FX.mysqlui.editIndexButton.setText(menu_Items_FX.resourceBundle.getString("EditIndex"));
		menu_Items_FX.mysqlui.deleteIndexButton.setText(menu_Items_FX.resourceBundle.getString("DeleteIndex"));
		menu_Items_FX.mysqlui.refreshIndexButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.viewProcedureButton.setText(menu_Items_FX.resourceBundle.getString("ViewProcedure"));
		menu_Items_FX.mysqlui.createProcedureButton.setText(menu_Items_FX.resourceBundle.getString("CreateProcedure"));
		menu_Items_FX.mysqlui.editProcedureButton.setText(menu_Items_FX.resourceBundle.getString("EditProcedure"));
		menu_Items_FX.mysqlui.deleteProcedureButton.setText(menu_Items_FX.resourceBundle.getString("DeleteProcedure"));
		menu_Items_FX.mysqlui.refreshProcedureButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.viewFunctionButton.setText(menu_Items_FX.resourceBundle.getString("ViewFunction"));
		menu_Items_FX.mysqlui.createFunctionButton.setText(menu_Items_FX.resourceBundle.getString("CreateFunction"));
		menu_Items_FX.mysqlui.editFunctionButton.setText(menu_Items_FX.resourceBundle.getString("EditFunction"));
		menu_Items_FX.mysqlui.deleteFunctionButton.setText(menu_Items_FX.resourceBundle.getString("DeleteFunction"));
		menu_Items_FX.mysqlui.viewTriggersButton.setText(menu_Items_FX.resourceBundle.getString("ViewTrigger"));
		menu_Items_FX.mysqlui.createTriggersButton.setText(menu_Items_FX.resourceBundle.getString("CreateTrigger"));
		menu_Items_FX.mysqlui.editTriggersButton.setText(menu_Items_FX.resourceBundle.getString("EditTrigger"));
		menu_Items_FX.mysqlui.deleteTriggersButton.setText(menu_Items_FX.resourceBundle.getString("DeleteTrigger"));
		menu_Items_FX.mysqlui.refreshTriggersButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.viewEventButton.setText(menu_Items_FX.resourceBundle.getString("ViewEvent"));
		menu_Items_FX.mysqlui.createEventButton.setText(menu_Items_FX.resourceBundle.getString("CreateEvent"));
		menu_Items_FX.mysqlui.editEventButton.setText(menu_Items_FX.resourceBundle.getString("EditEvent"));
		menu_Items_FX.mysqlui.deleteEventButton.setText(menu_Items_FX.resourceBundle.getString("DeleteEvent"));
		menu_Items_FX.mysqlui.refreshEventsButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.databaseDetails.setText(menu_Items_FX.resourceBundle.getString("Details"));
		menu_Items_FX.mysqlui.databaseDetailsPropertiesTab.setText(menu_Items_FX.resourceBundle.getString("Properties"));
		menu_Items_FX.mysqlui.databaseDetailsTablesTab.setText(menu_Items_FX.resourceBundle.getString("Tables"));
		menu_Items_FX.mysqlui.databaseDetailsViewsTab.setText(menu_Items_FX.resourceBundle.getString("Views"));
		menu_Items_FX.mysqlui.databaseDetailsIndexesTab.setText(menu_Items_FX.resourceBundle.getString("Indexes"));
		menu_Items_FX.mysqlui.databaseDetailsProceduresTab.setText(menu_Items_FX.resourceBundle.getString("Procedures"));
		menu_Items_FX.mysqlui.databaseDetailsFunctionsTab.setText(menu_Items_FX.resourceBundle.getString("Functions"));
		menu_Items_FX.mysqlui.databaseDetailsTriggersTab.setText(menu_Items_FX.resourceBundle.getString("Triggers"));
		menu_Items_FX.mysqlui.databaseDetailsEventsTab.setText(menu_Items_FX.resourceBundle.getString("Events/DDL"));
		menu_Items_FX.mysqlui.databaseERDiagram.setText(menu_Items_FX.resourceBundle.getString("ERDiagram"));
		menu_Items_FX.mysqlui.databaseGrahicsStats.setText(menu_Items_FX.resourceBundle.getString("GraphicsStats"));
		menu_Items_FX.mysqlui.databaseAIPrompt.setText(menu_Items_FX.resourceBundle.getString("AIPrompt"));
		menu_Items_FX.mysqlui.moreChartsButton.setText(menu_Items_FX.resourceBundle.getString("MoreCharts"));
		menu_Items_FX.mysqlui.showBarGraphButton.setText(menu_Items_FX.resourceBundle.getString("BarGraph"));
		menu_Items_FX.mysqlui.showBarGraphButton.setText(menu_Items_FX.resourceBundle.getString("PieGraph"));
		menu_Items_FX.mysqlui.userAccountsLabel.setText(menu_Items_FX.resourceBundle.getString("UserAccounts"));
		menu_Items_FX.mysqlui.addAccountButton.setText(menu_Items_FX.resourceBundle.getString("AddAccount"));
		menu_Items_FX.mysqlui.deletetButton.setText(menu_Items_FX.resourceBundle.getString("Delete"));
		menu_Items_FX.mysqlui.refreshButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.updatePasswordButton.setText(menu_Items_FX.resourceBundle.getString("UpdatePassword"));
		menu_Items_FX.mysqlui.expirePasswordButton.setText(menu_Items_FX.resourceBundle.getString("ExpirePassword"));
		menu_Items_FX.mysqlui.revertAccountChangesButton.setText(menu_Items_FX.resourceBundle.getString("Revert"));
		menu_Items_FX.mysqlui.saveAccountChangesButton.setText(menu_Items_FX.resourceBundle.getString("SaveButton"));
		menu_Items_FX.mysqlui.accountlockUnLock.setText(menu_Items_FX.resourceBundle.getString("Lock/Unlock"));
		menu_Items_FX.mysqlui.loginTab.setText(menu_Items_FX.resourceBundle.getString("Login"));
		menu_Items_FX.mysqlui.accountLimitsTab.setText(menu_Items_FX.resourceBundle.getString("AccountLimits"));
		menu_Items_FX.mysqlui.accountPrivilegesTab.setText(menu_Items_FX.resourceBundle.getString("AccountPrivileges"));
		menu_Items_FX.mysqlui.schemaPrivilegesTab.setText(menu_Items_FX.resourceBundle.getString("SchemaPrivileges"));
		menu_Items_FX.mysqlui.revertAccountLimitsButton.setText(menu_Items_FX.resourceBundle.getString("Revert"));
		menu_Items_FX.mysqlui.saveAccountLimitsButton.setText(menu_Items_FX.resourceBundle.getString("SaveButton"));
		
		menu_Items_FX.mysqlui.globalPrivilegesLable.setText(menu_Items_FX.resourceBundle.getString("GlobalPrivileges"));
		menu_Items_FX.mysqlui.killQuerysButton.setText(menu_Items_FX.resourceBundle.getString("KillQuery(s)"));
		menu_Items_FX.mysqlui.refreshButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.killConnectionsButton.setText(menu_Items_FX.resourceBundle.getString("KillConnection(s)"));
		menu_Items_FX.mysqlui.addSchemaPrivilegesEntryButton.setText(menu_Items_FX.resourceBundle.getString("AddSchemaEntry"));
		menu_Items_FX.mysqlui.deleteSchemaPrivilegesEntryButton.setText(menu_Items_FX.resourceBundle.getString("DeleteSchemaEntry"));
		menu_Items_FX.mysqlui.reverPriviligestButton.setText(menu_Items_FX.resourceBundle.getString("RevertPrivileges"));
		menu_Items_FX.mysqlui.savePrivilegesButton.setText(menu_Items_FX.resourceBundle.getString("SavePrivileges"));
		
		menu_Items_FX.mysqlui.loginNameDescriptionLable.setText(menu_Items_FX.resourceBundle.getString("loginNameDescription"));
		menu_Items_FX.mysqlui.authenticationTypeLabelDescription.setText(menu_Items_FX.resourceBundle.getString("authenticationTypeDescription"));
		menu_Items_FX.mysqlui.authenticationStringLabelDescription.setText(menu_Items_FX.resourceBundle.getString("Authenticationpluginspecificparameters"));
		menu_Items_FX.mysqlui.hostmatchingDescriptionLable.setText(menu_Items_FX.resourceBundle.getString("%and_wildcardsmaybeused,%accessesfromanywhere"));
		menu_Items_FX.mysqlui.passwordLastchangedLabel.setText(menu_Items_FX.resourceBundle.getString("PasswordLastChanged"));
		menu_Items_FX.mysqlui.passwordDescriptionLabel.setText(menu_Items_FX.resourceBundle.getString("Enterthepasswordtoresetit.Followthepasswordrequiements"));
		menu_Items_FX.mysqlui.confirmPasswordDescriptionLabel.setText(menu_Items_FX.resourceBundle.getString("Enterthepasswordagaintoconfirm"));
		menu_Items_FX.mysqlui.maxQueriesDescription.setText(menu_Items_FX.resourceBundle.getString("Thenumberofqueriestheaccountcanexecutewithinonehour"));
		menu_Items_FX.mysqlui.maxUpdatesDescription.setText(menu_Items_FX.resourceBundle.getString("Thenumberofupdatestheaccountcanexecutewithinonehour"));
		menu_Items_FX.mysqlui.maxConnectionsDescription.setText(menu_Items_FX.resourceBundle.getString("Thenumberofupdatestheaccountcanexecutewithinonehour"));
		menu_Items_FX.mysqlui.concurrentConnectionsDescription.setText(menu_Items_FX.resourceBundle.getString("Thenumberofupdatestheaccountcanexecutewithinonehour"));
		menu_Items_FX.mysqlui.allsSchemaSelectedDescriptionLabel.setText(menu_Items_FX.resourceBundle.getString("Thisrulewillapplytoanyschemaname"));
		menu_Items_FX.mysqlui.schemaMatchingPatternDescriptionLabel.setText(menu_Items_FX.resourceBundle.getString("Youmayuse%and_aswildcardsinapattern."));
		menu_Items_FX.mysqlui.selectedSchemaDescription.setText(menu_Items_FX.resourceBundle.getString("Selectaspecificschemafortheruletoapplyto"));
		
		
		menu_Items_FX.mysqlui.searchDatabasesLabel.setText(menu_Items_FX.resourceBundle.getString("Find"));
		menu_Items_FX.mysqlui.hboxDescriptionLabel.setText(menu_Items_FX.resourceBundle.getString("Description"));
		menu_Items_FX.mysqlui.statusVariableLabel.setText(menu_Items_FX.resourceBundle.getString("Value"));
		menu_Items_FX.mysqlui.variableNameLabel.setText(menu_Items_FX.resourceBundle.getString("Name"));
		menu_Items_FX.mysqlui.saveVariablebtn.setText(menu_Items_FX.resourceBundle.getString("SaveButton"));
		menu_Items_FX.mysqlui.saveVariablebtn.setText(menu_Items_FX.resourceBundle.getString("ReadOnly"));
		menu_Items_FX.mysqlui.refreshPerformanceButton.setText(menu_Items_FX.resourceBundle.getString("Refresh"));
		menu_Items_FX.mysqlui.exportPerformanceButton.setText(menu_Items_FX.resourceBundle.getString("Export"));
		menu_Items_FX.mysqlui.performanceCopySelected.setText(menu_Items_FX.resourceBundle.getString("CopySelected"));
		menu_Items_FX.mysqlui.performanceCopyQuery.setText(menu_Items_FX.resourceBundle.getString("CopyQuery"));
				
		
//		/*
//		 * menu_Items_FX.mysqlui.alterPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("ALTER"));
//		 * menu_Items_FX.mysqlui.alterRoutinePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("ALTERROUTINE"));
//		 * menu_Items_FX.mysqlui.createPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("CREATE"));
//		 * menu_Items_FX.mysqlui.createRolePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("CREATEROLE"));
//		 * menu_Items_FX.mysqlui.createRoutinePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("CREATEROUTINE"));
//		 * menu_Items_FX.mysqlui.createTableSpacePrivilegeCheckBox.setText(menu_Items_FX
//		 * .resourceBundle.getString("CREATETABLESPACE"));
//		 * menu_Items_FX.mysqlui.createTemporaryTablesPrivilegeCheckBox.setText(
//		 * menu_Items_FX.resourceBundle.getString("CREATETEMPORARYTABLES"));
//		 * menu_Items_FX.mysqlui.createUserPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("CREATEUSER"));
//		 * menu_Items_FX.mysqlui.createViewPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("CREATEVIEW"));
//		 * menu_Items_FX.mysqlui.deletePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("DELETE"));
//		 * 
//		 * menu_Items_FX.mysqlui.dropPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("DROP"));
//		 * menu_Items_FX.mysqlui.dropRolePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("DROPROLE"));
//		 * menu_Items_FX.mysqlui.eventPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("EVENT"));
//		 * menu_Items_FX.mysqlui.executePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("EXECUTE"));
//		 * menu_Items_FX.mysqlui.filePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("FILE"));
//		 * menu_Items_FX.mysqlui.grantOptionPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("GRANTOPTION"));
//		 * menu_Items_FX.mysqlui.indexPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("INDEX"));
//		 * menu_Items_FX.mysqlui.insertPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("INSERT"));
//		 * menu_Items_FX.mysqlui.lockTablesPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("LOCKTABLES"));
//		 * menu_Items_FX.mysqlui.processPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("PROCESS"));
//		 * 
//		 * menu_Items_FX.mysqlui.referencesPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("REFERENCES"));
//		 * menu_Items_FX.mysqlui.reloadPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("RELOAD"));
//		 * menu_Items_FX.mysqlui.replicationSlavePrivilegeCheckBox.setText(menu_Items_FX
//		 * .resourceBundle.getString("REPLICATIONSLAVE"));
//		 * menu_Items_FX.mysqlui.replicationClientPrivilegeCheckBox.setText(
//		 * menu_Items_FX.resourceBundle.getString("REPLICATIONCLIENT"));
//		 * menu_Items_FX.mysqlui.selectPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("SELECT"));
//		 * menu_Items_FX.mysqlui.showDatabasesPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("SHOWDATABASES"));
//		 * menu_Items_FX.mysqlui.showViewPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("SHOWVIEW"));
//		 * menu_Items_FX.mysqlui.shutdowmPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("SHUTDOWN"));
//		 * menu_Items_FX.mysqlui.superPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("SUPER"));
//		 * menu_Items_FX.mysqlui.triggerPrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("TRIGGER"));
//		 * menu_Items_FX.mysqlui.updatePrivilegeCheckBox.setText(menu_Items_FX.
//		 * resourceBundle.getString("UPDATE"));
//		 */
	}
}
