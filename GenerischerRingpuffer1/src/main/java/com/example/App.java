package com.example;

import java.util.ArrayList;
import java.util.Collection;

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
        ring.add(9);
        System.out.println(ring);
        ring.add(10);
        System.out.println(ring);
        // ring.remove(1);
        System.out.println(ring);

        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(1);
        array.add(3);
        array.add(2);
        ring.retainAll(array);
        System.out.println(ring.contains(2));
        for (Object el : ring.toArray()) {
            System.out.println(el + " ");
        }
    }
}
