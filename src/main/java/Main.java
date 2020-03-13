import Function.SaveInFile;
import Function.SaveInDatabase;
import Function.SaveInList;
import Visual.*;
import Visual.Process;
import view.TUI;

public class Main {
    public static void main(String[] args) {
        //new TUI(new SaveInDatabase()).work();
        new Menu("mainMenu",
            new Process("Save in list", () -> new TUI(new SaveInList()).work()),
            new Process("Save in files", () -> new TUI(new SaveInFile()).work()),
            new Process("Save in database", () -> new TUI(new SaveInDatabase()).work())
        ).work();
    }
}