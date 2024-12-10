package com.labas.countwords;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String sourcePath = "src/main/resources/source.txt";
    private static final String resultPath = "src/main/resources/result.txt";

    public static void main(String[] args) {
        File source = new File(sourcePath);
        File result = new File(resultPath);

        firstSolution(source, result);

        try {
            secondSolution(source, result);
        } catch (IOException e) {
            logger.error("Error occurred while processing the file: {}", e.getMessage(), e);
        }

    }

    private static void firstSolution(File source, File result) {
        try {
            String str = FileUtils.readFileToString(source, "UTF-8").toLowerCase();

            Set<String> uniqueWords = new HashSet<>(
                    Arrays.asList(
                            StringUtils.split(str, " \n\t\r\f.,!?;:")
                    )
            );

            FileUtils.writeStringToFile(
                    result,
                    String.format("The number of unique words - %d", uniqueWords.size()), "UTF-8"
            );

        } catch (IOException e) {
            logger.error("Error occurred while processing the file: {}", e.getMessage(), e);
        }
    }

    private static void secondSolution(File source, File result) throws IOException {
        FileUtils.writeStringToFile(result, "The number of unique words - " + new HashSet<>(Arrays.asList(StringUtils.split(FileUtils.readFileToString(source, "UTF-8").toLowerCase()))).size(), "UTF-8");
    }
}
