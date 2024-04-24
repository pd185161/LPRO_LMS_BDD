Feature: Member Export Mode 19

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

  @copyResponselog @MemberPromotions @RegisterToPromotion
  Scenario: RegisterPromotion to Member
    Given Extract PromotionHeaderID from DB and incorporate in the RegisterPromotion request
    And Add MemberPromotion "RegisterToPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "RegisterToPromotionResult" status in Response
      |  |

#  Member export for the first time after registering promotion
  @MemberExport
  Scenario Outline: Member Export- Mode 19
    Given Create an export Bat File
      | serverBatchFilepath   | batchFileName   |outputFilePath| workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   | exportMode   |
      | <serverBatchFilepath> | <batchFileName> |<outputFilePath> |<workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> | <exportMode> |
    When Execute the BAT File and Validate the exitValue
     | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
     | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    And Validate promotion in the export file
      | serverIP   |
      | <serverIP> |
    Examples:
      | exportMode | serverBatchFilepath                                     | batchFileName     | serverIP       | serverUserName     | serverPassword | outputFilePath               | workFilePath                 | logFile                                                     | errorFile                                                   | batProcess                  |
      | 19         | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_MemberExport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\hql\exe\BatchManager\out\ | C:\hql\exe\BatchManager\WRK\ | C:\hql\exe\BatchManager\LOG\LoyaltyMembersGenericExport.log | C:\hql\exe\BatchManager\err\LoyaltyMembersGenericExport.err | LOYALTYMEMBERSGENERICEXPORT |
#
#  Member export re-executing the batch to check if any file exported
  @MemberExport
  Scenario Outline: Member Export- Mode 19 -re-execute
    Given Create an export Bat File
      | serverBatchFilepath   | batchFileName   |outputFilePath| workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   | exportMode   |
      | <serverBatchFilepath> | <batchFileName> |<outputFilePath> |<workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> | <exportMode> |
    When Execute the BAT File and Validate the exitValue
      | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    And Validate no export file generated
    Examples:
      | exportMode | serverBatchFilepath                                     | batchFileName     | serverIP       | serverUserName     | serverPassword | outputFilePath               | workFilePath                 | logFile                                                     | errorFile                                                   | batProcess                  |
      | 19         | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_MemberExport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\hql\exe\BatchManager\out\ | C:\hql\exe\BatchManager\WRK\ | C:\hql\exe\BatchManager\LOG\LoyaltyMembersGenericExport.log | C:\hql\exe\BatchManager\err\LoyaltyMembersGenericExport.err | LOYALTYMEMBERSGENERICEXPORT |

    # Unregister promotion from member
  @copyResponselog @MemberPromotions @UnregisterFromExternalPromotion
  Scenario: UnregisterFromExternalPromotion
    Given Add MemberPromotion "UnregisterFromExternalPromotion" request Payload
    When User call "MemberPromotions" API Call with "Post" http Method
    Then Response Status should be 200
    And Validate the "UnregisterFromExternalPromotionResult" status in Response
      |  |

    #  Member export re-executing the batch after unregistering the promotion to check if any file exported
  @MemberExport
  Scenario Outline: Member Export- Mode 19 after Promotion unregister
    Given Create an export Bat File
      | serverBatchFilepath   | batchFileName   |outputFilePath| workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   | exportMode   |
      | <serverBatchFilepath> | <batchFileName> |<outputFilePath> |<workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> | <exportMode> |
    When Execute the BAT File and Validate the exitValue
      | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    And Validate no export file generated
    Examples:
      | exportMode | serverBatchFilepath                                     | batchFileName     | serverIP       | serverUserName     | serverPassword | outputFilePath               | workFilePath                 | logFile                                                     | errorFile                                                   | batProcess                  |
      | 19         | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_MemberExport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\hql\exe\BatchManager\out\ | C:\hql\exe\BatchManager\WRK\ | C:\hql\exe\BatchManager\LOG\LoyaltyMembersGenericExport.log | C:\hql\exe\BatchManager\err\LoyaltyMembersGenericExport.err | LOYALTYMEMBERSGENERICEXPORT |
