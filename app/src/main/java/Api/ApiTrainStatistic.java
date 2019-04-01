package Api;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import Entity.Stats;
import Entity.Training;

public class ApiTrainStatistic extends Api{

    /*
     * Usage: api.saveTraining(training)
     * Before: Training training = new Training(Date date, Session session)
     * After: Training is saved
     */
    public void saveTraining(Training training) {

        //Convert trainings to jsonString
        String jsonTraining = objToJson(training, new ObjectMapper());

        //Post trainings to server
        post(new HttpPostRequest(jsonTraining), "api/finishTraining");
    }

    /*
     * Usage: api.deleteTraining(x)
     * Before: {int x, x > 0} And has to be an id of a training that exists.
     * After: if success trained session with according id is deleted else error.
     */
    public String deleteTraining(int id) {
        // TODO
        return "";
    }

    /*
    * TODO
     */
    public Stats findStatistics(Stats stats) {
        // TODO
        return stats;
    }

    /*
     * Usage: api.findTraining(x)
     * Before: {int x, x > 0} And has to be an id of a training that exists.
     * After: if success training with according id returned else error message.
     */
    public Training findTraining(int id){
        // TODO
        return null;
    }
}