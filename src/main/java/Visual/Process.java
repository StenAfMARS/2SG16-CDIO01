package Visual;

public class Process extends TUIElement {
    Runnable runnable;

    public Process(String name, Runnable function) {
        super(name);
        this.runnable = function;
    }

    @Override
    protected boolean run() {
        runnable.run();
        return false;
    }
}