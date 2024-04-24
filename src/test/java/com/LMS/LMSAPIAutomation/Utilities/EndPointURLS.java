/**
 *
 */
package com.LMS.LMSAPIAutomation.Utilities;
/**
 * @author sa185402
 *
 */
public enum EndPointURLS {

	Message("/RetalixCommGty/SOAP/StoreAndForwardMessageService.asmx"),
	Promotion_JSON("/LoyaltyOnlineWS/REST/Promotion.ashx"),
	Login("/LoyaltyOnlineWS/Authorization/Login.asmx"),
	Session("/LoyaltyOnlineWS/Authorization/Session.asmx"),
	SingleSignOn("/LoyaltyOnlineWS/Authorization/SingleSignOn.asmx"),
	GetCatalogEntitties("/LoyaltyOnlineWS/CatalogInterface/GetCatalogEntitties.asmx"),
	MemberPrivacy("/LoyaltyOnlineWS/CRM/MemberPrivacy.asmx"),
	MemberPromotions("/LoyaltyOnlineWS/CRM/MemberPromotions.asmx"),
	MemberService("/LoyaltyOnlineWS/CRM/MemberService.asmx"),
	MemberServiceFullData("/LoyaltyOnlineWS/CRM/MemberServiceFullData.asmx"),
	MemberOnlineService("/LoyaltyOnlineWS/CRM/MemberServiceOnline.asmx"),
	MemberServiceOperations("/LoyaltyOnlineWS/CRM/MemberServiceOperations.asmx"),
	MemberWebServiceStrongType("/LoyaltyOnlineWS/CRM/MemberWebService_StrongType.asmx"),
	OpenAuthorization("/LoyaltyOnlineWS/CRM/OpenAuthorization.asmx"),
	Download("/LoyaltyOnlineWS/GeneralServices/Download.asmx"),
	DownloadPackagesStatus("/LoyaltyOnlineWS/GeneralServices/DownloadPackagesStatus.asmx"),
	EODStatus("/LoyaltyOnlineWS/GeneralServices/EODStatus.asmx"),
	GeneralTables("/LoyaltyOnlineWS/GeneralServices/GeneralTables.asmx"),
	MemberDynamicAttributeDefaults("/LoyaltyOnlineWS/GeneralServices/MemberDynamicAttributeDefaults.asmx"),
	DataProvider("/LoyaltyOnlineWS/LoyaltyDataProvider/DataProvider.asmx"),
	HouseHoldDocumentsActivity("/LoyaltyOnlineWS/LoyaltyDocuments/HouseHoldDocumentsActivity.asmx"),
	SaveLoyaltyDocument("/LoyaltyOnlineWS/LoyaltyDocuments/SaveLoyaltyDocument.asmx"),
	TemplateEditor("/LoyaltyOnlineWS/MediaCenter/TemplateEditor.asmx"),
	ItemGetPriceOnline("/LoyaltyOnlineWS/OnlineItemQuery/ItemGetPriceOnline.asmx"),
	ClubServices("/LoyaltyOnlineWS/Portal/ClubServices.asmx"),
	LookupServices("/LoyaltyOnlineWS/Portal/LookupServices.asmx"),
	MemberServices("/LoyaltyOnlineWS/Portal/MemberServices.asmx"),
	OfferServices("/LoyaltyOnlineWS/Portal/OfferServices.asmx"),
	VisitServices("/LoyaltyOnlineWS/Portal/VisitServices.asmx"),
	LoyaltyDocuments("/LoyaltyOnlineWS/Promotion/LoyaltyDocuments.asmx"),
	MarketingPromotions("/LoyaltyOnlineWS/Promotion/MarketingPromotions.asmx"),
	MediaEditorImages("/LoyaltyOnlineWS/Promotion/MediaEditorImages.asmx"),
	Promotion_XML("LoyaltyOnlineWS/Promotion/Promotion.asmx"),
	OrganizationStructure("/LoyaltyOnlineWS/RetailerServices/OrganizationStructure.asmx"),
	SegmentMaintenance("/LoyaltyOnlineWS/SegmentMaintenance/SegmentMaintenance.asmx"),
	StoreUpload("/LoyaltyOnlineWS/StoreUpload/StoreUpload.asmx"),
	TransactionItems("/LoyaltyOnlineWS/TDM/TransactionItems.asmx"),
	CustomerMessages("/LoyaltyOnlineWS/TouchPointServer/CustomerMessages.asmx"),
	UserMaintenance("/LoyaltyOnlineWS/UserServices/UserMaintenance.asmx"),
	UserProfile("/LoyaltyOnlineWS/UserServices/UserProfile.asmx"),
	DataProvider_Retailers("/RetalixCommGty/api/dataprovider/retailers"),
	DataProvider_RetailerStores("/RetalixCommGty/api/dataprovider/retailer-stores"),
	CDMAdapter_Ping("/RetalixCommGty/api/cdm-adapter/ping"),
	CDMAdapter_Consumer("/RetalixCommGty/api/cdm-adapter/consumers/1"),
	Dictionary("/LoyaltyOnlineWS/Utils/Dictionary.asmx");

	public String resources;

	EndPointURLS(String resources) {
		// TODO Auto-generated constructor stub
		this.resources = resources;
	}

	public String getResources() {
		return resources;
	}

}