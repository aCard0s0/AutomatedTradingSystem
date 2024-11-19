package cryptobot.mws.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

@Component
@Scope("singleton")
public class TasksMap {
    private final Map<String, Future<?>> activeTasks;

    public TasksMap() {
        this.activeTasks = new HashMap<>();
    }

    public void addTask(String marketCode, Future<?> task) {
        activeTasks.put(marketCode, task);
    }

    public Future<?> getTask(String marketCode) {
        return activeTasks.get(marketCode);
    }

    public boolean containsTask(String marketCode) {
        return activeTasks.containsKey(marketCode);
    }

    public void removeTask(String marketCode) {
        activeTasks.remove(marketCode);
    }

    public Set<String> getTasksKeys() {
        return activeTasks.keySet();
    }

}
