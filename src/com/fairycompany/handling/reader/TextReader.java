package com.fairycompany.handling.reader;

import com.fairycompany.handling.exception.CompositeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReader {
    private static Logger logger = LogManager.getLogger();

    public String readFile(String path) throws CompositeException {
        if (path == null || path.isBlank()) {
            throw new CompositeException(String.format("File name %s is null or empty", path));
        }

        List<String> doubleStringList;
        Path dataFile = Paths.get(path);
        String text;

        try (Stream<String> dataStream = Files.lines(dataFile)){

            text = dataStream
                    .collect(Collectors.joining("\n"));

            logger.log(Level.INFO, "Read file {} is successful", dataFile.getFileName());

        } catch (IOException e) {
            throw new CompositeException(String.format("Input error during reading file %s", dataFile.getFileName()), e);
        }

        return text;
    }
}
