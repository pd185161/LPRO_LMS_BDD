/**
 *  Generic Method JAVA Files holds all the Generic Methods
 */
package com.LMS.LMSAPIAutomation.Generic;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.commons.io.FileUtils;
import com.LMS.LMSAPIAutomation.Resources.Log4j;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;
import com.jayway.jsonpath.JsonPath;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * @author sa185402
 * This Class Holds the Methods --
 * 	- fileTransfer, - DatabaseConnection, - DeleteDataBaseRecord
 *  - SQLRecordCount, - clear_Files_Folder, - delete_Folder, - getfilecount_Folder
 */
public class GenericMethods {

	static ReadProperties prop = new ReadProperties();
	
	/**
	 * Generate UUID 
	 * Example Format : f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454
	 * **/
	public static String UUIDGenerator() {
		
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
		
	}

	
	/**
	 * Copy Directory from Source Folder to Destination Folder
	 * 
	 * **/

	public static void fileTransfer(String Source_Path, String Destination_Path) throws IOException {
		FileUtils.copyDirectoryToDirectory(new File(Source_Path),new File(Destination_Path));
		Log4j.loginfo(Source_Path + " File is copied sucessfully");	
	}
	
	/**
	 * Copy File from Source Folder to Destination Folder
	 * 
	 * **/
	public static void Copyfile(String Source_Path, String Destination_Path) throws IOException {
		FileUtils.copyFile(new File(Source_Path),new File(Destination_Path));
	}
	
	/**
	 * Establish the connect to Database 
	 * Inputs: DB_URL, UserName , Password,
	 * return DB Connection
	 * */
	
