package Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import Entity.User;

public class Api {

    public String objToJson(Object o, ObjectMapper mapper){
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String post(HttpPostRequest request, String url) {
        try {
            return request.execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

}
