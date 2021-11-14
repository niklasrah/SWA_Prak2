package messaging;

import java.util.Map;

import com.example.Ringpuffer;

public class SimpleMQ<T> {
    private final static SimpleMQ OBJ = new SimpleMQ();
    private Map<String, Ringpuffer<T>> messageQueue;

    private SimpleMQ() {

    }

    public static SimpleMQ getInstance() {
        return OBJ;
    }
}