	public static Connection DatabaseConnection(String DB_URL, String USER, String PASSWORD) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			Log4j.loginfo("DataBase Connection Established successfully, DB URL : " + DB_URL);
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.error("DatabaseConnection is Failed  due to Error : " + e.getMessage());
			assertTrue(false, e.getMessage());
			//assertTrue(false);
		}
		return conn;
		
	}
	
	/**
	 * connecttoDBServer -- Connect to the respective database in a server defined in Environment variables
	 * @param dB_Name - Database Name
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 *  
	 * **/
	
	public static Connection connecttoDBServer(String dB, String dB_Name, String userName, String password) {
		
		Connection dB_Connection = null;
		String DB_URL = "";
		String dB_Server = prop.dB_Server();
		
		try {
			
			if((dB.toUpperCase()).contains("ONLINE") && (dB_Server.toUpperCase()).contains("SER36VVM310")) {
				 DB_URL =  "jdbc:sqlserver://SER36VVM310\\ONLINE; databaseName= "+ dB_Name + ";encrypt=true;trustServerCertificate=true";
			}else if((dB.toUpperCase()).contains("OPERATIONAL") && (dB_Server.toUpperCase()).contains("SER36VVM310")){
				 DB_URL =  "jdbc:sqlserver://SER36VVM310\\OPERATIONAL; databaseName= "+ dB_Name + ";encrypt=true;trustServerCertificate=true";
			}else if((dB_Server.toUpperCase()).contains("SRRAADEVGUI")){
				 DB_URL =  "jdbc:sqlserver://SRRAADEVGUI; databaseName= "+ dB_Name + ";encrypt=true;trustServerCertificate=true";
			}
			
			dB_Connection = GenericMethods.DatabaseConnection(DB_URL, userName, password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log4j.error(e.getMessage());
		}
			
			
		return dB_Connection;
	}
	
	/**
	 * Delete the Records from Database 
	 * Inputs: Connection
	 * */
	
	public static void DeleteDataBaseRecord(Connection conn, List<String> SQLQuery) throws SQLException {
		
		try {
			Statement stmt = conn.createStatement();
			for (String str : SQLQuery) {
				stmt.execute(str);
				Log4j.loginfo("Deleted Records from Table :" + str);
			}
			conn.close();
		}catch(Exception e){
			Log4j.error("DeleteDataBaseRecord is Failed  due to Error : " + e.getMessage());
			assertTrue(false, e.getMessage());
			//assertTrue(false);
		}
	}
	
	public static void insertDBRecord(Connection conn, String SQLQuery) {
		
		try {
			Statement stmt = conn.createStatement();
				stmt.execute(SQLQuery);
				Log4j.loginfo("Inserted Records to Table :" + SQLQuery);

			conn.close();
		}catch(Exception e){
			Log4j.error("DeleteDataBaseRecord is Failed  due to Error : " + e.getMessage());
			assertTrue(false, e.getMessage());
			//assertTrue(false);
		}
	}
	public static void updateDBRecord(Connection conn, String SQLQuery) {

		try {
			Statement stmt = conn.createStatement();
			stmt.execute(SQLQuery);
			Log4j.loginfo("DB table updated With query :" + SQLQuery);

			conn.close();
		}catch(Exception e){
			Log4j.error("UpdateDBRecord is Failed  due to Error : " + e.getMessage());
			assertTrue(false, e.getMessage());
			//assertTrue(false);
		}
	}
	
	/**
	 *  SQLRecordCount -- get the records from DB tables
	 * */
	
	public static ResultSet retriveDatabaseRecord(Connection conn, String sqlQuery) {
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sqlQuery);
			Log4j.loginfo("retrive Records from Table :" + sqlQuery);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	/**
	 *  SQLRecordCount -- get the record count from DB tables
	 *  @param conn DB Connection
	 *  @param tables List
	 *  
	 * */
	
	public static List<Integer> SQLRecordCount(Connection conn, List<String> tables) {
		int count = 0;
		List<Integer> list = new ArrayList<Integer>();
		try {
			Statement st = conn.createStatement();
			for (String table : tables) {
				ResultSet res = st.executeQuery("SELECT COUNT(*) FROM "+table);
	            while (res.next()){
	                count = res.getInt(1);
	                list.add(count);
	            }
			}
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.error("SQLRecordCount extraction is Failed due to Error : " + e.getMessage());
			assertTrue(false, e.getMessage());
		}
		return list;
		
	}
	
	/**
	 *  SQLRecordCount -- get the record count from DB tables
	 *  @param conn DB Connection
	 *  @param table - table name
	 *  @return count of records in a table
	 *  
	 * */
	public static int SQLRecordCount(Connection conn, String table) {
		int count = 0;
		
		try {
			Statement st = conn.createStatement();
				ResultSet res = st.executeQuery("SELECT COUNT(*) FROM "+table);
	            while (res.next()){
	                count = res.getInt(1);
	                
	            }
	            conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.error("SQLRecordCount extraction is Failed due to Error : " + e.getMessage());
			assertTrue(false, e.getMessage());
		}
		return count;
		
	}
	
	/**
	 * Clear the Files in the given folder
	 * */
	
	public static void clear_Files_Folder(String FolderPath) throws IOException {
		File F_import = new File(FolderPath);
		
		if(F_import.isDirectory()){
			FileUtils.cleanDirectory(new File(FolderPath));
			Log4j.loginfo(FolderPath + " Folder files are removed successfully");
			int file_count_success = 0;
			Iterator<File> Success_it = FileUtils.iterateFiles(new File(FolderPath), null, false);
			while(Success_it.hasNext())
	       	{
	       	file_count_success++;      
	       	}
			// Verifying the success folder is cleaned successfully
			assertTrue(file_count_success == 0,FolderPath + " Folder is not cleaned");
		}
	}
	
	/**
	 * Delete the Folder 
	 * **/
	
	public static void delete_Folder(String deleteFolder) { 
		
		File f = new File(deleteFolder);
		if(f.isDirectory()){
			try {
				FileUtils.deleteDirectory(new File(deleteFolder));
				Log4j.loginfo(deleteFolder + " Folder is deleted successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				assertTrue(true,e.getMessage());
				Log4j.error(e.getMessage());
			}
		}else {
			Log4j.Warn(deleteFolder + " Folder is not available");
//			assertTrue(f.isDirectory(),deleteFolder + " Folder is not available");
		}
	}
	
	/**
	 * Get Files from the Folder
	 * **/
	
	public static int getfilecount_Folder(String Folder) {
		
		int file_count_success =0 ;
		File f_Success = new File(Folder);
		if(f_Success.isDirectory()){	
			Iterator<File> it = FileUtils.iterateFiles(new File(Folder), null, false);
			
			while(it.hasNext()){
		      	file_count_success++;
		      	Log4j.loginfo(((File) it.next()).getName());     
		       }
		}else{
			//assertTrue(f_Success.isDirectory(),Folder + " Folder is not available");
			Log4j.Warn(Folder + " Folder is not available");
		}
		
		return file_count_success;
		
	}
	
	/**
	 * getTextcount_txtFile --- Retrieve the count of text available in text file
	 * **/
	@SuppressWarnings("deprecation")
	public static int getTextcount_txtFile(String filePath) {
		
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(new File(filePath));
			Log4j.loginfo(filePath + " File is available");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log4j.error(filePath + " File is not available");
			assertTrue(false,filePath + " File is not available");
		}
		
		int count = 0;
		for(int i=0;i<lines.size();i++){
			String list_value = (String) lines.get(i);
			if(list_value.contains("Cache loaded successfully"))
			{
				count++;
			}
		}
		
		return count;
		
	}
	
	/**
	 * Get Current System Date
	 * @return current date of pattern yyyy-MM-dd
	 * **/
	public static String currentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		//System.out.println(dtf.format(now));  
		return dtf.format(now);
		
	}
	
	/**
	 * Get the JSON Attribute Value
	 * @return String
	 * **/
	
	public static String jsonExtraction(String response, String JSONPath) {
		
		String returnValue = null;
		
		try {
			returnValue = JsonPath.read(response, JSONPath);
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.error(e.getMessage());
		}
		
		return returnValue;
	}
	
	/**
	 * getFileLastUpdate 
	 * @param folderPath
	 * @return file name of last updated in folderPath
	 * **/
	
	public static String getFileLastUpdate(String folderPath) {
		
		String File = "";
		
		try{

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
				File file = new File(folderPath);
				File[] files1 = file.listFiles();
				String date = "01/01/2021 00:00:00";
				
				for(File file1 : files1){
					String Filename1 = file1.getName();
					String date1 = simpleDateFormat.format(file1.lastModified());
					if (simpleDateFormat.parse(date).before(simpleDateFormat.parse(date1))) {
						date = date1;
						File = Filename1;
//					}else{
//						File = Filename;
					}
				}

//			System.out.println(File);
		}catch(Exception e){
			Log4j.error(e.getMessage());
		}

		return File;
	}
	
	/**
	 * commandLine_Execution -  This method will execute command via CMD
	 * @param command
	 * @return Process - where we can used to read the data from command prompt
	 * **/
	
	public static Process commandLine_Execution(String[] command) throws IOException {
		
		Process p = null;
		
		try {
			ProcessBuilder builder = new ProcessBuilder(command);
	        builder.redirectErrorStream(true);
	        p = builder.start();
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.error(e.getMessage());
		}
		return p;
		
	}
	
	public static void clearLogFile(String fileLocation) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation));
	        bw.write("");
	        bw.flush();
	        bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.error(e.getMessage());
		}
	}
	
	public static boolean isProcessRunning(String serviceName) {
		
		try {
			Process pro = Runtime.getRuntime().exec("tasklist");
			BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				 System.out.println(line);
				if (line.startsWith(serviceName)) {
					return true;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			Log4j.error(e.getMessage());
		}
		
		return false;
		
	}
	
	public static void killProcess(String serviceName) {
		try {
			int i = 0;
			boolean isrunning = isProcessRunning(serviceName);
			if(i<=3) {
				if(!isrunning) {
					return ;
				}
			}else if(i == 3) {
					return ;
			}
			
			i++;
			Runtime.getRuntime().exec("taskkill /IM " + serviceName);
			//System.out.println(serviceName+" killed successfully!");
			killProcess(serviceName);
			Log4j.loginfo(serviceName + "Process is Stopped Successfully");
		} catch (IOException e) {
			e.printStackTrace();
			Log4j.error(e.getMessage());
		}	
	}

	private static BufferedReader getOutput(Process p) {
    	return new BufferedReader(new InputStreamReader(p.getInputStream()));
	}

	 private static BufferedReader getError(Process p) {
        return new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

	public static int cmd_ProcessBuilder(String fileName, String server, String userName, String password, String batFile_Path){

		String line;
		StringBuilder line1 = new StringBuilder();
		int exitValue = 0;

		try {

//			String[] command = {"CMD", "/C", "PsExec.exe \\\\153.77.180.130 -u HQL\\s_raa_appadmin -p HAha123! cmd /c \"cd /d C:\\Retalix\\LMS\\HQ Loyalty and Promotions.1\\BatchManager && TestBatch_Jmeter.bat\""};
			String psExe = psExec_CMD(server, userName, password, batFile_Path);
			String[] command = {"CMD", "/C", psExe};
			ProcessBuilder probuilder = new ProcessBuilder( command );
			Process process = probuilder.start();

			BufferedReader output = getOutput(process);
			BufferedReader error = getError(process);

			while ((line = output.readLine()) != null) {
            	line1.append(line);
//            System.out.println(line);
        	}

			while ((line = error.readLine()) != null) {
				line1.append(line);
//				 System.out.println(line);
			}

			File f1 = new File("./log/"+fileName+".txt");
                if (!f1.isDirectory()){
                    f1.mkdir();
                }

//        	System.out.println(line1);
			writeTextFile("./log/"+fileName+".txt",line1.toString());

			//Wait to get exit value
			try {
				exitValue = process.waitFor();
				System.out.println("\n\nExit Value is " + exitValue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Log4j.error(e.getMessage());
				fail(e.getMessage());
			}


		}catch (Exception e){
			Log4j.error(e.getMessage());
			fail(e.getMessage());
		}
		return exitValue;
	}

	public static void writeTextFile(String filePath, String text){

		boolean result;

		try {
			File file = new File(filePath);
			result = file.createNewFile();

			if(result){
					Log4j.loginfo("File is created: "+file.getCanonicalPath());
			}else {
				Log4j.loginfo("File already exist at location: "+file.getCanonicalPath());
			}

			FileOutputStream fileOutputStream = new FileOutputStream(file, true);
			fileOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
			fileOutputStream.close();


		}catch (IOException e) {
			Log4j.error(e.getMessage());
			fail(e.getMessage());
		}

	}

	public static String psExec_CMD(String server, String userName, String password, String batFile_Path){

		return "PsExec.exe \\\\"+server+" -u "+userName+" -p "+password+" cmd /c \"cd /d "+batFile_Path+"\"";
	}


    public static List<String> sortFiles(String filePath, String endWith){

        ArrayList<String> arrayList = new ArrayList<>();
        try{
            File f = new File(filePath);

            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File f, String name) {
                    // We want to find only .c files
                    return name.endsWith(endWith);
                }
            };

            // Note that this time we are using a File class as an array,
            // instead of String
            File[] files = f.listFiles(filter);

            // Get the names of the files by using the .getName() method
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
                arrayList.add(files[i].getName());
            }

        }catch (Exception e){

        }
        return arrayList;
    }

    public static boolean fileExtension(String filePath, String endsWith, String fileName){

        boolean flag = false;
        try{

            List<String> files = sortFiles(filePath,endsWith);
            for (String file : files) {

                if (file.contains(fileName)) {
//                    System.out.println("Process Completed Successfully");
                    flag = true;
                    break;
                }

            }
        }catch (Exception e){
			Log4j.error(e.getMessage());
			fail(e.getMessage());
        }

        return flag;
    }

    public static StringBuilder readLogFile(String serverLogFilePath){

        FileInputStream fstream = null;
        String strLine;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fstream = new FileInputStream(serverLogFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
           /* read log line by line */
           while ((strLine = br.readLine()) != null)   {
             /* parse strLine to obtain what you want */
//             System.out.println (strLine);
             stringBuilder.append(strLine+"\n");
           }
            fstream.close();
        } catch (IOException e) {
			Log4j.error(e.getMessage());
			fail(e.getMessage());
        }

        return stringBuilder;
    }

    public static void appendBatchLogFile(String batchLogFileName, String serverLogFilePath){

        try {

            File f2 = new File("./target/BatchFileLogs");
            if (!f2.isDirectory()){
                f2.mkdir();
            }

            File myObj = new File("./target/BatchFileLogs/"+batchLogFileName);
            if (myObj.createNewFile()) {
                Log4j.Warn(batchLogFileName+ " File is created in path ./target/BatchFileLogs/");
            }

           StringBuilder builder = readLogFile(serverLogFilePath);
           FileOutputStream oStream =  new FileOutputStream("./target/BatchFileLogs/"+batchLogFileName);
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oStream));
           bw.write(String.valueOf(builder));
           bw.flush();
           oStream.close();

        } catch (IOException e) {
			Log4j.error(e.getMessage());
			fail(e.getMessage());
        }
    }

	public static String validateImportFile(String importFilePath, String importFileName,String batchLogFileName,String batchErrFileName, String serverLogFilePath,String serverErrFilePath){

        try {
            boolean flag;
            flag = fileExtension(importFilePath,"scc",importFileName);

            if(!flag){
                flag = fileExtension(importFilePath,"wrn",importFileName);
                System.out.println("Read Log File");
                appendBatchLogFile(batchLogFileName, serverLogFilePath);
				appendBatchLogFile(batchErrFileName, serverErrFilePath);

                if(!flag){
                     flag =fileExtension(importFilePath,"err",importFileName);
                     if(flag){
//                         System.out.println("Read Log File");
                         appendBatchLogFile(batchLogFileName, serverLogFilePath);
						 appendBatchLogFile(batchErrFileName, serverErrFilePath);
						 return "err";
                     }
                }else {
					return "wrn";
				}
            }
        }catch (Exception e){
			Log4j.error(e.getMessage());
			fail(e.getMessage());
        }

		return "scc";
    }

	public static String encodeToBase64(String inputString)
	{
		String encodedString= Base64.getEncoder().encodeToString(inputString.getBytes());;
		return encodedString;
	}
