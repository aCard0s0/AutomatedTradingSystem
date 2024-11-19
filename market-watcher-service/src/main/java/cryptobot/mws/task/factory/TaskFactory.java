package cryptobot.mws.task.factory;

import cryptobot.cml.model.Market;


public interface TaskFactory {
    void createTask(Market market);
}
