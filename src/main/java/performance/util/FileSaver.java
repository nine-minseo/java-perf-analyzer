package performance.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileSaver {
    private static final String DIRECTORY_PATH = "reports";

    public String save(String title, String content) throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String safeTitle = title.replaceAll("[^a-zA-Z0-9]", "_");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = String.format("%s_%s.md", safeTitle, timestamp);
        File file = new File(directory, filename);

        try (FileWriter writer = new FileWriter(file)) {
            String cleanContent = stripAnsiCodes(content);

            writer.write("# Performance Analysis Report\n");
            writer.write("- Analyzed at: " + LocalDateTime.now() + "\n");
            writer.write("- Topic: " + title + "\n\n");
            writer.write("```text\n");
            writer.write(cleanContent);
            writer.write("\n```\n");
        }

        return file.getAbsolutePath();
    }

    private String stripAnsiCodes(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}