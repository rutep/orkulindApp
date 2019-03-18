package Api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import Entity.User;

public class ApiUser {

    /*
    * Usage: api.login(user)
    * Before: User user = new User(username,password)
    * After: if user logs in: return User, else return User with error message
     */
    public User login(User user) {

        Api api = new Api();
        //Convert user to jsonString
        String jsonUser = null;
        String response = null;

        ObjectMapper mapper = new ObjectMapper();
        jsonUser = api.objToJson(user,mapper);

        HttpPostRequest request = new HttpPostRequest(jsonUser);

        //Post user to server
        response = api.Post(request,"/login/api");

        User u = null;
        try {
            u = mapper.readValue(response, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return u;
    }



    /*
    * Usage: api.register(user)
    * Before: User user = new User(username,password)
    * After: if user if passes validation then new user is register'd, else return error message.
     */
    public User register(User user){

        Api api = new Api();

        //Convert user to jsonString
        ObjectMapper mapper = new ObjectMapper();
        String jsonUser = null;
        String response = null;

        jsonUser = api.objToJson(user, mapper);
        HttpPostRequest request = new HttpPostRequest(jsonUser);
        response = api.Post(request,"/register/api");
        //Post user to server
        User u = null;
        try {
            u = mapper.readValue(response, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return u;
    }

}
