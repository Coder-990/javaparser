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

    public Map<List<WebServers>, List<Long>> unosSvihWebServera() {
       Map <List<WebServers>, List<Long>> mapalogWebServera = new HashMap<>();
        putContactInMap(mapalogWebServera,"home");
        putContactInMap(mapalogWebServera,"help_page");
        putContactInMap(mapalogWebServera,"contact");
        putContactInMap(mapalogWebServera,"about");
        putContactInMap(mapalogWebServera, "index");
        return mapalogWebServera;
    }

    private void putContactInMap(Map<List<WebServers>, List<Long>> mapaLogWebServera, String contact) {
        List<WebServers> nazivPosjeta = logListWebServers.stream()
                .filter(webServers -> webServers.getIpAddress().contains(contact)).collect(Collectors.toList());
        mapaLogWebServera.put(nazivPosjeta, new ArrayList<>());
        long brojPosjeta = logListWebServers.stream()
                .filter(webServers -> webServers.getIpAddress().contains(contact)).count();
        mapaLogWebServera.get(nazivPosjeta).add(brojPosjeta);
    }

    public List<WebServers> ucitajServere(String imeDatoteke) {
        List<String> ucitavanje = ucitajDatoteku(imeDatoteke);
        return prebrojiLinije(ucitavanje).stream().map(line -> {
            List<String> adrese = Arrays.asList(pattern.split(line));
            String ipAdresa = adrese.get(0);
            return new WebServers(ipAdresa);
        }).collect(Collectors.toList());
    }

    private List<String> prebrojiLinije(List<String> datotekaAsListOfStr) {
        List<String> listaLinija = new ArrayList<>();
        for (int i = 0; i < datotekaAsListOfStr.size(); i += BROJ_LINIJA_PO_ZAPISU) {
            for (int j = 0; j < datotekaAsListOfStr.size(); j++)
                listaLinija.add(datotekaAsListOfStr.get(i + j));
        }
        return listaLinija;
    }

    private List<String> ucitajDatoteku(String datoteka) {
        Path putanjaDatoteke = Paths.get(FILE_LOCATION + datoteka);
        Charset encoding = StandardCharsets.UTF_8;
        List<String> listaDatoteke = new ArrayList<>();
        try (Stream<String> linije = Files.lines(Path.of(String.valueOf(putanjaDatoteke)), encoding)) {
            listaDatoteke = linije.map(String::trim).filter(file -> !file.isEmpty()).collect(Collectors.toList());
        } catch (IOException ex) {
            System.err.println("Pogreska kod ucitavanja datoteke " + datoteka + "\n" + ex.getMessage());
        }
        return listaDatoteke;
    }

//    private void putHelpPageInMap(Map<WebServers, List<Long>> mapaLogWebServera) {
//        List<WebServers> nazivPosjetaHelpPage = logListWebServers.stream()
//                .filter(webServers -> webServers.getIpAddress().contains("help_page")).collect(Collectors.toList());
//        mapaLogWebServera.put((WebServers) nazivPosjetaHelpPage, new ArrayList<>());
//        long brojPosjetaHelpPage = logListWebServers.stream()
//                .filter(webServers -> webServers.getIpAddress().contains("help_page")).count();
//        mapaLogWebServera.get(nazivPosjetaHelpPage).add(brojPosjetaHelpPage);
//    }
//
//    private void putHomeInMap(Map<WebServers, List<Long>> mapaLogWebServera) {
//        List<WebServers> nazivPosjetaHome = logListWebServers.stream()
//                .filter(webServers -> webServers.getIpAddress().contains("home")).collect(Collectors.toList());
//        mapaLogWebServera.put((WebServers) nazivPosjetaHome, new ArrayList<>());
//        long brojPosjetaHome = logListWebServers.stream()
//                .filter(webServers -> webServers.getIpAddress().contains("home")).count();
//        mapaLogWebServera.get(nazivPosjetaHome).add(brojPosjetaHome);
//    }



}
