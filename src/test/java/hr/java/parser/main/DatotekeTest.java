package hr.java.parser.main;

import hr.java.parser.model.WebServers;
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
class DatotekeTest {

    @Mock
    Datoteke datotekeMock;
    @Mock
    WebServers webServersMock;

    @Test
    void shouldReturnListOfWebServers() throws IOException {
        //given
        String datoteka = WebServers.FILE_WEB_SERVERS;
        List<WebServers> listaDatoteka = datotekeMock.ucitajServere(datoteka);
        List<WebServers> webServersList = Arrays.asList(
                new WebServers("home 127.0.0.1."),
                new WebServers("help_page 127.1.2.1."),
                new WebServers("contact 97.19.21.12."),
                new WebServers("about 27.19.41.82."),
                new WebServers("index 114.119.141.182."));
        when(this.webServersMock.getListaWebServera(listaDatoteka)).thenReturn(webServersList);
        int expected = 5;
        //when
        int actual = webServersMock.getListaWebServera(listaDatoteka).size();
        //then
        assertEquals(expected, actual);
    }
}