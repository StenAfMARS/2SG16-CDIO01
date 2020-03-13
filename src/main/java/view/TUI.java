package view;

import Data.UserDTO;
import Function.IUserDAO;
import Visual.*;
import Visual.Process;

import java.util.Arrays;

public class TUI {
    IUserDAO userDAO;
    TUIElement main;

    public TUI (IUserDAO userDAO) {
        this.userDAO = userDAO;

        main = new Menu("mainMenu", ColouredSystemOutPrint.ANSI_PURPLE,
                new Process("Opret Bruger", () -> {
                    UserDTO user = new UserDTO();

                    user.randomizePassword();

                    do {
                        staticTUI.print("username: ");}
                    while (!user.setUserName(staticTUI.getLine()));

                    do {staticTUI.print("initials: ");}
                    while (!user.setIni(staticTUI.getLine()));

                    do {staticTUI.print("cpr: ");}
                    while (!user.setCpr(staticTUI.getLine()));

                    staticTUI.print("roles: ");
                    user.setRoles(Arrays.asList(staticTUI.getLine().split(" ")));

                    userDAO.createUser(user);
                }),
                new Process("Vis Brugere", () -> {
                    for (UserDTO user : userDAO.getUserList()){
                        staticTUI.println(user.getUserID() + " - " + user.getUserName());
                    }
                }),
                new Process("Vis Bruger", () -> {
                    UserDTO user;

                    while (true){
                        staticTUI.print("userID: ");
                        try {
                            user = userDAO.getUser(Integer.parseInt(staticTUI.getLine()));
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
                            user = userDAO.getUser(Integer.parseInt(staticTUI.getLine()));
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

                    userDAO.updateUser(user);
                }),
                new Process("Slet Bruger", () -> {
                    while (true){
                        staticTUI.print("userID: ");
                        try {
                            userDAO.deleteUser(Integer.parseInt(staticTUI.getLine()));
                            break;
                        } catch (Exception e) {}
                    }
                })
        );
    }

    public void work(){
        main.work();
    }
}
