import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TxtTaskList {
    List<String> taskList;
    public TxtTaskList(String path) {
        Path filePath = Paths.get(path);
        List<String> tasks;
        try {
            tasks = Files.readAllLines(filePath);
        } catch (IOException e) { return; }
        this.taskList = tasks;
    }
    String readAllTasks() {
        if (this.taskList.isEmpty()) {
            return "\nNo todos for today! :)";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.taskList.toArray().length; i++) {
            output.append("\n").append(i + 1).append(" - ").append(this.taskList.get(i));
        }
        return output.toString();
    }
    String readUncheckedTasks() {
        if (this.taskList.isEmpty()) {
            return "\nNo todos for today! :)";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.taskList.toArray().length; i++) {
            if (this.taskList.get(i).charAt(1) == ' ') {
                output.append("\n").append(i + 1).append(" - ").append(this.taskList.get(i));
            }
        }
        if (output.isEmpty()) {
            return "Not a single unchecked task.";
        }
        return output.toString();

    }
    void addNewTask(String newTask) {
        newTask = newTask.toLowerCase();
        String cap = newTask.substring(0, 1).toUpperCase() + newTask.substring(1);
        this.taskList.add("[ ] " + cap);
        try {
            Files.write(Paths.get("tasks.txt"), this.taskList);
            System.out.println("\nThe " + cap + " task was successfully added to Todo.");
        } catch (Exception e1) {
            System.err.println("UNABLE TO WRITE TO FILE");
        }
    }
    void removeTask(String removeTask) {
        int index;
        try {
            index = Integer.parseInt(removeTask);
        } catch (NumberFormatException z) {
            System.out.println("Unable to remove: index is not a number.");
            return;
        }
        if (this.taskList.size() < 1) {
            System.out.println("Unable to remove, ToDo list is smaller than 2.");
            return;
        }
        try {
            this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException p) {
            System.out.println("Unable to remove: index out of bounds");
            return;
        }
        try {
            Files.write(Paths.get("tasks.txt"), this.taskList);
            System.out.println("\nThe " + removeTask + " task was removed from Todo.");
        } catch (Exception e1) {
            System.err.println("UNABLE TO WRITE TO FILE");
        }
    }
    void checkTask(String checkTask) {
        if (this.taskList.size() < 1) {
            System.out.println("Unable to check, ToDo list is smaller than 2.");
            return;
        }
        int index;
        try {
            index = Integer.parseInt(checkTask) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Unable to check: index is not a number.");
            return;
        }
        String checkString;
        try {
            checkString = this.taskList.get(index);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Unable to check: index is out of bounds.");
            return;
        }
        if (checkString.charAt(1) == 'X') {
            System.out.println("This task is already checked.");
            return;
        }
        checkString = checkString.replaceFirst(" ", "X");
        this.taskList.set(index,checkString);
        try {
            Files.write(Paths.get("tasks.txt"), this.taskList);
            System.out.println("The " + checkString + " task, was successfully checked.");
        } catch (Exception e1) {
            System.err.println("UNABLE TO WRITE TO FILE");
        }
    }
    void uncheckTask(String uncheckTask) {
        if (this.taskList.size() < 1) {
            System.out.println("Unable to check, ToDo list is smaller than 2.");
            return;
        }
        int index;
        try {
            index = Integer.parseInt(uncheckTask) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Unable to check: index is not a number.");
            return;
        }
        String checkString;
        try {
            checkString = this.taskList.get(index);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Unable to check: index is out of bounds.");
            return;
        }
        if (checkString.charAt(1) == ' ') {
            System.out.println("This task is already unchecked.");
            return;
        }
        checkString = checkString.replaceFirst("X", " ");
        this.taskList.set(index,checkString);
        try {
            Files.write(Paths.get("tasks.txt"), this.taskList);
            System.out.println("The " + checkString + " task, was successfully unchecked.");
        } catch (Exception e1) {
            System.err.println("UNABLE TO WRITE TO FILE");
        }
    }
}

