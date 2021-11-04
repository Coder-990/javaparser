package hr.java.parser.main;

import hr.java.parser.model.WebServers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hr.java.parser.main.FilesRead.logListOfWebServers;

public class FilesStore {

    public Map<List<WebServers>, List<Long>> getAllUniqueVisits() {
        Map<List<WebServers>, List<Long>> mapOfUniqueVisits = new HashMap<>();
        putUniqueVisitsInMap(mapOfUniqueVisits, "home");
        putUniqueVisitsInMap(mapOfUniqueVisits, "help_page");
        putUniqueVisitsInMap(mapOfUniqueVisits, "contact");
        putUniqueVisitsInMap(mapOfUniqueVisits, "about");
        putUniqueVisitsInMap(mapOfUniqueVisits, "index");
        return mapOfUniqueVisits;
    }

    private void putUniqueVisitsInMap(Map<List<WebServers>, List<Long>> mapOfUniqueVisits, String uniqueName) {
        List<WebServers> listOfUniqueVisit = filterUniqueVisits(mapOfUniqueVisits, uniqueName);
        countUniqueVisits(mapOfUniqueVisits, uniqueName, listOfUniqueVisit);
    }

    private List<WebServers> filterUniqueVisits(Map<List<WebServers>, List<Long>> mapOfUniqueVisits, String uniqueName) {
        List<WebServers> listOfUniqueVisit = logListOfWebServers.stream()
                .filter(uniqueVisits -> uniqueVisits.getIpAddress().contains(uniqueName)).collect(Collectors.toList());
        mapOfUniqueVisits.put(listOfUniqueVisit, new ArrayList<>());
        return listOfUniqueVisit;
    }

    private void countUniqueVisits(Map<List<WebServers>, List<Long>> mapOfUniqueVisits, String uniqueName, List<WebServers> listOfUniqueVisit) {
        long uniqueVisitsNumber = logListOfWebServers.stream()
                .filter(uniqueVisits -> uniqueVisits.getIpAddress().contains(uniqueName)).count();
        mapOfUniqueVisits.get(listOfUniqueVisit).add(uniqueVisitsNumber);
    }


}
