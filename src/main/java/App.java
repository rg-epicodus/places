import models.placesYouveBeen;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<placesYouveBeen> allPlaces = placesYouveBeen.getAllPlaces();
            model.put("allPlaces", allPlaces);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/places/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newplaces-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/places/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String places = request.queryParams("places");
            placesYouveBeen newplaces = new placesYouveBeen(places);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/places/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPlaceToFind = Integer.parseInt(req.params("id"));
            placesYouveBeen placesVisited = placesYouveBeen.findById(idOfPlaceToFind);
            model.put("places", placesVisited);
            return new ModelAndView(model, "place-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
    }
}
