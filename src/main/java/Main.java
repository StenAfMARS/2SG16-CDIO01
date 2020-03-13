import Function.SaveInFile;
import Function.SaveInDatabase;
import Function.SaveInList;
import view.TUI;

public class Main {
    public static void main(String[] args) {
        //new TUI(new SaveInDatabase()).work();
        new TUI(new SaveInFile()).work();
    }
}
