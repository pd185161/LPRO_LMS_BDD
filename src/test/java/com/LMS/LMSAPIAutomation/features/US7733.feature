Feature: US7733

  Scenario Outline: US7733
#	Given : Get File Names in Bin Folder
#	Then : Write the Names in the Excel File
    Given dll file signature Validation in folder "<folderName>"
    Examples:
      | folderName                                                                                              |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup/Packages/x64                     |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup/redist/dotnet/Updates/Server2003 |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup/redist/dotnet/Updates/Server2008 |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup/redist/msi/x64                   |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup/redist/msi/x86                   |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup/Setup                            |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup/Setup/SSF                        |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Setup                                  |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Support/net4/Win32                     |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Support/net4/x64                       |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Support/Win32                          |
      | C:/Manoj/LMS_CR_Testing/CodeSignature_CruiseControl/1726/Install/Support/x64                            |

#  Scenario Outline: Earn Points Total
#    Given Add Member Accounts "Earn_Point_Total" Payload
#    And Copy File "<Sourcepath>" to "<DestinationPath>" LPE Folder
#    When User call "Total" API Call with Post http Method
#    Then Response Status should be 200
#    And Validate the attributes in the Response of Earn point Total
#    Examples:
#      | Sourcepath                                            | DestinationPath         |
#      | C:/LPETestdata/MemberAccounts/Msg2_Testcase1/Msg2.xml | C:/Retalix/LPE/Msg2.xml |
