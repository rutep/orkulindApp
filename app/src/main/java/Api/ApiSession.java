package Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
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
    public Exercise createSession(Exercise[] exercises) {
        // Todo
        return null;
    }

    /*
     * Usage: api.delete(x)
     * Before: {int x, x > 0} And has to be an id of a session that exists.
     * After: if success session with according id is deleted else error.
     */
    public String deleteSession(int id) {
        // Todo
        return "";
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
    public List<Session> findAllUserSession(User user) {

        HttpGetRequest request = new HttpGetRequest();
        String result = null;
        try {
            result = request.execute("api/sessions").get();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Session> sessions = null;
        try {
            sessions = mapper.readValue(result, new TypeReference<List<Session>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessions;
    }
}
