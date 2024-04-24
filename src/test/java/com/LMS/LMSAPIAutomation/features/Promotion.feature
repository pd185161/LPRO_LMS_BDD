Feature: Promotion

  @copyResponselog @UserLogin
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Retrieve Values from DB
    Given Retrieve all the Required parameter values from DataBase

  @copyResponselog @Promotions
  Scenario: CreateRegisterPromotion
    Given Validate the Promotion Exist
    And Add CampaignManager "SINGLECONDITION" Json request Payload
      | memberRegistrationRequired |
      | 1                          |
    When User call "Promotion_JSON" API Call with "Post" http Method
    Then Response Status should be 201

  @copyResponselog @Promotions @CopyAndSuspendFromSource
  Scenario: CopyAndSuspendFromSource
    Given Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request
    And Add Promotion "CopyAndSuspendFromSource" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "CopyAndSuspendFromSourceResult" status in Response
      |  |
    And Validate the attributes in CopyAndSuspendFromSourceResult Response

  @copyResponselog @Promotions @CopyPromotion
  Scenario: CopyPromotion
    Given Add Promotion "CopyPromotion" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "CopyPromotionResult" status in Response
      |  |
    And Validate the attributes in CopyPromotionResult Response

  @copyResponselog @Promotions @GetPromotionByExternalId
  Scenario: GetPromotionByExternalId
    Given Add Promotion "GetPromotionByExternalId" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetPromotionByExternalIdResult" status in Response
      |  |
    And Validate the attributes in GetPromotionByExternalIdResult Response

  @copyResponselog @Promotions @GetPromotionByInternalId
  Scenario: GetPromotionByInternalId
    Given Add Promotion "GetPromotionByInternalId" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetPromotionByInternalIdResult" status in Response
      |  |
    And Validate the attributes in GetPromotionByInternalIdResult Response

  @copyResponselog @Promotions @GetPromotionsByStoreId
  Scenario: GetPromotionsByStoreId
    Given Add Promotion "GetPromotionsByStoreId" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetPromotionsByStoreIdResult" status in Response
      |  |

  @copyResponselog @Promotions @UpdatePromotion
  Scenario: UpdatePromotion
    Given Add Promotion "UpdatePromotion" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UpdatePromotionResult" status in Response
      |  |
    And Validate the attributes in UpdatePromotion Response

  @copyResponselog @Promotions @UpdateStoreParticipation
  Scenario: UpdateStoreParticipation
    Given Add Promotion "UpdateStoreParticipation" request Payload
    When User call "Promotion_XML" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UpdateStoreParticipationResult" status in Response
      |  |