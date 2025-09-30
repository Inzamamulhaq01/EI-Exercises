// src/managers/ScheduleManager.java
// Singleton Pattern:
// ScheduleManager: This class is implemented as a Singleton, ensuring that there is only one
// instance of the schedule manager handling all tasks throughout the application.


package exercise_2.managers;

import exercise_2.factories.TaskFactory;
import exercise_2.models.Task;
import exercise_2.observers.EventType;
import exercise_2.observers.TaskObserver;
import exercise_2.utils.Logger;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager
{
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<TaskObserver> observers;


    private ScheduleManager(){
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

//    Singleton only one object
    public static ScheduleManager getInstance()
    {
        if(instance == null)
            return new ScheduleManager();
        return instance;
    }

//    Add the tasks
    public void addTask(String description,String startTime,String endTime,String priority)
    {
        if(startTime.equals(endTime))
        {
            System.out.println("Error: End time must be after start time.");
            return;
        }
        Task task = TaskFactory.createTask(description,startTime,endTime,priority);
        if(task != null)
        {
            if(checkForConflicts(task,-1) != true)
            {
                tasks.add(task);
                notifyObservers(task,EventType.ADDED);
                Logger.info("Task added: " + task);
            }

        }
    }

//    Notify the users that when task added, conflicted, completed
    private void notifyObservers(Task task,EventType eventType)
    {
        for (TaskObserver observer : observers) {
            observer.update(task, eventType);
        }
    }

//   checks for conflit with other tasks
    private boolean checkForConflicts(Task newTask,int index)
    {
        for(int i=0;i< tasks.size();i++)
        {
            if(i == index)
                continue;
            Task existTask = tasks.get(i);
            String existTaskDesc = existTask.getDescription();
            LocalTime newStartTime = newTask.getStartTime();
            LocalTime newEndTime = newTask.getEndTime();
            LocalTime existStartTime = existTask.getStartTime();
            LocalTime existEndTime = existTask.getEndTime();

            if(newStartTime.isBefore(existEndTime) && newEndTime.isAfter(existStartTime))
            {
                notifyObservers(existTask,EventType.CONFLICT);
                return true;
            }
            else if(existTaskDesc.equalsIgnoreCase(newTask.getDescription()))
            {
                System.out.println("Conflict detected with task description must be unique ");
                return true;
            }
        }
        return false;
        }

//    gives the index of the given task
    public int findIndex(String removeDescription)
    {
        int index = -1;
        for (int i = 0; i < tasks.size(); i++)
        {
            if(tasks.get(i).getDescription().equalsIgnoreCase(removeDescription))
            {
                index = i;
                break;
            }
        }
        return index;
    }

// to remove the task
    public void removeTask(String removeDescription)
    {
        int index = findIndex(removeDescription);
        if(index != -1)
        {
            Task removedTask = tasks.remove(index);
            System.out.println("Task removed successfully.");
            Logger.info("Task removed: " + removedTask.getDescription());
        }
        else
        {
            System.out.println("Error: Task not found.");
        }
    }

//  add new Observer like EG: conflit
    public void addObserver(TaskObserver taskObserver)
    {
        observers.add(taskObserver);
    }

//  view all tasks
    public void viewTasks()
    {
        if(tasks.isEmpty())
        {
            System.out.println("No tasks scheduled for the day.");
            return;
        }

        tasks.sort((a,b)->a.getStartTime().compareTo(b.getStartTime()));

        for(Task task:tasks)
        {
            System.out.println(task);
        }

    }

//    can edit the existing task
    public void editTask(String oldDescription, String newDescription, String newStartTime, String newEndTime, String newPriority)
    {
        int index = findIndex(oldDescription);
        if(index != -1)
        {
            Task oldTask = tasks.get(index);
            Task newTask = TaskFactory.createTask(newDescription,newStartTime,newEndTime,newPriority);
            if(checkForConflicts(newTask,index))
            {
                notifyObservers(newTask,EventType.CONFLICT);

            }
            else
            {
                tasks.remove(index);
                tasks.add(index,newTask);
                System.out.println("Task edited successfully. No conflicts.");
                Logger.info("Task edited: "+oldTask.getDescription()+" to "+newTask.getDescription());
            }

        }
        else
        {
            System.out.println("Error: Task not found.");
        }
    }

//    to mark the task as completed
    public void markTaskAsCompleted(String completeDescription)
    {
        for(Task task:tasks)
        {
            if(task.getDescription().equalsIgnoreCase(completeDescription))
            {
                task.setCompleted(true);
                notifyObservers(task,EventType.COMPLETED);
                Logger.info("Task Completed: "+task);
                return;
            }
        }
        System.out.println("Error: Task not found");
    }

//    view the tasks by their priority level
    public void viewTasksByPriority(String priorityLevel)
    {
        String priority = priorityLevel.trim();
        tasks.sort((a,b)->a.getStartTime().compareTo(b.getStartTime()));
        List<Task> filteredTasks = new ArrayList<>();

        for(Task task:tasks)
        {
            if(task.getPriority().equalsIgnoreCase(priority))
            {
                filteredTasks.add(task);
            }
        }

        if(filteredTasks.isEmpty())
        {
            System.out.println("No tasks found with "+priorityLevel);
        }
        else
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            System.out.println("Tasks with priority " + priorityLevel + ":");
            for(Task filteredtask:filteredTasks)
            {
                System.out.println("- "+filteredtask.getDescription()+" from "+filteredtask.getStartTime().format(formatter)
                +" to "+filteredtask.getEndTime().format(formatter)
                );
            }
        }
    }

//    view the tasks by their short to long time
    public void viewTasksByShortestTime()
    {
        List<Task> filteredTasks = new ArrayList<>(tasks);
        filteredTasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                long duration1 = Duration.between(t1.getStartTime(),t1.getEndTime()).toMinutes();
                long duration2 = Duration.between(t2.getStartTime(),t2.getEndTime()).toMinutes();
                return Long.compare(duration1,duration2);
            }
        });

        if(filteredTasks.isEmpty())
        {
            System.out.println("Error: No tasks found ");
        }
        else
        {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            System.out.println("Tasks with shortest time: ");
            for(Task ftask:filteredTasks)
            {
                System.out.println("- "+ftask.getDescription()+" with duration of "+ Duration.between(ftask.getStartTime(),ftask.getEndTime()).toMinutes()
                +" minutes");
            }
        }
    }

