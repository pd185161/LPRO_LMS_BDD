package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionItems {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "GETTRANSACTIONSITEMS":
                return getTransactionsItems();
        }
        return "";
    }

    public String getTransactionsItems(){
        return"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:GetTransactionsItems>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:in_xml><![CDATA[<Transactions> <Transaction StoreId=\"26\" PosDateTime=\"2022-04-13\" PosId=\"1\" TranId=\"5\" StartDateTime=\"2022-04-13T12:02:55\"/></Transactions>]]></hql:in_xml>\n" +
                "      </hql:GetTransactionsItems>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
