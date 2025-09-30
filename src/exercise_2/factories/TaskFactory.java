// src/managers/TaskFactory.ts


package exercise_2.factories;

import exercise_2.models.Task;
import exercise_2.utils.TimeValidator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    public static Task createTask(String desc,String startTime,String endTime,String priority)
    {
        if(desc == null || desc.isBlank())
        {
            System.out.println("Error: Task description cannot be empty.");
            return null;
        }
        if(!TimeValidator.isValidTime(startTime) || !TimeValidator.isValidTime(endTime))
        {
            System.out.println("Error: Invalid time format. ");
            return null;
        }

        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime start = LocalTime.parse(startTime,formatter);
            LocalTime end = LocalTime.parse(endTime,formatter);

            if(end.isBefore(start))
            {
                System.out.println("Error: End time cannot be earlier than start time.");
                return null;
            }
            return new Task(desc,start,end,priority);
        }
        catch (DateTimeParseException e)
        {
            System.out.println("Error: Unable to parse time. Use format HH:MM (24-hour).");
            return null;
        }
    }
}
