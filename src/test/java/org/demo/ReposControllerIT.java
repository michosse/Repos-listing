package org.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ReposControllerIT {
    @Test
    void testGetAllUserRepos(){
        String username = "octocat";
        given()
                .when()
                .get("/"+username)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userRepos", not(empty()))
                .body("userRepos[0].branches", not(empty()));
    }
}
