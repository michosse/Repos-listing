package org.demo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ReposControllerIT {
    @ConfigProperty(name = "wiremock.port")
    int wireMockPort;
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(wireMockPort);
        wireMockServer.start();
        wireMockServer.stubFor(WireMock.get("/users/octocat/repos")
                .willReturn(WireMock.okJson("""
{
  "userRepos": [
    {
      "repositoryName": "test",
      "owner": "octocat",
      "branches": [
        {
          "name": "main",
          "lastCommitSha": "a49d518fb6e8d5c4020ba5aa3b67af583598de00"
        }
      ]
    }
  ]
}
""")));
    }
    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testGetAllUserReposHappyPath(){
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
