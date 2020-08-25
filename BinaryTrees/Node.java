package com.BinaryTrees;

public class Node<T extends Comparable<T>> {
    private T data;
    private int height;
    private Node<T> leftChild;
    private Node<T> rightChild;


    public Node(T data){
        this.data=data;
        height=1;
        leftChild=rightChild=null;
    }
    public int getHeight(){return height;}
    public void setHeight(int height){this.height=height;}
    public T getData() {
        return data;
    }
    public void setData(T data){
        this.data=data;
    }
    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
    public boolean isLeaf(){
        return leftChild==null && rightChild==null;
    }
    public String toString(){
        return data.toString();
    }
}