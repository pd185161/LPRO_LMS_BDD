/**
 * 
 */
package com.LMS.LMSAPIAutomation.BusinessScenarios;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;
//import jdk.internal.shellsupport.doc.resources.javadocformatter;

/**
 * @author sa185402
 *
 */
public class Resources {
	
	String matrixMemberId,accountId, retailerId, promotionHeaderId, segmentInternalKey, 
	segmentId, segmentDescription, promotionStartDate, promotionEndDate,zipcode,countryID,
	subTypeId, clubId;

	ReadProperties prop = new ReadProperties();

	
	public static HashMap<String,String> parameters = new HashMap<String,String>();
	
	public void DBExtract() throws ClassNotFoundException, SQLException {
		
		matrixMemberId = BusinessScenarios.retriveDB("OPERATIONAL", "MP", "Select MatrixMemberId from AppUser where UserName = "+"'"+prop.App_UserName()+"'", "MatrixMemberId");
		accountId = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "SELECT TOP (1) [AccountId] FROM CRM_AccumulatedAccounts where MatrixMemberId = "+matrixMemberId, "AccountId");
		retailerId = BusinessScenarios.retriveDB("OPERATIONAL","HOST", "Select RetailerId from RetailerCode where MatrixMemberId = "+matrixMemberId, "RetailerId");
		segmentInternalKey = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "SELECT TOP (1) SegmentInternalKey FROM CRM_Segment where MatrixMemberId = "+matrixMemberId+ " order by SegmentInternalKey desc", "SegmentInternalKey");
		segmentId = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "select SegmentId from CRM_Segment where SegmentInternalKey = "+segmentInternalKey, "SegmentId");
		segmentDescription = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "select SegmentDescription from CRM_Segment where SegmentInternalKey = "+segmentInternalKey, "SegmentDescription");
		clubId = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "select clubId from CRM_Club where ClubInternalKey IN (select ClubInternalKey from CRM_RetailerClubAssign where MatrixMemberId ="+ matrixMemberId+ ")", "clubId");
		
		parameters.put("accountId", accountId);
		parameters.put("retailerId", retailerId);
		parameters.put("segmentInternalKey", segmentInternalKey);
		parameters.put("segmentId", segmentId);
		parameters.put("segmentDescription", segmentDescription);
		parameters.put("matrixMemberId", matrixMemberId);
		parameters.put("clubId", clubId);
		
	}
	
	public void validate_clubCardId_DB() throws ClassNotFoundException, SQLException {
		
		String count;
		int clubCardId;
		int newclubCardId;
		
		Random rnd = new Random();
		
		do {
			
			clubCardId = rnd.nextInt(99999999);
			
			count =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "Select COUNT(*) ClubCardId from CRM_Clubcard where ClubCardId = "+"'"+clubCardId+"'", "ClubCardId");
			System.out.println(count);
			
		} while (Integer.parseInt(count) != 0);
		
		do {
			
			newclubCardId = rnd.nextInt(99999999);
			
			count =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "Select COUNT(*) ClubCardId from CRM_Clubcard where ClubCardId = "+"'"+newclubCardId+"'", "ClubCardId");
			System.out.println(count);
			
		} while (Integer.parseInt(count) != 0);
		
		parameters.put("clubCardId", clubCardId+"");
		parameters.put("newclubCardId", newclubCardId+"");
		
	}
	
	
	public static void  validate_PromotionHeaderId_DB() throws ClassNotFoundException, SQLException {
		
		String count;
		int promotionExternalId;
		Random rnd = new Random();
		
		do {
			
			promotionExternalId = rnd.nextInt(99999);
			
			count =  BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", "Select COUNT(*) PromotionHeaderId from PromotionHeader where ExternalReferenceID = "+"'"+promotionExternalId+"'", "PromotionHeaderId");
			System.out.println(count);
			
		} while (Integer.parseInt(count) != 0);
		
		parameters.put("promotionExternalId", promotionExternalId+"");
	}

	public static String RetrieveSystemParameterValue(String DBName, String ParameterKey) throws SQLException, ClassNotFoundException {
		String sPValue="";
		if(DBName.equalsIgnoreCase("MP")) {
			sPValue = BusinessScenarios.retriveDB("OPERATIONAL", "MP", "select ParameterValue from SystemParameters where ParameterKey = '"+ParameterKey+"'", "ParameterValue");
		}
		else if(DBName.equalsIgnoreCase("Retailer") || DBName.equalsIgnoreCase("Host"))
		{
			sPValue = BusinessScenarios.retriveDB("OPERATIONAL", "HOST", "select ParameterValue from SystemParameters where MatrixMemberId = '"+Resources.parameters.get("matrixMemberId")+"' ParameterKey = '"+ParameterKey+"' ", "ParameterValue");
		}
		return sPValue;
	}
	public static void UpdateSystemParameter(String SPName, String SPValue, String DBName) throws SQLException {
		if (DBName.equalsIgnoreCase("MP")) {
			BusinessScenarios.UpdateDB("OPERATIONAL", "MP", "UPDATE SYSTEMPARAMETERS SET PARAMETERVALUE ='"+SPValue+"' WHERE PARAMETERKEY = '"+SPName+"'");
		} else if (DBName.equalsIgnoreCase("Retailer") || DBName.equalsIgnoreCase("Host")) {
			BusinessScenarios.UpdateDB("OPERATIONAL", "MP", "UPDATE SYSTEMPARAMETERS SET PARAMETERVALUE = '"+SPValue+"' WHERE PARAMETERKEY ='"+SPName+"' AND MATRIXMEMBERID = '"+Resources.parameters.get("matrixMemberId")+"'");
		}

	}
}
