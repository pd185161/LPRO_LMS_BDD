@ignore
Feature: CPALL_PromotionTestData

	@copyResponselog
	Scenario: Login
		Given Add Login "UserLogin" request Payload
		When User call "Login" API Call with "Post" http Method
		Then Response Status should be 200
		And Extract sessionId from response
#
#	Scenario: Member Service Extract values from DB
#
#		Given Retrieve all the Required parameter values from DataBase

	@copyResponselog
  	Scenario: Create Promotion
#		Given Validate the Promotion Exist
		Given Add CampaignManager "CopyPromotion" Json request Payload with loop 10000
			|memberRegistrationRequired|
			|0                        |
#		When User call "Promotion_JSON" API Call with "Post" http Method
#		Then Response Status should be 201



