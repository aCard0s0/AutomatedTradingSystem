package cryptobot.mws.httppool;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Log4j2
public class GenericApiClient implements ExchangeApi {
    protected HttpClient httpClient;

    public GenericApiClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String fetchPublicMarketData(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();

            if (statusLine.getStatusCode() == 200) {
                log.info("operation=fetchPublicMarketData, status=success, url={}", url);
                return processResponseEntity(response.getEntity());

            } else {
                log.error("operation=fetchPublicMarketData, status={}, message='Response ignored', url='{}'", statusLine.getStatusCode(), url);
                throw new RuntimeException("Failed to fetch public market data");
            }
        } catch (IOException e) {
            log.error("operation=fetchPublicMarketData, status=failed, message={}", e.getMessage());
            throw new RuntimeException("Failed to fetch public market data.");
        }
    }

    public String processResponseEntity(HttpEntity entity) throws IOException {
        return EntityUtils.toString(entity);
    }
}

/*
private Map<String, String> getHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Accept", "application/json");
    headers.put("Content-Type", "application/json");
    //headers.put("Authorization", "Bearer " + API_KEY);
    headers.put("X-Kraken-API-Version", "0");
    return headers;
}
 */