package com.codurance.training.tasks.project;

class ProjectId {
    private final String id;

    public String getId() {
        return id;
    }

    ProjectId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectId)) {
            return false;
        }

        ProjectId projectId = (ProjectId) o;

        return id != null ? id.equals(projectId.id) : projectId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}