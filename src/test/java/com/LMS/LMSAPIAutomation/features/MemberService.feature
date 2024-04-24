Feature: MemberService

  @copyResponselog @MemberServices
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Member Service Extract values from DB
    Given Retrieve all the Required parameter values from DataBase

  @copyResponselog @MemberServices @SaveDemographics
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

  @copyResponselog @MemberServices @CardValidate
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
      | clubcard  | errorcode |
      | sa!@#$%&* | 60002     |
      | 3546      | 60178     |
      |           | NA        |

  @copyResponselog @MemberServices @GetDemographic
  Scenario Outline: GetDemographic
    Given Add MemberService "GetDemographic" request Payload
      | ClubCard   | Errorcode   |
      | <clubcard> | <errorcode> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the GetDemographic details
      | Errorcode   |
      | <errorcode> |
    Examples:
      | clubcard  | errorcode |
      | 3546      | 60002     |
      | sa!@#$%&* | 60002     |
      |           | NA        |

  @copyResponselog @MemberServices @GetAddressByZipCode
  Scenario Outline: GetAddressByZipCode
    Given Check the entry in Zipcode table of MP Database and Insert the record if no data
    And Retrive the details from Zipcode table of MP Database
    And Add MemberService "GetAddressByZipCode" request Payload
      | ZipCode   | CountryId   | Errorcode   |
      | <zipCode> | <countryID> | <errorcode> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate GetAddressByZipCode Status and response
      | Message   |
      | <message> |
    Examples:
      | zipCode  | countryID | errorcode   | message                                   |
      | 50088@#$ | 100!@$    | NOERRORCODE | Input string was not in a correct format. |
      | NA       | NA        | NA          | NA                                        |

  @copyResponselog @MemberServices
  Scenario: GetSegments
    Given Add MemberService "GetSegments" request Payload
      | NA |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate GetSegments status

