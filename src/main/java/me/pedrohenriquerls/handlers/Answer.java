package me.pedrohenriquerls.handlers;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Response;

public class Answer {

    private Object response;

    private String message;
    private int code = Response.SC_OK;

    public Integer getCode() {
        return code;
    }

    public Answer(){}
    public Answer(Object response){
        this.response = response;
    }

    public static Answer ok(Object response, String message){
        Answer answer = new Answer(response);
        answer.response = response;
        answer.message = message;
        return answer;
    }

    public static Answer error(String message){
        Answer answer = new Answer();
        answer.message = message;
        answer.code = Response.SC_BAD_REQUEST;
        return answer;
    }

    public String getBody(){
        if(code == Response.SC_OK){
            Gson gson = new Gson();
            return gson.toJson(this);
        }

        return message;
    }
}
