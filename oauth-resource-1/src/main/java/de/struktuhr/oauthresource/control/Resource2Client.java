package de.struktuhr.oauthresource.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Resource2Client {

    @Autowired
    private RestTemplate restTemplate;

    public String demo() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("http://localhost:8082/demo", String.class);
        return response.getBody();
    }
}

