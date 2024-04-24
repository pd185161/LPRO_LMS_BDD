@10_7_14 @Security @LOYPRO_1402
Feature: EPIC 1402

  # Login WS -UserLogin Method
  @copyResponselog
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Member Service Extract values from DB
    Given Retrieve all the Required parameter values from DataBase

    #Create a new clubcard member
  @copyResponselog @SaveDemographics
  Scenario: SaveDemographics
    Given Verify the clubcardId is available in DB
    And Add MemberService "SaveDemographics" request Payload
      | ClubCard | StoreId |
      | NA       | NA      |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And  Validate the save demographics Response Status

    #Create a new Registered promotion
  @copyResponselog @CreateRegisterPromotion
  Scenario: CreateRegisterPromotion
    Given Validate the Promotion Exist
    And Add CampaignManager "SINGLECONDITION" Json request Payload
      | memberRegistrationRequired |
      | 1                          |
    When User call "Promotion_JSON" API Call with "Post" http Method
    Then Response Status should be 201

    # Create a new coupon barcode
  @copyResponselog @SaveMessageTemplate
  Scenario: SaveMessageTemplate
    Given Add Documents "SaveMessageTemplate" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200
    And  Extract "MessageTemplateID" created

    # Create a new document
  @copyResponselog @SaveDocument
  Scenario: SaveDocument
    Given Extract the Document and Barcode Details from DB
    Given Add Documents "SaveDocument" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200
    And  Extract "DocumentID" created

   #Create a a new Coupon Promotion
  @copyResponselog
  Scenario: Create Promotion with Issuing Coupon
    Given Validate the Promotion Exist
    And Add CampaignManager "MultiCondition_Coupon" Json request Payload
      |  |
    When User call "Promotion_JSON" API Call with "Post" http Method
    And Response Status should be 201
    Then Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request

    #Issue Coupon to the created clubcard via MSG34
  @copyResponselog
  Scenario: Issue Coupon - Upload MSG34 for ClubCardID
    Given User Enter valid data to the MSG34 request
      | Action |
      | 0      |
    Then Add Messages "MSG34" request Payload
      |  |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG Response

    #SaveLoyaltyDocument WS-ValidateCentralDocument Method
  @copyResponselog @ValidateCentralizedDocument
  Scenario: ValidateCentralizedDocument
    Given Add Documents "ValidateCentralizedDocument" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200


    #6. MemberPromotion WS-Executing RegisterToPromotion Method
  @copyResponselog @RegisterToPromotion
  Scenario: RegisterPromotion to Member
    Given Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request
    And Add MemberPromotion "RegisterToPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "RegisterToPromotionResult" status in Response
      |  |

    #7. MemberService WS-CardValidate Method
  @copyResponselog @CardValidate
  Scenario Outline: CardValidate
    Given Add MemberService "CardValidate" request Payload
      | ClubCard   | Errorcode   |
      | <clubcard> | <errorcode> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And  Validate the Card and Status of Response
      | Errorcode   |
      | <errorcode> |
    Examples:
      | clubcard | errorcode |
      |          | NA        |



 #MemberServiceOnline WS- GetMemberdata- Msg1-2 without QueryMode
  @copyResponselog @GetMemberdata
  Scenario: Message1_2
    And Add Messages "MSG1" request Payload
      | QueryMode |
      | 0         |
    When User call "MemberOnlineService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG2

  #Promotion WS-GetPromotionsByStoreId Method
  @copyResponselog @GetPromotionsByStoreId
  Scenario: GetPromotionsByStoreId
    Given Add Promotion "GetPromotionsByStoreId" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200


      #1.session WS- IsSessionKeyValid Method
  @copyResponselog @IsSessionKeyValid
  Scenario: IsSessionKeyValid
    Given Add session "IsSessionKeyValid" request Payload
      |  |
      |  |
    When User call "Session" API Call with "Post" http Method
    Then Response Status should be 200
    Then Validate the response result as "true"
