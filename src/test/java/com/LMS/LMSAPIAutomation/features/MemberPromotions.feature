@MemberPromotions
Feature: MemberPromotions

  @copyResponselog @UserLogin
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Retrieve Values from DB
    Given Retrieve all the Required parameter values from DataBase

  @copyResponselog @SaveDemographics
  Scenario: SaveDemographics
    Given Verify the clubcardId is available in DB
    And Add MemberService "SaveDemographics" request Payload
      | ClubCard | StoreId |
      | NA       | NA      |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And  Validate the save demographics Response Status

  @copyResponselog @MemberPromotions @CreateRegisterPromotion
  Scenario: CreateRegisterPromotion
    Given Validate the Promotion Exist
    And Add CampaignManager "SINGLECONDITION" Json request Payload
      | memberRegistrationRequired |
      | 1                          |
    When User call "Promotion_JSON" API Call with "Post" http Method
    Then Response Status should be 201

  @copyResponselog @MemberPromotions @RegisterToPromotion
  Scenario: RegisterPromotion to Member
    Given Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request
    And Add MemberPromotion "RegisterToPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "RegisterToPromotionResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @GetPromotionsHouseHoldRegisteredFor
  Scenario: GetPromotionsHouseHoldRegisteredFor
    Given Add MemberPromotion "GetPromotionsHouseHoldRegisteredFor" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetPromotionsHouseHoldRegisteredForResult" status in Response
      |  |
    And Validate the GetPromotionsHouseHoldRegisteredFor Response

  @copyResponselog @MemberPromotions @GetRegisterToPromotionHistory
  Scenario: GetRegisterToPromotionHistory
    Given Add MemberPromotion "GetRegisterToPromotionHistory" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetRegisterToPromotionHistoryResult" status in Response
      |  |
    And Validate the GetRegisterToPromotionHistory Response

  @copyResponselog @MemberPromotions @GetRegisterToPromotionHistoryByExternalId
  Scenario: GetRegisterToPromotionHistoryByExternalId
    Given Add MemberPromotion "GetRegisterToPromotionHistoryByExternalId" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetRegisterToPromotionHistoryByExternalIdResult" status in Response
      |  |
    And Validate the GetRegisterToPromotionHistoryByExternalId Response

  @copyResponselog @MemberPromotions @GetRegisterToPromotionHistoryByInternalId
  Scenario: GetRegisterToPromotionHistoryByInternalId
    Given Add MemberPromotion "GetRegisterToPromotionHistoryByInternalId" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetRegisterToPromotionHistoryByInternalIdResult" status in Response
      |  |
    And Validate the GetRegisterToPromotionHistoryByInternalId Response

  @copyResponselog @MemberPromotions @Host_GetMemberEligiblePromotions
  Scenario: Host_GetMemberEligiblePromotions
    Given Add MemberPromotion "Host_GetMemberEligiblePromotions" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "Host_GetMemberEligiblePromotionsResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @UnregisterFromPromotion
  Scenario: UnregisterFromPromotion
    Given Add MemberPromotion "UnregisterFromPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UnregisterFromPromotionResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @RegisterToExternalPromotion
  Scenario: RegisterToExternalPromotion
    Given Add MemberPromotion "RegisterToExternalPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "RegisterToExternalPromotionResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @UnregisterFromPromotion
  Scenario: UnregisterFromPromotion
    Given Add MemberPromotion "UnregisterFromPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UnregisterFromPromotionResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @RegisterToExternalPromotionWithDatesRange
  Scenario: RegisterToExternalPromotionWithDatesRange
    Given Add MemberPromotion "RegisterToExternalPromotionWithDatesRange" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "RegisterToExternalPromotionWithDatesRangeResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @UnregisterFromPromotion
  Scenario: UnregisterFromPromotion
    Given Add MemberPromotion "UnregisterFromPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UnregisterFromPromotionResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @RegisterToPromotionWithDatesRange
  Scenario: RegisterToPromotionWithDatesRange
    Given Add MemberPromotion "RegisterToPromotionWithDatesRange" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "RegisterToPromotionWithDatesRangeResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @UnregisterFromExternalPromotion
  Scenario: UnregisterFromExternalPromotion
    Given Add MemberPromotion "UnregisterFromExternalPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UnregisterFromExternalPromotionResult" status in Response
      |  |

  @copyResponselog @MemberPromotions @Login
  Scenario: ManagerLogin
    Given Add Login "ManagerLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  @copyResponselog @MemberPromotions @Mp_GetMemberEligiblePromotions
  Scenario: Mp_GetMemberEligiblePromotions
    Given Add MemberPromotion "Mp_GetMemberEligiblePromotions" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "Mp_GetMemberEligiblePromotionsResult" status in Response
      |  |