## need to Get Clarity on this
#	@copyResponselog @MemberServices
#	Scenario: GetTransactionImage
#		Given Add MemberService "GetTransactionImage" request Payload
#			|storeID|storeName|TranId|
#			|       |         |      |
#		When User call "MemberService" API Call with "Post" http Method
#		Then Response Status should be 200
#
## need to Get Clarity on this
#	@copyResponselog @MemberServices
#	Scenario Outline: PendingPointAdjustmentRequest
#		Given Add MemberService "PendingPointAdjustmentRequest" request Payload
#			|AccountId  |EarnDate  |expirationDate  |clubCardId  |externalReferenceId  |transactionStartDateTime  |transactionID  |ErrorCode  |
#			|<accountId>|<earnDate>|<expirationDate>|<clubCardId>|<externalReferenceId>|<transactionStartDateTime>|<transactionID>|<errorCode>|
#		When User call "MemberService" API Call with "Post" http Method
#		Then Response Status should be 200
#
#		Examples:
#		|accountId|earnDate|expirationDate|clubCardId|externalReferenceId|transactionStartDateTime|transactionID|errorCode|

  @copyResponselog @MemberServices @CreateRegisterPromotion
  Scenario: CreateRegisterPromotion
    Given Validate the Promotion Exist
    And Add CampaignManager "singleCondition" Json request Payload
      | memberRegistrationRequired |
      | 1                          |
    When User call "Promotion_JSON" API Call with "Post" http Method
    Then Response Status should be 201

  @copyResponselog @MemberServices
  Scenario: RegisterPromotion
    Given Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request
    And Add MemberService "RegisterPromotion" request Payload
      | NA |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the RegisterPromotionResult in Response

  @copyResponselog @MemberServices @GetMemberPromotion
  Scenario: GetMemberPromotion
    Given Add MemberService "GetMemberPromotion" request Payload
      | NA |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the GetMemberPromotionsResult and Promotion Results in Response

  @copyResponselog @MemberServices @SaveDemographics
  Scenario: SaveDemographics
    Given Add MemberService "SaveDemographics" request Payload
      | ClubCard |
      | NewCard  |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And  Validate the save demographics Response Status

  @copyResponselog @MemberServices @RegisterPromotionWithDateRange
  Scenario: RegisterPromotionWithDateRange
    Given Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request
    And Add MemberService "RegisterPromotionWithDateRange" request Payload
      | ClubCard |
      | NewCard  |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the RegisterPromotionWithDatesRangeResult in Response

  @copyResponselog @MemberServices @UnregisterPromotion
  Scenario: UnregisterPromotion
    Given Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request
    And Add MemberService "UnregisterPromotion" request Payload
      | ClubCard |
      | NewCard  |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the UnregisterPromotionResult in Response

  @copyResponselog @MemberServices @SaveNotes
  Scenario Outline: SaveNotes
    Given Extract SubtypeID from CRM_NotesSubTypes Table from DB
    And Add MemberService "SaveNotes" request Payload
      | ClubCardId   | RetailerId   | NoteDateTime   | SubTypeId   | ErrorCode   |
      | <clubcardid> | <retailerid> | <notedatetime> | <subtypeid> | <errorcode> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the SaveNotesResult in Response
      | ErrorCode   | ErrorDescription   |
      | <errorcode> | <errordescription> |
    Examples:
      | clubcardid | retailerid | notedatetime        | subtypeid | errorcode   | errordescription                                     |
      | sa!@#$%&*  | NA         | NA                  | NA        | 60002       | NA                                                   |
      | NA         | 100000     | NA                  | NA        | 60168       | NA                                                   |
      | 5476       | NA         | NA                  | NA        | 60178       | NA                                                   |
      | NA         | NA         | NA                  | 5         | 60280       | NA                                                   |
      | NA         | NA         | 2056-12-19T12:12:12 | NA        | NOERRORCODE | NoteDateTime cannot be greater than current DateTime |
      | NA         | NA         | NA                  | NA        | NA          | NA                                                   |

  @copyResponselog @MemberServices @SegmentsMovements
  Scenario Outline: SegmentsMovements Attach and Detach Segments
    Given Add MemberService "SEGMENTSMOVEMENTS" request Payload
      | ClubCardID   | SegmentID   | StartDate   | SegmentStatus   |
      | <clubcardid> | <segmentid> | <startdate> | <segmentstatus> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the SegmentsMovementsResult "<type>" in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | ScenarioName            | clubcardid | segmentid | startdate           | segmentstatus | errorcode | type   |
      | Club Card doesnot exist | 784        | NA        | NA                  | 1             | 60191     | Attach |
      | Segment id Not found    | NA         | 999999999 | NA                  | 1             | 60210     | Attach |
      | StartDate > End Date    | NA         | NA        | 2057-12-19T12:12:12 | 1             | 60211     | Attach |
      | Attach                  | NA         | NA        | NA                  | 1             | NA        | Attach |
      | Detach                  | NA         | NA        | NA                  | 2             | NA        | Detach |

  @copyResponselog @MemberServices @AdjustMemberAccount
  Scenario Outline: AdjustMemberAccount
    Given Add MemberService "AdjustMemberAccount" request Payload
      | ClubCardID   | AccountID   | EarnValue   | InitialValue   |
      | <clubcardid> | <accountid> | <earnvalue> | <initialValue> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the AdjustMemberAccountResult in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Scenario                              | clubcardid | accountid | earnvalue | initialValue | errorcode |
      | Invalid ClubcardID (include SPL Char) | sa!@#$%&*  | NA        | 5.00      | 0.00         | 60002     |
      | Invalid ClubcardID                    | 8756       | NA        | 5.00      | 0.00         | 60002     |
      | MA ID not Found                       | NA         | 9000      | 5.00      | 0.00         | 60197     |
      | Valid Scenario                        | NA         | NA        | 5.00      | 0.00         | NA        |

  @copyResponselog @MemberServices @GetFilteredMemberAccountDetailedTransactions
  Scenario Outline: GetFilteredMemberAccountDetailedTransactions
    Given Add MemberService "GetFilteredMemberAccountDetailedTransactions" request Payload
      | clubCardId   | accountId   |
      | <clubcardid> | <accountid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetFilteredMemberAccountDetailedTransactionsResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetFilteredMemberAccountDetailedTransactions Response
      | ErrorCode   | EarnValue   | RedeemValue   |
      | <errorcode> | <earnvalue> | <redeemvalue> |
    Examples:
      | Scenarios          | clubcardid | accountid | earnvalue | redeemvalue | errorcode |
      | ClubCard Not Exist | 60178      | NA        |           |             | 60002     |
      | Invalid clubcard   | sa!@#$     | NA        |           |             | 60002     |
      | Invalid Accountid  | NA         | -1        |           |             | 60364     |
      | Valid Sceanrio     | NA         | NA        | 5.0000    | 0.0000      | NA        |

  @copyResponselog @MemberServices @GetMemberAccountDetailedTransactions
  Scenario Outline: GetMemberAccountDetailedTransactions
    Given Add MemberService "GetMemberAccountDetailedTransactions" request Payload
      | clubCardId   | accountId   |
      | <clubcardid> | <accountid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetMemberAccountDetailedTransactionsResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetMemberAccountDetailedTransactions Response
      | ErrorCode   | EarnValue   | TicketTotal   | RedeemValue   |
      | <errorcode> | <earnvalue> | <ticketTotal> | <redeemvalue> |
    Examples:
      | Sceanario          | clubcardid | accountid | earnvalue | ticketTotal | redeemvalue | errorcode |
      | ClubCard Not Exist | 60178      | NA        |           |             |             | 60178     |
      | Invalid clubcard   | sa!@#$     | NA        |           |             |             | 60002     |
      | Invalid accountid  | NA         | -1        |           |             |             | 60019     |
      | Valid Sceanrio     | NA         | NA        | 5.0000    | 0.0000      | 0.0000      | NA        |

  @copyResponselog @MemberServices @GetHouseHoldTransactions
  Scenario Outline: GetHouseHoldTransactions
    Given Add MemberService "GetHouseHoldTransactions" request Payload
      | clubCardId   |
      | <clubcardid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetHouseHoldTransactionsResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetHouseHoldTransactions Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Sceanario          | clubcardid | errorcode |
      | ClubCard Not Exist | 60178      | 60178     |
      | Invalid clubcard   | sa!@#$     | 60002     |
      | Valid Sceanrio     | NA         | NA        |

  @copyResponselog @MemberServices @GetMemberAccountsSummary
  Scenario Outline: GetMemberAccountsSummary
    Given Add MemberService "GetMemberAccountsSummary" request Payload
      | clubCardId   | accountId   |
      | <clubcardid> | <accountid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetMemberAccountsSummaryResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Sceanario          | clubcardid | accountid | errorcode |
      | ClubCard Not Exist | 60178      | NA        | 60178     |
      | Invalid clubcard   | sa!@#$     | NA        | 60002     |
      | Valid Sceanrio     | NA         | NA        | NA        |

  @copyResponselog @MemberServices @MergeHouseHold
  Scenario Outline: MergeHouseHold
    Given Add MemberService "MergeHouseHold" request Payload
      | SourceMemberClubCardId | TargetMemberClubCardId |
      | <sourceclubcardid>     | <targetclubcardid>     |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "MergeHouseHoldResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Scenario           | sourceclubcardid | targetclubcardid | errorcode |
      | ClubCard Not Exist | 60178            | NA               | 60178     |
      | ClubCard Not Exist | NA               | 60178            | 60178     |
      | ClubCard Not Exist | 60178            | 60178            | 60178     |
      | Invalid clubcard   | sa!@#$           | NA               | 60002     |
      | Invalid Clubcard   | NA               | sa!@#$           | 60002     |
      | Valid Scenario     | NA               | NA               | NA        |
      | source added >1    | NA               | NA               | 60021     |

  @copyResponselog @MemberServices @ChangeHouseHoldHead
  Scenario Outline: ChangeHouseHoldHead
    Given Add MemberService "ChangeHouseHoldHead" request Payload
      | currentHeadClubCardId   | newHeadClubCardId   |
      | <currentHeadClubCardId> | <newHeadClubCardId> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "ChangeHouseHoldHeadResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Scenario               | currentHeadClubCardId | newHeadClubCardId | errorcode |
      | ClubCard Not Exist     | 60178                 | NA                | 60178     |
      | ClubCard Not Exist     | NA                    | 60178             | 60178     |
      | ClubCard Not Exist     | 60178                 | 60178             | 60178     |
      | Invalid clubcard       | NA                    | sa!@#$            | 60002     |
      | Invalid Clubcard       | sa!@#$                | sa!@#$            | 60002     |
      | Source and Target Same | source                | source            | 60143     |
      | Valid Scenario         | NA                    | NA                | NA        |
      | cards not in same HH   | 9766000009457         | NA                | 60017     |

  @copyResponselog @MemberServices @UnmergeHouseHold
  Scenario Outline: UnmergeHouseHold
    Given Add MemberService "UnmergeHouseHold" request Payload
      | HouseHoldHeadClubCardId   | UnmargeMemberClubCardId   |
      | <houseHoldHeadClubCardId> | <unmargeMemberClubCardId> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UnmergeHouseHoldResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Scenario               | houseHoldHeadClubCardId | unmargeMemberClubCardId | errorcode |
      | ClubCard Not Exist     | 60178                   | NA                      | 60178     |
      | ClubCard Not Exist     | NA                      | 60178                   | 60178     |
      | ClubCard Not Exist     | 60178                   | 60178                   | 60178     |
      | Invalid clubcard       | sa!@#$                  | NA                      | 60002     |
      | Invalid clubcard       | NA                      | sa!@#$                  | 60002     |
      | Invalid Clubcard       | sa!@#$                  | sa!@#$                  | 60002     |
      | Source and Target Same | source                  | source                  | 60024     |
      | Valid Scenario         | NA                      | NA                      | NA        |
      | cards not in same HH   | 9766000009457           | NA                      | 60023     |

  @copyResponselog @MemberServices @SaveMemberCards
  Scenario Outline: SaveMemberCards
    Given Add MemberService "SAVEMEMBERCARDS" request Payload
      | memberCard   |
      | <membercard> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "SaveMemberCardsResult" status in Response
      | ErrorCode |
      | NA        |
    Examples:
      | membercard |
      | SAMEHH     |
      | SAMEHH1    |
      | DIFFHH     |

