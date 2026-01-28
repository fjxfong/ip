import java.util.*;
import Tasklist.*;

public class Psyduck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tasklist tasklist = new Tasklist();
        String greeting = """
                ____________________________________________________________\s
                Hello! I'm Psyduck\s
                What can I do for you?\s
                ____________________________________________________________""";
        System.out.println(greeting);

        //waits for input from user
        //will echo user input to output
        //will break if user input "bye"
        while (true) {
           String input = scanner.nextLine();

           if (input.equals("bye")) {
               break;
           }
           if (input.equals("list")) {
               System.out.println("____________________________________________________________");
               System.out.println(tasklist.list());
               System.out.println("____________________________________________________________");
           } else {
               tasklist.add(input);
               System.out.println("____________________________________________________________");
               System.out.println("added: " + input);
               System.out.println("____________________________________________________________");
           }
        }

        String goodbye = "Bye. Hope to see you again soon! \n" +
                "____________________________________________________________";

        System.out.println("____________________________________________________________");
        System.out.println(goodbye);
    }
}
