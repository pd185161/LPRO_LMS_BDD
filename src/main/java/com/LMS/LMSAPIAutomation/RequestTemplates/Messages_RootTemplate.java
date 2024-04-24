package com.LMS.LMSAPIAutomation.RequestTemplates;

import com.LMS.LMSAPIAutomation.PayloadObjects.Messages.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class Messages_RootTemplate {


    public String requestTemplate(Root documents, String retailerId ) throws JAXBException, ParserConfigurationException, TransformerException {

        StringWriter stringWriter_root = new StringWriter();

        JAXBContext contextObj = JAXBContext.newInstance(Root.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        marshallerObj.marshal(documents, stringWriter_root);

        Document doc = documentbuilder(stringWriter_root,retailerId);
        return transformerFactory(doc);
    }

    public Document documentbuilder(StringWriter root, String retailerId) throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("soap:Envelope");
        rootElement.setAttribute("xmlns:soap","http://www.w3.org/2003/05/soap-envelope");
        rootElement.setAttribute("xmlns:tem","http://tempuri.org/");
        doc.appendChild(rootElement);

        Element header = doc.createElement("soap:Header");
        rootElement.appendChild(header);

        Element body = doc.createElement("soap:Body");
        rootElement.appendChild(body);

        Element getMemberData = doc.createElement("tem:StoreData");
//        processMessage.setAttribute("xmlns","http://tempuri.org/");
        body.appendChild(getMemberData);

        Element messageHandlerApplication = doc.createElement("tem:messageHandlerApplication");
        messageHandlerApplication.setTextContent("StoreInLoyalty");
        getMemberData.appendChild(messageHandlerApplication);

        Element chain = doc.createElement("tem:chain");
        chain.setTextContent(retailerId);
        getMemberData.appendChild(chain);

        Element branch = doc.createElement("tem:branch");
        branch.setTextContent("1");
        getMemberData.appendChild(branch);

        Element description = doc.createElement("tem:messageData");
        description.appendChild(doc.createCDATASection(root.toString()));
        getMemberData.appendChild(description);
        return doc;
    }

    public String transformerFactory(Document doc) throws TransformerException {

        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        doc.setXmlStandalone(true);
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, "Customer");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);

        return stringWriter.toString();

    }

}
