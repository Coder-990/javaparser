package hr.java.parser.model;

import java.util.List;

public class WebServers {

    private String ipAddress;
    private List<WebServers> listaWebServera;

    public static final String FILE_WEB_SERVERS = "webserver.log";
    public static final Integer BROJ_LINIJA_PO_ZAPISU = 500;



    public WebServers(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<WebServers> getListaWebServera(List<WebServers> listaDatoteka) {
        return listaWebServera;
    }

    public void setListaWebServera(List<WebServers> listaWebServera) {
        this.listaWebServera = listaWebServera;
    }

    @Override
    public String toString() {
        return ipAddress.substring(0,10)+ '\'';
    }
}
