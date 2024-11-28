package com.labas.LinkedList;

import com.labas.travelagency.model.agency.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        try {
            List<String> lines = readLinesFromResource("src/main/resources/data.txt");
            if (lines != null) {
                lines.forEach(list::add);
            }
        } catch (IOException e) {
            logger.error("An error occurred while reading lines from resource", e);
        }

        list.add(1, "Dave");
        logListInfo(list, "After adding 'Dave' at index 1");

        list.remove(2);
        logListInfo(list, "After removing element at index 2");

        list.clear();
        logListInfo(list, "After clearing the list");
    }

    public static List<String> readLinesFromResource(String resourcePath) throws IOException {
        Path path = Paths.get(resourcePath);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Unable to read file: {}", resourcePath, e);
            throw e;
        }
    }

    private static void logListInfo(CustomLinkedList<String> list, String message) {
        logger.info(message);
        logger.info("List size: {}", list.size());
        logger.info("Is list empty: {}", list.isEmpty());
        for (int i = 0; i < list.size(); i++) {
            logger.info("Element at index {}: {}", i, list.get(i));
        }
    }
}