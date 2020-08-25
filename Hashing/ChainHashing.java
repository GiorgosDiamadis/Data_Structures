package com.Hashing;

import com.Arrays.Array;
import com.LinkedList.SinglyLinkedList;

public class ChainHashing<T extends Comparable<T>> {

    private final Array<SinglyLinkedList<T>> hashTable;//Array of lists
    private final int size;

    public ChainHashing(int size,T[] data){

        hashTable=new Array<>(size);
        this.size=size;
        for(int i=0;i<size;i++)
            hashTable.set(i, new SinglyLinkedList<>());
    }



    public void insert(T data){
        int index=hashFunction(data);
        SinglyLinkedList<T> list=hashTable.get(index);
        list.sortedInsert(data);
    }
    public T search(T data){
        int index=hashFunction(data);
        SinglyLinkedList<T> list=hashTable.get(index);
        return list.search(data);
    }
    public void delete(T data){
        int index=hashFunction(data);
        SinglyLinkedList<T> list=hashTable.get(index);
        list.delete(data);
    }
    private int hashFunction(T data){
        int index=Integer.parseInt(data.toString())%size;
        return index;
    }
}
