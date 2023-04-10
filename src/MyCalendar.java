import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.*;


public class MyCalendar {

    private static final Map<Integer, Task> actualTask = new HashMap<>();


    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите название задачи: ");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи: 0 - Рабочая 1 - личная ");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0 - Однократная, 1 - Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная ");
            int occurrence = scanner.nextInt();
            System.out.println("Введите дату dd.mm.yyyy HH:mm ");
            scanner.nextLine();
            createEvent(scanner, title, description, taskType, occurrence);
            System.out.println("Для входа нажмите Enter");
            scanner.nextLine();
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occerrence) throws WrongInputException {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(),DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm "));
            Task task;
            task = createTask(occerrence, title, description, taskType, eventDate);
            System.out.println("Создана задача" + task);

        } catch (DateTimeException e) {
            System.out.println("Проверьте формат даты и попробуйте еще раз");
            createEvent(scanner, title, description, taskType, occerrence);
        }
    }
    private static Task createTask(int occurrence, String tittle, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException {
        return switch (occurrence) {
            case 0 -> {
                OncelyTask oncelyTask = new OncelyTask(tittle, description, taskType, localDateTime);
                actualTask.put(oncelyTask.getId(), oncelyTask);
                yield oncelyTask;
            }
            case 1 -> {
                DailyTask task = new DailyTask(tittle, description, taskType, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(tittle, description, taskType, localDateTime);
                actualTask.put(task.getId(), task);
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(tittle, description, taskType, localDateTime);
                actualTask.put(task.getId(), task);
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(taskType, tittle, description, localDateTime);
                actualTask.put(task.getId(), task);
            }
        };
    }

    public static List<Task> findTaskByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : actualTask.values()) {
            if (task.checkOccurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }


    public static void getTaskByDay(Scanner scanner) {
        System.out.println("Введите дату dd.MM.yyyy");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateTimeFormatter);
            List<Task> foundEvent = findTaskByDate(requestedDate);
            System.out.println("События на " + requestedDate + ";");
            for (Task task : foundEvent) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте форматы даты и попробуйте еще раз");
        }
        scanner.nextLine();
        System.out.println("Для входа нажмите Enter");
    }
    public static void editTask(Scanner scanner) {
        try {
            System.out.println("Редактирование задачи: введите Id");
            printActualTask();
            int id = scanner.nextInt();
            if (!actualTask.containsKey(id)) ;
            {
                throw new TaskNotFoundException("Задачи не найдены");
            }
            System.out.println("Редактирование");
            int menuCase = scanner.nextInt();
            switch (menuCase) {
                case 0 -> {
                    scanner.nextLine();
                    System.out.println("Введите название задачи");
                    String tittle = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setTitle(tittle);
                }
                case 1 -> {
                    scanner.nextLine();
                    System.out.println("Введите описание задачи");
                    String description = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setDescription(description);
                }
                case 2 -> {
                    scanner.nextLine();

                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.println("Проверьте описание задачи");
                    String taskType = scanner.next();
                    Task task = actualTask.get(id);
                    task;
                }
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessange());
        }
    }

    public static void deleteTask(Scanner scanner) throws TaskNotFoundException {
        int i = scanner.nextInt();
        if (!actualTask.containsKey(i)) {
            throw new TaskNotFoundException();
        }
        actualTask.remove(i);
    }

    private static void printActualTask() {
        for (Task task : actualTask.values()) {
            System.out.println(task);
        }
    }

    private static class yield {
    }
}