/**
 * 
 */
package com.LMS.LMSAPIAutomation.Generic;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.LMS.LMSAPIAutomation.Resources.Log4j;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;

//import io.restassured.response.ResponseBody;

import org.w3c.dom.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author sa185402
 *
 */
public class XMLExtraction {
	
	
	 ReadProperties prop = new ReadProperties();
	
	public static String Extraction(String Response, String TagName, int index, String Attribute) throws SAXException, IOException, ParserConfigurationException {
		
		 DocumentBuilderFactory documentBuilderFactory;
		 DocumentBuilder documentBuilder;
		 Document document;
		 
//		System.out.println(Response.asPrettyString());
		String NodeValue = null;
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(false); //optional; only if needed
		documentBuilderFactory.setValidating(false); //optional; only if needed
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		document = documentBuilder.parse(new InputSource(new StringReader(Response)));
//		"UserLoginResult"
		NodeList dependencyElements = document.getElementsByTagName(TagName);
		Node node = dependencyElements.item(index);
		
//		String Str = dependencyElements.item(0).getTextContent();
//		System.out.println(Str);

		if(node.getNodeType() == Node.ELEMENT_NODE){
			Element eElement = (Element) node;
//			System.out.println( "eElement Length" + eElement.getChildNodes().getLength());
//			System.out.println("eElement, getAttributes Length" +eElement.getAttributes().getLength());
			
			if(eElement.getChildNodes().getLength() > 0) {
				NodeValue = dependencyElements.item(index).getTextContent();
				Log4j.loginfo("Node Value is : " + NodeValue);
			}
			
			if(eElement.getAttributes().getLength() > 0) {
				if(eElement.getAttributes().getNamedItem(Attribute) != null){
					NodeValue = eElement.getAttributes().getNamedItem(Attribute).getNodeValue();
				 	//System.out.println(NodeValue);
					Log4j.loginfo("Node Value is : " + NodeValue);
				}
			}
		}
		
		return NodeValue;
		
	}

	
	public void updateXMLFile(String xmlFileName, String tagName, String attribute, String updateValue) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		String XMLFile = "C:\\Manoj\\ManojJMeter\\LMS\\ILoader\\Import-01-2021-12-21T144204-GT.xml";
		//String XMLFile = prop.getIloader_XML_File_Path() + "\\" + xmlFileName;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//API to obtain DOM Document instance
		DocumentBuilder builder = null;
		//Create DocumentBuilder with default configuration
		builder = factory.newDocumentBuilder();
		//Parse the content to Document object
		Document doc = builder.parse(new File(XMLFile));
		NodeList dependencyElements = doc.getElementsByTagName(tagName);
	

		for(int i=0;i<dependencyElements.getLength();i++)
		{
			Node node = dependencyElements.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE){
			 Element eElement = (Element) node;
			 eElement.getAttributes().getNamedItem(attribute).setTextContent(updateValue);
			 String HeaderId = eElement.getAttributes().getNamedItem(attribute).getNodeValue();
			 System.out.println(HeaderId);
			 TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         	 Transformer transformer = transformerFactory.newTransformer();
	         	 DOMSource source = new DOMSource(doc);
	         	 StreamResult consoleResult = new StreamResult(new File(XMLFile));
	         	 transformer.transform(source, consoleResult);
				}
		}
	}


	public static String msg5_Extract_Attr_Value_File(String folderName, String xmlFileName, String TagName, int index, String Attribute) throws SAXException, IOException, ParserConfigurationException {
		
		String NodeValue = null;
		
		try{
			
			File file = new File(folderName + "/" + xmlFileName); 
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setCoalescing(true);
			//API to obtain DOM Document instance
			DocumentBuilder builder = null;
			
			//Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();
			
			//Parse the content to Document object
			Document doc = builder.parse(file);
			NodeList dependencyElements = doc.getElementsByTagName(TagName);
//			String Str = dependencyElements.item(index).getNodeName();
//			System.out.println(Str);
			Node node = dependencyElements.item(index);

			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) node;			
				if(eElement.getElementsByTagName(Attribute).item(index) == null){
					Log4j.loginfo("Attribute " + Attribute + " retrived is null value");
				}else{
					NodeValue = eElement.getElementsByTagName(Attribute).item(index).getTextContent();	 	 
				}
			}
		}
		catch(Exception e){
			Log4j.error(e.getMessage());
		}
		return NodeValue;
	}
	
	public static String msg3_4_Extract_Attr_values(String folderName, String xmlFileName, String TagName, int index, String Attribute) throws SAXException, IOException, ParserConfigurationException {
		
		String NodeValue = null;
		
		try{
			
			File file = new File(folderName + "/" + xmlFileName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setCoalescing(true);
			
			//API to obtain DOM Document instance
			DocumentBuilder builder = null;
			
			//Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();
			
			//Parse the content to Document object
			Document doc = builder.parse(file);
	        NodeList dependencyElements = doc.getElementsByTagName(TagName);
//	        String Str = dependencyElements.item(index).getNodeName();
//	        System.out.println(Str);
	        Node node = dependencyElements.item(index);
	
	          if(node.getNodeType() == Node.ELEMENT_NODE){
                 Element eElement = (Element) node;
//                 log.info((eElement.getAttributes().getNamedItem(Attribute)).toString());
                 if(eElement.getAttributes().getNamedItem(Attribute) == null){
                    Log4j.loginfo("Attribute " + Attribute + " retrived is null value");
                 }else{
                    NodeValue = eElement.getAttributes().getNamedItem(Attribute).getNodeValue();
                 }
	          }
	          
		   }
		   catch(Exception e){
			   Log4j.error(e.getMessage());
		   }
		
		return NodeValue;
		
	}
}
