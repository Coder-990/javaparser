package hr.java.parser.main;

import hr.java.parser.model.WebServers;

import java.io.IOException;
import java.util.List;

public class FilesRead {

    static List<WebServers> logListOfWebServers;

    public static void fileReader(Files files) throws IOException {
        System.out.println("loading file " + WebServers.FILE_WEB_SERVERS + " ...\n");
        logListOfWebServers = files.loadingFromFile();
    }
}
