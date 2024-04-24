package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfile {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETUSERPROFILES":
                return getUserProfiles(att);
        }
        return "";
    }

    public String getUserProfiles(List<Map<Object, Object>> att){

        return"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:getUserProfiles>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:retailerId>"+att.get(0).get("retailerId")+"</hql:retailerId>\n" +
                "      </hql:getUserProfiles>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
