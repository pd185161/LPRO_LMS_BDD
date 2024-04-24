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

import com.LMS.LMSAPIAutomation.BusinessScenarios.BusinessScenarios;
import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import com.LMS.LMSAPIAutomation.Payload.PromotionPayload;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author sa185402
 *
 */
public class Promotion extends BaseClass {
	
	PromotionPayload promotionPayload = new PromotionPayload();
	@Given("Add Promotion {string} request Payload")
	public void add_promotion_request_payload(String string) throws FileNotFoundException {
	  
		HashMap<String,String> HttpHeaders = new HashMap<String,String>();	
		//HttpHeaders.put("Content-Type", "text/xml");
		if(prop.accessToken().equalsIgnoreCase("1")){
			HttpHeaders.put("Authorization", "AccessToken "+Login.sessionId);
			HttpHeaders.put("Content-Type", "text/xml");
		}
	
		add_payload(promotionPayload.payload(string, Resources.parameters),HttpHeaders,"text/xml;charset=UTF-8",Login.sessionId);
	
	}
	
	@Then("Validate the attributes in CopyAndSuspendFromSourceResult Response")
	public void validate_the_attributes_in_copy_and_suspend_from_source_result_response() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		String response_Pretty1 = response_Pretty.replace(response_Pretty.substring(response_Pretty.indexOf("<?xml"), response_Pretty.indexOf("<HQL_Promotion")), "");
	
		String promotionid = XMLExtraction.Extraction(response_Pretty1, "HQL_Promotion_WS_Response_Copy", 0, "SourceInternalPromotionId");
		String newpromotionid_actual = XMLExtraction.Extraction(response_Pretty1, "HQL_Promotion_WS_Response_Copy", 0, "NewInternalPromotionId");
		int newPromotionid = Integer.parseInt(Resources.parameters.get("promotionHeaderId")) +1;
		
		String promotionHeaderDescription_DB =  BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", 
				"Select PromotionHeaderDescription from PromotionHeader where PromotionHeaderId = "+newPromotionid, "PromotionHeaderDescription");
		
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "CopyAndSuspendFromSourceResult - promotionHeaderId is not as per expected : actual Value : " + promotionid);
		assertEquals(newpromotionid_actual, newPromotionid+"", "CopyAndSuspendFromSourceResult - New promotion created is not as per expected : actual Value : " + newpromotionid_actual);
		assertEquals(promotionHeaderDescription_DB, "CopynSuspended_"+Resources.parameters.get("promotionHeaderId"), "CopyAndSuspendFromSourceResult - New promotion Header Description is not as per expected : actual Value : " + promotionHeaderDescription_DB);
		
		
	}
	
	@Then("Validate the attributes in CopyPromotionResult Response")
	public void validate_the_attributes_in_copy_promotion_result_response() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
		String response_Pretty1 = response_Pretty.replace(response_Pretty.substring(response_Pretty.indexOf("<?xml"), response_Pretty.indexOf("<HQL_Promotion")), "");
	
		String promotionid = XMLExtraction.Extraction(response_Pretty1, "HQL_Promotion_WS_Response_Copy", 0, "SourceInternalPromotionId");
		String copypromotionid_actual = XMLExtraction.Extraction(response_Pretty1, "HQL_Promotion_WS_Response_Copy", 0, "NewInternalPromotionId");
		int copyPromotionid = Integer.parseInt(Resources.parameters.get("promotionHeaderId")) +2;
		
		String promotionHeaderDescription_DB =  BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", 
				"Select PromotionHeaderDescription from PromotionHeader where PromotionHeaderId = "+copyPromotionid, "PromotionHeaderDescription");
		
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "CopyAndSuspendFromSourceResult - promotionHeaderId is not as per expected : actual Value : " + promotionid);
		assertEquals(copypromotionid_actual, copyPromotionid+"", "CopyAndSuspendFromSourceResult - New promotion created is not as per expected : actual Value : " + copyPromotionid);
		assertEquals(promotionHeaderDescription_DB, "Automation copy of "+Resources.parameters.get("promotionHeaderId"), "CopyPromotionResult - New promotion Header Description is not as per expected : actual Value : " + promotionHeaderDescription_DB);
		
	}
	
	@Then("Validate the attributes in GetPromotionByExternalIdResult Response")
	public void validate_the_attributes_in_get_promotion_by_external_id_result_response() throws SAXException, IOException, ParserConfigurationException {
		
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
	
		String promotionid = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "PromotionInternalKey");
		
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "GetPromotionByExternalIdResult - promotionHeaderId is not as per expected : actual Value : " + promotionid);	
		
	}
	
	@Then("Validate the attributes in GetPromotionByInternalIdResult Response")
	public void validate_the_attributes_in_get_promotion_by_internal_id_result_response() throws SAXException, IOException, ParserConfigurationException {
		
		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");
	
		String promotionid = XMLExtraction.Extraction(response_Pretty, "Promotion", 0, "PromotionInternalKey");
		
		assertEquals(promotionid, Resources.parameters.get("promotionHeaderId"), "GetPromotionByInternalIdResult - promotionHeaderId is not as per expected : actual Value : " + promotionid);	
		
	}
	
	@Then("Validate the attributes in UpdatePromotion Response")
	public void validate_the_attributes_in_update_promotion_response() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		
		String promotionHeaderDescription_DB =  BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", 
				"Select PromotionHeaderDescription from PromotionHeader where PromotionHeaderId = "+Resources.parameters.get("promotionHeaderId"), "PromotionHeaderDescription");
		
		assertEquals(promotionHeaderDescription_DB, "Automation Update Promotion", "UpdatePromotionResults - promotionHeaderDescription_DB is not as per expected : actual Value : " + promotionHeaderDescription_DB);	
		
	}


}
