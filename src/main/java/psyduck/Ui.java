package psyduck;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Deals with displaying messages and reading user input.
 */
public class Ui {
    private final Scanner scanner;
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Creates a new Ui instance with a Scanner for reading input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome/greeting message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Psyduck");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the exit/goodbye message.
     */
    public void showExit() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the divider line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("☹ " + message);
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        System.out.println("☹ Error loading tasks. Starting with an empty psyduck.task list.");
    }

    /**
     * Displays a message after successfully adding a psyduck.task.
     *
     * @param task The psyduck.task that was added.
     * @param totalTasks The total number of tasks in the list.
     */
    public void showAddedTask(String task, int totalTasks) {
        System.out.println("Got it. I've added this psyduck.task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " psyduck.task(s) in the list.");
    }

    /**
     * Displays a message after successfully marking a psyduck.task as done.
     *
     * @param task The psyduck.task that was marked.
     */
    public void showMarkedTask(String task) {
        System.out.println("Nice! I've marked this psyduck.task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message after successfully unmarking a psyduck.task.
     *
     * @param task The psyduck.task that was unmarked.
     */
    public void showUnmarkedTask(String task) {
        System.out.println("OK! I've marked this psyduck.task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message after successfully deleting a psyduck.task.
     *
     * @param task The psyduck.task that was deleted.
     * @param totalTasks The total number of tasks remaining.
     */
    public void showDeletedTask(String task, int totalTasks) {
        System.out.println("Noted. I've removed this psyduck.task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " psyduck.task(s) in the list.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskListStr The formatted string of tasks.
     */
    public void showTaskList(String taskListStr) {
        if (taskListStr.isEmpty()) {
            System.out.println("Your psyduck.task list is empty! Add some tasks to get started.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskListStr);
        }
    }

    /**
     * Displays the list of tasks found by date.
     *
     * @param result The formatted string of matching tasks.
     * @param dateStr The date that was searched.
     */
    public void showFindResults(String result, String dateStr) {
        if (result.isEmpty()) {
            System.out.println("No tasks found on " + dateStr);
        } else {
            System.out.println("Tasks on " + dateStr + ":");
            System.out.println(result);
        }
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}
