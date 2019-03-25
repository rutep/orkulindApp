package Api;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import Entity.Exercise;
import Entity.User;
import Entity.Session;

public class ApiSession {

    /*
     * Usage: api.createSession(session)
     * Before: Session session = new Session()
     * After: The selected session has been created, if not error message
     */
    public void createSession(Session session) {

        //Convert session to jsonString
        ObjectMapper mapper = new ObjectMapper();
        String jsonSession = null;

        try {
            jsonSession = mapper.writeValueAsString(session);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Post to server
        HttpPostRequest request = new HttpPostRequest(jsonSession);
        request.execute("api/createSession");

    }

    /*
     * Usage: api.delete(x)
     * Before: Session session = new Session()
     * After: if success session with matching id is deleted else error.
     */
    public void deleteSession(Session session) {

        //Convert session to jsonString
        ObjectMapper mapper = new ObjectMapper();
        String jsonSession = null;


        try {
            jsonSession = mapper.writeValueAsString(session);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Post to server
        HttpPostRequest request = new HttpPostRequest(jsonSession);
        request.execute("api/removeSession");
    }

    /*
     * Usage: api.findSession(x)
     * Before: {int x, x > 0} And has to be an id of a exercise that exists.
     * After: if success session with according id returned else error message.
     */
    public Session findSession(int id) {
        // Todo
        return new Session();
    }


    /*
     * Usage: api.findAllUserSession(user)
     * Before: User user, user = logged in user
     * After: return all user sessions
     */
    public List<Session> findAllUserSessions(User user) {

        //Test user
        user.setId(1);
        user.setPassword("leyniord");
        user.setName("notandi");

        //Convert user to jsonString
        ObjectMapper mapper = new ObjectMapper();
        String jsonUser = null;
        String response = null;
        try {
            jsonUser = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Post user to server
        HttpPostRequest request = new HttpPostRequest(jsonUser);

        try {
            response = request.execute("api/sessions").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Convert sessions json response to a list of Session objects
        List<Session> sessions = null;
        try {
            sessions = mapper.readValue(response, new TypeReference<List<Session>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessions;

    }
}