//  checks the current ongoing tasks and list them
    public void checkNotifications()
    {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");

        boolean found = false;

        for (Task task : tasks) {

            if(!task.isCompleted())
            {
                if((task.getStartTime().isBefore(now) || task.getStartTime().equals(now))
                        && (task.getEndTime().isAfter(now) || task.getEndTime().equals(now))) {
                    System.out.println("\n[Notification]");
                    System.out.println("Description : " + task.getDescription());
                    System.out.println("Start Time  : " + task.getStartTime());
                    System.out.println("End Time    : " + task.getEndTime());
                    System.out.println("Priority    : " + task.getPriority());
                    System.out.println("Status      : Ongoing");
                    System.out.println("------------------------------------");
                    found = true;
                }
            }

        }

        if (!found) {
            System.out.println("No ongoing tasks at this moment (" + now.format(formatter) + ")");
        }
    }

//    shows the report of all tasks
    public void taskReports() {
        LocalTime now = LocalTime.now();

        if(tasks.isEmpty())
        {
            System.out.println("No tasks scheduled for the day");
            return;
        }
        for (Task task : tasks) {
            String status;

            if (task.isCompleted()) {
                status = "Completed";
            }
            else if (now.isBefore(task.getStartTime())) {
                status = "Not Started";
            } else if ((task.getStartTime().equals(now) || task.getStartTime().isBefore(now)) &&
                    (task.getEndTime().equals(now) || task.getEndTime().isAfter(now))) {
                status = "Ongoing";
            } else {
                status = "Completed";
                task.setCompleted(true); // auto mark if past end time
            }
            System.out.println("\n[Task Report]");
            System.out.println("Description : " + task.getDescription());
            System.out.println("Start Time  : " + task.getStartTime());
            System.out.println("End Time    : " + task.getEndTime());
            System.out.println("Priority    : " + task.getPriority());
            System.out.println("Status      : " + status);
            System.out.println("------------------------------------");
        }
    }


}
