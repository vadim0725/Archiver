package com.javarush.task.task31.task3110;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String ReadStr = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            ReadStr = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ReadStr;
    }

    public static int readInt() {
        int ReadInt = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            ReadInt = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ReadInt;
    }
}
