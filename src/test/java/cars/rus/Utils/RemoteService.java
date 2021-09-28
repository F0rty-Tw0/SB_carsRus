package cars.rus.Utils;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class RemoteService {

  private TestRestTemplate restTemplate = new TestRestTemplate();

  private final String BASE_PATH = "/api/cars";
  private final HttpHeaders headers = new HttpHeaders();

  private String makeUrl(int port, String path) {
    String pathBuilded = "http://localhost:" + port + path;
    return pathBuilded;
  }

  public <REQ, RES> ResponseEntity<RES> query(int port, REQ req, HttpMethod method, String... params) {
    String param1 = params.length > 0 ? params[0] : "";
    String param2 = params.length > 1 ? params[1] : "";
    String url = makeUrl(port, BASE_PATH + param1 + param2);
    ResponseEntity<RES> response = null;
    try {
      long startMillis = System.currentTimeMillis();
      HttpEntity<REQ> request = new HttpEntity<>(req, headers);
      response = restTemplate.exchange(url, method, request, new ParameterizedTypeReference<RES>() {
      });

      System.out.println(response);
      long stopMillis = System.currentTimeMillis() - startMillis;
      System.out.println(method + ":" + url + " took " + stopMillis + " ms");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return response;
  }
}
