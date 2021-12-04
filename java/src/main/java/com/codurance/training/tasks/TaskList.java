package com.codurance.training.tasks;

import com.codurance.training.tasks.infrastructure.ConsoleApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public final class TaskList {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        new ConsoleApplication(in, out).run();
    }
}
