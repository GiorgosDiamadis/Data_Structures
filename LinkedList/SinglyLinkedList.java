package com.LinkedList;


public class SinglyLinkedList<T extends Comparable<T>> implements Iterable<T>{


    class ListNode<T> {
        T data;
        ListNode<T> nextNode;

        public ListNode(T data){
            this.data=data;
            this.nextNode=null;
        }
        public void setNextNode(ListNode<T> next){
            nextNode=next;
        }
        public ListNode<T> getNextNode(){
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

    class Iterator implements java.util.Iterator<T> {
        private ListNode<T> iteratorNode;
        private ListNode<T> previous;

        public Iterator(){
            iteratorNode=first;
            previous=null;
        }
        @Override
        public boolean hasNext() {
            return iteratorNode!=null;
        }

        @Override
        public void remove() {
            if(previous==first){
                first=iteratorNode;
                return;
            }
            delete(previous.data);
        }

        @Override
        public T next() {
            T temp=iteratorNode.data;
            previous=iteratorNode;
            iteratorNode=iteratorNode.nextNode;
            return temp;
        }
    }

    private ListNode<T> first;
    private ListNode<T> last;

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    public T getFirst(){
        return first!=null ? first.data : null;
    }
    public T getLast(){
        return last.data;
    }

    public SinglyLinkedList(){ }

    public int indexOf(T data){
        int i=0;
        for(ListNode<T> tempNode = first; tempNode!=null; tempNode=tempNode.getNextNode()){
            i++;
        }
        return i;
    }

    public T get(int index){
        ListNode<T> temp=first;
        for(int i=0;i<index;i++)
            temp=temp.getNextNode();
        return temp.getData();
    }

    public void reverseList(){

        ListNode<T> placeHolder=last;
        ListNode<T> tempNode=first;
        ListNode<T> previous=null;
        ListNode<T> nextNode=null;

        while(tempNode!=null){
            nextNode=tempNode.getNextNode();
            tempNode.setNextNode(previous);
            previous=tempNode;
            tempNode=nextNode;
        }

        last=first;
        first=placeHolder;
    }
    public T getMiddleNode(){
        ListNode<T> currentNode=first;
        ListNode<T> nextNode=first;
        while (nextNode!=null) {
            nextNode=nextNode.getNextNode();
            if(nextNode!=null)nextNode=nextNode.getNextNode();
            if(nextNode!=null)currentNode=currentNode.getNextNode();
        }
        return currentNode.getData();
    }
    public T intersectingPoint(SinglyLinkedList<T> list){
        for(ListNode<T> tempNode = first; tempNode!=null; tempNode=tempNode.getNextNode()){
            if(tempNode.getData().equals(list.last.getNextNode().getData()))
                return tempNode.getData();
        }
        return null;
    }
    public void concatinate(SinglyLinkedList<T> list){
        last.setNextNode(list.first);
        list.first=null;
        removeDuplicates(first);
    }
    public void sortList(){
        for(ListNode<T> tempNode = first; tempNode!=null&& tempNode.getNextNode()!=null; tempNode=tempNode.getNextNode()){
            if(tempNode.getData().compareTo(tempNode.getNextNode().getData())>0){
                T data=tempNode.getData();
                tempNode.setData(tempNode.getNextNode().getData());
                tempNode.getNextNode().setData(data);

            }
        }
        if(!checkIfSorted())
            sortList();
    }
    public T search(T data){
        for(ListNode<T> tempNode = first; tempNode!=null; tempNode=tempNode.getNextNode()){
            if(tempNode.getData().equals(data))
                return tempNode.getData();
        }
        return null;
    }
    public boolean checkIfSorted(){
        for(ListNode<T> tempNode = first; tempNode!=null && tempNode.getNextNode()!=null; tempNode=tempNode.getNextNode()){

            if(tempNode.getData().compareTo(tempNode.getNextNode().getData())>0)
                return false;
        }
        return  true;
    }
    public void sortedInsert(T data){
        ListNode<T> t,q=null,current=first;
        t=new ListNode<>(data);
        if(first==null) {
            first = t;
        }

        else{
            while(current!=null && current.getData().compareTo(data)<0){
                q=current;
                current=current.getNextNode();
            }
            if(current==first){
                t.setNextNode(first);
                first=t;
            }else{
                t.setNextNode(q.getNextNode());
                q.setNextNode(t);
            }
        }
    }
    public void insertLast(T data){
        if(search(data)!=null){
            return;
        }
        if(first==null){
            first=new ListNode<>(data);
            last=first;
            return;
        }
        last.setNextNode(new ListNode<>(data));
        last=last.getNextNode();
    }
    public void insertFirst(T data){
        if(first==null){
            first=new ListNode<>(data);
            last=first;
            return;
        }
        ListNode<T> newNode=new ListNode<>(data);
        newNode.setNextNode(first);
        first=newNode;
    }
    private void removeDuplicates(ListNode<T> currentNode){
        if(currentNode==null)return;
        ListNode<T> nextNode=currentNode.getNextNode();
        ListNode<T> previous=currentNode;
        while(nextNode!=null){
            if(!currentNode.getData().equals(nextNode.getData())){
                previous=nextNode;
                nextNode=nextNode.getNextNode();
            }else{
                previous.setNextNode(nextNode.getNextNode());
                nextNode=null;
                break;
            }
        }
        removeDuplicates(currentNode.getNextNode());
    }
    public void insertAtPosition(int position,T data){

        if(position-1>=count())return;
        if(search(data)!=null){
            return;
        }

        ListNode<T> tmpNode=first;
        ListNode<T> newNode=new ListNode<>(data);

        if(position==0){
            newNode.setNextNode(first);
            first=newNode;
            return;
        }

        for(int i=0;i<position-1;i++)
            tmpNode=tmpNode.getNextNode();

        if(tmpNode.getNextNode()==null)
        {
            insertLast(data);
        }
        else
        {
            newNode.setNextNode(tmpNode.getNextNode());
            tmpNode.setNextNode(newNode);
        }

    }

    public void deleteAt(int index){
        delete(get(index));
    }

    public void delete(T data){
        ListNode<T> tempNode=first;
        ListNode<T> previous=null;

        if(tempNode.getData().equals(data)){
            first=first.getNextNode();
            return;
        }

        while(tempNode!=null){
            if(tempNode.getData().equals(data)){

                if(tempNode.getNextNode()==null){
                    previous.setNextNode(null);
                    last=previous;
                    return;
                }

                previous.setNextNode(tempNode.getNextNode());
                tempNode=null;
                return;
            }
            previous=tempNode;
            tempNode=tempNode.getNextNode();
        }
    }
    public int count(){
        int elements=0;
        for(ListNode<T> tempNode = first; tempNode!=null; tempNode=tempNode.getNextNode())
            elements++;
        return elements;
    }
    public void display(){
        for(ListNode<T> tempNode = first; tempNode!=null; tempNode=tempNode.getNextNode())
            System.out.print(tempNode.getData()+" ");
        System.out.println();
    }
    public String toString(){
        String list="";
        for(ListNode<T> tempNode = first; tempNode!=null; tempNode=tempNode.getNextNode()){
            list+=tempNode;
            list+="=>";
        }
        list+="NULL";
        return list;
    }
}