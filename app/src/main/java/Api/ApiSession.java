package Api;

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
     * Before: Session session = new Session(Exercise[] exercises)
     * After: The selected exercises have bin stored if not error message
     */
    public void saveSession(Session session) {
        // Todo

    }

    /*
     * Usage: api.delete(x)
     * Before: {int x, x > 0} And has to be an id of a session that exists.
     * After: if success session with according id is deleted else error.
     */
    public void deleteSession(int id) {
        // Todo

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
        user.setId(1000);
        user.setPassword("notandi");
        user.setName("lykilord");

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

        //Convert sessions response to a list of sessions
        List<Session> sessions = null;
        try {
            sessions = mapper.readValue(response, new TypeReference<List<Session>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessions;

    }
}
