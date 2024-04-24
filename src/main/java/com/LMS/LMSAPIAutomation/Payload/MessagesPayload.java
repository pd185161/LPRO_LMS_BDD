/**
 *
 */
package com.LMS.LMSAPIAutomation.Payload;

import java.util.*;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.PayloadObjects.Messages.*;
import com.LMS.LMSAPIAutomation.RequestTemplates.Messages_CustomerTemplate;
import com.LMS.LMSAPIAutomation.RequestTemplates.Messages_DocumentTemplate;
import com.LMS.LMSAPIAutomation.RequestTemplates.Messages_RootTemplate;
import com.LMS.LMSAPIAutomation.Resources.Log4j;

/**
 * @author sa185402
 *
 */
public class MessagesPayload {


	static Random rnd = new Random();
//	parameters.get("clubId"))
//	public static String payload(String Payload,HashMap<String, String> parameters) {
//
//		HashMap<String, String> hs = new HashMap<String,String>();
//		hs.put("MSG3", MSG3(parameters.get("clubCardId"), parameters.get("retailerId"), parameters.get("accountId"), parameters.get("earnValue"), parameters.get("rdmValue"), parameters.get("openBalance"),parameters.get("ticketTotal")));
//		hs.put("MSG35", MSG35(parameters.get("clubCardId"),parameters.get("retailerId"), parameters.get("accountId")));
//		hs.put("MSG3_RegisterPromotion",MSG3_RegisterPromotion(parameters.get("clubCardId"),parameters.get("retailerId"), parameters.get("accountId"),parameters.get("promotionHeaderId"), parameters.get("earnValue"),parameters.get("rdmValue"), parameters.get("openBalance")));
//		hs.put("MSG34",MSG34(parameters.get("retailerId"),parameters.get("clubCardId"),parameters.get("promotionHeaderId"),parameters.get("clubId"),parameters.get("barcode"),Integer.parseInt(parameters.get("action")),parameters.get("documentID"), parameters.get("barcodeProgrammingId")));
//		hs.put("MSG11",MSG11(parameters.get("retailerId")));
//		hs.put("MSG1",MSG1(parameters.get("retailerId"),parameters.get("clubCardId")));
//		return hs.get(Payload);
//	}

//	public  String payload(String Payload,HashMap<String, String> parameters, io.cucumber.datatable.DataTable userTable){
	public static String payload(String Payload,HashMap<String, String> parameters,io.cucumber.datatable.DataTable userTable){

	List<Map<Object, Object>> att= userTable.asMaps(String.class,String.class);
	HashMap<String,String> data= new HashMap<String, String>();
	data.put("retailerId", parameters.get("retailerId"));
	data.put("clubCardId", parameters.get("clubCardId"));
	data.put("promotionHeaderId", parameters.get("promotionHeaderId"));
	data.put("clubId",parameters.get("clubId"));
	data.put("barcode", parameters.get("barcode"));
	data.put("documentID",parameters.get("documentID"));
	data.put("barcodeProgrammingId",  parameters.get("barcodeProgrammingId"));
	data.put("accountId",  parameters.get("accountId"));

		switch (Payload.toUpperCase()) {
			case "MSG1":
				//return MSG1(parameters.get("retailerId"),parameters.get("clubCardId"));
				data.put("QueryMode", att.get(0).get("QueryMode")+"");
				return MSG1(data);
			case "MSG3":
				data.put("earnValue",att.get(0).get("earnValue")+"");
				data.put("rdmValue", att.get(0).get("rdmValue")+"");
				data.put("openBalance", att.get(0).get("openBalance")+"");
				data.put("ticketTotal", att.get(0).get("TicketTotal")+"");
				return MSG3_1(data);
			case "MSG34":

				data.put("earnValue", att.get(0).get("earnValue")+"");
				data.put("rdmValue", att.get(0).get("rdmValue")+"");
				data.put("openBalance", att.get(0).get("openBalance")+"");

				data.put("action", att.get(0).get("Action")+"");
				data.put("storeid", att.get(0).get("Storeid")+"");
				data.put("ticketTotal", att.get(0).get("TicketTotal")+"");
				data.put("locked", att.get(0).get("locked")+"");

				return MSG34_1(data);
			case "MSG3_REGISTERPROMOTION":
				return MSG3_RegisterPromotion(parameters.get("clubCardId"),parameters.get("retailerId"), parameters.get("accountId"),parameters.get("promotionHeaderId"), parameters.get("earnValue"),parameters.get("rdmValue"), parameters.get("openBalance"),parameters.get("ticketTotal"));
			case "MSG7":
				return  MSG7(data);
			case "MSG15":
				return MSG15("","",parameters.get("retailerId"));
			case "MSG11":
//				return MSG11(parameters.get("retailerId"),parameters.get("mobilenumber"));
		}

		return "";

	}


