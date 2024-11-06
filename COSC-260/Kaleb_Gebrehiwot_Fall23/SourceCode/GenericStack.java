/**
 * This is a custom implementation of a Generic Stack. 
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
import java.util.ArrayList;

public class GenericStack<T> {
    private ArrayList<T> stack;

    public GenericStack() {
        stack = new ArrayList<>();
    }

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        // return next object and remove form stack
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        // preview next object without popping 
        if (stack.isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
