package Api;

import Entity.Stats;
import Entity.Training;

public class ApiTrainStatistic {

    /*
     * Usage: api.saveTraining(training)
     * Before: Training training = new Training(Date date, Session session)
     * After: Training is saved
     */
    public String saveTraining(Training training) {
        // TODO
        return "";
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