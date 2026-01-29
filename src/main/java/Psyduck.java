import java.util.Scanner;
import tasklist.TaskList;

/**
 * Runs the Psyduck task management chatbot application.
 * Handles user input and delegates task operation to the Tasklist.
 */
public class Psyduck {
    /**
     * Starts the application and processes user commands until exit.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
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
                        handleList(taskList);
                        break;
                    case MARK:
                        handleMark(input, taskList);
                        break;
                    case UNMARK:
                        handleUnmark(input, taskList);
                        break;
                    case DELETE:
                        handleDelete(input, taskList);
                        break;
                    case TODO:
                        handleToDo(input, taskList);
                        break;
                    case DEADLINE:
                        handleDeadline(input, taskList);
                        break;
                    case EVENT:
                        handleEvent(input, taskList);
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
     * Handles the list command to display all tasks.
     *
     * @param taskList The task list to display.
     */
    private static void handleList(TaskList taskList) {
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
     * @param taskList The task list to modify.
     * @throws PsyduckException If the input is invalid.
     */
    private static void handleMark(String input, TaskList taskList) throws PsyduckException {
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
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(taskIndex));
        printDivider();
    }

    /**
     * Handles unmarking a task as not done.
     *
     * @param input User input string.
     * @param taskList The task list to modify.
     * @throws PsyduckException If the input is invalid.
     */
    private static void handleUnmark(String input, TaskList taskList) throws PsyduckException {
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
        printDivider();
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(taskIndex));
        printDivider();
    }

    /**
     * Handles deleting a task from the list.
     *
     * @param input User input string.
     * @param taskList The task list to modify.
     * @throws PsyduckException If the input is invalid.
     */
    private static void handleDelete(String input, TaskList taskList) throws PsyduckException {
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
     * @param taskList The task list to modify.
     * @throws PsyduckException If the input is invalid.
     */
    private static void handleToDo(String input, TaskList taskList) throws PsyduckException {
        if (input.length() <= 4 || input.substring(4).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a todo cannot be empty.\n" +
                    "Usage: todo <description>");
        }

        String description = input.substring(5).trim();
        taskList.addToDo(description);
        printSuccessMessage(taskList);
    }

    /**
     * Handles adding a Deadline task.
     *
     * @param input User input string.
     * @param taskList The task list to modify.
     * @throws PsyduckException If the input is invalid.
     */
    private static void handleDeadline(String input, TaskList taskList) throws PsyduckException {
        if (input.length() <= 8 || input.substring(8).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <deadline>");
        }

        String details = input.substring(9).trim();
        int byIndex = details.indexOf("/by");

        if (byIndex == -1) {
            throw new PsyduckException("OOPS!!! Please specify the deadline with /by.\n" +
                    "Usage: deadline <description> /by <deadline>");
        }

        String description = details.substring(0, byIndex).trim();
        if (description.isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <deadline>");
        }

        if (byIndex + 3 >= details.length()) {
            throw new PsyduckException("OOPS!!! The deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <deadline>");
        }

        String by = details.substring(byIndex + 3).trim();
        if (by.isEmpty()) {
            throw new PsyduckException("OOPS!!! The deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <deadline>");
        }

        taskList.addDeadline(description, by);
        printSuccessMessage(taskList);
    }

    /**
     * Handles adding an Event task.
     *
     * @param input User input string.
     * @param taskList The task list to modify.
     * @throws PsyduckException If the input is invalid.
     */
    private static void handleEvent(String input, TaskList taskList) throws PsyduckException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of an event cannot be empty.\n" +
                    "Usage: event <description> /from <start> /to <end>");
        }

        String details = input.substring(6).trim();
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new PsyduckException("OOPS!!! Please specify the event time with /from and /to.\n" +
                    "Usage: event <description> /from <start> /to <end>");
        }

        if (fromIndex >= toIndex) {
            throw new PsyduckException("OOPS!!! /from must come before /to.\n" +
                    "Usage: event <description> /from <start> /to <end>");
        }

        String description = details.substring(0, fromIndex).trim();
        if (description.isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of an event cannot be empty.\n" +
                    "Usage: event <description> /from <start> /to <end>");
        }

        String from = details.substring(fromIndex + 5, toIndex).trim();
        if (from.isEmpty()) {
            throw new PsyduckException("OOPS!!! The start time cannot be empty.\n" +
                    "Usage: event <description> /from <start> /to <end>");
        }

        String to = details.substring(toIndex + 3).trim();
        if (to.isEmpty()) {
            throw new PsyduckException("OOPS!!! The end time cannot be empty.\n" +
                    "Usage: event <description> /from <start> /to <end>");
        }

        taskList.addEvent(description, from, to);
        printSuccessMessage(taskList);
    }

    /**
     * Prints success message after adding a task.
     *
     * @param taskList The task list containing the newly added task.
     */
    private static void printSuccessMessage(TaskList taskList) {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        printDivider();
    }

    /**
     * Prints a divider line to the console.
     */
    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }
}