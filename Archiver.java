package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) {
        BufferedReader savePath = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader ArchPath = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fullPathSave = savePath.readLine();
            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(fullPathSave));
            String fullPathOpen = ArchPath.readLine();
            zipFileManager.createZip(Paths.get(fullPathOpen));
            } catch (Exception e) {
            e.printStackTrace();
        }

        ExitCommand exitCommand = new ExitCommand();
        try {
            exitCommand.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
