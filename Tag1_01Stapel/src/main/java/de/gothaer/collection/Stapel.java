package de.gothaer.collection;

import java.util.ArrayList;
import java.util.List;

public class Stapel {

    private List<Integer>data = new ArrayList<Integer>();

    public Stapel(){

    }

    public void push(int value) throws StapelException{
        if(isFull()) throw new StapelException("Overflow");
        data.add(value);
    }

    public int pop() throws StapelException{
        if(isEmpty()) throw new StapelException("Underflow");
        return data.remove(data.toArray().length - 1);
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public boolean isFull(){
        return false;
    }
}
