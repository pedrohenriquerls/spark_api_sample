package me.pedrohenriquerls.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.pedrohenriquerls.Config;
import spark.Request;
import spark.Response;
import spark.Route;

import java.lang.reflect.Type;
import java.util.Map;

import static spark.Spark.halt;

public abstract class AbstractRequestHandler implements Route {
    private String token;
    private static Gson gson = new Gson();
    protected abstract Answer process(Map<String, String> params);

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if(Config.debug){
            System.out.println(gson.toJson(request.queryParams()));
            System.out.println(request.body());
        }

        Map<String, String> urlParams;
        if(request.requestMethod().equals("GET")) {
            token = request.queryParams("token");
            urlParams = request.params();
        }else{
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            urlParams = gson.fromJson(request.body(), type);
            token = urlParams.get("token");
        }

        if(token == null || !token.equals(Config.token)){
            halt(403, "Invalid Token");
            return "Invalid Token";
        }

        Answer answer = process(urlParams);

        response.type("application/json");
        response.status(answer.getCode());

        String body = answer.getBody();
        response.body(body);

        return body;
    }
}
