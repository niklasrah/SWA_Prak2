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
    private int size;
    private int capacity;
    private boolean fixedCapacity;
    private boolean discarding;

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
        return this.readPos == this.writePos;
    }

    @Override
    public boolean contains(Object o) {
        for (T el : this.elements) {
            T objectToFind;
            try {
                objectToFind = (T) o;
            } catch (Exception e) {
                return false;
            }
            if (el == objectToFind) {
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
        return null;
    }

    @Override
    public Object[] toArray(Object[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        this.readPos = this.writePos;
        this.size = 0;
    }

    @Override
    public boolean add(T e) {
        if (this.size() == capacity) { // Wenn die maximalgröße erreicht ist
            if (this.fixedCapacity) {
                if (!this.discarding) {
                    return false; // wenn die Kapazität fest ist und werte nicht überschrieben werden dürfen
                }
            } else {
                this.capacity *= 2; // Kapazität verdoppeln
            }
        }
        if (this.elements.size() < this.capacity) { // ArrayList hat seine volle größe noch nicht erreicht
            this.elements.add(this.writePos, e);
        } else {
            this.elements.set(this.writePos, e);
        }
        this.size++;
        if (this.writePos >= this.capacity - 1) { // WritePos zurücksetzten wenn zeiger am ende der liste
            this.writePos = 0;
        } else {
            this.writePos++;
        }
        return true;
    }

    @Override
    public boolean offer(Object e) {
        // TODO Auto-generated method stub
        return false;
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
        this.readPos++;
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
        return this.elements + " WritePos" + this.writePos + "; ReadPos" + this.readPos;
    }

}