#
     #2.SingleSignOn WS-GetUserToken Method
  @copyResponselog @GetUserToken
  Scenario: GetUserToken
    Given Add singlesignon "GetUserToken" request Payload
      |  |
      |  |
    When User call "SingleSignOn" API Call with "Post" http Method
    Then Response Status should be 200

     #3.GetCatalogEntitties Ws- Test Method
  #works without session key
  @copyResponselog @GetCatalogEntitties
  Scenario: GetCatalogEntitties
    Given Add GetCatalogEntitties "Test" request Payload
      |  |
      |  |
    When User call "GetCatalogEntitties" API Call with "Post" http Method
    Then Response Status should be 200

    #4.MemberPrivacy WS -AllowPersonalData Method
  @copyResponselog @AllowPersonalData
  Scenario: AllowPersonalData
    Given Add MemberPrivacy "allowPersonalData" request Payload
      | clubcardid   |
      | 123456755591 |
    When User call "MemberPrivacy" API Call with "Post" http Method
    Then Response Status should be 200

    #5. MemberServiceFullData WS - GetData Method
  @copyResponselog @GetData
  Scenario: GetData
    Given Add MemberServiceFullData "GetData" request Payload
      | clubcardid    |
      | 0000000000094 |
    When User call "MemberServiceFullData" API Call with "Post" http Method
    Then Response Status should be 200

#    #MemberServiceOnline WS-GetMemberData Method
     #works without session key
#  @copyResponselog
#  Scenario: GetMemberData
#    Given Add MemberServiceOnline "GetMemberData" request Payload
#    When User call "MemberServiceOnline" API Call with "Post" http Method
#    Then Response Status should be 200

    #6. MemberServiceOperations WS- AGeneralDocumentation Method
  @copyResponselog @AGeneralDocumentation
  Scenario: AGeneralDocumentation
    Given Add MemberServiceOperations "AGeneralDocumentation" request Payload
      |  |
      |  |
    When User call "MemberServiceOperations" API Call with "Post" http Method
    Then Response Status should be 200

#    7. MemberWebService_StrongType WS-CardValidate Method
  @copyResponselog @CardValidate
  Scenario: CardValidate
    Given Add MemberWebService_StrongType "CardValidate" request Payload
      | clubcardid   |
      | 123456755594 |
    When User call "MemberWebServiceStrongType" API Call with "Post" http Method
    Then Response Status should be 200

#    8. OpenAuthorization WS- ValidateClubCardId Method
  @copyResponselog @ValidateClubCardId
  Scenario: ValidateClubCardId
    Given Add OpenAuthorization "ValidateClubCardId" request Payload
      | clubcardid   |
      | 123456755594 |
    When User call "OpenAuthorization" API Call with "Post" http Method
    Then Response Status should be 200

    #9. Download WS- ForceDownloadExistingEvents Method
  @copyResponselog @ForceDownloadExistingEvents
  Scenario: ForceDownloadExistingEvents
    Given Add Download "ForceDownloadExistingEvents" request Payload
      | retailerid |
      | 2          |
    When User call "Download" API Call with "Post" http Method
    Then Response Status should be 200

    # 10.DownloadPackagesStatus WS- GetDownloadPackagesStatus Method
  @copyResponselog @GetDownloadPackagesStatus
  Scenario: GetDownloadPackagesStatus
    Given Add DownloadPackagesStatus "GetDownloadPackagesStatus" request Payload
      |  |
      |  |
    When User call "DownloadPackagesStatus" API Call with "Post" http Method
    Then Response Status should be 200


  # 11. EODStatus WS- GetEODStatus Method
  @copyResponselog @GetEODStatus
  Scenario: GetEODStatus
    Given Add EODStatus "GetEODStatus" request Payload
      |  |
      |  |
    When User call "EODStatus" API Call with "Post" http Method
    Then Response Status should be 200


    #12. GeneralTables WS-GetBarcodeTemplates Method
  @copyResponselog @GetBarcodeTemplates
  Scenario: GetBarcodeTemplates
    Given Add GeneralTables "GetBarcodeTemplates" request Payload
      |  |
      |  |
    When User call "GeneralTables" API Call with "Post" http Method
    Then Response Status should be 200

    #13. MemberDynamicAttributeDefaults WS- GetMemberDynamicAttributeDefaults Method
  @copyResponselog @GetMemberDynamicAttributeDefaults
  Scenario: GetMemberDynamicAttributeDefaults
    Given Add MemberDynamicAttributeDefaults "GetMemberDynamicAttributeDefaults" request Payload
      |  |
      |  |
    When User call "MemberDynamicAttributeDefaults" API Call with "Post" http Method
    Then Response Status should be 200

    #14. DataProvider WS- GetReasonCodes Method
  @copyResponselog @GetReasonCodes
  Scenario: GetReasonCodes
    Given Add DataProvider "GetReasonCodes" request Payload
      |  |
      |  |
    When User call "DataProvider" API Call with "Post" http Method
    Then Response Status should be 200

