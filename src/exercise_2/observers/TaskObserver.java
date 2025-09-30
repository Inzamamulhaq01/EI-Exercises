// src/observers/TaskObservers.java
// Observer Pattern:
// Observers in TaskObservers.java: The application uses observers to notify users of task conflicts and task completion. The ScheduleManager class manages
//  a list of observers and notifies them when relevant events occur.


package exercise_2.observers;

import exercise_2.models.Task;


public interface TaskObserver {
    void update(Task task,EventType eventType);
}
