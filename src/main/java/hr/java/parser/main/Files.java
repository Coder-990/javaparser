package hr.java.parser.main;

import hr.java.parser.model.WebServers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static hr.java.parser.model.WebServers.NUMBER_OF_LINES_PER_RECORD;
import static java.nio.file.Files.lines;

public class Files {

    static final String FILE_LOCATION = "file/";
    private static final String newLine = "#newLine#";
    private static final Pattern pattern = Pattern.compile(newLine);
    private static final Path filePath = Paths.get(FILE_LOCATION + WebServers.FILE_WEB_SERVERS);
    private static final Charset encoding = StandardCharsets.UTF_8;

    public List<WebServers> loadingFromFile() throws IOException {
        List<String> loading = fileToLineConverter();
        return lineConverter(loading).stream().map(line -> {
            List<String> addresses = Arrays.asList(pattern.split(line));
            String ipAdresa = addresses.get(0);
            return new WebServers(ipAdresa);
        }).collect(Collectors.toList());
    }

    private List<String> fileToLineConverter() throws IOException {
        Stream<String> lines = lines(Path.of(String.valueOf(filePath)), encoding);
        return lines.map(String::trim)
                .filter(file -> !file.isEmpty())
                .collect(Collectors.toList());
    }

    private List<String> lineConverter(List<String> fileAsListOfString) {
        List<String> listOfLines = new ArrayList<>();
        for (int i = 0; i < fileAsListOfString.size(); i += NUMBER_OF_LINES_PER_RECORD) {
            for (int j = 0; j < fileAsListOfString.size(); j++)
                listOfLines.add(fileAsListOfString.get(i + j)); }
        return listOfLines;
    }
}
