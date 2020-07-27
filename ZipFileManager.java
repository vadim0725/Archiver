package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        if (Files.notExists(zipFile.getParent()))
            Files.createDirectories(zipFile.getParent());

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if (Files.isRegularFile(source)) {
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            }
            else if (Files.isDirectory(source)) {
                FileManager fileManager = new FileManager(source);
                List<Path> fileNames = fileManager.getFileList();
                for (Path path : fileNames) {
                    addNewZipEntry(zipOutputStream, source, path);
                }
            } else throw new PathIsNotFoundException();
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        while (in.available() != 0) {
            out.write(in.read());
        }
        out.flush();
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        Path fullPath = filePath.resolve(fileName);

        ZipEntry zipEntry = new ZipEntry(String.valueOf(fileName));
        zipOutputStream.putNextEntry(zipEntry);
        try (InputStream inputStream = Files.newInputStream(fullPath)) {
            copyData(inputStream,zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }

}