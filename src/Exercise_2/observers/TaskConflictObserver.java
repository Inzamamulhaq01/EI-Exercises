// src/observers/TaskConflictObserver.java

package Exercise_2.observers;

import Exercise_2.models.Task;

public class TaskConflictObserver implements TaskObserver{
    @Override
    public void update(Task task,EventType eventType) {
        if(eventType == EventType.CONFLICT)
        {
            System.out.println("Error: Conflict with existing task: " + task.getDescription());
        }
    }
}
