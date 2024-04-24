/**
 * 
 */
package com.LMS.LMSAPIAutomation.BusinessScenarios;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.Resources.Log4j;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;


/**
 * @author sa185402
 * This Class holds the Methods -- 
 *  - deleteDBRecords, - dbtables_RecordCount_Empty, - clearFiles_Import_Success_Reject_Folders
 *  - copy_TestDate_OUTFolder
 */
public class BusinessScenarios {
	
	static ReadProperties prop = new ReadProperties();
	
	/**
	 * retriveDB -- Retrieve the value from the database
	 * @param dB - Instance Name
	 * @param dB_Name - Database Name
	 * @param SQLQuery - SQL Query
	 * @param columnName -- Name of the column
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 *  
	 * **/
	
	public static String retriveDB( String dB, String dB_Name,String SQLQuery,String columnName) throws ClassNotFoundException, SQLException {
		
		String str = "";
		try {
			Resources.parameters.put("dB", dB);
			Connection dB_Connection = GenericMethods.connecttoDBServer(dB, retrive_DBName(dB_Name),retrive_DBName("USERNAME"), retrive_DBName("PASSWORD"));
			ResultSet rs  = GenericMethods.retriveDatabaseRecord(dB_Connection, SQLQuery);
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();
			if (size>0) {
				
				while (rs.next()) {
					
					ResultSetMetaData resultSetMetaData= rs.getMetaData();
					String type = resultSetMetaData.getColumnTypeName(rs.findColumn(columnName));
					if(type.equalsIgnoreCase("nvarchar")) {
						str = rs.getString(columnName);
					}else if(type.equalsIgnoreCase("varchar")) {
						str = rs.getString(columnName)+"";
					}else if(type.equalsIgnoreCase("smalldatetime")){
						str = rs.getDate(columnName)+"";
					}else if(type.equalsIgnoreCase("datetime")) {
						str = rs.getDate(columnName)+"";
					}else if(type.equalsIgnoreCase("int")) {
						str = rs.getInt(columnName)+"";
					}else
						//str = rs.getInt(columnName)+"";
						str = rs.getString(columnName)+"";
					}
				}
			dB_Connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log4j.error(e.getMessage());
		}
			
		return str;
		
	}
	
	public static void signer_DocuSign(String path_Signerjarfile, String folderName) {
		try {
//			
			String[] command = {"java", "-jar" , path_Signerjarfile, "verify", "-d", folderName, "-w", "**.dll|**.exe|**.msi"};
			Process p = GenericMethods.commandLine_Execution(command);
			BufferedReader r= new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			Thread.sleep(1000);
	        while (true) {
	            line = r.readLine();
	            System.out.println(line);
	            Log4j.loginfo(line);
	            if (line == null) { break; }
	            //System.out.println(line);
	            Log4j.loginfo(line);
	        }
			
		} catch (Exception e) {
			Log4j.error(e.getMessage());
			assertTrue(false, e.getMessage());
		}
	}
	
	public static String retrive_DBName(String dBName) {
		
		String dB = "";
		dBName = dBName.toUpperCase();
		String dBServer = prop.dB_Server();
		
		if ((dBServer.toUpperCase()).contains("SER36VVM310")) {
			dBServer = prop.dB_Server()+"\\"+Resources.parameters.get("dB");
		}else if((dBServer.toUpperCase()).contains("SRRAADEVGUI")) {
			dBServer = prop.dB_Server();
		}
		
		switch (dBServer.toUpperCase()) {
		
		case "SER36VVM310\\OPERATIONAL":
			
			HashMap<String, String> ser36vvm310_DBnames = new HashMap<String, String>();
			ser36vvm310_DBnames.put("USERNAME", "sa");
			ser36vvm310_DBnames.put("PASSWORD", "Abcd1234@");
			ser36vvm310_DBnames.put("HOST", "HOST_LMS_MAIN");
			ser36vvm310_DBnames.put("INTERFACE", "INTERFACE_LMS");
			ser36vvm310_DBnames.put("LOYALTY", "Loyalty_LMS_MAIN");
			ser36vvm310_DBnames.put("MP", "MP_LMS");
			ser36vvm310_DBnames.put("PROMOTION", "Promotion_LMS");
			
			dB = ser36vvm310_DBnames.get(dBName);
			
			break;
		
		case "SER36VVM310\\ONLINE":
			
			HashMap<String, String> ser36vvm310_Online_DBname = new HashMap<String, String>();
			ser36vvm310_Online_DBname.put("USERNAME", "sa");
			ser36vvm310_Online_DBname.put("PASSWORD", "Abcd1234@");
			ser36vvm310_Online_DBname.put("LOYALTYONLINE", "LoyaltyOnline_LMS_MAIN");
			
			dB = ser36vvm310_Online_DBname.get(dBName);
			break;
			
		case "SRRAADEVGUI":
			
			HashMap<String, String> SRRAADEVGUI = new HashMap<String, String>();
			SRRAADEVGUI.put("USERNAME", "sa");
			SRRAADEVGUI.put("PASSWORD", "Abcd1234@");
			SRRAADEVGUI.put("HOST", "HOST_LMS_1");
			SRRAADEVGUI.put("INTERFACE", "INTERFACE_LMS_1");
			SRRAADEVGUI.put("LOYALTY", "Loyalty_LMS_1");
			SRRAADEVGUI.put("LOYALTYONLINE", "LoyaltyOnline_LMS_1");
			SRRAADEVGUI.put("MP", "MP_LMS");
			SRRAADEVGUI.put("PROMOTION", "PROMOTION_LMS_1");
			
			dB = SRRAADEVGUI.get(dBName);
			break;
		default:
			break;
		}
		
		
		return dB;
		
		
	}

	public static void UpdateDB( String dB, String dB_Name,String SQLQuery) throws SQLException {

		String str = "";
		try {
			Resources.parameters.put("dB", dB);
			Connection dB_Connection = GenericMethods.connecttoDBServer(dB, retrive_DBName(dB_Name),retrive_DBName("USERNAME"), retrive_DBName("PASSWORD"));
			GenericMethods.updateDBRecord(dB_Connection, SQLQuery);

			dB_Connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log4j.error(e.getMessage());
		}


	}


}
