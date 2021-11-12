package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Ringpuffer<T> implements Queue<T>, Serializable{

    private ArrayList<T> elements;
    private int writePos = 0;
    private int readPos = 0;
    private int size;
    private int capacity;
    private boolean fixedCapacity;
    private boolean discarding;

    public Ringpuffer(int capacity, boolean fixedCapacity, boolean discarding){
        this.elements = new ArrayList<T>();
        this.capacity = capacity;
        this.fixedCapacity = fixedCapacity;
        this.discarding = discarding;
    }

    public boolean setCapacity(int capacity) {
        if (!this.fixedCapacity){
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray(Object[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
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

        if(this.size() == capacity){
            if(this.fixedCapacity){
                if(!this.discarding){
                    return false;
                }
            } else {
                this.capacity *= 2;
            }
        }
        if (this.elements.size() < this.capacity){
            //add
        } else {
            //set
        }
        try{
            this.elements.set(this.writePos, e);
        }catch(IndexOutOfBoundsException exception){
            this.elements.add(this.writePos, e);
        }
        this.size++;
        if(this.writePos >= this.capacity - 1){
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
    public T remove() {
        T result = this.elements.get(this.readPos);
        this.readPos++;
        this.size--;
        return result;
    }

    @Override
    public T poll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T element() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T peek() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString(){
        return this.elements + " WritePos" + this.writePos + "; ReadPos" + this.readPos;
    }

}
