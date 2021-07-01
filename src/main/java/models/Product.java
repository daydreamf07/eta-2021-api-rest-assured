package models;
import org.json.simple.JSONObject;

public class Product {

    public String name;
    public int price;
    public String description;
    public int quantity;
    public String productID;

    public Product(String name, int price, String description, int quantity){
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public void setProductId(String productID){
        this.productID = productID;
    }

    public String getProductID(){
        return this.productID;
    }
}