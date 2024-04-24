/**
 * 
 */
package com.LMS.LMSAPIAutomation.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

/**
 * @author sa185402
 *
 */
public class ReportingUtils {
	
	public static void generateJVMReport() {
		
		File reportOutputDirectory = new File("target/CucumberReports");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/JsonReports/report.json");

		String buildNumber = "1";
		String projectName = "LMS Automation";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration - check javadoc for details
		configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
		// do not make scenario failed when step has status SKIPPED
		configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
		configuration.setBuildNumber(buildNumber);
		// Additional metadata presented on main page
		configuration.addClassifications("Platform", "Windows");
//		configuration.addClassifications("Browser", "Firefox");
//		configuration.addClassifications("Branch", "release/1.0");

		// optionally add metadata presented on main page via properties file
//		List<String> classificationFiles = new ArrayList<>();
//		classificationFiles.add("properties-1.properties");
//		classificationFiles.add("properties-2.properties");
//		configuration.addClassificationFiles(classificationFiles);

		// optionally specify qualifiers for each of the report json files
//		        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
//		        configuration.setQualifier("cucumber-report-1","First report");
//		        configuration.setQualifier("cucumber-report-2","Second report");

		        ReportBuilder reportBuilder=new ReportBuilder(jsonFiles,configuration);
		        @SuppressWarnings("unused")
				Reportable result=reportBuilder.generateReports();
		// and here validate 'result' to decide what to do if report has failed
	}
	
	public static void generateCucumberReport() {
		
		Collection<File> jsonFiles = FileUtils.listFiles(new File("target/JsonReports/"), new String[] {"json"}, true);

	    List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
	    jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

	    Configuration config = new Configuration(new File("target/CucumberReports"), "LMS");

	    ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
	    reportBuilder.generateReports();
	}
	
}
