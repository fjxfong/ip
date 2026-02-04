package psyduck.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.task.Deadline;
import psyduck.task.Event;
import psyduck.task.Task;
import psyduck.task.ToDo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Storage class.
 */
public class StorageTest {
    private static final String TEST_FILE_PATH = "./data/test_psyduck.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        // Clean up test file after each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testLoadEmptyFile() throws PsyduckException {
        // File does not exist, should return empty list
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void testSaveAndLoadToDo() throws PsyduckException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("borrow book"));
        storage.save(tasks);

        ArrayList<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals("[T][ ] borrow book", loaded.get(0).toString());
    }

    @Test
    public void testSaveAndLoadDeadline() throws PsyduckException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("return book", "2024-12-15"));
        storage.save(tasks);

        ArrayList<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals("[D][ ] return book (by: Dec 15 2024)", loaded.get(0).toString());
    }

    @Test
    public void testSaveAndLoadEvent() throws PsyduckException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("meeting", "2024-12-20", "2024-12-21"));
        storage.save(tasks);

        ArrayList<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals("[E][ ] meeting (from: Dec 20 2024 to: Dec 21 2024)", loaded.get(0).toString());
    }

    @Test
    public void testSaveAndLoadMarkedTask() throws PsyduckException {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo todo = new ToDo("borrow book");
        todo.mark();
        tasks.add(todo);
        storage.save(tasks);

        ArrayList<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0).isDone());
        assertEquals("[T][X] borrow book", loaded.get(0).toString());
    }

    @Test
    public void testSaveAndLoadMultipleTasks() throws PsyduckException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("borrow book"));
        tasks.add(new Deadline("return book", "2024-12-15"));
        tasks.add(new Event("meeting", "2024-12-20", "2024-12-21"));
        storage.save(tasks);

        ArrayList<Task> loaded = storage.load();
        assertEquals(3, loaded.size());
    }

    @Test
    public void testLoadCorruptedFile() throws PsyduckException {
        // Write corrupted data manually
        Files.writeString(Paths.get(TEST_FILE_PATH), "corrupted data\n");

        ArrayList<Task> loaded = storage.load();
        // Corrupted lines are skipped, so list should be empty
        assertTrue(loaded.isEmpty());
    }

    @Test
    public void testLoadPartiallyCorruptedFile() throws PsyduckException {
        // Write mix of valid and corrupted data
        String content = "T | 0 | borrow book\n"
                + "corrupted line\n"
                + "D | 1 | return book | 2024-12-15\n";
        Files.writeString(Paths.get(TEST_FILE_PATH), content);

        ArrayList<Task> loaded = storage.load();
        // Only 2 valid tasks loaded, corrupted line is skipped
        assertEquals(2, loaded.size());
        assertEquals("[T][ ] borrow book", loaded.get(0).toString());
        assertTrue(loaded.get(1).isDone());
    }

    @Test
    public void testSaveCreatesDirectory() throws PsyduckException {
        String nestedPath = "./data/nested/test_psyduck.txt";
        Storage nestedStorage = new Storage(nestedPath);

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("test"));
        nestedStorage.save(tasks);

        assertTrue(new File(nestedPath).exists());

        // Cleanup
        new File(nestedPath).delete();
        new File("./data/nested").delete();
    }
}