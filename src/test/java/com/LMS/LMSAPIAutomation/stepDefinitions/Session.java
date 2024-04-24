package com.LMS.LMSAPIAutomation.stepDefinitions;

import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class Session extends BaseClass{

    com.LMS.LMSAPIAutomation.Payload.Session Session = new com.LMS.LMSAPIAutomation.Payload.Session();

    @Given("Add session {string} request Payload")
    public void add_session_payload(String scenario_name, io.cucumber.datatable.DataTable userTable) throws FileNotFoundException, JAXBException, TransformerException {

        HashMap<String,String> HttpHeaders = new HashMap<>();
        if(prop.accessToken().equalsIgnoreCase("1")){
            HttpHeaders.put("Authorization", "AccessToken "+Login.sessionId);
            HttpHeaders.put("Content-Type","text/xml");
        }

        add_payload(Session.payload(scenario_name, Resources.parameters,userTable),HttpHeaders,"text/xml;charset=UTF-8");
    }

    @Then("Validate the response result as {string}")
    public void validateTheResponseResultAs(String resultValue) throws IOException, ParserConfigurationException, SAXException {

        String response1 = response.asPrettyString().replace("&lt;", "<");
        String response_Pretty = response1.replace("&gt;", ">");

        String responseResult = XMLExtraction.Extraction(response_Pretty, "IsSessionKeyValidResult", 0, "");

        assertEquals(responseResult, resultValue, "Response result is not as per expectation");
    }

}
