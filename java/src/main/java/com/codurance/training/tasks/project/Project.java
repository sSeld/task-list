package com.codurance.training.tasks.project;

import java.util.ArrayList;
import java.util.List;

public final class Project {
    private final List<Task> tasks;
    private final ProjectId projectId;

    public List<Task> getTasks() {
        return tasks;
    }

    public Project(String id) {
        this.projectId = new ProjectId(id);
        tasks = new ArrayList<>();
    }

    public String getId() {
        return projectId.getId();
    }
}