package com.LMS.LMSAPIAutomation.RequestTemplates;

import com.LMS.LMSAPIAutomation.Resources.Log4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Documentbuilder {

    public Document documentbuilder(){

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.newDocument();
             return doc;
        } catch (ParserConfigurationException e) {
            Log4j.loginfo(e.getMessage());
        }

        return null;

    }

    public Element rootElement(Document doc, HashMap<String,HashMap<String,String>> rootElementTag  ){
        try {

            Element rootElement = null;

             Set<String> child_key = rootElementTag.keySet();
                for (String ele: child_key) {
                    rootElement = doc.createElement(ele);
                    if (rootElementTag.get(ele).keySet().size() != 0){
                        Set<String> child_key1 = rootElementTag.get(ele).keySet();
                        for (String element1:child_key1) {
                            rootElement.setAttribute(element1,rootElementTag.get(ele).get(element1));
                        }
                    }
                    doc.appendChild(rootElement);
                    return rootElement;
                }

        }catch(Exception e){
            Log4j.loginfo(e.getMessage());
        }

        return null;
    }

    public Element nodeElement(Element parentelement,Document doc, HashMap<String,HashMap<String,String>> tagName){
       Element element =null;
        Set<String> child_key = tagName.keySet();
         for (String ele: child_key) {
                    element = doc.createElement(ele);
                    if (tagName.get(ele).keySet().size() != 0){
                        Set<String> child_key1 = tagName.get(ele).keySet();
                        for (String element1:child_key1) {
                            element.setAttribute(element1,tagName.get(ele).get(element1));
                        }
                    }
                    parentelement.appendChild(element);
                }

        return element;
    }



    public Element nodeElement_setTextContent(Element parentElement,Document doc, HashMap<String,String> childTagName){
        Element childElement = null;
        Set<String> child_key = childTagName.keySet();
                for (String ele: child_key) {
                    childElement = doc.createElement(ele);
                    if (!childTagName.get(ele).equalsIgnoreCase("")){
                        childElement.setTextContent(childTagName.get(ele));
                    }
                    parentElement.appendChild(childElement);
                }


        return childElement;
    }

    public Element nodeElement_CDATA(Element parentElement,Document doc, String ele, StringWriter classObject){
        Element childElement = null;
        childElement = doc.createElement(ele);
        childElement.appendChild(doc.createCDATASection(classObject.toString()));
        parentElement.appendChild(childElement);
        return childElement;
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


    public String removeAttributeOrderIdentifiers(String xml) {
    return xml.replaceAll("[a-z]__(.+?=\")", "$1");
    }

    public static void main(String[] args) throws TransformerException {





        HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
        	childElementTag.put("soapenv:Body",new HashMap<>());
			childElementTag.put("soapenv:Header",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetAddressByZipCode = new HashMap<String, String>();
			childElementTag1.put("hql:GetDemographic", GetAddressByZipCode);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("hql:in_ClubCardId","clubCardId");

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);

//        System.out.println(str);

    }



}
