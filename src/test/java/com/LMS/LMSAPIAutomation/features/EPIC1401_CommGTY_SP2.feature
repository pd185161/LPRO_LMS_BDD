@10_7_14 @Security @LOYPRO_1401 @CommGty
Feature: EPIC 1401 Use Access Token in Communication Gateway

  # 1.Login WS -UserLogin Method
  @copyResponselog
  Scenario: Login
    Given Add Login "UserLogin" request Payload
    When User call "Login" API Call with "Post" http Method
    Then Response Status should be 200
    And Extract sessionId from response

  Scenario: Member Service Extract values from DB
    Given Retrieve all the Required parameter values from DataBase

#############################################################################################
  # SP value 2 #
  Scenario: Update SystemParameter
    Given Update System Parameter "Security_CommGty_AuthenticationMethod" value to "2" in "MP" DB
#############################################################################################
#    SP =2 , Basic Authentication, Valid Credentials
  @copyResponselog @StoreAndForwardMessageService
  Scenario: StoreAndForwardMessageService_StoreData_SP_2_BasicAuth_ValidCreds
    Given System uses "Basic" authorization with "Valid" credentials
      | Credentials             |
      | s_raa_appadmin:HAha123! |
    Given Add CommGTY Messages "MSG3" request Payload
      | earnValue | rdmValue | openBalance | TicketTotal |
      | 31.9000   | 2.3000   | 0.0000      | 21.05       |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200

#    SP =2 , Basic Authentication, Invalid Credentials
  @copyResponselog @StoreAndForwardMessageService
  Scenario: StoreAndForwardMessageService_StoreData_SP_2_BasicAuth_InvalidCreds
    Given System uses "Basic" authorization with "Invalid" credentials
      | Credentials            |
      | s_raa_appadmin:HAha123 |
    Given Add CommGTY Messages "MSG3" request Payload
      | earnValue | rdmValue | openBalance | TicketTotal |
      | 31.9000   | 2.3000   | 0.0000      | 21.05       |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200

#  SP =2 , AccessToken Authentication, Valid Credentials
  @copyResponselog @StoreAndForwardMessageService
  Scenario: StoreAndForwardMessageService_StoreData_SP_2_AccessTokenAuth_ValidCreds
    Given System uses "AccessToken" authorization with "Valid" credentials
      |  |
      |  |
    Given Add CommGTY Messages "MSG3" request Payload
      | earnValue | rdmValue | openBalance | TicketTotal |
      | 31.9000   | 2.3000   | 0.0000      | 21.05       |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200

#  SP =2 , AccessToken Authentication, Invalid Credentials
  @copyResponselog @StoreAndForwardMessageService
  Scenario: StoreAndForwardMessageService_StoreData_SP_2_AccessTokenAuth_InvalidCreds
    Given System uses "AccessToken" authorization with "Invalid" credentials
      |  |
      |  |
    Given Add CommGTY Messages "MSG3" request Payload
      | earnValue | rdmValue | openBalance | TicketTotal |
      | 31.9000   | 2.3000   | 0.0000      | 21.05       |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200

#  SP =2 , No Authentication
  @copyResponselog @StoreAndForwardMessageService
  Scenario: StoreAndForwardMessageService_StoreData_SP_2_NoAuth
    Given System uses "" authorization with "" credentials
      |  |
      |  |
    Given Add CommGTY Messages "MSG3" request Payload
      | earnValue | rdmValue | openBalance | TicketTotal |
      | 31.9000   | 2.3000   | 0.0000      | 21.05       |
    When User call "Message" API Call with "Post" http Method
    Then Response Status should be 200

#############################################################################################

#  SP =2 , Basic Authentication, Valid Credentials
  @copyResponselog @DataProvider @GetRetailers
  Scenario: DataProvider_GetRetailers_SP_2_BasicAuth_ValidCreds
    Given System uses "Basic" authorization with "Valid" credentials
      | Credentials             |
      | s_raa_appadmin:HAha123! |
    Given Add CommGTY "GetRetailers" request Payload
    When User call "DataProvider_Retailers" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , Basic Authentication, Invalid Credentials
  @copyResponselog @DataProvider @GetRetailers
  Scenario: DataProvider_GetRetailers_SP_2_BasicAuth_InvalidCreds
    Given System uses "Basic" authorization with "Invalid" credentials
      | Credentials        |
      | s_raa_appadmin:HAh |
    Given Add CommGTY "GetRetailers" request Payload
    When User call "DataProvider_Retailers" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , AccessToken Authentication, Valid Credentials
  @copyResponselog @DataProvider @GetRetailers
  Scenario: DataProvider_GetRetailers_SP_2_AccessTokenAuth_ValidCreds
    Given System uses "AccessToken" authorization with "Valid" credentials
      |  |
      |  |
    Given Add CommGTY "GetRetailers" request Payload
    When User call "DataProvider_Retailers" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , AccessToken Authentication, Invalid Credentials
  @copyResponselog @DataProvider @GetRetailers
  Scenario: DataProvider_GetRetailers_SP_2_AccessTokenAuth_InvalidCreds
    Given System uses "AccessToken" authorization with "Invalid" credentials
      |  |
      |  |
    Given Add CommGTY "GetRetailers" request Payload
    When User call "DataProvider_Retailers" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , No Authentication
  @copyResponselog @DataProvider @GetRetailers
  Scenario: DataProvider_GetRetailers_SP_2_NoAuth
    Given System uses "" authorization with "" credentials
      |  |
      |  |
    Given Add CommGTY "GetRetailers" request Payload
    When User call "DataProvider_Retailers" API Call with "Get" http Method
    Then Response Status should be 200

#############################################################################################

