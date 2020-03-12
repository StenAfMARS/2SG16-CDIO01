package Visual;

import java.util.Arrays;
import java.util.List;

public class Menu extends TUIElement {
    private List<TUIElement> TUIElements;
    private String color;

    public Menu(String name, TUIElement... TUIElements){
        super(name);
        this.TUIElements = Arrays.asList(TUIElements);
        this.color = null;
    }
    public Menu(String name, String color, TUIElement... TUIElements){
        super(name);
        this.TUIElements = Arrays.asList(TUIElements);
        this.color = color;
    }

    public void AddMenuPoint(TUIElement tuiElement){
        TUIElements.add(tuiElement);
    }

    public boolean RemoveMenuPoint(TUIElement tuiElement){
        return TUIElements.remove(tuiElement);
    }

    @Override
    protected boolean run() {
        StringBuilder sb = new StringBuilder();

        if (color != null)
            sb.append(color);

        int i = 0;
        for (TUIElement tuiElement : TUIElements) {
            sb.append(++i);
            sb.append(" - ");
            sb.append(tuiElement.name);
            sb.append("\n");
        }

        sb.append("0 - Back");
        if (color != null)
            sb.append(ColouredSystemOutPrint.ANSI_RESET);

        staticTUI.println(sb.toString());

        try {
            i = Integer.parseInt(staticTUI.getLine());

            if (i == 0)
                return false;
            else
                TUIElements.get(i - 1).work();
        }
        catch (Exception ignored){}

        return true;
    }
}