# Failing need to check
#	@copyResponselog @MergerMembersSameHH
#	Scenario: MergerMembersSameHH
#	Given Add MemberService "MergerMembersSameHH" request Payload
#	When User call "MemberService" API Call with "Post" http Method
#	Then Response Status should be 200
#	And Validate the "MergeMembersResult" status in Response

  @copyResponselog @MemberServices @mergerMembersDiffHH
  Scenario Outline: mergerMembersDiffHH
    Given Add MemberService "MERGEMEMBERSDIFFHH" request Payload
      | sourceMemberCard   | targetMemberCard   |
      | <sourcemembercard> | <targetmembercard> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "MergeMembersInDifferentHHResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Scenario               | sourcemembercard | targetmembercard | errorcode |
      | invalid characters     | sa!@#            | NA               | 60002     |
      | invalid characters     | sa!@#            | sa!@#            | 60002     |
      | Invalid SC Member Card | 60178            | NA               | 60178     |
      | Invalid Tar MemberCard | NA               | 60178            | 60178     |
      | Invalid Cards          | 60178            | 60178            | 60178     |
      | Card already part HH   | target           | target           | 60021     |
      | valid sceanrio         | NA               | NA               | NA        |

  @copyResponselog @MemberServices @GetAccumulatedAccounts
  Scenario: GetAccumulatedAccounts
    Given Add MemberService "GetAccumulatedAccounts" request Payload
      | NA |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetAccumulatedAccountsResult" status in Response
      | ErrorCode |
      | NA        |

  @copyResponselog @MemberServices @GetContinuityPromotionDetailedTransactions
  Scenario Outline: GetContinuityPromotionDetailedTransactions
    Given Add MemberService "GETCONTINUITYPROMOTIONDETAILEDTRANSACTION" request Payload
      | clubCardId   | promotionHeaderID   |
      | <clubcardid> | <promotionheaderid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetContinuityPromotionDetailedTransactionsResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Scenario           | clubcardid | promotionheaderid | errorcode |
      | ClubCard Not Exist | 60178      | NA                | 60178     |
      | Invalid clubcard   | sa!@#$     | NA                | 60002     |
      | Valid Scenario     | NA         | NA                | NA        |

  @copyResponselog @MemberServices @GetHHNotes
  Scenario Outline: GetHHNotes
    Given Add MemberService "GetHHNotes" request Payload
      | clubCardId   | retailerId   |
      | <clubcardid> | <retailerid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetHHNotesResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Sceanario          | clubcardid | retailerid | errorcode |
      | ClubCard Not Exist | 60178      | NA         | 60178     |
      | Invalid clubcard   | sa!@#$     | NA         | 60002     |
      | Invalid Retailerid | NA         | -1         | 60168     |
      | Valid Sceanrio     | NA         | NA         | NA        |

  @copyResponselog @MemberServices @GetHouseHoldAccountsActivity
  Scenario Outline: GetHouseHoldAccountsActivity
    Given Add MemberService "GetHouseHoldAccountsActivity" request Payload
      | clubCardId   |
      | <clubcardid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetHouseHoldAccountsActivityResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetHouseHoldAccountsActivity Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Sceanario          | clubcardid | errorcode |
      | ClubCard Not Exist | 60178      | 60178     |
      | Invalid clubcard   | sa!@#$     | 60002     |
      | Valid Sceanrio     | NA         | NA        |

  @copyResponselog @MemberServices @GetHouseHoldContinuityPromotionsActivity
  Scenario Outline: GetHouseHoldContinuityPromotionsActivity
    Given Add MemberService "GetHouseHoldContinuityPromotionsActivity" request Payload
      | clubCardId   |
      | <clubcardid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetHouseHoldContinuityPromotionsActivityResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetHouseHoldContinuityPromotionsActivity Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Sceanario          | clubcardid | errorcode |
      | ClubCard Not Exist | 60178      | 60178     |
      | Invalid clubcard   | sa!@#$     | 60002     |
      | Valid Sceanrio     | NA         | NA        |

