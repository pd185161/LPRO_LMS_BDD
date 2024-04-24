package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateEditor {
    public HashMap<String,String> sessionid =new HashMap<>();
    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

        switch (Payload.toUpperCase()){
            case "GETMESSAGES":
                return getMessages(sessionid);
        }
        return "";
    }


    public String getMessages(HashMap<String,String> sessionid){

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:GetMessages>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:sessionId>"+sessionid.get("sessionid")+"</hql:sessionId>\n" +
                "      </hql:GetMessages>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

    }
}
