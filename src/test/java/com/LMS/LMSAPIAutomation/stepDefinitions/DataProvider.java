package com.LMS.LMSAPIAutomation.stepDefinitions;

import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import io.cucumber.java.en.Given;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class DataProvider extends BaseClass{

    com.LMS.LMSAPIAutomation.Payload.DataProvider DataProvider = new com.LMS.LMSAPIAutomation.Payload.DataProvider();

    @Given("Add DataProvider {string} request Payload")
    public void add_DataProvider_payload(String scenario_name, io.cucumber.datatable.DataTable userTable) throws FileNotFoundException, JAXBException, TransformerException {

        HashMap<String,String> HttpHeaders = new HashMap<>();
        if(prop.accessToken().equalsIgnoreCase("1")){
            HttpHeaders.put("Authorization", "AccessToken "+Login.sessionId);
            HttpHeaders.put("Content-Type","text/xml");
        }

        add_payload(DataProvider.payload(scenario_name, Resources.parameters,userTable),HttpHeaders,"text/xml;charset=UTF-8");
    }
}
