package com.LMS.LMSAPIAutomation.BusinessScenarios;

import com.LMS.LMSAPIAutomation.Resources.Log4j;
import org.testng.Assert;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;


public class ImportXMLHandling {

    public static String extractNode(String path, String exp){

        try {
            Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path));

            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression xPathExpression = xPath.compile(exp);
            NodeList nodes = (NodeList) xPathExpression.evaluate(xmlDocument, XPathConstants.NODESET);

            Document newXmlDocument = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().newDocument();
            newXmlDocument.setXmlStandalone(true);

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                Node copyNode = newXmlDocument.importNode(node, true);
                newXmlDocument.appendChild(copyNode);
            }

            return domSource(newXmlDocument);

        }catch (Exception ignored){
             Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }

        return "";
    }

    public static String appendNode(String filePath, String xml, String nodeName, String node) {

        try{
            String extractNode = extractNode(filePath, node);
            extractNode = extractNode.replaceAll("<\\?xml(.+?)\\?>", "").trim();
            StringBuilder both = new StringBuilder(xml).insert(xml.indexOf("</"+nodeName+"s>"),"\n")
                .insert(xml.indexOf("</"+nodeName+"s>"),extractNode);
            return both.toString();

        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }
        return "";
    }

    public static String removeNode(String xml, String tagName) {

        try{
            Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            Element element = (Element) xmlDocument.getElementsByTagName(tagName).item(0);
            Node prevElem = element.getPreviousSibling();
            if (prevElem != null &&
                    prevElem .getNodeType() == Node.TEXT_NODE &&
                    prevElem .getNodeValue().trim().length() == 0) {
                    element.getParentNode().removeChild(prevElem );
            }

            element.getParentNode().removeChild( element );

            return domSource(xmlDocument);

        }catch (ParserConfigurationException|IOException|SAXException ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }

        return "";
    }

    public static void createXmlFile(String fileName, String xml) {

        try {
            Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            xmlDocument.setXmlStandalone(true);
            FileOutputStream output = new FileOutputStream(fileName);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        DOMSource source = new DOMSource(xmlDocument);
	        StreamResult result = new StreamResult(output);
	        transformer.transform(source, result);
	        output.close();

        }catch (IOException |ParserConfigurationException|SAXException| TransformerException ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }

    }

    public static String updateAttributeValues(String xml, int index, String tagName, String attributeName, String attributeValue) {

        try {
            Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            //Element element = (Element) xmlDocument.getElementsByTagName(tagName).item(index);

            /*=========Pravat[30-05-2023]: Edited to add new element for index>0====*/
            Element element;
            if(!(xmlDocument.getElementsByTagName(tagName).getLength()>=(index+1)))
            {
                element = (Element) xmlDocument.getElementsByTagName(tagName).item(0);
                Element element1 = (Element)element.cloneNode(true);
                element.getParentNode().appendChild(element1);
            }
                element = (Element) xmlDocument.getElementsByTagName(tagName).item(index);
            /*=====================================================================*/

                   NamedNodeMap namedNodeMap = element.getAttributes();
                   Node valueNode = namedNodeMap.getNamedItem(attributeName);
                   valueNode.setNodeValue(attributeValue);

                   if(attributeName.equalsIgnoreCase("PromotionHeaderId") && attributeValue.equalsIgnoreCase("NA"))
                   {
                       valueNode.setNodeValue(Resources.parameters.get("promotionExternalId"));
                   }

                   return domSource(xmlDocument);

        }catch (ParserConfigurationException | IOException | SAXException ignored ){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }

        return xml;
    }

    public static String removeAttribute(String xml, String tagName, String attributeName, int index){

        try {
            Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            Element element = (Element) xmlDocument.getElementsByTagName(tagName).item(index);
            boolean hasAttribute = element.hasAttribute(attributeName);
            if(hasAttribute){
                element.removeAttribute(attributeName);
            }else {
                Log4j.Warn(attributeName+ " Attribute is not available in the Element "+tagName);
            }

            return domSource(xmlDocument);

        }catch (IOException| ParserConfigurationException| SAXException|RuntimeException ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }

        return "";
    }

    public static String domSource(Document xmlDocument) {

        StringWriter sw = new StringWriter();

        try {

            DOMSource domSource = new DOMSource(xmlDocument);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            return sw.toString();

        }catch (TransformerException ignored){

            Log4j.error(ignored.getMessage());
        }

        return sw.toString();
    }


    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {


        Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("./resources/#SampleCustomerImport.xml"));
            Element element = (Element) xmlDocument.getElementsByTagName("Attribute").item(0);
            boolean hasAttribute = element.hasAttribute("Id");
            if(hasAttribute){
                System.out.println(element.getAttribute("Id"));
                element.removeAttribute("Id");
                System.out.println(element.getAttribute("Id"));
            }


            DOMSource domSource = new DOMSource(xmlDocument);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw.toString());



    }

}
