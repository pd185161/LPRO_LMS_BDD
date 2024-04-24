package com.LMS.LMSAPIAutomation.RequestTemplates;

import com.LMS.LMSAPIAutomation.PayloadObjects.Messages.Customer;
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
public class Messages_DocumentTemplate {

    public String requestTemplate(Customer customer, String retailerId ) throws JAXBException, ParserConfigurationException, TransformerException {

        StringWriter stringWriter_Customer = new StringWriter();
//        stringWriter_LoyaltyMessages.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

        JAXBContext contextObj = JAXBContext.newInstance(Customer.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        marshallerObj.marshal(customer, stringWriter_Customer);

        Document doc = documentbuilder(stringWriter_Customer,retailerId);
        return transformerFactory(doc);
    }

    public Document documentbuilder(StringWriter customer, String retailerId) throws ParserConfigurationException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("soapenv:Envelope");
        rootElement.setAttribute("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
        rootElement.setAttribute("xmlns:web","http://www.retalix.com/rema/webservices");
        doc.appendChild(rootElement);

        Element header = doc.createElement("soapenv:Header");
        rootElement.appendChild(header);

        Element body = doc.createElement("soapenv:Body");
        rootElement.appendChild(body);

        Element validateTest = doc.createElement("web:ValidateTest");
//        processMessage.setAttribute("xmlns","http://tempuri.org/");
        body.appendChild(validateTest);

        Element chain = doc.createElement("web:in_RetailerId");
        chain.setTextContent(retailerId);
        validateTest.appendChild(chain);

//        Element branch = doc.createElement("branch");
//        branch.setTextContent("1");
//        getMemberData.appendChild(branch);

        Element description = doc.createElement("web:in_XML");
        description.appendChild(doc.createCDATASection(customer.toString()));
        validateTest.appendChild(description);
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
