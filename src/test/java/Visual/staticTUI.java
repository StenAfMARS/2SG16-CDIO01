package Visual;

import consoleColours.ColouredSystemOutPrint;

import java.util.Scanner;
import java.util.function.Predicate;

public class staticTUI {

    Scanner in = new Scanner(System.in);
    String textColor = ColouredSystemOutPrint.ANSI_CYAN;

    private void println(String string){
        StringBuilder sb = new StringBuilder(string.length() + 20);

        sb.append(textColor);
        sb.append(string);
        sb.append(ColouredSystemOutPrint.ANSI_RESET);

        System.out.println(sb.toString());
    }

    public String getUserString(String text){
        println(text);
        return in.nextLine();
    }
    public String getUserString(String text, Predicate<String> criteria){
        println(text);
        String userString = in.nextLine();
        while (criteria.test(userString)){
            println("Please enter a valid string. Type \\C to return");
            userString = in.nextLine();
        }
        return userString;
    }



}
