import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(TaskType title, String description, String taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return (getFirstDate().getDayOfMonth() == requestedDate.getDayOfMonth() && getFirstDate().getMonth() == requestedDate.getMonth());
    }
}