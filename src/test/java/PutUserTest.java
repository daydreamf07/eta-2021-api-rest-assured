import io.restassured.response.Response;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static requests.UserEndpoint.*;

public class PutUserTest extends TestBase {
    private User validUser;
    private User editUserValue;

    @BeforeClass
    public void generateTestData() {
        validUser = new User("validUsername", "a4@email.com", "password123", "true");
        registerUserRequest(SPEC, validUser);
        editUserValue = new User("editedUserName", "a5@email.com", "password123", "true");

    }

    @AfterClass
    public void deleteTestData(){
        deleteUserRequest(SPEC,validUser);
        deleteUserRequest(SPEC,editUserValue);
    }

    @Test
    public void shouldEditUserAndReturnSuccessMessageAndStatus200() {
        Response editUserRequest = editUserRequest(SPEC, validUser, editUserValue);
        editUserRequest.
                then().
                assertThat().
                statusCode(200).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_USER_EDITION));
    }
}