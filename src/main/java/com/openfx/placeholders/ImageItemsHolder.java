package com.openfx.placeholders;

import java.util.logging.ConsoleHandler;

import org.openjfx.fx.Main;

import com.openfx.ai.ConnectionsConstants;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageItemsHolder {
	
	
	public static ImageView getTableImage() {
		
		Image imageDatbaseTable = new Image(Main.class.getResourceAsStream("/images/table.png"));
		ImageView imageDatbaseTablenode = new ImageView(imageDatbaseTable);
		imageDatbaseTablenode.setFitHeight(20);
		imageDatbaseTablenode.setFitWidth(20);
		imageDatbaseTablenode.setPreserveRatio(true);
		
		return imageDatbaseTablenode;
	}
	
	public static ImageView getMySqlImage(String connectionName) {
		
		Image imageMySQL = new Image(ImageItemsHolder.class.getResourceAsStream("/images/mysql.png"));
		ImageView imageMySQLnode = new ImageView(imageMySQL);
		imageMySQLnode.setId(ConnectionsConstants.MySql+"##"+connectionName);  
		imageMySQLnode.setFitHeight(20);
		imageMySQLnode.setFitWidth(20);
		imageMySQLnode.setPreserveRatio(true);
		
		return imageMySQLnode;
						
	}
	
	public static ImageView getPostgreeSqlImage(String connectionName) {
		

		Image imagePostgreeSQL = new Image(ImageItemsHolder.class.getResourceAsStream("/images/postgreeLogo.png"));
		ImageView imagePostgreeSqlnode = new ImageView(imagePostgreeSQL);
		imagePostgreeSqlnode.setId(ConnectionsConstants.PostgreeSql+"##"+connectionName);
		imagePostgreeSqlnode.setFitHeight(20);
		imagePostgreeSqlnode.setFitWidth(20);
		imagePostgreeSqlnode.setPreserveRatio(true);
		
		return imagePostgreeSqlnode;
						
	}
	
	public static ImageView getDuckDBImage(String connectionName) {
		

		Image imageDuckDB = new Image(ImageItemsHolder.class.getResourceAsStream("/graphics/duckDBLogo.png"));
		ImageView imageDuckDBnode = new ImageView(imageDuckDB);
		imageDuckDBnode.setId(ConnectionsConstants.DuckDB+"##"+connectionName);  // This id will reflect for SQL editer
		imageDuckDBnode.setFitHeight(20);
		imageDuckDBnode.setFitWidth(20);
		imageDuckDBnode.setPreserveRatio(true);
		
		return imageDuckDBnode;
						
	}
	
	public static ImageView getDatabricksImage(String connectionName) {
		
		Image imageDatabricks = new Image(ImageItemsHolder.class.getResourceAsStream("/images/databricks.png"));
		ImageView imageDatabricksnode = new ImageView(imageDatabricks);
		imageDatabricksnode.setId(ConnectionsConstants.Databricks+"##"+connectionName);
		imageDatabricksnode.setFitHeight(20);
		imageDatabricksnode.setFitWidth(20);
		imageDatabricksnode.setPreserveRatio(true);
		
		return imageDatabricksnode;
						
	}
	
	public static ImageView getOracleImage(String connectionName) {
		
		Image imageOracle = new Image(ImageItemsHolder.class.getResourceAsStream("/graphics/oracleLogo.png"));
		ImageView imageOraclenode = new ImageView(imageOracle);
		imageOraclenode.setId(ConnectionsConstants.Oracle+"##"+connectionName);
		imageOraclenode.setFitHeight(20);
		imageOraclenode.setFitWidth(20);
		imageOraclenode.setPreserveRatio(true);

		
		return imageOraclenode;
						
	}
	
	public static ImageView getSaphanaImage(String connectionName) {
		
		Image imageSapHana = new Image(ImageItemsHolder.class.getResourceAsStream("/images/saphana.png"));
		ImageView imageSapHananode = new ImageView(imageSapHana);
		imageSapHananode.setId(ConnectionsConstants.SapHana+"##"+connectionName);
		imageSapHananode.setFitHeight(20);
		imageSapHananode.setFitWidth(20);
		imageSapHananode.setPreserveRatio(true);
	
		return imageSapHananode;
	}
		
	public static ImageView getSqliteImage(String connectionName) {
		
		Image imageSQLite = new Image(ImageItemsHolder.class.getResourceAsStream("/images/sqlite.png"));
		ImageView imageSQLitenode = new ImageView(imageSQLite);
		imageSQLitenode.setId(ConnectionsConstants.Sqlite+"##"+connectionName);
		imageSQLitenode.setFitHeight(20);
		imageSQLitenode.setFitWidth(20);
		imageSQLitenode.setPreserveRatio(true);

	
		return imageSQLitenode;
	}
	
	public static ImageView getMSSQLServerImage(String connectionName) {
		
		Image imageMssqlServer = new Image(ImageItemsHolder.class.getResourceAsStream("/graphics/MssqlServer.png"));
		ImageView imageMssqlServernode = new ImageView(imageMssqlServer);
		imageMssqlServernode.setId(ConnectionsConstants.MSSQLSErver+"##"+connectionName);
		imageMssqlServernode.setFitHeight(20);
		imageMssqlServernode.setFitWidth(20);
		imageMssqlServernode.setPreserveRatio(true);

	
		return imageMssqlServernode;
	}

	public static ImageView contructImageView(Node graphic) {
		
		if(graphic.getId().contains(ConnectionsConstants.MySql)) {
		
			return getMySqlImage(graphic.getId().split("##")[1]);
		}
		if(graphic.getId().contains(ConnectionsConstants.PostgreeSql)) {
			
			return getPostgreeSqlImage(graphic.getId().split("##")[1]);
		}
		if(graphic.getId().contains(ConnectionsConstants.Sqlite)) {
			
			return getSqliteImage(graphic.getId().split("##")[1]);
		}
		if(graphic.getId().contains(ConnectionsConstants.SapHana)) {
			
			return getSaphanaImage(graphic.getId().split("##")[1]);
		}
		if(graphic.getId().contains(ConnectionsConstants.Databricks)) {
			
			return getDatabricksImage(graphic.getId().split("##")[1]);
		}
		if(graphic.getId().contains(ConnectionsConstants.Oracle)) {
			
			return getOracleImage(graphic.getId().split("##")[1]);
		}
		if(graphic.getId().contains(ConnectionsConstants.MSSQLSErver)) {
			
			return getMSSQLServerImage(graphic.getId().split("##")[1]);
		}
		if(graphic.getId().contains(ConnectionsConstants.DuckDB)) {
			
			return getDuckDBImage(graphic.getId().split("##")[1]);
		}
		
		return null;

	
		
	}
		
	
					

}
