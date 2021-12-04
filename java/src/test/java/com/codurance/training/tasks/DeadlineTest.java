package com.codurance.training.tasks;

import com.codurance.training.tasks.project.Task;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {

    @Test
    public void
    addTaskDeadlineTest() {
        Task task = new Task(1, "", false);

        assertEquals(task.getDeadline(), null);


        LocalDate now = LocalDate.now();
        task.addDeadline(now);
        assertEquals(task.getDeadline(), now);
    }
}
