package com.LMS.LMSAPIAutomation.stepDefinitions;
import com.LMS.LMSAPIAutomation.BusinessScenarios.Resources;
import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.Generic.XMLExtraction;
import com.LMS.LMSAPIAutomation.Resources.Log4j;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.LMS.LMSAPIAutomation.BusinessScenarios.CustomerImport.executeCommand;
import static com.LMS.LMSAPIAutomation.BusinessScenarios.MemberExport.*;
import static com.LMS.LMSAPIAutomation.Generic.GenericMethods.*;
import static org.testng.Assert.*;

public class ExportProcess {
    @Given("Create an export Bat File")
    public void create_an_export_bat_file(io.cucumber.datatable.DataTable userTable) throws IOException {
        createMemberExportBatchFile(userTable);
    }
    @When("Execute the BAT File and Validate the exitValue")
    public void run_the_bat_file_and_validate_the_exit_value(io.cucumber.datatable.DataTable userTable) throws IOException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        int code = executeCommand(userTable);

        String batchLogFileName = "\\\\"+att.get(0).get("serverIP").toString()+"\\"+att.get(0).get("logFile").toString().replace(":","");
        String batchErrFileName = "\\\\"+att.get(0).get("serverIP").toString()+"\\"+att.get(0).get("errLogFile").toString().replace(":","");

        if(code==0)
        {
            //Read log file and take the file names
            String logcontent = ReadImportExportLogFileContent(batchLogFileName);
            System.out.println("-------------------------File data starts here----------------------------");
            System.out.println(logcontent);
            System.out.println("-------------------------File data ends here------------------------------");
        }
        else {
            String errorlogcontent =ReadImportExportLogFileContent(batchErrFileName);
            System.out.println("-------------------------Error File data starts here----------------------------");
            System.out.println(errorlogcontent);
            System.out.println("-------------------------Error File data ends here------------------------------");
        }
        Resources.parameters.put("batchLogFileName", batchLogFileName);
    }

    @And("Validate promotion in the export file")
    public void validatePromotionInTheExportFile(io.cucumber.datatable.DataTable userTable) throws IOException, ParserConfigurationException, SAXException {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);


        String batchLogFileName = Resources.parameters.get("batchLogFileName");
        String logText = ReadImportExportLogFileContent(batchLogFileName);

        List<String> exportFileNames= GetExportFileNamesFromLogtext(logText);
        if(exportFileNames.size()==0)
        {
            System.out.println("No file exported.");
            assertFalse(true,"no file exported. please check");

        }
        else
        {
            boolean flag = false;
            for(String filename : exportFileNames)
            {
                String exportfilefullpath = "\\\\"+att.get(0).get("serverIP").toString()+"\\"+filename.toString().replace(":","");
                //String Status = XMLExtraction.Extraction(response_Pretty, "SaveDemographicResult", 0, "Status");
                String exportfileData = ReadFile(exportfilefullpath);
                System.out.println(exportfileData);
                String promotionExternalID = XMLExtraction.Extraction(exportfileData, "RegisteredPromotion", 0, "RegisteredPromotionExternal");
                if(promotionExternalID.equalsIgnoreCase(Resources.parameters.get("promotionExternalId")))
                {
                    flag = true;
                }
            }
            assertEquals(flag, true, "Registered Promotion validation against the Member Export failed.");
        }
    }

    @And("Validate no export file generated")
    public void validateNoExportFileGenerated() throws IOException {
        String batchLogFileName = Resources.parameters.get("batchLogFileName");
        String logText = ReadImportExportLogFileContent(batchLogFileName);

        List<String> exportFileNames= GetExportFileNamesFromLogtext(logText);
        assertEquals(exportFileNames.size(), 0, "No export file should be created. created "+exportFileNames.size()+" no of export files");
    }
}
