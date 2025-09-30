// src/observers/TaskCompletionObserver.java

package exercise_2.observers;

import exercise_2.models.Task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskCompletionObserver implements TaskObserver{
    @Override
    public void update(Task task,EventType eventType) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        if(eventType == EventType.COMPLETED)
        {
            task.setCompleted(true);
            System.out.println("Task '" + task.getDescription() +"' ["+task.getPriority()+"] "+ "completed at "+ now.format(formatter));
            System.out.println("Task '"+task.getDescription()+"' marked as completed.");
        }

    }
}
