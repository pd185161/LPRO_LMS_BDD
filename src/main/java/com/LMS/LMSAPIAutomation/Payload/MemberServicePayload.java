/**
 * 
 */
package com.LMS.LMSAPIAutomation.Payload;

import java.io.StringWriter;
import java.util.*;

import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.PayloadObjects.Messages.*;
import com.LMS.LMSAPIAutomation.RequestTemplates.Documentbuilder;
import com.LMS.LMSAPIAutomation.Resources.Log4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.transform.TransformerException;

/**
 * @author sa185402
 *
 */
public class MemberServicePayload {

	static Random rnd = new Random();
	public static final int memberCardId_SameHH = rnd.nextInt(99999999);
	public static final int memberCardId_SameHH1 = rnd.nextInt(99999999);
	public static final int memberCardId_diffHH = rnd.nextInt(99999999);

	public String payload(String Payload,HashMap<String, String> parameters,io.cucumber.datatable.DataTable userTable) throws JAXBException, TransformerException {
		List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

		switch (Payload.toUpperCase()){

			case "SAVEDEMOGRAPHICS":
				return saveDemographics(parameters,att);

			case "GETDEMOGRAPHIC":
				return getDemographics(parameters,att);

			case "CARDVALIDATE":
				return cardValidate(parameters,att);

			case "GETADDRESSBYZIPCODE":
				return getAddressByZipCode(parameters,att);

			case "GETSEGMENTS":
				return getSegments();

			case "PENDINGPOINTADJUSTMENTREQUEST":
				return pendingPointAdjustmentRequest(parameters,att);

			case "REGISTERPROMOTION":
				return registerPromotion(parameters,att);

			case "GETMEMBERPROMOTION":
				return getMemberPromotion(parameters);

			case "REGISTERPROMOTIONWITHDATERANGE":
				return registerPromotionWithDateRange(parameters,att);

			case "UNREGISTERPROMOTION":
				return unregisterPromotion(parameters,att);

			case "SAVENOTES":
				return SaveNotes(parameters,att);

			case "SEGMENTSMOVEMENTS":
				return segmentsMovements(parameters,att);

			case "ADJUSTMEMBERACCOUNT":
				return adjustMemberAccount(parameters,att);

			case "MERGEHOUSEHOLD":
				return mergeHouseHold(parameters,att);

			case "CHANGEHOUSEHOLDHEAD":
				return changeHouseHoldHead(parameters,att);

			case "UNMERGEHOUSEHOLD":
				return unmergeHouseHolds(parameters,att);

			case "GETACCUMULATEDACCOUNTS":
				return getAccumulatedAccounts();

			case "GETCONTINUITYPROMOTIONDETAILEDTRANSACTION":
				return getContinuityPromotionDetailedTransactions(parameters,att);

			case "GETFILTEREDMEMBERACCOUNTDETAILEDTRANSACTIONS":
				return getFilteredMemberAccountDetailedTransactions(parameters,att);

			case "GETHHNOTES":
				return getHHNotes(parameters,att);

			case "GETHOUSEHOLDACCOUNTSACTIVITY":
				return getHouseHoldAccountsActivity(parameters,att);

			case "GETHOUSEHOLDCONTINUITYPROMOTIONSACTIVITY":
				return getHouseHoldContinuityPromotionsActivity(parameters,att);

			case "GETHOUSEHOLDFILTEREDTRANSACTIONS":
				return getHouseHoldFilteredTransactions(parameters,att);

			case "GETHOUSEHOLDMEMBERS":
				return getHouseHoldMembers(parameters,att);

			case "GETHOUSEHOLDMEMBERSDEMOGRAPHICS":
				return getHouseHoldMembersDemographic(parameters,att);

			case "GETHOUSEHOLDTRANSACTIONS":
				return getHouseHoldTransactions(parameters,att);

			case "GETMEMBERACCOUNTDETAILEDPENDINGTRANSACTIONS":
				return getMemberAccountDetailedPendingTransactions(parameters,att);

			case "GETMEMBERACCOUNTDETAILEDTRANSACTIONS":
				return getMemberAccountDetailedTransactions(parameters,att);

			case "GETMEMBERACCOUNTSACTIVITY":
				return getMemberAccountsActivity(parameters,att);

			case "GETMEMBERACCOUNTSSUMMARY":
				return getMemberAccountsSummary(parameters,att);

			case "MS_COMBOS_GET":
				return MS_Combos_Get();

			case "UPDATEEXTERNALS":
				return updateExternals(parameters,att);

			case "GETTRANSACTIONIMAGE":
				return getTransactionImage(parameters,att);

			case "SAVEMEMBERCARDS":
				return saveMemberCards(parameters,att);

			case "MERGEMEMBERSDIFFHH":
				return mergerMembersDiffHH(att);

		}

		return "";

	}

