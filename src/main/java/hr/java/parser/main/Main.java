package hr.java.parser.main;

import hr.java.parser.model.WebServers;

import java.util.List;
import java.util.Map;

public class Main {

    public static final String FILE_LOCATION = "dat/";

    public static List<WebServers> logListWebServers;
    protected static Map<List<WebServers>, List<Long>> mapalogWebServera;

    public static void main(String[] args) {

        Datoteke datoteke = new Datoteke();

        System.out.println("Ucitavanje datoteke " + WebServers.FILE_WEB_SERVERS + " ...");

        logListWebServers = datoteke.ucitajServere(WebServers.FILE_WEB_SERVERS);

        mapalogWebServera = datoteke.unosSvihWebServera();
        
        for (List<WebServers> ws:mapalogWebServera.keySet()) {
            System.out.println(ws.toString());
        }


    }
}
