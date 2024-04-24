package com.LMS.LMSAPIAutomation.PayloadObjects;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Test {

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException {
//        JAXBContext contextObj = JAXBContext.newInstance(Customer.class);
//        Marshaller marshallerObj = contextObj.createMarshaller();
//
//        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//
//
//        ArrayList<Attribute> att = new ArrayList<Attribute>();
//        att.add(new Attribute(null,"ClubCardID",null));
//        att.add(new Attribute(null,"FirstName",null));
//        att.add(new Attribute(null,"LastName",null));
//        att.add(new Attribute(null,"BirthDate",null));
//        att.add(new Attribute(null,"City",null));
//        att.add(new Attribute(null,"Country",null));
//        att.add(new Attribute(null,"MobilePhoneNumber",null));
//        att.add(new Attribute(null,"PhonePrefix",null));
//        att.add(new Attribute(null,"PostalCode",null));
//        att.add(new Attribute(null,"State",null));
//        att.add(new Attribute(null,"Street1",null));
//
//
//        Attribute req_att =   new Attribute(null,"MobilePhoneNumber",null);
//        Request req = new Request("AND",req_att);
//        Response res = new Response("0","50",att);
//        Search search = new Search(req,res);
//        Customer cust = new Customer("1344433","V44","11","2018-06-27","1","1","HQL_Messaging_MSG11.xsd","http://www.w3.org/2001/XMLSchema-instance",search);


//        StringWriter stringWriter = new StringWriter();
//        stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//        marshallerObj.marshal(cust, stringWriter );
//        stringWriter.toString();
//        System.out.println(stringWriter.toString());

//        MessageTemplates msg = new MessageTemplates();
//        System.out.println(msg.requestTemplate(cust,"1"));
    }


}
