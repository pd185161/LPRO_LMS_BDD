package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Download {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "FORCEDOWNLOADEXISTINGEVENTS":
                return forceDownloadExistingEvents(att);
        }
        return "";
    }

    public String forceDownloadExistingEvents(List<Map<Object, Object>> att){

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ret=\"http://www.Retalix.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ret:ForceDownloadExistingEvents>\n" +
                "         <!--Optional:-->\n" +
                "         <ret:in_RetailerId>"+att.get(0).get("retailerid")+"</ret:in_RetailerId>\n" +
                "      </ret:ForceDownloadExistingEvents>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