#    15. HouseHoldDocumentsActivity WS -GetHouseHoldDocumentsActivity Method
  @copyResponselog @GetHouseHoldDocumentsActivity
  Scenario: GetHouseHoldDocumentsActivity
    Given Add HouseHoldDocumentsActivity "GetHouseHoldDocumentsActivity" request Payload
      | clubcardid |
      | 1001000030 |
    When User call "HouseHoldDocumentsActivity" API Call with "Post" http Method
    Then Response Status should be 200

#    #23. SaveLoyaltyDocument WS- GetIssuingOrRedemptionDocuments Method
#  @copyResponselog @GetIssuingOrRedemptionDocuments
#  Scenario: GetIssuingOrRedemptionDocuments
#    Given Add SaveLoyaltyDocument "GetIssuingOrRedemptionDocuments" request Payload
#    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
#    Then Response Status should be 200

#    16. TemplateEditor WS- GetMessages Method
  @copyResponselog @GetMessages
  Scenario: GetMessages
    Given Add TemplateEditor "GetMessages" request Payload
      |  |
      |  |
    When User call "TemplateEditor" API Call with "Post" http Method
    Then Response Status should be 200

    #17. ItemGetPriceOnline WS- Ping Method
  @copyResponselog @ItemGetPriceOnline
  Scenario: Ping
    Given Add ItemGetPriceOnline "Ping" request Payload
      |  |
      |  |
    When User call "ItemGetPriceOnline" API Call with "Post" http Method
    Then Response Status should be 200

    #18. ClubServices WS- GetClubs Method
  @copyResponselog @ClubServices @GetClubs
  Scenario: GetClubs
    Given Add ClubServices "GetClubs" request Payload
      |  |
      |  |
    When User call "ClubServices" API Call with "Post" http Method
    Then Response Status should be 200

    #19. LookupServices WS-GetLookup Method
  @copyResponselog @LookupServices @GetLookup
  Scenario: GetLookup
    Given Add LookupServices "GetLookup" request Payload
      |  |
      |  |
    When User call "LookupServices" API Call with "Post" http Method
    Then Response Status should be 200

#    20. MemberServices WS- GetMember Method
  @copyResponselog @MemberServices @GetMember
  Scenario: GetMember
    Given Add MemberServices "GetMember" request Payload
      | clubcardid   |
      | 123456755594 |
    When User call "MemberServices" API Call with "Post" http Method
    Then Response Status should be 200

    #21. OfferServices WS- GetMarketingOfferCategories Method
  @copyResponselog @OfferServices @GetMarketingOfferCategories
  Scenario: GetMarketingOfferCategories
    Given Add OfferServices "GetMarketingOfferCategories" request Payload
      |  |
      |  |
    When User call "OfferServices" API Call with "Post" http Method
    Then Response Status should be 200

   #22. VisitServices WS- GetVisitTicket Method
  @copyResponselog @VisitServices @GetVisitTicket
  Scenario: GetVisitTicket
    Given Add VisitServices "GetVisitTicket" request Payload
      |  |
      |  |
    When User call "VisitServices" API Call with "Post" http Method
    Then Response Status should be 200

#    #34. LotteryOnline WS- GetLotteryPromotionsCounters Method
  #need input xml
#  @copyResponselog @LotteryOnline @GetLotteryPromotionsCounters
#  Scenario: GetLotteryPromotionsCounters
#    Given Add LotteryOnline "GetLotteryPromotionsCounters" request Payload
#    When User call "LotteryOnline" API Call with "Post" http Method
#    Then Response Status should be 200
#
#    #3. LoyaltyDocuments WS- ValidateTest Method
  # works without session key
#  @copyResponselog @LoyaltyDocuments @ValidateTest
#  Scenario: ValidateTest
#    Given Add LoyaltyDocuments "ValidateTest" request Payload
#    When User call "LoyaltyDocuments" API Call with "Post" http Method
#    Then Response Status should be 200

    #23. MarketingPromotions WS-getMarketingPromotions Method
  @copyResponselog @MarketingPromotions @getMarketingPromotions
  Scenario: getMarketingPromotions
    Given Add MarketingPromotions "getMarketingPromotions" request Payload
      |  |
      |  |
    When User call "MarketingPromotions" API Call with "Post" http Method
    Then Response Status should be 200

    #24. MediaEditorImages WS-GetRetailerImages Method
  @copyResponselog @MediaEditorImages @GetRetailerImages
  Scenario: GetRetailerImages
    Given Add MediaEditorImages "GetRetailerImages" request Payload
      |  |
      |  |
    When User call "MediaEditorImages" API Call with "Post" http Method
    Then Response Status should be 200

