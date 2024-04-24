package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SegmentMaintenance {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "CREATECLUBTARGETEDSEGMENT":
                return createClubTargetedSegment();
        }
        return "";
    }

    public String createClubTargetedSegment(){

        return"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:CreateClubTargetedSegment>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:inXmlData><![CDATA[<Segments>\n" +
                "\t<TargetedSegment SegmentDescription=\"Segment412\" GroupId=\"1\">\n" +
                "\t\t<Terms SubType=\"1\" IsShared=\"true\"/>\n" +
                "\t\t<ActivePeriod DetachExpired=\"false\">\n" +
                "\t\t\t<Always>true</Always>\n" +
                "\t\t</ActivePeriod>\n" +
                "\t</TargetedSegment>\n" +
                "</Segments>\n" +
                "]]></hql:inXmlData>\n" +
                "         <hql:clubId>2</hql:clubId>\n" +
                "      </hql:CreateClubTargetedSegment>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
