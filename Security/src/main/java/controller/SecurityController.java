package controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SecurityController
{
    @Value("${server.url}")
    private String serverUrl;

    @Value("${server.http.port}")
    private int httpPort;

    @Value("${server.port}")
    private int httpsPort;

    private final RestTemplate httpRestTemplate;
    private final RestTemplate httpsRestTemplate;

    public SecurityController(RestTemplateBuilder builder)
    {
        this.httpRestTemplate = builder.build();
        this.httpsRestTemplate = builder.build();
    }

    @GetMapping("/http/data")
    public String getHttpData() {
        String response = httpRestTemplate.getForObject("http://" + serverUrl + ":" + httpPort + "/api/data", String.class);
        return response;
    }

    @GetMapping("/https/data")
    public String getHttpsData() {
        String response = httpsRestTemplate.getForObject("https://" + serverUrl + ":" + httpsPort + "/api/data", String.class);
        return response;
    }
}
