package Api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Entity.Exercise;
import Entity.User;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiExercise{

    public ApiExercise() {

    }

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
    public void deleteExercise(Exercise exercise) {
        // Todo

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
    public List<Exercise> findAllUserExercises(User user) {

        HttpGetRequest request = new HttpGetRequest();
        String result = null;
        try {
            result = request.execute("api/exercises").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Exercise> exercises = null;
        try {
            exercises = mapper.readValue(result, new TypeReference<List<Exercise>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exercises;
    }

}
