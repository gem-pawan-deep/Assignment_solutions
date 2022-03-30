import com.github.tsohr.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class RestAssignment {
    public static void main(String[] args) {
        RestAssured.baseURI="https://reqres.in/api/users";

        //GET
        Response response=RestAssured.given().param("page","2").when().get();
        System.out.println(response.prettyPrint());

        //POST
        String input = new JSONObject()
                .put("name", "Pawan Deep")
                .put("gender", "male")
                .put("email", "Honey1@gmail.com")
                .put("status","active").toString();
        Response response2=given().header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation().body(input).log().all().
                when().post();
        System.out.println(response2.prettyPrint());

      //UPDATE
        String input2 = new JSONObject()
                .put("name", "Aman Deep")
                .put("email", "Aman123@gmail.com")
                .toString();
        Response response3=given().header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation().body(input2).log()
                .all().when().put();
        System.out.println(response3.prettyPrint());

        //DELETE
        Response response4=given().header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation().body(input2).log()
                .all().when().delete();
        System.out.println(response4.prettyPrint());
    }
}
