package Visual;

public abstract class TUIElement {
    protected String name;

    protected TUIElement(String name){
        this.name = name;
    }

    public abstract boolean workOnce(staticTUI tui);
    public void work(staticTUI tui){
        //noinspection StatementWithEmptyBody
        while(workOnce(tui)){}
    }
}
