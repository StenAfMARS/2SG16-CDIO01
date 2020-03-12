package Visual;

import Data.UserDTO;
import Function.IUserDAO;
import Function.SaveInList;

import java.lang.reflect.Array;
import java.util.*;

public class staticTUI {
    static Scanner in = new Scanner(System.in);

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
