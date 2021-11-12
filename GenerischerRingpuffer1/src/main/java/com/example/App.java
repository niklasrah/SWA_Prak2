package com.example;

public final class App {
    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Ringpuffer<Integer> ring = new Ringpuffer<Integer>(3, false, false);
        System.out.println(ring);
        ring.add(1);
        System.out.println(ring);
        ring.add(2);
        System.out.println(ring);
        ring.remove();
        System.out.println(ring);
        ring.add(3);
        System.out.println(ring);
        ring.add(4);
        System.out.println(ring);
        ring.add(5);
        System.out.println(ring);
        ring.add(6);
        System.out.println(ring);
        ring.add(7);
        System.out.println(ring);
        ring.add(8);
        System.out.println(ring);
    }
}