	public  String MSG3(String clubCardId, String retailerId, String accountId, String earnValue, String rdmValue, String openBalance, String TicketTotal) {

		int transID_MSG3 = rnd.nextInt(99999);
//		GenericMethods.tempValues("transID_MSG3", transID_MSG3+"");

		return "<soap:Envelope xmlns:soap=\"http:www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http:tempuri.org/\">\r\n"
				+ "	<soap:Header/>\r\n"
				+ "	<soap:Body>\r\n"
				+ "		<tem:StoreData>\r\n"
				+ "			<!--Optional:-->\r\n"
				+ "			<tem:messageHandlerApplication>StoreInLoyalty</tem:messageHandlerApplication>\r\n"
				+ "			<!--Optional:-->\r\n"
				+ "			<tem:chain>"+retailerId+"</tem:chain>\r\n"
				+ "			<!--Optional:-->\r\n"
				+ "			<tem:branch>1</tem:branch>\r\n"
				+ "			<!--Optional:-->\r\n"
				+ "			<tem:messageData>"
				+ "			<![CDATA["
				+ "			<Root>"
				+ "				<Customer MsgType=\"3\" StoreID=\"1\" PosID=\"1\" TransID=\""+transID_MSG3+"\" BusinessDate=\""+GenericMethods.currentDate()+"\" "
				+ "					LPEVer=\"5.0.0.0.1\" CashierID=\"1\" StartDateTime=\""+GenericMethods.currentDate()+"T07:18:56\" TicketTotal=\""+TicketTotal+"\" HomeStore=\"0\" "
				+ "					FileSource=\"1\">"
				+ "					<LoyaltyInfo CardID=\""+clubCardId+"\" ServerDate=\""+GenericMethods.currentDate()+"\">"
				+ "						<Accounts>"
				+ "							<Acc ID=\""+accountId+"\" EarnValue=\""+earnValue+"\" RdmValue=\""+rdmValue+"\" OpenBalance=\""+openBalance+"\"/>"
				+ "						</Accounts>"
				+ "					</LoyaltyInfo>"
				+ "				</Customer>"
				+ "			</Root>]]>"
				+ "			</tem:messageData>\r\n"
				+ "		</tem:StoreData>\r\n"
				+ "	</soap:Body>\r\n"
				+ "</soap:Envelope>\r\n"
				+ "";
	}

