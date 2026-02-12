package psyduck.task;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a single psyduck.task with a description, completion status and tags.
 */
public class Task {
    protected String description; // Description of psyduck.task
    protected boolean isDone; // Indicates whether the psyduck.task has been completed
    protected Set<String> tags; // Set of tags associated with task

    /**
     * Creates a new psyduck.task with the given description.
     *
     * @param description Description of the psyduck.task.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    /**
     * Marks the psyduck.task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the psyduck.task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns whether the psyduck.task is done.
     *
     * @return True if psyduck.task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * returns the description of the psyduck.task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon representing the completion state.
     * "X" indicates completed, blank indicates not completed.
     *
     * @return Status icon of string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag Tag to add (without the # prefix).
     */
    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    /**
     * Removes a tag from the task.
     *
     * @param tag Tag to remove (without the # prefix).
     */
    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase());
    }

    /**
     * Returns all tags associated with this task.
     *
     * @return Set of tags.
     */
    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    /**
     * Checks if the task has a specific tag.
     *
     * @param tag Tag to check for.
     * @return True if task has the tag.
     */
    public boolean hasTag(String tag) {
        return tags.contains(tag.toLowerCase());
    }

    /**
     * Returns tags formatted for display.
     *
     * @return Formatted tags string (e.g., "#work #urgent").
     */
    public String getTagsForDisplay() {
        if (tags.isEmpty()) {
            return "";
        }
        return tags.stream()
                .sorted()
                .map(tag -> "#" + tag)
                .collect(Collectors.joining(" "));
    }

    /**
     * Returns tags formatted for storage.
     *
     * @return Comma-separated tags.
     */
    public String getTagsForStorage() {
        if (tags.isEmpty()) {
            return "";
        }
        return String.join(",", tags);
    }

    /**
     * Sets tags from storage format.
     *
     * @param tagsStr Comma-separated tags string.
     */
    public void setTagsFromStorage(String tagsStr) {
        if (tagsStr == null || tagsStr.trim().isEmpty()) {
            return;
        }
        String[] tagArray = tagsStr.split(",");
        for (String tag : tagArray) {
            if (!tag.trim().isEmpty()) {
                tags.add(tag.trim().toLowerCase());
            }
        }
    }

    /**
     * Returns a formatted string representation of the psyduck.task.
     *
     * @return Task description with completion status.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
