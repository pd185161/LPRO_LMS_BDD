/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import io.cucumber.java.DataTableType;
import org.xml.sax.SAXException;

import com.LMS.LMSAPIAutomation.BusinessScenarios.BusinessScenarios;
import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import com.LMS.LMSAPIAutomation.Payload.*;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author sa185402
 *
 */

public class MemberService extends BaseClass{
		
		ReadProperties prop = new ReadProperties();
		
		Resources resources = new Resources();
		
		String zipcode,countryID,promotionHeaderId,subTypeId;
		MemberServicePayload memberServicePayload= new MemberServicePayload();
	
		@Given("Retrieve all the Required parameter values from DataBase")
		public void retrieve_all_the_required_parameter_values_from_data_base() throws ClassNotFoundException, SQLException {
			resources.DBExtract();
		}
	
		@Given("Add MemberService {string} request Payload")
		public void add_member_service_request_payload(String scenario_name, io.cucumber.datatable.DataTable userTable) throws FileNotFoundException, JAXBException, TransformerException {
				
			HashMap<String,String> HttpHeaders = new HashMap<String,String>();
			if(prop.accessToken().equalsIgnoreCase("1")){
				HttpHeaders.put("Authorization", "AccessToken "+Login.sessionId);
				HttpHeaders.put("Content-Type","text/xml");
			}

			add_payload(memberServicePayload.payload(scenario_name,Resources.parameters,userTable),HttpHeaders,"text/xml;charset=UTF-8",Login.sessionId);
		}
		
		@Given("Verify the clubcardId is available in DB")
		public void verify_the_club_card_id_is_available_in_DB() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {   
			resources.validate_clubCardId_DB();	
		}
		
		@Given("Validate the save demographics Response Status")
		public void validate_the_save_demographics_response_status() throws SAXException, IOException, ParserConfigurationException {   
			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "SaveDemographicResult", 0, "Status");
			
