package com.LMS.LMSAPIAutomation.Hooks;

import com.LMS.LMSAPIAutomation.Generic.GenericMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;

public class Hooks {


    @Before("@ClrResponselog")
    public void clearResponseLog(){
        System.out.println("ClearResponseLog");
        GenericMethods.clearLogFile("Responselog.txt");

    }

    @After("@copyResponselog")
    public void copyResponseLog(Scenario scenario) throws IOException {
//        System.out.println(scenario.getName());
//        System.out.println(scenarioId);
        String scenarioId=scenario.getId();
        int start = scenarioId.indexOf("features/");
        int end=scenarioId.indexOf(".");
//        System.out.println( "End" + end);
        String[] str = scenarioId.substring(start,end).split("/");
        String featureName = str[1];
//        System.out.println(str[1]);
//        System.out.println("featureName ="+scenarioId.substring(start,end));

        File f1 = new File("./target/ResponseLogs");
        if (!f1.isDirectory()){
            f1.mkdir();
        }

        File f2 = new File("./target/ResponseLogs/"+featureName);
        if (!f2.isDirectory()){
            f2.mkdir();
        }

        String responsefolderpath = f2.getPath();
        System.out.println(f2.getPath());
        GenericMethods.Copyfile("Responselog.txt",responsefolderpath+"\\"+scenario.getName()+".txt");
    }

}
