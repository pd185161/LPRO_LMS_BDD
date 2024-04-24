package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreUpload {

    public String payload(String Payload, HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        switch (Payload.toUpperCase()){
            case "SETDAILYSTOREUPLOADSTATUS":
                return setDailyStoreUploadStatus(att);
        }
        return "";
    }


    public String setDailyStoreUploadStatus(List<Map<Object, Object>> att){
        return"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hql:SetDailyStoreUploadStatus>\n" +
                "         <!--Optional:-->\n" +
                "         <hql:in_XML><![CDATA[<DailyStoreStatuses CreateDateTime=\"2001-12-17T09:30:47Z\" RetailerId="+att.get(0).get("retailerid")+" StoreId=\"1\">\n" +
                "\t<DailyStoreStatus BusinessDate=\"2023-01-13\" BOStatusId=\"0\" BOStatusDescription=\"String\" BOManualInterventionRequired=\"1\" BOStatusType=\"E\"/>\n" +
                "</DailyStoreStatuses>]]></hql:in_XML>\n" +
                "      </hql:SetDailyStoreUploadStatus>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

    }
}
