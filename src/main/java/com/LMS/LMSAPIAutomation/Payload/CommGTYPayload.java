package com.LMS.LMSAPIAutomation.Payload;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommGTYPayload {
    public String payload(String Payload, HashMap<String, String> parameters)  {

        switch (Payload.toUpperCase()) {
            case "GETRETAILERS":
                return getRetailers(parameters);
            case "GETRETAILER-STORES":
                return getRetailerStores(parameters);
            case "CDMADAPTER_PING":
                return cdmadapterPing(parameters);
            case "CDMADAPTER_CONSUMER":
                return cdmadapterConsumer(parameters);
        }
        return "";
    }
    public static String getRetailers(HashMap<String, String> parameters)  {
        return "";
    }
    public static String getRetailerStores(HashMap<String, String> parameters)  {
        return "";
    }
    public static String cdmadapterPing(HashMap<String, String> parameters)  {
        return "";
    }
    public static String cdmadapterConsumer(HashMap<String, String> parameters)  {
        return "";
    }
}
