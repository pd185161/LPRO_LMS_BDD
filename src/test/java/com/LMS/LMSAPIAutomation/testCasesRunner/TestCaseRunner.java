/**
 *
 */
package com.LMS.LMSAPIAutomation.testCasesRunner;

import com.LMS.LMSAPIAutomation.Hooks.Hooks;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.Utilities.ReportingUtils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



/**
 * @author sa185402
 *
 */

//@RunWith(Cucumber.class)
//tags = "@Smoke",
///memberService.feature
///GeneralBasic.feature
//src/test/java/com/LPE/APIAutomation/
@CucumberOptions(

		features = "src/test/java/com/LMS/LMSAPIAutomation/features/EPIC1401_CommGTY_SP0.feature"
		,glue = {"com/LMS/LMSAPIAutomation/stepDefinitions", "com/LMS/LMSAPIAutomation/Hooks"}
		,plugin = { "pretty",  "html:target/report.html", "json:target/JsonReports/report.json", "junit:target/JunitReports/report.xml"}
		,monochrome = true
		,tags = "not @ignore"
)

public class TestCaseRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite
	public void clearlog() {
		GenericMethods.clearLogFile("log/Detail.log");
		GenericMethods.clearLogFile("Responselog.txt");
	}

	@AfterSuite
	public void generateReport() {
		ReportingUtils.generateCucumberReport();
	}
}