package com.csye6225.demo.rest_assured;

import io.restassured.RestAssured;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class RestApiTest {

    @Test
    public void testGetHomePage() throws URISyntaxException {
        RestAssured.when().get(new URI("http://localhost:8080/")).then().statusCode(200);
    }

}
