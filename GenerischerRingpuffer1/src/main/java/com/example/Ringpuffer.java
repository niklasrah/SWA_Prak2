package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Ringpuffer<T> implements Queue<T>, Serializable {

    private ArrayList<T> elements;
    private int writePos = 0;
    private int readPos = 0;
    private int size = 0;
    private int capacity;
    private final boolean fixedCapacity;
    private final boolean discarding;

    public Ringpuffer(int capacity, boolean fixedCapacity, boolean discarding) {
        this.elements = new ArrayList<T>();
        this.capacity = capacity;
        this.fixedCapacity = fixedCapacity;
        this.discarding = discarding;
    }

    public boolean setCapacity(int capacity) {
        if (!this.fixedCapacity) {
            this.capacity = capacity;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        T objectToFind;
        try {
            objectToFind = (T) o;
        } catch (Exception e) {
            return false;
        }
        for (T el : this.elements) {
            if (el.equals(objectToFind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return this.elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.toArrayList().toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return this.toArrayList().toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        int index = this.elements.indexOf(o);
        if (!this.elements.remove(o)) {
            return false;
        }
        if (index < this.readPos) {
            this.readPos--;
        }
        if (index < this.writePos) {
            this.writePos--;
        }
        this.size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object el : c) {
            if (!this.contains(el)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object t : c) {
            T el;
            try {
                el = (T) t;
            } catch (Exception e) {
                return false;
            }
            this.add(el);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        for (Object el : c) {
            while (this.remove(el)) {

            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        ArrayList<T> zuLoeschendeElemente = new ArrayList<T>();
        for (T el : this.elements) {
            if (!c.contains(el)) {
                zuLoeschendeElemente.add(el);
            }
        }
        for (T el : zuLoeschendeElemente) {
            this.remove(el);
        }
        return true;
    }

    @Override
    public void clear() {
        this.readPos = this.writePos;
        this.size = 0;
    }

    @Override
    public boolean add(T e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (this.size() == capacity) { // Wenn die maximalgroesse erreicht ist
            if (this.fixedCapacity) {
                if (!this.discarding) {
                    throw new IllegalStateException(); // wenn die Kapazitaet fest ist und werte nicht ueberschrieben
                                                       // werden duerfen
                }
            } else {
                this.capacity *= 2; // Kapazitaet verdoppeln
            }
        }
        if (this.elements.size() < this.capacity) { // ArrayList hat seine volle groesse noch nicht erreicht
            if (this.readPos >= this.writePos && this.size > 0) {
                this.readPos++;
            }
            this.elements.add(this.writePos, e);
        } else {
            this.elements.set(this.writePos, e);
        }
        this.size++;
        if (this.writePos >= this.capacity - 1) { // WritePos zuruecksetzten wenn zeiger am ende der liste
            this.writePos = 0;
        } else {
            this.writePos++;
        }
        return true;
    }

    @Override
    public boolean offer(T e) {
        try {
            this.add(e);
            return true;
        } catch (IllegalStateException exception) {
            return false;
        }
    }

    @Override
    public T remove() throws NoSuchElementException {
        T element = this.poll();
        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public T poll() {
        if (this.size == 0) {
            return null;
        }
        T result = this.elements.get(this.readPos);
        if (this.readPos >= this.capacity - 1) { // readPos zuruecksetzten wenn zeiger am ende der liste
            this.readPos = 0;
        } else {
            this.readPos++;
        }
        this.size--;
        return result;
    }

    @Override
    public T element() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.elements.get(this.readPos);
    }

    @Override
    public T peek() {
        if (this.size == 0) {
            return null;
        }
        return this.elements.get(this.readPos);
    }

    @Override
    public String toString() {
        return "WritePos" + this.writePos + "; ReadPos" + this.readPos + "; Size:" + this.size + "\t " + this.elements;
    }

    private ArrayList<T> toArrayList() {
        ArrayList<T> arraylist = new ArrayList<T>();
        for (int i = this.readPos; i < this.size && (i < this.writePos || this.writePos <= this.readPos); i++) {
            arraylist.add(this.elements.get(i));
        }
        if (this.readPos >= this.writePos) {
            for (int i = 0; i < this.writePos; i++) {
                arraylist.add(this.elements.get(i));
            }
        }
        return arraylist;
    }

}
