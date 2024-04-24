/**
 * 
 */
package com.LMS.LMSAPIAutomation.Payload;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;

import java.util.HashMap;

/**
 * @author er185127
 *
 */
public class SaveLoyaltyDocumentsPayload {
	
public static String payload(String Payload,HashMap<String, String> parameters) {
		
		HashMap<String, String> hs = new HashMap<String,String>();
		hs.put("DeactivateCentralizedDocument", getDeactivateCentralizedDocument(parameters.get("barcode"),parameters.get("clubCardId")));
		hs.put("GetMessageLegend", getGetMessageLegend());
		hs.put("ValidateCentralizedDocument", getValidateCentralizedDocument(parameters.get("barcode"),parameters.get("clubCardId")));
		hs.put("SaveMessageTemplate", saveMessageTemplate());
		hs.put("SaveVoucherBarcode", getSaveVoucherBarcode(parameters.get("voucherBarcode")));
		hs.put("SaveDocument", saveDocument(parameters.get("barcodeProgrammingId"),parameters.get("messageTemplateId")));
		hs.put("GetIssuingOrRedemptionDocuments", getGetIssuingOrRedemptionDocuments());
		
		return hs.get(Payload);
	}
	
	public static String getDeactivateCentralizedDocument(String barcode, String clubCardId) {

		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "    <hql:DeactivateCentralizedDocument>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:in_DocumentsXML><![CDATA[<DeactivateCentralizedDocument Action=\"1\">\r\n"
				+ "	<Retailer Id=\"2\">\r\n"
				+ "		<Documents>\r\n"
				+ "			<SameHouseHoldDocument Barcode=\""+barcode+"\" ClubCardId=\""+clubCardId+"\" Qty=\"1\"/>\r\n"
				+ "		</Documents>\r\n"
				+ "	</Retailer>\r\n"
				+ "</DeactivateCentralizedDocument>\r\n"
				+ "]]></hql:in_DocumentsXML>\r\n"
				+ "      </hql:DeactivateCentralizedDocument>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}

	public static String getGetMessageLegend() {

		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "   <!--Return all messages from the media center-->\r\n"
				+ "      <hql:GetMessageLegend/>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}

	public static String getValidateCentralizedDocument(String barcode, String clubCardId) {

		return "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <ValidateCentralizedDocument xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "    <!--validate and select the coupon-->\r\n"
				+ "      <in_DocumentsXML><![CDATA[<DeactivateCentralizedDocument Action=\"1\">\r\n"
				+ "	<Retailer Id=\"2\">\r\n"
				+ "		<Documents>\r\n"
				+ "			<SameHouseHoldDocument Barcode=\""+barcode+"\" ClubCardId=\""+clubCardId+"\" Qty=\"200\"/>\r\n"
				+ "		</Documents>\r\n"
				+ "	</Retailer>\r\n"
				+ "</DeactivateCentralizedDocument>]]></in_DocumentsXML>\r\n"
				+ "    </ValidateCentralizedDocument>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}

	public static String saveMessageTemplate() {
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
				"<soapenv:Header/>\n" +
				" <soapenv:Body>\n" +
				"   <hql:SaveMessageTemplate>\n" +
				"		<!--Optional:-->\n" +
				"			<!--Create new media center:-->\n" +
				"				<hql:messageXml><![CDATA[<Document  GroupBy=\"2\" Name=\"Automation\" Description=\"Customer Target Message\" DocumentType=\"5\" DeviceType=\"13\" Layout=\"Portrait\">\n" +
				"<Control ToolType=\"TextEditor_Rows\" ToolId=\"TicketLevel\">\n" +
				"                              <Data>\n" +
				"                                             <Language Id=\"0\">\n" +
				"                                                            <Row Font=\"1\" FontSize=\"56\" Align=\"Left\">\n" +
				"                                                                           <Field FieldId=\"Date\" Format=\"x|yyyy-MM-dd\"/>\n" +
				"                                                            </Row>\n" +
				"                                             </Language>\n" +
				"                              </Data>\n" +
				"               </Control>\n" +
				"</Document>\n" +
				"]]></hql:messageXml>\n" +
				"	</hql:SaveMessageTemplate>\n" +
				"	</soapenv:Body>\n" +
				"</soapenv:Envelope>\n";
	}

	public static String getSaveVoucherBarcode(String voucherBarcode) {
		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:SaveVoucherBarcode>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:barcodeName>WS_Automation</hql:barcodeName>\r\n"
				+ "         <hql:documentGroup>1</hql:documentGroup>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:value>"+voucherBarcode+"</hql:value>\r\n"
				+ "         <hql:action>1</hql:action>\r\n"
				+ "      </hql:SaveVoucherBarcode>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}

	public static String saveDocument(String barcodeId, String messageTemplateId) {
		int barcodeId_int = 0,messageTemplateId_int=0;

		if(!(barcodeId == null && messageTemplateId == null )){
			barcodeId_int = Integer.parseInt(barcodeId);
			messageTemplateId_int = Integer.parseInt(messageTemplateId);
		}

		String DocumentName = "Doc_Automation_"+ GenericMethods.currentDate();

		return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <hql:SaveDocument>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:DocumentName>"+DocumentName+"</hql:DocumentName>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:DocumentDescription>"+DocumentName+"</hql:DocumentDescription>\r\n"
				+ "         <hql:BarcodeTemplateId>"+barcodeId_int+"</hql:BarcodeTemplateId>\r\n"
				+ "         <hql:MessageTemplateId>"+messageTemplateId_int+"</hql:MessageTemplateId>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <hql:BusinessId>1</hql:BusinessId>\r\n"
				+ "         <hql:Action>1</hql:Action>\r\n"
				+ "      </hql:SaveDocument>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}


	public static String getGetIssuingOrRedemptionDocuments(){
	return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hql=\"http://www.retalix.com/HQLWebServices\">\n" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <hql:GetIssuingOrRedemptionDocuments>\n" +
			"         <GetDocumentRequest DocumentsRetrievePurpose=\"0\" DocumentType=\"5\" IssuingSource=\"0\" RewardDefinition=\"0\" LastUpdate=\""+GenericMethods.currentDate()+"\"/>\n" +
			"      </hql:GetIssuingOrRedemptionDocuments>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>";
	}

}