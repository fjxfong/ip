package psyduck.storage;

import psyduck.PsyduckException;
import psyduck.task.Task;
import psyduck.task.ToDo;
import psyduck.task.Deadline;
import psyduck.task.Event;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving of tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath Path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return List of tasks loaded from file.
     * @throws PsyduckException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws PsyduckException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create directory if it doesn't exist
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new PsyduckException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line Line from the file.
     * @return Task object or null if line is corrupted.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null; // Corrupted data
            }

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = switch (type) {
                case "T" -> new ToDo(description);
                case "D" -> {
                    if (parts.length < 4) yield null;
                    yield new Deadline(description, parts[3]);
                }
                case "E" -> {
                    if (parts.length < 5) yield null;
                    yield new Event(description, parts[3], parts[4]);
                }
                default -> null;
            };

            if (task != null && isDone) {
                task.mark();
            }

            return task;
        } catch (Exception e) {
            // Corrupted data, skip this line
            return null;
        }
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks List of tasks to save.
     * @throws PsyduckException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws PsyduckException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            // Create directory if it doesn't exist
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(formatTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new PsyduckException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Formats a psyduck.task for saving to file.
     *
     * @param task Task to format.
     * @return Formatted string representation of the psyduck.task.
     */
    private String formatTask(Task task) {
        String type;
        String additionalInfo = "";

        if (task instanceof ToDo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
            Deadline deadline = (Deadline) task;
            additionalInfo = " | " + deadline.getByForStorage();
        } else if (task instanceof Event) {
            type = "E";
            Event event = (Event) task;
            additionalInfo = " | " + event.getFromForStorage() + " | " + event.getToForStorage();
        } else {
            type = "T"; // Default to ToDo
        }

        int isDone = task.isDone() ? 1 : 0;
        return type + " | " + isDone + " | " + task.getDescription() + additionalInfo;
    }
}