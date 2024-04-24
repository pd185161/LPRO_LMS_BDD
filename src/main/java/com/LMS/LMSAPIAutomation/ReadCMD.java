package com.LMS.LMSAPIAutomation;



import com.LMS.LMSAPIAutomation.Resources.Log4j;
import com.hierynomus.smbj.SMBClient;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class ReadCMD {

    private static BufferedReader getOutput(Process p) {
    return new BufferedReader(new InputStreamReader(p.getInputStream()));
}

    private static BufferedReader getError(Process p) {
        return new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

    public static void cmd() throws IOException {
                    String[] array = { "cmd", "/C", "start", "PsExec.exe \\\\153.77.180.130 -u HQL\\s_raa_appadmin -p HAha123! cmd /c cd /d C:\\Retalix\\LMS\\HQ Loyalty and Promotions.1\\BatchManager && TestBatch_Jmeter.bat" };
//            Runtime.getRuntime().exec(array);
            String txtfile = "C:\\Users\\sa185402\\OneDrive - NCR Corporation\\Desktop\\CMD.txt";



        String[] command = {"CMD", "/C", "PsExec.exe \\\\153.77.180.130 -u HQL\\s_raa_appadmin -p HAha123! cmd /c \"cd /d C:\\Retalix\\LMS\\HQ Loyalty and Promotions.1\\BatchManager && TestBatch_Jmeter.bat\""};
        List<String> vsArrays = new ArrayList<String>();
        vsArrays.add("CMD");
        vsArrays.add("/C");
        vsArrays.add("PsExec.exe \\\\153.77.180.130 -u HQL\\s_raa_appadmin -p HAha123! cmd /c \"cd /d C:\\Retalix\\LMS\\HQ Loyalty and Promotions.1\\BatchManager && TestBatch_Jmeter.bat\"");


        ProcessBuilder probuilder = new ProcessBuilder(command);
//        probuilder.command(vsArrays);


        //You can set up your work directory
//        probuilder.directory(new File("C:\\GITHUB\\LMSAPIAutomation"));

        Process process = probuilder.start();
         String line;
        StringBuilder line1 = new StringBuilder();
        
         BufferedReader output = getOutput(process);
        BufferedReader error = getError(process);

        //Read out dir output
//        InputStream is = process.getInputStream();
//
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);

//        System.out.printf("Output of running %s is:\n",
//                Arrays.toString(command));
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }

        while ((line = output.readLine()) != null) {
            line1.append(line);
            System.out.println(line);
        }

        while ((line = error.readLine()) != null) {
            line1.append(line);
             System.out.println(line);
        }

        System.out.println(line1);

        //Wait to get exit value
        try {
            int exitValue = process.waitFor();
            System.out.println("\n\nExit Value is " + exitValue);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void cmd_runtime()  {

        try {
                 String[] command = {"CMD", "/C", "PsExec.exe \\\\153.77.180.130 -u HQL\\s_raa_appadmin -p HAha123! cmd /c \"cd /d C:\\Retalix\\LMS\\HQ Loyalty and Promotions.1\\BatchManager && TestBatch_Jmeter.bat\""};

                Process process = Runtime.getRuntime().exec(
                        command);

                StringBuilder output = new StringBuilder();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                    System.out.println(output);
                }

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                    System.out.println(output);
                    System.exit(0);
                } else {
                    //abnormal...
                }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

//            cmd();
//        validateImportFile("\\\\153.77.180.130\\c\\HQL\\EXE\\BatchManager","CustomerImport-2023-04-20T035034-11.xml");
//"\\\\153.77.180.130\\c\\HQL\\EXE\\BatchManager\\MEMBERIMPORT.ERR"
//MEMBERIMPORT.LOG





    }


    }

