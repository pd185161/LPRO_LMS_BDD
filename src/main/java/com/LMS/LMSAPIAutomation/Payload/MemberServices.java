package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberServices {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETMEMBER":
                return getMember(att);
        }
        return "";
    }

    public String getMember(List<Map<Object, Object>> att){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:GetMember>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:id>"+att.get(0).get("clubcardid")+"</hql:id>\n" +
                "      </hql:GetMember>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
