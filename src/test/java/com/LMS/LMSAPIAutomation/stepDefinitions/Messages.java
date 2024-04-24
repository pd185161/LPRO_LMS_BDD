/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.LMS.LMSAPIAutomation.BusinessScenarios.BusinessScenarios;
import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import com.LMS.LMSAPIAutomation.Payload.MessagesPayload;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import static org.testng.Assert.assertEquals;

/**
 * @author sa185402
 *
 */
public class Messages extends BaseClass {
	
	ReadProperties prop = new ReadProperties();
	MessagesPayload messagesPayload = new MessagesPayload();
	
	@Given("User Enter valid data to the MSG3 request")
	public void user_enter_valid_data_to_the_MSG3_request(DataTable userTable) {
		
		List<Map<Object, Object>> user = userTable.asMaps(String.class, String.class);
		Resources.parameters.put("earnValue", user.get(0).get("earnValue")+"");
		Resources.parameters.put("rdmValue", user.get(0).get("rdmValue")+"");
		Resources.parameters.put("openBalance", user.get(0).get("openBalance")+"");
		Resources.parameters.put("ticketTotal", user.get(0).get("ticketTotal")+"");

	}
	
	@Given("Add Messages {string} request Payload")
	public void add_messages_request_payload(String payload, io.cucumber.datatable.DataTable userTable) throws FileNotFoundException {
	    
		HashMap<String,String> HttpHeaders = new HashMap<String,String>();	
		HttpHeaders.put("Content-Type", "text/xml");
		add_payload(messagesPayload.payload(payload, Resources.parameters,userTable),HttpHeaders,"text/xml");
		
	}

	@Given("Validate the MSG2")
	public void validate_the_msg2() throws SAXException, IOException, ParserConfigurationException, SQLException, ClassNotFoundException {

		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		String response_Pretty1 = response_Pretty.replace(response_Pretty.substring(response_Pretty.indexOf("<?xml"), response_Pretty.indexOf("<Customer")), "");

		//System.out.println(response_Pretty1);
		String response_MsgType = XMLExtraction.Extraction(response_Pretty1, "Customer", 0, "MsgType");
		String MemberExternalId= XMLExtraction.Extraction(response_Pretty1, "Member", 0, "MemberExternalId");
		String lockingKey = XMLExtraction.Extraction(response_Pretty1, "LoyaltyInfo", 0, "LockingKey");
		String accountId =XMLExtraction.Extraction(response_Pretty1, "Acc", 0, "ID");
		String accValue =XMLExtraction.Extraction(response_Pretty1, "Acc", 0, "Value");

		String db_BuyingUnitInternalKey = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY",
				"select BuyingUnitInternalKey from CRM_Member where MemberInternalKey =(select MemberInternalKey from CRM_ClubCard where Clubcardid='"+Resources.parameters.get("clubCardId")+"' and Matrixmemberid='"+Resources.parameters.get("matrixMemberId")+"')", "BuyingUnitInternalKey");
		String db_Lockingkey = BusinessScenarios.retriveDB("ONLINE","LOYALTYONLINE",
				"select LockTicketKey from [dbo].[CRM_OL_BuyingUnitData] where buyingunitinternalkey = '"+db_BuyingUnitInternalKey+"'", "LockTicketKey");
		String db_Msg2XMLdata = BusinessScenarios.retriveDB("ONLINE","LOYALTYONLINE",
				"select xmlData from [dbo].[CRM_OL_BuyingUnitData] where buyingunitinternalkey = '"+db_BuyingUnitInternalKey+"'", "xmlData");


		String db_MemberExternalId = XMLExtraction.Extraction(db_Msg2XMLdata, "Member", 0, "MemberExternalId");
		String db_accountId= XMLExtraction.Extraction(db_Msg2XMLdata, "Acc", 0, "ID");
		String db_accValue= XMLExtraction.Extraction(db_Msg2XMLdata, "Acc", 0, "Value");

