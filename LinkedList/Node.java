package com.LinkedList;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> nextNode;


    public Node(T data){
        this.data=data;
        this.nextNode=null;
    }
    public Node(){

    }
    public void setNextNode(Node<T> next){
        nextNode=next;
    }
    public Node<T> getNextNode(){
        return nextNode;
    }
    public void setData(T data){
        this.data=data;
    }
    public T getData(){
        return data;
    }
    public String toString(){
        return data.toString();
    }
}
