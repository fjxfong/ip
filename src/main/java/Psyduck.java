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
        Tasklist tasklist = new Tasklist();
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
               System.out.println(tasklist.list());
               printDivider();
           }

           else if (input.startsWith("mark ")) {
               int taskNum = Integer.parseInt(input.substring(5)) - 1; //zero-based indexing
               tasklist.markTask(taskNum);
               printDivider();
               System.out.println("Nice! I've marked this task as done: ");
               System.out.println(" " + tasklist.get(taskNum));
               printDivider();
           }

           else if (input.startsWith("unmark ")) {
               int taskNum = Integer.parseInt(input.substring(7)) - 1; //zero-based indexing
               tasklist.unmarkTask(taskNum);
               printDivider();
               System.out.println("OK! I've marked this task as not done yet: ");
               System.out.println(" " + tasklist.get(taskNum));
               printDivider();
           }

           else {
               tasklist.add(input);
               printDivider();
               System.out.println("added: " + input);
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
