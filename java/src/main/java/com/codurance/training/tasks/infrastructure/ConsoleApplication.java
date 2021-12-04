package com.codurance.training.tasks.infrastructure;

import com.codurance.training.tasks.project.Project;
import com.codurance.training.tasks.project.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConsoleApplication implements Runnable {
    private static final String QUIT = "quit";
    private long lastId = 0;
    private Map<String, Project> projects = new LinkedHashMap<>();

    private final BufferedReader in;
    private final PrintWriter out;

    public ConsoleApplication(BufferedReader in, PrintWriter out) { this.in = in;
        this.out = out;
    }



    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "deadline":
                deadline(commandRest[1]);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void deadline(String commandLine) {
        System.out.println(commandLine);
        String[] subcommandRest = commandLine.split(" ", 2);
        if (subcommandRest.length < 2) {
            out.printf("Invalid number of arguments provided.");
            out.println();
            return;
        }

        String idParam = subcommandRest[0];
        String dateParam = subcommandRest[1];

        long id = Long.parseLong(idParam);
        LocalDate date = LocalDate.parse(dateParam);
//
//        //guard
//        if(id == null || !projects.values().containsKey(id)){
//            out.printf("Could not find a task with an ID of %d.", id);
//            out.println();
//            return;
//        }

        projects.values()
                .stream()
                .flatMap(project -> project.getTasks()
                        .stream())
                .filter(task -> task.getId() == id)
                .forEach(task -> task.addDeadline(date));
    }

    private void show() {
        for (Project project : projects.values()) {
            out.println(project.getId());
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %d%12s: %s%n",
                        (task.isDone() ? 'x' : ' '),
                        task.getId(),
                        (task.getDeadline() == null ? "" : "(" + task.getDeadline() +")"),
                        task.getDescription());
            }
            out.println();
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projects.get(projectTask[0]), projectTask[1]);
        }
    }

    private void addProject(String name) {
        projects.put(name, new Project(name));
    }

    private void addTask(Project project, String description) {
        List<Task> projectTasks = project.getTasks();
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    private void check(String idString) {
        setDone(idString, true);
    }

    private void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Project project : projects.values()) {
            for (Task task : project.getTasks()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID> <date>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private long nextId() {
        return ++lastId;
    }
}
