package Visual;

public class Menu extends TUIElement {
    private TUIElement[] TUIElements;
    private String color;

    public Menu(String name, TUIElement... TUIElements){
        super(name);
        this.TUIElements = TUIElements;
        this.color = null;
    }
    public Menu(String name, String color, TUIElement... TUIElements){
        super(name);
        this.TUIElements = TUIElements;
        this.color = color;
    }

    @Override
    protected boolean run() {
        StringBuilder sb = new StringBuilder();

        if (color != null)
            sb.append(color);

        for (int i = 0; i < TUIElements.length; i++) {
            sb.append(i + 1);
            sb.append(" - ");
            sb.append(TUIElements[i].name);
            sb.append("\n");
        }

        sb.append("0 - Back");
        if (color != null)
            sb.append(ColouredSystemOutPrint.ANSI_RESET);

        staticTUI.println(sb.toString());

        try {
            int i = Integer.parseInt(staticTUI.getLine());

            if (i == 0)
                return false;
            else
                TUIElements[i - 1].work();
        }
        catch (Exception ignored){}

        return true;
    }
}
