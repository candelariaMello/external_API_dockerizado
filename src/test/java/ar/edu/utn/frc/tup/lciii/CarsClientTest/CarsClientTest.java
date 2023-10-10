package ar.edu.utn.frc.tup.lciii.CarsClientTest;

import ar.edu.utn.frc.tup.lciii.clients.CarClientResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CarsClientTest {
    @MockBean
    private RestTemplate restTemplate;

   /* @Test
    void getPostIntegrationTest() {
        ResponseEntity<CarClientResponse> result = postRestClient.getPost(1L);
        assertEquals("hello", Objects.requireNonNull(result.getBody()).title());
    }*/
}
