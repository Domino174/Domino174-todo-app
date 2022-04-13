
public class Main {
    public static void main(String[] args) {
        /*if we want to display in the console every input arg, just remove these comments.
        for (String arg : args) {
            System.out.println(arg);
        }*/
        ToDo toDoApp = new ToDo();
        if(args.length == 0) { toDoApp.welcomePrint(); }
        else { toDoApp.menu(args); }
    }
}