##Getting Error need to check
#	@copyResponselog @MemberServices @GETHOUSEHOLDFILTEREDTRANSACTIONS
#	Scenario Outline: GetHouseHoldFilteredTransactions
#		Given Add MemberService "GETHOUSEHOLDFILTEREDTRANSACTIONS" request Payload
#			| clubCardId   |
#			| <clubcardid> |
#		When User call "MemberService" API Call with "Post" http Method
#		Then Response Status should be 200
#		And Validate the "GetHouseHoldFilteredTransactionsResult" status in Response
#			| ErrorCode   |
#			| <errorcode> |
#		Examples:
#			| Sceanario          | clubcardid | errorcode |
#			| ClubCard Not Exist | 60178      | 60178     |
#			| Invalid clubcard   | sa!@#$     | 60002     |
#			| Valid Sceanrio     | NA         | NA        |

  @copyResponselog @MemberServices @GetHouseHoldMembers
  Scenario Outline: GetHouseHoldMembers
    Given Add MemberService "GetHouseHoldMembers" request Payload
      | clubCardId   |
      | <clubcardid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetHouseHoldMembersResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetHouseHoldMembers Response
      | ErrorCode   |
      | <errorcode> |

    Examples:
      | Sceanario          | clubcardid | errorcode |
      | ClubCard Not Exist | 60178      | 60002     |
      | Invalid clubcard   | sa!@#$     | 60002     |
      | Valid Sceanrio     | NA         | NA        |

  @copyResponselog @MemberServices @GETHOUSEHOLDMEMBERSDEMOGRAPHICS
  Scenario Outline: GetHouseHoldMembersDemographic
    Given Add MemberService "GETHOUSEHOLDMEMBERSDEMOGRAPHICS" request Payload
      | clubCardId   |
      | <clubcardid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetHouseHoldMembersDemographicResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetHouseHoldMembersDemographic Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Sceanario          | clubcardid | errorcode |
      | ClubCard Not Exist | 60178      | 60186     |
      | Invalid clubcard   | sa!@#$     | 60186     |
      | Valid Sceanrio     | NA         | NA        |

  @copyResponselog @MemberServices @GetMemberAccountDetailedPendingTransactions
  Scenario Outline: GetMemberAccountDetailedPendingTransactions
    Given Add MemberService "GetMemberAccountDetailedPendingTransactions" request Payload
      | clubCardId   | accountId   |
      | <clubcardid> | <accountid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetMemberAccountDetailedPendingTransactionsResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Scenario       | clubcardid | accountid | errorcode |
      | Valid Sceanrio | NA         | NA        | NA        |

  @copyResponselog @MemberServices @GetMemberAccountsActivity
  Scenario Outline: GetMemberAccountsActivity
    Given Add MemberService "GetMemberAccountsActivity" request Payload
      | clubCardId   |
      | <clubcardid> |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "GetMemberAccountsActivityResult" status in Response
      | ErrorCode   |
      | <errorcode> |
    And Validate the GetMemberAccountsActivity Response
      | ErrorCode   |
      | <errorcode> |
    Examples:
      | Sceanario          | clubcardid | errorcode |
      | ClubCard Not Exist | 60178      | 60178     |
      | Invalid clubcard   | sa!@#$     | 60002     |
      | Valid Sceanrio     | NA         | NA        |

  @copyResponselog @MemberServices @MS_Combos_Get
  Scenario: MS_Combos_Get
    Given Add MemberService "MS_Combos_Get" request Payload
      | NA |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "MS_Combos_GetResult" status in Response
      | ErrorCode |
      | NA        |

  @copyResponselog @MemberServices @UpdateExternals
  Scenario: UpdateExternals
    Given Add MemberService "UpdateExternals" request Payload
      | NA |
    When User call "MemberService" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UpdateExternalsResult" status in Response
      | ErrorCode |
      | NA        |
    And Validate the UpdateExternalsResult Response