package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) {
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        } while (operation != Operation.EXIT);
    }

    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("Выберите операцию:\n " +
                        Operation.CREATE.ordinal() + " - упаковать файлы в архив\n" +
                        Operation.ADD.ordinal() + "1 - добавить файл в архив\n" +
                        Operation.REMOVE.ordinal() + "2 - удалить файл из архива\n" +
                        Operation.EXTRACT.ordinal() + "3 - распаковать архив\n" +
                        Operation.CONTENT.ordinal() + "4 - просмотреть содержимое архива\n" +
                        Operation.EXIT.ordinal() + "5 - выход");

        switch (ConsoleHelper.readInt()) {
            case 0:
                return Operation.CREATE;
            case 1:
                return Operation.ADD;
            case 2:
                return Operation.REMOVE;
            case 3:
                return Operation.EXTRACT;
            case 4:
                return Operation.CONTENT;
            case 5:
                return Operation.EXIT;
            default:
                throw new IllegalStateException("Unexpected value: " + ConsoleHelper.readInt());
        }
    }
}
