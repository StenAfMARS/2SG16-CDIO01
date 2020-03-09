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

                do {staticTUI.print("username: ");}
                while (!user.setUserName(staticTUI.getLine()));

                do {staticTUI.print("initials: ");}
                while (!user.setIni(staticTUI.getLine()));

                do {staticTUI.print("cpr: ");}
                while (!user.setCpr(staticTUI.getLine()));

                staticTUI.print("roles: ");
                user.setRoles(Arrays.asList(staticTUI.getLine().split(" ")));

                users.createUser(user);
            }),
            new Process("Vis Brugere", () -> {
                for (UserDTO user : users.getUserList()){
                    staticTUI.println(user.getUserID() + " - " + user.getUserName());
                }
            }),
            new Process("Vis Bruger", () -> {
                UserDTO user;

                while (true){
                    staticTUI.print("userID: ");
                    try {
                        user = users.getUser(Integer.parseInt(staticTUI.getLine()));
                        break;
                    } catch (Exception e) {}
                }

                staticTUI.print("username: ");
                staticTUI.println( user.getUserName() );

                staticTUI.print("password: ");
                staticTUI.println( user.getPassword() );

                staticTUI.print("ini: ");
                staticTUI.println( user.getIni());

                staticTUI.print("cpr: ");
                staticTUI.println( user.getCpr());

                staticTUI.print("roles: ");
                staticTUI.println( user.getRoles().toString() );
            }),
            new Process("Opdater Bruger", () -> {
                UserDTO user;

                while (true){
                    staticTUI.print("userID: ");
                    try {
                        user = users.getUser(Integer.parseInt(staticTUI.getLine()));
                        break;
                    } catch (Exception e) {}
                }

                do {staticTUI.print("new username: ");}
                while (!user.setUserName(staticTUI.getLine()));

                do {staticTUI.print("new password: ");}
                while (!user.setPassword(staticTUI.getLine()));

                do {staticTUI.print("new ini: ");}
                while (!user.setIni(staticTUI.getLine()));

                do {staticTUI.print("new cpr: ");}
                while (!user.setCpr(staticTUI.getLine()));

                staticTUI.print("new roles: ");
                user.setRoles(Arrays.asList(staticTUI.getLine().split(" ")));

                users.updateUser(user);
            }),
            new Process("Slet Bruger", () -> {
                while (true){
                    staticTUI.print("userID: ");
                    try {
                        users.deleteUser(Integer.parseInt(staticTUI.getLine()));
                        break;
                    } catch (Exception e) {}
                }
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
