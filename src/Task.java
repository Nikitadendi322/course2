import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private String title;
    private String description;
    private final TaskType taskType;
    private final LocalDateTime firstDate;
    private boolean archived;
    private static Integer counter = 1;
    private final Integer id;

    public Task(String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException {
        this.title = ValidateUtils.checkString(title);
        this.description = ValidateUtils.checkString(description);
        this.taskType = taskType;
        this.firstDate = localDateTime;
        this.archived = false;
        id = counter++;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public abstract boolean checkOccurrence(LocalDateTime localDateTime);

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title,description,taskType,firstDate,id);
    }

    @Override
    public String toString() {
        return String.valueOf(Objects.hash(title, description, taskType, firstDate, archived, id));
    }
}