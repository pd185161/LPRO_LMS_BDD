package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketingPromotions {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETMARKETINGPROMOTIONS":
                return getMarketingPromotions();
        }
        return "";
    }
    public String getMarketingPromotions(){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetMarketingPromotions>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:inXml><![CDATA[<PromotionQuery>\n" +
                "\t<Sort Column=\"UpdatedDate\" Order=\"desc\"/>\n" +
                "\t<MaxRecords Value=\"10\"/>\n" +
                "\t<Filter>\n" +
                "\t\t<Field Name=\"PromotionStartDate\" Value=\"2022-01-03\"/>\n" +
                "\t</Filter>\n" +
                "</PromotionQuery>]]></tem:inXml>\n" +
                "      </tem:GetMarketingPromotions>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
