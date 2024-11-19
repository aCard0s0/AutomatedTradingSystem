package cryptobot.mws.task.callback;

import cryptobot.cml.model.Market;
import cryptobot.mws.task.factory.TaskFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class WatcherTaskCallback implements TaskCallback {
    private final TaskFactory taskFactory;

    public WatcherTaskCallback(@Qualifier("watcherFactory") TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    @Override
    public void onTaskCompleted(Market market){
        taskFactory.createTask(market);
    }
}
