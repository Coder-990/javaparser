package hr.java.parser.model;

public class WebServers {

    private String ipAddress;

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

    @Override
    public String toString() {
        return "WebServers{" +
                "ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
