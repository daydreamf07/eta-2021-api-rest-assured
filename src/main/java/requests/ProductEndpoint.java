package requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Product;
import models.User;
import org.json.simple.JSONObject;


import static io.restassured.RestAssured.given;
import static requests.RequestBase.getValueFromResponse;

public class ProductEndpoint{

    public static Response registerProductRequest(RequestSpecification spec, Product product, User user){
        JSONObject productJsonRepresentation = new JSONObject();
        productJsonRepresentation.put("nome", product.name);
        productJsonRepresentation.put("preco",product.price);
        productJsonRepresentation.put("descricao", product.description);
        productJsonRepresentation.put("quantidade",product.quantity);

        Response registerProductResponse =
                given().
                        spec(spec).
                        header("Content-Type","application/json").
                        header("authorization",user.getUserAuthToken()).
                        and().
                        body(productJsonRepresentation.toJSONString()).
                        when().
                        post("produtos");
        product.setProductId(getValueFromResponse(registerProductResponse, "_id"));
        return registerProductResponse;
    }

    public static Response deleteProductRequest(RequestSpecification spec, Product product, User user){
        Response deleteProductResponse =
                given().
                        spec(spec).
                        header("Content-Type","application/json").
                        header("authorization",user.getUserAuthToken()).
                        when().
                        delete("produtos/"+product.getProductID());
        return deleteProductResponse;
    }
}
