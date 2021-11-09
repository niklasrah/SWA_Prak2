package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Ringpuffer<T> implements Queue<T>, Serializable{

    private ArrayList<T> elements;
    private int writePos;
    private int readPos;
    private int size;
    private int capacity;
    private boolean fixedCapacity;
    private boolean discarding;

    public Ringpuffer(T t){
        this.elements = new ArrayList<T>();
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean add(Object e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean offer(Object e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object remove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object poll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object element() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object peek() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