		assertEquals(response_MsgType, "2", "MsgType in GetMemberData-MSG2 Response is not as per expected : actual Value : " + response_MsgType);
		assertEquals(MemberExternalId, Resources.parameters.get("clubCardId"), "MemberExternalId in GetMemberData-MSG2 Response is not as per expected : actual Value : " + MemberExternalId);
		assertEquals(lockingKey, db_Lockingkey, "LockingKey in GetMemberData-MSG2 Response is not as per expected : actual Value : " + lockingKey);
		assertEquals(MemberExternalId, db_MemberExternalId, "MemberExternalKey in xmlData-Online DB is not as per expected : actual Value : " + MemberExternalId);
		assertEquals(accountId, db_accountId, "Account ID in xmlData-Online DB is not as per expected : actual Value : " + accountId);
		assertEquals(accValue, db_accValue, "Account balance Value in xmlData-Online DB is not as per expected : actual Value : " + accValue);

	}

	@Given("Retrieve Locking details from online Database")
	public void retrieve_locking_details_from_online_database() throws SQLException, ClassNotFoundException {
		String db_BuyingUnitInternalKey = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY",
				"select BuyingUnitInternalKey from CRM_Member where MemberInternalKey =(select MemberInternalKey from CRM_ClubCard where Clubcardid='"+Resources.parameters.get("clubCardId")+"' and Matrixmemberid='"+Resources.parameters.get("matrixMemberId")+"')", "BuyingUnitInternalKey");

		String db_LockingKey_BeforeMsg1 = BusinessScenarios.retriveDB("ONLINE","LOYALTYONLINE",
				"select LockTicketKey from [dbo].[CRM_OL_BuyingUnitData] where buyingunitinternalkey = '"+db_BuyingUnitInternalKey+"'", "LockTicketKey");

		String db_LockDateTime_BeforeMsg1 =BusinessScenarios.retriveDB("ONLINE","LOYALTYONLINE",
				"select LockDateTime from [dbo].[CRM_OL_BuyingUnitData] where buyingunitinternalkey = '"+db_BuyingUnitInternalKey+"'", "LockDateTime");

		Resources.parameters.put("db_LockingKey_BeforeMsg1", db_LockingKey_BeforeMsg1+"");
		Resources.parameters.put("db_LockDateTime_BeforeMsg1", db_LockDateTime_BeforeMsg1+"");
	}
	@Then("Validate the MSG2 with QueryMode")
	public void validate_the_msg2_with_query_mode() throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		String response_Pretty1 = response_Pretty.replace(response_Pretty.substring(response_Pretty.indexOf("<?xml"), response_Pretty.indexOf("<Customer")), "");

		//System.out.println(response_Pretty1);
		String response_MsgType = XMLExtraction.Extraction(response_Pretty1, "Customer", 0, "MsgType");
		String MemberExternalId= XMLExtraction.Extraction(response_Pretty1, "Member", 0, "MemberExternalId");
		String locked = XMLExtraction.Extraction(response_Pretty1, "LoyaltyInfo", 0, "Locked");
		String QueryMode= XMLExtraction.Extraction(response_Pretty1, "Customer", 0, "QueryMode");

		assertEquals(QueryMode,"1", "Value of QueryMode in Msg2 response is not as per expected : actual Value : " + QueryMode);

		String db_SP_CRM_OL_Msg2_Lock_Enabled = BusinessScenarios.retriveDB("ONLINE","LOYALTYONLINE",
				"select ParameterValue from SystemParametersLocal where ParameterKey='CRM_OL_Msg2_Lock_Enabled' and MatrixMemberId='"+Resources.parameters.get("matrixMemberId")+"'", "ParameterValue");

		if(Integer.parseInt(db_SP_CRM_OL_Msg2_Lock_Enabled)==1)
		{
			assertEquals(locked,"1", "Value of locked in Msg2 response is not as per expected : actual Value : " + locked);
		}
		else if (Integer.parseInt(db_SP_CRM_OL_Msg2_Lock_Enabled)==0)
		{
			assertEquals(locked,"0", "Value of locked in Msg2 response is not as per expected : actual Value : " + locked);
		}
		else
		{
			System.out.println("Incorrect value for System parameter CRM_OL_Msg2_Lock_Enabled in Online Database");
		}

		String db_BuyingUnitInternalKey = BusinessScenarios.retriveDB("OPERATIONAL","LOYALTY",
				"select BuyingUnitInternalKey from CRM_Member where MemberInternalKey =(select MemberInternalKey from CRM_ClubCard where Clubcardid='"+Resources.parameters.get("clubCardId")+"' and Matrixmemberid='"+Resources.parameters.get("matrixMemberId")+"')", "BuyingUnitInternalKey");
		String db_LockingKey_AfterMsg1 = BusinessScenarios.retriveDB("ONLINE","LOYALTYONLINE",
				"select LockTicketKey from [dbo].[CRM_OL_BuyingUnitData] where buyingunitinternalkey = '"+db_BuyingUnitInternalKey+"'", "LockTicketKey");
		String db_LockDateTime_AfterMsg1 =BusinessScenarios.retriveDB("ONLINE","LOYALTYONLINE",
				"select LockDateTime from [dbo].[CRM_OL_BuyingUnitData] where buyingunitinternalkey = '"+db_BuyingUnitInternalKey+"'", "LockDateTime");
		assertEquals(Resources.parameters.get("db_LockingKey_BeforeMsg1"),db_LockingKey_AfterMsg1, "Value of LockingKey in Online DB is not as per expected : actual Value : " + db_LockingKey_AfterMsg1);
		assertEquals(Resources.parameters.get("db_LockDateTime_BeforeMsg1"),db_LockDateTime_AfterMsg1, "Value of LockDateTime in Online DB is not as per expected : actual Value : " + db_LockDateTime_AfterMsg1);
	}


}
