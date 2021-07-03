import io.restassured.response.Response;
import models.Product;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requests.ProductEndpoint.deleteProductRequest;
import static requests.ProductEndpoint.registerProductRequest;
import static requests.UserEndpoint.*;

public class PostProductTest extends TestBase{
    private Product validProduct;
    private User validUserAdmin;
    private User validUserNotAdmin;
    private Product validCreatedProduct;
    private User validUserToken;

    @BeforeClass
    public void generateTestData(){
        validUserToken = new User("validUserToken", "validUserToken@email.com", "validUserToken", "true");
        registerUserRequest(SPEC, validUserToken);
        authenticateUserRequest(SPEC, validUserToken);
        validUserAdmin = new User("Tatu", "tatu@email.com", "123abc", "true");
        registerUserRequest(SPEC, validUserAdmin);
        authenticateUserRequest(SPEC, validUserAdmin);
        validUserNotAdmin = new User("NotAdmin", "NotAdmin@email.com", "NotAdmin", "false");
        registerUserRequest(SPEC, validUserNotAdmin);
        authenticateUserRequest(SPEC, validUserNotAdmin);
        validProduct = new Product("Mouse Logitech 3", 230, "Mouse", 10);
        validCreatedProduct = new Product("Teclado Anne Pro 2", 230, "Teclado", 15);
        registerProductRequest(SPEC, validCreatedProduct, validUserAdmin);
    }

    @AfterClass
    public void eraseTestData(){
        deleteProductRequest(SPEC,validProduct,validUserAdmin);
        deleteProductRequest(SPEC,validCreatedProduct,validUserAdmin);
        deleteUserRequest(SPEC,validUserAdmin);
        deleteUserRequest(SPEC,validUserNotAdmin);
    }

    @Test
    public void shouldCreateProductAndReturnSuccessMessageAndStatus201(){
        Response registerProductRequest = registerProductRequest(SPEC, validProduct, validUserAdmin);
        registerProductRequest.
                then().
                assertThat().
                statusCode(201).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_PRODUCT_CREATION)).
                body("_id",notNullValue());
    }

    @Test
    public void shouldNotCreateProductAlreadyCreatedAndReturnErrorMessageAndStatus400(){
        Response registerProductRequest = registerProductRequest(SPEC, validCreatedProduct, validUserAdmin);
        registerProductRequest.
                then().
                assertThat().
                statusCode(400).
                body("message", equalTo(Constants.MESSAGE_FAILED_PRODUCT_CREATION_NAME_ALREADY_IN_USE));
    }

    @Test
    public void shouldNotCreateProductWithUserNotAdminAndReturnErrorMessageAndStatus403(){
        Response registerProductRequest = registerProductRequest(SPEC, validProduct, validUserNotAdmin);
        registerProductRequest.
                then().
                assertThat().
                statusCode(403).
                body("message", equalTo(Constants.MESSAGE_FAILED_PRODUCT_CREATION_USER_NOT_ADMIN));
    }

    @Test
    public void shouldNotCreateProductWithoutTokenAndReturnErrorMessageAndStatus401(){
        deleteUserRequest(SPEC,validUserToken);
        Response registerProductRequest = registerProductRequest(SPEC, validProduct,validUserToken);
        registerProductRequest.
                then().
                assertThat().
                statusCode(401).
                body("message", equalTo(Constants.MESSAGE_FAILED_PRODUCT_CREATION_EMPTY_TOKEN));
    }
}
