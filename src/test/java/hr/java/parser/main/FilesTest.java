package hr.java.parser.main;

import hr.java.parser.model.WebServers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilesTest {

    private static Files files;

    @Mock
    Files filesMock;
    @Mock
    WebServers webServersMock;

    @BeforeAll
    public static void setUp(){
       files = new Files();
    }

    @Test
    public void shouldLoadFromFile() {
        String file = WebServers.FILE_WEB_SERVERS;
        assertEquals("webserver.log",file);
    }

    @Test
    public void shouldReturnListOfWebServers() throws IOException {
        //given
        List<WebServers> listOfFiles = filesMock.loadingFromFile();
        List<WebServers> listOfWebServerLogs = Arrays.asList(
                new WebServers("home 127.0.0.1."),
                new WebServers("help_page 127.1.2.1."),
                new WebServers("contact 97.19.21.12."),
                new WebServers("about 27.19.41.82."),
                new WebServers("index 114.119.141.182."));
        when(this.webServersMock.getListWebServers(listOfFiles)).thenReturn(listOfWebServerLogs);
        int expected = 5;
        //when
        int actual = webServersMock.getListWebServers(listOfFiles).size();
        //then
        assertEquals(expected, actual);
    }
}