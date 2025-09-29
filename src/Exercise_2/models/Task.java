// src/models/Task.java


package Exercise_2.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task
{
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;
    private boolean isCompleted = false;

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public Task(String description, LocalTime startTime, LocalTime endTime, String priority)
    {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString()
    {
        return  formatTime(startTime) + " - " + formatTime(endTime) + ": "+ description + " ["+priority+"]";
    }

    private String formatTime(LocalTime time)
    {
        return time.format(TIME_FORMAT);
    }

}
