package org.openjfx.fx;

public class Test {

	public static void main(String[] args) {

		String[] sqlConstantsWithoutResultSet = {"USE","ALTER","CREATE"};
		String sqlQueryTextToRun = "use mydatabase";
		
		boolean sqlQueryTextToRunWithoutResultSet = false;


		for(int i=0;i<sqlConstantsWithoutResultSet.length;i++)
		{
			sqlQueryTextToRunWithoutResultSet = sqlQueryTextToRun.trim().toUpperCase().startsWith(sqlConstantsWithoutResultSet[i]) ?  true : false;
			if(sqlQueryTextToRunWithoutResultSet)
				break;
		}
		System.out.println(sqlQueryTextToRunWithoutResultSet);

	}

}
