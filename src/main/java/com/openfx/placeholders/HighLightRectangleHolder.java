package com.openfx.placeholders;

import java.util.ArrayList;

import org.openjfx.fx.Menu_Items_FX;
import org.openjfx.graphics.DataBricksRectangle;
import org.openjfx.graphics.DuckDBRectangle;
import org.openjfx.graphics.MSSQLServerRectangle;
import org.openjfx.graphics.MariaDBRectangle;
import org.openjfx.graphics.MySqlRectangle;
import org.openjfx.graphics.OracleRectangle;
import org.openjfx.graphics.PostgreeSqlRectangle;
import org.openjfx.graphics.SapHanaRectangle;
import org.openjfx.graphics.SqliteRectangle;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class HighLightRectangleHolder {
	  
    private ArrayList<Rectangle> avaialbleHighRectangleConnections;

	
	public HighLightRectangleHolder(ArrayList<Rectangle> avaialbleHighRectangleConnections){
		
		this.avaialbleHighRectangleConnections = avaialbleHighRectangleConnections;
		
	}
	
	
	public StackPane getHighlightRectangleMySql(String connectionName) {
		MySqlRectangle highlightRectangleMySql = new MySqlRectangle(100.0,150.0);
		this.avaialbleHighRectangleConnections.add(highlightRectangleMySql);
		
	    StackPane stackPaneMySql = new StackPane();
	    stackPaneMySql.setId("stackPaneMySql");
	     highlightRectangleMySql.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		            new Stop[]{
		            new Stop(0,Color.WHITE),
		            new Stop(0.5, Color.WHITE),
		            new Stop(1,Color.WHITE),}));
	        highlightRectangleMySql.setStroke(Color.WHITE);
	        highlightRectangleMySql.setArcHeight(3.5);
	        highlightRectangleMySql.setArcWidth(3.5);
             
	    
	        Label mySqlLabel = new Label();
	        if(connectionName == null) 
	        	mySqlLabel.setText("My SQL");
	        else
	        	mySqlLabel.setText(connectionName);
	        mySqlLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/mysqlLogo.png"))));
	        mySqlLabel.setContentDisplay(ContentDisplay.TOP);	    
	        stackPaneMySql.getChildren().addAll(highlightRectangleMySql, mySqlLabel);
	        
	        return stackPaneMySql;
	}

	public StackPane getHighlightRectanglePostgreeSql(String connectionName){
		
        StackPane stackPanePostgreeSql = new StackPane();
        PostgreeSqlRectangle highlightRectanglePostgreeSql = new PostgreeSqlRectangle(100.0, 150.0);
    	this.avaialbleHighRectangleConnections.add(highlightRectanglePostgreeSql);
        highlightRectanglePostgreeSql.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.WHITE),
	            new Stop(0.5, Color.WHITE),
	            new Stop(1,Color.WHITE),}));
        highlightRectanglePostgreeSql.setStroke(Color.WHITE);
        highlightRectanglePostgreeSql.setArcHeight(3.5);
        highlightRectanglePostgreeSql.setArcWidth(3.5);

        Label postgreeSqlLabel = new Label();
        if(connectionName == null) 
        	postgreeSqlLabel.setText("PostgreSQL");
        else
        	postgreeSqlLabel.setText(connectionName);
	    postgreeSqlLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/postgreeLogo.png"))));
	    postgreeSqlLabel.setContentDisplay(ContentDisplay.TOP);
	    
	    stackPanePostgreeSql.getChildren().addAll(highlightRectanglePostgreeSql, postgreeSqlLabel);
	    
	    return stackPanePostgreeSql;
	}
	
	public StackPane getHighlightRectangleSqlite(String connectionName) {	
        StackPane stackPaneSqlite = new StackPane();
        SqliteRectangle highlightRectangleSqlite = new SqliteRectangle(130.0, 150.0);
        this.avaialbleHighRectangleConnections.add(highlightRectangleSqlite);
        highlightRectangleSqlite.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.WHITE),
	            new Stop(0.5, Color.WHITE),
	            new Stop(1,Color.WHITE),}));
        highlightRectangleSqlite.setStroke(Color.WHITE);
        highlightRectangleSqlite.setArcHeight(3.5);
        highlightRectangleSqlite.setArcWidth(3.5);

        Label sqliteLabel = new Label();
        if(connectionName == null) 
        	 sqliteLabel.setText("SQLite");
        else
        	sqliteLabel.setText(connectionName);
        sqliteLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/sqliteLogo.png"))));
        sqliteLabel.setContentDisplay(ContentDisplay.TOP);
        
	    stackPaneSqlite.getChildren().addAll(highlightRectangleSqlite, sqliteLabel);
	    
	    return stackPaneSqlite;
	}
	
	public StackPane getHighlightRectangleDatabricks(String connectionName) {
		
		  StackPane stackPanedatabricks = new StackPane();
		  DataBricksRectangle highlightRectangledatabricks = new DataBricksRectangle(100.0, 150.0);   
		  this.avaialbleHighRectangleConnections.add(highlightRectangledatabricks);
	      highlightRectangledatabricks.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		            new Stop[]{
		            new Stop(0,Color.WHITE),
		            new Stop(0.5, Color.WHITE),
		            new Stop(1,Color.WHITE),}));
	      highlightRectangledatabricks.setStroke(Color.WHITE);
	      highlightRectangledatabricks.setArcHeight(3.5);
	      highlightRectangledatabricks.setArcWidth(3.5);

	      Label databricksLabel = new Label();
	      if(connectionName == null) 
	    	  databricksLabel.setText("Databricks");
	        else
	        	databricksLabel.setText(connectionName);
	     
	      databricksLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/databricksLogo.png"))));
	      databricksLabel.setContentDisplay(ContentDisplay.TOP);
	    
	      stackPanedatabricks.getChildren().addAll(highlightRectangledatabricks, databricksLabel);
	      
	      return stackPanedatabricks;
	}
	
	public StackPane getHighlightRectanglesaphana(String connectionName) {
		
        StackPane stackPaneSapHana = new StackPane();
        SapHanaRectangle highlightRectanglesaphana = new SapHanaRectangle(100.0, 150.0);
		this.avaialbleHighRectangleConnections.add(highlightRectanglesaphana);
        highlightRectanglesaphana.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.WHITE),
	            new Stop(0.5, Color.WHITE),
	            new Stop(1,Color.WHITE),}));
        highlightRectanglesaphana.setStroke(Color.WHITE);
        highlightRectanglesaphana.setArcHeight(3.5);
        highlightRectanglesaphana.setArcWidth(3.5);

        Label saphanaLabel = new Label();
        if(connectionName == null)
        	saphanaLabel.setText("SapHana");
        else
        	saphanaLabel.setText(connectionName);
        saphanaLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/saphanaLogo.png"))));
        saphanaLabel.setContentDisplay(ContentDisplay.TOP);
        stackPaneSapHana.getChildren().addAll(highlightRectanglesaphana, saphanaLabel);
        
        return stackPaneSapHana;

	}
	
	public StackPane getHighlightRectangleOracle(String connectionName) {
		
        StackPane stackPaneOracle = new StackPane();
        OracleRectangle highlightRectangleOracle = new OracleRectangle(100.0,150.0);
    	this.avaialbleHighRectangleConnections.add(highlightRectangleOracle);
        highlightRectangleOracle.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.WHITE),
	            new Stop(0.5, Color.WHITE),
	            new Stop(1,Color.WHITE),}));
        highlightRectangleOracle.setStroke(Color.WHITE);
        highlightRectangleOracle.setArcHeight(3.5);
        highlightRectangleOracle.setArcWidth(3.5);

        Label oracleLabel = new Label();
        if(connectionName == null)
        	oracleLabel.setText("Oracle");
        else
        	oracleLabel.setText(connectionName);
        oracleLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/oracleLogo.png"))));
        oracleLabel.setContentDisplay(ContentDisplay.TOP);
	    
        stackPaneOracle.getChildren().addAll(highlightRectangleOracle, oracleLabel);
        
        return stackPaneOracle;
	}
	
	public StackPane getHighlightRectangleMSSQLServer(String connectionName) {
		
		StackPane stackPaneMssql = new StackPane();
		MSSQLServerRectangle highlightRectangleMSSQLServer = new MSSQLServerRectangle(100.0, 150.0);
    	this.avaialbleHighRectangleConnections.add(highlightRectangleMSSQLServer);
		highlightRectangleMSSQLServer.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		            new Stop[]{
		            new Stop(0,Color.WHITE),
		            new Stop(0.5, Color.WHITE),
		            new Stop(1,Color.WHITE),}));
		highlightRectangleMSSQLServer.setStroke(Color.WHITE);
		highlightRectangleMSSQLServer.setArcHeight(3.5);
		highlightRectangleMSSQLServer.setArcWidth(3.5);

	    Label mssqlLabel = new Label();
	    if(connectionName == null)
	    	mssqlLabel.setText("MS SQL");
	    else
	    	mssqlLabel.setText(connectionName);
	    mssqlLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/MssqlServer.png"))));
	    mssqlLabel.setContentDisplay(ContentDisplay.TOP);
		    
	    stackPaneMssql.getChildren().addAll(highlightRectangleMSSQLServer, mssqlLabel);
	    
	    return stackPaneMssql;
	}
	
	public StackPane getHighlightRectangleDuckDB(String connectionName) {
		
	    StackPane stackPaneDuckDB = new StackPane();
	    DuckDBRectangle highlightRectangleDuckDB = new DuckDBRectangle(100.0, 150.0);
    	this.avaialbleHighRectangleConnections.add(highlightRectangleDuckDB);
	    highlightRectangleDuckDB.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.WHITE),
	            new Stop(0.5, Color.WHITE),
	            new Stop(1,Color.WHITE),}));
	    highlightRectangleDuckDB.setStroke(Color.WHITE);
	    highlightRectangleDuckDB.setArcHeight(3.5);
	    highlightRectangleDuckDB.setArcWidth(3.5);

        Label duckDBLabel = new Label();
        if(connectionName == null)
        	duckDBLabel.setText("Duck DB");
        else
        	duckDBLabel.setText(connectionName);
        duckDBLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/duckDBLogo.png"))));
        duckDBLabel.setContentDisplay(ContentDisplay.TOP);
	    
        stackPaneDuckDB.getChildren().addAll(highlightRectangleDuckDB, duckDBLabel);
        
        return stackPaneDuckDB;
		
	}
	
	public StackPane getHighlightRectangleMariaDB(String connectionName) {
		MariaDBRectangle highlightRectangleMariaDB = new MariaDBRectangle(100.0,150.0);
		this.avaialbleHighRectangleConnections.add(highlightRectangleMariaDB);
		
	    StackPane stackPaneMariaDB = new StackPane();
	     highlightRectangleMariaDB.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		            new Stop[]{
		            new Stop(0,Color.WHITE),
		            new Stop(0.5, Color.WHITE),
		            new Stop(1,Color.WHITE),}));
	     highlightRectangleMariaDB.setStroke(Color.WHITE);
	        highlightRectangleMariaDB.setArcHeight(3.5);
	        highlightRectangleMariaDB.setArcWidth(3.5);

	        Label mariaDBLabel = new Label();
	        if(connectionName == null) 
	        	mariaDBLabel.setText("MariaDB");
	        else
	        	mariaDBLabel.setText(connectionName);
	        mariaDBLabel.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/graphics/MariaDBLogo.jpg"))));
	        mariaDBLabel.setContentDisplay(ContentDisplay.TOP);	    
	        stackPaneMariaDB.getChildren().addAll(highlightRectangleMariaDB, mariaDBLabel);
	        
	        return stackPaneMariaDB;
	}
	
}
