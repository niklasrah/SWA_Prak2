package messaging;

public class Producer<T> {
    private SimpleMQ<T> sMq;

    public Producer() {
        sMq = SimpleMQ.getInstance();
    }

    public void addMessage(String mqName, T message) {
        if (!this.sMq.addMessage(mqName, message)) {
            System.out.println("Die Liste \"" + mqName + "\" ist voll");
        }
    }

    public void addMessage(String mqName, T message, boolean fixedCapacity, boolean discarding) {
        this.sMq.addMessage(mqName, message, fixedCapacity, discarding);
    }

    public void addListener(Consumer<T> con, String mqName) {
        this.sMq.addListener(con, mqName);
    }
}
