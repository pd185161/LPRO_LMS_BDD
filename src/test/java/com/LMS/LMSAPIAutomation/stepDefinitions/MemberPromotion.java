/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;
import com.LMS.LMSAPIAutomation.Payload.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author sa185402
 *
 */
public class MemberPromotion extends BaseClass{
	
	ReadProperties prop = new ReadProperties();
	
	@Given("Add MemberPromotion {string} request Payload")
	public void add_member_promotion_request_payload(String scenario_name) throws FileNotFoundException, ClassNotFoundException, SQLException {
			
		HashMap<String,String> HttpHeaders = new HashMap<String,String>();
		if(prop.accessToken().equalsIgnoreCase("1")){
			HttpHeaders.put("Authorization", "AccessToken "+Login.sessionId);
			HttpHeaders.put("Content-Type","text/xml");
		}
		
		add_payload(MemberPromotionsPayload.payload(scenario_name,Resources.parameters),HttpHeaders,"text/xml;charset=UTF-8",Login.sessionId);
	}
	
	@Then("Validate the GetPromotionsHouseHoldRegisteredFor Response")
	public void validate_the_get_promotions_house_hold_registered_for_response() throws SAXException, IOException, ParserConfigurationException {
	   
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		
		String promotionid = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "id");
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "GetPromotionsHouseHoldRegisteredFor - promotionHeaderId is not as per expected : actual Value : " + promotionid);
	}
	
	@Then("Validate the GetRegisterToPromotionHistory Response")
	public void validate_the_get_register_to_promotion_history_response() throws SAXException, IOException, ParserConfigurationException {
	  
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		
		String promotionid = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "PromotionHeaderId");
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "GetRegisterToPromotionHistory - promotionHeaderId is not as per expected : actual Value : " + promotionid);
		
	}
	
	@Then("Validate the GetRegisterToPromotionHistoryByExternalId Response")
	public void validate_the_get_register_to_promotion_history_by_external_id_response() throws SAXException, IOException, ParserConfigurationException {
	    
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		
		String promotionid = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "PromotionHeaderId");
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "GetRegisterToPromotionHistoryByExternalId - promotionHeaderId is not as per expected : actual Value : " + promotionid);
		
	}
	
	@Then("Validate the GetRegisterToPromotionHistoryByInternalId Response")
	public void validate_the_get_register_to_promotion_history_by_internal_id_response() throws SAXException, IOException, ParserConfigurationException {
	 
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		
		String promotionid = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "PromotionHeaderId");
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "GetRegisterToPromotionHistoryByInternalId - promotionHeaderId is not as per expected : actual Value : " + promotionid);
		
	}

}
