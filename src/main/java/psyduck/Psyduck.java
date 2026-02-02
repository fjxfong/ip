package psyduck;

import java.util.Scanner;
import tasklist.TaskList;
import storage.Storage;

/**
 * Runs the Psyduck task management chatbot application.
 * Handles user input and delegates task operation to the Tasklist.
 */
public class Psyduck {
    private static final String FILE_PATH = "./data/psyduck.txt";
    private final Storage storage;
    private TaskList taskList;

    /**
     * Creates a new Psyduck instance.
     */
    public Psyduck() {
        storage = new Storage(FILE_PATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (PsyduckException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            System.out.println("Starting with an empty task list.");
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String greeting = """
                ____________________________________________________________\s
                Hello! I'm Psyduck\s
                What can I do for you?\s
                ____________________________________________________________""";
        System.out.println(greeting);

        // Continuously read and process user input until "bye" is entered
        while (true) {
            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    throw new PsyduckException("Please enter a command!");
                }

                CommandType command = Parser.parseCommand(input);

                switch (command) {
                    case BYE:
                        printDivider();
                        System.out.println("Bye. Hope to see you again soon!");
                        printDivider();
                        scanner.close();
                        return;
                    case LIST:
                        handleList();
                        break;
                    case MARK:
                        handleMark(input);
                        break;
                    case UNMARK:
                        handleUnmark(input);
                        break;
                    case DELETE:
                        handleDelete(input);
                        break;
                    case TODO:
                        handleToDo(input);
                        break;
                    case DEADLINE:
                        handleDeadline(input);
                        break;
                    case EVENT:
                        handleEvent(input);
                        break;
                    case UNKNOWN:
                        throw new PsyduckException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (PsyduckException e) {
                printDivider();
                System.out.println("☹ " + e.getMessage());
                printDivider();
            } catch (IndexOutOfBoundsException e) {
                printDivider();
                System.out.println("☹ OOPS!!! That task number doesn't exist in your list!");
                printDivider();
            } catch (NumberFormatException e) {
                printDivider();
                System.out.println("☹ OOPS!!! Please provide a valid task number!");
                printDivider();
            } catch (Exception e) {
                printDivider();
                System.out.println("☹ OOPS!!! Something went wrong: " + e.getMessage());
                printDivider();
            }
        }
    }

    /**
     * Saves tasks to storage.
     */
    private void saveToStorage() {
        try {
            storage.save(taskList.getTasks());
        } catch (PsyduckException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Handles the list command to display all tasks.
     */
    private void handleList() {
        printDivider();
        if (taskList.size() == 0) {
            System.out.println("Your task list is empty! Add some tasks to get started.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList.list());
        }
        printDivider();
    }

    /**
     * Handles marking a task as done.
     *
     * @param input User input string.
     * @throws PsyduckException If the input is invalid.
     */
    private void handleMark(String input) throws PsyduckException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify which task to mark!\n" +
                    "Usage: mark <task number>");
        }

        int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;

        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new PsyduckException("OOPS!!! Task number " + (taskIndex + 1) +
                    " doesn't exist! You have " + taskList.size() + " task(s).");
        }

        taskList.markTask(taskIndex);
        saveToStorage();
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(taskIndex));
        printDivider();
    }

