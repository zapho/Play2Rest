package controllers;

import org.codehaus.jackson.JsonNode;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
  public static Result index() {
    return ok(index.render("Your new application is ready !"));
  }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result testJson() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").getTextValue();
        if (name == null) {
            return badRequest("Missing parameter [name]");
        } else {
            return ok("Hello " + name);
        }
    }
  
}