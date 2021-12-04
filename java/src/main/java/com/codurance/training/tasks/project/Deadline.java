package com.codurance.training.tasks.project;

import java.time.LocalDate;

class Deadline {
    public LocalDate getDate() {
        return date;
    }

    private LocalDate date;

    Deadline(LocalDate date) {
        this.date = date;
    }
}
