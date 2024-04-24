/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.LMS.LMSAPIAutomation.Resources.ReadProperties;
import com.LMS.LMSAPIAutomation.Utilities.EndPointURLS;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author sa185402
 *
 */
public class BaseClass {
	
	public static RequestSpecification req;
	public static ResponseSpecification res;
	public static Response response;
	public static boolean query = false;
	ReadProperties prop = new ReadProperties();
	
//	,Map<String, String> Headers
	public RequestSpecification RequestSpecification(String contentType) throws FileNotFoundException {
		
//		if(req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Responselog.txt"));
			req = new RequestSpecBuilder().setBaseUri(prop.getBaseURL())
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
//					.setConfig(RestAssured.config().encoderConfig(new EncoderConfig().defaultCharsetForContentType("UTF-8", contentType)))
					.setAccept(ContentType.ANY)
					.build();
//		}
		return req;
	}

	public void add_payload(Object requestBody, Map<String,String> Headers, String contentType) throws FileNotFoundException {
        //"application/json"
        //8001
		if (req != null) {
			FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) req;
			 filterableRequestSpecification.removeHeaders();
			 filterableRequestSpecification.removeQueryParam("sk");


		}

        req = given().spec(RequestSpecification(contentType))
        		.headers(Headers)
                .body(requestBody);
    }
	
	public void add_payload(String requestBody, Map<String,String> Headers, String contentType) throws FileNotFoundException {
        //"application/json"
        //8001
		if (req != null) {
			FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) req;
			 filterableRequestSpecification.removeHeaders();
			 filterableRequestSpecification.removeQueryParam("sk");
			 
			
		}
		
        req = given().spec(RequestSpecification(contentType))
        		.headers(Headers)
                .body(requestBody);
    }
	
	public void add_payload(String requestBody, Map<String,String> Headers, String contentType, String sessionId) throws FileNotFoundException {
        //"application/json"
        //8001
		
		if (req != null) {
			FilterableRequestSpecification filterableRequestSpecification = (FilterableRequestSpecification) req;
			 filterableRequestSpecification.removeHeaders();
			 filterableRequestSpecification.removeQueryParam("sk");
		}

		if(prop.accessToken().equalsIgnoreCase("0")){
			req = given().spec(RequestSpecification(contentType))
					.headers(Headers)
					.contentType(contentType)
					.queryParam("sk", sessionId)
					.body(requestBody);
		}else if(prop.accessToken().equalsIgnoreCase("1")){
			req = given().spec(RequestSpecification(contentType))
					.headers(Headers)
					.body(requestBody);
		}
    }
	
	public void postMethod(String resources) {
		EndPointURLS APIResources = EndPointURLS.valueOf(resources);
		response = req.when().post(APIResources.getResources());
	}
	
	public void getMethod(String resources) {
		EndPointURLS APIResources = EndPointURLS.valueOf(resources);
		response = req.when().get(APIResources.getResources());
		
	}
	
	public void responseValidation(int expected_Response) {
		assertEquals(response.getStatusCode(), expected_Response, "Respone Status Code is not as per expected : actual Value : " + response.getStatusCode());
	}
}
