import java.time.LocalDateTime;

public class OncelyTask extends Task{
    public OncelyTask(String title, String description, TaskType taskType, LocalDateTime date)throws WrongInputException{
        super(title,description,taskType,date);
    }

    @Override
    public boolean checkOccurrence(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }
}