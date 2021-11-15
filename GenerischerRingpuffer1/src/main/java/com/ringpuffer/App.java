package com.ringpuffer;

public final class App {
    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Test einer FixedRingliste ohne Ueberschreiben");
        Ringpuffer<Integer> ringFixed = new Ringpuffer<Integer>(3, true, false);
        ringFixed.add(1);
        System.out.println(ringFixed);
        ringFixed.offer(2);
        System.out.println(ringFixed);
        ringFixed.add(3);
        System.out.println(ringFixed);
        System.out.println(ringFixed.remove());
        System.out.println(ringFixed);
        ringFixed.add(4);
        System.out.println(ringFixed);
        try {
            ringFixed.add(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(ringFixed);

        System.out.println("");
        System.out.println("Test einer FixedRingliste mit Ueberschreiben");
        Ringpuffer<Integer> ringFixedDis = new Ringpuffer<Integer>(3, true, true);
        ringFixedDis.add(1);
        System.out.println(ringFixedDis);
        ringFixedDis.offer(2);
        System.out.println(ringFixedDis);
        ringFixedDis.add(3);
        System.out.println(ringFixedDis);
        System.out.println(ringFixedDis.remove());
        System.out.println(ringFixedDis);
        ringFixedDis.add(4);
        System.out.println(ringFixedDis);
        ringFixedDis.add(5);
        System.out.println(ringFixedDis);
        System.out.println("clear");
        ringFixedDis.clear();
        System.out.println(ringFixedDis);

        System.out.println("");
        System.out.println("Test einer NotFixedRingliste");
        Ringpuffer<Integer> ringNotFixed = new Ringpuffer<Integer>(3, false, false);
        ringNotFixed.add(1);
        System.out.println(ringNotFixed);
        ringNotFixed.add(2);
        System.out.println(ringNotFixed);
        ringNotFixed.add(3);
        System.out.println(ringNotFixed);
        ringNotFixed.add(4);
        System.out.println(ringNotFixed);
        ringNotFixed.add(5);
        System.out.println(ringNotFixed);

    }
}
