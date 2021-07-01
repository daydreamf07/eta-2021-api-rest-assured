import io.restassured.response.Response;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requests.UserEndpoint.deleteUserRequest;
import static requests.UserEndpoint.registerUserRequest;

public class PostUserTest extends TestBase{
    private User validUser;

    @BeforeClass
    public void generateTestData(){
        validUser = new User("Tatu", "tatu@email.com", "123abc", "true");
    }

    @Test
    public void shouldCreateUserAndReturnSuccessMessageAndStatus201(){
        Response registerUserRequest = registerUserRequest(SPEC, validUser);
        registerUserRequest.
                then().
                assertThat().
                statusCode(201).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_USER_CREATION)).
                body("_id",notNullValue());
    }
}
