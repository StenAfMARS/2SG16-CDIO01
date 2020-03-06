package Visual;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.Consumer;

public class TUIMemory {
    Stack<HashMap<String, Object>> stack = new Stack<>();

    private void newScope() {
        stack.push(new HashMap<>());
    }
    private void oldScope() {
        stack.pop();
    }

    public void runScope(Consumer<TUIMemory> consumer){
        newScope();
        consumer.accept(this);
        oldScope();
    }

    public HashMap<String, Object> getScope(int i){
        if (i < 0 || i >= stack.size())
            return null;
        else
            return stack.elementAt(i);
    }
}