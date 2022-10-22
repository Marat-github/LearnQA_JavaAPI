import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {

    @Test
    public void RestAssured(){

        int statusCode = 0;
        String address = "https://playground.learnqa.ru/api/long_redirect";
        boolean follow = false;
        Response response;

        while(statusCode != 200){
             response = RestAssured
                    .given()
                    .redirects()
                    .follow(follow)
                    .when()
                    .get(address)
                    .andReturn();

            statusCode = response.getStatusCode();
            address = response.getHeader("location");
        }

        System.out.println("Status code is " + statusCode);

    }
}
