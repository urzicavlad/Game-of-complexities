package ro.urzicavlad.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOUtil {

    private IOUtil() {
        throw new RuntimeException();
    }

    public static Map<String, String> readDataFromFile(Path path) throws IOException {
        final Map<String, String> algorithms = new HashMap<>();
        final List<String> result = Files.readAllLines(path);
        result.forEach(line -> {
            final String[] parts = line.split(",");
            final String algorithmName = parts[0];
            final String algorithmComplexity = parts[1];
            algorithms.put(algorithmName, algorithmComplexity);
        });
        return algorithms;
    }
}
