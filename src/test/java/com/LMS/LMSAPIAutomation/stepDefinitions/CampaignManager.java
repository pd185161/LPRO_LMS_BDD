/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;

import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Payload.CampaignManagerPayload;
import com.LMS.LMSAPIAutomation.Payload.PromotionPayload;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author sa185402
 *
 */
public class CampaignManager extends BaseClass {
	
	ReadProperties prop = new ReadProperties();
	static HashMap<String,String> parameters = new HashMap<String,String>();
	String matrixMemberId,retailerId;
	CampaignManagerPayload  CampaignManagerPayload= new CampaignManagerPayload();
	PromotionPayload promotionPayload = new PromotionPayload();
	
	@Given("Add CampaignManager {string} Json request Payload")
	public void add_campaign_manager_json_request_payload(String string, io.cucumber.datatable.DataTable userTable) throws FileNotFoundException {
	   
		HashMap<String,String> HttpHeaders = new HashMap<String,String>();	
		HttpHeaders.put("Content-Type", "application/json");
		HttpHeaders.put("Authorization", "LmsSession "+Login.sessionId);
		HttpHeaders.put("DebugLevel", "Info");
		
		add_payload(CampaignManagerPayload.payload(string, Resources.parameters,userTable),HttpHeaders,"application/json");
		
	}
	
	@Then("Validate the Promotion Exist")
	public void validate_the_promotion_exist() throws ClassNotFoundException, SQLException {
		
		Resources.validate_PromotionHeaderId_DB();
	
	}

	@Given("Add CampaignManager {string} Json request Payload with loop {int}")
	public void add_campaign_manager_json_request_payload_with_loop(String payload, Integer loop,io.cucumber.datatable.DataTable userTable)  throws FileNotFoundException, SQLException, ClassNotFoundException {

		int count =0;

		do {

			count = count+1;

//			Resources.validate_PromotionHeaderId_DB();
//			HashMap<String,String> HttpHeaders = new HashMap<String,String>();
//			HttpHeaders.put("Content-Type", "application/json");
//			HttpHeaders.put("Authorization", "LmsSession "+Login.sessionId);
//			HttpHeaders.put("DebugLevel", "Info");
//
//			add_payload(CampaignManagerPayload.payload(payload, Resources.parameters,userTable),HttpHeaders,"application/json");
			HashMap<String,String> HttpHeaders = new HashMap<String,String>();
			HttpHeaders.put("Content-Type", "text/xml");
//
			add_payload(promotionPayload.payload(payload, Resources.parameters),HttpHeaders,"text/xml;charset=UTF-8",Login.sessionId);

			postMethod("Promotion_XML");

			responseValidation(200);

		} while(count < loop+1);
	}

}
