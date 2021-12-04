package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final List<Task> tasks;
    private final String id;

    public List<Task> getTasks() {
        return tasks;
    }

    public Project(String id) {
        this.id = id;
        tasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
}