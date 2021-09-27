package hr.java.parser.main;

import hr.java.parser.model.WebServers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static hr.java.parser.main.Main.FILE_LOCATION;
import static hr.java.parser.main.Main.logListWebServers;
import static hr.java.parser.model.WebServers.BROJ_LINIJA_PO_ZAPISU;

public class Datoteke {

    private static final String newLine = "#newLine#";
    private static final Pattern pattern = Pattern.compile(newLine);

    public void printMap(Map<List<WebServers>, List<Long>> map) {
        for (Map.Entry<List<WebServers>, List<Long>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
            System.out.println(" " + entry.getKey().stream().findFirst() + " posjeta : " + entry.getValue());
        }
    }

    public Map<List<WebServers>, List<Long>> unosSvihWebServera() {
        Map<List<WebServers>, List<Long>> mapalogWebServera = new HashMap<>();
        putContactInMap(mapalogWebServera, "home");
        putContactInMap(mapalogWebServera, "help_page");
        putContactInMap(mapalogWebServera, "contact");
        putContactInMap(mapalogWebServera, "about");
        putContactInMap(mapalogWebServera, "index");
        return mapalogWebServera;
    }

    public List<WebServers> ucitajServere(String imeDatoteke) throws IOException {
        List<String> ucitavanje = ucitajDatoteku(imeDatoteke);
        return prebrojiLinije(ucitavanje).stream().map(line -> {
            List<String> adrese = Arrays.asList(pattern.split(line));
            String ipAdresa = adrese.get(0);
            return new WebServers(ipAdresa);
        }).collect(Collectors.toList());
    }

    private void putContactInMap(Map<List<WebServers>, List<Long>> mapaLogWebServera, String name) {
        List<WebServers> nazivPosjeta = logListWebServers.stream()
                .filter(webServers -> webServers.getIpAddress().contains(name)).collect(Collectors.toList());
        mapaLogWebServera.put(nazivPosjeta, new ArrayList<>());
        long brojPosjeta = logListWebServers.stream()
                .filter(webServers -> webServers.getIpAddress().contains(name)).count();
        mapaLogWebServera.get(nazivPosjeta).add(brojPosjeta);
    }

    private List<String> prebrojiLinije(List<String> datotekaAsListOfStr) {
        List<String> listaLinija = new ArrayList<>();
        for (int i = 0; i < datotekaAsListOfStr.size(); i += BROJ_LINIJA_PO_ZAPISU) {
            for (int j = 0; j < datotekaAsListOfStr.size(); j++)
                listaLinija.add(datotekaAsListOfStr.get(i + j));
        }
        return listaLinija;
    }

    private List<String> ucitajDatoteku(String datoteka) throws IOException {
        Path putanjaDatoteke = Paths.get(FILE_LOCATION + datoteka);
        Charset encoding = StandardCharsets.UTF_8;
        Stream<String> linije = Files.lines(Path.of(String.valueOf(putanjaDatoteke)), encoding);
        return linije.map(String::trim).filter(file -> !file.isEmpty()).collect(Collectors.toList());
    }
}