	public  String MSG35(String clubCardId, String retailerId, String accountId) {

		int transID_MSG35 = rnd.nextInt(99999);
//		GenericMethods.tempValues("transID_MSG35", transID_MSG35+"");

		return "<soapenv:Envelope xmlns:soapenv=\"http:schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http:tempuri.org/\">\r\n"
				+ "  <soapenv:Header/>\r\n"
				+ "  <soapenv:Body>\r\n"
				+ "    <tem:StoreData>\r\n"
				+ "      <tem:messageHandlerApplication>StoreInLoyalty</tem:messageHandlerApplication>\r\n"
				+ "      <tem:chain>"+retailerId+"</tem:chain>\r\n"
				+ "      <tem:branch>1</tem:branch>\r\n"
				+ "      <tem:messageData><![CDATA[<Root xmlns:xsi=\"http:www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "	<Customer MsgType=\"3\" LPEVer=\"5.0.0.0.1\" StoreID=\"1\" PosID=\"8\" TransID=\""+transID_MSG35+"\" BusinessDate=\""+GenericMethods.currentDate()+"\" StartDateTime=\""+GenericMethods.currentDate()+"T13:41:22.000\" CashierID=\"10\" TicketTotal=\"100.00\" HomeStore=\"0\" FileSource=\"1\">\r\n"
				+ "		<LoyaltyInfo CardID=\""+clubCardId+"\" ServerDate=\""+GenericMethods.currentDate()+"\" MemberEffectiveDate=\""+GenericMethods.currentDate()+"T10:37:00\">\r\n"
				+ "			<Accounts>\r\n"
				+ "				<Acc ID=\""+accountId+"\" EarnValue=\"40.000000\"/>\r\n"
				+ "			</Accounts>\r\n"
				+ "		</LoyaltyInfo>\r\n"
				+ "	</Customer>\r\n"
				+ "	<biztalk_1>\r\n"
				+ "		<body>\r\n"
				+ "			<ActiveStore_SalesTransaction_1.70>\r\n"
				+ "				<StoreID>1</StoreID>\r\n"
				+ "				<BusinessDate>2022-03-03</BusinessDate>\r\n"
				+ "				<ServerDate/>\r\n"
				+ "				<TransactionNumber>"+transID_MSG35+"</TransactionNumber>\r\n"
				+ "				<ExternalReferenceId>"+transID_MSG35+"</ExternalReferenceId>\r\n"
				+ "				<TillID>8</TillID>\r\n"
				+ "				<StartDateTime>"+GenericMethods.currentDate()+"T13:41:22.000</StartDateTime>\r\n"
				+ "				<EndDateTime>"+GenericMethods.currentDate()+"T13:41:23.000</EndDateTime>\r\n"
				+ "				<CashierID>10</CashierID>\r\n"
				+ "				<OfflineFlag>0</OfflineFlag>\r\n"
				+ "				<RetroactiveFlag>0</RetroactiveFlag>\r\n"
				+ "				<Total>\r\n"
				+ "					<TotalAmount>100.00</TotalAmount>\r\n"
				+ "				</Total>\r\n"
				+ "				<TransactionDetail>\r\n"
				+ "					<SequenceNumber>1</SequenceNumber>\r\n"
				+ "					<TransactionDetailGroup>\r\n"
				+ "						<TransactionDetailLine>\r\n"
				+ "							<TimingType>3</TimingType>\r\n"
				+ "							<DeferredID>0</DeferredID>\r\n"
				+ "							<Amount>0.000000</Amount>\r\n"
				+ "							<PromotionID>3816</PromotionID>\r\n"
				+ "							<MarkdownItemID>4560</MarkdownItemID>\r\n"
				+ "							<MarkdownDepartmentID>2</MarkdownDepartmentID>\r\n"
				+ "							<AllocatedQty>2</AllocatedQty>\r\n"
				+ "							<TriggeredQty>2</TriggeredQty>\r\n"
				+ "							<RedeemedQty>1</RedeemedQty>\r\n"
				+ "							<TransactionDetailLineTax>\r\n"
				+ "								<TaxId>1</TaxId>\r\n"
				+ "								<TaxAmount>0</TaxAmount>\r\n"
				+ "								<TaxableAmount>0</TaxableAmount>\r\n"
				+ "								<TaxPercent>0</TaxPercent>\r\n"
				+ "							</TransactionDetailLineTax>\r\n"
				+ "						</TransactionDetailLine>\r\n"
				+ "					</TransactionDetailGroup>\r\n"
				+ "				</TransactionDetail>\r\n"
				+ "\r\n"
				+ "				<TransactionText>\r\n"
				+ "					<TextData>test</TextData>\r\n"
				+ "					<TextAttributes><Line ID=\"21\" Enlarge=\"0\" Cut=\"1\"/>\r\n"
				+ "					</TextAttributes>\r\n"
				+ "				</TransactionText>\r\n"
				+ "				<CustomerInfo Rejected=\"0\">\r\n"
				+ "					<ClubCard>"+clubCardId+"</ClubCard>\r\n"
				+ "					<ClubId>0</ClubId>\r\n"
				+ "				</CustomerInfo>\r\n"
				+ "			</ActiveStore_SalesTransaction_1.70>\r\n"
				+ "		</body>\r\n"
				+ "	</biztalk_1>\r\n"
				+ "</Root>\r\n"
				+ "]]>\r\n"
				+ "</tem:messageData>\r\n"
				+ "    </tem:StoreData>\r\n"
				+ "  </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}

	public static String MSG3_RegisterPromotion(String clubCardId, String retailerId, String accountId, String promotionHeaderId, String earnValue, String rdmValue, String openBalance, String ticketTotal) {

		int transID_MSG3_RegisterPromotion = rnd.nextInt(99999);
//		GenericMethods.tempValues("transID_MSG3_RegisterPromotion", transID_MSG3_RegisterPromotion+"");

		return "<soapenv:Envelope xmlns:soapenv=\"http:schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http:tempuri.org/\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <tem:StoreData>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <tem:messageHandlerApplication>StoreInLoyalty</tem:messageHandlerApplication>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <tem:chain>"+retailerId+"</tem:chain>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <tem:branch>1</tem:branch>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <tem:messageData><![CDATA[<Root>\r\n"
				+ "    <Customer MsgType=\"3\" StoreID=\"0\" PosID=\"1\" TransID=\""+transID_MSG3_RegisterPromotion+"\" BusinessDate=\""+GenericMethods.currentDate()+"\" LPEVer=\"5.0.0.0.1\" CashierID=\"1\" StartDateTime=\"2022-10-14T07:18:56\" TicketTotal=\"100.00\" HomeStore=\"0\" FileSource=\"1\">\r\n"
				+ "        <LoyaltyInfo CardID=\""+clubCardId+"\" ServerDate=\""+GenericMethods.currentDate()+"\">\r\n"
				+ "            <Accounts>\r\n"
				+ "                <Acc ID=\""+accountId+"\" EarnValue=\""+earnValue+"\" RdmValue=\""+rdmValue+"\" OpenBalance=\""+openBalance+"\"/>\r\n"
				+ "            </Accounts>\r\n"
				+ "            <RegisteredPromotions>\r\n"
				+ "                <RegisteredPromotion PromotionHeaderId=\""+promotionHeaderId+"\" NumOfRedemptions=\"1\"/>\r\n"
				+ "            </RegisteredPromotions>\r\n"
				+ "        </LoyaltyInfo>\r\n"
				+ "    </Customer>\r\n"
				+ "</Root>\r\n"
				+ "]]></tem:messageData>\r\n"
				+ "      </tem:StoreData>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
	}

	public  String MSG34(String retailerId, String clubCardId, String promotionHeaderId, String clubId, String Barcode, int action, String documentID, String BarcodeProgrammingId){
		int transID_MSG34 = rnd.nextInt(99999);

		return "<soap:Envelope xmlns:soap=\"http:www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http:tempuri.org/\">\n" +
				"\t<soap:Header/>\n" +
				"\t<soap:Body>\n" +
				"\t\t<tem:StoreData>\n" +
				"\t\t\t<!--Optional:-->\n" +
				"\t\t\t<tem:messageHandlerApplication>StoreInLoyalty</tem:messageHandlerApplication>\n" +
				"\t\t\t<!--Optional:-->\n" +
				"\t\t\t<tem:chain>"+retailerId+"</tem:chain>\n" +
				"\t\t\t<!--Optional:-->\n" +
				"\t\t\t<tem:branch>1</tem:branch>\n" +
				"\t\t\t<!--Optional:-->\n" +
				"\t\t\t<tem:messageData><![CDATA[\n" +
				"\t\t\t\t\t\t\t<Root xmlns:xsi=\"http:www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"file:/C:/Manoj/XSDS/UploadOnline.xsd\">\n" +
				"\t<Customer MsgType=\"3\" LPEVer=\"33\" StoreID=\"1\" PosID=\"1\" TransID=\""+transID_MSG34+"\" BusinessDate=\""+GenericMethods.currentDate()+"\" StartDateTime=\""+GenericMethods.currentDate()+"T14:43:27.000\" CashierID=\"1\" TicketTotal=\"21.05\" HomeStore=\"0\" FileSource=\"1\" Locked=\"0\">\n" +
				"\t\t<LoyaltyInfo CardID=\""+clubCardId+"\" ServerDate=\""+GenericMethods.currentDate()+"\" MemberEffectiveDate=\""+ GenericMethods.currentDate() +"T15:14:29.362\">\n" +
				"\t\t\t\t\t\t\t\t</LoyaltyInfo>\n" +
				"\t</Customer>\n" +
				"\t<Documents MsgType=\"4\" LPEVer=\"33\" StoreID=\"1\" BusinessDate=\""+GenericMethods.currentDate()+"\" TillID=\"161\" TransactionNumber=\""+transID_MSG34+"\" ExternalReferenceId=\"11\" StartDateTime=\"2023-01-03T14:43:27.000\" EndDateTime=\"2023-01-03T14:45:34.000\" CashierID=\"1\" TicketTotal=\"21.05\">\n" +
				"\t\t<CustomerInfo>\n" +
				"\t\t\t<ClubCard>"+clubCardId+"</ClubCard>\n" +
				"\t\t\t<HomeStore>0</HomeStore>\n" +
				"\t\t\t<ClubId>"+clubId+"</ClubId>\n" +
				"\t\t</CustomerInfo>\n" +
				"\t\t<Document>\n" +
				"\t\t\t<Details>\n" +
				"\t\t\t\t<!-- <DocumentInternalKey>2689</DocumentInternalKey> -->\n" +
				"\t\t\t\t<Action>"+action+"</Action>\n" +
				"\t\t\t\t<Barcode>"+Barcode+"</Barcode>\n" +
				"\t\t\t\t<ID>"+documentID+"</ID>\n" +
				"\t\t\t\t<BarcodeProgrammingId>"+BarcodeProgrammingId+"</BarcodeProgrammingId>\n" +
				"\t\t\t\t<IsUnique>1</IsUnique>\n" +
				"\t\t\t\t<UniqueID>"+Barcode+"</UniqueID>\n" +
				"\t\t\t\t<TriggerPromotionId>"+promotionHeaderId+"</TriggerPromotionId>\n" +
				"\t\t\t\t<Value>200.00</Value>\n" +
				"\t\t\t\t<Qty>1</Qty>\n" +
				"\t\t\t\t<TenderGroup>2</TenderGroup>\n" +
				"\t\t\t\t<Type>3</Type>\n" +
				"\t\t\t</Details>\n" +
				"\t\t\t<ValidationRules>\n" +
				"\t\t\t\t<StartDate>2018-11-27</StartDate>\n" +
				"\t\t\t\t<EndDate>2056-12-11</EndDate>\n" +
				"\t\t\t\t<RedemptionType>2</RedemptionType>\n" +
				"\t\t\t\t<RedemptionLocation>2</RedemptionLocation>\n" +
				"\t\t\t\t<RedemptionMode>1</RedemptionMode>\n" +
				"\t\t\t</ValidationRules>\n" +
				"\t\t</Document>\n" +
				"\t</Documents>\n" +
				"</Root>]]></tem:messageData>\n" +
				"\t\t</tem:StoreData>\n" +
				"\t</soap:Body>\n" +
				"</soap:Envelope>\n";
	}

//	public  String MSG11(String retailerId,String mobilephonenumbervalue) {
//
//		try {
//
//		ArrayList<Attribute> att = new ArrayList<Attribute>();
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
//        Attribute req_att =   new Attribute(null,"MobilePhoneNumber",mobilephonenumbervalue);
//        Request req = new Request("AND",req_att);
//        Response res = new Response("0","50",att);
//        Search search = new Search(req,res);
//
//		"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID","clientID",
//			 "TicketIdentifier","startDateTime","cashierID","ticketTotal","homeStore","fileSource","locked",
//			 "xsi_noNamespaceSchemaLocation","xmlns_xsi","search"
//		Customer cust = new Customer(rnd.nextInt(99999)+"","V44","11",
//				null,GenericMethods.currentDate(),"1","1",null,
//				null,null,null,null,null,
//				null,null,null,"HQL_Messaging_MSG11.xsd",
//				"http:www.w3.org/2001/XMLSchema-instance",search,null);
//
//		Messages_CustomerTemplate msg = new Messages_CustomerTemplate();
//		return msg.requestTemplate(cust,retailerId);
//
//		}catch (Exception e){
//
//			Log4j.loginfo(e.getMessage());
//		}
//
//		return "";
//	}

	public static String MSG1(HashMap<String,String> data){

		try {
//			"cardID","serverDate","memberEffectiveDate","cardIDType","identifier","linkUnlinkIdentifier","action","homeStore","documents","accounts","registeredPromotions"
			LoyaltyInfo loyaltyInfo = new LoyaltyInfo(data.get("clubCardId"),null,null,"P",null,null,null,null,null,null,null);

//			//"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID","clientID","TicketIdentifier","startDateTime","cashierID","ticketTotal","homeStore","fileSource","QueryMode",locked","xsi_noNamespaceSchemaLocation","xmlns_xsi","search"
			Customer cust = new Customer(rnd.nextInt(99999)+"",null,"1",null,GenericMethods.currentDate(),"1422","1",null,"12345",GenericMethods.currentDate(),null,null,null,null,data.get("QueryMode"), null,"file:///C:/Products/Tags/10.4.0/Loyalty/Design/HQL_Messaging_MSG1.xsd","http://www.w3.org/2001/XMLSchema-instance",null,loyaltyInfo);

			Messages_CustomerTemplate msg = new Messages_CustomerTemplate();
			return msg.requestTemplate(cust,data.get("retailerId"));

		}catch (Exception e){
			Log4j.loginfo(e.getMessage());
		}

		return "";
	}

	public static String MSG7(HashMap<String,String> data){

		try {

			Document document = new Document(data.get("barcode"),"3","0","0",null,null);

//			"cardID","serverDate","memberEffectiveDate","cardIDType","identifier","linkUnlinkIdentifier","action",
//			 "homeStore","documents","accounts","registeredPromotions"
			LoyaltyInfo loyaltyInfo = new LoyaltyInfo(data.get("clubCardId"),null,null,
					"P",null,null,null,"0",document,
					null,null);

//			"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID",
//			 "clientID","TicketIdentifier","startDateTime","cashierID","ticketTotal","homeStore",
//			 "fileSource","QueryMode","locked","xsi_noNamespaceSchemaLocation","xmlns_xsi","search"
			Customer cust = new Customer(rnd.nextInt(99999)+"",null,"7",
					null,GenericMethods.currentDate(),null,"1","1",null,
					null,null,null,null,null,
					null,null,null,null,null,
					loyaltyInfo);

			Messages_DocumentTemplate msg = new Messages_DocumentTemplate();
			return msg.requestTemplate(cust,data.get("retailerId"));

		}catch (Exception e){
			Log4j.loginfo(e.getMessage());
		}

		return "";

	}

	public  static String MSG15(String identifier,String linkUnlinkIdentifier, String retailerId){

		try {

//			"cardID","serverDate","memberEffectiveDate","cardIDType","identifier","linkUnlinkIdentifier","action","homeStore","documents","accounts","registeredPromotions"
			LoyaltyInfo loyaltyInfo = new LoyaltyInfo(null,null,null,null,identifier,linkUnlinkIdentifier,"1",null,null,null,null);
//			"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID","clientID","TicketIdentifier","startDateTime","cashierID","ticketTotal","homeStore","fileSource","locked","xsi_noNamespaceSchemaLocation","xmlns_xsi","search"
			Customer cust = new Customer(rnd.nextInt(99999)+"",null,"15",retailerId,GenericMethods.currentDate(),null,"228","1","0",null,null,null,null,null,null,null,null,null,null,loyaltyInfo);

			Messages_CustomerTemplate msg = new Messages_CustomerTemplate();
			return msg.requestTemplate(cust,retailerId);

		}catch (Exception e){
			Log4j.loginfo(e.getMessage());
		}

		return "";

	}

	public  static String MSG34_1(HashMap<String, String> data){
		try {
			Accounts accounts = new Accounts(null,new Account(data.get("accountId"),null,data.get("earnValue"),data.get("rdmValue"),data.get("openBalance"),null,null));

//			"cardID","serverDate","memberEffectiveDate","cardIDType","identifier","linkUnlinkIdentifier","action","homeStore","documents","accounts","registeredPromotions"
			LoyaltyInfo loyaltyInfo = new LoyaltyInfo(data.get("clubCardId"),GenericMethods.currentDate(),
											GenericMethods.currentDate()+"T00:00:00.000",null,null,
											null,null,null,null,accounts,null);

//			"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID","clientID","TicketIdentifier",
//					 "startDateTime","cashierID","ticketTotal","homeStore","fileSource","locked","xsi_noNamespaceSchemaLocation","xmlns_xsi","search"
			Customer cust = new Customer(rnd.nextInt(99999)+"","33","3",data.get("retailerId"),GenericMethods.currentDate(),
					"1",data.get("storeid"),null,"0",GenericMethods.currentDate()+"T00:00:00.000",
					"1",data.get("ticketTotal"),"0","1",null,data.get("locked"),null,null,
					null,loyaltyInfo);

//			"clubCard","homeStore","clubId"
			CustomerInfo customerInfo = new CustomerInfo(data.get("clubCardId"),"0",data.get("clubId"));

//			"action","barcode","iD","barcodeProgrammingId","isUnique","uniqueID","triggerPromotionId","value","qty","tenderGroup","type"
			Details details = new Details(data.get("action")+"",data.get("barcode"),data.get("documentID"),data.get("barcodeProgrammingId"),"1",
					data.get("barcode"),data.get("promotionHeaderId"),"200.00","1","2","3");

//			"startDate","endDate","redemptionType","redemptionLocation","redemptionMode"
			ValidationRules validationRules = new ValidationRules(GenericMethods.currentDate(),GenericMethods.currentDate(),"2",
													"2","1");

//			"documentBarcode","documentType","redemptionType","isUnique","details","validationRules"
			Document document = new Document(null,null,null,null,details,validationRules);

//			"msgType","lPEVer","storeID","businessDate","tillID","transactionNumber","externalReferenceId",
//					 "startDateTime","endDateTime","cashierID","ticketTotal","customerInfo","document"
			Documents documents = new Documents("4","33",data.get("storeid"),GenericMethods.currentDate(),"161",
											rnd.nextInt(99999)+"",rnd.nextInt(99)+"",
											GenericMethods.currentDate()+"T00:00:00.000",GenericMethods.currentDate()+"T00:00:00.000",
											"1","21.05",customerInfo,document);

			Root root = new Root("http:www.w3.org/2001/XMLSchema-instance","file:/C:/Manoj/XSDS/UploadOnline.xsd",cust,documents);
			Messages_RootTemplate msg = new Messages_RootTemplate();

			return msg.requestTemplate(root,data.get("retailerId"));

		}catch (Exception e){
			Log4j.loginfo(e.getMessage());
		}

		return "";
	}

	public static String MSG3_1(HashMap<String, String> data){

		try {
//			"ID","earnValue","rdmValue","openBalance"
			Account acc = new Account(data.get("accountId"),null,data.get("earnValue"),data.get("rdmValue"),data.get("openBalance"),null,null);
			Accounts accounts = new Accounts(acc,null);

//			"cardID","serverDate","memberEffectiveDate","cardIDType","identifier","linkUnlinkIdentifier","action","homeStore","documents","accounts","registeredPromotions"
			LoyaltyInfo loyaltyInfo = new LoyaltyInfo(data.get("clubCardId"),GenericMethods.currentDate(),
											GenericMethods.currentDate()+"T00:00:00.000",null,null,
											null,"1",null,null,accounts,null);

//			"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID","clientID","TicketIdentifier",
//					 "startDateTime","cashierID","ticketTotal","homeStore","fileSource","locked","xsi_noNamespaceSchemaLocation","xmlns_xsi","search"
			Customer cust = new Customer(rnd.nextInt(99999)+"","33","3",data.get("retailerId"),GenericMethods.currentDate(),
					"1","1","1","0",GenericMethods.currentDate()+"T00:00:00.000",
					"1",data.get("ticketTotal"),"0","1",null,"0",null,null,
					null,loyaltyInfo);

			Root root = new Root("http:www.w3.org/2001/XMLSchema-instance","file:/C:/Manoj/XSDS/UploadOnline.xsd",cust,null);
			Messages_RootTemplate msg = new Messages_RootTemplate();

			return msg.requestTemplate(root,data.get("retailerId"));

		}catch (Exception e){
			Log4j.loginfo(e.getMessage());
		}
		return "";
	}

//	public  String MSG3_RegisterPromotion(String clubCardId, String retailerId, String accountId, String promotionHeaderId,String earnValue, String rdmValue, String openBalance,String ticketTotal){
//
//		try {
//			"ID","earnValue","rdmValue","openBalance"
//			Accounts accounts = new Accounts(new Account(accountId,earnValue,rdmValue,openBalance));
//
//			RegisteredPromotions registeredPromotions = new RegisteredPromotions(new RegisteredPromotion(promotionHeaderId,"1"));
//
//			"cardID","serverDate","memberEffectiveDate","cardIDType","identifier","linkUnlinkIdentifier","action","homeStore","documents","accounts","registeredPromotions"
//			LoyaltyInfo loyaltyInfo = new LoyaltyInfo(clubCardId,GenericMethods.currentDate(),
//											GenericMethods.currentDate()+"T00:00:00.000",null,null,
//											null,"1",null,null,accounts,registeredPromotions);
//
//			"transID","LPEVer","msgType","retailerId","businessDate","posID","storeID","clientID","TicketIdentifier",
//					 "startDateTime","cashierID","ticketTotal","homeStore","fileSource","locked","xsi_noNamespaceSchemaLocation","xmlns_xsi","search"
//			Customer cust = new Customer(rnd.nextInt(99999)+"","33","3",retailerId,GenericMethods.currentDate(),
//					"1","1","1","0",GenericMethods.currentDate()+"T00:00:00.000",
//					"1",ticketTotal,"0","1",null,"0",null,null,
//					null,loyaltyInfo);
//
//			Root root = new Root("http:www.w3.org/2001/XMLSchema-instance","file:/C:/Manoj/XSDS/UploadOnline.xsd",cust,null);
//			Messages_RootTemplate msg = new Messages_RootTemplate();
//
//			return msg.requestTemplate(root,retailerId);
//
//		}catch (Exception e){
//			Log4j.loginfo(e.getMessage());
//		}
//		return "";
//	}


}
