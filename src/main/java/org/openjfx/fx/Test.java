package org.openjfx.fx;

import java.util.ArrayList;
import java.util.List;

import com.openfx.constants.MySQLConstants;

public class Test {

	
	public static void main(String[] args) {

		/*
	 	String mysql = "CREATE TABLE `address` (\r\n"
	 			+ "  `address_id` smallint unsigned NOT NULL AUTO_INCREMENT,\r\n"
	 			+ "  `address` varchar(50) NOT NULL,\r\n"
	 			+ "  `address2` varchar(50) DEFAULT NULL,\r\n"
	 			+ "  `district` varchar(20) NOT NULL,\r\n"
	 			+ "  `city_id` smallint unsigned NOT NULL,\r\n"
	 			+ "  `postal_code` varchar(10) DEFAULT NULL,\r\n"
	 			+ "  `phone` varchar(20) NOT NULL,\r\n"
	 			+ "  `location` geometry NOT NULL ,\r\n"
	 			+ "  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\r\n"
	 			+ "  PRIMARY KEY (`address_id`),\r\n"
	 			+ "  KEY `idx_fk_city_id` (`city_id`),\r\n"
	 			+ "  SPATIAL KEY `idx_location` (`location`),\r\n"
	 			+ "  CONSTRAINT `fk_address_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE RESTRICT ON UPDATE CASCADE\r\n"
	 			+ ") ENGINE=InnoDB AUTO_INCREMENT=606 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
		
		String mysqlQuerySplit[] = mysql.split("\n");
		
		for(int i=0;i<mysqlQuerySplit.length;i++) {
			
			if(mysqlQuerySplit[i].contains("CONSTRAINT") || mysqlQuerySplit[i].contains("FOREIGN KEY")) {
			
				System.out.println( mysqlQuerySplit[i]);
			}
			
		}
		
		System.out.println(MySQLConstants.valueOf("admin_p"));
	 	*/
		
		String procedureSql = "CREATE DEFINER=`root`@`localhost` PROCEDURE `film_in_stock`(IN p_film_id INT, IN p_store_id INT, OUT p_film_count INT)    "
				+ "READS SQL DATABEGIN     SELECT inventory_id     FROM inventory     "
				+ "WHERE film_id = p_film_id     AND store_id = p_store_id     AND inventory_in_stock(inventory_id);   "
				+ " SELECT FOUND_ROWS() INTO p_film_count;END";
	  	
		
		
			
			
			System.out.println( procedureSql.indexOf("IN ", 0));
			
			List<Integer> indexes = new ArrayList<Integer>();
		    String lowerCaseTextString = procedureSql;
		    String lowerCaseWord = "IN ";

		    int index = 0;
		    while(index != -1){
		    index = lowerCaseTextString.indexOf(lowerCaseWord, index);
		        if (index != -1) {
		                indexes.add(index);
		                index++;
		        }
		    }
		       
		    System.out.println(indexes.toString());
		
		
		
	 	
	}

}
