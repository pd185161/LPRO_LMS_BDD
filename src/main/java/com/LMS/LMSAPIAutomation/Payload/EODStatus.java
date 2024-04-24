package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EODStatus {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETEODSTATUS":
                return getEODStatus();
        }
        return "";
    }

    public String getEODStatus(){

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ret=\"http://www.Retalix.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ret:GetEODStatus>\n" +
                "         <!--Optional:-->\n" +
                "         <ret:in_XML><![CDATA[<Filter>\n" +
                "\t<Attribute Id=\"StoreFilter\" Value=\"String\" ValueTo=\"String\">\n" +
                "\t\t<Union>\n" +
                "\t\t\t<Stores>\n" +
                "\t\t\t\t<Store MatrixMemberId=\"3\" StoreId=\"1\" StoreInternalKey=\"0\" StoreName=\"1\" RetailerId=\"2\"/>\n" +
                "\t\t\t</Stores>\n" +
                "\t\t</Union>\n" +
                "\t</Attribute>\n" +
                "</Filter>]]></ret:in_XML>\n" +
                "      </ret:GetEODStatus>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
