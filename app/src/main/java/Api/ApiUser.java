package Api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import Entity.User;

public class ApiUser extends Api{

    /*
    * Usage: api.login(user)
    * Before: User user = new User(username,password)
    * After: if user logs in: return User, else return User with error message
     */
    public User login(User user) {

        //Convert user to jsonString
        String jsonUser = objToJson(user,new ObjectMapper());

        //Post user to server
        String response = post(new HttpPostRequest(jsonUser),"/login/api");

        //Convert json response to User object
        User u = null;
        try {
            u = new ObjectMapper().readValue(response, User.class);
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

        //Convert user to jsonString
        String jsonUser = objToJson(user, new ObjectMapper());

        //Post user to server
        String response = post(new HttpPostRequest(jsonUser),"/register/api");

        //Convert json response to User object
        User u = null;
        try {
            u = new ObjectMapper().readValue(response, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return u;
    }

}
