package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadPackagesStatus {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETDOWNLOADPACKAGESSTATUS":
                return getDownloadPackagesStatus();
        }
        return "";
    }

    public String getDownloadPackagesStatus(){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ret=\"http://www.Retalix.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ret:GetDownloadPackagesStatus>\n" +
                "         <!--Optional:-->\n" +
                "         <ret:in_XML><![CDATA[<DownloadRequest StartDate=\"2023-01-13\" EndDate=\"2023-07-13\"/>]]></ret:in_XML>\n" +
                "      </ret:GetDownloadPackagesStatus>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

    }
}
