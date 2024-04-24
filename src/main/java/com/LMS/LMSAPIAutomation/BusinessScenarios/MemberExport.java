package com.LMS.LMSAPIAutomation.BusinessScenarios;
import com.LMS.LMSAPIAutomation.Resources.Log4j;
import org.testng.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.LMS.LMSAPIAutomation.BusinessScenarios.ImportXMLHandling.*;
import static com.LMS.LMSAPIAutomation.Generic.GenericMethods.cmd_ProcessBuilder;

public class MemberExport {

    public static void createMemberExportBatchFile(io.cucumber.datatable.DataTable userTable) {
        List<Map<Object, Object>> att = userTable.asMaps(String.class, String.class);

        try {
            StringBuilder bat = new StringBuilder("ReMABatchManager.exe");
            bat.append(" -Itrue");
            bat.append(" -A" + att.get(0).get("batProcess").toString());
            bat.append(" -O" + att.get(0).get("outputFilePath").toString());
            bat.append(" -W" + att.get(0).get("workFilePath").toString());
            bat.append(" -L" + att.get(0).get("logFile").toString());
            bat.append(" -E" + att.get(0).get("errorFile").toString());
            bat.append(" -R" + Resources.parameters.get("retailerId"));
            bat.append(" /Mode=" + att.get(0).get("exportMode").toString());
            bat.append(" " + "");

            String remoteBatchFilePath = "\\\\" + att.get(0).get("serverIP").toString() + "\\" + att.get(0).get("serverBatchFilepath").toString().replace(":", "");
            String remoteBatchFile = remoteBatchFilePath + "\\" + att.get(0).get("batchFileName").toString() + ".bat";

            File file = new File(remoteBatchFile);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(bat.toString());
            bw.close();

        } catch (Exception ignored) {
            Log4j.error(ignored.getMessage());
            Assert.fail(ignored.getMessage());
        }
    }

    public static String ReadImportExportLogFileContent(String filePath) throws IOException {
        File file = new File(filePath);

        String str;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int startlinenumber = 0;
        int endlinenumber = 0;
        int linecounter = 1;
        while ((str = br.readLine()) != null) {

            if (str.contains("======================Start")) {
                startlinenumber = linecounter;
                //System.out.println(Integer.toString(startlinenumber));
            }
            if (str.contains("==============End")) {
                endlinenumber = linecounter;
                //System.out.println(Integer.toString(endlinenumber));
            }
            linecounter++;
        }

        String logFileText = "";

        String line;
        for (int j = startlinenumber + 1; j < endlinenumber; j++) {
            FileReader fr1 = new FileReader(file);
            BufferedReader br1 = new BufferedReader(fr1);
            for (int i = 1; i < j; i++) {
                br1.readLine();
            }
            line = br1.readLine();
            logFileText = logFileText.concat("\n" + line);
        }

//        System.out.println("-------------------------File data starts here----------------------------");
//        System.out.println(logFileText);
//        System.out.println("-------------------------File data ends here------------------------------");
        return logFileText;
    }

    public static List<String> GetExportFileNamesFromLogtext(String logText) {
        String[] lines = logText.split(System.getProperty("line.separator"));

        List<String> sList = new ArrayList<String>();
        for (int i = 0; i < lines.length; i++) {
            //System.out.println(lines[i]);
            if (lines[i].contains("Created members export file")) {
                //sList.add();
                String regPattern = "Created members export file (.*);";
                Pattern r = Pattern.compile(regPattern);
                java.util.regex.Matcher m = r.matcher(lines[i]);
                if (m.find()) {
                    sList.add(m.group(1));
                } else {
                    System.out.println("NO MATCH. No File exported");
                }

            }
        }
        return sList;
    }
}
