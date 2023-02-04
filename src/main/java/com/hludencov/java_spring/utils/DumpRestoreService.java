package com.hludencov.java_spring.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DumpRestoreService {

    public static void dump(String fileName) throws IOException, InterruptedException {

        String[] config = loadConfig();

//        String executeCmd = String.format("mysqldump -h %s -P %s -B %s -p%s -u %s --set-gtid-purged=OFF --add-drop-database --opt --single-transaction -r %s", config[0], config[1], config[2], config[4], config[3], fileName);
        String executeCmd = String.format("mysqldump -h %s -P %s -B %s -u %s --set-gtid-purged=OFF --add-drop-database --opt --single-transaction -r %s", config[0], config[1], config[2], config[3], fileName);

        DumpRestoreService.exec(executeCmd);
    }

    public static void restore(String fileName) throws IOException, InterruptedException {

        String[] config = loadConfig();

//        String executeCmd = String.format("mysql -h %s -P %s -B %s -p%s -u %s < %s", config[0], config[1], config[2], config[4], config[3], fileName);
        String executeCmd = String.format("mysql -h %s -P %s -B %s -u %s < %s", config[0], config[1], config[2], config[3], fileName);

        DumpRestoreService.exec(executeCmd);
    }

    private static String[] loadConfig() {
        Pattern p = Pattern.compile("//([\\d.\\w]+):(\\d+)/(\\w+)", Pattern.MULTILINE);
        Matcher m = p.matcher(ConfigLoader.load("spring.datasource.url"));
        m.find();
        String host = m.group(1);
        String port = m.group(2);
        String dbname = m.group(3);

        String dbUser = ConfigLoader.load("spring.datasource.username");
        String dbPass = ConfigLoader.load("spring.datasource.password");

        return new String[]{
                host,
                port,
                dbname,
                dbUser,
                dbPass
        };
    }


    private static void exec(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("cmd");

        OutputStream stdin = process.getOutputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        writer.write(command);
        writer.newLine();
        writer.flush();
        writer.close();

        process.waitFor();

    }


}