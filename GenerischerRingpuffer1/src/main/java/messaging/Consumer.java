package messaging;

public class Consumer<T> {
    private String name;
    private SimpleMQ<T> sMq;

    public Consumer(String name) {
        this.name = name;
        sMq = SimpleMQ.getInstance();
    }

    public void getMessage(String mqName) {
        T message = this.sMq.getMessage(mqName);
        if (message == null) {
            System.out.println("Keine Message in \"" + mqName + "\"");
        } else {
            System.out.println("Message: " + message);
        }
    }

    public void update(T message) {
        System.out.println("Consumer " + this.name + " hat die Message " + message + " erhalten.");
    }
}