	public static String saveDemographics(HashMap<String, String> parameters,List<Map<Object, Object>> att)  {

		String clubCardId ="";
		String segmentInternalKey = parameters.get("segmentInternalKey");
		String segmentId = parameters.get("segmentId");
		String segmentDescription = parameters.get("segmentDescription");

		if(!(att.get(0).get("ClubCard")+"").equalsIgnoreCase("NewCard")){
			 clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = parameters.get("newclubCardId");
		}

		String numeric =  "0123456789";
		StringBuilder mobilenumber = new StringBuilder(10);

		for ( int i=0; i<10; i++) {
			//generating a random number using math.random()
			int ch = (int)(numeric.length() * Math.random());
			//adding Random character one by one at the end of s
			mobilenumber.append(numeric.charAt(ch));
		}

		Resources.parameters.put("mobilenumber",mobilenumber+"");

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> SaveDemographic = new HashMap<String, String>();
			childElementTag1.put("hql:SaveDemographic", SaveDemographic);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("hql:in_ClubCardId",clubCardId);

			Segment segment = new Segment();
			segment.setId(segmentId);
			segment.setSegmentInternalKey(segmentInternalKey);
			segment.setDescription(segmentDescription);

			MemberSegments memberSegments = new MemberSegments();
			memberSegments.setSegment(segment);

			Card card = new Card();
			card.setId(clubCardId);

			Cards cards = new Cards();
			cards.setCard(card);

			MemberAttributes memberAttributes = new MemberAttributes(new Attribute());
			MemberAdditionalAddress memberAdditionalAddress = new MemberAdditionalAddress();
			PrivacySettings privacySettings = new PrivacySettings();


			Member member = new Member();
			member.setMemberInternalKey("0");
			member.setMemberExternalId(clubCardId);
			member.setMobilePhoneNumber(mobilenumber+"");
			member.setCards(cards);
			member.setMemberAttributes(memberAttributes);
			member.setMemberAdditionalAddress(memberAdditionalAddress);
			member.setPrivacySettings(privacySettings);
			member.setMemberSegments(memberSegments);

			Members members = new Members();
			members.setMember(member);

			HouseHold houseHold = new HouseHold();
			houseHold.setHouseHoldExternalId(clubCardId);
			houseHold.setMembers(members);


			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(HouseHold.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(houseHold, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"hql:in_HouseHold",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException | JAXBException e) {
			Log4j.error(e.getMessage());
		}

		return "";

	}
	public static String getDemographics(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

			String clubCardId = "";

			if (att.get(0).get("Errorcode").toString().equalsIgnoreCase("NA")){
					clubCardId = parameters.get("clubCardId");
			}else {
					clubCardId = att.get(0).get("ClubCard")+"";
			}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetDemographic = new HashMap<String, String>();
			childElementTag1.put("hql:GetDemographic", GetDemographic);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("hql:in_ClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";

	}
	public static String adjustMemberAccount(HashMap<String, String> parameters,List<Map<Object, Object>> att){

		String clubCardId;
		String accountId;
		String earnValue = att.get(0).get("EarnValue")+"";
		String initialValue = att.get(0).get("InitialValue")+"";

		if((att.get(0).get("ClubCardID")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("ClubCardID")+"";
		}

		if ((att.get(0).get("AccountID")+"").equalsIgnoreCase("NA")){
			accountId = parameters.get("accountId");
		}else {
			accountId = att.get(0).get("AccountID")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema/");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			childElementTag.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> AdjustMemberAccount = new HashMap<String, String>();
			AdjustMemberAccount.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("AdjustMemberAccount", AdjustMemberAccount);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("in_ClubCardId",clubCardId);

			Account account = new Account();
			account.setIds(accountId);
//					setId(accountId);
			account.setEarnValue(earnValue);
			account.setInitialValue(initialValue);
//			Account acc =  new Account(accountId, earnValue, null, null, null, null, null);
			Accounts accounts = new Accounts(account,null);
			accounts.setAccount(account);

			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(Accounts.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(accounts, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"in_Accounts",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		} catch (PropertyException e) {
			Log4j.error(e.getMessage());
		} catch (JAXBException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String changeHouseHoldHead(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String currentHeadClubCardId;
		String newHeadClubCardId;

		if((att.get(0).get("currentHeadClubCardId")+"").equalsIgnoreCase("NA") || (att.get(0).get("currentHeadClubCardId")+"").equalsIgnoreCase("source")){
			currentHeadClubCardId = parameters.get("newclubCardId");
		}else {
			currentHeadClubCardId = att.get(0).get("currentHeadClubCardId")+"";
		}

		if((att.get(0).get("newHeadClubCardId")+"").equalsIgnoreCase("NA")){
			newHeadClubCardId = parameters.get("clubCardId");
		}else if((att.get(0).get("newHeadClubCardId")+"").equalsIgnoreCase("source")) {
			newHeadClubCardId = parameters.get("newclubCardId");
		}else {
			newHeadClubCardId = att.get(0).get("newHeadClubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> ChangeHouseHoldHead = new HashMap<String, String>();
			childElementTag1.put("hql:ChangeHouseHoldHead", ChangeHouseHoldHead);

			HashMap<String,String> in_CurrentHeadClubCardId = new HashMap<String,String>();
			in_CurrentHeadClubCardId.put("hql:in_CurrentHeadClubCardId",currentHeadClubCardId);

			HashMap<String,String> in_NewHeadClubCardId = new HashMap<String,String>();
			in_NewHeadClubCardId.put("hql:in_NewHeadClubCardId",newHeadClubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_CurrentHeadClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_NewHeadClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getAccumulatedAccounts() {

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetAccumulatedAccounts = new HashMap<String, String>();
			childElementTag1.put("hql:GetAccumulatedAccounts", GetAccumulatedAccounts);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getContinuityPromotionDetailedTransactions(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;
		String promotionHeaderID;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		if ((att.get(0).get("promotionHeaderID")+"").equalsIgnoreCase("NA")){
			promotionHeaderID = parameters.get("promotionHeaderId");
		}else {
			promotionHeaderID = att.get(0).get("promotionHeaderID")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetContinuityPromotionDetailedTransactions = new HashMap<String, String>();
			childElementTag1.put("hql:GetContinuityPromotionDetailedTransactions", GetContinuityPromotionDetailedTransactions);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			HashMap<String,String> in_PromotionHeaderId = new HashMap<String,String>();
			in_PromotionHeaderId.put("hql:in_PromotionHeaderId",promotionHeaderID);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_PromotionHeaderId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getFilteredMemberAccountDetailedTransactions(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;
		String accountId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")) {
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		if((att.get(0).get("accountId")+"").equalsIgnoreCase("NA")){
			accountId = parameters.get("accountId");
		}else {
			accountId = att.get(0).get("accountId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetFilteredMemberAccountDetailedTransactions = new HashMap<String, String>();
			GetFilteredMemberAccountDetailedTransactions.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("GetFilteredMemberAccountDetailedTransactions", GetFilteredMemberAccountDetailedTransactions);

			Member member = new Member();
			member.setMemberInternalKey(null);
			member.setMemberExternalId(null);
			member.setIsMainMember(null);
			member.setLastName(null);
			member.setFirstName(null);
			member.setAdditionalFirstName(null);
			member.setAdditionalLastName(null);
			member.setMiddleInitial(null);
			member.setDriversLicense(null);
			member.setNationalInsuranceNumber(null);
			member.setRemarks(null);
			member.setMobilePhoneNumber(null);
			member.setWorkPhoneNumber(null);
			member.setGender(null);
			member.setPassport(null);
			member.setStartDate(null);
			member.setRedemptionPrivileges(null);
			member.setLanguageId(null);
			member.setPostOption(null);
			member.setNumberOfFamilyMembers(null);
			member.setAnonimity(null);
			member.setSpouseLastName(null);
			member.setMemberStatus(null);
			member.setReceiptLayoutId(null);
			member.setAdressNormalizationUpdate(null);
			member.setUpdatedDate(null);
			member.setCompanyName(null);
			member.setEMailAddress(null);
			member.setProcessingPrevention(null);
			member.setReceiptType(null);

			member.setClubCardId(clubCardId);

			Account account = new Account();
			account.setIds(accountId);

			member.setAccount(account);

			FilteredAccountDetailedTransactions filteredAccountDetailedTransactions = new FilteredAccountDetailedTransactions();
			filteredAccountDetailedTransactions.setMember(member);

			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(FilteredAccountDetailedTransactions.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(filteredAccountDetailedTransactions, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"in_parametersAsXml",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException | JAXBException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getHHNotes(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;
		String retailerId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		if((att.get(0).get("retailerId")+"").equalsIgnoreCase("NA")){
			retailerId = parameters.get("retailerId");
		}else {
			retailerId = att.get(0).get("retailerId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetHHNotes = new HashMap<String, String>();
			childElementTag1.put("hql:GetHHNotes", GetHHNotes);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			HashMap<String,String> in_RetailerId = new HashMap<String,String>();
			in_RetailerId.put("hql:in_RetailerId",retailerId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_RetailerId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getHouseHoldAccountsActivity(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetHouseHoldAccountsActivity = new HashMap<String, String>();
			childElementTag1.put("hql:GetHouseHoldAccountsActivity", GetHouseHoldAccountsActivity);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getHouseHoldContinuityPromotionsActivity(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetHouseHoldContinuityPromotionsActivity = new HashMap<String, String>();
			childElementTag1.put("hql:GetHouseHoldContinuityPromotionsActivity", GetHouseHoldContinuityPromotionsActivity);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getHouseHoldFilteredTransactions(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema/");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			childElementTag.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetHouseHoldFilteredTransactions = new HashMap<String, String>();
			GetHouseHoldFilteredTransactions.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("GetHouseHoldFilteredTransactions", GetHouseHoldFilteredTransactions);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("in_ClubCardId",clubCardId);

			ArrayList<Pos> posesArrayList = new ArrayList<Pos>();
			posesArrayList.add(new Pos("1"));
			posesArrayList.add(new Pos("6"));

			Poses poses = new Poses();
			poses.setPos(posesArrayList);

			Filter filter = new Filter();
			filter.setMaxRows("10000");
			filter.setManualTransactionsOnly("1");
			filter.setPosTransactionsOnly("0");
			filter.setStartDate(GenericMethods.currentDate());
			filter.setEndDate(GenericMethods.currentDate());
			filter.setPoses(poses);

			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(Filter.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(filter, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"xmlFilter",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		} catch (PropertyException e) {
			Log4j.error(e.getMessage());
		} catch (JAXBException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getHouseHoldMembers(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetHouseHoldMembers = new HashMap<String, String>();
			childElementTag1.put("hql:GetHouseHoldMembers", GetHouseHoldMembers);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}
		return "";
	}
	public static String mergeHouseHold(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String sourceMemberClubCardId;
		String targetMemberClubCardId;

		if((att.get(0).get("SourceMemberClubCardId")+"").equalsIgnoreCase("NA") || (att.get(0).get("SourceMemberClubCardId")+"").equalsIgnoreCase("source")){
			sourceMemberClubCardId = parameters.get("clubCardId");
		}else {
			sourceMemberClubCardId = att.get(0).get("SourceMemberClubCardId")+"";
		}

		if((att.get(0).get("TargetMemberClubCardId")+"").equalsIgnoreCase("NA")){
			targetMemberClubCardId = parameters.get("newclubCardId");
		}else if((att.get(0).get("TargetMemberClubCardId")+"").equalsIgnoreCase("source")) {
			targetMemberClubCardId = parameters.get("clubCardId");
		}else {
			targetMemberClubCardId = att.get(0).get("TargetMemberClubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> MergeHouseHold = new HashMap<String, String>();
			childElementTag1.put("hql:MergeHouseHold", MergeHouseHold);

			HashMap<String,String> in_SourceMemberClubCardId = new HashMap<String,String>();
			in_SourceMemberClubCardId.put("hql:in_SourceMemberClubCardId",sourceMemberClubCardId);

			HashMap<String,String> in_TargetMemberClubCardId = new HashMap<String,String>();
			in_TargetMemberClubCardId.put("hql:in_TargetMemberClubCardId",targetMemberClubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_SourceMemberClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_TargetMemberClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getHouseHoldMembersDemographic(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetHouseHoldMembersDemographic = new HashMap<String, String>();
			GetHouseHoldMembersDemographic.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("GetHouseHoldMembersDemographic", GetHouseHoldMembersDemographic);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("clubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getHouseHoldTransactions(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetHouseHoldTransactions = new HashMap<String, String>();
			childElementTag1.put("hql:GetHouseHoldTransactions", GetHouseHoldTransactions);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getMemberAccountDetailedPendingTransactions(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;
		String accountId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		if((att.get(0).get("accountId")+"").equalsIgnoreCase("NA")){
			accountId = parameters.get("accountId");
		}else {
			accountId = att.get(0).get("accountId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetMemberAccountDetailedPendingTransactions = new HashMap<String, String>();
			childElementTag1.put("hql:GetMemberAccountDetailedPendingTransactions", GetMemberAccountDetailedPendingTransactions);

			HashMap<String,String> Hql_accountId = new HashMap<String,String>();
			Hql_accountId.put("hql:accountId",accountId);

			HashMap<String,String> cardId = new HashMap<String,String>();
			cardId.put("hql:cardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,Hql_accountId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,cardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getMemberAccountDetailedTransactions(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;
		String accountId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		if((att.get(0).get("accountId")+"").equalsIgnoreCase("NA")){
			accountId = parameters.get("accountId");
		}else {
			accountId = att.get(0).get("accountId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetMemberAccountDetailedTransactions = new HashMap<String, String>();
			childElementTag1.put("hql:GetMemberAccountDetailedTransactions", GetMemberAccountDetailedTransactions);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			HashMap<String,String> in_AccountId = new HashMap<String,String>();
			in_AccountId.put("hql:in_AccountId",accountId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_AccountId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}
		return "";
	}
	public static String getMemberAccountsActivity(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetMemberAccountsActivity = new HashMap<String, String>();
			childElementTag1.put("hql:GetMemberAccountsActivity", GetMemberAccountsActivity);

			HashMap<String,String> in_ClubCardId = new HashMap<String,String>();
			in_ClubCardId.put("hql:in_ClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_ClubCardId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String getMemberAccountsSummary(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;
		String accountId;

		if((att.get(0).get("clubCardId")+"").equalsIgnoreCase("NA")) {
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("clubCardId")+"";
		}

		if((att.get(0).get("accountId")+"").equalsIgnoreCase("NA")){
			accountId = parameters.get("accountId");
		}else {
			accountId = att.get(0).get("accountId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetMemberAccountsSummary = new HashMap<String, String>();
			GetMemberAccountsSummary.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("GetMemberAccountsSummary", GetMemberAccountsSummary);

			Member member = new Member();
			member.setMemberInternalKey(null);
			member.setMemberExternalId(null);
			member.setIsMainMember(null);
			member.setLastName(null);
			member.setFirstName(null);
			member.setAdditionalFirstName(null);
			member.setAdditionalLastName(null);
			member.setMiddleInitial(null);
			member.setDriversLicense(null);
			member.setNationalInsuranceNumber(null);
			member.setRemarks(null);
			member.setMobilePhoneNumber(null);
			member.setWorkPhoneNumber(null);
			member.setGender(null);
			member.setPassport(null);
			member.setStartDate(null);
			member.setRedemptionPrivileges(null);
			member.setLanguageId(null);
			member.setPostOption(null);
			member.setNumberOfFamilyMembers(null);
			member.setAnonimity(null);
			member.setSpouseLastName(null);
			member.setMemberStatus(null);
			member.setReceiptLayoutId(null);
			member.setAdressNormalizationUpdate(null);
			member.setUpdatedDate(null);
			member.setCompanyName(null);
			member.setEMailAddress(null);
			member.setProcessingPrevention(null);
			member.setReceiptType(null);

			member.setClubCardId(clubCardId);

			Account account = new Account();
			account.setIds(accountId);

			member.setAccount(account);

			AccountsSummary accountsSummary = new AccountsSummary();
			accountsSummary.setMember(member);


			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(AccountsSummary.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(accountsSummary, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"in_SearchCriteriaXML",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		} catch (PropertyException e) {
			Log4j.error(e.getMessage());
		} catch (JAXBException e) {
			Log4j.error(e.getMessage());
		}
		return "";
	}
	public static String unmergeHouseHolds(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String houseHoldHeadClubCardId;
		String unmargeMemberClubCardId;
		String memberAccounts = "";

		if((att.get(0).get("HouseHoldHeadClubCardId")+"").equalsIgnoreCase("NA") || (att.get(0).get("HouseHoldHeadClubCardId")+"").equalsIgnoreCase("source")){
			houseHoldHeadClubCardId = parameters.get("clubCardId");
		}else {
			houseHoldHeadClubCardId = att.get(0).get("HouseHoldHeadClubCardId")+"";
		}

		if((att.get(0).get("UnmargeMemberClubCardId")+"").equalsIgnoreCase("NA")){
			unmargeMemberClubCardId = parameters.get("newclubCardId");
		}else if((att.get(0).get("UnmargeMemberClubCardId")+"").equalsIgnoreCase("source")){
			unmargeMemberClubCardId = parameters.get("clubCardId");
		}else {
			unmargeMemberClubCardId = att.get(0).get("UnmargeMemberClubCardId")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> UnmergeHouseHold = new HashMap<String, String>();
			UnmergeHouseHold.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("UnmergeHouseHold", UnmergeHouseHold);

			HashMap<String,String> in_HouseHoldHeadClubCardId = new HashMap<String,String>();
			in_HouseHoldHeadClubCardId.put("in_HouseHoldHeadClubCardId",houseHoldHeadClubCardId);

			HashMap<String,String> in_UnmargeMemberClubCardId = new HashMap<String,String>();
			in_UnmargeMemberClubCardId.put("in_UnmargeMemberClubCardId",unmargeMemberClubCardId);

			HashMap<String,String> in_UnmergeMemberAcconts = new HashMap<String,String>();
			in_UnmergeMemberAcconts.put("in_UnmergeMemberAcconts",memberAccounts);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_HouseHoldHeadClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_UnmargeMemberClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_UnmergeMemberAcconts);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String cardValidate(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId = "";

		if (att.get(0).get("Errorcode").toString().equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("ClubCard")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema/");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			childElementTag.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> CardValidate = new HashMap<String, String>();
			CardValidate.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("CardValidate", CardValidate);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("in_ClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}

	}
	public static String getAddressByZipCode(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String zipCode = "";
		String countryId = "";

		if (att.get(0).get("Errorcode").toString().equalsIgnoreCase("NA")){
					zipCode = parameters.get("zipcode");
					countryId=parameters.get("countryID");

				}else {
					zipCode = att.get(0).get("ZipCode")+"";
					countryId=att.get(0).get("CountryId")+"";
				}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			childElementTag.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetAddressByZipCode = new HashMap<String, String>();
			GetAddressByZipCode.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("GetAddressByZipCode", GetAddressByZipCode);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("zipCode",zipCode);
			childElementTag2.put("countryId",countryId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
	public static String getMemberPromotion(HashMap<String, String> parameters) {

		String clubCardId = parameters.get("clubCardId");

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetMemberPromotions = new HashMap<String, String>();
			childElementTag1.put("hql:GetMemberPromotions", GetMemberPromotions);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("hql:inClubCardId",clubCardId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
	public static String getSegments() {

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetSegments = new HashMap<String, String>();
			childElementTag1.put("hql:GetSegments", GetSegments);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
	public static String getTransactionImage(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId = parameters.get("clubCardId");
		String storeID = att.get(0).get("storeID")+"";
		String storeName = att.get(0).get("storeName")+"";
		String TranId = att.get(0).get("TranId")+"";

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema/");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			childElementTag.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> GetTransactionImage = new HashMap<String, String>();
			GetTransactionImage.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("GetTransactionImage", GetTransactionImage);

			Transaction transaction = new Transaction();
			transaction.setStoreId(storeID);
			transaction.setPosDateTime("2019-06-02");
			transaction.setPosId("1");
			transaction.setTranId(TranId);
			transaction.setStartDateTime("2019-06-02T13:41:22");
			transaction.setClubCardId(clubCardId);
			transaction.setCreatedAt("2019-06-02");
			transaction.setUpdatedBy("1");
			transaction.setTotalAmount("100.0000");
			transaction.setStoreName(storeName);
			transaction.setIsTransactionVoid("false");

			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(Transaction.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(transaction, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"in_TransactionXml",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		} catch (PropertyException e) {
			Log4j.error(e.getMessage());
		} catch (JAXBException e) {
			Log4j.error(e.getMessage());
		}
		return "";
	}
	public static String pendingPointAdjustmentRequest(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String accountId =parameters.get("accountId");
		String earnDate = GenericMethods.currentDate();
		String expirationDate =GenericMethods.currentDate();
		String clubCardId = parameters.get("clubCardId");
		String externalReferenceId = "";
		String transactionStartDateTime = "";
		String transactionID = "";

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> PendingPointsAdjustmentsRequest = new HashMap<String, String>();
			PendingPointsAdjustmentsRequest.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag_Body.put("PendingPointsAdjustmentsRequest",PendingPointsAdjustmentsRequest);

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> pendingRequest = new HashMap<String, String>();
			childElementTag1.put("pendingRequest", pendingRequest);

			HashMap<String, HashMap<String ,String>> childElementTag2 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> PendingAccountsActivityRequest = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag_Body.put("PendingAccountsActivityRequest",PendingAccountsActivityRequest);

			HashMap<String, String> AccountId = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("AccountId",accountId);

			HashMap<String, String> EarnValue = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("EarnValue","999.00");

			HashMap<String, String> EarnStartDate = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("EarnStartDate",earnDate);

			HashMap<String, String> ExpirationDate = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("ExpirationDate",expirationDate);

			HashMap<String, String> ReasonCode = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("ReasonCode","1");

			HashMap<String, String> Remarks = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("Remarks","Test Automation Remarks");

			HashMap<String, String> ClubCardId = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("ClubCardId",clubCardId);

			HashMap<String, String> ExternalReferenceId = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("ExternalReferenceId",externalReferenceId);

			HashMap<String, String> TransactionStartDateTime = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("TransactionStartDateTime",transactionStartDateTime);

			HashMap<String, String> TransactionId = new HashMap<String, String>();
			PendingAccountsActivityRequest.put("TransactionId",transactionID);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement = documentbuilder.nodeElement(childElement_Body,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,AccountId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,EarnValue);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,EarnStartDate);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ExpirationDate);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ReasonCode);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,Remarks);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ExternalReferenceId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,TransactionStartDateTime);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,TransactionId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}

	}
	public static String registerPromotion(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String promotionHeaderId = parameters.get("promotionHeaderId");
		String clubCardId = parameters.get("clubCardId");

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> RegisterPromotion = new HashMap<String, String>();
			childElementTag1.put("hql:RegisterPromotion", RegisterPromotion);

			HashMap<String,String> ClubCardId = new HashMap<String,String>();
			ClubCardId.put("hql:inClubCardId",clubCardId);

			HashMap<String,String> PromotionHeaderId = new HashMap<String,String>();
			PromotionHeaderId.put("hql:inPromotionHeaderId",promotionHeaderId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,PromotionHeaderId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
	public static String registerPromotionWithDateRange(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId = "";
		String promotionHeaderId = parameters.get("promotionHeaderId");

		if(!(att.get(0).get("ClubCard")+"").equalsIgnoreCase("NewCard")){
			 clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = parameters.get("newclubCardId");
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> RegisterPromotionWithDatesRange = new HashMap<String, String>();
			childElementTag1.put("hql:RegisterPromotionWithDatesRange", RegisterPromotionWithDatesRange);

			HashMap<String,String> ClubCardId = new HashMap<String,String>();
			ClubCardId.put("hql:inClubCardId",clubCardId);

			HashMap<String,String> PromotionHeaderId = new HashMap<String,String>();
			PromotionHeaderId.put("hql:inPromotionHeaderId",promotionHeaderId);

			HashMap<String,String> registrationStartDate = new HashMap<String,String>();
			registrationStartDate.put("hql:registrationStartDate",GenericMethods.currentDate()+"T00:00:00");

			HashMap<String,String> registrationEndDate = new HashMap<String,String>();
			registrationEndDate.put("hql:registrationEndDate",GenericMethods.currentDate()+"T23:59:00");

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,PromotionHeaderId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,registrationStartDate);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,registrationEndDate);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String saveMemberCards(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId = parameters.get("clubCardId");
		String cardId = "";

		if((att.get(0).get("memberCard")+"").toUpperCase().contains("SAMEHH")){
			cardId = memberCardId_SameHH+"";
		} else if ((att.get(0).get("memberCard")+"").toUpperCase().contains("SAMEHH1")) {
			cardId = memberCardId_SameHH1+"";
		}else if ((att.get(0).get("memberCard")+"").toUpperCase().contains("DIFFHH")){
			cardId = memberCardId_diffHH+"";
			clubCardId = parameters.get("newclubCardId");
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema/");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			childElementTag.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> SaveMemberCards = new HashMap<String, String>();
			SaveMemberCards.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("SaveMemberCards", SaveMemberCards);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("in_ClubCardId",clubCardId);

			Card card = new Card();
			card.setId(cardId);
			card.setCardStatus("1");
			card.setIssueDate(GenericMethods.currentDate()+"T00:00:00");
			card.setExpirationDate("2056-12-31T00:00:00");
			card.setBarcodeId("22");

			Cards cards = new Cards();
			cards.setCard(card);

			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(Cards.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(cards, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"in_Cards",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException | JAXBException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String mergerMembersSameHH() {
		return "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <MergeMembers xmlns=\"http://www.retalix.com/HQLWebServices\">\r\n"
				+ "      <in_SourceMemberClubCardId>"+memberCardId_SameHH+"</in_SourceMemberClubCardId>\r\n"
				+ "      <in_TargetMemberClubCardId>"+memberCardId_SameHH1+"</in_TargetMemberClubCardId>\r\n"
				+ "    </MergeMembers>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
	}
	public static String mergerMembersDiffHH(List<Map<Object, Object>> att) {

		String sourceMemberCard= "";
		String targetMemberCard = "";

		if((att.get(0).get("sourceMemberCard")+"").equalsIgnoreCase("NA") || (att.get(0).get("sourceMemberCard")+"").equalsIgnoreCase("target")){
			sourceMemberCard = memberCardId_SameHH+"";
		}else {
			sourceMemberCard = att.get(0).get("sourceMemberCard")+"";
		}

		if((att.get(0).get("targetMemberCard")+"").equalsIgnoreCase("NA")){
			targetMemberCard = memberCardId_diffHH+"";
		}else if((att.get(0).get("targetMemberCard")+"").equalsIgnoreCase("target")){
			targetMemberCard = memberCardId_SameHH+"";
		}else {
			targetMemberCard = att.get(0).get("targetMemberCard")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema/");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag = new HashMap<String, HashMap<String ,String>>();
			childElementTag.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> MergeMembersInDifferentHH = new HashMap<String, String>();
			MergeMembersInDifferentHH.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("MergeMembersInDifferentHH", MergeMembersInDifferentHH);

			HashMap<String,String> in_SourceMemberClubCardId = new HashMap<String,String>();
			in_SourceMemberClubCardId.put("in_SourceMemberClubCardId",sourceMemberCard);

			HashMap<String,String> in_TargetMemberClubCardId = new HashMap<String,String>();
			in_TargetMemberClubCardId.put("in_TargetMemberClubCardId",targetMemberCard);


			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement = documentbuilder.nodeElement(rootElement,doc,childElementTag);
			Element childElement1 = documentbuilder.nodeElement(childElement,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_SourceMemberClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,in_TargetMemberClubCardId);


			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String SaveNotes(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId = "";
		String retailerId = "";
		String subTypeId = "";
		String notedate = "";

		if((att.get(0).get("ClubCardId")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("ClubCardId")+"";
		}

		if ((att.get(0).get("RetailerId")+"").equalsIgnoreCase("NA")){
			retailerId = parameters.get("retailerId");
		}else {
			retailerId = att.get(0).get("RetailerId")+"";
		}

		if ((att.get(0).get("SubTypeId")+"").equalsIgnoreCase("NA")){
			subTypeId = parameters.get("subTypeId");
		}else {
			subTypeId = att.get(0).get("SubTypeId")+"";
		}

		if ((att.get(0).get("NoteDateTime")+"").equalsIgnoreCase("NA")){
			notedate = GenericMethods.currentDate()+"T00:00:00";
		}else {
			notedate = att.get(0).get("NoteDateTime")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> SaveNotes = new HashMap<String, String>();
			SaveNotes.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("SaveNotes", SaveNotes);

			HashMap<String,String> childElementTag2 = new HashMap<String,String>();
			childElementTag2.put("in_ClubCardId",clubCardId);

			HashMap<String,String> childElementTag3 = new HashMap<String,String>();
			childElementTag3.put("in_RetailerId",retailerId);

			Note note = new Note();
			note.setSubTypeId(subTypeId);
			note.setNoteDateTime(notedate);

			Notes notes = new Notes();
			notes.setNote(note);


			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(Notes.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(notes, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag2);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,childElementTag3);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"in_ParamsXML",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		} catch (PropertyException e) {
			Log4j.error(e.getMessage());
		} catch (JAXBException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String segmentsMovements(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId;
		String segmentId;
		String status = att.get(0).get("SegmentStatus")+"";
		String startDate;

		if((att.get(0).get("ClubCardID")+"").equalsIgnoreCase("NA")){
			clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = att.get(0).get("ClubCardID")+"";
		}

		if((att.get(0).get("SegmentID")+"").equalsIgnoreCase("NA")){
			segmentId = parameters.get("segmentId");
		}else {
			segmentId = att.get(0).get("SegmentID")+"";
		}

		if((att.get(0).get("StartDate")+"").equalsIgnoreCase("NA")){
			startDate = GenericMethods.currentDate()+"T00:00:00";
		}else {
			startDate = att.get(0).get("StartDate")+"";
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> SegmentsMovements = new HashMap<String, String>();
			SegmentsMovements.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("SegmentsMovements", SegmentsMovements);


			Segment segment = new Segment();
			segment.setSegmentId(segmentId);
			segment.setStatus(status);
			segment.setAttachment(null);
			segment.setStartDate(startDate);

			Segments segments = new Segments();
			segments.setSegment(segment);

			Card card = new Card();
			card.setCardId(clubCardId);
			card.setBarcodeId(null);
			card.setCardStatus(null);
			card.setExpirationDate(null);
			card.setIssueDate(null);
			card.setSegments(segments);


			StringWriter stringWriter = new StringWriter();
			JAXBContext contextObj = JAXBContext.newInstance(Card.class);
        	Marshaller marshallerObj = contextObj.createMarshaller();
        	marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        	marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        	marshallerObj.marshal(card, stringWriter);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_CDATA(childElement1,doc,"in_ClubCardIdXML",stringWriter);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		} catch (PropertyException e) {
			Log4j.error(e.getMessage());
		} catch (JAXBException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String unregisterPromotion(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId = "";
		String promotionHeaderId = parameters.get("promotionHeaderId");

		if(!(att.get(0).get("ClubCard")+"").equalsIgnoreCase("NewCard")){
			 clubCardId = parameters.get("clubCardId");
		}else {
			clubCardId = parameters.get("newclubCardId");
		}

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> UnregisterPromotion = new HashMap<String, String>();
			childElementTag1.put("hql:UnregisterPromotion", UnregisterPromotion);

			HashMap<String,String> ClubCardId = new HashMap<String,String>();
			ClubCardId.put("hql:inClubCardId",clubCardId);

			HashMap<String,String> PromotionHeaderId = new HashMap<String,String>();
			PromotionHeaderId.put("hql:inPromotionHeaderId",promotionHeaderId);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,PromotionHeaderId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
	public static String MS_Combos_Get() {

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance/");
			rootElementattribute.put("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
			rootElementattribute.put("xmlns:soap","http://schemas.xmlsoap.org/soap/envelope/");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soap:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soap:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> MS_Combos_Get = new HashMap<String, String>();
			MS_Combos_Get.put("xmlns","http://www.retalix.com/HQLWebServices");
			childElementTag1.put("MS_Combos_Get", MS_Combos_Get);

			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}
		return "";
	}
	public static String updateExternals(HashMap<String, String> parameters,List<Map<Object, Object>> att) {

		String clubCardId = parameters.get("clubCardId");
		String newMemberId = (Integer.parseInt(parameters.get("clubCardId"))+1)+"";
		String newBuyingUnitId = (Integer.parseInt(parameters.get("clubCardId"))+1)+"";

		try {

			HashMap<String,String> rootElementattribute = new HashMap<String,String>();
			rootElementattribute.put("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
			rootElementattribute.put("xmlns:hql","http://www.retalix.com/HQLWebServices");

			HashMap<String, HashMap<String ,String>> rootElementTag = new HashMap<String, HashMap<String ,String>>();
			rootElementTag.put("soapenv:Envelope",rootElementattribute);

			HashMap<String, HashMap<String ,String>> childElementTag_Header = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Header.put("soapenv:Header",new HashMap<>());
			HashMap<String, HashMap<String ,String>> childElementTag_Body = new HashMap<String, HashMap<String ,String>>();
			childElementTag_Body.put("soapenv:Body",new HashMap<>());

			HashMap<String, HashMap<String ,String>> childElementTag1 = new HashMap<String, HashMap<String ,String>>();
			HashMap<String, String> UpdateExternals = new HashMap<String, String>();
			childElementTag1.put("hql:UpdateExternals", UpdateExternals);

			HashMap<String,String> ClubCardId = new HashMap<String,String>();
			ClubCardId.put("hql:in_ClubCardId",clubCardId);

			HashMap<String,String> loyaltyCardId = new HashMap<String,String>();
			loyaltyCardId.put("hql:loyaltyCardId",clubCardId);

			HashMap<String,String> oldMemberId = new HashMap<String,String>();
			oldMemberId.put("hql:oldMemberId",clubCardId);

			HashMap<String,String> oldBuyingUnitId = new HashMap<String,String>();
			oldBuyingUnitId.put("hql:oldBuyingUnitId",clubCardId);

			HashMap<String,String> hq_newMemberId = new HashMap<String,String>();
			hq_newMemberId.put("hql:newMemberId",newMemberId);

			HashMap<String,String> hq_newBuyingUnitId = new HashMap<String,String>();
			hq_newBuyingUnitId.put("hql:newBuyingUnitId",newBuyingUnitId);


			Documentbuilder documentbuilder = new Documentbuilder();
			Document doc = documentbuilder.documentbuilder();
			Element rootElement = documentbuilder.rootElement(doc,rootElementTag);
			Element childElement_Header = documentbuilder.nodeElement(rootElement,doc,childElementTag_Header);
			Element childElement_Body = documentbuilder.nodeElement(rootElement,doc,childElementTag_Body);
			Element childElement1 = documentbuilder.nodeElement(childElement_Body,doc,childElementTag1);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,ClubCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,loyaltyCardId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,oldMemberId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,oldBuyingUnitId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,hq_newMemberId);
			documentbuilder.nodeElement_setTextContent(childElement1,doc,hq_newBuyingUnitId);

			return documentbuilder.transformerFactory(doc);

		} catch (TransformerException e) {
			Log4j.error(e.getMessage());
		}

		return "";
	}
}
