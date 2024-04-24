/**
 * 
 */
package com.LMS.LMSAPIAutomation.stepDefinitions;

import java.io.File;

import com.LMS.LMSAPIAutomation.BusinessScenarios.BusinessScenarios;
import com.LMS.LMSAPIAutomation.Generic.ExcelHandeling;
import com.LMS.LMSAPIAutomation.Resources.Log4j;
import com.LMS.LMSAPIAutomation.Resources.ReadProperties;

import io.cucumber.java.en.Given;

/**
 * @author sa185402
 *
 */
public class US7733 {

	ReadProperties prop = new ReadProperties();
	
	@Given(": Get File Names in Bin Folder")
	public void get_file_names_in_bin_folder() {
	   
		File directory = new File("C:\\Manoj\\LMS CR Testing\\New folder\\Loyalty_Classification");
		File[] files = directory.listFiles();
		for (File file : files) {
			String Filename = file.getName();
			//System.out.println(Filename);
			
			int index = Filename.lastIndexOf('.');
			if (index >0 ) {
				String extension = Filename.substring(index+1);
				
				ExcelHandeling.writeExcel("C:\\Users\\sa185402\\OneDrive - NCR Corporation\\Desktop\\US7733.xlsx", "Loyalty_Classification",Filename, extension);
			}
		}
		
	}
	
//	@Then(": Write the Names in the Excel File")
//	public void write_the_names_in_the_excel_file() {
//	   BusinessScenarios.signer_DocuSign();
//	}
	
	
	@Given("dll file signature Validation in folder {string}")
	public void dll_file_signature_validation_in_folder(String folderName) {
		
		Log4j.loginfo("------------------------------------------- :::: Validating Files From Folder :::: " + folderName + " :::: -----------------------------------------------------------");
		String path_Signerjarfile = "C:\\Users\\sa185402\\Downloads\\signer.jar";
		BusinessScenarios.signer_DocuSign(path_Signerjarfile,folderName);
	}

}
