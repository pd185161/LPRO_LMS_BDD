/**
 * 
 */
package com.LMS.LMSAPIAutomation.Payload;

import java.util.HashMap;
import java.util.Properties;

import com.LMS.LMSAPIAutomation.Resources.ReadProperties;

/**
 * @author sa185402
 *
 */
public class LoginPayload {
	static ReadProperties prop = new ReadProperties();
	
	public static String Payload(String Payload) {
		
		HashMap<String, String> hs = new HashMap<String,String>();
		hs.put("UserLogin", Login(prop.App_UserName(),prop.App_Password()));
		hs.put("UserLogin1", Login1());
		hs.put("ManagerLogin", Login(prop.Manager_UserName(),prop.Manager_Password()));
		
		return hs.get(Payload);
	}

	public static String Login(String userName, String password) {
		return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <UserLogin xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <in_UserName>"+userName+"</in_UserName>\r\n"
				+ "      <in_Password>"+password+"</in_Password>\r\n"
				+ "    </UserLogin>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}

	public static String Login1() {
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\\r\n" +
				"   <soapenv:Header/>\\r\n" +
				"   <soapenv:Body>\\r\n" +
				"      <hql:UserLogin>\\r\n" +
				"         <!--Optional:-->\\r\n" +
				"         <hql:in_UserName>hq1</hql:in_UserName>\\r\n" +
				"         <!--Optional:-->\\r\n" +
				"         <hql:in_Password>Abcd1234</hql:in_Password>\\r\n" +
				"      </hql:UserLogin>\\r\n" +
				"   </soapenv:Body>\\r\n" +
				"</soapenv:Envelope>";
	}
	
	
}
