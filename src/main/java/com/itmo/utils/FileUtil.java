package com.itmo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {
    public static String[] getFromFile(String fileName){
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return (String[]) stream.toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }
}
