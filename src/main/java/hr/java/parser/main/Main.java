package hr.java.parser.main;

import hr.java.parser.model.WebServers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hr.java.parser.main.FilesRead.fileReader;

public class Main {

    public static void main(String[] args) throws IOException {

        Files files = new Files();
        FilesStore fs = new FilesStore();
        FilesPrint fp = new FilesPrint();

        fileReader(files);
        Map<List<WebServers>, List<Long>> countedMapOfVisitWebServers = fs.getAllUniqueVisits();
        fp.printMap(countedMapOfVisitWebServers);
    }


}
