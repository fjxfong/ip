package Tasklist;

import Task.Task;

import java.util.*;

public class Tasklist {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void add(String task) {
        tasks.add(new Task(task));
    }

    public void markTask(int index) {
        tasks.get(index).mark();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    public String list() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) { // this is zero-based internally
        return tasks.get(index);
    }
}
