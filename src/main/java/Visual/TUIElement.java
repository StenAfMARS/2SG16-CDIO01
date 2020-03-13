package Visual;

public abstract class TUIElement {
    protected String name;

    protected TUIElement(String name){
        this.name = name;
    }

    protected abstract boolean run();

    public void work(){
        //noinspection StatementWithEmptyBody
        while(run()){}
    }
}
