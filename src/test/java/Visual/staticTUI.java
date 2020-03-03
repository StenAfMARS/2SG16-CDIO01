package Visual;

import consoleColours.ColouredSystemOutPrint;

import java.util.Scanner;

public class staticTUI {
    public static void main(String[] args) {
        staticTUI s = new staticTUI();

        Menu m =
            new Menu("main menu", ColouredSystemOutPrint.ANSI_PURPLE,
                new Process("Create User"),
                new Process("See Users"),
                new Menu("Edit User", ColouredSystemOutPrint.ANSI_PURPLE,
                    new Process("Change Username"),
                    new Process("Change Initials"),
                    new Process("Change CPR number"),
                    new Process("Change password"),
                    new Process("Change Roles")
                ),
                new Process("Delete User")
            );
        m.work(s);
    }

    Scanner in = new Scanner(System.in);

    public void println(String string){
        System.out.println(string);
    }
    public String getLine(){
        return in.nextLine();
    }
}
