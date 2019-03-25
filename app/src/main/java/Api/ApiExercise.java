package Api;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import Entity.Exercise;
import Entity.User;

public class ApiExercise extends Api{

    public ApiExercise() {

    }

    /*
     * Usage: api.createExercise(exercise)
     * Before: Exercise exercise = new Exercise(int id, String name, String type,
     *                             int reps, String repType, String info, String videoLink)
     * After: if exercise passes validation then new exercise has bin created else error message.
     */
    public void createExercise(Exercise exercise) {

        //Convert exercise to jsonString
        String jsonExercise = objToJson(exercise, new ObjectMapper());

        //Post to server
        post(new HttpPostRequest(jsonExercise), "api/createExercise");
    }

    /*
     * Usage: api.delete(x)
     * Before: Exercise exercise = new Exercise(int id, String name, String type,
     *                             int reps, String repType, String info, String videoLink)
     * After: if success exercise with according id is deleted else error.
     */
    public void deleteExercise(Exercise exercise) {

        //Convert exercise to jsonString
        String jsonExercise = objToJson(exercise, new ObjectMapper());

        //Post to server
        post(new HttpPostRequest(jsonExercise), "api/removeExercise");

    }

    /*
     * Usage: api.findExercise(x)
     * Before: {int x, x > 0} And has to be an id of a exercise that exists.
     * After: if success exercise with according id returned else error message.
     */
    public Exercise findExercise(int id) {

        return new Exercise();
    }


    /*
     * Usage: api.findAllUserExercises(user)
     * Before: User user, user = logged in user
     * After: return all user exercises
     */
    public List<Exercise> findAllUserExercises(User user) {


        //Convert user to jsonString
        String jsonUser = objToJson(user, new ObjectMapper());

        //Post user to server
        String response = post(new HttpPostRequest(jsonUser), "api/exercises");

        //Convert exercises json response to a list of Exercise objects
        List<Exercise> exercises = null;
        try {
            exercises = new ObjectMapper().readValue(response, new TypeReference<List<Exercise>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exercises;

    }

}
