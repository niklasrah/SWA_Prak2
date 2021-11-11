package com.example;

public final class App {
    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Ringpuffer<Integer> ring = new Ringpuffer<Integer>();
        ring.add(5);
        ring.add(6);
        System.out.println(ring.remove());
        System.out.println(ring.remove());
    }
}
