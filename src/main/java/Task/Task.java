package Task;

public class Task {
    protected String description; //name of task
    protected boolean isDone; //true when marked as done, false when not done

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() { //task marked as done
        isDone = true;
    }

    public void unmark() { //task marked as not done
        isDone = false;
    }

    public String statusIcon() { //true will return "X", false return " "
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + statusIcon() + "] " + description;
    }
}
