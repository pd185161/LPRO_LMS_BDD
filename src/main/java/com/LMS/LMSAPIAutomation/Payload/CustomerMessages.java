package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerMessages {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETCUSTOMERMESSAGES":
                return getCustomerMessages(att);
        }
        return "";
    }

    public String getCustomerMessages(List<Map<Object, Object>> att){
        return"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetCustomerMessages>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:clubCardId>"+att.get(0).get("clubcardid")+"</tem:clubCardId>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:touchPointType>1</tem:touchPointType>\n" +
                "      </tem:GetCustomerMessages>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
