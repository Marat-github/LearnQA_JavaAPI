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
    public void RestAssured() throws InterruptedException {

        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response.get("token");

        response.prettyPrint();
        //System.out.println(token);

        String status = null;

        boolean flag = true;

        while(flag){
            response = RestAssured
                    .given()
                    .queryParam("token", token)
                    .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                    .jsonPath();

            status = response.get("status");
            
            if(status.equals("Job is ready")){
                System.out.println(status);
                flag = false;
            } else {
                Thread.sleep(3000);
                System.out.println("Waiting.....");
            }
        }


        /*JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response.get("token");

        response.prettyPrint();
        System.out.println(token);

        response = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status = response.get("status");
        boolean job = status.equals("Job is NOT ready");

        response.prettyPrint();
        System.out.println(job);

        Thread.sleep(5000);

        response = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        //status = response.get("status");
        //job = status.equals("Job is NOT ready");

        response.prettyPrint();
        //System.out.println(job);*/
    }
}
