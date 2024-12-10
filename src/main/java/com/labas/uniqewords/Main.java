package com.labas.uniqewords;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String sourcePath = "src/main/resources/source.txt";
    private static final String resultPath = "src/main/resources/result.txt";

    public static void main(String[] args) {
        File source = new File(sourcePath);
        File result = new File(resultPath);

        firstSolution(source, result);
//        secondSolution(source, result);
    }

    private static void firstSolution(File source, File result) {
        try {
            String content = FileUtils.readFileToString(source, "UTF-8").toLowerCase();

            Set<String> uniqueWords = new HashSet<>(
                    List.of(
                            StringUtils.split(
                                    content, " \n\t\r\f.,!?;:"
                            )
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

    private static void secondSolution(File source, File result) {
        try {
            String content = FileUtils.readFileToString(source, "UTF-8").toLowerCase();
            Map<String, Long> wordCounts = Arrays.stream(StringUtils.split(content, " \n\t\r\f.,!?;:"))
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

            String result1 = wordCounts.entrySet().stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue())
                    .collect(Collectors.joining("\n"));

            FileUtils.writeStringToFile(result, result1, "UTF-8");
        } catch (IOException e) {
            logger.error("Error occurred while processing the file: {}", e.getMessage(), e);
        }
    }
}
