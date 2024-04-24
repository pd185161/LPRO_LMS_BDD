/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import io.cucumber.datatable.DataTableType;
import org.xml.sax.SAXException;

import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import com.LMS.LMSAPIAutomation.Payload.LoginPayload;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * @author sa185402
 *
 */
public class Login extends BaseClass{
	
	public static String sessionId;
	
	@Given("Add Login {string} request Payload")
	public void add_login_request_payload(String string) throws FileNotFoundException {
		
		HashMap<String,String> HttpHeaders = new HashMap<String,String>();	
		HttpHeaders.put("Content-Type", "text/xml;charset=UTF-8");
		HttpHeaders.put("Accept-Encoding", "gzip,deflate");
		
		add_payload(LoginPayload.Payload(string),HttpHeaders,"text/xml;charset=UTF-8");
	}

	@Then("Extract sessionId from response")
	public void extract_session_id_from_response() throws SAXException, IOException, ParserConfigurationException {
		
		String response_Pretty = response.asPrettyString();
		sessionId = XMLExtraction.Extraction(response_Pretty,"out_SessionKey", 0, "");
		System.out.println("session id is " + sessionId);
	   
	}
}