			assertEquals(Status, "Ok", "Save Demographics - Status is not as per expected : actual Value : " + Status);
			
		}
		
		@Given("Validate the Card and Status of Response")
		public void validate_the_card_and_status_of_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException {

			String response_Pretty = response.asPrettyString();

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
			if (att.get(0).get("Errorcode").toString().equalsIgnoreCase("NA")){
				String response1 = response.asPrettyString().replace("&lt;", "<");
				response_Pretty = response1.replace("&gt;", ">");
			}

			String Status = XMLExtraction.Extraction(response_Pretty, "CardValidateResult", 0, "Status");

			if(Status.equalsIgnoreCase("Ok")){

				String MemberExternalId = XMLExtraction.Extraction(response_Pretty, "Member", 0, "MemberExternalId");
				String HouseHoldExternalId = XMLExtraction.Extraction(response_Pretty, "Member", 0, "HouseHoldExternalId");

				assertEquals(Status, "Ok", "Card Validate - Status is not as per expected : actual Value : " + Status);
				assertEquals(MemberExternalId, Resources.parameters.get("clubCardId")+"", "MemberExternalId in Card Validate is not as per expected : actual Value : " + MemberExternalId);
				assertEquals(HouseHoldExternalId, Resources.parameters.get("clubCardId")+"", "HouseHoldExternalId in Card Validate is not as per expected : actual Value : " + HouseHoldExternalId);

			}else if(Status.equalsIgnoreCase("Error")){

				String errorCode = XMLExtraction.Extraction(response_Pretty, "CardValidateResult", 0, "ErrorCode");
				assertEquals(errorCode, att.get(0).get("Errorcode"), "Card Validate - Errorcode is not as per expected : actual Value : " + errorCode);

			}
			

		}
		
		@Then("Validate the GetDemographic details")
		public void validate_the_get_demographic_details(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException {
			String response_Pretty1 = response.asPrettyString();

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			if (att.get(0).get("Errorcode").toString().equalsIgnoreCase("NA")){
				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");
				response_Pretty1 = response_Pretty.replace(response_Pretty.substring(response_Pretty.indexOf("<?xml"), response_Pretty.indexOf("<HouseHold")), "");
			}

			String Status = XMLExtraction.Extraction(response_Pretty1, "GetDemographicResult", 0, "Status");

			if(Status.equalsIgnoreCase("Ok")){

				String HouseHoldExternalId = XMLExtraction.Extraction(response_Pretty1, "HouseHold", 0, "HouseHoldExternalId");
				assertEquals(HouseHoldExternalId, Resources.parameters.get("clubCardId")+"", "HouseHoldExternalId in Get Demographics is not as per expected : actual Value : " + HouseHoldExternalId);

			}else if(Status.equalsIgnoreCase("Error")){

				String errorCode = XMLExtraction.Extraction(response_Pretty1, "GetDemographicResult", 0, "ErrorCode");
				assertEquals(errorCode, att.get(0).get("Errorcode"), "Get Demographics - Errorcode is not as per expected : actual Value : " + errorCode);

			}

		}
		
		
		@Given("Check the entry in Zipcode table of MP Database and Insert the record if no data")
		public void check_the_entry_in_zipcode_table_of_mp_database_and_insert_the_record_if_no_data() {
			int count = GenericMethods.SQLRecordCount(GenericMethods.connecttoDBServer("OPERATIONAL",BusinessScenarios.retrive_DBName("MP"),BusinessScenarios.retrive_DBName("USERNAME"),BusinessScenarios.retrive_DBName("PASSWORD")), "ZipCode");
			if (count == 0) {
				String insert_Query = "insert INTO ZipCode (ZipCode,CountryId,StateId,City,Address) VALUES ('06802','100','6','Arizona','Address Arizona');";
				GenericMethods.insertDBRecord(GenericMethods.connecttoDBServer("OPERATIONAL",BusinessScenarios.retrive_DBName("MP"),BusinessScenarios.retrive_DBName("USERNAME"),BusinessScenarios.retrive_DBName("PASSWORD")), insert_Query);
			}
		}
		
		@Given("Retrive the details from Zipcode table of MP Database")
		public void retrive_the_details_from_zipcode_table_of_mp_database() throws ClassNotFoundException, SQLException {
		   
			zipcode = BusinessScenarios.retriveDB("OPERATIONAL","MP", "SELECT TOP (1) ZipCode FROM ZipCode", "ZipCode");
			countryID = BusinessScenarios.retriveDB("OPERATIONAL","MP", "SELECT TOP (1) CountryId FROM ZipCode", "CountryId");
			Resources.parameters.put("zipcode", zipcode);
			Resources.parameters.put("countryID", countryID);
		}
		
		@Then("Validate GetAddressByZipCode Status and response")
		public void validate_get_address_by_zip_code_status_and_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "GetAddressByZipCodeResult", 0, "Status");

			if(Status.equalsIgnoreCase("Ok")){

				String ContryId = XMLExtraction.Extraction(response_Pretty, "ContryId", 0, "ContryId");
				String StateId = XMLExtraction.Extraction(response_Pretty, "StateId", 0, "StateId");
				String City = XMLExtraction.Extraction(response_Pretty, "City", 0, "City");
				String AddressText = XMLExtraction.Extraction(response_Pretty, "AddressText", 0, "AddressText");

				/**
				 * Retrieve Values from DB
				 */

				String dB_ContryId = countryID;
				String dB_StateId = BusinessScenarios.retriveDB("OPERATIONAL","MP", "SELECT StateId FROM ZipCode where CountryId ="+countryID, "StateId");
				String dB_City = BusinessScenarios.retriveDB("OPERATIONAL","MP", "SELECT City FROM ZipCode where CountryId ="+countryID, "City");
				String dB_AddressText = BusinessScenarios.retriveDB("OPERATIONAL","MP", "SELECT  Address FROM ZipCode where CountryId ="+countryID, "Address");


				assertEquals(ContryId, dB_ContryId, "GetAddressByZipCode - ContryId is not as per expected : actual Value : " + ContryId);
				assertEquals(StateId, dB_StateId, "GetAddressByZipCode - StateId is not as per expected : actual Value : " + StateId);
				assertEquals(City, dB_City, "GetAddressByZipCode - City is not as per expected : actual Value : " + City);
				assertEquals(AddressText, dB_AddressText, "GetAddressByZipCode - AddressText is not as per expected : actual Value : " + AddressText);

			}else if (Status.equalsIgnoreCase("Error")){

				String message = XMLExtraction.Extraction(response_Pretty, "GetAddressByZipCodeResult", 0, "Message");
				assertEquals(message, att.get(0).get("Message"), "Save Demographics - Message is not as per expected : actual Value : " + message);

			}

			
		}
		
		@Then("Validate GetSegments status")
		public void validate_get_segments_status() throws SAXException, IOException, ParserConfigurationException {
		    
			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "GetSegmentsResult", 0, "Status");
			
			assertEquals(Status, "Ok", "GetSegments - Status is not as per expected : actual Value : " + Status);
		}
		
		@Then("Validate the RegisterPromotionResult in Response")
		public void validate_the_register_promotion_result_in_response() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		   
			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "RegisterPromotionResult", 0, "Status");
			
			assertEquals(Status, "Ok", "RegisterPromotionResult - Status is not as per expected : actual Value : " + Status);
			
			String count =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "Select COUNT(*) PromotionHeaderId from CRM_buyingunitPromotion where PromotionHeaderId = "+Resources.parameters.get("promotionHeaderId"), "PromotionHeaderId");
			System.out.println(count);
			
			if (Integer.parseInt(count) >= 1) {
				assertEquals(true, true);
			}else {
				assertEquals(true, false, "RegisterPromotionResult - RegisterPromotion is not added to the member = " + Resources.parameters.get("clubCardId"));
			}
		}
		
		
		@Then("Validate the GetMemberPromotionsResult and Promotion Results in Response")
		public void validate_the_get_member_promotions_result_and_promotion_results_in_response() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
			
			String response1 = response.asPrettyString().replace("&lt;", "<");
			String response_Pretty = response1.replace("&gt;", ">");
