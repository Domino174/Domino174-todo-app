
public class ToDo {
    void welcomePrint() {
        System.out.println("""
                $ todo

                Command Line Todo application
                =============================

                Command line arguments:
                -la  Lists all the tasks
                -l   Lists unchecked tasks
                -a   Adds a new task
                -r   Removes an task
                -c   Completes an task
                -u   Uncompleted an task""");
    }
    void menu(String[] args) {
        TxtTaskList taskList = new TxtTaskList("tasks.txt");
        switch(args[0]) {
            case "-la" -> System.out.println(taskList.readAllTasks());
            case "-l" -> System.out.println(taskList.readUncheckedTasks());
            case "-a" -> {
                try {
                    taskList.addNewTask(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Unable to add: no task provided");
                }
            }
            case "-r" -> {
                try {
                    taskList.removeTask(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Unable to remove: no index provided");
                }
            }
            case "-c" -> {
                try {
                    taskList.checkTask(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Unable to check: no index provided");
                }
            }
            case "-u" -> {
                try {
                    taskList.uncheckTask(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Unable to check: no index provided");
                }
            }
            default -> System.out.println("""
                    Unsupported argument
                    
                    $ todo

                    Command Line Todo application
                    =============================

                    Command line arguments:
                    -la  Lists all the tasks
                    -l   Lists unchecked tasks
                    -a   Adds a new task
                    -r   Removes an task
                    -c   Completes an task
                    -u   Uncompleted an task""");
        }
    }
}
