package com.Stack;


public class Stack<T> {

    static class StackNode<T>{
        StackNode<T> next;
        T data;

        public StackNode(){}
        public StackNode(T data){
            this.data=data;
            next=null;
        }
    }

    private StackNode<T> top;

    public void push(T data){
        if(top==null){
            top= new StackNode<>(data);
            return;
        }

        if(isFull())return;

        StackNode<T> temp= new StackNode<>(data);
        temp.next=top;
        top=temp;
    }
    public T pop(){
        if(isEmpty())return null;

        T data=top.data;
        top=top.next;
        return  data;
    }
    public T peek(int position){
        int i;
        StackNode<T> temp;

        for(temp=top,i=0;i<=position;i++,temp=temp.next);

        if (temp==null)return null;
        return temp.data;
    }

    public T getTop(){
        if(top==null)return null;
        return top.data;
    }
    public boolean isEmpty(){
        return top==null;
    }
    public boolean isFull(){
        return new StackNode<>() ==null;
    }
}