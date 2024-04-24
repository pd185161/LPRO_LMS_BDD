/**
 * 
 */
package com.LMS.LMSAPIAutomation.Payload;

import java.util.HashMap;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;

/**
 * @author sa185402
 *
 */
public class MemberPromotionsPayload {
	
	public static String payload(String Payload,HashMap<String, String> parameters) {
		
		HashMap<String, String> hs = new HashMap<String,String>();
		hs.put("GetPromotionsHouseHoldRegisteredFor", getPromotionsHouseHoldRegisteredFor(parameters.get("clubCardId")));
		hs.put("GetRegisterToPromotionHistory", getRegisterToPromotionHistory(parameters.get("clubCardId")));
		hs.put("GetRegisterToPromotionHistoryByExternalId", getRegisterToPromotionHistoryByExternalId(parameters.get("clubCardId"),parameters.get("promotionExternalId")));
		hs.put("GetRegisterToPromotionHistoryByInternalId", getRegisterToPromotionHistoryByInternalId(parameters.get("clubCardId"),parameters.get("promotionHeaderId")));
		hs.put("Host_GetMemberEligiblePromotions", host_GetMemberEligiblePromotions(parameters.get("clubCardId")));
		hs.put("Mp_GetMemberEligiblePromotions", mp_GetMemberEligiblePromotions(parameters.get("clubCardId"), parameters.get("clubId")));
		hs.put("RegisterToExternalPromotion", registerToExternalPromotion(parameters.get("clubCardId"), parameters.get("promotionExternalId")));
		hs.put("RegisterToExternalPromotionWithDatesRange", registerToExternalPromotionWithDatesRange(parameters.get("clubCardId"),parameters.get("promotionExternalId")));
		hs.put("RegisterToPromotion", registerToPromotion(parameters.get("clubCardId"),parameters.get("promotionHeaderId")));
		hs.put("RegisterToPromotionWithDatesRange", registerToPromotionWithDatesRange(parameters.get("clubCardId"),parameters.get("promotionHeaderId")));
		hs.put("UnregisterFromExternalPromotion", unregisterFromExternalPromotion(parameters.get("clubCardId"),parameters.get("promotionExternalId")));
		hs.put("UnregisterFromPromotion", unregisterFromPromotion(parameters.get("clubCardId"),parameters.get("promotionHeaderId")));
		
		return hs.get(Payload);
	}
	
	public static String getPromotionsHouseHoldRegisteredFor(String clubCardId) {
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:GetPromotionsHouseHoldRegisteredFor>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:clubCardId>"+clubCardId+"</hql:clubCardId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "      </hql:GetPromotionsHouseHoldRegisteredFor>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}
	
	public static String getRegisterToPromotionHistory(String clubCardId) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <GetRegisterToPromotionHistory xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "    </GetRegisterToPromotionHistory>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String getRegisterToPromotionHistoryByExternalId(String clubCardId, String promotionExternalKey) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <GetRegisterToPromotionHistoryByExternalId xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionExternalKey>"+promotionExternalKey+"</promotionExternalKey>\r\n"
				+ "    </GetRegisterToPromotionHistoryByExternalId>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String getRegisterToPromotionHistoryByInternalId(String clubCardId, String promotionInternalKey) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <GetRegisterToPromotionHistoryByInternalId xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionInternalKey>"+promotionInternalKey+"</promotionInternalKey>\r\n"
				+ "    </GetRegisterToPromotionHistoryByInternalId>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String host_GetMemberEligiblePromotions(String clubCardId) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <Host_GetMemberEligiblePromotions xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "    </Host_GetMemberEligiblePromotions>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String mp_GetMemberEligiblePromotions(String clubCardId, String clubId) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <Mp_GetMemberEligiblePromotions xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <clubId>"+clubId+"</clubId>\r\n"
				+ "    </Mp_GetMemberEligiblePromotions>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String registerToExternalPromotion(String clubCardId, String promotionExternalReference) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <RegisterToExternalPromotion xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionExternalReference>"+promotionExternalReference+"</promotionExternalReference>\r\n"
				+ "    </RegisterToExternalPromotion>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String registerToExternalPromotionWithDatesRange(String clubCardId, String promotionExternalReference) {
//		2021-08-13
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <RegisterToExternalPromotionWithDatesRange xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionExternalReference>"+promotionExternalReference+"</promotionExternalReference>\r\n"
				+ "      <registrationStartDate>"+GenericMethods.currentDate()+"</registrationStartDate>\r\n"
				+ "      <registrationEndDate>2056-12-30</registrationEndDate>\r\n"
				+ "    </RegisterToExternalPromotionWithDatesRange>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String registerToPromotion(String clubCardId, String promotionHeaderId) {
		
		return "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <RegisterToPromotion xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionHeaderId>"+promotionHeaderId+"</promotionHeaderId>\r\n"
				+ "    </RegisterToPromotion>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String registerToPromotionWithDatesRange(String clubCardId, String promotionHeaderId) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <RegisterToPromotionWithDatesRange xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionHeaderId>"+promotionHeaderId+"</promotionHeaderId>\r\n"
				+ "      <registrationStartDate>"+GenericMethods.currentDate()+"</registrationStartDate>\r\n"
				+ "      <registrationEndDate>2056-12-30</registrationEndDate>\r\n"
				+ "    </RegisterToPromotionWithDatesRange>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String unregisterFromExternalPromotion(String clubCardId, String promotionExternalReference) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <UnregisterFromExternalPromotion xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionExternalReference>"+promotionExternalReference+"</promotionExternalReference>\r\n"
				+ "    </UnregisterFromExternalPromotion>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	
	public static String unregisterFromPromotion(String clubCardId, String promotionHeaderId) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <UnregisterFromPromotion xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <clubCardId>"+clubCardId+"</clubCardId>\r\n"
				+ "      <promotionHeaderId>"+promotionHeaderId+"</promotionHeaderId>\r\n"
				+ "    </UnregisterFromPromotion>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}

}
