Feature: Messages

	# Feature File Description
	# Methods

  @copyResponselog
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Extract values from DB
    Given Retrieve all the Required parameter values from DataBase

    #Create a ClubcardId
  @copyResponselog
  Scenario: SaveDemographics
    Given Verify the clubcardId is available in DB
    And Add MemberService "SaveDemographics" request Payload
      | ClubCard | StoreId   |
      | NA       | <storeId> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And  Validate the save demographics Response Status

    # Create a Message in Media Center
  @copyResponselog
  Scenario: SaveMessageTemplate
    Given Add Documents "SaveMessageTemplate" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200
    And  Extract "MessageTemplateID" created

    # Create a new Document in Document Maintenance
  @copyResponselog
  Scenario: SaveDocument
    Given Extract the Document and Barcode Details from DB
    Given Add Documents "SaveDocument" request Payload
    When User call "SaveLoyaltyDocument" API Call with "Post" http Method
    Then Response Status should be 200
    And  Extract "DocumentID" created

  @copyResponselog @Messages
  Scenario: Issue Coupon - Upload MSG34 for ClubCardID
    Given Add Messages "MSG34" request Payload
      | earnValue | rdmValue | openBalance | Action | Storeid | TicketTotal | locked |
      | 31.9000   | 2.3000   | 0.0000      | 0      | 1       | 21.05       | 0      |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG Response

  #GetMemberdata- Msg1-2 without QueryMode
  @copyResponselog @Messages
  Scenario: Message1_2
    And Add Messages "MSG1" request Payload
      | QueryMode |
      | 0         |
    When User call "MemberOnlineService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG2

  #GetMemberdata- Msg1-2 with queryMode
  @copyResponselog @Messages
  Scenario: Message1_2 with Query Mode
    Given Retrieve Locking details from online Database
    And Add Messages "MSG1" request Payload
      | QueryMode |
      | 1         |
    When User call "MemberOnlineService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the MSG2 with QueryMode

   #ValidateTest- Msg7-8 to validate a Document
  @copyResponselog @Messages
  Scenario: Message7_8
    And Add Messages "MSG7" request Payload
      | Storeid |
      | 1       |
    When User call "LoyaltyDocuments" API Call with "Post" http Method
    Then Response Status should be 200
	#	And Validate the MSG8