#  SP =2 , Basic Authentication, Valid Credentials
  @copyResponselog @DataProvider
  Scenario: DataProvider_GetRetailer-Stores_SP_2_BasicAuth_ValidCreds
    Given System uses "Basic" authorization with "Valid" credentials
      | Credentials             |
      | s_raa_appadmin:HAha123! |
    Given Add CommGTY "GetRetailer-Stores" request Payload
    When User call "DataProvider_RetailerStores" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , Basic Authentication, Invalid Credentials
  @copyResponselog @DataProvider
  Scenario: DataProvider_GetRetailer-Stores_SP_2_BasicAuth_InvalidCreds
    Given System uses "Basic" authorization with "Invalid" credentials
      | Credentials        |
      | s_raa_appadmin:HAh |
    Given Add CommGTY "GetRetailer-Stores" request Payload
    When User call "DataProvider_RetailerStores" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , AccessToken Authentication, Valid Credentials
  @copyResponselog @DataProvider
  Scenario: DataProvider_GetRetailer-Stores_SP_2_AccessTokenAuth_ValidCreds
    Given System uses "AccessToken" authorization with "Valid" credentials
      |  |
      |  |
    Given Add CommGTY "GetRetailer-Stores" request Payload
    When User call "DataProvider_RetailerStores" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , AccessToken Authentication, Invalid Credentials
  @copyResponselog @DataProvider
  Scenario: DataProvider_GetRetailer-Stores_SP_2_AccessTokenAuth_InvalidCreds
    Given System uses "AccessToken" authorization with "Invalid" credentials
      |  |
      |  |
    Given Add CommGTY "GetRetailer-Stores" request Payload
    When User call "DataProvider_RetailerStores" API Call with "Get" http Method
    Then Response Status should be 200

#    SP =2 , No Authentication
  @copyResponselog @DataProvider
  Scenario: DataProvider_GetRetailer-Stores_SP_2_NoAuth
    Given System uses "" authorization with "" credentials
      |  |
      |  |
    Given Add CommGTY "GetRetailer-Stores" request Payload
    When User call "DataProvider_RetailerStores" API Call with "Get" http Method
    Then Response Status should be 200

#############################################################################################

#  SP =2 , Basic Authentication, Valid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Ping_SP_2_BasicAuth_ValidCreds
    Given System uses "Basic" authorization with "Valid" credentials
      | Credentials             |
      | s_raa_appadmin:HAha123! |
    Given Add CommGTY "CDMAdapter_Ping" request Payload
    When User call "CDMAdapter_Ping" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , Basic Authentication, Invalid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Ping_SP_2_BasicAuth_InvalidCreds
    Given System uses "Basic" authorization with "Invalid" credentials
      | Credentials        |
      | s_raa_appadmin:HAh |
    Given Add CommGTY "CDMAdapter_Ping" request Payload
    When User call "CDMAdapter_Ping" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , AccessToken Authentication, Valid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Ping_SP_2_AccessTokenAuth_ValidCreds
    Given System uses "AccessToken" authorization with "Valid" credentials
      |  |
      |  |
    Given Add CommGTY "CDMAdapter_Ping" request Payload
    When User call "CDMAdapter_Ping" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , AccessToken Authentication, Invalid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Ping_SP_2_AccessTokenAuth_InvalidCreds
    Given System uses "AccessToken" authorization with "Invalid" credentials
      |  |
      |  |
    Given Add CommGTY "CDMAdapter_Ping" request Payload
    When User call "CDMAdapter_Ping" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , No Authentication
  @copyResponselog
  Scenario: CDMAdapter_Ping_SP_2_NoAuth
    Given System uses "" authorization with "" credentials
      |  |
      |  |
    Given Add CommGTY "CDMAdapter_Ping" request Payload
    When User call "CDMAdapter_Ping" API Call with "Get" http Method
    Then Response Status should be 401
#############################################################################################

#  SP =2 , Basic Authentication, Valid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Consumer_SP_2_BasicAuth_ValidCreds
    Given System uses "Basic" authorization with "Valid" credentials
      | Credentials             |
      | s_raa_appadmin:HAha123! |
    Given Add CommGTY "CDMAdapter_Consumer" request Payload
    When User call "CDMAdapter_Consumer" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , Basic Authentication, Invalid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Consumer_SP_2_BasicAuth_InvalidCreds
    Given System uses "Basic" authorization with "Invalid" credentials
      | Credentials        |
      | s_raa_appadmin:HAh |
    Given Add CommGTY "CDMAdapter_Consumer" request Payload
    When User call "CDMAdapter_Consumer" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , AccessToken Authentication, Valid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Consumer_SP_2_AccessTokenAuth_ValidCreds
    Given System uses "AccessToken" authorization with "Valid" credentials
      |  |
      |  |
    Given Add CommGTY "CDMAdapter_Consumer" request Payload
    When User call "CDMAdapter_Consumer" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , AccessToken Authentication, Invalid Credentials
  @copyResponselog
  Scenario: CDMAdapter_Consumer_SP_2_AccessTokenAuth_InvalidCreds
    Given System uses "AccessToken" authorization with "Invalid" credentials
      |  |
      |  |
    Given Add CommGTY "CDMAdapter_Consumer" request Payload
    When User call "CDMAdapter_Consumer" API Call with "Get" http Method
    Then Response Status should be 401

#    SP =2 , No Authentication
  @copyResponselog
  Scenario: CDMAdapter_Consumer_SP_2_NoAuth
    Given System uses "" authorization with "" credentials
      |  |
      |  |
    Given Add CommGTY "CDMAdapter_Consumer" request Payload
    When User call "CDMAdapter_Consumer" API Call with "Get" http Method
    Then Response Status should be 401
#############################################################################################