package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenAuthorization {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "VALIDATECLUBCARDID":
                return validateClubCardId(att);
        }
        return "";
    }

    public String validateClubCardId(List<Map<Object, Object>> att){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:ValidateClubCardId>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:clubCardId>"+att.get(0).get("clubcardid")+"</hql:clubCardId>\n" +
                "      </hql:ValidateClubCardId>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
