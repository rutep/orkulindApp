package Api;

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
    public Session[] findAllUserSession(User user) {
        // Todo
        return null;
    }

}