    /**
     * Handles unmarking a task as not done.
     *
     * @param input User input string.
     * @throws PsyduckException If the input is invalid.
     */
    private void handleUnmark(String input) throws PsyduckException {
        if (input.length() <= 7 || input.substring(7).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify which task to unmark!\n" +
                    "Usage: unmark <task number>");
        }

        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;

        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new PsyduckException("OOPS!!! Task number " + (taskIndex + 1) +
                    " doesn't exist! You have " + taskList.size() + " task(s).");
        }

        taskList.unmarkTask(taskIndex);
        saveToStorage();
        printDivider();
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(taskIndex));
        printDivider();
    }

    /**
     * Handles deleting a task from the list.
     *
     * @param input User input string.
     * @throws PsyduckException If the input is invalid.
     */
    private void handleDelete(String input) throws PsyduckException {
        if (input.length() <= 7 || input.substring(7).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify which task to delete!\n" +
                    "Usage: delete <task number>");
        }

        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;

        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new PsyduckException("OOPS!!! Task number " + (taskIndex + 1) +
                    " doesn't exist! You have " + taskList.size() + " task(s).");
        }

        task.Task deletedTask = taskList.delete(taskIndex);
        saveToStorage();
        printDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        printDivider();
    }

    /**
     * Handles adding a ToDo task.
     *
     * @param input User input string.
     * @throws PsyduckException If the input is invalid.
     */
    private void handleToDo(String input) throws PsyduckException {
        if (input.length() <= 4 || input.substring(4).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a todo cannot be empty.\n" +
                    "Usage: todo <description>");
        }

        String description = input.substring(5).trim();
        taskList.addToDo(description);
        saveToStorage();
        printSuccessMessage();
    }

    /**
     * Handles adding a Deadline task.
     *
     * @param input User input string.
     * @throws PsyduckException If the input is invalid.
     */
    private void handleDeadline(String input) throws PsyduckException {
        if (input.length() <= 8 || input.substring(8).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        String details = input.substring(9).trim();
        int byIndex = details.indexOf("/by");

        if (byIndex == -1) {
            throw new PsyduckException("OOPS!!! Please specify the deadline with /by.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        String description = details.substring(0, byIndex).trim();
        if (description.isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        if (byIndex + 3 >= details.length()) {
            throw new PsyduckException("OOPS!!! The deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        String by = details.substring(byIndex + 3).trim();
        if (by.isEmpty()) {
            throw new PsyduckException("OOPS!!! The deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        taskList.addDeadline(description, by);
        saveToStorage();
        printSuccessMessage();
    }

    /**
     * Handles adding an Event task.
     *
     * @param input User input string.
     * @throws PsyduckException If the input is invalid.
     */
    /**
     * Handles adding an Event task.
     *
     * @param input User input string.
     * @throws PsyduckException If the input is invalid.
     */
    private void handleEvent(String input) throws PsyduckException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of an event cannot be empty.\n" +
                    "Usage: event <description> /from <date in yyyy-MM-dd> /to <date in yyyy-MM-dd>");
        }

        String details = input.substring(6).trim();
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new PsyduckException("OOPS!!! Please specify the event time with /from and /to.\n" +
                    "Usage: event <description> /from <date in yyyy-MM-dd> /to <date in yyyy-MM-dd>");
        }

        if (fromIndex >= toIndex) {
            throw new PsyduckException("OOPS!!! /from must come before /to.\n" +
                    "Usage: event <description> /from <date in yyyy-MM-dd> /to <date in yyyy-MM-dd>");
        }

        String description = details.substring(0, fromIndex).trim();
        if (description.isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of an event cannot be empty.\n" +
                    "Usage: event <description> /from <date in yyyy-MM-dd> /to <date in yyyy-MM-dd>");
        }

        String from = details.substring(fromIndex + 5, toIndex).trim();
        if (from.isEmpty()) {
            throw new PsyduckException("OOPS!!! The start time cannot be empty.\n" +
                    "Usage: event <description> /from <date in yyyy-MM-dd> /to <date in yyyy-MM-dd>");
        }

        String to = details.substring(toIndex + 3).trim();
        if (to.isEmpty()) {
            throw new PsyduckException("OOPS!!! The end time cannot be empty.\n" +
                    "Usage: event <description> /from <date in yyyy-MM-dd> /to <date in yyyy-MM-dd>");
        }

        taskList.addEvent(description, from, to);
        saveToStorage();
        printSuccessMessage();
    }

    /**
     * Prints success message after adding a task.
     */
    private void printSuccessMessage() {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        printDivider();
    }

    /**
     * Prints a divider line to the console.
     */
    private void printDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Starts the application and processes user commands until exit.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Psyduck().run();
    }
}