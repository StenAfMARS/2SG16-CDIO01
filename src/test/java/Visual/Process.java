package Visual;

public class Process<T> extends TUIElement {
    protected Process(String name) {
        super(name);
    }

    @Override
    public boolean workOnce(staticTUI tui) {
        return false;
    }
}
