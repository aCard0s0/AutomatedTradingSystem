package cryptobot.mws.task.callback;

import cryptobot.cml.model.Market;

/**
 * This interface is used to notify that a task has been completed.
 *
 */
public interface TaskCallback {

    /**
     * This method is called when a task has been completed.
     *
     * @param market The market that has been processed.
     */
    void onTaskCompleted(Market market);
}
