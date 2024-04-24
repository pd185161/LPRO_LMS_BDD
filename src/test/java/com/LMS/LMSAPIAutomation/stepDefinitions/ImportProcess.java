package com.LMS.LMSAPIAutomation.stepDefinitions;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import com.LMS.LMSAPIAutomation.Resources.Log4j;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.LMS.LMSAPIAutomation.BusinessScenarios.CustomerImport.*;
import static com.LMS.LMSAPIAutomation.BusinessScenarios.ImportXMLHandling.createXmlFile;
import static com.LMS.LMSAPIAutomation.Generic.GenericMethods.fileTransfer;

public class ImportProcess {

    String xml;

    @Given("Configure Import XML file with following all nodes")
    public void configure_import_xml_file(io.cucumber.datatable.DataTable userTable) {
        xml = importCustomerFileNodes(userTable);
    }

    @Given("Include the addition node to the XML file")
    public void add_node_to_xml_file(io.cucumber.datatable.DataTable userTable) {
        xml = includeNodes(xml,userTable);
    }

    @Given("Update Import Element with respective attribute values")
    public void update_xml_file(io.cucumber.datatable.DataTable userTable) {
        xml = updateAttributes(xml,userTable);
//        System.out.println(xml);
    }

    @Given("Remove respective Attribute from Element")
    public void remove_attribute_from_element(io.cucumber.datatable.DataTable userTable) {
       xml= removeAttributes(xml,userTable);
    }

    @Given("Create a Import XML file")
    public void create_import_xml_file(io.cucumber.datatable.DataTable userTable) {
        createFolder_XMLFile(xml,userTable);
    }

    @When("Create a Import Bat File")
    public void create_a_import_bat_file(io.cucumber.datatable.DataTable userTable) throws IOException {
        createCustImportBatchFile(userTable);
    }

     @When("Execute the BAT File and Validate the exitValue and ImportXML File Extension Status and Log File for err and wrn")
    public void run_the_bat_file_and_validate_the_exit_value(io.cucumber.datatable.DataTable userTable) {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        int code = executeCommand(userTable);

        if(code !=0){
             String destination="\\\\"+att.get(0).get("serverIP").toString()+"\\"+att.get(0).get("inputFilePath").toString().replace(":","");
             String batchLogFileName = "\\\\"+att.get(0).get("serverIP").toString()+"\\"+att.get(0).get("logFile").toString().replace(":","");
             String batchErrFileName = "\\\\"+att.get(0).get("serverIP").toString()+"\\"+att.get(0).get("errLogFile").toString().replace(":","");
             String[] importFileName = att.get(0).get("importFilepath").toString().split("BatchFiles/");
             //String importFilePath, String importFileName,String batchLogFileName, String serverLogFilePath
             String status = GenericMethods.validateImportFile(destination,importFileName[1],"Log_"+importFileName[1],"Err_"+importFileName[1],batchLogFileName,batchErrFileName);

             Assert.assertEquals(status.toLowerCase(),att.get(0).get("ExpectedResult"),"Import Process is failed, as the we received status as "+status +" Expected is "+att.get(0).get("ExpectedResult"));
        }

    }

    @When("Copy the ImportXML File to the Respective Folder")
    public void copy_the_import_xml_file_to_the_respective_folder(io.cucumber.datatable.DataTable userTable) {

         List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

         try{

             String destination="\\\\"+att.get(0).get("serverIP").toString()+"\\"+att.get(0).get("inputFilePath").toString().replace(":","");
             String[] importFileName = att.get(0).get("importFilepath").toString().split("BatchFiles/");
             String destinationPath = destination+"\\"+importFileName[1];
             createXmlFile(destinationPath, xml);

        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }

    }

    @Then("Validate the Data in DataBase")
    public void validate_the_data_in_data_base() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
