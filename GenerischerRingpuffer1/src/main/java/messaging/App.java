package messaging;

public final class App {
    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Producer<Integer> pro = new Producer<Integer>();
        Consumer<Integer> con1 = new Consumer<Integer>("Niklas");
        Consumer<Integer> con2 = new Consumer<Integer>("Steffen");
        pro.addListener(con1, "GanzeZahlen");
        pro.addListener(con2, "Primzahlen");
        pro.addMessage("GanzeZahlen", 0);
        pro.addMessage("GanzeZahlen", 1);
        pro.addMessage("Primzahlen", 2, true, false);
        pro.addMessage("Primzahlen", 3);
        pro.addMessage("Primzahlen", 5);
        pro.addMessage("Primzahlen", 7);
        pro.addMessage("Primzahlen", 11);
        con1.getMessage("GanzeZahlen");
        con1.getMessage("GanzeZahlen");
        con1.getMessage("GanzeZahlen");
        con1.getMessage("NatuerlicheZahlen");
    }
}
