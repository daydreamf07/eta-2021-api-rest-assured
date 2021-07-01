import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;

public class GetUserTests {

    public RequestSpecification spec = new RequestSpecBuilder()
            .addHeader("accept", "application/json")
            .setBaseUri("http://localhost:3000/").build();

    private static User validUser1;
    private static User validUser2;

    @BeforeClass
    public static void generateTestData(){
        validUser1 = new User("Maria","maria@test.com", );

    }







    @Test
    public void shouldReturnAllUsersAndStatus200(){
        given().
                spec(spec).
        when().
                get("usuarios").
        then().
                assertThat().
                statusCode(200).
                body("quantidade", equalTo(1));
    }
}
