package Visual;

import consoleColours.ColouredSystemOutPrint;

public class Menu extends TUIElement {
    private TUIElement[] TUIElements;
    private String color;

    public Menu(String name, TUIElement... TUIElements){
        super(name);
        this.TUIElements = TUIElements;
        this.color = ColouredSystemOutPrint.ANSI_RESET;
    }
    public Menu(String name, String color, TUIElement... TUIElements){
        super(name);
        this.TUIElements = TUIElements;
        this.color = color;
    }

    @Override
    public boolean workOnce(staticTUI tui) {
        StringBuilder sb = new StringBuilder();

        sb.append(color);

        for (int i = 0; i < TUIElements.length; i++) {
            sb.append(i + 1);
            sb.append(" - ");
            sb.append(TUIElements[i].name);
            sb.append("\n");
        }

        sb.append("0 - Back");

        tui.println(sb.toString());

        try {
            int i = Integer.parseInt(tui.getLine());

            if (i == 0)
                return false;
            else
                TUIElements[i - 1].work(tui);
        }
        catch (Exception ignored){}

        return true;
    }
}
