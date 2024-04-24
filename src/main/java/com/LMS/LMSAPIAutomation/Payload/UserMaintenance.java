package com.LMS.LMSAPIAutomation.Payload;

import com.LMS.LMSAPIAutomation.Resources.ReadProperties;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMaintenance {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETUSERDETAILS":
                return getUserDetails(att);
        }
        return "";
    }

    public String getUserDetails(List<Map<Object, Object>> att){
        return"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:GetUserDetails>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:UserName>"+att.get(0).get("username")+"</hql:UserName>\n" +
                "      </hql:GetUserDetails>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

    }
}
