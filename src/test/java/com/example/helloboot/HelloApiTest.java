package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloApiTest {
    @Test
    void helloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> resp = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // status code 200
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header Contents-Type = text/plain
        Assertions.assertThat(resp.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring;;
        Assertions.assertThat(resp.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    void failsHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();

//        ResponseEntity<String> resp = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "");
        ResponseEntity<String> resp = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, (Object) null);

        // status code 200
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        // header Contents-Type = text/plain
//        Assertions.assertThat(resp.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring
//        Assertions.assertThat(resp.getBody()).isEqualTo("Hello Spring");
    }
}
