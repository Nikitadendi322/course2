import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TaskNotFoundException {
        try {
            Scanner scanner = new Scanner(System.in);
            {
                label:
                while (true) {
                    System.out.println("Выбрать пункт меню");
                    printMenu();
                    if (scanner.hasNext()) {
                        int menu = scanner.nextInt();
                        {
                            switch (menu) {
                                case 1:
                                    MyCalendar.addTask(scanner);
                                    break;
                                case 2:
                                    MyCalendar.editTask(scanner);
                                    break;
                                case 3:
                                    MyCalendar.deleteTask(scanner);
                                    break;
                                case 4:
                                    MyCalendar.getTaskByDay(scanner);

                                case 0:
                                    break label;
                            }
                        }
                    } else {
                        scanner.next();
                        System.out.println("Выберите меню из списка!");
                    }
                }
            }

        } finally {

        }

    }

    private static void printMenu() {
        System.out.println("1.Добавить задачу" +
                "2.Удалить задачу" +
                "3.Редактировать задачу" +
                "4.Получить задачи на день");


    }
}