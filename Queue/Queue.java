package com.Queue;

public class Queue<T> {

    class QueueNode<T>{
        QueueNode<T> next;
        T data;
        public QueueNode(){}
        public QueueNode(T data){
            next=null;
            this.data=data;
        }
    }

    private QueueNode<T> front;
    private QueueNode<T> last;

    public Queue(){
        front=last=null;
    }
    public void enqueue(T data){

        QueueNode<T> node=new QueueNode<>(data);
        if(front==null){
            front=last=node;
        }else{
            last.next=node;
            last=node;
        }

    }

    public void clearQueue(){
        while (!isEmpty())
            dequeue();
    }

    public T dequeue(){
        if(front==null)return null;
        QueueNode<T> temp=front;
        front=front.next;
        return temp.data;
    }

    public boolean isFull(){
        return new QueueNode<>()==null;
    }
    public boolean isEmpty(){
        return front==null;
    }
}
