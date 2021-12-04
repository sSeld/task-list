package com.codurance.training.tasks;

import com.codurance.training.tasks.project.Project;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProjectTest {


    private static final Project PROJECT = new Project("training");
//
//    @Test
//    public void getTasks() {
//        assertThat(PROJECT.getTasks(),);
//    }

    @Test
    public void getId() {
        assertThat(PROJECT.getId(), is("training"));
    }
}