package com.LMS.LMSAPIAutomation.BusinessScenarios;

import com.LMS.LMSAPIAutomation.Resources.Log4j;
import org.testng.Assert;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import static com.LMS.LMSAPIAutomation.BusinessScenarios.ImportXMLHandling.*;
import static com.LMS.LMSAPIAutomation.Generic.GenericMethods.cmd_ProcessBuilder;

public class CustomerImport {

    public static String importCustomerFileNodes(io.cucumber.datatable.DataTable userTable){

        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        String allNoded = "";

        try {
                String rootNode="";
                if(att.get(0).get("SampleXMLFle").toString().toLowerCase().contains("customer"))
                {
                    rootNode="/LoyaltyCustomer";
                }
                else if(att.get(0).get("SampleXMLFle").toString().toLowerCase().contains("promotion"))
                {
                    rootNode = "/HQL_Promotion_Interface";
                }
                allNoded = extractNode(att.get(0).get("SampleXMLFle").toString(),rootNode); //

            for(int i =0; i< att.size(); i++){
                allNoded = removeNode(allNoded,att.get(i).get("NodesNotRequired").toString());
            }

            return allNoded;

        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }

        return allNoded;
    }

    public static String includeNodes(String xml, io.cucumber.datatable.DataTable userTable){

        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        String appendedXML = xml;

        try {
            for(int i =0; i< att.size(); i++) {
                appendedXML = appendNode(att.get(0).get("SampleXMLFle").toString(), appendedXML, att.get(i).get("Node").toString(), att.get(i).get("Xpath").toString());
            }
            return appendedXML;

        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }
        return appendedXML;
    }

    public static String updateAttributes(String xml, io.cucumber.datatable.DataTable userTable) {

       try{

           List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

            for(int i =0; i< att.size(); i++) {
                for (Map.Entry<Object, Object> entry: att.get(i).entrySet()) {
                    String key = (String) entry.getKey();
                    String value = (String) entry.getValue();
                    if(!(key.equalsIgnoreCase("Nodes")||key.equalsIgnoreCase("Index"))){
                        xml = updateAttributeValues(xml, Integer.parseInt(att.get(i).get("Index").toString()), att.get(i).get("Nodes").toString(), key.trim(), value.trim());
                    }
                }
            }

        return xml;
       }catch (Exception ignored){
           Log4j.error(ignored.getMessage());
           Assert.fail(ignored.getMessage());
       }

        return "";
    }

    public static void createFolder_XMLFile(String xml, io.cucumber.datatable.DataTable userTable){
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

        try {
            File f1 = new File("./target/BatchFiles");
                if (!f1.isDirectory()){
                    f1.mkdir();
                }

//            for (Map<Object, Object> objectObjectMap : att) {
//                for (Map.Entry<Object, Object> entry : objectObjectMap.entrySet()) {
                    createXmlFile(att.get(0).get("importFilepath").toString(), xml);
//                }
//            }

        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }
    }

    public static void createCustImportBatchFile(io.cucumber.datatable.DataTable userTable){
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

        try {
            StringBuilder bat = new StringBuilder("ReMABatchManager.exe");
            bat.append(" -Itrue");
            bat.append(" -A"+ att.get(0).get("batProcess").toString());
            bat.append(" -N*.xml");
            bat.append(" -F"+ att.get(0).get("inputFilePath").toString());
            bat.append(" -W"+ att.get(0).get("workFilePath").toString());
            bat.append(" -L"+ att.get(0).get("logFile").toString());
            bat.append(" -E"+ att.get(0).get("errorFile").toString());
            bat.append(" "+ "");

            String remoteBatchFilePath="\\\\"+att.get(0).get("serverIP").toString()+"\\"+att.get(0).get("serverBatchFilepath").toString().replace(":","");
            String remoteBatchFile = remoteBatchFilePath+"\\"+att.get(0).get("batchFileName").toString()+".bat";

            File file = new File(remoteBatchFile);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(bat.toString());
            bw.close();

        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }
    }

    public static int executeCommand(io.cucumber.datatable.DataTable userTable){

        int errorCode = 0;

        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

        try {

            StringBuilder psExe = new StringBuilder();
            psExe.append(att.get(0).get("serverBatchFilepath").toString()).append(" && ");
            psExe.append(att.get(0).get("batchFileName").toString()).append(".bat");

            errorCode = cmd_ProcessBuilder("Batchlogs", att.get(0).get("serverIP").toString(), att.get(0).get("serverUserName").toString(), att.get(0).get("serverPassword").toString(), psExe.toString());
            return errorCode;

        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }
        return errorCode;
    }

    public static String removeAttributes(String xml, io.cucumber.datatable.DataTable userTable){

         List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);
        String appendedXML = xml;

        try {
            for(int i =0; i< att.size(); i++) {
                appendedXML = removeAttribute(appendedXML,att.get(i).get("element").toString(),att.get(i).get("attributeName").toString(),Integer.parseInt(att.get(i).get("index").toString()));
            }
        }catch (Exception ignored){
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }
        return appendedXML;
    }

}