//	public static String ReadFile(String filepath)
//	{
//		String content="";
//		try {
//			FileInputStream fis = new FileInputStream(filepath);
//			byte[] buffer = new byte[10];
//			StringBuilder sb = new StringBuilder();
//			while (fis.read(buffer) != -1) {
//				sb.append(new String(buffer));
//				buffer = new byte[10];
//			}
//			fis.close();
//
//			content = sb.toString();
//		}
//		catch(Exception e )
//		{
//			System.out.println(e.getMessage());
//		}
//		return content;
//	}

//	public static String ReadFile(String path) throws IOException {
//		StringBuilder sb = new StringBuilder();
//		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//			String sCurrentLine = "";
//			while ((sCurrentLine = br.readLine()) != null) {
//				sb.append(sCurrentLine);
//			}
//		}
//		return sb.toString();
//	}

	public static String ReadFile(String path) throws IOException, ParserConfigurationException, SAXException {
		File file = new File(path);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setCoalescing(true);

		//API to obtain DOM Document instance
		DocumentBuilder builder = null;

		//Create DocumentBuilder with default configuration
		builder = factory.newDocumentBuilder();

		//Parse the content to Document object
		Document doc = builder.parse(file);

		String txt = convertXmlDomToString(doc);

		return txt;
	}
	public static String convertXmlDomToString(Document xmlDocument) {

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();

			// Uncomment if you do not require XML declaration
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

			//A character stream that collects its output in a string buffer,
			//which can then be used to construct a string.
			StringWriter writer = new StringWriter();

			//transform document to string
			transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));

			return writer.getBuffer().toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}