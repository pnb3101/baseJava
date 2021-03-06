package ru.javawebinar.basejava;

import ru.javawebinar.basejava.exception.StorageException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getAllFileNames(dir);
    }

    private static void getAllFileNames(File file) {
        File[] files = file.listFiles();
        if (files == null) {
            throw new StorageException("Directory is empty: ", file.getName());
        } else {
            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.println(f.getName());
                    getAllFileNames(f);
                } else {
                    System.out.println("  " + f.getName());
                }
            }
        }
    }
}

