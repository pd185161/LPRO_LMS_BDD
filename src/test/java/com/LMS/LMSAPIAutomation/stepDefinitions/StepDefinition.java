/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;


import com.LMS.LMSAPIAutomation.BusinessScenarios.BusinessScenarios;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


/**
 * @author sa185402
 *
 */
public class StepDefinition extends BaseClass {
	
	BusinessScenarios scenario = new BusinessScenarios();
	
	@When("User call {string} API Call with {string} http Method")
	public void user_call_api_call_with_post_http_method(String resources, String Method) {
		
		if (Method.equalsIgnoreCase("POST")) {
			postMethod(resources);
		}else if(Method.equalsIgnoreCase("GET")) {
			getMethod(resources);
		}
		
	}
	
	@Then("Response Status should be {int}")
	public void response_status_should_be(Integer int1) {
		responseValidation(int1);
	}
	
}
