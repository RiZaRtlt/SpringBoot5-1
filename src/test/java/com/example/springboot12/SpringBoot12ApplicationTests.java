package com.example.springboot12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBoot12ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> myDevApp = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    private static final GenericContainer<?> myProdApp = new GenericContainer<>("prodapp:latest").withExposedPorts(8080);


    @BeforeAll
    public static void setUp() {
        myDevApp.start();
        myProdApp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myDevApp.getMappedPort(8080), String.class);
        System.out.println(forEntity.getBody());
        Assertions.assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void contextLoads2() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myProdApp.getMappedPort(8080), String.class);
        System.out.println(forEntity.getBody());
        Assertions.assertEquals("Current profile is production", forEntity.getBody());
    }
}
