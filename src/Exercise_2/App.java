// 1. Astronaut Daily Schedule Organizer Programming Exercise

// src/App.java

package Exercise_2;

import Exercise_2.managers.ScheduleManager;
import Exercise_2.observers.TaskAddedObserver;
import Exercise_2.observers.TaskCompletionObserver;
import Exercise_2.observers.TaskConflictObserver;
import Exercise_2.utils.TimeValidator;

import java.util.Scanner;

public class App
{
    public static void main(String[] args) {
        // Initialize ScheduleManager (Singleton)
        ScheduleManager scheduleManager = ScheduleManager.getInstance();


        // Add observers
        scheduleManager.addObserver(new TaskConflictObserver());
        scheduleManager.addObserver(new TaskCompletionObserver());
        scheduleManager.addObserver(new TaskAddedObserver());

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            showMenu();
            System.out.print("Select an option (1-10): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter start time (HH:MM): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:MM): ");
                    String endTime = scanner.nextLine();
                    System.out.print("Enter priority level (High, Medium, Low): ");
                    String priority = scanner.nextLine();

                    if (!TimeValidator.isValidTime(startTime) || !TimeValidator.isValidTime(endTime)) {
                        System.out.println("Error: Invalid time format.");
                        break;
                    }

                    scheduleManager.addTask(description, startTime, endTime, priority);
                    break;

                case "2":
                    System.out.print("Enter task description to remove: ");
                    String removeDescription = scanner.nextLine();
                    scheduleManager.removeTask(removeDescription);
                    break;

                case "3":
                    scheduleManager.viewTasks();
                    break;

                case "4":
                    System.out.print("Enter task description to edit: ");
                    String oldDescription = scanner.nextLine();
                    System.out.print("Enter new task description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new start time (HH:MM): ");
                    String newStartTime = scanner.nextLine();
                    System.out.print("Enter new end time (HH:MM): ");
                    String newEndTime = scanner.nextLine();
                    System.out.print("Enter new priority level (High, Medium, Low): ");
                    String newPriority = scanner.nextLine();

//                    Check valid time format
                    if (!TimeValidator.isValidTime(newStartTime) || !TimeValidator.isValidTime(newEndTime)) {
                        System.out.println("Error: Invalid time format.");
                        break;
                    }

                    scheduleManager.editTask(oldDescription, newDescription, newStartTime, newEndTime, newPriority);
                    break;

                case "5":
                    System.out.print("Enter task description to mark as completed: ");
                    String completeDescription = scanner.nextLine();
                    scheduleManager.markTaskAsCompleted(completeDescription);
                    break;

                case "6":
                    System.out.print("Enter priority level to view tasks (High, Medium, Low): ");
                    String priorityLevel = scanner.nextLine();
                    scheduleManager.viewTasksByPriority(priorityLevel);
                    break;

                case "7":
                    scheduleManager.viewTasksByShortestTime();
                    break;

                case "8":
                    scheduleManager.checkNotifications();
                    break;

                case "9":
                    scheduleManager.taskReports();
                    break;

                case "10":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option. Please select a valid option (1-10).");
                    break;
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Astronaut Daily Schedule Organizer!");
    }

    private static void showMenu() {
        System.out.println("\nAstronaut Daily Schedule Organizer");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. View Tasks");
        System.out.println("4. Edit Task");
        System.out.println("5. Mark Task as Completed");
        System.out.println("6. View Tasks by Priority");
        System.out.println("7. View Tasks by shortest Time");
        System.out.println("8. Check Notifications");
        System.out.println("9. Task Reports");
        System.out.println("10. Exit");
    }
}


// //description1, HH:MM, HH:MM, High
// description2, HH:MM, HH:MM, Medium
// ...
