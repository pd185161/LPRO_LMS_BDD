package com.LMS.LMSAPIAutomation.Payload;

import com.LMS.LMSAPIAutomation.RequestTemplates.Documentbuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;

public class SessionPayload {
    public static String payload(String Payload) throws ParserConfigurationException, TransformerException {
        HashMap<String, String> hs = new HashMap<String,String>();
        hs.put("IsSessionKeyValid",IsSessionKeyValid());
        return hs.get(Payload);
    }
    public static String IsSessionKeyValid() throws ParserConfigurationException, TransformerException {
        Documentbuilder documentbuilder = new Documentbuilder();
        Document doc  = documentbuilder.documentbuilder();

        Element rootElement = doc.createElement("soapenv:Envelope");
        rootElement.setAttribute("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
        rootElement.setAttribute("xmlns:hql","http://www.retalix.com/HQLWebServices");
        doc.appendChild(rootElement);

        Element header = doc.createElement("soapenv:Header");
        rootElement.appendChild(header);

        Element body = doc.createElement("soapenv:Body");
        rootElement.appendChild(body);

        Element isSessionKeyValid = doc.createElement("hql:IsSessionKeyValid");
        body.appendChild(isSessionKeyValid);

        String payload= documentbuilder.transformerFactory(doc);

        return payload;

    }
}
