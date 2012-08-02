package controllers;

import models.Customer;
import models.Product;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        List<Customer> customers = Customer.all();
        List<Product> products = Product.all();
        return ok(index.render(customers, products));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result testJson() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").getTextValue();
        ObjectNode result = Json.newObject();
        if (name == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter [name]");
            return badRequest(result);
        } else {
            result.put("status", "OK");
            result.put("message", "Hello " + name);
            return ok(result);
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result createCustomer() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").getTextValue();
        ObjectNode result = Json.newObject();
        if (name == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter [name] for customer");
            return badRequest(result);
        } else {
            Customer newCustomer = Json.fromJson(json, Customer.class);
            newCustomer.save();
            result.put("status", "OK");
            result.put("message", "Customer " + name + " created with id=" + newCustomer.id);
            result.put("customer-url", routes.Application.getCustomer(newCustomer.id).toString());
            return ok(result);
        }
    }

    public static Result getCustomer(Long id) {
        Customer found = Customer.find.byId(id);
        if (found == null) {
            return notFound();
        } else {
            return ok(Json.toJson(found));
        }
    }

    public static Result updateCustomer(Long id) {
        return TODO;
    }

    public static Result deleteCustomer(Long id) {
        return TODO;
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result createProduct() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").getTextValue();
        ObjectNode result = Json.newObject();
        if (name == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter [name] for product");
            return badRequest(result);
        } else {
            result.put("status", "KO");
            result.put("message", "Product already exists");
            if (!Product.findByName(name).isEmpty()) {
                return badRequest(result);
            }

            Product newProduct = Json.fromJson(json, Product.class);
            newProduct.save();
            result.put("status", "OK");
            result.put("message", "Product " + name + " created with id=" + newProduct.id);
            result.put("product-url", routes.Application.getProduct(newProduct.id).toString());
            return ok(result);
        }
    }

    public static Result getProduct(Long id) {
        Product found = Product.find.byId(id);
        if (found == null) {
            return notFound();
        } else {
            return ok(Json.toJson(found));
        }
    }

    public static Result updateProduct(Long id) {
        return TODO;
    }

    public static Result deleteProduct(Long id) {
        return TODO;
    }

    public static Result createOrder(Long customerId) {
        return TODO;
    }

    public static Result getOrders(Long customerId) {
        return TODO;
    }

    public static Result getOrder(Long customerId, Long OrderId) {
        return TODO;
    }

}