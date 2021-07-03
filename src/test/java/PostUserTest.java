import io.restassured.response.Response;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requests.UserEndpoint.deleteUserRequest;
import static requests.UserEndpoint.registerUserRequest;

public class PostUserTest extends TestBase{
    private User validUser;
    private User emailInUse;
    private User emptyEmail;
    private User emptyName;
    private User emptyPassword;
    private User emptyAdmin;
    private User differentAdminValue;
    private User allEmptyUser;

    @BeforeClass
    public void generateTestData(){
        validUser = new User("Tatu", "tatu@email.com", "123abc", "true");
        emailInUse = new User("emailInUse", "tatu@email.com", "password123", "true");
        emptyEmail = new User("EmptyEmail", "", "password123", "true");
        emptyName = new User("", "emptyName@email.com", "password123", "true");
        emptyPassword = new User("emptyPassword", "emptyPassword@email.com", "", "true");
        emptyAdmin = new User("emptyAdmin", "emptyAdmin@email.com", "password123", "");
        differentAdminValue = new User("differentAdminValue", "differentAdminValue@email.com", "password123", "value");
        allEmptyUser = new User("", "", "", "");
    }

    @AfterClass
    public void eraseTestData(){
        deleteUserRequest(SPEC, validUser);
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

    @Test
    public void shouldNotCreateUserWithEmailAlreadyInUseAndReturnErrorMessageAndStatus400(){
        Response registerUserRequest = registerUserRequest(SPEC, emailInUse);
        registerUserRequest.
                then().
                assertThat().
                statusCode(400).
                body("message", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_EMAIL_ALREADY_IN_USE));
    }

    @Test
    public void shouldNotCreateUserWithEmptyEmailAndReturnErrorMessageAndStatus400(){
        Response registerUserRequest = registerUserRequest(SPEC, emptyEmail);
        registerUserRequest.
                then().
                assertThat().
                statusCode(400).
                body("email", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_EMPTY_EMAIL));
    }

    @Test
    public void shouldNotCreateUserWithEmptyNameAndReturnErrorMessageAndStatus400(){
        Response registerUserRequest = registerUserRequest(SPEC, emptyName);
        registerUserRequest.
                then().
                assertThat().
                statusCode(400).
                body("nome", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_EMPTY_NAME));
    }

    @Test
    public void shouldNotCreateUserWithEmptyPasswordAndReturnErrorMessageAndStatus400(){
        Response registerUserRequest = registerUserRequest(SPEC, emptyPassword);
        registerUserRequest.
                then().
                assertThat().
                statusCode(400).
                body("password", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_EMPTY_PASSWORD));
    }

    @Test
    public void shouldNotCreateUserWithEmptyAdminAndReturnErrorMessageAndStatus400(){
        Response registerUserRequest = registerUserRequest(SPEC, emptyAdmin);
        registerUserRequest.
                then().
                assertThat().
                statusCode(400).
                body("administrador", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_WRONG_ADMIN));
    }

    @Test
    public void shouldNotCreateUserWithAdminValueDifferentFromTrueOrFalseAndReturnErrorMessageAndStatus400(){
        Response registerUserRequest = registerUserRequest(SPEC, differentAdminValue);
        registerUserRequest.
                then().
                assertThat().
                statusCode(400).
                body("administrador", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_WRONG_ADMIN));
    }

    @Test
    public void shouldNotCreateUserWithAllRequiredFieldsEmptyAndReturnErrorMessageAndStatus400(){
        Response registerUserRequest = registerUserRequest(SPEC, allEmptyUser);
        registerUserRequest.
                then().
                assertThat().
                statusCode(400).
                body("nome", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_EMPTY_NAME)).
                body("email", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_EMPTY_EMAIL)).
                body("password", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_EMPTY_PASSWORD)).
                body("administrador", equalTo(Constants.MESSAGE_FAILED_USER_CREATION_WRONG_ADMIN));
    }
}
