package Api;

import Entity.Exercise;
import Entity.User;

public class ApiExercise {

    /*
     * Usage: api.createExercise(exercise)
     * Before: Exercise exercise = new Exercise(int id, String name, String type,
     *                             int reps, String repType, String info, String videoLink)
     * After: if exercise passes validation then new exercise has bin created else error message.
     */
    public Exercise createExercise(Exercise exercise) {
        // Todo
        return exercise;
    }

    /*
     * Usage: api.delete(x)
     * Before: {int x, x > 0} And has to be an id of a exercise that exists.
     * After: if success exercise with according id is deleted else error.
     */
    public String deleteExercise(int id) {
        // Todo
        return "";
    }

    /*
     * Usage: api.findExercise(x)
     * Before: {int x, x > 0} And has to be an id of a exercise that exists.
     * After: if success exercise with according id returned else error message.
     */
    public Exercise findExercise(int id) {
        // Todo
        return new Exercise();
    }


    /*
     * Usage: api.findAllUserExercises(user)
     * Before: User user, user = logged in user
     * After: return all user exercises
     */
    public Exercise[] findAllUserExercises(User user) {
        // Todo
        return null;
    }

}
