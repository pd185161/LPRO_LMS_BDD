Feature: Import BAT Process

#    And Update Import Node with respective values
#      |Nodes        |Index|FamilyMemberId   | TypeId|Name|BirthdayDate|Gender|Action|
#      |FamilyMembers|0    |HH City          |       |    |            |      |      |
#    And Update Import Node with respective values
#      |Nodes        |Index|Id           |Value      |
#      |Attribute    |0    |1001000025   |1001000025 |
#           |Nodes |Index|Id            | CardStatus|IssueDate              |EffectiveDate          |
#          |Card  |0    |1001000025    |1          |2056-12-30T00:00:00.000|2056-12-30T00:00:00.000|
  @ImportCustomer
  Scenario Outline: Customer Import Bat process
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        |                  |
      | MemberAttributes     |                  |
      | Stores               |                  |
      | MemberSegments       |                  |
      | HouseHoldSegments    |                  |
      | RegisteredPromotions |                  |
#    And Include the addition node to the XML file
#      | Node  | Xpath   | SampleXMLFle     |
#      | Store | //Store | <sampleFilePath> |
#      | Card  | //Card  |                  |
    And Remove respective Attribute from Element
      | element         | attributeName       | index |
      | LoyaltyCustomer | SystemSource        | 0     |
      | Retailer        | ClubId              | 0     |
      | Card            | IssueDate           | 0     |
      | Card            | ExpirationDate      | 0     |
      | Card            | EffectiveDate       | 0     |
      | Account         | RedeemValue         | 0     |
      | Account         | ExpirationDate      | 0     |
      | Account         | ReasonCode          | 0     |
      | Account         | InitialValue        | 0     |
      | Account         | Remarks             | 0     |
      | Account         | StartDate           | 0     |
      | Account         | ExternalReferenceId | 0     |
    And Update Import Node with respective values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Node with respective values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId | PostalCode | Street1           | StreetNum |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | 1000000009          | 01752      | 112 DENONCOURT ST | 22        |
    And Update Import Node with respective values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
#      And Update Import Node with respective values
#          |Nodes  |Index|Id | StoreTypeId|IsHomeStore|
#          |Store  |0    |1  |2           |true       |
    And Update Import Node with respective values
      | Nodes | Index | Id         | CardStatus |
      | Card  | 0     | 1000000009 | 1          |
#      | Card  | 1     | 1001000026 | 2          | 2056-12-30T00:00:00.000 | 2056-12-30T00:00:00.000 |
#    And Update Import Node with respective values
#      | Nodes   | Index | Id         | Status | StartDate               | EndDate                 |
#      | Segment | 0     | 1001000025 | 1      | 2056-12-30T00:00:00.000 | 2056-12-30T00:00:00.000 |
    And Update Import Node with respective values
      | Nodes   | Index | Id    | EarnValue | InitialValue |
      | Account | 0     | 98980 | 10.0000   | 80.0000      |
#      And Update Import Node with respective values
#          |Nodes    |Index|Id   |Status |StartDate              |EndDate                |
#          |Segment  |0    |1    |2      |2023-01-30T00:00:00.000|2056-12-30T00:00:00.000|

#      And Update Import Node with respective values
#          |Nodes                |Index|PromotionInternalKey     |Action |RegistrationStartDate  |RegistrationEndDate    |
#          |RegisteredPromotion  |0    |1234                     |1      |2023-01-30T00:00:00.000|2056-12-30T00:00:00.000|
    And Create a Import XML file
      | importFilepath   |
      | <importFilepath> |
    And Create a Import Bat File
      | serverBatchFilepath   | batchFileName   | inputFilePath   | workFilePath   | logFile   | errorFile   | batProcess   | serverIP   | serverUserName   | serverPassword   |
      | <serverBatchFilepath> | <batchFileName> | <inputFilePath> | <workFilePath> | <logFile> | <errorFile> | <batProcess> | <serverIP> | <serverUserName> | <serverPassword> |
    And Copy the ImportXML File to the Respective Folder
      | importFilepath   | inputFilePath   | serverIP   |
      | <importFilepath> | <inputFilePath> | <serverIP> |
    When Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn
      | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | importFilepath   | inputFilePath   |
      | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <importFilepath> | <inputFilePath> |
#    Then Validate the ImportXML File Extension Status and Read Log File for err and wrn
#      | importFilepath   | inputFilePath   | serverIP   | logFile   |
#      | <importFilepath> | <inputFilePath> | <serverIP> | <logFile> |

#      And Validate the Data in DataBase
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-08T035033-12.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS |
