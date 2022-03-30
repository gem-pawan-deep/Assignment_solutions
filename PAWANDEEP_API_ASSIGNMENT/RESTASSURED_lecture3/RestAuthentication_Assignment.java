import com.github.tsohr.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class RestAuthentication_Assignment {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "/public/v2/users";

        //GET
        Response response = given()
                .header("Authorization", "Bearer aac0f5647c78288207003c8f46abdf1422c9b245597e215f0a019430e0e7d513").
                contentType(ContentType.JSON).when().relaxedHTTPSValidation().get();
        System.out.println(response.prettyPrint());

        //POST
        String input = new JSONObject()
                .put("name", "Rahul")
                .put("gender", "male")
                .put("email", "Rahul@gmail.com")
                .put("status", "active").toString();
        Response response2 = given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer aac0f5647c78288207003c8f46abdf1422c9b245597e215f0a019430e0e7d513")
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation().body(input).log().all().when().post();
        System.out.println(response2.prettyPrint());

        //UPDATE
        String input2 = new JSONObject()
                .put("name", "sandeep")
                .put("gender", "male")
                .put("email", "sandeep123@gmail.com")
                .put("status", "active").toString();
        Response response3 = given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer aac0f5647c78288207003c8f46abdf1422c9b245597e215f0a019430e0e7d513")
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation().body(input2).log().all().when().put();
        System.out.println(response3.prettyPrint());

        //DELETE
        Response response4 = given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer aac0f5647c78288207003c8f46abdf1422c9b245597e215f0a019430e0e7d513")
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation().body(input2).log().all().when().delete();
        System.out.println(response4.prettyPrint());
    }
}