#    #38. Promotion WS- GetPromotionsByStoreId Method
#  @copyResponselog @GetPromotionsByStoreId
#  Scenario: GetPromotionsByStoreId
#    Given Add Promotion "GetPromotionsByStoreId" request Payload
#    ||
#    ||
#    When User call "Promotion" API Call with "Post" http Method
#    Then Response Status should be 200

    #25. OrganizationStructure WS_ GetRetailers Method
  @copyResponselog @GetRetailers
  Scenario: GetRetailers
    Given Add OrganizationStructure "GetRetailers" request Payload
      |  |
      |  |
    When User call "OrganizationStructure" API Call with "Post" http Method
    Then Response Status should be 200


  @copyResponselog
  Scenario: ManagerLogin
    Given Add Login "ManagerLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

    #26. SegmentMaintenance WS- CreateClubTargetedSegment Method
  # Uses managerlogin only
  @copyResponselog @CreateClubTargetedSegment
  Scenario: CreateClubTargetedSegment
    Given Add SegmentMaintenance "CREATECLUBTARGETEDSEGMENT" request Payload
      |  |
      |  |
    When User call "SegmentMaintenance" API Call with "Post" http Method
    Then Response Status should be 200

    # 1.Login WS -UserLogin Method
  @copyResponselog @UserLogin
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

    # 27. StoreUpload WS- SetDailyStoreUploadStatus Method
  @copyResponselog @StoreUpload @SetDailyStoreUploadStatus
  Scenario: SetDailyStoreUploadStatus
    Given Add StoreUpload "SetDailyStoreUploadStatus" request Payload
      | retailerid |
      | 2          |
    When User call "StoreUpload" API Call with "Post" http Method
    Then Response Status should be 200

    #28. TransactionItems WS- GetTransactionsItems Method
  @copyResponselog @TransactionItems @GetTransactionsItems
  Scenario: GetTransactionsItems
    Given Add TransactionItems "GetTransactionsItems" request Payload
      |  |
      |  |
    When User call "TransactionItems" API Call with "Post" http Method
    Then Response Status should be 200

#    #44. HealthCheck WS - HasSystemParametersInRetailerLevel Method
#  # It does not take SessionKey
#  @copyResponselog
#  Scenario: HasSystemParametersInRetailerLevel
#    Given Add HealthCheck "HasSystemParametersInRetailerLevel" request Payload
#    When User call "HealthCheck" API Call with "Post" http Method
#    Then Response Status should be 200

#    29. CustomerMessages WS- GetCustomerMessages Method
#   Works for Customer 20 only.Run SSF for 20
  @copyResponselog @CustomerMessages @GetCustomerMessages
  Scenario: GetCustomerMessages
    Given Add CustomerMessages "GetCustomerMessages" request Payload
      | clubcardid   |
      | 123456755594 |
    When User call "CustomerMessages" API Call with "Post" http Method
    Then Response Status should be 200

    #30. UserMaintenance WS- GetUserDetails Method
  @copyResponselog @UserMaintenance @GetUserDetails
  Scenario: GetUserDetails
    Given Add UserMaintenance "GetUserDetails" request Payload
      | username |
      | hql      |
    When User call "UserMaintenance" API Call with "Post" http Method
    Then Response Status should be 200

    #31. UserProfile WS- getUserProfiles Method
  @copyResponselog @UserProfile @getUserProfiles
  Scenario: getUserProfiles
    Given Add UserProfile "getUserProfiles" request Payload
      | retailerId |
      | 2          |
    When User call "UserProfile" API Call with "Post" http Method
    Then Response Status should be 200

    #32. Dictionary WS- GetLanguages Method
  @copyResponselog @Dictionary @GetLanguages
  Scenario: GetLanguages
    Given Add Dictionary "GetLanguages" request Payload
      |  |
      |  |
    When User call "Dictionary" API Call with "Post" http Method
    Then Response Status should be 200

    #33. MemberService WS- CardValidate Method
  @copyResponselog @MemberService @CardValidate
  Scenario: CardValidate
    Given Add MemberService "CardValidate" request Payload
      | ClubCard | Errorcode |
      |          | NA        |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200

   #34. SaveLoyaltyDocuments WS-GetMessageLegend Method
  @copyResponselog @Documents @GetMessageLegend
  Scenario: GetMessageLegend
    Given Add Documents "GetMessageLegend" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200


