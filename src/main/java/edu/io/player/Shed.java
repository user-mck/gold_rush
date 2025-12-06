package edu.io.player;
import java.util.Stack;

public class Shed {
    private final Stack<Tool> tools = new Stack<>();

    public boolean isEmpty() {
        return tools.isEmpty();
    }

    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Tool cannot be null");
        }
        tools.push(tool);
    }

    public Tool getTool() {
        if (isEmpty()) {
            return new NoTool();
        }
        return tools.peek();
    }

    public void dropTool() {
        if (!isEmpty()) {
            tools.pop();
        }
    }
}