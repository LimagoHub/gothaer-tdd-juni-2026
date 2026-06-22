package de.gothaer.collection;

public class Stapel {

    private int []data;
    private int index;
    public Stapel(){
        this.data=new int[10];
        index=0;
    }

    public void push(int value) throws StapelException{
        if(isFull()) throw new StapelException("Overflow");
        data[index]=value;
        index++;
    }

    public int pop() throws StapelException{
        if(isEmpty()) throw new StapelException("Underflow");
        return data[--index];
    }

    public boolean isEmpty(){
        return index==0;
    }

    public boolean isFull(){
        return index==data.length;
    }
}
