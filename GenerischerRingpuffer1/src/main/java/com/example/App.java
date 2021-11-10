package com.example;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Ringpuffer<Integer> ring = new Ringpuffer<Integer>();
        ring.add(5);
        ring.add(6);
        System.out.println(ring.remove());
        System.out.println(ring.remove());
    }
}
