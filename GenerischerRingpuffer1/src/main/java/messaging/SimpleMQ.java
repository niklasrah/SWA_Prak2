package messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.example.Ringpuffer;

public class SimpleMQ<T> {
    private final static SimpleMQ OBJ = new SimpleMQ();
    private Map<String, Ringpuffer<T>> messageQueue;
    private Map<String, ArrayList<Consumer<T>>> listener;

    private SimpleMQ() {
        this.messageQueue = new HashMap<String, Ringpuffer<T>>();
        this.listener = new HashMap<String, ArrayList<Consumer<T>>>();
    }

    public static SimpleMQ getInstance() {
        return OBJ;
    }

    public boolean addMessage(String mqName, T message) {
        return this.addMessage(mqName, message, false, false);
    }

    public boolean addMessage(String mqName, T message, boolean fixedCapacity, boolean discarding) {
        if (!this.messageQueue.containsKey(mqName)) {
            this.messageQueue.put(mqName, new Ringpuffer<T>(4, fixedCapacity, discarding));
        }
        if (this.messageQueue.get(mqName).offer(message)) {
            this.notifyListener(mqName, message);
            return true;
        }
        return false;
    }

    public T getMessage(String mqName) {
        if (this.messageQueue.get(mqName) == null) {
            return null;
        }
        T result;
        try {
            result = this.messageQueue.get(mqName).remove();
        } catch (NoSuchElementException e) {
            return null;
        }
        return result;
    }

    private void notifyListener(String mqName, T message) {
        if (this.listener.get(mqName) == null) {
            return;
        }
        for (Consumer<T> con : this.listener.get(mqName)) {
            con.update(message);
        }
    }

    public void addListener(Consumer<T> con, String mqName) {
        if (!this.listener.containsKey(mqName)) {
            this.listener.put(mqName, new ArrayList<Consumer<T>>());
        }
        this.listener.get(mqName).add(con);
    }

    public String toString(String mqName) {
        return this.messageQueue.get(mqName) + "";
    }
}
