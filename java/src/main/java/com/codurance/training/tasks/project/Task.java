package com.codurance.training.tasks.project;

import java.time.LocalDate;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;
    private Deadline deadline;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public void addDeadline(LocalDate date) {
        this.deadline = new Deadline(date);
    }


    public LocalDate getDeadline() {
        return deadline != null ? deadline.getDate() : null;
    }
}
