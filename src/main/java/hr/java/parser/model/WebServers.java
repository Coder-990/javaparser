package hr.java.parser.model;

import java.util.List;

public class WebServers{

    private String ipAddress;
    private List<WebServers> listWebServers;

    public static final String FILE_WEB_SERVERS = "webserver.log";
    public static final Integer NUMBER_OF_LINES_PER_RECORD = 500;

    public WebServers(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<WebServers> getListWebServers(List<WebServers> listaDatoteka) {
        return listWebServers;
    }

    public void setListWebServers(List<WebServers> listWebServers) {
        this.listWebServers = listWebServers;
    }@Override

    public String toString() {
        return ipAddress.substring(0,10)+ '\'';
    }
}
