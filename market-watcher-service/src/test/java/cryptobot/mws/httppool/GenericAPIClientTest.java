package cryptobot.mws.httppool;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertThrows;

@Ignore
public class GenericAPIClientTest {

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private GenericApiClient victim;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void fetchPublicMarketDataWithValidUrl() throws IOException {
        /*String url = "https://api.exchange.com/public/market-data";
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);
        HttpEntity entity = mock(HttpEntity.class);

        when(httpClient.execute(httpGet)).thenReturn(httpResponse);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getEntity()).thenReturn(entity);

        String responseBody = victim.fetchPublicMarketData(url);

        verify(httpClient, times(1)).execute(httpGet);
        assertNotNull(responseBody);*/
    }

    @Test(expectedExceptions = RuntimeException.class)
    void fetchPublicMarketData_Exception() {
        String url = "https://api.example.com/market";
        HttpGet httpGet = new HttpGet(url);
        try {
            when(httpClient.execute(httpGet)).thenThrow(RuntimeException.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThrows(RuntimeException.class, () -> httpClient.execute(httpGet));
        victim.fetchPublicMarketData(url);
        assertThrows(RuntimeException.class, () -> victim.fetchPublicMarketData(url));
    }
}
