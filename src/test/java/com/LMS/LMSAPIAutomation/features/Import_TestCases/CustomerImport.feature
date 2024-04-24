Feature: Customer Import General Validations

  @CustomerImport
  Scenario Outline: ID_48 - RetailerId "X" - HouseHoldExternalId "HHHH" - AccountId "AC" defined as "virtual" in the database , points accumulation is not allowed on this Account
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
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
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId   | PostalCode | Street1           | StreetNum |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | <HouseHoldExternalId> | 01752      | 112 DENONCOURT ST | 22        |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id         | CardStatus |
      | Card  | 0     | 1000000009 | 1          |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id | EarnValue |
      | Account | 0     | 6  | 10.0000   |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | wrn            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             | HouseHoldExternalId |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-10T035033-48.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS | 1000000009          |

  @CustomerImport
  Scenario Outline: ID_49 - RetailerId "X" - HouseHoldExternalId "HHHH" - AccountId "AC" - ReasonCode "ACReason" does not exists in the database as active ReasonCode, therefore points accumulated on this Account Id will not be updated
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element         | attributeName       | index |
      | LoyaltyCustomer | SystemSource        | 0     |
      | Retailer        | ClubId              | 0     |
      | Card            | IssueDate           | 0     |
      | Card            | ExpirationDate      | 0     |
      | Card            | EffectiveDate       | 0     |
      | Account         | RedeemValue         | 0     |
      | Account         | ExpirationDate      | 0     |
      | Account         | InitialValue        | 0     |
      | Account         | Remarks             | 0     |
      | Account         | StartDate           | 0     |
      | Account         | ExternalReferenceId | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId | PostalCode | Street1           | StreetNum | Extension | Remarks    |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | 1000000009          | 01752      | 112 DENONCOURT ST | 22        | 10        | Automation |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id         | CardStatus |
      | Card  | 0     | 1000000009 | 1          |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id    | EarnValue | ReasonCode |
      | Account | 0     | 85103 | 10.0000   | 50         |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | wrn            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-10T035033-49.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS |

  @CustomerImport
  Scenario Outline: ID_65 - RetailerId "X" - HouseHoldExternalId "HHHH" - AccountId "AC" Start Date must be greater than current date, record will not be imported
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element         | attributeName       | index |
      | LoyaltyCustomer | SystemSource        | 0     |
      | Retailer        | ClubId              | 0     |
      | Card            | IssueDate           | 0     |
      | Card            | ExpirationDate      | 0     |
      | Card            | EffectiveDate       | 0     |
      | Account         | RedeemValue         | 0     |
      | Account         | ExpirationDate      | 0     |
      | Account         | InitialValue        | 0     |
      | Account         | Remarks             | 0     |
      | Account         | ExternalReferenceId | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId | PostalCode | Street1           | StreetNum | Extension | Remarks    |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | 1000000009          | 01752      | 112 DENONCOURT ST | 22        | 10        | Automation |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id         | CardStatus |
      | Card  | 0     | 1000000009 | 1          |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id    | EarnValue | ReasonCode | StartDate  |
      | Account | 0     | 85103 | 10.0000   | 3          | 1957-08-13 |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | wrn            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-10T035033-65.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS |

  @CustomerImport
  Scenario Outline: ID_76 - RetailerId "X" - HouseHoldExternalId "HHHH" does not have active cards, therefore points accumulated on account "AC" will not be updated
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
    And Remove respective Attribute from Element
      | element         | attributeName  | index |
      | LoyaltyCustomer | SystemSource   | 0     |
      | Retailer        | ClubId         | 0     |
      | Card            | IssueDate      | 0     |
      | Card            | ExpirationDate | 0     |
      | Card            | EffectiveDate  | 0     |
      | Account         | RedeemValue    | 0     |
      | Account         | ExpirationDate | 0     |
      | Account         | Remarks        | 0     |
      | Account         | ReasonCode     | 0     |
      | Account         | StartDate      | 0     |
      | Account         | ExpirationDate | 0     |
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId | PostalCode | Street1           | StreetNum | Extension | Remarks    |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | 1000000009          | 01752      | 112 DENONCOURT ST | 22        | 10        | Automation |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id         | CardStatus |
      | Card  | 0     | 1000000009 | 1          |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id    | EarnValue | InitialValue |
      | Account | 0     | 98980 | 10.0000   | 80           |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | wrn            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-10T035033-76.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS |

  @CustomerImport
  Scenario Outline: ID_12 - RetailerId "X" - HouseHoldExternalId "HHHH" - MemberExternalId "MMMM" - CardId "CCCC" exists more than once in the import file
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
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
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId   | PostalCode | Street1           | StreetNum |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | <HouseHoldExternalId> | 01752      | 112 DENONCOURT ST | 22        |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id         | CardStatus |
      | Card  | 0     | 1000000009 | 1          |
      | Card  | 1     | 1000000009 | 1          |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id | EarnValue |
      | Account | 0     | 6  | 10.0000   |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | wrn            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             | HouseHoldExternalId |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-25T035033-12.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS | 1000000009          |

  @CustomerImport
  Scenario Outline: ID_13 - RetailerId "'X" - HouseHoldExternalId "HHHH" - MemberExternalId "MMMM" - CardId "CCCC" has invalid status
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
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
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId   | PostalCode | Street1           | StreetNum |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | <HouseHoldExternalId> | 01752      | 112 DENONCOURT ST | 22        |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id         | CardStatus |
      | Card    | 0     | 1000000009 | 7          |
      #CardStatus7 is invalidAnd Update Import Element with respective attribute values
      | Nodes   | Index | Id         | EarnValue  |
      | Account | 0     | 6          | 10.0000    |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | wrn            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             | HouseHoldExternalId |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-25T035033-13.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS | 1000000009          |

  @CustomerImport
  Scenario Outline: ID_15 - RetailerId "X" - HouseHoldExternalId "HHHH" - MemberExternalId "MMMM" - CardId "CCCC" doesn't fit one of the Loyalty Cards format
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
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
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId   | PostalCode | Street1           | StreetNum |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | <HouseHoldExternalId> | 01752      | 112 DENONCOURT ST | 22        |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000009       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id                | CardStatus |
      | Card    | 0     | TestAutomation123 | 1          |
      #CardStatus7 is invalidAnd Update Import Element with respective attribute values
      | Nodes   | Index | Id                | EarnValue  |
      | Account | 0     | 6                 | 10.0000    |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | wrn            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             | HouseHoldExternalId |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-25T035033-15.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS | 1000000009          |

  @CustomerImport
  Scenario Outline: SuccessFul Customer Import
    Given Configure Import XML file with following all nodes
      | NodesNotRequired     | SampleXMLFle     |
      | Notes                | <sampleFilePath> |
      | FamilyMembers        | <sampleFilePath> |
      | MemberAttributes     | <sampleFilePath> |
      | Stores               | <sampleFilePath> |
      | MemberSegments       | <sampleFilePath> |
      | HouseHoldSegments    | <sampleFilePath> |
      | RegisteredPromotions | <sampleFilePath> |
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
    And Update Import Element with respective attribute values
      | Nodes    | Index | Id |
      | Retailer | 0     | 1  |
    And Update Import Element with respective attribute values
      | Nodes     | Index | City | Country | State | EMailAddress | HouseHoldExternalId   | PostalCode | Street1           | StreetNum |
      | HouseHold | 0     | Roma | 100     | 5     | Auto@a.com   | <HouseHoldExternalId> | 01752      | 112 DENONCOURT ST | 22        |
    And Update Import Element with respective attribute values
      | Nodes  | Index | FirstName  | LastName  | Gender | EMailAddress | MemberExternalId | MemberStatus | MobilePhoneNumber | WorkPhoneNumber | Remarks    |
      | Member | 0     | AUTO_First | AUTO_Last | 1      | Auto@a.com   | 1000000077       | 1            | 992172            | 24324           | Automation |
    And Update Import Element with respective attribute values
      | Nodes | Index | Id         | CardStatus |
      | Card  | 0     | 1000000077 | 1          |
    And Update Import Element with respective attribute values
      | Nodes   | Index | Id | EarnValue |
      | Account | 0     | 1  | 10.0000   |
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
      | ExpectedResult | serverBatchFilepath   | batchFileName   | serverIP   | serverUserName   | serverPassword   | logFile   | errLogFile  | importFilepath   | inputFilePath   |
      | scc            | <serverBatchFilepath> | <batchFileName> | <serverIP> | <serverUserName> | <serverPassword> | <logFile> | <errorFile> | <importFilepath> | <inputFilePath> |
    Examples:
      | sampleFilePath                        | importFilepath                                              | serverBatchFilepath                                     | batchFileName       | serverIP       | serverUserName     | serverPassword | inputFilePath             | workFilePath              | logFile                                  | errorFile                                | batProcess             | HouseHoldExternalId |
      | ./resources/#SampleCustomerImport.xml | ./target/BatchFiles/CustomerImport-2023-05-24T035033-99.xml | C:\Retalix\LMS\HQ Loyalty and Promotions.1\BatchManager | Auto_CustomerImport | 153.77.180.130 | HQL\s_raa_appadmin | HAha123!       | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\\ | C:\HQL\EXE\BatchManager\MemberImport.log | C:\HQL\EXE\BatchManager\MemberImport.err | IMPORTLOYALTYCUSTOMERS | 1000000077          |