//			System.out.println(response_Pretty);
			
			String Status = XMLExtraction.Extraction(response_Pretty, "GetMemberPromotionsResult", 0, "Status");
			String Promotion_id = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "id");
			String PromotionStartDate = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "PromotionStartDate");
			String PromotionEndDate = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "PromotionEndDate");
			
			assertEquals(Status, "Ok", "GetMemberPromotionsResult - Status is not as per expected : actual Value : " + Status);
			assertEquals(Promotion_id, Resources.parameters.get("promotionHeaderId"), "GetMemberPromotionsResult - Promotion_id is not as per expected : actual Value : " + Promotion_id);
			assertEquals(PromotionStartDate, GenericMethods.currentDate()+"T00:00:00", "GetMemberPromotionsResult - PromotionStartDate is not as per expected : actual Value : " + PromotionStartDate);
			assertEquals(PromotionEndDate, "2056-12-30T23:59:00", "GetMemberPromotionsResult - PromotionEndDate is not as per expected : actual Value : " + PromotionEndDate);
			
		}
		
		@Then("Validate the UnregisterPromotionResult in Response")
		public void validate_the_unregister_promotion_result_in_response() throws SAXException, IOException, ParserConfigurationException {
		   
			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "UnregisterPromotionResult", 0, "Status");
			
			assertEquals(Status, "Ok", "UnregisterPromotionResult - Status is not as per expected : actual Value : " + Status);
		}
		
		@Given("Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request")
		public void extract_promotion_header_id_from_db_and_incorporate_in_the_register_promotion_request() throws ClassNotFoundException, SQLException, InterruptedException {
		    
			Thread.sleep(60000);
			promotionHeaderId = BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", "Select PromotionHeaderId from PromotionHeader where ExternalReferenceID = "+"'"+Resources.parameters.get("promotionExternalId")+"'", "PromotionHeaderId");
			Resources.parameters.put("promotionHeaderId", promotionHeaderId);
			
		}
		
		@Then("Validate the RegisterPromotionWithDatesRangeResult in Response")
		public void validate_the_register_promotion_with_dates_range_result_in_response() throws SAXException, IOException, ParserConfigurationException {
		   
			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "RegisterPromotionWithDatesRangeResult", 0, "Status");
			
			assertEquals(Status, "Ok", "RegisterPromotionWithDatesRangeResult - Status is not as per expected : actual Value : " + Status);
		}

		@Given("Extract SubtypeID from CRM_NotesSubTypes Table from DB")
		public void extract_subtype_id_from_crm_notes_sub_types_table_from_db() throws ClassNotFoundException, SQLException {
		    
			subTypeId = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "Select top(1) SubTypeId from CRM_NotesSubTypes where MatrixMemberId = "+Resources.parameters.get("matrixMemberId"), "SubTypeId");
			Resources.parameters.put("subTypeId", subTypeId);
			
		}
		
		@Then("Validate the SaveNotesResult in Response")
		public void validate_the_save_notes_result_in_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException {
		   	List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "SaveNotesResult", 0, "Status");

			if(Status.equalsIgnoreCase("Error")){

				if (!(att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NOERRORCODE")){

					String errorCode = XMLExtraction.Extraction(response_Pretty, "SaveNotesResult", 0, "ErrorCode");
					assertEquals(errorCode, att.get(0).get("ErrorCode")+"", "SaveNotesResult - ErrorCode is not as per expected : actual Value : " + errorCode);

					if(!(att.get(0).get("ErrorDescription")+"").equalsIgnoreCase("NA")){
						String errorMessage = XMLExtraction.Extraction(response_Pretty, "SaveNotesResult", 0, "Message");
						assertEquals(errorMessage, att.get(0).get("ErrorDescription")+"", "SaveNotesResult - ErrorMessage is not as per expected : actual Value : " + errorMessage);
					}

				}else {
					String errorMessage = XMLExtraction.Extraction(response_Pretty, "SaveNotesResult", 0, "Message");
					assertEquals(errorMessage, att.get(0).get("ErrorDescription")+"", "SaveNotesResult - errorMessage is not as per expected : actual Value : " + errorMessage);
				}

			}else {
				assertEquals(Status, "Ok", "SaveNotesResult - Status is not as per expected : actual Value : " + Status);
			}


		}
		
		@Then("Validate the SegmentsMovementsResult {string} in Response")
		public void validate_the_segments_movements_result_in_response(String type, io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "SegmentsMovementsResult", 0, "Status");

			if(Status.equalsIgnoreCase("Error")){

				String errorCode = XMLExtraction.Extraction(response_Pretty, "SegmentsMovementsResult", 0, "ErrorCode");
				assertEquals(errorCode, att.get(0).get("ErrorCode")+"", "SegmentsMovementsResult - ErrorCode is not as per expected : actual Value : " + errorCode);

			}else {

				assertEquals(Status, "Ok", "SegmentsMovementsResult - Status is not as per expected : actual Value : " + Status);

				switch (type.toUpperCase()) {

				case "ATTACH":

					String count =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "Select COUNT(*) Action from CRM_MemberSegment_Log where ClubCardId = "+"'"+Resources.parameters.get("clubCardId")+"'"+" and Action = '1'", "Action");
					System.out.println(count);

					if (Integer.parseInt(count) >= 1) {
						assertEquals(true, true);
					}else {
						assertEquals(true, false, "SegmentsMovementsResult - Segment is not attched Segment id = " + Resources.parameters.get("segmentId"));
					}

					break;

				case "DETACH":

					String count2 =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "Select COUNT(*) Action from CRM_MemberSegment_Log where ClubCardId = "+"'"+Resources.parameters.get("clubCardId")+"'"+" and Action = '2'", "Action");
					System.out.println(count2);

					if (Integer.parseInt(count2) >= 1) {
						assertEquals(true, true);
					}else {
						assertEquals(true, false, "SegmentsMovementsResult - Segment is not detached Segment id = " + Resources.parameters.get("segmentId"));
					}

					break;
				}

			}
			
		}
		
		@Then("Validate the AdjustMemberAccountResult in Response")
		public void validate_the_adjust_member_account_result_in_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		   List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			String response_Pretty = response.asPrettyString();
			String Status = XMLExtraction.Extraction(response_Pretty, "AdjustMemberAccountResult", 0, "Status");

			if(Status.equalsIgnoreCase("Error")){

				String errorCode = XMLExtraction.Extraction(response_Pretty, "AdjustMemberAccountResult", 0, "ErrorCode");
				assertEquals(errorCode, att.get(0).get("ErrorCode")+"", "AdjustMemberAccountResult - ErrorCode is not as per expected : actual Value : " + errorCode);

			}else {

				String TotalAccumulated = BusinessScenarios.retriveDB("OPERATIONAL", "LOYALTY",
						"Select TotalAccumulated from [dbo].[CRM_MemberAccountsActivity] where MemberInternalKey "
								+ "= ( Select MemberInternalKey from [dbo].[CRM_Clubcard] where ClubCardId = " + "'" + Resources.parameters.get("clubCardId") + "'" + ")",
						"TotalAccumulated");

				assertEquals(Status, "Ok", "AdjustMemberAccountResult - Status is not as per expected : actual Value : " + Status);
				assertEquals(TotalAccumulated, "5.0000", "AdjustMemberAccountResult - TotalAccumulated is not as per expected : actual Value : " + TotalAccumulated);
			}
		}
		
		@Then("Validate the {string} status in Response")
		public void validate_the_status_in_response(String scenario, io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException, InterruptedException {

			Thread.sleep(6000);
			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
			
			String response_Pretty = response.asPrettyString();
			
			String Status = XMLExtraction.Extraction(response_Pretty, scenario, 0, "Status");

			if(Status.equalsIgnoreCase("Error")){

				String errorCode = XMLExtraction.Extraction(response_Pretty, scenario, 0, "ErrorCode");
				assertEquals(errorCode, att.get(0).get("ErrorCode")+"", scenario+" - ErrorCode is not as per expected : actual Value : " + errorCode);

			}else {
				assertEquals(Status, "Ok", scenario+" - Status is not as per expected : actual Value : " + Status);
			}

		}
		
		@Then("Validate the GetFilteredMemberAccountDetailedTransactions Response")
		public void validate_the_get_filtered_member_account_detailed_transactions_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){

				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");

				String clubCardId = XMLExtraction.Extraction(response_Pretty, "Transaction", 0, "ClubCardId");
				String memberAccount_Id = XMLExtraction.Extraction(response_Pretty, "MemberAccount", 0, "Id");
				String earnValue = XMLExtraction.Extraction(response_Pretty, "MemberAccount", 0, "EarnValue");
				String redeemValue = XMLExtraction.Extraction(response_Pretty, "MemberAccount", 0, "RedeemValue");

				assertEquals(clubCardId, Resources.parameters.get("clubCardId"), "AdjustMemberAccountResult - ClubCardId is not as per expected : actual Value : " + clubCardId);
				assertEquals(memberAccount_Id, Resources.parameters.get("accountId"), "AdjustMemberAccountResult - memberAccount_Id is not as per expected : actual Value : " + memberAccount_Id);
				assertEquals(earnValue, att.get(0).get("EarnValue")+"", "AdjustMemberAccountResult - earnValue is not as per expected : actual Value : " + earnValue);
				assertEquals(redeemValue, att.get(0).get("RedeemValue")+"", "AdjustMemberAccountResult - redeemValue is not as per expected : actual Value : " + redeemValue);

				String count =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", "Select COUNT(*) ClubCardId from CRM_POSTran where ClubCardId = "+"'"+Resources.parameters.get("clubCardId")+"'"+" and matrixmemberid ="+ Resources.parameters.get("matrixMemberId"), "ClubCardId");
				System.out.println(count);

				if (Integer.parseInt(count) >= 1) {
					assertEquals(true, true);
				}else {
					assertEquals(true, false, "GetFilteredMemberAccountDetailedTransactions - Transaction Details are not displaying for ClubCardId : " + Resources.parameters.get("clubCardId"));
				}

			}

			
		}
		
		@Then("Validate the MSG Response")
		public void validate_the_msg_response() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		   
			String response_Pretty = response.asPrettyString();
			
			String StoreDataResult = XMLExtraction.Extraction(response_Pretty, "StoreDataResult", 0, "StoreDataResult");
			assertEquals(StoreDataResult, "true", "StoreDataResult - MSG3 Status is not as per expected : actual Value : " + StoreDataResult);
		}
		
		@Then("Validate the GetHouseHoldAccountsActivity Response")
		public void validate_the_get_house_hold_accounts_activity_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){
				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");

				String AccountId = XMLExtraction.Extraction(response_Pretty, "Account", 0, "Id");
				assertEquals(AccountId, Resources.parameters.get("accountId"), "GetHouseHoldAccountsActivity - AccountId is not as per expected : actual Value : " + AccountId);
			}
		}
		
		@Then("Validate the GetHouseHoldContinuityPromotionsActivity Response")
		public void validate_the_get_house_hold_continuity_promotions_activity_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		   	List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			   if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){
				   	String response1 = response.asPrettyString().replace("&lt;", "<");
					String response_Pretty = response1.replace("&gt;", ">");

					String promotionHeaderId = XMLExtraction.Extraction(response_Pretty, "ContinuityProgram", 0, "PromotionHeaderId");
					assertEquals(promotionHeaderId, Resources.parameters.get("promotionHeaderId"), "GetHouseHoldContinuityPromotionsActivity - promotionHeaderId is not as per expected : actual Value : " + promotionHeaderId);
			   }

		}
		
		@Then("Validate the GetHouseHoldMembers Response")
		public void validate_the_get_house_hold_members_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){

				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");
				String response_Pretty1 = response_Pretty.replace(response_Pretty.substring(response_Pretty.indexOf("<?xml"), response_Pretty.indexOf("<HouseHold")), "");

				String memberInternalKey = XMLExtraction.Extraction(response_Pretty1, "Member", 0, "MemberInternalKey");
				String buyingUnitInternalKey = XMLExtraction.Extraction(response_Pretty1, "HouseHold", 0, "BuyingUnitInternalKey");

				String memberInternalKey_DB =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY",
						"Select MemberInternalKey from CRM_Clubcard where ClubCardId =  "+ "'"+Resources.parameters.get("clubCardId")+"'", "MemberInternalKey");

				String buyingUnitInternalKey_DB =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY",
						"Select BuyingUnitInternalKey From CRM_Member where MemberInternalKey ="+"'"+memberInternalKey_DB+"'", "BuyingUnitInternalKey");

				assertEquals(memberInternalKey, memberInternalKey_DB, "GetHouseHoldMembers - MemberInternalKey is not as per expected : actual Value : " + memberInternalKey);
				assertEquals(buyingUnitInternalKey, buyingUnitInternalKey_DB, "GetHouseHoldMembers - BuyingUnitInternalKey is not as per expected : actual Value : " + buyingUnitInternalKey);

			}

		}
		
		@Then("Validate the GetHouseHoldMembersDemographic Response")
		public void validate_the_get_house_hold_members_demographic_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

			if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){

				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");
	//			System.out.println(response_Pretty);
	//			String response_Pretty1 = response_Pretty.replace(response_Pretty.substring(response_Pretty.indexOf("<?xml"), response_Pretty.indexOf("<HouseHold")), "");

				String memberInternalKey = XMLExtraction.Extraction(response_Pretty, "Member", 0, "MemberInternalKey");
				String buyingUnitInternalKey = XMLExtraction.Extraction(response_Pretty, "HouseHold", 0, "BuyingUnitInternalKey");

				String memberInternalKey_DB =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY",
						"Select MemberInternalKey from CRM_Clubcard where ClubCardId =  "+"'"+Resources.parameters.get("clubCardId")+"'", "MemberInternalKey");

				String buyingUnitInternalKey_DB =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY",
						"Select BuyingUnitInternalKey From CRM_Member where MemberInternalKey ="+"'"+memberInternalKey_DB+"'", "BuyingUnitInternalKey");

				assertEquals(memberInternalKey, memberInternalKey_DB, "GetHouseHoldMembersDemographic - MemberInternalKey is not as per expected : actual Value : " + memberInternalKey);
				assertEquals(buyingUnitInternalKey, buyingUnitInternalKey_DB, "GetHouseHoldMembersDemographic - BuyingUnitInternalKey is not as per expected : actual Value : " + buyingUnitInternalKey);

			}

		}
		
		@Then("Validate the GetMemberAccountsActivity Response")
		public void validate_the_get_member_accounts_activity_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
			if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){
				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");

				String AccountId = XMLExtraction.Extraction(response_Pretty, "Account", 0, "Id");
				assertEquals(AccountId, Resources.parameters.get("accountId"), "GetHouseHoldAccountsActivity - AccountId is not as per expected : actual Value : " + AccountId);

			}
		}
		
		@Then("Validate the GetHouseHoldTransactions Response")
		public void validate_the_get_house_hold_transactions_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
			if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){
				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");
				String clubCardId = XMLExtraction.Extraction(response_Pretty, "Transaction", 0, "ClubCardId");
				assertEquals(clubCardId, Resources.parameters.get("clubCardId"), "GetHouseHoldTransactions - ClubCardId is not as per expected : actual Value : " + clubCardId);
			}

		}
		
		@Then("Validate the GetMemberAccountDetailedTransactions Response")
		public void validate_the_get_member_account_detailed_transactions_response(io.cucumber.datatable.DataTable userTable) throws SAXException, IOException, ParserConfigurationException {

			List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
			if((att.get(0).get("ErrorCode")+"").equalsIgnoreCase("NA")){
				String response1 = response.asPrettyString().replace("&lt;", "<");
				String response_Pretty = response1.replace("&gt;", ">");

				String clubCardId = XMLExtraction.Extraction(response_Pretty, "AccountTransaction", 0, "ClubCardId");
				String transactionTotalAmount = XMLExtraction.Extraction(response_Pretty, "AccountTransaction", 0, "TransactionTotalAmount");
				String earnValue = XMLExtraction.Extraction(response_Pretty, "AccountTransaction", 0, "EarnValue");
				String redeemValue = XMLExtraction.Extraction(response_Pretty, "AccountTransaction", 0, "RedeemValue");

				assertEquals(clubCardId, Resources.parameters.get("clubCardId"), "GetMemberAccountDetailedTransactions - ClubCardId is not as per expected : actual Value : " + clubCardId);
				assertEquals(transactionTotalAmount, att.get(0).get("TicketTotal")+"", "GetMemberAccountDetailedTransactions - transactionTotalAmount is not as per expected : actual Value : " + transactionTotalAmount);
				assertEquals(earnValue, att.get(0).get("EarnValue")+"", "GetMemberAccountDetailedTransactions - earnValue is not as per expected : actual Value : " + earnValue);
				assertEquals(redeemValue, att.get(0).get("RedeemValue")+"", "GetMemberAccountDetailedTransactions - redeemValue is not as per expected : actual Value : " + redeemValue);

			}

		}
		
		@Then("Validate the UpdateExternalsResult Response")
		public void validate_the_update_externals_result_response() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
			
			
			String externalMemberKey_DB =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", 
					"Select ExternalMemberKey from CRM_Member where MemberInternalKey in (Select MemberInternalKey from CRM_Clubcard where ClubCardId = "+"'"+Resources.parameters.get("clubCardId")+"'"+")", "ExternalMemberKey");
			
			String externalBuyingUnit_DB =  BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY", 
					"select ExternalBuyingUnit from CRM_BuyingUnit where BuyingUnitInternalKey in "
					+ "(Select BuyingUnitInternalKey from CRM_Member where MemberInternalKey in "
					+ "(Select MemberInternalKey from CRM_Clubcard where ClubCardId = "+"'"+Resources.parameters.get("clubCardId")+"'"+"))", "BuyingUnitInternalKey");
			
			assertEquals(externalMemberKey_DB, (Integer.parseInt(Resources.parameters.get("clubCardId"))+1)+"", "UpdateExternalsResult - ExternalMemberKey_DB is not as per expected : actual Value : " + externalMemberKey_DB);
			assertEquals(externalBuyingUnit_DB, (Integer.parseInt(Resources.parameters.get("clubCardId"))+1)+"", "UpdateExternalsResult - ExternalBuyingUnit_DB is not as per expected : actual Value : " + externalBuyingUnit_DB);
			
		}


}
