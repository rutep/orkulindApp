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

public class ApiSession extends Api{

    /*
     * Usage: api.createSession(session)
     * Before: Session session = new Session()
     * After: The selected session has been created, if not error message
     */
    public void createSession(Session session) {

        //Convert session to jsonString
        String jsonSession = objToJson(session, new ObjectMapper());

        //Post to server
        post(new HttpPostRequest(jsonSession), "api/createSession");

    }

    /*
     * Usage: api.delete(x)
     * Before: Session session = new Session()
     * After: if success session with matching id is deleted else error.
     */
    public void deleteSession(Session session) {

        //Convert session to jsonString
        String jsonSession = objToJson(session, new ObjectMapper());

        //Post to server
        post(new HttpPostRequest(jsonSession), "api/removeSession");
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
        String jsonUser = objToJson(user, new ObjectMapper());

        //Post user to server
        String response = post(new HttpPostRequest(jsonUser), "api/sessions");

        //Convert sessions json response to a list of Session objects
        List<Session> sessions = null;
        try {
            sessions = new ObjectMapper().readValue(response, new TypeReference<List<Session>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessions;

    }
}
