package com.LMS.LMSAPIAutomation.stepDefinitions;

import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.Payload.CommGTYPayload;
import com.LMS.LMSAPIAutomation.Payload.MemberServicePayload;
import com.LMS.LMSAPIAutomation.Payload.MessagesPayload;
import io.cucumber.java.en.Given;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommGTY extends BaseClass{

    CommGTYPayload CommGTYPayload= new CommGTYPayload();
    MessagesPayload messagesPayload = new MessagesPayload();
    @Given("SystemParameter {string} value is {string} in {string} DB")
    public void systemparameterValueIsInDB(String arg0, String arg1, String arg2) {
//        Write here to update system parameter values

    }

    @Given("System uses {string} authorization with {string} credentials")
    public void systemUsesAuthorizationWithCredentials(String authorizationType,String validity,  io.cucumber.datatable.DataTable userTable) {

        List<Map<Object, Object>> att= userTable.asMaps(String.class,String.class);
        HashMap<String,String> data= new HashMap<String, String>();
        data.put("Credentials", att.get(0).get("Credentials")+"");

        String base64EncodedString = GenericMethods.encodeToBase64(data.get("Credentials"));

        Resources.parameters.put("authorizationType",authorizationType);
        switch (authorizationType.toUpperCase()) {
            case "BASIC":
                Resources.parameters.put("authorizationValue", base64EncodedString);
                break;

            case "ACCESSTOKEN":
                if(validity.equalsIgnoreCase("VALID")){
                    Resources.parameters.put("authorizationValue", Login.sessionId);
                }
                else if (validity.equalsIgnoreCase("INVALID")){
                    //Static SessionId which is invalid

                    Resources.parameters.put("authorizationValue", "A7275AC7-79BF-431B-87EC-DEB4EC3A8DD1");
                }
                break;
            default:
                break;
        }

    }


    @Given("Add CommGTY {string} request Payload")
    public void addCommGTYRequestPayload(String scenarioName) throws FileNotFoundException {
        HashMap<String,String> HttpHeaders = new HashMap<String,String>();
        HttpHeaders.put("Content-Type", "text/xml");

        if(Resources.parameters.get("authorizationType").equalsIgnoreCase("BASIC"))
        {
            HttpHeaders.put("Authorization", "Basic "+Resources.parameters.get("authorizationValue"));
        } else if (Resources.parameters.get("authorizationType").equalsIgnoreCase("AccessToken")) {
            HttpHeaders.put("Authorization", "AccessToken "+Resources.parameters.get("authorizationValue"));
        }
        add_payload(CommGTYPayload.payload(scenarioName, Resources.parameters),HttpHeaders,"text/xml");

    }
    @Given("Add CommGTY Messages {string} request Payload")
    public void add_CommGTY_messages_request_payload(String payload, io.cucumber.datatable.DataTable userTable) throws FileNotFoundException {

        HashMap<String,String> HttpHeaders = new HashMap<String,String>();
        HttpHeaders.put("Content-Type", "text/xml");

        if(Resources.parameters.get("authorizationType").equalsIgnoreCase("BASIC"))
        {
            HttpHeaders.put("Authorization", "Basic "+Resources.parameters.get("authorizationValue"));
        } else if (Resources.parameters.get("authorizationType").equalsIgnoreCase("AccessToken")) {
            HttpHeaders.put("Authorization", "AccessToken "+Resources.parameters.get("authorizationValue"));
        }
        add_payload(messagesPayload.payload(payload, Resources.parameters,userTable),HttpHeaders,"text/xml");

    }

    @Given("Update System Parameter {string} value to {string} in {string} DB")
    public void updateSystemParameterValueInDB(String SystemParameterName, String SystemParameterValue, String DBName) throws SQLException {
        Resources.UpdateSystemParameter(SystemParameterName,SystemParameterValue,DBName);
    }
}