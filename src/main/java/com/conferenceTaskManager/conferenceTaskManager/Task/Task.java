package com.conferenceTaskManager.conferenceTaskManager.Task;

public class Task {

    private String description;

    private Integer duration;


    public Task() {
    }

    public Task(String description, Integer duration) {
        this.description = description;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}

