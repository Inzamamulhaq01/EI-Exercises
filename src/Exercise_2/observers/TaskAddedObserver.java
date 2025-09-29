// src/observers/TaskAddedObserver.java

package Exercise_2.observers;

import Exercise_2.models.Task;

public class TaskAddedObserver implements TaskObserver{
    @Override
    public void update(Task task, EventType eventType) {
        if(eventType == EventType.ADDED)
        {
            System.out.println("Task added successfully. No conflicts.");
        }
    }
}
