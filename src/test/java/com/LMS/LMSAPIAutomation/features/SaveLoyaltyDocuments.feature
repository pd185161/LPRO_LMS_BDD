Feature: Documents

  @copyResponselog
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Retrieve Values from DB
    Given Retrieve all the Required parameter values from DataBase

  @copyResponselog @SaveDemographics
  Scenario Outline: SaveDemographics
    Given Verify the clubcardId is available in DB
    And Add MemberService "SaveDemographics" request Payload
      | ClubCard | StoreId   |
      | NA       | <storeId> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And  Validate the save demographics Response Status
    Examples:
      | errorcode | storeId |
      | 60001     |         |

  @copyResponselog @SaveMessageTemplate
  Scenario: SaveMessageTemplate
    Given Add Documents "SaveMessageTemplate" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200
    And  Extract "MessageTemplateID" created

  @copyResponselog @SaveDocument
  Scenario: SaveDocument
    Given Extract the Document and Barcode Details from DB
    Given Add Documents "SaveDocument" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200
    And  Extract "DocumentID" created

  @copyResponselog
  Scenario: Create Promotion with Issuing Coupon
    Given Validate the Promotion Exist
    And Add CampaignManager "MultiCondition_Coupon" Json request Payload
      | memberRegistrationRequired |
      | 1                          |
    When User call "Promotion_JSON" API Call with "Post" http Method
    And Response Status should be 201
    Then Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request

  @copyResponselog
  Scenario: Issue Coupon - Upload MSG34 for ClubCardID
    Given User Enter valid data to the MSG34 request
      | Action |
      | 0      |
    Then Add Messages "MSG34" request Payload
      | earnValue | rdmValue | openBalance | Action | Storeid | TicketTotal | locked |
      | 31.9000   | 2.3000   | 0.0000      | 0      | 1       | 21.05       | 0      |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG Response

  @copyResponselog @SaveLoyaltyDocument @ValidateCentralizedDocument
  Scenario: ValidateCentralizedDocument
    Given Add Documents "ValidateCentralizedDocument" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200

  @copyResponselog
  Scenario: Redeem Coupon - Upload MSG34 for ClubCardID
    Given User Enter valid data to the MSG34 request
      | Action |
      | 1      |
    Then Add Messages "MSG34" request Payload
      | earnValue | rdmValue | openBalance | Action | Storeid | TicketTotal | locked |
      | 31.9000   | 2.3000   | 0.0000      | 0      | 1       | 21.05       | 0      |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG Response

  @copyResponselog @SaveLoyaltyDocument @DeactivateCentralizedDocument
  Scenario: DeactivateCentralizedDocument
    Given Add Documents "DeactivateCentralizedDocument" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200

  @copyResponselog @SaveLoyaltyDocument @GetMessageLegend
  Scenario: GetMessageLegend
    Given Add Documents "GetMessageLegend" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200

  @copyResponselog @SaveLoyaltyDocument @SaveVoucherBarcode
  Scenario: SaveVoucherBarcode
    Given Generate VoucherBarcodeId
    And Add Documents "SaveVoucherBarcode" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200

  @copyResponselog @SaveLoyaltyDocument @GetIssuingOrRedemptionDocuments
  Scenario: GetIssuingOrRedemptionDocuments
    And Add Documents "GetIssuingOrRedemptionDocuments" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200