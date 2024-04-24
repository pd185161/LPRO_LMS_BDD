package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitServices {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETVISITTICKET":
                return getVisitTicket();
        }
        return "";
    }

    public String getVisitTicket(){

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:GetVisitTicket>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:visit><![CDATA[<Visit Id=\"9\" TotalEarned=\"1\" TotalSaved=\"1\" PosId=\"10\" BusinessDate=\"2023-07-04T00:00:00\" PurchaseDate=\"2023-07-04T09:34:43\">\n" +
                "<Store Id=\"1\" />\n" +
                "</Visit>]]></hql:visit>\n" +
                "      </hql:GetVisitTicket>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

    }
}
