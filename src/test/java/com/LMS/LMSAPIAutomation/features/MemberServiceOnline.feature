Feature: MemberServiceOnline
	# Feature File Description
	# Methods

  @copyResponselog @UserLogin
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Extract values from DB
    Given Retrieve all the Required parameter values from DataBase

  @copyResponselog @SaveDemographics
  Scenario: SaveDemographics
    Given Verify the clubcardId is available in DB
    And Add MemberService "SaveDemographics" request Payload
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And  Validate the save demographics Response Status

  # Method Name Desceiption :
  @copyResponselog @MemberServiceOnline
  Scenario: Message1_2
#      Given User Enter valid data to the MSG3 request
#		|earnValue		|rdmValue	|openBalance|ticketTotal	|
#		|31.9000		|2.3000		|0.0000		|100.0000		|
    And Add Messages "MSG1" request Payload
    When User call "MemberOnlineService" API Call with "Post" http Method
    Then Response Status should be 200
#		And Validate the MSG2

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
    When User call "Promotion_JSON" API Call with "Post" http Method
    And Response Status should be 201
    Then Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request

  @copyResponselog
  Scenario: Issue Coupon - Upload MSG34 for ClubCardID
    Given User Enter valid data to the MSG34 request
    Then Add Messages "MSG34" request Payload
      | Action | Storeid | TicketTotal | locked |
      | 0      | 1       | 21.05       | 0      |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG Response

#	@copyResponselog @MemberServiceOnline @Messages
#    Scenario: Message7_8
#		And Add Messages "MSG7" request Payload
#		When User call "MemberOnlineService" API Call with "Post" http Method
#		Then Response Status should be 200
#	#	And Validate the MSG8
#
#	@copyResponselog @MemberServiceOnline @Messages
#    Scenario: Message11_12
#		And Add Messages "MSG11" request Payload
#		When User call "MemberOnlineService" API Call with "Post" http Method
#		Then Response Status should be 200
#	#	And Validate the MSG12

