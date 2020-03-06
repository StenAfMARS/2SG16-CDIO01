package Visual;

import Data.UserDTO;
import Function.IUserDAO;
import Function.SaveInList;

import java.lang.reflect.Array;
import java.util.*;

public class staticTUI {
    public static void main(String[] args) {
        in = new Scanner(System.in);

        IUserDAO users = new SaveInList();

        new Menu("mainMenu", ColouredSystemOutPrint.ANSI_PURPLE,
            new Process("Opret Bruger", () -> {
                UserDTO user = new UserDTO();

                staticTUI.print("username: ");
                user.userName = staticTUI.getLine();

                staticTUI.print("initials: ");
                user.ini = staticTUI.getLine();

                staticTUI.print("cpr: ");
                user.cpr = staticTUI.getLine();

                staticTUI.print("roles: ");
                user.roles = Arrays.asList(staticTUI.getLine().split(" "));

                users.createUser(user);
            }),
            new Process("Vis Brugere", () -> {
                for (UserDTO user : users.getUserList()){
                    staticTUI.println(user.userID + " " + user.userName);
                }
            }),
            new Process("Opdater Bruger", () -> {
                UserDTO user = new UserDTO();
                staticTUI.print("userID: ");
                user.userID = Integer.parseInt(staticTUI.getLine());

                staticTUI.print("username: ");
                user.userName = staticTUI.getLine();

                staticTUI.print("password: ");
                user.password = staticTUI.getLine();

                staticTUI.print("initials: ");
                user.ini = staticTUI.getLine();

                staticTUI.print("cpr: ");
                user.cpr = staticTUI.getLine();

                staticTUI.print("roles: ");
                user.roles = Arrays.asList(staticTUI.getLine().split(" "));

                users.updateUser(user);
            }),
            new Process("Slet Bruger", () -> {
                staticTUI.print("brugerID: ");
                try {users.deleteUser(Integer.parseInt(staticTUI.getLine()));}
                catch (Exception e) {}
            })
        ).work();
    }

    static Scanner in;

    public static void print(String string){
        System.out.print(string);
    }
    public static void println(String string){
        System.out.println(string);
    }
    public static String getLine(){
        return in.nextLine();
    }
}
