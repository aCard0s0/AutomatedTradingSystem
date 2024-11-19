package cryptobot.mws.service.api;

import cryptobot.cml.model.Market;
import cryptobot.mws.domain.reponse.MessageResponse;
import cryptobot.mws.domain.reponse.TaskOutput;
import cryptobot.mws.domain.request.TaskInput;
import cryptobot.mws.mapper.MarketMapper;
import cryptobot.mws.task.factory.TaskFactory;
import cryptobot.mws.task.TasksMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskService {
    private final MarketMapper marketMapper;
    private final TasksMap tasks;
    private final TaskFactory taskFactory;

    public TaskService(MarketMapper marketMapper, TasksMap tasks, @Qualifier("historicFactory") TaskFactory taskFactory) {
        this.marketMapper = marketMapper;
        this.tasks = tasks;
        this.taskFactory = taskFactory;
    }

    public Set<TaskOutput> getAllTasks() {
        Set<TaskOutput> result = new HashSet<>();
        tasks.getTasksKeys().forEach(marketCode -> result.add(new TaskOutput(marketCode, "RUNNING")));
        return result;
    }

    public MessageResponse startTask(TaskInput inputTask) {
        if (taskExists(inputTask.marketCode())) {
            return new MessageResponse("Market is already running");
        }

        if (isMarketPresentInDB(inputTask.marketCode())) {
            //log
            //return new MessageResponse("Market is not present in DB");
        }

        Market market = marketMapper.taskInputToMarket(inputTask);
        taskFactory.createTask(market);
        return new MessageResponse(String.format("Market started with code: %s", market.getMarketCode()));
    }

    //TODO: review this method
    public MessageResponse startTask(Market market) {
        taskFactory.createTask(market);
        return new MessageResponse(String.format("Market started with code: %s", market.getMarketCode()));
    }

    public MessageResponse stopTask(TaskInput inputTask) {
        if (!taskExists(inputTask.marketCode())) {
            return new MessageResponse("Market is not running");
        }

        tasks.getTask(inputTask.marketCode()).cancel(true);
        tasks.removeTask(inputTask.marketCode());
        return new MessageResponse(String.format("Market stopped with code: %s", inputTask.marketCode()));
    }

    //TODO: review this method
    public MessageResponse stopTask(Market market) {
        tasks.getTask(market.getMarketCode()).cancel(true);
        tasks.removeTask(market.getMarketCode());
        return new MessageResponse(String.format("Market started with code: %s", market.getMarketCode()));
    }

    private boolean taskExists(String marketCode) {
        return tasks.containsTask(marketCode);
    }

    private boolean isMarketPresentInDB(String marketCode) {
        return false; //marketService.getMarketByCode(marketCode) != null;
    }
}
