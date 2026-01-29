import java.util.Scanner;
import tasklist.Tasklist;

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
        Tasklist taskList = new Tasklist();
        String greeting = """
                ____________________________________________________________\s
                Hello! I'm Psyduck\s
                What can I do for you?\s
                ____________________________________________________________""";
        System.out.println(greeting);

        // Continuously read and processes user input until "bye" is entered
        while (true) {
           String input = scanner.nextLine();

           if (input.equals("bye")) {
               break;
           }

           if (input.equals("list")) {
               printDivider();
               System.out.println("Here are the tasks in your list: ");
               System.out.println(taskList.list());
               printDivider();
           }

           else if (input.startsWith("mark ")) {
               int taskNum = Integer.parseInt(input.substring(5)) - 1; //zero-based indexing
               taskList.markTask(taskNum);
               printDivider();
               System.out.println("Nice! I've marked this task as done: ");
               System.out.println(" " + taskList.get(taskNum));
               printDivider();
           }

           else if (input.startsWith("unmark ")) {
               int taskNum = Integer.parseInt(input.substring(7)) - 1; //zero-based indexing
               taskList.unmarkTask(taskNum);
               printDivider();
               System.out.println("OK! I've marked this task as not done yet: ");
               System.out.println(" " + taskList.get(taskNum));
               printDivider();
           }

           else if (input.startsWith("todo ")) {
               String description = input.substring(5);
               taskList.addToDo(description);
               printDivider();
               System.out.println("Got it. I've added this task:");
               System.out.println("  " + taskList.get(taskList.size() - 1));
               System.out.println("Now you have " + taskList.size() + " tasks in the list.");
               printDivider();
           }

           else if (input.startsWith("deadline ")) {
               String details = input.substring(9);
               int byIndex = details.indexOf("/by ");
               if (byIndex == -1) {
                   System.out.println("Please specify deadline with /by");
                   continue;
               }
               String description = details.substring(0, byIndex).trim();
               String by = details.substring(byIndex + 4).trim();
               taskList.addDeadline(description, by);
               printDivider();
               System.out.println("Got it. I've added this task:");
               System.out.println("  " + taskList.get(taskList.size() - 1));
               System.out.println("Now you have " + taskList.size() + " tasks in the list.");
               printDivider();
           }

           else if (input.startsWith("event ")) {
               String details = input.substring(6);
               int fromIndex = details.indexOf("/from ");
               int toIndex = details.indexOf("/to ");
               if (fromIndex == -1 || toIndex == -1) {
                   System.out.println("Please specify event with /from and /to");
                   continue;
               }
               String description = details.substring(0, fromIndex).trim();
               String from = details.substring(fromIndex + 6, toIndex).trim();
               String to = details.substring(toIndex + 4).trim();
               taskList.addEvent(description, from, to);
               printDivider();
               System.out.println("Got it. I've added this task:");
               System.out.println("  " + taskList.get(taskList.size() - 1));
               System.out.println("Now you have " + taskList.size() + " tasks in the list.");
               printDivider();
           }

           else {
               printDivider();
               System.out.println("I don't understand that command!");
               printDivider();
           }
        }

        printDivider();
        System.out.println("Bye. Hope to see you again soon! ");
        printDivider();
    }

    /**
     * Prints a divider line to the console
     */
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }
}
