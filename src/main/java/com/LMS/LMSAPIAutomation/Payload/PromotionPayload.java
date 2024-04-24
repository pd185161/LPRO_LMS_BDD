/**
 * 
 */
package com.LMS.LMSAPIAutomation.Payload;

import java.util.HashMap;
import java.util.Random;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;

/**
 * @author sa185402
 *
 */
public class PromotionPayload {
	

	public String payload(String Payload,HashMap<String, String> parameters) {
		
		HashMap<String, String> hs = new HashMap<String,String>();
		hs.put("CopyAndSuspendFromSource", copyAndSuspendFromSource(parameters.get("retailerId"), parameters.get("promotionHeaderId")));
		hs.put("CopyPromotion", copyPromotion(parameters.get("retailerId"),parameters.get("promotionHeaderId")));
		//hs.put("CopyPromotion", copyPromotion("1","1"));
		hs.put("GetPromotionByExternalId", getPromotionByExternalId(parameters.get("retailerId"),parameters.get("promotionExternalId")));
		hs.put("GetPromotionByInternalId", getPromotionByInternalId(parameters.get("retailerId"),parameters.get("promotionHeaderId")));
		hs.put("GetPromotionsByStoreId", getPromotionsByStoreId(parameters.get("retailerId")));
		hs.put("UpdatePromotion", updatePromotion(parameters.get("retailerId"), parameters.get("promotionHeaderId")));
		hs.put("UpdateStoreParticipation", updateStoreParticipation(parameters.get("retailerId"), parameters.get("promotionHeaderId")));
			
		return hs.get(Payload);
	}

	public String copyAndSuspendFromSource(String retailerId, String promotionHeaderId) {
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:CopyAndSuspendFromSource>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:PromotionInputXml><![CDATA[<HQL_Promotion_WS>\r\n"
				+ "	<Retailer Id=\""+retailerId+"\">\r\n"
				+ "		<Promotion InternalPromotionId=\""+promotionHeaderId+"\">\r\n"
				+ "			<PromotionDetails HeaderDescription=\"CopynSuspended_"+promotionHeaderId+"\"/>\r\n"
				+ "			<Distribution>\r\n"
				+ "				<Store Id=\"1\" StartDate=\""+GenericMethods.currentDate()+"T16:00:00\" EndDate=\"2056-12-30T23:59:00\" UseTimeZone=\"1\"/>\r\n"
				+ "			</Distribution>\r\n"
				+ "			<PromotionsLevels>\r\n"
				+ "				<PromotionsLevel Id=\"1\">\r\n"
				+ "					<Reward RewardValue=\"8.61\"/>\r\n"
				+ "				</PromotionsLevel>\r\n"
				+ "			</PromotionsLevels>\r\n"
				+ "		</Promotion>\r\n"
				+ "	</Retailer>\r\n"
				+ "</HQL_Promotion_WS>]]></hql:PromotionInputXml>\r\n"
				+ "      </hql:CopyAndSuspendFromSource>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}
	
	public String copyPromotion(String retailerId, String promotionHeaderId) {
//		Automation copy of
//		Automation copy of 6456
//		InternalPromotionId
//		Random rnd = new Random();
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:CopyPromotion>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:PromotionInputXml><![CDATA[<HQL_Promotion_WS>\r\n"
				+ "	<Retailer Id=\""+retailerId+"\">\r\n"
				+ "		<Promotion  PromotionHeaderId=\""+promotionHeaderId+"\">\r\n"
				+ "			<PromotionDetails HeaderDescription= Automation copy of \""+promotionHeaderId+"\" PromotionReceiptDescription=\"Copy\"/>\r\n"
				+ "		</Promotion>\r\n"
				+ "	</Retailer>\r\n"
				+ "</HQL_Promotion_WS>]]></hql:PromotionInputXml>\r\n"
				+ "      </hql:CopyPromotion>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		
	}
	
	public String getPromotionByExternalId(String retailerId, String promotionExternalId) {
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:GetPromotionByExternalId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:retailerId>"+retailerId+"</hql:retailerId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:promotions>\r\n"
				+ "            <!--Zero or more repetitions:-->\r\n"
				+ "            <hql:string>"+promotionExternalId+"</hql:string>\r\n"
				+ "         </hql:promotions>\r\n"
				+ "      </hql:GetPromotionByExternalId>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}
	
	public String getPromotionByInternalId(String retailerId, String promotionHeaderId) {
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:GetPromotionByInternalId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:retailerId>"+retailerId+"</hql:retailerId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:promotions>\r\n"
				+ "            <!--Zero or more repetitions:-->\r\n"
				+ "            <hql:int>"+promotionHeaderId+"</hql:int>\r\n"
				+ "         </hql:promotions>\r\n"
				+ "      </hql:GetPromotionByInternalId>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}
	
	public String getPromotionsByStoreId(String retailerId) {
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:GetPromotionsByStoreId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:retailerId>"+retailerId+"</hql:retailerId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:storeId>0</hql:storeId>\r\n"
				+ "      </hql:GetPromotionsByStoreId>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}
	
	public String updatePromotion(String retailerId, String promotionHeaderId) {
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:UpdatePromotion>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:PromotionInputXml><![CDATA[<HQL_Promotion_WS xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"file:///C:/Users/pd185161/OneDrive%20-%20NCR%20Corporation/Desktop/LP/XML%20schemas/HQL_Promotion_WS.xsd\">\r\n"
				+ "			<Retailer Id=\""+retailerId+"\">\r\n"
				+ "				<Promotion InternalPromotionId=\""+promotionHeaderId+"\">\r\n"
				+ "					<PromotionDetails HeaderDescription=\"Automation Update Promotion\"/>\r\n"
				+ "					<Distribution>\r\n"
				+ "						<Store Id=\"0\" StartDate=\""+GenericMethods.currentDate()+"T16:00:00\" EndDate=\"2056-12-30T23:59:00\" UseTimeZone=\"1\"/>\r\n"
				+ "					</Distribution>\r\n"
				+ "					<PromotionsLevels>\r\n"
				+ "					<PromotionsLevel Id=\"1\">\r\n"
				+ "					<Reward  RewardValue=\"8.00\"/>\r\n"
				+ "					</PromotionsLevel>\r\n"
				+ "					</PromotionsLevels>\r\n"
				+ "				</Promotion>\r\n"
				+ "			</Retailer>\r\n"
				+ "		</HQL_Promotion_WS>]]></hql:PromotionInputXml>\r\n"
				+ "      </hql:UpdatePromotion>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}
	
	public String updateStoreParticipation(String retailerId, String promotionHeaderId) {
		
		int newPromotionid = Integer.parseInt(promotionHeaderId) +1;
		int newPromotionid1 = Integer.parseInt(promotionHeaderId) +2;
		
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:UpdateStoreParticipation>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:RetailerID>"+retailerId+"</hql:RetailerID>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:StoreID>1</hql:StoreID>\r\n"
				+ "         <hql:InternalPromotionIdToSuspendStore>"+newPromotionid+"</hql:InternalPromotionIdToSuspendStore>\r\n"
				+ "         <hql:InternalPromotionIdToUnSuspendStore>"+newPromotionid1+"</hql:InternalPromotionIdToUnSuspendStore>\r\n"
				+ "      </hql:UpdateStoreParticipation>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		
	}

}
