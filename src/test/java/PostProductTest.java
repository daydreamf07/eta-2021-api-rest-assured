import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Product;
import models.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requests.ProductEndpoint.deleteProductRequest;
import static requests.ProductEndpoint.registerProductRequest;
import static requests.UserEndpoint.registerUserRequest;
import static requests.UserEndpoint.authenticateUserRequest;

public class PostProductTest extends TestBase{
    private Product validProduct;
    private User validUser;

    @BeforeClass
    public void generateTestData(){
        validProduct = new Product("Mouse Logitech 3", 230, "Mouse", 10);
        validUser = new User("Tatu", "tatu@email.com", "123abc", "true");
        registerUserRequest(SPEC, validUser);
        authenticateUserRequest(SPEC, validUser);
    }
    @AfterClass
    public void eraseTestData(){
        deleteProductRequest(SPEC, validProduct, validUser);
    }

    @Test
    public void shouldCreateProductAndReturnSuccessMessageAndStatus201(){
        Response registerProductRequest = registerProductRequest(SPEC, validProduct, validUser);
        registerProductRequest.
                then().
                assertThat().
                statusCode(201).
                body("message", equalTo(Constants.MESSAGE_SUCCESS_PRODUCT_CREATION)).
                body("_id",notNullValue());
    }
}
