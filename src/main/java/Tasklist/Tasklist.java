package Tasklist;

import java.util.*;

public class Tasklist {
    private final ArrayList<String> tasks = new ArrayList<>();

    public void add(String task) {
        tasks.add(task);
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
}
