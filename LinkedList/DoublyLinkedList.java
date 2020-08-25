package com.LinkedList;

import com.Trees.BinaryTrees.BinarySearchTree;

import java.util.Iterator;

public class DoublyLinkedList<T extends Comparable<T>> implements Iterable {

    class ListNode<T>{
        ListNode<T> next;
        ListNode<T> prev;
        T data;

        public ListNode(T data){
            next=null;
            prev=null;
            this.data=data;
        }
        @Override
        public String toString() {
            return "{previous:"+(prev==null ? "null":prev.data)
                    +" current:"+data
                    +" next:"+(next==null ? "null":next.data)+"}";
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            ListNode<T> current;
            @Override
            public boolean hasNext() {
                if(iteratorNode!=null)
                    return true;
                return false;
            }

            @Override
            public void remove() {

            }

            @Override
            public Object next() {
               T temp=iteratorNode.data;
               iteratorNode=iteratorNode.next;
               return temp;
            }
        };
    }

    private ListNode<T> first;
    private ListNode<T> last;
    private ListNode<T> iteratorNode;

    public DoublyLinkedList(){}

    public DoublyLinkedList(BinarySearchTree<T> tree){
        DoublyLinkedList<T> listFromTree=tree.treeToAscendingSortedList();
        first=listFromTree.first;
        last=listFromTree.last;
        iteratorNode=first;
    }

    public int indexOf(T data){
        int i;
        ListNode<T> tmp;
        for(tmp=first,i=0;!tmp.data.equals(data);tmp=tmp.next,i++);
        return i;
    }

    public void insert(T data){
        if(first==null){
            first=new ListNode<>(data);
            last=first;
            iteratorNode=first;
            return;
        }

        ListNode<T> newNode=new ListNode<>(data);
        newNode.prev=last;
        last.next=newNode;
        last=newNode;
    }

    public void delete(T data){
        ListNode<T> tmp;
        for(tmp=first;!tmp.data.equals(data);tmp=tmp.next);
        tmp.prev.next=tmp.next;
        tmp.next.prev=tmp.prev;
    }
    public void printList(){
        for(ListNode<T> tmp = first; tmp!=null; tmp=tmp.next)
            System.out.println(tmp);
    }
    public void printListReverse(){
        for(ListNode<T> tmp = last; tmp!=null; tmp=tmp.prev)
            System.out.println(tmp);
    }
}
