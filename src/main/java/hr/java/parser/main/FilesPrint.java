package hr.java.parser.main;

import hr.java.parser.model.WebServers;

import java.util.*;

public class FilesPrint {

    public void printMap(Map<List<WebServers>, List<Long>> map) {
        for (Map.Entry<List<WebServers>, List<Long>> entry : map.entrySet()) {
            new ArrayList<>(entry.getValue()).sort(Comparator.reverseOrder());
            System.out.println(" " + entry.getKey()
                    .stream().findFirst().get() + ":" + entry.getValue() + " unique views");
        }
    }
}
