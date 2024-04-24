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
import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import com.LMS.LMSAPIAutomation.Payload.SaveLoyaltyDocumentsPayload;

import com.LMS.LMSAPIAutomation.Resources.Log4j;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @author er185127
 *
 */
public class SaveLoyaltyDocuments extends BaseClass {

	SaveLoyaltyDocumentsPayload documents = new SaveLoyaltyDocumentsPayload();

	String action, barcode, barcodeProgrammingId, voucherBarcode;
	
	@Given("Add Documents {string} request Payload")
	public void add_documents_request_payload(String scenario_name) throws FileNotFoundException, ClassNotFoundException, SQLException {
			
		HashMap<String,String> HttpHeaders = new HashMap<String,String>();
		if(prop.accessToken().equalsIgnoreCase("1")){
			HttpHeaders.put("Authorization", "AccessToken "+Login.sessionId);
			HttpHeaders.put("Content-Type","text/xml");
		}
		add_payload(documents.payload(scenario_name,Resources.parameters),HttpHeaders,"text/xml;charset=UTF-8",Login.sessionId);
	}

	@Given("User Enter valid data to the MSG34 request")
	public void user_enter_valid_data_to_the_msg34_request(io.cucumber.datatable.DataTable userTable) {
//		List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
//		Resources.parameters.put("action", att.get(0).get("Action")+"");
	}

	@Given("Extract the Document and Barcode Details from DB")
	public void extract_the_document_and_barcode_details_from_db() throws SQLException, ClassNotFoundException {
//		"Select top 1 BarcodeId from BarcodeTemplateHeader where ValidateCentralDocument = '1' and EnableMultipleRedemptionPromotions = '1' order by BarcodeId desc"
		String barcodeId = BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", "Select top 1 BarcodeId from BarcodeTemplateHeader where ValidateCentralDocument = '1' and EnableMultipleRedemptionPromotions = '1' order by BarcodeId desc", "BarcodeId");
		String barcode_Prefix = BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", "Select Value from  BarcodeTemplateLine where BarcodeId = "+"'"+barcodeId+"'"+" and AttributeId = '1'", "Value");

		String BarcodeMaxLength = BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", "Select BarcodeMaxLength from BarcodeTemplateHeader where BarcodeId =" +"'"+barcodeId+"'", "BarcodeMaxLength");

		int range = Integer.parseInt(BarcodeMaxLength) - (barcode_Prefix.length());

		String numeric =  "0123456789";
		StringBuilder barcode_1 = new StringBuilder(range);

		for ( int i=0; i<range; i++) {
			//generating a random number using math.random()
			int ch = (int)(numeric.length() * Math.random());
			//adding Random character one by one at the end of s
			barcode_1.append(numeric.charAt(ch));
		}

		String barcode = barcode_Prefix + barcode_1+"";
		if (barcode.length() == (Integer.parseInt(BarcodeMaxLength))){
			Resources.parameters.put("barcode", barcode);
			Resources.parameters.put("barcodeProgrammingId",barcodeId);
		}else{
			Log4j.Warn("Barcode is not generated correctly : Barcode - "+barcode);
		}

	}

	@Then("Extract {string} created")
	public void extract_created(String value) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {

		String response1 = response.asPrettyString().replace("&lt;", "<");
		String response_Pretty = response1.replace("&gt;", ">");

		if(value.equalsIgnoreCase("DocumentID")){

//			String Message1 = response_Pretty.replace("&quot;","'");
//			String Message2 = Message1.replace("\"<","\"");
//			String Message3 = Message2.replace("/>\"","\"");
//
//			String documentId1 = XMLExtraction.Extraction(Message3, "SaveDocumentResult", 0, "Message");
//
//			String[] Split_doc = documentId1.split("documentId='");
//			String documentId = documentId1.substring(0,Split_doc[1].length()-1);
//
			String instanceDescription = BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", "Select top 1 InstanceDescription  from [dbo].[CouponInstance] order by DocumentId desc", "InstanceDescription");
			if (instanceDescription.equalsIgnoreCase("Doc_Automation_"+ GenericMethods.currentDate())){
				String documentId = BusinessScenarios.retriveDB("OPERATIONAL","PROMOTION", "Select top 1 DocumentId from CouponInstance order by DocumentId desc", "DocumentId");
				Resources.parameters.put("documentID", documentId);
			}

		}

		if (value.equalsIgnoreCase("MessageTemplateID")){
			String messageTemplateId = XMLExtraction.Extraction(response_Pretty, "templateId", 0, "");
			Resources.parameters.put("messageTemplateId", messageTemplateId);
		}




	}


	@Given("Generate VoucherBarcodeId")
	public void generate_voucher_barcode_id() {

		String numeric =  "0123456789";
		StringBuilder voucher_Barcode = new StringBuilder(13);

		for ( int i=0; i<13; i++) {
			//generating a random number using math.random()
			int ch = (int)(numeric.length() * Math.random());
			//adding Random character one by one at the end of s
			voucher_Barcode.append(numeric.charAt(ch));
		}

		Resources.parameters.put("voucherBarcode", voucher_Barcode+"");

	}

}